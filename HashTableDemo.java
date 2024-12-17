import java.io.*;
import java.net.*;
import java.util.*;

public class HashTableDemo {

    public static boolean isStrongPassword(String password) {
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        boolean isLongEnough = password.length() >= 12;
        return hasUpper && hasLower && hasDigit && hasSpecial && isLongEnough;
    }

    public static List<String> fetchWordList(String urlString) throws IOException {
        List<String> words = new ArrayList<>();
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                words.add(line);
                lineNumber++;
            }
        }
        return words;
    }


    public static void main(String[] args) {
        try {
            String wordListUrl = "https://www.mit.edu/~ecprice/wordlist.10000";
            List<String> words = fetchWordList(wordListUrl);

            int M1 = 1000, M2 = 20000;
            HashFunction currentHashFunction = new CurrentHashFunction();
            SCHashTable separateChaining = new SCHashTable(M1, currentHashFunction);
            LPHashTable linearProbing = new LPHashTable(M2, currentHashFunction);

            int line = 1;
            for (String word : words) {
                separateChaining.insert(word, line);
                linearProbing.insert(word, line);
                line++;
            }

            String[] passwords = {
                "account8",
                "accountability",
                "9a$D#qW7!uX&Lv3zT",
                "B@k45*W!c$Y7#zR9P",
                "X$8vQ!mW#3Dz&Yr4K5"
        };

        System.out.println("Password Analysis");
        for (String password : passwords) {
            String strength = isStrongPassword(password) ? "Strong" : "Weak";
            System.out.println("Password: " + password + " | Strength: " + strength);

            int separateChainingCost = separateChaining.search(password);
            int linearProbingCost = linearProbing.search(password);

            System.out.println("  Separate Chaining (HashCode): " + separateChainingCost + " comparisons");
            System.out.println("  Linear Probing (HashCode): " + linearProbingCost + " comparisons");
        }
    }
    
    catch (IOException e) {
        System.err.println("Error fetching word list: " + e.getMessage());
    }

}

}
