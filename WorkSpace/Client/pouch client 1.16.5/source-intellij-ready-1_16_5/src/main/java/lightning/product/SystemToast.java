/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_1624_i;
import lightning.product.F_2904_S;
import lightning.product.Toast;
import lightning.product.U_2871_b;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.x_282_a;

public class SystemToast
implements Toast {
    private final n_1700_B R_4764_Y;
    private x_282_a G_564_y;
    private List<FormattedCharSequence> P_1922_E;
    private long u_1723_Y;
    private boolean v_4262_N;
    private final int w_1484_f;

    public SystemToast(n_1700_B typeIn, x_282_a titleComponent, @Nullable x_282_a subtitleComponent) {
        this(typeIn, titleComponent, (List<FormattedCharSequence>)SystemToast.n_1700_B(subtitleComponent), 160);
    }

    public static SystemToast n_1700_B(MinecraftClient p_238534_0_, n_1700_B p_238534_1_, x_282_a p_238534_2_, x_282_a p_238534_3_) {
        Y_4083_F fontrenderer = p_238534_0_.t_148_a;
        List<FormattedCharSequence> list = fontrenderer.J_1907_R(p_238534_3_, 200);
        int i = Math.max(200, list.stream().mapToInt(fontrenderer::n_1700_B).max().orElse(200));
        return new SystemToast(p_238534_1_, p_238534_2_, list, i + 30);
    }

    private SystemToast(n_1700_B p_i232264_1_, x_282_a p_i232264_2_, List<FormattedCharSequence> p_i232264_3_, int p_i232264_4_) {
        this.R_4764_Y = p_i232264_1_;
        this.G_564_y = p_i232264_2_;
        this.P_1922_E = p_i232264_3_;
        this.w_1484_f = p_i232264_4_;
    }

    private static ImmutableList<FormattedCharSequence> n_1700_B(@Nullable x_282_a p_238537_0_) {
        return p_238537_0_ == null ? ImmutableList.of() : ImmutableList.of((Object)p_238537_0_.u_1723_Y());
    }

    @Override
    public int J_1907_R() {
        return this.w_1484_f;
    }

    @Override
    public Toast.n_1700_B func_230444_a_(g_221_o p_230444_1_, D_1624_i p_230444_2_, long p_230444_3_) {
        if (this.v_4262_N) {
            this.u_1723_Y = p_230444_3_;
            this.v_4262_N = false;
        }
        p_230444_2_.J_1907_R().G_624_v().n_1700_B(n_1700_B);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f);
        int i = this.J_1907_R();
        int j = 12;
        if (i == 160 && this.P_1922_E.size() <= 1) {
            p_230444_2_.blit(p_230444_1_, 0, 0, 0, 64, i, this.R_4764_Y());
        } else {
            int k = this.R_4764_Y() + Math.max(0, this.P_1922_E.size() - 1) * 12;
            int l = 28;
            int i1 = Math.min(4, k - 28);
            this.n_1700_B(p_230444_1_, p_230444_2_, i, 0, 0, 28);
            for (int j1 = 28; j1 < k - i1; j1 += 10) {
                this.n_1700_B(p_230444_1_, p_230444_2_, i, 16, j1, Math.min(16, k - j1 - i1));
            }
            this.n_1700_B(p_230444_1_, p_230444_2_, i, 32 - i1, k - i1, i1);
        }
        if (this.P_1922_E == null) {
            p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.G_564_y, 18.0f, 12.0f, -256);
        } else {
            p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.G_564_y, 18.0f, 7.0f, -256);
            for (int k1 = 0; k1 < this.P_1922_E.size(); ++k1) {
                p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.P_1922_E.get(k1), 18.0f, (float)(18 + k1 * 12), -1);
            }
        }
        return p_230444_3_ - this.u_1723_Y < 5000L ? Toast.n_1700_B.n_1700_B : Toast.n_1700_B.J_1907_R;
    }

    private void n_1700_B(g_221_o p_238533_1_, D_1624_i p_238533_2_, int p_238533_3_, int p_238533_4_, int p_238533_5_, int p_238533_6_) {
        int i = p_238533_4_ == 0 ? 20 : 5;
        int j = Math.min(60, p_238533_3_ - i);
        p_238533_2_.blit(p_238533_1_, 0, p_238533_5_, 0, 64 + p_238533_4_, i, p_238533_6_);
        for (int k = i; k < p_238533_3_ - j; k += 64) {
            p_238533_2_.blit(p_238533_1_, k, p_238533_5_, 32, 64 + p_238533_4_, Math.min(64, p_238533_3_ - k - j), p_238533_6_);
        }
        p_238533_2_.blit(p_238533_1_, p_238533_3_ - j, p_238533_5_, 160 - j, 64 + p_238533_4_, j, p_238533_6_);
    }

    public void n_1700_B(x_282_a titleComponent, @Nullable x_282_a subtitleComponent) {
        this.G_564_y = titleComponent;
        this.P_1922_E = SystemToast.n_1700_B(subtitleComponent);
        this.v_4262_N = true;
    }

    public n_1700_B G_564_y() {
        return this.R_4764_Y;
    }

    public static void n_1700_B(D_1624_i p_238536_0_, n_1700_B p_238536_1_, x_282_a p_238536_2_, @Nullable x_282_a p_238536_3_) {
        p_238536_0_.n_1700_B(new SystemToast(p_238536_1_, p_238536_2_, p_238536_3_));
    }

    public static void J_1907_R(D_1624_i p_193657_0_, n_1700_B p_193657_1_, x_282_a p_193657_2_, @Nullable x_282_a p_193657_3_) {
        SystemToast systemtoast = p_193657_0_.n_1700_B(SystemToast.class, (Object)p_193657_1_);
        if (systemtoast == null) {
            SystemToast.n_1700_B(p_193657_0_, p_193657_1_, p_193657_2_, p_193657_3_);
        } else {
            systemtoast.n_1700_B(p_193657_2_, p_193657_3_);
        }
    }

    public static void n_1700_B(MinecraftClient p_238535_0_, String p_238535_1_) {
        SystemToast.n_1700_B(p_238535_0_.e_1992_r(), n_1700_B.u_1723_Y, (x_282_a)new F_2904_S("selectWorld.access_failure"), (x_282_a)new U_2871_b(p_238535_1_));
    }

    public static void J_1907_R(MinecraftClient p_238538_0_, String p_238538_1_) {
        SystemToast.n_1700_B(p_238538_0_.e_1992_r(), n_1700_B.u_1723_Y, (x_282_a)new F_2904_S("selectWorld.delete_failure"), (x_282_a)new U_2871_b(p_238538_1_));
    }

    public static void R_4764_Y(MinecraftClient p_238539_0_, String p_238539_1_) {
        SystemToast.n_1700_B(p_238539_0_.e_1992_r(), n_1700_B.v_4262_N, (x_282_a)new F_2904_S("pack.copyFailure"), (x_282_a)new U_2871_b(p_238539_1_));
    }

    @Override
    public /* synthetic */ Object n_1700_B() {
        return this.G_564_y();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.SystemToast$n_1700_B.n_1700_B();
        }
    }
}



