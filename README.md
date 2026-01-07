# Word-Search-Solver  
A Java-based word search solver that locates target words within a character grid across all 8 directions (horizontal, vertical, and diagonal), supporting both forward and backward matches. Correctness is validated using JUnit 5 tests and the project is built with Apache Maven.

## Features  
- Searches in 8 directions: N, NE, E, SE, S, SW, W, NW
- Supports forward and reverse word matching
- Reads a grid + word list from a text file
- Prints each word with the start and end coordinates (row:col)
- Includes JUnit tests for directional cases and edge cases

## Input Format  
The program accepts a single text file as a command-line argument.

- Line 1: grid dimensions in the form `ROWSxCOLS` (example: `3x3`)
- Next `ROWS` lines: the character grid, with characters separated by spaces
- Remaining lines: words to find (whitespace is ignored)

Example:  
3x3  
A B C  
D E F  
G H I  
ABC  
AEI  

## Output Format
For each word, the program prints:  
WORD startRow:startCol endRow:endCol  
Example:  
ABC 0:0 0:2  
AEI 0:0 2:2  

### Requirements
- Java 24
- Apache Maven


## Notes
- Assumes correctly formatted input files.
- The search logic checks all directions from each grid cell and prints the first match found for each word.
