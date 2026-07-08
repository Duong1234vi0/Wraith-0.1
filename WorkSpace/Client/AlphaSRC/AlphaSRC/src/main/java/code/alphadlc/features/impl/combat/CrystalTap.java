/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2350
 *  net.minecraft.class_239
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class CrystalTap
extends Module {
    private final BooleanSetting autoAddCrystal = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0441\u0442\u0430\u0432\u0438\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b", "\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0441\u0442\u0430\u0432\u0438\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b").setValue(true);
    private final SliderSettings addCooldown = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043d\u0430 \u0441\u0442\u0430\u0432\u043a\u0443 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043d\u0430 \u0441\u0442\u0430\u0432\u043a\u0443 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430").setValue(0.0f).range(0, 5).step(1.0f);
    private final SliderSettings attackCooldown = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043d\u0430 \u0430\u0442\u0430\u043a\u0443 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043d\u0430 \u0430\u0442\u0430\u043a\u0443 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430").setValue(0.0f).range(0, 5).step(1.0f);
    private int attackCrystalCooldown;
    private int addCrystalCooldown;

    public static CrystalTap getInstance() {
        return Instance.get(CrystalTap.class);
    }

    public CrystalTap() {
        super("CrystalTap", "CrystalTap", ModuleCategory.COMBAT);
        this.setup(this.autoAddCrystal, this.addCooldown, this.attackCooldown);
    }

    private void addCrystal() {
        class_3965 obsidianTarget;
        class_239 class_2392 = CrystalTap.mc.field_1765;
        if (class_2392 instanceof class_3965 && CrystalTap.mc.field_1687.method_8320((obsidianTarget = (class_3965)class_2392).method_17777()).method_26204() == class_2246.field_10540) {
            for (class_1297 entity : CrystalTap.mc.field_1687.method_18112()) {
                class_1511 crystal;
                if (!(entity instanceof class_1511) || !(crystal = (class_1511)entity).method_24515().method_10074().equals((Object)obsidianTarget.method_17777())) continue;
                return;
            }
            class_2350 side = obsidianTarget.method_17780();
            class_3965 crystalHit = new class_3965(obsidianTarget.method_17784(), side, obsidianTarget.method_17777(), true);
            if (CrystalTap.mc.field_1724.method_6047().method_7909() != class_1802.field_8301) {
                return;
            }
            if (this.addCrystalCooldown <= this.addCooldown.getInt()) {
                CrystalTap.mc.field_1761.method_2896(CrystalTap.mc.field_1724, class_1268.field_5808, crystalHit);
                this.addCrystalCooldown = 0;
            } else {
                ++this.addCrystalCooldown;
            }
        }
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (CrystalTap.mc.field_1724 == null || CrystalTap.mc.field_1687 == null || CrystalTap.mc.field_1761 == null) {
            return;
        }
        if (this.autoAddCrystal.isValue()) {
            this.addCrystal();
        }
        for (class_1297 entity : CrystalTap.mc.field_1687.method_18112()) {
            class_3966 hitResult;
            if (!(entity instanceof class_1511)) continue;
            class_1511 crystal = (class_1511)entity;
            class_239 class_2392 = CrystalTap.mc.field_1765;
            if (!(class_2392 instanceof class_3966) || !((hitResult = (class_3966)class_2392).method_17782() instanceof class_1511)) continue;
            if (this.attackCrystalCooldown >= this.attackCooldown.getInt()) {
                CrystalTap.mc.field_1761.method_2918((class_1657)CrystalTap.mc.field_1724, hitResult.method_17782());
                CrystalTap.mc.field_1724.method_6104(class_1268.field_5808);
                this.attackCrystalCooldown = 0;
                continue;
            }
            ++this.attackCrystalCooldown;
        }
    }

    public BooleanSetting getAutoAddCrystal() {
        return this.autoAddCrystal;
    }

    public SliderSettings getAddCooldown() {
        return this.addCooldown;
    }

    public SliderSettings getAttackCooldown() {
        return this.attackCooldown;
    }

    public int getAttackCrystalCooldown() {
        return this.attackCrystalCooldown;
    }

    public int getAddCrystalCooldown() {
        return this.addCrystalCooldown;
    }
}
