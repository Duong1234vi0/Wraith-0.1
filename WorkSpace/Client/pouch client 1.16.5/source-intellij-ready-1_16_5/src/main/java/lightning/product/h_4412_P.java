/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.O_694_j;
import lightning.product.S_4258_d;
import lightning.product.S_499_t;
import lightning.product.T_2971_J;
import lightning.product.U_1085_u;
import lightning.product.SoundEvents;
import lightning.product.Z_1567_W;
import lightning.product.BetterMinecraft;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.ClientBootstrap;
import lightning.product.r_2478_U;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_4642_Y;

public class h_4412_P
extends k_2603_m {
    private String historyBuffer = "";
    private int sentHistoryCursor = -1;
    protected O_694_j inputField;
    private String defaultInputFieldText = "";
    private S_499_t commandSuggestionHelper;
    private static boolean isHVisible = false;
    private static final File CONFIG_FILE = new File(MinecraftClient.A_4115_X().M_182_A, "chat_config.txt");

    public h_4412_P(String defaultText) {
        super(I_1084_e.n_1700_B);
        this.defaultInputFieldText = defaultText;
        this.loadHVisibleState();
    }

    private void loadHVisibleState() {
        if (CONFIG_FILE.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE));){
                String line = reader.readLine();
                if (line != null) {
                    isHVisible = Boolean.parseBoolean(line.trim());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveHVisibleState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE));){
            writer.write(String.valueOf(isHVisible));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.sentHistoryCursor = this.minecraft.M_588_G.R_4764_Y().J_1907_R().size();
        this.inputField = new O_694_j(this.font, 4, this.height - 12, this.width - 4, 12, (x_282_a)new F_2904_S("chat.editBox")){

            @Override
            protected MutableComponent getNarrationMessage() {
                return super.getNarrationMessage().n_1700_B(h_4412_P.this.commandSuggestionHelper.J_1907_R());
            }
        };
        this.inputField.setMaxStringLength(256);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setText(this.defaultInputFieldText);
        this.inputField.setResponder(this::func_212997_a);
        this.children.add(this.inputField);
        this.commandSuggestionHelper = new S_499_t(this.minecraft, this, this.inputField, this.font, false, false, 1, 10, true, -805306368);
        this.commandSuggestionHelper.n_1700_B();
        this.n_1700_B(this.inputField);
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.inputField.getText();
        this.init(minecraft, width, height);
        this.setChatLine(s);
        this.commandSuggestionHelper.n_1700_B();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
        this.minecraft.M_588_G.R_4764_Y().R_4764_Y();
        this.saveHVisibleState();
    }

    @Override
    public void tick() {
        this.inputField.tick();
    }

    private void func_212997_a(String p_2129971) {
        String s = this.inputField.getText();
        this.commandSuggestionHelper.n_1700_B(!s.equals(this.defaultInputFieldText));
        this.commandSuggestionHelper.n_1700_B();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.commandSuggestionHelper.n_1700_B(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == 256) {
            this.minecraft.n_1700_B((k_2603_m)null);
            return true;
        }
        if (keyCode != 257 && keyCode != 335) {
            if (keyCode == 265) {
                this.getSentHistory(-1);
                return true;
            }
            if (keyCode == 264) {
                this.getSentHistory(1);
                return true;
            }
            if (keyCode == 266) {
                this.minecraft.M_588_G.R_4764_Y().n_1700_B((double)(this.minecraft.M_588_G.R_4764_Y().v_4262_N() - 1));
                return true;
            }
            if (keyCode == 267) {
                this.minecraft.M_588_G.R_4764_Y().n_1700_B((double)(-this.minecraft.M_588_G.R_4764_Y().v_4262_N() + 1));
                return true;
            }
            return false;
        }
        String s = this.inputField.getText().trim();
        if (!s.isEmpty()) {
            this.sendMessage(s);
        }
        this.minecraft.n_1700_B((k_2603_m)null);
        return true;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (delta > 1.0) {
            delta = 1.0;
        }
        if (delta < -1.0) {
            delta = -1.0;
        }
        if (this.commandSuggestionHelper.n_1700_B(delta)) {
            return true;
        }
        if (!h_4412_P.hasShiftDown()) {
            delta *= 7.0;
        }
        this.minecraft.M_588_G.R_4764_Y().n_1700_B(delta);
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!y_4642_Y.R_4764_Y()) {
            int yTop = this.height - 14;
            int yBottom = this.height - 2;
            int secondRectWidth = 12;
            int thirdRectWidth = 12;
            int gap = 2;
            int firstRectEndX = this.width - 2 - (secondRectWidth + thirdRectWidth + gap * 2);
            int secondRectStartX = firstRectEndX + gap;
            int secondRectEndX = secondRectStartX + secondRectWidth;
            int thirdRectStartX = secondRectEndX + gap;
            int thirdRectEndX = thirdRectStartX + thirdRectWidth;
            if (button == 0) {
                if (mouseX >= (double)secondRectStartX && mouseX <= (double)secondRectEndX && mouseY >= (double)yTop && mouseY <= (double)yBottom) {
                    this.minecraft.Y_601_j.n_1700_B(this.minecraft.Y_259_p, this.minecraft.Y_259_p.O_3598_v(), this.minecraft.Y_259_p.X_2960_b(), this.minecraft.Y_259_p.l_2647_k(), SoundEvents.HayBlock, D_38_f.n_1700_B, 1.0f, 1.0f);
                    ClientBootstrap.Y_601_j().M_182_A().M_588_G();
                    return true;
                }
                if (mouseX >= (double)thirdRectStartX && mouseX <= (double)thirdRectEndX && mouseY >= (double)yTop && mouseY <= (double)yBottom) {
                    this.minecraft.Y_601_j.n_1700_B(this.minecraft.Y_259_p, this.minecraft.Y_259_p.O_3598_v(), this.minecraft.Y_259_p.X_2960_b(), this.minecraft.Y_259_p.l_2647_k(), SoundEvents.HayBlock, D_38_f.n_1700_B, 1.0f, 1.0f);
                    isHVisible = !isHVisible;
                    this.saveHVisibleState();
                    return true;
                }
            }
        }
        if (this.commandSuggestionHelper.n_1700_B(mouseX, mouseY, button)) {
            return true;
        }
        if (button == 0) {
            U_1085_u newchatgui = this.minecraft.M_588_G.R_4764_Y();
            if (newchatgui.n_1700_B(mouseX, mouseY)) {
                return true;
            }
            Z_1567_W style = newchatgui.J_1907_R(mouseX, mouseY);
            if (style != null && this.handleComponentClicked(style)) {
                return true;
            }
        }
        A_4115_X.n_1700_B(new S_4258_d(button, (float)mouseX, (float)mouseY));
        return this.inputField.mouseClicked(mouseX, mouseY, button) || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        A_4115_X.n_1700_B(new T_2971_J(button, mouseX, mouseY));
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected void insertText(String text, boolean overwrite) {
        if (overwrite) {
            this.inputField.setText(text);
        } else {
            this.inputField.writeText(text);
        }
    }

    public void getSentHistory(int msgPos) {
        int i = this.sentHistoryCursor + msgPos;
        int j = this.minecraft.M_588_G.R_4764_Y().J_1907_R().size();
        if ((i = u_530_F.n_1700_B(i, 0, j)) != this.sentHistoryCursor) {
            if (i == j) {
                this.sentHistoryCursor = j;
                this.inputField.setText(this.historyBuffer);
            } else {
                if (this.sentHistoryCursor == j) {
                    this.historyBuffer = this.inputField.getText();
                }
                this.inputField.setText(this.minecraft.M_588_G.R_4764_Y().J_1907_R().get(i));
                this.commandSuggestionHelper.n_1700_B(false);
                this.sentHistoryCursor = i;
            }
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        int thirdRectStartX;
        int secondRectEndX;
        String inputText;
        this.setListener(this.inputField);
        this.inputField.setFocused2(true);
        boolean isPanic = y_4642_Y.R_4764_Y();
        BetterMinecraft betterMinecraft = isPanic ? null : (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        int chatBackgroundColor = this.minecraft.P_4830_p.n_1700_B(Integer.MIN_VALUE);
        int rectHeight = 12;
        int yTop = this.height - 14;
        int yBottom = this.height - 2;
        int secondRectWidth = 12;
        int thirdRectWidth = 12;
        int gap = 2;
        int firstRectEndX = this.width - 2 - (secondRectWidth + thirdRectWidth + gap * 2);
        if (!isPanic && betterMinecraft != null && betterMinecraft.w_1484_f() && betterMinecraft.Y_259_p().J_1907_R("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u043d\u044b\u0439 \u0432\u0432\u043e\u0434") != null && betterMinecraft.Y_259_p().J_1907_R("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u043d\u044b\u0439 \u0432\u0432\u043e\u0434").booleanValue()) {
            inputText = this.inputField.getText();
            int textWidth = inputText.isEmpty() ? this.font.J_1907_R("_") : this.font.J_1907_R(inputText);
            int minWidth = 2 + this.font.J_1907_R("_") + 4;
            firstRectEndX = Math.max(2 + textWidth + 4, minWidth);
            int maxWidth = this.width - 2 - (secondRectWidth + thirdRectWidth + gap * 2);
            if ((firstRectEndX = Math.min(firstRectEndX, maxWidth)) < minWidth) {
                firstRectEndX = minWidth;
            }
        }
        if (isPanic) {
            h_4412_P.fill(matrixStack, 2, yTop, this.width - 2, yBottom, chatBackgroundColor);
        } else {
            h_4412_P.fill(matrixStack, 2, yTop, firstRectEndX, yBottom, chatBackgroundColor);
            int secondRectStartX = firstRectEndX + gap;
            secondRectEndX = secondRectStartX + secondRectWidth;
            h_4412_P.fill(matrixStack, secondRectStartX, yTop, secondRectEndX, yBottom, chatBackgroundColor);
            thirdRectStartX = secondRectEndX + gap;
            int thirdRectEndX = thirdRectStartX + thirdRectWidth;
            h_4412_P.fill(matrixStack, thirdRectStartX, yTop, thirdRectEndX, yBottom, chatBackgroundColor);
            String textH = "R";
            int textHWidth = this.font.J_1907_R(textH);
            int textHX = secondRectStartX + (secondRectWidth - textHWidth) / 2;
            int textHY = yTop + (rectHeight - this.font.n_1700_B) / 2 + 1;
            this.font.n_1700_B(matrixStack, textH, (float)textHX, (float)textHY, -1);
            String textR = "H";
            int textRWidth = this.font.J_1907_R(textR);
            int textRX = thirdRectStartX + (thirdRectWidth - textRWidth) / 2;
            int textRY = yTop + (rectHeight - this.font.n_1700_B) / 2 + 1;
            this.font.n_1700_B(matrixStack, textR, (float)textRX, (float)textRY, -1);
        }
        this.inputField.render(matrixStack, mouseX, mouseY, partialTicks);
        if (!isPanic) {
            if (isHVisible) {
                inputText = this.inputField.getText();
                int startIndex = -1;
                int endIndex = inputText.length();
                if (inputText.contains("/reg")) {
                    startIndex = inputText.indexOf("/reg") + 1;
                } else if (inputText.contains("/l")) {
                    startIndex = inputText.indexOf("/l") + 1;
                }
                if (startIndex != -1) {
                    String blurText = inputText.substring(startIndex, endIndex);
                    int blurTextWidth = this.font.J_1907_R(blurText);
                    String textBefore = inputText.substring(0, startIndex);
                    int textBeforeWidth = this.font.J_1907_R(textBefore);
                    int inputFieldX = 4;
                    int inputFieldY = this.height - 12;
                    int blurStartX = inputFieldX + textBeforeWidth;
                    int blurEndX = blurStartX + blurTextWidth;
                    int blurEndY = inputFieldY + rectHeight;
                    h_4412_P.fill(matrixStack, blurStartX, inputFieldY + 9, blurEndX, blurEndY - 13, -16777216);
                }
            }
            int secondRectStartX = firstRectEndX + gap;
            secondRectEndX = secondRectStartX + secondRectWidth;
            thirdRectStartX = secondRectEndX + gap;
            int thirdRectEndX = thirdRectStartX + thirdRectWidth;
            if (mouseX >= secondRectStartX && mouseX <= secondRectEndX && mouseY >= yTop && mouseY <= yBottom) {
                this.renderTooltip(matrixStack, new F_2904_S("\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u0440\u0430\u0441\u043f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435"), mouseX, mouseY);
            }
            if (mouseX >= thirdRectStartX && mouseX <= thirdRectEndX && mouseY >= yTop && mouseY <= yBottom) {
                String activeIndicator = isHVisible ? " +" : " -";
                this.renderTooltip(matrixStack, new F_2904_S("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044e" + activeIndicator), mouseX, mouseY);
            }
        }
        this.commandSuggestionHelper.n_1700_B(matrixStack, mouseX, mouseY);
        Z_1567_W style = this.minecraft.M_588_G.R_4764_Y().J_1907_R(mouseX, mouseY);
        if (style != null && style.t_148_a() != null) {
            this.renderComponentHoverEffect(matrixStack, style, mouseX, mouseY);
        }
        A_4115_X.n_1700_B(new r_2478_U(mouseX, mouseY));
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void setChatLine(String p_208604_1_) {
        this.inputField.setText(p_208604_1_);
    }
}



