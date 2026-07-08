/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1291
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2824
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2886
 *  net.minecraft.class_304
 *  net.minecraft.class_3532
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_3965
 *  net.minecraft.class_4050
 *  net.minecraft.class_408
 *  net.minecraft.class_437
 *  net.minecraft.class_6880
 *  net.minecraft.class_7204
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.utils.interactions.interact;

import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.packet.network.ServerUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2886;
import net.minecraft.class_304;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_3965;
import net.minecraft.class_4050;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_6880;
import net.minecraft.class_7204;
import org.lwjgl.glfw.GLFW;

public final class PlayerInteractionHelper
implements QuickImports {
    public static void sendSequencedPacket(class_7204 packetCreator) {
        PlayerInteractionHelper.mc.field_1761.method_41931(PlayerInteractionHelper.mc.field_1687, packetCreator);
    }

    public static void interactItem(class_1268 hand) {
        PlayerInteractionHelper.interactItem(hand, MathAngle.cameraAngle());
    }

    public static void interactItem(class_1268 hand, Turns angle) {
        PlayerInteractionHelper.sendSequencedPacket(i -> new class_2886(hand, i, angle.getYaw(), angle.getPitch()));
    }

    public static void interactEntity(class_1297 entity) {
        PlayerInteractionHelper.mc.field_1724.field_3944.method_52787((class_2596)class_2824.method_34208((class_1297)entity, (boolean)false, (class_1268)class_1268.field_5808, (class_243)entity.method_5829().method_1005()));
        PlayerInteractionHelper.mc.field_1724.field_3944.method_52787((class_2596)class_2824.method_34207((class_1297)entity, (boolean)false, (class_1268)class_1268.field_5808));
    }

    public static void startFallFlying() {
        PlayerInteractionHelper.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)PlayerInteractionHelper.mc.field_1724, class_2848.class_2849.field_12982));
        PlayerInteractionHelper.mc.field_1724.method_23669();
    }

    public static void sendPacketWithOutEvent(class_2596<?> packet) {
        mc.method_1562().method_48296().method_10752(packet, null);
    }

    public static void grimSuperBypass$$$(double y, Turns angle) {
        PlayerInteractionHelper.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(PlayerInteractionHelper.mc.field_1724.method_23317(), PlayerInteractionHelper.mc.field_1724.method_23318() + y, PlayerInteractionHelper.mc.field_1724.method_23321(), angle.getYaw(), angle.getPitch(), PlayerInteractionHelper.mc.field_1724.method_24828(), PlayerInteractionHelper.mc.field_1724.field_5976));
    }

    public static String getHealthString(class_1309 entity) {
        return PlayerInteractionHelper.getHealthString(PlayerInteractionHelper.getHealth(entity));
    }

    public static String getHealthString(float hp) {
        return String.format("%.1f", Float.valueOf(hp)).replace(",", ".").replace(".0", "");
    }

    public static float getHealth(class_1309 entity) {
        float hp = ServerUtil.getHealthValue(entity) + entity.method_6067();
        return class_3532.method_15363((float)hp, (float)0.0f, (float)(ServerUtil.getMaxHealth(entity) + entity.method_6067()));
    }

    public static void jump() {
        if (PlayerInteractionHelper.mc.field_1724.method_5624()) {
            float g = PlayerInteractionHelper.mc.field_1724.method_36454() * ((float)Math.PI / 180);
            PlayerInteractionHelper.mc.field_1724.method_45319(new class_243((double)(-class_3532.method_15374((float)g) * 0.2f), 0.0, (double)(class_3532.method_15362((float)g) * 0.2f)));
        }
        PlayerInteractionHelper.mc.field_1724.field_6007 = true;
    }

    public static List<class_2338> getCube(class_2338 center, float radius) {
        return PlayerInteractionHelper.getCube(center, radius, radius, true);
    }

    public static List<class_2338> getCube(class_2338 center, float radiusXZ, float radiusY) {
        return PlayerInteractionHelper.getCube(center, radiusXZ, radiusY, true);
    }

    public static List<class_2338> getCube(class_2338 center, float radiusXZ, float radiusY, boolean down) {
        ArrayList<class_2338> positions = new ArrayList<class_2338>();
        int centerX = center.method_10263();
        int centerY = center.method_10264();
        int centerZ = center.method_10260();
        int posY = down ? centerY - (int)radiusY : centerY;
        int x = centerX - (int)radiusXZ;
        while ((float)x <= (float)centerX + radiusXZ) {
            int z = centerZ - (int)radiusXZ;
            while ((float)z <= (float)centerZ + radiusXZ) {
                int y = posY;
                while ((float)y <= (float)centerY + radiusY) {
                    positions.add(new class_2338(x, y, z));
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return positions;
    }

    public static List<class_2338> getCube(class_2338 start, class_2338 end) {
        ArrayList<class_2338> positions = new ArrayList<class_2338>();
        for (int x = start.method_10263(); x <= end.method_10263(); ++x) {
            for (int z = start.method_10260(); z <= end.method_10260(); ++z) {
                for (int y = start.method_10264(); y <= end.method_10264(); ++y) {
                    positions.add(new class_2338(x, y, z));
                }
            }
        }
        return positions;
    }

    public static class_3675.class_307 getKeyType(int key) {
        return key < 8 ? class_3675.class_307.field_1672 : class_3675.class_307.field_1668;
    }

    public static Stream<class_1297> streamEntities() {
        return StreamSupport.stream(PlayerInteractionHelper.mc.field_1687.method_18112().spliterator(), false);
    }

    public static boolean canChangeIntoPose(class_4050 pose, class_243 pos) {
        return PlayerInteractionHelper.mc.field_1724.method_37908().method_8587((class_1297)PlayerInteractionHelper.mc.field_1724, PlayerInteractionHelper.mc.field_1724.method_18377(pose).method_30757(pos).method_1011(1.0E-7));
    }

    public static boolean isPotionActive(class_6880<class_1291> statusEffect) {
        return PlayerInteractionHelper.mc.field_1724.method_6088().containsKey(statusEffect);
    }

    public static boolean isPlayerInBlock(class_2248 block) {
        return PlayerInteractionHelper.isBoxInBlock(PlayerInteractionHelper.mc.field_1724.method_5829().method_1014(-0.001), block);
    }

    public static boolean isBoxInBlock(class_238 box, class_2248 block) {
        return PlayerInteractionHelper.isBox(box, pos -> PlayerInteractionHelper.mc.field_1687.method_8320(pos).method_26204().equals(block));
    }

    public static boolean isBoxInBlocks(class_238 box, List<class_2248> blocks) {
        return PlayerInteractionHelper.isBox(box, pos -> blocks.contains(PlayerInteractionHelper.mc.field_1687.method_8320(pos).method_26204()));
    }

    public static boolean isBox(class_238 box, Predicate<class_2338> pos) {
        return class_2338.method_29715((class_238)box).anyMatch(pos);
    }

    public static boolean isKey(BindSetting setting) {
        if (PlayerInteractionHelper.mc.field_1755 != null || !setting.isVisible()) {
            return false;
        }
        int key = setting.getKey();
        if (setting.isCombo()) {
            int secondKey = setting.getSecondKey();
            return PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(key), key) && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(secondKey), secondKey);
        }
        return PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(key), key);
    }

    public static boolean isKey(class_304 key) {
        return PlayerInteractionHelper.isKey(key.method_1429().method_1442(), key.method_1429().method_1444());
    }

    public static boolean isKey(class_3675.class_307 type, int keyCode) {
        if (keyCode != -1) {
            switch (type) {
                case field_1668: {
                    return GLFW.glfwGetKey((long)mc.method_22683().method_4490(), (int)keyCode) == 1;
                }
                case field_1672: {
                    return GLFW.glfwGetMouseButton((long)mc.method_22683().method_4490(), (int)keyCode) == 1;
                }
            }
        }
        return false;
    }

    public static boolean isAir(class_2338 blockPos) {
        return PlayerInteractionHelper.isAir(PlayerInteractionHelper.mc.field_1687.method_8320(blockPos));
    }

    public static boolean isAir(class_2680 state) {
        return state.method_26215() || state.method_26204().equals(class_2246.field_10543) || state.method_26204().equals(class_2246.field_10243);
    }

    public static boolean isChat(class_437 screen) {
        return screen instanceof class_408;
    }

    public static boolean nullCheck() {
        return PlayerInteractionHelper.mc.field_1724 == null || PlayerInteractionHelper.mc.field_1687 == null;
    }

    public static class_243 getSafePositionNearChest(class_2338 chest, class_2350 side) {
        class_2338[] candidates;
        if (PlayerInteractionHelper.mc.field_1687 == null) {
            return null;
        }
        for (class_2338 p : candidates = new class_2338[]{chest.method_10093(side), chest.method_10084(), chest.method_10093(side).method_10084(), chest.method_10086(2)}) {
            if (PlayerInteractionHelper.mc.field_1687.method_8320(p).method_51367()) continue;
            return class_243.method_24953((class_2382)p);
        }
        return null;
    }

    public static void interactBlockChestWithInBlockBypass(class_2338 chest, class_243 center, class_2350 side) {
        class_243 safe = PlayerInteractionHelper.getSafePositionNearChest(chest, side);
        if (safe != null) {
            PlayerInteractionHelper.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(safe.field_1352, safe.field_1351, safe.field_1350, PlayerInteractionHelper.mc.field_1724.method_36454(), PlayerInteractionHelper.mc.field_1724.method_36455(), PlayerInteractionHelper.mc.field_1724.method_24828(), false));
        }
        PlayerInteractionHelper.mc.field_1761.method_2896(PlayerInteractionHelper.mc.field_1724, class_1268.field_5808, new class_3965(center, side, chest, false));
    }

    private PlayerInteractionHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
