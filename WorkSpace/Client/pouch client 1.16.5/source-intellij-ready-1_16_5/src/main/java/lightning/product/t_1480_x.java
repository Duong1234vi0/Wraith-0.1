/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.D_590_W;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;
import lightning.product.ControlsScreen;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;

public class t_1480_x
extends ContainerObjectSelectionList<J_1907_R> {
    private final ControlsScreen n_1700_B;
    private int J_1907_R;

    public t_1480_x(ControlsScreen controls, MinecraftClient mcIn) {
        super(mcIn, controls.width + 45, controls.height, 43, controls.height - 32, 20);
        this.n_1700_B = controls;
        D_590_W[] akeybinding = (D_590_W[])ArrayUtils.clone((Object[])mcIn.P_4830_p.RealmsDefaultUncaughtExceptionHandler);
        Arrays.sort(akeybinding);
        String s = null;
        for (D_590_W keybinding : akeybinding) {
            F_2904_S itextcomponent;
            int i;
            String s1 = keybinding.P_1922_E();
            if (!s1.equals(s)) {
                s = s1;
                this.addEntry(new n_1700_B(new F_2904_S(s1)));
            }
            if ((i = mcIn.t_148_a.n_1700_B((FormattedText)(itextcomponent = new F_2904_S(keybinding.v_4262_N())))) > this.J_1907_R) {
                this.J_1907_R = i;
            }
            this.addEntry(new R_4764_Y(keybinding, itextcomponent));
        }
    }

    @Override
    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 15;
    }

    @Override
    public int getRowWidth() {
        return super.getRowWidth() + 32;
    }

    public class n_1700_B
    extends J_1907_R {
        private final x_282_a J_1907_R;
        private final int R_4764_Y;

        public n_1700_B(x_282_a p_i232280_2_) {
            this.J_1907_R = p_i232280_2_;
            this.R_4764_Y = t_1480_x.this.minecraft.t_148_a.n_1700_B((FormattedText)this.J_1907_R);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            t_1480_x.this.minecraft.t_148_a.J_1907_R(p_230432_1_, this.J_1907_R, (float)(t_1480_x.this.minecraft.Y_1740_V.width / 2 - this.R_4764_Y / 2), (float)(p_230432_3_ + p_230432_6_ - 9 - 1), 0xFFFFFF);
        }

        @Override
        public boolean changeFocus(boolean focus) {
            return false;
        }

        @Override
        public List<? extends GuiEventListener> getEventListeners() {
            return Collections.emptyList();
        }
    }

    public class R_4764_Y
    extends J_1907_R {
        private final D_590_W J_1907_R;
        private final x_282_a R_4764_Y;
        private final Button G_564_y;
        private final Button P_1922_E;

        private R_4764_Y(final D_590_W p_i232281_2_, final x_282_a p_i232281_3_) {
            this.J_1907_R = p_i232281_2_;
            this.R_4764_Y = p_i232281_3_;
            this.G_564_y = new Button(this, 0, 0, 75, 20, p_i232281_3_, p_214386_2_ -> {
                t_1480_x.this.n_1700_B.n_1700_B = p_i232281_2_;
            }){

                @Override
                protected MutableComponent getNarrationMessage() {
                    return p_i232281_2_.t_148_a() ? new F_2904_S("narrator.controls.unbound", p_i232281_3_) : new F_2904_S("narrator.controls.bound", p_i232281_3_, super.getNarrationMessage());
                }
            };
            this.P_1922_E = new Button(this, 0, 0, 50, 20, new F_2904_S("controls.reset"), p_214387_2_ -> {
                t_1480_x.this.minecraft.P_4830_p.n_1700_B(p_i232281_2_, p_i232281_2_.w_1484_f());
                D_590_W.R_4764_Y();
            }){

                @Override
                protected MutableComponent getNarrationMessage() {
                    return new F_2904_S("narrator.controls.reset", p_i232281_3_);
                }
            };
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            boolean flag = t_1480_x.this.n_1700_B.n_1700_B == this.J_1907_R;
            t_1480_x.this.minecraft.t_148_a.J_1907_R(p_230432_1_, this.R_4764_Y, (float)(p_230432_4_ + 90 - t_1480_x.this.J_1907_R), (float)(p_230432_3_ + p_230432_6_ / 2 - 4), 0xFFFFFF);
            this.P_1922_E.x = p_230432_4_ + 190;
            this.P_1922_E.y = p_230432_3_;
            this.P_1922_E.active = !this.J_1907_R.M_588_G();
            this.P_1922_E.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
            this.G_564_y.x = p_230432_4_ + 105;
            this.G_564_y.y = p_230432_3_;
            this.G_564_y.setMessage(this.J_1907_R.u_2550_I());
            boolean flag1 = false;
            if (!this.J_1907_R.t_148_a()) {
                for (D_590_W keybinding : t_1480_x.this.minecraft.P_4830_p.RealmsDefaultUncaughtExceptionHandler) {
                    if (keybinding == this.J_1907_R || !this.J_1907_R.J_1907_R(keybinding)) continue;
                    flag1 = true;
                    break;
                }
            }
            if (flag) {
                this.G_564_y.setMessage(new U_2871_b("> ").n_1700_B(this.G_564_y.getMessage().P_1922_E().n_1700_B(D_4024_W.Q_4569_t)).n_1700_B(" <").n_1700_B(D_4024_W.Q_4569_t));
            } else if (flag1) {
                this.G_564_y.setMessage(this.G_564_y.getMessage().P_1922_E().n_1700_B(D_4024_W.P_4830_p));
            }
            this.G_564_y.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
        }

        @Override
        public List<? extends GuiEventListener> getEventListeners() {
            return ImmutableList.of((Object)this.G_564_y, (Object)this.P_1922_E);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (this.G_564_y.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
            return this.P_1922_E.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseReleased(double mouseX, double mouseY, int button) {
            return this.G_564_y.mouseReleased(mouseX, mouseY, button) || this.P_1922_E.mouseReleased(mouseX, mouseY, button);
        }
    }

    public static abstract class J_1907_R
    extends ContainerObjectSelectionList.n_1700_B<J_1907_R> {
    }
}



