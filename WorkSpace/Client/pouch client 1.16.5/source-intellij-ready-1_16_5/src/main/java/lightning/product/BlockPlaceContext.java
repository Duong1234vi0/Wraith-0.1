/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.x_1688_C;

public class BlockPlaceContext
extends UseOnContext {
    private final c_1514_x J_1907_R;
    protected boolean n_1700_B = true;

    public BlockPlaceContext(a_3913_L p_i241237_1_, x_1688_C p_i241237_2_, Z_1993_T p_i241237_3_, BlockHitResult p_i241237_4_) {
        this(p_i241237_1_.O_508_d, p_i241237_1_, p_i241237_2_, p_i241237_3_, p_i241237_4_);
    }

    public BlockPlaceContext(UseOnContext context) {
        this(context.getWorld(), context.getPlayer(), context.getHand(), context.getItem(), context.func_242401_i());
    }

    protected BlockPlaceContext(b_4507_u worldIn, @Nullable a_3913_L playerIn, x_1688_C handIn, Z_1993_T stackIn, BlockHitResult rayTraceResultIn) {
        super(worldIn, playerIn, handIn, stackIn, rayTraceResultIn);
        this.J_1907_R = rayTraceResultIn.n_1700_B().offset(rayTraceResultIn.J_1907_R());
        this.n_1700_B = worldIn.getBlockState(rayTraceResultIn.n_1700_B()).n_1700_B(this);
    }

    public static BlockPlaceContext n_1700_B(BlockPlaceContext context, c_1514_x pos, b_257_Y directionIn) {
        return new BlockPlaceContext(context.getWorld(), context.getPlayer(), context.getHand(), context.getItem(), new BlockHitResult(new e_2866_D((double)pos.getX() + 0.5 + (double)directionIn.t_148_a() * 0.5, (double)pos.getY() + 0.5 + (double)directionIn.s_956_w() * 0.5, (double)pos.getZ() + 0.5 + (double)directionIn.u_2550_I() * 0.5), directionIn, pos, false));
    }

    @Override
    public c_1514_x getPos() {
        return this.n_1700_B ? super.getPos() : this.J_1907_R;
    }

    public boolean n_1700_B() {
        return this.n_1700_B || this.getWorld().getBlockState(this.getPos()).n_1700_B(this);
    }

    public boolean J_1907_R() {
        return this.n_1700_B;
    }

    public b_257_Y R_4764_Y() {
        return b_257_Y.n_1700_B(this.getPlayer())[0];
    }

    public b_257_Y[] G_564_y() {
        int i;
        b_257_Y[] adirection = b_257_Y.n_1700_B(this.getPlayer());
        if (this.n_1700_B) {
            return adirection;
        }
        b_257_Y direction = this.getFace();
        for (i = 0; i < adirection.length && adirection[i] != direction.u_1723_Y(); ++i) {
        }
        if (i > 0) {
            System.arraycopy(adirection, 0, adirection, 1, i);
            adirection[0] = direction.u_1723_Y();
        }
        return adirection;
    }
}


