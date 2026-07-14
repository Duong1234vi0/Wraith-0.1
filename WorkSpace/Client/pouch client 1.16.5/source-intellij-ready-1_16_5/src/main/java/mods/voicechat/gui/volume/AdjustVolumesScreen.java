/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.volume;

import java.util.Locale;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.O_694_j;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import mods.voicechat.gui.volume.AdjustVolumeList;
import mods.voicechat.gui.widgets.ListScreenBase;

public class AdjustVolumesScreen
extends ListScreenBase {
    protected static final g_2336_b TEXTURE = new g_2336_b("voicechat/textures/gui/gui_volumes.png");
    protected static final x_282_a TITLE = new F_2904_S("gui.voicechat.adjust_volume.title");
    protected static final x_282_a SEARCH_HINT = new F_2904_S("message.voicechat.search_hint").n_1700_B(D_4024_W.Y_259_p).n_1700_B(D_4024_W.w_1484_f);
    protected static final x_282_a EMPTY_SEARCH = new F_2904_S("message.voicechat.search_empty").n_1700_B(D_4024_W.w_1484_f);
    protected static final int HEADER_SIZE = 16;
    protected static final int FOOTER_SIZE = 8;
    protected static final int SEARCH_HEIGHT = 16;
    protected static final int UNIT_SIZE = 18;
    protected static final int CELL_HEIGHT = 36;
    protected AdjustVolumeList volumeList;
    protected O_694_j searchBox;
    protected String lastSearch = "";
    protected int units;

    public AdjustVolumesScreen() {
        super(TITLE, 236, 0);
    }

    @Override
    public void tick() {
        super.tick();
        this.searchBox.tick();
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft += 2;
        this.guiTop = 32;
        int minUnits = u_530_F.u_1723_Y(3.1111112f);
        this.units = Math.max(minUnits, (this.height - 16 - 8 - this.guiTop * 2 - 16) / 18);
        this.ySize = 16 + this.units * 18 + 8;
        this.minecraft.Q_4569_t.n_1700_B(true);
        if (this.volumeList != null) {
            this.volumeList.updateSize(this.width, this.units * 18 - 16, this.guiTop + 16 + 16);
        } else {
            this.volumeList = new AdjustVolumeList(this.width, this.units * 18 - 16, this.guiTop + 16 + 16, 36, this);
        }
        String string = this.searchBox != null ? this.searchBox.getText() : "";
        this.searchBox = new O_694_j(this.font, this.guiLeft + 28, this.guiTop + 16 + 6, 196, 16, SEARCH_HINT);
        this.searchBox.setMaxStringLength(16);
        this.searchBox.setEnableBackgroundDrawing(false);
        this.searchBox.setVisible(true);
        this.searchBox.setTextColor(0xFFFFFF);
        this.searchBox.setText(string);
        this.searchBox.setResponder(this::checkSearchStringUpdate);
        this.addListener(this.searchBox);
        this.addListener(this.volumeList);
    }

    @Override
    public void onClose() {
        super.onClose();
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void renderBackground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.minecraft.G_624_v().n_1700_B(TEXTURE);
        this.blit(poseStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, 16);
        for (int i = 0; i < this.units; ++i) {
            this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * i, 0, 16, this.xSize, 18);
        }
        this.blit(poseStack, this.guiLeft, this.guiTop + 16 + 18 * this.units, 0, 34, this.xSize, 8);
        this.blit(poseStack, this.guiLeft + 10, this.guiTop + 16 + 6 - 2, this.xSize, 0, 12, 12);
    }

    @Override
    public void renderForeground(g_221_o poseStack, int mouseX, int mouseY, float delta) {
        this.font.J_1907_R(poseStack, TITLE, (float)(this.width / 2 - this.font.n_1700_B((FormattedText)TITLE) / 2), (float)(this.guiTop + 5), 0x404040);
        if (!this.volumeList.isEmpty()) {
            this.volumeList.render(poseStack, mouseX, mouseY, delta);
        } else if (!this.searchBox.getText().isEmpty()) {
            AdjustVolumesScreen.drawCenteredString(poseStack, this.font, EMPTY_SEARCH, this.width / 2, this.guiTop + 16 + this.units * 18 / 2 - this.font.n_1700_B / 2, -1);
        }
        if (!this.searchBox.isFocused() && this.searchBox.getText().isEmpty()) {
            AdjustVolumesScreen.drawString(poseStack, this.font, SEARCH_HINT, this.searchBox.x, this.searchBox.y, -1);
        } else {
            this.searchBox.render(poseStack, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBox.isFocused()) {
            this.searchBox.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button) || this.volumeList.mouseClicked(mouseX, mouseY, button);
    }

    private void checkSearchStringUpdate(String string) {
        if (!(string = string.toLowerCase(Locale.ROOT)).equals(this.lastSearch)) {
            this.volumeList.setFilter(string);
            this.lastSearch = string;
        }
    }
}


