/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.shorts.Short2BooleanMap
 *  it.unimi.dsi.fastutil.shorts.Short2BooleanOpenHashMap
 *  it.unimi.dsi.fastutil.shorts.Short2ObjectMap
 *  it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import it.unimi.dsi.fastutil.shorts.Short2BooleanMap;
import it.unimi.dsi.fastutil.shorts.Short2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.util.EnumMap;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.S_1431_H;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.g_88_D;
import lightning.product.BlockTags;
import lightning.product.s_1395_c;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;
import lightning.product.z_3539_x;

public abstract class U_4243_e
extends Fluid {
    public static final U_1266_O n_1700_B = BlockStateProperties.t_148_a;
    public static final g_88_D J_1907_R = BlockStateProperties.h_4320_q;
    private static final ThreadLocal<Object2ByteLinkedOpenHashMap<T_2915_h.n_1700_B>> P_1922_E = ThreadLocal.withInitial(() -> {
        Object2ByteLinkedOpenHashMap<T_2915_h.n_1700_B> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<T_2915_h.n_1700_B>(200){

            protected void rehash(int p_rehash_1_) {
            }
        };
        object2bytelinkedopenhashmap.defaultReturnValue((byte)127);
        return object2bytelinkedopenhashmap;
    });
    private final Map<FluidState, s_1395_c> u_1723_Y = Maps.newIdentityHashMap();

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<Fluid, FluidState> builder) {
        builder.n_1700_B(new v_3760_Q[]{n_1700_B});
    }

    @Override
    public e_2866_D n_1700_B(BlockGetter blockReader, c_1514_x pos, FluidState fluidState) {
        double d0 = 0.0;
        double d1 = 0.0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            blockpos$mutable.n_1700_B(pos, direction);
            FluidState fluidstate = blockReader.getFluidState(blockpos$mutable);
            if (!this.v_4262_N(fluidstate)) continue;
            float f = fluidstate.G_564_y();
            float f1 = 0.0f;
            if (f == 0.0f) {
                z_3539_x blockpos;
                FluidState fluidstate1;
                if (!blockReader.getBlockState(blockpos$mutable).R_4764_Y().R_4764_Y() && this.v_4262_N(fluidstate1 = blockReader.getFluidState((c_1514_x)(blockpos = blockpos$mutable.down()))) && (f = fluidstate1.G_564_y()) > 0.0f) {
                    f1 = fluidState.G_564_y() - (f - 0.8888889f);
                }
            } else if (f > 0.0f) {
                f1 = fluidState.G_564_y() - f;
            }
            if (f1 == 0.0f) continue;
            d0 += (double)((float)direction.t_148_a() * f1);
            d1 += (double)((float)direction.u_2550_I() * f1);
        }
        e_2866_D vector3d = new e_2866_D(d0, 0.0, d1);
        if (fluidState.R_4764_Y(n_1700_B).booleanValue()) {
            for (b_257_Y direction1 : b_257_Y.R_4764_Y.n_1700_B) {
                blockpos$mutable.n_1700_B(pos, direction1);
                if (!this.n_1700_B(blockReader, (c_1514_x)blockpos$mutable, direction1) && !this.n_1700_B(blockReader, (c_1514_x)blockpos$mutable.up(), direction1)) continue;
                vector3d = vector3d.G_564_y().J_1907_R(0.0, -6.0, 0.0);
                break;
            }
        }
        return vector3d.G_564_y();
    }

    private boolean v_4262_N(FluidState state) {
        return state.R_4764_Y() || state.n_1700_B().n_1700_B(this);
    }

    protected boolean n_1700_B(BlockGetter worldIn, c_1514_x neighborPos, b_257_Y side) {
        K_4074_S blockstate = worldIn.getBlockState(neighborPos);
        FluidState fluidstate = worldIn.getFluidState(neighborPos);
        if (fluidstate.n_1700_B().n_1700_B(this)) {
            return false;
        }
        if (side == b_257_Y.J_1907_R) {
            return true;
        }
        return blockstate.R_4764_Y() == Material.e_4240_b ? false : blockstate.G_564_y(worldIn, neighborPos, side);
    }

    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos, FluidState stateIn) {
        if (!stateIn.R_4764_Y()) {
            K_4074_S blockstate = worldIn.getBlockState(pos);
            c_1514_x blockpos = pos.down();
            K_4074_S blockstate1 = worldIn.getBlockState(blockpos);
            FluidState fluidstate = this.n_1700_B((T_1316_M)worldIn, blockpos, blockstate1);
            if (this.n_1700_B((BlockGetter)worldIn, pos, blockstate, b_257_Y.n_1700_B, blockpos, blockstate1, worldIn.getFluidState(blockpos), fluidstate.n_1700_B())) {
                this.n_1700_B(worldIn, blockpos, blockstate1, b_257_Y.n_1700_B, fluidstate);
                if (this.n_1700_B(worldIn, pos) >= 3) {
                    this.n_1700_B(worldIn, pos, stateIn, blockstate);
                }
            } else if (stateIn.J_1907_R() || !this.n_1700_B(worldIn, fluidstate.n_1700_B(), pos, blockstate, blockpos, blockstate1)) {
                this.n_1700_B(worldIn, pos, stateIn, blockstate);
            }
        }
    }

    private void n_1700_B(LevelAccessor p_207937_1_, c_1514_x p_207937_2_, FluidState p_207937_3_, K_4074_S p_207937_4_) {
        int i = p_207937_3_.P_1922_E() - this.R_4764_Y(p_207937_1_);
        if (p_207937_3_.R_4764_Y(n_1700_B).booleanValue()) {
            i = 7;
        }
        if (i > 0) {
            Map<b_257_Y, FluidState> map = this.J_1907_R(p_207937_1_, p_207937_2_, p_207937_4_);
            for (Map.Entry<b_257_Y, FluidState> entry : map.entrySet()) {
                K_4074_S blockstate;
                b_257_Y direction = entry.getKey();
                FluidState fluidstate = entry.getValue();
                c_1514_x blockpos = p_207937_2_.offset(direction);
                if (!this.n_1700_B((BlockGetter)p_207937_1_, p_207937_2_, p_207937_4_, direction, blockpos, blockstate = p_207937_1_.getBlockState(blockpos), p_207937_1_.getFluidState(blockpos), fluidstate.n_1700_B())) continue;
                this.n_1700_B(p_207937_1_, blockpos, blockstate, direction, fluidstate);
            }
        }
    }

    protected FluidState n_1700_B(T_1316_M worldIn, c_1514_x pos, K_4074_S blockStateIn) {
        c_1514_x blockpos1;
        K_4074_S blockstate2;
        FluidState fluidstate2;
        int i = 0;
        int j = 0;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            FluidState fluidstate = blockstate.P_4830_p();
            if (!fluidstate.n_1700_B().n_1700_B(this) || !this.n_1700_B(direction, worldIn, pos, blockStateIn, blockpos, blockstate)) continue;
            if (fluidstate.J_1907_R()) {
                ++j;
            }
            i = Math.max(i, fluidstate.P_1922_E());
        }
        if (this.u_1723_Y() && j >= 2) {
            K_4074_S blockstate1 = worldIn.getBlockState(pos.down());
            FluidState fluidstate1 = blockstate1.P_4830_p();
            if (blockstate1.R_4764_Y().J_1907_R() || this.w_1484_f(fluidstate1)) {
                return this.n_1700_B(false);
            }
        }
        if (!(fluidstate2 = (blockstate2 = worldIn.getBlockState(blockpos1 = pos.up())).P_4830_p()).R_4764_Y() && fluidstate2.n_1700_B().n_1700_B(this) && this.n_1700_B(b_257_Y.J_1907_R, worldIn, pos, blockStateIn, blockpos1, blockstate2)) {
            return this.n_1700_B(8, true);
        }
        int k = i - this.R_4764_Y(worldIn);
        return k <= 0 ? Fluids.n_1700_B.w_1484_f() : this.n_1700_B(k, false);
    }

    private boolean n_1700_B(b_257_Y p_212751_1_, BlockGetter p_212751_2_, c_1514_x p_212751_3_, K_4074_S p_212751_4_, c_1514_x p_212751_5_, K_4074_S p_212751_6_) {
        s_1395_c voxelshape;
        s_1395_c voxelshape1;
        boolean flag;
        T_2915_h.n_1700_B block$rendersidecachekey;
        Object2ByteLinkedOpenHashMap<T_2915_h.n_1700_B> object2bytelinkedopenhashmap = !p_212751_4_.J_1907_R().w_1457_N() && !p_212751_6_.J_1907_R().w_1457_N() ? P_1922_E.get() : null;
        if (object2bytelinkedopenhashmap != null) {
            block$rendersidecachekey = new T_2915_h.n_1700_B(p_212751_4_, p_212751_6_, p_212751_1_);
            byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst((Object)block$rendersidecachekey);
            if (b0 != 127) {
                return b0 != 0;
            }
        } else {
            block$rendersidecachekey = null;
        }
        boolean bl = flag = !x_268_Y.J_1907_R(voxelshape1 = p_212751_4_.u_2550_I(p_212751_2_, p_212751_3_), voxelshape = p_212751_6_.u_2550_I(p_212751_2_, p_212751_5_), p_212751_1_);
        if (object2bytelinkedopenhashmap != null) {
            if (object2bytelinkedopenhashmap.size() == 200) {
                object2bytelinkedopenhashmap.removeLastByte();
            }
            object2bytelinkedopenhashmap.putAndMoveToFirst((Object)block$rendersidecachekey, (byte)(flag ? 1 : 0));
        }
        return flag;
    }

    public abstract Fluid G_564_y();

    public FluidState n_1700_B(int level, boolean falling) {
        return (FluidState)((FluidState)this.G_564_y().w_1484_f().n_1700_B(J_1907_R, level)).n_1700_B(n_1700_B, falling);
    }

    public abstract Fluid P_1922_E();

    public FluidState n_1700_B(boolean falling) {
        return (FluidState)this.P_1922_E().w_1484_f().n_1700_B(n_1700_B, falling);
    }

    protected abstract boolean u_1723_Y();

    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S blockStateIn, b_257_Y direction, FluidState fluidStateIn) {
        if (blockStateIn.J_1907_R() instanceof LiquidBlockContainer) {
            ((LiquidBlockContainer)((Object)blockStateIn.J_1907_R())).n_1700_B(worldIn, pos, blockStateIn, fluidStateIn);
        } else {
            if (!blockStateIn.v_4262_N()) {
                this.n_1700_B(worldIn, pos, blockStateIn);
            }
            worldIn.n_1700_B(pos, fluidStateIn.v_4262_N(), 3);
        }
    }

    protected abstract void n_1700_B(LevelAccessor var1, c_1514_x var2, K_4074_S var3);

    private static short n_1700_B(c_1514_x p_212752_0_, c_1514_x p_212752_1_) {
        int i = p_212752_1_.getX() - p_212752_0_.getX();
        int j = p_212752_1_.getZ() - p_212752_0_.getZ();
        return (short)((i + 128 & 0xFF) << 8 | j + 128 & 0xFF);
    }

    protected int n_1700_B(T_1316_M p_205571_1_, c_1514_x p_205571_2_, int p_205571_3_, b_257_Y p_205571_4_, K_4074_S p_205571_5_, c_1514_x p_205571_6_, Short2ObjectMap<Pair<K_4074_S, FluidState>> p_205571_7_, Short2BooleanMap p_205571_8_) {
        int i = 1000;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            int j;
            if (direction == p_205571_4_) continue;
            c_1514_x blockpos = p_205571_2_.offset(direction);
            short short1 = U_4243_e.n_1700_B(p_205571_6_, blockpos);
            Pair pair = (Pair)p_205571_7_.computeIfAbsent(short1, p_212748_2_ -> {
                K_4074_S blockstate1 = p_205571_1_.getBlockState(blockpos);
                return Pair.of((Object)blockstate1, (Object)blockstate1.P_4830_p());
            });
            K_4074_S blockstate = (K_4074_S)pair.getFirst();
            FluidState fluidstate = (FluidState)pair.getSecond();
            if (!this.n_1700_B((BlockGetter)p_205571_1_, this.G_564_y(), p_205571_2_, p_205571_5_, direction, blockpos, blockstate, fluidstate)) continue;
            boolean flag = p_205571_8_.computeIfAbsent(short1, p_212749_4_ -> {
                c_1514_x blockpos1 = blockpos.down();
                K_4074_S blockstate1 = p_205571_1_.getBlockState(blockpos1);
                return this.n_1700_B(p_205571_1_, this.G_564_y(), blockpos, blockstate, blockpos1, blockstate1);
            });
            if (flag) {
                return p_205571_3_;
            }
            if (p_205571_3_ >= this.J_1907_R(p_205571_1_) || (j = this.n_1700_B(p_205571_1_, blockpos, p_205571_3_ + 1, direction.u_1723_Y(), blockstate, p_205571_6_, p_205571_7_, p_205571_8_)) >= i) continue;
            i = j;
        }
        return i;
    }

    private boolean n_1700_B(BlockGetter p_211759_1_, Fluid p_211759_2_, c_1514_x p_211759_3_, K_4074_S p_211759_4_, c_1514_x p_211759_5_, K_4074_S p_211759_6_) {
        if (!this.n_1700_B(b_257_Y.n_1700_B, p_211759_1_, p_211759_3_, p_211759_4_, p_211759_5_, p_211759_6_)) {
            return false;
        }
        return p_211759_6_.P_4830_p().n_1700_B().n_1700_B(this) ? true : this.n_1700_B(p_211759_1_, p_211759_5_, p_211759_6_, p_211759_2_);
    }

    private boolean n_1700_B(BlockGetter p_211760_1_, Fluid p_211760_2_, c_1514_x p_211760_3_, K_4074_S p_211760_4_, b_257_Y p_211760_5_, c_1514_x p_211760_6_, K_4074_S p_211760_7_, FluidState p_211760_8_) {
        return !this.w_1484_f(p_211760_8_) && this.n_1700_B(p_211760_5_, p_211760_1_, p_211760_3_, p_211760_4_, p_211760_6_, p_211760_7_) && this.n_1700_B(p_211760_1_, p_211760_6_, p_211760_7_, p_211760_2_);
    }

    private boolean w_1484_f(FluidState stateIn) {
        return stateIn.n_1700_B().n_1700_B(this) && stateIn.J_1907_R();
    }

    protected abstract int J_1907_R(T_1316_M var1);

    private int n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        int i = 0;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction);
            FluidState fluidstate = worldIn.getFluidState(blockpos);
            if (!this.w_1484_f(fluidstate)) continue;
            ++i;
        }
        return i;
    }

    protected Map<b_257_Y, FluidState> J_1907_R(T_1316_M p_205572_1_, c_1514_x p_205572_2_, K_4074_S p_205572_3_) {
        int i = 1000;
        EnumMap map = Maps.newEnumMap(b_257_Y.class);
        Short2ObjectOpenHashMap short2objectmap = new Short2ObjectOpenHashMap();
        Short2BooleanOpenHashMap short2booleanmap = new Short2BooleanOpenHashMap();
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = p_205572_2_.offset(direction);
            short short1 = U_4243_e.n_1700_B(p_205572_2_, blockpos);
            Pair pair = (Pair)short2objectmap.computeIfAbsent(short1, p_212755_2_ -> {
                K_4074_S blockstate1 = p_205572_1_.getBlockState(blockpos);
                return Pair.of((Object)blockstate1, (Object)blockstate1.P_4830_p());
            });
            K_4074_S blockstate = (K_4074_S)pair.getFirst();
            FluidState fluidstate = (FluidState)pair.getSecond();
            FluidState fluidstate1 = this.n_1700_B(p_205572_1_, blockpos, blockstate);
            if (!this.n_1700_B((BlockGetter)p_205572_1_, fluidstate1.n_1700_B(), p_205572_2_, p_205572_3_, direction, blockpos, blockstate, fluidstate)) continue;
            c_1514_x blockpos1 = blockpos.down();
            boolean flag = short2booleanmap.computeIfAbsent(short1, p_212753_5_ -> {
                K_4074_S blockstate1 = p_205572_1_.getBlockState(blockpos1);
                return this.n_1700_B(p_205572_1_, this.G_564_y(), blockpos, blockstate, blockpos1, blockstate1);
            });
            int j = flag ? 0 : this.n_1700_B(p_205572_1_, blockpos, 1, direction.u_1723_Y(), blockstate, p_205572_2_, (Short2ObjectMap<Pair<K_4074_S, FluidState>>)short2objectmap, (Short2BooleanMap)short2booleanmap);
            if (j < i) {
                map.clear();
            }
            if (j > i) continue;
            map.put(direction, fluidstate1);
            i = j;
        }
        return map;
    }

    private boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, Fluid fluidIn) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof LiquidBlockContainer) {
            return ((LiquidBlockContainer)((Object)block)).n_1700_B(worldIn, pos, state, fluidIn);
        }
        if (!(block instanceof S_1431_H) && !block.n_1700_B(BlockTags.O_508_d) && block != a_3742_W.L_3570_A && block != a_3742_W.l_3609_d && block != a_3742_W.S_4325_V) {
            Material material = state.R_4764_Y();
            if (material != Material.R_4764_Y && material != Material.J_1907_R && material != Material.u_1723_Y && material != Material.t_148_a) {
                return !material.R_4764_Y();
            }
            return false;
        }
        return false;
    }

    protected boolean n_1700_B(BlockGetter worldIn, c_1514_x fromPos, K_4074_S fromBlockState, b_257_Y direction, c_1514_x toPos, K_4074_S toBlockState, FluidState toFluidState, Fluid fluidIn) {
        return toFluidState.n_1700_B(worldIn, toPos, fluidIn, direction) && this.n_1700_B(direction, worldIn, fromPos, fromBlockState, toPos, toBlockState) && this.n_1700_B(worldIn, toPos, toBlockState, fluidIn);
    }

    protected abstract int R_4764_Y(T_1316_M var1);

    protected int n_1700_B(b_4507_u world, c_1514_x pos, FluidState p_215667_3_, FluidState p_215667_4_) {
        return this.n_1700_B(world);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, FluidState state) {
        if (!state.J_1907_R()) {
            FluidState fluidstate = this.n_1700_B((T_1316_M)worldIn, pos, worldIn.getBlockState(pos));
            int i = this.n_1700_B(worldIn, pos, state, fluidstate);
            if (fluidstate.R_4764_Y()) {
                state = fluidstate;
                worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
            } else if (!fluidstate.equals(state)) {
                state = fluidstate;
                K_4074_S blockstate = fluidstate.v_4262_N();
                worldIn.n_1700_B(pos, blockstate, 2);
                worldIn.M_588_G().n_1700_B(pos, fluidstate.n_1700_B(), i);
                worldIn.J_1907_R(pos, blockstate.J_1907_R());
            }
        }
        this.n_1700_B((LevelAccessor)worldIn, pos, state);
    }

    protected static int P_1922_E(FluidState state) {
        return state.J_1907_R() ? 0 : 8 - Math.min(state.P_1922_E(), 8) + (state.R_4764_Y(n_1700_B) != false ? 8 : 0);
    }

    private static boolean R_4764_Y(FluidState p_215666_0_, BlockGetter p_215666_1_, c_1514_x p_215666_2_) {
        return p_215666_0_.n_1700_B().n_1700_B(p_215666_1_.getFluidState(p_215666_2_.up()).n_1700_B());
    }

    @Override
    public float n_1700_B(FluidState p_215662_1_, BlockGetter p_215662_2_, c_1514_x p_215662_3_) {
        return U_4243_e.R_4764_Y(p_215662_1_, p_215662_2_, p_215662_3_) ? 1.0f : p_215662_1_.G_564_y();
    }

    @Override
    public float n_1700_B(FluidState p_223407_1_) {
        return (float)p_223407_1_.P_1922_E() / 9.0f;
    }

    @Override
    public s_1395_c J_1907_R(FluidState p_215664_1_, BlockGetter p_215664_2_, c_1514_x p_215664_3_) {
        return p_215664_1_.P_1922_E() == 9 && U_4243_e.R_4764_Y(p_215664_1_, p_215664_2_, p_215664_3_) ? x_268_Y.J_1907_R() : this.u_1723_Y.computeIfAbsent(p_215664_1_, p_215668_2_ -> x_268_Y.n_1700_B(0.0, 0.0, 0.0, 1.0, p_215668_2_.n_1700_B(p_215664_2_, p_215664_3_), 1.0));
    }
}


