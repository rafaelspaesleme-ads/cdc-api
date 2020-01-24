package com.example.cdcapi.configurations;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class PasswordConfiguration {

    private static String ALG_CONFIG = "PBKDF2WithHmacSHA256";
    private static final String MSG = "[89, -108, 71, 26, -69, 1, 17, 42, -4, -63, -127, 89, -10, -52, 116, -76, -11, 17, -71, -104, 6, -38, 89, -77, -54, -11, -87, -63, 115, -54, -49, -59]";
    private static final byte[] CODE = MSG.getBytes();
    private static final String salt1 = "_yh_";
    private static final String salt2 = "_7¬*_";
    private static final String salt3 = "#3e00iuQ";
    private static final String salt4 = "12@saxc_)y_";
    private static final String salt5 = "_|_";
    private static final String salt = "=*=";
    private static String secretKey = "5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5";
    private static String sign;

    public String passwordConfiguration(String password, String user, Long id){
        return encrypt(password, user, id, CODE, MSG, ALG_CONFIG);
    }

    public String passwordDesconfiguration(String passwordEncrypt){
        return decrypt(passwordEncrypt, ALG_CONFIG);
    }

    private static String encrypt(String pass, String user, Long id, byte[] code, String msg, String algoritmo) {
        sign = user+salt1+id+salt2+code+salt3+pass+salt4+code+salt5+msg;
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(algoritmo);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(sign.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    private static String decrypt(String passEncrypt, String algoritmo) {
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(algoritmo);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(passEncrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public String[] retornaTudo(String token){
        String s3 = passwordDesconfiguration(token);
        String s4 = s3.replace(salt1, ",")
                .replace(salt2, ",")
                .replace(salt3, "")
                .replace(salt4, "")
                .replace(salt5, "")
                .replace(String.valueOf(CODE), "")
                .replace(MSG, "");

        System.out.println(s4);
        String array[];

        array = s4.split(",");
        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);

        return array;

    }

}
