/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.annotation.Nullable;

public class d_4007_L {
    private int[] n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    @Nullable
    public BufferedImage n_1700_B(BufferedImage p_225228_1_) {
        boolean flag;
        if (p_225228_1_ == null) {
            return null;
        }
        this.J_1907_R = 64;
        this.R_4764_Y = 64;
        BufferedImage bufferedimage = new BufferedImage(this.J_1907_R, this.R_4764_Y, 2);
        Graphics graphics = bufferedimage.getGraphics();
        graphics.drawImage(p_225228_1_, 0, 0, null);
        boolean bl = flag = p_225228_1_.getHeight() == 32;
        if (flag) {
            graphics.setColor(new Color(0, 0, 0, 0));
            graphics.fillRect(0, 32, 64, 32);
            graphics.drawImage(bufferedimage, 24, 48, 20, 52, 4, 16, 8, 20, null);
            graphics.drawImage(bufferedimage, 28, 48, 24, 52, 8, 16, 12, 20, null);
            graphics.drawImage(bufferedimage, 20, 52, 16, 64, 8, 20, 12, 32, null);
            graphics.drawImage(bufferedimage, 24, 52, 20, 64, 4, 20, 8, 32, null);
            graphics.drawImage(bufferedimage, 28, 52, 24, 64, 0, 20, 4, 32, null);
            graphics.drawImage(bufferedimage, 32, 52, 28, 64, 12, 20, 16, 32, null);
            graphics.drawImage(bufferedimage, 40, 48, 36, 52, 44, 16, 48, 20, null);
            graphics.drawImage(bufferedimage, 44, 48, 40, 52, 48, 16, 52, 20, null);
            graphics.drawImage(bufferedimage, 36, 52, 32, 64, 48, 20, 52, 32, null);
            graphics.drawImage(bufferedimage, 40, 52, 36, 64, 44, 20, 48, 32, null);
            graphics.drawImage(bufferedimage, 44, 52, 40, 64, 40, 20, 44, 32, null);
            graphics.drawImage(bufferedimage, 48, 52, 44, 64, 52, 20, 56, 32, null);
        }
        graphics.dispose();
        this.n_1700_B = ((DataBufferInt)bufferedimage.getRaster().getDataBuffer()).getData();
        this.J_1907_R(0, 0, 32, 16);
        if (flag) {
            this.n_1700_B(32, 0, 64, 32);
        }
        this.J_1907_R(0, 16, 64, 32);
        this.J_1907_R(16, 48, 48, 64);
        return bufferedimage;
    }

    private void n_1700_B(int p_225227_1_, int p_225227_2_, int p_225227_3_, int p_225227_4_) {
        for (int i = p_225227_1_; i < p_225227_3_; ++i) {
            for (int j = p_225227_2_; j < p_225227_4_; ++j) {
                int k = this.n_1700_B[i + j * this.J_1907_R];
                if ((k >> 24 & 0xFF) >= 128) continue;
                return;
            }
        }
        for (int l = p_225227_1_; l < p_225227_3_; ++l) {
            for (int i1 = p_225227_2_; i1 < p_225227_4_; ++i1) {
                int n = l + i1 * this.J_1907_R;
                this.n_1700_B[n] = this.n_1700_B[n] & 0xFFFFFF;
            }
        }
    }

    private void J_1907_R(int p_225229_1_, int p_225229_2_, int p_225229_3_, int p_225229_4_) {
        for (int i = p_225229_1_; i < p_225229_3_; ++i) {
            for (int j = p_225229_2_; j < p_225229_4_; ++j) {
                int n = i + j * this.J_1907_R;
                this.n_1700_B[n] = this.n_1700_B[n] | 0xFF000000;
            }
        }
    }
}

