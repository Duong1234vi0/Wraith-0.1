/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  it.unimi.dsi.fastutil.longs.Long2ByteLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap
 */
package net.optifine.util;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.longs.Long2ByteLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import java.util.Collection;
import java.util.List;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;
import net.optifine.render.RenderEnv;

public class BlockUtils {
    private static final ThreadLocal<RenderSideCacheKey> threadLocalKey = ThreadLocal.withInitial(() -> new RenderSideCacheKey(null, null, null));
    private static final ThreadLocal<Object2ByteLinkedOpenHashMap<RenderSideCacheKey>> threadLocalMap = ThreadLocal.withInitial(() -> {
        Object2ByteLinkedOpenHashMap<RenderSideCacheKey> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<RenderSideCacheKey>(200){

            protected void rehash(int p_rehash_1_) {
            }
        };
        object2bytelinkedopenhashmap.defaultReturnValue((byte)127);
        return object2bytelinkedopenhashmap;
    });

    public static boolean shouldSideBeRendered(K_4074_S blockStateIn, BlockGetter blockReaderIn, c_1514_x blockPosIn, b_257_Y facingIn, RenderEnv renderEnv) {
        c_1514_x blockpos = blockPosIn.offset(facingIn);
        K_4074_S blockstate = blockReaderIn.getBlockState(blockpos);
        if (blockstate.Q_2552_b()) {
            return false;
        }
        if (blockStateIn.n_1700_B(blockstate, facingIn)) {
            return false;
        }
        return blockstate.M_588_G() ? BlockUtils.shouldSideBeRenderedCached(blockStateIn, blockReaderIn, blockPosIn, facingIn, renderEnv, blockstate, blockpos) : true;
    }

    public static boolean shouldSideBeRenderedCached(K_4074_S blockStateIn, BlockGetter blockReaderIn, c_1514_x blockPosIn, b_257_Y facingIn, RenderEnv renderEnv, K_4074_S stateNeighbourIn, c_1514_x posNeighbourIn) {
        long i = (long)blockStateIn.Y_259_p() << 36 | (long)stateNeighbourIn.Y_259_p() << 4 | (long)facingIn.ordinal();
        Long2ByteLinkedOpenHashMap long2bytelinkedopenhashmap = renderEnv.getRenderSideMap();
        byte b0 = long2bytelinkedopenhashmap.getAndMoveToFirst(i);
        if (b0 != 0) {
            return b0 > 0;
        }
        s_1395_c voxelshape = blockStateIn.n_1700_B(blockReaderIn, blockPosIn, facingIn);
        s_1395_c voxelshape1 = stateNeighbourIn.n_1700_B(blockReaderIn, posNeighbourIn, facingIn.u_1723_Y());
        boolean flag = x_268_Y.R_4764_Y(voxelshape, voxelshape1, BooleanOp.P_1922_E);
        if (long2bytelinkedopenhashmap.size() > 400) {
            long2bytelinkedopenhashmap.removeLastByte();
        }
        long2bytelinkedopenhashmap.putAndMoveToFirst(i, (byte)(flag ? 1 : -1));
        return flag;
    }

    public static int getBlockId(T_2915_h block) {
        return V_3137_a.q_4610_l.n_1700_B(block);
    }

    public static T_2915_h getBlock(g_2336_b loc) {
        return !V_3137_a.q_4610_l.R_4764_Y(loc) ? null : V_3137_a.q_4610_l.n_1700_B(loc);
    }

    public static int getMetadata(K_4074_S blockState) {
        T_2915_h block = blockState.J_1907_R();
        Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
        ImmutableList<K_4074_S> list = statecontainer.n_1700_B();
        return list.indexOf(blockState);
    }

    public static int getMetadataCount(T_2915_h block) {
        Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
        ImmutableList<K_4074_S> list = statecontainer.n_1700_B();
        return list.size();
    }

    public static K_4074_S getBlockState(T_2915_h block, int metadata) {
        Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
        ImmutableList<K_4074_S> list = statecontainer.n_1700_B();
        return metadata >= 0 && metadata < list.size() ? (K_4074_S)list.get(metadata) : null;
    }

    public static List<K_4074_S> getBlockStates(T_2915_h block) {
        Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
        ImmutableList<K_4074_S> list = statecontainer.n_1700_B();
        return list;
    }

    public static boolean isFullCube(K_4074_S stateIn, BlockGetter blockReaderIn, c_1514_x posIn) {
        return stateIn.C_2741_M();
    }

    public static Collection<v_3760_Q> getProperties(K_4074_S blockState) {
        return blockState.k_2293_S();
    }

    public static final class RenderSideCacheKey {
        private K_4074_S blockState1;
        private K_4074_S blockState2;
        private b_257_Y facing;
        private int hashCode;

        private RenderSideCacheKey(K_4074_S blockState1In, K_4074_S blockState2In, b_257_Y facingIn) {
            this.blockState1 = blockState1In;
            this.blockState2 = blockState2In;
            this.facing = facingIn;
        }

        private void init(K_4074_S blockState1In, K_4074_S blockState2In, b_257_Y facingIn) {
            this.blockState1 = blockState1In;
            this.blockState2 = blockState2In;
            this.facing = facingIn;
            this.hashCode = 0;
        }

        public RenderSideCacheKey duplicate() {
            return new RenderSideCacheKey(this.blockState1, this.blockState2, this.facing);
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof RenderSideCacheKey)) {
                return false;
            }
            RenderSideCacheKey blockutils$rendersidecachekey = (RenderSideCacheKey)p_equals_1_;
            return this.blockState1 == blockutils$rendersidecachekey.blockState1 && this.blockState2 == blockutils$rendersidecachekey.blockState2 && this.facing == blockutils$rendersidecachekey.facing;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                this.hashCode = 31 * this.hashCode + this.blockState1.hashCode();
                this.hashCode = 31 * this.hashCode + this.blockState2.hashCode();
                this.hashCode = 31 * this.hashCode + this.facing.hashCode();
            }
            return this.hashCode;
        }
    }
}


