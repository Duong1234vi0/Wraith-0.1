/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package code.alphadlc.display.screens.clickgui.components.implement.module;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleDescriptions;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.setting.SettingComponentAdder;
import code.alphadlc.utils.animation.AnimationHelper;
import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class ModuleComponent
extends AbstractComponent {
    private final List<AbstractSettingComponent> components = new ArrayList<AbstractSettingComponent>();
    private final Module module;
    private final Rectangle rectangle = new Rectangle();
    private boolean binding = false;
    private boolean isExpanded = false;
    private boolean wasExpanded = false;

    public ModuleComponent(Module module) {
        this.module = module;
        new SettingComponentAdder().addSettingComponent(module.settings(), this.components, module);
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        boolean hasSettings = !this.module.settings().isEmpty();
        boolean isExpanded = this.isExpanded();
        this.height = 20.0f;
        int backgroundColor = this.module.isState() ? ColorAssist.getClientColor() : ThemeManager.offModuleColor.getColor();
        int secondaryColor = this.module.isState() ? ColorAssist.getClientColor2() : ThemeManager.offModuleColor.getColor();
        this.rectangle.render(ShapeProperties.create(context.method_51448(), this.x, this.y, this.width, this.height).round(5.0f).color(backgroundColor, backgroundColor, secondaryColor, secondaryColor).build());
        String moduleName = this.binding ? "binding.." : this.module.getVisibleName();
        float textWidth = Fonts.getSize(14, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringWidth(moduleName);
        float textX = this.x + (this.width - textWidth) / 2.0f;
        float textY = this.y + 16.5f - Fonts.getSize(14, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringHeight(moduleName);
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_SEMIBOLD).drawString(context.method_51448(), moduleName, textX, textY, ThemeManager.textColor.getColor());
        if (hasSettings) {
            String arrow = isExpanded ? "j" : "i";
            float arrowX = this.x + this.width - 15.0f;
            float arrowY = this.y + 15.5f - Fonts.getSize(12, Fonts.Type.ALPHADLC_ICONS).getStringHeight(arrow);
            Fonts.getSize(12, Fonts.Type.ALPHADLC_ICONS).drawString(context.method_51448(), arrow, arrowX, arrowY, ThemeManager.textColor.getColor());
        }
        String animationKey = "module_settings_" + this.module.getName();
        boolean isAnimating = AnimationHelper.isAnimationActive(animationKey);
        if (this.isExpanded() && hasSettings || isAnimating) {
            this.renderSettings(context, mouseX, mouseY, delta);
        }
    }

    public void renderModuleDescriptionAt(class_332 context, float moduleX, float moduleY, float moduleWidth, boolean isHovered) {
        if (context == null) {
            return;
        }
        try {
            float alpha;
            String description = ModuleDescriptions.tryGetDescription(this.module);
            if (ModuleDescriptions.isMissingDescription(description)) {
                return;
            }
            class_4587 matrix = context.method_51448();
            if (matrix == null) {
                return;
            }
            FontRenderer font = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD);
            if (font == null) {
                return;
            }
            String animationKey = "module_description_alpha_" + this.module.getName();
            boolean isAnimationActive = AnimationHelper.isAnimationActive(animationKey);
            float currentAlpha = AnimationHelper.getAnimationValue(animationKey, isHovered ? 1.0f : 0.0f);
            if (isHovered && !isAnimationActive && currentAlpha < 0.99f) {
                AnimationHelper.fadeIn(animationKey, 400L);
            }
            if ((alpha = AnimationHelper.getAnimationValue(animationKey, isHovered ? 1.0f : 0.0f)) <= 0.0f) {
                return;
            }
            float maxWidth = 200.0f;
            String wrappedText = StringHelper.wrap(description, (int)maxWidth, 12);
            if (wrappedText == null) {
                return;
            }
            String[] lines = wrappedText.split("\n");
            if (lines == null || lines.length == 0) {
                return;
            }
            float lineHeight = font.getStringHeight("A");
            float textPadding = 6.0f;
            float descriptionWidth = 0.0f;
            for (String line : lines) {
                float lineWidth;
                if (line == null || !((lineWidth = font.getStringWidth(line)) > descriptionWidth)) continue;
                descriptionWidth = lineWidth;
            }
            descriptionWidth = Math.min(descriptionWidth + textPadding, maxWidth);
            float descriptionHeight = (float)lines.length * lineHeight;
            float descriptionX = moduleX + moduleWidth + 5.0f;
            float descriptionY = moduleY + 3.5f;
            int screenWidth = mc.method_22683().method_4486();
            int screenHeight = mc.method_22683().method_4502();
            if (descriptionX + descriptionWidth > (float)screenWidth) {
                descriptionX = moduleX - descriptionWidth - 2.5f;
            }
            if (descriptionY + descriptionHeight > (float)screenHeight) {
                descriptionY = (float)screenHeight - descriptionHeight;
            }
            if (descriptionY < 0.0f) {
                descriptionY = 0.0f;
            }
            int alphaValue = (int)(alpha * 255.0f);
            int backgroundColor = new Color(25, 25, 25, alphaValue).getRGB();
            int textColor = new Color(255, 255, 255, alphaValue).getRGB();
            RenderSystem.disableDepthTest();
            RenderSystem.disableScissor();
            matrix.method_22903();
            matrix.method_46416(0.0f, 0.0f, 3000.0f);
            this.rectangle.render(ShapeProperties.create(matrix, descriptionX, descriptionY, descriptionWidth, descriptionHeight).round(2.0f).color(backgroundColor).build());
            float textX = descriptionX + textPadding / 2.0f;
            float textY = descriptionY + textPadding;
            for (String line : lines) {
                if (line == null || font == null) continue;
                font.drawString(matrix, line, textX, textY, textColor);
                textY += lineHeight;
            }
            matrix.method_22909();
            RenderSystem.enableDepthTest();
        }
        catch (Throwable e) {
            RenderSystem.enableDepthTest();
        }
    }

    public void renderModuleDescription(class_332 context, int mouseX, int mouseY) {
        if (context == null) {
            return;
        }
        try {
            String description = ModuleDescriptions.tryGetDescription(this.module);
            if (ModuleDescriptions.isMissingDescription(description)) {
                return;
            }
            class_4587 matrix = context.method_51448();
            if (matrix == null) {
                return;
            }
            FontRenderer font = Fonts.getSize(12, Fonts.Type.ROCKSTAR_SEMIBOLD);
            if (font == null) {
                return;
            }
            float maxWidth = 200.0f;
            String wrappedText = StringHelper.wrap(description, (int)maxWidth, 12);
            if (wrappedText == null) {
                return;
            }
            String[] lines = wrappedText.split("\n");
            if (lines == null || lines.length == 0) {
                return;
            }
            float lineHeight = font.getStringHeight("A");
            float textPadding = 6.0f;
            float descriptionWidth = 0.0f;
            for (String line : lines) {
                float lineWidth;
                if (line == null || !((lineWidth = font.getStringWidth(line)) > descriptionWidth)) continue;
                descriptionWidth = lineWidth;
            }
            descriptionWidth = Math.min(descriptionWidth + textPadding, maxWidth);
            float descriptionHeight = (float)lines.length * lineHeight;
            float descriptionX = this.x + this.width + 5.0f;
            float descriptionY = this.y;
            int screenWidth = mc.method_22683().method_4486();
            int screenHeight = mc.method_22683().method_4502();
            if (descriptionX + descriptionWidth > (float)screenWidth) {
                descriptionX = this.x - descriptionWidth - 2.5f;
            }
            if (descriptionY + descriptionHeight > (float)screenHeight) {
                descriptionY = (float)screenHeight - descriptionHeight;
            }
            if (descriptionY < 0.0f) {
                descriptionY = 0.0f;
            }
            RenderSystem.disableDepthTest();
            RenderSystem.disableScissor();
            matrix.method_22903();
            matrix.method_46416(0.0f, 0.0f, 3000.0f);
            this.rectangle.render(ShapeProperties.create(matrix, descriptionX, descriptionY, descriptionWidth, descriptionHeight).round(3.0f).color(new Color(25, 25, 25, 255).getRGB()).build());
            float textX = descriptionX + textPadding / 2.0f;
            float textY = descriptionY + textPadding;
            int textColor = ThemeManager.textColor != null ? ThemeManager.textColor.getColor() : 0xFFFFFF;
            for (String line : lines) {
                if (line == null || font == null) continue;
                font.drawString(matrix, line, textX, textY, textColor);
                textY += lineHeight;
            }
            matrix.method_22909();
            RenderSystem.enableDepthTest();
        }
        catch (Throwable e) {
            RenderSystem.enableDepthTest();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        String animationKey;
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.updateSettingsPositions();
            animationKey = "module_settings_" + this.module.getName();
            float slideProgress = AnimationHelper.getAnimationValue(animationKey, 1.0f);
            for (AbstractSettingComponent setting : this.components) {
                float settingOffset;
                float actualSettingY;
                if (!setting.getSetting().isVisible() || !Calculate.isHovered(mouseX, mouseY, this.x, actualSettingY = setting.y - (settingOffset = (setting.y - this.y - 20.0f - 1.0f) * (1.0f - slideProgress)), this.width, setting.height) || !setting.mouseClicked(mouseX, mouseY, button)) continue;
                return true;
            }
        }
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, 20.0)) {
            if (button == 0) {
                String moduleToggleKey = "module_toggle_" + this.module.getName();
                AnimationHelper.startAnimation(moduleToggleKey, 1.0f, 1.1f, 150L, AnimationHelper.EasingType.EASE_OUT);
                this.module.switchState();
                return true;
            }
            if (button == 1 && !this.module.settings().isEmpty()) {
                this.wasExpanded = this.isExpanded;
                this.isExpanded = !this.isExpanded;
                animationKey = "module_settings_" + this.module.getName();
                if (this.isExpanded) {
                    AnimationHelper.startAnimation(animationKey, 0.0f, 1.0f, 200L, AnimationHelper.EasingType.EASE_OUT);
                } else {
                    AnimationHelper.startAnimation(animationKey, 1.0f, 0.0f, 150L, AnimationHelper.EasingType.EASE_IN);
                }
                SoundManager.playSound(SoundManager.CATEGORY_CLICK);
                return true;
            }
            if (button == 2) {
                this.binding = !this.binding;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, 20.0)) {
            return true;
        }
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.updateSettingsPositions();
            String animationKey = "module_settings_" + this.module.getName();
            float slideProgress = AnimationHelper.getAnimationValue(animationKey, 1.0f);
            for (AbstractSettingComponent setting : this.components) {
                float settingOffset;
                float actualSettingY;
                if (!setting.getSetting().isVisible() || !Calculate.isHovered(mouseX, mouseY, this.x, actualSettingY = setting.y - (settingOffset = (setting.y - this.y - 20.0f - 1.0f) * (1.0f - slideProgress)), this.width, setting.height)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.components.stream().filter(setting -> setting.getSetting().isVisible()).forEach(AbstractComponent::tick);
        }
        super.tick();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.updateSettingsPositions();
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible() || !setting.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) continue;
                return true;
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.updateSettingsPositions();
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible()) continue;
                setting.mouseReleased(mouseX, mouseY, button);
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            this.updateSettingsPositions();
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible() || !setting.mouseScrolled(mouseX, mouseY, amount)) continue;
                return true;
            }
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.binding) {
            if (keyCode == 256) {
                this.binding = false;
                return true;
            }
            if (keyCode == 261) {
                this.module.setKey(-1);
                this.binding = false;
                return true;
            }
            this.module.setKey(keyCode);
            this.binding = false;
            return true;
        }
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible() || !setting.keyPressed(keyCode, scanCode, modifiers)) continue;
                return true;
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.isExpanded && !this.module.settings().isEmpty()) {
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible() || !setting.charTyped(chr, modifiers)) continue;
                return true;
            }
        }
        return super.charTyped(chr, modifiers);
    }

    public int getComponentHeight() {
        int baseHeight = 20;
        String animationKey = "module_settings_" + this.module.getName();
        boolean isAnimating = AnimationHelper.isAnimationActive(animationKey);
        if (this.isExpanded() && !this.module.settings().isEmpty() || isAnimating) {
            int settingsHeight = 0;
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible()) continue;
                settingsHeight = (int)((float)settingsHeight + (setting.height + 1.0f));
            }
            float slideProgress = AnimationHelper.getAnimationValue(animationKey, 1.0f);
            return baseHeight + (int)((float)(settingsHeight += 2) * slideProgress);
        }
        return baseHeight;
    }

    public float getSettingsBottomY() {
        String animationKey = "module_settings_" + this.module.getName();
        boolean isAnimating = AnimationHelper.isAnimationActive(animationKey);
        if (this.isExpanded() && !this.module.settings().isEmpty() || isAnimating) {
            int settingsHeight = 0;
            for (AbstractSettingComponent setting : this.components) {
                if (!setting.getSetting().isVisible()) continue;
                settingsHeight = (int)((float)settingsHeight + (setting.height + 1.0f));
            }
            float slideProgress = AnimationHelper.getAnimationValue(animationKey, 1.0f);
            float currentHeight = (float)(settingsHeight += 2) * slideProgress;
            return this.y + 20.0f + 1.0f + currentHeight;
        }
        return this.y + 20.0f;
    }

    private boolean isExpanded() {
        return this.isExpanded;
    }

    private void renderSettings(class_332 context, int mouseX, int mouseY, float delta) {
        float currentHeight;
        this.updateSettingsPositions();
        String animationKey = "module_settings_" + this.module.getName();
        float slideProgress = AnimationHelper.getAnimationValue(animationKey, 1.0f);
        int settingsHeight = 0;
        for (AbstractSettingComponent setting : this.components) {
            if (!setting.getSetting().isVisible()) continue;
            settingsHeight = (int)((float)settingsHeight + (setting.height + 1.0f));
        }
        float backgroundHeight = currentHeight = (float)(settingsHeight += 2) * slideProgress;
        int backgroundColor = ThemeManager.BackgroundSettings.getColor();
        if (backgroundHeight > 0.0f) {
            this.rectangle.render(ShapeProperties.create(context.method_51448(), this.x, this.y + 20.0f + 1.0f, this.width, backgroundHeight).round(3.0f).color(backgroundColor).build());
        }
        boolean clip = slideProgress < 0.999f;
        ScissorAssist scissor = AlphaDLC.getInstance().getScissorManager();
        if (clip) {
            Matrix4f positionMatrix = context.method_51448().method_23760().method_23761();
            scissor.push(positionMatrix, this.x, this.y + 20.0f + 1.0f, this.width, Math.max(0.0f, backgroundHeight));
        }
        float prevAlpha = RenderSystem.getShaderColor()[3];
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)(slideProgress * prevAlpha));
        for (AbstractSettingComponent setting : this.components) {
            if (!setting.getSetting().isVisible()) continue;
            float originalY = setting.y;
            float originalX = setting.x;
            float settingOffset = (setting.y - this.y - 20.0f - 1.0f) * (1.0f - slideProgress);
            setting.x = this.x;
            setting.y = originalY - settingOffset;
            setting.render(context, mouseX, mouseY, delta);
            setting.y = originalY;
            setting.x = originalX;
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)prevAlpha);
        if (clip) {
            scissor.pop();
        }
    }

    private void updateSettingsPositions() {
        float settingsY = this.y + 20.0f + 2.0f + 4.0f;
        for (AbstractSettingComponent setting : this.components) {
            if (!setting.getSetting().isVisible()) continue;
            setting.x = this.x;
            setting.y = settingsY;
            setting.width = this.width;
            settingsY += setting.height + 1.0f;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ModuleComponent that = (ModuleComponent)o;
        return this.module.equals(that.module);
    }

    public int hashCode() {
        return Objects.hash(this.module);
    }

    public List<AbstractSettingComponent> getComponents() {
        return this.components;
    }

    public Module getModule() {
        return this.module;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public boolean isBinding() {
        return this.binding;
    }

    public boolean isWasExpanded() {
        return this.wasExpanded;
    }
}
