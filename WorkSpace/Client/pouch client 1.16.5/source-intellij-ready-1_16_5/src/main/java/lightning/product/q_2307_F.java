/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.M_182_A;
import lightning.product.N_4263_v;
import lightning.product.Z_875_P;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_3005_b;
import lightning.product.k_2293_S;
import lightning.product.m_3054_I;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;

public class q_2307_F
extends k_2293_S {
    private boolean Q_4569_t = false;
    private c_1514_x M_182_A = null;
    private N_4263_v t_1786_h = null;

    public q_2307_F(MinecraftClient mcIn, M_182_A netHandler) {
        super(mcIn, netHandler);
    }

    @Override
    public boolean n_1700_B(c_1514_x loc, b_257_Y face) {
        this.Q_4569_t = true;
        this.M_182_A = loc;
        boolean flag = super.n_1700_B(loc, face);
        this.Q_4569_t = false;
        return flag;
    }

    @Override
    public boolean J_1907_R(c_1514_x posBlock, b_257_Y directionFacing) {
        this.Q_4569_t = true;
        this.M_182_A = posBlock;
        boolean flag = super.J_1907_R(posBlock, directionFacing);
        this.Q_4569_t = false;
        return flag;
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, b_4507_u worldIn, x_1688_C hand) {
        this.Q_4569_t = true;
        m_3054_I actionresulttype = super.n_1700_B(player, worldIn, hand);
        this.Q_4569_t = false;
        return actionresulttype;
    }

    @Override
    public m_3054_I n_1700_B(Z_875_P player, c_3005_b worldIn, x_1688_C hand, BlockHitResult rayTrace) {
        this.Q_4569_t = true;
        this.M_182_A = rayTrace.n_1700_B();
        m_3054_I actionresulttype = super.n_1700_B(player, worldIn, hand, rayTrace);
        this.Q_4569_t = false;
        return actionresulttype;
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, N_4263_v target, x_1688_C hand) {
        this.t_1786_h = target;
        return super.n_1700_B(player, target, hand);
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, N_4263_v target, EntityHitResult ray, x_1688_C hand) {
        this.t_1786_h = target;
        return super.n_1700_B(player, target, ray, hand);
    }

    public boolean M_182_A() {
        return this.Q_4569_t;
    }

    public c_1514_x t_1786_h() {
        return this.M_182_A;
    }

    public N_4263_v multiplayerClientSuggestionProvider() {
        return this.t_1786_h;
    }
}



