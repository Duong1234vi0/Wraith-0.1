/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class q_4610_l {
    private static final String[] n_1700_B = new String[0];
    private static final Set<String> J_1907_R = new HashSet<String>();
    private static final char[] R_4764_Y;

    private q_4610_l() {
    }

    private static String n_1700_B(byte[] b) {
        char[] out = new char[b.length * 2];
        for (int i = 0; i < b.length; ++i) {
            int v = b[i] & 0xFF;
            out[i * 2] = R_4764_Y[v >>> 4];
            out[i * 2 + 1] = R_4764_Y[v & 0xF];
        }
        return new String(out);
    }

    private static boolean n_1700_B(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean n_1700_B(String[] domains) {
        Path[] candidates = new Path[]{Paths.get(System.getenv().getOrDefault("WINDIR", "C:\\Windows"), new String[0]).resolve("System32\\drivers\\etc\\hosts"), Paths.get("/etc/hosts", new String[0])};
        Path hosts = null;
        for (Path p : candidates) {
            if (p == null || !Files.exists(p, new LinkOption[0]) || !Files.isReadable(p)) continue;
            hosts = p;
            break;
        }
        if (hosts == null) {
            return true;
        }
        HashSet<String> targets = new HashSet<String>();
        for (String d : domains) {
            if (q_4610_l.n_1700_B(d)) continue;
            targets.add(d.trim().toLowerCase(Locale.ROOT));
        }
        if (targets.isEmpty()) {
            return true;
        }
        try {
            for (String raw : Files.readAllLines(hosts, StandardCharsets.UTF_8)) {
                String[] parts;
                String line = raw;
                int hash = line.indexOf(35);
                if (hash >= 0) {
                    line = line.substring(0, hash);
                }
                if ((line = line.trim()).isEmpty() || (parts = line.split("\\s+")).length < 2) continue;
                String ip = parts[0].trim();
                for (int i = 1; i < parts.length; ++i) {
                    String name = parts[i].trim().toLowerCase(Locale.ROOT);
                    if (!targets.contains(name) || ("localhost".equals(name) || "127.0.0.1".equals(name) || "::1".equals(name)) && J_1907_R.contains(ip)) continue;
                    return false;
                }
            }
            return true;
        }
        catch (Exception ignored) {
            return true;
        }
    }

    public static HttpClient n_1700_B(Duration connectTimeout) throws Exception {
        if (n_1700_B.length == 0) {
            return HttpClient.newBuilder().connectTimeout(connectTimeout).version(HttpClient.Version.HTTP_1_1).build();
        }
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore)null);
        X509TrustManager defaultTm = null;
        for (TrustManager tm : tmf.getTrustManagers()) {
            if (!(tm instanceof X509TrustManager)) continue;
            defaultTm = (X509TrustManager)tm;
            break;
        }
        if (defaultTm == null) {
            throw new IllegalStateException("no default X509TrustManager");
        }
        final X509TrustManager finalDefault = defaultTm;
        TrustManager[] pinning = new TrustManager[]{new X509TrustManager(){

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                finalDefault.checkClientTrusted(chain, authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                finalDefault.checkServerTrusted(chain, authType);
                if (chain == null || chain.length == 0) {
                    throw new CertificateException("empty cert chain");
                }
                try {
                    byte[] spki = chain[0].getPublicKey().getEncoded();
                    String got = q_4610_l.n_1700_B(MessageDigest.getInstance("SHA-256").digest(spki));
                    for (String pin : n_1700_B) {
                        if (pin == null || !got.equalsIgnoreCase(pin.trim())) continue;
                        return;
                    }
                    throw new CertificateException("SPKI pin mismatch: got=" + got);
                }
                catch (CertificateException e) {
                    throw e;
                }
                catch (Exception e) {
                    throw new CertificateException("pin check failed: " + e.getMessage());
                }
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return finalDefault.getAcceptedIssuers();
            }
        }};
        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
        ctx.init(null, pinning, new SecureRandom());
        return HttpClient.newBuilder().connectTimeout(connectTimeout).version(HttpClient.Version.HTTP_1_1).sslContext(ctx).build();
    }

    static {
        J_1907_R.add("127.0.0.1");
        J_1907_R.add("::1");
        J_1907_R.add("0.0.0.0");
        R_4764_Y = "0123456789abcdef".toCharArray();
    }
}

