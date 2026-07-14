/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.List;
import lightning.product.F_1013_a;
import lightning.product.F_2904_S;
import lightning.product.DirectJoinServerScreen;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.MinecraftAccess;
import lightning.product.EditServerScreen;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.m_3545_A;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.t_3048_V;
import lightning.product.v_3049_Q;
import lightning.product.x_282_a;
import lightning.product.LanServer;
import lightning.product.y_4559_d;
import lightning.product.y_4642_Y;
import mods.proxy.Config;
import mods.proxy.GuiProxy;
import mods.proxy.ProxyServer;
import mods.viaversion.viamcp.ViaMCP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class q_3131_N
extends k_2603_m {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final F_1013_a R_4764_Y = new F_1013_a();
    private final k_2603_m G_564_y;
    protected v_3049_Q n_1700_B;
    private m_3545_A P_1922_E;
    private Button u_1723_Y;
    private Button v_4262_N;
    private Button w_1484_f;
    private List<x_282_a> t_148_a;
    private ServerData s_956_w;
    private y_4559_d.J_1907_R u_2550_I;
    private y_4559_d.n_1700_B M_588_G;
    private boolean P_4830_p;

    public q_3131_N(k_2603_m parentScreen) {
        super(new F_2904_S("multiplayer.title"));
        this.G_564_y = parentScreen;
    }

    @Override
    protected void init() {
        super.init();
        this.minecraft.Q_4569_t.n_1700_B(true);
        if (this.P_4830_p) {
            this.n_1700_B.updateSize(this.width, this.height, 32, this.height - 64);
        } else {
            this.P_4830_p = true;
            this.P_1922_E = new m_3545_A(this.minecraft);
            this.P_1922_E.n_1700_B();
            this.u_2550_I = new y_4559_d.J_1907_R();
            try {
                this.M_588_G = new y_4559_d.n_1700_B(this.u_2550_I);
                this.M_588_G.start();
            }
            catch (Exception exception) {
                J_1907_R.warn("Unable to start LAN server detection: {}", (Object)exception.getMessage());
            }
            this.n_1700_B = new v_3049_Q(this, this.minecraft, this.width, this.height, 32, this.height - 64, 36);
            this.n_1700_B.n_1700_B(this.P_1922_E);
        }
        this.children.add(this.n_1700_B);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 154, this.height - 52, 100, 20, new F_2904_S("selectServer.select"), p_214293_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 - 50, this.height - 52, 100, 20, new F_2904_S("selectServer.direct"), p_214286_1_ -> {
            this.s_956_w = new ServerData(K_1289_S.n_1700_B("selectServer.defaultName", new Object[0]), "", false);
            this.minecraft.n_1700_B(new DirectJoinServerScreen(this, this::G_564_y, this.s_956_w));
        }));
        this.addButton(new Button(this.width / 2 + 4 + 50, this.height - 52, 100, 20, new F_2904_S("selectServer.add"), p_214288_1_ -> {
            this.s_956_w = new ServerData(K_1289_S.n_1700_B("selectServer.defaultName", new Object[0]), "", false);
            this.minecraft.n_1700_B(new EditServerScreen(this, this::R_4764_Y, this.s_956_w));
        }));
        this.u_1723_Y = this.addButton(new Button(this.width / 2 - 154, this.height - 28, 70, 20, new F_2904_S("selectServer.edit"), p_214283_1_ -> {
            v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
            if (serverselectionlist$entry instanceof v_3049_Q.G_564_y) {
                ServerData serverdata = ((v_3049_Q.G_564_y)serverselectionlist$entry).J_1907_R();
                this.s_956_w = new ServerData(serverdata.n_1700_B, serverdata.J_1907_R, false);
                this.s_956_w.n_1700_B(serverdata);
                this.minecraft.n_1700_B(new EditServerScreen(this, this::J_1907_R, this.s_956_w));
            }
        }));
        this.w_1484_f = this.addButton(new Button(this.width / 2 - 74, this.height - 28, 70, 20, new F_2904_S("selectServer.delete"), p_214294_1_ -> {
            String s;
            v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
            if (serverselectionlist$entry instanceof v_3049_Q.G_564_y && (s = ((v_3049_Q.G_564_y)serverselectionlist$entry).J_1907_R().n_1700_B) != null) {
                F_2904_S itextcomponent = new F_2904_S("selectServer.deleteQuestion");
                F_2904_S itextcomponent1 = new F_2904_S("selectServer.deleteWarning", s);
                F_2904_S itextcomponent2 = new F_2904_S("selectServer.deleteButton");
                x_282_a itextcomponent3 = CommonComponents.G_564_y;
                this.minecraft.n_1700_B(new q_3418_t(this::n_1700_B, itextcomponent, itextcomponent1, itextcomponent2, itextcomponent3));
            }
        }));
        this.addButton(new Button(this.width / 2 + 4, this.height - 28, 70, 20, new F_2904_S("selectServer.refresh"), p_214291_1_ -> this.P_1922_E()));
        this.addButton(new Button(this.width / 2 + 4 + 76, this.height - 28, 75, 20, CommonComponents.G_564_y, p_214289_1_ -> this.minecraft.n_1700_B(this.G_564_y)));
        String playerName = MinecraftAccess.c_3005_b.z_1737_N().P_1922_E().getName();
        if (!playerName.equals(Config.lastPlayerName)) {
            Config.lastPlayerName = playerName;
            if (Config.accounts.containsKey(playerName)) {
                ProxyServer.proxy = Config.accounts.get(playerName);
            } else if (Config.accounts.containsKey("")) {
                ProxyServer.proxy = Config.accounts.get("");
            }
        }
        q_3131_N ms = this;
        ProxyServer.proxyMenuButton = new Button(ms.width - 125, 5, 120, 20, new U_2871_b("Proxy: " + ProxyServer.getLastUsedProxyIp()), buttonWidget -> MinecraftAccess.c_3005_b.n_1700_B(new GuiProxy(ms)));
        ms.addButton(ProxyServer.proxyMenuButton);
        this.addButton(ViaMCP.INSTANCE.getVersionSelectScreen());
        if (y_4642_Y.R_4764_Y()) {
            ProxyServer.proxyMenuButton.visible = false;
            ViaMCP.INSTANCE.getVersionSelectScreen().visible = false;
        }
        this.J_1907_R();
    }

    @Override
    public void tick() {
        super.tick();
        ViaMCP.INSTANCE.getVersionSelectScreen().tick();
        if (this.u_2550_I.n_1700_B()) {
            List<LanServer> list = this.u_2550_I.R_4764_Y();
            this.u_2550_I.J_1907_R();
            this.n_1700_B.n_1700_B(list);
        }
        this.R_4764_Y.n_1700_B();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
        if (this.M_588_G != null) {
            this.M_588_G.interrupt();
            this.M_588_G = null;
        }
        this.R_4764_Y.J_1907_R();
    }

    private void P_1922_E() {
        this.minecraft.n_1700_B(new q_3131_N(this.G_564_y));
    }

    private void n_1700_B(boolean p_214285_1_) {
        v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
        if (p_214285_1_ && serverselectionlist$entry instanceof v_3049_Q.G_564_y) {
            this.P_1922_E.n_1700_B(((v_3049_Q.G_564_y)serverselectionlist$entry).J_1907_R());
            this.P_1922_E.J_1907_R();
            this.n_1700_B.n_1700_B((v_3049_Q.n_1700_B)null);
            this.n_1700_B.n_1700_B(this.P_1922_E);
        }
        this.minecraft.n_1700_B(this);
    }

    private void J_1907_R(boolean p_214292_1_) {
        v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
        if (p_214292_1_ && serverselectionlist$entry instanceof v_3049_Q.G_564_y) {
            ServerData serverdata = ((v_3049_Q.G_564_y)serverselectionlist$entry).J_1907_R();
            serverdata.n_1700_B = this.s_956_w.n_1700_B;
            serverdata.J_1907_R = this.s_956_w.J_1907_R;
            serverdata.n_1700_B(this.s_956_w);
            this.P_1922_E.J_1907_R();
            this.n_1700_B.n_1700_B(this.P_1922_E);
        }
        this.minecraft.n_1700_B(this);
    }

    private void R_4764_Y(boolean p_214284_1_) {
        if (p_214284_1_) {
            this.P_1922_E.J_1907_R(this.s_956_w);
            this.P_1922_E.J_1907_R();
            this.n_1700_B.n_1700_B((v_3049_Q.n_1700_B)null);
            this.n_1700_B.n_1700_B(this.P_1922_E);
        }
        this.minecraft.n_1700_B(this);
    }

    private void G_564_y(boolean p_214290_1_) {
        if (p_214290_1_) {
            this.n_1700_B(this.s_956_w);
        } else {
            this.minecraft.n_1700_B(this);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == 294) {
            this.P_1922_E();
            return true;
        }
        if (this.n_1700_B.getSelected() != null) {
            if (keyCode != 257 && keyCode != 335) {
                return this.n_1700_B.keyPressed(keyCode, scanCode, modifiers);
            }
            this.n_1700_B();
            return true;
        }
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.t_148_a = null;
        this.renderBackground(matrixStack);
        this.n_1700_B.render(matrixStack, mouseX, mouseY, partialTicks);
        q_3131_N.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.t_148_a != null) {
            this.func_243308_b(matrixStack, this.t_148_a, mouseX, mouseY);
        }
    }

    public void n_1700_B() {
        v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
        if (serverselectionlist$entry instanceof v_3049_Q.G_564_y) {
            this.n_1700_B(((v_3049_Q.G_564_y)serverselectionlist$entry).J_1907_R());
        } else if (serverselectionlist$entry instanceof v_3049_Q.J_1907_R) {
            LanServer lanserverinfo = ((v_3049_Q.J_1907_R)serverselectionlist$entry).n_1700_B();
            this.n_1700_B(new ServerData(lanserverinfo.n_1700_B(), lanserverinfo.J_1907_R(), true));
        }
    }

    private void n_1700_B(ServerData server) {
        this.minecraft.n_1700_B(new t_3048_V(this, this.minecraft, server));
    }

    public void n_1700_B(v_3049_Q.n_1700_B p_214287_1_) {
        this.n_1700_B.n_1700_B(p_214287_1_);
        this.J_1907_R();
    }

    protected void J_1907_R() {
        this.v_4262_N.active = false;
        this.u_1723_Y.active = false;
        this.w_1484_f.active = false;
        v_3049_Q.n_1700_B serverselectionlist$entry = (v_3049_Q.n_1700_B)this.n_1700_B.getSelected();
        if (serverselectionlist$entry != null && !(serverselectionlist$entry instanceof v_3049_Q.R_4764_Y)) {
            this.v_4262_N.active = true;
            if (serverselectionlist$entry instanceof v_3049_Q.G_564_y) {
                this.u_1723_Y.active = true;
                this.w_1484_f.active = true;
            }
        }
    }

    public F_1013_a R_4764_Y() {
        return this.R_4764_Y;
    }

    public void n_1700_B(List<x_282_a> p_238854_1_) {
        this.t_148_a = p_238854_1_;
    }

    public m_3545_A G_564_y() {
        return this.P_1922_E;
    }
}



