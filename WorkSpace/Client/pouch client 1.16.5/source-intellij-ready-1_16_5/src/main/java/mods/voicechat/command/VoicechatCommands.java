/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.tree.CommandNode
 *  javax.annotation.Nullable
 */
package mods.voicechat.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.CommandNode;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.i_4556_r;
import lightning.product.j_3341_s;
import lightning.product.UuidArgument;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import mods.voicechat.Voicechat;
import mods.voicechat.command.GroupNameSuggestionProvider;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.permission.Permission;
import mods.voicechat.permission.PermissionManager;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.server.ClientConnection;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.PingManager;
import mods.voicechat.voice.server.Server;

public class VoicechatCommands {
    public static final String VOICECHAT_COMMAND = "voicechat";

    public static void register(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder<y_2498_m> literalBuilder = Q_2241_p.n_1700_B(VOICECHAT_COMMAND);
        literalBuilder.executes(commandSource -> VoicechatCommands.help(dispatcher, (CommandContext<y_2498_m>)commandSource));
        literalBuilder.then(Q_2241_p.n_1700_B("help").executes(commandSource -> VoicechatCommands.help(dispatcher, (CommandContext<y_2498_m>)commandSource)));
        literalBuilder.then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("test").requires(commandSource -> VoicechatCommands.checkPermission(commandSource, PermissionManager.INSTANCE.ADMIN_PERMISSION))).then(Q_2241_p.n_1700_B("target", i_4556_r.R_4764_Y()).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            B_4088_l player = i_4556_r.P_1922_E((CommandContext<y_2498_m>)commandSource, "target");
            Server server = Voicechat.SERVER.getServer();
            if (server == null) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.voice_chat_unavailable"), false);
                return 1;
            }
            if (!Voicechat.SERVER.isCompatible(player)) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.player_no_voicechat", player.c_(), CommonCompatibilityManager.INSTANCE.getModName()), false);
                return 1;
            }
            ClientConnection clientConnection = server.getConnections().get(player.w_2705_t());
            if (clientConnection == null) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.client_not_connected"), false);
                return 1;
            }
            try {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.sending_ping"), false);
                server.getPingManager().sendPing(clientConnection, 500L, 10, new PingManager.PingListener(){

                    @Override
                    public void onPong(int attempts, long pingMilliseconds) {
                        if (attempts <= 1) {
                            ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.ping_received", pingMilliseconds), false);
                        } else {
                            ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.ping_received_attempt", pingMilliseconds, attempts), false);
                        }
                    }

                    @Override
                    public void onFailedAttempt(int attempts) {
                        ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.ping_retry"), false);
                    }

                    @Override
                    public void onTimeout(int attempts) {
                        ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.ping_timed_out", attempts), false);
                    }
                });
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.ping_sent_waiting"), false);
            }
            catch (Exception e) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.failed_to_send_ping", e.getMessage()), false);
                Voicechat.LOGGER.warn("Failed to send ping", e);
                return 1;
            }
            return 1;
        })));
        literalBuilder.then(Q_2241_p.n_1700_B("invite").then(Q_2241_p.n_1700_B("target", i_4556_r.R_4764_Y()).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            B_4088_l source = ((y_2498_m)commandSource.getSource()).t_1786_h();
            Server server = Voicechat.SERVER.getServer();
            if (server == null) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.voice_chat_unavailable"), false);
                return 1;
            }
            PlayerState state = server.getPlayerStateManager().getState(source.w_2705_t());
            if (state == null || !state.hasGroup()) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.not_in_group"), false);
                return 1;
            }
            B_4088_l player = i_4556_r.P_1922_E((CommandContext<y_2498_m>)commandSource, "target");
            Group group = server.getGroupManager().getGroup(state.getGroup());
            if (group == null) {
                return 1;
            }
            if (!Voicechat.SERVER.isCompatible(player)) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.player_no_voicechat", player.c_(), CommonCompatibilityManager.INSTANCE.getModName()), false);
                return 1;
            }
            String passwordSuffix = group.getPassword() == null ? "" : " \"" + group.getPassword() + "\"";
            player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.invite", source.c_(), new U_2871_b(group.getName()).n_1700_B(D_4024_W.w_1484_f), ComponentUtils.n_1700_B(new F_2904_S("message.voicechat.accept_invite").n_1700_B(style -> style.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, "/voicechat join " + group.getId().toString() + passwordSuffix)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new F_2904_S("message.voicechat.accept_invite.hover"))))).n_1700_B(D_4024_W.u_2550_I)), j_3341_s.J_1907_R);
            ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.invite_successful", player.c_()), false);
            return 1;
        })));
        literalBuilder.then(Q_2241_p.n_1700_B("join").then(Q_2241_p.n_1700_B("group_id", UuidArgument.n_1700_B()).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            UUID groupID = UuidArgument.n_1700_B((CommandContext<y_2498_m>)commandSource, "group_id");
            return VoicechatCommands.joinGroupById((y_2498_m)commandSource.getSource(), groupID, null);
        })));
        literalBuilder.then(Q_2241_p.n_1700_B("join").then(Q_2241_p.n_1700_B("group_id", UuidArgument.n_1700_B()).then(Q_2241_p.n_1700_B("password", StringArgumentType.string()).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            UUID groupID = UuidArgument.n_1700_B((CommandContext<y_2498_m>)commandSource, "group_id");
            String password = StringArgumentType.getString((CommandContext)commandSource, (String)"password");
            return VoicechatCommands.joinGroupById((y_2498_m)commandSource.getSource(), groupID, password.isEmpty() ? null : password);
        }))));
        literalBuilder.then(Q_2241_p.n_1700_B("join").then(Q_2241_p.n_1700_B("group_name", StringArgumentType.string()).suggests((SuggestionProvider)GroupNameSuggestionProvider.INSTANCE).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            String groupName = StringArgumentType.getString((CommandContext)commandSource, (String)"group_name");
            return VoicechatCommands.joinGroupByName((y_2498_m)commandSource.getSource(), groupName, null);
        })));
        literalBuilder.then(Q_2241_p.n_1700_B("join").then(Q_2241_p.n_1700_B("group_name", StringArgumentType.string()).suggests((SuggestionProvider)GroupNameSuggestionProvider.INSTANCE).then(Q_2241_p.n_1700_B("password", StringArgumentType.string()).executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            String groupName = StringArgumentType.getString((CommandContext)commandSource, (String)"group_name");
            String password = StringArgumentType.getString((CommandContext)commandSource, (String)"password");
            return VoicechatCommands.joinGroupByName((y_2498_m)commandSource.getSource(), groupName, password.isEmpty() ? null : password);
        }))));
        literalBuilder.then(Q_2241_p.n_1700_B("leave").executes(commandSource -> {
            if (VoicechatCommands.checkNoVoicechat((CommandContext<y_2498_m>)commandSource)) {
                return 0;
            }
            if (!((Boolean)Voicechat.SERVER_CONFIG.groupsEnabled.get()).booleanValue()) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.groups_disabled"));
                return 1;
            }
            Server server = Voicechat.SERVER.getServer();
            if (server == null) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.voice_chat_unavailable"), false);
                return 1;
            }
            B_4088_l source = ((y_2498_m)commandSource.getSource()).t_1786_h();
            PlayerState state = server.getPlayerStateManager().getState(source.w_2705_t());
            if (state == null || !state.hasGroup()) {
                ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.not_in_group"), false);
                return 1;
            }
            server.getGroupManager().leaveGroup(source);
            ((y_2498_m)commandSource.getSource()).n_1700_B(new F_2904_S("message.voicechat.leave_successful"), false);
            return 1;
        }));
        dispatcher.register(literalBuilder);
    }

    private static Server joinGroup(y_2498_m source) throws CommandSyntaxException {
        if (!((Boolean)Voicechat.SERVER_CONFIG.groupsEnabled.get()).booleanValue()) {
            source.n_1700_B(new F_2904_S("message.voicechat.groups_disabled"));
            return null;
        }
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            source.n_1700_B(new F_2904_S("message.voicechat.voice_chat_unavailable"), false);
            return null;
        }
        B_4088_l player = source.t_1786_h();
        if (!PermissionManager.INSTANCE.GROUPS_PERMISSION.hasPermission(player)) {
            source.n_1700_B(new F_2904_S("message.voicechat.no_group_permission"), false);
            return null;
        }
        return server;
    }

    private static int joinGroupByName(y_2498_m source, String groupName, @Nullable String password) throws CommandSyntaxException {
        Server server = VoicechatCommands.joinGroup(source);
        if (server == null) {
            return 1;
        }
        List groups = server.getGroupManager().getGroups().values().stream().filter(group -> group.getName().equals(groupName)).collect(Collectors.toList());
        if (groups.isEmpty()) {
            source.n_1700_B(new F_2904_S("message.voicechat.group_does_not_exist"));
            return 1;
        }
        if (groups.size() > 1) {
            source.n_1700_B(new F_2904_S("message.voicechat.group_name_not_unique"));
            return 1;
        }
        return VoicechatCommands.joinGroup(source, server, ((Group)groups.get(0)).getId(), password);
    }

    private static int joinGroupById(y_2498_m source, UUID groupID, @Nullable String password) throws CommandSyntaxException {
        Server server = VoicechatCommands.joinGroup(source);
        if (server == null) {
            return 1;
        }
        return VoicechatCommands.joinGroup(source, server, groupID, password);
    }

    private static int joinGroup(y_2498_m source, Server server, UUID groupID, @Nullable String password) throws CommandSyntaxException {
        Group group = server.getGroupManager().getGroup(groupID);
        if (group == null) {
            source.n_1700_B(new F_2904_S("message.voicechat.group_does_not_exist"));
            return 1;
        }
        server.getGroupManager().joinGroup(group, source.t_1786_h(), password);
        source.n_1700_B(new F_2904_S("message.voicechat.join_successful", new U_2871_b(group.getName()).n_1700_B(D_4024_W.w_1484_f)), false);
        return 1;
    }

    private static int help(CommandDispatcher<y_2498_m> dispatcher, CommandContext<y_2498_m> commandSource) {
        if (VoicechatCommands.checkNoVoicechat(commandSource)) {
            return 0;
        }
        CommandNode voicechatCommand = dispatcher.getRoot().getChild(VOICECHAT_COMMAND);
        Map map = dispatcher.getSmartUsage(voicechatCommand, (Object)((y_2498_m)commandSource.getSource()));
        for (Map.Entry entry : map.entrySet()) {
            ((y_2498_m)commandSource.getSource()).n_1700_B(new U_2871_b("/voicechat " + (String)entry.getValue()), false);
        }
        return map.size();
    }

    private static boolean checkNoVoicechat(CommandContext<y_2498_m> commandSource) {
        try {
            B_4088_l player = ((y_2498_m)commandSource.getSource()).t_1786_h();
            if (Voicechat.SERVER.isCompatible(player)) {
                return false;
            }
            ((y_2498_m)commandSource.getSource()).n_1700_B(new U_2871_b(String.format((String)Voicechat.TRANSLATIONS.voicechatNeededForCommandMessage.get(), CommonCompatibilityManager.INSTANCE.getModName())));
            return true;
        }
        catch (Exception e) {
            ((y_2498_m)commandSource.getSource()).n_1700_B(new U_2871_b((String)Voicechat.TRANSLATIONS.playerCommandMessage.get()));
            return true;
        }
    }

    private static boolean checkPermission(y_2498_m stack, Permission permission) {
        try {
            return permission.hasPermission(stack.t_1786_h());
        }
        catch (CommandSyntaxException e) {
            return stack.n_1700_B(stack.w_1457_N().t_1786_h());
        }
    }
}


