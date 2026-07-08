/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1542
 *  net.minecraft.class_1735
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_9288
 *  net.minecraft.class_9334
 *  org.joml.Vector4d
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.container.HandledScreenEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1735;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import org.joml.Vector4d;

public class ShulkerView
extends Module {
    private final class_2960 TEXTURE = class_2960.method_60654((String)"textures/container.png");
    private final BooleanSetting showGroundItems = new BooleanSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0448\u0430\u043b\u043a\u0435\u0440\u044b, \u043b\u0435\u0436\u0430\u0449\u0438\u0435 \u043d\u0430 \u0437\u0435\u043c\u043b\u0435").setValue(true);

    public static ShulkerView getInstance() {
        return Instance.get(ShulkerView.class);
    }

    public ShulkerView() {
        super("ShulkerView", "ShulkerView", ModuleCategory.RENDER);
        this.setup(this.showGroundItems);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (!this.showGroundItems.isValue()) {
            return;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        RenderSystem.disableDepthTest();
        matrix.method_22903();
        matrix.method_46416(0.0f, 0.0f, -1000.0f);
        List<class_1297> entities = PlayerInteractionHelper.streamEntities().sorted(Comparator.comparing(ent -> {
            class_1542 item;
            return ent instanceof class_1542 && (item = (class_1542)ent).method_6983().method_7964().method_10851().toString().equals("empty");
        })).toList();
        for (class_1297 entity : entities) {
            List<class_1799> list;
            if (!(entity instanceof class_1542)) continue;
            class_1542 item = (class_1542)entity;
            Vector4d vec4d = Projection.getVector4D(entity);
            class_1799 stack = item.method_6983();
            class_9288 compoundTag = (class_9288)stack.method_57824(class_9334.field_49622);
            List<Object> list2 = list = compoundTag != null ? compoundTag.method_57489().toList() : List.of();
            if (Projection.cantSee(vec4d) || list.isEmpty()) continue;
            this.drawShulkerBox(context, stack, list, vec4d);
        }
        matrix.method_22909();
        RenderSystem.enableDepthTest();
    }

    @EventHandler
    public void onHandledScreen(HandledScreenEvent e) {
        class_1735 hoverSlot = e.getSlotHover();
        if (hoverSlot == null || !hoverSlot.method_7681()) {
            return;
        }
        class_1799 stack = hoverSlot.method_7677();
        class_9288 compoundTag = (class_9288)stack.method_57824(class_9334.field_49622);
        if (compoundTag == null) {
            return;
        }
        List<class_1799> list = compoundTag.method_57489().toList();
        if (list.isEmpty()) {
            return;
        }
        class_332 context = e.getDrawContext();
        double mouseX = ShulkerView.mc.field_1729.method_1603() * (double)mc.method_22683().method_4486() / (double)mc.method_22683().method_4480();
        double mouseY = ShulkerView.mc.field_1729.method_1604() * (double)mc.method_22683().method_4502() / (double)mc.method_22683().method_4507();
        this.drawShulkerBoxAtPosition(context, stack, list, (int)mouseX, (int)mouseY);
    }

    private void drawShulkerBox(class_332 context, class_1799 itemStack, List<class_1799> stacks, Vector4d vec) {
        class_4587 matrix = context.method_51448();
        int width = 176;
        int height = 67;
        int color = ColorAssist.multBright(ColorAssist.replAlpha(((class_1747)itemStack.method_7909()).method_7711().method_26403().field_16011, 1.0f), 1.0f);
        RenderSystem.disableDepthTest();
        matrix.method_22903();
        matrix.method_22904(Projection.centerX(vec) - (double)width / 4.0, vec.w + 2.0, 0.0);
        matrix.method_22905(0.5f, 0.5f, 1.0f);
        context.method_25291(class_1921::method_62277, this.TEXTURE, 0, 0, 0.0f, 0.0f, width, height, width, height, color);
        int posX = 7;
        int posY = 6;
        for (class_1799 stack : stacks.stream().toList()) {
            Render2D.defaultDrawStack(context, stack, posX, posY, false, true, 1.0f);
            if ((posX += 18) < 165) continue;
            posY += 18;
            posX = 7;
        }
        matrix.method_22909();
    }

    private void drawShulkerBoxAtPosition(class_332 context, class_1799 itemStack, List<class_1799> stacks, int mouseX, int mouseY) {
        class_4587 matrix = context.method_51448();
        int width = 176;
        int height = 67;
        int color = ColorAssist.multBright(ColorAssist.replAlpha(((class_1747)itemStack.method_7909()).method_7711().method_26403().field_16011, 1.0f), 1.0f);
        RenderSystem.disableDepthTest();
        matrix.method_22903();
        int screenWidth = mc.method_22683().method_4486();
        int screenHeight = mc.method_22683().method_4502();
        float scale = 0.8f;
        int scaledWidth = (int)((float)width * scale);
        int scaledHeight = (int)((float)height * scale);
        int tooltipHeight = 20;
        int posX = mouseX + 10;
        int posY = mouseY + tooltipHeight + 5;
        if (posX + scaledWidth > screenWidth) {
            posX = mouseX - scaledWidth - 10;
        }
        if (posY + scaledHeight > screenHeight) {
            posY = mouseY - scaledHeight - tooltipHeight - 15;
        }
        if (posX < 0) {
            posX = 10;
        }
        if (posY < 0) {
            posY = 10;
        }
        matrix.method_46416((float)posX, (float)(posY - 100), 300.0f);
        matrix.method_22905(scale, scale, 1.0f);
        context.method_25291(class_1921::method_62277, this.TEXTURE, 0, 0, 0.0f, 0.0f, width, height, width, height, color);
        int itemPosX = 7;
        int itemPosY = 6;
        for (class_1799 stack : stacks.stream().toList()) {
            Render2D.defaultDrawStack(context, stack, itemPosX, itemPosY, false, true, 1.0f);
            if ((itemPosX += 18) < 165) continue;
            itemPosY += 18;
            itemPosX = 7;
        }
        matrix.method_22909();
    }
}
