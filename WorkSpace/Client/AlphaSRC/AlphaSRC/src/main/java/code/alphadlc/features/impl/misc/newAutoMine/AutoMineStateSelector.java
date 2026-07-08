/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc.newAutoMine;

import code.alphadlc.features.impl.misc.newAutoMine.AutoMineController;
import code.alphadlc.features.impl.misc.newAutoMine.AutoMineState;
import code.alphadlc.features.impl.misc.newAutoMine.ConnectProvider;
import code.alphadlc.features.impl.misc.newAutoMine.RegionState;
import code.alphadlc.features.impl.misc.newAutoMine.api.MineParser;
import code.alphadlc.utils.display.interfaces.QuickImports;

public class AutoMineStateSelector
implements QuickImports {
    private final AutoMineController autoMineController;

    public AutoMineStateSelector(AutoMineController autoMineController) {
        this.autoMineController = autoMineController;
    }

    public AutoMineState selectState() {
        RegionState regionState = this.autoMineController.getRegionState();
        MineParser mineParser = this.autoMineController.getMineParser();
        ConnectProvider connectProvider = this.autoMineController.getConnectProvider();
        switch (this.autoMineController.getAutoMineState()) {
            default: 
        }
        return AutoMineState.ON_MINING;
    }
}
