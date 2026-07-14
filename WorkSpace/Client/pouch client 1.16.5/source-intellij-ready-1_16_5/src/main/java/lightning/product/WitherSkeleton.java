/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.AbstractSkeleton;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.AbstractPiglin;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.h_384_L;
import lightning.product.k_2610_C;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class WitherSkeleton
extends AbstractSkeleton {
    public WitherSkeleton(t_5_h<? extends WitherSkeleton> typeIn, b_4507_u worldIn) {
        super((t_5_h<? extends AbstractSkeleton>)typeIn, worldIn);
        this.n_1700_B(I_1869_h.v_4262_N, 8.0f);
    }

    @Override
    protected void M_182_A() {
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<AbstractPiglin>((Z_530_i)this, AbstractPiglin.class, true));
        super.M_182_A();
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.PipeBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.PlayerHeadBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.SkullBlock;
    }

    @Override
    SoundEvent y_4642_Y() {
        return SoundEvents.PlayerWallHeadBlock;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        b_3485_j creeperentity;
        super.n_1700_B(source, looting, recentlyHitIn);
        N_4263_v entity = source.u_2550_I();
        if (entity instanceof b_3485_j && (creeperentity = (b_3485_j)entity).J_3635_s()) {
            creeperentity.o_82_k();
            this.n_1700_B((q_1803_e)Items.DropperBlock);
        }
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.Y_3623_f));
    }

    @Override
    protected void J_1907_R(DifficultyInstance difficulty) {
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        V_3157_k ilivingentitydata = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.n_1700_B(Attributes.u_1723_Y).n_1700_B(4.0);
        this.V_1176_p();
        return ilivingentitydata;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 2.1f;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        if (!super.q_2307_F(entityIn)) {
            return false;
        }
        if (entityIn instanceof r_4811_B) {
            ((r_4811_B)entityIn).n_1700_B(new k_2610_C(MobEffects.Y_601_j, 200));
        }
        return true;
    }

    @Override
    protected h_384_L J_1907_R(Z_1993_T arrowStack, float distanceFactor) {
        h_384_L abstractarrowentity = super.J_1907_R(arrowStack, distanceFactor);
        abstractarrowentity.P_1922_E(100);
        return abstractarrowentity;
    }

    @Override
    public boolean J_1907_R(k_2610_C potioneffectIn) {
        return potioneffectIn.n_1700_B() == MobEffects.Y_601_j ? false : super.J_1907_R(potioneffectIn);
    }
}


