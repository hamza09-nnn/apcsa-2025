package piglatin;

import java.io.*;
import java.net.*;
import java.util.*;

public class PigLatinTranslator {
    public static void main(String[] args) {
        System.out.println("Running tests...\n");
        TestSuite.run();
        System.out.println();
        
        processBook("https://www.gutenberg.org/cache/epub/1513/pg1513.txt", 
                    "pg1513.txt", "pg1513_piglatin.txt", "Romeo and Juliet");
        
        processBook("https://www.gutenberg.org/cache/epub/84/pg84.txt", 
                    "pg84.txt", "pg84_piglatin.txt", "Frankenstein");
    }
    
    private static void processBook(String urlString, String inputFileName, 
                                     String outputFileName, String bookTitle) {
        File inputFile = new File(inputFileName);
        
        try {
            System.out.println("Downloading " + bookTitle + "...");
            downloadFile(urlString, inputFile);
            System.out.println("Download complete!");
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
            return;
        }

        File outputFile = new File(outputFileName);

        try (Scanner scanner = new Scanner(inputFile);
             PrintWriter writer = new PrintWriter(outputFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String translatedLine = translate(line);
                writer.println(translatedLine);
            }

            System.out.println("Translation complete! Saved as: " + outputFile.getName());

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void downloadFile(String urlString, File outputFile) throws IOException {
        URI uri = URI.create(urlString);
        URL url = uri.toURL();
        
        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(outputFile)) {
            
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    public static String translate(String input) {
        if (input.trim().isEmpty()) return "";

        String[] words = input.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (word.length() == 0) continue;
            sb.append(translateWord(word)).append(" ");
        }

        return sb.toString().trim();
    }

    private static String translateWord(String word) {
        if (word.isEmpty()) return "";

        String punctuation = "";
        int len = word.length();
        while (len > 0 && !Character.isLetterOrDigit(word.charAt(len - 1))) {
            punctuation = word.charAt(len - 1) + punctuation;
            len--;
        }
        String coreWord = word.substring(0, len);

        if (coreWord.contains("-")) {
            int hyphenPos = coreWord.indexOf("-");
            String firstPart = coreWord.substring(0, hyphenPos);
            String secondPart = coreWord.substring(hyphenPos + 1);
            
            int firstVowel = 0;
            String lowerFirst = firstPart.toLowerCase();
            while (firstVowel < lowerFirst.length() &&
                    "aeiou".indexOf(lowerFirst.charAt(firstVowel)) == -1) {
                firstVowel++;
            }
            
            String translatedFirst;
            if (firstVowel == 0) {
                translatedFirst = firstPart;
            } else {
                translatedFirst = firstPart.substring(firstVowel);
            }
            
            return translatedFirst.toLowerCase() + "-" + secondPart + "clay" + punctuation;
        }

        if (coreWord.isEmpty()) return punctuation;

        boolean onlyFirstCap = Character.isUpperCase(coreWord.charAt(0));
        for (int i = 1; i < coreWord.length(); i++) {
            if (Character.isUpperCase(coreWord.charAt(i))) {
                onlyFirstCap = false;
                break;
            }
        }

        String lowerWord = coreWord.toLowerCase();
        
        int firstVowelIndex = 0;
        while (firstVowelIndex < lowerWord.length() &&
                "aeiou".indexOf(lowerWord.charAt(firstVowelIndex)) == -1) {
            firstVowelIndex++;
        }

        String translated;
        if (firstVowelIndex == 0) {
            translated = lowerWord + "ay";
        } else {
            String consonantCluster = lowerWord.substring(0, firstVowelIndex);
            String rest = lowerWord.substring(firstVowelIndex);
            translated = rest + consonantCluster + "ay";
        }

        if (onlyFirstCap) {
            translated = Character.toUpperCase(translated.charAt(0)) + translated.substring(1);
        } else {
            char[] resultChars = translated.toLowerCase().toCharArray();
            
            for (int i = firstVowelIndex; i < coreWord.length(); i++) {
                if (Character.isUpperCase(coreWord.charAt(i))) {
                    int newPos = i - firstVowelIndex;
                    if (newPos < resultChars.length) {
                        resultChars[newPos] = Character.toUpperCase(resultChars[newPos]);
                    }
                }
            }
            translated = new String(resultChars);
        }

        return translated + punctuation;
    }
}