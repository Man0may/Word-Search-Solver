/** 
 * Author: Manomay Bhavani 
 * Date: 10/12/2025
 * 
 * Description:
 * This is a word search solver which takes in a file with the word searches dimensions, the grid, and the hidden words.
 * The following conditions apply: Within the grid of characters, the words may appear vertical, horizontal or diagonal.
 * Within the grid of characters, the words may appear forwards or backwards. After finding the words, the program will output
 * the coordinates of the first and last letter of each word. The solution was built and executed using 
 * Apache Maven 3.9.11 with JDK 24 runtime.
 * 
 * Input:
 * - The Grid Dimensions are given on line one.
 * - The Grid of characters is given on the next lines.
 * - The words to be found are given on the following lines.
 * 
 * Output:
 * - The output is the word followed by the coordinates of the first and last letter in the given format
 * 
 * Approach:
 * 1. Take in Input File and Parse
 *  a. First Line used for parsing the character grid
 *  b. Parse Grid and store in 2D Array
 *  c. Parse words and store in Array
 * 2. Search for words in the grid, one word at a time
 *  a. For each char, search the grid in all 8 directions for words
 *  b. Use a function for each specific direction
 *  c. If word is found, store the coordinates of the first and last letter
 *  d. Store the coordinates in a 2D Array
 * 3. Print the coordinates of each word in the specified format
 *  a. Iterate through the 2D Array and print the coordinates
 *  b. Ensure formatting matches the requirements
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class AlphabetSoup {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 1) {
            System.out.println("Please provide the input file as a command line argument.");
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        File wordSearch = new File(args[0]);
        Scanner input = new Scanner(wordSearch);
        String firstLine = input.nextLine();
        /* Parse the first line to get the dimensions of the grid */
        String[] dimensions = firstLine.split("x");
        int[] dim = new int[2];
        dim[0] = Integer.parseInt(dimensions[0]);
        dim[1] = Integer.parseInt(dimensions[1]);
        /*  Parse the rest of the input file for the grid and words */
        char[][] charGrid = new char[dim[0]][dim[1]];
        ArrayList<String> wordList = new ArrayList <String>();
        inputParser(input, charGrid, wordList);
        /* This list is for the different directions */
        int[][] dirList = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
        /* Search for each word in the list at a time*/
        for(String word : wordList) {
            /* Iterates through the entire grid to search for the word*/
            boolean found = false;
            for(int row = 0; row < charGrid.length; row++) {
                if(found) row = charGrid.length; // Breaks out of the loop if the word is found
                for(int col = 0; col < charGrid[0].length; col++) {
                    /* Searches in every direction for each character in the grid */
                    if(found) col = charGrid[0].length; // Breaks out of the loop if the word is found
                    for(int dirIndex = 0; dirIndex < dirList.length; dirIndex++) {
                        int rowDir = dirList[dirIndex][0];
                        int colDir = dirList[dirIndex][1];
                        int[] result = findWord(charGrid, word, row, col, rowDir, colDir);
                        if (result != null) {
                            System.out.println(word + " " + result[0] + ":" + result[1] + " " + result[2] + ":" + result[3]);
                            found = true;
                        }
                        if(found) dirIndex = dirList.length; // Breaks out of the loop if the word is found
                    }
                }
            }
        }
    }
    
    /* The inputParser function takes in the Scanner object, the 2D char array, and the ArrayList of words
    It fills the char array with the grid and the ArrayList with the words to search */
    public static void inputParser(Scanner input, char[][] charGrid, ArrayList<String> wordList) {
        /*  Places the Word Search grid into a 2D char array to be searched */
        for (int row = 0; row < charGrid.length; row++) {
            String line = input.nextLine();
            int col = 0;
            for (int iterator = 0; iterator < line.length(); iterator++) {
                if(line.charAt(iterator) != ' ') {
                    charGrid[row][col] = line.charAt(iterator);
                    col++;
                }
            }
        }
        /* Places the words to be searched into an ArrayList due to an unspecified quantity for words*/
        while (input.hasNextLine()) {
            String word = input.nextLine().replaceAll("\\s+", ""); // Removes White Space
            wordList.add(word);
        }
    }

    /* This findWord function is the directional search function, it looks for the word and direction is determined by the parameters*/
    public static int[] findWord(char[][] charGrid, String word, int startRow, int startCol, int rowDir, int colDir) {
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < word.length(); i++) {
            if (row < 0 || row >= charGrid.length || col < 0 || col >= charGrid[0].length || charGrid[row][col] != word.charAt(i)) {
                return null; // Word not found in this direction
            }
            row += rowDir;
            col += colDir;
        }
        // Needed to go back one step
        int endRow = row - rowDir;
        int endCol = col - colDir;
        return new int[]{startRow, startCol, endRow, endCol};
    }
}