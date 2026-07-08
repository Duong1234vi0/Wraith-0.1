/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_3414
 *  net.minecraft.class_3419
 *  net.minecraft.class_4050
 *  net.minecraft.class_4587
 *  net.minecraft.class_745
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.player.EntityDeathEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.geometry.Render3D;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_4050;
import net.minecraft.class_4587;
import net.minecraft.class_745;

public class KillEffect
extends Module {
    private final SliderSettings volume = new SliderSettings("Volume", "Volume").setValue(100.0f).range(0, 100);
    private final BooleanSetting playSound = new BooleanSetting("Play Sound", "Play Sound").setValue(true);
    private final BooleanSetting mobs = new BooleanSetting("Mobs", "Mobs").setValue(false);
    private final SelectSetting effectType = new SelectSetting("Effect Type", "Effect Type").value("Cross", "Soul").selected("Soul");
    private final SelectSetting soundType = new SelectSetting("Sound Type", "Sound Type").value("Orthodox", "Kill1", "Kill2", "Kill3", "Kill4", "Kill5", "Kill6", "Random").selected("Orthodox");
    private final Map<class_1297, EntityRenderData> renderEntities = new ConcurrentHashMap<class_1297, EntityRenderData>();
    private final Random random = new Random();

    public KillEffect() {
        super("KillEffect", "Kill Effect", ModuleCategory.RENDER);
        this.setup(this.volume, this.playSound, this.mobs, this.effectType, this.soundType);
    }

    @Override
    public void deactivate() {
        this.renderEntities.clear();
        super.deactivate();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (KillEffect.mc.field_1687 == null || KillEffect.mc.field_1724 == null) {
            return;
        }
        class_1297 entity = event.getEntity();
        if (!(entity instanceof class_1309)) {
            return;
        }
        if (!this.mobs.isValue() && !(entity instanceof class_1657)) {
            return;
        }
        if (entity == KillEffect.mc.field_1724 || this.renderEntities.containsKey(entity)) {
            return;
        }
        if (this.playSound.isValue()) {
            class_3414 soundToPlay = this.getSelectedSound();
            KillEffect.mc.field_1687.method_8396((class_1657)KillEffect.mc.field_1724, entity.method_24515(), soundToPlay, class_3419.field_15245, this.volume.getValue() / 100.0f, 1.0f);
        }
        class_745 fakePlayer = null;
        if (this.effectType.isSelected("Soul") && entity instanceof class_1657) {
            fakePlayer = new class_745(KillEffect.mc.field_1687, ((class_1657)entity).method_7334());
            fakePlayer.method_36457(-30.0f);
            fakePlayer.method_36456(entity.method_36454());
            fakePlayer.field_6241 = entity.method_36454();
            fakePlayer.field_6283 = entity.method_36454();
            fakePlayer.method_5880(false);
            fakePlayer.method_5665((class_2561)class_2561.method_43470((String)("Ghost_" + String.valueOf(((class_1657)entity).method_7334().getId()))));
            KillEffect.mc.field_1687.method_53875((class_1297)fakePlayer);
        }
        this.renderEntities.put(entity, new EntityRenderData(System.currentTimeMillis(), entity.method_36454(), entity.method_19538(), entity, fakePlayer));
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (KillEffect.mc.field_1687 == null || KillEffect.mc.field_1724 == null) {
            return;
        }
        class_4587 stack = e.getStack();
        float tickDelta = e.getPartialTicks();
        ArrayList entitiesToRemove = new ArrayList();
        this.renderEntities.forEach((entity, data) -> {
            if (System.currentTimeMillis() - data.getTimestamp() > 3000L) {
                entitiesToRemove.add(entity);
                if (data.getFakePlayer() != null) {
                    KillEffect.mc.field_1687.method_2945(data.getFakePlayer().method_5628(), class_1297.class_5529.field_26999);
                }
            } else {
                float timeProgress = (float)(System.currentTimeMillis() - data.getTimestamp()) / 3000.0f;
                if (this.effectType.isSelected("Cross")) {
                    int color = new Color(255, 255, 255, (int)(150.0f * (1.0f - timeProgress))).getRGB();
                    float yaw = (float)Math.toRadians(data.getYaw() + 95.0f);
                    class_243 pos = data.getStartPos();
                    Render3D.drawLine(pos.method_1031(0.0, 0.0, 0.0), pos.method_1031(0.0, 3.0, 0.0), color, 5.0f, true);
                    float armLength = 1.0f;
                    float yOffset = 2.3f;
                    class_243 start = pos.method_1031((double)(-armLength) * Math.sin(yaw), (double)yOffset, (double)armLength * Math.cos(yaw));
                    class_243 end = pos.method_1031((double)armLength * Math.sin(yaw), (double)yOffset, (double)(-armLength) * Math.cos(yaw));
                    Render3D.drawLine(start, end, color, 5.0f, true);
                } else if (this.effectType.isSelected("Soul")) {
                    float yOffset = timeProgress * 3.0f;
                    int alpha = (int)(255.0f * (1.0f - timeProgress));
                    class_243 soulPos = data.getStartPos().method_1031(0.0, (double)yOffset, 0.0);
                    class_1297 renderEntity = data.getEntity();
                    if (data.getFakePlayer() != null) {
                        renderEntity = data.getFakePlayer();
                        renderEntity.method_23327(soulPos.field_1352, soulPos.field_1351, soulPos.field_1350);
                    }
                    Render3D.drawEntity(renderEntity, soulPos, data.getYaw(), alpha, stack, tickDelta);
                }
            }
        });
        entitiesToRemove.forEach(this.renderEntities::remove);
    }

    private class_3414 getSelectedSound() {
        switch (this.soundType.getSelected()) {
            case "Kill1": {
                return SoundManager.KILL1;
            }
            case "Kill2": {
                return SoundManager.KILL2;
            }
            case "Kill3": {
                return SoundManager.KILL3;
            }
            case "Kill4": {
                return SoundManager.KILL4;
            }
            case "Kill5": {
                return SoundManager.KILL5;
            }
            case "Kill6": {
                return SoundManager.KILL6;
            }
            case "Random": {
                class_3414[] killSounds = new class_3414[]{SoundManager.KILL1, SoundManager.KILL2, SoundManager.KILL3, SoundManager.KILL4, SoundManager.KILL5, SoundManager.KILL6};
                return killSounds[this.random.nextInt(killSounds.length)];
            }
        }
        return SoundManager.ORTHODOX;
    }

    private static class EntityRenderData {
        private final long timestamp;
        private final float yaw;
        private final class_243 startPos;
        private final class_1297 entity;
        private final GameProfile gameProfile;
        private final class_4050 pose;
        private final class_745 fakePlayer;

        public EntityRenderData(long timestamp, float yaw, class_243 startPos, class_1297 entity, class_745 fakePlayer) {
            this.timestamp = timestamp;
            this.yaw = yaw;
            this.startPos = startPos;
            this.entity = entity;
            this.gameProfile = entity instanceof class_1657 ? ((class_1657)entity).method_7334() : null;
            this.pose = entity.method_18376();
            this.fakePlayer = fakePlayer;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public float getYaw() {
            return this.yaw;
        }

        public class_243 getStartPos() {
            return this.startPos;
        }

        public class_1297 getEntity() {
            return this.entity;
        }

        public GameProfile getGameProfile() {
            return this.gameProfile;
        }

        public class_4050 getPose() {
            return this.pose;
        }

        public class_745 getFakePlayer() {
            return this.fakePlayer;
        }
    }
}
