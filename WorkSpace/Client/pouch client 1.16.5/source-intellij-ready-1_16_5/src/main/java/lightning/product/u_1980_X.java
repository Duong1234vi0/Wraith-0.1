/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lightning.product.E_2115_e;
import lightning.product.q_2475_j;

public final class u_1980_X {
    private static final String[][] n_1700_B = new String[][]{{"python"}, {"py", "-3"}, {"python3"}};
    private static final String J_1907_R = "3.11.9";
    private static final String R_4764_Y = "https://www.python.org/ftp/python/3.11.9/python-3.11.9-embed-amd64.zip";
    private static final String G_564_y = "https://bootstrap.pypa.io/get-pip.py";
    private static final String P_1922_E = "https://download.pytorch.org/whl/cpu";
    private static final String u_1723_Y = "python311._pth";
    private static final String v_4262_N = ".neuro_stack_v2";
    private static final String w_1484_f = ".python_embed_v2";
    private static final String t_148_a = "3.11.9";
    private static final int s_956_w = 60;
    private static final int u_2550_I = 45;
    private static final int M_588_G = 65536;
    private static final long P_4830_p = 2000L;
    private static final long h_1847_R = 524288L;
    private static volatile String[] Q_4569_t;

    private u_1980_X() {
    }

    public static void n_1700_B() {
        Q_4569_t = null;
        q_2475_j.n_1700_B("Python launcher cache cleared");
    }

    public static String[] J_1907_R() {
        q_2475_j.R_4764_Y("Neuro training bootstrap");
        String[] launcher = u_1980_X.R_4764_Y();
        if (launcher == null) {
            return null;
        }
        try {
            u_1980_X.n_1700_B(launcher);
            return launcher;
        }
        catch (Exception e) {
            q_2475_j.n_1700_B("Neuro stack install failed: " + e.getMessage(), e);
            u_1980_X.n_1700_B();
            u_1980_X.u_1723_Y();
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String[] R_4764_Y() {
        CharSequence[] local = Q_4569_t;
        if (local != null) {
            q_2475_j.n_1700_B("Using cached Python: " + String.join((CharSequence)" ", local));
            return local;
        }
        Class<u_1980_X> clazz = u_1980_X.class;
        synchronized (u_1980_X.class) {
            if (Q_4569_t != null) {
                // ** MonitorExit[clazz] (shouldn't be in output)
                return Q_4569_t;
            }
            q_2475_j.R_4764_Y("Python interpreter lookup");
            for (CharSequence[] charSequenceArray : n_1700_B) {
                q_2475_j.n_1700_B("Trying launcher: " + String.join((CharSequence)" ", charSequenceArray));
                if (!u_1980_X.P_1922_E((String[])charSequenceArray)) continue;
                Q_4569_t = charSequenceArray;
                q_2475_j.n_1700_B("Selected system Python: " + String.join((CharSequence)" ", charSequenceArray));
                // ** MonitorExit[clazz] (shouldn't be in output)
                return Q_4569_t;
            }
            File portable = u_1980_X.w_1484_f();
            if (portable.isFile() && u_1980_X.P_1922_E(new String[]{portable.getAbsolutePath()})) {
                Q_4569_t = new String[]{portable.getAbsolutePath()};
                q_2475_j.n_1700_B("Selected portable Python: " + portable.getAbsolutePath());
                // ** MonitorExit[clazz] (shouldn't be in output)
                return Q_4569_t;
            }
            if (!u_1980_X.t_148_a()) {
                q_2475_j.J_1907_R("Auto Python install skipped: not Windows (install python3 + torch + onnx manually)");
                // ** MonitorExit[clazz] (shouldn't be in output)
                return null;
            }
            try {
                String[] installed = u_1980_X.s_956_w();
                Q_4569_t = installed;
                // ** MonitorExit[clazz] (shouldn't be in output)
                return installed;
            }
            catch (Exception e) {
                q_2475_j.n_1700_B("Embeddable Python install failed", e);
                // ** MonitorExit[clazz] (shouldn't be in output)
                return null;
            }
        }
    }

    private static void n_1700_B(String[] launcher) throws IOException, InterruptedException {
        String launcherKey = String.join((CharSequence)" ", launcher);
        if (u_1980_X.G_564_y(launcher) && u_1980_X.n_1700_B(launcherKey)) {
            q_2475_j.n_1700_B("PyTorch + ONNX stack already present \u2014 skip pip");
            return;
        }
        q_2475_j.R_4764_Y("pip + PyTorch + ONNX + onnxscript");
        u_1980_X.J_1907_R(launcher);
        if (!u_1980_X.n_1700_B(launcher, "torch")) {
            q_2475_j.n_1700_B("Installing PyTorch (CPU)...");
            u_1980_X.n_1700_B(launcher, "install", "torch", "--index-url", P_1922_E);
        } else {
            q_2475_j.n_1700_B("torch already installed \u2014 skip");
        }
        if (!u_1980_X.n_1700_B(launcher, "onnx")) {
            q_2475_j.n_1700_B("Installing onnx...");
            u_1980_X.n_1700_B(launcher, "install", "onnx");
        } else {
            q_2475_j.n_1700_B("onnx already installed \u2014 skip");
        }
        if (!u_1980_X.n_1700_B(launcher, "onnxscript")) {
            q_2475_j.n_1700_B("Installing onnxscript (required for torch.onnx.export)...");
            u_1980_X.n_1700_B(launcher, "install", "onnxscript");
        } else {
            q_2475_j.n_1700_B("onnxscript already installed \u2014 skip");
        }
        if (!u_1980_X.G_564_y(launcher)) {
            throw new IOException("torch/onnx/onnxscript import failed after pip");
        }
        u_1980_X.J_1907_R(launcherKey);
        q_2475_j.n_1700_B("Neuro stack ready");
    }

    private static void J_1907_R(String[] launcher) throws IOException, InterruptedException {
        if (u_1980_X.R_4764_Y(launcher)) {
            q_2475_j.n_1700_B("pip already available");
            return;
        }
        q_2475_j.n_1700_B("Installing pip via ensurepip...");
        ArrayList<String> argv = new ArrayList<String>();
        Collections.addAll(argv, launcher);
        argv.add("-m");
        argv.add("ensurepip");
        argv.add("--upgrade");
        u_1980_X.n_1700_B(argv.toArray(new String[0]), "ensurepip", 10);
        if (!u_1980_X.R_4764_Y(launcher)) {
            throw new IOException("pip unavailable after ensurepip");
        }
    }

    private static boolean R_4764_Y(String[] launcher) {
        try {
            ArrayList<String> argv = new ArrayList<String>();
            Collections.addAll(argv, launcher);
            argv.add("-m");
            argv.add("pip");
            argv.add("--version");
            Process p = new ProcessBuilder(argv).redirectErrorStream(true).start();
            if (!p.waitFor(15L, TimeUnit.SECONDS)) {
                p.destroyForcibly();
                q_2475_j.n_1700_B("pip --version", false);
                return false;
            }
            boolean ok = p.exitValue() == 0;
            q_2475_j.n_1700_B("pip --version", ok);
            return ok;
        }
        catch (Exception e) {
            q_2475_j.n_1700_B("pip --version", false);
            return false;
        }
    }

    private static void n_1700_B(String[] launcher, String ... pipArgs) throws IOException, InterruptedException {
        ArrayList<String> argv = new ArrayList<String>();
        Collections.addAll(argv, launcher);
        argv.add("-m");
        argv.add("pip");
        Collections.addAll(argv, pipArgs);
        argv.add("--no-warn-script-location");
        u_1980_X.n_1700_B(argv.toArray(new String[0]), "pip", 45);
    }

    private static boolean n_1700_B(String[] launcher, String module) {
        try {
            ArrayList<String> argv = new ArrayList<String>();
            Collections.addAll(argv, launcher);
            argv.add("-c");
            argv.add("import " + module);
            Process p = new ProcessBuilder(argv).redirectErrorStream(true).start();
            if (!p.waitFor(60L, TimeUnit.SECONDS)) {
                p.destroyForcibly();
                q_2475_j.n_1700_B("import " + module, false);
                return false;
            }
            boolean ok = p.exitValue() == 0;
            q_2475_j.n_1700_B("import " + module, ok);
            return ok;
        }
        catch (Exception e) {
            q_2475_j.n_1700_B("import " + module, false);
            return false;
        }
    }

    private static boolean G_564_y(String[] launcher) {
        return u_1980_X.n_1700_B(launcher, "torch") && u_1980_X.n_1700_B(launcher, "onnx") && u_1980_X.n_1700_B(launcher, "onnxscript");
    }

    private static boolean P_1922_E(String[] launcher) {
        try {
            ArrayList<String> argv = new ArrayList<String>();
            Collections.addAll(argv, launcher);
            argv.add("--version");
            Process p = new ProcessBuilder(argv).redirectErrorStream(true).start();
            if (!p.waitFor(4L, TimeUnit.SECONDS)) {
                p.destroyForcibly();
                q_2475_j.n_1700_B(String.join((CharSequence)" ", launcher) + " --version", false);
                return false;
            }
            boolean ok = p.exitValue() == 0;
            q_2475_j.n_1700_B(String.join((CharSequence)" ", launcher) + " --version", ok);
            return ok;
        }
        catch (Exception e) {
            q_2475_j.n_1700_B(String.join((CharSequence)" ", launcher) + " --version", false);
            return false;
        }
    }

    private static File G_564_y() {
        return E_2115_e.R_4764_Y().getParentFile();
    }

    private static File P_1922_E() {
        return new File(u_1980_X.G_564_y(), v_4262_N);
    }

    private static boolean n_1700_B(String launcherKey) {
        File marker = u_1980_X.P_1922_E();
        if (!marker.isFile()) {
            return false;
        }
        try {
            return Files.readString(marker.toPath(), StandardCharsets.UTF_8).trim().equals(launcherKey);
        }
        catch (IOException e) {
            return false;
        }
    }

    private static void J_1907_R(String launcherKey) throws IOException {
        File root = u_1980_X.G_564_y();
        if (!root.exists() && !root.mkdirs()) {
            throw new IOException("Cannot create " + String.valueOf(root));
        }
        Path marker = u_1980_X.P_1922_E().toPath();
        Files.writeString(marker, (CharSequence)launcherKey, StandardCharsets.UTF_8, new OpenOption[0]);
        q_2475_j.n_1700_B(marker, (long)launcherKey.length(), "deps marker");
    }

    private static void u_1723_Y() {
        try {
            Files.deleteIfExists(u_1980_X.P_1922_E().toPath());
            q_2475_j.n_1700_B("Deleted deps marker");
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private static File v_4262_N() {
        return new File(u_1980_X.G_564_y(), "python");
    }

    private static File w_1484_f() {
        return new File(u_1980_X.v_4262_N(), "python.exe");
    }

    private static boolean t_148_a() {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        return os.contains("win");
    }

    private static String[] s_956_w() throws IOException, InterruptedException {
        q_2475_j.R_4764_Y("Portable Python 3.11.9");
        File dir = u_1980_X.v_4262_N();
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Cannot create " + String.valueOf(dir));
        }
        q_2475_j.n_1700_B("Portable dir: " + dir.getAbsolutePath());
        File exe = u_1980_X.w_1484_f();
        String[] launcher = new String[]{exe.getAbsolutePath()};
        if (u_1980_X.u_1723_Y(launcher)) {
            q_2475_j.n_1700_B("Portable Python 3.11.9 already installed \u2014 skip zip/get-pip");
            return launcher;
        }
        if (!exe.isFile()) {
            q_2475_j.n_1700_B("Downloading portable Python 3.11.9");
            File zip = new File(dir, "python-embed.zip");
            u_1980_X.n_1700_B(new URL(R_4764_Y), zip.toPath(), "python embed zip");
            q_2475_j.n_1700_B("Unpacking Python...");
            u_1980_X.n_1700_B(zip, dir);
            if (!zip.delete()) {
                zip.deleteOnExit();
            }
            q_2475_j.n_1700_B("Removed zip: " + zip.getName());
            if (!exe.isFile()) {
                throw new IOException("python.exe not found at " + String.valueOf(exe));
            }
            u_1980_X.J_1907_R(dir);
        } else {
            q_2475_j.n_1700_B(exe.toPath(), "python.exe already on disk");
            u_1980_X.J_1907_R(dir);
        }
        u_1980_X.n_1700_B(dir, launcher);
        if (!u_1980_X.P_1922_E(launcher)) {
            throw new IOException("python.exe failed --version after install");
        }
        u_1980_X.h_1847_R();
        q_2475_j.n_1700_B("Portable Python ready: " + exe.getAbsolutePath());
        return launcher;
    }

    private static boolean u_1723_Y(String[] launcher) {
        File exe = u_1980_X.w_1484_f();
        if (!exe.isFile() || !u_1980_X.P_1922_E(launcher)) {
            return false;
        }
        u_1980_X.n_1700_B(exe.getParentFile());
        if (!u_1980_X.R_4764_Y(launcher)) {
            return false;
        }
        return u_1980_X.P_4830_p();
    }

    private static void n_1700_B(File dir, String[] launcher) throws IOException, InterruptedException {
        if (u_1980_X.R_4764_Y(launcher)) {
            q_2475_j.n_1700_B("pip already available in portable Python \u2014 skip get-pip");
            return;
        }
        q_2475_j.n_1700_B("Bootstrapping pip...");
        try {
            u_1980_X.n_1700_B(new String[]{launcher[0], "-m", "ensurepip", "--upgrade"}, "ensurepip", 10);
        }
        catch (IOException e) {
            q_2475_j.J_1907_R("ensurepip failed: " + e.getMessage());
        }
        if (u_1980_X.R_4764_Y(launcher)) {
            q_2475_j.n_1700_B("pip ready after ensurepip");
            return;
        }
        File getPip = new File(dir, "get-pip.py");
        u_1980_X.n_1700_B(new URL(G_564_y), getPip.toPath(), "get-pip.py");
        q_2475_j.n_1700_B("Running get-pip.py...");
        try {
            u_1980_X.n_1700_B(new String[]{launcher[0], getPip.getAbsolutePath(), "--no-warn-script-location"}, "get-pip", 10);
        }
        catch (IOException e) {
            q_2475_j.J_1907_R("get-pip.py failed: " + e.getMessage() + " \u2014 trying pip --ignore-installed");
            u_1980_X.n_1700_B(launcher, "install", "--ignore-installed", "--no-warn-script-location", "pip", "setuptools", "wheel");
        }
        if (!getPip.delete()) {
            getPip.deleteOnExit();
        }
        if (!u_1980_X.R_4764_Y(launcher)) {
            u_1980_X.u_2550_I();
            throw new IOException("pip unavailable after bootstrap (ensurepip + get-pip)");
        }
        q_2475_j.n_1700_B("pip bootstrap complete");
    }

    private static void u_2550_I() {
        try {
            Files.deleteIfExists(u_1980_X.M_588_G().toPath());
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private static void n_1700_B(File dir) {
        try {
            u_1980_X.J_1907_R(dir);
        }
        catch (IOException e) {
            q_2475_j.J_1907_R("Could not patch ._pth: " + e.getMessage());
        }
    }

    private static File M_588_G() {
        return new File(u_1980_X.v_4262_N(), w_1484_f);
    }

    private static boolean P_4830_p() {
        File marker = u_1980_X.M_588_G();
        if (!marker.isFile()) {
            return false;
        }
        try {
            return Files.readString(marker.toPath(), StandardCharsets.UTF_8).trim().equals("3.11.9");
        }
        catch (IOException e) {
            return false;
        }
    }

    private static void h_1847_R() throws IOException {
        Path marker = u_1980_X.M_588_G().toPath();
        Files.writeString(marker, (CharSequence)"3.11.9", StandardCharsets.UTF_8, new OpenOption[0]);
        q_2475_j.n_1700_B(marker, (long)"3.11.9".length(), "portable python marker");
    }

    private static void J_1907_R(File dir) throws IOException {
        String content;
        File pth = new File(dir, u_1723_Y);
        if (!pth.isFile()) {
            File[] candidates = dir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).matches("python\\d+\\._pth"));
            if (candidates == null || candidates.length == 0) {
                q_2475_j.J_1907_R("No python*._pth found in " + String.valueOf(dir));
                return;
            }
            pth = candidates[0];
        }
        if ((content = new String(Files.readAllBytes(pth.toPath()), StandardCharsets.UTF_8)).contains("\nimport site")) {
            q_2475_j.n_1700_B(pth.toPath(), "import site already enabled");
            return;
        }
        Object patched = content.replace("#import site", "import site");
        if (((String)patched).equals(content)) {
            patched = content + System.lineSeparator() + "import site" + System.lineSeparator();
        }
        Files.write(pth.toPath(), ((String)patched).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        q_2475_j.n_1700_B(pth.toPath(), (long)((String)patched).length(), "enable import site");
    }

    private static void n_1700_B(URL url, Path target, String label) throws IOException {
        if (Files.isRegularFile(target, new LinkOption[0]) && Files.size(target) > 0L) {
            q_2475_j.n_1700_B(target, label + " already on disk");
            return;
        }
        u_1980_X.n_1700_B(url, target);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void n_1700_B(URL url, Path target) throws IOException {
        long started;
        q_2475_j.n_1700_B(url, target);
        long lastLogAt = started = System.currentTimeMillis();
        long lastLogBytes = 0L;
        long total = -1L;
        long downloaded = 0L;
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(120000);
        conn.setInstanceFollowRedirects(true);
        conn.connect();
        int code = conn.getResponseCode();
        if (code >= 400) {
            throw new IOException("HTTP " + code + " for " + String.valueOf(url));
        }
        total = conn.getContentLengthLong();
        q_2475_j.n_1700_B("HTTP " + code + " Content-Length=" + (total >= 0L ? q_2475_j.n_1700_B(total) : "unknown"));
        Path parent = target.getParent();
        if (parent != null) {
            Files.createDirectories(parent, new FileAttribute[0]);
        }
        try (InputStream raw = conn.getInputStream();
             BufferedInputStream in = new BufferedInputStream(raw);
             OutputStream out = Files.newOutputStream(target, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);){
            int read;
            byte[] buf = new byte[65536];
            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
                long now = System.currentTimeMillis();
                if ((downloaded += (long)read) - lastLogBytes < 524288L && now - lastLogAt < 2000L) continue;
                q_2475_j.n_1700_B(url, downloaded, total, now - started);
                lastLogAt = now;
                lastLogBytes = downloaded;
            }
        }
        finally {
            conn.disconnect();
        }
        long elapsed = System.currentTimeMillis() - started;
        q_2475_j.n_1700_B(target, downloaded, elapsed);
    }

    private static void n_1700_B(File zip, File targetDir) throws IOException {
        q_2475_j.n_1700_B(zip, targetDir);
        int entries = 0;
        long uncompressed = 0L;
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(Files.newInputStream(zip.toPath(), new OpenOption[0])));){
            ZipEntry e;
            while ((e = zis.getNextEntry()) != null) {
                File out = new File(targetDir, e.getName());
                String canonicalTarget = targetDir.getCanonicalPath();
                if (!out.getCanonicalPath().startsWith(canonicalTarget + File.separator) && !out.getCanonicalPath().equals(canonicalTarget)) {
                    throw new IOException("Zip slip: " + e.getName());
                }
                if (e.isDirectory()) {
                    out.mkdirs();
                } else {
                    File parent = out.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }
                    Files.copy(zis, out.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    uncompressed += e.getSize() > 0L ? e.getSize() : out.length();
                    if (++entries <= 30 || entries % 50 == 0) {
                        q_2475_j.n_1700_B(e.getName(), e.getCompressedSize());
                    }
                }
                zis.closeEntry();
            }
        }
        q_2475_j.n_1700_B(entries, uncompressed);
    }

    public static void n_1700_B(ProcessBuilder pb) {
        pb.environment().put("PYTHONIOENCODING", "utf-8");
        pb.environment().put("PYTHONUTF8", "1");
    }

    private static void n_1700_B(String[] argv, String tag, int timeoutMinutes) throws IOException, InterruptedException {
        q_2475_j.n_1700_B(tag, argv);
        long started = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder(argv);
        u_1980_X.n_1700_B(pb);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));){
            String line;
            while ((line = br.readLine()) != null) {
                q_2475_j.n_1700_B(tag, line);
            }
        }
        if (!p.waitFor(timeoutMinutes, TimeUnit.MINUTES)) {
            p.destroyForcibly();
            q_2475_j.n_1700_B(tag, -1, System.currentTimeMillis() - started);
            throw new IOException(tag + " timeout after " + timeoutMinutes + " min");
        }
        int code = p.exitValue();
        q_2475_j.n_1700_B(tag, code, System.currentTimeMillis() - started);
        if (code != 0) {
            throw new IOException(tag + " exited " + code);
        }
    }
}

