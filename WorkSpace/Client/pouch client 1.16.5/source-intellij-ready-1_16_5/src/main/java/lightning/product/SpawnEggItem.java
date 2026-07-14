/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.AgableMob;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Q_584_o;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.s_3834_w;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;

public class SpawnEggItem
extends q_1613_l {
    private static final Map<t_5_h<?>, SpawnEggItem> n_1700_B = Maps.newIdentityHashMap();
    private final int J_1907_R;
    private final int R_4764_Y;
    private final t_5_h<?> G_564_y;

    public SpawnEggItem(t_5_h<?> typeIn, int primaryColorIn, int secondaryColorIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.G_564_y = typeIn;
        this.J_1907_R = primaryColorIn;
        this.R_4764_Y = secondaryColorIn;
        n_1700_B.put(typeIn, this);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        i_2154_H tileentity;
        b_4507_u world = context.getWorld();
        if (!(world instanceof e_3591_l)) {
            return m_3054_I.n_1700_B;
        }
        Z_1993_T itemstack = context.getItem();
        c_1514_x blockpos = context.getPos();
        b_257_Y direction = context.getFace();
        K_4074_S blockstate = world.getBlockState(blockpos);
        if (blockstate.n_1700_B(a_3742_W.j_306_t) && (tileentity = world.getTileEntity(blockpos)) instanceof SpawnerBlockEntity) {
            Q_584_o abstractspawner = ((SpawnerBlockEntity)tileentity).v_4262_N();
            t_5_h<?> entitytype1 = this.n_1700_B(itemstack.Q_4569_t());
            abstractspawner.n_1700_B(entitytype1);
            tileentity.J_1907_R();
            world.n_1700_B(blockpos, blockstate, blockstate, 3);
            itemstack.v_4262_N(1);
            return m_3054_I.J_1907_R;
        }
        c_1514_x blockpos1 = blockstate.u_2550_I(world, blockpos).J_1907_R() ? blockpos : blockpos.offset(direction);
        t_5_h<?> entitytype = this.n_1700_B(itemstack.Q_4569_t());
        if (entitytype.n_1700_B((e_3591_l)world, itemstack, context.getPlayer(), blockpos1, a_3160_D.P_4830_p, true, !Objects.equals(blockpos, blockpos1) && direction == b_257_Y.J_1907_R) != null) {
            itemstack.v_4262_N(1);
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        BlockHitResult raytraceresult = SpawnEggItem.n_1700_B(worldIn, playerIn, ClipContext.J_1907_R.J_1907_R);
        if (((HitResult)raytraceresult).R_4764_Y() != HitResult.n_1700_B.J_1907_R) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        if (!(worldIn instanceof e_3591_l)) {
            return InteractionResultHolder.n_1700_B(itemstack);
        }
        BlockHitResult blockraytraceresult = raytraceresult;
        c_1514_x blockpos = blockraytraceresult.n_1700_B();
        if (!(worldIn.getBlockState(blockpos).J_1907_R() instanceof s_3834_w)) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        if (worldIn.n_1700_B(playerIn, blockpos) && playerIn.n_1700_B(blockpos, blockraytraceresult.J_1907_R(), itemstack)) {
            t_5_h<?> entitytype = this.n_1700_B(itemstack.Q_4569_t());
            if (entitytype.n_1700_B((e_3591_l)worldIn, itemstack, playerIn, blockpos, a_3160_D.P_4830_p, false, false) == null) {
                return InteractionResultHolder.R_4764_Y(itemstack);
            }
            if (!playerIn.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
            return InteractionResultHolder.J_1907_R(itemstack);
        }
        return InteractionResultHolder.G_564_y(itemstack);
    }

    public boolean n_1700_B(@Nullable U_2912_j nbt, t_5_h<?> type) {
        return Objects.equals(this.n_1700_B(nbt), type);
    }

    public int n_1700_B(int tintIndex) {
        return tintIndex == 0 ? this.J_1907_R : this.R_4764_Y;
    }

    @Nullable
    public static SpawnEggItem n_1700_B(@Nullable t_5_h<?> type) {
        return n_1700_B.get(type);
    }

    public static Iterable<SpawnEggItem> v_4262_N() {
        return Iterables.unmodifiableIterable(n_1700_B.values());
    }

    public t_5_h<?> n_1700_B(@Nullable U_2912_j nbt) {
        U_2912_j compoundnbt;
        if (nbt != null && nbt.R_4764_Y("EntityTag", 10) && (compoundnbt = nbt.M_182_A("EntityTag")).R_4764_Y("id", 8)) {
            return t_5_h.n_1700_B(compoundnbt.M_588_G("id")).orElse(this.G_564_y);
        }
        return this.G_564_y;
    }

    public Optional<Z_530_i> n_1700_B(a_3913_L player, Z_530_i mob, t_5_h<? extends Z_530_i> entityType, e_3591_l world, e_2866_D pos, Z_1993_T stack) {
        if (!this.n_1700_B(stack.Q_4569_t(), entityType)) {
            return Optional.empty();
        }
        Z_530_i mobentity = mob instanceof AgableMob ? ((AgableMob)mob).n_1700_B(world, (AgableMob)mob) : entityType.n_1700_B(world);
        if (mobentity == null) {
            return Optional.empty();
        }
        mobentity.n_1700_B(true);
        if (!mobentity.d_()) {
            return Optional.empty();
        }
        mobentity.J_1907_R(pos.n_1700_B(), pos.J_1907_R(), pos.R_4764_Y(), 0.0f, 0.0f);
        world.n_1700_B((N_4263_v)mobentity);
        if (stack.Y_601_j()) {
            mobentity.n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
        if (!player.C_415_h.G_564_y) {
            stack.v_4262_N(1);
        }
        return Optional.of(mobentity);
    }
}


