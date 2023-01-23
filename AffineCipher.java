import java.util.HashMap;

public class AffineCipher {
    
    /** 
     * @param key1
     * @return int
     */
    public static int multiplicative_inverse(int key1)
    {
        for(int i = 0; i < 26; i++)
            {
                if((key1*i)%26 == 1)
                    return i;
            }
        return 0;
    }
    
    /** 
     * @param ciphertext
     */
    public static void affineBreak(String ciphertext) {
        HashMap<Character, Integer> frequency = new HashMap<>();

        for(int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if(frequency.containsKey(c)) {
                frequency.put(c, frequency.get(c) + 1);
            } else {
                frequency.put(c, 1);
            }
        }

        char mostCommon = ' ';
        int mostCommonValue = 0;
        for(char c : frequency.keySet()) {
            if(frequency.get(c) > mostCommonValue) {
                mostCommon = c;
                mostCommonValue = frequency.get(c);
            }
        }

        System.out.println("Most common letter in ciphertext: " + mostCommon);
        System.out.println("Value of most common letter: " + (mostCommon - 'A'));

        for(int a = 1; a < 26; a++) {
            if(a%2 != 0) {
                int b = (mostCommonValue - (ciphertext.indexOf('E') - ciphertext.indexOf('A')) * a) % 26;
                System.out.println("Possible values of a and b: " + a + " " + b);
                String plaintext = "";
                for(int i = 0; i < ciphertext.length(); i++) {
                    int c = (ciphertext.charAt(i) - 'A' - b);
                    int inv = multiplicative_inverse(a);
                    plaintext += (char)((c * inv % 26 + 'A'));
                }
                System.out.println("Potential plaintext: " + plaintext);
            }
        }
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        affineBreak("OZGVB");
    }
}
