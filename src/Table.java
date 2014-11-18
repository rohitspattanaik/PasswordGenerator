import java.io.*;
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
            line = line.toLowerCase();
            //System.out.println(line);
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
                else {
                    if(!(currentChar >= 'a' && currentChar <= 'z')) {
                        continue;
                    }
                }
                //System.out.println(currentChar);
                counts[currentChar - 'a']++;
                if(prevSpace) {
                    prevSpace = false;
                    starters[currentChar - 'a']++;
                }
                //Second loop here but most of the time will not go through to completion
                for(int j = i + 1; j < line.length(); j++) {
                    nextChar = line.charAt(j);
                    if(Character.isLetter(nextChar)) {
                        //System.out.print(nextChar + " ");
                        //System.out.println((int)nextChar);
                        if(!(nextChar >= 'a' && nextChar <= 'z')) {
                            continue;
                        }
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
        System.out.print("  \t");
        int i;
        for(i = 0; i < 26; i++) {
            System.out.printf("%6s", (char)('A' + i));   //Allows for max of 3 digits in each cell before overflow
        }
        System.out.println();
        for(i = 0; i < 26; i++) {
            for(int j = -1; j < 26; j++) {
                if(j == -1) {
                    System.out.print((char)('A' + i) + ":\t");
                    continue;
                }
                System.out.printf("%6d", followers[i][j]);

            }
            System.out.println();
        }
    }

    public void printCounts() {
        for(int i = 0; i < 26; i++) {
            System.out.printf("%s : %6d\n", (char)('A' + i), counts[i]);
        }
    }

    public void printStarters() {
        for(int i = 0; i < 26; i++) {
            System.out.printf("%s : %6d\n", (char)('A' + i), starters[i]);
        }
    }

    /* Allows for the tables to be written to a file
     * pretty indicates whether the output to the file should be formatted or not
     * Reading from the file for later processing will be easier if pretty=false
     * because the output will be in a predictable format
     */
    public boolean writeToFile(boolean pretty) {
        String fileName = "tables.data";
        Writer writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
        try {
            if(pretty) {
                //formatted output
            }
            else {
                for(int row = 0; row < 26; row++) {
                    for(int col = 0; col < 26; col++) {
                        writer.write(Integer.toString(followers[row][col]));
                        writer.write('\n');
                    }
                }
                for(int i = 0; i < 26; i++) {
                    writer.write(Integer.toString(counts[i]));
                    writer.write('\n');
                }
                for(int i = 0; i < 26; i++) {
                    writer.write(Integer.toString(starters[i]));
                    writer.write('\n');
                }
            }
            writer.close();

        }
        catch(IOException e) {
            System.out.println("IOException encounter. Aborting table write");
            return false;
        }

        return true;
    }
}
