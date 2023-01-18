import java.util.Scanner;

public class Affine
{
    
    /** 
     * @param plainText
     * @param key1
     * @param key2
     * @return String
     */
    public String encrypt(String plainText, int key1, int key2)
    {
        String cipherText = "";
        for(int i = 0; i < plainText.length(); i++)
        {
            if(plainText.charAt(i) == ' ')
                continue;
            cipherText +=  (char) (((((plainText.charAt(i) - 'A') * key1) + key2) % 26) + 'A');
        }
        return cipherText;
    }

    
    /** 
     * @param cipherText
     * @param key1
     * @param key2
     * @return String
     */
    public String decrypt(String cipherText, int key1, int key2)
    {
        int inverse = this.multiplicative_inverse(key1);
        String plainText = "";
        for(int i = 0; i < cipherText.length(); i++)
        {
            plainText += (char) (((inverse * ((cipherText.charAt(i) + 'A' - key2)) % 26)) + 'A');
        }
        return plainText;
    }

    
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
     * @param args
     */
    public static void main(String[] args) {
        Affine a = new Affine();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter text to encrypt : ");
        String plaintext = in.nextLine();
        System.out.print("Enter multiplicative key : ");
        int key1 = in.nextInt();
        if(key1 > 26 || key1 %2 == 0)
        {
            System.out.println("Invalid key");
            in.close();
            return;
        }
        System.out.print("Enter additive key : ");
        int key2 = in.nextInt();
        if(key2 > 26)
        {
            System.out.println("Invalid key");
            in.close();
            return;
        }

        String cipherText = a.encrypt(plaintext, key1, key2);
        System.out.println("Encrypted Message : " + cipherText);

        String plaintext2 = a.decrypt(cipherText, key1, key2);
        System.out.println("Decrypted Message : " + plaintext2);
        in.close();
    }
}