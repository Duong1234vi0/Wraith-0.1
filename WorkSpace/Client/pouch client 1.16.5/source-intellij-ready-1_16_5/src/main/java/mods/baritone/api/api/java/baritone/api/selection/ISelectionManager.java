/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.selection;

import lightning.product.b_257_Y;
import mods.baritone.api.api.java.baritone.api.selection.ISelection;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public interface ISelectionManager {
    public ISelection addSelection(ISelection var1);

    public ISelection addSelection(BetterBlockPos var1, BetterBlockPos var2);

    public ISelection removeSelection(ISelection var1);

    public ISelection[] removeAllSelections();

    public ISelection[] getSelections();

    public ISelection getOnlySelection();

    public ISelection getLastSelection();

    public ISelection expand(ISelection var1, b_257_Y var2, int var3);

    public ISelection contract(ISelection var1, b_257_Y var2, int var3);

    public ISelection shift(ISelection var1, b_257_Y var2, int var3);
}

