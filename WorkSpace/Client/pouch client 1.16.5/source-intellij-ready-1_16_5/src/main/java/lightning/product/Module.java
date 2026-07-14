/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  lombok.Generated
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lightning.product.A_4115_X;
import lightning.product.D_1410_T;
import lightning.product.ToggleSounds;
import lightning.product.U_3758_B;
import lightning.product.MinecraftAccess;
import lightning.product.Setting;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.p_863_D;
import lightning.product.ModuleCategory;
import lombok.Generated;

public abstract class Module
implements MinecraftAccess {
    private static boolean v_4262_N = false;
    String n_1700_B;
    String J_1907_R;
    ModuleCategory R_4764_Y;
    int G_564_y;
    boolean P_1922_E;
    private n_1700_B w_1484_f = lightning.product.Module$n_1700_B.n_1700_B;
    private boolean t_148_a = true;
    List<Setting<?>> u_1723_Y = new ObjectArrayList();
    private final Animation s_956_w = new Animation(0.0f, 8.0f, Easing.u_1723_Y);

    public Module(String name, ModuleCategory category) {
        this(name, "module." + Character.toLowerCase(name.charAt(0)) + name.substring(1) + ".desc", category);
    }

    public Module(String name, String descriptionKey, ModuleCategory category) {
        this.n_1700_B = name;
        this.J_1907_R = descriptionKey;
        this.R_4764_Y = category;
        this.G_564_y = -100;
    }

    public void addSettings(Setting<?> ... settings) {
        this.u_1723_Y.addAll(List.of(settings));
    }

    public void onEnable() {
        this.s_956_w.n_1700_B(1.0f);
        if (!v_4262_N && this.t_148_a) {
            p_863_D.n_1700_B(ToggleSounds.P_1922_E(true));
            if (D_1410_T.G_564_y.t_148_a().booleanValue()) {
                U_3758_B.n_1700_B("J", "\u00ab" + this.n_1700_B + "\u00bb \u0432\u043a\u043b\u044e\u0447\u0435\u043d!", -1);
            }
        }
        A_4115_X.n_1700_B(this);
    }

    public void onDisable() {
        this.s_956_w.n_1700_B(0.0f);
        if (!v_4262_N && this.t_148_a) {
            p_863_D.n_1700_B(ToggleSounds.P_1922_E(false));
            if (D_1410_T.G_564_y.t_148_a().booleanValue()) {
                U_3758_B.n_1700_B("K", "\u00ab" + this.n_1700_B + "\u00bb \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d!", -1);
            }
        }
        A_4115_X.J_1907_R(this);
    }

    public final void R_4764_Y() {
        this.setEnabled(!this.P_1922_E);
    }

    public final void setEnabled(boolean toggle) {
        if (this.P_1922_E == toggle) {
            return;
        }
        this.P_1922_E = toggle;
        if (this.P_1922_E) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Generated
    public String getName() {
        return this.n_1700_B;
    }

    @Generated
    public String getDescriptionKey() {
        return this.J_1907_R;
    }

    @Generated
    public ModuleCategory getCategory() {
        return this.R_4764_Y;
    }

    @Generated
    public int getKeyBind() {
        return this.G_564_y;
    }

    @Generated
    public boolean isEnabled() {
        return this.P_1922_E;
    }

    @Generated
    public n_1700_B t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public boolean s_956_w() {
        return this.t_148_a;
    }

    @Generated
    public List<Setting<?>> getSettings() {
        return this.u_1723_Y;
    }

    @Generated
    public Animation getToggleAnimation() {
        return this.s_956_w;
    }

    @Generated
    public void n_1700_B(String name) {
        this.n_1700_B = name;
    }

    @Generated
    public void J_1907_R(String description) {
        this.J_1907_R = description;
    }

    @Generated
    public void n_1700_B(ModuleCategory category) {
        this.R_4764_Y = category;
    }

    @Generated
    public void n_1700_B(int bind) {
        this.G_564_y = bind;
    }

    @Generated
    public void J_1907_R(boolean enabled) {
        this.P_1922_E = enabled;
    }

    @Generated
    public void n_1700_B(n_1700_B toggleMode) {
        this.w_1484_f = toggleMode;
    }

    @Generated
    public void R_4764_Y(boolean keybindvisible) {
        this.t_148_a = keybindvisible;
    }

    @Generated
    public void n_1700_B(List<Setting<?>> settings) {
        this.u_1723_Y = settings;
    }

    @Generated
    public static boolean P_4830_p() {
        return v_4262_N;
    }

    @Generated
    public static void G_564_y(boolean suppressToggleEffects) {
        v_4262_N = suppressToggleEffects;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.Module$n_1700_B.n_1700_B();
        }
    }
}


