/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.module;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.setting.SettingComponentAdder;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class ModuleSettingsWindow
extends AbstractWindow {
    private final List<AbstractSettingComponent> components = new ArrayList<AbstractSettingComponent>();
    public final Module module;

    public ModuleSettingsWindow(Module module) {
        this.module = module;
        new SettingComponentAdder().addSettingComponent(module.settings(), this.components, module);
        this.draggable(true);
    }

    @Override
    public void drawWindow(class_332 context, int mouseX, int mouseY, float delta) {
        boolean isLimitedHeight;
        class_4587 matrix = context.method_51448();
        ScissorAssist scissorManager = AlphaDLC.getInstance().getScissorManager();
        this.height = class_3532.method_15340((int)(this.getComponentHeight() + 5), (int)0, (int)200);
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, this.width, this.height).round(8.0f).softness(1.0f).thickness(2.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(18, 19, 20, 255).getRGB()).build());
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y + 22.0f, this.width, 0.5).color(new Color(48, 48, 48, 255).getRGB()).build());
        Fonts.getSize(15, Fonts.Type.SuisseIntlSemiBold).drawGradientString(context.method_51448(), this.module.getVisibleName() + " Settings", this.x + 10.0f, this.y + 10.0f, ColorAssist.getText(), new Color(165, 165, 165, 255).getRGB());
        Fonts.getSize(17, Fonts.Type.ICONS).drawString(context.method_51448(), "K", this.x + 145.0f, this.y + 10.0f, ColorAssist.getText(0.5f));
        boolean bl = isLimitedHeight = class_3532.method_15363((float)this.height, (float)0.0f, (float)200.0f) == 200.0f;
        if (isLimitedHeight) {
            scissorManager.push(matrix.method_23760().method_23761(), this.x, this.y + 23.0f, this.width, this.height - 24.0f);
        }
        float offset = 0.0f;
        int totalHeight = 0;
        for (int i = this.components.size() - 1; i >= 0; --i) {
            AbstractSettingComponent component = this.components.get(i);
            Supplier<Boolean> visible = component.getSetting().getVisible();
            if (visible != null && !visible.get().booleanValue()) continue;
            component.x = this.x;
            component.y = (float)((double)(this.y + 22.0f + offset + ((float)(this.getComponentHeight() - 25) - component.height)) + this.smoothedScroll);
            component.width = this.width;
            component.render(context, mouseX, mouseY, delta);
            offset -= component.height;
            totalHeight += (int)component.height;
        }
        if (isLimitedHeight) {
            scissorManager.pop();
        }
        int maxScroll = (int)Math.max(0.0f, (float)totalHeight - (this.height - 28.0f));
        this.scroll = class_3532.method_15350((double)this.scroll, (double)(-maxScroll), (double)0.0);
        this.smoothedScroll = class_3532.method_16436((double)0.1f, (double)this.smoothedScroll, (double)this.scroll);
        if (isLimitedHeight) {
            float viewableHeight = this.height - 30.0f;
            float scrollbarHeight = Math.max(20.0f, viewableHeight / (float)totalHeight * viewableHeight);
            float scrollPercent = (float)(-this.smoothedScroll / (double)maxScroll);
            float scrollbarY = this.y + 30.0f + scrollPercent * (viewableHeight - scrollbarHeight);
            float scrollbarX = this.x + this.width - 6.0f;
            float scrollbarWidth = 3.0f;
            rectangle.render(ShapeProperties.create(matrix, scrollbarX, this.y + 30.0f, scrollbarWidth, viewableHeight - 6.0f).round(1.0f).color(new Color(30, 30, 30, 100).getRGB()).build());
            rectangle.render(ShapeProperties.create(matrix, scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight - 6.0f).round(1.5f).color(new Color(100, 100, 100, 180).getRGB()).build());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean isAnyComponentHovered;
        if (button == 0) {
            if (Calculate.isHovered(mouseX, mouseY, this.x + 145.0f, this.y + 5.0f, 15.0, 15.0)) {
                this.startCloseAnimation();
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, 19.0)) {
                this.dragging = true;
                this.dragX = (int)((double)this.x - mouseX);
                this.dragY = (int)((double)this.y - mouseY);
                return true;
            }
        }
        if (isAnyComponentHovered = this.components.stream().anyMatch(abstractComponent -> abstractComponent.isHover(mouseX, mouseY))) {
            this.components.forEach(abstractComponent -> {
                if (abstractComponent.isHover(mouseX, mouseY)) {
                    abstractComponent.mouseClicked(mouseX, mouseY, button);
                }
            });
            return true;
        }
        this.components.forEach(abstractComponent -> abstractComponent.mouseClicked(mouseX, mouseY, button));
        return true;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        this.components.forEach(abstractComponent -> abstractComponent.isHover(mouseX, mouseY));
        for (AbstractComponent abstractComponent2 : this.components) {
            if (!abstractComponent2.isHover(mouseX, mouseY)) continue;
            return true;
        }
        return super.isHovered(mouseX, mouseY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
        this.components.forEach(abstractComponent -> abstractComponent.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        boolean scrolled;
        boolean bl = scrolled = class_3532.method_15363((float)this.height, (float)0.0f, (float)200.0f) == 200.0f && Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
        if (scrolled) {
            this.scroll += amount * 20.0;
        }
        this.components.forEach(abstractComponent -> abstractComponent.mouseScrolled(mouseX, mouseY, amount));
        return scrolled;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.components.forEach(abstractComponent -> abstractComponent.keyPressed(keyCode, scanCode, modifiers));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        this.components.forEach(abstractComponent -> abstractComponent.charTyped(chr, modifiers));
        return super.charTyped(chr, modifiers);
    }

    public int getComponentHeight() {
        float offsetY = 0.0f;
        for (AbstractSettingComponent component : this.components) {
            Supplier<Boolean> visible = component.getSetting().getVisible();
            if (visible != null && !visible.get().booleanValue()) continue;
            offsetY += component.height;
        }
        return (int)(offsetY + 25.0f);
    }
}
