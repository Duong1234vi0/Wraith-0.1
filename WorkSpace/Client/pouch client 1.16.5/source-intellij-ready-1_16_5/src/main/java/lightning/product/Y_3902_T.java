/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import lightning.product.C_2701_A;
import lightning.product.FormattedText;
import lightning.product.O_3797_X;
import lightning.product.SoundInstance;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.WeighedSoundEvents;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class Y_3902_T
extends C_2701_A
implements O_3797_X {
    private final MinecraftClient n_1700_B;
    private final List<n_1700_B> J_1907_R = Lists.newArrayList();
    private boolean R_4764_Y;

    public Y_3902_T(MinecraftClient clientIn) {
        this.n_1700_B = clientIn;
    }

    public void n_1700_B(g_221_o p_195620_1_) {
        if (!this.R_4764_Y && this.n_1700_B.P_4830_p.H_1990_U) {
            this.n_1700_B.Z_976_R().n_1700_B(this);
            this.R_4764_Y = true;
        } else if (this.R_4764_Y && !this.n_1700_B.P_4830_p.H_1990_U) {
            this.n_1700_B.Z_976_R().J_1907_R(this);
            this.R_4764_Y = false;
        }
        if (this.R_4764_Y && !this.J_1907_R.isEmpty()) {
            c_4037_x.v_4276_D();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            e_2866_D vector3d = new e_2866_D(this.n_1700_B.Y_259_p.O_3598_v(), this.n_1700_B.Y_259_p.X_2048_Y(), this.n_1700_B.Y_259_p.l_2647_k());
            e_2866_D vector3d1 = new e_2866_D(0.0, 0.0, -1.0).n_1700_B(-this.n_1700_B.Y_259_p.f_4016_n * ((float)Math.PI / 180)).J_1907_R(-this.n_1700_B.Y_259_p.p_178_J * ((float)Math.PI / 180));
            e_2866_D vector3d2 = new e_2866_D(0.0, 1.0, 0.0).n_1700_B(-this.n_1700_B.Y_259_p.f_4016_n * ((float)Math.PI / 180)).J_1907_R(-this.n_1700_B.Y_259_p.p_178_J * ((float)Math.PI / 180));
            e_2866_D vector3d3 = vector3d1.R_4764_Y(vector3d2);
            int i = 0;
            int j = 0;
            Iterator<n_1700_B> iterator = this.J_1907_R.iterator();
            while (iterator.hasNext()) {
                n_1700_B subtitleoverlaygui$subtitle = iterator.next();
                if (subtitleoverlaygui$subtitle.J_1907_R() + 3000L <= j_3341_s.J_1907_R()) {
                    iterator.remove();
                    continue;
                }
                j = Math.max(j, this.n_1700_B.t_148_a.n_1700_B((FormattedText)subtitleoverlaygui$subtitle.n_1700_B()));
            }
            j = j + this.n_1700_B.t_148_a.J_1907_R("<") + this.n_1700_B.t_148_a.J_1907_R(" ") + this.n_1700_B.t_148_a.J_1907_R(">") + this.n_1700_B.t_148_a.J_1907_R(" ");
            for (n_1700_B subtitleoverlaygui$subtitle1 : this.J_1907_R) {
                int k = 255;
                x_282_a itextcomponent = subtitleoverlaygui$subtitle1.n_1700_B();
                e_2866_D vector3d4 = subtitleoverlaygui$subtitle1.R_4764_Y().G_564_y(vector3d).G_564_y();
                double d0 = -vector3d3.J_1907_R(vector3d4);
                double d1 = -vector3d1.J_1907_R(vector3d4);
                boolean flag = d1 > 0.5;
                int l = j / 2;
                int i1 = 9;
                int j1 = i1 / 2;
                float f = 1.0f;
                int k1 = this.n_1700_B.t_148_a.n_1700_B((FormattedText)itextcomponent);
                int l1 = u_530_F.R_4764_Y(u_530_F.J_1907_R(255.0, 75.0, (double)((float)(j_3341_s.J_1907_R() - subtitleoverlaygui$subtitle1.J_1907_R()) / 3000.0f)));
                int i2 = l1 << 16 | l1 << 8 | l1;
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y((float)this.n_1700_B.RealmsServerPing().Q_4569_t() - (float)l * 1.0f - 2.0f, (float)(this.n_1700_B.RealmsServerPing().M_182_A() - 30) - (float)(i * (i1 + 1)) * 1.0f, 0.0f);
                c_4037_x.J_1907_R(1.0f, 1.0f, 1.0f);
                Y_3902_T.fill(p_195620_1_, -l - 1, -j1 - 1, l + 1, j1 + 1, this.n_1700_B.P_4830_p.J_1907_R(0.8f));
                c_4037_x.Y_601_j();
                if (!flag) {
                    if (d0 > 0.0) {
                        this.n_1700_B.t_148_a.J_1907_R(p_195620_1_, ">", (float)(l - this.n_1700_B.t_148_a.J_1907_R(">")), (float)(-j1), i2 + -16777216);
                    } else if (d0 < 0.0) {
                        this.n_1700_B.t_148_a.J_1907_R(p_195620_1_, "<", (float)(-l), (float)(-j1), i2 + -16777216);
                    }
                }
                this.n_1700_B.t_148_a.J_1907_R(p_195620_1_, itextcomponent, (float)(-k1 / 2), (float)(-j1), i2 + -16777216);
                c_4037_x.d_2461_k();
                ++i;
            }
            c_4037_x.Y_259_p();
            c_4037_x.d_2461_k();
        }
    }

    @Override
    public void n_1700_B(SoundInstance soundIn, WeighedSoundEvents accessor) {
        if (accessor.G_564_y() != null) {
            x_282_a itextcomponent = accessor.G_564_y();
            if (!this.J_1907_R.isEmpty()) {
                for (n_1700_B subtitleoverlaygui$subtitle : this.J_1907_R) {
                    if (!subtitleoverlaygui$subtitle.n_1700_B().equals(itextcomponent)) continue;
                    subtitleoverlaygui$subtitle.n_1700_B(new e_2866_D(soundIn.h_1847_R(), soundIn.Q_4569_t(), soundIn.M_182_A()));
                    return;
                }
            }
            this.J_1907_R.add(new n_1700_B(this, itextcomponent, new e_2866_D(soundIn.h_1847_R(), soundIn.Q_4569_t(), soundIn.M_182_A())));
        }
    }

    public class n_1700_B {
        private final x_282_a n_1700_B;
        private long J_1907_R;
        private e_2866_D R_4764_Y;

        public n_1700_B(Y_3902_T this$0, x_282_a p_i232263_2_, e_2866_D p_i232263_3_) {
            this.n_1700_B = p_i232263_2_;
            this.R_4764_Y = p_i232263_3_;
            this.J_1907_R = j_3341_s.J_1907_R();
        }

        public x_282_a n_1700_B() {
            return this.n_1700_B;
        }

        public long J_1907_R() {
            return this.J_1907_R;
        }

        public e_2866_D R_4764_Y() {
            return this.R_4764_Y;
        }

        public void n_1700_B(e_2866_D locationIn) {
            this.R_4764_Y = locationIn;
            this.J_1907_R = j_3341_s.J_1907_R();
        }
    }
}



