/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import lightning.product.Resource;
import lightning.product.T_335_n;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import org.apache.commons.io.IOUtils;

public class r_1328_I
implements Resource {
    private final String n_1700_B;
    private final g_2336_b J_1907_R;
    private final InputStream R_4764_Y;
    private final InputStream G_564_y;
    private boolean P_1922_E;
    private JsonObject u_1723_Y;

    public r_1328_I(String packNameIn, g_2336_b locationIn, InputStream inputStreamIn, @Nullable InputStream metadataInputStreamIn) {
        this.n_1700_B = packNameIn;
        this.J_1907_R = locationIn;
        this.R_4764_Y = inputStreamIn;
        this.G_564_y = metadataInputStreamIn;
    }

    @Override
    public g_2336_b n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public InputStream J_1907_R() {
        return this.R_4764_Y;
    }

    public boolean G_564_y() {
        return this.G_564_y != null;
    }

    @Override
    @Nullable
    public <T> T n_1700_B(T_335_n<T> serializer) {
        if (!this.G_564_y()) {
            return null;
        }
        if (this.u_1723_Y == null && !this.P_1922_E) {
            this.P_1922_E = true;
            BufferedReader bufferedreader = null;
            try {
                bufferedreader = new BufferedReader(new InputStreamReader(this.G_564_y, StandardCharsets.UTF_8));
                this.u_1723_Y = i_4431_W.n_1700_B(bufferedreader);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(bufferedreader);
                throw throwable;
            }
            IOUtils.closeQuietly((Reader)bufferedreader);
        }
        if (this.u_1723_Y == null) {
            return null;
        }
        String s = serializer.n_1700_B();
        return this.u_1723_Y.has(s) ? (T)serializer.J_1907_R(i_4431_W.M_588_G(this.u_1723_Y, s)) : null;
    }

    @Override
    public String R_4764_Y() {
        return this.n_1700_B;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof r_1328_I)) {
            return false;
        }
        r_1328_I simpleresource = (r_1328_I)p_equals_1_;
        if (this.J_1907_R != null ? !this.J_1907_R.equals(simpleresource.J_1907_R) : simpleresource.J_1907_R != null) {
            return false;
        }
        return !(this.n_1700_B != null ? !this.n_1700_B.equals(simpleresource.n_1700_B) : simpleresource.n_1700_B != null);
    }

    public int hashCode() {
        int i = this.n_1700_B != null ? this.n_1700_B.hashCode() : 0;
        return 31 * i + (this.J_1907_R != null ? this.J_1907_R.hashCode() : 0);
    }

    @Override
    public void close() throws IOException {
        this.R_4764_Y.close();
        if (this.G_564_y != null) {
            this.G_564_y.close();
        }
    }
}


