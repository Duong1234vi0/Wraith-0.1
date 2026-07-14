/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Objects;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.Widget;
import lightning.product.SimpleSoundInstance;
import lightning.product.U_2871_b;
import lightning.product.SoundEvents;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_4218_M;
import lightning.product.u_530_F;
import lightning.product.v_143_j;
import lightning.product.x_282_a;

public abstract class V_2511_L
extends C_2701_A
implements GuiEventListener,
Widget {
    public static final g_2336_b WIDGETS_LOCATION = new g_2336_b("textures/gui/widgets.png");
    protected int width;
    protected int height;
    public int x;
    public int y;
    private x_282_a message;
    private boolean wasHovered;
    protected boolean isHovered;
    public boolean active = true;
    public boolean visible = true;
    protected float alpha = 1.0f;
    protected long nextNarration = Long.MAX_VALUE;
    private boolean focused;

    public V_2511_L(int x, int y, int width, int height, x_282_a title) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = title;
    }

    public int getHeightRealms() {
        return this.height;
    }

    protected int getYImage(boolean isHovered) {
        int i = 1;
        if (!this.active) {
            i = 0;
        } else if (isHovered) {
            i = 2;
        }
        return i;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            boolean bl = this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (this.wasHovered != this.isHovered()) {
                if (this.isHovered()) {
                    if (this.focused) {
                        this.queueNarration(200);
                    } else {
                        this.queueNarration(750);
                    }
                } else {
                    this.nextNarration = Long.MAX_VALUE;
                }
            }
            if (this.visible) {
                this.renderButton(matrixStack, mouseX, mouseY, partialTicks);
            }
            this.narrate();
            this.wasHovered = this.isHovered();
        }
    }

    protected void narrate() {
        String s;
        if (this.active && this.isHovered() && j_3341_s.J_1907_R() > this.nextNarration && !(s = this.getNarrationMessage().getString()).isEmpty()) {
            I_1084_e.J_1907_R.n_1700_B(s);
            this.nextNarration = Long.MAX_VALUE;
        }
    }

    protected MutableComponent getNarrationMessage() {
        return new F_2904_S("gui.narrate.button", this.getMessage());
    }

    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        Y_4083_F fontrenderer = minecraft.t_148_a;
        minecraft.G_624_v().n_1700_B(WIDGETS_LOCATION);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, this.alpha);
        int i = this.getYImage(this.isHovered());
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.multiplayerClientSuggestionProvider();
        this.blit(matrixStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.blit(matrixStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
        int j = this.active ? 0xFFFFFF : 0xA0A0A0;
        V_2511_L.drawCenteredString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | u_530_F.u_1723_Y(this.alpha * 255.0f) << 24);
    }

    protected void renderBg(g_221_o matrixStack, MinecraftClient minecraft, int mouseX, int mouseY) {
    }

    public void onClick(double mouseX, double mouseY) {
    }

    public void onRelease(double mouseX, double mouseY) {
    }

    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            boolean flag;
            if (this.isValidClickButton(button) && (flag = this.clicked(mouseX, mouseY))) {
                this.playDownSound(MinecraftClient.A_4115_X().Z_976_R());
                this.onClick(mouseX, mouseY);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isValidClickButton(button)) {
            this.onRelease(mouseX, mouseY);
            return true;
        }
        return false;
    }

    protected boolean isValidClickButton(int button) {
        return button == 0;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.isValidClickButton(button)) {
            this.onDrag(mouseX, mouseY, dragX, dragY);
            return true;
        }
        return false;
    }

    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double)this.x && mouseY >= (double)this.y && mouseX < (double)(this.x + this.width) && mouseY < (double)(this.y + this.height);
    }

    public boolean isHovered() {
        return this.isHovered || this.focused;
    }

    @Override
    public boolean changeFocus(boolean focus) {
        if (this.active && this.visible) {
            this.focused = !this.focused;
            this.onFocusedChanged(this.focused);
            return this.focused;
        }
        return false;
    }

    protected void onFocusedChanged(boolean focused) {
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double)this.x && mouseY >= (double)this.y && mouseX < (double)(this.x + this.width) && mouseY < (double)(this.y + this.height);
    }

    public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
    }

    public void playDownSound(k_4218_M handler) {
        handler.n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HayBlock, 1.0f));
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setMessage(x_282_a message) {
        if (!Objects.equals(message.getString(), this.message.getString())) {
            this.queueNarration(250);
        }
        this.message = message;
    }

    public void queueNarration(int delay) {
        this.nextNarration = j_3341_s.J_1907_R() + (long)delay;
    }

    public x_282_a getMessage() {
        String fixed;
        if (this.message != null && !(fixed = v_143_j.n_1700_B(this.message.getString())).equals(this.message.getString())) {
            return new U_2871_b(fixed);
        }
        return this.message;
    }

    public boolean isFocused() {
        return this.focused;
    }

    protected void setFocused(boolean focused) {
        this.focused = focused;
    }
}



