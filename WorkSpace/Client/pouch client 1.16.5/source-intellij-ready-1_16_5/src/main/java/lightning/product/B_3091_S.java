/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.Hotbar;
import lightning.product.D_4024_W;
import lightning.product.E_2561_m;
import lightning.product.CreativeInventoryListener;
import lightning.product.F_2904_S;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.ItemTags;
import lightning.product.O_694_j;
import lightning.product.SearchRegistry;
import lightning.product.Q_1939_l;
import lightning.product.NonNullList;
import lightning.product.Q_4113_P;
import lightning.product.S_1134_u;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.W_3491_f;
import lightning.product.X_4340_E;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_3316_o;
import lightning.product.MutableSearchTree;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.t_3127_w;
import lightning.product.HotbarManager;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_6_Q;

public class B_3091_S
extends t_3127_w<n_1700_B> {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/container/creative_inventory/tabs.png");
    private static final N_1216_z R_4764_Y = new N_1216_z(45);
    private static final x_282_a G_564_y = new F_2904_S("inventory.binSlot");
    private static int P_1922_E = S_1134_u.J_1907_R.n_1700_B();
    private float u_1723_Y;
    private boolean v_4262_N;
    private O_694_j Q_2552_b;
    @Nullable
    private List<Slot> C_2741_M;
    @Nullable
    private Slot k_2293_S;
    private CreativeInventoryListener q_2307_F;
    private boolean Z_875_P;
    private boolean c_3005_b;
    private final Map<g_2336_b, r_109_r<q_1613_l>> H_2857_Y = Maps.newTreeMap();

    public B_3091_S(a_3913_L player) {
        super(new n_1700_B(player), player.l_1268_F, U_2871_b.R_4764_Y);
        player.H_1873_g = this.Q_4569_t;
        this.passEvents = true;
        this.s_956_w = 136;
        this.t_148_a = 195;
    }

    @Override
    public void tick() {
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        boolean isCreativeMode = bot1 != null ? bot1.P_1922_E.C_2741_M.w_1484_f() : this.minecraft.w_1457_N.isInCreativeMode();
        if (!isCreativeMode) {
            if (bot1 == null) {
                this.minecraft.n_1700_B(new Q_1939_l(this.minecraft.Y_259_p));
            } else {
                this.minecraft.n_1700_B(new Q_1939_l(bot1.P_1922_E.Q_2552_b));
            }
            return;
        }
        if (this.Q_2552_b != null) {
            this.Q_2552_b.tick();
        }
    }

    @Override
    protected void n_1700_B(@Nullable Slot slotIn, int slotId, int mouseButton, a_408_T type) {
        if (this.n_1700_B(slotIn)) {
            this.Q_2552_b.setCursorPositionEnd();
            this.Q_2552_b.setSelectionPos(0);
        }
        boolean flag = type == a_408_T.J_1907_R;
        type = slotId == -999 && type == a_408_T.n_1700_B ? a_408_T.P_1922_E : type;
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (slotIn == null && P_1922_E != S_1134_u.h_1847_R.n_1700_B() && type != a_408_T.u_1723_Y) {
            W_3491_f w_3491_f = playerinventory = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F : this.minecraft.Y_259_p.l_1268_F;
            if (!playerinventory.s_956_w().n_1700_B() && this.c_3005_b) {
                if (mouseButton == 0) {
                    if (bot1 == null) {
                        this.minecraft.Y_259_p.n_1700_B(playerinventory.s_956_w(), true);
                        this.minecraft.w_1457_N.sendPacketDropItem(playerinventory.s_956_w());
                    } else {
                        bot1.P_1922_E.Q_2552_b.n_1700_B(playerinventory.s_956_w(), true);
                        bot1.P_1922_E.C_2741_M.n_1700_B(playerinventory.s_956_w());
                    }
                    playerinventory.v_4262_N(Z_1993_T.J_1907_R);
                }
                if (mouseButton == 1) {
                    Z_1993_T itemstack6 = playerinventory.s_956_w().n_1700_B(1);
                    if (bot1 == null) {
                        this.minecraft.Y_259_p.n_1700_B(itemstack6, true);
                        this.minecraft.w_1457_N.sendPacketDropItem(itemstack6);
                    } else {
                        bot1.P_1922_E.Q_2552_b.n_1700_B(itemstack6, true);
                        bot1.P_1922_E.C_2741_M.n_1700_B(itemstack6);
                    }
                }
            }
        } else {
            X_4340_E playerEntity;
            X_4340_E x_4340_E = playerEntity = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
            if (slotIn != null && !slotIn.n_1700_B(playerEntity)) {
                return;
            }
            if (slotIn == this.k_2293_S && flag) {
                for (int j = 0; j < playerEntity.o_1800_r.u_2550_I().size(); ++j) {
                    if (bot1 == null) {
                        this.minecraft.w_1457_N.sendSlotPacket(Z_1993_T.J_1907_R, j);
                        continue;
                    }
                    bot1.P_1922_E.C_2741_M.n_1700_B(Z_1993_T.J_1907_R, j);
                }
            } else if (P_1922_E == S_1134_u.h_1847_R.n_1700_B()) {
                if (slotIn == this.k_2293_S) {
                    playerEntity.l_1268_F.v_4262_N(Z_1993_T.J_1907_R);
                } else if (type == a_408_T.P_1922_E && slotIn != null && slotIn.J_1907_R()) {
                    Z_1993_T itemstack = slotIn.n_1700_B(mouseButton == 0 ? 1 : slotIn.n_1700_B().R_4764_Y());
                    Z_1993_T itemstack1 = slotIn.n_1700_B();
                    if (bot1 == null) {
                        this.minecraft.Y_259_p.n_1700_B(itemstack, true);
                        this.minecraft.w_1457_N.sendPacketDropItem(itemstack);
                        this.minecraft.w_1457_N.sendSlotPacket(itemstack1, ((J_1907_R)slotIn).n_1700_B.G_564_y);
                    } else {
                        bot1.P_1922_E.Q_2552_b.n_1700_B(itemstack, true);
                        bot1.P_1922_E.C_2741_M.n_1700_B(itemstack);
                        bot1.P_1922_E.C_2741_M.n_1700_B(itemstack1, ((J_1907_R)slotIn).n_1700_B.G_564_y);
                    }
                } else if (type == a_408_T.P_1922_E && !playerEntity.l_1268_F.s_956_w().n_1700_B()) {
                    if (bot1 == null) {
                        this.minecraft.Y_259_p.n_1700_B(this.minecraft.Y_259_p.l_1268_F.s_956_w(), true);
                        this.minecraft.w_1457_N.sendPacketDropItem(this.minecraft.Y_259_p.l_1268_F.s_956_w());
                    } else {
                        bot1.P_1922_E.Q_2552_b.n_1700_B(bot1.P_1922_E.Q_2552_b.l_1268_F.s_956_w(), true);
                        bot1.P_1922_E.C_2741_M.n_1700_B(bot1.P_1922_E.Q_2552_b.l_1268_F.s_956_w());
                    }
                    playerEntity.l_1268_F.v_4262_N(Z_1993_T.J_1907_R);
                } else {
                    playerEntity.o_1800_r.n_1700_B(slotIn == null ? slotId : ((J_1907_R)slotIn).n_1700_B.G_564_y, mouseButton, type, playerEntity);
                    playerEntity.o_1800_r.M_588_G();
                }
            } else if (type != a_408_T.u_1723_Y && slotIn.R_4764_Y == R_4764_Y) {
                playerinventory = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F : this.minecraft.Y_259_p.l_1268_F;
                Z_1993_T itemstack5 = playerinventory.s_956_w();
                Z_1993_T itemstack7 = slotIn.n_1700_B();
                if (type == a_408_T.R_4764_Y) {
                    if (!itemstack7.n_1700_B()) {
                        Z_1993_T itemstack10 = itemstack7.t_148_a();
                        itemstack10.P_1922_E(itemstack10.R_4764_Y());
                        playerinventory.J_1907_R(mouseButton, itemstack10);
                        playerEntity.o_1800_r.M_588_G();
                    }
                    return;
                }
                if (type == a_408_T.G_564_y) {
                    if (playerinventory.s_956_w().n_1700_B() && slotIn.J_1907_R()) {
                        Z_1993_T itemstack9 = slotIn.n_1700_B().t_148_a();
                        itemstack9.P_1922_E(itemstack9.R_4764_Y());
                        playerinventory.v_4262_N(itemstack9);
                    }
                    return;
                }
                if (type == a_408_T.P_1922_E) {
                    if (!itemstack7.n_1700_B()) {
                        Z_1993_T itemstack8 = itemstack7.t_148_a();
                        itemstack8.P_1922_E(mouseButton == 0 ? 1 : itemstack8.R_4764_Y());
                        if (bot1 == null) {
                            this.minecraft.Y_259_p.n_1700_B(itemstack8, true);
                            this.minecraft.w_1457_N.sendPacketDropItem(itemstack8);
                        } else {
                            bot1.P_1922_E.Q_2552_b.n_1700_B(itemstack8, true);
                            bot1.P_1922_E.C_2741_M.n_1700_B(itemstack8);
                        }
                    }
                    return;
                }
                if (!itemstack5.n_1700_B() && !itemstack7.n_1700_B() && itemstack5.n_1700_B(itemstack7) && Z_1993_T.n_1700_B(itemstack5, itemstack7)) {
                    if (mouseButton == 0) {
                        if (flag) {
                            itemstack5.P_1922_E(itemstack5.R_4764_Y());
                        } else if (itemstack5.t_4043_B() < itemstack5.R_4764_Y()) {
                            itemstack5.u_1723_Y(1);
                        }
                    } else {
                        itemstack5.v_4262_N(1);
                    }
                } else if (!itemstack7.n_1700_B() && itemstack5.n_1700_B()) {
                    playerinventory.v_4262_N(itemstack7.t_148_a());
                    itemstack5 = playerinventory.s_956_w();
                    if (flag) {
                        itemstack5.P_1922_E(itemstack5.R_4764_Y());
                    }
                } else if (mouseButton == 0) {
                    playerinventory.v_4262_N(Z_1993_T.J_1907_R);
                } else {
                    playerinventory.s_956_w().v_4262_N(1);
                }
            } else if (this.Q_4569_t != null) {
                Z_1993_T itemstack3 = slotIn == null ? Z_1993_T.J_1907_R : ((n_1700_B)this.Q_4569_t).n_1700_B(slotIn.G_564_y).n_1700_B();
                ((n_1700_B)this.Q_4569_t).n_1700_B(slotIn == null ? slotId : slotIn.G_564_y, mouseButton, type, bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p);
                if (a_2900_S.R_4764_Y(mouseButton) == 2) {
                    for (int k = 0; k < 9; ++k) {
                        if (bot1 == null) {
                            this.minecraft.w_1457_N.sendSlotPacket(((n_1700_B)this.Q_4569_t).n_1700_B(45 + k).n_1700_B(), 36 + k);
                            continue;
                        }
                        bot1.P_1922_E.C_2741_M.n_1700_B(((n_1700_B)this.Q_4569_t).n_1700_B(45 + k).n_1700_B(), 36 + k);
                    }
                } else if (slotIn != null) {
                    Z_1993_T itemstack4 = ((n_1700_B)this.Q_4569_t).n_1700_B(slotIn.G_564_y).n_1700_B();
                    if (bot1 == null) {
                        this.minecraft.w_1457_N.sendSlotPacket(itemstack4, slotIn.G_564_y - ((n_1700_B)this.Q_4569_t).P_1922_E.size() + 9 + 36);
                    } else {
                        bot1.P_1922_E.C_2741_M.n_1700_B(itemstack4, slotIn.G_564_y - ((n_1700_B)this.Q_4569_t).P_1922_E.size() + 9 + 36);
                    }
                    int i = 45 + mouseButton;
                    if (type == a_408_T.R_4764_Y) {
                        if (bot1 == null) {
                            this.minecraft.w_1457_N.sendSlotPacket(itemstack3, i - ((n_1700_B)this.Q_4569_t).P_1922_E.size() + 9 + 36);
                        } else {
                            bot1.P_1922_E.C_2741_M.n_1700_B(itemstack3, i - ((n_1700_B)this.Q_4569_t).P_1922_E.size() + 9 + 36);
                        }
                    } else if (type == a_408_T.P_1922_E && !itemstack3.n_1700_B()) {
                        Z_1993_T itemstack2 = itemstack3.t_148_a();
                        itemstack2.P_1922_E(mouseButton == 0 ? 1 : itemstack2.R_4764_Y());
                        if (bot1 == null) {
                            this.minecraft.Y_259_p.n_1700_B(itemstack2, true);
                            this.minecraft.w_1457_N.sendPacketDropItem(itemstack2);
                        } else {
                            bot1.P_1922_E.Q_2552_b.n_1700_B(itemstack2, true);
                            bot1.P_1922_E.C_2741_M.n_1700_B(itemstack2);
                        }
                    }
                    if (bot1 == null) {
                        this.minecraft.Y_259_p.o_1800_r.M_588_G();
                    } else {
                        bot1.P_1922_E.Q_2552_b.o_1800_r.M_588_G();
                    }
                }
            }
        }
    }

    private boolean n_1700_B(@Nullable Slot slotIn) {
        return slotIn != null && slotIn.R_4764_Y == R_4764_Y;
    }

    @Override
    protected void o_() {
        int i = this.multiplayerClientSuggestionProvider;
        super.o_();
        if (this.Q_2552_b != null && this.multiplayerClientSuggestionProvider != i) {
            this.Q_2552_b.setX(this.multiplayerClientSuggestionProvider + 82);
        }
    }

    @Override
    protected void init() {
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        boolean isCreativeMode = bot1 != null ? bot1.P_1922_E.C_2741_M.w_1484_f() : this.minecraft.w_1457_N.isInCreativeMode();
        if (isCreativeMode) {
            super.init();
            this.minecraft.Q_4569_t.n_1700_B(true);
            this.Q_2552_b = new O_694_j(this.font, this.multiplayerClientSuggestionProvider + 82, this.w_1457_N + 6, 80, 9, new F_2904_S("itemGroup.search"));
            this.Q_2552_b.setMaxStringLength(50);
            this.Q_2552_b.setEnableBackgroundDrawing(false);
            this.Q_2552_b.setVisible(false);
            this.Q_2552_b.setTextColor(0xFFFFFF);
            this.children.add(this.Q_2552_b);
            int i = P_1922_E;
            P_1922_E = -1;
            this.n_1700_B(S_1134_u.n_1700_B[i]);
            if (bot1 == null) {
                this.minecraft.Y_259_p.o_1800_r.J_1907_R(this.q_2307_F);
            } else {
                bot1.P_1922_E.Q_2552_b.o_1800_r.J_1907_R(this.q_2307_F);
            }
            this.q_2307_F = new CreativeInventoryListener(this.minecraft);
            if (bot1 == null) {
                this.minecraft.Y_259_p.o_1800_r.n_1700_B(this.q_2307_F);
            } else {
                bot1.P_1922_E.Q_2552_b.o_1800_r.n_1700_B(this.q_2307_F);
            }
        } else if (bot1 == null) {
            this.minecraft.n_1700_B(new Q_1939_l(this.minecraft.Y_259_p));
        } else {
            this.minecraft.n_1700_B(new Q_1939_l(bot1.P_1922_E.Q_2552_b));
        }
    }

    @Override
    public void resize(MinecraftClient minecraft, int width, int height) {
        String s = this.Q_2552_b.getText();
        this.init(minecraft, width, height);
        this.Q_2552_b.setText(s);
        if (!this.Q_2552_b.getText().isEmpty()) {
            this.u_1723_Y();
        }
    }

    @Override
    public void onClose() {
        X_4340_E player;
        super.onClose();
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E x_4340_E = player = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
        if (player != null && player.l_1268_F != null) {
            player.o_1800_r.J_1907_R(this.q_2307_F);
        }
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.Z_875_P) {
            return false;
        }
        if (P_1922_E != S_1134_u.v_4262_N.n_1700_B()) {
            return false;
        }
        String s = this.Q_2552_b.getText();
        if (this.Q_2552_b.charTyped(codePoint, modifiers)) {
            if (!Objects.equals(s, this.Q_2552_b.getText())) {
                this.u_1723_Y();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.Z_875_P = false;
        if (P_1922_E != S_1134_u.v_4262_N.n_1700_B()) {
            if (this.minecraft.P_4830_p.Ops.n_1700_B(keyCode, scanCode)) {
                this.Z_875_P = true;
                this.n_1700_B(S_1134_u.v_4262_N);
                return true;
            }
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
        boolean flag = !this.n_1700_B(this.t_1786_h) || this.t_1786_h.J_1907_R();
        boolean flag1 = Q_4113_P.n_1700_B(keyCode, scanCode).P_1922_E().isPresent();
        if (flag && flag1 && this.n_1700_B(keyCode, scanCode)) {
            this.Z_875_P = true;
            return true;
        }
        String s = this.Q_2552_b.getText();
        if (this.Q_2552_b.keyPressed(keyCode, scanCode, modifiers)) {
            if (!Objects.equals(s, this.Q_2552_b.getText())) {
                this.u_1723_Y();
            }
            return true;
        }
        return this.Q_2552_b.isFocused() && this.Q_2552_b.getVisible() && keyCode != 256 ? true : super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        this.Z_875_P = false;
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    private void u_1723_Y() {
        ((n_1700_B)this.Q_4569_t).n_1700_B.clear();
        this.H_2857_Y.clear();
        String s = this.Q_2552_b.getText();
        if (s.isEmpty()) {
            for (q_1613_l item : V_3137_a.e_2887_G) {
                item.n_1700_B(S_1134_u.v_4262_N, ((n_1700_B)this.Q_4569_t).n_1700_B);
            }
        } else {
            MutableSearchTree<Z_1993_T> isearchtree;
            if (s.startsWith("#")) {
                s = s.substring(1);
                isearchtree = this.minecraft.n_1700_B(SearchRegistry.J_1907_R);
                this.n_1700_B(s);
            } else {
                isearchtree = this.minecraft.n_1700_B(SearchRegistry.n_1700_B);
            }
            ((n_1700_B)this.Q_4569_t).n_1700_B.addAll(isearchtree.n_1700_B(s.toLowerCase(Locale.ROOT)));
        }
        this.u_1723_Y = 0.0f;
        ((n_1700_B)this.Q_4569_t).n_1700_B(0.0f);
    }

    private void n_1700_B(String search) {
        Predicate<g_2336_b> predicate;
        int i = search.indexOf(58);
        if (i == -1) {
            predicate = p_214084_1_ -> p_214084_1_.J_1907_R().contains(search);
        } else {
            String s = search.substring(0, i).trim();
            String s1 = search.substring(i + 1).trim();
            predicate = p_214081_2_ -> p_214081_2_.R_4764_Y().contains(s) && p_214081_2_.J_1907_R().contains(s1);
        }
        E_2561_m<q_1613_l> itagcollection = ItemTags.n_1700_B();
        itagcollection.J_1907_R().stream().filter(predicate).forEach(p_214082_2_ -> {
            r_109_r itag = this.H_2857_Y.put((g_2336_b)p_214082_2_, itagcollection.n_1700_B((g_2336_b)p_214082_2_));
        });
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        S_1134_u itemgroup = S_1134_u.n_1700_B[P_1922_E];
        if (itemgroup.v_4262_N()) {
            c_4037_x.Y_259_p();
            this.font.J_1907_R(matrixStack, itemgroup.R_4764_Y(), 8.0f, 6.0f, 0x404040);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            double d0 = mouseX - (double)this.multiplayerClientSuggestionProvider;
            double d1 = mouseY - (double)this.w_1457_N;
            for (S_1134_u itemgroup : S_1134_u.n_1700_B) {
                if (!this.n_1700_B(itemgroup, d0, d1)) continue;
                return true;
            }
            if (P_1922_E != S_1134_u.h_1847_R.n_1700_B() && this.R_4764_Y(mouseX, mouseY)) {
                this.v_4262_N = this.v_4262_N();
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            double d0 = mouseX - (double)this.multiplayerClientSuggestionProvider;
            double d1 = mouseY - (double)this.w_1457_N;
            this.v_4262_N = false;
            for (S_1134_u itemgroup : S_1134_u.n_1700_B) {
                if (!this.n_1700_B(itemgroup, d0, d1)) continue;
                this.n_1700_B(itemgroup);
                return true;
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean v_4262_N() {
        return P_1922_E != S_1134_u.h_1847_R.n_1700_B() && S_1134_u.n_1700_B[P_1922_E].t_148_a() && ((n_1700_B)this.Q_4569_t).n_1700_B();
    }

    private void n_1700_B(S_1134_u tab) {
        int i = P_1922_E;
        P_1922_E = tab.n_1700_B();
        this.Y_601_j.clear();
        ((n_1700_B)this.Q_4569_t).n_1700_B.clear();
        if (tab == S_1134_u.P_4830_p) {
            HotbarManager creativesettings = this.minecraft.Ops();
            for (int j = 0; j < 9; ++j) {
                Hotbar hotbarsnapshot = creativesettings.n_1700_B(j);
                if (hotbarsnapshot.isEmpty()) {
                    for (int k = 0; k < 9; ++k) {
                        if (k == j) {
                            Z_1993_T itemstack = new Z_1993_T(Items.l_3370_o);
                            itemstack.n_1700_B("CustomCreativeLock");
                            x_282_a itextcomponent = this.minecraft.P_4830_p.RealmsServerPing[j].u_2550_I();
                            x_282_a itextcomponent1 = this.minecraft.P_4830_p.j_1564_a.u_2550_I();
                            itemstack.n_1700_B(new F_2904_S("inventory.hotbarInfo", itextcomponent1, itextcomponent));
                            ((n_1700_B)this.Q_4569_t).n_1700_B.add(itemstack);
                            continue;
                        }
                        ((n_1700_B)this.Q_4569_t).n_1700_B.add(Z_1993_T.J_1907_R);
                    }
                    continue;
                }
                ((n_1700_B)this.Q_4569_t).n_1700_B.addAll((Collection<Z_1993_T>)((Object)hotbarsnapshot));
            }
        } else if (tab != S_1134_u.v_4262_N) {
            tab.n_1700_B(((n_1700_B)this.Q_4569_t).n_1700_B);
        }
        if (tab == S_1134_u.h_1847_R) {
            y_6_Q container;
            lightning.product.n_1700_B bot1 = null;
            for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
            }
            y_6_Q y_6_Q2 = container = bot1 != null ? bot1.P_1922_E.Q_2552_b.o_1800_r : this.minecraft.Y_259_p.o_1800_r;
            if (this.C_2741_M == null) {
                this.C_2741_M = ImmutableList.copyOf((Collection)((n_1700_B)this.Q_4569_t).P_1922_E);
            }
            ((n_1700_B)this.Q_4569_t).P_1922_E.clear();
            for (int l = 0; l < container.P_1922_E.size(); ++l) {
                int j1;
                int i1;
                if (l >= 5 && l < 9) {
                    int l1 = l - 5;
                    int j2 = l1 / 2;
                    int l2 = l1 % 2;
                    i1 = 54 + j2 * 54;
                    j1 = 6 + l2 * 27;
                } else if (l >= 0 && l < 5) {
                    i1 = -2000;
                    j1 = -2000;
                } else if (l == 45) {
                    i1 = 35;
                    j1 = 20;
                } else {
                    int k1 = l - 9;
                    int i2 = k1 % 9;
                    int k2 = k1 / 9;
                    i1 = 9 + i2 * 18;
                    j1 = l >= 36 ? 112 : 54 + k2 * 18;
                }
                J_1907_R slot = new J_1907_R(container.P_1922_E.get(l), l, i1, j1);
                ((n_1700_B)this.Q_4569_t).P_1922_E.add(slot);
            }
            this.k_2293_S = new Slot(R_4764_Y, 0, 173, 112);
            ((n_1700_B)this.Q_4569_t).P_1922_E.add(this.k_2293_S);
        } else if (i == S_1134_u.h_1847_R.n_1700_B()) {
            ((n_1700_B)this.Q_4569_t).P_1922_E.clear();
            ((n_1700_B)this.Q_4569_t).P_1922_E.addAll(this.C_2741_M);
            this.C_2741_M = null;
        }
        if (this.Q_2552_b != null) {
            if (tab == S_1134_u.v_4262_N) {
                this.Q_2552_b.setVisible(true);
                this.Q_2552_b.setCanLoseFocus(false);
                this.Q_2552_b.setFocused2(true);
                if (i != tab.n_1700_B()) {
                    this.Q_2552_b.setText("");
                }
                this.u_1723_Y();
            } else {
                this.Q_2552_b.setVisible(false);
                this.Q_2552_b.setCanLoseFocus(true);
                this.Q_2552_b.setFocused2(false);
                this.Q_2552_b.setText("");
            }
        }
        this.u_1723_Y = 0.0f;
        ((n_1700_B)this.Q_4569_t).n_1700_B(0.0f);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (!this.v_4262_N()) {
            return false;
        }
        int i = (((n_1700_B)this.Q_4569_t).n_1700_B.size() + 9 - 1) / 9 - 5;
        this.u_1723_Y = (float)((double)this.u_1723_Y - delta / (double)i);
        this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y, 0.0f, 1.0f);
        ((n_1700_B)this.Q_4569_t).n_1700_B(this.u_1723_Y);
        return true;
    }

    @Override
    protected boolean n_1700_B(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
        boolean flag = mouseX < (double)guiLeftIn || mouseY < (double)guiTopIn || mouseX >= (double)(guiLeftIn + this.t_148_a) || mouseY >= (double)(guiTopIn + this.s_956_w);
        this.c_3005_b = flag && !this.n_1700_B(S_1134_u.n_1700_B[P_1922_E], mouseX, mouseY);
        return this.c_3005_b;
    }

    protected boolean R_4764_Y(double p_195376_1_, double p_195376_3_) {
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        int k = i + 175;
        int l = j + 18;
        int i1 = k + 14;
        int j1 = l + 112;
        return p_195376_1_ >= (double)k && p_195376_3_ >= (double)l && p_195376_1_ < (double)i1 && p_195376_3_ < (double)j1;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.v_4262_N) {
            int i = this.w_1457_N + 18;
            int j = i + 112;
            this.u_1723_Y = ((float)mouseY - (float)i - 7.5f) / ((float)(j - i) - 15.0f);
            this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y, 0.0f, 1.0f);
            ((n_1700_B)this.Q_4569_t).n_1700_B(this.u_1723_Y);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        for (S_1134_u itemgroup : S_1134_u.n_1700_B) {
            if (this.n_1700_B(matrixStack, itemgroup, mouseX, mouseY)) break;
        }
        if (this.k_2293_S != null && P_1922_E == S_1134_u.h_1847_R.n_1700_B() && this.n_1700_B(this.k_2293_S.P_1922_E, this.k_2293_S.u_1723_Y, 16, 16, mouseX, mouseY)) {
            this.renderTooltip(matrixStack, G_564_y, mouseX, mouseY);
        }
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(g_221_o matrixStack, Z_1993_T itemStack, int mouseX, int mouseY) {
        if (P_1922_E == S_1134_u.v_4262_N.n_1700_B()) {
            Map<K_1310_v, Integer> map;
            lightning.product.n_1700_B bot1 = null;
            for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.minecraft.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
            }
            X_4340_E player = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.minecraft.Y_259_p;
            List<x_282_a> list = itemStack.n_1700_B(player, this.minecraft.P_4830_p.M_182_A ? g_3316_o.n_1700_B.J_1907_R : g_3316_o.n_1700_B.n_1700_B);
            ArrayList list1 = Lists.newArrayList(list);
            q_1613_l item = itemStack.J_1907_R();
            S_1134_u itemgroup = item.w_1457_N();
            if (itemgroup == null && item == Items.M_4472_P && (map = K_4096_w.n_1700_B(itemStack)).size() == 1) {
                K_1310_v enchantment = map.keySet().iterator().next();
                for (S_1134_u itemgroup1 : S_1134_u.n_1700_B) {
                    if (!itemgroup1.n_1700_B(enchantment.J_1907_R)) continue;
                    itemgroup = itemgroup1;
                    break;
                }
            }
            this.H_2857_Y.forEach((p_214083_2_, p_214083_3_) -> {
                if (p_214083_3_.n_1700_B(item)) {
                    list1.add(1, new U_2871_b("#" + String.valueOf(p_214083_2_)).n_1700_B(D_4024_W.u_1723_Y));
                }
            });
            if (itemgroup != null) {
                list1.add(1, itemgroup.R_4764_Y().P_1922_E().n_1700_B(D_4024_W.s_956_w));
            }
            this.func_243308_b(matrixStack, list1, mouseX, mouseY);
        } else {
            super.renderTooltip(matrixStack, itemStack, mouseX, mouseY);
        }
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        S_1134_u itemgroup = S_1134_u.n_1700_B[P_1922_E];
        for (S_1134_u itemgroup1 : S_1134_u.n_1700_B) {
            this.minecraft.G_624_v().n_1700_B(J_1907_R);
            if (itemgroup1.n_1700_B() == P_1922_E) continue;
            this.n_1700_B(matrixStack, itemgroup1);
        }
        this.minecraft.G_624_v().n_1700_B(new g_2336_b("textures/gui/container/creative_inventory/tab_" + itemgroup.u_1723_Y()));
        this.blit(matrixStack, this.multiplayerClientSuggestionProvider, this.w_1457_N, 0, 0, this.t_148_a, this.s_956_w);
        this.Q_2552_b.render(matrixStack, x, y, partialTicks);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        int i = this.multiplayerClientSuggestionProvider + 175;
        int j = this.w_1457_N + 18;
        int k = j + 112;
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        if (itemgroup.t_148_a()) {
            this.blit(matrixStack, i, j + (int)((float)(k - j - 17) * this.u_1723_Y), 232 + (this.v_4262_N() ? 0 : 12), 0, 12, 15);
        }
        this.n_1700_B(matrixStack, itemgroup);
        if (itemgroup == S_1134_u.h_1847_R) {
            Q_1939_l.n_1700_B(this.multiplayerClientSuggestionProvider + 88, this.w_1457_N + 45, 20, this.multiplayerClientSuggestionProvider + 88 - x, this.w_1457_N + 45 - 30 - y, this.minecraft.Y_259_p);
        }
    }

    protected boolean n_1700_B(S_1134_u p_195375_1_, double p_195375_2_, double p_195375_4_) {
        int i = p_195375_1_.u_2550_I();
        int j = 28 * i;
        int k = 0;
        if (p_195375_1_.P_4830_p()) {
            j = this.t_148_a - 28 * (6 - i) + 2;
        } else if (i > 0) {
            j += i;
        }
        k = p_195375_1_.M_588_G() ? (k -= 32) : (k += this.s_956_w);
        return p_195375_2_ >= (double)j && p_195375_2_ <= (double)(j + 28) && p_195375_4_ >= (double)k && p_195375_4_ <= (double)(k + 32);
    }

    protected boolean n_1700_B(g_221_o p_238809_1_, S_1134_u p_238809_2_, int p_238809_3_, int p_238809_4_) {
        int i = p_238809_2_.u_2550_I();
        int j = 28 * i;
        int k = 0;
        if (p_238809_2_.P_4830_p()) {
            j = this.t_148_a - 28 * (6 - i) + 2;
        } else if (i > 0) {
            j += i;
        }
        k = p_238809_2_.M_588_G() ? (k -= 32) : (k += this.s_956_w);
        if (this.n_1700_B(j + 3, k + 3, 23, 27, p_238809_3_, p_238809_4_)) {
            this.renderTooltip(p_238809_1_, p_238809_2_.R_4764_Y(), p_238809_3_, p_238809_4_);
            return true;
        }
        return false;
    }

    protected void n_1700_B(g_221_o p_238808_1_, S_1134_u p_238808_2_) {
        boolean flag = p_238808_2_.n_1700_B() == P_1922_E;
        boolean flag1 = p_238808_2_.M_588_G();
        int i = p_238808_2_.u_2550_I();
        int j = i * 28;
        int k = 0;
        int l = this.multiplayerClientSuggestionProvider + 28 * i;
        int i1 = this.w_1457_N;
        int j1 = 32;
        if (flag) {
            k += 32;
        }
        if (p_238808_2_.P_4830_p()) {
            l = this.multiplayerClientSuggestionProvider + this.t_148_a - 28 * (6 - i);
        } else if (i > 0) {
            l += i;
        }
        if (flag1) {
            i1 -= 28;
        } else {
            k += 64;
            i1 += this.s_956_w - 4;
        }
        this.blit(p_238808_1_, l, i1, j, k, 28, 32);
        this.itemRenderer.J_1907_R = 100.0f;
        i1 = i1 + 8 + (flag1 ? 1 : -1);
        c_4037_x.n_3318_d();
        Z_1993_T itemstack = p_238808_2_.G_564_y();
        this.itemRenderer.J_1907_R(itemstack, l += 6, i1);
        this.itemRenderer.n_1700_B(this.font, itemstack, l, i1);
        this.itemRenderer.J_1907_R = 0.0f;
    }

    public int P_1922_E() {
        return P_1922_E;
    }

    public static void n_1700_B(MinecraftClient client, int index, boolean load, boolean save) {
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (client.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        X_4340_E clientplayerentity = bot1 != null ? bot1.P_1922_E.Q_2552_b : client.Y_259_p;
        HotbarManager creativesettings = client.Ops();
        Hotbar hotbarsnapshot = creativesettings.n_1700_B(index);
        if (load) {
            for (int i = 0; i < W_3491_f.G_564_y(); ++i) {
                Z_1993_T itemstack = ((Z_1993_T)hotbarsnapshot.get(i)).t_148_a();
                clientplayerentity.l_1268_F.J_1907_R(i, itemstack);
                if (bot1 == null) {
                    client.w_1457_N.sendSlotPacket(itemstack, 36 + i);
                    continue;
                }
                bot1.P_1922_E.C_2741_M.n_1700_B(itemstack, 36 + i);
            }
            clientplayerentity.o_1800_r.M_588_G();
        } else if (save) {
            for (int j = 0; j < W_3491_f.G_564_y(); ++j) {
                hotbarsnapshot.set(j, clientplayerentity.l_1268_F.s_956_w(j).t_148_a());
            }
            x_282_a itextcomponent = client.P_4830_p.RealmsServerPing[index].u_2550_I();
            x_282_a itextcomponent1 = client.P_4830_p.M_1641_O.u_2550_I();
            client.M_588_G.n_1700_B(new F_2904_S("inventory.hotbarSaved", itextcomponent1, itextcomponent), false);
            creativesettings.n_1700_B();
        }
    }

    public static class n_1700_B
    extends a_2900_S {
        public final NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B();

        public n_1700_B(a_3913_L player) {
            super(null, 0);
            W_3491_f playerinventory = player.l_1268_F;
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.J_1907_R(new R_4764_Y(R_4764_Y, i * 9 + j, 9 + j * 18, 18 + i * 18));
                }
            }
            for (int k = 0; k < 9; ++k) {
                this.J_1907_R(new Slot(playerinventory, k, 9 + k * 18, 112));
            }
            this.n_1700_B(0.0f);
        }

        @Override
        public boolean n_1700_B(a_3913_L playerIn) {
            return true;
        }

        public void n_1700_B(float pos) {
            int i = (this.n_1700_B.size() + 9 - 1) / 9 - 5;
            int j = (int)((double)(pos * (float)i) + 0.5);
            if (j < 0) {
                j = 0;
            }
            for (int k = 0; k < 5; ++k) {
                for (int l = 0; l < 9; ++l) {
                    int i1 = l + (k + j) * 9;
                    if (i1 >= 0 && i1 < this.n_1700_B.size()) {
                        R_4764_Y.J_1907_R(l + k * 9, this.n_1700_B.get(i1));
                        continue;
                    }
                    R_4764_Y.J_1907_R(l + k * 9, Z_1993_T.J_1907_R);
                }
            }
        }

        public boolean n_1700_B() {
            return this.n_1700_B.size() > 45;
        }

        @Override
        public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
            Slot slot;
            if (index >= this.P_1922_E.size() - 9 && index < this.P_1922_E.size() && (slot = (Slot)this.P_1922_E.get(index)) != null && slot.J_1907_R()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            }
            return Z_1993_T.J_1907_R;
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
            return slotIn.R_4764_Y != R_4764_Y;
        }

        @Override
        public boolean n_1700_B(Slot slotIn) {
            return slotIn.R_4764_Y != R_4764_Y;
        }
    }

    static class J_1907_R
    extends Slot {
        private final Slot n_1700_B;

        public J_1907_R(Slot p_i229959_1_, int p_i229959_2_, int p_i229959_3_, int p_i229959_4_) {
            super(p_i229959_1_.R_4764_Y, p_i229959_2_, p_i229959_3_, p_i229959_4_);
            this.n_1700_B = p_i229959_1_;
        }

        @Override
        public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
            return this.n_1700_B.n_1700_B(thePlayer, stack);
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack) {
            return this.n_1700_B.n_1700_B(stack);
        }

        @Override
        public Z_1993_T n_1700_B() {
            return this.n_1700_B.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B.J_1907_R();
        }

        @Override
        public void J_1907_R(Z_1993_T stack) {
            this.n_1700_B.J_1907_R(stack);
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.R_4764_Y();
        }

        @Override
        public int G_564_y() {
            return this.n_1700_B.G_564_y();
        }

        @Override
        public int R_4764_Y(Z_1993_T stack) {
            return this.n_1700_B.R_4764_Y(stack);
        }

        @Override
        @Nullable
        public Pair<g_2336_b, g_2336_b> P_1922_E() {
            return this.n_1700_B.P_1922_E();
        }

        @Override
        public Z_1993_T n_1700_B(int amount) {
            return this.n_1700_B.n_1700_B(amount);
        }

        @Override
        public boolean u_1723_Y() {
            return this.n_1700_B.u_1723_Y();
        }

        @Override
        public boolean n_1700_B(a_3913_L playerIn) {
            return this.n_1700_B.n_1700_B(playerIn);
        }
    }

    static class R_4764_Y
    extends Slot {
        public R_4764_Y(Container inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean n_1700_B(a_3913_L playerIn) {
            if (super.n_1700_B(playerIn) && this.J_1907_R()) {
                return this.n_1700_B().J_1907_R("CustomCreativeLock") == null;
            }
            return !this.J_1907_R();
        }
    }
}



