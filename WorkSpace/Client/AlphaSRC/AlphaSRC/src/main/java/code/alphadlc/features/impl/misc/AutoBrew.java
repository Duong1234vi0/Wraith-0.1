/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1294
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3965
 *  net.minecraft.class_472
 *  net.minecraft.class_476
 *  net.minecraft.class_6880
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3965;
import net.minecraft.class_472;
import net.minecraft.class_476;
import net.minecraft.class_6880;
import net.minecraft.class_9334;

public class AutoBrew
extends Module {
    private static final int INGREDIENT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int BOTTLE_SLOT_START = 2;
    private static final int BOTTLE_SLOT_END = 4;
    private static final int PLAYER_SLOT_MIN = 5;
    private final SelectSetting potionType = new SelectSetting("\u0417\u0435\u043b\u044c\u0435", "\u041a\u0430\u043a\u043e\u0435 \u0437\u0435\u043b\u044c\u0435 \u0432\u0430\u0440\u0438\u0442\u044c").value("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c", "\u0421\u0438\u043b\u0430", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435").selected("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c");
    private final BooleanSetting redstoneExtend = new BooleanSetting("\u0423\u0434\u043b\u0438\u043d\u0435\u043d\u0438\u0435", "\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0440\u0435\u0434\u0441\u0442\u043e\u0443\u043d \u0434\u043b\u044f \u0443\u0432\u0435\u043b\u0438\u0447\u0435\u043d\u0438\u044f \u0434\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u0438").setValue(true);
    private final BooleanSetting glowstoneUpgrade = new BooleanSetting("\u0423\u0441\u0438\u043b\u0435\u043d\u0438\u0435 II", "\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0441\u0432\u0435\u0442\u043e\u043f\u044b\u043b\u044c \u0434\u043b\u044f \u0443\u0440\u043e\u0432\u043d\u044f II").setValue(false);
    private final BooleanSetting infiniteMode = new BooleanSetting("\u0411\u0435\u0441\u043a\u043e\u043d\u0435\u0447\u043d\u043e", "\u041f\u043e\u0432\u0442\u043e\u0440\u044f\u0442\u044c \u0432\u0430\u0440\u043a\u0443, \u043f\u043e\u043a\u0430 \u043c\u043e\u0434\u0443\u043b\u044c \u0432\u043a\u043b\u044e\u0447\u0451\u043d").setValue(true);
    private final BooleanSetting searchChest = new BooleanSetting("\u0418\u0441\u043a\u0430\u0442\u044c \u0432 \u0441\u0443\u043d\u0434\u0443\u043a\u0435", "\u041f\u0435\u0440\u0435\u0434 \u0432\u0430\u0440\u043a\u043e\u0439 \u043f\u044b\u0442\u0430\u0435\u0442\u0441\u044f \u0432\u0437\u044f\u0442\u044c \u0440\u0435\u0441\u0443\u0440\u0441\u044b \u0438\u0437 \u0441\u0443\u043d\u0434\u0443\u043a\u0430").setValue(true);
    private final SliderSettings brewTime = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0432\u0430\u0440\u043a\u0438", "\u0421\u0435\u043a\u0443\u043d\u0434 \u043e\u0436\u0438\u0434\u0430\u043d\u0438\u044f \u0432\u0430\u0440\u043a\u0438").setValue(20.0f).range(5.0f, 60.0f).step(1.0f);
    private final SliderSettings clickDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043a\u043b\u0438\u043a\u043e\u0432", "\u041c\u0438\u043b\u043b\u0438\u0441\u0435\u043a\u0443\u043d\u0434 \u043c\u0435\u0436\u0434\u0443 \u043a\u043b\u0438\u043a\u0430\u043c\u0438").setValue(250.0f).range(50.0f, 1000.0f).step(50.0f);
    private final StopWatch actionTimer = new StopWatch();
    private final StopWatch brewTimer = new StopWatch();
    private BrewState state = BrewState.IDLE;
    private int cycles;
    private class_2338 standPos;
    private class_2338 chestPos;
    private BrewRecipe recipe;
    private BrewStage stage;
    private List<class_1792> neededItems = new ArrayList<class_1792>();
    private int lootItemIndex;
    private boolean awkwardBrewDone;
    private boolean waitingSync;

    public AutoBrew() {
        super("AutoBrew", "Auto Brew", ModuleCategory.MISC);
        this.setup(this.potionType, this.redstoneExtend, this.glowstoneUpgrade, this.infiniteMode, this.searchChest, this.brewTime, this.clickDelay);
    }

    @Override
    public void activate() {
        this.cycles = 0;
        this.reset();
        this.state = BrewState.PREPARE;
        ChatMessage.brandmessage("AutoBrew: \u0437\u0430\u043f\u0443\u0441\u043a \u2014 " + this.potionType.getSelected());
    }

    @Override
    public void deactivate() {
        this.reset();
        super.deactivate();
    }

    private void reset() {
        this.state = BrewState.IDLE;
        this.standPos = null;
        this.chestPos = null;
        this.recipe = null;
        this.stage = BrewStage.MAIN;
        this.neededItems.clear();
        this.lootItemIndex = 0;
        this.awkwardBrewDone = false;
        this.waitingSync = false;
        this.actionTimer.reset();
        this.brewTimer.reset();
        if (AutoBrew.mc.field_1755 != null) {
            AutoBrew.mc.field_1724.method_7346();
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (AutoBrew.mc.field_1724 == null || AutoBrew.mc.field_1687 == null) {
            return;
        }
        switch (this.state.ordinal()) {
            case 0: {
                break;
            }
            case 1: {
                this.prepareCycle();
                break;
            }
            case 2: {
                this.searchChest();
                break;
            }
            case 3: {
                this.lootChest();
                break;
            }
            case 4: {
                this.findStand();
                break;
            }
            case 5: {
                this.openStand();
                break;
            }
            case 6: {
                this.placeIngredient();
                break;
            }
            case 7: {
                this.waitBrew();
                break;
            }
            case 8: {
                this.collectPotions();
                break;
            }
            case 9: {
                this.finishCycle();
            }
        }
    }

    private void prepareCycle() {
        this.recipe = BrewRecipe.fromLabel(this.potionType.getSelected());
        if (this.recipe == null) {
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u043d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u044b\u0439 \u0442\u0438\u043f \u0437\u0435\u043b\u044c\u044f");
            this.setState(BrewState.IDLE);
            this.deactivate();
            return;
        }
        this.stage = this.recipe.isTwoStage() ? BrewStage.PRE : BrewStage.MAIN;
        this.awkwardBrewDone = false;
        this.waitingSync = false;
        this.neededItems = this.recipe.itemsForStage(this.stage, this.redstoneExtend.isValue(), this.glowstoneUpgrade.isValue());
        this.lootItemIndex = 0;
        if (this.searchChest.isValue() && !this.hasItems(this.neededItems)) {
            this.setState(BrewState.SEARCH_CHEST);
            return;
        }
        this.setState(BrewState.FIND_STAND);
    }

    private void searchChest() {
        if (!this.actionTimer.finished((long)this.clickDelay.getValue())) {
            return;
        }
        this.chestPos = this.findNearbyBlock(10, class_2246.field_10034, class_2246.field_10380, class_2246.field_16328);
        if (this.chestPos == null) {
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u0441\u0443\u043d\u0434\u0443\u043a \u0441 \u0440\u0435\u0441\u0443\u0440\u0441\u0430\u043c\u0438 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
            this.setState(BrewState.FIND_STAND);
            return;
        }
        this.interactBlock(this.chestPos);
        this.actionTimer.reset();
        this.setState(BrewState.LOOT_CHEST);
    }

    private void lootChest() {
        if (!(AutoBrew.mc.field_1755 instanceof class_476) || AutoBrew.mc.field_1724.field_7512 == null) {
            if (this.actionTimer.finished(500.0)) {
                this.setState(BrewState.FIND_STAND);
            }
            return;
        }
        if (!this.actionTimer.finished((long)this.clickDelay.getValue())) {
            return;
        }
        if (this.lootItemIndex >= this.neededItems.size()) {
            AutoBrew.mc.field_1724.method_7346();
            this.actionTimer.reset();
            this.setState(BrewState.FIND_STAND);
            return;
        }
        class_1792 wanted = this.neededItems.get(this.lootItemIndex);
        class_1735 slot = this.findPlayerItem(wanted);
        if (slot != null) {
            InventoryTask.clickSlot(slot.field_7874, 0, class_1713.field_7794, false, false);
            this.actionTimer.reset();
        }
        ++this.lootItemIndex;
    }

    private void findStand() {
        this.standPos = this.findNearbyBlock(5, class_2246.field_10333);
        if (this.standPos == null) {
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u0432\u0430\u0440\u043e\u0447\u043d\u0430\u044f \u0441\u0442\u043e\u0439\u043a\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430");
            this.finishCycle();
            return;
        }
        this.setState(BrewState.OPEN_STAND);
    }

    private void openStand() {
        if (!this.actionTimer.finished((long)this.clickDelay.getValue())) {
            return;
        }
        if (!(AutoBrew.mc.field_1755 instanceof class_472)) {
            this.interactBlock(this.standPos);
            this.actionTimer.reset();
            return;
        }
        this.waitingSync = false;
        this.setState(BrewState.PLACE_INGREDIENT);
    }

    private void placeIngredient() {
        if (!(AutoBrew.mc.field_1755 instanceof class_472) || AutoBrew.mc.field_1724.field_7512 == null) {
            this.setState(BrewState.OPEN_STAND);
            return;
        }
        if (!this.actionTimer.finished((long)this.clickDelay.getValue())) {
            return;
        }
        if (this.waitingSync) {
            this.waitingSync = false;
            this.actionTimer.reset();
            return;
        }
        class_1703 handler = AutoBrew.mc.field_1724.field_7512;
        if (!handler.method_34255().method_7960()) {
            class_1735 empty = this.findEmptyPlayerSlot();
            if (empty != null) {
                InventoryTask.clickSlot(empty.field_7874, 0, class_1713.field_7790, false, false);
                this.waitingSync = true;
                this.actionTimer.reset();
            }
            return;
        }
        if (!this.isFuelReady(handler)) {
            class_1735 fuel = this.findPlayerItem(class_1802.field_8183);
            if (fuel != null) {
                InventoryTask.clickSlot(fuel.field_7874, 0, class_1713.field_7794, false, false);
                this.waitingSync = true;
                this.actionTimer.reset();
                return;
            }
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u043d\u0435\u0442 \u043f\u043e\u0440\u043e\u0448\u043a\u0430 \u0438\u0444\u0440\u0438\u0442\u0430");
            AutoBrew.mc.field_1724.method_7346();
            this.finishCycle();
            return;
        }
        int emptyBottleSlot = this.findEmptyBottleSlot(handler);
        if (emptyBottleSlot >= 0) {
            class_1735 bottle = this.findBottleSlot();
            if (bottle != null) {
                InventoryTask.clickSlot(bottle.field_7874, 0, class_1713.field_7794, false, false);
                this.waitingSync = true;
                this.actionTimer.reset();
                return;
            }
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u043d\u0435\u0442 \u043f\u043e\u0434\u0445\u043e\u0434\u044f\u0449\u0438\u0445 \u0431\u0443\u0442\u044b\u043b\u043e\u043a");
            AutoBrew.mc.field_1724.method_7346();
            this.finishCycle();
            return;
        }
        class_1792 ingredient = this.activeIngredient();
        if (ingredient == null) {
            this.brewTimer.reset();
            this.setState(BrewState.WAIT_BREW);
            return;
        }
        class_1799 ingredientStack = handler.method_7611(0).method_7677();
        if (!ingredientStack.method_31574(ingredient)) {
            if (!ingredientStack.method_7960()) {
                InventoryTask.clickSlot(0, 0, class_1713.field_7794, false, false);
                this.waitingSync = true;
                this.actionTimer.reset();
                return;
            }
            class_1735 ingredientSlot = this.findPlayerItem(ingredient);
            if (ingredientSlot != null) {
                InventoryTask.clickSlot(ingredientSlot.field_7874, 0, class_1713.field_7794, false, false);
                this.waitingSync = true;
                this.actionTimer.reset();
                return;
            }
            ChatMessage.brandmessage("\u00a7cAutoBrew: \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d " + ingredient.method_63680().getString());
            AutoBrew.mc.field_1724.method_7346();
            this.finishCycle();
            return;
        }
        this.brewTimer.reset();
        this.setState(BrewState.WAIT_BREW);
    }

    private boolean isFuelReady(class_1703 handler) {
        class_1799 fuelStack = handler.method_7611(1).method_7677();
        if (!fuelStack.method_7960() && fuelStack.method_31574(class_1802.field_8183)) {
            return true;
        }
        return this.findPlayerItem(class_1802.field_8183) == null;
    }

    private int findEmptyBottleSlot(class_1703 handler) {
        for (int bottleSlot = 2; bottleSlot <= 4; ++bottleSlot) {
            if (!handler.method_7611(bottleSlot).method_7677().method_7960()) continue;
            return bottleSlot;
        }
        return -1;
    }

    private class_1735 findEmptyPlayerSlot() {
        return InventoryTask.slots().filter(slot -> slot.field_7871 instanceof class_1661).filter(slot -> slot.field_7874 >= 5).filter(slot -> slot.method_7677().method_7960()).findFirst().orElse(null);
    }

    private void waitBrew() {
        if (!this.brewTimer.finished((long)(this.brewTime.getValue() * 1000.0f))) {
            return;
        }
        this.setState(BrewState.COLLECT);
    }

    private void collectPotions() {
        if (!(AutoBrew.mc.field_1755 instanceof class_472) || AutoBrew.mc.field_1724.field_7512 == null) {
            this.setState(BrewState.OPEN_STAND);
            return;
        }
        if (!this.actionTimer.finished((long)this.clickDelay.getValue())) {
            return;
        }
        for (int slot = 2; slot <= 4; ++slot) {
            if (AutoBrew.mc.field_1724.field_7512.method_7611(slot).method_7677().method_7960()) continue;
            InventoryTask.clickSlot(slot, 0, class_1713.field_7794, false, false);
        }
        AutoBrew.mc.field_1724.method_7346();
        this.actionTimer.reset();
        if (this.needsAwkwardBrewFirst()) {
            this.awkwardBrewDone = true;
            this.setState(BrewState.OPEN_STAND);
            return;
        }
        if (this.recipe.isTwoStage() && this.stage == BrewStage.PRE) {
            this.stage = BrewStage.MAIN;
            this.neededItems = this.recipe.itemsForStage(this.stage, this.redstoneExtend.isValue(), this.glowstoneUpgrade.isValue());
            this.setState(BrewState.PREPARE);
            return;
        }
        class_1792 modifier = this.currentModifier();
        if (modifier != null && this.stage != BrewStage.MODIFIER) {
            this.stage = BrewStage.MODIFIER;
            this.setState(BrewState.OPEN_STAND);
            return;
        }
        ++this.cycles;
        ChatMessage.brandmessage("\u00a7aAutoBrew: \u0446\u0438\u043a\u043b #" + this.cycles + " \u0437\u0430\u0432\u0435\u0440\u0448\u0451\u043d");
        this.setState(BrewState.FINISH);
    }

    private void finishCycle() {
        if (!this.infiniteMode.isValue()) {
            ChatMessage.brandmessage("\u00a7aAutoBrew: \u0433\u043e\u0442\u043e\u0432\u043e. \u0426\u0438\u043a\u043b\u043e\u0432: " + this.cycles);
            this.deactivate();
            return;
        }
        this.setState(BrewState.PREPARE);
    }

    private class_1792 activeIngredient() {
        if (this.stage == BrewStage.MODIFIER) {
            return this.currentModifier();
        }
        if (this.needsAwkwardBrewFirst()) {
            return class_1802.field_8790;
        }
        return this.recipe.ingredientFor(this.stage);
    }

    private boolean needsAwkwardBrewFirst() {
        return this.recipe != null && this.recipe.needsAwkwardBase() && this.stage == BrewStage.MAIN && !this.awkwardBrewDone;
    }

    private class_1735 findPlayerItem(class_1792 item) {
        return InventoryTask.slots().filter(slot -> slot.field_7871 instanceof class_1661).filter(slot -> slot.field_7874 >= 5).filter(slot -> slot.method_7677().method_31574(item)).findFirst().orElse(null);
    }

    private class_1735 findBottleSlot() {
        class_1735 nightVision;
        if (this.recipe.isTwoStage() && this.stage == BrewStage.MAIN && (nightVision = InventoryTask.getPotion((class_6880<class_1291>)class_1294.field_5925)) != null && nightVision.field_7871 instanceof class_1661 && nightVision.field_7874 >= 5) {
            return nightVision;
        }
        if (this.needsAwkwardBrewFirst()) {
            water = this.findWaterBottle();
            if (water != null) {
                return water;
            }
        } else if (this.recipe.needsAwkwardBase() && this.awkwardBrewDone) {
            class_1735 awkward = this.findAwkwardPotion();
            if (awkward != null) {
                return awkward;
            }
        } else {
            water = this.findWaterBottle();
            if (water != null) {
                return water;
            }
        }
        return InventoryTask.slots().filter(slot -> slot.field_7871 instanceof class_1661).filter(slot -> slot.field_7874 >= 5).filter(slot -> slot.method_7677().method_31574(class_1802.field_8574)).findFirst().orElse(null);
    }

    private class_1735 findWaterBottle() {
        return InventoryTask.slots().filter(slot -> slot.field_7871 instanceof class_1661).filter(slot -> slot.field_7874 >= 5).filter(slot -> this.isWaterBottle(slot.method_7677())).findFirst().orElse(null);
    }

    private class_1735 findAwkwardPotion() {
        return InventoryTask.slots().filter(slot -> slot.field_7871 instanceof class_1661).filter(slot -> slot.field_7874 >= 5).filter(slot -> this.isAwkwardPotion(slot.method_7677())).findFirst().orElse(null);
    }

    private boolean isWaterBottle(class_1799 stack) {
        if (!stack.method_31574(class_1802.field_8574)) {
            return false;
        }
        if (this.isAwkwardPotion(stack)) {
            return false;
        }
        class_1844 contents = (class_1844)stack.method_57824(class_9334.field_49651);
        if (contents == null) {
            return true;
        }
        if (contents.method_57397().iterator().hasNext()) {
            return false;
        }
        if (contents.comp_2378().isEmpty()) {
            return true;
        }
        return this.hasPotionId(contents, "water");
    }

    private boolean isAwkwardPotion(class_1799 stack) {
        if (!stack.method_31574(class_1802.field_8574)) {
            return false;
        }
        class_1844 contents = (class_1844)stack.method_57824(class_9334.field_49651);
        if (contents == null || contents.comp_2378().isEmpty()) {
            return false;
        }
        if (contents.method_57397().iterator().hasNext()) {
            return false;
        }
        return this.hasPotionId(contents, "awkward");
    }

    private boolean hasPotionId(class_1844 contents, String id) {
        return contents.comp_2378().map(entry -> entry.method_40230().map(key -> key.method_29177().equals((Object)class_2960.method_60656((String)id))).orElse(false)).orElse(false);
    }

    private class_1792 currentIngredient() {
        return this.activeIngredient();
    }

    private class_1792 currentModifier() {
        if (this.glowstoneUpgrade.isValue()) {
            return class_1802.field_8601;
        }
        if (this.redstoneExtend.isValue()) {
            return class_1802.field_8725;
        }
        return null;
    }

    private boolean hasItems(List<class_1792> items) {
        for (class_1792 item : items) {
            if (this.findPlayerItem(item) != null) continue;
            return false;
        }
        return true;
    }

    private void setState(BrewState next) {
        this.state = next;
        this.actionTimer.reset();
    }

    private class_2338 findNearbyBlock(int radius, class_2248 ... blocks) {
        class_2338 origin = AutoBrew.mc.field_1724.method_24515();
        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    class_2338 pos = origin.method_10069(x, y, z);
                    class_2248 block = AutoBrew.mc.field_1687.method_8320(pos).method_26204();
                    for (class_2248 target : blocks) {
                        if (block != target) continue;
                        return pos;
                    }
                }
            }
        }
        return null;
    }

    private void interactBlock(class_2338 pos) {
        class_243 hit = class_243.method_24953((class_2382)pos);
        class_3965 result = new class_3965(hit, class_2350.field_11036, pos, false);
        if (AutoBrew.mc.field_1761 != null) {
            AutoBrew.mc.field_1761.method_2896(AutoBrew.mc.field_1724, class_1268.field_5808, result);
            AutoBrew.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    private static enum BrewState {
        IDLE,
        PREPARE,
        SEARCH_CHEST,
        LOOT_CHEST,
        FIND_STAND,
        OPEN_STAND,
        PLACE_INGREDIENT,
        WAIT_BREW,
        COLLECT,
        FINISH;

    }

    private static enum BrewRecipe {
        FIRE_RESISTANCE("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", class_1802.field_8135, null, null, false),
        INVISIBILITY("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c", class_1802.field_8711, class_1802.field_8071, (class_6880<class_1291>)class_1294.field_5925, true),
        STRENGTH("\u0421\u0438\u043b\u0430", class_1802.field_8183, class_1802.field_8790, (class_6880<class_1291>)class_1294.field_5910, false),
        SPEED("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", class_1802.field_8479, class_1802.field_8790, (class_6880<class_1291>)class_1294.field_5904, false),
        HEALING("\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435", class_1802.field_8597, class_1802.field_8790, (class_6880<class_1291>)class_1294.field_5915, false);

        private final String label;
        private final class_1792 mainIngredient;
        private final class_1792 preIngredient;
        private final class_6880<class_1291> resultEffect;
        private final boolean twoStage;

        static BrewRecipe fromLabel(String label) {
            for (BrewRecipe recipe : BrewRecipe.values()) {
                if (!recipe.label.equals(label)) continue;
                return recipe;
            }
            return null;
        }

        class_1792 ingredientFor(BrewStage stage) {
            if (this.twoStage && stage == BrewStage.PRE) {
                return this.preIngredient;
            }
            return this.mainIngredient;
        }

        List<class_1792> itemsForStage(BrewStage stage, boolean redstone, boolean glowstone) {
            ArrayList<class_1792> items = new ArrayList<class_1792>();
            if (this.twoStage && stage == BrewStage.PRE) {
                items.add(this.preIngredient);
                items.add(class_1802.field_8574);
            } else if (this.twoStage && stage == BrewStage.MAIN) {
                items.add(this.mainIngredient);
            } else if (!this.twoStage && this.needsAwkwardBase()) {
                items.add(this.mainIngredient);
                items.add(class_1802.field_8790);
                items.add(class_1802.field_8574);
            } else {
                items.add(this.mainIngredient);
                items.add(class_1802.field_8574);
            }
            if (redstone) {
                items.add(class_1802.field_8725);
            }
            if (glowstone) {
                items.add(class_1802.field_8601);
            }
            items.add(class_1802.field_8183);
            return items;
        }

        boolean needsAwkwardBase() {
            return this == STRENGTH || this == SPEED || this == HEALING;
        }

        public String getLabel() {
            return this.label;
        }

        public class_1792 getMainIngredient() {
            return this.mainIngredient;
        }

        public class_1792 getPreIngredient() {
            return this.preIngredient;
        }

        public class_6880<class_1291> getResultEffect() {
            return this.resultEffect;
        }

        public boolean isTwoStage() {
            return this.twoStage;
        }

        private BrewRecipe(String label, class_1792 mainIngredient, class_1792 preIngredient, class_6880<class_1291> resultEffect, boolean twoStage) {
            this.label = label;
            this.mainIngredient = mainIngredient;
            this.preIngredient = preIngredient;
            this.resultEffect = resultEffect;
            this.twoStage = twoStage;
        }
    }

    private static enum BrewStage {
        PRE,
        MAIN,
        MODIFIER;

    }
}
