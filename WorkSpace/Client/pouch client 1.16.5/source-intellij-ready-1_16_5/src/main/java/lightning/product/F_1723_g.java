/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_2226_Q;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.ConfirmLinkScreen;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.PlayerSocialManager;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.SocialInteractionsPlayerList;
import lightning.product.x_282_a;

public class F_1723_g
extends k_2603_m {
    protected static final g_2336_b n_1700_B = new g_2336_b("textures/gui/social_interactions.png");
    private static final x_282_a J_1907_R = new F_2904_S("gui.socialInteractions.tab_all");
    private static final x_282_a R_4764_Y = new F_2904_S("gui.socialInteractions.tab_hidden");
    private static final x_282_a G_564_y = new F_2904_S("gui.socialInteractions.tab_blocked");
    private static final x_282_a P_1922_E = J_1907_R.G_564_y().n_1700_B(D_4024_W.Y_601_j);
    private static final x_282_a u_1723_Y = R_4764_Y.G_564_y().n_1700_B(D_4024_W.Y_601_j);
    private static final x_282_a v_4262_N = G_564_y.G_564_y().n_1700_B(D_4024_W.Y_601_j);
    private static final x_282_a w_1484_f = new F_2904_S("gui.socialInteractions.search_hint").n_1700_B(D_4024_W.Y_259_p).n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a t_148_a = new F_2904_S("gui.socialInteractions.search_empty").n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a s_956_w = new F_2904_S("gui.socialInteractions.empty_hidden").n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a u_2550_I = new F_2904_S("gui.socialInteractions.empty_blocked").n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a M_588_G = new F_2904_S("gui.socialInteractions.blocking_hint");
    private SocialInteractionsPlayerList P_4830_p;
    private O_694_j h_1847_R;
    private String Q_4569_t = "";
    private n_1700_B M_182_A = lightning.product.F_1723_g$n_1700_B.n_1700_B;
    private Button t_1786_h;
    private Button multiplayerClientSuggestionProvider;
    private Button w_1457_N;
    private Button Y_601_j;
    @Nullable
    private x_282_a Y_259_p;
    private int Q_2552_b;
    private boolean C_2741_M;
    @Nullable
    private Runnable k_2293_S;

    public F_1723_g() {
        super(new F_2904_S("gui.socialInteractions.title"));
        this.n_1700_B(MinecraftClient.A_4115_X());
    }

    private int n_1700_B() {
        return Math.max(52, this.height - 128 - 16);
    }

    private int J_1907_R() {
        return this.n_1700_B() / 16;
    }

    private int R_4764_Y() {
        return 80 + this.J_1907_R() * 16 - 8;
    }

    private int G_564_y() {
        return (this.width - 238) / 2;
    }

    @Override
    public String getNarrationMessage() {
        return super.getNarrationMessage() + ". " + this.Y_259_p.getString();
    }

    @Override
    public void tick() {
        super.tick();
        this.h_1847_R.tick();
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        if (this.C_2741_M) {
            this.P_4830_p.updateSize(this.width, this.height, 88, this.R_4764_Y());
        } else {
            this.P_4830_p = new SocialInteractionsPlayerList(this, this.minecraft, this.width, this.height, 88, this.R_4764_Y(), 36);
        }
        int i = this.P_4830_p.getRowWidth() / 3;
        int j = this.P_4830_p.getRowLeft();
        int k = this.P_4830_p.func_244736_r();
        int l = this.font.n_1700_B((FormattedText)M_588_G) + 40;
        int i1 = 64 + 16 * this.J_1907_R();
        int j1 = (this.width - l) / 2;
        this.t_1786_h = this.addButton(new Button(j, 45, i, 20, J_1907_R, p_244686_1_ -> this.n_1700_B(lightning.product.F_1723_g$n_1700_B.n_1700_B)));
        this.multiplayerClientSuggestionProvider = this.addButton(new Button((j + k - i) / 2 + 1, 45, i, 20, R_4764_Y, p_244681_1_ -> this.n_1700_B(lightning.product.F_1723_g$n_1700_B.J_1907_R)));
        this.w_1457_N = this.addButton(new Button(k - i + 1, 45, i, 20, G_564_y, p_244769_1_ -> this.n_1700_B(lightning.product.F_1723_g$n_1700_B.R_4764_Y)));
        this.Y_601_j = this.addButton(new Button(j1, i1, l, 20, M_588_G, p_244767_1_ -> this.minecraft.n_1700_B(new ConfirmLinkScreen(p_244771_1_ -> {
            if (p_244771_1_) {
                j_3341_s.t_148_a().n_1700_B("https://aka.ms/javablocking");
            }
            this.minecraft.n_1700_B(this);
        }, "https://aka.ms/javablocking", true))));
        String s = this.h_1847_R != null ? this.h_1847_R.getText() : "";
        this.h_1847_R = new O_694_j(this.font, this.G_564_y() + 28, 78, 196, 16, w_1484_f){

            @Override
            protected MutableComponent getNarrationMessage() {
                return !F_1723_g.this.h_1847_R.getText().isEmpty() && F_1723_g.this.P_4830_p.n_1700_B() ? super.getNarrationMessage().n_1700_B(", ").n_1700_B(t_148_a) : super.getNarrationMessage();
            }
        };
        this.h_1847_R.setMaxStringLength(16);
        this.h_1847_R.setEnableBackgroundDrawing(false);
        this.h_1847_R.setVisible(true);
        this.h_1847_R.setTextColor(0xFFFFFF);
        this.h_1847_R.setText(s);
        this.h_1847_R.setResponder(this::n_1700_B);
        this.children.add(this.h_1847_R);
        this.children.add(this.P_4830_p);
        this.C_2741_M = true;
        this.n_1700_B(this.M_182_A);
    }

    private void n_1700_B(n_1700_B p_244682_1_) {
        this.M_182_A = p_244682_1_;
        this.t_1786_h.setMessage(J_1907_R);
        this.multiplayerClientSuggestionProvider.setMessage(R_4764_Y);
        this.w_1457_N.setMessage(G_564_y);
        Object collection = switch (p_244682_1_.ordinal()) {
            case 0 -> {
                this.t_1786_h.setMessage(P_1922_E);
                yield this.minecraft.Y_259_p.n_1700_B.u_1723_Y();
            }
            case 1 -> {
                this.multiplayerClientSuggestionProvider.setMessage(u_1723_Y);
                yield this.minecraft.dtoRealmsServerAddress().n_1700_B();
            }
            case 2 -> {
                this.w_1457_N.setMessage(v_4262_N);
                PlayerSocialManager filtermanager = this.minecraft.dtoRealmsServerAddress();
                yield this.minecraft.Y_259_p.n_1700_B.u_1723_Y().stream().filter(filtermanager::P_1922_E).collect(Collectors.toSet());
            }
            default -> ImmutableList.of();
        };
        this.M_182_A = p_244682_1_;
        this.P_4830_p.n_1700_B((Collection<UUID>)collection, this.P_4830_p.getScrollAmount());
        if (!this.h_1847_R.getText().isEmpty() && this.P_4830_p.n_1700_B() && !this.h_1847_R.isFocused()) {
            I_1084_e.J_1907_R.n_1700_B(t_148_a.getString());
        } else if (collection.isEmpty()) {
            if (p_244682_1_ == lightning.product.F_1723_g$n_1700_B.J_1907_R) {
                I_1084_e.J_1907_R.n_1700_B(s_956_w.getString());
            } else if (p_244682_1_ == lightning.product.F_1723_g$n_1700_B.R_4764_Y) {
                I_1084_e.J_1907_R.n_1700_B(u_2550_I.getString());
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void renderBackground(g_221_o matrixStack) {
        int i = this.G_564_y() + 3;
        super.renderBackground(matrixStack);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        this.blit(matrixStack, i, 64, 1, 1, 236, 8);
        int j = this.J_1907_R();
        for (int k = 0; k < j; ++k) {
            this.blit(matrixStack, i, 72 + 16 * k, 1, 10, 236, 16);
        }
        this.blit(matrixStack, i, 72 + 16 * j, 1, 27, 236, 8);
        this.blit(matrixStack, i + 10, 76, 243, 1, 12, 12);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.n_1700_B(this.minecraft);
        this.renderBackground(matrixStack);
        if (this.Y_259_p != null) {
            F_1723_g.drawString(matrixStack, this.minecraft.t_148_a, this.Y_259_p, this.G_564_y() + 8, 35, -1);
        }
        if (!this.P_4830_p.n_1700_B()) {
            this.P_4830_p.render(matrixStack, mouseX, mouseY, partialTicks);
        } else if (!this.h_1847_R.getText().isEmpty()) {
            F_1723_g.drawCenteredString(matrixStack, this.minecraft.t_148_a, t_148_a, this.width / 2, (78 + this.R_4764_Y()) / 2, -1);
        } else {
            switch (this.M_182_A.ordinal()) {
                case 1: {
                    F_1723_g.drawCenteredString(matrixStack, this.minecraft.t_148_a, s_956_w, this.width / 2, (78 + this.R_4764_Y()) / 2, -1);
                    break;
                }
                case 2: {
                    F_1723_g.drawCenteredString(matrixStack, this.minecraft.t_148_a, u_2550_I, this.width / 2, (78 + this.R_4764_Y()) / 2, -1);
                }
            }
        }
        if (!this.h_1847_R.isFocused() && this.h_1847_R.getText().isEmpty()) {
            F_1723_g.drawString(matrixStack, this.minecraft.t_148_a, w_1484_f, this.h_1847_R.x, this.h_1847_R.y, -1);
        } else {
            this.h_1847_R.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        this.Y_601_j.visible = this.M_182_A == lightning.product.F_1723_g$n_1700_B.R_4764_Y;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.k_2293_S != null) {
            this.k_2293_S.run();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.h_1847_R.isFocused()) {
            this.h_1847_R.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button) || this.P_4830_p.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.h_1847_R.isFocused() && this.minecraft.P_4830_p.V_1446_Y.n_1700_B(keyCode, scanCode)) {
            this.minecraft.n_1700_B((k_2603_m)null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void n_1700_B(String p_244687_1_) {
        if (!(p_244687_1_ = p_244687_1_.toLowerCase(Locale.ROOT)).equals(this.Q_4569_t)) {
            this.P_4830_p.n_1700_B(p_244687_1_);
            this.Q_4569_t = p_244687_1_;
            this.n_1700_B(this.M_182_A);
        }
    }

    private void n_1700_B(MinecraftClient p_244680_1_) {
        int i = p_244680_1_.k_2293_S().P_1922_E().size();
        if (this.Q_2552_b != i) {
            String s = "";
            ServerData serverdata = p_244680_1_.t_4043_B();
            if (p_244680_1_.x_607_J()) {
                s = p_244680_1_.n_3318_d().z_1333_t();
            } else if (serverdata != null) {
                s = serverdata.n_1700_B;
            }
            this.Y_259_p = i > 1 ? new F_2904_S("gui.socialInteractions.server_label.multiple", s, i) : new F_2904_S("gui.socialInteractions.server_label.single", s, i);
            this.Q_2552_b = i;
        }
    }

    public void n_1700_B(A_2226_Q p_244683_1_) {
        this.P_4830_p.n_1700_B(p_244683_1_, this.M_182_A);
    }

    public void n_1700_B(UUID p_244685_1_) {
        this.P_4830_p.n_1700_B(p_244685_1_);
    }

    public void n_1700_B(@Nullable Runnable p_244684_1_) {
        this.k_2293_S = p_244684_1_;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.F_1723_g$n_1700_B.n_1700_B();
        }
    }
}



