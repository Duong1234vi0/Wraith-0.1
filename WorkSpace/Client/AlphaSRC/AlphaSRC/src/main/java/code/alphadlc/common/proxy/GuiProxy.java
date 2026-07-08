/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_342
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_4286
 *  net.minecraft.class_4286$class_8929
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  net.minecraft.class_500
 *  net.minecraft.class_5348
 */
package code.alphadlc.common.proxy;

import code.alphadlc.common.proxy.Config;
import code.alphadlc.common.proxy.Proxy;
import code.alphadlc.common.proxy.ProxyServer;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4286;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import net.minecraft.class_5348;

public class GuiProxy
extends class_437 {
    private boolean isSocks4 = false;
    private class_342 ipPort;
    private class_342 username;
    private class_342 password;
    private class_4286 enabledCheck;
    private class_437 parentScreen;
    private String msg = "";
    private int[] positionY;
    private int positionX;

    public GuiProxy(class_437 parentScreen) {
        super((class_2561)class_2561.method_43471((String)"PROXY"));
        this.parentScreen = parentScreen;
    }

    private static boolean isValidIpPort(String ipP) {
        if (ipP == null || ipP.isEmpty()) {
            return false;
        }
        String[] split = ipP.split(":");
        if (split.length > 1) {
            if (!GuiProxy.isNumeric(split[1])) {
                return false;
            }
            try {
                int port = Integer.parseInt(split[1]);
                return port >= 0 && port <= 65535;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); ++i) {
            if (Character.isDigit(value.charAt(i))) continue;
            return false;
        }
        return true;
    }

    private boolean checkProxy() {
        if (!GuiProxy.isValidIpPort(this.ipPort.method_1882())) {
            this.msg = String.valueOf(class_124.field_1061) + class_2561.method_43471((String)"\u041e\u0428\u0418\u0411\u041a\u0410 \u0412 \u041d\u0410\u041f\u0418\u0421\u0410\u041d\u0418\u0418 \u0410\u0419\u041f\u0418 \u0410\u0414\u0420\u0415\u0421\u0410 \u0418\u041b\u0418 \u041f\u041e\u0420\u0422\u0410").getString();
            this.ipPort.method_25365(true);
            return false;
        }
        return true;
    }

    private void centerButtons(int amount, int buttonLength, int gap) {
        this.positionX = this.field_22789 / 2 - buttonLength / 2;
        this.positionY = new int[amount];
        int center = (this.field_22790 + amount * gap) / 2;
        int buttonStarts = center - amount * gap;
        for (int i = 0; i != amount; ++i) {
            this.positionY[i] = buttonStarts + gap * i;
        }
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        super.method_25404(keyCode, scanCode, modifiers);
        this.msg = "";
        return true;
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float partialTicks) {
        super.method_25394(context, mouseX, mouseY, partialTicks);
        if (this.enabledCheck.method_20372() && !GuiProxy.isValidIpPort(this.ipPort.method_1882())) {
            this.enabledCheck.method_25306();
        }
        context.method_25303(this.field_22793, class_2561.method_43471((String)"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0430\u0439\u043f\u0438 \u0430\u0434\u0440\u0435\u0441 \u0438 \u043f\u043e\u0440\u0442. \u041f\u0440\u0438\u043c\u0435\u0440 \u043d\u0438\u0436\u0435").getString(), this.field_22789 / 2 - 106, this.positionY[2] - 30, 0xA0A0A0);
        context.method_25303(this.field_22793, class_2561.method_43471((String)"\u0410\u0439\u043f\u0438:\u041f\u043e\u0440\u0442").getString(), this.field_22789 / 2 - 24, this.positionY[2] - 14, 0xA0A0A0);
        this.ipPort.method_25394(context, mouseX, mouseY, partialTicks);
        context.method_25303(this.field_22793, class_2561.method_43471((String)"\u041d\u0438\u043a\u043d\u0435\u0439\u043c").getString(), this.field_22789 / 2 - 20, this.positionY[4] - 14, 0xA0A0A0);
        context.method_25303(this.field_22793, class_2561.method_43471((String)"\u041f\u0430\u0440\u043e\u043b\u044c").getString(), this.field_22789 / 2 - 20, this.positionY[5] + 3, 0xA0A0A0);
        this.username.method_25394(context, mouseX, mouseY, partialTicks);
        this.password.method_25394(context, mouseX, mouseY, partialTicks);
        context.method_25300(this.field_22793, this.msg, this.field_22789 / 2, this.positionY[6] + 5, 0xA0A0A0);
    }

    public void method_25426() {
        int buttonLength = 160;
        this.centerButtons(10, buttonLength, 26);
        this.isSocks4 = ProxyServer.proxy.type == Proxy.ProxyType.SOCKS4;
        this.ipPort = new class_342(this.field_22793, this.positionX, this.positionY[2], buttonLength, 20, (class_2561)class_2561.method_43470((String)""));
        this.ipPort.method_1852(ProxyServer.proxy.ipPort);
        this.ipPort.method_1880(1024);
        this.ipPort.method_25365(true);
        this.method_25429((class_364)this.ipPort);
        this.username = new class_342(this.field_22793, this.positionX, this.positionY[4], buttonLength, 20, (class_2561)class_2561.method_43470((String)""));
        this.username.method_1880(255);
        this.username.method_1852(ProxyServer.proxy.username);
        this.method_25429((class_364)this.username);
        this.password = new class_342(this.field_22793, this.positionX, this.positionY[5] + 15, buttonLength, 20, (class_2561)class_2561.method_43470((String)""));
        this.password.method_1880(255);
        this.password.method_1852(ProxyServer.proxy.password);
        this.method_25429((class_364)this.password);
        int posXButtons = this.field_22789 / 2 - buttonLength / 2 * 3 / 2;
        class_4185 apply = class_4185.method_46430((class_2561)class_2561.method_43471((String)"\u041f\u0440\u0438\u043c\u0435\u043d\u0438\u0442\u044c"), button -> {
            if (this.checkProxy()) {
                ProxyServer.proxy = new Proxy(this.isSocks4, this.ipPort.method_1882(), this.username.method_1882(), this.password.method_1882());
                ProxyServer.proxyEnabled = this.enabledCheck.method_20372();
                Config.setDefaultProxy(ProxyServer.proxy);
                Config.saveConfig();
                class_310.method_1551().method_1507((class_437)new class_500((class_437)new class_442()));
            }
        }).method_46434(posXButtons + (buttonLength / 2 - 65) * 2, this.positionY[8], buttonLength / 2 + 3, 20).method_46431();
        this.method_37063((class_364)apply);
        class_4286.class_8929 checkboxBuilder = class_4286.method_54787((class_2561)class_2561.method_43471((String)"\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438"), (class_327)this.field_22793);
        checkboxBuilder.method_54789(this.field_22789 / 2 - (15 + this.field_22793.method_27525((class_5348)class_2561.method_43471((String)"\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438"))) / 2, this.positionY[7]);
        if (ProxyServer.proxyEnabled) {
            checkboxBuilder.method_54794(ProxyServer.proxyEnabled);
        }
        this.enabledCheck = checkboxBuilder.method_54788();
        this.method_37063((class_364)this.enabledCheck);
        class_4185 cancel = class_4185.method_46430((class_2561)class_2561.method_43471((String)"\u041e\u0442\u043c\u0435\u043d\u0438\u0442\u044c"), button -> class_310.method_1551().method_1507(this.parentScreen)).method_46434(posXButtons + (buttonLength / 2 - 16) * 2, this.positionY[8], buttonLength / 2 - 3, 20).method_46431();
        this.method_37063((class_364)cancel);
    }

    public void method_25419() {
        this.msg = "";
    }
}
