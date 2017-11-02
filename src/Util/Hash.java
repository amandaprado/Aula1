package Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hash {
    public static String hash(String senha){
        MessageDigest algoritmo;
        try {
           algoritmo  = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        byte[] vetor;
        try {
            vetor = algoritmo.digest(senha.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        try {
            return new String(vetor, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
