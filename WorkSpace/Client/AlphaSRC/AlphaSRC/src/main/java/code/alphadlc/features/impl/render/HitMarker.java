/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10055
 *  net.minecraft.class_1007
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_239
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3966
 *  net.minecraft.class_4050
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_591
 *  net.minecraft.class_638
 *  net.minecraft.class_745
 *  net.minecraft.class_7833
 *  net.minecraft.class_897
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.render.Chams;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.mixins.LivingEntityRendererAccessor;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render2D;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import net.minecraft.class_4050;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_638;
import net.minecraft.class_745;
import net.minecraft.class_7833;
import net.minecraft.class_897;

public class HitMarker
extends Module {
    private static final long DURATION = 1000L;
    private static final long CHAMS_SOUL_DURATION = 3000L;
    private static final int MAX_MARKERS = 24;
    private static final float WORLD_SCALE = 0.012f;
    private static final float SOUL_PITCH = 0.0f;
    private final SliderSettings sizeSetting = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0445\u0438\u0442-\u043c\u0430\u0440\u043a\u0435\u0440\u0430").setValue(12.0f).range(6, 30);
    private final BooleanSetting chams = new BooleanSetting("Chams", "\u041f\u0440\u0438\u0437\u0440\u0430\u043a \u0432 \u0441\u0442\u0438\u043b\u0435 Chams \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435").setValue(false);
    private final ColorSetting chamsColor = new ColorSetting("\u0426\u0432\u0435\u0442 Chams", "\u0426\u0432\u0435\u0442 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430 Chams").value(ColorAssist.getClientColor()).visible(() -> this.chams.isValue());
    private final SliderSettings chamsOpacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c Chams", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430 Chams").setValue(0.85f).range(0.1f, 1.0f).step(0.05f).visible(() -> this.chams.isValue());
    private final SliderSettings chamsSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c Chams", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e\u043b\u0451\u0442\u0430 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430").setValue(1.0f).range(0.2f, 4.0f).step(0.1f).visible(() -> this.chams.isValue());
    private final MultiSelectSetting chamsDirection = new MultiSelectSetting("\u041d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 Chams", "\u041a\u0443\u0434\u0430 \u043b\u0435\u0442\u0438\u0442 \u043f\u0440\u0438\u0437\u0440\u0430\u043a").value("\u0412\u0432\u0435\u0440\u0445", "\u0412\u043d\u0438\u0437", "\u0412\u043b\u0435\u0432\u043e", "\u0412\u043f\u0440\u0430\u0432\u043e").selected("\u0412\u0432\u0435\u0440\u0445").visible(() -> this.chams.isValue());
    private static final int MAX_CHAMS_SOULS = 32;
    private final List<Marker> markers = new ArrayList<Marker>();
    private final List<ChamsSoul> chamsSouls = new ArrayList<ChamsSoul>();
    private int directionCycleIndex;

    public static HitMarker getInstance() {
        return Instance.get(HitMarker.class);
    }

    public HitMarker() {
        super("Hit Marker", "Hit Marker", ModuleCategory.RENDER);
        this.setup(this.sizeSetting, this.chams, this.chamsColor, this.chamsOpacity, this.chamsSpeed, this.chamsDirection);
    }

    @Override
    public void deactivate() {
        this.markers.clear();
        this.chamsSouls.clear();
    }

    @EventHandler
    public void onAttack(AttackEvent e) {
        class_3966 result;
        class_1309 living;
        class_1297 entity = e.getEntity();
        if (!(entity instanceof class_1309) || (living = (class_1309)entity) == HitMarker.mc.field_1724 || !living.method_5805()) {
            return;
        }
        class_239 class_2392 = HitMarker.mc.field_1765;
        class_243 hitPos = class_2392 instanceof class_3966 && (result = (class_3966)class_2392).method_17782() == entity ? result.method_17784() : living.method_5829().method_1005();
        this.markers.add(new Marker(hitPos, System.currentTimeMillis()));
        if (this.markers.size() > 24) {
            this.markers.remove(0);
        }
        if (this.chams.isValue() && entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            this.spawnChamsSoul(player, living.method_19538());
        }
    }

    private void spawnChamsSoul(class_1657 player, class_243 startPos) {
        class_638 clientWorld;
        block5: {
            block4: {
                class_638 class_6382 = HitMarker.mc.field_1687;
                if (!(class_6382 instanceof class_638)) break block4;
                clientWorld = class_6382;
                if (HitMarker.mc.field_1724 != null) break block5;
            }
            return;
        }
        GameProfile profile = player.method_7334();
        class_745 fakePlayer = new class_745(clientWorld, profile);
        fakePlayer.method_5878((class_1297)player);
        fakePlayer.method_18380(class_4050.field_18076);
        fakePlayer.method_23327(startPos.field_1352, startPos.field_1351, startPos.field_1350);
        fakePlayer.method_18800(0.0, 0.0, 0.0);
        fakePlayer.method_5648(true);
        fakePlayer.field_5960 = true;
        float faceYaw = this.computeYawTowardsPlayer(startPos);
        HitMarker.freezeEntityPose(fakePlayer, faceYaw, faceYaw, 0.0f);
        class_243 flyDirection = this.computeFlyDirection(startPos);
        this.chamsSouls.add(new ChamsSoul(fakePlayer, startPos, flyDirection, System.currentTimeMillis()));
        if (this.chamsSouls.size() > 32) {
            this.chamsSouls.remove(0);
        }
    }

    private float computeYawTowardsPlayer(class_243 from) {
        if (HitMarker.mc.field_1724 == null) {
            return 0.0f;
        }
        double dx = HitMarker.mc.field_1724.method_23317() - from.field_1352;
        double dz = HitMarker.mc.field_1724.method_23321() - from.field_1350;
        return class_3532.method_15393((float)((float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0)));
    }

    private class_243 computeFlyDirection(class_243 soulPos) {
        if (HitMarker.mc.field_1724 == null) {
            return new class_243(0.0, 1.0, 0.0);
        }
        class_243 toVictim = soulPos.method_1031(0.0, 0.9, 0.0).method_1020(HitMarker.mc.field_1724.method_33571());
        class_243 forward = new class_243(toVictim.field_1352, 0.0, toVictim.field_1350);
        if (forward.method_1027() < 1.0E-6) {
            double yawRad = Math.toRadians(HitMarker.mc.field_1724.method_36454());
            forward = new class_243(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
        }
        forward = forward.method_1029();
        class_243 right = forward.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
        class_243 left = right.method_22882();
        ArrayList<class_243> options = new ArrayList<class_243>();
        if (this.chamsDirection.isSelected("\u0412\u0432\u0435\u0440\u0445")) {
            options.add(new class_243(0.0, 1.0, 0.0));
        }
        if (this.chamsDirection.isSelected("\u0412\u043d\u0438\u0437")) {
            options.add(new class_243(0.0, -1.0, 0.0));
        }
        if (this.chamsDirection.isSelected("\u0412\u043b\u0435\u0432\u043e")) {
            options.add(left);
        }
        if (this.chamsDirection.isSelected("\u0412\u043f\u0440\u0430\u0432\u043e")) {
            options.add(right);
        }
        if (options.isEmpty()) {
            return new class_243(0.0, 1.0, 0.0);
        }
        class_243 direction = (class_243)options.get(this.directionCycleIndex % options.size());
        ++this.directionCycleIndex;
        return direction;
    }

    private static void freezeEntityPose(class_745 entity, float yaw, float bodyYaw, float pitch) {
        entity.method_36456(yaw);
        entity.field_5982 = yaw;
        entity.method_36457(pitch);
        entity.field_6004 = pitch;
        entity.field_6241 = yaw;
        entity.field_6259 = yaw;
        entity.field_6283 = bodyYaw;
        entity.field_6220 = bodyYaw;
        entity.field_6038 = entity.method_23317();
        entity.field_5971 = entity.method_23318();
        entity.field_5989 = entity.method_23321();
        entity.field_6014 = entity.method_23317();
        entity.field_6036 = entity.method_23318();
        entity.field_5969 = entity.method_23321();
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (HitMarker.mc.field_1724 == null || HitMarker.mc.field_1687 == null || HitMarker.mc.field_1773 == null) {
            return;
        }
        long now = System.currentTimeMillis();
        class_4184 camera = HitMarker.mc.field_1773.method_19418();
        class_4587 matrices = e.getStack();
        if (!this.markers.isEmpty()) {
            this.renderMarkers(now, camera, matrices);
        }
        if (!this.chamsSouls.isEmpty()) {
            this.renderChamsSouls(now, matrices);
        }
    }

    private void renderMarkers(long now, class_4184 camera, class_4587 matrices) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Iterator<Marker> iterator = this.markers.iterator();
        while (iterator.hasNext()) {
            Marker marker = iterator.next();
            long elapsed = now - marker.time;
            if (elapsed > 1000L) {
                iterator.remove();
                continue;
            }
            float progress = (float)elapsed / 1000.0f;
            float fade = 1.0f - progress;
            float scale = 1.0f + progress * 0.35f;
            float size = this.sizeSetting.getValue() * scale;
            float half = size / 2.0f;
            float gap = size * 0.18f;
            float thickness = Math.max(1.5f, size / 7.0f);
            int color = ColorAssist.multAlpha(ColorAssist.getHudColor(), fade);
            matrices.method_22903();
            matrices.method_22904(marker.pos.field_1352, marker.pos.field_1351, marker.pos.field_1350);
            matrices.method_22907(camera.method_23767());
            matrices.method_22905(-0.012f, -0.012f, 0.012f);
            HitMarker.drawCross(matrices, 0.0f, 0.0f, half, gap, thickness, color);
            matrices.method_22909();
        }
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderChamsSouls(long now, class_4587 matrices) {
        Chams chamsModule = Chams.getInstance();
        if (chamsModule == null) {
            return;
        }
        Iterator<ChamsSoul> iterator = this.chamsSouls.iterator();
        while (iterator.hasNext()) {
            ChamsSoul soul = iterator.next();
            long elapsed = now - soul.startTime;
            if (elapsed > 3000L) {
                iterator.remove();
                continue;
            }
            float progress = (float)elapsed / 3000.0f;
            float fade = 1.0f - progress;
            float travel = (float)elapsed / 1000.0f * this.chamsSpeed.getValue();
            class_243 soulPos = soul.startPos.method_1019(soul.flyDirection.method_1021((double)travel));
            class_745 fakePlayer = soul.fakePlayer;
            fakePlayer.method_23327(soulPos.field_1352, soulPos.field_1351, soulPos.field_1350);
            float faceYaw = this.computeYawTowardsPlayer(soulPos);
            HitMarker.freezeEntityPose(fakePlayer, faceYaw, faceYaw, 0.0f);
            class_897 class_8972 = mc.method_1561().method_3953((class_1297)fakePlayer);
            if (!(class_8972 instanceof class_1007)) continue;
            class_1007 renderer = (class_1007)class_8972;
            class_591 model = (class_591)((LivingEntityRendererAccessor)renderer).alphadlc$getModel();
            class_10055 state = (class_10055)renderer.method_62425((class_1297)fakePlayer, 0.0f);
            model.method_62110(state);
            matrices.method_22903();
            matrices.method_22904(soulPos.field_1352, soulPos.field_1351, soulPos.field_1350);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - faceYaw));
            matrices.method_22905(1.0f, -1.0f, 1.0f);
            int rgb = this.chamsColor.getColor();
            chamsModule.renderCustom(matrices, model, rgb, this.chamsOpacity.getValue() * fade);
            matrices.method_22909();
        }
    }

    private static void drawCross(class_4587 matrix, float cx, float cy, float half, float gap, float thickness, int color) {
        HitMarker.drawArm(matrix, cx, cy, -1, -1, half, gap, thickness, color);
        HitMarker.drawArm(matrix, cx, cy, 1, -1, half, gap, thickness, color);
        HitMarker.drawArm(matrix, cx, cy, 1, 1, half, gap, thickness, color);
        HitMarker.drawArm(matrix, cx, cy, -1, 1, half, gap, thickness, color);
    }

    private static void drawArm(class_4587 matrix, float cx, float cy, int sx, int sy, float half, float gap, float thickness, int color) {
        float nx = (float)sx / (float)Math.sqrt(2.0);
        float ny = (float)sy / (float)Math.sqrt(2.0);
        float x1 = cx + nx * gap;
        float y1 = cy + ny * gap;
        float x2 = cx + nx * half;
        float y2 = cy + ny * half;
        Render2D.drawLine(matrix, x1, y1, x2, y2, thickness, color);
    }

    private record Marker(class_243 pos, long time) {
    }

    private static final class ChamsSoul {
        final class_745 fakePlayer;
        final class_243 startPos;
        final class_243 flyDirection;
        final long startTime;

        ChamsSoul(class_745 fakePlayer, class_243 startPos, class_243 flyDirection, long startTime) {
            this.fakePlayer = fakePlayer;
            this.startPos = startPos;
            this.flyDirection = flyDirection;
            this.startTime = startTime;
        }
    }
}
