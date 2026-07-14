/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.entity.InputStreamEntity
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 *  org.apache.http.util.Args
 *  org.apache.http.util.EntityUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import lightning.product.D_4361_a;
import lightning.product.R_3908_n;
import lightning.product.u_3100_Q;
import lightning.product.UploadStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class i_1637_u {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final File J_1907_R;
    private final long R_4764_Y;
    private final int G_564_y;
    private final R_3908_n P_1922_E;
    private final String u_1723_Y;
    private final String v_4262_N;
    private final String w_1484_f;
    private final UploadStatus t_148_a;
    private final AtomicBoolean s_956_w = new AtomicBoolean(false);
    private CompletableFuture<D_4361_a> u_2550_I;
    private final RequestConfig M_588_G = RequestConfig.custom().setSocketTimeout((int)TimeUnit.MINUTES.toMillis(10L)).setConnectTimeout((int)TimeUnit.SECONDS.toMillis(15L)).build();

    public i_1637_u(File p_i232194_1_, long p_i232194_2_, int p_i232194_4_, R_3908_n p_i232194_5_, u_3100_Q p_i232194_6_, String p_i232194_7_, UploadStatus p_i232194_8_) {
        this.J_1907_R = p_i232194_1_;
        this.R_4764_Y = p_i232194_2_;
        this.G_564_y = p_i232194_4_;
        this.P_1922_E = p_i232194_5_;
        this.u_1723_Y = p_i232194_6_.n_1700_B();
        this.v_4262_N = p_i232194_6_.R_4764_Y();
        this.w_1484_f = p_i232194_7_;
        this.t_148_a = p_i232194_8_;
    }

    public void n_1700_B(Consumer<D_4361_a> p_224874_1_) {
        if (this.u_2550_I == null) {
            this.u_2550_I = CompletableFuture.supplyAsync(() -> this.n_1700_B(0));
            this.u_2550_I.thenAccept((Consumer)p_224874_1_);
        }
    }

    public void n_1700_B() {
        this.s_956_w.set(true);
        if (this.u_2550_I != null) {
            this.u_2550_I.cancel(false);
            this.u_2550_I = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private D_4361_a n_1700_B(int p_224879_1_) {
        D_4361_a uploadresult;
        D_4361_a.n_1700_B uploadresult$builder = new D_4361_a.n_1700_B();
        if (this.s_956_w.get()) {
            return uploadresult$builder.n_1700_B();
        }
        this.t_148_a.J_1907_R = this.J_1907_R.length();
        HttpPost httppost = new HttpPost(this.P_1922_E.J_1907_R().resolve("/upload/" + this.R_4764_Y + "/" + this.G_564_y));
        CloseableHttpClient closeablehttpclient = HttpClientBuilder.create().setDefaultRequestConfig(this.M_588_G).build();
        try {
            this.n_1700_B(httppost);
            CloseableHttpResponse httpresponse = closeablehttpclient.execute((HttpUriRequest)httppost);
            long i = this.n_1700_B((HttpResponse)httpresponse);
            if (!this.n_1700_B(i, p_224879_1_)) {
                this.n_1700_B((HttpResponse)httpresponse, uploadresult$builder);
                D_4361_a d_4361_a = uploadresult$builder.n_1700_B();
                return d_4361_a;
            }
            uploadresult = this.J_1907_R(i, p_224879_1_);
        }
        catch (Exception exception) {
            if (!this.s_956_w.get()) {
                n_1700_B.error("Caught exception while uploading: ", (Throwable)exception);
            }
            D_4361_a d_4361_a = uploadresult$builder.n_1700_B();
            return d_4361_a;
        }
        finally {
            this.n_1700_B(httppost, closeablehttpclient);
        }
        return uploadresult;
    }

    private void n_1700_B(HttpPost p_224877_1_, CloseableHttpClient p_224877_2_) {
        p_224877_1_.releaseConnection();
        if (p_224877_2_ != null) {
            try {
                p_224877_2_.close();
            }
            catch (IOException ioexception) {
                n_1700_B.error("Failed to close Realms upload client");
            }
        }
    }

    private void n_1700_B(HttpPost p_224872_1_) throws FileNotFoundException {
        p_224872_1_.setHeader("Cookie", "sid=" + this.u_1723_Y + ";token=" + this.P_1922_E.n_1700_B() + ";user=" + this.v_4262_N + ";version=" + this.w_1484_f);
        n_1700_B fileupload$custominputstreamentity = new n_1700_B(new FileInputStream(this.J_1907_R), this.J_1907_R.length(), this.t_148_a);
        fileupload$custominputstreamentity.setContentType("application/octet-stream");
        p_224872_1_.setEntity((HttpEntity)fileupload$custominputstreamentity);
    }

    private void n_1700_B(HttpResponse p_224875_1_, D_4361_a.n_1700_B p_224875_2_) throws IOException {
        String s;
        int i = p_224875_1_.getStatusLine().getStatusCode();
        if (i == 401) {
            n_1700_B.debug("Realms server returned 401: " + String.valueOf(p_224875_1_.getFirstHeader("WWW-Authenticate")));
        }
        p_224875_2_.n_1700_B(i);
        if (p_224875_1_.getEntity() != null && (s = EntityUtils.toString((HttpEntity)p_224875_1_.getEntity(), (String)"UTF-8")) != null) {
            try {
                JsonParser jsonparser = new JsonParser();
                JsonElement jsonelement = jsonparser.parse(s).getAsJsonObject().get("errorMsg");
                Optional<String> optional = Optional.ofNullable(jsonelement).map(JsonElement::getAsString);
                p_224875_2_.n_1700_B(optional.orElse(null));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private boolean n_1700_B(long p_224882_1_, int p_224882_3_) {
        return p_224882_1_ > 0L && p_224882_3_ + 1 < 5;
    }

    private D_4361_a J_1907_R(long p_224876_1_, int p_224876_3_) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(p_224876_1_).toMillis());
        return this.n_1700_B(p_224876_3_ + 1);
    }

    private long n_1700_B(HttpResponse p_224880_1_) {
        return Optional.ofNullable(p_224880_1_.getFirstHeader("Retry-After")).map(Header::getValue).map(Long::valueOf).orElse(0L);
    }

    public boolean J_1907_R() {
        return this.u_2550_I.isDone() || this.u_2550_I.isCancelled();
    }

    static class n_1700_B
    extends InputStreamEntity {
        private final long n_1700_B;
        private final InputStream J_1907_R;
        private final UploadStatus R_4764_Y;

        public n_1700_B(InputStream p_i51622_1_, long p_i51622_2_, UploadStatus p_i51622_4_) {
            super(p_i51622_1_);
            this.J_1907_R = p_i51622_1_;
            this.n_1700_B = p_i51622_2_;
            this.R_4764_Y = p_i51622_4_;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void writeTo(OutputStream p_writeTo_1_) throws IOException {
            block7: {
                Args.notNull((Object)p_writeTo_1_, (String)"Output stream");
                try (InputStream inputstream = this.J_1907_R;){
                    int j;
                    byte[] abyte = new byte[4096];
                    if (this.n_1700_B < 0L) {
                        int j2;
                        while ((j2 = inputstream.read(abyte)) != -1) {
                            p_writeTo_1_.write(abyte, 0, j2);
                            this.R_4764_Y.n_1700_B += (long)j2;
                        }
                        break block7;
                    }
                    for (long i = this.n_1700_B; i > 0L; i -= (long)j) {
                        j = inputstream.read(abyte, 0, (int)Math.min(4096L, i));
                        if (j == -1) {
                            break;
                        }
                        p_writeTo_1_.write(abyte, 0, j);
                        this.R_4764_Y.n_1700_B += (long)j;
                        p_writeTo_1_.flush();
                    }
                }
            }
        }
    }
}


