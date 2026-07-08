/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_746
 */
package code.alphadlc.utils.client.chat;

import net.minecraft.class_746;

public final class ChatCommandSender {
    private ChatCommandSender() {
    }

    public static void sendCommand(class_746 player, String command) {
        if (player == null || player.field_3944 == null || command == null) {
            return;
        }
        if (command.startsWith("/")) {
            player.field_3944.method_45730(command.substring(1));
        } else {
            player.field_3944.method_45729(command);
        }
    }
}
