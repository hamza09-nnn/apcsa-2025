 package piglatin;

import java.io.*;
import java.net.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // Download and translate Romeo and Juliet
        processBook("https://www.gutenberg.org/cache/epub/1513/pg1513.txt", 
                    "pg1513.txt", "pg1513_piglatin.txt", "Romeo and Juliet");
        
        // Download and translate Frankenstein
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
                String translatedLine = translateToPigLatin(line);
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

    public static String translateToPigLatin(String sentence) {
        if (sentence.trim().isEmpty()) return "";

        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (word.length() == 0) continue;

            String punctuation = "";
            String coreWord = word;

            // Handle punctuation at the end
            if (!Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
                punctuation = word.substring(word.length() - 1);
                coreWord = word.substring(0, word.length() - 1);
            }

            String translated = translateWord(coreWord);
            sb.append(translated).append(punctuation).append(" ");
        }

        return sb.toString().trim();
    }

    private static String translateWord(String word) {
        if (word.length() == 0) return "";

        boolean firstUpper = Character.isUpperCase(word.charAt(0));
        String lowerWord = word.toLowerCase();

        String pigLatin;
        
        // Move all leading consonants to the end
        int firstVowelIndex = -1;
        for (int i = 0; i < lowerWord.length(); i++) {
            if ("aeiou".indexOf(lowerWord.charAt(i)) != -1) {
                firstVowelIndex = i;
                break;
            }
        }

        if (firstVowelIndex == 0) {
            // Starts with vowel
            pigLatin = lowerWord + "ay";
        } else if (firstVowelIndex > 0) {
            // Starts with consonants
            pigLatin = lowerWord.substring(firstVowelIndex) + lowerWord.substring(0, firstVowelIndex) + "ay";
        } else {
            // No vowels (unlikely but handle it)
            pigLatin = lowerWord + "ay";
        }

        // Preserve capitalization
        if (firstUpper) {
            pigLatin = Character.toUpperCase(pigLatin.charAt(0)) + pigLatin.substring(1).toLowerCase();
        }

        return pigLatin;
    }
}