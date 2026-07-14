/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import mods.baritone.api.api.java.baritone.api.behavior.IBehavior;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;

public interface IInputOverrideHandler
extends IBehavior {
    public boolean isInputForcedDown(Input var1);

    public void setInputForceState(Input var1, boolean var2);

    public void clearAllKeys();
}

