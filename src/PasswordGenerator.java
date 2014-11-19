
public class PasswordGenerator {
    private int[][] followers = new int[26][26];
    private int[] counts = new int[26];
    private int[] starters = new int[26];

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
        }
        return true;
    }

    public String generatePassword(int len) {
        String password = "";
        

        return password;
    }


}
