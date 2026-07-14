/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet
 *  it.unimi.dsi.fastutil.objects.ObjectSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.RecipeCollection;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.G_304_t;
import lightning.product.I_3887_a;
import lightning.product.I_4724_t;
import lightning.product.J_1907_R;
import lightning.product.ServerboundRecipeBookChangeSettingsPacket;
import lightning.product.O_694_j;
import lightning.product.SearchRegistry;
import lightning.product.P_69_Y;
import lightning.product.Widget;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.b_164_E;
import lightning.product.b_3278_X;
import lightning.product.c_1070_s;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.LanguageManager;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.StateSwitchingButton;
import lightning.product.Recipe;
import lightning.product.k_2603_m;
import lightning.product.PlaceRecipe;
import lightning.product.n_1700_B;
import lightning.product.n_4974_X;
import lightning.product.RecipeBookTabButton;
import lightning.product.r_4432_i;
import lightning.product.x_282_a;
import lightning.product.RecipeBookMenu;

public class j_4436_c
extends C_2701_A
implements GuiEventListener,
G_304_t,
Widget,
PlaceRecipe<b_3278_X> {
    protected static final g_2336_b n_1700_B = new g_2336_b("textures/gui/recipe_book.png");
    private static final x_282_a u_1723_Y = new F_2904_S("gui.recipebook.search_hint").n_1700_B(D_4024_W.Y_259_p).n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a v_4262_N = new F_2904_S("gui.recipebook.toggleRecipes.craftable");
    private static final x_282_a w_1484_f = new F_2904_S("gui.recipebook.toggleRecipes.all");
    private int t_148_a;
    private int s_956_w;
    private int u_2550_I;
    protected final P_69_Y J_1907_R = new P_69_Y();
    private final List<RecipeBookTabButton> M_588_G = Lists.newArrayList();
    private RecipeBookTabButton P_4830_p;
    protected StateSwitchingButton R_4764_Y;
    protected RecipeBookMenu<?> G_564_y;
    protected MinecraftClient P_1922_E;
    private O_694_j h_1847_R;
    private String Q_4569_t = "";
    private c_1070_s M_182_A;
    private final I_4724_t t_1786_h = new I_4724_t();
    private final r_4432_i multiplayerClientSuggestionProvider = new r_4432_i();
    private int w_1457_N;
    private boolean Y_601_j;

    public void n_1700_B(int widthIn, int heightIn, MinecraftClient minecraftIn, boolean widthTooNarrowIn, RecipeBookMenu<?> containerIn) {
        this.P_1922_E = minecraftIn;
        this.s_956_w = widthIn;
        this.u_2550_I = heightIn;
        this.G_564_y = containerIn;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (minecraftIn.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 == null) {
            minecraftIn.Y_259_p.H_1873_g = containerIn;
        } else {
            bot1.P_1922_E.Q_2552_b.H_1873_g = containerIn;
        }
        this.M_182_A = bot1 != null ? bot1.P_1922_E.Q_2552_b.M_182_A() : minecraftIn.Y_259_p.M_182_A();
        int n = this.w_1457_N = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F.t_148_a() : minecraftIn.Y_259_p.l_1268_F.t_148_a();
        if (this.u_1723_Y()) {
            this.n_1700_B(widthTooNarrowIn);
        }
        minecraftIn.Q_4569_t.n_1700_B(true);
    }

    public void n_1700_B(boolean widthTooNarrowIn) {
        this.t_148_a = widthTooNarrowIn ? 0 : 86;
        int i = (this.s_956_w - 147) / 2 - this.t_148_a;
        int j = (this.u_2550_I - 166) / 2;
        this.multiplayerClientSuggestionProvider.n_1700_B();
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 == null) {
            this.P_1922_E.Y_259_p.l_1268_F.n_1700_B(this.multiplayerClientSuggestionProvider);
        } else {
            bot1.P_1922_E.Q_2552_b.l_1268_F.n_1700_B(this.multiplayerClientSuggestionProvider);
        }
        this.G_564_y.n_1700_B(this.multiplayerClientSuggestionProvider);
        String s = this.h_1847_R != null ? this.h_1847_R.getText() : "";
        this.h_1847_R = new O_694_j(this.P_1922_E.t_148_a, i + 25, j + 14, 80, 14, new F_2904_S("itemGroup.search"));
        this.h_1847_R.setMaxStringLength(50);
        this.h_1847_R.setEnableBackgroundDrawing(false);
        this.h_1847_R.setVisible(true);
        this.h_1847_R.setTextColor(0xFFFFFF);
        this.h_1847_R.setText(s);
        this.t_1786_h.n_1700_B(this.P_1922_E, i, j);
        this.t_1786_h.n_1700_B(this);
        this.R_4764_Y = new StateSwitchingButton(i + 110, j + 12, 26, 16, this.M_182_A.n_1700_B(this.G_564_y));
        this.n_1700_B();
        this.M_588_G.clear();
        for (n_4974_X recipebookcategories : n_4974_X.n_1700_B(this.G_564_y.t_148_a())) {
            this.M_588_G.add(new RecipeBookTabButton(recipebookcategories));
        }
        if (this.P_4830_p != null) {
            this.P_4830_p = this.M_588_G.stream().filter(p_209505_1_ -> p_209505_1_.n_1700_B().equals((Object)this.P_4830_p.n_1700_B())).findFirst().orElse(null);
        }
        if (this.P_4830_p == null) {
            this.P_4830_p = this.M_588_G.get(0);
        }
        this.P_4830_p.n_1700_B(true);
        this.R_4764_Y(false);
        this.J_1907_R();
    }

    @Override
    public boolean changeFocus(boolean focus) {
        return false;
    }

    protected void n_1700_B() {
        this.R_4764_Y.n_1700_B(152, 41, 28, 18, n_1700_B);
    }

    public void G_564_y() {
        this.h_1847_R = null;
        this.P_4830_p = null;
        this.P_1922_E.Q_4569_t.n_1700_B(false);
    }

    public int n_1700_B(boolean p_193011_1_, int p_193011_2_, int p_193011_3_) {
        int i = this.u_1723_Y() && !p_193011_1_ ? 177 + (p_193011_2_ - p_193011_3_ - 200) / 2 : (p_193011_2_ - p_193011_3_) / 2;
        return i;
    }

    public void P_1922_E() {
        this.J_1907_R(!this.u_1723_Y());
    }

    public boolean u_1723_Y() {
        return this.M_182_A.n_1700_B(this.G_564_y.t_148_a());
    }

    protected void J_1907_R(boolean p_193006_1_) {
        this.M_182_A.n_1700_B(this.G_564_y.t_148_a(), p_193006_1_);
        if (!p_193006_1_) {
            this.t_1786_h.R_4764_Y();
        }
        this.t_148_a();
    }

    public void n_1700_B(@Nullable Slot slotIn) {
        if (slotIn != null && slotIn.G_564_y < this.G_564_y.P_1922_E()) {
            this.J_1907_R.n_1700_B();
            if (this.u_1723_Y()) {
                this.s_956_w();
            }
        }
    }

    private void R_4764_Y(boolean p_193003_1_) {
        List<RecipeCollection> list = this.M_182_A.n_1700_B(this.P_4830_p.n_1700_B());
        list.forEach(p_193944_1_ -> p_193944_1_.n_1700_B(this.multiplayerClientSuggestionProvider, this.G_564_y.R_4764_Y(), this.G_564_y.G_564_y(), this.M_182_A));
        ArrayList list1 = Lists.newArrayList(list);
        list1.removeIf(p_193952_0_ -> !p_193952_0_.n_1700_B());
        list1.removeIf(p_193953_0_ -> !p_193953_0_.R_4764_Y());
        String s = this.h_1847_R.getText();
        if (!s.isEmpty()) {
            ObjectLinkedOpenHashSet objectset = new ObjectLinkedOpenHashSet(this.P_1922_E.n_1700_B(SearchRegistry.R_4764_Y).n_1700_B(s.toLowerCase(Locale.ROOT)));
            list1.removeIf(arg_0 -> j_4436_c.n_1700_B((ObjectSet)objectset, arg_0));
        }
        if (this.M_182_A.n_1700_B(this.G_564_y)) {
            list1.removeIf(p_193958_0_ -> !p_193958_0_.J_1907_R());
        }
        this.t_1786_h.n_1700_B(list1, p_193003_1_);
    }

    private void J_1907_R() {
        int i = (this.s_956_w - 147) / 2 - this.t_148_a - 30;
        int j = (this.u_2550_I - 166) / 2 + 3;
        int k = 27;
        int l = 0;
        for (RecipeBookTabButton recipetabtogglewidget : this.M_588_G) {
            n_4974_X recipebookcategories = recipetabtogglewidget.n_1700_B();
            if (recipebookcategories != n_4974_X.n_1700_B && recipebookcategories != n_4974_X.u_1723_Y) {
                if (!recipetabtogglewidget.n_1700_B(this.M_182_A)) continue;
                recipetabtogglewidget.n_1700_B(i, j + 27 * l++);
                recipetabtogglewidget.n_1700_B(this.P_1922_E);
                continue;
            }
            recipetabtogglewidget.visible = true;
            recipetabtogglewidget.n_1700_B(i, j + 27 * l++);
        }
    }

    public void v_4262_N() {
        if (this.u_1723_Y()) {
            int inventoryChanges;
            n_1700_B bot1 = null;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
            }
            int n = inventoryChanges = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F.t_148_a() : this.P_1922_E.Y_259_p.l_1268_F.t_148_a();
            if (this.w_1457_N != inventoryChanges) {
                this.s_956_w();
                this.w_1457_N = inventoryChanges;
            }
            this.h_1847_R.tick();
        }
    }

    private void s_956_w() {
        this.multiplayerClientSuggestionProvider.n_1700_B();
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 == null) {
            this.P_1922_E.Y_259_p.l_1268_F.n_1700_B(this.multiplayerClientSuggestionProvider);
        } else {
            bot1.P_1922_E.Q_2552_b.l_1268_F.n_1700_B(this.multiplayerClientSuggestionProvider);
        }
        this.G_564_y.n_1700_B(this.multiplayerClientSuggestionProvider);
        this.R_4764_Y(false);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.u_1723_Y()) {
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(0.0f, 0.0f, 100.0f);
            this.P_1922_E.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int i = (this.s_956_w - 147) / 2 - this.t_148_a;
            int j = (this.u_2550_I - 166) / 2;
            this.blit(matrixStack, i, j, 1, 1, 147, 166);
            if (!this.h_1847_R.isFocused() && this.h_1847_R.getText().isEmpty()) {
                j_4436_c.drawString(matrixStack, this.P_1922_E.t_148_a, u_1723_Y, i + 25, j + 14, -1);
            } else {
                this.h_1847_R.render(matrixStack, mouseX, mouseY, partialTicks);
            }
            for (RecipeBookTabButton recipetabtogglewidget : this.M_588_G) {
                recipetabtogglewidget.render(matrixStack, mouseX, mouseY, partialTicks);
            }
            this.R_4764_Y.render(matrixStack, mouseX, mouseY, partialTicks);
            this.t_1786_h.n_1700_B(matrixStack, i, j, mouseX, mouseY, partialTicks);
            c_4037_x.d_2461_k();
        }
    }

    public void n_1700_B(g_221_o p_238924_1_, int p_238924_2_, int p_238924_3_, int p_238924_4_, int p_238924_5_) {
        if (this.u_1723_Y()) {
            this.t_1786_h.n_1700_B(p_238924_1_, p_238924_4_, p_238924_5_);
            if (this.R_4764_Y.isHovered()) {
                x_282_a itextcomponent = this.u_2550_I();
                if (this.P_1922_E.Y_1740_V != null) {
                    this.P_1922_E.Y_1740_V.renderTooltip(p_238924_1_, itextcomponent, p_238924_4_, p_238924_5_);
                }
            }
            this.J_1907_R(p_238924_1_, p_238924_2_, p_238924_3_, p_238924_4_, p_238924_5_);
        }
    }

    private x_282_a u_2550_I() {
        return this.R_4764_Y.J_1907_R() ? this.R_4764_Y() : w_1484_f;
    }

    protected x_282_a R_4764_Y() {
        return v_4262_N;
    }

    private void J_1907_R(g_221_o p_238925_1_, int p_238925_2_, int p_238925_3_, int p_238925_4_, int p_238925_5_) {
        Z_1993_T itemstack = null;
        for (int i = 0; i < this.J_1907_R.J_1907_R(); ++i) {
            P_69_Y.n_1700_B ghostrecipe$ghostingredient = this.J_1907_R.n_1700_B(i);
            int j = ghostrecipe$ghostingredient.n_1700_B() + p_238925_2_;
            int k = ghostrecipe$ghostingredient.J_1907_R() + p_238925_3_;
            if (p_238925_4_ < j || p_238925_5_ < k || p_238925_4_ >= j + 16 || p_238925_5_ >= k + 16) continue;
            itemstack = ghostrecipe$ghostingredient.R_4764_Y();
        }
        if (itemstack != null && this.P_1922_E.Y_1740_V != null) {
            this.P_1922_E.Y_1740_V.func_243308_b(p_238925_1_, this.P_1922_E.Y_1740_V.getTooltipFromItem(itemstack), p_238925_4_, p_238925_5_);
        }
    }

    public void n_1700_B(g_221_o p_230477_1_, int p_230477_2_, int p_230477_3_, boolean p_230477_4_, float p_230477_5_) {
        this.J_1907_R.n_1700_B(p_230477_1_, this.P_1922_E, p_230477_2_, p_230477_3_, p_230477_4_, p_230477_5_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (this.u_1723_Y()) {
            boolean isSpectator;
            boolean bl = isSpectator = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2461_k() : this.P_1922_E.Y_259_p.d_2461_k();
            if (isSpectator) {
                return false;
            }
            if (this.t_1786_h.n_1700_B(mouseX, mouseY, button, (this.s_956_w - 147) / 2 - this.t_148_a, (this.u_2550_I - 166) / 2, 147, 166)) {
                Recipe<?> irecipe = this.t_1786_h.n_1700_B();
                RecipeCollection recipelist = this.t_1786_h.J_1907_R();
                if (irecipe != null && recipelist != null) {
                    if (!recipelist.n_1700_B(irecipe) && this.J_1907_R.R_4764_Y() == irecipe) {
                        return false;
                    }
                    this.J_1907_R.n_1700_B();
                    if (bot1 == null) {
                        this.P_1922_E.w_1457_N.sendPlaceRecipePacket(this.P_1922_E.Y_259_p.H_1873_g.u_1723_Y, irecipe, k_2603_m.hasShiftDown());
                    } else {
                        bot1.P_1922_E.C_2741_M.n_1700_B(bot1.P_1922_E.Q_2552_b.H_1873_g.u_1723_Y, irecipe, k_2603_m.hasShiftDown());
                    }
                    if (!this.h_1847_R()) {
                        this.J_1907_R(false);
                    }
                }
                return true;
            }
            if (this.h_1847_R.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
            if (this.R_4764_Y.mouseClicked(mouseX, mouseY, button)) {
                boolean flag = this.M_588_G();
                this.R_4764_Y.n_1700_B(flag);
                this.t_148_a();
                this.R_4764_Y(false);
                return true;
            }
            for (RecipeBookTabButton recipetabtogglewidget : this.M_588_G) {
                if (!recipetabtogglewidget.mouseClicked(mouseX, mouseY, button)) continue;
                if (this.P_4830_p != recipetabtogglewidget) {
                    this.P_4830_p.n_1700_B(false);
                    this.P_4830_p = recipetabtogglewidget;
                    this.P_4830_p.n_1700_B(true);
                    this.R_4764_Y(true);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean M_588_G() {
        I_3887_a recipebookcategory = this.G_564_y.t_148_a();
        boolean flag = !this.M_182_A.J_1907_R(recipebookcategory);
        this.M_182_A.J_1907_R(recipebookcategory, flag);
        return flag;
    }

    public boolean n_1700_B(double mouseX, double mouseY, int guiLeft, int guiTop, int xSize, int ySize, int mouseButton) {
        if (!this.u_1723_Y()) {
            return true;
        }
        boolean flag = mouseX < (double)guiLeft || mouseY < (double)guiTop || mouseX >= (double)(guiLeft + xSize) || mouseY >= (double)(guiTop + ySize);
        boolean flag1 = (double)(guiLeft - 147) < mouseX && mouseX < (double)guiLeft && (double)guiTop < mouseY && mouseY < (double)(guiTop + ySize);
        return flag && !flag1 && !this.P_4830_p.isHovered();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean isSpectator;
        this.Y_601_j = false;
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        boolean bl = isSpectator = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2461_k() : this.P_1922_E.Y_259_p.d_2461_k();
        if (this.u_1723_Y() && !isSpectator) {
            if (keyCode == 256 && !this.h_1847_R()) {
                this.J_1907_R(false);
                return true;
            }
            if (this.h_1847_R.keyPressed(keyCode, scanCode, modifiers)) {
                this.P_4830_p();
                return true;
            }
            if (this.h_1847_R.isFocused() && this.h_1847_R.getVisible() && keyCode != 256) {
                return true;
            }
            if (this.P_1922_E.P_4830_p.Ops.n_1700_B(keyCode, scanCode) && !this.h_1847_R.isFocused()) {
                this.Y_601_j = true;
                this.h_1847_R.setFocused2(true);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        this.Y_601_j = false;
        return GuiEventListener.super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (this.Y_601_j) {
            return false;
        }
        if (this.u_1723_Y() && !(bot1 == null ? this.P_1922_E.Y_259_p.d_2461_k() : bot1.P_1922_E.Q_2552_b.d_2461_k())) {
            if (this.h_1847_R.charTyped(codePoint, modifiers)) {
                this.P_4830_p();
                return true;
            }
            return GuiEventListener.super.charTyped(codePoint, modifiers);
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }

    private void P_4830_p() {
        String s = this.h_1847_R.getText().toLowerCase(Locale.ROOT);
        this.n_1700_B(s);
        if (!s.equals(this.Q_4569_t)) {
            this.R_4764_Y(false);
            this.Q_4569_t = s;
        }
    }

    private void n_1700_B(String text) {
        if ("excitedze".equals(text)) {
            LanguageManager languagemanager = this.P_1922_E.e_2887_G();
            b_164_E language = languagemanager.n_1700_B("en_pt");
            if (languagemanager.J_1907_R().n_1700_B(language) == 0) {
                return;
            }
            languagemanager.n_1700_B(language);
            this.P_1922_E.P_4830_p.RealmsConfirmScreen = language.getCode();
            this.P_1922_E.w_1484_f();
            this.P_1922_E.P_4830_p.J_1907_R();
        }
    }

    private boolean h_1847_R() {
        return this.t_148_a == 86;
    }

    public void w_1484_f() {
        this.J_1907_R();
        if (this.u_1723_Y()) {
            this.R_4764_Y(false);
        }
    }

    @Override
    public void n_1700_B(List<Recipe<?>> recipes) {
        for (Recipe<?> irecipe : recipes) {
            this.P_1922_E.Y_259_p.n_1700_B(irecipe);
        }
    }

    public void n_1700_B(Recipe<?> p_193951_1_, List<Slot> p_193951_2_) {
        Z_1993_T itemstack = p_193951_1_.R_4764_Y();
        this.J_1907_R.n_1700_B(p_193951_1_);
        this.J_1907_R.n_1700_B(b_3278_X.n_1700_B(itemstack), p_193951_2_.get((int)0).P_1922_E, p_193951_2_.get((int)0).u_1723_Y);
        this.n_1700_B(this.G_564_y.R_4764_Y(), this.G_564_y.G_564_y(), this.G_564_y.J_1907_R(), p_193951_1_, p_193951_1_.n_1700_B().iterator(), 0);
    }

    @Override
    public void n_1700_B(Iterator<b_3278_X> ingredients, int slotIn, int maxAmount, int y, int x) {
        b_3278_X ingredient = ingredients.next();
        if (!ingredient.G_564_y()) {
            Slot slot = (Slot)this.G_564_y.P_1922_E.get(slotIn);
            this.J_1907_R.n_1700_B(ingredient, slot.P_1922_E, slot.u_1723_Y);
        }
    }

    protected void t_148_a() {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.P_1922_E.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (bot1 != null ? bot1.P_1922_E.Q_2552_b.n_1700_B == null : this.P_1922_E.k_2293_S() == null) {
            return;
        }
        I_3887_a recipebookcategory = this.G_564_y.t_148_a();
        boolean flag = this.M_182_A.J_1907_R().n_1700_B(recipebookcategory);
        boolean flag1 = this.M_182_A.J_1907_R().J_1907_R(recipebookcategory);
        if (bot1 == null) {
            this.P_1922_E.k_2293_S().n_1700_B(new ServerboundRecipeBookChangeSettingsPacket(recipebookcategory, flag, flag1));
        } else {
            bot1.P_1922_E.Q_2552_b.n_1700_B.n_1700_B(new ServerboundRecipeBookChangeSettingsPacket(recipebookcategory, flag, flag1));
        }
    }

    private static /* synthetic */ boolean n_1700_B(ObjectSet objectset, RecipeCollection p_193947_1_) {
        return !objectset.contains((Object)p_193947_1_);
    }
}



