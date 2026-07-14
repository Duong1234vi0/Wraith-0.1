/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package mods.voicechat.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import lightning.product.y_2498_m;
import mods.voicechat.Voicechat;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.Server;

public class GroupNameSuggestionProvider
implements SuggestionProvider<y_2498_m> {
    public static final GroupNameSuggestionProvider INSTANCE = new GroupNameSuggestionProvider();

    public CompletableFuture<Suggestions> getSuggestions(CommandContext<y_2498_m> context, SuggestionsBuilder builder) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return builder.buildFuture();
        }
        server.getGroupManager().getGroups().values().stream().map(Group::getName).distinct().map(s -> {
            if (s.contains(" ")) {
                return String.format("\"%s\"", s);
            }
            return s;
        }).forEach(arg_0 -> ((SuggestionsBuilder)builder).suggest(arg_0));
        return builder.buildFuture();
    }
}

