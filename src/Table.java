import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Table {

    //followers keeps track of frequency of a character following another.
    private int[][] followers = new int[26][26];
    private int[] counts = new int[26];
    private int[] starters = new int[26];

    public boolean generateTable(String filepath) {
        File inputFile = new File(filepath);
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile);
        }
        catch (IOException e) {
            System.out.println("Error- Unable to open text file");
            return false;
        }

        while(scanner.hasNextLine()) {
            boolean prevSpace = true; //If previous character was a space. Helps determine if starter
            String line = scanner.nextLine();
            line.toLowerCase();
            //Not considering a character that follows one onto the next line as a follower
            for(int i = 0; i < line.length() - 1; i++) {
                char currentChar = line.charAt(i);
                char nextChar = '~'; //dummy initialization
                if(Character.isSpaceChar(currentChar)) {
                    prevSpace = true;
                    continue;
                }
                if(!Character.isLetter(currentChar)) {
                    continue;
                }
                counts[currentChar - 'a']++;
                if(prevSpace) {
                    prevSpace = false;
                    starters[currentChar - 'a']++;
                }
                //Second loop here but most of the time will not go through to completion
                for(int j = i + 1; j < line.length(); j++) {
                    nextChar = line.charAt(j);
                    if(Character.isLetter(nextChar)) {
                        break;
                    }
                    if(Character.isSpaceChar(nextChar)) {
                        //Not considering letters of the next word as followers
                        break;
                    }
                }
                if(Character.isSpaceChar(nextChar)) {
                    continue;
                }
                //No more letters in line, so continue to next one
                if(!Character.isLetter(nextChar)) {
                    break; //will not work or will need additional steps if more in while after this loop
                }
                followers[currentChar - 'a'][nextChar - 'a']++;
            }
        }


        return true;
    }

    public void printFollowers() {
        //code to print table
    }

    public void printCounts() {

    }

    public void printStarters() {

    }
}
