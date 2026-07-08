/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_266
 *  net.minecraft.class_269
 *  net.minecraft.class_3532
 *  net.minecraft.class_5250
 *  net.minecraft.class_8646
 *  net.minecraft.class_9013
 *  net.minecraft.class_9015
 *  net.minecraft.class_9022
 *  net.minecraft.class_9025
 */
package code.alphadlc.utils.client.packet.network;

import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_3532;
import net.minecraft.class_5250;
import net.minecraft.class_8646;
import net.minecraft.class_9013;
import net.minecraft.class_9015;
import net.minecraft.class_9022;
import net.minecraft.class_9025;

public final class ServerUtil
implements QuickImports {
    private static final Map<UUID, Float> lastValidHealth = new WeakHashMap<UUID, Float>();
    private static long scoreboardCacheTick = -1L;
    private static final Map<UUID, Float> scoreboardCache = new WeakHashMap<UUID, Float>();

    public static float getHealthFraction(class_1309 target) {
        if (target == null) {
            return 0.0f;
        }
        if (target instanceof class_1308) {
            return ServerUtil.safeVanillaFraction(target);
        }
        if (mc.method_1558() == null || mc.method_47392()) {
            return ServerUtil.safeVanillaFraction(target);
        }
        float maxHp = ServerUtil.getMaxHealth(target);
        if (maxHp <= 0.0f) {
            return 0.0f;
        }
        return class_3532.method_15363((float)(ServerUtil.getHealthValue(target) / maxHp), (float)0.0f, (float)1.0f);
    }

    public static float getHealthValue(class_1309 target) {
        Float lastKnown;
        if (target == null) {
            return 0.0f;
        }
        float vanillaHp = target.method_6032();
        if (!(target instanceof class_1657)) {
            return class_3532.method_15363((float)vanillaHp, (float)0.0f, (float)target.method_6063());
        }
        class_1657 player = (class_1657)target;
        if (!player.method_5805()) {
            lastValidHealth.remove(player.method_5667());
            return 0.0f;
        }
        float scoreboardHp = ServerUtil.getCachedScoreboardHealth(player);
        if (scoreboardHp > 0.0f) {
            lastValidHealth.put(player.method_5667(), Float.valueOf(scoreboardHp));
            return class_3532.method_15363((float)scoreboardHp, (float)0.0f, (float)ServerUtil.getMaxHealth(target));
        }
        if (ServerUtil.usesExtendedHealthScale() && mc.method_1558() != null && !mc.method_47392() && (lastKnown = lastValidHealth.get(player.method_5667())) != null && lastKnown.floatValue() > 0.0f) {
            return class_3532.method_15363((float)lastKnown.floatValue(), (float)0.0f, (float)ServerUtil.getMaxHealth(target));
        }
        return class_3532.method_15363((float)vanillaHp, (float)0.0f, (float)ServerUtil.getMaxHealth(target));
    }

    public static float getMaxHealth(class_1309 target) {
        if (target == null) {
            return 20.0f;
        }
        if (target instanceof class_1657) {
            class_1657 player = (class_1657)target;
            if (ServerUtil.usesExtendedHealthScale()) {
                Float lastKnown;
                float scoreboardHp = ServerUtil.getCachedScoreboardHealth(player);
                if (scoreboardHp <= 0.0f && (lastKnown = lastValidHealth.get(player.method_5667())) != null && lastKnown.floatValue() > 0.0f) {
                    scoreboardHp = lastKnown.floatValue();
                }
                if (scoreboardHp > 0.0f) {
                    float vanillaMax = target.method_6063();
                    return scoreboardHp <= vanillaMax ? vanillaMax : ServerUtil.getExtendedMaxHealth();
                }
            }
        }
        return target.method_6063();
    }

    public static float getDisplayHealth(class_1309 target, float healthFraction) {
        if (target == null) {
            return 0.0f;
        }
        if (target instanceof class_1657 && ServerUtil.usesExtendedHealthScale() && mc.method_1558() != null && !mc.method_47392()) {
            return ServerUtil.getHealthValue(target);
        }
        return healthFraction * ServerUtil.getMaxHealth(target);
    }

    private static float getCachedScoreboardHealth(class_1657 player) {
        long tick;
        long l = tick = ServerUtil.mc.field_1687 != null ? ServerUtil.mc.field_1687.method_8510() : 0L;
        if (scoreboardCacheTick != tick) {
            scoreboardCache.clear();
            scoreboardCacheTick = tick;
        }
        return scoreboardCache.computeIfAbsent(player.method_5667(), uuid -> Float.valueOf(ServerUtil.readScoreboardHealth(player))).floatValue();
    }

    private static float readScoreboardHealth(class_1657 player) {
        class_269 scoreboard = player.method_7327();
        class_266 objective = scoreboard.method_1189(class_8646.field_45158);
        if (objective == null) {
            return -1.0f;
        }
        if (ServerUtil.usesReallyWorldScoreboard()) {
            try {
                int score = scoreboard.method_1180(class_9015.method_55422((String)player.method_5820()), objective).method_55409();
                if (score >= 0) {
                    if (ServerUtil.usesExtendedHealthScale()) {
                        return score > 0 ? (float)score : -1.0f;
                    }
                    if ((float)score <= player.method_6063()) {
                        return score;
                    }
                }
            }
            catch (RuntimeException score) {
                // empty catch block
            }
        }
        if (ServerUtil.usesFormattedScoreboardHealth()) {
            try {
                class_5250 formatted = class_9013.method_55398((class_9013)scoreboard.method_55430((class_9015)player, objective), (class_9022)objective.method_55380((class_9022)class_9025.field_47566));
                float parsed = Float.parseFloat(ColorAssist.removeFormatting(formatted.getString()));
                if (parsed > 0.0f) {
                    return parsed;
                }
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return -1.0f;
    }

    private static float getExtendedMaxHealth() {
        return 1000.0f;
    }

    private static boolean usesReallyWorldScoreboard() {
        if (ServerUtil.usesExtendedHealthScale()) {
            return true;
        }
        String address = ServerUtil.getServerAddress();
        return address.contains("reallyworld") || address.contains("playrw") || address.contains("saturn-x") || address.contains("skytime") || address.contains("space-times");
    }

    private static boolean usesFormattedScoreboardHealth() {
        return ServerUtil.usesExtendedHealthScale() || ServerUtil.usesReallyWorldScoreboard();
    }

    private static boolean usesExtendedHealthScale() {
        return switch (Network.server) {
            case "FunTime", "ReallyWorld", "GulPvP", "HolyWorld", "HopLite", "CopyTime" -> true;
            default -> false;
        };
    }

    private static String getServerAddress() {
        if (mc.method_1562() == null || mc.method_1562().method_45734() == null) {
            return "";
        }
        String address = ServerUtil.mc.method_1562().method_45734().field_3761;
        return address == null ? "" : address.toLowerCase(Locale.ROOT);
    }

    private static float safeVanillaFraction(class_1309 target) {
        float max = target.method_6063();
        return max > 0.0f ? class_3532.method_15363((float)(target.method_6032() / max), (float)0.0f, (float)1.0f) : 0.0f;
    }

    private ServerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
