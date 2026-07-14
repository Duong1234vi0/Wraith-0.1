/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;

public final class TextColor {
    private static final Map<D_4024_W, TextColor> n_1700_B = (Map)Stream.of(D_4024_W.values()).filter(D_4024_W::R_4764_Y).collect(ImmutableMap.toImmutableMap(Function.identity(), formatting -> new TextColor(formatting.G_564_y(), formatting.P_1922_E())));
    private static final Map<String, TextColor> J_1907_R = (Map)n_1700_B.values().stream().collect(ImmutableMap.toImmutableMap(color -> color.G_564_y, Function.identity()));
    private final int R_4764_Y;
    @Nullable
    private final String G_564_y;

    private TextColor(int color, String name) {
        this.R_4764_Y = color;
        this.G_564_y = name;
    }

    public TextColor(int color) {
        this.R_4764_Y = color;
        this.G_564_y = null;
    }

    public int n_1700_B() {
        return this.R_4764_Y;
    }

    public String J_1907_R() {
        return this.G_564_y != null ? this.G_564_y : this.R_4764_Y();
    }

    private String R_4764_Y() {
        return String.format("#%06X", this.R_4764_Y);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            TextColor color = (TextColor)p_equals_1_;
            return this.R_4764_Y == color.R_4764_Y;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.R_4764_Y, this.G_564_y);
    }

    public String toString() {
        return this.G_564_y != null ? this.G_564_y : this.R_4764_Y();
    }

    @Nullable
    public static TextColor n_1700_B(D_4024_W formatting) {
        return n_1700_B.get((Object)formatting);
    }

    public static TextColor n_1700_B(int color) {
        return new TextColor(color);
    }

    @Nullable
    public static TextColor n_1700_B(String hexString) {
        if (hexString.startsWith("#")) {
            try {
                int i = Integer.parseInt(hexString.substring(1), 16);
                return TextColor.n_1700_B(i);
            }
            catch (NumberFormatException numberformatexception) {
                return null;
            }
        }
        return J_1907_R.get(hexString);
    }
}


