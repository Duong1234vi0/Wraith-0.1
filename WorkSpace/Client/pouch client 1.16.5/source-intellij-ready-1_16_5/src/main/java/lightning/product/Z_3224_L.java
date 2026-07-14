/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_1114_L;
import lightning.product.c_4037_x;
import lightning.product.i_2518_W;
import net.optifine.shaders.Shaders;

public class Z_3224_L
implements AutoCloseable {
    public static final int n_1700_B = Z_3224_L.n_1700_B(0, 10);
    private final T_1114_L J_1907_R = new T_1114_L(16, 16, false);

    public Z_3224_L() {
        i_2518_W nativeimage = this.J_1907_R.J_1907_R();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    nativeimage.n_1700_B(j, i, -1308622593);
                    continue;
                }
                int k = (int)((1.0f - (float)j / 15.0f * 0.75f) * 255.0f);
                nativeimage.n_1700_B(j, i, k << 24 | 0xFFFFFF);
            }
        }
        c_4037_x.P_1922_E(33985);
        this.J_1907_R.bindTexture();
        c_4037_x.u_2550_I(5890);
        c_4037_x.z_1737_N();
        float f = 0.06666667f;
        c_4037_x.J_1907_R(0.06666667f, 0.06666667f, 0.06666667f);
        c_4037_x.u_2550_I(5888);
        this.J_1907_R.bindTexture();
        nativeimage.n_1700_B(0, 0, 0, 0, 0, nativeimage.n_1700_B(), nativeimage.J_1907_R(), false, true, false, false);
        c_4037_x.P_1922_E(33984);
    }

    @Override
    public void close() {
        this.J_1907_R.close();
    }

    public void n_1700_B() {
        if (!Shaders.isOverlayDisabled()) {
            c_4037_x.n_1700_B(this.J_1907_R::getGlTextureId, 16);
        }
    }

    public static int n_1700_B(float uIn) {
        return (int)(uIn * 15.0f);
    }

    public static int n_1700_B(boolean hurtIn) {
        return hurtIn ? 3 : 10;
    }

    public static int n_1700_B(int uIn, int vIn) {
        return uIn | vIn << 16;
    }

    public static int n_1700_B(float uIn, boolean hurtIn) {
        return Z_3224_L.n_1700_B(Z_3224_L.n_1700_B(uIn), Z_3224_L.n_1700_B(hurtIn));
    }

    public void J_1907_R() {
        if (!Shaders.isOverlayDisabled()) {
            c_4037_x.Z_976_R();
        }
    }
}

