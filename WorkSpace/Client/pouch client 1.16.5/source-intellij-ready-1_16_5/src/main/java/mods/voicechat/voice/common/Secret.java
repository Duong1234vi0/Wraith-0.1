/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package mods.voicechat.voice.common;

import io.netty.buffer.ByteBuf;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lightning.product.b_2585_i;

public class Secret {
    public static final int SECRET_SIZE_BYTES = 16;
    public static final int IV_SIZE_BYTES = 12;
    public static final int TAG_LEN_BITS = 128;
    public static final String CIPHER = "AES/GCM/NoPadding";
    private static final SecureRandom RANDOM = new SecureRandom();
    private final byte[] secret;
    private final SecretKeySpec keySpec;

    protected Secret(byte[] secret) {
        this.secret = secret;
        this.keySpec = new SecretKeySpec(secret, "AES");
    }

    public static Secret generateNewRandomSecret() {
        byte[] secret = new byte[16];
        RANDOM.nextBytes(secret);
        return new Secret(secret);
    }

    public static Secret fromBytes(byte[] secret) {
        return new Secret(secret);
    }

    public static Secret fromBytes(ByteBuf buf) {
        byte[] secretBytes = new byte[16];
        buf.readBytes(secretBytes);
        return Secret.fromBytes(secretBytes);
    }

    public static Secret fromBytes(b_2585_i buf) {
        byte[] secretBytes = new byte[16];
        buf.readBytes(secretBytes);
        return Secret.fromBytes(secretBytes);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBytes(this.secret);
    }

    public void toBytes(b_2585_i buf) {
        buf.writeBytes(this.secret);
    }

    public byte[] getSecret() {
        return this.secret;
    }

    public SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    public static byte[] generateIV() {
        byte[] iv = new byte[12];
        RANDOM.nextBytes(iv);
        return iv;
    }

    public byte[] encrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] iv = Secret.generateIV();
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(1, (Key)this.getKeySpec(), new GCMParameterSpec(128, iv));
        byte[] enc = cipher.doFinal(data);
        byte[] payload = new byte[iv.length + enc.length];
        System.arraycopy(iv, 0, payload, 0, iv.length);
        System.arraycopy(enc, 0, payload, iv.length, enc.length);
        return payload;
    }

    public byte[] decrypt(byte[] payload) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] iv = Arrays.copyOfRange(payload, 0, 12);
        byte[] data = Arrays.copyOfRange(payload, 12, payload.length);
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(2, (Key)this.getKeySpec(), new GCMParameterSpec(128, iv));
        return cipher.doFinal(data);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Secret)) {
            return false;
        }
        return Arrays.equals(this.secret, ((Secret)o).secret);
    }

    public int hashCode() {
        return Arrays.hashCode(this.secret);
    }
}

