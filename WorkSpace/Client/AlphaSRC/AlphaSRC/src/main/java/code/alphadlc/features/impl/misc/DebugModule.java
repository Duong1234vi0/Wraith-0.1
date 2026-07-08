/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1295
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1686
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1844
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2718
 *  net.minecraft.class_2783
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.lang.reflect.Method;
import java.lang.runtime.SwitchBootstraps;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1295;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1686;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2718;
import net.minecraft.class_2783;
import net.minecraft.class_9334;

public class DebugModule
extends Module {
    private final MultiSelectSetting logs = new MultiSelectSetting("\u041b\u043e\u0433\u0438", "\u0427\u0442\u043e \u043b\u043e\u0433\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0432 \u043a\u043e\u043d\u0441\u043e\u043b\u044c").value("\u0412\u0437\u0440\u044b\u0432\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", "\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439").selected("\u0412\u0437\u0440\u044b\u0432\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f", "\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439");
    private final Set<Integer> seenPotionEntities = new HashSet<Integer>();
    private final Set<Integer> seenAreaClouds = new HashSet<Integer>();

    public DebugModule() {
        super("DebugModule", ModuleCategory.MISC);
        this.setup(this.logs);
    }

    private boolean logPotions() {
        return this.logs.isSelected("\u0412\u0437\u0440\u044b\u0432\u043d\u044b\u0435 \u0437\u0435\u043b\u044c\u044f");
    }

    private boolean logEffects() {
        return this.logs.isSelected("\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439");
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (DebugModule.mc.field_1687 == null) {
            return;
        }
        if (!this.logPotions()) {
            return;
        }
        for (class_1297 entity : DebugModule.mc.field_1687.method_18112()) {
            class_1295 cloud;
            int id;
            if (entity instanceof class_1686) {
                class_1799 stack;
                class_1686 potion = (class_1686)entity;
                id = potion.method_5628();
                if (!this.seenPotionEntities.add(id) || (stack = potion.method_7495()) == null || stack.method_7960() || !stack.method_31574(class_1802.field_8436) && !stack.method_31574(class_1802.field_8150)) continue;
                String name = stack.method_7964().getString();
                String effects = this.describePotionEffects(stack);
                Logger.info("[DebugModule] PotionEntity #" + id + " @(" + String.format("%.2f", entity.method_23317()) + ", " + String.format("%.2f", entity.method_23318()) + ", " + String.format("%.2f", entity.method_23321()) + ") " + name + (String)(effects.isEmpty() ? "" : " | " + effects));
                continue;
            }
            if (!(entity instanceof class_1295) || !this.seenAreaClouds.add(id = (cloud = (class_1295)entity).method_5628())) continue;
            String effects = this.describeCloudEffects(cloud);
            Logger.info("[DebugModule] AreaEffectCloud #" + id + " @(" + String.format("%.2f", entity.method_23317()) + ", " + String.format("%.2f", entity.method_23318()) + ", " + String.format("%.2f", entity.method_23321()) + ") " + (String)(effects.isEmpty() ? "" : "| " + effects));
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (DebugModule.mc.field_1687 == null) {
            return;
        }
        if (!this.logEffects()) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2783.class, class_2718.class}, class_25963, n)) {
            case 0: {
                class_2783 p = (class_2783)class_25963;
                class_1297 entity = DebugModule.mc.field_1687.method_8469(p.method_11943());
                String entityName = this.describeEntity(entity);
                String effectName = class_2561.method_43471((String)((class_1291)p.method_11946().comp_349()).method_5567()).getString();
                int level = p.method_11945() + 1;
                int durationTicks = p.method_11944();
                Logger.info("[DebugModule] EFFECT_ADD " + entityName + " -> " + effectName + " " + level + " (" + this.formatDuration(durationTicks) + ") ambient=" + p.method_11950() + " particles=" + p.method_11949() + " icon=" + p.method_11942());
                break;
            }
            case 1: {
                class_2718 p = (class_2718)class_25963;
                class_1297 entity = DebugModule.mc.field_1687.method_8469(p.comp_2175());
                String entityName = this.describeEntity(entity);
                String effectName = class_2561.method_43471((String)((class_1291)p.comp_2176().comp_349()).method_5567()).getString();
                Logger.info("[DebugModule] EFFECT_REMOVE " + entityName + " -> " + effectName);
                break;
            }
        }
    }

    private String describePotionEffects(class_1799 stack) {
        class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
        if (component == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (class_1293 eff : component.method_57397()) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(this.describeEffect(eff));
        }
        return sb.toString();
    }

    private String describeCloudEffects(class_1295 cloud) {
        class_1844 contents = this.getCloudPotionContents(cloud);
        if (contents == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (class_1293 eff : contents.method_57397()) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(this.describeEffect(eff));
        }
        return sb.toString();
    }

    private class_1844 getCloudPotionContents(class_1295 cloud) {
        class_1844 pc;
        Object value = this.tryInvokeNoArg(cloud, "getPotionContents");
        if (value == null) {
            value = this.tryInvokeNoArg(cloud, "getPotionContentsComponent");
        }
        if (value == null) {
            value = this.tryInvokeNoArg(cloud, "getPotionContentsComponent");
        }
        return value instanceof class_1844 ? (pc = (class_1844)value) : null;
    }

    private Object tryInvokeNoArg(Object target, String methodName) {
        try {
            Method m = target.getClass().getMethod(methodName, new Class[0]);
            return m.invoke(target, new Object[0]);
        }
        catch (Throwable ignored) {
            return null;
        }
    }

    private String describeEffect(class_1293 eff) {
        String name = class_2561.method_43471((String)((class_1291)eff.method_5579().comp_349()).method_5567()).getString();
        int lvl = eff.method_5578() + 1;
        return name + " " + lvl + " (" + this.formatDuration(eff.method_5584()) + ")";
    }

    private String describeEntity(class_1297 entity) {
        if (entity == null) {
            return "unknown_entity";
        }
        if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            return living.method_5477().getString() + "(id=" + living.method_5628() + ")";
        }
        return entity.method_5864().toString() + "(id=" + entity.method_5628() + ")";
    }

    private String formatDuration(int ticks) {
        if (ticks < 0) {
            return "\u221e";
        }
        int seconds = ticks / 20;
        int minutes = seconds / 60;
        return minutes > 0 ? minutes + "m " + seconds + "s" : (seconds %= 60) + "s";
    }
}
