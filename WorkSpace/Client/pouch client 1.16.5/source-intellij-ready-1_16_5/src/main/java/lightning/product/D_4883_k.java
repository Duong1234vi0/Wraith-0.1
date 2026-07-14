/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.X_933_l;
import lightning.product.b_1213_w;
import lightning.product.c_4037_x;
import net.optifine.render.MultiTextureData;
import net.optifine.render.MultiTextureRenderer;
import net.optifine.render.VboRange;
import net.optifine.render.VboRegion;

public class D_4883_k
implements AutoCloseable {
    private int n_1700_B;
    private final b_1213_w J_1907_R;
    private int R_4764_Y;
    private VboRegion G_564_y;
    private VboRange P_1922_E;
    private int u_1723_Y;
    private MultiTextureData v_4262_N;

    public D_4883_k(b_1213_w vertexFormatIn) {
        this.J_1907_R = vertexFormatIn;
        c_4037_x.n_1700_B((Integer p_lambda$new$0_1_) -> {
            this.n_1700_B = p_lambda$new$0_1_;
        });
    }

    public void n_1700_B() {
        X_933_l.v_4262_N(34962, this.n_1700_B);
    }

    public void n_1700_B(D_3318_r bufferIn) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> this.R_4764_Y(bufferIn));
        } else {
            this.R_4764_Y(bufferIn);
        }
    }

    public CompletableFuture<Void> J_1907_R(D_3318_r bufferIn) {
        if (!c_4037_x.J_1907_R()) {
            return CompletableFuture.runAsync(() -> this.R_4764_Y(bufferIn), p_lambda$uploadLater$3_0_ -> c_4037_x.n_1700_B(p_lambda$uploadLater$3_0_::run));
        }
        this.R_4764_Y(bufferIn);
        return CompletableFuture.completedFuture(null);
    }

    private void R_4764_Y(D_3318_r bufferIn) {
        Pair<D_3318_r.n_1700_B, ByteBuffer> pair = bufferIn.v_4262_N();
        this.u_1723_Y = 0;
        D_3318_r.n_1700_B bufferbuilder$drawstate = (D_3318_r.n_1700_B)pair.getFirst();
        if (bufferbuilder$drawstate.G_564_y() != 7) {
            this.u_1723_Y = bufferbuilder$drawstate.G_564_y();
        }
        if (this.G_564_y != null) {
            ByteBuffer bytebuffer1 = (ByteBuffer)pair.getSecond();
            this.G_564_y.bufferData(bytebuffer1, this.P_1922_E);
        } else {
            this.v_4262_N = bufferbuilder$drawstate.n_1700_B();
            if (this.n_1700_B != -1) {
                ByteBuffer bytebuffer = (ByteBuffer)pair.getSecond();
                this.R_4764_Y = bytebuffer.remaining() / this.J_1907_R.J_1907_R();
                this.n_1700_B();
                c_4037_x.n_1700_B(34962, bytebuffer, 35044);
                D_4883_k.J_1907_R();
            }
        }
    }

    public void n_1700_B(D_1098_v matrixIn, int modeIn) {
        c_4037_x.v_4276_D();
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(matrixIn);
        if (this.u_1723_Y > 0) {
            modeIn = this.u_1723_Y;
        }
        if (this.G_564_y != null) {
            this.G_564_y.drawArrays(modeIn, this.P_1922_E);
        } else if (this.v_4262_N != null) {
            MultiTextureRenderer.draw(modeIn, this.v_4262_N);
        } else {
            c_4037_x.G_564_y(modeIn, 0, this.R_4764_Y);
        }
        c_4037_x.d_2461_k();
    }

    public void n_1700_B(int p_draw_1_) {
        if (this.u_1723_Y > 0) {
            p_draw_1_ = this.u_1723_Y;
        }
        if (this.G_564_y != null) {
            this.G_564_y.drawArrays(p_draw_1_, this.P_1922_E);
        } else if (this.v_4262_N != null) {
            MultiTextureRenderer.draw(p_draw_1_, this.v_4262_N);
        } else {
            c_4037_x.G_564_y(p_draw_1_, 0, this.R_4764_Y);
        }
    }

    public static void J_1907_R() {
        X_933_l.v_4262_N(34962, 0);
    }

    @Override
    public void close() {
        if (this.n_1700_B >= 0) {
            c_4037_x.P_4830_p(this.n_1700_B);
            this.n_1700_B = -1;
        }
    }

    public void n_1700_B(VboRegion p_setVboRegion_1_) {
        if (p_setVboRegion_1_ != null) {
            this.close();
            this.G_564_y = p_setVboRegion_1_;
            this.P_1922_E = new VboRange();
        }
    }

    public VboRegion R_4764_Y() {
        return this.G_564_y;
    }
}

