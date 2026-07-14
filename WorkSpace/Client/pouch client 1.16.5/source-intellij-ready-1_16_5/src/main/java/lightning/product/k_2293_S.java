/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.ServerboundInteractPacket;
import lightning.product.F_1464_b;
import lightning.product.BlockHitResult;
import lightning.product.H_4075_o;
import lightning.product.I_14_v;
import lightning.product.K_4074_S;
import lightning.product.M_182_A;
import lightning.product.N_4263_v;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.T_2915_h;
import lightning.product.SimpleSoundInstance;
import lightning.product.StructureBlock;
import lightning.product.U_2534_D;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.Z_875_P;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1070_s;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_3005_b;
import lightning.product.SoundType;
import lightning.product.e_2866_D;
import lightning.product.StatsCounter;
import lightning.product.h_2739_B;
import lightning.product.CommandBlock;
import lightning.product.h_516_K;
import lightning.product.i_2572_h;
import lightning.product.Recipe;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.JigsawBlock;
import lightning.product.m_3054_I;
import lightning.product.p_1183_T;
import lightning.product.r_586_S;
import lightning.product.LevelAccessor;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_2293_S {
    public static final Logger n_1700_B = LogManager.getLogger();
    public final MinecraftClient J_1907_R;
    public final M_182_A R_4764_Y;
    public c_1514_x G_564_y = new c_1514_x(-1, -1, -1);
    public Z_1993_T P_1922_E = Z_1993_T.J_1907_R;
    public float u_1723_Y;
    public float v_4262_N;
    public int w_1484_f;
    public boolean t_148_a;
    public I_14_v s_956_w = I_14_v.J_1907_R;
    public I_14_v u_2550_I = I_14_v.n_1700_B;
    public final Object2ObjectLinkedOpenHashMap<Pair<c_1514_x, ServerboundPlayerActionPacket.n_1700_B>, e_2866_D> M_588_G = new Object2ObjectLinkedOpenHashMap();
    public int P_4830_p;
    public Z_875_P h_1847_R;

    public k_2293_S(MinecraftClient mcIn, M_182_A netHandler) {
        this.J_1907_R = mcIn;
        this.R_4764_Y = netHandler;
    }

    public void n_1700_B(a_3913_L player) {
        this.s_956_w.n_1700_B(player.C_415_h);
    }

    public void n_1700_B(I_14_v p_241675_1_) {
        this.u_2550_I = p_241675_1_;
    }

    public void J_1907_R(I_14_v type) {
        if (this.h_1847_R == null) {
            return;
        }
        if (type != this.s_956_w) {
            this.u_2550_I = this.s_956_w;
        }
        this.s_956_w = type;
        this.s_956_w.n_1700_B(this.h_1847_R.C_415_h);
    }

    public boolean n_1700_B() {
        return this.s_956_w.u_1723_Y();
    }

    public boolean n_1700_B(c_1514_x pos) {
        if (this.h_1847_R.n_1700_B(this.R_4764_Y.G_564_y(), pos, this.s_956_w)) {
            return false;
        }
        c_3005_b world = this.R_4764_Y.G_564_y();
        K_4074_S blockstate = world.getBlockState(pos);
        if (!this.h_1847_R.A_2714_y().J_1907_R().n_1700_B(blockstate, (b_4507_u)world, pos, this.h_1847_R)) {
            return false;
        }
        T_2915_h block = blockstate.J_1907_R();
        if ((block instanceof CommandBlock || block instanceof StructureBlock || block instanceof JigsawBlock) && !this.h_1847_R.ModuleManager()) {
            return false;
        }
        if (blockstate.v_4262_N()) {
            return false;
        }
        block.n_1700_B((b_4507_u)world, pos, blockstate, (a_3913_L)this.h_1847_R);
        FluidState fluidstate = world.getFluidState(pos);
        boolean flag = ((b_4507_u)world).n_1700_B(pos, fluidstate.v_4262_N(), 11);
        if (flag) {
            block.n_1700_B((LevelAccessor)world, pos, blockstate);
        }
        return flag;
    }

    public void n_1700_B(Z_875_P botPlayer) {
        this.h_1847_R = botPlayer;
        if (this.h_1847_R != null) {
            this.s_956_w.n_1700_B(this.h_1847_R.C_415_h);
        }
    }

    public boolean n_1700_B(c_1514_x loc, b_257_Y face) {
        if (this.h_1847_R.n_1700_B(this.R_4764_Y.G_564_y(), loc, this.s_956_w)) {
            return false;
        }
        if (!this.R_4764_Y.G_564_y().H_2857_Y().n_1700_B(loc)) {
            return false;
        }
        if (this.s_956_w.P_1922_E()) {
            K_4074_S blockstate1 = this.R_4764_Y.G_564_y().getBlockState(loc);
            this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, loc, face);
            this.n_1700_B(loc);
            this.w_1484_f = 5;
        } else if (!this.t_148_a || !this.J_1907_R(loc)) {
            boolean flag;
            if (this.t_148_a) {
                this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.J_1907_R, this.G_564_y, face);
            }
            K_4074_S blockstate1 = this.R_4764_Y.G_564_y().getBlockState(loc);
            this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, loc, face);
            boolean bl = flag = !blockstate1.v_4262_N();
            if (flag && this.u_1723_Y == 0.0f) {
                blockstate1.n_1700_B((b_4507_u)this.R_4764_Y.G_564_y(), loc, this.h_1847_R);
            }
            if (flag && blockstate1.n_1700_B(this.h_1847_R, this.h_1847_R.O_508_d, loc) >= 1.0f) {
                this.n_1700_B(loc);
            } else {
                this.t_148_a = true;
                this.G_564_y = loc;
                this.P_1922_E = this.h_1847_R.A_2714_y();
                this.u_1723_Y = 0.0f;
                this.v_4262_N = 0.0f;
                this.R_4764_Y.G_564_y().n_1700_B(this.h_1847_R.j_276_v(), this.G_564_y, (int)(this.u_1723_Y * 10.0f) - 1);
            }
        }
        return true;
    }

    public void J_1907_R() {
        if (this.t_148_a) {
            K_4074_S blockstate = this.R_4764_Y.G_564_y().getBlockState(this.G_564_y);
            this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.J_1907_R, this.G_564_y, b_257_Y.n_1700_B);
            this.t_148_a = false;
            this.u_1723_Y = 0.0f;
            this.R_4764_Y.G_564_y().n_1700_B(this.h_1847_R.j_276_v(), this.G_564_y, -1);
            this.h_1847_R.ModuleCategory();
        }
    }

    public boolean J_1907_R(c_1514_x posBlock, b_257_Y directionFacing) {
        this.P_1922_E();
        if (this.w_1484_f > 0) {
            --this.w_1484_f;
            return true;
        }
        if (this.s_956_w.P_1922_E() && this.R_4764_Y.G_564_y().H_2857_Y().n_1700_B(posBlock)) {
            this.w_1484_f = 5;
            K_4074_S blockstate = this.R_4764_Y.G_564_y().getBlockState(posBlock);
            this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, posBlock, directionFacing);
            this.n_1700_B(posBlock);
            return true;
        }
        if (this.J_1907_R(posBlock)) {
            K_4074_S blockstate = this.R_4764_Y.G_564_y().getBlockState(posBlock);
            if (blockstate.v_4262_N()) {
                this.t_148_a = false;
                return false;
            }
            this.u_1723_Y += blockstate.n_1700_B(this.h_1847_R, this.h_1847_R.O_508_d, posBlock);
            if (this.v_4262_N % 4.0f == 0.0f) {
                SoundType soundtype = blockstate.Q_4569_t();
                this.J_1907_R.Z_976_R().n_1700_B(new SimpleSoundInstance(soundtype.u_1723_Y(), D_38_f.P_1922_E, (soundtype.n_1700_B() + 1.0f) / 8.0f, soundtype.J_1907_R() * 0.5f, posBlock));
            }
            this.v_4262_N += 1.0f;
            if (this.u_1723_Y >= 1.0f) {
                this.t_148_a = false;
                this.n_1700_B(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, posBlock, directionFacing);
                this.n_1700_B(posBlock);
                this.u_1723_Y = 0.0f;
                this.v_4262_N = 0.0f;
                this.w_1484_f = 5;
            }
            this.R_4764_Y.G_564_y().n_1700_B(this.h_1847_R.j_276_v(), this.G_564_y, (int)(this.u_1723_Y * 10.0f) - 1);
            return true;
        }
        return this.n_1700_B(posBlock, directionFacing);
    }

    public float R_4764_Y() {
        return this.s_956_w.P_1922_E() ? 5.0f : 4.5f;
    }

    public void G_564_y() {
        this.P_1922_E();
        if (this.R_4764_Y.getBotNetwork().v_4262_N()) {
            this.R_4764_Y.getBotNetwork().J_1907_R();
        } else {
            this.R_4764_Y.getBotNetwork().M_588_G();
        }
    }

    private boolean J_1907_R(c_1514_x pos) {
        boolean flag;
        Z_1993_T itemstack = this.h_1847_R.A_2714_y();
        boolean bl = flag = this.P_1922_E.n_1700_B() && itemstack.n_1700_B();
        if (!this.P_1922_E.n_1700_B() && !itemstack.n_1700_B()) {
            flag = itemstack.J_1907_R() == this.P_1922_E.J_1907_R() && Z_1993_T.n_1700_B(itemstack, this.P_1922_E) && (itemstack.P_1922_E() || itemstack.v_4262_N() == this.P_1922_E.v_4262_N());
        }
        return pos.equals(this.G_564_y) && flag;
    }

    public void P_1922_E() {
        if (this.h_1847_R == null || this.h_1847_R.l_1268_F == null) {
            return;
        }
        int i = this.h_1847_R.l_1268_F.G_564_y;
        if (i != this.P_4830_p) {
            this.P_4830_p = i;
            this.R_4764_Y.n_1700_B(new p_1183_T(this.P_4830_p));
        }
    }

    public m_3054_I n_1700_B(Z_875_P p_217292_1_, c_3005_b p_217292_2_, x_1688_C p_217292_3_, BlockHitResult p_217292_4_) {
        m_3054_I actionresulttype;
        boolean flag1;
        this.P_1922_E();
        c_1514_x blockpos = p_217292_4_.n_1700_B();
        if (!this.R_4764_Y.G_564_y().H_2857_Y().n_1700_B(blockpos)) {
            return m_3054_I.G_564_y;
        }
        Z_1993_T itemstack = p_217292_1_.R_4764_Y(p_217292_3_);
        if (this.s_956_w == I_14_v.P_1922_E) {
            this.R_4764_Y.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
            return m_3054_I.n_1700_B;
        }
        boolean flag = !p_217292_1_.A_2714_y().n_1700_B() || !p_217292_1_.S_4035_N().n_1700_B();
        boolean bl = flag1 = p_217292_1_.z_3000_g() && flag;
        if (!flag1 && (actionresulttype = p_217292_2_.getBlockState(blockpos).n_1700_B((b_4507_u)p_217292_2_, p_217292_1_, p_217292_3_, p_217292_4_)).n_1700_B()) {
            this.R_4764_Y.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
            return actionresulttype;
        }
        this.R_4764_Y.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
        if (!itemstack.n_1700_B() && !p_217292_1_.p_1458_L().n_1700_B(itemstack.J_1907_R())) {
            m_3054_I actionresulttype1;
            UseOnContext itemusecontext = new UseOnContext(p_217292_1_, p_217292_3_, p_217292_4_);
            if (this.s_956_w.P_1922_E()) {
                int i = itemstack.t_4043_B();
                actionresulttype1 = itemstack.n_1700_B(itemusecontext);
                itemstack.P_1922_E(i);
            } else {
                actionresulttype1 = itemstack.n_1700_B(itemusecontext);
            }
            return actionresulttype1;
        }
        return m_3054_I.R_4764_Y;
    }

    public m_3054_I n_1700_B(a_3913_L player, b_4507_u worldIn, x_1688_C hand) {
        if (this.s_956_w == I_14_v.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        this.P_1922_E();
        this.R_4764_Y.n_1700_B(new Z_3504_M(hand));
        Z_1993_T itemstack = player.R_4764_Y(hand);
        if (player.p_1458_L().n_1700_B(itemstack.J_1907_R())) {
            return m_3054_I.R_4764_Y;
        }
        int i = itemstack.t_4043_B();
        InteractionResultHolder<Z_1993_T> actionresult = itemstack.n_1700_B(worldIn, player, hand);
        Z_1993_T itemstack1 = actionresult.J_1907_R();
        if (itemstack1 != itemstack) {
            player.n_1700_B(hand, itemstack1);
        }
        return actionresult.n_1700_B();
    }

    public Z_875_P n_1700_B(c_3005_b worldIn, StatsCounter statsManager, c_1070_s recipes) {
        return this.n_1700_B(worldIn, statsManager, recipes, false, false);
    }

    public Z_875_P n_1700_B(c_3005_b p_239167_1_, StatsCounter p_239167_2_, c_1070_s p_239167_3_, boolean p_239167_4_, boolean p_239167_5_) {
        return new Z_875_P(this.J_1907_R, p_239167_1_, this.R_4764_Y, p_239167_2_, p_239167_3_, p_239167_4_, p_239167_5_);
    }

    public void n_1700_B(a_3913_L playerIn, N_4263_v targetEntity) {
        h_2739_B event = new h_2739_B(targetEntity);
        A_4115_X.n_1700_B(event);
        if (!event.n_1700_B()) {
            this.P_1922_E();
            this.R_4764_Y.n_1700_B(new ServerboundInteractPacket(targetEntity, playerIn.q_2307_F()));
            if (this.s_956_w != I_14_v.P_1922_E) {
                playerIn.H_2857_Y(targetEntity);
                playerIn.ModuleCategory();
            }
        }
    }

    public m_3054_I n_1700_B(a_3913_L player, N_4263_v target, x_1688_C hand) {
        this.P_1922_E();
        this.R_4764_Y.n_1700_B(new ServerboundInteractPacket(target, hand, player.q_2307_F()));
        return this.s_956_w == I_14_v.P_1922_E ? m_3054_I.R_4764_Y : player.n_1700_B(target, hand);
    }

    public m_3054_I n_1700_B(a_3913_L player, N_4263_v target, EntityHitResult ray, x_1688_C hand) {
        this.P_1922_E();
        e_2866_D vector3d = ray.P_1922_E().n_1700_B(target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
        this.R_4764_Y.n_1700_B(new ServerboundInteractPacket(target, hand, vector3d, player.q_2307_F()));
        return this.s_956_w == I_14_v.P_1922_E ? m_3054_I.R_4764_Y : target.n_1700_B(player, vector3d, hand);
    }

    public Z_1993_T n_1700_B(int windowId, int slotId, int mouseButton, a_408_T type, a_3913_L player) {
        if (player.H_1873_g == null) {
            return Z_1993_T.J_1907_R;
        }
        int slotCount = player.H_1873_g.P_1922_E.size();
        if (slotId != -999 && (slotId < 0 || slotId >= slotCount)) {
            return Z_1993_T.J_1907_R;
        }
        short short1 = player.H_1873_g.n_1700_B(player.l_1268_F);
        Z_1993_T itemstack = player.H_1873_g.n_1700_B(slotId, mouseButton, type, player);
        this.R_4764_Y.n_1700_B(new ServerboundContainerClickPacket(windowId, slotId, mouseButton, type, itemstack, short1));
        return itemstack;
    }

    public Z_1993_T n_1700_B(int windowId, int slotId, int mouseButton, a_408_T type, Z_1993_T itemStack, a_3913_L player) {
        if (player.H_1873_g == null) {
            return Z_1993_T.J_1907_R;
        }
        int slotCount = player.H_1873_g.P_1922_E.size();
        if (slotId != -999 && (slotId < 0 || slotId >= slotCount)) {
            return Z_1993_T.J_1907_R;
        }
        short short1 = player.H_1873_g.n_1700_B(player.l_1268_F);
        Z_1993_T itemstack = player.H_1873_g.n_1700_B(slotId, mouseButton, type, player);
        this.R_4764_Y.n_1700_B(new ServerboundContainerClickPacket(windowId, slotId, mouseButton, type, itemStack, short1));
        return itemstack;
    }

    public void n_1700_B(int windowId, int slotId, int mouseButton, a_408_T type, a_3913_L player, int timeWait, boolean reason) {
        this.h_1847_R.u_1723_Y.add(new Z_875_P.n_1700_B(windowId, slotId, mouseButton, type, player, timeWait, reason));
    }

    public void n_1700_B(int p_203413_1_, Recipe<?> p_203413_2_, boolean p_203413_3_) {
        this.R_4764_Y.n_1700_B(new H_4075_o(p_203413_1_, p_203413_2_, p_203413_3_));
    }

    public void n_1700_B(int windowID, int button) {
        this.R_4764_Y.n_1700_B(new h_516_K(windowID, button));
    }

    public void n_1700_B(Z_1993_T itemStackIn, int slotId) {
        if (this.s_956_w.P_1922_E()) {
            this.R_4764_Y.n_1700_B(new r_586_S(slotId, itemStackIn));
        }
    }

    public void n_1700_B(Z_1993_T itemStackIn) {
        if (this.s_956_w.P_1922_E() && !itemStackIn.n_1700_B()) {
            this.R_4764_Y.n_1700_B(new r_586_S(-1, itemStackIn));
        }
    }

    public void J_1907_R(a_3913_L playerIn) {
        this.P_1922_E();
        this.R_4764_Y.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.u_1723_Y, c_1514_x.ZERO, b_257_Y.n_1700_B));
        playerIn.g_134_G();
    }

    public boolean u_1723_Y() {
        return this.s_956_w.u_1723_Y();
    }

    public boolean v_4262_N() {
        return !this.s_956_w.P_1922_E();
    }

    public boolean w_1484_f() {
        return this.s_956_w.P_1922_E();
    }

    public boolean t_148_a() {
        return this.s_956_w.P_1922_E();
    }

    public boolean s_956_w() {
        return this.h_1847_R.y_2772_m() && this.h_1847_R.l_3609_d() instanceof U_2534_D;
    }

    public boolean u_2550_I() {
        return this.s_956_w == I_14_v.P_1922_E;
    }

    public I_14_v M_588_G() {
        return this.u_2550_I;
    }

    public I_14_v P_4830_p() {
        return this.s_956_w;
    }

    public boolean h_1847_R() {
        return this.t_148_a;
    }

    public c_1514_x Q_4569_t() {
        return this.G_564_y;
    }

    public void n_1700_B(int index) {
        this.R_4764_Y.n_1700_B(new i_2572_h(index));
    }

    private void n_1700_B(ServerboundPlayerActionPacket.n_1700_B action, c_1514_x pos, b_257_Y dir) {
        Z_875_P clientplayerentity = this.h_1847_R;
        this.M_588_G.put((Object)Pair.of((Object)pos, (Object)((Object)action)), (Object)clientplayerentity.s_4990_V());
        this.R_4764_Y.n_1700_B(new ServerboundPlayerActionPacket(action, pos, dir));
    }

    public void n_1700_B(c_3005_b worldIn, c_1514_x pos, K_4074_S blockIn, ServerboundPlayerActionPacket.n_1700_B action, boolean successful) {
        e_2866_D vector3d = (e_2866_D)this.M_588_G.remove((Object)Pair.of((Object)pos, (Object)((Object)action)));
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if ((vector3d == null || !successful || action != ServerboundPlayerActionPacket.n_1700_B.n_1700_B && blockstate != blockIn) && blockstate != blockIn) {
            worldIn.n_1700_B(pos, blockIn);
            Z_875_P playerentity = this.h_1847_R;
            if (vector3d != null && worldIn == playerentity.O_508_d && playerentity.n_1700_B(pos, blockIn)) {
                playerentity.G_564_y(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            }
        }
        while (this.M_588_G.size() >= 50) {
            Pair pair = (Pair)this.M_588_G.firstKey();
            this.M_588_G.removeFirst();
            n_1700_B.error("Too many unacked block actions, dropping " + String.valueOf(pair));
        }
    }
}



