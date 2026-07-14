/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.ModuleCategory;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public class BaritoneSettings
extends Module
implements MinecraftAccess {
    public static final BooleanSetting ignoreScreensEnabled = new BooleanSetting("Ignore Screens", false);
    public static final BooleanSetting ignorePauseEnabled = new BooleanSetting("Ignore Pause", false);
    private final BooleanSetting autoEatEnabled = new BooleanSetting("Auto Eat", false);
    private final NumberSetting minFoodSetting = new NumberSetting("Min Food", 10.0f, 1.0f, 20.0f, 1.0f, this.autoEatEnabled::isEnabled);

    public BaritoneSettings() {
        super("BaritoneSettings", ModuleCategory.G_564_y);
        BaritoneAPI.getSettings().chunkCaching.value = false;
        BaritoneAPI.getSettings().pruneRegionsFromRAM.value = true;
        BaritoneAPI.getSettings().cachedChunksExpirySeconds.value = 1L;
        this.addSettings(ignoreScreensEnabled, ignorePauseEnabled, this.autoEatEnabled, this.minFoodSetting);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (BaritoneSettings.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.autoEatEnabled.isEnabled().booleanValue() && (float)BaritoneSettings.c_3005_b.Y_259_p.P_2295_B().n_1700_B() <= ((Float)this.minFoodSetting.getValue()).floatValue()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        Z_1993_T offhand = BaritoneSettings.c_3005_b.Y_259_p.S_4035_N();
        boolean hasFood = offhand.J_1907_R().Y_259_p();
        if (!hasFood) {
            for (int i = 0; i < 36; ++i) {
                Z_1993_T stack = BaritoneSettings.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!stack.J_1907_R().Y_259_p() || stack.J_1907_R() == Items.m_1964_F || stack.J_1907_R() == Items.r_2687_x) continue;
                int slot = i < 9 ? i + 36 : i;
                BaritoneSettings.c_3005_b.w_1457_N.windowClick(0, slot, 0, a_408_T.n_1700_B, BaritoneSettings.c_3005_b.Y_259_p);
                BaritoneSettings.c_3005_b.w_1457_N.windowClick(0, 45, 0, a_408_T.n_1700_B, BaritoneSettings.c_3005_b.Y_259_p);
                Z_1993_T cursor = BaritoneSettings.c_3005_b.Y_259_p.l_1268_F.s_956_w();
                if (!cursor.n_1700_B()) {
                    BaritoneSettings.c_3005_b.w_1457_N.windowClick(0, slot, 0, a_408_T.n_1700_B, BaritoneSettings.c_3005_b.Y_259_p);
                }
                break;
            }
        } else if (!BaritoneSettings.c_3005_b.Y_259_p.Y_601_j()) {
            BaritoneSettings.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        BaritoneSettings.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
    }
}



