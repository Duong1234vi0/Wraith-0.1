/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.V_2454_J;
import lightning.product.Y_408_h;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.ClientboundChatPacket;
import lightning.product.q_1613_l;
import lightning.product.v_1669_V;

public class ScaffoldingBlockItem
extends v_1669_V {
    public ScaffoldingBlockItem(T_2915_h block, q_1613_l.n_1700_B builder) {
        super(block, builder);
    }

    @Override
    @Nullable
    public BlockPlaceContext J_1907_R(BlockPlaceContext context) {
        T_2915_h block;
        c_1514_x blockpos = context.getPos();
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos);
        if (!blockstate.n_1700_B(block = this.v_4262_N())) {
            return V_2454_J.n_1700_B(world, blockpos) == 7 ? null : context;
        }
        b_257_Y direction = context.hasSecondaryUseForPlayer() ? (context.isInside() ? context.getFace().u_1723_Y() : context.getFace()) : (context.getFace() == b_257_Y.J_1907_R ? context.getPlacementHorizontalFacing() : b_257_Y.J_1907_R);
        int i = 0;
        c_1514_x.n_1700_B blockpos$mutable = blockpos.toMutable().n_1700_B(direction);
        while (i < 7) {
            if (!world.Y_259_p && !b_4507_u.P_4830_p(blockpos$mutable)) {
                a_3913_L playerentity = context.getPlayer();
                int j = world.c_3005_b();
                if (!(playerentity instanceof B_4088_l) || blockpos$mutable.getY() < j) break;
                ClientboundChatPacket schatpacket = new ClientboundChatPacket(new F_2904_S("build.tooHigh", j).n_1700_B(D_4024_W.P_4830_p), Y_408_h.R_4764_Y, j_3341_s.J_1907_R);
                ((B_4088_l)playerentity).n_1700_B.n_1700_B(schatpacket);
                break;
            }
            blockstate = world.getBlockState(blockpos$mutable);
            if (!blockstate.n_1700_B(this.v_4262_N())) {
                if (!blockstate.n_1700_B(context)) break;
                return BlockPlaceContext.n_1700_B(context, blockpos$mutable, direction);
            }
            blockpos$mutable.n_1700_B(direction);
            if (!direction.h_1847_R().G_564_y()) continue;
            ++i;
        }
        return null;
    }

    @Override
    protected boolean P_1922_E() {
        return false;
    }
}


