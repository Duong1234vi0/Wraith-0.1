/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_5250
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.common.repository.friend.Friend;
import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.datatypes.FriendDataType;
import code.alphadlc.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class FriendCommand
extends Command {
    protected FriendCommand() {
        super("friend");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String name;
        String arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
        args.requireMax(1);
        if (arg.contains("add")) {
            name = args.getString();
            if (!FriendUtils.isFriend(name)) {
                FriendUtils.addFriend(name);
                this.logDirect("\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0434\u043e\u0431\u0430\u0432\u0438\u043b\u0438 " + name + " \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439!");
            } else {
                this.logDirect(name + " \u0443\u0436\u0435 \u0435\u0441\u0442\u044c \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u0434\u0440\u0443\u0437\u0435\u0439!", class_124.field_1061);
            }
        }
        if (arg.contains("remove")) {
            name = args.getString();
            if (FriendUtils.isFriend(name)) {
                FriendUtils.removeFriend(name);
                this.logDirect("\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0443\u0434\u0430\u043b\u0438\u043b\u0438 " + name + " \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u0434\u0440\u0443\u0437\u0435\u0439!");
                return;
            }
            this.logDirect(name + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u0434\u0440\u0443\u0437\u0435\u0439", class_124.field_1061);
        }
        if (arg.contains("list")) {
            Paginator.paginate(args, new Paginator<Friend>(FriendUtils.getFriends()), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439:"), friend -> {
                String names = friend.getName();
                class_5250 namesComponent = class_2561.method_43470((String)names);
                namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                return namesComponent;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
        }
        if (arg.contains("clear")) {
            FriendUtils.clear();
            this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439 \u043e\u0447\u0438\u0449\u0435\u043d.");
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (args.hasExactlyOne()) {
                if (arg.equalsIgnoreCase("add")) {
                    return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
                }
                if (arg.equalsIgnoreCase("remove")) {
                    return args.tabCompleteDatatype(FriendDataType.INSTANCE);
                }
            } else {
                return new TabCompleteHelper().sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(arg).stream();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0443\u043f\u0440\u0430\u0432\u043b\u044f\u0442\u044c \u0441\u043f\u0438\u0441\u043a\u043e\u043c \u0434\u0440\u0443\u0437\u0435\u0439";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0421 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u044d\u0442\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043c\u043e\u0436\u043d\u043e \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u0442\u044c/\u0443\u0434\u0430\u043b\u044f\u0442\u044c \u0434\u0440\u0443\u0437\u0435\u0439 \u0432 \u0447\u0438\u0442\u0435", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> friend add <name> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0438\u043c\u044f \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439.", "> friend remove <name> - \u0423\u0434\u0430\u043b\u044f\u0435\u0442 \u0438\u043c\u044f \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u0434\u0440\u0443\u0437\u0435\u0439.", "> friend list - \u0412\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439", "> friend clear - \u041e\u0447\u0438\u0449\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439.");
    }
}
