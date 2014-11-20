import java.util.Random;

public class PasswordGenerator {
    private int[][] followers = new int[26][26];
    private int[] counts = new int[26];
    private int[] starters = new int[26];
    private int startersSum = 0;
    Random random = new Random();

    public boolean setFollowers(int[][] fl) {
        if(fl.length != 26 || fl[0].length != 26) {
            return false;
        }

        for(int row = 0; row < 26; row++) {
            for(int col = 0; col < 26; col++) {
                followers[row][col] = fl[row][col];
            }
        }
        return true;
    }

    public boolean setCounts(int[] ct) {
        if(ct.length != 26) {
            return false;
        }

        for(int i = 0; i < 26; i++) {
            counts[i] = ct[i];
        }
        return true;
    }

    public boolean setStarters(int[] st) {
        if(st.length != 26) {
            return false;
        }

        for(int i = 0; i < 26; i++) {
            starters[i] = st[i];
            startersSum += starters[i];
        }

        //System.out.println("Stater sum = " + startersSum);

        return true;
    }

    public String generatePassword(int len) {
        String password = "";
        //generate a random number from 0 to starterSum
        int rand = random.nextInt(startersSum);
        //System.out.println("rand : " + rand);
        int i, sum = 0;
        for(i = 0; i < 26; i++) {
            sum += starters[i];
            //System.out.println("sum : " + sum);
            if(sum > rand) {
                password += (char)('a' + i);
                break;
            }
        }
        if(i == 26) {
            System.out.println("Error generating starter");
            return null;
        }

        while(password.length() != len) {
            sum = 0;
            char curr = password.charAt(password.length() - 1);
            //System.out.println("curr : " + curr);
            rand = random.nextInt(counts[curr - 'a']);
           // System.out.println("rand : " + rand);
            for(i = 0; i < 26; i++) {
                sum += followers[curr - 'a'][i];
               // System.out.println("sum : " + sum);
                if(sum > rand) {
                    password += (char)('a' + i);
                    break;
                }
            }
            if(i == 26) {
                System.out.println("Error generating character");
                return null;
            }
        }

        return password;
    }


}
