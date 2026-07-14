/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.argparser;

import mods.baritone.api.api.java.baritone.api.command.argparser.IArgParser;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidTypeException;
import mods.baritone.api.api.java.baritone.api.command.registry.Registry;

public interface IArgParserManager {
    public <T> IArgParser.Stateless<T> getParserStateless(Class<T> var1);

    public <T, S> IArgParser.Stated<T, S> getParserStated(Class<T> var1, Class<S> var2);

    public <T> T parseStateless(Class<T> var1, ICommandArgument var2) throws CommandInvalidTypeException;

    public <T, S> T parseStated(Class<T> var1, Class<S> var2, ICommandArgument var3, S var4) throws CommandInvalidTypeException;

    public Registry<IArgParser> getRegistry();
}

