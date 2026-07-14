/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lightning.product.TieredItem;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.T_2915_h;
import lightning.product.V_772_m;
import lightning.product.Z_1993_T;
import lightning.product.Enchantments;
import lightning.product.SwordItem;
import mods.baritone.Baritone;

public class ToolSet {
    private final Map<T_2915_h, Double> breakStrengthCache = new HashMap<T_2915_h, Double>();
    private final Function<T_2915_h, Double> backendCalculation;
    private final V_772_m player;

    public ToolSet(V_772_m player) {
        this.player = player;
        if (((Boolean)Baritone.settings().considerPotionEffects.value).booleanValue()) {
            double amplifier = this.potionAmplifier();
            Function<Double, Double> amplify = x -> amplifier * x;
            this.backendCalculation = amplify.compose(this::getBestDestructionTime);
        } else {
            this.backendCalculation = this::getBestDestructionTime;
        }
    }

    public double getStrVsBlock(K_4074_S state) {
        return this.breakStrengthCache.computeIfAbsent(state.J_1907_R(), this.backendCalculation);
    }

    private int getMaterialCost(Z_1993_T itemStack) {
        if (itemStack.J_1907_R() instanceof TieredItem) {
            TieredItem tool = (TieredItem)itemStack.J_1907_R();
            return tool.w_1484_f().G_564_y();
        }
        return -1;
    }

    public boolean hasSilkTouch(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) > 0;
    }

    public int getBestSlot(T_2915_h b, boolean preferSilkTouch) {
        return this.getBestSlot(b, preferSilkTouch, false);
    }

    public int getBestSlot(T_2915_h b, boolean preferSilkTouch, boolean pathingCalculation) {
        if (!((Boolean)Baritone.settings().autoTool.value).booleanValue() && pathingCalculation) {
            return this.player.l_1268_F.G_564_y;
        }
        int best = 0;
        double highestSpeed = Double.NEGATIVE_INFINITY;
        int lowestCost = Integer.MIN_VALUE;
        boolean bestSilkTouch = false;
        K_4074_S blockState = b.multiplayerClientSuggestionProvider();
        for (int i = 0; i < 9; ++i) {
            int cost;
            Z_1993_T itemStack = this.player.l_1268_F.s_956_w(i);
            if (!((Boolean)Baritone.settings().useSwordToMine.value).booleanValue() && itemStack.J_1907_R() instanceof SwordItem || ((Boolean)Baritone.settings().itemSaver.value).booleanValue() && itemStack.v_4262_N() + (Integer)Baritone.settings().itemSaverThreshold.value >= itemStack.w_1484_f() && itemStack.w_1484_f() > 1) continue;
            double speed = ToolSet.calculateSpeedVsBlock(itemStack, blockState);
            boolean silkTouch = this.hasSilkTouch(itemStack);
            if (speed > highestSpeed) {
                highestSpeed = speed;
                best = i;
                lowestCost = this.getMaterialCost(itemStack);
                bestSilkTouch = silkTouch;
                continue;
            }
            if (speed != highestSpeed || ((cost = this.getMaterialCost(itemStack)) >= lowestCost || !silkTouch && bestSilkTouch) && (!preferSilkTouch || bestSilkTouch || !silkTouch)) continue;
            highestSpeed = speed;
            best = i;
            lowestCost = cost;
            bestSilkTouch = silkTouch;
        }
        return best;
    }

    private double getBestDestructionTime(T_2915_h b) {
        Z_1993_T stack = this.player.l_1268_F.s_956_w(this.getBestSlot(b, false, true));
        return ToolSet.calculateSpeedVsBlock(stack, b.multiplayerClientSuggestionProvider()) * this.avoidanceMultiplier(b);
    }

    private double avoidanceMultiplier(T_2915_h b) {
        return ((List)Baritone.settings().blocksToAvoidBreaking.value).contains(b) ? (Double)Baritone.settings().avoidBreakingMultiplier.value : 1.0;
    }

    public static double calculateSpeedVsBlock(Z_1993_T item, K_4074_S state) {
        int effLevel;
        float hardness = state.w_1484_f(null, null);
        if (hardness < 0.0f) {
            return -1.0;
        }
        float speed = item.n_1700_B(state);
        if (speed > 1.0f && (effLevel = K_4096_w.n_1700_B(Enchantments.Y_601_j, item)) > 0 && !item.n_1700_B()) {
            speed += (float)(effLevel * effLevel + 1);
        }
        speed /= hardness;
        if (!state.t_1786_h() || !item.n_1700_B() && item.J_1907_R(state)) {
            return speed / 30.0f;
        }
        return speed / 100.0f;
    }

    private double potionAmplifier() {
        double speed = 1.0;
        if (this.player.J_1907_R(MobEffects.R_4764_Y)) {
            speed *= 1.0 + (double)(this.player.R_4764_Y(MobEffects.R_4764_Y).R_4764_Y() + 1) * 0.2;
        }
        if (this.player.J_1907_R(MobEffects.G_564_y)) {
            switch (this.player.R_4764_Y(MobEffects.G_564_y).R_4764_Y()) {
                case 0: {
                    speed *= 0.3;
                    break;
                }
                case 1: {
                    speed *= 0.09;
                    break;
                }
                case 2: {
                    speed *= 0.0027;
                    break;
                }
                default: {
                    speed *= 8.1E-4;
                }
            }
        }
        return speed;
    }
}


