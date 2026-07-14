/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_1658_r;
import lightning.product.C_2701_A;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.GuiEventListener;
import lightning.product.Widget;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import lightning.product.u_530_F;

public abstract class o_2488_o<E extends n_1700_B<E>>
extends A_1658_r
implements Widget {
    protected final MinecraftClient minecraft;
    protected final int itemHeight;
    private final List<E> children = new R_4764_Y();
    protected int width;
    protected int height;
    protected int y0;
    protected int y1;
    protected int x1;
    protected int x0;
    protected boolean centerListVertically = true;
    private double scrollAmount;
    private boolean renderSelection = true;
    private boolean renderHeader;
    protected int headerHeight;
    private boolean scrolling;
    private E selected;
    private boolean field_244603_t = true;
    private boolean field_244604_u = true;

    public o_2488_o(MinecraftClient mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int itemHeightIn) {
        this.minecraft = mcIn;
        this.width = widthIn;
        this.height = heightIn;
        this.y0 = topIn;
        this.y1 = bottomIn;
        this.itemHeight = itemHeightIn;
        this.x0 = 0;
        this.x1 = widthIn;
    }

    public void setRenderSelection(boolean value) {
        this.renderSelection = value;
    }

    protected void setRenderHeader(boolean value, int height) {
        this.renderHeader = value;
        this.headerHeight = height;
        if (!value) {
            this.headerHeight = 0;
        }
    }

    public int getRowWidth() {
        return 220;
    }

    @Nullable
    public E getSelected() {
        return this.selected;
    }

    public void setSelected(@Nullable E entry) {
        this.selected = entry;
    }

    public void func_244605_b(boolean p_244605_1_) {
        this.field_244603_t = p_244605_1_;
    }

    public void func_244606_c(boolean p_244606_1_) {
        this.field_244604_u = p_244606_1_;
    }

    @Nullable
    public E getListener() {
        return (E)((n_1700_B)super.getListener());
    }

    public final List<E> getEventListeners() {
        return this.children;
    }

    protected final void clearEntries() {
        this.children.clear();
    }

    protected void replaceEntries(Collection<E> entries) {
        this.children.clear();
        this.children.addAll(entries);
    }

    protected E getEntry(int index) {
        return (E)((n_1700_B)this.getEventListeners().get(index));
    }

    protected int addEntry(E entry) {
        this.children.add(entry);
        return this.children.size() - 1;
    }

    protected int getItemCount() {
        return this.getEventListeners().size();
    }

    protected boolean isSelectedItem(int index) {
        return Objects.equals(this.getSelected(), this.getEventListeners().get(index));
    }

    @Nullable
    protected final E getEntryAtPosition(double p_230933_1_, double p_230933_3_) {
        int i = this.getRowWidth() / 2;
        int j = this.x0 + this.width / 2;
        int k = j - i;
        int l = j + i;
        int i1 = u_530_F.R_4764_Y(p_230933_3_ - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount() - 4;
        int j1 = i1 / this.itemHeight;
        return (E)(p_230933_1_ < (double)this.getScrollbarPosition() && p_230933_1_ >= (double)k && p_230933_1_ <= (double)l && j1 >= 0 && i1 >= 0 && j1 < this.getItemCount() ? (n_1700_B)this.getEventListeners().get(j1) : null);
    }

    public void updateSize(int p_230940_1_, int p_230940_2_, int p_230940_3_, int p_230940_4_) {
        this.width = p_230940_1_;
        this.height = p_230940_2_;
        this.y0 = p_230940_3_;
        this.y1 = p_230940_4_;
        this.x0 = 0;
        this.x1 = p_230940_1_;
    }

    public void setLeftPos(int p_230959_1_) {
        this.x0 = p_230959_1_;
        this.x1 = p_230959_1_ + this.width;
    }

    protected int getMaxPosition() {
        return this.getItemCount() * this.itemHeight + this.headerHeight;
    }

    protected void clickedHeader(int p_230938_1_, int p_230938_2_) {
    }

    protected void renderHeader(g_221_o p_230448_1_, int p_230448_2_, int p_230448_3_, l_3747_P p_230448_4_) {
    }

    protected void renderBackground(g_221_o p_230433_1_) {
    }

    protected void renderDecorations(g_221_o p_230447_1_, int p_230447_2_, int p_230447_3_) {
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        int k1;
        this.renderBackground(matrixStack);
        int i = this.getScrollbarPosition();
        int j = i + 6;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        if (this.field_244603_t) {
            this.minecraft.G_624_v().n_1700_B(C_2701_A.BACKGROUND_LOCATION);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            float f = 32.0f;
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(this.x0, this.y1, 0.0).tex((float)this.x0 / 32.0f, (float)(this.y1 + (int)this.getScrollAmount()) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1, 0.0).tex((float)this.x1 / 32.0f, (float)(this.y1 + (int)this.getScrollAmount()) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y0, 0.0).tex((float)this.x1 / 32.0f, (float)(this.y0 + (int)this.getScrollAmount()) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x0, this.y0, 0.0).tex((float)this.x0 / 32.0f, (float)(this.y0 + (int)this.getScrollAmount()) / 32.0f).color(32, 32, 32, 255).endVertex();
            tessellator.J_1907_R();
        }
        int j1 = this.getRowLeft();
        int k = this.y0 + 4 - (int)this.getScrollAmount();
        if (this.renderHeader) {
            this.renderHeader(matrixStack, j1, k, tessellator);
        }
        this.renderList(matrixStack, j1, k, mouseX, mouseY, partialTicks);
        if (this.field_244604_u) {
            this.minecraft.G_624_v().n_1700_B(C_2701_A.BACKGROUND_LOCATION);
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.J_1907_R(519);
            float f1 = 32.0f;
            int l = -100;
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(this.x0, this.y0, -100.0).tex(0.0f, (float)this.y0 / 32.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0 + this.width, this.y0, -100.0).tex((float)this.width / 32.0f, (float)this.y0 / 32.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0 + this.width, 0.0, -100.0).tex((float)this.width / 32.0f, 0.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0, 0.0, -100.0).tex(0.0f, 0.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0, this.height, -100.0).tex(0.0f, (float)this.height / 32.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0 + this.width, this.height, -100.0).tex((float)this.width / 32.0f, (float)this.height / 32.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0 + this.width, this.y1, -100.0).tex((float)this.width / 32.0f, (float)this.y1 / 32.0f).color(64, 64, 64, 255).endVertex();
            bufferbuilder.pos(this.x0, this.y1, -100.0).tex(0.0f, (float)this.y1 / 32.0f).color(64, 64, 64, 255).endVertex();
            tessellator.J_1907_R();
            c_4037_x.J_1907_R(515);
            c_4037_x.t_1786_h();
            c_4037_x.Y_601_j();
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.P_1922_E);
            c_4037_x.u_2550_I();
            c_4037_x.w_1484_f(7425);
            c_4037_x.e_4240_b();
            int i1 = 4;
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(this.x0, this.y0 + 4, 0.0).tex(0.0f, 1.0f).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos(this.x1, this.y0 + 4, 0.0).tex(1.0f, 1.0f).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos(this.x1, this.y0, 0.0).tex(1.0f, 0.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x0, this.y0, 0.0).tex(0.0f, 0.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x0, this.y1, 0.0).tex(0.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1, 0.0).tex(1.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1 - 4, 0.0).tex(1.0f, 0.0f).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos(this.x0, this.y1 - 4, 0.0).tex(0.0f, 0.0f).color(0, 0, 0, 0).endVertex();
            tessellator.J_1907_R();
        }
        if ((k1 = this.getMaxScroll()) > 0) {
            c_4037_x.e_4240_b();
            int l1 = (int)((float)((this.y1 - this.y0) * (this.y1 - this.y0)) / (float)this.getMaxPosition());
            l1 = u_530_F.n_1700_B(l1, 32, this.y1 - this.y0 - 8);
            int i2 = (int)this.getScrollAmount() * (this.y1 - this.y0 - l1) / k1 + this.y0;
            if (i2 < this.y0) {
                i2 = this.y0;
            }
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(i, this.y1, 0.0).tex(0.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(j, this.y1, 0.0).tex(1.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(j, this.y0, 0.0).tex(1.0f, 0.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(i, this.y0, 0.0).tex(0.0f, 0.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(i, i2 + l1, 0.0).tex(0.0f, 1.0f).color(128, 128, 128, 255).endVertex();
            bufferbuilder.pos(j, i2 + l1, 0.0).tex(1.0f, 1.0f).color(128, 128, 128, 255).endVertex();
            bufferbuilder.pos(j, i2, 0.0).tex(1.0f, 0.0f).color(128, 128, 128, 255).endVertex();
            bufferbuilder.pos(i, i2, 0.0).tex(0.0f, 0.0f).color(128, 128, 128, 255).endVertex();
            bufferbuilder.pos(i, i2 + l1 - 1, 0.0).tex(0.0f, 1.0f).color(192, 192, 192, 255).endVertex();
            bufferbuilder.pos(j - 1, i2 + l1 - 1, 0.0).tex(1.0f, 1.0f).color(192, 192, 192, 255).endVertex();
            bufferbuilder.pos(j - 1, i2, 0.0).tex(1.0f, 0.0f).color(192, 192, 192, 255).endVertex();
            bufferbuilder.pos(i, i2, 0.0).tex(0.0f, 0.0f).color(192, 192, 192, 255).endVertex();
            tessellator.J_1907_R();
        }
        this.renderDecorations(matrixStack, mouseX, mouseY);
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
        c_4037_x.M_588_G();
        c_4037_x.Y_259_p();
    }

    protected void centerScrollOn(E p_230951_1_) {
        this.setScrollAmount(this.getEventListeners().indexOf(p_230951_1_) * this.itemHeight + this.itemHeight / 2 - (this.y1 - this.y0) / 2);
    }

    protected void ensureVisible(E p_230954_1_) {
        int k;
        int i = this.getRowTop(this.getEventListeners().indexOf(p_230954_1_));
        int j = i - this.y0 - 4 - this.itemHeight;
        if (j < 0) {
            this.scroll(j);
        }
        if ((k = this.y1 - i - this.itemHeight - this.itemHeight) < 0) {
            this.scroll(-k);
        }
    }

    private void scroll(int p_230937_1_) {
        this.setScrollAmount(this.getScrollAmount() + (double)p_230937_1_);
    }

    public double getScrollAmount() {
        return this.scrollAmount;
    }

    public void setScrollAmount(double p_230932_1_) {
        this.scrollAmount = u_530_F.n_1700_B(p_230932_1_, 0.0, (double)this.getMaxScroll());
    }

    public int getMaxScroll() {
        return Math.max(0, this.getMaxPosition() - (this.y1 - this.y0 - 4));
    }

    protected void updateScrollingState(double p_230947_1_, double p_230947_3_, int p_230947_5_) {
        this.scrolling = p_230947_5_ == 0 && p_230947_1_ >= (double)this.getScrollbarPosition() && p_230947_1_ < (double)(this.getScrollbarPosition() + 6);
    }

    protected int getScrollbarPosition() {
        return this.width / 2 + 124;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.updateScrollingState(mouseX, mouseY, button);
        if (!this.isMouseOver(mouseX, mouseY)) {
            return false;
        }
        E e = this.getEntryAtPosition(mouseX, mouseY);
        if (e != null) {
            if (e.mouseClicked(mouseX, mouseY, button)) {
                this.setListener((GuiEventListener)e);
                this.setDragging(true);
                return true;
            }
        } else if (button == 0) {
            this.clickedHeader((int)(mouseX - (double)(this.x0 + this.width / 2 - this.getRowWidth() / 2)), (int)(mouseY - (double)this.y0) + (int)this.getScrollAmount() - 4);
            return true;
        }
        return this.scrolling;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.getListener() != null) {
            this.getListener().mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (super.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        if (button == 0 && this.scrolling) {
            if (mouseY < (double)this.y0) {
                this.setScrollAmount(0.0);
            } else if (mouseY > (double)this.y1) {
                this.setScrollAmount(this.getMaxScroll());
            } else {
                double d0 = Math.max(1, this.getMaxScroll());
                int i = this.y1 - this.y0;
                int j = u_530_F.n_1700_B((int)((float)(i * i) / (float)this.getMaxPosition()), 32, i - 8);
                double d1 = Math.max(1.0, d0 / (double)(i - j));
                this.setScrollAmount(this.getScrollAmount() + dragY * d1);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        this.setScrollAmount(this.getScrollAmount() - delta * (double)this.itemHeight / 2.0);
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == 264) {
            this.moveSelection(J_1907_R.J_1907_R);
            return true;
        }
        if (keyCode == 265) {
            this.moveSelection(J_1907_R.n_1700_B);
            return true;
        }
        return false;
    }

    protected void moveSelection(J_1907_R p_241219_1_) {
        this.func_241572_a_(p_241219_1_, p_241573_0_ -> true);
    }

    protected void func_241574_n_() {
        E e = this.getSelected();
        if (e != null) {
            this.setSelected(e);
            this.ensureVisible(e);
        }
    }

    protected void func_241572_a_(J_1907_R p_241572_1_, Predicate<E> p_241572_2_) {
        int i;
        int n = i = p_241572_1_ == J_1907_R.n_1700_B ? -1 : 1;
        if (!this.getEventListeners().isEmpty()) {
            int k;
            int j = this.getEventListeners().indexOf(this.getSelected());
            while (j != (k = u_530_F.n_1700_B(j + i, 0, this.getItemCount() - 1))) {
                n_1700_B e = (n_1700_B)this.getEventListeners().get(k);
                if (p_241572_2_.test(e)) {
                    this.setSelected(e);
                    this.ensureVisible(e);
                    break;
                }
                j = k;
            }
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseY >= (double)this.y0 && mouseY <= (double)this.y1 && mouseX >= (double)this.x0 && mouseX <= (double)this.x1;
    }

    protected void renderList(g_221_o p_238478_1_, int p_238478_2_, int p_238478_3_, int p_238478_4_, int p_238478_5_, float p_238478_6_) {
        int i = this.getItemCount();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        for (int j = 0; j < i; ++j) {
            int k = this.getRowTop(j);
            int l = this.getRowBottom(j);
            if (l < this.y0 || k > this.y1) continue;
            int i1 = p_238478_3_ + j * this.itemHeight + this.headerHeight;
            int j1 = this.itemHeight - 4;
            E e = this.getEntry(j);
            int k1 = this.getRowWidth();
            if (this.renderSelection && this.isSelectedItem(j)) {
                int l1 = this.x0 + this.width / 2 - k1 / 2;
                int i2 = this.x0 + this.width / 2 + k1 / 2;
                c_4037_x.e_4240_b();
                float f = this.isFocused() ? 1.0f : 0.5f;
                c_4037_x.G_564_y(f, f, f, 1.0f);
                bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
                bufferbuilder.pos(l1, i1 + j1 + 2, 0.0).endVertex();
                bufferbuilder.pos(i2, i1 + j1 + 2, 0.0).endVertex();
                bufferbuilder.pos(i2, i1 - 2, 0.0).endVertex();
                bufferbuilder.pos(l1, i1 - 2, 0.0).endVertex();
                tessellator.J_1907_R();
                c_4037_x.G_564_y(0.0f, 0.0f, 0.0f, 1.0f);
                bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
                bufferbuilder.pos(l1 + 1, i1 + j1 + 1, 0.0).endVertex();
                bufferbuilder.pos(i2 - 1, i1 + j1 + 1, 0.0).endVertex();
                bufferbuilder.pos(i2 - 1, i1 - 1, 0.0).endVertex();
                bufferbuilder.pos(l1 + 1, i1 - 1, 0.0).endVertex();
                tessellator.J_1907_R();
                c_4037_x.x_607_J();
            }
            int j2 = this.getRowLeft();
            ((n_1700_B)e).render(p_238478_1_, j, k, j2, k1, j1, p_238478_4_, p_238478_5_, this.isMouseOver(p_238478_4_, p_238478_5_) && Objects.equals(this.getEntryAtPosition(p_238478_4_, p_238478_5_), e), p_238478_6_);
        }
    }

    public int getRowLeft() {
        return this.x0 + this.width / 2 - this.getRowWidth() / 2 + 2;
    }

    public int func_244736_r() {
        return this.getRowLeft() + this.getRowWidth();
    }

    protected int getRowTop(int p_230962_1_) {
        return this.y0 + 4 - (int)this.getScrollAmount() + p_230962_1_ * this.itemHeight + this.headerHeight;
    }

    private int getRowBottom(int p_230948_1_) {
        return this.getRowTop(p_230948_1_) + this.itemHeight;
    }

    protected boolean isFocused() {
        return false;
    }

    protected E remove(int p_230964_1_) {
        n_1700_B e = (n_1700_B)this.children.get(p_230964_1_);
        return (E)(this.removeEntry((n_1700_B)this.children.get(p_230964_1_)) ? e : null);
    }

    protected boolean removeEntry(E p_230956_1_) {
        boolean flag = this.children.remove(p_230956_1_);
        if (flag && p_230956_1_ == this.getSelected()) {
            this.setSelected(null);
        }
        return flag;
    }

    private void func_238480_f_(n_1700_B<E> p_238480_1_) {
        p_238480_1_.list = this;
    }

    class R_4764_Y
    extends AbstractList<E> {
        private final List<E> J_1907_R = Lists.newArrayList();

        private R_4764_Y() {
        }

        public E n_1700_B(int p_get_1_) {
            return (n_1700_B)this.J_1907_R.get(p_get_1_);
        }

        @Override
        public int size() {
            return this.J_1907_R.size();
        }

        public E n_1700_B(int p_set_1_, E p_set_2_) {
            n_1700_B e = (n_1700_B)this.J_1907_R.set(p_set_1_, p_set_2_);
            o_2488_o.this.func_238480_f_(p_set_2_);
            return e;
        }

        public void J_1907_R(int p_add_1_, E p_add_2_) {
            this.J_1907_R.add(p_add_1_, p_add_2_);
            o_2488_o.this.func_238480_f_(p_add_2_);
        }

        public E J_1907_R(int p_remove_1_) {
            return (n_1700_B)this.J_1907_R.remove(p_remove_1_);
        }

        @Override
        public /* synthetic */ Object remove(int n) {
            return this.J_1907_R(n);
        }

        @Override
        public /* synthetic */ void add(int n, Object object) {
            this.J_1907_R(n, (n_1700_B)object);
        }

        @Override
        public /* synthetic */ Object set(int n, Object object) {
            return this.n_1700_B(n, (n_1700_B)object);
        }

        @Override
        public /* synthetic */ Object get(int n) {
            return this.n_1700_B(n);
        }
    }

    public static abstract class n_1700_B<E extends n_1700_B<E>>
    implements GuiEventListener {
        @Deprecated
        private o_2488_o<E> list;

        public abstract void render(g_221_o var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, float var10);

        @Override
        public boolean isMouseOver(double mouseX, double mouseY) {
            return Objects.equals(this.list.getEntryAtPosition(mouseX, mouseY), this);
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.o_2488_o$J_1907_R.n_1700_B();
        }
    }
}



