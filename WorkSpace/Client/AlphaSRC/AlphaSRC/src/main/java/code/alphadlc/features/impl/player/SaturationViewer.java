/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;

public class SaturationViewer
extends Module {
    public static SaturationViewer getInstance() {
        return Instance.get(SaturationViewer.class);
    }

    public SaturationViewer() {
        super("SaturationViewer", ModuleCategory.PLAYER);
    }
}
