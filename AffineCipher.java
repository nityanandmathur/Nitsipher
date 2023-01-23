import java.util.HashMap;

public class AffineCipher {
    
    /** 
     * @param a
     * @param b
     * @return int
     */
    public static int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    
    /** 
     * @param a
     * @param m
     * @return int
     */
    public static int findInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;
        if (m == 1) return 0;
        while (a > 1) {
            int q = a / m;
            int t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) x += m0;
        return x;
    }

    
    /** 
     * @param ciphertext
     */
    public static void affineBreak(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if(freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }

        char mostCommon = ' ';
        int mostCommonValue = 0;
        for(char c : freqMap.keySet()) {
            if(freqMap.get(c) > mostCommonValue) {
                mostCommon = c;
                mostCommonValue = freqMap.get(c);
            }
        }

        System.out.println("Most common letter in ciphertext: " + mostCommon);
        System.out.println("Value of most common letter: " + (mostCommon - 'A'));

        for(int a = 1; a < 26; a++) {
            if(gcd(a, 26) == 1) {
                int b = (mostCommonValue - (ciphertext.indexOf('E') - ciphertext.indexOf('A')) * a) % 26;
                System.out.println("Possible values of a and b: " + a + " " + b);
                String plaintext = "";
                for(int i = 0; i < ciphertext.length(); i++) {
                    int c = (ciphertext.charAt(i) - 'A' - b);
                    int inv = findInverse(a, 26);
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
