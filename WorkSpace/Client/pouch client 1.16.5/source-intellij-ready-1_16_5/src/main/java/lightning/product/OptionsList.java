/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.GuiEventListener;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.MinecraftClient;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;

public class OptionsList
extends ContainerObjectSelectionList<n_1700_B> {
    public OptionsList(MinecraftClient p_i51130_1_, int p_i51130_2_, int p_i51130_3_, int p_i51130_4_, int p_i51130_5_, int p_i51130_6_) {
        super(p_i51130_1_, p_i51130_2_, p_i51130_3_, p_i51130_4_, p_i51130_5_, p_i51130_6_);
        this.centerListVertically = false;
    }

    public int n_1700_B(M_2935_g p_214333_1_) {
        return this.addEntry(n_1700_B.n_1700_B(this.minecraft.P_4830_p, this.width, p_214333_1_));
    }

    public void n_1700_B(M_2935_g p_214334_1_, @Nullable M_2935_g p_214334_2_) {
        this.addEntry(n_1700_B.n_1700_B(this.minecraft.P_4830_p, this.width, p_214334_1_, p_214334_2_));
    }

    public void n_1700_B(M_2935_g[] p_214335_1_) {
        for (int i = 0; i < p_214335_1_.length; i += 2) {
            this.n_1700_B(p_214335_1_[i], i < p_214335_1_.length - 1 ? p_214335_1_[i + 1] : null);
        }
    }

    @Override
    public int getRowWidth() {
        return 400;
    }

    @Override
    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 32;
    }

    @Nullable
    public V_2511_L J_1907_R(M_2935_g p_243271_1_) {
        for (n_1700_B optionsrowlist$row : this.getEventListeners()) {
            for (V_2511_L widget : optionsrowlist$row.n_1700_B) {
                if (!(widget instanceof Y_4729_x) || ((Y_4729_x)widget).J_1907_R() != p_243271_1_) continue;
                return widget;
            }
        }
        return null;
    }

    public Optional<V_2511_L> R_4764_Y(double p_238518_1_, double p_238518_3_) {
        for (n_1700_B optionsrowlist$row : this.getEventListeners()) {
            for (V_2511_L widget : optionsrowlist$row.n_1700_B) {
                if (!widget.isMouseOver(p_238518_1_, p_238518_3_)) continue;
                return Optional.of(widget);
            }
        }
        return Optional.empty();
    }

    public static class n_1700_B
    extends ContainerObjectSelectionList.n_1700_B<n_1700_B> {
        private final List<V_2511_L> n_1700_B;

        private n_1700_B(List<V_2511_L> widgetsIn) {
            this.n_1700_B = widgetsIn;
        }

        public static n_1700_B n_1700_B(V_4423_d settings, int guiWidth, M_2935_g option) {
            return new n_1700_B((List<V_2511_L>)ImmutableList.of((Object)option.createWidget(settings, guiWidth / 2 - 155, 0, 310)));
        }

        public static n_1700_B n_1700_B(V_4423_d settings, int guiWidth, M_2935_g leftOption, @Nullable M_2935_g rightOption) {
            V_2511_L widget = leftOption.createWidget(settings, guiWidth / 2 - 155, 0, 150);
            return rightOption == null ? new n_1700_B((List<V_2511_L>)ImmutableList.of((Object)widget)) : new n_1700_B((List<V_2511_L>)ImmutableList.of((Object)widget, (Object)rightOption.createWidget(settings, guiWidth / 2 - 155 + 160, 0, 150)));
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B.forEach(p_238519_5_ -> {
                p_238519_5_.y = p_230432_3_;
                p_238519_5_.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
            });
        }

        @Override
        public List<? extends GuiEventListener> getEventListeners() {
            return this.n_1700_B;
        }
    }
}



