/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.F_1573_j;
import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.K_4074_S;
import lightning.product.Attribute;
import lightning.product.FoodProperties;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.NonNullList;
import lightning.product.S_1134_u;
import lightning.product.T_2915_h;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.g_3316_o;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.q_1803_e;
import lightning.product.q_1874_T;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class q_1613_l
implements q_1803_e {
    public static final Map<T_2915_h, q_1613_l> P_1922_E = Maps.newHashMap();
    protected static final UUID u_1723_Y = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    protected static final UUID v_4262_N = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    protected static final Random w_1484_f = new Random();
    protected final S_1134_u t_148_a;
    private final q_1874_T n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final boolean G_564_y;
    private final q_1613_l s_956_w;
    @Nullable
    private String u_2550_I;
    @Nullable
    private final FoodProperties M_588_G;

    public static int n_1700_B(q_1613_l itemIn) {
        return itemIn == null ? 0 : V_3137_a.e_2887_G.n_1700_B(itemIn);
    }

    public static q_1613_l J_1907_R(int id) {
        return V_3137_a.e_2887_G.n_1700_B(id);
    }

    @Deprecated
    public static q_1613_l n_1700_B(T_2915_h blockIn) {
        return P_1922_E.getOrDefault(blockIn, Items.n_1700_B);
    }

    public q_1613_l(n_1700_B properties) {
        this.t_148_a = properties.G_564_y;
        this.n_1700_B = properties.P_1922_E;
        this.s_956_w = properties.R_4764_Y;
        this.R_4764_Y = properties.J_1907_R;
        this.J_1907_R = properties.n_1700_B;
        this.M_588_G = properties.u_1723_Y;
        this.G_564_y = properties.v_4262_N;
    }

    public void n_1700_B(b_4507_u worldIn, r_4811_B livingEntityIn, Z_1993_T stack, int count) {
    }

    public boolean J_1907_R(U_2912_j nbt) {
        return false;
    }

    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        return true;
    }

    @Override
    public q_1613_l u_1723_Y() {
        return this;
    }

    public m_3054_I n_1700_B(UseOnContext context) {
        return m_3054_I.R_4764_Y;
    }

    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        return 1.0f;
    }

    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        if (this.Y_259_p()) {
            Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
            if (playerIn.w_1457_N(this.Q_2552_b().G_564_y())) {
                playerIn.J_1907_R(handIn);
                return InteractionResultHolder.J_1907_R(itemstack);
            }
            return InteractionResultHolder.G_564_y(itemstack);
        }
        return InteractionResultHolder.R_4764_Y(playerIn.R_4764_Y(handIn));
    }

    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        return this.Y_259_p() ? entityLiving.n_1700_B(worldIn, stack) : stack;
    }

    public final int u_2550_I() {
        return this.J_1907_R;
    }

    public final int M_588_G() {
        return this.R_4764_Y;
    }

    public boolean P_4830_p() {
        return this.R_4764_Y > 0;
    }

    public boolean n_1700_B(Z_1993_T stack, r_4811_B target, r_4811_B attacker) {
        return false;
    }

    public boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, K_4074_S state, c_1514_x pos, r_4811_B entityLiving) {
        return false;
    }

    public boolean J_1907_R(K_4074_S blockIn) {
        return false;
    }

    public m_3054_I n_1700_B(Z_1993_T stack, a_3913_L playerIn, r_4811_B target, x_1688_C hand) {
        return m_3054_I.R_4764_Y;
    }

    public x_282_a h_1847_R() {
        return new F_2904_S(this.J_1907_R());
    }

    public String toString() {
        return V_3137_a.e_2887_G.J_1907_R(this).J_1907_R();
    }

    protected String Q_4569_t() {
        if (this.u_2550_I == null) {
            this.u_2550_I = j_3341_s.n_1700_B("item", V_3137_a.e_2887_G.J_1907_R(this));
        }
        return this.u_2550_I;
    }

    public String J_1907_R() {
        return this.Q_4569_t();
    }

    public String u_1723_Y(Z_1993_T stack) {
        return this.J_1907_R();
    }

    public boolean M_182_A() {
        return true;
    }

    @Nullable
    public final q_1613_l t_1786_h() {
        return this.s_956_w;
    }

    public boolean multiplayerClientSuggestionProvider() {
        return this.s_956_w != null;
    }

    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, N_4263_v entityIn, int itemSlot, boolean isSelected) {
    }

    public void J_1907_R(Z_1993_T stack, b_4507_u worldIn, a_3913_L playerIn) {
    }

    public boolean n_1700_B() {
        return false;
    }

    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return stack.J_1907_R().Y_259_p() ? F_1573_j.J_1907_R : F_1573_j.n_1700_B;
    }

    public int J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R().Y_259_p()) {
            return this.Q_2552_b().P_1922_E() ? 16 : 32;
        }
        return 0;
    }

    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving, int timeLeft) {
    }

    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
    }

    public x_282_a w_1484_f(Z_1993_T stack) {
        return new F_2904_S(this.u_1723_Y(stack));
    }

    public boolean P_1922_E(Z_1993_T stack) {
        return stack.k_2293_S();
    }

    public q_1874_T t_148_a(Z_1993_T stack) {
        if (!stack.k_2293_S()) {
            return this.n_1700_B;
        }
        switch (this.n_1700_B) {
            case n_1700_B: 
            case J_1907_R: {
                return q_1874_T.R_4764_Y;
            }
            case R_4764_Y: {
                return q_1874_T.G_564_y;
            }
        }
        return this.n_1700_B;
    }

    public boolean n_1700_B(Z_1993_T stack) {
        return this.u_2550_I() == 1 && this.P_4830_p();
    }

    protected static BlockHitResult n_1700_B(b_4507_u worldIn, a_3913_L player, ClipContext.J_1907_R fluidMode) {
        float f = player.f_4016_n;
        float f1 = player.p_178_J;
        e_2866_D vector3d = player.u_2550_I(1.0f);
        float f2 = u_530_F.J_1907_R(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f3 = u_530_F.n_1700_B(-f1 * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = -u_530_F.J_1907_R(-f * ((float)Math.PI / 180));
        float f5 = u_530_F.n_1700_B(-f * ((float)Math.PI / 180));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 5.0;
        e_2866_D vector3d1 = vector3d.J_1907_R((double)f6 * 5.0, (double)f5 * 5.0, (double)f7 * 5.0);
        return worldIn.n_1700_B(new ClipContext(vector3d, vector3d1, ClipContext.n_1700_B.J_1907_R, fluidMode, player));
    }

    public int G_564_y() {
        return 0;
    }

    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        if (this.n_1700_B(group)) {
            items.add(new Z_1993_T(this));
        }
    }

    protected boolean n_1700_B(S_1134_u group) {
        S_1134_u itemgroup = this.w_1457_N();
        return itemgroup != null && (group == S_1134_u.v_4262_N || group == itemgroup);
    }

    @Nullable
    public final S_1134_u w_1457_N() {
        return this.t_148_a;
    }

    public boolean n_1700_B(Z_1993_T toRepair, Z_1993_T repair) {
        return false;
    }

    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        return ImmutableMultimap.of();
    }

    public boolean s_956_w(Z_1993_T stack) {
        return stack.J_1907_R() == Items.V_2454_J;
    }

    public Z_1993_T Y_601_j() {
        return new Z_1993_T(this);
    }

    public boolean n_1700_B(r_109_r<q_1613_l> tagIn) {
        return tagIn.n_1700_B(this);
    }

    public boolean Y_259_p() {
        return this.M_588_G != null;
    }

    @Nullable
    public FoodProperties Q_2552_b() {
        return this.M_588_G;
    }

    public SoundEvent C_() {
        return SoundEvents.r_4414_L;
    }

    public SoundEvent D_() {
        return SoundEvents.P_2272_O;
    }

    public boolean C_2741_M() {
        return this.G_564_y;
    }

    public boolean n_1700_B(P_11_z damageSource) {
        return !this.G_564_y || !damageSource.M_182_A();
    }

    public static class n_1700_B {
        private int n_1700_B = 64;
        private int J_1907_R;
        private q_1613_l R_4764_Y;
        private S_1134_u G_564_y;
        private q_1874_T P_1922_E = q_1874_T.n_1700_B;
        private FoodProperties u_1723_Y;
        private boolean v_4262_N;

        public n_1700_B n_1700_B(FoodProperties foodIn) {
            this.u_1723_Y = foodIn;
            return this;
        }

        public n_1700_B n_1700_B(int maxStackSizeIn) {
            if (this.J_1907_R > 0) {
                throw new RuntimeException("Unable to have damage AND stack.");
            }
            this.n_1700_B = maxStackSizeIn;
            return this;
        }

        public n_1700_B J_1907_R(int maxDamageIn) {
            return this.J_1907_R == 0 ? this.R_4764_Y(maxDamageIn) : this;
        }

        public n_1700_B R_4764_Y(int maxDamageIn) {
            this.J_1907_R = maxDamageIn;
            this.n_1700_B = 1;
            return this;
        }

        public n_1700_B n_1700_B(q_1613_l containerItemIn) {
            this.R_4764_Y = containerItemIn;
            return this;
        }

        public n_1700_B n_1700_B(S_1134_u groupIn) {
            this.G_564_y = groupIn;
            return this;
        }

        public n_1700_B n_1700_B(q_1874_T rarityIn) {
            this.P_1922_E = rarityIn;
            return this;
        }

        public n_1700_B n_1700_B() {
            this.v_4262_N = true;
            return this;
        }
    }
}


