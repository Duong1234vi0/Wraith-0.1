/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.TorchBlock;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.DustParticleOptions;
import lightning.product.v_3760_Q;

public class RedstoneTorchBlock
extends TorchBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.multiplayerClientSuggestionProvider;
    private static final Map<BlockGetter, List<n_1700_B>> h_1847_R = new WeakHashMap<BlockGetter, List<n_1700_B>>();

    protected RedstoneTorchBlock(q_4293_E.P_1922_E properties) {
        super(properties, DustParticleOptions.n_1700_B);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, true));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        for (b_257_Y direction : b_257_Y.values()) {
            worldIn.J_1907_R(pos.offset(direction), this);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving) {
            for (b_257_Y direction : b_257_Y.values()) {
                worldIn.J_1907_R(pos.offset(direction), this);
            }
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p) != false && b_257_Y.J_1907_R != side ? 15 : 0;
    }

    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        return worldIn.J_1907_R(pos.down(), b_257_Y.n_1700_B);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        boolean flag = this.n_1700_B((b_4507_u)worldIn, pos, state);
        List<n_1700_B> list = h_1847_R.get(worldIn);
        while (list != null && !list.isEmpty() && worldIn.X_933_l() - list.get((int)0).J_1907_R > 60L) {
            list.remove(0);
        }
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            if (flag) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, false), 3);
                if (RedstoneTorchBlock.n_1700_B((b_4507_u)worldIn, pos, true)) {
                    worldIn.R_4764_Y(1502, pos, 0);
                    worldIn.Q_2552_b().n_1700_B(pos, worldIn.getBlockState(pos).J_1907_R(), 160);
                }
            }
        } else if (!flag && !RedstoneTorchBlock.n_1700_B((b_4507_u)worldIn, pos, false)) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 3);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (state.R_4764_Y(P_4830_p).booleanValue() == this.n_1700_B(worldIn, pos, state) && !worldIn.u_2550_I().J_1907_R(pos, this)) {
            worldIn.u_2550_I().n_1700_B(pos, this, 2);
        }
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return side == b_257_Y.n_1700_B ? blockState.J_1907_R(blockAccess, pos, side) : 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            double d0 = (double)pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
            double d1 = (double)pos.getY() + 0.7 + (rand.nextDouble() - 0.5) * 0.2;
            double d2 = (double)pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
            worldIn.n_1700_B(this.t_1786_h, d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    private static boolean n_1700_B(b_4507_u world, c_1514_x worldIn, boolean pos) {
        List list = h_1847_R.computeIfAbsent(world, reader -> Lists.newArrayList());
        if (pos) {
            list.add(new n_1700_B(worldIn.toImmutable(), world.X_933_l()));
        }
        int i = 0;
        for (int j = 0; j < list.size(); ++j) {
            n_1700_B redstonetorchblock$toggle = (n_1700_B)list.get(j);
            if (!redstonetorchblock$toggle.n_1700_B.equals(worldIn) || ++i < 8) continue;
            return true;
        }
        return false;
    }

    public static class n_1700_B {
        private final c_1514_x n_1700_B;
        private final long J_1907_R;

        public n_1700_B(c_1514_x pos, long time) {
            this.n_1700_B = pos;
            this.J_1907_R = time;
        }
    }
}


