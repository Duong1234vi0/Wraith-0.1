/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class W_1488_x {
    private static final String n_1700_B = "kotopesprot";
    private static final byte[] J_1907_R = new byte[]{-54, -2, -70, -66};

    public static String n_1700_B(String data) {
        if (data == null || data.isEmpty()) {
            return data;
        }
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytes = n_1700_B.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = new byte[dataBytes.length];
            for (int i = 0; i < dataBytes.length; ++i) {
                encrypted[i] = (byte)(dataBytes[i] ^ keyBytes[i % keyBytes.length]);
            }
            byte[] finalData = new byte[J_1907_R.length + encrypted.length];
            System.arraycopy(J_1907_R, 0, finalData, 0, J_1907_R.length);
            System.arraycopy(encrypted, 0, finalData, J_1907_R.length, encrypted.length);
            return Base64.getEncoder().encodeToString(finalData);
        }
        catch (Exception e) {
            return data;
        }
    }

    public static String J_1907_R(String encryptedData) {
        if (encryptedData == null || encryptedData.isEmpty()) {
            return encryptedData;
        }
        try {
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            if (decodedData.length < J_1907_R.length) {
                return encryptedData;
            }
            boolean hasMagic = true;
            for (int i = 0; i < J_1907_R.length; ++i) {
                if (decodedData[i] == J_1907_R[i]) continue;
                hasMagic = false;
                break;
            }
            if (!hasMagic) {
                return encryptedData;
            }
            byte[] encrypted = new byte[decodedData.length - J_1907_R.length];
            System.arraycopy(decodedData, J_1907_R.length, encrypted, 0, encrypted.length);
            byte[] keyBytes = n_1700_B.getBytes(StandardCharsets.UTF_8);
            byte[] decrypted = new byte[encrypted.length];
            for (int i = 0; i < encrypted.length; ++i) {
                decrypted[i] = (byte)(encrypted[i] ^ keyBytes[i % keyBytes.length]);
            }
            return new String(decrypted, StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            return encryptedData;
        }
    }
}

