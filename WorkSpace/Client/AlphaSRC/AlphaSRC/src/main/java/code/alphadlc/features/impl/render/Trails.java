/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_746
 *  net.minecraft.class_9801
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_9801;

public class Trails
extends Module {
    private final MultiSelectSetting targetsSetting = new MultiSelectSetting("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u044b\u0432\u0430\u0442\u044c \u043d\u0430", "trails.targets.desc").value("\u0421\u0435\u0431\u0435", "\u0414\u0440\u0443\u0437\u044c\u044f\u0445", "\u0418\u0433\u0440\u043e\u043a\u0430\u0445").selected("\u0421\u0435\u0431\u0435", "\u0414\u0440\u0443\u0437\u044c\u044f\u0445");
    private final SliderSettings lengthSetting = new SliderSettings("\u0414\u043b\u0438\u043d\u0430", "trails.length.desc").range(0.1f, 5.0f).setValue(2.5f).step(0.1f);
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u0441\u043b\u0435\u0434\u0430").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB());
    private final Map<Integer, List<Trail>> trailsByEntity = new HashMap<Integer, List<Trail>>();

    public Trails() {
        super("Trails", "Trails", ModuleCategory.RENDER);
        this.setup(this.targetsSetting, this.lengthSetting, this.color);
    }

    @Override
    public void deactivate() {
        this.trailsByEntity.clear();
        super.deactivate();
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent event) {
        if (Trails.mc.field_1687 == null || Trails.mc.field_1724 == null) {
            return;
        }
        float partialTicks = event.getPartialTicks();
        float lengthValue = this.lengthSetting.getValue() * 1000.0f;
        int themeColor = this.color.getColor();
        ArrayList<TrailQuad> quadsToRender = new ArrayList<TrailQuad>();
        ArrayList<class_746> players = new ArrayList<class_746>(Trails.mc.field_1687.method_18456());
        if (!players.contains(Trails.mc.field_1724)) {
            players.add(Trails.mc.field_1724);
        }
        for (class_1297 class_12972 : players) {
            if (!this.isValidTarget(class_12972) || class_12972 == Trails.mc.field_1724 && Trails.mc.field_1690.method_31044().method_31034() || !(class_12972 instanceof class_1309)) continue;
            class_1309 living = (class_1309)class_12972;
            double posX = living.field_6014 + (living.method_23317() - living.field_6014) * (double)partialTicks;
            double posY = living.field_6036 + (living.method_23318() - living.field_6036) * (double)partialTicks + 0.05 - (living.method_6128() ? 0.15 : 0.0);
            double posZ = living.field_5969 + (living.method_23321() - living.field_5969) * (double)partialTicks;
            List list = this.trailsByEntity.computeIfAbsent(living.method_5628(), id -> new ArrayList());
            list.add(new Trail(posX, posY, posZ));
            float height = living.method_17682() * (living.method_18276() ? 0.8f : 1.0f);
            Trail prevTrail = null;
            boolean isFirst = true;
            int prevColor = 0;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Trail trail = (Trail)it.next();
                if ((float)(System.currentTimeMillis() - trail.time) > lengthValue) {
                    it.remove();
                    continue;
                }
                trail.alpha = Math.clamp((float)(System.currentTimeMillis() - trail.time) / lengthValue, 0.0f, 1.0f) * 255.0f;
                int color = this.applyOpacity(themeColor, (int)((float)(255 * (255 - Math.round(trail.alpha))) / 220.0f));
                if (!isFirst && prevTrail != null) {
                    quadsToRender.add(new TrailQuad(prevTrail.x, prevTrail.y, prevTrail.z, prevTrail.x, prevTrail.y + (double)height, prevTrail.z, trail.x, trail.y + (double)height, trail.z, trail.x, trail.y, trail.z, prevColor, color));
                }
                isFirst = false;
                prevTrail = trail;
                prevColor = color;
            }
            if (!list.isEmpty()) continue;
            this.trailsByEntity.remove(living.method_5628());
        }
        if (!quadsToRender.isEmpty()) {
            class_4587.class_4665 entry = event.getStack().method_23760();
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            class_289 class_2892 = class_289.method_1348();
            class_287 buffer = class_2892.method_60827(class_293.class_5596.field_27382, class_290.field_1576);
            for (TrailQuad q : quadsToRender) {
                buffer.method_22918(entry.method_23761(), (float)q.x0, (float)q.y0, (float)q.z0).method_39415(q.color1);
                buffer.method_22918(entry.method_23761(), (float)q.x1, (float)q.y1, (float)q.z1).method_39415(q.color1);
                buffer.method_22918(entry.method_23761(), (float)q.x2, (float)q.y2, (float)q.z2).method_39415(q.color2);
                buffer.method_22918(entry.method_23761(), (float)q.x3, (float)q.y3, (float)q.z3).method_39415(q.color2);
            }
            class_286.method_43433((class_9801)buffer.method_60800());
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
        }
    }

    private boolean isValidTarget(class_1297 entity) {
        if (!entity.method_5805()) {
            return false;
        }
        if (entity.method_5767()) {
            return false;
        }
        if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            if (entity == Trails.mc.field_1724) {
                return this.targetsSetting.isSelected("\u0421\u0435\u0431\u0435");
            }
            if (FriendUtils.isFriend((class_1297)player)) {
                return this.targetsSetting.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f\u0445");
            }
            return this.targetsSetting.isSelected("\u0418\u0433\u0440\u043e\u043a\u0430\u0445");
        }
        return false;
    }

    private int applyOpacity(int color, int alpha) {
        return Math.clamp((long)alpha, 0, 255) << 24 | color & 0xFFFFFF;
    }

    public static Trails getInstance() {
        return Instance.get(Trails.class);
    }

    private static final class Trail {
        final double x;
        final double y;
        final double z;
        float alpha;
        final long time;

        public Trail(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = System.currentTimeMillis();
        }
    }

    private record TrailQuad(double x0, double y0, double z0, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, int color1, int color2) {
    }
}
