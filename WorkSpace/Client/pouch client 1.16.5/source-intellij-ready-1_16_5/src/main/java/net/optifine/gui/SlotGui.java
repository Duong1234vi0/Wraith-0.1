/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import java.util.Collections;
import java.util.List;
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

public abstract class SlotGui
extends A_1658_r
implements Widget {
    protected static final int NO_DRAG = -1;
    protected static final int DRAG_OUTSIDE = -2;
    protected final MinecraftClient minecraft;
    protected int width;
    protected int height;
    protected int y0;
    protected int y1;
    protected int x1;
    protected int x0;
    protected final int itemHeight;
    protected boolean centerListVertically = true;
    protected int yDrag = -2;
    protected double yo;
    protected boolean visible = true;
    protected boolean renderSelection = true;
    protected boolean renderHeader;
    protected int headerHeight;
    private boolean scrolling;

    public SlotGui(MinecraftClient mcIn, int width, int height, int topIn, int bottomIn, int slotHeightIn) {
        this.minecraft = mcIn;
        this.width = width;
        this.height = height;
        this.y0 = topIn;
        this.y1 = bottomIn;
        this.itemHeight = slotHeightIn;
        this.x0 = 0;
        this.x1 = width;
    }

    public void updateSize(int p_updateSize_1_, int p_updateSize_2_, int p_updateSize_3_, int p_updateSize_4_) {
        this.width = p_updateSize_1_;
        this.height = p_updateSize_2_;
        this.y0 = p_updateSize_3_;
        this.y1 = p_updateSize_4_;
        this.x0 = 0;
        this.x1 = p_updateSize_1_;
    }

    public void setRenderSelection(boolean p_setRenderSelection_1_) {
        this.renderSelection = p_setRenderSelection_1_;
    }

    protected void setRenderHeader(boolean p_setRenderHeader_1_, int p_setRenderHeader_2_) {
        this.renderHeader = p_setRenderHeader_1_;
        this.headerHeight = p_setRenderHeader_2_;
        if (!p_setRenderHeader_1_) {
            this.headerHeight = 0;
        }
    }

    public void setVisible(boolean p_setVisible_1_) {
        this.visible = p_setVisible_1_;
    }

    public boolean isVisible() {
        return this.visible;
    }

    protected abstract int getItemCount();

    @Override
    public List<? extends GuiEventListener> getEventListeners() {
        return Collections.emptyList();
    }

    protected boolean selectItem(int p_selectItem_1_, int p_selectItem_2_, double p_selectItem_3_, double p_selectItem_5_) {
        return true;
    }

    protected abstract boolean isSelectedItem(int var1);

    protected int getMaxPosition() {
        return this.getItemCount() * this.itemHeight + this.headerHeight;
    }

    protected abstract void renderBackground();

    protected void updateItemPosition(int p_updateItemPosition_1_, int p_updateItemPosition_2_, int p_updateItemPosition_3_, float p_updateItemPosition_4_) {
    }

    protected abstract void renderItem(g_221_o var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8);

    protected void renderHeader(int p_renderHeader_1_, int p_renderHeader_2_, l_3747_P p_renderHeader_3_) {
    }

    protected void clickedHeader(int p_clickedHeader_1_, int p_clickedHeader_2_) {
    }

    protected void renderDecorations(int p_renderDecorations_1_, int p_renderDecorations_2_) {
    }

    public int getItemAtPosition(double p_getItemAtPosition_1_, double p_getItemAtPosition_3_) {
        int i = this.x0 + this.width / 2 - this.getRowWidth() / 2;
        int j = this.x0 + this.width / 2 + this.getRowWidth() / 2;
        int k = u_530_F.R_4764_Y(p_getItemAtPosition_3_ - (double)this.y0) - this.headerHeight + (int)this.yo - 4;
        int l = k / this.itemHeight;
        return p_getItemAtPosition_1_ < (double)this.getScrollbarPosition() && p_getItemAtPosition_1_ >= (double)i && p_getItemAtPosition_1_ <= (double)j && l >= 0 && k >= 0 && l < this.getItemCount() ? l : -1;
    }

    protected void capYPosition() {
        this.yo = u_530_F.n_1700_B(this.yo, 0.0, (double)this.getMaxScroll());
    }

    public int getMaxScroll() {
        return Math.max(0, this.getMaxPosition() - (this.y1 - this.y0 - 4));
    }

    public void centerScrollOn(int p_centerScrollOn_1_) {
        this.yo = p_centerScrollOn_1_ * this.itemHeight + this.itemHeight / 2 - (this.y1 - this.y0) / 2;
        this.capYPosition();
    }

    public int getScroll() {
        return (int)this.yo;
    }

    public boolean isMouseInList(double p_isMouseInList_1_, double p_isMouseInList_3_) {
        return p_isMouseInList_3_ >= (double)this.y0 && p_isMouseInList_3_ <= (double)this.y1 && p_isMouseInList_1_ >= (double)this.x0 && p_isMouseInList_1_ <= (double)this.x1;
    }

    public int getScrollBottom() {
        return (int)this.yo - this.height - this.headerHeight;
    }

    public void scroll(int p_scroll_1_) {
        this.yo += (double)p_scroll_1_;
        this.capYPosition();
        this.yDrag = -2;
    }

    @Override
    public void render(g_221_o matrixStackIn, int p_render_1_, int p_render_2_, float p_render_3_) {
        if (this.visible) {
            this.renderBackground();
            int i = this.getScrollbarPosition();
            int j = i + 6;
            this.capYPosition();
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            this.minecraft.G_624_v().n_1700_B(C_2701_A.BACKGROUND_LOCATION);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            float f = 32.0f;
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(this.x0, this.y1, 0.0).tex((float)this.x0 / 32.0f, (float)(this.y1 + (int)this.yo) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1, 0.0).tex((float)this.x1 / 32.0f, (float)(this.y1 + (int)this.yo) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y0, 0.0).tex((float)this.x1 / 32.0f, (float)(this.y0 + (int)this.yo) / 32.0f).color(32, 32, 32, 255).endVertex();
            bufferbuilder.pos(this.x0, this.y0, 0.0).tex((float)this.x0 / 32.0f, (float)(this.y0 + (int)this.yo) / 32.0f).color(32, 32, 32, 255).endVertex();
            tessellator.J_1907_R();
            int k = this.x0 + this.width / 2 - this.getRowWidth() / 2 + 2;
            int l = this.y0 + 4 - (int)this.yo;
            if (this.renderHeader) {
                this.renderHeader(k, l, tessellator);
            }
            this.renderList(matrixStackIn, k, l, p_render_1_, p_render_2_, p_render_3_);
            c_4037_x.t_1786_h();
            this.renderHoleBackground(0, this.y0, 255, 255);
            this.renderHoleBackground(this.y1, this.height, 255, 255);
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
            tessellator.J_1907_R();
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(this.x0, this.y1, 0.0).tex(0.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1, 0.0).tex(1.0f, 1.0f).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(this.x1, this.y1 - 4, 0.0).tex(1.0f, 0.0f).color(0, 0, 0, 0).endVertex();
            bufferbuilder.pos(this.x0, this.y1 - 4, 0.0).tex(0.0f, 0.0f).color(0, 0, 0, 0).endVertex();
            tessellator.J_1907_R();
            int j1 = this.getMaxScroll();
            if (j1 > 0) {
                int k1 = (int)((float)((this.y1 - this.y0) * (this.y1 - this.y0)) / (float)this.getMaxPosition());
                int l1 = (int)this.yo * (this.y1 - this.y0 - (k1 = u_530_F.n_1700_B(k1, 32, this.y1 - this.y0 - 8))) / j1 + this.y0;
                if (l1 < this.y0) {
                    l1 = this.y0;
                }
                bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
                bufferbuilder.pos(i, this.y1, 0.0).tex(0.0f, 1.0f).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(j, this.y1, 0.0).tex(1.0f, 1.0f).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(j, this.y0, 0.0).tex(1.0f, 0.0f).color(0, 0, 0, 255).endVertex();
                bufferbuilder.pos(i, this.y0, 0.0).tex(0.0f, 0.0f).color(0, 0, 0, 255).endVertex();
                tessellator.J_1907_R();
                bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
                bufferbuilder.pos(i, l1 + k1, 0.0).tex(0.0f, 1.0f).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(j, l1 + k1, 0.0).tex(1.0f, 1.0f).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(j, l1, 0.0).tex(1.0f, 0.0f).color(128, 128, 128, 255).endVertex();
                bufferbuilder.pos(i, l1, 0.0).tex(0.0f, 0.0f).color(128, 128, 128, 255).endVertex();
                tessellator.J_1907_R();
                bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
                bufferbuilder.pos(i, l1 + k1 - 1, 0.0).tex(0.0f, 1.0f).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos(j - 1, l1 + k1 - 1, 0.0).tex(1.0f, 1.0f).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos(j - 1, l1, 0.0).tex(1.0f, 0.0f).color(192, 192, 192, 255).endVertex();
                bufferbuilder.pos(i, l1, 0.0).tex(0.0f, 0.0f).color(192, 192, 192, 255).endVertex();
                tessellator.J_1907_R();
            }
            this.renderDecorations(p_render_1_, p_render_2_);
            c_4037_x.x_607_J();
            c_4037_x.w_1484_f(7424);
            c_4037_x.M_588_G();
            c_4037_x.Y_259_p();
        }
    }

    protected void updateScrollingState(double p_updateScrollingState_1_, double p_updateScrollingState_3_, int p_updateScrollingState_5_) {
        this.scrolling = p_updateScrollingState_5_ == 0 && p_updateScrollingState_1_ >= (double)this.getScrollbarPosition() && p_updateScrollingState_1_ < (double)(this.getScrollbarPosition() + 6);
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        this.updateScrollingState(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
        if (this.isVisible() && this.isMouseInList(p_mouseClicked_1_, p_mouseClicked_3_)) {
            int i = this.getItemAtPosition(p_mouseClicked_1_, p_mouseClicked_3_);
            if (i == -1 && p_mouseClicked_5_ == 0) {
                this.clickedHeader((int)(p_mouseClicked_1_ - (double)(this.x0 + this.width / 2 - this.getRowWidth() / 2)), (int)(p_mouseClicked_3_ - (double)this.y0) + (int)this.yo - 4);
                return true;
            }
            if (i != -1 && this.selectItem(i, p_mouseClicked_5_, p_mouseClicked_1_, p_mouseClicked_3_)) {
                if (this.getEventListeners().size() > i) {
                    this.setListener(this.getEventListeners().get(i));
                }
                this.setDragging(true);
                return true;
            }
            return this.scrolling;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
        if (this.getListener() != null) {
            this.getListener().mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        if (super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_)) {
            return true;
        }
        if (this.isVisible() && p_mouseDragged_5_ == 0 && this.scrolling) {
            if (p_mouseDragged_3_ < (double)this.y0) {
                this.yo = 0.0;
            } else if (p_mouseDragged_3_ > (double)this.y1) {
                this.yo = this.getMaxScroll();
            } else {
                double d0 = this.getMaxScroll();
                if (d0 < 1.0) {
                    d0 = 1.0;
                }
                int i = (int)((float)((this.y1 - this.y0) * (this.y1 - this.y0)) / (float)this.getMaxPosition());
                double d1 = d0 / (double)(this.y1 - this.y0 - (i = u_530_F.n_1700_B(i, 32, this.y1 - this.y0 - 8)));
                if (d1 < 1.0) {
                    d1 = 1.0;
                }
                this.yo += p_mouseDragged_8_ * d1;
                this.capYPosition();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
        if (!this.isVisible()) {
            return false;
        }
        this.yo -= p_mouseScrolled_5_ * (double)this.itemHeight / 2.0;
        return true;
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        if (!this.isVisible()) {
            return false;
        }
        if (super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
            return true;
        }
        if (p_keyPressed_1_ == 264) {
            this.moveSelection(1);
            return true;
        }
        if (p_keyPressed_1_ == 265) {
            this.moveSelection(-1);
            return true;
        }
        return false;
    }

    protected void moveSelection(int p_moveSelection_1_) {
    }

    @Override
    public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
        return !this.isVisible() ? false : super.charTyped(p_charTyped_1_, p_charTyped_2_);
    }

    @Override
    public boolean isMouseOver(double p_isMouseOver_1_, double p_isMouseOver_3_) {
        return this.isMouseInList(p_isMouseOver_1_, p_isMouseOver_3_);
    }

    public int getRowWidth() {
        return 220;
    }

    protected void renderList(g_221_o matrixStackIn, int p_renderList_1_, int p_renderList_2_, int p_renderList_3_, int p_renderList_4_, float p_renderList_5_) {
        int i = this.getItemCount();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        for (int j = 0; j < i; ++j) {
            int k = p_renderList_2_ + j * this.itemHeight + this.headerHeight;
            int l = this.itemHeight - 4;
            if (k > this.y1 || k + l < this.y0) {
                this.updateItemPosition(j, p_renderList_1_, k, p_renderList_5_);
            }
            if (this.renderSelection && this.isSelectedItem(j)) {
                int i1 = this.x0 + this.width / 2 - this.getRowWidth() / 2;
                int j1 = this.x0 + this.width / 2 + this.getRowWidth() / 2;
                c_4037_x.e_4240_b();
                float f = this.isFocused() ? 1.0f : 0.5f;
                c_4037_x.G_564_y(f, f, f, 1.0f);
                bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
                bufferbuilder.pos(i1, k + l + 2, 0.0).endVertex();
                bufferbuilder.pos(j1, k + l + 2, 0.0).endVertex();
                bufferbuilder.pos(j1, k - 2, 0.0).endVertex();
                bufferbuilder.pos(i1, k - 2, 0.0).endVertex();
                tessellator.J_1907_R();
                c_4037_x.G_564_y(0.0f, 0.0f, 0.0f, 1.0f);
                bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
                bufferbuilder.pos(i1 + 1, k + l + 1, 0.0).endVertex();
                bufferbuilder.pos(j1 - 1, k + l + 1, 0.0).endVertex();
                bufferbuilder.pos(j1 - 1, k - 1, 0.0).endVertex();
                bufferbuilder.pos(i1 + 1, k - 1, 0.0).endVertex();
                tessellator.J_1907_R();
                c_4037_x.x_607_J();
            }
            if (k + this.itemHeight < this.y0 || k > this.y1) continue;
            this.renderItem(matrixStackIn, j, p_renderList_1_, k, l, p_renderList_3_, p_renderList_4_, p_renderList_5_);
        }
    }

    protected boolean isFocused() {
        return false;
    }

    protected int getScrollbarPosition() {
        return this.width / 2 + 124;
    }

    protected void renderHoleBackground(int p_renderHoleBackground_1_, int p_renderHoleBackground_2_, int p_renderHoleBackground_3_, int p_renderHoleBackground_4_) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        this.minecraft.G_624_v().n_1700_B(C_2701_A.BACKGROUND_LOCATION);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = 32.0f;
        bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
        bufferbuilder.pos(this.x0, p_renderHoleBackground_2_, 0.0).tex(0.0f, (float)p_renderHoleBackground_2_ / 32.0f).color(64, 64, 64, p_renderHoleBackground_4_).endVertex();
        bufferbuilder.pos(this.x0 + this.width, p_renderHoleBackground_2_, 0.0).tex((float)this.width / 32.0f, (float)p_renderHoleBackground_2_ / 32.0f).color(64, 64, 64, p_renderHoleBackground_4_).endVertex();
        bufferbuilder.pos(this.x0 + this.width, p_renderHoleBackground_1_, 0.0).tex((float)this.width / 32.0f, (float)p_renderHoleBackground_1_ / 32.0f).color(64, 64, 64, p_renderHoleBackground_3_).endVertex();
        bufferbuilder.pos(this.x0, p_renderHoleBackground_1_, 0.0).tex(0.0f, (float)p_renderHoleBackground_1_ / 32.0f).color(64, 64, 64, p_renderHoleBackground_3_).endVertex();
        tessellator.J_1907_R();
    }

    public void setLeftPos(int p_setLeftPos_1_) {
        this.x0 = p_setLeftPos_1_;
        this.x1 = p_setLeftPos_1_ + this.width;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }
}



