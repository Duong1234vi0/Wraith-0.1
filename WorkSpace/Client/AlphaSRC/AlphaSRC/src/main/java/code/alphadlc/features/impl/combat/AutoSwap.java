/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1829
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_490
 *  net.minecraft.class_5134
 *  net.minecraft.class_5348
 *  net.minecraft.class_9285
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.features.impl.combat.autoswapUtil.AutoSwapEngine;
import code.alphadlc.features.impl.combat.autoswapUtil.AutoSwapWheelScreen;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.script.Script;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_490;
import net.minecraft.class_5134;
import net.minecraft.class_5348;
import net.minecraft.class_9285;
import net.minecraft.class_9334;

public class AutoSwap
extends Module
implements QuickImports {
    private BindSetting bind = new BindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442 \u043f\u0440\u0438 \u043d\u0430\u0436\u0430\u0442\u0438\u0438");
    private RadioSetting mode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u0440\u0430\u0431\u043e\u0442\u044b.", new String[]{"\u0414\u0432\u043e\u0439\u043d\u043e\u0439", "\u0422\u0440\u043e\u0439\u043d\u043e\u0439"}, "\u0414\u0432\u043e\u0439\u043d\u043e\u0439");
    private SelectSetting bypass = new SelectSetting("\u041e\u0431\u0445\u043e\u0434", "\u041e\u0431\u0445\u043e\u0434 \u0430\u043d\u0442\u0438\u0447\u0438\u0442\u0430 \u043f\u0440\u0438 \u0441\u0432\u0430\u043f\u0435").value("Vanilla", "Reallyworld", "Funtime", "Holyworld", "SpookyTime", "LonyGrief").selected("SpookyTime");
    private RadioSetting firstItem = new RadioSetting("\u041e\u0441\u043d\u043e\u0432\u043d\u043e\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043f\u0435\u0440\u0432\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0434\u043b\u044f \u043e\u0431\u043c\u0435\u043d\u0430.", new String[]{"\u0422\u043e\u0442\u0435\u043c", "\u0421\u0444\u0435\u0440\u0430", "\u0413\u0435\u043f\u043b", "\u0429\u0438\u0442"}, "\u0422\u043e\u0442\u0435\u043c").visible(() -> "\u0414\u0432\u043e\u0439\u043d\u043e\u0439".equals(this.mode.get()));
    private RadioSetting secondItem = new RadioSetting("\u0412\u0442\u043e\u0440\u0438\u0447\u043d\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0432\u0442\u043e\u0440\u043e\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0434\u043b\u044f \u043e\u0431\u043c\u0435\u043d\u0430.", new String[]{"\u0422\u043e\u0442\u0435\u043c", "\u0421\u0444\u0435\u0440\u0430", "\u0413\u0435\u043f\u043b", "\u0429\u0438\u0442"}, "\u0422\u043e\u0442\u0435\u043c").visible(() -> "\u0414\u0432\u043e\u0439\u043d\u043e\u0439".equals(this.mode.get()));
    private BooleanSetting autoDamage = new BooleanSetting("\u0410\u0432\u0442\u043e \u0443\u0440\u043e\u043d", "\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0441\u0432\u0430\u043f\u0430\u0435\u0442 \u043d\u0430 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0441 \u043c\u0430\u043a\u0441 \u0443\u0440\u043e\u043d\u043e\u043c");
    private MultiSelectSetting autoDamageConditions = new MultiSelectSetting("\u0423\u0441\u043b\u043e\u0432\u0438\u044f", "\u0423\u0441\u043b\u043e\u0432\u0438\u044f \u0434\u043b\u044f \u0430\u0432\u0442\u043e \u0443\u0440\u043e\u043d\u0430").value("\u0422\u0430\u0440\u0433\u0435\u0442 \u0441 \u043c\u0430\u043b\u044b\u043c HP", "\u0423 \u0442\u0430\u0440\u0433\u0435\u0442\u0430 \u043d\u0435\u0442 \u043c\u0435\u0447\u0430").selected("\u0422\u0430\u0440\u0433\u0435\u0442 \u0441 \u043c\u0430\u043b\u044b\u043c HP").visible(() -> this.autoDamage.isValue());
    private SliderSettings lowHpThreshold = new SliderSettings("\u041f\u043e\u0440\u043e\u0433 HP", "\u041f\u043e\u0440\u043e\u0433 \u043d\u0438\u0437\u043a\u043e\u0433\u043e HP \u0442\u0430\u0440\u0433\u0435\u0442\u0430").setValue(10.0f).range(1.0f, 20.0f).step(0.5f).visible(() -> this.autoDamage.isValue() && this.autoDamageConditions.isSelected("\u0422\u0430\u0440\u0433\u0435\u0442 \u0441 \u043c\u0430\u043b\u044b\u043c HP"));
    private class_1799 savedOffhandItem = class_1799.field_8037;
    private boolean autoDamageActive = false;
    private class_2561 lastSwapDisplayName = null;
    private long lastSwapTimeMs = 0L;
    private Animation swapAnim = new Decelerate().setMs(250).setValue(1.0);
    private final Script script = new Script();
    static final long SWAP_TOAST_DURATION_MS = 1800L;
    static final long SWAP_FADE_MS = 250L;
    private WheelSlotItem[] wheelSlots = new WheelSlotItem[3];
    private Integer selectingSlotIndex = null;
    private boolean awaitingInventoryPick = false;

    public AutoSwap() {
        super("AutoSwap", "AutoSwap", ModuleCategory.COMBAT);
        this.setup(this.mode, this.bypass, this.firstItem, this.secondItem, this.bind, this.autoDamage, this.autoDamageConditions, this.lowHpThreshold);
    }

    @Override
    public void deactivate() {
        AutoSwapEngine.get().reset();
        super.deactivate();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.script.update();
        AutoSwapEngine.get().onTick(e);
        if (this.awaitingInventoryPick && this.selectingSlotIndex != null && mc != null && !(AutoSwap.mc.field_1755 instanceof class_490)) {
            this.awaitingInventoryPick = false;
            this.selectingSlotIndex = null;
            if (!(AutoSwap.mc.field_1755 instanceof AutoSwapWheelScreen)) {
                mc.method_1507((class_437)new AutoSwapWheelScreen(this));
            }
        }
        if (this.autoDamage.isValue()) {
            this.handleAutoDamage();
        }
    }

    @EventHandler
    public void onInput(InputEvent e) {
        AutoSwapEngine.get().onInput(e);
    }

    private void swapOffhand(class_1735 slot, Runnable onComplete) {
        if (slot == null) {
            return;
        }
        AutoSwapEngine.get().requestSwap(slot, this.bypass.getSelected());
        if (onComplete != null) {
            this.script.cleanup().addTickStep(2, onComplete::run);
        }
    }

    private void handleAutoDamage() {
        if (AutoSwap.mc.field_1724 == null || AutoSwap.mc.field_1687 == null) {
            return;
        }
        class_1309 target = AttackAura.getInstance() != null ? AttackAura.getInstance().getTarget() : null;
        boolean shouldSwapToDamage = false;
        if (target != null && target.method_5805()) {
            boolean lowHpCondition = false;
            boolean noSwordCondition = false;
            if (this.autoDamageConditions.isSelected("\u0422\u0430\u0440\u0433\u0435\u0442 \u0441 \u043c\u0430\u043b\u044b\u043c HP")) {
                boolean hpBelowThreshold = target.method_6032() <= this.lowHpThreshold.getValue();
                boolean hpLessThanMine = target.method_6032() < AutoSwap.mc.field_1724.method_6032();
                boolean bl = lowHpCondition = hpBelowThreshold && hpLessThanMine;
            }
            if (this.autoDamageConditions.isSelected("\u0423 \u0442\u0430\u0440\u0433\u0435\u0442\u0430 \u043d\u0435\u0442 \u043c\u0435\u0447\u0430") && target instanceof class_1657) {
                class_1657 player = (class_1657)target;
                class_1799 mainHand = player.method_6047();
                boolean bl = noSwordCondition = !(mainHand.method_7909() instanceof class_1829);
            }
            if (this.autoDamageConditions.getSelected().isEmpty()) {
                shouldSwapToDamage = true;
            } else {
                boolean bl = shouldSwapToDamage = lowHpCondition || noSwordCondition;
            }
        }
        if (shouldSwapToDamage && !this.autoDamageActive) {
            this.savedOffhandItem = AutoSwap.mc.field_1724.method_6079().method_7972();
            class_1799 bestDamageItem = this.findBestDamageItem();
            if (!bestDamageItem.method_7960()) {
                this.swapToItemStack(bestDamageItem);
                this.autoDamageActive = true;
            }
        } else if (!shouldSwapToDamage && this.autoDamageActive) {
            if (!this.savedOffhandItem.method_7960()) {
                this.swapToItemStack(this.savedOffhandItem);
            }
            this.autoDamageActive = false;
            this.savedOffhandItem = class_1799.field_8037;
        }
    }

    private class_1799 findBestDamageItem() {
        if (AutoSwap.mc.field_1724 == null) {
            return class_1799.field_8037;
        }
        ArrayList<class_1799> damageItems = new ArrayList<class_1799>();
        for (int i = 0; i < AutoSwap.mc.field_1724.method_31548().method_5439(); ++i) {
            double damage;
            class_1799 stack = AutoSwap.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || stack.method_7909() != class_1802.field_8575 && stack.method_7909() != class_1802.field_8288 || !((damage = this.getAttackDamage(stack)) > 0.0)) continue;
            damageItems.add(stack);
        }
        if (damageItems.isEmpty()) {
            return class_1799.field_8037;
        }
        return damageItems.stream().max(Comparator.comparingDouble(this::getAttackDamage)).orElse(class_1799.field_8037);
    }

    private double getAttackDamage(class_1799 stack) {
        if (stack.method_7960()) {
            return 0.0;
        }
        class_9285 modifiers = (class_9285)stack.method_57824(class_9334.field_49636);
        if (modifiers == null) {
            return 0.0;
        }
        double[] totalDamage = new double[]{0.0};
        modifiers.comp_2393().forEach(entry -> {
            if (entry.comp_2395().comp_349() == class_5134.field_23721.comp_349()) {
                totalDamage[0] = totalDamage[0] + entry.comp_2396().comp_2449();
            }
        });
        return totalDamage[0];
    }

    private void swapToItemStack(class_1799 targetStack) {
        if (targetStack == null || targetStack.method_7960()) {
            return;
        }
        class_1792 item = targetStack.method_7909();
        String itemName = targetStack.method_7964().getString();
        class_1735 foundSlot = null;
        for (class_1735 slot : InventoryTask.slots().filter(s -> s.field_7874 != 46 && s.field_7874 != 45).toList()) {
            String slotName;
            class_1799 slotStack = slot.method_7677();
            if (slotStack.method_7960() || slotStack.method_7909() != item || !(slotName = slotStack.method_7964().getString()).equals(itemName)) continue;
            foundSlot = slot;
            break;
        }
        if (foundSlot != null) {
            class_1735 slotToSwap = foundSlot;
            this.swapOffhand(slotToSwap, () -> this.triggerSwapToast(slotToSwap.method_7677()));
        }
    }

    @EventHandler
    public void onClickSlot(ClickSlotEvent e) {
        if (this.selectingSlotIndex == null || !(AutoSwap.mc.field_1755 instanceof class_490)) {
            return;
        }
        if (AutoSwap.mc.field_1724 == null || AutoSwap.mc.field_1724.field_7512 == null) {
            return;
        }
        class_1735 slot = null;
        for (class_1735 s : AutoSwap.mc.field_1724.field_7512.field_7761) {
            if (s.field_7874 != e.getSlotId()) continue;
            slot = s;
            break;
        }
        if (slot == null || !slot.method_7681() || !(slot.field_7871 instanceof class_1661)) {
            return;
        }
        class_1799 stack = slot.method_7677();
        if (stack.method_7960()) {
            return;
        }
        int slotIndex = this.selectingSlotIndex;
        this.setWheelSlotItem(slotIndex, stack.method_7909(), stack.method_7964().getString());
        this.selectingSlotIndex = null;
        this.awaitingInventoryPick = false;
        e.setCancelled(true);
        mc.method_1507((class_437)new AutoSwapWheelScreen(this));
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        class_1735 validSlot;
        if (!e.isKeyDown(this.bind.getKey()) || AutoSwap.mc.field_1724 == null) {
            return;
        }
        if (this.mode.get().equals("\u0422\u0440\u043e\u0439\u043d\u043e\u0439")) {
            if (AutoSwap.mc.field_1755 == null) {
                mc.method_1507((class_437)new AutoSwapWheelScreen(this));
            }
            return;
        }
        class_1735 first = InventoryTask.getSlot(this.getItemByType(this.firstItem.get()), Comparator.comparing(s -> s.method_7677().method_7942()), s -> s.field_7874 != 46 && s.field_7874 != 45);
        class_1735 second = InventoryTask.getSlot(this.getItemByType(this.secondItem.get()), Comparator.comparing(s -> s.method_7677().method_7942()), s -> s.field_7874 != 46 && s.field_7874 != 45);
        if (first == null && second == null) {
            return;
        }
        class_1792 offhandItem = AutoSwap.mc.field_1724.method_6079().method_7909();
        if (first != null && offhandItem != first.method_7677().method_7909()) {
            validSlot = first;
        } else if (second != null && offhandItem != second.method_7677().method_7909()) {
            validSlot = second;
        } else {
            class_1735 class_17352 = validSlot = first != null ? first : second;
        }
        if (validSlot != null) {
            class_1799 stackToSwap = validSlot.method_7677();
            this.swapOffhand(validSlot, () -> this.triggerSwapToast(stackToSwap));
        }
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (this.lastSwapTimeMs == 0L) {
            return;
        }
        if (mc == null || mc.method_22683() == null || AutoSwap.mc.field_1772 == null) {
            return;
        }
        long elapsed = System.currentTimeMillis() - this.lastSwapTimeMs;
        if (elapsed > 1800L) {
            this.lastSwapTimeMs = 0L;
            this.lastSwapDisplayName = null;
            return;
        }
        boolean fadingIn = elapsed < 250L;
        boolean fadingOut = elapsed > 1550L;
        float anim = 1.0f;
        if (fadingIn) {
            anim = Math.min(1.0f, (float)elapsed / 250.0f);
        } else if (fadingOut) {
            anim = Math.max(0.0f, (float)(1800L - elapsed) / 250.0f);
        }
        if (anim <= 0.01f) {
            return;
        }
        class_332 context = e.getDrawContext();
        if (context == null) {
            return;
        }
        class_4587 matrix = context.method_51448();
        String prefixStr = "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 ";
        int screenW = mc.method_22683().method_4486();
        int screenH = mc.method_22683().method_4502();
        float y = screenH - 80;
        float prefixW = AutoSwap.mc.field_1772.method_1727("\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 ");
        float nameW = 0.0f;
        if (this.lastSwapDisplayName != null) {
            nameW = AutoSwap.mc.field_1772.method_27525((class_5348)this.lastSwapDisplayName);
        }
        float nameWFinal = nameW;
        float totalW = prefixW + nameWFinal;
        float x = ((float)screenW - totalW) / 2.0f;
        float animFinal = anim;
        int alpha = (int)(255.0f * animFinal);
        int prefixColor = ColorAssist.rgba(255, 255, 255, alpha);
        class_2561 lastSwapDisplayNameFinal = this.lastSwapDisplayName;
        Calculate.setAlpha(animFinal, () -> Calculate.scale(matrix, x + totalW / 2.0f, y + 6.0f, animFinal, () -> {
            context.method_51433(AutoSwap.mc.field_1772, "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 ", (int)x, (int)y, prefixColor, false);
            if (lastSwapDisplayNameFinal != null) {
                context.method_51439(AutoSwap.mc.field_1772, lastSwapDisplayNameFinal, (int)(x + prefixW), (int)y, 0xFFFFFF | alpha << 24, false);
            }
        }));
    }

    private void triggerSwapToast(class_1799 swappedTo) {
        try {
            this.lastSwapTimeMs = System.currentTimeMillis();
            this.lastSwapDisplayName = swappedTo.method_7964();
            this.swapAnim.setDirection(Direction.FORWARDS);
            this.swapAnim.reset();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public class_1792 getItemByType(String itemType) {
        return switch (itemType) {
            case "\u0422\u043e\u0442\u0435\u043c" -> class_1802.field_8288;
            case "\u0421\u0444\u0435\u0440\u0430" -> class_1802.field_8575;
            case "\u0413\u0435\u043f\u043b" -> class_1802.field_8463;
            case "\u0429\u0438\u0442" -> class_1802.field_8255;
            default -> class_1802.field_8162;
        };
    }

    public void setWheelSlotItem(int index, class_1792 item, String itemName) {
        if (index >= 0 && index < this.wheelSlots.length) {
            this.wheelSlots[index] = new WheelSlotItem(item, itemName);
        }
    }

    public void clearWheelSlot(int index) {
        if (index >= 0 && index < this.wheelSlots.length) {
            this.wheelSlots[index] = null;
        }
    }

    public boolean isWheelSlotEmpty(int index) {
        if (index < 0 || index >= this.wheelSlots.length) {
            return true;
        }
        WheelSlotItem slotItem = this.wheelSlots[index];
        return slotItem == null || slotItem.item == null || slotItem.item == class_1802.field_8162;
    }

    public void startSelectingItem(int wheelSlotIndex) {
        this.selectingSlotIndex = wheelSlotIndex;
        this.awaitingInventoryPick = true;
        if (mc != null && AutoSwap.mc.field_1724 != null) {
            mc.method_1507((class_437)new class_490((class_1657)AutoSwap.mc.field_1724));
        }
    }

    public class_1792 getWheelSlotItem(int index) {
        if (index >= 0 && index < this.wheelSlots.length && this.wheelSlots[index] != null) {
            return this.wheelSlots[index].item;
        }
        return null;
    }

    public class_1799 getWheelSlotStack(int index) {
        if (index < 0 || index >= this.wheelSlots.length) {
            Logger.warn("[AutoSwap] getWheelSlotStack: \u043d\u0435\u0432\u0430\u043b\u0438\u0434\u043d\u044b\u0439 \u0438\u043d\u0434\u0435\u043a\u0441 " + index);
            return class_1799.field_8037;
        }
        WheelSlotItem slotItem = this.wheelSlots[index];
        if (slotItem == null || slotItem.item == null || slotItem.item == class_1802.field_8162) {
            Logger.info("[AutoSwap] getWheelSlotStack: \u0441\u043b\u043e\u0442 " + index + " \u043f\u0443\u0441\u0442");
            return class_1799.field_8037;
        }
        if (mc == null || AutoSwap.mc.field_1724 == null) {
            Logger.warn("[AutoSwap] getWheelSlotStack: mc \u0438\u043b\u0438 player null");
            return class_1799.field_8037;
        }
        class_1661 inv = AutoSwap.mc.field_1724.method_31548();
        for (int i = 0; i < inv.method_5439(); ++i) {
            String stackName;
            class_1799 stack = inv.method_5438(i);
            if (stack.method_7960() || stack.method_7909() != slotItem.item || !(stackName = stack.method_7964().getString()).equals(slotItem.itemName)) continue;
            Logger.info("[AutoSwap] getWheelSlotStack: \u043d\u0430\u0439\u0434\u0435\u043d \u0441\u0442\u0435\u043a \u0434\u043b\u044f \u0441\u043b\u043e\u0442\u0430 " + index + ", item=" + String.valueOf(slotItem.item) + ", name=" + slotItem.itemName + ", \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435 \u0441\u043b\u043e\u0442 " + i);
            return stack;
        }
        Logger.warn("[AutoSwap] getWheelSlotStack: \u043f\u0440\u0435\u0434\u043c\u0435\u0442 " + String.valueOf(slotItem.item) + " \u0441 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435\u043c '" + slotItem.itemName + "' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435 \u0434\u043b\u044f \u0441\u043b\u043e\u0442\u0430 " + index);
        return class_1799.field_8037;
    }

    public void startSwapToItem(class_1792 item) {
        Logger.info("[AutoSwap] startSwapToItem \u0432\u044b\u0437\u0432\u0430\u043d: item=" + (item != null ? item.toString() : "null"));
        if (mc == null || AutoSwap.mc.field_1724 == null || item == null || item == class_1802.field_8162) {
            Logger.warn("[AutoSwap] startSwapToItem \u043e\u0442\u043c\u0435\u043d\u0435\u043d: mc=" + (mc != null) + ", player=" + (mc != null && AutoSwap.mc.field_1724 != null) + ", item=" + String.valueOf(item));
            return;
        }
        Logger.info("[AutoSwap] \u0418\u0449\u0435\u043c \u0441\u043b\u043e\u0442 \u0434\u043b\u044f \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430: " + String.valueOf(item));
        class_1735 slot = InventoryTask.getSlot(item, Comparator.comparing(s -> s.method_7677().method_7942()), s -> s.field_7874 != 46 && s.field_7874 != 45);
        if (slot != null) {
            class_1799 stackToSwap = slot.method_7677();
            this.swapOffhand(slot, () -> this.triggerSwapToast(stackToSwap));
        } else {
            Logger.warn("[AutoSwap] \u0421\u043b\u043e\u0442 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0434\u043b\u044f \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430: " + String.valueOf(item));
        }
    }

    public void startSwapToItemStack(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            Logger.warn("[AutoSwap] startSwapToItemStack: \u0441\u0442\u0435\u043a \u043f\u0443\u0441\u0442");
            return;
        }
        class_1792 item = stack.method_7909();
        String itemName = stack.method_7964().getString();
        Logger.info("[AutoSwap] startSwapToItemStack: item=" + String.valueOf(item) + ", name=" + itemName);
        if (mc == null || AutoSwap.mc.field_1724 == null || item == null || item == class_1802.field_8162) {
            Logger.warn("[AutoSwap] startSwapToItemStack \u043e\u0442\u043c\u0435\u043d\u0435\u043d");
            return;
        }
        class_1735 foundSlot = null;
        for (class_1735 slot : InventoryTask.slots().filter(s -> s.field_7874 != 46 && s.field_7874 != 45).toList()) {
            String slotName;
            class_1799 slotStack = slot.method_7677();
            if (slotStack.method_7960() || slotStack.method_7909() != item || !(slotName = slotStack.method_7964().getString()).equals(itemName)) continue;
            foundSlot = slot;
            break;
        }
        if (foundSlot != null) {
            class_1799 stackToSwap = foundSlot.method_7677();
            this.swapOffhand(foundSlot, () -> this.triggerSwapToast(stackToSwap));
        } else {
            Logger.warn("[AutoSwap] \u0421\u043b\u043e\u0442 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0434\u043b\u044f \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430: " + String.valueOf(item) + " \u0441 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435\u043c '" + itemName + "'");
        }
    }

    @Override
    public BindSetting getBind() {
        return this.bind;
    }

    public RadioSetting getMode() {
        return this.mode;
    }

    public SelectSetting getBypass() {
        return this.bypass;
    }

    public RadioSetting getFirstItem() {
        return this.firstItem;
    }

    public RadioSetting getSecondItem() {
        return this.secondItem;
    }

    public BooleanSetting getAutoDamage() {
        return this.autoDamage;
    }

    public MultiSelectSetting getAutoDamageConditions() {
        return this.autoDamageConditions;
    }

    public SliderSettings getLowHpThreshold() {
        return this.lowHpThreshold;
    }

    public class_1799 getSavedOffhandItem() {
        return this.savedOffhandItem;
    }

    public boolean isAutoDamageActive() {
        return this.autoDamageActive;
    }

    public class_2561 getLastSwapDisplayName() {
        return this.lastSwapDisplayName;
    }

    public long getLastSwapTimeMs() {
        return this.lastSwapTimeMs;
    }

    public Animation getSwapAnim() {
        return this.swapAnim;
    }

    public Script getScript() {
        return this.script;
    }

    public WheelSlotItem[] getWheelSlots() {
        return this.wheelSlots;
    }

    public Integer getSelectingSlotIndex() {
        return this.selectingSlotIndex;
    }

    public boolean isAwaitingInventoryPick() {
        return this.awaitingInventoryPick;
    }

    private static class WheelSlotItem {
        final class_1792 item;
        final String itemName;

        WheelSlotItem(class_1792 item, String itemName) {
            this.item = item;
            this.itemName = itemName;
        }
    }
}
