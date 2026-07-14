/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import lightning.product.A_2714_y;
import lightning.product.A_4115_X;
import lightning.product.AxeItem;
import lightning.product.B_3217_H;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.E_453_w;
import lightning.product.F_1573_j;
import lightning.product.F_3620_e;
import lightning.product.ItemTransforms;
import lightning.product.G_3165_y;
import lightning.product.H_3330_w;
import lightning.product.TridentItem;
import lightning.product.J_1907_R;
import lightning.product.L_3273_c;
import lightning.product.M_1336_P;
import lightning.product.O_4882_g;
import lightning.product.T_2915_h;
import lightning.product.X_4340_E;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.Z_875_P;
import lightning.product.DiggerItem;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_4311_S;
import lightning.product.i_789_Q;
import lightning.product.k_4231_L;
import lightning.product.n_1700_B;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_1595_x;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.w_2040_b;
import lightning.product.x_1688_C;
import net.optifine.Config;
import net.optifine.CustomItems;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;

public class l_456_f {
    private static final o_2576_A n_1700_B = o_2576_A.M_182_A(new g_2336_b("textures/map/map_background.png"));
    private static final o_2576_A J_1907_R = o_2576_A.M_182_A(new g_2336_b("textures/map/map_background_checkerboard.png"));
    private final MinecraftClient R_4764_Y;
    private Z_1993_T G_564_y = Z_1993_T.J_1907_R;
    private Z_1993_T P_1922_E = Z_1993_T.J_1907_R;
    private float u_1723_Y;
    private float v_4262_N;
    private float w_1484_f;
    private float t_148_a;
    private final w_2040_b s_956_w;
    private final H_3330_w u_2550_I;

    public l_456_f(MinecraftClient mcIn) {
        this.R_4764_Y = mcIn;
        this.s_956_w = mcIn.O_508_d();
        this.u_2550_I = mcIn.r_715_M();
    }

    private t_1595_x J_1907_R() {
        if (ClientBootstrap.Y_601_j() == null || ClientBootstrap.Y_601_j().J_1907_R() == null) {
            return null;
        }
        t_1595_x swordAnimations = ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y;
        if (swordAnimations == null || !swordAnimations.w_1484_f() || !swordAnimations.v_4262_N.J_1907_R("New")) {
            return null;
        }
        return swordAnimations;
    }

    private float n_1700_B(float value) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;
        if (value < 0.5f) {
            return (float)(Math.pow(2.0f * value, 2.0) * (double)((c2 + 1.0f) * 2.0f * value - c2) / 2.0);
        }
        return (float)((Math.pow(2.0f * value - 2.0f, 2.0) * (double)((c2 + 1.0f) * (value * 2.0f - 2.0f) + c2) + 2.0) / 2.0);
    }

    private float J_1907_R(float swingProgress) {
        return swingProgress < 0.6f ? u_530_F.n_1700_B(u_530_F.n_1700_B(swingProgress, 0.0f, 0.12506f) * 12.56f) : u_530_F.n_1700_B(u_530_F.n_1700_B(swingProgress, 0.62532f, 0.75038f) * 12.56f);
    }

    private float R_4764_Y(float swingProgress) {
        return this.n_1700_B(u_530_F.n_1700_B(swingProgress * (float)Math.PI));
    }

    private void n_1700_B(g_221_o matrixStackIn, float equippedProgress, k_4231_L side) {
        int direction = side == k_4231_L.J_1907_R ? 1 : -1;
        matrixStackIn.n_1700_B((double)direction, (double)(-equippedProgress) * 0.3, 0.3);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(45.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-40.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(30.0f));
        matrixStackIn.n_1700_B(0.9f, 0.9f, 0.9f);
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, k_4231_L side, float equippedProgress, float swingProgress) {
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, swingProgress, side);
    }

    private boolean n_1700_B(X_4340_E player) {
        t_1595_x swordAnimations = this.J_1907_R();
        return swordAnimations != null && swordAnimations.h_1847_R.t_148_a() != false && !player.F_3572_x();
    }

    private boolean n_1700_B(X_4340_E player, k_4231_L handside, float swingProgress, float equippedProgress, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        boolean movementPose;
        if (player.F_3572_x()) {
            return false;
        }
        int direction = handside == k_4231_L.J_1907_R ? 1 : -1;
        boolean bl = movementPose = player.C_1269_X() || player.t_1446_I() || !player.M_1641_O() && player.e_();
        if (movementPose) {
            this.n_1700_B(matrixStackIn, equippedProgress, handside);
        } else {
            float swingRot = this.J_1907_R(swingProgress);
            float swing = this.R_4764_Y(swingProgress);
            matrixStackIn.n_1700_B(0.0, 0.2 * (double)swingRot, 0.15 * (double)swingRot);
            matrixStackIn.n_1700_B(0.1 * (double)direction * (double)swing, 0.15 * (double)swing, -0.45 * (double)swing);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(35.0f * swing * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-30.0f * swing));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-10.0f * swingRot * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(10.0f * swingRot));
        }
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, equippedProgress, swingProgress);
        return true;
    }

    private boolean n_1700_B(X_4340_E player, float partialTicks, float swingProgress, Z_1993_T stack, float equippedProgress, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        boolean usingThisHand;
        if (!this.n_1700_B(player)) {
            return false;
        }
        k_4231_L handside = player.d_2169_p().n_1700_B();
        boolean bl = usingThisHand = player.Y_601_j() && player.U_144_f() > 0 && player.Q_2552_b() == x_1688_C.J_1907_R;
        if (stack.n_1700_B()) {
            return this.n_1700_B(player, handside, swingProgress, equippedProgress, matrixStackIn, bufferIn, combinedLightIn);
        }
        if (stack.J_1907_R() instanceof G_3165_y || stack.J_1907_R() instanceof Z_1630_j) {
            return false;
        }
        if (usingThisHand) {
            float useTicks = (float)stack.u_2550_I() - ((float)player.U_144_f() - partialTicks + 1.0f);
            switch (stack.M_588_G()) {
                case J_1907_R: 
                case R_4764_Y: {
                    this.n_1700_B(matrixStackIn, handside, useTicks);
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, swingProgress);
                    break;
                }
                case G_564_y: {
                    this.J_1907_R(matrixStackIn, handside, useTicks);
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, swingProgress);
                    break;
                }
                default: {
                    this.n_1700_B(matrixStackIn, equippedProgress, handside);
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, 0.0f);
                    break;
                }
            }
        } else {
            this.n_1700_B(matrixStackIn, equippedProgress, handside);
            this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, 0.0f);
        }
        this.n_1700_B(matrixStackIn, handside);
        this.n_1700_B(matrixStackIn, handside, stack);
        this.n_1700_B((r_4811_B)player, stack, handside == k_4231_L.J_1907_R ? ItemTransforms.J_1907_R.P_1922_E : ItemTransforms.J_1907_R.G_564_y, handside == k_4231_L.n_1700_B, matrixStackIn, bufferIn, combinedLightIn);
        return true;
    }

    private boolean n_1700_B(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        F_1573_j useAction = stack.M_588_G();
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof DiggerItem || item instanceof L_3273_c || item instanceof TridentItem || item instanceof O_4882_g || useAction == F_1573_j.P_1922_E || useAction == F_1573_j.G_564_y || useAction == F_1573_j.u_1723_Y || item == Items.LightPredicate || item == Items.EndRodBlock || item == Items.j_3599_p;
    }

    private boolean J_1907_R(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        return item == Items.x_92_N || item == Items.r_260_T || item == Items.M_766_z;
    }

    private boolean R_4764_Y(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        T_2915_h block = T_2915_h.n_1700_B(item);
        return item == Items.Animation || item == Items.v_570_f || item == Items.l_3609_d || item == Items.k_578_l || block.n_1700_B(BlockTags.n_3318_d) || block.n_1700_B(BlockTags.h_4320_q) || block.n_1700_B(BlockTags.M_182_A) || block.n_1700_B(BlockTags.u_1723_Y) || block.n_1700_B(BlockTags.M_588_G);
    }

    private boolean G_564_y(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        return item.Y_259_p() || item instanceof B_3217_H || !this.n_1700_B(stack) && !(item instanceof v_1669_V);
    }

    private void n_1700_B(g_221_o matrixStackIn, k_4231_L side) {
        int direction = side == k_4231_L.J_1907_R ? 1 : -1;
        matrixStackIn.n_1700_B(-0.3 * (double)direction, 0.65, -0.1);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-65.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(10.0f));
    }

    private void n_1700_B(g_221_o matrixStackIn, k_4231_L side, float useTicks) {
        int direction = side == k_4231_L.J_1907_R ? 1 : -1;
        float progress = u_530_F.n_1700_B(useTicks / 5.0f, 0.0f, 1.0f);
        float wobble = u_530_F.n_1700_B(useTicks / 2.0f * (float)Math.PI) / 10.0f;
        matrixStackIn.n_1700_B((double)direction, 0.1, 0.3);
        matrixStackIn.n_1700_B(0.2 * (double)direction * (double)progress, -0.7 * (double)progress, -0.2 * (double)progress);
        matrixStackIn.n_1700_B(0.0, -0.2 * (double)wobble, -0.2 * (double)wobble);
        matrixStackIn.n_1700_B(0.0, 0.1 * (double)this.n_1700_B(u_530_F.n_1700_B(progress * (float)Math.PI)), 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(45.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-40.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(30.0f));
        matrixStackIn.n_1700_B(0.9f, 0.9f, 0.9f);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(45.0f * progress * (float)direction));
    }

    private void J_1907_R(g_221_o matrixStackIn, k_4231_L side, float useTicks) {
        int direction = side == k_4231_L.J_1907_R ? 1 : -1;
        float blockProgress = u_530_F.n_1700_B(useTicks / 4.0f, 0.0f, 1.0f);
        float settleProgress = u_530_F.n_1700_B(useTicks / 6.0f, 0.0f, 1.0f);
        matrixStackIn.n_1700_B(0.0, -0.2, 0.0);
        matrixStackIn.n_1700_B((double)direction, 0.0, 0.3);
        matrixStackIn.n_1700_B(0.7 * (double)blockProgress * (double)direction, 0.0, -1.3 * (double)blockProgress);
        matrixStackIn.n_1700_B(-0.2 * (double)direction * (double)settleProgress, 0.0, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(10.0f * u_530_F.n_1700_B(settleProgress * (float)Math.PI)));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(70.0f * blockProgress * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(45.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-40.0f * (float)direction));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(30.0f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(5.0f * (float)direction * blockProgress));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-10.0f * blockProgress));
        matrixStackIn.n_1700_B(0.0, 0.0, -0.2 * (double)blockProgress);
        matrixStackIn.n_1700_B(0.9f, 0.9f, 0.9f);
    }

    private void n_1700_B(g_221_o matrixStackIn, k_4231_L side, Z_1993_T stack) {
        int direction;
        int n = direction = side == k_4231_L.J_1907_R ? 1 : -1;
        if (this.J_1907_R(stack)) {
            matrixStackIn.n_1700_B(1.5f, 1.5f, 1.5f);
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(25.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(5.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(75.0f * (float)direction));
            matrixStackIn.n_1700_B(0.2 * (double)direction, 0.2, 0.05);
            return;
        }
        if (this.G_564_y(stack)) {
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(5.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(15.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(75.0f * (float)direction));
            matrixStackIn.n_1700_B(0.0, -0.05, -0.1);
            matrixStackIn.n_1700_B(0.7f, 0.7f, 0.7f);
            return;
        }
        if (this.R_4764_Y(stack)) {
            matrixStackIn.n_1700_B(0.0, 0.0, -0.1);
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(5.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(15.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(75.0f * (float)direction));
            return;
        }
        if (stack.J_1907_R() instanceof v_1669_V) {
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(25.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(5.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(75.0f * (float)direction));
            matrixStackIn.n_1700_B(0.2 * (double)direction, 0.2, 0.05);
            return;
        }
        if (stack.M_588_G() == F_1573_j.G_564_y) {
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(160.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-60.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-70.0f));
            matrixStackIn.n_1700_B(0.75f, 0.75f, 0.75f);
            matrixStackIn.n_1700_B(0.15 * (double)direction, side == k_4231_L.J_1907_R ? 0.35 : 0.45, side == k_4231_L.J_1907_R ? -0.15 : -0.1);
            matrixStackIn.n_1700_B(0.17 * (double)direction, 0.0, 0.3);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-90.0f * (float)direction));
            return;
        }
        if (stack.M_588_G() == F_1573_j.u_1723_Y) {
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(75.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(45.0f * (float)direction));
            matrixStackIn.n_1700_B(-0.3 * (double)direction, 0.0, 0.0);
        } else {
            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y(75.0f * (float)direction));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(70.0f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(45.0f * (float)direction));
        }
        matrixStackIn.n_1700_B(1.2f, 1.2f, 1.2f);
        if (stack.M_588_G() == F_1573_j.P_1922_E) {
            matrixStackIn.n_1700_B(-0.1 * (double)direction, -0.2, 0.0);
        }
    }

    private boolean n_1700_B(X_4340_E player, float partialTicks, x_1688_C handIn, float swingProgress, Z_1993_T stack, float equippedProgress, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        boolean usingThisHand;
        t_1595_x swordAnimations = this.J_1907_R();
        if (swordAnimations == null || player.B_3040_x()) {
            return false;
        }
        if (handIn == x_1688_C.J_1907_R) {
            return this.n_1700_B(player, partialTicks, swingProgress, stack, equippedProgress, matrixStackIn, bufferIn, combinedLightIn);
        }
        if (stack.n_1700_B()) {
            return this.n_1700_B(player, player.d_2169_p(), swingProgress, equippedProgress, matrixStackIn, bufferIn, combinedLightIn);
        }
        if (stack.J_1907_R() instanceof G_3165_y || stack.J_1907_R() instanceof Z_1630_j) {
            return false;
        }
        k_4231_L handside = player.d_2169_p();
        boolean rightHand = handside == k_4231_L.J_1907_R;
        boolean bl = usingThisHand = player.Y_601_j() && player.U_144_f() > 0 && player.Q_2552_b() == handIn;
        if (usingThisHand) {
            float useTicks = (float)stack.u_2550_I() - ((float)player.U_144_f() - partialTicks + 1.0f);
            switch (stack.M_588_G()) {
                case J_1907_R: 
                case R_4764_Y: {
                    this.n_1700_B(matrixStackIn, handside, useTicks);
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, swingProgress);
                    break;
                }
                case G_564_y: {
                    this.J_1907_R(matrixStackIn, handside, useTicks);
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, swingProgress);
                    break;
                }
                default: {
                    return false;
                }
            }
        } else {
            i_789_Q eventSwingAnimation = new i_789_Q(player, swingProgress, handIn, matrixStackIn);
            A_4115_X.n_1700_B(eventSwingAnimation);
            this.n_1700_B(matrixStackIn, equippedProgress, handside);
            this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, handside, 0.0f, 0.0f);
        }
        this.n_1700_B(matrixStackIn, handside);
        this.n_1700_B(matrixStackIn, handside, stack);
        this.n_1700_B((r_4811_B)player, stack, rightHand ? ItemTransforms.J_1907_R.P_1922_E : ItemTransforms.J_1907_R.G_564_y, !rightHand, matrixStackIn, bufferIn, combinedLightIn);
        return true;
    }

    public void n_1700_B(r_4811_B livingEntityIn, Z_1993_T itemStackIn, ItemTransforms.J_1907_R transformTypeIn, boolean leftHand, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        CustomItems.setRenderOffHand(leftHand);
        if (!itemStackIn.n_1700_B()) {
            this.u_2550_I.n_1700_B(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, livingEntityIn.O_508_d, combinedLightIn, Z_3224_L.n_1700_B);
        }
        CustomItems.setRenderOffHand(false);
    }

    private float G_564_y(float pitch) {
        float f = 1.0f - pitch / 45.0f + 0.1f;
        f = u_530_F.n_1700_B(f, 0.0f, 1.0f);
        return -u_530_F.J_1907_R(f * (float)Math.PI) * 0.5f + 0.5f;
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, k_4231_L side) {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.R_4764_Y.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        this.R_4764_Y.G_624_v().n_1700_B(bot1 != null ? bot1.P_1922_E.Q_2552_b.g_221_o() : this.R_4764_Y.Y_259_p.g_221_o());
        h_4311_S playerrenderer = (h_4311_S)this.s_956_w.n_1700_B(bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p);
        matrixStackIn.n_1700_B();
        float f = side == k_4231_L.J_1907_R ? 1.0f : -1.0f;
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(92.0f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(45.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f * -41.0f));
        matrixStackIn.n_1700_B((double)(f * 0.3f), (double)-1.1f, (double)0.45f);
        if (side == k_4231_L.J_1907_R) {
            playerrenderer.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p);
        } else {
            playerrenderer.J_1907_R(matrixStackIn, bufferIn, combinedLightIn, bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p);
        }
        matrixStackIn.J_1907_R();
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, float equippedProgress, k_4231_L handIn, float swingProgress, Z_1993_T stack) {
        float f = handIn == k_4231_L.J_1907_R ? 1.0f : -1.0f;
        matrixStackIn.n_1700_B((double)(f * 0.125f), -0.125, 0.0);
        if (!this.R_4764_Y.Y_259_p.F_3572_x()) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f * 10.0f));
            this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, swingProgress, handIn);
            matrixStackIn.J_1907_R();
        }
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B((double)(f * 0.51f), (double)(-0.08f + equippedProgress * -1.2f), -0.75);
        float f1 = u_530_F.R_4764_Y(swingProgress);
        float f2 = u_530_F.n_1700_B(f1 * (float)Math.PI);
        float f3 = -0.5f * f2;
        float f4 = 0.4f * u_530_F.n_1700_B(f1 * ((float)Math.PI * 2));
        float f5 = -0.3f * u_530_F.n_1700_B(swingProgress * (float)Math.PI);
        matrixStackIn.n_1700_B((double)(f * f3), (double)(f4 - 0.3f * f2), (double)f5);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f2 * -45.0f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f * f2 * -30.0f));
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, stack);
        matrixStackIn.J_1907_R();
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, float pitch, float equippedProgress, float swingProgress) {
        float f = u_530_F.R_4764_Y(swingProgress);
        float f1 = -0.2f * u_530_F.n_1700_B(swingProgress * (float)Math.PI);
        float f2 = -0.4f * u_530_F.n_1700_B(f * (float)Math.PI);
        matrixStackIn.n_1700_B(0.0, (double)(-f1 / 2.0f), (double)f2);
        float f3 = this.G_564_y(pitch);
        matrixStackIn.n_1700_B(0.0, (double)(0.04f + equippedProgress * -1.2f + f3 * -0.5f), (double)-0.72f);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f3 * -85.0f));
        if (!this.R_4764_Y.Y_259_p.F_3572_x()) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f));
            this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, k_4231_L.J_1907_R);
            this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, k_4231_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
        float f4 = u_530_F.n_1700_B(f * (float)Math.PI);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f4 * 20.0f));
        matrixStackIn.n_1700_B(2.0f, 2.0f, 2.0f);
        this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, this.G_564_y);
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, Z_1993_T stack) {
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
        matrixStackIn.n_1700_B(0.38f, 0.38f, 0.38f);
        matrixStackIn.n_1700_B(-0.5, -0.5, 0.0);
        matrixStackIn.n_1700_B(0.0078125f, 0.0078125f, 0.0078125f);
        F_3620_e mapdata = G_3165_y.J_1907_R(stack, this.R_4764_Y.Y_601_j);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(mapdata == null ? n_1700_B : J_1907_R);
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        ivertexbuilder.n_1700_B(matrix4f, -7.0f, 135.0f, 0.0f).color(255, 255, 255, 255).tex(0.0f, 1.0f).J_1907_R(combinedLightIn).endVertex();
        ivertexbuilder.n_1700_B(matrix4f, 135.0f, 135.0f, 0.0f).color(255, 255, 255, 255).tex(1.0f, 1.0f).J_1907_R(combinedLightIn).endVertex();
        ivertexbuilder.n_1700_B(matrix4f, 135.0f, -7.0f, 0.0f).color(255, 255, 255, 255).tex(1.0f, 0.0f).J_1907_R(combinedLightIn).endVertex();
        ivertexbuilder.n_1700_B(matrix4f, -7.0f, -7.0f, 0.0f).color(255, 255, 255, 255).tex(0.0f, 0.0f).J_1907_R(combinedLightIn).endVertex();
        if (mapdata != null) {
            this.R_4764_Y.s_956_w.t_148_a().n_1700_B(matrixStackIn, bufferIn, mapdata, false, combinedLightIn);
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, float equippedProgress, float swingProgress, k_4231_L side) {
        boolean flag = side != k_4231_L.n_1700_B;
        float f = flag ? 1.0f : -1.0f;
        float f1 = u_530_F.R_4764_Y(swingProgress);
        float f2 = -0.3f * u_530_F.n_1700_B(f1 * (float)Math.PI);
        float f3 = 0.4f * u_530_F.n_1700_B(f1 * ((float)Math.PI * 2));
        float f4 = -0.4f * u_530_F.n_1700_B(swingProgress * (float)Math.PI);
        matrixStackIn.n_1700_B((double)(f * (f2 + 0.64000005f)), (double)(f3 + -0.6f + equippedProgress * -0.6f), (double)(f4 + -0.71999997f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f * 45.0f));
        float f5 = u_530_F.n_1700_B(swingProgress * swingProgress * (float)Math.PI);
        float f6 = u_530_F.n_1700_B(f1 * (float)Math.PI);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f * f6 * 70.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f * f5 * -20.0f));
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.R_4764_Y.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E abstractclientplayerentity = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p;
        this.R_4764_Y.G_624_v().n_1700_B(abstractclientplayerentity.g_221_o());
        matrixStackIn.n_1700_B((double)(f * -1.0f), (double)3.6f, 3.5);
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f * 120.0f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(200.0f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f * -135.0f));
        matrixStackIn.n_1700_B((double)(f * 5.6f), 0.0, 0.0);
        h_4311_S playerrenderer = (h_4311_S)this.s_956_w.n_1700_B(abstractclientplayerentity);
        if (flag) {
            playerrenderer.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, abstractclientplayerentity);
        } else {
            playerrenderer.J_1907_R(matrixStackIn, bufferIn, combinedLightIn, abstractclientplayerentity);
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, float partialTicks, k_4231_L handIn, Z_1993_T stack) {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.R_4764_Y.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        float f = bot1 != null ? (float)bot1.P_1922_E.Q_2552_b.U_144_f() - partialTicks + 1.0f : (float)this.R_4764_Y.Y_259_p.U_144_f() - partialTicks + 1.0f;
        float f1 = f / (float)stack.u_2550_I();
        if (f1 < 0.8f) {
            float f2 = u_530_F.P_1922_E(u_530_F.J_1907_R(f / 4.0f * (float)Math.PI) * 0.03f);
            matrixStackIn.n_1700_B(0.0, (double)f2, 0.0);
        }
        float f3 = 1.0f - (float)Math.pow(f1, 27.0);
        int i = handIn == k_4231_L.J_1907_R ? 1 : -1;
        matrixStackIn.n_1700_B((double)(f3 * 0.6f * (float)i), (double)(f3 * -0.5f), (double)(f3 * 0.0f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)i * f3 * 90.0f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f3 * 10.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)i * f3 * 30.0f));
    }

    private void R_4764_Y(g_221_o matrixStackIn, k_4231_L handIn, float swingProgress) {
        int i = handIn == k_4231_L.J_1907_R ? 1 : -1;
        float f = u_530_F.n_1700_B(swingProgress * swingProgress * (float)Math.PI);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)i * (45.0f + f * -20.0f)));
        float f1 = u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress) * (float)Math.PI);
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)i * f1 * -20.0f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f1 * -80.0f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)i * -45.0f));
    }

    private void G_564_y(g_221_o matrixStackIn, k_4231_L handIn, float equippedProg) {
        int i = handIn == k_4231_L.J_1907_R ? 1 : -1;
        A_2714_y event = new A_2714_y(handIn, equippedProg);
        A_4115_X.n_1700_B(event);
        matrixStackIn.n_1700_B((double)((float)i * 0.56f), (double)(-0.52f + equippedProg * -event.J_1907_R()), (double)-0.72f);
    }

    public void n_1700_B(float partialTicks, g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, X_4340_E playerEntityIn, int combinedLightIn) {
        float f = playerEntityIn.Y_601_j(partialTicks);
        x_1688_C hand = (x_1688_C)((Object)MoreObjects.firstNonNull((Object)((Object)playerEntityIn.C_290_v), (Object)((Object)x_1688_C.n_1700_B)));
        float f1 = u_530_F.v_4262_N(partialTicks, playerEntityIn.UploadStatus, playerEntityIn.f_4016_n);
        boolean flag = true;
        boolean flag1 = true;
        if (playerEntityIn.Y_601_j()) {
            Z_1993_T itemstack1;
            x_1688_C hand1;
            Z_1993_T itemstack = playerEntityIn.B_2580_P();
            if (itemstack.J_1907_R() instanceof ProjectileWeaponItem) {
                flag = playerEntityIn.Q_2552_b() == x_1688_C.n_1700_B;
                boolean bl = flag1 = !flag;
            }
            if ((hand1 = playerEntityIn.Q_2552_b()) == x_1688_C.n_1700_B && (itemstack1 = playerEntityIn.S_4035_N()).J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack1)) {
                flag1 = false;
            }
        } else {
            Z_1993_T itemstack2 = playerEntityIn.A_2714_y();
            Z_1993_T itemstack3 = playerEntityIn.S_4035_N();
            if (itemstack2.J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack2)) {
                boolean bl = flag1 = !flag;
            }
            if (itemstack3.J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack3)) {
                flag = !itemstack2.n_1700_B();
                flag1 = !flag;
            }
        }
        float f3 = u_530_F.v_4262_N(partialTicks, playerEntityIn.g_164_R, playerEntityIn.e_2887_G);
        float f4 = u_530_F.v_4262_N(partialTicks, playerEntityIn.B_1668_F, playerEntityIn.g_221_o);
        if (flag) {
            float f5 = hand == x_1688_C.n_1700_B ? f : 0.0f;
            float f2 = 1.0f - u_530_F.v_4262_N(partialTicks, this.v_4262_N, this.u_1723_Y);
            if (!Reflector.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_renderSpecificFirstPersonHand, new Object[]{x_1688_C.n_1700_B, matrixStackIn, bufferIn, combinedLightIn, Float.valueOf(partialTicks), Float.valueOf(f1), Float.valueOf(f5), Float.valueOf(f2), this.G_564_y})) {
                this.n_1700_B(playerEntityIn, partialTicks, f1, x_1688_C.n_1700_B, f5, this.G_564_y, f2, matrixStackIn, (o_3091_w)bufferIn, combinedLightIn);
            }
        }
        if (flag1) {
            float f6 = hand == x_1688_C.J_1907_R ? f : 0.0f;
            float f7 = 1.0f - u_530_F.v_4262_N(partialTicks, this.t_148_a, this.w_1484_f);
            if (!Reflector.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_renderSpecificFirstPersonHand, new Object[]{x_1688_C.J_1907_R, matrixStackIn, bufferIn, combinedLightIn, Float.valueOf(partialTicks), Float.valueOf(f1), Float.valueOf(f6), Float.valueOf(f7), this.P_1922_E})) {
                this.n_1700_B(playerEntityIn, partialTicks, f1, x_1688_C.J_1907_R, f6, this.P_1922_E, f7, matrixStackIn, (o_3091_w)bufferIn, combinedLightIn);
            }
        }
        bufferIn.J_1907_R();
    }

    public void n_1700_B(float partialTicks, g_221_o matrixStackIn, o_3091_w.n_1700_B bufferIn, Z_875_P playerEntityIn, int combinedLightIn) {
        float f = playerEntityIn.Y_601_j(partialTicks);
        x_1688_C hand = (x_1688_C)((Object)MoreObjects.firstNonNull((Object)((Object)playerEntityIn.C_290_v), (Object)((Object)x_1688_C.n_1700_B)));
        float f1 = u_530_F.v_4262_N(partialTicks, playerEntityIn.UploadStatus, playerEntityIn.f_4016_n);
        boolean flag = true;
        boolean flag1 = true;
        if (playerEntityIn.Y_601_j()) {
            Z_1993_T itemstack1;
            x_1688_C hand1;
            Z_1993_T itemstack = playerEntityIn.B_2580_P();
            if (itemstack.J_1907_R() instanceof ProjectileWeaponItem) {
                flag = playerEntityIn.Q_2552_b() == x_1688_C.n_1700_B;
                boolean bl = flag1 = !flag;
            }
            if ((hand1 = playerEntityIn.Q_2552_b()) == x_1688_C.n_1700_B && (itemstack1 = playerEntityIn.S_4035_N()).J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack1)) {
                flag1 = false;
            }
        } else {
            Z_1993_T itemstack2 = playerEntityIn.A_2714_y();
            Z_1993_T itemstack3 = playerEntityIn.S_4035_N();
            if (itemstack2.J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack2)) {
                boolean bl = flag1 = !flag;
            }
            if (itemstack3.J_1907_R() instanceof Z_1630_j && Z_1630_j.G_564_y(itemstack3)) {
                flag = !itemstack2.n_1700_B();
                flag1 = !flag;
            }
        }
        float f3 = u_530_F.v_4262_N(partialTicks, playerEntityIn.c_3005_b, playerEntityIn.q_2307_F);
        float f4 = u_530_F.v_4262_N(partialTicks, playerEntityIn.Z_875_P, playerEntityIn.k_2293_S);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y((playerEntityIn.J_1907_R(partialTicks) - f3) * 0.1f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((playerEntityIn.R_4764_Y(partialTicks) - f4) * 0.1f));
        if (flag) {
            float f5 = hand == x_1688_C.n_1700_B ? f : 0.0f;
            float f2 = 1.0f - u_530_F.v_4262_N(partialTicks, this.v_4262_N, this.u_1723_Y);
            if (!Reflector.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_renderSpecificFirstPersonHand, new Object[]{x_1688_C.n_1700_B, matrixStackIn, bufferIn, combinedLightIn, Float.valueOf(partialTicks), Float.valueOf(f1), Float.valueOf(f5), Float.valueOf(f2), this.G_564_y})) {
                this.n_1700_B(playerEntityIn, partialTicks, f1, x_1688_C.n_1700_B, f5, this.G_564_y, f2, matrixStackIn, (o_3091_w)bufferIn, combinedLightIn);
            }
        }
        if (flag1) {
            float f6 = hand == x_1688_C.J_1907_R ? f : 0.0f;
            float f7 = 1.0f - u_530_F.v_4262_N(partialTicks, this.t_148_a, this.w_1484_f);
            if (!Reflector.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_renderSpecificFirstPersonHand, new Object[]{x_1688_C.J_1907_R, matrixStackIn, bufferIn, combinedLightIn, Float.valueOf(partialTicks), Float.valueOf(f1), Float.valueOf(f6), Float.valueOf(f7), this.P_1922_E})) {
                this.n_1700_B(playerEntityIn, partialTicks, f1, x_1688_C.J_1907_R, f6, this.P_1922_E, f7, matrixStackIn, (o_3091_w)bufferIn, combinedLightIn);
            }
        }
        bufferIn.J_1907_R();
    }

    private void n_1700_B(Z_875_P player, float partialTicks, float pitch, x_1688_C handIn, float swingProgress, Z_1993_T stack, float equippedProgress, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        if (!Config.isShaders() || !Shaders.isSkipRenderHand(handIn)) {
            boolean flag = handIn == x_1688_C.n_1700_B;
            k_4231_L handside = flag ? player.d_2169_p() : player.d_2169_p().n_1700_B();
            matrixStackIn.n_1700_B();
            if (stack.n_1700_B()) {
                if (flag && !player.F_3572_x()) {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, swingProgress, handside);
                }
            } else if (stack.J_1907_R() instanceof G_3165_y) {
                if (flag && this.P_1922_E.n_1700_B()) {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, pitch, equippedProgress, swingProgress);
                } else {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, handside, swingProgress, stack);
                }
            } else {
                this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, player, stack, handside, swingProgress, equippedProgress);
            }
            matrixStackIn.J_1907_R();
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, Z_875_P player, Z_1993_T stack, k_4231_L handside, float swingProgress, float equippedProgress) {
        boolean flag2 = handside == k_4231_L.J_1907_R;
        int i = flag2 ? 1 : -1;
        this.G_564_y(matrixStackIn, handside, equippedProgress);
        this.R_4764_Y(matrixStackIn, handside, swingProgress);
        this.R_4764_Y.r_715_M().n_1700_B(player, stack, flag2 ? ItemTransforms.J_1907_R.P_1922_E : ItemTransforms.J_1907_R.G_564_y, !flag2, matrixStackIn, bufferIn, player.O_508_d, combinedLightIn, Z_3224_L.n_1700_B);
    }

    private void n_1700_B(X_4340_E player, float partialTicks, float pitch, x_1688_C handIn, float swingProgress, Z_1993_T stack, float equippedProgress, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn) {
        if (!Config.isShaders() || !Shaders.isSkipRenderHand(handIn)) {
            boolean flag = handIn == x_1688_C.n_1700_B;
            k_4231_L handside = flag ? player.d_2169_p() : player.d_2169_p().n_1700_B();
            matrixStackIn.n_1700_B();
            A_4115_X.n_1700_B(new E_453_w(matrixStackIn, handside));
            if (this.n_1700_B(player, partialTicks, handIn, swingProgress, stack, equippedProgress, matrixStackIn, bufferIn, combinedLightIn)) {
                matrixStackIn.J_1907_R();
                return;
            }
            if (stack.n_1700_B()) {
                if (flag && !player.F_3572_x()) {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, swingProgress, handside);
                }
            } else if (stack.J_1907_R() instanceof G_3165_y) {
                if (flag && this.P_1922_E.n_1700_B()) {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, pitch, equippedProgress, swingProgress);
                } else {
                    this.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, equippedProgress, handside, swingProgress, stack);
                }
            } else if (stack.J_1907_R() instanceof Z_1630_j) {
                int i;
                boolean flag1 = Z_1630_j.G_564_y(stack);
                boolean flag2 = handside == k_4231_L.J_1907_R;
                int n = i = flag2 ? 1 : -1;
                if (player.Y_601_j() && player.U_144_f() > 0 && player.Q_2552_b() == handIn) {
                    this.G_564_y(matrixStackIn, handside, equippedProgress);
                    matrixStackIn.n_1700_B((double)((float)i * -0.4785682f), (double)-0.094387f, 0.05731530860066414);
                    matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-11.935f));
                    matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)i * 65.3f));
                    matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)i * -9.785f));
                    float f9 = (float)stack.u_2550_I() - ((float)this.R_4764_Y.Y_259_p.U_144_f() - partialTicks + 1.0f);
                    float f12 = f9 / (float)Z_1630_j.v_4262_N(stack);
                    if (f12 > 1.0f) {
                        f12 = 1.0f;
                    }
                    if (f12 > 0.1f) {
                        float f15 = u_530_F.n_1700_B((f9 - 0.1f) * 1.3f);
                        float f3 = f12 - 0.1f;
                        float f4 = f15 * f3;
                        matrixStackIn.n_1700_B((double)(f4 * 0.0f), (double)(f4 * 0.004f), (double)(f4 * 0.0f));
                    }
                    matrixStackIn.n_1700_B((double)(f12 * 0.0f), (double)(f12 * 0.0f), (double)(f12 * 0.04f));
                    matrixStackIn.n_1700_B(1.0f, 1.0f, 1.0f + f12 * 0.2f);
                    matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y((float)i * 45.0f));
                } else {
                    float f = -0.4f * u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress) * (float)Math.PI);
                    float f1 = 0.2f * u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress) * ((float)Math.PI * 2));
                    float f2 = -0.2f * u_530_F.n_1700_B(swingProgress * (float)Math.PI);
                    matrixStackIn.n_1700_B((double)((float)i * f), (double)f1, (double)f2);
                    this.G_564_y(matrixStackIn, handside, equippedProgress);
                    this.R_4764_Y(matrixStackIn, handside, swingProgress);
                    if (flag1 && swingProgress < 0.001f) {
                        matrixStackIn.n_1700_B((double)((float)i * -0.641864f), 0.0, 0.0);
                        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)i * 10.0f));
                    }
                }
                this.n_1700_B((r_4811_B)player, stack, flag2 ? ItemTransforms.J_1907_R.P_1922_E : ItemTransforms.J_1907_R.G_564_y, !flag2, matrixStackIn, bufferIn, combinedLightIn);
            } else {
                boolean flag3;
                boolean bl = flag3 = handside == k_4231_L.J_1907_R;
                if (player.Y_601_j() && player.U_144_f() > 0 && player.Q_2552_b() == handIn) {
                    int k = flag3 ? 1 : -1;
                    switch (stack.M_588_G()) {
                        case n_1700_B: {
                            this.G_564_y(matrixStackIn, handside, equippedProgress);
                            break;
                        }
                        case J_1907_R: 
                        case R_4764_Y: {
                            this.n_1700_B(matrixStackIn, partialTicks, handside, stack);
                            this.G_564_y(matrixStackIn, handside, equippedProgress);
                            break;
                        }
                        case G_564_y: {
                            this.G_564_y(matrixStackIn, handside, equippedProgress);
                            break;
                        }
                        case P_1922_E: {
                            this.G_564_y(matrixStackIn, handside, equippedProgress);
                            matrixStackIn.n_1700_B((double)((float)k * -0.2785682f), 0.18344387412071228, 0.15731531381607056);
                            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-13.935f));
                            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)k * 35.3f));
                            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)k * -9.785f));
                            float f8 = (float)stack.u_2550_I() - ((float)this.R_4764_Y.Y_259_p.U_144_f() - partialTicks + 1.0f);
                            float f11 = f8 / 20.0f;
                            f11 = (f11 * f11 + f11 * 2.0f) / 3.0f;
                            if (f11 > 1.0f) {
                                f11 = 1.0f;
                            }
                            if (f11 > 0.1f) {
                                float f14 = u_530_F.n_1700_B((f8 - 0.1f) * 1.3f);
                                float f17 = f11 - 0.1f;
                                float f19 = f14 * f17;
                                matrixStackIn.n_1700_B((double)(f19 * 0.0f), (double)(f19 * 0.004f), (double)(f19 * 0.0f));
                            }
                            matrixStackIn.n_1700_B((double)(f11 * 0.0f), (double)(f11 * 0.0f), (double)(f11 * 0.04f));
                            matrixStackIn.n_1700_B(1.0f, 1.0f, 1.0f + f11 * 0.2f);
                            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y((float)k * 45.0f));
                            break;
                        }
                        case u_1723_Y: {
                            this.G_564_y(matrixStackIn, handside, equippedProgress);
                            matrixStackIn.n_1700_B((double)((float)k * -0.5f), (double)0.7f, (double)0.1f);
                            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-55.0f));
                            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)k * 35.3f));
                            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)k * -9.785f));
                            float f13 = (float)stack.u_2550_I() - ((float)this.R_4764_Y.Y_259_p.U_144_f() - partialTicks + 1.0f);
                            float f16 = f13 / 10.0f;
                            if (f16 > 1.0f) {
                                f16 = 1.0f;
                            }
                            if (f16 > 0.1f) {
                                float f18 = u_530_F.n_1700_B((f13 - 0.1f) * 1.3f);
                                float f20 = f16 - 0.1f;
                                float f5 = f18 * f20;
                                matrixStackIn.n_1700_B((double)(f5 * 0.0f), (double)(f5 * 0.004f), (double)(f5 * 0.0f));
                            }
                            matrixStackIn.n_1700_B(0.0, 0.0, (double)(f16 * 0.2f));
                            matrixStackIn.n_1700_B(1.0f, 1.0f, 1.0f + f16 * 0.2f);
                            matrixStackIn.n_1700_B(M_1336_P.R_4764_Y.R_4764_Y((float)k * 45.0f));
                        }
                    }
                } else if (player.B_3040_x()) {
                    this.G_564_y(matrixStackIn, handside, equippedProgress);
                    int j = flag3 ? 1 : -1;
                    matrixStackIn.n_1700_B((double)((float)j * -0.4f), (double)0.8f, (double)0.3f);
                    matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)j * 65.0f));
                    matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)j * -85.0f));
                } else {
                    int l;
                    this.G_564_y(matrixStackIn, handside, equippedProgress);
                    i_789_Q eventSwingAnimation = new i_789_Q(player, swingProgress, handIn, matrixStackIn);
                    A_4115_X.n_1700_B(eventSwingAnimation);
                    float f6 = -0.4f * u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress) * (float)Math.PI);
                    float f7 = 0.2f * u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress) * ((float)Math.PI * 2));
                    float f10 = -0.2f * u_530_F.n_1700_B(swingProgress * (float)Math.PI);
                    int n = l = flag3 ? 1 : -1;
                    if (!eventSwingAnimation.n_1700_B()) {
                        matrixStackIn.n_1700_B((double)((float)l * f6), (double)f7, (double)f10);
                        this.R_4764_Y(matrixStackIn, handside, swingProgress);
                    } else if (handside != eventSwingAnimation.J_1907_R().d_2169_p()) {
                        matrixStackIn.n_1700_B((double)((float)l * f6), (double)f7, (double)f10);
                        this.R_4764_Y(matrixStackIn, handside, swingProgress);
                    }
                }
                this.n_1700_B((r_4811_B)player, stack, flag3 ? ItemTransforms.J_1907_R.P_1922_E : ItemTransforms.J_1907_R.G_564_y, !flag3, matrixStackIn, bufferIn, combinedLightIn);
            }
            matrixStackIn.J_1907_R();
        }
    }

    public void n_1700_B() {
        X_4340_E playerForTick;
        this.v_4262_N = this.u_1723_Y;
        this.t_148_a = this.w_1484_f;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.R_4764_Y.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E clientplayerentity = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p;
        Z_1993_T itemstack = clientplayerentity.A_2714_y();
        Z_1993_T itemstack1 = clientplayerentity.S_4035_N();
        if (Z_1993_T.J_1907_R(this.G_564_y, itemstack)) {
            this.G_564_y = itemstack;
        }
        if (Z_1993_T.J_1907_R(this.P_1922_E, itemstack1)) {
            this.P_1922_E = itemstack1;
        }
        X_4340_E x_4340_E = playerForTick = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.R_4764_Y.Y_259_p;
        if (this.R_4764_Y.Y_259_p.e_4240_b()) {
            this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y - 0.4f, 0.0f, 1.0f);
            this.w_1484_f = u_530_F.n_1700_B(this.w_1484_f - 0.4f, 0.0f, 1.0f);
        } else {
            float f = playerForTick.k_2293_S(1.0f);
            if (Reflector.ForgeHooksClient_shouldCauseReequipAnimation.exists()) {
                boolean flag = Reflector.callBoolean(Reflector.ForgeHooksClient_shouldCauseReequipAnimation, this.G_564_y, itemstack, playerForTick.l_1268_F.G_564_y);
                boolean flag1 = Reflector.callBoolean(Reflector.ForgeHooksClient_shouldCauseReequipAnimation, this.P_1922_E, itemstack1, -1);
                if (!flag && !Objects.equals(this.G_564_y, itemstack)) {
                    this.G_564_y = itemstack;
                }
                if (!flag1 && !Objects.equals(this.P_1922_E, itemstack1)) {
                    this.P_1922_E = itemstack1;
                }
            }
            this.u_1723_Y += u_530_F.n_1700_B((this.G_564_y == itemstack ? f * f * f : 0.0f) - this.u_1723_Y, -0.4f, 0.4f);
            this.w_1484_f += u_530_F.n_1700_B((float)(this.P_1922_E == itemstack1 ? 1 : 0) - this.w_1484_f, -0.4f, 0.4f);
        }
        if (this.u_1723_Y < 0.1f) {
            this.G_564_y = itemstack;
            if (Config.isShaders()) {
                Shaders.setItemToRenderMain(this.G_564_y);
            }
        }
        if (this.w_1484_f < 0.1f) {
            this.P_1922_E = itemstack1;
            if (Config.isShaders()) {
                Shaders.setItemToRenderOff(this.P_1922_E);
            }
        }
    }

    public void n_1700_B(x_1688_C hand) {
        if (hand == x_1688_C.n_1700_B) {
            this.u_1723_Y = 0.0f;
        } else {
            this.w_1484_f = 0.0f;
        }
    }
}



