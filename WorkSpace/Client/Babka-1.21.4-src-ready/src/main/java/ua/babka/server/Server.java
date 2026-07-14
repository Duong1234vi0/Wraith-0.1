package ua.babka.server;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.ScoreboardDisplaySlot;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.number.NumberFormat;
import net.minecraft.scoreboard.number.StyledNumberFormat;
import net.minecraft.text.MutableText;
import ua.babka.Babka;
import ua.babka.module.impl.player.ScoreboardHealth;
import ua.babka.util.IMinecraft;

public final class Server
implements IMinecraft {
    public static int getPing(PlayerEntity entity) {
        PlayerListEntry list = mc.getNetworkHandler().getPlayerListEntry(entity.getUuid());
        return list != null ? list.getLatency() : 0;
    }

    public static float getHealth(LivingEntity entity, boolean gapple) {
        if (Babka.getInstance().getModuleStorage().get(ScoreboardHealth.class).isEnabled()) {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entity;
                if (player.getScoreboard().getObjectiveForSlot(ScoreboardDisplaySlot.BELOW_NAME) == null) {
                    return 0.0f;
                }
                ScoreboardObjective objective = player.getScoreboard().getObjectiveForSlot(ScoreboardDisplaySlot.BELOW_NAME);
                if (objective == null) {
                    return 0.0f;
                }
                ReadableScoreboardScore score = player.getScoreboard().getScore((ScoreHolder)player, objective);
                MutableText text = ReadableScoreboardScore.getFormattedScore((ReadableScoreboardScore)score, (NumberFormat)objective.getNumberFormatOr((NumberFormat)StyledNumberFormat.EMPTY));
                return Float.parseFloat(text.getString().replaceAll("\\D", ""));
            }
            return entity.getHealth() + (gapple ? entity.getAbsorptionAmount() : 0.0f);
        }
        return entity.getHealth() + (gapple ? entity.getAbsorptionAmount() : 0.0f);
    }

    private Server() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

