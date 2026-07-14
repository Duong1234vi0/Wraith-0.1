package ua.babka.ui.component;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import ua.babka.module.Module;
import ua.babka.module.impl.render.TargetESP;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ColorSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.module.setting.ThemeSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.Scissor;
import ua.babka.render.font.Fonts;
import ua.babka.ui.component.BindComponent;
import ua.babka.ui.component.BooleanComponent;
import ua.babka.ui.component.ColorPickerComponent;
import ua.babka.ui.component.Component;
import ua.babka.ui.component.ModeComponent;
import ua.babka.ui.component.ModeListComponent;
import ua.babka.ui.component.Panel;
import ua.babka.ui.component.SliderComponent;
import ua.babka.ui.component.StringComponent;
import ua.babka.ui.component.ThemeComponent;
import ua.babka.ui.component.ThemeManagerWindow;
import ua.babka.util.ChatUtil;
import ua.babka.util.CursorManager;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class ModuleComponent
extends Component {
    private final Module module;
    private final Panel panel;
    private final Animation animation = new Animation(Easing.QUINTIC_OUT, 320L);
    private final Animation hoverAnim = new Animation(Easing.QUINTIC_OUT, 200L);
    private final Animation enabledAnim = new Animation(Easing.QUINTIC_OUT, 300L);
    public boolean open;
    private boolean isHovered;
    private boolean binding;
    private final ObjectArrayList<Component> components = new ObjectArrayList();
    private static final float IND_SZ = 5.0f;

    public ModuleComponent(Module module, Panel panel) {
        this.module = module;
        this.panel = panel;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting value) {
                this.components.add(new BooleanComponent(value));
            } else if (setting instanceof ModeSetting value) {
                this.components.add(new ModeComponent(value));
            } else if (setting instanceof ModeListSetting value) {
                this.components.add(new ModeListComponent(value));
            } else if (setting instanceof SliderSetting value) {
                this.components.add(new SliderComponent(value));
            } else if (setting instanceof BindSetting value) {
                this.components.add(new BindComponent(value));
            } else if (setting instanceof ThemeSetting value) {
                this.components.add(new ThemeComponent(value));
            } else if (setting instanceof ColorSetting value) {
                this.components.add(new ColorPickerComponent(value));
            } else if (setting instanceof StringSetting value) {
                this.components.add(new StringComponent(value));
            }
        }
        if (module.getName().equals("Interface")) {
            this.components.add((Object)new ThemeActionComponent(this));
        }
        if (module.getName().equals("Target ESP")) {
            this.components.add((Object)new TargetESPActionComponent(this));
        }
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        this.isHovered = HoverUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, 18.0);
        this.hoverAnim.run(this.isHovered);
        this.animation.run(this.open);
        this.enabledAnim.run(this.module.isEnabled());
        if (this.isHovered) {
            CursorManager.requestHand();
        }
        float a2 = Math.max(Math.min(this.panel.getAnimationAlpha().getValue(), 1.0f), 0.0f);
        int ai2 = (int)(255.0f * a2);
        float ev = this.enabledAnim.getValue();
        float hv = this.hoverAnim.getValue();
        float currentH = 18.0f + (this.height - 18.0f) * this.animation.getValue();
        if (hv > 0.01f) {
            DrawUtil.drawRound(this.x, this.y, this.width, currentH - 0.5f, 3.0f, ColorProvider.rgba(255, 255, 255, (int)(255.0f * hv * 0.1f * a2)));
        }
        if (ev > 0.01f) {
            int themeC = ColorProvider.getThemeColor();
            DrawUtil.drawRound(this.x, this.y, this.width, 18.0f, 3.0f, ColorProvider.setAlpha(themeC, (int)(40.0f * a2 * ev)));
        }
        int textEnabled = ColorProvider.rgba(255, 255, 255, ai2);
        int textDisabled = ColorProvider.rgba(160, 160, 160, ai2);
        int textColor = ColorProvider.interpolateColor(textDisabled, textEnabled, ev);
        if (this.binding) {
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "Нажмите клавишу...", this.x + 4.0f, this.y + 5.0f, ColorProvider.rgba(255, 255, 255, ai2), 6.5f);
        } else {
            DrawUtil.drawText(Fonts.SFREGULAR.get(), this.module.getName(), this.x + 4.0f, this.y + 5.25f, textColor, 6.5f);
        }
        float indX = this.x + this.width - 5.0f - 3.0f;
        float indY = this.y + 9.0f - 2.5f;
        int themeC = ColorProvider.getThemeColor();
        int indOff = ColorProvider.rgba(60, 60, 65, ai2);
        int indOn = ColorProvider.setAlpha(themeC, ai2);
        int indCol = ColorProvider.interpolateColor(indOff, indOn, ev);
        DrawUtil.drawRound(indX, indY, 5.0f, 5.0f, 2.5f, indCol);
        if (this.animation.getValue() > 0.01f) {
            float darkH = currentH - 18.0f;
            if (darkH > 0.5f) {
                DrawUtil.drawRound(this.x, this.y + 18.0f, this.width, darkH - 0.5f, 0.0f, ColorProvider.rgba(0, 0, 0, (int)(20.0f * a2 * this.animation.getValue())));
            }
            float compY = this.y + 17.5f;
            float pTop = this.panel.getY() + 24.0f;
            float pBtm = this.panel.getY() + this.panel.getHeight() - 4.0f;
            float secY = this.y + 18.0f;
            float secBtm = this.y + currentH;
            float intY = Math.max(secY, pTop);
            float intBtm = Math.min(secBtm, pBtm);
            float intH = Math.max(0.0f, intBtm - intY);
            for (Component comp : this.components) {
                comp.getAlphaAnim().setValue(Math.min(this.panel.getAnimationAlpha().getValue(), 1.0f));
                comp.getAlphaAnimSetting().run(comp.isVisible());
                float vp = MathHelper.clamp((float)comp.getAlphaAnimSetting().getValue(), (float)0.0f, (float)1.0f);
                if (!comp.isVisible() && !(vp > 0.0f)) continue;
                comp.setX(this.x);
                comp.setY(compY);
                comp.setWidth(this.width - 4.0f);
                Scissor.push();
                Scissor.setFromComponentCoordinates(this.x, intY, this.width, intH);
                comp.render(ms, mouseX, mouseY, pt);
                Scissor.unset();
                Scissor.pop();
                compY += comp.getHeight() * vp;
            }
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (HoverUtil.isHovered(mouseX, mouseY, this.x, this.y, this.width, 18.0)) {
            if (this.binding) {
                if (button == 2 || button == 1) {
                    this.binding = false;
                    return;
                }
                if (button != 0) {
                    this.module.setKey(button);
                    this.binding = false;
                    return;
                }
                return;
            }
            if (button == 0) {
                this.module.setEnabled(!this.module.isEnabled());
            }
            if (button == 1 && !this.components.isEmpty()) {
                boolean bl = this.open = !this.open;
            }
            if (button == 2) {
                boolean bl = this.binding = !this.binding;
            }
        }
        if (this.open && !this.binding) {
            for (Component comp : this.components) {
                if (!comp.isVisible() || !(comp.getAlphaAnimSetting().getValue() > 0.5f)) continue;
                comp.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (this.open) {
            for (Component comp : this.components) {
                comp.mouseReleased(mouseX, mouseY, button);
            }
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.binding) {
            if (keyCode == 256 || keyCode == 261) {
                this.module.setKey(-1);
            } else {
                this.module.setKey(keyCode);
            }
            this.binding = false;
            return;
        }
        if (this.open) {
            for (Component comp : this.components) {
                comp.keyPressed(keyCode, scanCode, modifiers);
            }
        }
    }

    public void charTyped(char chr, int modifiers) {
        for (Component comp : this.components) {
            if (!(comp instanceof StringComponent)) continue;
            StringComponent sc = (StringComponent)comp;
            sc.charTyped(chr);
        }
    }

    public Module getModule() {
        return this.module;
    }

    public Panel getPanel() {
        return this.panel;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public Animation getHoverAnim() {
        return this.hoverAnim;
    }

    public Animation getEnabledAnim() {
        return this.enabledAnim;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isHovered() {
        return this.isHovered;
    }

    public boolean isBinding() {
        return this.binding;
    }

    public ObjectArrayList<Component> getComponents() {
        return this.components;
    }

    public static class ThemeActionComponent
    extends Component {
        private final ModuleComponent parent;
        private final Animation hoverAnim = new Animation(Easing.QUINTIC_OUT, 200L);

        public ThemeActionComponent(ModuleComponent parent) {
            this.parent = parent;
        }

        @Override
        public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
            float bx = this.x + 2.0f;
            float by = this.y + 1.0f;
            float bw = this.width - 4.0f;
            float bh = 13.0f;
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, bx, by, bw, bh);
            this.hoverAnim.run(hov);
            if (hov) {
                CursorManager.requestHand();
            }
            float a2 = this.getAlphaAnim().getValue();
            int bg = ColorProvider.rgba(40, 40, 44, (int)(180.0f * a2));
            int bgH = ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(80.0f * a2 * this.hoverAnim.getValue()));
            DrawUtil.drawRound(bx, by, bw, bh, 3.0f, ColorProvider.interpolateColor(bg, bgH, this.hoverAnim.getValue()));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "Открыть менеджер тем", bx + 3.5f, by + 2.75f, ColorProvider.rgba(200, 200, 200, (int)(255.0f * a2)), 6.5f);
        }

        @Override
        public void mouseClicked(double mouseX, double mouseY, int button) {
            if (HoverUtil.isHovered(mouseX, mouseY, this.x + 2.0f, this.y + 1.0f, this.width - 4.0f, 13.0) && button == 0) {
                ThemeManagerWindow tm = this.parent.getPanel().getParent().getThemeManager();
                tm.toggle();
            }
        }

        @Override
        public void mouseReleased(double mouseX, double mouseY, int button) {
        }

        @Override
        public void keyPressed(int keyCode, int scanCode, int modifiers) {
        }

        @Override
        public float getHeight() {
            return 15.0f;
        }

        @Override
        public boolean isVisible() {
            return true;
        }
    }

    public static class TargetESPActionComponent
    extends Component {
        private final ModuleComponent parent;
        private final Animation hoverAnim = new Animation(Easing.QUINTIC_OUT, 200L);

        public TargetESPActionComponent(ModuleComponent parent) {
            this.parent = parent;
        }

        @Override
        public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
            float bx = this.x + 2.0f;
            float by = this.y + 1.0f;
            float bw = this.width - 4.0f;
            float bh = 13.0f;
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, bx, by, bw, bh);
            this.hoverAnim.run(hov);
            if (hov) {
                CursorManager.requestHand();
            }
            float a2 = this.getAlphaAnim().getValue();
            int bg = ColorProvider.rgba(40, 40, 44, (int)(180.0f * a2));
            int bgH = ColorProvider.setAlpha(ColorProvider.getThemeColor(), (int)(80.0f * a2 * this.hoverAnim.getValue()));
            DrawUtil.drawRound(bx, by, bw, bh, 3.0f, ColorProvider.interpolateColor(bg, bgH, this.hoverAnim.getValue()));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), "Открыть папку текстур", bx + 3.5f, by + 2.75f, ColorProvider.rgba(200, 200, 200, (int)(255.0f * a2)), 6.5f);
        }

        @Override
        public void mouseClicked(double mouseX, double mouseY, int button) {
            if (HoverUtil.isHovered(mouseX, mouseY, this.x + 2.0f, this.y + 1.0f, this.width - 4.0f, 13.0) && button == 0) {
                TargetESP.openCustomFolder();
                ChatUtil.send((Object)"Внимание! После добавления PNG выключите и включите модуль TargetESP, чтобы текстуры появились.");
            }
        }

        @Override
        public void mouseReleased(double mouseX, double mouseY, int button) {
        }

        @Override
        public void keyPressed(int keyCode, int scanCode, int modifiers) {
        }

        @Override
        public float getHeight() {
            return 15.0f;
        }

        @Override
        public boolean isVisible() {
            return true;
        }
    }
}

