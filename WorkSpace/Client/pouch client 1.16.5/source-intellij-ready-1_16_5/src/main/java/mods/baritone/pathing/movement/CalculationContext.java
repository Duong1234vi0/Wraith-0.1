/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.T_2915_h;
import lightning.product.V_772_m;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.Items;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.cache.WorldData;
import mods.baritone.pathing.precompute.PrecomputedData;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.ToolSet;
import mods.baritone.utils.pathing.BetterWorldBorder;

public class CalculationContext {
    private static final Z_1993_T STACK_BUCKET_WATER = new Z_1993_T(Items.W_2770_z);
    public final boolean safeForThreadedUse;
    public final IBaritone baritone;
    public final b_4507_u world;
    public final WorldData worldData;
    public final BlockStateInterface bsi;
    public final ToolSet toolSet;
    public final boolean hasWaterBucket;
    public final boolean hasThrowaway;
    public final boolean canSprint;
    protected final double placeBlockCost;
    public final boolean allowBreak;
    public final List<T_2915_h> allowBreakAnyway;
    public final boolean allowParkour;
    public final boolean allowParkourPlace;
    public final boolean allowJumpAt256;
    public final boolean allowParkourAscend;
    public final boolean assumeWalkOnWater;
    public final int frostWalker;
    public final boolean allowDiagonalDescend;
    public final boolean allowDiagonalAscend;
    public final boolean allowDownward;
    public final int maxFallHeightNoWater;
    public final int maxFallHeightBucket;
    public final double waterWalkSpeed;
    public final double breakBlockAdditionalCost;
    public double backtrackCostFavoringCoefficient;
    public double jumpPenalty;
    public final double walkOnWaterOnePenalty;
    public final BetterWorldBorder worldBorder;
    public final PrecomputedData precomputedData = new PrecomputedData();

    public CalculationContext(IBaritone baritone) {
        this(baritone, false);
    }

    public CalculationContext(IBaritone baritone, boolean forUseOnAnotherThread) {
        this.safeForThreadedUse = forUseOnAnotherThread;
        this.baritone = baritone;
        V_772_m player = baritone.getPlayerContext().player();
        this.world = baritone.getPlayerContext().world();
        this.worldData = (WorldData)baritone.getPlayerContext().worldData();
        this.bsi = new BlockStateInterface(baritone.getPlayerContext(), forUseOnAnotherThread);
        this.toolSet = new ToolSet(player);
        this.hasThrowaway = (Boolean)Baritone.settings().allowPlace.value != false && ((Baritone)baritone).getInventoryBehavior().hasGenericThrowaway();
        this.hasWaterBucket = (Boolean)Baritone.settings().allowWaterBucketFall.value != false && W_3491_f.J_1907_R(player.l_1268_F.J_1907_R(STACK_BUCKET_WATER)) && this.world.g_2268_R() != b_4507_u.v_4262_N;
        this.canSprint = (Boolean)Baritone.settings().allowSprint.value != false && player.P_2295_B().n_1700_B() > 6;
        this.placeBlockCost = (Double)Baritone.settings().blockPlacementPenalty.value;
        this.allowBreak = (Boolean)Baritone.settings().allowBreak.value;
        this.allowBreakAnyway = new ArrayList<T_2915_h>((Collection)Baritone.settings().allowBreakAnyway.value);
        this.allowParkour = (Boolean)Baritone.settings().allowParkour.value;
        this.allowParkourPlace = (Boolean)Baritone.settings().allowParkourPlace.value;
        this.allowJumpAt256 = (Boolean)Baritone.settings().allowJumpAt256.value;
        this.allowParkourAscend = (Boolean)Baritone.settings().allowParkourAscend.value;
        this.assumeWalkOnWater = (Boolean)Baritone.settings().assumeWalkOnWater.value;
        this.frostWalker = K_4096_w.n_1700_B(Enchantments.s_956_w, baritone.getPlayerContext().player());
        this.allowDiagonalDescend = (Boolean)Baritone.settings().allowDiagonalDescend.value;
        this.allowDiagonalAscend = (Boolean)Baritone.settings().allowDiagonalAscend.value;
        this.allowDownward = (Boolean)Baritone.settings().allowDownward.value;
        this.maxFallHeightNoWater = (Integer)Baritone.settings().maxFallHeightNoWater.value;
        this.maxFallHeightBucket = (Integer)Baritone.settings().maxFallHeightBucket.value;
        int depth = K_4096_w.P_1922_E(player);
        if (depth > 3) {
            depth = 3;
        }
        float mult = (float)depth / 3.0f;
        this.waterWalkSpeed = 9.09090909090909 * (double)(1.0f - mult) + 4.63284688441047 * (double)mult;
        this.breakBlockAdditionalCost = (Double)Baritone.settings().blockBreakAdditionalPenalty.value;
        this.backtrackCostFavoringCoefficient = (Double)Baritone.settings().backtrackCostFavoringCoefficient.value;
        this.jumpPenalty = (Double)Baritone.settings().jumpPenalty.value;
        this.walkOnWaterOnePenalty = (Double)Baritone.settings().walkOnWaterOnePenalty.value;
        this.worldBorder = new BetterWorldBorder(this.world.H_2857_Y());
    }

    public final IBaritone getBaritone() {
        return this.baritone;
    }

    public K_4074_S get(int x, int y, int z) {
        return this.bsi.get0(x, y, z);
    }

    public boolean isLoaded(int x, int z) {
        return this.bsi.isLoaded(x, z);
    }

    public K_4074_S get(c_1514_x pos) {
        return this.get(pos.getX(), pos.getY(), pos.getZ());
    }

    public T_2915_h getBlock(int x, int y, int z) {
        return this.get(x, y, z).J_1907_R();
    }

    public double costOfPlacingAt(int x, int y, int z, K_4074_S current) {
        if (!this.hasThrowaway) {
            return 1000000.0;
        }
        if (this.isPossiblyProtected(x, y, z)) {
            return 1000000.0;
        }
        if (!this.worldBorder.canPlaceAt(x, z)) {
            return 1000000.0;
        }
        return this.placeBlockCost;
    }

    public double breakCostMultiplierAt(int x, int y, int z, K_4074_S current) {
        if (!this.allowBreak && !this.allowBreakAnyway.contains(current.J_1907_R())) {
            return 1000000.0;
        }
        if (this.isPossiblyProtected(x, y, z)) {
            return 1000000.0;
        }
        return 1.0;
    }

    public double placeBucketCost() {
        return this.placeBlockCost;
    }

    public boolean isPossiblyProtected(int x, int y, int z) {
        return false;
    }
}


