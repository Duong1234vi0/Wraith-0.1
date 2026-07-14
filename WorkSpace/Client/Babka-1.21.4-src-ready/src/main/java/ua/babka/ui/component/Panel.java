package ua.babka.ui.component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import ua.babka.Babka;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.impl.render.ClickGuiScreen;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.Scissor;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.SkycoreIcons;
import ua.babka.ui.component.Component;
import ua.babka.ui.component.ModuleComponent;
import ua.babka.util.IMinecraft;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class Panel
implements IMinecraft {
    public float x;
    public float y;
    public float width;
    public float height;
    public final ModuleCategory category;
    public List<ModuleComponent> moduleComponents = new ArrayList<ModuleComponent>();
    private Animation animation = new Animation(Easing.QUINTIC_OUT, 350L);
    private Animation animationAlpha = new Animation(Easing.BOUNCE_OUT, 350L);
    private final Animation scrollbarAnim = new Animation(Easing.CUBIC_IN_OUT, 220L);
    private final Animation slideX = new Animation(Easing.QUINTIC_OUT, 333L);
    private final Animation slideY = new Animation(Easing.QUINTIC_OUT, 333L);
    private final Animation closeAnimX = new Animation(Easing.QUINTIC_IN, 300L);
    private final Animation closeAnimY = new Animation(Easing.QUINTIC_IN, 300L);
    private boolean isClosing = false;
    float scroll;
    float maxScroll;
    private final ClickGuiScreen parent;
    private static final float PAD = 5.0f;
    private static final float CORNER = 8.0f;
    private static final float HDR_H = 30.0f;
    private static final float ITEM_GAP = 1.0f;

    public Panel(ModuleCategory category, ClickGuiScreen parent) {
        this.category = category;
        this.parent = parent;
        Babka.getInstance().getModuleStorage().getModules().stream().filter(m2 -> m2.getCategory() == this.category).sorted(Comparator.comparing(m2 -> m2.getName().toLowerCase())).forEach(m2 -> this.moduleComponents.add(new ModuleComponent((Module)m2, this)));
    }

    public void startCloseAnimation() {
        this.isClosing = true;
        this.closeAnimX.setValue(0.0f);
        this.closeAnimY.setValue(0.0f);
    }

    public void resetSlide() {
        this.isClosing = false;
        this.closeAnimX.setValue(0.0f);
        this.closeAnimY.setValue(0.0f);
        float sox = switch (this.category) {
            case ModuleCategory.COMBAT -> -300.0f;
            case ModuleCategory.MISC -> 300.0f;
            default -> 0.0f;
        };
        float soy = switch (this.category) {
            case ModuleCategory.MOVEMENT, ModuleCategory.PLAYER -> -300.0f;
            case ModuleCategory.RENDER -> 300.0f;
            default -> 0.0f;
        };
        this.slideX.setValue(sox);
        this.slideY.setValue(soy);
    }

    public void clampScroll() {
        this.scroll = this.maxScroll > 0.0f ? MathHelper.clamp((float)this.scroll, (float)(-this.maxScroll), (float)0.0f) : 0.0f;
    }

    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        this.slideX.run(0.0f);
        this.slideY.run(0.0f);
        float ox = this.slideX.getValue();
        float oy = this.slideY.getValue();
        if (this.isClosing) {
            this.closeAnimX.run(1.0f);
            this.closeAnimY.run(1.0f);
        }
        float tox = switch (this.category) {
            case ModuleCategory.COMBAT -> -300.0f;
            case ModuleCategory.MISC -> 300.0f;
            default -> 0.0f;
        };
        float toy = switch (this.category) {
            case ModuleCategory.MOVEMENT, ModuleCategory.PLAYER -> -300.0f;
            case ModuleCategory.RENDER -> 300.0f;
            default -> 0.0f;
        };
        float cox = this.isClosing ? this.closeAnimX.getValue() * tox : 0.0f;
        float coy = this.isClosing ? this.closeAnimY.getValue() * toy : 0.0f;
        float ca = this.isClosing ? (float)(1.0 - (double)this.closeAnimX.getValue()) : 1.0f;
        float rx = this.x + ox + cox;
        float ry = this.y + oy + coy;
        this.animationAlpha.setValue(1.0f);
        float a2 = Math.min(255.0f * this.animationAlpha.getValue() * ca, 255.0f);
        float ar2 = a2 / 255.0f;
        int ai2 = (int)a2;
        int themeColor = ColorProvider.getThemeColor();
        int bgColor = ColorProvider.interpolateColor(themeColor, -16777216, 0.88f);
        DrawUtil.drawRound(rx, ry, this.width, this.height, 8.0f, ColorProvider.setAlpha(bgColor, (int)(190.0f * ar2)));
        char iconChar = switch (this.category) {
            default -> throw new MatchException(null, null);
            case ModuleCategory.COMBAT -> 'l';
            case ModuleCategory.MOVEMENT -> 'm';
            case ModuleCategory.RENDER -> 'b';
            case ModuleCategory.PLAYER -> 'i';
            case ModuleCategory.MISC -> 'j';
        };
        String cap = this.category.name().substring(0, 1).toUpperCase() + this.category.name().substring(1).toLowerCase();
        float iconSize = 22.0f;
        SkycoreIcons.IconEntry iconEntry = SkycoreIcons.getIcon(iconChar);
        if (iconEntry != null) {
            float iconY = ry + (30.0f - iconSize) / 2.0f + 0.4f;
            ((BuiltTexture)Builder.texture().size(new SizeState(iconSize, iconSize)).texture(0.0f, 0.0f, 1.0f, 1.0f, iconEntry.textureId()).color(new QuadColorState(themeColor, themeColor, themeColor, themeColor)).build()).render(ms.peek().getPositionMatrix(), rx + 5.0f + 2.0f, iconY, 0.0f);
        }
        float textX = rx + 5.0f + 2.0f + (iconEntry != null ? iconSize + 2.0f : 0.0f);
        float textW = Fonts.SFREGULAR.get().getWidth(cap, 9.0f);
        if (iconEntry == null) {
            textX = rx + (this.width - textW) / 2.0f;
        }
        float textY = ry + 10.5f - 1.0f;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), cap, textX, textY, ColorProvider.rgba(255, 255, 255, ai2), 9.0f);
        DrawUtil.drawRound(rx + 5.0f + 2.0f, ry + 30.0f - 0.5f, this.width - 10.0f - 4.0f, 1.0f, 0.0f, ColorProvider.setAlpha(themeColor, (int)(50.0f * ar2)));
        float offset = 3.0f;
        this.clampScroll();
        this.animation.run(this.scroll);
        Scissor.push();
        Scissor.setFromComponentCoordinates(rx, ry + 30.0f, this.width, this.height - 30.0f - 5.0f);
        for (ModuleComponent comp : this.moduleComponents) {
            if (this.parent.searchCheck(comp.getModule().getName())) continue;
            comp.setX(rx + 5.0f + 2.0f);
            comp.setY(ry + 30.0f + offset + this.animation.getValue());
            comp.setWidth(this.width - 14.0f);
            float baseH = 18.0f;
            float extraH = 0.0f;
            if (comp.getAnimation().getValue() > 0.01f) {
                for (Component c2 : comp.getComponents()) {
                    float vp = MathHelper.clamp((float)c2.getAlphaAnimSetting().getValue(), (float)0.0f, (float)1.0f);
                    if (!c2.isVisible() && !(vp > 0.0f)) continue;
                    extraH += c2.getHeight() * vp;
                }
            }
            comp.setHeight(baseH + extraH * comp.getAnimation().getValue());
            Scissor.setFromComponentCoordinates(rx, ry + 30.0f, this.width, this.height - 30.0f - 5.0f);
            comp.render(ms, mouseX, mouseY, pt);
            Scissor.setFromComponentCoordinates(rx, ry + 30.0f, this.width, this.height - 30.0f - 5.0f);
            offset += comp.getHeight() + 1.0f;
        }
        this.maxScroll = Math.max(0.0f, offset - (this.height - 30.0f - 10.0f));
        this.scrollbarAnim.run(this.maxScroll > 0.0f);
        if (this.scrollbarAnim.getValue() > 0.01f) {
            float sbH = this.height - 30.0f - 10.0f;
            float sbTh = Math.max(14.0f, sbH * sbH / (sbH + this.maxScroll));
            float sbR = sbH - sbTh;
            float sbY = sbR > 0.0f ? -this.scroll / this.maxScroll * sbR : 0.0f;
            float sbX = rx + this.width - 5.0f - 1.5f;
            float sbTY = ry + 30.0f + 5.0f;
            int sbA = (int)(60.0f * ar2 * this.scrollbarAnim.getValue());
            DrawUtil.drawRound(sbX, sbTY, 3.0f, sbH, 1.5f, ColorProvider.rgba(255, 255, 255, sbA / 3));
            DrawUtil.drawRound(sbX, sbTY + sbY, 3.0f, sbTh, 1.5f, ColorProvider.rgba(255, 255, 255, sbA));
        }
        Scissor.unset();
        Scissor.pop();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (HoverUtil.isHovered(mouseX, mouseY, this.x, this.y + 30.0f, this.width, this.height - 30.0f)) {
            for (ModuleComponent mc : this.moduleComponents) {
                if (this.parent.searchCheck(mc.getModule().getName())) continue;
                mc.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (ModuleComponent mc : this.moduleComponents) {
            if (this.parent.searchCheck(mc.getModule().getName())) continue;
            mc.mouseReleased(mouseX, mouseY, button);
        }
    }

    public void mouseScrolled(double mouseX, double mouseY, double ha, double va) {
        if (HoverUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            this.scroll += (float)(va * 30.0);
            this.clampScroll();
        }
    }

    public void charTyped(char chr, int modifiers) {
        for (ModuleComponent mc : this.moduleComponents) {
            mc.charTyped(chr, modifiers);
        }
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for (ModuleComponent mc : this.moduleComponents) {
            mc.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public List<ModuleComponent> getModuleComponents() {
        return this.moduleComponents;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public Animation getAnimationAlpha() {
        return this.animationAlpha;
    }

    public Animation getScrollbarAnim() {
        return this.scrollbarAnim;
    }

    public Animation getSlideX() {
        return this.slideX;
    }

    public Animation getSlideY() {
        return this.slideY;
    }

    public Animation getCloseAnimX() {
        return this.closeAnimX;
    }

    public Animation getCloseAnimY() {
        return this.closeAnimY;
    }

    public boolean isClosing() {
        return this.isClosing;
    }

    public float getScroll() {
        return this.scroll;
    }

    public float getMaxScroll() {
        return this.maxScroll;
    }

    public ClickGuiScreen getParent() {
        return this.parent;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setModuleComponents(List<ModuleComponent> moduleComponents) {
        this.moduleComponents = moduleComponents;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setAnimationAlpha(Animation animationAlpha) {
        this.animationAlpha = animationAlpha;
    }

    public void setClosing(boolean isClosing) {
        this.isClosing = isClosing;
    }

    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    public void setMaxScroll(float maxScroll) {
        this.maxScroll = maxScroll;
    }
}

