
public class Passwords {

    private static String fileName;
    private static int passwordLength;
    private static int noOfPasswords;

    public static void main(String[] args) {
        if(!parseArgs(args)) {
            return;
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
