/*
 * Decompiled with CFR 0.152.
 */
package mods.proxy;

import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.O_694_j;
import lightning.product.O_922_L;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftAccess;
import lightning.product.Checkbox;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.k_596_g;
import lightning.product.q_3131_N;
import lightning.product.y_4642_Y;
import mods.proxy.Config;
import mods.proxy.Proxy;
import mods.proxy.ProxyServer;
import mods.proxy.TestPing;

public class GuiProxy
extends k_2603_m {
    private static final String BUY_PROXY_TEXT = "\u041d\u0435\u0442 \u043f\u0440\u043e\u043a\u0441\u0438? \u041d\u0435 \u0431\u0435\u0434\u0430. \u041f\u0435\u0440\u0435\u0445\u043e\u0434\u0438 \u0438 \u043f\u043e\u043a\u0443\u043f\u0430\u0439 \u043b\u0443\u0447\u0448\u0438\u0435 \u043f\u0440\u043e\u043a\u0441\u0438 \u043f\u0440\u044f\u043c\u043e \u043f\u043e \u043a\u043d\u043e\u043f\u043a\u0435!";
    private static final String BUY_PROXY_URL = "https://t.me/zenithmarketbot?start=ref_kotiksene4kin";
    private boolean isSocks4 = false;
    private O_694_j ipPort;
    private O_694_j username;
    private O_694_j password;
    private Checkbox enabledCheck;
    private k_2603_m parentScreen;
    private String msg = "";
    private int[] positionY;
    private int positionX;
    private TestPing testPing = new TestPing();
    private static int sharedProxyCursor = 0;

    public GuiProxy(k_2603_m parentScreen) {
        super(new U_2871_b("Proxy"));
        this.parentScreen = parentScreen;
    }

    private boolean checkProxy() {
        if (!GuiProxy.isValidIpPort(this.ipPort.getText())) {
            this.msg = String.valueOf((Object)D_4024_W.P_4830_p) + "Invalid IP:PORT";
            this.ipPort.changeFocus(true);
            return false;
        }
        return true;
    }

    private static boolean isValidIpPort(String ipP) {
        if (ipP == null || ipP.trim().isEmpty()) {
            return false;
        }
        String[] parts = ipP.split(":");
        if (parts.length != 2) {
            return false;
        }
        try {
            int port = Integer.parseInt(parts[1]);
            if (port < 1 || port > 65535) {
                return false;
            }
        }
        catch (NumberFormatException e) {
            return false;
        }
        String host = parts[0].trim();
        return !host.isEmpty();
    }

    private void centerButtons(int amount, int buttonLength, int gap) {
        this.positionX = this.width / 2 - buttonLength / 2;
        this.positionY = new int[amount];
        int center = (this.height + amount * gap) / 2;
        int buttonStarts = center - amount * gap;
        for (int i = 0; i != amount; ++i) {
            this.positionY[i] = buttonStarts + gap * i;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.ipPort != null && this.ipPort.keyPressed(keyCode, scanCode, modifiers)) {
            this.msg = "";
            this.testPing.state = "";
            return true;
        }
        if (this.username != null && this.username.keyPressed(keyCode, scanCode, modifiers)) {
            this.msg = "";
            this.testPing.state = "";
            return true;
        }
        if (this.password != null && this.password.keyPressed(keyCode, scanCode, modifiers)) {
            this.msg = "";
            this.testPing.state = "";
            return true;
        }
        super.keyPressed(keyCode, scanCode, modifiers);
        this.msg = "";
        this.testPing.state = "";
        return true;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.ipPort != null && this.ipPort.charTyped(codePoint, modifiers)) {
            return true;
        }
        if (this.username != null && this.username.charTyped(codePoint, modifiers)) {
            return true;
        }
        if (this.password != null && this.password.charTyped(codePoint, modifiers)) {
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.ipPort != null && this.ipPort.mouseClicked(mouseX, mouseY, button)) {
            this.setListener(this.ipPort);
            return true;
        }
        if (this.username != null && this.username.mouseClicked(mouseX, mouseY, button)) {
            this.setListener(this.username);
            return true;
        }
        if (this.password != null && this.password.mouseClicked(mouseX, mouseY, button)) {
            this.setListener(this.password);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.enabledCheck.n_1700_B() && !GuiProxy.isValidIpPort(this.ipPort.getText())) {
            this.enabledCheck.onPress();
        }
        GuiProxy.drawString(matrixStack, this.font, "Proxy Type:", this.width / 2 - 149, this.positionY[1] + 5, 0xA0A0A0);
        GuiProxy.drawCenteredString(matrixStack, this.font, "Proxy Authentication (optional)", this.width / 2, this.positionY[3] + 8, (int)D_4024_W.M_182_A.G_564_y());
        GuiProxy.drawString(matrixStack, this.font, "IP:PORT: ", this.width / 2 - 125, this.positionY[2] + 5, 0xA0A0A0);
        this.ipPort.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.isSocks4) {
            GuiProxy.drawString(matrixStack, this.font, "User ID: ", this.width / 2 - 140, this.positionY[4] + 5, 0xA0A0A0);
            this.username.render(matrixStack, mouseX, mouseY, partialTicks);
        } else {
            GuiProxy.drawString(matrixStack, this.font, "Username: ", this.width / 2 - 140, this.positionY[4] + 5, 0xA0A0A0);
            GuiProxy.drawString(matrixStack, this.font, "Password: ", this.width / 2 - 140, this.positionY[5] + 5, 0xA0A0A0);
            this.username.render(matrixStack, mouseX, mouseY, partialTicks);
            this.password.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        GuiProxy.drawCenteredString(matrixStack, this.font, !this.msg.isEmpty() ? this.msg : this.testPing.state, this.width / 2, this.positionY[6] + 5, 0xA0A0A0);
        GuiProxy.drawCenteredString(matrixStack, this.font, BUY_PROXY_TEXT, this.width / 2, this.positionY[8] + 28, (int)D_4024_W.M_182_A.G_564_y());
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        this.testPing.pingPendingNetworks();
        this.ipPort.tick();
        this.username.tick();
        this.password.tick();
    }

    @Override
    public void init() {
        MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B(true);
        this.testPing.setListener((Proxy proxy, long pingMs) -> Config.markProxyWorking(proxy, pingMs));
        int buttonLength = 160;
        this.centerButtons(10, buttonLength, 26);
        this.isSocks4 = ProxyServer.proxy.type == Proxy.ProxyType.SOCKS4;
        Button proxyType = new Button(this.positionX, this.positionY[1], buttonLength, 20, new U_2871_b(this.isSocks4 ? "Socks 4" : "Socks 5"), button -> {
            this.isSocks4 = !this.isSocks4;
            button.setMessage(new U_2871_b(this.isSocks4 ? "Socks 4" : "Socks 5"));
        });
        this.addButton(proxyType);
        this.ipPort = new O_694_j(this.font, this.positionX, this.positionY[2], buttonLength, 20, new U_2871_b(""));
        this.ipPort.setText(ProxyServer.proxy.ipPort);
        this.ipPort.setMaxStringLength(255);
        this.ipPort.changeFocus(true);
        this.children.add(this.ipPort);
        this.username = new O_694_j(this.font, this.positionX, this.positionY[4], buttonLength, 20, new U_2871_b(""));
        this.username.setMaxStringLength(255);
        this.username.setText(ProxyServer.proxy.username);
        this.children.add(this.username);
        this.password = new O_694_j(this.font, this.positionX, this.positionY[5], buttonLength, 20, new U_2871_b(""));
        this.password.setMaxStringLength(255);
        this.password.setText(ProxyServer.proxy.password);
        this.children.add(this.password);
        int posXButtons = this.width / 2 - buttonLength / 2 * 3 / 2;
        Button apply = new Button(posXButtons, this.positionY[8], buttonLength / 2 - 3, 20, new U_2871_b("Apply"), button -> {
            if (this.checkProxy()) {
                ProxyServer.proxy = new Proxy(this.isSocks4, this.ipPort.getText(), this.username.getText(), this.password.getText());
                ProxyServer.proxyEnabled = this.enabledCheck.n_1700_B();
                Config.setDefaultProxy(ProxyServer.proxy);
                Config.saveConfig();
                MinecraftAccess.c_3005_b.n_1700_B(new q_3131_N(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L()));
            }
        });
        this.addButton(apply);
        Button test = new Button(posXButtons + buttonLength / 2 + 3, this.positionY[8], buttonLength / 2 - 3, 20, new U_2871_b("Test"), button -> {
            if (this.ipPort.getText().isEmpty() || this.ipPort.getText().equalsIgnoreCase("none")) {
                this.msg = String.valueOf((Object)D_4024_W.P_4830_p) + "Specify proxy to test";
                return;
            }
            if (this.checkProxy()) {
                this.testPing = new TestPing();
                this.testPing.setListener((Proxy proxy, long pingMs) -> Config.markProxyWorking(proxy, pingMs));
                this.testPing.run("mc.reallyworld.ru", 25565, new Proxy(this.isSocks4, this.ipPort.getText(), this.username.getText(), this.password.getText()));
            }
        });
        this.addButton(test);
        Button freeList = new Button(posXButtons + (buttonLength / 2 + 3) * 3, this.positionY[8], buttonLength / 2 - 3, 20, new U_2871_b("Free List"), button -> {
            Config.refreshSharedFreeProxies();
            Proxy selected = this.pickSharedProxyByType(this.isSocks4);
            if (selected == null) {
                this.msg = String.valueOf((Object)D_4024_W.P_4830_p) + "No free proxies found";
                return;
            }
            this.ipPort.setText(selected.ipPort);
            if (selected.username != null && !selected.username.isEmpty()) {
                this.username.setText(selected.username);
            }
            if (selected.password != null && !selected.password.isEmpty()) {
                this.password.setText(selected.password);
            }
            boolean hasAuth = selected.username != null && !selected.username.isEmpty();
            this.msg = String.valueOf((Object)D_4024_W.u_2550_I) + "Loaded free " + selected.type.name() + " proxy" + (hasAuth ? " (auth)" : "");
        });
        this.addButton(freeList);
        this.enabledCheck = new Checkbox(this.width / 2 - (15 + this.font.J_1907_R("Proxy Enabled")) / 2, this.positionY[7], buttonLength, 20, new U_2871_b("Proxy Enabled"), ProxyServer.proxyEnabled);
        this.addButton(this.enabledCheck);
        Button cancel = new Button(posXButtons + (buttonLength / 2 + 3) * 2, this.positionY[8], buttonLength / 2 - 3, 20, new U_2871_b("Cancel"), button -> MinecraftAccess.c_3005_b.n_1700_B(this.parentScreen));
        this.addButton(cancel);
        Button buy = new Button(this.width / 2 - buttonLength / 4, this.positionY[8] + 42, buttonLength / 2, 20, new U_2871_b("\u041a\u0443\u043f\u0438\u0442\u044c"), button -> j_3341_s.t_148_a().n_1700_B(BUY_PROXY_URL));
        this.addButton(buy);
    }

    @Override
    public void onClose() {
        this.msg = "";
        MinecraftAccess.c_3005_b.Q_4569_t.n_1700_B(false);
    }

    private Proxy pickSharedProxyByType(boolean socks4) {
        List<Proxy> proxies = Config.getSharedFreeProxies();
        if (proxies == null || proxies.isEmpty()) {
            return null;
        }
        Proxy.ProxyType wanted = socks4 ? Proxy.ProxyType.SOCKS4 : Proxy.ProxyType.SOCKS5;
        int size = proxies.size();
        for (int i = 0; i < size; ++i) {
            int idx = Math.floorMod(sharedProxyCursor + i, size);
            Proxy proxy = proxies.get(idx);
            if (proxy == null || proxy.type != wanted || !GuiProxy.isValidIpPort(proxy.ipPort)) continue;
            sharedProxyCursor = idx + 1;
            return proxy;
        }
        return null;
    }
}



