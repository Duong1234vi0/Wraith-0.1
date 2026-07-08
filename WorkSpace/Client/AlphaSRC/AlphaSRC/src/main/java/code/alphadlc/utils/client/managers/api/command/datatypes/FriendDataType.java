/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.common.repository.friend.Friend;
import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum FriendDataType implements IDatatypeFor<Friend>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext datatypeContext) throws CommandException {
        Stream<String> friends = this.getFriends().stream().map(Friend::getName);
        String context = datatypeContext.getConsumer().getString();
        return new TabCompleteHelper().append(friends).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public Friend get(IDatatypeContext datatypeContext) throws CommandException {
        String username = datatypeContext.getConsumer().getString();
        return this.getFriends().stream().filter(s -> s.getName().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    private List<? extends Friend> getFriends() {
        return FriendUtils.getFriends();
    }
}
