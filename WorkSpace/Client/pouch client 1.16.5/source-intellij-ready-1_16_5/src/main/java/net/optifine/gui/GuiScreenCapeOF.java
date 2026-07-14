/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.exceptions.InvalidCredentialsException
 */
package net.optifine.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import net.optifine.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;

public class GuiScreenCapeOF
extends GuiScreenOF {
    private final k_2603_m parentScreen;
    private String message;
    private long messageHideTimeMs;
    private String linkUrl;
    private GuiButtonOF buttonCopyLink;

    public GuiScreenCapeOF(k_2603_m parentScreenIn) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.capeOF.title", new Object[0])));
        this.parentScreen = parentScreenIn;
    }

    @Override
    protected void init() {
        int i = 0;
        this.addButton(new GuiButtonOF(210, this.width / 2 - 155, this.height / 6 + 24 * ((i += 2) >> 1), 150, 20, K_1289_S.n_1700_B("of.options.capeOF.openEditor", new Object[0])));
        this.addButton(new GuiButtonOF(220, this.width / 2 - 155 + 160, this.height / 6 + 24 * (i >> 1), 150, 20, K_1289_S.n_1700_B("of.options.capeOF.reloadCape", new Object[0])));
        this.buttonCopyLink = new GuiButtonOF(230, this.width / 2 - 100, this.height / 6 + 24 * ((i += 6) >> 1), 200, 20, K_1289_S.n_1700_B("of.options.capeOF.copyEditorLink", new Object[0]));
        this.buttonCopyLink.visible = this.linkUrl != null;
        this.addButton(this.buttonCopyLink);
        this.addButton(new GuiButtonOF(200, this.width / 2 - 100, this.height / 6 + 24 * ((i += 4) >> 1), K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
            if (guibuttonof.active) {
                if (guibuttonof.id == 200) {
                    this.minecraft.n_1700_B(this.parentScreen);
                }
                if (guibuttonof.id == 210) {
                    try {
                        String s = this.minecraft.z_1737_N().P_1922_E().getName();
                        String s1 = this.minecraft.z_1737_N().P_1922_E().getId().toString().replace("-", "");
                        String s2 = this.minecraft.z_1737_N().G_564_y();
                        Random random = new Random();
                        Random random1 = new Random(System.identityHashCode(new Object()));
                        BigInteger biginteger = new BigInteger(128, random);
                        BigInteger biginteger1 = new BigInteger(128, random1);
                        BigInteger biginteger2 = biginteger.xor(biginteger1);
                        String s3 = biginteger2.toString(16);
                        this.minecraft.N_2525_X().joinServer(this.minecraft.z_1737_N().P_1922_E(), s2, s3);
                        String s4 = "https://optifine.net/capeChange?u=" + s1 + "&n=" + s + "&s=" + s3;
                        boolean flag = Config.openWebLink(new URI(s4));
                        if (flag) {
                            this.showMessage(Lang.get("of.message.capeOF.openEditor"), 10000L);
                        } else {
                            this.showMessage(Lang.get("of.message.capeOF.openEditorError"), 10000L);
                            this.setLinkUrl(s4);
                        }
                    }
                    catch (InvalidCredentialsException invalidcredentialsexception) {
                        Config.showGuiMessage(K_1289_S.n_1700_B("of.message.capeOF.error1", new Object[0]), K_1289_S.n_1700_B("of.message.capeOF.error2", invalidcredentialsexception.getMessage()));
                        Config.warn("Mojang authentication failed");
                        Config.warn(((Object)((Object)invalidcredentialsexception)).getClass().getName() + ": " + invalidcredentialsexception.getMessage());
                    }
                    catch (Exception exception) {
                        Config.warn("Error opening OptiFine cape link");
                        Config.warn(exception.getClass().getName() + ": " + exception.getMessage());
                    }
                }
                if (guibuttonof.id == 220) {
                    this.showMessage(Lang.get("of.message.capeOF.reloadCape"), 15000L);
                    if (this.minecraft.Y_259_p != null) {
                        long i = 15000L;
                        long j = System.currentTimeMillis() + i;
                        this.minecraft.Y_259_p.n_1700_B(j);
                    }
                }
                if (guibuttonof.id == 230 && this.linkUrl != null) {
                    this.minecraft.Q_4569_t.n_1700_B(this.linkUrl);
                }
            }
        }
    }

    private void showMessage(String msg, long timeMs) {
        this.message = msg;
        this.messageHideTimeMs = System.currentTimeMillis() + timeMs;
        this.setLinkUrl(null);
    }

    @Override
    public void render(g_221_o matrixStackIn, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStackIn);
        GuiScreenCapeOF.drawCenteredString(matrixStackIn, this.fontRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        if (this.message != null) {
            GuiScreenCapeOF.drawCenteredString(matrixStackIn, this.fontRenderer, this.message, this.width / 2, this.height / 6 + 60, 0xFFFFFF);
            if (System.currentTimeMillis() > this.messageHideTimeMs) {
                this.message = null;
                this.setLinkUrl(null);
            }
        }
        super.render(matrixStackIn, mouseX, mouseY, partialTicks);
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        this.buttonCopyLink.visible = linkUrl != null;
    }
}

