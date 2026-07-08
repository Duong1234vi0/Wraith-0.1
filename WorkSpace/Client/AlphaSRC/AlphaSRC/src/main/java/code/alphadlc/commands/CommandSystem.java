/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.commands;

import code.alphadlc.commands.argparser.ArgParserManager;
import code.alphadlc.utils.client.managers.api.command.ICommandSystem;
import code.alphadlc.utils.client.managers.api.command.argparser.IArgParserManager;

public enum CommandSystem implements ICommandSystem
{
    INSTANCE;


    @Override
    public IArgParserManager getParserManager() {
        return ArgParserManager.INSTANCE;
    }
}
