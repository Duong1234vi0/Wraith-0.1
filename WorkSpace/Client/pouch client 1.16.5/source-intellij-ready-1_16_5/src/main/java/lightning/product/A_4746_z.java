/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.PageButton;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.D_4338_T;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.StringTag;
import lightning.product.I_1084_e;
import lightning.product.SharedConstants;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.Button;
import lightning.product.X_933_l;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.f_1703_u;
import lightning.product.g_221_o;
import lightning.product.i_1140_L;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.q_2896_o;
import lightning.product.CommonComponents;
import lightning.product.Rect2i;
import lightning.product.w_2043_E;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

public class A_4746_z
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("book.editTitle");
    private static final x_282_a J_1907_R = new F_2904_S("book.finalizeWarning");
    private static final FormattedCharSequence R_4764_Y = FormattedCharSequence.n_1700_B("_", Z_1567_W.n_1700_B.n_1700_B(D_4024_W.n_1700_B));
    private static final FormattedCharSequence G_564_y = FormattedCharSequence.n_1700_B("_", Z_1567_W.n_1700_B.n_1700_B(D_4024_W.w_1484_f));
    private final a_3913_L P_1922_E;
    private final Z_1993_T u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private final List<String> u_2550_I = Lists.newArrayList();
    private String M_588_G = "";
    private final w_2043_E P_4830_p = new w_2043_E(this::M_588_G, this::J_1907_R, this::n_1700_B, this::n_1700_B, p_238774_1_ -> p_238774_1_.length() < 1024 && this.font.J_1907_R((String)p_238774_1_, 114) <= 128);
    private final w_2043_E h_1847_R = new w_2043_E(() -> this.M_588_G, p_238772_1_ -> {
        this.M_588_G = p_238772_1_;
    }, this::n_1700_B, this::n_1700_B, p_238771_0_ -> p_238771_0_.length() < 16);
    private long Q_4569_t;
    private int M_182_A = -1;
    private PageButton t_1786_h;
    private PageButton multiplayerClientSuggestionProvider;
    private Button w_1457_N;
    private Button Y_601_j;
    private Button Y_259_p;
    private Button Q_2552_b;
    private final x_1688_C C_2741_M;
    @Nullable
    private J_1907_R k_2293_S = lightning.product.A_4746_z$J_1907_R.n_1700_B;
    private x_282_a q_2307_F = U_2871_b.R_4764_Y;
    private final x_282_a Z_875_P;

    public A_4746_z(a_3913_L player, Z_1993_T bookIn, x_1688_C handIn) {
        super(I_1084_e.n_1700_B);
        this.P_1922_E = player;
        this.u_1723_Y = bookIn;
        this.C_2741_M = handIn;
        U_2912_j compoundnbt = bookIn.Q_4569_t();
        if (compoundnbt != null) {
            q_2896_o listnbt = compoundnbt.G_564_y("pages", 8).G_564_y();
            for (int i = 0; i < listnbt.size(); ++i) {
                this.u_2550_I.add(listnbt.t_148_a(i));
            }
        }
        if (this.u_2550_I.isEmpty()) {
            this.u_2550_I.add("");
        }
        this.Z_875_P = new F_2904_S("book.byAuthor", player.O_1309_Q()).n_1700_B(D_4024_W.t_148_a);
    }

    private void n_1700_B(String p_238760_1_) {
        if (this.minecraft != null) {
            w_2043_E.n_1700_B(this.minecraft, p_238760_1_);
        }
    }

    private String n_1700_B() {
        return this.minecraft != null ? w_2043_E.J_1907_R(this.minecraft) : "";
    }

    private int J_1907_R() {
        return this.u_2550_I.size();
    }

    @Override
    public void tick() {
        super.tick();
        ++this.t_148_a;
    }

    @Override
    protected void init() {
        this.h_1847_R();
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.Y_601_j = this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, new F_2904_S("book.signButton"), p_214201_1_ -> {
            this.w_1484_f = true;
            this.P_1922_E();
        }));
        this.w_1457_N = this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, CommonComponents.R_4764_Y, p_214204_1_ -> {
            this.minecraft.n_1700_B((k_2603_m)null);
            this.n_1700_B(false);
        }));
        this.Y_259_p = this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, new F_2904_S("book.finalizeButton"), p_214195_1_ -> {
            if (this.w_1484_f) {
                this.n_1700_B(true);
                this.minecraft.n_1700_B((k_2603_m)null);
            }
        }));
        this.Q_2552_b = this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, CommonComponents.G_564_y, p_214212_1_ -> {
            if (this.w_1484_f) {
                this.w_1484_f = false;
            }
            this.P_1922_E();
        }));
        int i = (this.width - 192) / 2;
        int j = 2;
        this.t_1786_h = this.addButton(new PageButton(i + 116, 159, true, p_214208_1_ -> this.G_564_y(), true));
        this.multiplayerClientSuggestionProvider = this.addButton(new PageButton(i + 43, 159, false, p_214205_1_ -> this.R_4764_Y(), true));
        this.P_1922_E();
    }

    private void R_4764_Y() {
        if (this.s_956_w > 0) {
            --this.s_956_w;
        }
        this.P_1922_E();
        this.Q_4569_t();
    }

    private void G_564_y() {
        if (this.s_956_w < this.J_1907_R() - 1) {
            ++this.s_956_w;
        } else {
            this.v_4262_N();
            if (this.s_956_w < this.J_1907_R() - 1) {
                ++this.s_956_w;
            }
        }
        this.P_1922_E();
        this.Q_4569_t();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void P_1922_E() {
        this.multiplayerClientSuggestionProvider.visible = !this.w_1484_f && this.s_956_w > 0;
        this.t_1786_h.visible = !this.w_1484_f;
        this.w_1457_N.visible = !this.w_1484_f;
        this.Y_601_j.visible = !this.w_1484_f;
        this.Q_2552_b.visible = this.w_1484_f;
        this.Y_259_p.visible = this.w_1484_f;
        this.Y_259_p.active = !this.M_588_G.trim().isEmpty();
    }

    private void u_1723_Y() {
        ListIterator<String> listiterator = this.u_2550_I.listIterator(this.u_2550_I.size());
        while (listiterator.hasPrevious() && listiterator.previous().isEmpty()) {
            listiterator.remove();
        }
    }

    private void n_1700_B(boolean publish) {
        if (this.v_4262_N) {
            this.u_1723_Y();
            q_2896_o listnbt = new q_2896_o();
            this.u_2550_I.stream().map(StringTag::n_1700_B).forEach(listnbt::add);
            if (!this.u_2550_I.isEmpty()) {
                this.u_1723_Y.n_1700_B("pages", listnbt);
            }
            if (publish) {
                this.u_1723_Y.n_1700_B("author", StringTag.n_1700_B(this.P_1922_E.y_4642_Y().getName()));
                this.u_1723_Y.n_1700_B("title", StringTag.n_1700_B(this.M_588_G.trim()));
            }
            int i = this.C_2741_M == x_1688_C.n_1700_B ? this.P_1922_E.l_1268_F.G_564_y : 40;
            this.minecraft.k_2293_S().n_1700_B(new D_4338_T(this.u_1723_Y, publish, i));
        }
    }

    private void v_4262_N() {
        if (this.J_1907_R() < 100) {
            this.u_2550_I.add("");
            this.v_4262_N = true;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (this.w_1484_f) {
            return this.J_1907_R(keyCode, scanCode, modifiers);
        }
        boolean flag = this.n_1700_B(keyCode, scanCode, modifiers);
        if (flag) {
            this.h_1847_R();
            return true;
        }
        return false;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (super.charTyped(codePoint, modifiers)) {
            return true;
        }
        if (this.w_1484_f) {
            boolean flag = this.h_1847_R.n_1700_B(codePoint);
            if (flag) {
                this.P_1922_E();
                this.v_4262_N = true;
                return true;
            }
            return false;
        }
        if (SharedConstants.n_1700_B(codePoint)) {
            this.P_4830_p.n_1700_B(Character.toString(codePoint));
            this.h_1847_R();
            return true;
        }
        return false;
    }

    private boolean n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (k_2603_m.isSelectAll(keyCode)) {
            this.P_4830_p.G_564_y();
            return true;
        }
        if (k_2603_m.isCopy(keyCode)) {
            this.P_4830_p.R_4764_Y();
            return true;
        }
        if (k_2603_m.isPaste(keyCode)) {
            this.P_4830_p.J_1907_R();
            return true;
        }
        if (k_2603_m.isCut(keyCode)) {
            this.P_4830_p.n_1700_B();
            return true;
        }
        switch (keyCode) {
            case 257: 
            case 335: {
                this.P_4830_p.n_1700_B("\n");
                return true;
            }
            case 259: {
                this.P_4830_p.J_1907_R(-1);
                return true;
            }
            case 261: {
                this.P_4830_p.J_1907_R(1);
                return true;
            }
            case 262: {
                this.P_4830_p.n_1700_B(1, k_2603_m.hasShiftDown());
                return true;
            }
            case 263: {
                this.P_4830_p.n_1700_B(-1, k_2603_m.hasShiftDown());
                return true;
            }
            case 264: {
                this.t_148_a();
                return true;
            }
            case 265: {
                this.w_1484_f();
                return true;
            }
            case 266: {
                this.multiplayerClientSuggestionProvider.onPress();
                return true;
            }
            case 267: {
                this.t_1786_h.onPress();
                return true;
            }
            case 268: {
                this.s_956_w();
                return true;
            }
            case 269: {
                this.u_2550_I();
                return true;
            }
        }
        return false;
    }

    private void w_1484_f() {
        this.n_1700_B(-1);
    }

    private void t_148_a() {
        this.n_1700_B(1);
    }

    private void n_1700_B(int p_238755_1_) {
        int i = this.P_4830_p.u_1723_Y();
        int j = this.P_4830_p().n_1700_B(i, p_238755_1_);
        this.P_4830_p.R_4764_Y(j, k_2603_m.hasShiftDown());
    }

    private void s_956_w() {
        int i = this.P_4830_p.u_1723_Y();
        int j = this.P_4830_p().n_1700_B(i);
        this.P_4830_p.R_4764_Y(j, k_2603_m.hasShiftDown());
    }

    private void u_2550_I() {
        J_1907_R editbookscreen$bookpage = this.P_4830_p();
        int i = this.P_4830_p.u_1723_Y();
        int j = editbookscreen$bookpage.J_1907_R(i);
        this.P_4830_p.R_4764_Y(j, k_2603_m.hasShiftDown());
    }

    private boolean J_1907_R(int keyCode, int scanCode, int modifiers) {
        switch (keyCode) {
            case 257: 
            case 335: {
                if (!this.M_588_G.isEmpty()) {
                    this.n_1700_B(true);
                    this.minecraft.n_1700_B((k_2603_m)null);
                }
                return true;
            }
            case 259: {
                this.h_1847_R.J_1907_R(-1);
                this.P_1922_E();
                this.v_4262_N = true;
                return true;
            }
        }
        return false;
    }

    private String M_588_G() {
        return this.s_956_w >= 0 && this.s_956_w < this.u_2550_I.size() ? this.u_2550_I.get(this.s_956_w) : "";
    }

    private void J_1907_R(String p_214217_1_) {
        if (this.s_956_w >= 0 && this.s_956_w < this.u_2550_I.size()) {
            this.u_2550_I.set(this.s_956_w, p_214217_1_);
            this.v_4262_N = true;
            this.h_1847_R();
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.setListener(null);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(i_1140_L.J_1907_R);
        int i = (this.width - 192) / 2;
        int j = 2;
        this.blit(matrixStack, i, 2, 0, 0, 192, 192);
        if (this.w_1484_f) {
            boolean flag = this.t_148_a / 6 % 2 == 0;
            FormattedCharSequence ireorderingprocessor = FormattedCharSequence.n_1700_B(FormattedCharSequence.n_1700_B(this.M_588_G, Z_1567_W.n_1700_B), flag ? R_4764_Y : G_564_y);
            int k = this.font.n_1700_B((FormattedText)n_1700_B);
            this.font.J_1907_R(matrixStack, n_1700_B, (float)(i + 36 + (114 - k) / 2), 34.0f, 0);
            int l = this.font.n_1700_B(ireorderingprocessor);
            this.font.J_1907_R(matrixStack, ireorderingprocessor, (float)(i + 36 + (114 - l) / 2), 50.0f, 0);
            int i1 = this.font.n_1700_B((FormattedText)this.Z_875_P);
            this.font.J_1907_R(matrixStack, this.Z_875_P, (float)(i + 36 + (114 - i1) / 2), 60.0f, 0);
            this.font.n_1700_B(J_1907_R, i + 36, 82, 114, 0);
        } else {
            int j1 = this.font.n_1700_B((FormattedText)this.q_2307_F);
            this.font.J_1907_R(matrixStack, this.q_2307_F, (float)(i - j1 + 192 - 44), 18.0f, 0);
            J_1907_R editbookscreen$bookpage = this.P_4830_p();
            for (n_1700_B editbookscreen$bookline : editbookscreen$bookpage.u_1723_Y) {
                this.font.J_1907_R(matrixStack, editbookscreen$bookline.R_4764_Y, (float)editbookscreen$bookline.G_564_y, (float)editbookscreen$bookline.P_1922_E, -16777216);
            }
            this.n_1700_B(editbookscreen$bookpage.v_4262_N);
            this.n_1700_B(matrixStack, editbookscreen$bookpage.R_4764_Y, editbookscreen$bookpage.G_564_y);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void n_1700_B(g_221_o p_238756_1_, R_4764_Y p_238756_2_, boolean p_238756_3_) {
        if (this.t_148_a / 6 % 2 == 0) {
            p_238756_2_ = this.J_1907_R(p_238756_2_);
            if (!p_238756_3_) {
                C_2701_A.fill(p_238756_1_, p_238756_2_.n_1700_B, p_238756_2_.J_1907_R - 1, p_238756_2_.n_1700_B + 1, p_238756_2_.J_1907_R + 9, -16777216);
            } else {
                this.font.J_1907_R(p_238756_1_, "_", (float)p_238756_2_.n_1700_B, (float)p_238756_2_.J_1907_R, 0);
            }
        }
    }

    private void n_1700_B(Rect2i[] p_238764_1_) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        c_4037_x.G_564_y(0.0f, 0.0f, 255.0f, 255.0f);
        c_4037_x.e_4240_b();
        c_4037_x.Y_1740_V();
        c_4037_x.n_1700_B(X_933_l.h_1847_R.h_1847_R);
        bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
        for (Rect2i rectangle2d : p_238764_1_) {
            int i = rectangle2d.n_1700_B();
            int j = rectangle2d.J_1907_R();
            int k = i + rectangle2d.R_4764_Y();
            int l = j + rectangle2d.G_564_y();
            bufferbuilder.pos(i, l, 0.0).endVertex();
            bufferbuilder.pos(k, l, 0.0).endVertex();
            bufferbuilder.pos(k, j, 0.0).endVertex();
            bufferbuilder.pos(i, j, 0.0).endVertex();
        }
        tessellator.J_1907_R();
        c_4037_x.t_4043_B();
        c_4037_x.x_607_J();
    }

    private R_4764_Y n_1700_B(R_4764_Y p_238758_1_) {
        return new R_4764_Y(p_238758_1_.n_1700_B - (this.width - 192) / 2 - 36, p_238758_1_.J_1907_R - 32);
    }

    private R_4764_Y J_1907_R(R_4764_Y p_238767_1_) {
        return new R_4764_Y(p_238767_1_.n_1700_B + (this.width - 192) / 2 + 36, p_238767_1_.J_1907_R + 32);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (button == 0) {
            long i = j_3341_s.J_1907_R();
            J_1907_R editbookscreen$bookpage = this.P_4830_p();
            int j = editbookscreen$bookpage.n_1700_B(this.font, this.n_1700_B(new R_4764_Y((int)mouseX, (int)mouseY)));
            if (j >= 0) {
                if (j == this.M_182_A && i - this.Q_4569_t < 250L) {
                    if (!this.P_4830_p.w_1484_f()) {
                        this.J_1907_R(j);
                    } else {
                        this.P_4830_p.G_564_y();
                    }
                } else {
                    this.P_4830_p.R_4764_Y(j, k_2603_m.hasShiftDown());
                }
                this.h_1847_R();
            }
            this.M_182_A = j;
            this.Q_4569_t = i;
        }
        return true;
    }

    private void J_1907_R(int p_238765_1_) {
        String s = this.M_588_G();
        this.P_4830_p.n_1700_B(f_1703_u.n_1700_B(s, -1, p_238765_1_, false), f_1703_u.n_1700_B(s, 1, p_238765_1_, false));
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (super.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        if (button == 0) {
            J_1907_R editbookscreen$bookpage = this.P_4830_p();
            int i = editbookscreen$bookpage.n_1700_B(this.font, this.n_1700_B(new R_4764_Y((int)mouseX, (int)mouseY)));
            this.P_4830_p.R_4764_Y(i, true);
            this.h_1847_R();
        }
        return true;
    }

    private J_1907_R P_4830_p() {
        if (this.k_2293_S == null) {
            this.k_2293_S = this.M_182_A();
            this.q_2307_F = new F_2904_S("book.pageIndicator", this.s_956_w + 1, this.J_1907_R());
        }
        return this.k_2293_S;
    }

    private void h_1847_R() {
        this.k_2293_S = null;
    }

    private void Q_4569_t() {
        this.P_4830_p.P_1922_E();
        this.h_1847_R();
    }

    private J_1907_R M_182_A() {
        R_4764_Y editbookscreen$point;
        boolean flag;
        String s = this.M_588_G();
        if (s.isEmpty()) {
            return lightning.product.A_4746_z$J_1907_R.n_1700_B;
        }
        int i = this.P_4830_p.u_1723_Y();
        int j = this.P_4830_p.v_4262_N();
        IntArrayList intlist = new IntArrayList();
        ArrayList list = Lists.newArrayList();
        MutableInt mutableint = new MutableInt();
        MutableBoolean mutableboolean = new MutableBoolean();
        f_1703_u charactermanager = this.font.J_1907_R();
        charactermanager.n_1700_B(s, 114, Z_1567_W.n_1700_B, true, (arg_0, arg_1, arg_2) -> this.n_1700_B(mutableint, s, mutableboolean, (IntList)intlist, list, arg_0, arg_1, arg_2));
        int[] aint = intlist.toIntArray();
        boolean bl = flag = i == s.length();
        if (flag && mutableboolean.isTrue()) {
            editbookscreen$point = new R_4764_Y(0, list.size() * 9);
        } else {
            int k = A_4746_z.n_1700_B(aint, i);
            int l = this.font.J_1907_R(s.substring(aint[k], i));
            editbookscreen$point = new R_4764_Y(l, k * 9);
        }
        ArrayList list1 = Lists.newArrayList();
        if (i != j) {
            int k1;
            int l2 = Math.min(i, j);
            int i1 = Math.max(i, j);
            int j1 = A_4746_z.n_1700_B(aint, l2);
            if (j1 == (k1 = A_4746_z.n_1700_B(aint, i1))) {
                int l1 = j1 * 9;
                int i2 = aint[j1];
                list1.add(this.n_1700_B(s, charactermanager, l2, i1, l1, i2));
            } else {
                int i3 = j1 + 1 > aint.length ? s.length() : aint[j1 + 1];
                list1.add(this.n_1700_B(s, charactermanager, l2, i3, j1 * 9, aint[j1]));
                for (int j3 = j1 + 1; j3 < k1; ++j3) {
                    int j2 = j3 * 9;
                    String s1 = s.substring(aint[j3], aint[j3 + 1]);
                    int k2 = (int)charactermanager.n_1700_B(s1);
                    list1.add(this.n_1700_B(new R_4764_Y(0, j2), new R_4764_Y(k2, j2 + 9)));
                }
                list1.add(this.n_1700_B(s, charactermanager, aint[k1], i1, k1 * 9, aint[k1]));
            }
        }
        return new J_1907_R(s, editbookscreen$point, flag, aint, list.toArray(new n_1700_B[0]), list1.toArray(new Rect2i[0]));
    }

    private static int n_1700_B(int[] p_238768_0_, int p_238768_1_) {
        int i = Arrays.binarySearch(p_238768_0_, p_238768_1_);
        return i < 0 ? -(i + 2) : i;
    }

    private Rect2i n_1700_B(String p_238761_1_, f_1703_u p_238761_2_, int p_238761_3_, int p_238761_4_, int p_238761_5_, int p_238761_6_) {
        String s = p_238761_1_.substring(p_238761_6_, p_238761_3_);
        String s1 = p_238761_1_.substring(p_238761_6_, p_238761_4_);
        R_4764_Y editbookscreen$point = new R_4764_Y((int)p_238761_2_.n_1700_B(s), p_238761_5_);
        R_4764_Y editbookscreen$point1 = new R_4764_Y((int)p_238761_2_.n_1700_B(s1), p_238761_5_ + 9);
        return this.n_1700_B(editbookscreen$point, editbookscreen$point1);
    }

    private Rect2i n_1700_B(R_4764_Y p_238759_1_, R_4764_Y p_238759_2_) {
        R_4764_Y editbookscreen$point = this.J_1907_R(p_238759_1_);
        R_4764_Y editbookscreen$point1 = this.J_1907_R(p_238759_2_);
        int i = Math.min(editbookscreen$point.n_1700_B, editbookscreen$point1.n_1700_B);
        int j = Math.max(editbookscreen$point.n_1700_B, editbookscreen$point1.n_1700_B);
        int k = Math.min(editbookscreen$point.J_1907_R, editbookscreen$point1.J_1907_R);
        int l = Math.max(editbookscreen$point.J_1907_R, editbookscreen$point1.J_1907_R);
        return new Rect2i(i, k, j - i, l - k);
    }

    private /* synthetic */ void n_1700_B(MutableInt mutableint, String s, MutableBoolean mutableboolean, IntList intlist, List list, Z_1567_W p_238762_6_, int p_238762_7_, int p_238762_8_) {
        int k3 = mutableint.getAndIncrement();
        String s2 = s.substring(p_238762_7_, p_238762_8_);
        mutableboolean.setValue(s2.endsWith("\n"));
        String s3 = StringUtils.stripEnd((String)s2, (String)" \n");
        int l3 = k3 * 9;
        R_4764_Y editbookscreen$point1 = this.J_1907_R(new R_4764_Y(0, l3));
        intlist.add(p_238762_7_);
        list.add(new n_1700_B(p_238762_6_, s3, editbookscreen$point1.n_1700_B, editbookscreen$point1.J_1907_R));
    }

    static class J_1907_R {
        private static final J_1907_R n_1700_B = new J_1907_R("", new R_4764_Y(0, 0), true, new int[]{0}, new n_1700_B[]{new n_1700_B(Z_1567_W.n_1700_B, "", 0, 0)}, new Rect2i[0]);
        private final String J_1907_R;
        private final R_4764_Y R_4764_Y;
        private final boolean G_564_y;
        private final int[] P_1922_E;
        private final n_1700_B[] u_1723_Y;
        private final Rect2i[] v_4262_N;

        public J_1907_R(String p_i232288_1_, R_4764_Y p_i232288_2_, boolean p_i232288_3_, int[] p_i232288_4_, n_1700_B[] p_i232288_5_, Rect2i[] p_i232288_6_) {
            this.J_1907_R = p_i232288_1_;
            this.R_4764_Y = p_i232288_2_;
            this.G_564_y = p_i232288_3_;
            this.P_1922_E = p_i232288_4_;
            this.u_1723_Y = p_i232288_5_;
            this.v_4262_N = p_i232288_6_;
        }

        public int n_1700_B(Y_4083_F p_238789_1_, R_4764_Y p_238789_2_) {
            int i = p_238789_2_.J_1907_R / 9;
            if (i < 0) {
                return 0;
            }
            if (i >= this.u_1723_Y.length) {
                return this.J_1907_R.length();
            }
            n_1700_B editbookscreen$bookline = this.u_1723_Y[i];
            return this.P_1922_E[i] + p_238789_1_.J_1907_R().n_1700_B(editbookscreen$bookline.J_1907_R, p_238789_2_.n_1700_B, editbookscreen$bookline.n_1700_B);
        }

        public int n_1700_B(int p_238788_1_, int p_238788_2_) {
            int k;
            int i = A_4746_z.n_1700_B(this.P_1922_E, p_238788_1_);
            int j = i + p_238788_2_;
            if (0 <= j && j < this.P_1922_E.length) {
                int l = p_238788_1_ - this.P_1922_E[i];
                int i1 = this.u_1723_Y[j].J_1907_R.length();
                k = this.P_1922_E[j] + Math.min(l, i1);
            } else {
                k = p_238788_1_;
            }
            return k;
        }

        public int n_1700_B(int p_238787_1_) {
            int i = A_4746_z.n_1700_B(this.P_1922_E, p_238787_1_);
            return this.P_1922_E[i];
        }

        public int J_1907_R(int p_238791_1_) {
            int i = A_4746_z.n_1700_B(this.P_1922_E, p_238791_1_);
            return this.P_1922_E[i] + this.u_1723_Y[i].J_1907_R.length();
        }
    }

    static class n_1700_B {
        private final Z_1567_W n_1700_B;
        private final String J_1907_R;
        private final x_282_a R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;

        public n_1700_B(Z_1567_W p_i232289_1_, String p_i232289_2_, int p_i232289_3_, int p_i232289_4_) {
            this.n_1700_B = p_i232289_1_;
            this.J_1907_R = p_i232289_2_;
            this.G_564_y = p_i232289_3_;
            this.P_1922_E = p_i232289_4_;
            this.R_4764_Y = new U_2871_b(p_i232289_2_).n_1700_B(p_i232289_1_);
        }
    }

    static class R_4764_Y {
        public final int n_1700_B;
        public final int J_1907_R;

        R_4764_Y(int p_i232290_1_, int p_i232290_2_) {
            this.n_1700_B = p_i232290_1_;
            this.J_1907_R = p_i232290_2_;
        }
    }
}


