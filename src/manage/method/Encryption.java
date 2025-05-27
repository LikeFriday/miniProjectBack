package manage.method;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryption {
    public static final String FIXED_KEY = "MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI="; // "12345678901234567890123456789012"
    public static final String FIXED_IV = "MTIzNDU2Nzg5MDEyMzQ1Ng==";

    public static void main(String[] args) {
        String key = FIXED_KEY;
        String iv = FIXED_IV;
        String plainText = "Hello, World!";

        String encryptedText = encrypt(key, iv, plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(key, iv, encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String decrypt(String key, String iv, String password) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            byte[] decodedIV = Base64.getDecoder().decode(iv);
            byte[] decodedEncryptedText = Base64.getDecoder().decode(password);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decodedIV);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decryptedBytes = cipher.doFinal(decodedEncryptedText);

            return new String(decryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String key, String iv, String password){
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            byte[] decodedIV = Base64.getDecoder().decode(iv);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decodedIV);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
