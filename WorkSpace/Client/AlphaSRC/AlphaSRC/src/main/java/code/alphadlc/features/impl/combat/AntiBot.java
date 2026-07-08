/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_742
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.EntitySpawnEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.FakePlayerManager;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import com.mojang.authlib.GameProfile;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_742;

public class AntiBot
extends Module {
    private static final Set<UUID> BOTS = new HashSet<UUID>();
    private final SelectSetting mode = new SelectSetting("\u041c\u043e\u0434", "\u0420\u0435\u0436\u0438\u043c \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u044f \u0431\u043e\u0442\u043e\u0432").value("Matrix", "UniAC", "Reallyworld");
    private final BooleanSetting removeFromWorld = new BooleanSetting("\u0423\u0434\u0430\u043b\u044f\u0442\u044c \u0438\u0437 \u043c\u0438\u0440\u0430", "\u0423\u0434\u0430\u043b\u044f\u0435\u0442 \u0431\u043e\u0442\u043e\u0432 UniAC \u0438\u0437 \u043c\u0438\u0440\u0430").setValue(false).visible(() -> this.mode.isSelected("UniAC"));

    public AntiBot() {
        super("AntiBot", "Anti Bot", ModuleCategory.COMBAT);
        this.setup(this.mode, this.removeFromWorld);
    }

    public static AntiBot getInstance() {
        return Instance.get(AntiBot.class);
    }

    public static boolean isBot(class_1657 entity) {
        if (entity == null) {
            return false;
        }
        if (FakePlayerManager.isFakePlayer((class_1297)entity)) {
            return false;
        }
        return BOTS.contains(entity.method_5667());
    }

    public static boolean checkBot(class_1309 entity) {
        class_1657 player;
        return entity instanceof class_1657 && AntiBot.isBot(player = (class_1657)entity);
    }

    public boolean isBot(UUID uuid) {
        if (FakePlayerManager.isFakePlayer(uuid)) {
            return false;
        }
        return BOTS.contains(uuid);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        class_1657 player;
        block5: {
            block4: {
                class_1297 class_12972 = event.getEntity();
                if (!(class_12972 instanceof class_1657)) break block4;
                player = (class_1657)class_12972;
                if (AntiBot.mc.field_1724 != null) break block5;
            }
            return;
        }
        if (player == AntiBot.mc.field_1724) {
            return;
        }
        this.markBot(player, this.evaluate(player));
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        BOTS.clear();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.isState() || AntiBot.mc.field_1724 == null || AntiBot.mc.field_1687 == null) {
            return;
        }
        for (class_1657 player : AntiBot.mc.field_1687.method_18456()) {
            if (player == AntiBot.mc.field_1724) continue;
            boolean isBot = this.evaluate(player);
            this.markBot(player, isBot);
            if (!this.mode.isSelected("UniAC") || !this.removeFromWorld.isValue() || !isBot) continue;
            AntiBot.mc.field_1687.method_2945(player.method_5628(), class_1297.class_5529.field_26999);
        }
    }

    @Override
    public void activate() {
        super.activate();
        if (AntiBot.mc.field_1687 == null) {
            return;
        }
        for (class_1657 player : AntiBot.mc.field_1687.method_18456()) {
            if (player == AntiBot.mc.field_1724) continue;
            this.markBot(player, this.evaluate(player));
        }
    }

    @Override
    public void deactivate() {
        BOTS.clear();
        super.deactivate();
    }

    private void markBot(class_1657 player, boolean bot) {
        if (bot) {
            BOTS.add(player.method_5667());
        } else {
            BOTS.remove(player.method_5667());
        }
    }

    private boolean evaluate(class_1657 player) {
        if (!(player instanceof class_742)) {
            return false;
        }
        if (player == AntiBot.mc.field_1724) {
            return false;
        }
        if (this.mode.isSelected("Matrix")) {
            return this.isMatrixBot(player);
        }
        if (this.mode.isSelected("UniAC")) {
            return this.isUniAcBot(player);
        }
        return this.isReallyworldBot(player);
    }

    private boolean isMatrixBot(class_1657 player) {
        String name = player.method_7334().getName();
        UUID expected = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
        return !player.method_5667().equals(expected);
    }

    private boolean isUniAcBot(class_1657 player) {
        if (FriendUtils.isFriend((class_1297)player)) {
            return false;
        }
        if (player.method_7325()) {
            return false;
        }
        boolean bootsEmpty = this.getArmor(player, 0).method_31574(class_1802.field_8162);
        boolean leggingsEmpty = this.getArmor(player, 1).method_31574(class_1802.field_8162);
        boolean chestEmpty = this.getArmor(player, 2).method_31574(class_1802.field_8162);
        boolean helmetEmpty = this.getArmor(player, 3).method_31574(class_1802.field_8162);
        boolean bootsFilled = !bootsEmpty;
        boolean leggingsFilled = !leggingsEmpty;
        boolean chestFilled = !chestEmpty;
        boolean helmetFilled = !helmetEmpty;
        boolean bootsRule = bootsEmpty || bootsFilled && !this.isEnchantable(this.getArmor(player, 0));
        boolean leggingsRule = leggingsEmpty || leggingsFilled && !this.isEnchantable(this.getArmor(player, 1));
        boolean chestRule = chestEmpty || chestFilled && !this.isEnchantable(this.getArmor(player, 2));
        boolean helmetRule = helmetEmpty || helmetFilled && !this.isEnchantable(this.getArmor(player, 3));
        boolean allArmorDamaged = !bootsEmpty && this.getArmor(player, 0).method_7986() && !leggingsEmpty && this.getArmor(player, 1).method_7986() && !chestEmpty && this.getArmor(player, 2).method_7986() && !helmetEmpty && this.getArmor(player, 3).method_7986();
        String name = player.method_5477().getString();
        boolean shortName = name.length() <= 6;
        boolean suspiciousName = name.matches("^[a-z]{6}$");
        boolean fullArmorCheck = bootsRule && leggingsRule && chestRule && helmetRule;
        boolean nameCheck = shortName || suspiciousName;
        boolean hasTablistEntry = mc.method_1562() != null && mc.method_1562().method_2880().stream().anyMatch(info -> info.method_2966().getName().equals(name));
        return nameCheck && !allArmorDamaged && fullArmorCheck && hasTablistEntry;
    }

    private boolean isReallyworldBot(class_1657 player) {
        GameProfile profile = player.method_7334();
        String name = profile.getName();
        if (name.contains("NPC")) {
            return true;
        }
        if (player.method_5767()) {
            return true;
        }
        boolean emptyProperties = profile.getProperties().isEmpty();
        return emptyProperties;
    }

    private boolean isEnchantable(class_1799 stack) {
        return !stack.method_7960() && stack.method_7923();
    }

    private class_1799 getArmor(class_1657 player, int slot) {
        return player.method_31548().method_7372(slot);
    }
}
