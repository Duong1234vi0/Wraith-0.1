/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Path;
import java.util.Locale;

public final class q_2475_j {
    private static final String n_1700_B = "[Pouch/Neuro] ";

    private q_2475_j() {
    }

    public static void n_1700_B(String message) {
        System.out.println(n_1700_B + message);
    }

    public static void J_1907_R(String message) {
        System.out.println("[Pouch/Neuro] [WARN] " + message);
    }

    public static void n_1700_B(String message, Throwable t) {
        System.err.println("[Pouch/Neuro] [ERROR] " + message);
        if (t != null) {
            StringWriter sw = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));
            for (String traceLine : sw.toString().split("\n")) {
                System.err.println("[Pouch/Neuro]   " + traceLine);
            }
        }
    }

    public static void R_4764_Y(String phase) {
        q_2475_j.n_1700_B("========== " + phase + " ==========");
    }

    public static void n_1700_B(URL url, Path target) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "DOWNLOAD START url=%s -> %s", url, target.toAbsolutePath()));
    }

    public static void n_1700_B(URL url, long downloaded, long total, long elapsedMs) {
        String size = q_2475_j.n_1700_B(downloaded) + (String)(total > 0L ? " / " + q_2475_j.n_1700_B(total) : "");
        String pct = total > 0L ? String.format(Locale.ROOT, " (%.1f%%)", (double)downloaded * 100.0 / (double)total) : "";
        String speed = elapsedMs > 0L ? String.format(Locale.ROOT, " @ %.2f MB/s", (double)downloaded / 1024.0 / 1024.0 / ((double)elapsedMs / 1000.0)) : "";
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "DOWNLOAD ... %s%s%s | %s", size, pct, speed, url.getHost()));
    }

    public static void n_1700_B(Path target, long bytes, long elapsedMs) {
        double sec = Math.max(0.001, (double)elapsedMs / 1000.0);
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "DOWNLOAD DONE %s (%s in %.1fs, %.2f MB/s)", target.toAbsolutePath(), q_2475_j.n_1700_B(bytes), sec, (double)bytes / 1024.0 / 1024.0 / sec));
    }

    public static void n_1700_B(Path target, long bytes, String reason) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "FILE WRITE %s (%s) reason=%s", target.toAbsolutePath(), q_2475_j.n_1700_B(bytes), reason));
    }

    public static void n_1700_B(Path target, String reason) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "FILE SKIP %s reason=%s", target.toAbsolutePath(), reason));
    }

    public static void n_1700_B(File zip, File targetDir) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "UNZIP START %s -> %s (zip %s)", zip.getAbsolutePath(), targetDir.getAbsolutePath(), q_2475_j.n_1700_B(zip.length())));
    }

    public static void n_1700_B(String entryName, long compressedSize) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "UNZIP + %s (%s)", entryName, q_2475_j.n_1700_B(compressedSize)));
    }

    public static void n_1700_B(int entries, long totalUncompressed) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "UNZIP DONE entries=%d total_uncompressed=%s", entries, q_2475_j.n_1700_B(totalUncompressed)));
    }

    public static void n_1700_B(String tag, String ... argv) {
        q_2475_j.n_1700_B("PROCESS START [" + tag + "] " + String.join((CharSequence)" ", argv));
    }

    public static void n_1700_B(String tag, String line) {
        if (line == null || line.isBlank()) {
            return;
        }
        System.out.println("[Pouch/Neuro] [" + tag + "] " + line.trim());
    }

    public static void n_1700_B(String tag, int exitCode, long elapsedMs) {
        q_2475_j.n_1700_B(String.format(Locale.ROOT, "PROCESS END [%s] exit=%d elapsed=%.1fs", tag, exitCode, (double)elapsedMs / 1000.0));
    }

    public static void n_1700_B(String what, boolean ok) {
        q_2475_j.n_1700_B("PROBE " + what + " -> " + (ok ? "OK" : "FAIL"));
    }

    public static String n_1700_B(long bytes) {
        if (bytes < 1024L) {
            return bytes + " B";
        }
        if (bytes < 0x100000L) {
            return String.format(Locale.ROOT, "%.1f KB", (double)bytes / 1024.0);
        }
        if (bytes < 0x40000000L) {
            return String.format(Locale.ROOT, "%.2f MB", (double)bytes / 1024.0 / 1024.0);
        }
        return String.format(Locale.ROOT, "%.2f GB", (double)bytes / 1024.0 / 1024.0 / 1024.0);
    }
}

