/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.H_3330_w;
import lightning.product.Z_1993_T;
import lightning.product.g_221_o;

final class I_2695_V
extends Enum<I_2695_V> {
    public static final /* enum */ I_2695_V n_1700_B = new I_2695_V(0, 0, 28, 32, 8);
    public static final /* enum */ I_2695_V J_1907_R = new I_2695_V(84, 0, 28, 32, 8);
    public static final /* enum */ I_2695_V R_4764_Y = new I_2695_V(0, 64, 32, 28, 5);
    public static final /* enum */ I_2695_V G_564_y = new I_2695_V(96, 64, 32, 28, 5);
    private final int P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final int t_148_a;
    private static final /* synthetic */ I_2695_V[] s_956_w;

    public static I_2695_V[] values() {
        return (I_2695_V[])s_956_w.clone();
    }

    public static I_2695_V valueOf(String name) {
        return Enum.valueOf(I_2695_V.class, name);
    }

    private I_2695_V(int textureX, int textureY, int widthIn, int heightIn, int max) {
        this.P_1922_E = textureX;
        this.u_1723_Y = textureY;
        this.v_4262_N = widthIn;
        this.w_1484_f = heightIn;
        this.t_148_a = max;
    }

    public int n_1700_B() {
        return this.t_148_a;
    }

    public void n_1700_B(g_221_o matrixStack, C_2701_A abstractGui, int offsetX, int offsetY, boolean isSelected, int index) {
        int i = this.P_1922_E;
        if (index > 0) {
            i += this.v_4262_N;
        }
        if (index == this.t_148_a - 1) {
            i += this.v_4262_N;
        }
        int j = isSelected ? this.u_1723_Y + this.w_1484_f : this.u_1723_Y;
        abstractGui.blit(matrixStack, offsetX + this.n_1700_B(index), offsetY + this.J_1907_R(index), i, j, this.v_4262_N, this.w_1484_f);
    }

    public void n_1700_B(int offsetX, int offsetY, int index, H_3330_w renderItemIn, Z_1993_T stack) {
        int i = offsetX + this.n_1700_B(index);
        int j = offsetY + this.J_1907_R(index);
        switch (this.ordinal()) {
            case 0: {
                i += 6;
                j += 9;
                break;
            }
            case 1: {
                i += 6;
                j += 6;
                break;
            }
            case 2: {
                i += 10;
                j += 5;
                break;
            }
            case 3: {
                i += 6;
                j += 5;
            }
        }
        renderItemIn.R_4764_Y(stack, i, j);
    }

    public int n_1700_B(int index) {
        switch (this.ordinal()) {
            case 0: {
                return (this.v_4262_N + 4) * index;
            }
            case 1: {
                return (this.v_4262_N + 4) * index;
            }
            case 2: {
                return -this.v_4262_N + 4;
            }
            case 3: {
                return 248;
            }
        }
        throw new UnsupportedOperationException("Don't know what this tab type is!" + String.valueOf((Object)this));
    }

    public int J_1907_R(int index) {
        switch (this.ordinal()) {
            case 0: {
                return -this.w_1484_f + 4;
            }
            case 1: {
                return 136;
            }
            case 2: {
                return this.w_1484_f * index;
            }
            case 3: {
                return this.w_1484_f * index;
            }
        }
        throw new UnsupportedOperationException("Don't know what this tab type is!" + String.valueOf((Object)this));
    }

    public boolean n_1700_B(int offsetX, int offsetY, int index, double mouseX, double mouseY) {
        int i = offsetX + this.n_1700_B(index);
        int j = offsetY + this.J_1907_R(index);
        return mouseX > (double)i && mouseX < (double)(i + this.v_4262_N) && mouseY > (double)j && mouseY < (double)(j + this.w_1484_f);
    }

    private static /* synthetic */ I_2695_V[] J_1907_R() {
        return new I_2695_V[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        s_956_w = I_2695_V.J_1907_R();
    }
}

