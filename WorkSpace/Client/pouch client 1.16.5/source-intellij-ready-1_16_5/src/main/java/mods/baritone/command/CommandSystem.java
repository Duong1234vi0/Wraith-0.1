/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command;

import mods.baritone.api.api.java.baritone.api.command.ICommandSystem;
import mods.baritone.api.api.java.baritone.api.command.argparser.IArgParserManager;
import mods.baritone.command.argparser.ArgParserManager;

public enum CommandSystem implements ICommandSystem
{
    INSTANCE;


    @Override
    public IArgParserManager getParserManager() {
        return ArgParserManager.INSTANCE;
    }
}

