/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_156
 *  net.minecraft.class_1921
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_342
 *  net.minecraft.class_364
 *  net.minecraft.class_437
 */
package code.alphadlc.display.screens.auth;

import code.alphadlc.display.screens.mainmenu.MainMenu;
import code.alphadlc.utils.client.auth.TelegramAuthManager;
import code.alphadlc.utils.client.auth.TelegramAuthService;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.display.gif.GifTextureRenderer;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import java.awt.Color;
import java.awt.Desktop;
import java.net.URI;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_156;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_437;

public class TelegramAuthScreen
extends class_437
implements QuickImports {
    private static final long POLL_INTERVAL_MS = 2500L;
    private static final long SESSION_RELOAD_DELAY_MS = 900L;
    private static final class_2960 BLATNOY_GIF = class_2960.method_60655((String)"minecraft", (String)"gifs/blatnoy.gif");
    private static final class_2960 BACKGROUND_IMAGE = class_2960.method_60655((String)"minecraft", (String)"textures/gui/background.png");
    private static final GifTextureRenderer AUTH_GIF_RENDERER = new GifTextureRenderer(BLATNOY_GIF);
    private static String draftLogin = "";
    private static String draftPassword = "";
    private String authCode = "";
    private String authUrl = "";
    private String statusText = "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043b\u043e\u0433\u0438\u043d \u0438 \u043f\u0430\u0440\u043e\u043b\u044c";
    private boolean loading;
    private boolean awaitingApproval;
    private long nextPollAt;
    private class_342 loginField;
    private class_342 passwordField;

    public TelegramAuthScreen() {
        super(class_2561.method_30163((String)"Telegram Auth"));
    }

    protected void method_25426() {
        super.method_25426();
        TelegramAuthManager.ensureInitialized();
        if (draftLogin.isBlank()) {
            draftLogin = TelegramAuthManager.getSavedLogin();
        }
        if (draftPassword.isBlank()) {
            draftPassword = TelegramAuthManager.getSavedPassword();
        }
        this.initTextFields();
        this.debug("Screen initialized");
    }

    private void initTextFields() {
        int fieldWidth = 236;
        int fieldHeight = 18;
        int x = this.field_22789 / 2 - fieldWidth / 2;
        int y = this.field_22790 / 2 - 24;
        this.loginField = new class_342(this.field_22793, x, y, fieldWidth, fieldHeight, (class_2561)class_2561.method_43470((String)"\u041b\u043e\u0433\u0438\u043d"));
        this.loginField.method_47404((class_2561)class_2561.method_43470((String)"\u041b\u043e\u0433\u0438\u043d"));
        this.loginField.method_1880(32);
        this.loginField.method_1852(draftLogin);
        this.loginField.method_1858(false);
        this.loginField.method_1868(0xF0F0F0);
        this.loginField.method_1860(0x909090);
        this.loginField.method_1863(value -> {
            draftLogin = value == null ? "" : value;
        });
        this.method_25429((class_364)this.loginField);
        this.passwordField = new class_342(this.field_22793, x, y + 30, fieldWidth, fieldHeight, (class_2561)class_2561.method_43470((String)"\u041f\u0430\u0440\u043e\u043b\u044c"));
        this.passwordField.method_47404((class_2561)class_2561.method_43470((String)"\u041f\u0430\u0440\u043e\u043b\u044c"));
        this.passwordField.method_1880(64);
        this.passwordField.method_1852(draftPassword);
        this.passwordField.method_1858(false);
        this.passwordField.method_1868(0xF0F0F0);
        this.passwordField.method_1860(0x909090);
        this.passwordField.method_1863(value -> {
            draftPassword = value == null ? "" : value;
        });
        this.method_25429((class_364)this.passwordField);
        this.method_48265((class_364)this.loginField);
    }

    public void method_25393() {
        super.method_25393();
        if (!this.awaitingApproval || this.authCode.isBlank() || this.loading) {
            return;
        }
        if (System.currentTimeMillis() >= this.nextPollAt) {
            this.pollStatus();
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        context.method_25290(class_1921::method_62277, BACKGROUND_IMAGE, 0, 0, 0.0f, 0.0f, this.field_22789, this.field_22790, this.field_22789, this.field_22790);
        float panelWidth = 276.0f;
        float panelHeight = 246.0f;
        float panelX = (float)this.field_22789 / 2.0f - panelWidth / 2.0f;
        float panelY = (float)this.field_22790 / 2.0f - panelHeight / 2.0f;
        int panelColor = new Color(21, 24, 29, 196).getRGB();
        int panelStroke = new Color(63, 68, 78, 220).getRGB();
        int titleColor = new Color(237, 239, 244, 255).getRGB();
        int subtitleColor = new Color(139, 146, 161, 255).getRGB();
        int actionColor = new Color(67, 150, 255, 255).getRGB();
        rectangle.render(ShapeProperties.create(context.method_51448(), panelX, panelY, panelWidth, panelHeight).round(10.0f).thickness(1.0f).outlineColor(panelStroke).color(panelColor).build());
        AUTH_GIF_RENDERER.render(context, panelX + 14.0f, panelY + 14.0f, 19.0f, 19.0f);
        FontRenderer titleFont = Fonts.getSize(16, Fonts.Type.DEFAULT);
        FontRenderer subtitleFont = Fonts.getSize(10, Fonts.Type.DEFAULT);
        titleFont.drawString(context.method_51448(), "TelegramAuth", panelX + 38.0f, panelY + 17.0f, titleColor);
        subtitleFont.drawString(context.method_51448(), "\u0412\u0445\u043e\u0434 \u0432 AlphaDLC \u0447\u0435\u0440\u0435\u0437 Telegram", panelX + 14.0f, panelY + 40.0f, subtitleColor);
        float fieldWidth = panelWidth - 28.0f;
        float fieldHeight = 22.0f;
        float fieldX = panelX + 14.0f;
        float loginFieldY = panelY + 66.0f;
        float passwordFieldY = loginFieldY + 30.0f;
        if (this.loginField != null && this.passwordField != null) {
            this.loginField.method_46421((int)fieldX);
            this.loginField.method_46419((int)loginFieldY);
            this.loginField.method_25358((int)fieldWidth);
            this.loginField.method_53533((int)fieldHeight);
            this.passwordField.method_46421((int)fieldX);
            this.passwordField.method_46419((int)passwordFieldY);
            this.passwordField.method_25358((int)fieldWidth);
            this.passwordField.method_53533((int)fieldHeight);
            this.drawInputField(context, this.loginField, "\u041b\u043e\u0433\u0438\u043d", fieldX, loginFieldY, fieldWidth, fieldHeight, false);
            this.drawInputField(context, this.passwordField, "\u041f\u0430\u0440\u043e\u043b\u044c", fieldX, passwordFieldY, fieldWidth, fieldHeight, true);
        }
        float buttonWidth = fieldWidth;
        float buttonHeight = 22.0f;
        float loginButtonY = panelY + 128.0f;
        float tgButtonY = loginButtonY + 28.0f;
        this.drawMainButton(context, fieldX, loginButtonY, buttonWidth, buttonHeight, this.loading ? "\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430..." : "\u0412\u043e\u0439\u0442\u0438", !this.loading, mouseX, mouseY, new Color(61, 137, 240, 255).getRGB(), titleColor);
        this.drawSecondaryButton(context, fieldX, tgButtonY, buttonWidth, buttonHeight, "\u0412\u043e\u0439\u0442\u0438 \u0447\u0435\u0440\u0435\u0437 Telegram", !this.loading, mouseX, mouseY, new Color(32, 36, 45, 255).getRGB(), new Color(59, 66, 81, 255).getRGB(), titleColor);
        if (this.statusText != null && !this.statusText.isBlank()) {
            FontRenderer statusFont = Fonts.getSize(10, Fonts.Type.DEFAULT);
            String lowered = this.statusText.toLowerCase(Locale.ROOT);
            boolean isError = lowered.contains("\u043e\u0448\u0438\u0431") || lowered.contains("denied") || lowered.contains("invalid") || lowered.contains("mismatch") || lowered.contains("failed");
            int statusColor = isError ? new Color(255, 114, 114, 255).getRGB() : subtitleColor;
            statusFont.drawCenteredString(context.method_51448(), this.trimToWidth(this.statusText, 52), panelX + panelWidth / 2.0f, panelY + 189.0f, statusColor);
        }
        FontRenderer bottomFont = Fonts.getSize(10, Fonts.Type.DEFAULT);
        String noAccountText = "\u041d\u0435\u0442 \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430? ";
        String registerText = "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u0447\u0435\u0440\u0435\u0437 Telegram";
        float noAccountWidth = bottomFont.getStringWidth(noAccountText);
        float totalWidth = noAccountWidth + bottomFont.getStringWidth(registerText);
        float startX = panelX + (panelWidth - totalWidth) / 2.0f;
        float bottomY = panelY + panelHeight - 18.0f;
        bottomFont.drawString(context.method_51448(), noAccountText, startX, bottomY, subtitleColor);
        bottomFont.drawString(context.method_51448(), registerText, startX + noAccountWidth, bottomY, actionColor);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.method_25402(mouseX, mouseY, button);
        }
        float panelWidth = 276.0f;
        float panelHeight = 246.0f;
        float panelX = (float)this.field_22789 / 2.0f - panelWidth / 2.0f;
        float panelY = (float)this.field_22790 / 2.0f - panelHeight / 2.0f;
        float fieldWidth = panelWidth - 28.0f;
        float fieldHeight = 22.0f;
        float fieldX = panelX + 14.0f;
        float loginFieldY = panelY + 66.0f;
        float passwordFieldY = loginFieldY + 30.0f;
        if (this.isHovered(mouseX, mouseY, fieldX, loginFieldY, fieldWidth, fieldHeight)) {
            this.method_25395((class_364)this.loginField);
            this.loginField.method_25365(true);
            this.passwordField.method_25365(false);
            return true;
        }
        if (this.isHovered(mouseX, mouseY, fieldX, passwordFieldY, fieldWidth, fieldHeight)) {
            this.method_25395((class_364)this.passwordField);
            this.passwordField.method_25365(true);
            this.loginField.method_25365(false);
            return true;
        }
        if (this.loginField != null && this.loginField.method_25402(mouseX, mouseY, button)) {
            return true;
        }
        if (this.passwordField != null && this.passwordField.method_25402(mouseX, mouseY, button)) {
            return true;
        }
        float buttonWidth = fieldWidth;
        float buttonHeight = 22.0f;
        float loginButtonY = panelY + 128.0f;
        float tgButtonY = loginButtonY + 28.0f;
        if (!this.loading && this.isHovered(mouseX, mouseY, fieldX, loginButtonY, buttonWidth, buttonHeight)) {
            this.loginWithCredentials();
            return true;
        }
        if (!this.loading && this.isHovered(mouseX, mouseY, fieldX, tgButtonY, buttonWidth, buttonHeight)) {
            this.beginAuthorization("login");
            return true;
        }
        FontRenderer bottomFont = Fonts.getSize(10, Fonts.Type.DEFAULT);
        String noAccountText = "\u041d\u0435\u0442 \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430? ";
        String registerText = "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u0447\u0435\u0440\u0435\u0437 Telegram";
        float noAccountWidth = bottomFont.getStringWidth(noAccountText);
        float totalWidth = noAccountWidth + bottomFont.getStringWidth(registerText);
        float startX = panelX + (panelWidth - totalWidth) / 2.0f;
        float registerX = startX + noAccountWidth;
        float registerWidth = bottomFont.getStringWidth(registerText);
        float registerY = panelY + panelHeight - 19.0f;
        if (!this.loading && this.isHovered(mouseX, mouseY, registerX, registerY - 1.0f, registerWidth, 11.0)) {
            this.beginAuthorization("register");
            return true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25422() {
        return false;
    }

    private void beginAuthorization(String mode) {
        this.loading = true;
        this.statusText = "\u0421\u043e\u0437\u0434\u0430\u0435\u043c \u0441\u0435\u0441\u0441\u0438\u044e...";
        CompletableFuture.supplyAsync(() -> {
            try {
                return TelegramAuthService.createSession(mode);
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }).orTimeout(12L, TimeUnit.SECONDS).whenComplete((response, throwable) -> mc.execute(() -> {
            this.loading = false;
            if (throwable != null || response == null) {
                this.debugError("Failed to create session", throwable instanceof Throwable ? throwable : new RuntimeException("Unknown auth error"));
                this.statusText = "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0441\u043e\u0437\u0434\u0430\u0442\u044c \u0441\u0435\u0441\u0441\u0438\u044e";
                this.awaitingApproval = false;
                this.authCode = "";
                this.authUrl = "";
                return;
            }
            this.authCode = response.code();
            this.authUrl = response.authUrl();
            this.awaitingApproval = true;
            this.statusText = "\u041e\u0442\u043a\u0440\u044b\u0432\u0430\u0435\u043c Telegram...";
            this.nextPollAt = System.currentTimeMillis() + 900L;
            this.openTelegramLink();
        }));
    }

    private void pollStatus() {
        this.loading = true;
        CompletableFuture.supplyAsync(() -> {
            try {
                return TelegramAuthService.fetchStatus(this.authCode);
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }).orTimeout(10L, TimeUnit.SECONDS).whenComplete((response, throwable) -> mc.execute(() -> {
            this.loading = false;
            this.nextPollAt = System.currentTimeMillis() + 2500L;
            if (throwable != null || response == null) {
                this.debugError("Poll failed", throwable instanceof Throwable ? throwable : new RuntimeException("Unknown poll error"));
                this.statusText = "\u0421\u0435\u0440\u0432\u0435\u0440 \u043d\u0435 \u043e\u0442\u0432\u0435\u0447\u0430\u0435\u0442";
                return;
            }
            String status = response.status() == null ? "" : response.status().toUpperCase(Locale.ROOT);
            String reason = response.reason() == null ? "" : response.reason().trim().toUpperCase(Locale.ROOT);
            String message = response.message() == null ? "" : response.message().trim();
            switch (status) {
                case "APPROVED": {
                    this.awaitingApproval = false;
                    TelegramAuthManager.authorize(new TelegramAuthManager.AuthSession(true, response.telegramId(), response.telegramId(), response.username(), response.displayName(), response.role(), response.subscriptionLabel(), TelegramAuthManager.getAuthToken()));
                    this.openMainMenuWithTransition();
                    break;
                }
                case "WAIT_CREDENTIALS": {
                    this.statusText = message.isBlank() ? "\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0435 \u0432\u0445\u043e\u0434 \u0438 \u043e\u0442\u043f\u0440\u0430\u0432\u044c\u0442\u0435 \u043b\u043e\u0433\u0438\u043d/\u043f\u0430\u0440\u043e\u043b\u044c \u0432 \u0431\u043e\u0442\u0435" : message;
                    break;
                }
                case "REGISTERED": {
                    this.awaitingApproval = false;
                    this.statusText = message.isBlank() ? "\u0410\u043a\u043a\u0430\u0443\u043d\u0442 \u0441\u043e\u0437\u0434\u0430\u043d. \u0422\u0435\u043f\u0435\u0440\u044c \u0432\u043e\u0439\u0434\u0438\u0442\u0435 \u043f\u043e \u043b\u043e\u0433\u0438\u043d\u0443 \u0438 \u043f\u0430\u0440\u043e\u043b\u044e" : message;
                    break;
                }
                case "DENIED": {
                    this.awaitingApproval = false;
                    if (message.isBlank() && "HWID_MISMATCH".equals(reason)) {
                        this.statusText = "HWID \u043d\u0435 \u0441\u043e\u0432\u043f\u0430\u0434\u0430\u0435\u0442. \u0417\u0430\u043f\u0440\u043e\u0441\u0438\u0442\u0435 \u0441\u0431\u0440\u043e\u0441 HWID \u0443 \u0430\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440\u0430 \u0432 \u0431\u043e\u0442\u0435";
                        break;
                    }
                    this.statusText = message.isBlank() ? "\u0414\u043e\u0441\u0442\u0443\u043f \u043e\u0442\u043a\u043b\u043e\u043d\u0435\u043d" : message;
                    break;
                }
                case "EXPIRED": {
                    this.awaitingApproval = false;
                    this.statusText = message.isBlank() ? "\u0421\u0435\u0441\u0441\u0438\u044f \u0438\u0441\u0442\u0435\u043a\u043b\u0430" : message;
                    break;
                }
                default: {
                    this.statusText = message.isBlank() ? "\u041e\u0436\u0438\u0434\u0430\u0435\u043c \u043f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0432 Telegram..." : message;
                }
            }
        }));
    }

    private void loginWithCredentials() {
        String password;
        String login = this.loginField == null ? "" : this.loginField.method_1882().trim();
        String string = password = this.passwordField == null ? "" : this.passwordField.method_1882();
        if (login.isBlank() || password.isBlank()) {
            this.statusText = "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043b\u043e\u0433\u0438\u043d \u0438 \u043f\u0430\u0440\u043e\u043b\u044c";
            return;
        }
        draftLogin = login;
        draftPassword = password;
        this.loading = true;
        this.statusText = "\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0435\u043c \u0434\u0430\u043d\u043d\u044b\u0435...";
        CompletableFuture.supplyAsync(() -> {
            try {
                return TelegramAuthService.loginWithCredentials(login, password);
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }).orTimeout(12L, TimeUnit.SECONDS).whenComplete((session, throwable) -> mc.execute(() -> {
            this.loading = false;
            if (throwable != null || session == null) {
                this.statusText = this.extractErrorMessage((Throwable)throwable, "\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u043b\u043e\u0433\u0438\u043d \u0438\u043b\u0438 \u043f\u0430\u0440\u043e\u043b\u044c");
                return;
            }
            TelegramAuthManager.setSavedCredentials(login, password);
            TelegramAuthManager.authorize(session);
            this.openMainMenuWithTransition();
        }));
    }

    private void openMainMenuWithTransition() {
        MainMenu.INSTANCE.startAuthTransition();
        mc.method_1507((class_437)MainMenu.INSTANCE);
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.loginField != null && this.loginField.method_25400(chr, modifiers)) {
            return true;
        }
        if (this.passwordField != null && this.passwordField.method_25400(chr, modifiers)) {
            return true;
        }
        return super.method_25400(chr, modifiers);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.loginField != null && this.loginField.method_25404(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (this.passwordField != null && this.passwordField.method_25404(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (!(this.loading || keyCode != 257 && keyCode != 335)) {
            this.loginWithCredentials();
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    private void openTelegramLink() {
        block9: {
            if (this.authUrl == null || this.authUrl.isBlank()) {
                this.statusText = "\u041f\u0443\u0441\u0442\u0430\u044f \u0441\u0441\u044b\u043b\u043a\u0430 \u0430\u0432\u0442\u043e\u0440\u0438\u0437\u0430\u0446\u0438\u0438";
                return;
            }
            try {
                if (!this.openViaCommand(this.authUrl)) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(URI.create(this.authUrl));
                    } else {
                        class_156.method_668().method_670(this.authUrl);
                    }
                }
            }
            catch (Throwable firstError) {
                this.debugError("Primary browser open failed: " + firstError.getMessage(), firstError);
                try {
                    if (!this.openViaCommand(this.authUrl)) {
                        class_156.method_668().method_670(this.authUrl);
                    }
                }
                catch (Throwable secondError) {
                    this.debugError("Final browser open failed: " + secondError.getMessage(), secondError);
                    if (this.openViaCommand(this.authUrl)) break block9;
                    this.statusText = "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u0431\u0440\u0430\u0443\u0437\u0435\u0440";
                }
            }
        }
    }

    private boolean openViaCommand(String url) {
        try {
            String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
            if (os.contains("win")) {
                try {
                    new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
                    return true;
                }
                catch (Exception exception) {
                    this.debugError("rundll32 failed: " + exception.getMessage(), exception);
                    try {
                        new ProcessBuilder("powershell", "-NoProfile", "-Command", "Start-Process '" + url.replace("'", "''") + "'").start();
                        return true;
                    }
                    catch (Exception exception2) {
                        this.debugError("PowerShell opener failed: " + exception2.getMessage(), exception2);
                        new ProcessBuilder("cmd", "/c", "start", "", url).start();
                        return true;
                    }
                }
            }
            if (os.contains("mac")) {
                new ProcessBuilder("open", url).start();
                return true;
            }
            new ProcessBuilder("xdg-open", url).start();
            return true;
        }
        catch (Exception exception) {
            this.debugError("openViaCommand failed: " + exception.getMessage(), exception);
            return false;
        }
    }

    private void drawInputField(class_332 context, class_342 field, String placeholder, float x, float y, float width, float height, boolean passwordMode) {
        int drawColor;
        Object draw;
        String value;
        boolean focused = this.method_25399() == field;
        boolean showCaret = focused && System.currentTimeMillis() / 500L % 2L == 0L;
        int fill = new Color(28, 31, 38, 255).getRGB();
        int stroke = focused ? new Color(86, 154, 248, 255).getRGB() : new Color(52, 58, 71, 255).getRGB();
        int textColor = new Color(232, 235, 241, 255).getRGB();
        int placeholderColor = new Color(112, 120, 135, 255).getRGB();
        rectangle.render(ShapeProperties.create(context.method_51448(), x, y, width, height).round(6.0f).thickness(1.0f).outlineColor(stroke).color(fill).build());
        String string = value = field.method_1882() == null ? "" : field.method_1882();
        if (value.isBlank() && !focused) {
            draw = placeholder;
            drawColor = placeholderColor;
        } else if (passwordMode) {
            draw = "\u2022".repeat(Math.max(0, value.length()));
            drawColor = textColor;
        } else {
            draw = value;
            drawColor = textColor;
        }
        if (showCaret) {
            draw = (String)draw + "|";
        }
        FontRenderer inputFont = Fonts.getSize(13, Fonts.Type.DEFAULT);
        inputFont.drawString(context.method_51448(), this.trimToWidth((String)draw, 32), x + 9.0f, this.centeredTextY(inputFont, (String)draw, y, height), drawColor);
    }

    private void drawMainButton(class_332 context, float x, float y, float width, float height, String label, boolean enabled, int mouseX, int mouseY, int fill, int textColor) {
        boolean hovered;
        boolean bl = hovered = enabled && this.isHovered(mouseX, mouseY, x, y, width, height);
        int buttonColor = enabled ? (hovered ? this.brighten(fill, 14) : fill) : new Color(78, 83, 93, 255).getRGB();
        rectangle.render(ShapeProperties.create(context.method_51448(), x, y, width, height).round(6.0f).color(buttonColor).build());
        FontRenderer font = Fonts.getSize(11, Fonts.Type.DEFAULT);
        font.drawCenteredString(context.method_51448(), label, x + width / 2.0f, this.centeredTextY(font, label, y, height), enabled ? textColor : new Color(166, 170, 177, 255).getRGB());
    }

    private void drawSecondaryButton(class_332 context, float x, float y, float width, float height, String label, boolean enabled, int mouseX, int mouseY, int fill, int stroke, int textColor) {
        boolean hovered = enabled && this.isHovered(mouseX, mouseY, x, y, width, height);
        int buttonColor = hovered ? this.brighten(fill, 8) : fill;
        rectangle.render(ShapeProperties.create(context.method_51448(), x, y, width, height).round(6.0f).thickness(1.0f).outlineColor(stroke).color(buttonColor).build());
        FontRenderer font = Fonts.getSize(11, Fonts.Type.DEFAULT);
        font.drawCenteredString(context.method_51448(), label, x + width / 2.0f, this.centeredTextY(font, label, y, height), textColor);
    }

    private float centeredTextY(FontRenderer font, String text, float boxY, float boxHeight) {
        return font.getCenteredY(boxY, boxHeight, text);
    }

    private int brighten(int rgb, int delta) {
        Color color = new Color(rgb, true);
        return new Color(Math.min(255, color.getRed() + delta), Math.min(255, color.getGreen() + delta), Math.min(255, color.getBlue() + delta), color.getAlpha()).getRGB();
    }

    private boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    private void debug(String message) {
        String line = "[TelegramAuth] " + message;
        Logger.info(line);
        System.out.println(line);
    }

    private void debugError(String message, Throwable throwable) {
        String line = "[TelegramAuth] " + message;
        Logger.error(line, throwable);
        System.err.println(line);
        if (throwable != null) {
            throwable.printStackTrace(System.err);
        }
    }

    private String extractErrorMessage(Throwable throwable, String fallback) {
        String best = "";
        for (Throwable current = throwable; current != null; current = current.getCause()) {
            String message = current.getMessage();
            if (message == null || message.isBlank()) continue;
            best = message;
        }
        if (best.isBlank()) {
            return fallback;
        }
        return this.trimToWidth(best.replace("java.lang.RuntimeException:", "").replace("java.io.IOException:", "").trim(), 52);
    }

    private String trimToWidth(String value, int limit) {
        if (value == null || value.length() <= limit) {
            return value == null ? "" : value;
        }
        return value.substring(0, Math.max(0, limit - 3)) + "...";
    }
}
