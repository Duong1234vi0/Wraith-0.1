/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.R_3213_X;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;

public class f_2787_O
extends N_4006_T {
    private final h_2367_h n_1700_B;
    private final R_3213_X J_1907_R;

    public f_2787_O(h_2367_h setting) {
        this.n_1700_B = setting;
        this.J_1907_R = new R_3213_X(setting);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        int baseText = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        float textA = q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha;
        int labelColor = H_2506_c.n_1700_B(baseText, textA);
        l_3370_o.J_1907_R[12].n_1700_B(stack, this.n_1700_B.n_1700_B(), (double)(this.u_1723_Y() + 3.0f), (double)(this.v_4262_N() + 4.5f), labelColor);
        float circleRadius = 3.5f;
        float circleX = this.u_1723_Y() + this.w_1484_f() - 4.0f - circleRadius;
        float circleY = this.v_4262_N() + this.t_148_a() / 2.0f;
        int previewColor = H_2506_c.n_1700_B((int)((Integer)this.n_1700_B.J_1907_R()), alpha);
        F_489_x.n_1700_B(circleX - circleRadius, circleY - circleRadius, circleRadius * 2.0f, circleRadius * 2.0f, circleRadius - 0.5f, previewColor);
        int outlineColor = q_3148_R.n_1700_B(K_1200_E.h_1847_R);
        float outlineAlpha = q_3148_R.J_1907_R(K_1200_E.h_1847_R) / 255.0f * alpha;
        F_489_x.J_1907_R(circleX - circleRadius, circleY - circleRadius, circleRadius * 2.0f, circleRadius * 2.0f, circleRadius, outlineColor, outlineAlpha);
        this.G_564_y(Math.max(10.0f, l_3370_o.J_1907_R[12].h_1847_R()));
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        float circleRadius = 3.5f;
        float circleX = this.u_1723_Y() + this.w_1484_f() - 4.0f - circleRadius;
        float circleY = this.v_4262_N() + this.t_148_a() / 2.0f;
        boolean hoveredCircle = F_747_P.n_1700_B(mouseX, mouseY, circleX - circleRadius, circleY - circleRadius, circleRadius * 2.0f, circleRadius * 2.0f);
        boolean hoveredElement = F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N() - 1.0f, this.w_1484_f(), this.t_148_a() - 7.0f);
        if (button == 0 && (hoveredCircle || hoveredElement)) {
            this.J_1907_R.n_1700_B(!this.J_1907_R.G_564_y());
        } else if (button == 2 && hoveredElement) {
            this.n_1700_B.n_1700_B(this.n_1700_B.G_564_y);
            this.J_1907_R.J_1907_R();
        } else if (this.J_1907_R.G_564_y()) {
            this.J_1907_R.n_1700_B(mouseX, mouseY);
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        if (this.J_1907_R.G_564_y()) {
            this.J_1907_R.n_1700_B();
        }
    }

    @Generated
    public h_2367_h R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public R_3213_X G_564_y() {
        return this.J_1907_R;
    }
}

