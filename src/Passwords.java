
public class Passwords {

    private static String fileName;
    private static int passwordLength;
    private static int noOfPasswords;
    private static Table table;
    private static PasswordGenerator generator;

    public static void main(String[] args) {

        if(!parseArgs(args)) {
            return;
        }

        table = new Table();
        generator = new PasswordGenerator();
        table.generateTable(fileName);
        System.out.println("\nFollowers Table : ");
        table.printFollowers();
        System.out.println();
        System.out.println("Counts : ");
        table.printCounts();
        System.out.println();
        System.out.println("Starters : ");
        table.printStarters();
        if(!table.writeToFile(true)) {
            System.out.println("write failed");
        }
        generator.setFollowers(table.getFollowers());
        generator.setCounts(table.getCounts());
        generator.setStarters(table.getStarters());
        System.out.println();
        for(int i = 0; i < noOfPasswords; i++) {
            String temp = generator.generatePassword(passwordLength);
            if(temp == null) {
                return;
            }
            System.out.println(temp);
        }

    }

    private static boolean parseArgs(String[] args) {
        if(args.length != 3) {
            System.out.println("Error: Incorrect number of arguments");
            return false;
        }
        fileName = args[0];
        noOfPasswords = Integer.parseInt(args[1]);
        passwordLength = Integer.parseInt(args[2]);
        return true;
    }

}
