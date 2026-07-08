/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1839
 *  net.minecraft.class_4184
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.render.UseIndicatorRenderer;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1839;
import net.minecraft.class_4184;

public class UseIndicator
extends Module {
    public static UseIndicator getInstance() {
        return Instance.get(UseIndicator.class);
    }

    public UseIndicator() {
        super("UseIndicator", "UseIndicator", ModuleCategory.RENDER);
        this.setState(true);
    }

    @EventHandler
    public void onDraw(DrawEvent event) {
        if (UseIndicator.mc.field_1687 == null || UseIndicator.mc.field_1724 == null || UseIndicator.mc.field_1773 == null || event.getDrawContext() == null) {
            return;
        }
        class_4184 camera = UseIndicator.mc.field_1773.method_19418();
        for (class_1657 player : UseIndicator.mc.field_1687.method_18456()) {
            class_1799 stack;
            if (!this.shouldRender((class_1309)player) || (stack = this.resolveConsumableStack((class_1309)player)).method_7960()) continue;
            UseIndicatorRenderer.render(event.getDrawContext(), (class_1309)player, stack, camera);
        }
    }

    private boolean shouldRender(class_1309 entity) {
        if (!entity.method_5805() || !entity.method_6115()) {
            return false;
        }
        if (entity == UseIndicator.mc.field_1724 && UseIndicator.mc.field_1690.method_31044().method_31034()) {
            return false;
        }
        if (entity.method_5767() && entity != UseIndicator.mc.field_1724) {
            return false;
        }
        return !this.resolveConsumableStack(entity).method_7960();
    }

    private class_1799 resolveConsumableStack(class_1309 entity) {
        class_1799 handStack;
        if (!entity.method_6115()) {
            return class_1799.field_8037;
        }
        class_1799 activeItem = entity.method_6030();
        if (this.isConsumable(activeItem)) {
            return activeItem;
        }
        class_1268 activeHand = entity.method_6058();
        if (activeHand != null && this.isConsumable(handStack = entity.method_5998(activeHand))) {
            return handStack;
        }
        return class_1799.field_8037;
    }

    private boolean isConsumable(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        class_1839 action = stack.method_7976();
        return action == class_1839.field_8950 || action == class_1839.field_8946;
    }
}
