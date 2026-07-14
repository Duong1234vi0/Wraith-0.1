/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lightning.product.D_4024_W;
import lightning.product.SharedConstants;
import lightning.product.MinecraftClient;
import lightning.product.f_1703_u;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;

public class w_2043_E {
    private final Supplier<String> n_1700_B;
    private final Consumer<String> J_1907_R;
    private final Supplier<String> R_4764_Y;
    private final Consumer<String> G_564_y;
    private final Predicate<String> P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;

    public w_2043_E(Supplier<String> textSupplier, Consumer<String> textConsumer, Supplier<String> clipboardSupplier, Consumer<String> clipboardConsumer, Predicate<String> textLimiter) {
        this.n_1700_B = textSupplier;
        this.J_1907_R = textConsumer;
        this.R_4764_Y = clipboardSupplier;
        this.G_564_y = clipboardConsumer;
        this.P_1922_E = textLimiter;
        this.P_1922_E();
    }

    public static Supplier<String> n_1700_B(MinecraftClient minecraft) {
        return () -> w_2043_E.J_1907_R(minecraft);
    }

    public static String J_1907_R(MinecraftClient minecraft) {
        return D_4024_W.n_1700_B(minecraft.Q_4569_t.n_1700_B().replaceAll("\\r", ""));
    }

    public static Consumer<String> R_4764_Y(MinecraftClient minecraft) {
        return text -> w_2043_E.n_1700_B(minecraft, text);
    }

    public static void n_1700_B(MinecraftClient minecraft, String text) {
        minecraft.Q_4569_t.n_1700_B(text);
    }

    public boolean n_1700_B(char character) {
        if (SharedConstants.n_1700_B(character)) {
            this.n_1700_B(this.n_1700_B.get(), Character.toString(character));
        }
        return true;
    }

    public boolean n_1700_B(int key) {
        if (k_2603_m.isSelectAll(key)) {
            this.G_564_y();
            return true;
        }
        if (k_2603_m.isCopy(key)) {
            this.R_4764_Y();
            return true;
        }
        if (k_2603_m.isPaste(key)) {
            this.J_1907_R();
            return true;
        }
        if (k_2603_m.isCut(key)) {
            this.n_1700_B();
            return true;
        }
        if (key == 259) {
            this.J_1907_R(-1);
            return true;
        }
        if (key == 261) {
            this.J_1907_R(1);
        } else {
            if (key == 263) {
                if (k_2603_m.hasControlDown()) {
                    this.J_1907_R(-1, k_2603_m.hasShiftDown());
                } else {
                    this.n_1700_B(-1, k_2603_m.hasShiftDown());
                }
                return true;
            }
            if (key == 262) {
                if (k_2603_m.hasControlDown()) {
                    this.J_1907_R(1, k_2603_m.hasShiftDown());
                } else {
                    this.n_1700_B(1, k_2603_m.hasShiftDown());
                }
                return true;
            }
            if (key == 268) {
                this.J_1907_R(k_2603_m.hasShiftDown());
                return true;
            }
            if (key == 269) {
                this.R_4764_Y(k_2603_m.hasShiftDown());
                return true;
            }
        }
        return false;
    }

    private int R_4764_Y(int textIndex) {
        return u_530_F.n_1700_B(textIndex, 0, this.n_1700_B.get().length());
    }

    private void n_1700_B(String text, String clipboardText) {
        if (this.v_4262_N != this.u_1723_Y) {
            text = this.R_4764_Y(text);
        }
        this.u_1723_Y = u_530_F.n_1700_B(this.u_1723_Y, 0, text.length());
        String s = new StringBuilder(text).insert(this.u_1723_Y, clipboardText).toString();
        if (this.P_1922_E.test(s)) {
            this.J_1907_R.accept(s);
            this.v_4262_N = this.u_1723_Y = Math.min(s.length(), this.u_1723_Y + clipboardText.length());
        }
    }

    public void n_1700_B(String text) {
        this.n_1700_B(this.n_1700_B.get(), text);
    }

    private void n_1700_B(boolean keepSelection) {
        if (!keepSelection) {
            this.v_4262_N = this.u_1723_Y;
        }
    }

    public void n_1700_B(int direction, boolean keepSelection) {
        this.u_1723_Y = j_3341_s.n_1700_B(this.n_1700_B.get(), this.u_1723_Y, direction);
        this.n_1700_B(keepSelection);
    }

    public void J_1907_R(int direction, boolean keepSelection) {
        this.u_1723_Y = f_1703_u.n_1700_B(this.n_1700_B.get(), direction, this.u_1723_Y, true);
        this.n_1700_B(keepSelection);
    }

    public void J_1907_R(int bidiDirection) {
        String s = this.n_1700_B.get();
        if (!s.isEmpty()) {
            String s1;
            if (this.v_4262_N != this.u_1723_Y) {
                s1 = this.R_4764_Y(s);
            } else {
                int i = j_3341_s.n_1700_B(s, this.u_1723_Y, bidiDirection);
                int j = Math.min(i, this.u_1723_Y);
                int k = Math.max(i, this.u_1723_Y);
                s1 = new StringBuilder(s).delete(j, k).toString();
                if (bidiDirection < 0) {
                    this.v_4262_N = this.u_1723_Y = j;
                }
            }
            this.J_1907_R.accept(s1);
        }
    }

    public void n_1700_B() {
        String s = this.n_1700_B.get();
        this.G_564_y.accept(this.J_1907_R(s));
        this.J_1907_R.accept(this.R_4764_Y(s));
    }

    public void J_1907_R() {
        this.n_1700_B(this.n_1700_B.get(), this.R_4764_Y.get());
        this.v_4262_N = this.u_1723_Y;
    }

    public void R_4764_Y() {
        this.G_564_y.accept(this.J_1907_R(this.n_1700_B.get()));
    }

    public void G_564_y() {
        this.v_4262_N = 0;
        this.u_1723_Y = this.n_1700_B.get().length();
    }

    private String J_1907_R(String text) {
        int i = Math.min(this.u_1723_Y, this.v_4262_N);
        int j = Math.max(this.u_1723_Y, this.v_4262_N);
        return text.substring(i, j);
    }

    private String R_4764_Y(String text) {
        if (this.v_4262_N == this.u_1723_Y) {
            return text;
        }
        int i = Math.min(this.u_1723_Y, this.v_4262_N);
        int j = Math.max(this.u_1723_Y, this.v_4262_N);
        String s = text.substring(0, i) + text.substring(j);
        this.v_4262_N = this.u_1723_Y = i;
        return s;
    }

    private void J_1907_R(boolean keepSelection) {
        this.u_1723_Y = 0;
        this.n_1700_B(keepSelection);
    }

    public void P_1922_E() {
        this.R_4764_Y(false);
    }

    private void R_4764_Y(boolean keepSelection) {
        this.u_1723_Y = this.n_1700_B.get().length();
        this.n_1700_B(keepSelection);
    }

    public int u_1723_Y() {
        return this.u_1723_Y;
    }

    public void R_4764_Y(int textIndex, boolean keepSelection) {
        this.u_1723_Y = this.R_4764_Y(textIndex);
        this.n_1700_B(keepSelection);
    }

    public int v_4262_N() {
        return this.v_4262_N;
    }

    public void n_1700_B(int selectionStart, int selectionEnd) {
        int i = this.n_1700_B.get().length();
        this.u_1723_Y = u_530_F.n_1700_B(selectionStart, 0, i);
        this.v_4262_N = u_530_F.n_1700_B(selectionEnd, 0, i);
    }

    public boolean w_1484_f() {
        return this.u_1723_Y != this.v_4262_N;
    }
}



