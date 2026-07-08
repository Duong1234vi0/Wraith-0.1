/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1268
 *  net.minecraft.class_1511
 *  net.minecraft.class_1541
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1890
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2663
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_742
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.script.Script;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1511;
import net.minecraft.class_1541;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_9334;

public class AutoTotem
extends Module {
    private SliderSettings healthThreshold = new SliderSettings("\u041f\u043e\u0440\u043e\u0433 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").setValue(4.5f).range(1.0f, 20.0f).step(0.5f);
    private MultiSelectSetting settings = new MultiSelectSetting("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", "\u0414\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u044b\u0435 \u0443\u0441\u043b\u043e\u0432\u0438\u044f \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").value("\u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u0442\u0430\u043b\u0438\u043a\u0438", "\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0435\u0441\u043b\u0438 \u0448\u0430\u0440", "\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0435\u0441\u043b\u0438 \u0435\u0448\u044c").selected("\u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u0442\u0430\u043b\u0438\u043a\u0438", "\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442");
    private MultiSelectSetting modes = new MultiSelectSetting("\u0423\u0447\u0438\u0442\u044b\u0432\u0430\u0442\u044c", "\u0414\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u044b\u0435 \u0443\u0441\u043b\u043e\u0432\u0438\u044f \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").value("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", "\u041f\u0430\u0434\u0435\u043d\u0438\u0435", "\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0446\u0430", "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u044b", "\u041e\u0431\u0441\u0438\u0434\u0438\u0430\u043d", "\u0411\u0443\u043b\u0430\u0432\u0430", "\u0414\u0438\u043d\u0430\u043c\u0438\u0442").selected("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", "\u041f\u0430\u0434\u0435\u043d\u0438\u0435", "\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0446\u0430", "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u044b", "\u041e\u0431\u0441\u0438\u0434\u0438\u0430\u043d", "\u0411\u0443\u043b\u0430\u0432\u0430");
    private SliderSettings elytraHealth = new SliderSettings("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430 \u043f\u0440\u0438 \u043f\u043e\u043b\u0451\u0442\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435").setValue(8.5f).range(1.0f, 20.0f).visible(() -> this.modes.isSelected("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435"));
    private SliderSettings crystalDistance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u0430", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430 \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").setValue(4.0f).range(1.0f, 6.0f).visible(() -> this.modes.isSelected("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u044b"));
    private SliderSettings obsidianDistance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d\u0430", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d\u0430 \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").setValue(4.0f).range(1.0f, 6.0f).visible(() -> this.modes.isSelected("\u041e\u0431\u0441\u0438\u0434\u0438\u0430\u043d"));
    private SliderSettings tntDistance = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u0434\u0438\u043d\u0430\u043c\u0438\u0442\u0430", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0434\u043e \u0434\u0438\u043d\u0430\u043c\u0438\u0442\u0430 \u0434\u043b\u044f \u044d\u043a\u0438\u043f\u0438\u0440\u043e\u0432\u043a\u0438 \u0442\u043e\u0442\u0435\u043c\u0430").setValue(10.0f).range(1.0f, 50.0f).visible(() -> this.modes.isSelected("\u0414\u0438\u043d\u0430\u043c\u0438\u0442"));
    private BooleanSetting drawCounter = new BooleanSetting("\u0420\u0435\u043d\u0434\u0435\u0440\u0438\u0442\u044c \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e", "\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0442\u043e\u0442\u0435\u043c\u043e\u0432 \u043d\u0430 \u044d\u043a\u0440\u0430\u043d\u0435").setValue(false);
    private final Script script = new Script();
    private int oldSlot = -1;
    private class_1799 backItemStack = class_1799.field_8037;
    private boolean totemIsUsed = false;
    private long lastTotemUseTime = 0L;
    private boolean returningItem = false;
    private boolean swappingTotem = false;
    private long lastSwapAttempt = 0L;
    private long lastSuccessfulSwap = 0L;

    public AutoTotem() {
        super("AutoTotem", "AutoTotem", ModuleCategory.COMBAT);
        this.setup(this.healthThreshold, this.settings, this.modes, this.elytraHealth, this.crystalDistance, this.obsidianDistance, this.tntDistance, this.drawCounter);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        boolean shouldReturn;
        boolean hasNormalTotem;
        boolean isTotemOnCooldown;
        double dist;
        boolean shouldSwap;
        float health;
        if (AutoTotem.mc.field_1724 == null || AutoTotem.mc.field_1687 == null || AutoTotem.mc.field_1761 == null) {
            return;
        }
        this.script.update();
        float effectiveHealth = health = AutoTotem.mc.field_1724.method_6032();
        if (this.modes.isSelected("\u0417\u043e\u043b\u043e\u0442\u044b\u0435 \u0441\u0435\u0440\u0446\u0430")) {
            effectiveHealth += AutoTotem.mc.field_1724.method_6067();
        }
        boolean bl = shouldSwap = effectiveHealth <= this.healthThreshold.getValue() || this.totemIsUsed && this.getTotemCount() > 0 && System.currentTimeMillis() - this.lastTotemUseTime >= 500L;
        if (this.modes.isSelected("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435") && AutoTotem.mc.field_1724.method_6128() && health <= this.elytraHealth.getValue()) {
            shouldSwap = true;
        }
        if (this.modes.isSelected("\u041f\u0430\u0434\u0435\u043d\u0438\u0435") && AutoTotem.mc.field_1724.field_6017 > 10.0f) {
            shouldSwap = true;
        }
        if (this.modes.isSelected("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u044b") && (dist = this.getClosestCrystalDistance()) <= (double)this.crystalDistance.getValue()) {
            shouldSwap = this.settings.isSelected("\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0435\u0441\u043b\u0438 \u0448\u0430\u0440") && this.isHoldingSkull() ? effectiveHealth <= this.healthThreshold.getValue() : true;
        }
        if (this.modes.isSelected("\u041e\u0431\u0441\u0438\u0434\u0438\u0430\u043d") && (dist = this.getClosestObsidianDistance()) <= (double)this.obsidianDistance.getValue()) {
            shouldSwap = this.settings.isSelected("\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0435\u0441\u043b\u0438 \u0448\u0430\u0440") && this.isHoldingSkull() ? effectiveHealth <= this.healthThreshold.getValue() : true;
        }
        if (this.modes.isSelected("\u0414\u0438\u043d\u0430\u043c\u0438\u0442") && (dist = this.getClosestTntDistance()) <= (double)this.tntDistance.getValue()) {
            shouldSwap = true;
        }
        if (this.modes.isSelected("\u0411\u0443\u043b\u0430\u0432\u0430") && this.checkForMaceInEnemyHand()) {
            shouldSwap = true;
        }
        if (this.modes.isSelected("\u041d\u0435 \u0431\u0440\u0430\u0442\u044c \u0435\u0441\u043b\u0438 \u0435\u0448\u044c") && AutoTotem.mc.field_1724.method_6115() && AutoTotem.mc.field_1724.method_6030().method_57826(class_9334.field_50075)) {
            shouldSwap = false;
        }
        class_1799 offhandStack = AutoTotem.mc.field_1724.method_6079();
        boolean isTotemInOffhand = this.isTotemInOffhand();
        boolean isEnchantedTotemInOffhand = offhandStack.method_7909() == class_1802.field_8288 && class_1890.method_58117((class_1799)offhandStack);
        boolean bl2 = isTotemOnCooldown = isTotemInOffhand && AutoTotem.mc.field_1724.method_7357().method_7904(class_1802.field_8288.method_7854());
        if (isTotemOnCooldown && this.backItemStack != class_1799.field_8037 && this.oldSlot != -1 && this.settings.isSelected("\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442") && !this.returningItem) {
            class_1735 returnSlot = InventoryTask.slots().filter(s -> s.field_7874 == this.oldSlot).findFirst().orElse(null);
            if (returnSlot != null && !returnSlot.method_7677().method_7960() && returnSlot.method_7677().method_7909() == this.backItemStack.method_7909()) {
                if (InventoryFlowManager.script.isFinished()) {
                    this.returningItem = true;
                    boolean instant = GuiMove.mode.isSelected("Reallyworld") || GuiMove.mode.isSelected("Vanilla");
                    InventoryTask.swapHand(returnSlot, class_1268.field_5810, !instant, true);
                    this.backItemStack = class_1799.field_8037;
                    this.oldSlot = -1;
                    this.returningItem = false;
                    return;
                }
            } else {
                this.oldSlot = -1;
                this.backItemStack = class_1799.field_8037;
            }
        }
        boolean bl3 = hasNormalTotem = this.isTotemInOffhand() && !isEnchantedTotemInOffhand;
        if (hasNormalTotem && !shouldSwap) {
            this.swappingTotem = false;
        }
        boolean totemWasUsed = this.totemIsUsed && !this.isTotemInOffhand() && System.currentTimeMillis() - this.lastTotemUseTime > 200L;
        boolean canReturnAfterUse = totemWasUsed && AutoTotem.mc.field_1724.method_24828() && AutoTotem.mc.field_1724.field_6017 < 6.0f;
        boolean bl4 = shouldReturn = !shouldSwap && this.isTotemInOffhand() || canReturnAfterUse;
        if (shouldReturn && this.oldSlot != -1 && this.settings.isSelected("\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442")) {
            class_1735 returnSlot = InventoryTask.slots().filter(s -> s.field_7874 == this.oldSlot).findFirst().orElse(null);
            if (returnSlot != null && !returnSlot.method_7677().method_7960() && returnSlot.method_7677().method_7909() == this.backItemStack.method_7909()) {
                if (InventoryFlowManager.script.isFinished() && !this.returningItem) {
                    this.returningItem = true;
                    boolean instant = GuiMove.mode.isSelected("Reallyworld") || GuiMove.mode.isSelected("Vanilla");
                    InventoryTask.swapHand(returnSlot, class_1268.field_5810, !instant, true);
                    this.backItemStack = class_1799.field_8037;
                    this.oldSlot = -1;
                    this.totemIsUsed = false;
                    this.returningItem = false;
                    return;
                }
            } else {
                this.oldSlot = -1;
                this.backItemStack = class_1799.field_8037;
            }
        } else if (this.returningItem && !shouldReturn) {
            this.returningItem = false;
        }
        if (shouldSwap && (!hasNormalTotem || isEnchantedTotemInOffhand)) {
            if (hasNormalTotem && !isEnchantedTotemInOffhand) {
                this.swappingTotem = false;
                return;
            }
            if (this.swappingTotem) {
                return;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastSwapAttempt < 100L || currentTime - this.lastSuccessfulSwap < 200L) {
                return;
            }
            if (!InventoryFlowManager.script.isFinished()) {
                return;
            }
            class_1735 totemSlot = this.findTotemSlot();
            if (totemSlot == null) {
                return;
            }
            if (AutoTotem.mc.field_1724.method_7357().method_7904(class_1802.field_8288.method_7854())) {
                if (this.backItemStack != class_1799.field_8037 && this.oldSlot != -1 && this.settings.isSelected("\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442") && !this.returningItem) {
                    class_1735 returnSlot = InventoryTask.slots().filter(s -> s.field_7874 == this.oldSlot).findFirst().orElse(null);
                    if (returnSlot != null && !returnSlot.method_7677().method_7960() && returnSlot.method_7677().method_7909() == this.backItemStack.method_7909()) {
                        if (InventoryFlowManager.script.isFinished()) {
                            this.returningItem = true;
                            boolean instant = GuiMove.mode.isSelected("Reallyworld") || GuiMove.mode.isSelected("Vanilla");
                            InventoryTask.swapHand(returnSlot, class_1268.field_5810, !instant, true);
                            this.backItemStack = class_1799.field_8037;
                            this.oldSlot = -1;
                            this.returningItem = false;
                            return;
                        }
                    } else {
                        this.oldSlot = -1;
                        this.backItemStack = class_1799.field_8037;
                    }
                }
                return;
            }
            if (this.isTotemInOffhand()) {
                class_1799 currentOffhand = AutoTotem.mc.field_1724.method_6079();
                class_1799 foundTotem = totemSlot.method_7677();
                if (currentOffhand.method_7909() == foundTotem.method_7909()) {
                    boolean foundEnchanted;
                    boolean currentEnchanted = class_1890.method_58117((class_1799)currentOffhand);
                    if (currentEnchanted == (foundEnchanted = class_1890.method_58117((class_1799)foundTotem))) {
                        return;
                    }
                    if (!currentEnchanted && foundEnchanted) {
                        return;
                    }
                }
            }
            this.lastSwapAttempt = currentTime;
            this.swappingTotem = true;
            if (!offhandStack.method_7960() && this.oldSlot == -1 && this.settings.isSelected("\u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442")) {
                this.oldSlot = totemSlot.field_7874;
                this.backItemStack = offhandStack.method_7972();
            }
            boolean instant = GuiMove.mode.isSelected("Reallyworld") || GuiMove.mode.isSelected("Vanilla");
            InventoryTask.swapHand(totemSlot, class_1268.field_5810, !instant, true);
            this.totemIsUsed = false;
            this.script.cleanup().addTickStep(2, () -> {
                if (this.isTotemInOffhand() && !class_1890.method_58117((class_1799)AutoTotem.mc.field_1724.method_6079())) {
                    this.lastSuccessfulSwap = System.currentTimeMillis();
                }
                this.swappingTotem = false;
            });
        } else if (this.swappingTotem && hasNormalTotem) {
            this.swappingTotem = false;
        }
    }

    private boolean isHoldingSkull() {
        class_1799 mainHand = AutoTotem.mc.field_1724.method_6047();
        class_1799 offHand = AutoTotem.mc.field_1724.method_6079();
        return this.isSkull(mainHand) || this.isSkull(offHand);
    }

    private boolean isSkull(class_1799 stack) {
        if (stack.method_7960()) {
            return false;
        }
        return stack.method_7909() == class_1802.field_8398 || stack.method_7909() == class_1802.field_8791 || stack.method_7909() == class_1802.field_8470 || stack.method_7909() == class_1802.field_8575 || stack.method_7909() == class_1802.field_8681 || stack.method_7909() == class_1802.field_8712 || stack.method_7909() == class_1802.field_41304;
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_2663 statusPacket;
        class_2596<?> class_25962 = event.getPacket();
        if (class_25962 instanceof class_2663 && (statusPacket = (class_2663)class_25962).method_11470() == 35 && statusPacket.method_11469((class_1937)AutoTotem.mc.field_1687) == AutoTotem.mc.field_1724) {
            this.totemIsUsed = true;
            this.lastTotemUseTime = System.currentTimeMillis();
        }
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (!this.drawCounter.isValue() || AutoTotem.mc.field_1724 == null || AutoTotem.mc.field_1687 == null || AutoTotem.mc.field_1724.method_7337()) {
            return;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        float x = (float)mc.method_22683().method_4486() / 2.0f + 120.0f;
        float y = mc.method_22683().method_4502() - 17;
        int totemX = (int)(x - 128.0f);
        int totemY = (int)(y - 37.0f);
        class_1799 totemStack = new class_1799((class_1935)class_1802.field_8288);
        RenderSystem.disableBlend();
        Render2D.defaultDrawStack(context, totemStack, totemX, totemY, false, false, 1.0f);
        RenderSystem.enableBlend();
        String count = String.valueOf(this.getTotemCount());
        int countWidth = AutoTotem.mc.field_1772.method_1727(count);
        matrix.method_22903();
        matrix.method_46416(0.0f, 0.0f, 300.0f);
        context.method_51433(AutoTotem.mc.field_1772, count, (int)((float)totemX + 16.5f - (float)countWidth), totemY + 9, 0xFFFFFF, true);
        matrix.method_22909();
    }

    private boolean checkForMaceInEnemyHand() {
        if (!this.modes.isSelected("\u0411\u0443\u043b\u0430\u0432\u0430")) {
            return false;
        }
        class_238 box = AutoTotem.mc.field_1724.method_5829().method_1014(30.0);
        List players = AutoTotem.mc.field_1687.method_8390(class_742.class, box, e -> true);
        for (class_742 enemy : players) {
            if (enemy == AutoTotem.mc.field_1724) continue;
            class_1799 main = enemy.method_6047();
            class_1799 off = enemy.method_6079();
            if (main.method_7909() != class_1802.field_49814 && off.method_7909() != class_1802.field_49814) continue;
            return true;
        }
        return false;
    }

    private double getClosestCrystalDistance() {
        double minDist = Double.MAX_VALUE;
        class_243 playerPos = AutoTotem.mc.field_1724.method_19538();
        class_238 box = AutoTotem.mc.field_1724.method_5829().method_1014((double)this.crystalDistance.getValue());
        List crystals = AutoTotem.mc.field_1687.method_8390(class_1511.class, box, e -> true);
        for (class_1511 crystal : crystals) {
            double dist = playerPos.method_1022(crystal.method_19538());
            if (!(dist < minDist)) continue;
            minDist = dist;
        }
        return minDist;
    }

    private double getClosestObsidianDistance() {
        double minDist = Double.MAX_VALUE;
        class_2338 playerBlockPos = AutoTotem.mc.field_1724.method_24515();
        int dist = (int)Math.ceil(this.obsidianDistance.getValue());
        for (int x = -dist; x <= dist; ++x) {
            for (int y = -dist; y <= dist; ++y) {
                for (int z = -dist; z <= dist; ++z) {
                    double d;
                    class_2338 pos = playerBlockPos.method_10069(x, y, z);
                    if (!AutoTotem.mc.field_1687.method_8320(pos).method_27852(class_2246.field_10540) || !((d = (double)class_3532.method_15355((float)((float)playerBlockPos.method_10262((class_2382)pos)))) < minDist)) continue;
                    minDist = d;
                }
            }
        }
        return minDist;
    }

    private double getClosestTntDistance() {
        double minDist = Double.MAX_VALUE;
        class_243 playerPos = AutoTotem.mc.field_1724.method_19538();
        class_238 box = AutoTotem.mc.field_1724.method_5829().method_1014((double)this.tntDistance.getValue());
        List tntEntities = AutoTotem.mc.field_1687.method_8390(class_1541.class, box, e -> true);
        for (class_1541 tnt : tntEntities) {
            double dist = playerPos.method_1022(tnt.method_19538());
            if (!(dist < minDist)) continue;
            minDist = dist;
        }
        return minDist;
    }

    private boolean isTotemInOffhand() {
        class_1799 offhandStack = AutoTotem.mc.field_1724.method_6079();
        return offhandStack.method_7909() == class_1802.field_8288;
    }

    private class_1735 findTotemSlot() {
        if (this.settings.isSelected("\u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u0442\u0430\u043b\u0438\u043a\u0438")) {
            class_1735 nonEnchantedSlot = null;
            class_1735 enchantedSlot = null;
            for (class_1735 slot : InventoryTask.slots().toList()) {
                class_1799 stack = slot.method_7677();
                if (stack.method_7909() != class_1802.field_8288) continue;
                if (!class_1890.method_58117((class_1799)stack)) {
                    nonEnchantedSlot = slot;
                    continue;
                }
                enchantedSlot = slot;
            }
            if (nonEnchantedSlot != null) {
                return nonEnchantedSlot;
            }
            if (enchantedSlot != null) {
                return enchantedSlot;
            }
        } else {
            return InventoryTask.getSlot(class_1802.field_8288);
        }
        return null;
    }

    private int getTotemCount() {
        return (int)AutoTotem.mc.field_1724.method_31548().field_7547.stream().filter(s -> s.method_7909() == class_1802.field_8288).count();
    }

    @Override
    public void deactivate() {
        this.oldSlot = -1;
        this.backItemStack = class_1799.field_8037;
        this.totemIsUsed = false;
        this.lastTotemUseTime = 0L;
        this.returningItem = false;
        this.swappingTotem = false;
        this.lastSwapAttempt = 0L;
        this.lastSuccessfulSwap = 0L;
        this.script.cleanup();
        super.deactivate();
    }

    public SliderSettings getHealthThreshold() {
        return this.healthThreshold;
    }

    public MultiSelectSetting getSettings() {
        return this.settings;
    }

    public MultiSelectSetting getModes() {
        return this.modes;
    }

    public SliderSettings getElytraHealth() {
        return this.elytraHealth;
    }

    public SliderSettings getCrystalDistance() {
        return this.crystalDistance;
    }

    public SliderSettings getObsidianDistance() {
        return this.obsidianDistance;
    }

    public SliderSettings getTntDistance() {
        return this.tntDistance;
    }

    public BooleanSetting getDrawCounter() {
        return this.drawCounter;
    }

    public Script getScript() {
        return this.script;
    }

    public int getOldSlot() {
        return this.oldSlot;
    }

    public class_1799 getBackItemStack() {
        return this.backItemStack;
    }

    public boolean isTotemIsUsed() {
        return this.totemIsUsed;
    }

    public long getLastTotemUseTime() {
        return this.lastTotemUseTime;
    }

    public boolean isReturningItem() {
        return this.returningItem;
    }

    public boolean isSwappingTotem() {
        return this.swappingTotem;
    }

    public long getLastSwapAttempt() {
        return this.lastSwapAttempt;
    }

    public long getLastSuccessfulSwap() {
        return this.lastSuccessfulSwap;
    }
}
