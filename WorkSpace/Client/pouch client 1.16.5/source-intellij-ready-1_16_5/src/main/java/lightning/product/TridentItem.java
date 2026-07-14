/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 */
package lightning.product;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import lightning.product.D_38_f;
import lightning.product.E_4925_L;
import lightning.product.F_1573_j;
import lightning.product.Attributes;
import lightning.product.J_4485_t;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_461_d;
import lightning.product.Attribute;
import lightning.product.Stats;
import lightning.product.U_1880_G;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class TridentItem
extends q_1613_l
implements J_4485_t {
    private final Multimap<Attribute, U_1880_G> n_1700_B;

    public TridentItem(q_1613_l.n_1700_B builderIn) {
        super(builderIn);
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)Attributes.u_1723_Y, (Object)new U_1880_G(u_1723_Y, "Tool modifier", 8.0, U_1880_G.n_1700_B.n_1700_B));
        builder.put((Object)Attributes.w_1484_f, (Object)new U_1880_G(v_4262_N, "Tool modifier", (double)-2.9f, U_1880_G.n_1700_B.n_1700_B));
        this.n_1700_B = builder.build();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        return !player.G_624_v();
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.u_1723_Y;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 72000;
    }

    @Override
    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving, int timeLeft) {
        if (entityLiving instanceof a_3913_L) {
            int j;
            a_3913_L playerentity = (a_3913_L)entityLiving;
            int i = this.J_1907_R(stack) - timeLeft;
            if (i >= 10 && ((j = K_4096_w.v_4262_N(stack)) <= 0 || playerentity.LongRunningTask())) {
                if (!worldIn.Y_259_p) {
                    stack.n_1700_B(1, playerentity, (T player) -> player.G_564_y(entityLiving.Q_2552_b()));
                    if (j == 0) {
                        E_4925_L tridententity = new E_4925_L(worldIn, (r_4811_B)playerentity, stack);
                        tridententity.n_1700_B(playerentity, playerentity.f_4016_n, playerentity.p_178_J, 0.0f, 2.5f + (float)j * 0.5f, 1.0f);
                        if (playerentity.C_415_h.G_564_y) {
                            tridententity.R_4764_Y = h_384_L.n_1700_B.R_4764_Y;
                        }
                        worldIn.a_(tridententity);
                        worldIn.n_1700_B((a_3913_L)null, tridententity, SoundEvents.EndPortalFrameBlock, D_38_f.w_1484_f, 1.0f, 1.0f);
                        if (!playerentity.C_415_h.G_564_y) {
                            playerentity.l_1268_F.u_1723_Y(stack);
                        }
                    }
                }
                playerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
                if (j > 0) {
                    float f7 = playerentity.p_178_J;
                    float f = playerentity.f_4016_n;
                    float f1 = -u_530_F.n_1700_B(f7 * ((float)Math.PI / 180)) * u_530_F.J_1907_R(f * ((float)Math.PI / 180));
                    float f2 = -u_530_F.n_1700_B(f * ((float)Math.PI / 180));
                    float f3 = u_530_F.J_1907_R(f7 * ((float)Math.PI / 180)) * u_530_F.J_1907_R(f * ((float)Math.PI / 180));
                    float f4 = u_530_F.R_4764_Y(f1 * f1 + f2 * f2 + f3 * f3);
                    float f5 = 3.0f * ((1.0f + (float)j) / 4.0f);
                    playerentity.w_1484_f(f1 *= f5 / f4, f2 *= f5 / f4, f3 *= f5 / f4);
                    playerentity.Q_4569_t(20);
                    if (playerentity.M_1641_O()) {
                        float f6 = 1.1999999f;
                        playerentity.n_1700_B(L_461_d.n_1700_B, new e_2866_D(0.0, 1.1999999284744263, 0.0));
                    }
                    SoundEvent soundevent = j >= 3 ? SoundEvents.EndPortalBlock : (j == 2 ? SoundEvents.EndGatewayBlock : SoundEvents.C_3560_B);
                    worldIn.n_1700_B((a_3913_L)null, playerentity, soundevent, D_38_f.w_1484_f, 1.0f, 1.0f);
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (itemstack.v_4262_N() >= itemstack.w_1484_f() - 1) {
            return InteractionResultHolder.G_564_y(itemstack);
        }
        if (K_4096_w.v_4262_N(itemstack) > 0 && !playerIn.LongRunningTask()) {
            return InteractionResultHolder.G_564_y(itemstack);
        }
        playerIn.J_1907_R(handIn);
        return InteractionResultHolder.J_1907_R(itemstack);
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, r_4811_B target, r_4811_B attacker) {
        stack.n_1700_B(1, attacker, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        return true;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, b_4507_u worldIn, K_4074_S state, c_1514_x pos, r_4811_B entityLiving) {
        if ((double)state.w_1484_f(worldIn, pos) != 0.0) {
            stack.n_1700_B(2, entityLiving, (T entity) -> entity.R_4764_Y(e_1174_E.n_1700_B));
        }
        return true;
    }

    @Override
    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        return equipmentSlot == e_1174_E.n_1700_B ? this.n_1700_B : super.n_1700_B(equipmentSlot);
    }

    @Override
    public int G_564_y() {
        return 1;
    }
}


