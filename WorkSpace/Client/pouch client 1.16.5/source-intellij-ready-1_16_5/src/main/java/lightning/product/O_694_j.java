/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.MutableComponent;
import lightning.product.SharedConstants;
import lightning.product.Widget;
import lightning.product.V_2511_L;
import lightning.product.X_933_l;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class O_694_j
extends V_2511_L
implements GuiEventListener,
Widget {
    private final Y_4083_F fontRenderer;
    private String text = "";
    private int maxStringLength = 32;
    private int cursorCounter;
    private boolean enableBackgroundDrawing = true;
    private boolean canLoseFocus = true;
    private boolean isEnabled = true;
    private boolean field_212956_h;
    private int lineScrollOffset;
    private int cursorPosition;
    private int selectionEnd;
    private int enabledColor = 0xE0E0E0;
    private int disabledColor = 0x707070;
    private String suggestion;
    private Consumer<String> guiResponder;
    private Predicate<String> validator = Objects::nonNull;
    private BiFunction<String, Integer, FormattedCharSequence> textFormatter = (p_195610_0_, p_195610_1_) -> FormattedCharSequence.n_1700_B(p_195610_0_, Z_1567_W.n_1700_B);

    public O_694_j(Y_4083_F p_i232260_1_, int p_i232260_2_, int p_i232260_3_, int p_i232260_4_, int p_i232260_5_, x_282_a p_i232260_6_) {
        this(p_i232260_1_, p_i232260_2_, p_i232260_3_, p_i232260_4_, p_i232260_5_, null, p_i232260_6_);
    }

    public O_694_j(Y_4083_F p_i232259_1_, int p_i232259_2_, int p_i232259_3_, int p_i232259_4_, int p_i232259_5_, @Nullable O_694_j p_i232259_6_, x_282_a p_i232259_7_) {
        super(p_i232259_2_, p_i232259_3_, p_i232259_4_, p_i232259_5_, p_i232259_7_);
        this.fontRenderer = p_i232259_1_;
        if (p_i232259_6_ != null) {
            this.setText(p_i232259_6_.getText());
        }
    }

    public void setResponder(Consumer<String> rssponderIn) {
        this.guiResponder = rssponderIn;
    }

    public void setTextFormatter(BiFunction<String, Integer, FormattedCharSequence> textFormatterIn) {
        this.textFormatter = textFormatterIn;
    }

    public void tick() {
        ++this.cursorCounter;
    }

    @Override
    protected MutableComponent getNarrationMessage() {
        x_282_a itextcomponent = this.getMessage();
        return new F_2904_S("gui.narrate.editBox", itextcomponent, this.text);
    }

    public void setText(String textIn) {
        if (this.validator.test(textIn)) {
            this.text = textIn.length() > this.maxStringLength ? textIn.substring(0, this.maxStringLength) : textIn;
            this.setCursorPositionEnd();
            this.setSelectionPos(this.cursorPosition);
            this.onTextChanged(textIn);
        }
    }

    public String getText() {
        return this.text;
    }

    public String getSelectedText() {
        int i = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
        int j = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
        return this.text.substring(i, j);
    }

    public void setValidator(Predicate<String> validatorIn) {
        this.validator = validatorIn;
    }

    public void writeText(String textToWrite) {
        String s1;
        String s;
        int l;
        int i = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
        int j = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
        int k = this.maxStringLength - this.text.length() - (i - j);
        if (k < (l = (s = SharedConstants.n_1700_B(textToWrite)).length())) {
            s = s.substring(0, k);
            l = k;
        }
        if (this.validator.test(s1 = new StringBuilder(this.text).replace(i, j, s).toString())) {
            this.text = s1;
            this.clampCursorPosition(i + l);
            this.setSelectionPos(this.cursorPosition);
            this.onTextChanged(this.text);
        }
    }

    private void onTextChanged(String newText) {
        if (this.guiResponder != null) {
            this.guiResponder.accept(newText);
        }
        this.nextNarration = j_3341_s.J_1907_R() + 500L;
    }

    private void delete(int p_212950_1_) {
        if (k_2603_m.hasControlDown()) {
            this.deleteWords(p_212950_1_);
        } else {
            this.deleteFromCursor(p_212950_1_);
        }
    }

    public void deleteWords(int num) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                this.deleteFromCursor(this.getNthWordFromCursor(num) - this.cursorPosition);
            }
        }
    }

    public void deleteFromCursor(int num) {
        if (!this.text.isEmpty()) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                String s;
                int k;
                int i = this.func_238516_r_(num);
                int j = Math.min(i, this.cursorPosition);
                if (j != (k = Math.max(i, this.cursorPosition)) && this.validator.test(s = new StringBuilder(this.text).delete(j, k).toString())) {
                    this.text = s;
                    this.setCursorPosition(j);
                }
            }
        }
    }

    public int getNthWordFromCursor(int numWords) {
        return this.getNthWordFromPos(numWords, this.getCursorPosition());
    }

    private int getNthWordFromPos(int n, int pos) {
        return this.getNthWordFromPosWS(n, pos, true);
    }

    private int getNthWordFromPosWS(int n, int pos, boolean skipWs) {
        int i = pos;
        boolean flag = n < 0;
        int j = Math.abs(n);
        for (int k = 0; k < j; ++k) {
            if (!flag) {
                int l = this.text.length();
                if ((i = this.text.indexOf(32, i)) == -1) {
                    i = l;
                    continue;
                }
                while (skipWs && i < l && this.text.charAt(i) == ' ') {
                    ++i;
                }
                continue;
            }
            while (skipWs && i > 0 && this.text.charAt(i - 1) == ' ') {
                --i;
            }
            while (i > 0 && this.text.charAt(i - 1) != ' ') {
                --i;
            }
        }
        return i;
    }

    public void moveCursorBy(int num) {
        this.setCursorPosition(this.func_238516_r_(num));
    }

    private int func_238516_r_(int p_238516_1_) {
        return j_3341_s.n_1700_B(this.text, this.cursorPosition, p_238516_1_);
    }

    public void setCursorPosition(int pos) {
        this.clampCursorPosition(pos);
        if (!this.field_212956_h) {
            this.setSelectionPos(this.cursorPosition);
        }
        this.onTextChanged(this.text);
    }

    public void clampCursorPosition(int pos) {
        this.cursorPosition = u_530_F.n_1700_B(pos, 0, this.text.length());
    }

    public void setCursorPositionZero() {
        this.setCursorPosition(0);
    }

    public void setCursorPositionEnd() {
        this.setCursorPosition(this.text.length());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.canWrite()) {
            return false;
        }
        this.field_212956_h = k_2603_m.hasShiftDown();
        if (k_2603_m.isSelectAll(keyCode)) {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        if (k_2603_m.isCopy(keyCode)) {
            MinecraftClient.A_4115_X().Q_4569_t.n_1700_B(this.getSelectedText());
            return true;
        }
        if (k_2603_m.isPaste(keyCode)) {
            if (this.isEnabled) {
                this.writeText(MinecraftClient.A_4115_X().Q_4569_t.n_1700_B());
            }
            return true;
        }
        if (k_2603_m.isCut(keyCode)) {
            MinecraftClient.A_4115_X().Q_4569_t.n_1700_B(this.getSelectedText());
            if (this.isEnabled) {
                this.writeText("");
            }
            return true;
        }
        switch (keyCode) {
            case 259: {
                if (this.isEnabled) {
                    this.field_212956_h = false;
                    this.delete(-1);
                    this.field_212956_h = k_2603_m.hasShiftDown();
                }
                return true;
            }
            default: {
                return false;
            }
            case 261: {
                if (this.isEnabled) {
                    this.field_212956_h = false;
                    this.delete(1);
                    this.field_212956_h = k_2603_m.hasShiftDown();
                }
                return true;
            }
            case 262: {
                if (k_2603_m.hasControlDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                } else {
                    this.moveCursorBy(1);
                }
                return true;
            }
            case 263: {
                if (k_2603_m.hasControlDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                } else {
                    this.moveCursorBy(-1);
                }
                return true;
            }
            case 268: {
                this.setCursorPositionZero();
                return true;
            }
            case 269: 
        }
        this.setCursorPositionEnd();
        return true;
    }

    public boolean canWrite() {
        return this.getVisible() && this.isFocused() && this.isEnabled();
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (!this.canWrite()) {
            return false;
        }
        if (SharedConstants.n_1700_B(codePoint)) {
            if (this.isEnabled) {
                this.writeText(Character.toString(codePoint));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean flag;
        if (!this.getVisible()) {
            return false;
        }
        boolean bl = flag = mouseX >= (double)this.x && mouseX < (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY < (double)(this.y + this.height);
        if (this.canLoseFocus) {
            this.setFocused2(flag);
        }
        if (this.isFocused() && flag && button == 0) {
            int i = u_530_F.R_4764_Y(mouseX) - this.x;
            if (this.enableBackgroundDrawing) {
                i -= 4;
            }
            String s = this.fontRenderer.n_1700_B(this.text.substring(this.lineScrollOffset), this.getAdjustedWidth());
            this.setCursorPosition(this.fontRenderer.n_1700_B(s, i).length() + this.lineScrollOffset);
            return true;
        }
        return false;
    }

    public void setFocused2(boolean isFocusedIn) {
        super.setFocused(isFocusedIn);
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.getVisible()) {
            if (this.getEnableBackgroundDrawing()) {
                int i = this.isFocused() ? -1 : -6250336;
                O_694_j.fill(matrixStack, this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, i);
                O_694_j.fill(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
            }
            int i2 = this.isEnabled ? this.enabledColor : this.disabledColor;
            int j = this.cursorPosition - this.lineScrollOffset;
            int k = this.selectionEnd - this.lineScrollOffset;
            String s = this.fontRenderer.n_1700_B(this.text.substring(this.lineScrollOffset), this.getAdjustedWidth());
            boolean flag = j >= 0 && j <= s.length();
            boolean flag1 = this.isFocused() && this.cursorCounter / 6 % 2 == 0 && flag;
            int l = this.enableBackgroundDrawing ? this.x + 4 : this.x;
            int i1 = this.enableBackgroundDrawing ? this.y + (this.height - 8) / 2 : this.y;
            int j1 = l;
            if (k > s.length()) {
                k = s.length();
            }
            if (!s.isEmpty()) {
                String s1 = flag ? s.substring(0, j) : s;
                j1 = this.fontRenderer.n_1700_B(matrixStack, this.textFormatter.apply(s1, this.lineScrollOffset), (float)l, (float)i1, i2);
            }
            boolean flag2 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            int k1 = j1;
            if (!flag) {
                k1 = j > 0 ? l + this.width : l;
            } else if (flag2) {
                k1 = j1 - 1;
                --j1;
            }
            if (!s.isEmpty() && flag && j < s.length()) {
                this.fontRenderer.n_1700_B(matrixStack, this.textFormatter.apply(s.substring(j), this.cursorPosition), (float)j1, (float)i1, i2);
            }
            if (!flag2 && this.suggestion != null) {
                this.fontRenderer.n_1700_B(matrixStack, this.suggestion, (float)(k1 - 1), (float)i1, -8355712);
            }
            if (flag1) {
                if (flag2) {
                    C_2701_A.fill(matrixStack, k1, i1 - 1, k1 + 1, i1 + 1 + 9, -3092272);
                } else {
                    this.fontRenderer.n_1700_B(matrixStack, "_", (float)k1, (float)i1, i2);
                }
            }
            if (k != j) {
                int l1 = l + this.fontRenderer.J_1907_R(s.substring(0, k));
                this.drawSelectionBox(k1, i1 - 1, l1 - 1, i1 + 1 + 9);
            }
        }
    }

    private void drawSelectionBox(int startX, int startY, int endX, int endY) {
        if (startX < endX) {
            int i = startX;
            startX = endX;
            endX = i;
        }
        if (startY < endY) {
            int j = startY;
            startY = endY;
            endY = j;
        }
        if (endX > this.x + this.width) {
            endX = this.x + this.width;
        }
        if (startX > this.x + this.width) {
            startX = this.x + this.width;
        }
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        c_4037_x.G_564_y(0.0f, 0.0f, 255.0f, 255.0f);
        c_4037_x.e_4240_b();
        c_4037_x.Y_1740_V();
        c_4037_x.n_1700_B(X_933_l.h_1847_R.h_1847_R);
        bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
        bufferbuilder.pos(startX, endY, 0.0).endVertex();
        bufferbuilder.pos(endX, endY, 0.0).endVertex();
        bufferbuilder.pos(endX, startY, 0.0).endVertex();
        bufferbuilder.pos(startX, startY, 0.0).endVertex();
        tessellator.J_1907_R();
        c_4037_x.t_4043_B();
        c_4037_x.x_607_J();
    }

    public void setMaxStringLength(int length) {
        this.maxStringLength = length;
        if (this.text.length() > length) {
            this.text = this.text.substring(0, length);
            this.onTextChanged(this.text);
        }
    }

    private int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int getCursorPosition() {
        return this.cursorPosition;
    }

    private boolean getEnableBackgroundDrawing() {
        return this.enableBackgroundDrawing;
    }

    public void setEnableBackgroundDrawing(boolean enableBackgroundDrawingIn) {
        this.enableBackgroundDrawing = enableBackgroundDrawingIn;
    }

    public void setTextColor(int color) {
        this.enabledColor = color;
    }

    public void setDisabledTextColour(int color) {
        this.disabledColor = color;
    }

    @Override
    public boolean changeFocus(boolean focus) {
        return this.visible && this.isEnabled ? super.changeFocus(focus) : false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.visible && mouseX >= (double)this.x && mouseX < (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY < (double)(this.y + this.height);
    }

    @Override
    protected void onFocusedChanged(boolean focused) {
        if (focused) {
            this.cursorCounter = 0;
        }
    }

    private boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public int getAdjustedWidth() {
        return this.getEnableBackgroundDrawing() ? this.width - 8 : this.width;
    }

    public void setSelectionPos(int position) {
        int i = this.text.length();
        this.selectionEnd = u_530_F.n_1700_B(position, 0, i);
        if (this.fontRenderer != null) {
            if (this.lineScrollOffset > i) {
                this.lineScrollOffset = i;
            }
            int j = this.getAdjustedWidth();
            String s = this.fontRenderer.n_1700_B(this.text.substring(this.lineScrollOffset), j);
            int k = s.length() + this.lineScrollOffset;
            if (this.selectionEnd == this.lineScrollOffset) {
                this.lineScrollOffset -= this.fontRenderer.n_1700_B(this.text, j, true).length();
            }
            if (this.selectionEnd > k) {
                this.lineScrollOffset += this.selectionEnd - k;
            } else if (this.selectionEnd <= this.lineScrollOffset) {
                this.lineScrollOffset -= this.lineScrollOffset - this.selectionEnd;
            }
            this.lineScrollOffset = u_530_F.n_1700_B(this.lineScrollOffset, 0, i);
        }
    }

    public void setCanLoseFocus(boolean canLoseFocusIn) {
        this.canLoseFocus = canLoseFocusIn;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean isVisible) {
        this.visible = isVisible;
    }

    public void setSuggestion(@Nullable String p_195612_1_) {
        this.suggestion = p_195612_1_;
    }

    public int func_195611_j(int p_195611_1_) {
        return p_195611_1_ > this.text.length() ? this.x : this.x + this.fontRenderer.J_1907_R(this.text.substring(0, p_195611_1_));
    }

    public void setX(int xIn) {
        this.x = xIn;
    }
}



