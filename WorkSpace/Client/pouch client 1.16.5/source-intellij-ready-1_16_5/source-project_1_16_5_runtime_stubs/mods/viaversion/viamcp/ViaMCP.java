package mods.viaversion.viamcp;

import lightning.product.c_1404_X;
import lightning.product.x_282_a;
import mods.viaversion.viamcp.gui.VersionSelectScreen;

public class ViaMCP {
    public static final int NATIVE_VERSION = 754;
    public static ViaMCP INSTANCE;
    private final VersionSelectScreen versionSelectScreen;

    public ViaMCP() {
        this.versionSelectScreen = new VersionSelectScreen(c_1404_X.A_4115_X().t_148_a, 5, 5, 75, 20, x_282_a.J_1907_R("1.16.5"));
    }

    public static void create() {
        INSTANCE = new ViaMCP();
    }

    public VersionSelectScreen getVersionSelectScreen() {
        return this.versionSelectScreen;
    }
}
