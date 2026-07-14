/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.J_3017_d;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.S_3458_C;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.FluidState;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.l_3848_Y;
import lightning.product.q_4099_E;
import lightning.product.t_693_s;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public abstract class E_3771_B {
    protected static final K_4074_S P_4830_p = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();
    protected BoundingBox h_1847_R;
    @Nullable
    private b_257_Y n_1700_B;
    private q_4099_E J_1907_R;
    private W_2163_m R_4764_Y;
    protected int Q_4569_t;
    private final StructurePieceType G_564_y;
    private static final Set<T_2915_h> P_1922_E = ImmutableSet.builder().add((Object)a_3742_W.d_4500_Q).add((Object)a_3742_W.o_2341_D).add((Object)a_3742_W.C_1269_X).add((Object)a_3742_W.h_2848_I).add((Object)a_3742_W.AutoFish).add((Object)a_3742_W.AutoPilot).add((Object)a_3742_W.AutoLes).add((Object)a_3742_W.AutoJoiner).add((Object)a_3742_W.AutoLeave).add((Object)a_3742_W.L_3570_A).add((Object)a_3742_W.Z_4720_K).build();

    protected E_3771_B(StructurePieceType structurePieceTypeIn, int componentTypeIn) {
        this.G_564_y = structurePieceTypeIn;
        this.Q_4569_t = componentTypeIn;
    }

    public E_3771_B(StructurePieceType structurePierceTypeIn, U_2912_j nbt) {
        this(structurePierceTypeIn, nbt.w_1484_f("GD"));
        int i;
        if (nbt.P_1922_E("BB")) {
            this.h_1847_R = new BoundingBox(nbt.h_1847_R("BB"));
        }
        this.n_1700_B((i = nbt.w_1484_f("O")) == -1 ? null : b_257_Y.J_1907_R(i));
    }

    public final U_2912_j u_1723_Y() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("id", V_3137_a.RealmsWorldResetDto.J_1907_R(this.s_956_w()).toString());
        compoundnbt.n_1700_B("BB", this.h_1847_R.w_1484_f());
        b_257_Y direction = this.t_148_a();
        compoundnbt.J_1907_R("O", direction == null ? -1 : direction.G_564_y());
        compoundnbt.J_1907_R("GD", this.Q_4569_t);
        this.n_1700_B(compoundnbt);
        return compoundnbt;
    }

    protected abstract void n_1700_B(U_2912_j var1);

    public void n_1700_B(E_3771_B componentIn, List<E_3771_B> listIn, Random rand) {
    }

    public abstract boolean n_1700_B(WorldGenLevel var1, J_3017_d var2, z_1753_f var3, Random var4, BoundingBox var5, Y_1387_d var6, c_1514_x var7);

    public BoundingBox v_4262_N() {
        return this.h_1847_R;
    }

    public int w_1484_f() {
        return this.Q_4569_t;
    }

    public boolean n_1700_B(Y_1387_d p_214810_1_, int p_214810_2_) {
        int i = p_214810_1_.J_1907_R << 4;
        int j = p_214810_1_.R_4764_Y << 4;
        return this.h_1847_R.n_1700_B(i - p_214810_2_, j - p_214810_2_, i + 15 + p_214810_2_, j + 15 + p_214810_2_);
    }

    public static E_3771_B n_1700_B(List<E_3771_B> listIn, BoundingBox boundingboxIn) {
        for (E_3771_B structurepiece : listIn) {
            if (structurepiece.v_4262_N() == null || !structurepiece.v_4262_N().n_1700_B(boundingboxIn)) continue;
            return structurepiece;
        }
        return null;
    }

    protected boolean n_1700_B(BlockGetter worldIn, BoundingBox boundingboxIn) {
        int i = Math.max(this.h_1847_R.n_1700_B - 1, boundingboxIn.n_1700_B);
        int j = Math.max(this.h_1847_R.J_1907_R - 1, boundingboxIn.J_1907_R);
        int k = Math.max(this.h_1847_R.R_4764_Y - 1, boundingboxIn.R_4764_Y);
        int l = Math.min(this.h_1847_R.G_564_y + 1, boundingboxIn.G_564_y);
        int i1 = Math.min(this.h_1847_R.P_1922_E + 1, boundingboxIn.P_1922_E);
        int j1 = Math.min(this.h_1847_R.u_1723_Y + 1, boundingboxIn.u_1723_Y);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k1 = i; k1 <= l; ++k1) {
            for (int l1 = k; l1 <= j1; ++l1) {
                if (worldIn.getBlockState(blockpos$mutable.n_1700_B(k1, j, l1)).R_4764_Y().n_1700_B()) {
                    return true;
                }
                if (!worldIn.getBlockState(blockpos$mutable.n_1700_B(k1, i1, l1)).R_4764_Y().n_1700_B()) continue;
                return true;
            }
        }
        for (int i2 = i; i2 <= l; ++i2) {
            for (int k2 = j; k2 <= i1; ++k2) {
                if (worldIn.getBlockState(blockpos$mutable.n_1700_B(i2, k2, k)).R_4764_Y().n_1700_B()) {
                    return true;
                }
                if (!worldIn.getBlockState(blockpos$mutable.n_1700_B(i2, k2, j1)).R_4764_Y().n_1700_B()) continue;
                return true;
            }
        }
        for (int j2 = k; j2 <= j1; ++j2) {
            for (int l2 = j; l2 <= i1; ++l2) {
                if (worldIn.getBlockState(blockpos$mutable.n_1700_B(i, l2, j2)).R_4764_Y().n_1700_B()) {
                    return true;
                }
                if (!worldIn.getBlockState(blockpos$mutable.n_1700_B(l, l2, j2)).R_4764_Y().n_1700_B()) continue;
                return true;
            }
        }
        return false;
    }

    protected int n_1700_B(int x, int z) {
        b_257_Y direction = this.t_148_a();
        if (direction == null) {
            return x;
        }
        switch (direction) {
            case R_4764_Y: 
            case G_564_y: {
                return this.h_1847_R.n_1700_B + x;
            }
            case P_1922_E: {
                return this.h_1847_R.G_564_y - z;
            }
            case u_1723_Y: {
                return this.h_1847_R.n_1700_B + z;
            }
        }
        return x;
    }

    protected int n_1700_B(int y) {
        return this.t_148_a() == null ? y : y + this.h_1847_R.J_1907_R;
    }

    protected int J_1907_R(int x, int z) {
        b_257_Y direction = this.t_148_a();
        if (direction == null) {
            return z;
        }
        switch (direction) {
            case R_4764_Y: {
                return this.h_1847_R.u_1723_Y - z;
            }
            case G_564_y: {
                return this.h_1847_R.R_4764_Y + z;
            }
            case P_1922_E: 
            case u_1723_Y: {
                return this.h_1847_R.R_4764_Y + x;
            }
        }
        return z;
    }

    protected void n_1700_B(WorldGenLevel worldIn, K_4074_S blockstateIn, int x, int y, int z, BoundingBox boundingboxIn) {
        c_1514_x blockpos = new c_1514_x(this.n_1700_B(x, z), this.n_1700_B(y), this.J_1907_R(x, z));
        if (boundingboxIn.J_1907_R(blockpos)) {
            if (this.J_1907_R != q_4099_E.n_1700_B) {
                blockstateIn = blockstateIn.n_1700_B(this.J_1907_R);
            }
            if (this.R_4764_Y != W_2163_m.n_1700_B) {
                blockstateIn = blockstateIn.n_1700_B(this.R_4764_Y);
            }
            worldIn.n_1700_B(blockpos, blockstateIn, 2);
            FluidState fluidstate = worldIn.getFluidState(blockpos);
            if (!fluidstate.R_4764_Y()) {
                worldIn.M_588_G().n_1700_B(blockpos, fluidstate.n_1700_B(), 0);
            }
            if (P_1922_E.contains(blockstateIn.J_1907_R())) {
                worldIn.t_148_a(blockpos).P_1922_E(blockpos);
            }
        }
    }

    protected K_4074_S n_1700_B(BlockGetter worldIn, int x, int y, int z, BoundingBox boundingboxIn) {
        int k;
        int j;
        int i = this.n_1700_B(x, z);
        c_1514_x blockpos = new c_1514_x(i, j = this.n_1700_B(y), k = this.J_1907_R(x, z));
        return !boundingboxIn.J_1907_R(blockpos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : worldIn.getBlockState(blockpos);
    }

    protected boolean n_1700_B(T_1316_M worldIn, int x, int y, int z, BoundingBox boundingboxIn) {
        int k;
        int j;
        int i = this.n_1700_B(x, z);
        c_1514_x blockpos = new c_1514_x(i, j = this.n_1700_B(y + 1), k = this.J_1907_R(x, z));
        if (!boundingboxIn.J_1907_R(blockpos)) {
            return false;
        }
        return j < worldIn.n_1700_B(z_2963_s.n_1700_B.R_4764_Y, i, k);
    }

    protected void J_1907_R(WorldGenLevel worldIn, BoundingBox structurebb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        for (int i = minY; i <= maxY; ++i) {
            for (int j = minX; j <= maxX; ++j) {
                for (int k = minZ; k <= maxZ; ++k) {
                    this.n_1700_B(worldIn, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), j, i, k, structurebb);
                }
            }
        }
    }

    protected void n_1700_B(WorldGenLevel worldIn, BoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, K_4074_S boundaryBlockState, K_4074_S insideBlockState, boolean existingOnly) {
        for (int i = yMin; i <= yMax; ++i) {
            for (int j = xMin; j <= xMax; ++j) {
                for (int k = zMin; k <= zMax; ++k) {
                    if (existingOnly && this.n_1700_B((BlockGetter)worldIn, j, i, k, boundingboxIn).v_4262_N()) continue;
                    if (i != yMin && i != yMax && j != xMin && j != xMax && k != zMin && k != zMax) {
                        this.n_1700_B(worldIn, insideBlockState, j, i, k, boundingboxIn);
                        continue;
                    }
                    this.n_1700_B(worldIn, boundaryBlockState, j, i, k, boundingboxIn);
                }
            }
        }
    }

    protected void n_1700_B(WorldGenLevel worldIn, BoundingBox boundingboxIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean alwaysReplace, Random rand, n_1700_B blockselector) {
        for (int i = minY; i <= maxY; ++i) {
            for (int j = minX; j <= maxX; ++j) {
                for (int k = minZ; k <= maxZ; ++k) {
                    if (alwaysReplace && this.n_1700_B((BlockGetter)worldIn, j, i, k, boundingboxIn).v_4262_N()) continue;
                    blockselector.n_1700_B(rand, j, i, k, i == minY || i == maxY || j == minX || j == maxX || k == minZ || k == maxZ);
                    this.n_1700_B(worldIn, blockselector.n_1700_B(), j, i, k, boundingboxIn);
                }
            }
        }
    }

    protected void n_1700_B(WorldGenLevel worldIn, BoundingBox sbb, Random rand, float chance, int x1, int y1, int z1, int x2, int y2, int z2, K_4074_S edgeState, K_4074_S state, boolean requireNonAir, boolean requiredSkylight) {
        for (int i = y1; i <= y2; ++i) {
            for (int j = x1; j <= x2; ++j) {
                for (int k = z1; k <= z2; ++k) {
                    if (rand.nextFloat() > chance || requireNonAir && this.n_1700_B((BlockGetter)worldIn, j, i, k, sbb).v_4262_N() || requiredSkylight && !this.n_1700_B(worldIn, j, i, k, sbb)) continue;
                    if (i != y1 && i != y2 && j != x1 && j != x2 && k != z1 && k != z2) {
                        this.n_1700_B(worldIn, state, j, i, k, sbb);
                        continue;
                    }
                    this.n_1700_B(worldIn, edgeState, j, i, k, sbb);
                }
            }
        }
    }

    protected void n_1700_B(WorldGenLevel worldIn, BoundingBox boundingboxIn, Random rand, float chance, int x, int y, int z, K_4074_S blockstateIn) {
        if (rand.nextFloat() < chance) {
            this.n_1700_B(worldIn, blockstateIn, x, y, z, boundingboxIn);
        }
    }

    protected void n_1700_B(WorldGenLevel worldIn, BoundingBox boundingboxIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, K_4074_S blockstateIn, boolean excludeAir) {
        float f = maxX - minX + 1;
        float f1 = maxY - minY + 1;
        float f2 = maxZ - minZ + 1;
        float f3 = (float)minX + f / 2.0f;
        float f4 = (float)minZ + f2 / 2.0f;
        for (int i = minY; i <= maxY; ++i) {
            float f5 = (float)(i - minY) / f1;
            for (int j = minX; j <= maxX; ++j) {
                float f6 = ((float)j - f3) / (f * 0.5f);
                for (int k = minZ; k <= maxZ; ++k) {
                    float f8;
                    float f7 = ((float)k - f4) / (f2 * 0.5f);
                    if (excludeAir && this.n_1700_B((BlockGetter)worldIn, j, i, k, boundingboxIn).v_4262_N() || !((f8 = f6 * f6 + f5 * f5 + f7 * f7) <= 1.05f)) continue;
                    this.n_1700_B(worldIn, blockstateIn, j, i, k, boundingboxIn);
                }
            }
        }
    }

    protected void J_1907_R(WorldGenLevel worldIn, K_4074_S blockstateIn, int x, int y, int z, BoundingBox boundingboxIn) {
        int k;
        int j;
        int i = this.n_1700_B(x, z);
        if (boundingboxIn.J_1907_R(new c_1514_x(i, j = this.n_1700_B(y), k = this.J_1907_R(x, z)))) {
            while ((worldIn.u_1723_Y(new c_1514_x(i, j, k)) || worldIn.getBlockState(new c_1514_x(i, j, k)).R_4764_Y().n_1700_B()) && j > 1) {
                worldIn.n_1700_B(new c_1514_x(i, j, k), blockstateIn, 2);
                --j;
            }
        }
    }

    protected boolean n_1700_B(WorldGenLevel worldIn, BoundingBox structurebb, Random randomIn, int x, int y, int z, g_2336_b loot) {
        c_1514_x blockpos = new c_1514_x(this.n_1700_B(x, z), this.n_1700_B(y), this.J_1907_R(x, z));
        return this.n_1700_B((ServerLevelAccessor)worldIn, structurebb, randomIn, blockpos, loot, (K_4074_S)null);
    }

    public static K_4074_S n_1700_B(BlockGetter worldIn, c_1514_x posIn, K_4074_S blockStateIn) {
        b_257_Y direction = null;
        for (b_257_Y direction1 : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = posIn.offset(direction1);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (blockstate.n_1700_B(a_3742_W.L_1362_X)) {
                return blockStateIn;
            }
            if (!blockstate.t_148_a(worldIn, blockpos)) continue;
            if (direction != null) {
                direction = null;
                break;
            }
            direction = direction1;
        }
        if (direction != null) {
            return (K_4074_S)blockStateIn.n_1700_B(HorizontalDirectionalBlock.w_612_n, direction.u_1723_Y());
        }
        b_257_Y direction2 = blockStateIn.R_4764_Y(HorizontalDirectionalBlock.w_612_n);
        c_1514_x blockpos1 = posIn.offset(direction2);
        if (worldIn.getBlockState(blockpos1).t_148_a(worldIn, blockpos1)) {
            direction2 = direction2.u_1723_Y();
            blockpos1 = posIn.offset(direction2);
        }
        if (worldIn.getBlockState(blockpos1).t_148_a(worldIn, blockpos1)) {
            direction2 = direction2.v_4262_N();
            blockpos1 = posIn.offset(direction2);
        }
        if (worldIn.getBlockState(blockpos1).t_148_a(worldIn, blockpos1)) {
            direction2 = direction2.u_1723_Y();
            posIn.offset(direction2);
        }
        return (K_4074_S)blockStateIn.n_1700_B(HorizontalDirectionalBlock.w_612_n, direction2);
    }

    protected boolean n_1700_B(ServerLevelAccessor worldIn, BoundingBox boundsIn, Random rand, c_1514_x posIn, g_2336_b resourceLocationIn, @Nullable K_4074_S p_191080_6_) {
        if (boundsIn.J_1907_R(posIn) && !worldIn.getBlockState(posIn).n_1700_B(a_3742_W.L_1362_X)) {
            if (p_191080_6_ == null) {
                p_191080_6_ = E_3771_B.n_1700_B(worldIn, posIn, a_3742_W.L_1362_X.multiplayerClientSuggestionProvider());
            }
            worldIn.n_1700_B(posIn, p_191080_6_, 2);
            i_2154_H tileentity = worldIn.getTileEntity(posIn);
            if (tileentity instanceof t_693_s) {
                ((t_693_s)tileentity).n_1700_B(resourceLocationIn, rand.nextLong());
            }
            return true;
        }
        return false;
    }

    protected boolean n_1700_B(WorldGenLevel worldIn, BoundingBox sbb, Random rand, int x, int y, int z, b_257_Y facing, g_2336_b lootTableIn) {
        c_1514_x blockpos = new c_1514_x(this.n_1700_B(x, z), this.n_1700_B(y), this.J_1907_R(x, z));
        if (sbb.J_1907_R(blockpos) && !worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.Ops)) {
            this.n_1700_B(worldIn, (K_4074_S)a_3742_W.Ops.multiplayerClientSuggestionProvider().n_1700_B(S_3458_C.P_4830_p, facing), x, y, z, sbb);
            i_2154_H tileentity = worldIn.getTileEntity(blockpos);
            if (tileentity instanceof l_3848_Y) {
                ((l_3848_Y)tileentity).n_1700_B(lootTableIn, rand.nextLong());
            }
            return true;
        }
        return false;
    }

    public void n_1700_B(int x, int y, int z) {
        this.h_1847_R.n_1700_B(x, y, z);
    }

    @Nullable
    public b_257_Y t_148_a() {
        return this.n_1700_B;
    }

    public void n_1700_B(@Nullable b_257_Y facing) {
        this.n_1700_B = facing;
        if (facing == null) {
            this.R_4764_Y = W_2163_m.n_1700_B;
            this.J_1907_R = q_4099_E.n_1700_B;
        } else {
            switch (facing) {
                case G_564_y: {
                    this.J_1907_R = q_4099_E.J_1907_R;
                    this.R_4764_Y = W_2163_m.n_1700_B;
                    break;
                }
                case P_1922_E: {
                    this.J_1907_R = q_4099_E.J_1907_R;
                    this.R_4764_Y = W_2163_m.J_1907_R;
                    break;
                }
                case u_1723_Y: {
                    this.J_1907_R = q_4099_E.n_1700_B;
                    this.R_4764_Y = W_2163_m.J_1907_R;
                    break;
                }
                default: {
                    this.J_1907_R = q_4099_E.n_1700_B;
                    this.R_4764_Y = W_2163_m.n_1700_B;
                }
            }
        }
    }

    public W_2163_m n_1700_B() {
        return this.R_4764_Y;
    }

    public StructurePieceType s_956_w() {
        return this.G_564_y;
    }

    public static abstract class n_1700_B {
        protected K_4074_S n_1700_B = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();

        protected n_1700_B() {
        }

        public abstract void n_1700_B(Random var1, int var2, int var3, int var4, boolean var5);

        public K_4074_S n_1700_B() {
            return this.n_1700_B;
        }
    }
}



