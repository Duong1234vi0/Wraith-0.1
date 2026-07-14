/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.Hashing
 *  com.google.common.io.Files
 *  org.apache.commons.compress.archivers.tar.TarArchiveEntry
 *  org.apache.commons.compress.archivers.tar.TarArchiveInputStream
 *  org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.io.output.CountingOutputStream
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.C_290_v;
import lightning.product.F_1410_V;
import lightning.product.H_4757_Q;
import lightning.product.SharedConstants;
import lightning.product.J_2011_a;
import lightning.product.U_2912_j;
import lightning.product.RealmsDefaultUncaughtExceptionHandler;
import lightning.product.b_2971_z;
import lightning.product.MinecraftClient;
import lightning.product.r_1827_u;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A_1038_p {
    private static final Logger n_1700_B = LogManager.getLogger();
    private volatile boolean J_1907_R;
    private volatile boolean R_4764_Y;
    private volatile boolean G_564_y;
    private volatile boolean P_1922_E;
    private volatile File u_1723_Y;
    private volatile File v_4262_N;
    private volatile HttpGet w_1484_f;
    private Thread t_148_a;
    private final RequestConfig s_956_w = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();
    private static final String[] u_2550_I = new String[]{"CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long n_1700_B(String p_224827_1_) {
        long i;
        CloseableHttpClient closeablehttpclient = null;
        HttpGet httpget = null;
        try {
            httpget = new HttpGet(p_224827_1_);
            closeablehttpclient = HttpClientBuilder.create().setDefaultRequestConfig(this.s_956_w).build();
            CloseableHttpResponse closeablehttpresponse = closeablehttpclient.execute((HttpUriRequest)httpget);
            long l = Long.parseLong(closeablehttpresponse.getFirstHeader("Content-Length").getValue());
            return l;
        }
        catch (Throwable throwable) {
            n_1700_B.error("Unable to get content length for download");
            i = 0L;
        }
        finally {
            if (httpget != null) {
                httpget.releaseConnection();
            }
            if (closeablehttpclient != null) {
                try {
                    closeablehttpclient.close();
                }
                catch (IOException ioexception) {
                    n_1700_B.error("Could not close http client", (Throwable)ioexception);
                }
            }
        }
        return i;
    }

    public void n_1700_B(F_1410_V p_237688_1_, String p_237688_2_, C_290_v.n_1700_B p_237688_3_, b_2971_z p_237688_4_) {
        if (this.t_148_a == null) {
            this.t_148_a = new Thread(() -> {
                CloseableHttpClient closeablehttpclient = null;
                try {
                    this.u_1723_Y = File.createTempFile("backup", ".tar.gz");
                    this.w_1484_f = new HttpGet(p_237688_1_.n_1700_B);
                    closeablehttpclient = HttpClientBuilder.create().setDefaultRequestConfig(this.s_956_w).build();
                    CloseableHttpResponse httpresponse = closeablehttpclient.execute((HttpUriRequest)this.w_1484_f);
                    p_237688_3_.J_1907_R = Long.parseLong(httpresponse.getFirstHeader("Content-Length").getValue());
                    if (httpresponse.getStatusLine().getStatusCode() == 200) {
                        FileOutputStream outputstream = new FileOutputStream(this.u_1723_Y);
                        J_1907_R filedownload$progresslistener = new J_1907_R(p_237688_2_.trim(), this.u_1723_Y, p_237688_4_, p_237688_3_);
                        n_1700_B filedownload$downloadcountingoutputstream = new n_1700_B(this, outputstream);
                        filedownload$downloadcountingoutputstream.n_1700_B(filedownload$progresslistener);
                        IOUtils.copy((InputStream)httpresponse.getEntity().getContent(), (OutputStream)((Object)filedownload$downloadcountingoutputstream));
                        return;
                    }
                    this.G_564_y = true;
                    this.w_1484_f.abort();
                    return;
                }
                catch (Exception exception1) {
                    n_1700_B.error("Caught exception while downloading: " + exception1.getMessage());
                    this.G_564_y = true;
                    return;
                }
                finally {
                    block40: {
                        block41: {
                            CloseableHttpResponse httpresponse1;
                            this.w_1484_f.releaseConnection();
                            if (this.u_1723_Y != null) {
                                this.u_1723_Y.delete();
                            }
                            if (this.G_564_y) break block40;
                            if (p_237688_1_.J_1907_R.isEmpty() || p_237688_1_.R_4764_Y.isEmpty()) break block41;
                            try {
                                this.u_1723_Y = File.createTempFile("resources", ".tar.gz");
                                this.w_1484_f = new HttpGet(p_237688_1_.J_1907_R);
                                httpresponse1 = closeablehttpclient.execute((HttpUriRequest)this.w_1484_f);
                                p_237688_3_.J_1907_R = Long.parseLong(httpresponse1.getFirstHeader("Content-Length").getValue());
                                if (httpresponse1.getStatusLine().getStatusCode() != 200) {
                                    this.G_564_y = true;
                                    this.w_1484_f.abort();
                                    return;
                                }
                            }
                            catch (Exception exception) {
                                n_1700_B.error("Caught exception while downloading: " + exception.getMessage());
                                this.G_564_y = true;
                            }
                            FileOutputStream outputstream1 = new FileOutputStream(this.u_1723_Y);
                            R_4764_Y filedownload$resourcepackprogresslistener = new R_4764_Y(this.u_1723_Y, p_237688_3_, p_237688_1_);
                            n_1700_B filedownload$downloadcountingoutputstream1 = new n_1700_B(this, outputstream1);
                            filedownload$downloadcountingoutputstream1.n_1700_B(filedownload$resourcepackprogresslistener);
                            IOUtils.copy((InputStream)httpresponse1.getEntity().getContent(), (OutputStream)((Object)filedownload$downloadcountingoutputstream1));
                            break block40;
                            finally {
                                this.w_1484_f.releaseConnection();
                                if (this.u_1723_Y != null) {
                                    this.u_1723_Y.delete();
                                }
                            }
                        }
                        this.R_4764_Y = true;
                    }
                    if (closeablehttpclient != null) {
                        try {
                            closeablehttpclient.close();
                        }
                        catch (IOException ioexception) {
                            n_1700_B.error("Failed to close Realms download client");
                        }
                    }
                }
            });
            this.t_148_a.setUncaughtExceptionHandler(new RealmsDefaultUncaughtExceptionHandler(n_1700_B));
            this.t_148_a.start();
        }
    }

    public void n_1700_B() {
        if (this.w_1484_f != null) {
            this.w_1484_f.abort();
        }
        if (this.u_1723_Y != null) {
            this.u_1723_Y.delete();
        }
        this.J_1907_R = true;
    }

    public boolean J_1907_R() {
        return this.R_4764_Y;
    }

    public boolean R_4764_Y() {
        return this.G_564_y;
    }

    public boolean G_564_y() {
        return this.P_1922_E;
    }

    public static String J_1907_R(String p_224828_0_) {
        p_224828_0_ = ((String)p_224828_0_).replaceAll("[\\./\"]", "_");
        for (String s : u_2550_I) {
            if (!((String)p_224828_0_).equalsIgnoreCase(s)) continue;
            p_224828_0_ = "_" + (String)p_224828_0_ + "_";
        }
        return p_224828_0_;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(String p_237690_1_, File p_237690_2_, b_2971_z p_237690_3_) throws IOException {
        Object s;
        Pattern pattern = Pattern.compile(".*-([0-9]+)$");
        int i = 1;
        for (char c0 : SharedConstants.P_1922_E) {
            p_237690_1_ = p_237690_1_.replace(c0, '_');
        }
        if (StringUtils.isEmpty((CharSequence)p_237690_1_)) {
            p_237690_1_ = "Realm";
        }
        p_237690_1_ = A_1038_p.J_1907_R(p_237690_1_);
        try {
            Object object = p_237690_3_.n_1700_B().iterator();
            while (object.hasNext()) {
                J_2011_a worldsummary = (J_2011_a)object.next();
                if (!worldsummary.n_1700_B().toLowerCase(Locale.ROOT).startsWith(p_237690_1_.toLowerCase(Locale.ROOT))) continue;
                Matcher matcher = pattern.matcher(worldsummary.n_1700_B());
                if (matcher.matches()) {
                    if (Integer.valueOf(matcher.group(1)) <= i) continue;
                    i = Integer.valueOf(matcher.group(1));
                    continue;
                }
                ++i;
            }
        }
        catch (Exception exception1) {
            n_1700_B.error("Error getting level list", (Throwable)exception1);
            this.G_564_y = true;
            return;
        }
        if (p_237690_3_.n_1700_B(p_237690_1_) && i <= 1) {
            s = p_237690_1_;
        } else {
            s = p_237690_1_ + (String)(i == 1 ? "" : "-" + i);
            if (!p_237690_3_.n_1700_B((String)s)) {
                boolean flag = false;
                while (!flag) {
                    if (!p_237690_3_.n_1700_B((String)(s = p_237690_1_ + (String)(++i == 1 ? "" : "-" + i)))) continue;
                    flag = true;
                }
            }
        }
        TarArchiveInputStream tararchiveinputstream = null;
        File file1 = new File(MinecraftClient.A_4115_X().M_182_A.getAbsolutePath(), "saves");
        try {
            file1.mkdir();
            tararchiveinputstream = new TarArchiveInputStream((InputStream)new GzipCompressorInputStream((InputStream)new BufferedInputStream(new FileInputStream(p_237690_2_))));
            TarArchiveEntry tararchiveentry = tararchiveinputstream.getNextTarEntry();
            while (tararchiveentry != null) {
                File file2 = new File(file1, tararchiveentry.getName().replace("world", (CharSequence)s));
                if (tararchiveentry.isDirectory()) {
                    file2.mkdirs();
                } else {
                    file2.createNewFile();
                    try (FileOutputStream fileoutputstream = new FileOutputStream(file2);){
                        IOUtils.copy((InputStream)tararchiveinputstream, (OutputStream)fileoutputstream);
                    }
                }
                tararchiveentry = tararchiveinputstream.getNextTarEntry();
            }
        }
        catch (Exception exception) {
            n_1700_B.error("Error extracting world", (Throwable)exception);
            this.G_564_y = true;
        }
        finally {
            if (tararchiveinputstream != null) {
                tararchiveinputstream.close();
            }
            if (p_237690_2_ != null) {
                p_237690_2_.delete();
            }
            try (b_2971_z.n_1700_B saveformat$levelsave = p_237690_3_.R_4764_Y((String)s);){
                saveformat$levelsave.n_1700_B(((String)s).trim());
                Path path = saveformat$levelsave.n_1700_B(H_4757_Q.P_1922_E);
                A_1038_p.n_1700_B(path.toFile());
            }
            catch (IOException ioexception) {
                n_1700_B.error("Failed to rename unpacked realms level {}", s, (Object)ioexception);
            }
            this.v_4262_N = new File(file1, (String)s + File.separator + "resources.zip");
        }
    }

    private static void n_1700_B(File p_237689_0_) {
        if (p_237689_0_.exists()) {
            try {
                U_2912_j compoundnbt = r_1827_u.n_1700_B(p_237689_0_);
                U_2912_j compoundnbt1 = compoundnbt.M_182_A("Data");
                compoundnbt1.multiplayerClientSuggestionProvider("Player");
                r_1827_u.n_1700_B(compoundnbt, p_237689_0_);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class J_1907_R
    implements ActionListener {
        private final String J_1907_R;
        private final File R_4764_Y;
        private final b_2971_z G_564_y;
        private final C_290_v.n_1700_B P_1922_E;

        private J_1907_R(String p_i232192_2_, File p_i232192_3_, b_2971_z p_i232192_4_, C_290_v.n_1700_B p_i232192_5_) {
            this.J_1907_R = p_i232192_2_;
            this.R_4764_Y = p_i232192_3_;
            this.G_564_y = p_i232192_4_;
            this.P_1922_E = p_i232192_5_;
        }

        public void n_1700_B(ActionEvent p_actionPerformed_1_) {
            this.P_1922_E.n_1700_B = ((n_1700_B)((Object)p_actionPerformed_1_.getSource())).getByteCount();
            if (this.P_1922_E.n_1700_B >= this.P_1922_E.J_1907_R && !A_1038_p.this.J_1907_R && !A_1038_p.this.G_564_y) {
                try {
                    A_1038_p.this.P_1922_E = true;
                    A_1038_p.this.n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y);
                }
                catch (IOException ioexception) {
                    n_1700_B.error("Error extracting archive", (Throwable)ioexception);
                    A_1038_p.this.G_564_y = true;
                }
            }
        }
    }

    class n_1700_B
    extends CountingOutputStream {
        private ActionListener n_1700_B;

        public n_1700_B(A_1038_p this$0, OutputStream p_i51649_2_) {
            super(p_i51649_2_);
        }

        public void n_1700_B(ActionListener p_224804_1_) {
            this.n_1700_B = p_224804_1_;
        }

        protected void afterWrite(int p_afterWrite_1_) throws IOException {
            super.afterWrite(p_afterWrite_1_);
            if (this.n_1700_B != null) {
                this.n_1700_B.actionPerformed(new ActionEvent((Object)this, 0, null));
            }
        }
    }

    class R_4764_Y
    implements ActionListener {
        private final File J_1907_R;
        private final C_290_v.n_1700_B R_4764_Y;
        private final F_1410_V G_564_y;

        private R_4764_Y(File p_i51645_2_, C_290_v.n_1700_B p_i51645_3_, F_1410_V p_i51645_4_) {
            this.J_1907_R = p_i51645_2_;
            this.R_4764_Y = p_i51645_3_;
            this.G_564_y = p_i51645_4_;
        }

        public void n_1700_B(ActionEvent p_actionPerformed_1_) {
            this.R_4764_Y.n_1700_B = ((n_1700_B)((Object)p_actionPerformed_1_.getSource())).getByteCount();
            if (this.R_4764_Y.n_1700_B >= this.R_4764_Y.J_1907_R && !A_1038_p.this.J_1907_R) {
                try {
                    String s = Hashing.sha1().hashBytes(Files.toByteArray((File)this.J_1907_R)).toString();
                    if (s.equals(this.G_564_y.R_4764_Y)) {
                        FileUtils.copyFile((File)this.J_1907_R, (File)A_1038_p.this.v_4262_N);
                        A_1038_p.this.R_4764_Y = true;
                    } else {
                        n_1700_B.error("Resourcepack had wrong hash (expected " + this.G_564_y.R_4764_Y + ", found " + s + "). Deleting it.");
                        FileUtils.deleteQuietly((File)this.J_1907_R);
                        A_1038_p.this.G_564_y = true;
                    }
                }
                catch (IOException ioexception) {
                    n_1700_B.error("Error copying resourcepack file", (Object)ioexception.getMessage());
                    A_1038_p.this.G_564_y = true;
                }
            }
        }
    }
}



