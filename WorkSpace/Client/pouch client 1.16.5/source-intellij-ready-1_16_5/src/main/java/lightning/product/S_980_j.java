/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.RealmsWorldOptions;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.e_1813_Z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.q_1982_R;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;

public class S_980_j
extends Button
implements e_1813_Z {
    public static final g_2336_b n_1700_B = new g_2336_b("realms", "textures/gui/realms/slot_frame.png");
    public static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/empty_frame.png");
    public static final g_2336_b R_4764_Y = new g_2336_b("minecraft", "textures/gui/title/background/panorama_0.png");
    public static final g_2336_b G_564_y = new g_2336_b("minecraft", "textures/gui/title/background/panorama_2.png");
    public static final g_2336_b P_1922_E = new g_2336_b("minecraft", "textures/gui/title/background/panorama_3.png");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.configure.world.slot.tooltip.active");
    private static final x_282_a v_4262_N = new F_2904_S("mco.configure.world.slot.tooltip.minigame");
    private static final x_282_a w_1484_f = new F_2904_S("mco.configure.world.slot.tooltip");
    private final Supplier<q_1982_R> t_148_a;
    private final Consumer<x_282_a> s_956_w;
    private final int u_2550_I;
    private int M_588_G;
    @Nullable
    private J_1907_R P_4830_p;

    public S_980_j(int p_i232195_1_, int p_i232195_2_, int p_i232195_3_, int p_i232195_4_, Supplier<q_1982_R> p_i232195_5_, Consumer<x_282_a> p_i232195_6_, int p_i232195_7_, Button.n_1700_B p_i232195_8_) {
        super(p_i232195_1_, p_i232195_2_, p_i232195_3_, p_i232195_4_, U_2871_b.R_4764_Y, p_i232195_8_);
        this.t_148_a = p_i232195_5_;
        this.u_2550_I = p_i232195_7_;
        this.s_956_w = p_i232195_6_;
    }

    @Nullable
    public J_1907_R n_1700_B() {
        return this.P_4830_p;
    }

    @Override
    public void tick() {
        ++this.M_588_G;
        q_1982_R realmsserver = this.t_148_a.get();
        if (realmsserver != null) {
            boolean flag1;
            String s1;
            long i;
            String s;
            boolean flag;
            boolean flag2;
            RealmsWorldOptions realmsworldoptions = realmsserver.t_148_a.get(this.u_2550_I);
            boolean bl = flag2 = this.u_2550_I == 4;
            if (flag2) {
                flag = realmsserver.P_4830_p == q_1982_R.J_1907_R.J_1907_R;
                s = "Minigame";
                i = realmsserver.M_182_A;
                s1 = realmsserver.t_1786_h;
                flag1 = realmsserver.M_182_A == -1;
            } else {
                flag = realmsserver.h_1847_R == this.u_2550_I && realmsserver.P_4830_p != q_1982_R.J_1907_R.J_1907_R;
                s = realmsworldoptions.n_1700_B(this.u_2550_I);
                i = realmsworldoptions.u_2550_I;
                s1 = realmsworldoptions.M_588_G;
                flag1 = realmsworldoptions.h_1847_R;
            }
            n_1700_B realmsserverslotbutton$action = S_980_j.n_1700_B(realmsserver, flag, flag2);
            Pair<x_282_a, x_282_a> pair = this.n_1700_B(realmsserver, s, flag1, flag2, realmsserverslotbutton$action);
            this.P_4830_p = new J_1907_R(flag, s, i, s1, flag1, flag2, realmsserverslotbutton$action, (x_282_a)pair.getFirst());
            this.setMessage((x_282_a)pair.getSecond());
        }
    }

    private static n_1700_B n_1700_B(q_1982_R p_237720_0_, boolean p_237720_1_, boolean p_237720_2_) {
        if (p_237720_1_) {
            if (!p_237720_0_.s_956_w && p_237720_0_.P_1922_E != q_1982_R.R_4764_Y.R_4764_Y) {
                return lightning.product.S_980_j$n_1700_B.R_4764_Y;
            }
        } else {
            if (!p_237720_2_) {
                return lightning.product.S_980_j$n_1700_B.J_1907_R;
            }
            if (!p_237720_0_.s_956_w) {
                return lightning.product.S_980_j$n_1700_B.J_1907_R;
            }
        }
        return lightning.product.S_980_j$n_1700_B.n_1700_B;
    }

    private Pair<x_282_a, x_282_a> n_1700_B(q_1982_R p_237719_1_, String p_237719_2_, boolean p_237719_3_, boolean p_237719_4_, n_1700_B p_237719_5_) {
        if (p_237719_5_ == lightning.product.S_980_j$n_1700_B.n_1700_B) {
            return Pair.of((Object)null, (Object)new U_2871_b(p_237719_2_));
        }
        x_282_a itextcomponent = p_237719_4_ ? (p_237719_3_ ? U_2871_b.R_4764_Y : new U_2871_b(" ").n_1700_B(p_237719_2_).n_1700_B(" ").n_1700_B(p_237719_1_.Q_4569_t)) : new U_2871_b(" ").n_1700_B(p_237719_2_);
        x_282_a itextcomponent1 = p_237719_5_ == lightning.product.S_980_j$n_1700_B.R_4764_Y ? u_1723_Y : (p_237719_4_ ? v_4262_N : w_1484_f);
        MutableComponent itextcomponent2 = itextcomponent1.P_1922_E().n_1700_B(itextcomponent);
        return Pair.of((Object)itextcomponent1, (Object)itextcomponent2);
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.P_4830_p != null) {
            this.n_1700_B(matrixStack, this.x, this.y, mouseX, mouseY, this.P_4830_p.G_564_y, this.P_4830_p.P_1922_E, this.u_2550_I, this.P_4830_p.u_1723_Y, this.P_4830_p.v_4262_N, this.P_4830_p.n_1700_B, this.P_4830_p.J_1907_R, this.P_4830_p.R_4764_Y, this.P_4830_p.w_1484_f);
        }
    }

    private void n_1700_B(g_221_o p_237718_1_, int p_237718_2_, int p_237718_3_, int p_237718_4_, int p_237718_5_, boolean p_237718_6_, String p_237718_7_, int p_237718_8_, long p_237718_9_, @Nullable String p_237718_11_, boolean p_237718_12_, boolean p_237718_13_, n_1700_B p_237718_14_, @Nullable x_282_a p_237718_15_) {
        boolean flag1;
        boolean flag = this.isHovered();
        if (this.isMouseOver(p_237718_4_, p_237718_5_) && p_237718_15_ != null) {
            this.s_956_w.accept(p_237718_15_);
        }
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        C_3240_x texturemanager = minecraft.G_624_v();
        if (p_237718_13_) {
            y_2772_m.n_1700_B(String.valueOf(p_237718_9_), p_237718_11_);
        } else if (p_237718_12_) {
            texturemanager.n_1700_B(J_1907_R);
        } else if (p_237718_11_ != null && p_237718_9_ != -1L) {
            y_2772_m.n_1700_B(String.valueOf(p_237718_9_), p_237718_11_);
        } else if (p_237718_8_ == 1) {
            texturemanager.n_1700_B(R_4764_Y);
        } else if (p_237718_8_ == 2) {
            texturemanager.n_1700_B(G_564_y);
        } else if (p_237718_8_ == 3) {
            texturemanager.n_1700_B(P_1922_E);
        }
        if (p_237718_6_) {
            float f = 0.85f + 0.15f * u_530_F.J_1907_R((float)this.M_588_G * 0.2f);
            c_4037_x.G_564_y(f, f, f, 1.0f);
        } else {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        }
        S_980_j.blit(p_237718_1_, p_237718_2_ + 3, p_237718_3_ + 3, 0.0f, 0.0f, 74, 74, 74, 74);
        texturemanager.n_1700_B(n_1700_B);
        boolean bl = flag1 = flag && p_237718_14_ != lightning.product.S_980_j$n_1700_B.n_1700_B;
        if (flag1) {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        } else if (p_237718_6_) {
            c_4037_x.G_564_y(0.8f, 0.8f, 0.8f, 1.0f);
        } else {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        }
        S_980_j.blit(p_237718_1_, p_237718_2_, p_237718_3_, 0.0f, 0.0f, 80, 80, 80, 80);
        S_980_j.drawCenteredString(p_237718_1_, minecraft.t_148_a, p_237718_7_, p_237718_2_ + 40, p_237718_3_ + 66, 0xFFFFFF);
    }

    public static class J_1907_R {
        private final boolean G_564_y;
        private final String P_1922_E;
        private final long u_1723_Y;
        private final String v_4262_N;
        public final boolean n_1700_B;
        public final boolean J_1907_R;
        public final n_1700_B R_4764_Y;
        @Nullable
        private final x_282_a w_1484_f;

        J_1907_R(boolean p_i232196_1_, String p_i232196_2_, long p_i232196_3_, @Nullable String p_i232196_5_, boolean p_i232196_6_, boolean p_i232196_7_, n_1700_B p_i232196_8_, @Nullable x_282_a p_i232196_9_) {
            this.G_564_y = p_i232196_1_;
            this.P_1922_E = p_i232196_2_;
            this.u_1723_Y = p_i232196_3_;
            this.v_4262_N = p_i232196_5_;
            this.n_1700_B = p_i232196_6_;
            this.J_1907_R = p_i232196_7_;
            this.R_4764_Y = p_i232196_8_;
            this.w_1484_f = p_i232196_9_;
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.S_980_j$n_1700_B.n_1700_B();
        }
    }
}



