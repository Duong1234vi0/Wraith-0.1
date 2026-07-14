/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;

public interface IDatatypeContext {
    public IBaritone getBaritone();

    public IArgConsumer getConsumer();
}

