/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1304
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 *  net.minecraft.class_2868
 *  net.minecraft.class_2886
 *  net.minecraft.class_437
 *  net.minecraft.class_490
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemTask;
import code.alphadlc.utils.math.script.Script;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2815;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_437;
import net.minecraft.class_490;

public class ElytraHelper
extends Module {
    private final BindSetting elytraSetting = new BindSetting("\u0421\u0432\u0430\u043f \u044d\u043b\u0438\u0442\u0440", "\u041c\u0435\u043d\u044f\u0435\u0442 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u044b");
    private final BindSetting fireworkSetting = new BindSetting("\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435");
    private final RadioSetting fireworkMode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430", "\u0421\u043f\u043e\u0441\u043e\u0431 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430", new String[]{"\u041b\u0435\u0433\u0438\u0442\u043d\u043e", "\u041f\u0430\u043a\u0435\u0442\u043d\u043e"}, "\u041b\u0435\u0433\u0438\u0442\u043d\u043e").visible(() -> this.fireworkSetting.getKey() != -1);
    private final BooleanSetting hotbarOnly = new BooleanSetting("\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0438 \u0442\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", "\u0411\u0435\u0440\u0451\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u0442\u043e\u043b\u044c\u043a\u043e \u0438\u0437 \u0445\u043e\u0442\u0431\u0430\u0440\u0430").setValue(false).visible(() -> this.fireworkSetting.getKey() != -1);
    private final SliderSettings hotbarSlot = new SliderSettings("\u0421\u043b\u043e\u0442 \u0445\u043e\u0442\u0431\u0430\u0440\u0430 (1-9)", "\u0421\u043b\u043e\u0442 \u0434\u043b\u044f \u043f\u0435\u0440\u0435\u043c\u0435\u0449\u0435\u043d\u0438\u044f \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430").setValue(9.0f).range(1.0f, 9.0f).step(1.0f).visible(() -> this.hotbarOnly.isValue() && this.fireworkSetting.getKey() != -1);
    private final SliderSettings swapDelay = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043c\u0435\u0436\u0434\u0443 \u0441\u0432\u0430\u043f\u0430\u043c\u0438 (\u0442\u0438\u043a\u0438)").setValue(0.0f).range(0.0f, 20.0f).step(1.0f);
    private final BooleanSetting removeOnLand = new BooleanSetting("\u0421\u043d\u0438\u043c\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u044b \u043f\u0440\u0438 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u0438", "\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0441\u043d\u0438\u043c\u0430\u0435\u0442 \u044d\u043b\u0438\u0442\u0440\u0443 \u043d\u0430 \u0437\u0435\u043c\u043b\u0435").setValue(false).visible(() -> this.elytraSetting.getKey() != -1);
    private final BooleanSetting autoTakeoff = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0432\u0437\u043b\u0435\u0442\u0430\u0442\u044c", "\u0412\u0437\u043b\u0435\u0442\u0430\u0435\u0442 \u043f\u0440\u0438 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u0438 \u0441 \u044d\u043b\u0438\u0442\u0440\u043e\u0439").setValue(false);
    private final BooleanSetting fireworkOnTakeoff = new BooleanSetting("\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u043f\u0440\u0438 \u0432\u0437\u043b\u0451\u0442\u0435", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u043f\u0440\u0438 \u0441\u0442\u0430\u0440\u0442\u0435 \u043f\u043e\u043b\u0451\u0442\u0430").setValue(false);
    private final BooleanSetting startSetting = new BooleanSetting("\u0411\u044b\u0441\u0442\u0440\u044b\u0439 \u0441\u0442\u0430\u0440\u0442", "\u041f\u0440\u0438 \u0441\u0432\u0430\u043f\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443 \u0441\u0440\u0430\u0437\u0443 \u0432\u0437\u043b\u0435\u0442\u0430\u0435\u0442 \u0438 \u0436\u0436\u0451\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a").setValue(false);
    private final BooleanSetting equipInCT = new BooleanSetting("\u041d\u0430\u0434\u0435\u0432\u0430\u0442\u044c \u0432 \u043a\u0442", "\u041e\u0431\u0445\u043e\u0434 \u0441\u0432\u0430\u043f\u0430 \u0432 \u043a\u0442 \u0447\u0435\u0440\u0435\u0437 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c").setValue(false);
    private final Script script = new Script();
    private boolean swapQueued;
    private int swapCooldown;
    private boolean wasOnGround = true;
    private boolean ctSwapPending;
    private int ctSwapTicks;
    public static boolean start = false;

    public ElytraHelper() {
        super("ElytraHelper", "ElytraHelper", ModuleCategory.MISC);
        this.setup(this.elytraSetting, this.fireworkSetting, this.fireworkMode, this.hotbarOnly, this.hotbarSlot, this.swapDelay, this.removeOnLand, this.autoTakeoff, this.fireworkOnTakeoff, this.startSetting, this.equipInCT);
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (!this.autoTakeoff.isValue() || ElytraHelper.mc.field_1724 == null) {
            return;
        }
        if (!ElytraHelper.mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
            return;
        }
        if (ElytraHelper.mc.field_1724.method_24828()) {
            event.setJumping(true);
        } else if (!ElytraHelper.mc.field_1724.method_6128()) {
            PlayerInteractionHelper.startFallFlying();
            if (this.fireworkOnTakeoff.isValue()) {
                this.useFirework();
            }
        }
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        if (!this.script.isFinished() || ElytraHelper.mc.field_1724 == null) {
            return;
        }
        if (event.isKeyDown(this.elytraSetting.getKey()) && this.swapCooldown == 0) {
            boolean hasChest;
            boolean hasElytra = InventoryTask.getSlot(class_1802.field_8833) != null;
            boolean bl = hasChest = this.chestPlate() != null;
            if (!hasElytra && !hasChest) {
                return;
            }
            if (this.equipInCT.isValue()) {
                this.changeChestPlate();
            } else if (this.chestPlate() != null) {
                this.swapQueued = true;
            }
        } else if (event.isKeyDown(this.fireworkSetting.getKey()) && ElytraHelper.mc.field_1724.method_6128()) {
            this.useFirework();
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        this.script.update();
        if (ElytraHelper.mc.field_1724 == null) {
            return;
        }
        if (this.swapCooldown > 0) {
            --this.swapCooldown;
        }
        if (this.ctSwapPending) {
            this.handleCtSwap();
        }
        if (this.removeOnLand.isValue() && !this.wasOnGround && ElytraHelper.mc.field_1724.method_24828() && ElytraHelper.mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833) && this.chestPlate() != null && this.swapCooldown == 0) {
            this.performSwap();
        }
        this.wasOnGround = ElytraHelper.mc.field_1724.method_24828();
        if (this.swapQueued && this.swapCooldown == 0) {
            this.swapQueued = false;
            class_1735 slot = this.chestPlate();
            if (slot == null) {
                return;
            }
            boolean puttingElytra = slot.method_7677().method_31574(class_1802.field_8833);
            class_1735 firework = puttingElytra ? InventoryTask.getSlot(class_1802.field_8639) : null;
            this.performSwap();
            if (this.startSetting.isValue() && firework != null && puttingElytra) {
                this.script.cleanup().addTickStep(4, () -> {
                    if (ElytraHelper.mc.field_1724.method_24828()) {
                        ElytraHelper.mc.field_1724.method_6043();
                    }
                }).addTickStep(3, () -> {
                    if (ItemTask.getCooldownProgress(class_1802.field_8639) > 0.0f) {
                        this.notifyFireworkCooldown();
                        return;
                    }
                    PlayerInteractionHelper.startFallFlying();
                    InventoryTask.swapAndUse(class_1802.field_8639, false);
                });
            }
        }
    }

    private void performSwap() {
        class_1735 slot = this.chestPlate();
        if (slot == null) {
            return;
        }
        InventoryTask.moveItem(slot, 6, !GuiMove.mode.isSelected("Reallyworld"), true);
        this.swapCooldown = Math.round(this.swapDelay.getValue());
    }

    private void handleCtSwap() {
        class_1735 slot;
        ++this.ctSwapTicks;
        if (this.ctSwapTicks < 2) {
            return;
        }
        GuiMove guiMove = GuiMove.getInstance();
        if (guiMove != null && guiMove.isState()) {
            guiMove.setState(false);
        }
        if ((slot = this.chestPlate()) != null) {
            InventoryTask.moveItem(slot, 6, false, true);
        }
        if (mc.method_1562() != null) {
            mc.method_1562().method_52787((class_2596)new class_2815(ElytraHelper.mc.field_1724.field_7512.field_7763));
        }
        mc.method_1507(null);
        if (guiMove != null) {
            guiMove.setState(true);
        }
        this.ctSwapPending = false;
        this.ctSwapTicks = 0;
        this.swapCooldown = Math.round(this.swapDelay.getValue());
    }

    private class_1735 chestPlate() {
        if (Objects.requireNonNull(ElytraHelper.mc.field_1724).method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
            return InventoryTask.getSlot(List.of(class_1802.field_22028, class_1802.field_8058, class_1802.field_8873, class_1802.field_8523, class_1802.field_8678, class_1802.field_8577));
        }
        return InventoryTask.getSlot(class_1802.field_8833);
    }

    public void changeChestPlate() {
        class_1799 chestStack = ElytraHelper.mc.field_1724.method_6118(class_1304.field_6174);
        if (chestStack == null || chestStack.method_7909() != class_1802.field_8833) {
            int elytraSlot = this.getItemSlot(class_1802.field_8833);
            if (elytraSlot == -1) {
                ChatMessage.brandmessage("\u041d\u0435\u0442\u0443: \u042d\u043b\u0438\u0442\u0440\u0430");
                return;
            }
            if (GuiMove.getInstance() != null && GuiMove.getInstance().isState()) {
                mc.method_1507((class_437)new class_490((class_1657)ElytraHelper.mc.field_1724));
                this.ctSwapPending = true;
                this.ctSwapTicks = 0;
                InventoryTask.clickSlot(elytraSlot, 38, class_1713.field_7791, false, true);
                ChatMessage.brandmessage("\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443");
            } else {
                InventoryTask.clickSlot(elytraSlot, 38, class_1713.field_7791, false, true);
                ChatMessage.brandmessage("\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443");
            }
        } else {
            int chestSlot = ElytraHelper.getChestPlateSlot();
            if (chestSlot == -1) {
                ChatMessage.brandmessage("\u041d\u0435\u0442 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a\u0430!");
                return;
            }
            if (GuiMove.getInstance() != null && GuiMove.getInstance().isState()) {
                mc.method_1507((class_437)new class_490((class_1657)ElytraHelper.mc.field_1724));
                this.ctSwapPending = true;
                this.ctSwapTicks = 0;
                InventoryTask.clickSlot(chestSlot, 38, class_1713.field_7791, false, true);
                ChatMessage.brandmessage("\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a (\u043e\u0431\u043e\u0448\u0451\u043b \u0437\u0430\u043f\u0440\u0435\u0442 \u0441\u0432\u0430\u043f\u0430 \u0432 \u043a\u0442)");
            } else {
                InventoryTask.clickSlot(chestSlot, 38, class_1713.field_7791, false, true);
                ChatMessage.brandmessage("\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a");
            }
        }
    }

    private void useFirework() {
        if (ElytraHelper.mc.field_1724 == null || !ElytraHelper.mc.field_1724.method_6128()) {
            return;
        }
        if (ItemTask.getCooldownProgress(class_1802.field_8639) > 0.0f) {
            this.notifyFireworkCooldown();
            return;
        }
        if ("\u041f\u0430\u043a\u0435\u0442\u043d\u043e".equals(this.fireworkMode.get())) {
            this.useFireworkPacket();
            return;
        }
        this.useFireworkLegit();
    }

    private void useFireworkLegit() {
        if (this.hotbarOnly.isValue()) {
            int hotbarIndex = Math.round(this.hotbarSlot.getValue()) - 1;
            int rocketSlot = InventoryTask.findHotbarSlot(class_1802.field_8639);
            if (rocketSlot == -1) {
                int invSlot = InventoryTask.findInventorySlot(class_1802.field_8639);
                if (invSlot == -1) {
                    ChatMessage.brandmessage("\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u0435\u0440\u0435\u043c\u0435\u0441\u0442\u0438\u0442\u044c \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u0432 \u0445\u043e\u0442\u0431\u0430\u0440");
                    return;
                }
                InventoryTask.clickSlot(invSlot, hotbarIndex, class_1713.field_7791, false, true);
                rocketSlot = hotbarIndex;
            }
            int previous = ElytraHelper.mc.field_1724.method_31548().field_7545;
            InventoryTask.switchTo(rocketSlot);
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            ElytraHelper.mc.field_1724.method_6104(class_1268.field_5808);
            InventoryTask.switchTo(previous);
            return;
        }
        if (ElytraHelper.mc.field_1724.method_6047().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            ElytraHelper.mc.field_1724.method_6104(class_1268.field_5808);
            return;
        }
        if (ElytraHelper.mc.field_1724.method_6079().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.interactItem(class_1268.field_5810);
            ElytraHelper.mc.field_1724.method_6104(class_1268.field_5810);
            return;
        }
        int rocketHotbarSlot = InventoryTask.findHotbarSlot(class_1802.field_8639);
        if (rocketHotbarSlot != -1) {
            InventoryTask.switchTo(rocketHotbarSlot);
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            ElytraHelper.mc.field_1724.method_6104(class_1268.field_5808);
            return;
        }
        class_1735 slot = InventoryTask.getSlot(class_1802.field_8639);
        if (slot == null) {
            return;
        }
        InventoryTask.swapHand(slot, class_1268.field_5810, false);
        PlayerInteractionHelper.interactItem(class_1268.field_5810);
        ElytraHelper.mc.field_1724.method_6104(class_1268.field_5810);
    }

    private void useFireworkPacket() {
        if (mc.method_1562() == null) {
            return;
        }
        if (ItemTask.getCooldownProgress(class_1802.field_8639) > 0.0f) {
            this.notifyFireworkCooldown();
            return;
        }
        if (ElytraHelper.mc.field_1724.method_6047().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5808, seq, ElytraHelper.mc.field_1724.method_36454(), ElytraHelper.mc.field_1724.method_36455()));
            return;
        }
        if (ElytraHelper.mc.field_1724.method_6079().method_31574(class_1802.field_8639)) {
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5810, seq, ElytraHelper.mc.field_1724.method_36454(), ElytraHelper.mc.field_1724.method_36455()));
            return;
        }
        int rocketHotbarSlot = InventoryTask.findHotbarSlot(class_1802.field_8639);
        if (rocketHotbarSlot != -1) {
            int previousSlot = ElytraHelper.mc.field_1724.method_31548().field_7545;
            if (rocketHotbarSlot != previousSlot) {
                mc.method_1562().method_52787((class_2596)new class_2868(rocketHotbarSlot));
            }
            PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5808, seq, ElytraHelper.mc.field_1724.method_36454(), ElytraHelper.mc.field_1724.method_36455()));
            if (rocketHotbarSlot != previousSlot) {
                mc.method_1562().method_52787((class_2596)new class_2868(previousSlot));
            }
            return;
        }
        class_1735 slot = InventoryTask.getSlot(class_1802.field_8639);
        if (slot == null) {
            ChatMessage.brandmessage("\u00a7c\u041d\u0435\u0442 \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a\u0430 \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435");
            return;
        }
        InventoryTask.swapHand(slot, class_1268.field_5810, false);
        PlayerInteractionHelper.sendSequencedPacket(seq -> new class_2886(class_1268.field_5810, seq, ElytraHelper.mc.field_1724.method_36454(), ElytraHelper.mc.field_1724.method_36455()));
        InventoryTask.swapHand(slot, class_1268.field_5810, false, true);
    }

    private void notifyFireworkCooldown() {
        String time = String.format("%.1f", Float.valueOf(ItemTask.getCooldownProgress(class_1802.field_8639))) + "\u0441";
        Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a" + String.valueOf(class_124.field_1070) + " - \u0432 \u043a\u0434 \u0435\u0449\u0435 " + time, 2000);
    }

    private int getItemSlot(class_1792 input) {
        int slot = -1;
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = ElytraHelper.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != input) continue;
            slot = i;
            break;
        }
        if (slot < 9 && slot != -1) {
            slot += 36;
        }
        return slot;
    }

    public static int getChestPlateSlot() {
        class_1792[] items;
        for (class_1792 item : items = new class_1792[]{class_1802.field_22028, class_1802.field_8058, class_1802.field_8577}) {
            for (int i = 0; i < 36; ++i) {
                class_1792 stack = ElytraHelper.mc.field_1724.method_31548().method_5438(i).method_7909();
                if (stack != item) continue;
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
}
