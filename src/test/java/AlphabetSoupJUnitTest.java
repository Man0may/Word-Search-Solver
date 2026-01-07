/**
 * Author: Manomay Bhavani 
 * Date: 12/12/2025
 * 
 * Description:
 * These are the JUnit Tests used to validate the code. Testing different types of directions as well as 
 * different types of grids in a word search.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlphabetSoupJUnitTest {
    // These are general directional search tests
    @Test
    void verticalBackward() {
        char[][] grid = {{'Q','B','C'}, {'B','E','F'}, {'P','H','I'}};
        int[] result = AlphabetSoup.findWord(grid, "PBQ", 2, 0, -1, 0);
        assertArrayEquals(new int[]{2,0,0,0}, result);
    }
    @Test
    void backwardUpLeft() {
        char[][] grid = {{'Q','W','E'}, {'R','T','Y'}, {'U','I','O'}};
        int[] result = AlphabetSoup.findWord(grid, "OTQ", 2, 2, -1, -1);
        assertArrayEquals(new int[]{2,2,0,0}, result);
    }
    @Test
    void horizontalForward() {
        char[][] grid = {{'A','B','C'}, {'D','E','F'}, {'G','H','I'}};
        int[] result = AlphabetSoup.findWord(grid, "ABC", 0, 0, 0, 1);
        assertArrayEquals(new int[]{0,0,0,2}, result);
    }
    @Test
    void diagonalForward() {
        char[][] grid = {{'A','B','C'}, {'D','E','F'}, {'G','H','I'}};
        int[] result = AlphabetSoup.findWord(grid, "AEI", 0, 0, 1, 1);
        assertArrayEquals(new int[]{0,0,2,2}, result);
    }
    
    // These test edge cases in non ideal word conditions
    @Test
    void repeatLetters() {
        char[][] grid = {{'A','A','A'}, {'A','A','A'}, {'A','A','A'}};
        int[] result = AlphabetSoup.findWord(grid, "AAA", 0, 0, 0, 1);
        assertArrayEquals(new int[]{0,0,0,2}, result);
    }
    @Test
    void wordNotHere() {
        char[][] grid = {{'A','B','C'}, {'D','E','F'}, {'G','H','I'}};
        int[] result = AlphabetSoup.findWord(grid, "XYZ", 0, 0, 0, 1);
        assertNull(result);
    }
    @Test
    void wordTooBig() {
        char[][] grid = {{'A','B'}, {'C','D'}};
        int[] result = AlphabetSoup.findWord(grid, "ABCDQ", 0, 0, 0, 1);
        assertNull(result);
    }
    
    // These test non-regular grid sizes
    @Test
    void onebyone() {
        char[][] grid = {{'Q'}};
        int[] result = AlphabetSoup.findWord(grid, "Q", 0, 0, 0, 0);
        assertArrayEquals(new int[]{0,0,0,0}, result);
    }
    @Test
    void rectangle() {
        char[][] grid = {{'A','B','X','Y','Z'}, {'F','G','H','I','J'}};
        int[] result = AlphabetSoup.findWord(grid, "XYZ", 0, 2, 0, 1);
        assertArrayEquals(new int[]{0,2,0,4}, result);
    }
    
}