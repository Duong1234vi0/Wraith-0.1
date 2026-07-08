/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1304
 *  net.minecraft.class_1799
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4050
 *  net.minecraft.class_745
 */
package code.alphadlc.utils.client;

import com.mojang.authlib.GameProfile;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4050;
import net.minecraft.class_745;

public final class FakePlayerManager {
    private static final class_310 mc = class_310.method_1551();
    private static final UUID FAKE_UUID = UUID.nameUUIDFromBytes("AlphaDLCFakePlayer".getBytes(StandardCharsets.UTF_8));
    private static final String DEFAULT_NAME = "FakePlayer";
    private static class_745 fakePlayer;

    private FakePlayerManager() {
    }

    public static boolean add(String name) {
        if (FakePlayerManager.mc.field_1687 == null || FakePlayerManager.mc.field_1724 == null) {
            return false;
        }
        FakePlayerManager.remove();
        String cleanName = FakePlayerManager.sanitizeName(name);
        GameProfile profile = new GameProfile(FAKE_UUID, cleanName);
        class_745 entity = new class_745(FakePlayerManager.mc.field_1687, profile);
        float yawRad = FakePlayerManager.mc.field_1724.method_36454() * (float)Math.PI / 180.0f;
        class_243 forward = new class_243((double)(-class_3532.method_15374((float)yawRad)), 0.0, (double)class_3532.method_15362((float)yawRad)).method_1029();
        class_243 pos = FakePlayerManager.mc.field_1724.method_19538().method_1019(forward.method_1021(2.5));
        entity.method_5808(pos.field_1352, FakePlayerManager.mc.field_1724.method_23318(), pos.field_1350, FakePlayerManager.mc.field_1724.method_36454() + 180.0f, 0.0f);
        entity.field_6241 = entity.method_36454();
        entity.field_6283 = entity.method_36454();
        entity.method_18380(class_4050.field_18076);
        entity.method_6033(entity.method_6063());
        entity.method_6073(0.0f);
        entity.method_5684(true);
        entity.method_5665((class_2561)class_2561.method_43470((String)cleanName));
        entity.method_5880(true);
        entity.method_5673(class_1304.field_6173, class_1799.field_8037);
        entity.method_5673(class_1304.field_6171, class_1799.field_8037);
        FakePlayerManager.mc.field_1687.method_53875((class_1297)entity);
        fakePlayer = entity;
        return true;
    }

    public static boolean remove() {
        if (fakePlayer == null) {
            return false;
        }
        if (FakePlayerManager.mc.field_1687 != null) {
            FakePlayerManager.mc.field_1687.method_2945(fakePlayer.method_5628(), class_1297.class_5529.field_26999);
        }
        fakePlayer = null;
        return true;
    }

    public static boolean handleAttack(class_1297 target) {
        if (!FakePlayerManager.isFakePlayer(target)) {
            return false;
        }
        FakePlayerManager.restoreHealth();
        return true;
    }

    public static boolean isSpawned() {
        return fakePlayer != null && !fakePlayer.method_31481();
    }

    public static boolean isFakePlayer(class_1297 entity) {
        return fakePlayer != null && entity != null && entity.method_5628() == fakePlayer.method_5628();
    }

    public static boolean isFakePlayer(UUID uuid) {
        return fakePlayer != null && uuid != null && uuid.equals(fakePlayer.method_5667());
    }

    private static void restoreHealth() {
        if (fakePlayer == null) {
            return;
        }
        fakePlayer.method_6033(fakePlayer.method_6063());
        fakePlayer.method_6073(0.0f);
        FakePlayerManager.fakePlayer.field_6235 = 0;
        FakePlayerManager.fakePlayer.field_6254 = 0;
    }

    private static String sanitizeName(String name) {
        if (name == null || name.isBlank()) {
            return DEFAULT_NAME;
        }
        String cleanName = name.trim();
        if (cleanName.length() > 16) {
            cleanName = cleanName.substring(0, 16);
        }
        return cleanName;
    }
}
