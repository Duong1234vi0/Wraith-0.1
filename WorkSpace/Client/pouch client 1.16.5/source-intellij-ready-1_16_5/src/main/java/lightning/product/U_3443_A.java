/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;

public class U_3443_A {
    public static String n_1700_B(String url, String post, boolean json) {
        try {
            String lineBuffer;
            InputStream stream;
            HttpsURLConnection connection = (HttpsURLConnection)new URL(url).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            byte[] out = post.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            connection.setFixedLengthStreamingMode(length);
            connection.addRequestProperty("Content-Type", json ? "application/json" : "application/x-www-form-urlencoded; charset=UTF-8");
            connection.addRequestProperty("Accept", "application/json");
            connection.connect();
            try (OutputStream os = connection.getOutputStream();){
                os.write(out);
            }
            int responseCode = connection.getResponseCode();
            InputStream inputStream = stream = responseCode / 100 == 2 || responseCode / 100 == 3 ? connection.getInputStream() : connection.getErrorStream();
            if (stream == null) {
                System.err.println(responseCode + ": " + url);
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder response = new StringBuilder();
            while ((lineBuffer = reader.readLine()) != null) {
                response.append(lineBuffer);
            }
            reader.close();
            return response.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String n_1700_B(String url, String bearer) {
        try {
            String lineBuffer;
            HttpsURLConnection connection = (HttpsURLConnection)new URL(url).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
            connection.addRequestProperty("Accept", "application/json");
            connection.addRequestProperty("Authorization", "Bearer " + bearer);
            if (connection.getResponseCode() == 200) {
                String lineBuffer2;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((lineBuffer2 = reader.readLine()) != null) {
                    response.append(lineBuffer2);
                }
                return response.toString();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder response = new StringBuilder();
            while ((lineBuffer = reader.readLine()) != null) {
                response.append(lineBuffer);
            }
            return response.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
}

