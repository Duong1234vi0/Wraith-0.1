/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import lightning.product.RealmsClientConfig;
import lightning.product.y_1700_S;

public abstract class j_276_v<T extends j_276_v<T>> {
    protected HttpURLConnection n_1700_B;
    private boolean R_4764_Y;
    protected String J_1907_R;

    public j_276_v(String p_i51788_1_, int p_i51788_2_, int p_i51788_3_) {
        try {
            this.J_1907_R = p_i51788_1_;
            Proxy proxy = RealmsClientConfig.n_1700_B();
            this.n_1700_B = proxy != null ? (HttpURLConnection)new URL(p_i51788_1_).openConnection(proxy) : (HttpURLConnection)new URL(p_i51788_1_).openConnection();
            this.n_1700_B.setConnectTimeout(p_i51788_2_);
            this.n_1700_B.setReadTimeout(p_i51788_3_);
        }
        catch (MalformedURLException malformedurlexception) {
            throw new y_1700_S(malformedurlexception.getMessage(), malformedurlexception);
        }
        catch (IOException ioexception) {
            throw new y_1700_S(ioexception.getMessage(), ioexception);
        }
    }

    public void n_1700_B(String p_224962_1_, String p_224962_2_) {
        j_276_v.n_1700_B(this.n_1700_B, p_224962_1_, p_224962_2_);
    }

    public static void n_1700_B(HttpURLConnection p_224967_0_, String p_224967_1_, String p_224967_2_) {
        String s = p_224967_0_.getRequestProperty("Cookie");
        if (s == null) {
            p_224967_0_.setRequestProperty("Cookie", p_224967_1_ + "=" + p_224967_2_);
        } else {
            p_224967_0_.setRequestProperty("Cookie", s + ";" + p_224967_1_ + "=" + p_224967_2_);
        }
    }

    public int n_1700_B() {
        return j_276_v.n_1700_B(this.n_1700_B);
    }

    public static int n_1700_B(HttpURLConnection p_224964_0_) {
        String s = p_224964_0_.getHeaderField("Retry-After");
        try {
            return Integer.valueOf(s);
        }
        catch (Exception exception) {
            return 5;
        }
    }

    public int J_1907_R() {
        try {
            this.G_564_y();
            return this.n_1700_B.getResponseCode();
        }
        catch (Exception exception) {
            throw new y_1700_S(exception.getMessage(), exception);
        }
    }

    public String R_4764_Y() {
        try {
            this.G_564_y();
            String s = null;
            s = this.J_1907_R() >= 400 ? this.n_1700_B(this.n_1700_B.getErrorStream()) : this.n_1700_B(this.n_1700_B.getInputStream());
            this.u_1723_Y();
            return s;
        }
        catch (IOException ioexception) {
            throw new y_1700_S(ioexception.getMessage(), ioexception);
        }
    }

    private String n_1700_B(InputStream p_224954_1_) throws IOException {
        if (p_224954_1_ == null) {
            return "";
        }
        InputStreamReader inputstreamreader = new InputStreamReader(p_224954_1_, "UTF-8");
        StringBuilder stringbuilder = new StringBuilder();
        int i = inputstreamreader.read();
        while (i != -1) {
            stringbuilder.append((char)i);
            i = inputstreamreader.read();
        }
        return stringbuilder.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void u_1723_Y() {
        byte[] abyte = new byte[1024];
        try {
            InputStream inputstream = this.n_1700_B.getInputStream();
            while (inputstream.read(abyte) > 0) {
            }
            inputstream.close();
            return;
        }
        catch (Exception exception) {
            try {
                InputStream inputstream1 = this.n_1700_B.getErrorStream();
                if (inputstream1 != null) {
                    while (inputstream1.read(abyte) > 0) {
                    }
                    inputstream1.close();
                    return;
                }
            }
            catch (IOException ioexception) {
                return;
            }
        }
        finally {
            if (this.n_1700_B != null) {
                this.n_1700_B.disconnect();
            }
        }
    }

    protected T G_564_y() {
        if (this.R_4764_Y) {
            return (T)this;
        }
        T t = this.P_1922_E();
        this.R_4764_Y = true;
        return t;
    }

    protected abstract T P_1922_E();

    public static j_276_v<?> n_1700_B(String p_224953_0_) {
        return new J_1907_R(p_224953_0_, 5000, 60000);
    }

    public static j_276_v<?> n_1700_B(String p_224960_0_, int p_224960_1_, int p_224960_2_) {
        return new J_1907_R(p_224960_0_, p_224960_1_, p_224960_2_);
    }

    public static j_276_v<?> J_1907_R(String p_224951_0_, String p_224951_1_) {
        return new R_4764_Y(p_224951_0_, p_224951_1_, 5000, 60000);
    }

    public static j_276_v<?> n_1700_B(String p_224959_0_, String p_224959_1_, int p_224959_2_, int p_224959_3_) {
        return new R_4764_Y(p_224959_0_, p_224959_1_, p_224959_2_, p_224959_3_);
    }

    public static j_276_v<?> J_1907_R(String p_224952_0_) {
        return new n_1700_B(p_224952_0_, 5000, 60000);
    }

    public static j_276_v<?> R_4764_Y(String p_224965_0_, String p_224965_1_) {
        return new G_564_y(p_224965_0_, p_224965_1_, 5000, 60000);
    }

    public static j_276_v<?> J_1907_R(String p_224966_0_, String p_224966_1_, int p_224966_2_, int p_224966_3_) {
        return new G_564_y(p_224966_0_, p_224966_1_, p_224966_2_, p_224966_3_);
    }

    public String R_4764_Y(String p_224956_1_) {
        return j_276_v.n_1700_B(this.n_1700_B, p_224956_1_);
    }

    public static String n_1700_B(HttpURLConnection p_224961_0_, String p_224961_1_) {
        try {
            return p_224961_0_.getHeaderField(p_224961_1_);
        }
        catch (Exception exception) {
            return "";
        }
    }

    public static class J_1907_R
    extends j_276_v<J_1907_R> {
        public J_1907_R(String p_i51799_1_, int p_i51799_2_, int p_i51799_3_) {
            super(p_i51799_1_, p_i51799_2_, p_i51799_3_);
        }

        public J_1907_R u_1723_Y() {
            try {
                this.n_1700_B.setDoInput(true);
                this.n_1700_B.setDoOutput(true);
                this.n_1700_B.setUseCaches(false);
                this.n_1700_B.setRequestMethod("GET");
                return this;
            }
            catch (Exception exception) {
                throw new y_1700_S(exception.getMessage(), exception);
            }
        }

        @Override
        public /* synthetic */ j_276_v P_1922_E() {
            return this.u_1723_Y();
        }
    }

    public static class R_4764_Y
    extends j_276_v<R_4764_Y> {
        private final String R_4764_Y;

        public R_4764_Y(String p_i51798_1_, String p_i51798_2_, int p_i51798_3_, int p_i51798_4_) {
            super(p_i51798_1_, p_i51798_3_, p_i51798_4_);
            this.R_4764_Y = p_i51798_2_;
        }

        public R_4764_Y u_1723_Y() {
            try {
                if (this.R_4764_Y != null) {
                    this.n_1700_B.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                }
                this.n_1700_B.setDoInput(true);
                this.n_1700_B.setDoOutput(true);
                this.n_1700_B.setUseCaches(false);
                this.n_1700_B.setRequestMethod("POST");
                OutputStream outputstream = this.n_1700_B.getOutputStream();
                OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream, "UTF-8");
                outputstreamwriter.write(this.R_4764_Y);
                outputstreamwriter.close();
                outputstream.flush();
                return this;
            }
            catch (Exception exception) {
                throw new y_1700_S(exception.getMessage(), exception);
            }
        }

        @Override
        public /* synthetic */ j_276_v P_1922_E() {
            return this.u_1723_Y();
        }
    }

    public static class n_1700_B
    extends j_276_v<n_1700_B> {
        public n_1700_B(String p_i51800_1_, int p_i51800_2_, int p_i51800_3_) {
            super(p_i51800_1_, p_i51800_2_, p_i51800_3_);
        }

        public n_1700_B u_1723_Y() {
            try {
                this.n_1700_B.setDoOutput(true);
                this.n_1700_B.setRequestMethod("DELETE");
                this.n_1700_B.connect();
                return this;
            }
            catch (Exception exception) {
                throw new y_1700_S(exception.getMessage(), exception);
            }
        }

        @Override
        public /* synthetic */ j_276_v P_1922_E() {
            return this.u_1723_Y();
        }
    }

    public static class G_564_y
    extends j_276_v<G_564_y> {
        private final String R_4764_Y;

        public G_564_y(String p_i51797_1_, String p_i51797_2_, int p_i51797_3_, int p_i51797_4_) {
            super(p_i51797_1_, p_i51797_3_, p_i51797_4_);
            this.R_4764_Y = p_i51797_2_;
        }

        public G_564_y u_1723_Y() {
            try {
                if (this.R_4764_Y != null) {
                    this.n_1700_B.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                }
                this.n_1700_B.setDoOutput(true);
                this.n_1700_B.setDoInput(true);
                this.n_1700_B.setRequestMethod("PUT");
                OutputStream outputstream = this.n_1700_B.getOutputStream();
                OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream, "UTF-8");
                outputstreamwriter.write(this.R_4764_Y);
                outputstreamwriter.close();
                outputstream.flush();
                return this;
            }
            catch (Exception exception) {
                throw new y_1700_S(exception.getMessage(), exception);
            }
        }

        @Override
        public /* synthetic */ j_276_v P_1922_E() {
            return this.u_1723_Y();
        }
    }
}


