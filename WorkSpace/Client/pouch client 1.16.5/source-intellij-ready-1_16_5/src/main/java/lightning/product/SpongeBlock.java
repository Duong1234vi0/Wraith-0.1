/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import lightning.product.FluidTags;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.Tuple;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import lightning.product.o_3946_o;
import lightning.product.q_4293_E;
import lightning.product.s_3834_w;
import lightning.product.Material;

public class SpongeBlock
extends T_2915_h {
    protected SpongeBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(worldIn, pos);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        this.n_1700_B(worldIn, pos);
        super.n_1700_B(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        if (this.J_1907_R(worldIn, pos)) {
            worldIn.n_1700_B(pos, a_3742_W.UploadStatus.multiplayerClientSuggestionProvider(), 2);
            worldIn.R_4764_Y(2001, pos, T_2915_h.s_956_w(a_3742_W.c_3005_b.multiplayerClientSuggestionProvider()));
        }
    }

    private boolean J_1907_R(b_4507_u worldIn, c_1514_x pos) {
        LinkedList queue = Lists.newLinkedList();
        queue.add(new Tuple<c_1514_x, Integer>(pos, 0));
        int i = 0;
        while (!queue.isEmpty()) {
            Tuple tuple = (Tuple)queue.poll();
            c_1514_x blockpos = (c_1514_x)tuple.n_1700_B();
            int j = (Integer)tuple.J_1907_R();
            for (b_257_Y direction : b_257_Y.values()) {
                c_1514_x blockpos1 = blockpos.offset(direction);
                K_4074_S blockstate = worldIn.getBlockState(blockpos1);
                FluidState fluidstate = worldIn.getFluidState(blockpos1);
                Material material = blockstate.R_4764_Y();
                if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) continue;
                if (blockstate.J_1907_R() instanceof o_3946_o && ((o_3946_o)((Object)blockstate.J_1907_R())).J_1907_R(worldIn, blockpos1, blockstate) != Fluids.n_1700_B) {
                    ++i;
                    if (j >= 6) continue;
                    queue.add(new Tuple<c_1514_x, Integer>(blockpos1, j + 1));
                    continue;
                }
                if (blockstate.J_1907_R() instanceof s_3834_w) {
                    worldIn.n_1700_B(blockpos1, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
                    ++i;
                    if (j >= 6) continue;
                    queue.add(new Tuple<c_1514_x, Integer>(blockpos1, j + 1));
                    continue;
                }
                if (material != Material.u_1723_Y && material != Material.t_148_a) continue;
                i_2154_H tileentity = blockstate.J_1907_R().G_564_y() ? worldIn.getTileEntity(blockpos1) : null;
                SpongeBlock.n_1700_B(blockstate, worldIn, blockpos1, tileentity);
                worldIn.n_1700_B(blockpos1, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
                ++i;
                if (j >= 6) continue;
                queue.add(new Tuple<c_1514_x, Integer>(blockpos1, j + 1));
            }
            if (i <= 64) continue;
            break;
        }
        return i > 0;
    }
}


