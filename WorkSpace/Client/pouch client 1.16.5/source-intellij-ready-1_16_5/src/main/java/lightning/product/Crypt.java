/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lightning.product.Y_2605_X;

public class Crypt {
    public static SecretKey n_1700_B() throws Y_2605_X {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
            keygenerator.init(128);
            return keygenerator.generateKey();
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    public static KeyPair J_1907_R() throws Y_2605_X {
        try {
            KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("RSA");
            keypairgenerator.initialize(1024);
            return keypairgenerator.generateKeyPair();
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    public static byte[] n_1700_B(String serverId, PublicKey publicKey, SecretKey secretKey) throws Y_2605_X {
        try {
            return Crypt.n_1700_B(serverId.getBytes("ISO_8859_1"), secretKey.getEncoded(), publicKey.getEncoded());
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    private static byte[] n_1700_B(byte[] ... p_244731_0_) throws Exception {
        MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
        for (byte[] abyte : p_244731_0_) {
            messagedigest.update(abyte);
        }
        return messagedigest.digest();
    }

    public static PublicKey n_1700_B(byte[] encodedKey) throws Y_2605_X {
        try {
            X509EncodedKeySpec encodedkeyspec = new X509EncodedKeySpec(encodedKey);
            KeyFactory keyfactory = KeyFactory.getInstance("RSA");
            return keyfactory.generatePublic(encodedkeyspec);
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    public static SecretKey n_1700_B(PrivateKey key, byte[] secretKeyEncrypted) throws Y_2605_X {
        byte[] abyte = Crypt.J_1907_R(key, secretKeyEncrypted);
        try {
            return new SecretKeySpec(abyte, "AES");
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    public static byte[] n_1700_B(Key key, byte[] data) throws Y_2605_X {
        return Crypt.n_1700_B(1, key, data);
    }

    public static byte[] J_1907_R(Key key, byte[] data) throws Y_2605_X {
        return Crypt.n_1700_B(2, key, data);
    }

    private static byte[] n_1700_B(int opMode, Key key, byte[] data) throws Y_2605_X {
        try {
            return Crypt.n_1700_B(opMode, key.getAlgorithm(), key).doFinal(data);
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }

    private static Cipher n_1700_B(int opMode, String transformation, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(opMode, key);
        return cipher;
    }

    public static Cipher n_1700_B(int opMode, Key key) throws Y_2605_X {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(opMode, key, new IvParameterSpec(key.getEncoded()));
            return cipher;
        }
        catch (Exception exception) {
            throw new Y_2605_X(exception);
        }
    }
}


