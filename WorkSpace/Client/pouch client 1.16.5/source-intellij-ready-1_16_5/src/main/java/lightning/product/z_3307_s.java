/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lightning.product.D_1098_v;
import lightning.product.X_933_l;
import lightning.product.Z_4614_k;
import lightning.product.c_4037_x;
import lightning.product.y_3193_B;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.MemoryUtil;

public class z_3307_s
extends Z_4614_k
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final IntBuffer P_1922_E;
    private final FloatBuffer u_1723_Y;
    private final String v_4262_N;
    private boolean w_1484_f;
    private final y_3193_B t_148_a;

    public z_3307_s(String name, int type, int count, y_3193_B manager) {
        this.v_4262_N = name;
        this.R_4764_Y = count;
        this.G_564_y = type;
        this.t_148_a = manager;
        if (type <= 3) {
            this.P_1922_E = MemoryUtil.memAllocInt((int)count);
            this.u_1723_Y = null;
        } else {
            this.P_1922_E = null;
            this.u_1723_Y = MemoryUtil.memAllocFloat((int)count);
        }
        this.J_1907_R = -1;
        this.R_4764_Y();
    }

    public static int n_1700_B(int p_227806_0_, CharSequence p_227806_1_) {
        return X_933_l.J_1907_R(p_227806_0_, p_227806_1_);
    }

    public static void n_1700_B(int p_227805_0_, int p_227805_1_) {
        c_4037_x.u_1723_Y(p_227805_0_, p_227805_1_);
    }

    public static int J_1907_R(int p_227807_0_, CharSequence p_227807_1_) {
        return X_933_l.R_4764_Y(p_227807_0_, p_227807_1_);
    }

    @Override
    public void close() {
        if (this.P_1922_E != null) {
            MemoryUtil.memFree((Buffer)this.P_1922_E);
        }
        if (this.u_1723_Y != null) {
            MemoryUtil.memFree((Buffer)this.u_1723_Y);
        }
    }

    private void R_4764_Y() {
        this.w_1484_f = true;
        if (this.t_148_a != null) {
            this.t_148_a.J_1907_R();
        }
    }

    public static int n_1700_B(String typeName) {
        int i = -1;
        if ("int".equals(typeName)) {
            i = 0;
        } else if ("float".equals(typeName)) {
            i = 4;
        } else if (typeName.startsWith("matrix")) {
            if (typeName.endsWith("2x2")) {
                i = 8;
            } else if (typeName.endsWith("3x3")) {
                i = 9;
            } else if (typeName.endsWith("4x4")) {
                i = 10;
            }
        }
        return i;
    }

    public void n_1700_B(int uniformLocationIn) {
        this.J_1907_R = uniformLocationIn;
    }

    public String n_1700_B() {
        return this.v_4262_N;
    }

    @Override
    public void n_1700_B(float p_148090_1_) {
        ((Buffer)this.u_1723_Y).position(0);
        this.u_1723_Y.put(0, p_148090_1_);
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(float p_148087_1_, float p_148087_2_) {
        ((Buffer)this.u_1723_Y).position(0);
        this.u_1723_Y.put(0, p_148087_1_);
        this.u_1723_Y.put(1, p_148087_2_);
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(float p_148095_1_, float p_148095_2_, float p_148095_3_) {
        ((Buffer)this.u_1723_Y).position(0);
        this.u_1723_Y.put(0, p_148095_1_);
        this.u_1723_Y.put(1, p_148095_2_);
        this.u_1723_Y.put(2, p_148095_3_);
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(float p_148081_1_, float p_148081_2_, float p_148081_3_, float p_148081_4_) {
        ((Buffer)this.u_1723_Y).position(0);
        this.u_1723_Y.put(p_148081_1_);
        this.u_1723_Y.put(p_148081_2_);
        this.u_1723_Y.put(p_148081_3_);
        this.u_1723_Y.put(p_148081_4_);
        ((Buffer)this.u_1723_Y).flip();
        this.R_4764_Y();
    }

    @Override
    public void J_1907_R(float p_148092_1_, float p_148092_2_, float p_148092_3_, float p_148092_4_) {
        ((Buffer)this.u_1723_Y).position(0);
        if (this.G_564_y >= 4) {
            this.u_1723_Y.put(0, p_148092_1_);
        }
        if (this.G_564_y >= 5) {
            this.u_1723_Y.put(1, p_148092_2_);
        }
        if (this.G_564_y >= 6) {
            this.u_1723_Y.put(2, p_148092_3_);
        }
        if (this.G_564_y >= 7) {
            this.u_1723_Y.put(3, p_148092_4_);
        }
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(int p_148083_1_, int p_148083_2_, int p_148083_3_, int p_148083_4_) {
        ((Buffer)this.P_1922_E).position(0);
        if (this.G_564_y >= 0) {
            this.P_1922_E.put(0, p_148083_1_);
        }
        if (this.G_564_y >= 1) {
            this.P_1922_E.put(1, p_148083_2_);
        }
        if (this.G_564_y >= 2) {
            this.P_1922_E.put(2, p_148083_3_);
        }
        if (this.G_564_y >= 3) {
            this.P_1922_E.put(3, p_148083_4_);
        }
        this.R_4764_Y();
    }

    @Override
    public void n_1700_B(float[] p_148097_1_) {
        if (p_148097_1_.length < this.R_4764_Y) {
            n_1700_B.warn("Uniform.set called with a too-small value array (expected {}, got {}). Ignoring.", (Object)this.R_4764_Y, (Object)p_148097_1_.length);
        } else {
            ((Buffer)this.u_1723_Y).position(0);
            this.u_1723_Y.put(p_148097_1_);
            ((Buffer)this.u_1723_Y).position(0);
            this.R_4764_Y();
        }
    }

    @Override
    public void n_1700_B(D_1098_v p_195652_1_) {
        ((Buffer)this.u_1723_Y).position(0);
        p_195652_1_.n_1700_B(this.u_1723_Y);
        this.R_4764_Y();
    }

    public void J_1907_R() {
        if (!this.w_1484_f) {
            // empty if block
        }
        this.w_1484_f = false;
        if (this.G_564_y <= 3) {
            this.G_564_y();
        } else if (this.G_564_y <= 7) {
            this.P_1922_E();
        } else {
            if (this.G_564_y > 10) {
                n_1700_B.warn("Uniform.upload called, but type value ({}) is not a valid type. Ignoring.", (Object)this.G_564_y);
                return;
            }
            this.u_1723_Y();
        }
    }

    private void G_564_y() {
        ((Buffer)this.u_1723_Y).clear();
        switch (this.G_564_y) {
            case 0: {
                c_4037_x.n_1700_B(this.J_1907_R, this.P_1922_E);
                break;
            }
            case 1: {
                c_4037_x.J_1907_R(this.J_1907_R, this.P_1922_E);
                break;
            }
            case 2: {
                c_4037_x.R_4764_Y(this.J_1907_R, this.P_1922_E);
                break;
            }
            case 3: {
                c_4037_x.G_564_y(this.J_1907_R, this.P_1922_E);
                break;
            }
            default: {
                n_1700_B.warn("Uniform.upload called, but count value ({}) is  not in the range of 1 to 4. Ignoring.", (Object)this.R_4764_Y);
            }
        }
    }

    private void P_1922_E() {
        ((Buffer)this.u_1723_Y).clear();
        switch (this.G_564_y) {
            case 4: {
                c_4037_x.n_1700_B(this.J_1907_R, this.u_1723_Y);
                break;
            }
            case 5: {
                c_4037_x.J_1907_R(this.J_1907_R, this.u_1723_Y);
                break;
            }
            case 6: {
                c_4037_x.R_4764_Y(this.J_1907_R, this.u_1723_Y);
                break;
            }
            case 7: {
                c_4037_x.G_564_y(this.J_1907_R, this.u_1723_Y);
                break;
            }
            default: {
                n_1700_B.warn("Uniform.upload called, but count value ({}) is not in the range of 1 to 4. Ignoring.", (Object)this.R_4764_Y);
            }
        }
    }

    private void u_1723_Y() {
        ((Buffer)this.u_1723_Y).clear();
        switch (this.G_564_y) {
            case 8: {
                c_4037_x.n_1700_B(this.J_1907_R, false, this.u_1723_Y);
                break;
            }
            case 9: {
                c_4037_x.J_1907_R(this.J_1907_R, false, this.u_1723_Y);
                break;
            }
            case 10: {
                c_4037_x.R_4764_Y(this.J_1907_R, false, this.u_1723_Y);
            }
        }
    }
}

