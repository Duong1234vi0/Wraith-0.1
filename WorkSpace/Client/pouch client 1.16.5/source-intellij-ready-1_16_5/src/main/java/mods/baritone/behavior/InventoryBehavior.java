/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.NonNullList;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.V_772_m;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.DiggerItem;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.e_2866_D;
import lightning.product.q_1613_l;
import lightning.product.s_2612_h;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.event.events.TickEvent;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.behavior.Behavior;
import mods.baritone.utils.ToolSet;

public final class InventoryBehavior
extends Behavior
implements Helper {
    int ticksSinceLastInventoryMove;
    int[] lastTickRequestedMove;

    public InventoryBehavior(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void onTick(TickEvent event) {
        int pick;
        if (!((Boolean)Baritone.settings().allowInventory.value).booleanValue()) {
            return;
        }
        if (event.getType() == TickEvent.Type.OUT) {
            return;
        }
        if (this.ctx.player().H_1873_g != this.ctx.player().o_1800_r) {
            return;
        }
        ++this.ticksSinceLastInventoryMove;
        if (this.firstValidThrowaway() >= 9) {
            this.requestSwapWithHotBar(this.firstValidThrowaway(), 8);
        }
        if ((pick = this.bestToolAgainst(a_3742_W.J_1907_R, s_2612_h.class)) >= 9) {
            this.requestSwapWithHotBar(pick, 0);
        }
        if (this.lastTickRequestedMove != null) {
            this.logDebug("Remembering to move " + this.lastTickRequestedMove[0] + " " + this.lastTickRequestedMove[1] + " from a previous tick");
            this.requestSwapWithHotBar(this.lastTickRequestedMove[0], this.lastTickRequestedMove[1]);
        }
    }

    public boolean attemptToPutOnHotbar(int inMainInvy, Predicate<Integer> disallowedHotbar) {
        OptionalInt destination = this.getTempHotbarSlot(disallowedHotbar);
        return !destination.isPresent() || this.requestSwapWithHotBar(inMainInvy, destination.getAsInt());
    }

    public OptionalInt getTempHotbarSlot(Predicate<Integer> disallowedHotbar) {
        int i;
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        for (i = 1; i < 8; ++i) {
            if (!this.ctx.player().l_1268_F.n_1700_B.get(i).n_1700_B() || disallowedHotbar.test(i)) continue;
            candidates.add(i);
        }
        if (candidates.isEmpty()) {
            for (i = 1; i < 8; ++i) {
                if (disallowedHotbar.test(i)) continue;
                candidates.add(i);
            }
        }
        if (candidates.isEmpty()) {
            return OptionalInt.empty();
        }
        return OptionalInt.of((Integer)candidates.get(new Random().nextInt(candidates.size())));
    }

    private boolean requestSwapWithHotBar(int inInventory, int inHotbar) {
        this.lastTickRequestedMove = new int[]{inInventory, inHotbar};
        if (this.ticksSinceLastInventoryMove < (Integer)Baritone.settings().ticksBetweenInventoryMoves.value) {
            this.logDebug("Inventory move requested but delaying " + this.ticksSinceLastInventoryMove + " " + String.valueOf(Baritone.settings().ticksBetweenInventoryMoves.value));
            return false;
        }
        if (((Boolean)Baritone.settings().inventoryMoveOnlyIfStationary.value).booleanValue() && !this.baritone.getInventoryPauserProcess().stationaryForInventoryMove()) {
            this.logDebug("Inventory move requested but delaying until stationary");
            return false;
        }
        this.ctx.playerController().windowClick(this.ctx.player().o_1800_r.u_1723_Y, inInventory < 9 ? inInventory + 36 : inInventory, inHotbar, a_408_T.R_4764_Y, this.ctx.player());
        this.ticksSinceLastInventoryMove = 0;
        this.lastTickRequestedMove = null;
        return true;
    }

    private int firstValidThrowaway() {
        NonNullList<Z_1993_T> invy = this.ctx.player().l_1268_F.n_1700_B;
        for (int i = 0; i < invy.size(); ++i) {
            if (!((List)Baritone.settings().acceptableThrowawayItems.value).contains(invy.get(i).J_1907_R())) continue;
            return i;
        }
        return -1;
    }

    private int bestToolAgainst(T_2915_h against, Class<? extends DiggerItem> cla$$) {
        NonNullList<Z_1993_T> invy = this.ctx.player().l_1268_F.n_1700_B;
        int bestInd = -1;
        double bestSpeed = -1.0;
        for (int i = 0; i < invy.size(); ++i) {
            double speed;
            Z_1993_T stack = invy.get(i);
            if (stack.n_1700_B() || ((Boolean)Baritone.settings().itemSaver.value).booleanValue() && stack.v_4262_N() + (Integer)Baritone.settings().itemSaverThreshold.value >= stack.w_1484_f() && stack.w_1484_f() > 1 || !cla$$.isInstance(stack.J_1907_R()) || !((speed = ToolSet.calculateSpeedVsBlock(stack, against.multiplayerClientSuggestionProvider())) > bestSpeed)) continue;
            bestSpeed = speed;
            bestInd = i;
        }
        return bestInd;
    }

    public boolean hasGenericThrowaway() {
        for (q_1613_l item : (List)Baritone.settings().acceptableThrowawayItems.value) {
            if (!this.throwaway(false, stack -> item.equals(stack.J_1907_R()))) continue;
            return true;
        }
        return false;
    }

    public boolean selectThrowawayForLocation(boolean select, int x, int y, int z) {
        K_4074_S maybe = this.baritone.getBuilderProcess().placeAt(x, y, z, this.baritone.bsi.get0(x, y, z));
        if (maybe != null && this.throwaway(select, stack -> stack.J_1907_R() instanceof v_1669_V && maybe.equals(((v_1669_V)stack.J_1907_R()).v_4262_N().n_1700_B(new BlockPlaceContext(new UseOnContext(this, this.ctx.world(), this.ctx.player(), x_1688_C.n_1700_B, (Z_1993_T)stack, new BlockHitResult(new e_2866_D(this.ctx.player().s_4990_V().J_1907_R, this.ctx.player().s_4990_V().R_4764_Y, this.ctx.player().s_4990_V().G_564_y), b_257_Y.J_1907_R, this.ctx.playerFeet(), false)){}))))) {
            return true;
        }
        if (maybe != null && this.throwaway(select, stack -> stack.J_1907_R() instanceof v_1669_V && ((v_1669_V)stack.J_1907_R()).v_4262_N().equals(maybe.J_1907_R()))) {
            return true;
        }
        for (q_1613_l item : (List)Baritone.settings().acceptableThrowawayItems.value) {
            if (!this.throwaway(select, stack -> item.equals(stack.J_1907_R()))) continue;
            return true;
        }
        return false;
    }

    public boolean throwaway(boolean select, Predicate<? super Z_1993_T> desired) {
        return this.throwaway(select, desired, (Boolean)Baritone.settings().allowInventory.value);
    }

    public boolean throwaway(boolean select, Predicate<? super Z_1993_T> desired, boolean allowInventory) {
        Z_1993_T item;
        int i;
        V_772_m p = this.ctx.player();
        NonNullList<Z_1993_T> inv = p.l_1268_F.n_1700_B;
        for (i = 0; i < 9; ++i) {
            item = inv.get(i);
            if (!desired.test(item)) continue;
            if (select) {
                p.l_1268_F.G_564_y = i;
            }
            return true;
        }
        if (desired.test(p.l_1268_F.R_4764_Y.get(0))) {
            for (i = 0; i < 9; ++i) {
                item = inv.get(i);
                if (!item.n_1700_B() && !(item.J_1907_R() instanceof s_2612_h)) continue;
                if (select) {
                    p.l_1268_F.G_564_y = i;
                }
                return true;
            }
        }
        if (allowInventory) {
            for (i = 9; i < 36; ++i) {
                if (!desired.test(inv.get(i))) continue;
                if (select) {
                    this.requestSwapWithHotBar(i, 7);
                    p.l_1268_F.G_564_y = 7;
                }
                return true;
            }
        }
        return false;
    }
}


