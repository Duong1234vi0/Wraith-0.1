/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3091_S;
import lightning.product.F_2904_S;
import lightning.product.F_489_x;
import lightning.product.J_1907_R;
import lightning.product.M_1336_P;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.V_4557_X;
import lightning.product.V_772_m;
import lightning.product.ImageButton;
import lightning.product.X_4340_E;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.RecipeUpdateListener;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.BetterMinecraft;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.j_4436_c;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.n_1700_B;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.t_3127_w;
import lightning.product.w_2040_b;
import lightning.product.w_3785_E;
import lightning.product.y_4642_Y;
import lightning.product.y_6_Q;
import lightning.product.RecipeBookMenu;

public class Q_1939_l
extends t_3127_w<y_6_Q>
implements RecipeUpdateListener {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/recipe_button.png");
    private float R_4764_Y;
    private float G_564_y;
    private final j_4436_c P_1922_E = new j_4436_c();
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean Q_2552_b;
    private Button C_2741_M;
    private boolean k_2293_S;
    private V_4557_X q_2307_F = new V_4557_X();
    private final Animation Z_875_P = new Animation(0.0f, 12.0f, Easing.u_2550_I);

    private boolean u_1723_Y() {
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraft == null || !betterMinecraft.w_1484_f()) {
            return false;
        }
        Boolean enabled = betterMinecraft.Z_875_P().J_1907_R("\u0418\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c");
        return Boolean.TRUE.equals(enabled);
    }

    public Q_1939_l(a_3913_L player) {
        super(player.o_1800_r, player.l_1268_F, new F_2904_S("container.crafting"));
        this.passEvents = true;
        this.u_2550_I = 97;
    }

    @Override
    public void tick() {
        X_4340_E player;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 == null) {
            if (this.minecraft.w_1457_N.isInCreativeMode()) {
                this.minecraft.n_1700_B(new B_3091_S(this.minecraft.Y_259_p));
                return;
            }
        } else if (bot1.P_1922_E.C_2741_M.w_1484_f()) {
            this.minecraft.n_1700_B(new B_3091_S(bot1.P_1922_E.Q_2552_b));
            return;
        }
        this.P_1922_E.v_4262_N();
        X_4340_E x_4340_E = player = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
        if (this.C_2741_M != null) {
            boolean bl = this.C_2741_M.active = !player.l_1268_F.Q_2552_b();
        }
        if (this.k_2293_S && this.Q_4569_t != null && this.q_2307_F.J_1907_R(50L)) {
            int maxDrops = 5;
            boolean anyItemToDrop = false;
            block1: for (int iaa = 0; iaa < maxDrops; ++iaa) {
                for (Slot slot : ((y_6_Q)this.Q_4569_t).P_1922_E) {
                    if (!slot.J_1907_R()) continue;
                    if (bot1 != null) {
                        bot1.P_1922_E.C_2741_M.n_1700_B(((y_6_Q)this.Q_4569_t).u_1723_Y, slot.G_564_y, slot.n_1700_B().t_4043_B() > 1 ? 1 : 0, a_408_T.P_1922_E, bot1.P_1922_E.Q_2552_b);
                    } else {
                        this.minecraft.w_1457_N.windowClick(((y_6_Q)this.Q_4569_t).u_1723_Y, slot.G_564_y, slot.n_1700_B().t_4043_B() > 1 ? 1 : 0, a_408_T.P_1922_E, this.minecraft.Y_259_p);
                    }
                    anyItemToDrop = true;
                    continue block1;
                }
            }
            if (!anyItemToDrop) {
                this.k_2293_S = false;
                this.closeScreen();
            }
            this.q_2307_F.n_1700_B();
        }
    }

    @Override
    protected void init() {
        boolean isCreative;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        boolean bl = isCreative = bot1 != null ? bot1.P_1922_E.C_2741_M.w_1484_f() : this.minecraft.w_1457_N.isInCreativeMode();
        if (isCreative) {
            if (bot1 != null) {
                this.minecraft.n_1700_B(new B_3091_S(bot1.P_1922_E.Q_2552_b));
            } else {
                this.minecraft.n_1700_B(new B_3091_S(this.minecraft.Y_259_p));
            }
        } else {
            super.init();
            if (!y_4642_Y.R_4764_Y()) {
                this.C_2741_M = this.addButton(new Button(this.width / 2 - 50, this.height / 2 - 105, 100, 20, new U_2871_b("\u0412\u044b\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u0432\u0441\u0435"), button -> {
                    this.k_2293_S = true;
                    this.q_2307_F.n_1700_B();
                }));
                this.C_2741_M.active = !this.minecraft.Y_259_p.l_1268_F.Q_2552_b();
            }
            this.v_4262_N = this.width < 379;
            this.P_1922_E.n_1700_B(this.width, this.height, this.minecraft, this.v_4262_N, (RecipeBookMenu)this.Q_4569_t);
            this.u_1723_Y = true;
            this.multiplayerClientSuggestionProvider = this.P_1922_E.n_1700_B(this.v_4262_N, this.width, this.t_148_a);
            this.children.add(this.P_1922_E);
            this.n_1700_B(this.P_1922_E);
            this.addButton(new ImageButton(this.multiplayerClientSuggestionProvider + 104, this.height / 2 - 22, 20, 18, 0, 0, 19, J_1907_R, button -> {
                this.P_1922_E.n_1700_B(this.v_4262_N);
                this.P_1922_E.P_1922_E();
                this.multiplayerClientSuggestionProvider = this.P_1922_E.n_1700_B(this.v_4262_N, this.width, this.t_148_a);
                ((ImageButton)button).n_1700_B(this.multiplayerClientSuggestionProvider + 104, this.height / 2 - 22);
                this.Q_2552_b = true;
            }));
            if (this.u_1723_Y()) {
                this.Z_875_P.J_1907_R(0.5f);
            }
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        this.font.J_1907_R(matrixStack, this.title, (float)this.u_2550_I, (float)this.M_588_G, 0x404040);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        boolean doAnim = this.u_1723_Y();
        if (doAnim) {
            this.Z_875_P.n_1700_B(1.0f);
            F_489_x.n_1700_B((float)this.width / 2.0f, (float)this.height / 2.0f, this.Z_875_P.n_1700_B());
        }
        boolean bl = this.n_1700_B = !this.P_1922_E.u_1723_Y();
        if (this.P_1922_E.u_1723_Y() && this.v_4262_N) {
            this.n_1700_B(matrixStack, partialTicks, mouseX, mouseY);
            this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        } else {
            this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            this.P_1922_E.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, false, partialTicks);
        }
        this.J_1907_R(matrixStack, mouseX, mouseY);
        this.P_1922_E.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, mouseX, mouseY);
        this.R_4764_Y = mouseX;
        this.G_564_y = mouseY;
        if (doAnim) {
            F_489_x.R_4764_Y();
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(w_1484_f);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E entityToRender = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
        Q_1939_l.n_1700_B(i + 51, j + 75, 30, (float)(i + 51) - this.R_4764_Y, (float)(j + 75 - 50) - this.G_564_y, entityToRender);
    }

    public static void n_1700_B(int posX, int posY, int scale, float mouseX, float mouseY, r_4811_B livingEntity) {
        float f = (float)Math.atan(mouseX / 40.0f);
        float f1 = (float)Math.atan(mouseY / 40.0f);
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y((float)posX, (float)posY, 1050.0f);
        c_4037_x.J_1907_R(1.0f, 1.0f, -1.0f);
        g_221_o matrixstack = new g_221_o();
        matrixstack.n_1700_B(0.0, 0.0, 1000.0);
        matrixstack.n_1700_B(scale, scale, scale);
        w_3785_E quaternion = M_1336_P.u_1723_Y.R_4764_Y(180.0f);
        w_3785_E quaternion1 = M_1336_P.J_1907_R.R_4764_Y(f1 * 20.0f);
        quaternion.n_1700_B(quaternion1);
        matrixstack.n_1700_B(quaternion);
        float f2 = livingEntity.C_1162_e;
        float f3 = livingEntity.p_178_J;
        float f4 = livingEntity.f_4016_n;
        float f5 = livingEntity.JsonUtils;
        float f6 = livingEntity.f_3449_S;
        livingEntity.C_1162_e = 180.0f + f * 20.0f;
        livingEntity.p_178_J = 180.0f + f * 40.0f;
        livingEntity.f_4016_n = -f1 * 20.0f;
        livingEntity.f_3449_S = livingEntity.p_178_J;
        livingEntity.JsonUtils = livingEntity.p_178_J;
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        quaternion1.P_1922_E();
        entityrenderermanager.n_1700_B(quaternion1);
        entityrenderermanager.n_1700_B(false);
        o_3091_w.n_1700_B irendertypebuffer$impl = MinecraftClient.A_4115_X().j_1564_a().J_1907_R();
        c_4037_x.n_1700_B(() -> entityrenderermanager.n_1700_B(livingEntity, 0.0, 0.0, 0.0, 0.0f, 1.0f, matrixstack, irendertypebuffer$impl, 0xF000F0));
        irendertypebuffer$impl.J_1907_R();
        entityrenderermanager.n_1700_B(true);
        livingEntity.C_1162_e = f2;
        livingEntity.p_178_J = f3;
        livingEntity.f_4016_n = f4;
        livingEntity.JsonUtils = f5;
        livingEntity.f_3449_S = f6;
        c_4037_x.d_2461_k();
    }

    @Override
    protected boolean n_1700_B(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.v_4262_N || !this.P_1922_E.u_1723_Y()) && super.n_1700_B(x, y, width, height, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.P_1922_E.mouseClicked(mouseX, mouseY, button)) {
            this.setListener(this.P_1922_E);
            return true;
        }
        return this.v_4262_N && this.P_1922_E.u_1723_Y() ? false : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.Q_2552_b) {
            this.Q_2552_b = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        boolean flag = mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
        return this.P_1922_E.n_1700_B(mouseX, mouseY, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.t_148_a, this.s_956_w, mouseButton) && flag;
    }

    @Override
    protected void n_1700_B(Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        boolean isCtrlShiftClick;
        boolean bl = isCtrlShiftClick = k_2603_m.hasControlDown() && k_2603_m.hasShiftDown() && mouseButton == 1;
        if (isCtrlShiftClick && slotIn != null && !slotIn.n_1700_B().n_1700_B()) {
            X_4340_E player = this.minecraft.Y_259_p;
            n_1700_B controlledBot = null;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                player = bot.P_1922_E.Q_2552_b;
                controlledBot = bot;
            }
            q_1613_l hoveredItem = slotIn.n_1700_B().J_1907_R();
            for (int i = 0; i < ((y_6_Q)this.Q_4569_t).P_1922_E.size(); ++i) {
                Slot slot = (Slot)((y_6_Q)this.Q_4569_t).P_1922_E.get(i);
                Z_1993_T stack = slot.n_1700_B();
                if (stack.n_1700_B() || stack.J_1907_R() != hoveredItem) continue;
                if (controlledBot != null) {
                    controlledBot.P_1922_E.C_2741_M.n_1700_B(((y_6_Q)this.Q_4569_t).u_1723_Y, slot.G_564_y, 1, a_408_T.P_1922_E, controlledBot.P_1922_E.Q_2552_b);
                    continue;
                }
                this.minecraft.w_1457_N.windowClick(((y_6_Q)this.Q_4569_t).u_1723_Y, slot.G_564_y, 1, a_408_T.P_1922_E, this.minecraft.Y_259_p);
            }
            return;
        }
        super.n_1700_B(slotIn, slotId, mouseButton, type);
        this.P_1922_E.n_1700_B(slotIn);
    }

    public void P_1922_E() {
        X_4340_E player = this.minecraft.Y_259_p;
        n_1700_B bot = null;
        boolean isBot = false;
        for (n_1700_B b : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != b.P_1922_E.Q_2552_b) continue;
            bot = b;
            player = bot.P_1922_E.Q_2552_b;
            isBot = true;
            break;
        }
        V_772_m finalPlayer = player;
        n_1700_B finalBot = bot;
        boolean finalIsBot = isBot;
        new Thread(() -> {
            for (int i = 0; i < 46; ++i) {
                try {
                    if (this.minecraft.Y_1740_V != this) break;
                    if (finalIsBot && finalBot != null) {
                        if (!finalPlayer.l_1268_F.s_956_w().n_1700_B()) continue;
                        finalBot.P_1922_E.C_2741_M.n_1700_B(finalPlayer.H_1873_g.u_1723_Y, i, 1, a_408_T.P_1922_E, finalPlayer);
                        Thread.sleep(50L);
                        continue;
                    }
                    if (!this.minecraft.Y_259_p.l_1268_F.s_956_w().n_1700_B()) continue;
                    this.minecraft.w_1457_N.windowClick(this.minecraft.Y_259_p.H_1873_g.u_1723_Y, i, 1, a_408_T.P_1922_E, this.minecraft.Y_259_p);
                    Thread.sleep(50L);
                    continue;
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void n_1700_B() {
        this.P_1922_E.w_1484_f();
    }

    @Override
    public void onClose() {
        if (this.u_1723_Y) {
            this.P_1922_E.G_564_y();
        }
        super.onClose();
    }

    @Override
    public j_4436_c J_1907_R() {
        return this.P_1922_E;
    }
}



