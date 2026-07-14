/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  org.lwjgl.glfw.GLFW
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.AutoBuy;
import lightning.product.B_3871_I;
import lightning.product.D_4024_W;
import lightning.product.J_1907_R;
import lightning.product.M_766_z;
import lightning.product.N_4498_h;
import lightning.product.ChestMenu;
import lightning.product.Q_4113_P;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.V_4557_X;
import lightning.product.W_3491_f;
import lightning.product.ItemScroller;
import lightning.product.X_4340_E;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.n_1700_B;
import lightning.product.n_3864_h;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.x_353_w;
import lightning.product.z_1477_l;
import org.lwjgl.glfw.GLFW;

public abstract class z_3427_G<T extends a_2900_S>
extends k_2603_m
implements N_4498_h<T> {
    public static final g_2336_b w_1484_f = new g_2336_b("textures/gui/container/inventory.png");
    protected int t_148_a = 176;
    protected int s_956_w = 166;
    protected int u_2550_I;
    protected int M_588_G;
    protected int P_4830_p;
    protected int h_1847_R;
    protected final T Q_4569_t;
    protected final W_3491_f M_182_A;
    @Nullable
    protected Slot t_1786_h;
    @Nullable
    private Slot n_1700_B;
    @Nullable
    private Slot J_1907_R;
    @Nullable
    private Slot R_4764_Y;
    @Nullable
    private Slot G_564_y;
    protected int multiplayerClientSuggestionProvider;
    protected int w_1457_N;
    private boolean P_1922_E;
    private Z_1993_T u_1723_Y = Z_1993_T.J_1907_R;
    private int v_4262_N;
    private int Q_2552_b;
    private long C_2741_M;
    private Z_1993_T k_2293_S = Z_1993_T.J_1907_R;
    private long q_2307_F;
    private Button Z_875_P;
    private Button c_3005_b;
    private Button H_2857_Y;
    private Button A_4115_X;
    protected final Set<Slot> Y_601_j = Sets.newHashSet();
    protected boolean Y_259_p;
    private int Y_1740_V;
    private int t_4043_B;
    private boolean x_607_J;
    private int e_4240_b;
    private long n_3318_d;
    private int d_2427_y;
    private boolean z_1737_N;
    private Z_1993_T v_4276_D = Z_1993_T.J_1907_R;
    private V_4557_X d_2461_k = new V_4557_X();

    public z_3427_G(T screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(titleIn);
        this.Q_4569_t = screenContainer;
        this.M_182_A = inv;
        this.x_607_J = true;
        this.u_2550_I = 8;
        this.M_588_G = 6;
        this.P_4830_p = 8;
        this.h_1847_R = this.s_956_w - 94;
    }

    public int R_4764_Y() {
        return this.t_148_a;
    }

    public int G_564_y() {
        return this.s_956_w;
    }

    @Override
    protected void init() {
        super.init();
        this.multiplayerClientSuggestionProvider = (this.width - this.t_148_a) / 2;
        this.w_1457_N = (this.height - this.s_956_w) / 2;
        if (this.v_4262_N()) {
            this.t_148_a();
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        Z_1993_T itemstack;
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.n_1700_B(matrixStack, partialTicks, mouseX, mouseY);
        c_4037_x.d_2427_y();
        c_4037_x.t_1786_h();
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.s_956_w();
        lightning.product.A_4115_X.n_1700_B(new n_3864_h.J_1907_R(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, (a_2900_S)this.Q_4569_t));
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y((float)i, (float)j, 0.0f);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.n_3318_d();
        this.t_1786_h = null;
        int k = 240;
        int l = 240;
        c_4037_x.n_1700_B(33986, 240.0f, 240.0f);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        for (int i1 = 0; i1 < ((a_2900_S)this.Q_4569_t).P_1922_E.size(); ++i1) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i1);
            if (slot.u_1723_Y()) {
                this.n_1700_B(matrixStack, slot);
            }
            if (!this.n_1700_B(slot, (double)mouseX, (double)mouseY) || !slot.u_1723_Y()) continue;
            ItemScroller itemScroller = (ItemScroller)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ItemScroller.class);
            if (itemScroller != null && itemScroller.w_1484_f() && GLFW.glfwGetMouseButton((long)MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), (int)0) == 1 && GLFW.glfwGetKey((long)MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), (int)340) == 1 && MinecraftClient.A_4115_X().Y_1740_V != null && this.d_2461_k.J_1907_R(((Float)itemScroller.v_4262_N.J_1907_R()).longValue() * 100L) && slot.J_1907_R()) {
                this.n_1700_B(slot, slot.G_564_y, 1, a_408_T.J_1907_R);
                this.d_2461_k.n_1700_B();
            }
            this.t_1786_h = slot;
            c_4037_x.t_1786_h();
            int j1 = slot.P_1922_E;
            int k1 = slot.u_1723_Y;
            c_4037_x.n_1700_B(true, true, true, false);
            z_3427_G.fillGradient(matrixStack, j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
            c_4037_x.n_1700_B(true, true, true, true);
            c_4037_x.multiplayerClientSuggestionProvider();
        }
        this.n_1700_B(matrixStack, mouseX, mouseY);
        W_3491_f playerinventory = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            playerinventory = bot.P_1922_E.Q_2552_b.l_1268_F;
        }
        if (playerinventory == null) {
            playerinventory = this.minecraft.Y_259_p.l_1268_F;
        }
        Z_1993_T z_1993_T = itemstack = this.u_1723_Y.n_1700_B() ? playerinventory.s_956_w() : this.u_1723_Y;
        if (!itemstack.n_1700_B()) {
            int j2 = 8;
            int k2 = this.u_1723_Y.n_1700_B() ? 8 : 16;
            String s = null;
            if (!this.u_1723_Y.n_1700_B() && this.P_1922_E) {
                itemstack = itemstack.t_148_a();
                itemstack.P_1922_E(u_530_F.u_1723_Y((float)itemstack.t_4043_B() / 2.0f));
            } else if (this.Y_259_p && this.Y_601_j.size() > 1) {
                itemstack = itemstack.t_148_a();
                itemstack.P_1922_E(this.e_4240_b);
                if (itemstack.n_1700_B()) {
                    s = String.valueOf((Object)D_4024_W.Q_4569_t) + "0";
                }
            }
            this.n_1700_B(itemstack, mouseX - i - 8, mouseY - j - k2, s);
        }
        if (!this.k_2293_S.n_1700_B()) {
            float f = (float)(j_3341_s.J_1907_R() - this.C_2741_M) / 100.0f;
            if (f >= 1.0f) {
                f = 1.0f;
                this.k_2293_S = Z_1993_T.J_1907_R;
            }
            int l2 = this.J_1907_R.P_1922_E - this.v_4262_N;
            int i3 = this.J_1907_R.u_1723_Y - this.Q_2552_b;
            int l1 = this.v_4262_N + (int)((float)l2 * f);
            int i2 = this.Q_2552_b + (int)((float)i3 * f);
            this.n_1700_B(this.k_2293_S, l1, i2, (String)null);
        }
        c_4037_x.d_2461_k();
        lightning.product.A_4115_X.n_1700_B(new n_3864_h.n_1700_B(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, (a_2900_S)this.Q_4569_t));
        c_4037_x.multiplayerClientSuggestionProvider();
    }

    protected void J_1907_R(g_221_o matrixStack, int x, int y) {
        X_4340_E player = this.minecraft.Y_259_p;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            player = bot.P_1922_E.Q_2552_b;
        }
        if (player.l_1268_F.s_956_w().n_1700_B() && this.t_1786_h != null && this.t_1786_h.J_1907_R()) {
            this.renderTooltip(matrixStack, this.t_1786_h.n_1700_B(), x, y);
        }
    }

    private void n_1700_B(Z_1993_T stack, int x, int y, String altText) {
        c_4037_x.R_4764_Y(0.0f, 0.0f, 32.0f);
        this.setBlitOffset(200);
        this.itemRenderer.J_1907_R = 200.0f;
        this.itemRenderer.J_1907_R(stack, x, y);
        this.itemRenderer.n_1700_B(this.font, stack, x, y - (this.u_1723_Y.n_1700_B() ? 0 : 8), altText);
        this.setBlitOffset(0);
        this.itemRenderer.J_1907_R = 0.0f;
    }

    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        this.font.J_1907_R(matrixStack, this.title, (float)this.u_2550_I, (float)this.M_588_G, 0x404040);
        this.font.J_1907_R(matrixStack, this.M_182_A.c_(), (float)this.P_4830_p, (float)this.h_1847_R, 0x404040);
    }

    protected abstract void n_1700_B(g_221_o var1, float var2, int var3, int var4);

    private void n_1700_B(g_221_o matrixStack, Slot slot) {
        Pair<g_2336_b, g_2336_b> pair;
        X_4340_E playerMI = this.minecraft.Y_259_p;
        for (n_1700_B botMI : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botMI.P_1922_E.Q_2552_b) continue;
            playerMI = botMI.P_1922_E.Q_2552_b;
        }
        int i = slot.P_1922_E;
        int j = slot.u_1723_Y;
        Z_1993_T itemstack = slot.n_1700_B();
        boolean flag = false;
        boolean flag1 = slot == this.n_1700_B && !this.u_1723_Y.n_1700_B() && !this.P_1922_E;
        Z_1993_T itemstack1 = playerMI.l_1268_F.s_956_w();
        String s = null;
        if (slot == this.n_1700_B && !this.u_1723_Y.n_1700_B() && this.P_1922_E && !itemstack.n_1700_B()) {
            itemstack = itemstack.t_148_a();
            itemstack.P_1922_E(itemstack.t_4043_B() / 2);
        } else if (this.Y_259_p && this.Y_601_j.contains(slot) && !itemstack1.n_1700_B()) {
            if (this.Y_601_j.size() == 1) {
                return;
            }
            if (a_2900_S.n_1700_B(slot, itemstack1, true) && ((a_2900_S)this.Q_4569_t).n_1700_B(slot)) {
                itemstack = itemstack1.t_148_a();
                flag = true;
                a_2900_S.n_1700_B(this.Y_601_j, this.Y_1740_V, itemstack, slot.n_1700_B().n_1700_B() ? 0 : slot.n_1700_B().t_4043_B());
                int k = Math.min(itemstack.R_4764_Y(), slot.R_4764_Y(itemstack));
                if (itemstack.t_4043_B() > k) {
                    s = D_4024_W.Q_4569_t.toString() + k;
                    itemstack.P_1922_E(k);
                }
            } else {
                this.Y_601_j.remove(slot);
                this.P_1922_E();
            }
        }
        this.setBlitOffset(100);
        this.itemRenderer.J_1907_R = 100.0f;
        if (itemstack.n_1700_B() && slot.u_1723_Y() && (pair = slot.P_1922_E()) != null) {
            B_3871_I textureatlassprite = this.minecraft.n_1700_B((g_2336_b)pair.getFirst()).apply((g_2336_b)pair.getSecond());
            this.minecraft.G_624_v().n_1700_B(textureatlassprite.u_2550_I().R_4764_Y());
            z_3427_G.blit(matrixStack, i, j, this.getBlitOffset(), 16, 16, textureatlassprite);
            flag1 = true;
        }
        if (!flag1) {
            if (flag) {
                z_3427_G.fill(matrixStack, i, j, i + 16, j + 16, -2130706433);
            }
            c_4037_x.multiplayerClientSuggestionProvider();
            this.itemRenderer.n_1700_B(playerMI, itemstack, i, j);
            this.itemRenderer.n_1700_B(this.font, itemstack, i, j, s);
        }
        this.itemRenderer.J_1907_R = 0.0f;
        this.setBlitOffset(0);
    }

    private void P_1922_E() {
        X_4340_E playerUDS = this.minecraft.Y_259_p;
        for (n_1700_B botUDS : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botUDS.P_1922_E.Q_2552_b) continue;
            playerUDS = botUDS.P_1922_E.Q_2552_b;
        }
        Z_1993_T itemstack = playerUDS.l_1268_F.s_956_w();
        if (!itemstack.n_1700_B() && this.Y_259_p) {
            if (this.Y_1740_V == 2) {
                this.e_4240_b = itemstack.R_4764_Y();
            } else {
                this.e_4240_b = itemstack.t_4043_B();
                for (Slot slot : this.Y_601_j) {
                    Z_1993_T itemstack1 = itemstack.t_148_a();
                    Z_1993_T itemstack2 = slot.n_1700_B();
                    int i = itemstack2.n_1700_B() ? 0 : itemstack2.t_4043_B();
                    a_2900_S.n_1700_B(this.Y_601_j, this.Y_1740_V, itemstack1, i);
                    int j = Math.min(itemstack1.R_4764_Y(), slot.R_4764_Y(itemstack1));
                    if (itemstack1.t_4043_B() > j) {
                        itemstack1.P_1922_E(j);
                    }
                    this.e_4240_b -= itemstack1.t_4043_B() - i;
                }
            }
        }
    }

    @Nullable
    private Slot R_4764_Y(double mouseX, double mouseY) {
        for (int i = 0; i < ((a_2900_S)this.Q_4569_t).P_1922_E.size(); ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!this.n_1700_B(slot, mouseX, mouseY) || !slot.u_1723_Y()) continue;
            return slot;
        }
        return null;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        X_4340_E playerMC = this.minecraft.Y_259_p;
        for (n_1700_B botMC : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botMC.P_1922_E.Q_2552_b) continue;
            playerMC = botMC.P_1922_E.Q_2552_b;
        }
        boolean flag = this.minecraft.P_4830_p.k_3961_g.n_1700_B(button);
        Slot slot = this.R_4764_Y(mouseX, mouseY);
        if (button == 2 && slot != null && slot.J_1907_R()) {
            System.out.println(slot.n_1700_B().Q_4569_t() != null ? slot.n_1700_B().Q_4569_t().toString() : "\u041d\u0435\u0442 NBT");
            return true;
        }
        long i = j_3341_s.J_1907_R();
        this.z_1737_N = this.G_564_y == slot && i - this.n_3318_d < 250L && this.d_2427_y == button;
        this.x_607_J = false;
        if (button != 0 && button != 1 && !flag) {
            this.n_1700_B(button);
        } else {
            int j = this.multiplayerClientSuggestionProvider;
            int k = this.w_1457_N;
            boolean flag1 = this.n_1700_B(mouseX, mouseY, j, k, button);
            int l = -1;
            if (slot != null) {
                l = slot.G_564_y;
            }
            if (flag1) {
                l = -999;
            }
            if (this.minecraft.P_4830_p.c_4037_x && flag1 && playerMC.l_1268_F.s_956_w().n_1700_B()) {
                this.minecraft.n_1700_B((k_2603_m)null);
                return true;
            }
            if (l != -1) {
                if (this.minecraft.P_4830_p.c_4037_x) {
                    if (slot != null && slot.J_1907_R()) {
                        this.n_1700_B = slot;
                        this.u_1723_Y = Z_1993_T.J_1907_R;
                        this.P_1922_E = button == 1;
                    } else {
                        this.n_1700_B = null;
                    }
                } else if (!this.Y_259_p) {
                    if (playerMC.l_1268_F.s_956_w().n_1700_B()) {
                        if (this.minecraft.P_4830_p.k_3961_g.n_1700_B(button)) {
                            this.n_1700_B(slot, l, button, a_408_T.G_564_y);
                        } else {
                            boolean flag2 = l != -999 && (Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 340) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 344));
                            a_408_T clicktype = a_408_T.n_1700_B;
                            if (flag2) {
                                this.v_4276_D = slot != null && slot.J_1907_R() ? slot.n_1700_B().t_148_a() : Z_1993_T.J_1907_R;
                                clicktype = a_408_T.J_1907_R;
                            } else if (l == -999) {
                                clicktype = a_408_T.P_1922_E;
                            }
                            this.n_1700_B(slot, l, button, clicktype);
                        }
                        this.x_607_J = true;
                    } else {
                        this.Y_259_p = true;
                        this.t_4043_B = button;
                        this.Y_601_j.clear();
                        if (button == 0) {
                            this.Y_1740_V = 0;
                        } else if (button == 1) {
                            this.Y_1740_V = 1;
                        } else if (this.minecraft.P_4830_p.k_3961_g.n_1700_B(button)) {
                            this.Y_1740_V = 2;
                        }
                    }
                }
            }
        }
        this.G_564_y = slot;
        this.n_3318_d = i;
        this.d_2427_y = button;
        return true;
    }

    private void n_1700_B(int keyCode) {
        X_4340_E playerHSI = this.minecraft.Y_259_p;
        for (n_1700_B botHSI : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botHSI.P_1922_E.Q_2552_b) continue;
            playerHSI = botHSI.P_1922_E.Q_2552_b;
        }
        if (this.t_1786_h != null && playerHSI.l_1268_F.s_956_w().n_1700_B()) {
            if (this.minecraft.P_4830_p.j_276_v.n_1700_B(keyCode)) {
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, 40, a_408_T.R_4764_Y);
                return;
            }
            for (int i = 0; i < 9; ++i) {
                if (!this.minecraft.P_4830_p.RealmsServerPing[i].n_1700_B(keyCode)) continue;
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, i, a_408_T.R_4764_Y);
            }
        }
    }

    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        return mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        X_4340_E playerMD = this.minecraft.Y_259_p;
        for (n_1700_B botMD : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botMD.P_1922_E.Q_2552_b) continue;
            playerMD = botMD.P_1922_E.Q_2552_b;
        }
        Slot slot = this.R_4764_Y(mouseX, mouseY);
        Z_1993_T itemstack = playerMD.l_1268_F.s_956_w();
        if (this.n_1700_B != null && this.minecraft.P_4830_p.c_4037_x) {
            if (button == 0 || button == 1) {
                if (this.u_1723_Y.n_1700_B()) {
                    if (slot != this.n_1700_B && !this.n_1700_B.n_1700_B().n_1700_B()) {
                        this.u_1723_Y = this.n_1700_B.n_1700_B().t_148_a();
                    }
                } else if (this.u_1723_Y.t_4043_B() > 1 && slot != null && a_2900_S.n_1700_B(slot, this.u_1723_Y, false)) {
                    long i = j_3341_s.J_1907_R();
                    if (this.R_4764_Y == slot) {
                        if (i - this.q_2307_F > 500L) {
                            this.n_1700_B(this.n_1700_B, this.n_1700_B.G_564_y, 0, a_408_T.n_1700_B);
                            this.n_1700_B(slot, slot.G_564_y, 1, a_408_T.n_1700_B);
                            this.n_1700_B(this.n_1700_B, this.n_1700_B.G_564_y, 0, a_408_T.n_1700_B);
                            this.q_2307_F = i + 750L;
                            this.u_1723_Y.v_4262_N(1);
                        }
                    } else {
                        this.R_4764_Y = slot;
                        this.q_2307_F = i;
                    }
                }
            }
        } else if (this.Y_259_p && slot != null && !itemstack.n_1700_B() && (itemstack.t_4043_B() > this.Y_601_j.size() || this.Y_1740_V == 2) && a_2900_S.n_1700_B(slot, itemstack, true) && slot.n_1700_B(itemstack) && ((a_2900_S)this.Q_4569_t).n_1700_B(slot)) {
            this.Y_601_j.add(slot);
            this.P_1922_E();
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        X_4340_E playerMR = this.minecraft.Y_259_p;
        for (n_1700_B botMR : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botMR.P_1922_E.Q_2552_b) continue;
            playerMR = botMR.P_1922_E.Q_2552_b;
        }
        Slot slot = this.R_4764_Y(mouseX, mouseY);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        boolean flag = this.n_1700_B(mouseX, mouseY, i, j, button);
        int k = -1;
        if (slot != null) {
            k = slot.G_564_y;
        }
        if (flag) {
            k = -999;
        }
        if (this.z_1737_N && slot != null && button == 0 && ((a_2900_S)this.Q_4569_t).n_1700_B(Z_1993_T.J_1907_R, slot)) {
            if (z_3427_G.hasShiftDown()) {
                if (!this.v_4276_D.n_1700_B()) {
                    for (Slot slot2 : ((a_2900_S)this.Q_4569_t).P_1922_E) {
                        if (slot2 == null || !slot2.n_1700_B(playerMR) || !slot2.J_1907_R() || slot2.R_4764_Y != slot.R_4764_Y || !a_2900_S.n_1700_B(slot2, this.v_4276_D, true)) continue;
                        this.n_1700_B(slot2, slot2.G_564_y, button, a_408_T.J_1907_R);
                    }
                }
            } else {
                this.n_1700_B(slot, k, button, a_408_T.v_4262_N);
            }
            this.z_1737_N = false;
            this.n_3318_d = 0L;
        } else {
            if (this.Y_259_p && this.t_4043_B != button) {
                this.Y_259_p = false;
                this.Y_601_j.clear();
                this.x_607_J = true;
                return true;
            }
            if (this.x_607_J) {
                this.x_607_J = false;
                return true;
            }
            if (this.n_1700_B != null && this.minecraft.P_4830_p.c_4037_x) {
                if (button == 0 || button == 1) {
                    if (this.u_1723_Y.n_1700_B() && slot != this.n_1700_B) {
                        this.u_1723_Y = this.n_1700_B.n_1700_B();
                    }
                    boolean flag2 = a_2900_S.n_1700_B(slot, this.u_1723_Y, false);
                    if (k != -1 && !this.u_1723_Y.n_1700_B() && flag2) {
                        this.n_1700_B(this.n_1700_B, this.n_1700_B.G_564_y, button, a_408_T.n_1700_B);
                        this.n_1700_B(slot, k, 0, a_408_T.n_1700_B);
                        if (playerMR.l_1268_F.s_956_w().n_1700_B()) {
                            this.k_2293_S = Z_1993_T.J_1907_R;
                        } else {
                            this.n_1700_B(this.n_1700_B, this.n_1700_B.G_564_y, button, a_408_T.n_1700_B);
                            this.v_4262_N = u_530_F.R_4764_Y(mouseX - (double)i);
                            this.Q_2552_b = u_530_F.R_4764_Y(mouseY - (double)j);
                            this.J_1907_R = this.n_1700_B;
                            this.k_2293_S = this.u_1723_Y;
                            this.C_2741_M = j_3341_s.J_1907_R();
                        }
                    } else if (!this.u_1723_Y.n_1700_B()) {
                        this.v_4262_N = u_530_F.R_4764_Y(mouseX - (double)i);
                        this.Q_2552_b = u_530_F.R_4764_Y(mouseY - (double)j);
                        this.J_1907_R = this.n_1700_B;
                        this.k_2293_S = this.u_1723_Y;
                        this.C_2741_M = j_3341_s.J_1907_R();
                    }
                    this.u_1723_Y = Z_1993_T.J_1907_R;
                    this.n_1700_B = null;
                }
            } else if (this.Y_259_p && !this.Y_601_j.isEmpty()) {
                this.n_1700_B((Slot)null, -999, a_2900_S.R_4764_Y(0, this.Y_1740_V), a_408_T.u_1723_Y);
                for (Slot slot1 : this.Y_601_j) {
                    this.n_1700_B(slot1, slot1.G_564_y, a_2900_S.R_4764_Y(1, this.Y_1740_V), a_408_T.u_1723_Y);
                }
                this.n_1700_B((Slot)null, -999, a_2900_S.R_4764_Y(2, this.Y_1740_V), a_408_T.u_1723_Y);
            } else if (!playerMR.l_1268_F.s_956_w().n_1700_B()) {
                if (this.minecraft.P_4830_p.k_3961_g.n_1700_B(button)) {
                    this.n_1700_B(slot, k, button, a_408_T.G_564_y);
                } else {
                    boolean flag1;
                    boolean bl = flag1 = k != -999 && (Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 340) || Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 344));
                    if (flag1) {
                        this.v_4276_D = slot != null && slot.J_1907_R() ? slot.n_1700_B().t_148_a() : Z_1993_T.J_1907_R;
                    }
                    this.n_1700_B(slot, k, button, flag1 ? a_408_T.J_1907_R : a_408_T.n_1700_B);
                }
            }
        }
        if (playerMR.l_1268_F.s_956_w().n_1700_B()) {
            this.n_3318_d = 0L;
        }
        this.Y_259_p = false;
        return true;
    }

    private boolean n_1700_B(Slot slotIn, double mouseX, double mouseY) {
        return this.n_1700_B(slotIn.P_1922_E, slotIn.u_1723_Y, 16, 16, mouseX, mouseY);
    }

    protected boolean n_1700_B(int x, int y, int width, int height, double mouseX, double mouseY) {
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        return (mouseX -= (double)i) >= (double)(x - 1) && mouseX < (double)(x + width + 1) && (mouseY -= (double)j) >= (double)(y - 1) && mouseY < (double)(y + height + 1);
    }

    protected void n_1700_B(Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        M_766_z eventHandleMouseClick = new M_766_z(slotIn, slotId, mouseButton, type);
        lightning.product.A_4115_X.n_1700_B(eventHandleMouseClick);
        if (eventHandleMouseClick.n_1700_B()) {
            return;
        }
        if (slotIn != null) {
            slotId = slotIn.G_564_y;
        }
        n_1700_B controlledBot = null;
        Object targetContainer = this.Q_4569_t;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            controlledBot = bot;
            if (bot.P_1922_E.Q_2552_b.H_1873_g == null) break;
            targetContainer = bot.P_1922_E.Q_2552_b.H_1873_g;
            break;
        }
        int slotCount = ((a_2900_S)targetContainer).P_1922_E.size();
        if (slotId != -999 && (slotId < 0 || slotId >= slotCount)) {
            return;
        }
        if (controlledBot != null) {
            controlledBot.P_1922_E.C_2741_M.n_1700_B(((a_2900_S)targetContainer).u_1723_Y, slotId, mouseButton, type, controlledBot.P_1922_E.Q_2552_b);
        } else {
            this.minecraft.w_1457_N.windowClick(((a_2900_S)this.Q_4569_t).u_1723_Y, slotId, mouseButton, type, this.minecraft.Y_259_p);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (this.minecraft.P_4830_p.f_4016_n.n_1700_B(keyCode, scanCode)) {
            this.closeScreen();
            return true;
        }
        this.n_1700_B(keyCode, scanCode);
        if (keyCode == 81 && z_3427_G.hasControlDown() && z_3427_G.hasShiftDown()) {
            this.u_1723_Y();
            return true;
        }
        if (this.t_1786_h != null && this.t_1786_h.J_1907_R()) {
            if (this.minecraft.P_4830_p.k_3961_g.n_1700_B(keyCode, scanCode)) {
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, 0, a_408_T.G_564_y);
            } else if (this.minecraft.P_4830_p.UploadStatus.n_1700_B(keyCode, scanCode)) {
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, z_3427_G.hasControlDown() ? 1 : 0, a_408_T.P_1922_E);
            }
        }
        return true;
    }

    protected boolean n_1700_B(int keyCode, int scanCode) {
        X_4340_E playerISM = this.minecraft.Y_259_p;
        for (n_1700_B botISM : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != botISM.P_1922_E.Q_2552_b) continue;
            playerISM = botISM.P_1922_E.Q_2552_b;
        }
        if (playerISM.l_1268_F.s_956_w().n_1700_B() && this.t_1786_h != null) {
            if (this.minecraft.P_4830_p.j_276_v.n_1700_B(keyCode, scanCode)) {
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, 40, a_408_T.R_4764_Y);
                return true;
            }
            for (int i = 0; i < 9; ++i) {
                if (!this.minecraft.P_4830_p.RealmsServerPing[i].n_1700_B(keyCode, scanCode)) continue;
                this.n_1700_B(this.t_1786_h, this.t_1786_h.G_564_y, i, a_408_T.R_4764_Y);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClose() {
        if (this.minecraft.Y_259_p != null) {
            ((a_2900_S)this.Q_4569_t).J_1907_R(this.minecraft.Y_259_p);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.minecraft.Y_259_p.RealmsLongRunningMcoTaskScreen() || this.minecraft.Y_259_p.t_4219_U) {
            this.minecraft.Y_259_p.P_1922_E();
        }
    }

    @Override
    public T n_() {
        return this.Q_4569_t;
    }

    private void u_1723_Y() {
        if (this.t_1786_h == null || !this.t_1786_h.J_1907_R()) {
            return;
        }
        Z_1993_T targetItem = this.t_1786_h.n_1700_B().t_148_a();
        if (targetItem.n_1700_B()) {
            return;
        }
        for (Slot slot : ((a_2900_S)this.Q_4569_t).P_1922_E) {
            if (!slot.J_1907_R() || !this.n_1700_B(slot.n_1700_B(), targetItem)) continue;
            this.n_1700_B(slot, slot.G_564_y, 1, a_408_T.P_1922_E);
        }
    }

    private boolean n_1700_B(Z_1993_T stack1, Z_1993_T stack2) {
        if (stack1.n_1700_B() || stack2.n_1700_B()) {
            return false;
        }
        if (!stack1.J_1907_R().equals(stack2.J_1907_R())) {
            return false;
        }
        if (!stack1.h_1847_R() && !stack2.h_1847_R()) {
            return true;
        }
        if (stack1.h_1847_R() != stack2.h_1847_R()) {
            return true;
        }
        Z_1993_T copy1 = stack1.t_148_a();
        Z_1993_T copy2 = stack2.t_148_a();
        if (copy1.h_1847_R() && copy1.Q_4569_t().P_1922_E("Damage")) {
            copy1.Q_4569_t().multiplayerClientSuggestionProvider("Damage");
        }
        if (copy2.h_1847_R() && copy2.Q_4569_t().P_1922_E("Damage")) {
            copy2.Q_4569_t().multiplayerClientSuggestionProvider("Damage");
        }
        return Z_1993_T.n_1700_B(copy1, copy2);
    }

    private boolean v_4262_N() {
        return this.Q_4569_t instanceof ChestMenu || this.Q_4569_t instanceof x_353_w || this.Q_4569_t instanceof z_1477_l;
    }

    private boolean w_1484_f() {
        if (this.getTitle() == null) {
            return false;
        }
        String title = this.getTitle().getString();
        return title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") || title.contains("\u041f\u043e\u0438\u0441\u043a") || title.contains("\u041f:");
    }

    private void t_148_a() {
        AutoBuy autoBuy;
        int buttonWidth = 80;
        int buttonHeight = 20;
        int spacing = 2;
        int startX = this.multiplayerClientSuggestionProvider + this.t_148_a + 4;
        int startY = this.w_1457_N;
        boolean isAuction = this.w_1484_f();
        if (isAuction && (autoBuy = (AutoBuy)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoBuy.class)) != null && autoBuy.w_1484_f()) {
            this.A_4115_X = this.addButton(new Button(startX, startY, buttonWidth, buttonHeight, new U_2871_b(autoBuy.Q_4569_t() ? "AB: \u0412\u041a\u041b" : "AB: \u0412\u042b\u041a\u041b"), button -> {
                autoBuy.M_182_A();
                button.setMessage(new U_2871_b(autoBuy.Q_4569_t() ? "AB: \u0412\u041a\u041b" : "AB: \u0412\u042b\u041a\u041b"));
            }));
            this.addButton(new Button(startX, startY + buttonHeight + spacing, buttonWidth, buttonHeight, new U_2871_b(autoBuy.t_1786_h() ? "\u041f\u0430\u0440\u0441\u0435\u0440: \u0412\u041a\u041b" : "\u041f\u0430\u0440\u0441\u0435\u0440: \u0412\u042b\u041a\u041b"), button -> {
                autoBuy.multiplayerClientSuggestionProvider();
                button.setMessage(new U_2871_b(autoBuy.t_1786_h() ? "\u041f\u0430\u0440\u0441\u0435\u0440: \u0412\u041a\u041b" : "\u041f\u0430\u0440\u0441\u0435\u0440: \u0412\u042b\u041a\u041b"));
            }));
        }
        this.Z_875_P = this.addButton(new Button(startX, startY + (isAuction ? buttonHeight + spacing : 0), buttonWidth, buttonHeight, new U_2871_b("\u0412\u0437\u044f\u0442\u044c"), button -> this.P_4830_p()));
        this.c_3005_b = this.addButton(new Button(startX, startY + (buttonHeight + spacing) * (isAuction ? 2 : 1), buttonWidth, buttonHeight, new U_2871_b("\u0421\u043b\u043e\u0436\u0438\u0442\u044c"), button -> this.h_1847_R()));
        this.H_2857_Y = this.addButton(new Button(startX, startY + (buttonHeight + spacing) * (isAuction ? 3 : 2), buttonWidth, buttonHeight, new U_2871_b("\u0412\u044b\u043a\u0438\u043d\u0443\u0442\u044c"), button -> this.Q_4569_t()));
    }

    private void s_956_w() {
        AutoBuy autoBuy;
        if (!this.v_4262_N()) {
            return;
        }
        if (this.Z_875_P != null) {
            this.Z_875_P.active = this.u_2550_I();
        }
        if (this.c_3005_b != null) {
            this.c_3005_b.active = this.M_588_G();
        }
        if (this.H_2857_Y != null) {
            this.H_2857_Y.active = this.u_2550_I();
        }
        if (this.A_4115_X != null && (autoBuy = (AutoBuy)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoBuy.class)) != null) {
            this.A_4115_X.setMessage(new U_2871_b(autoBuy.Q_4569_t() ? "AB: \u0412\u041a\u041b" : "AB: \u0412\u042b\u041a\u041b"));
        }
    }

    private boolean u_2550_I() {
        int containerSlotCount = this.M_182_A();
        for (int i = 0; i < containerSlotCount; ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!slot.J_1907_R()) continue;
            return true;
        }
        return false;
    }

    private boolean M_588_G() {
        int containerSlotCount;
        for (int i = containerSlotCount = this.M_182_A(); i < ((a_2900_S)this.Q_4569_t).P_1922_E.size(); ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!slot.J_1907_R()) continue;
            return true;
        }
        return false;
    }

    private void P_4830_p() {
        if (!this.v_4262_N()) {
            return;
        }
        int containerSlotCount = this.M_182_A();
        for (int i = 0; i < containerSlotCount; ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!slot.J_1907_R()) continue;
            this.n_1700_B(slot, slot.G_564_y, 0, a_408_T.J_1907_R);
        }
    }

    private void h_1847_R() {
        int containerSlotCount;
        if (!this.v_4262_N()) {
            return;
        }
        for (int i = containerSlotCount = this.M_182_A(); i < ((a_2900_S)this.Q_4569_t).P_1922_E.size(); ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!slot.J_1907_R()) continue;
            this.n_1700_B(slot, slot.G_564_y, 0, a_408_T.J_1907_R);
        }
    }

    private void Q_4569_t() {
        if (!this.v_4262_N()) {
            return;
        }
        int containerSlotCount = this.M_182_A();
        for (int i = 0; i < containerSlotCount; ++i) {
            Slot slot = ((a_2900_S)this.Q_4569_t).P_1922_E.get(i);
            if (!slot.J_1907_R()) continue;
            this.n_1700_B(slot, slot.G_564_y, 1, a_408_T.P_1922_E);
        }
    }

    private int M_182_A() {
        if (this.Q_4569_t instanceof ChestMenu) {
            ChestMenu chest = (ChestMenu)this.Q_4569_t;
            return chest.J_1907_R() * 9;
        }
        if (this.Q_4569_t instanceof x_353_w) {
            return 27;
        }
        if (this.Q_4569_t instanceof z_1477_l) {
            return 5;
        }
        return 0;
    }

    @Override
    public void closeScreen() {
        this.minecraft.Y_259_p.P_1922_E();
        super.closeScreen();
    }
}



