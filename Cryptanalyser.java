import java.util.HashMap;
import java.util.Scanner;

public class Cryptanalyser {
    
    /** 
     * @param key1
     * @return int
     */
    public int multiplicative_inverse(int key1)
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
    public void frequency_analyser(String ciphertext) {
        System.out.println("Frequency Attack");
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

        String plaintext = "";
        for(int key1 = 1; key1 < 26; key1++) {
            if(key1%2 != 0) {
                int key2 = (mostCommonValue - (ciphertext.indexOf('E') - ciphertext.indexOf('A')) * key1) % 26;
                // int x = mostCommon - 'A';
                // int key2 = (x - 4 * key1) % 26;

                //System.out.println("Possible values of key1 and key2: " + key1 + " " + key2);
                plaintext = "";
                for(int i = 0; i < ciphertext.length(); i++) {
                    int c = (ciphertext.charAt(i) - 'A' - key2);
                    int inv = multiplicative_inverse(key1);
                    plaintext += (char)((c * inv % 26 + 'A'));
                }
                System.out.println("Plaintext: " + plaintext + " for key1 = " +  key1 + " & key2 = " + key2);
            }
        }
    }

    
    /** 
     * @param ciphertext
     */
    public void brute_analyser(String ciphertext)
    {
        for(int key1 = 1; key1 < 26; key1 += 2)
        {   
            int inverse = this.multiplicative_inverse(key1);
            for(int key2 = 0; key2 < 26; key2++)
            {
                String plaintext = "";
                for(int i = 0; i < ciphertext.length(); i++)
                {
                    plaintext += (char) (((inverse * ((ciphertext.charAt(i) + 'A' - key2)) % 26)) + 'A');
                }
                System.out.println("Plaintext: " + plaintext + " for key1 = " +  key1 + " & key2 = " + key2);
            }
        }
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        Cryptanalyser c = new Cryptanalyser();

        System.out.print("Enter CipherText: ");
        Scanner in = new Scanner(System.in);
        String cipherText = in.nextLine();
        c.brute_analyser(cipherText);
        c.frequency_analyser(cipherText);
        in.close();
    }
}
