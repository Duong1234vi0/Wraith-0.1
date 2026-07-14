/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;

public class ServerWorldUtils {
    public static Collection<B_4088_l> getPlayersInRange(e_3591_l level, e_2866_D pos, double range, @Nullable Predicate<B_4088_l> filter) {
        ArrayList<B_4088_l> nearbyPlayers = new ArrayList<B_4088_l>();
        List<B_4088_l> players = level.multiplayerClientSuggestionProvider();
        for (int i = 0; i < players.size(); ++i) {
            B_4088_l player = players.get(i);
            if (!ServerWorldUtils.isInRange(player.s_4990_V(), pos, range) || filter != null && !filter.test(player)) continue;
            nearbyPlayers.add(player);
        }
        return nearbyPlayers;
    }

    public static boolean isInRange(e_2866_D pos1, e_2866_D pos2, double range) {
        return pos1.v_4262_N(pos2) <= range * range;
    }
}


