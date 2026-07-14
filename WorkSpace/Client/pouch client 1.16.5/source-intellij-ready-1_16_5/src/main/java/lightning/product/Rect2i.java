/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class Rect2i {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;

    public Rect2i(int xIn, int yIn, int widthIn, int heightIn) {
        this.n_1700_B = xIn;
        this.J_1907_R = yIn;
        this.R_4764_Y = widthIn;
        this.G_564_y = heightIn;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public boolean n_1700_B(int x, int y) {
        return x >= this.n_1700_B && x <= this.n_1700_B + this.R_4764_Y && y >= this.J_1907_R && y <= this.J_1907_R + this.G_564_y;
    }
}


