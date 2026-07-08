/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1058
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1531
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector4d
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.math.projection.Projection;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1058;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1531;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4d;

public class Esp
extends Module {
    private static final class_2960 BLOOM_TEXTURE = class_2960.method_60654((String)"textures/bloom.png");
    private static final float BOX_EPSILON = 0.001f;
    private static final int[] BLOOM_PASS_ALPHA = new int[]{55, 110};
    private static final float[] BLOOM_PASS_EXPAND = new float[]{1.0f, 0.45f};
    private static final int[] GLOW_LAYER_ALPHA = new int[]{45, 85, 140, 235};
    private static final float[] GLOW_LAYER_WIDTH_MUL = new float[]{3.2f, 2.0f, 1.1f, 0.5f};
    private static final Vector3f LINE_START = new Vector3f();
    private static final Vector3f LINE_END = new Vector3f();
    private final List<class_1657> players = new ArrayList<class_1657>();
    private List<class_238> glowBatchBoxes = new ArrayList<class_238>(64);
    private List<Integer> glowBatchColors = new ArrayList<Integer>(64);
    private Vector4d vecScratch = new Vector4d();
    public static MultiSelectSetting entityType = new MultiSelectSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c", "\u0421\u0443\u0449\u043d\u043e\u0441\u0442\u0438, \u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0431\u0443\u0434\u0443\u0442 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c\u0441\u044f").value("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", "\u041c\u043e\u0431\u043e\u0432", "\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u043d\u0438", "\u0414\u0440\u0443\u0437\u044c\u044f").selected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
    public static RadioSetting boxType = new RadioSetting("\u0420\u0435\u0436\u0438\u043c \u0431\u043e\u043a\u0441\u0430", "\u0422\u0438\u043f \u0431\u043e\u043a\u0441\u0430", new String[]{"\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041a\u043e\u0440\u043d\u0435\u0440", "\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435"}, "\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435");
    public final SliderSettings glowThickness = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u0441\u0432\u0435\u0442\u044f\u0449\u0435\u0433\u043e\u0441\u044f \u043a\u043e\u043d\u0442\u0443\u0440\u0430").setValue(3.0f).range(1.0f, 6.0f).step(0.5f).visible(() -> boxType.get().equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435"));
    public final BooleanSetting potions = new BooleanSetting("\u0417\u0435\u043b\u044c\u044f", "\u041a\u043e\u043c\u043f\u0430\u043a\u0442\u043d\u044b\u0435 \u043f\u0430\u043d\u0435\u043b\u0438 \u044d\u0444\u0444\u0435\u043a\u0442\u043e\u0432 \u0441\u043f\u0440\u0430\u0432\u0430 \u043e\u0442 \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430").setValue(false);
    public final SliderSettings potionScale = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u0437\u0435\u043b\u0438\u0439", "\u041c\u0430\u0441\u0448\u0442\u0430\u0431 \u043f\u0430\u043d\u0435\u043b\u0435\u0439 \u044d\u0444\u0444\u0435\u043a\u0442\u043e\u0432").setValue(1.0f).range(0.5f, 2.0f).step(0.05f).visible(() -> this.potions.isValue());
    static ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442 \u0431\u043e\u043a\u0441\u043e\u0432", "\u0426\u0432\u0435\u0442 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u043e\u0432").value(new Color(255, 255, 255, 55).getRGB()).presets(new Color(0, 246, 255, 255).getRGB(), new Color(183, 1, 195, 255).getRGB(), new Color(255, 60, 0, 255).getRGB(), new Color(171, 253, 0, 255).getRGB());
    private static final int MAX_POTION_PANELS = 6;
    private static final float POTION_BASE_PANEL_HEIGHT = 11.0f;
    private static final float POTION_BASE_ICON_SIZE = 8.0f;
    private static final float POTION_BASE_PANEL_GAP = 1.5f;
    private static final float POTION_BASE_PADDING_X = 3.0f;
    private static final float POTION_BASE_ICON_TEXT_GAP = 2.5f;
    private static final float POTION_BASE_VERTICAL_PAD = 2.0f;
    private static final float POTION_BASE_FONT_SIZE = 10.0f;
    private static final int POTION_TEXT_COLOR = -1;
    private static final int POTION_MIN_FONT_SIZE = 8;
    private static final float POTION_REFERENCE_BOX_HEIGHT = 68.0f;
    private static final float POTION_MIN_DISTANCE_SCALE = 0.32f;
    private static final float POTION_MAX_DISTANCE_SCALE = 1.0f;

    public static Esp getInstance() {
        return Instance.get(Esp.class);
    }

    public Esp() {
        super("Esp", "Esp", ModuleCategory.ESP);
        this.setup(entityType, boxType, this.glowThickness, this.potions, this.potionScale, color);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.players.clear();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.players.clear();
        if (Esp.mc.field_1687 != null) {
            Esp.mc.field_1687.method_18456().stream().filter(player -> player != Esp.mc.field_1724).filter(player -> player.method_5797() == null || !player.method_5797().getString().startsWith("Ghost_")).forEach(this.players::add);
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (Esp.mc.field_1724 == null || Esp.mc.field_1687 == null) {
            return;
        }
        if (Esp.mc.field_1755 instanceof MenuScreen) {
            return;
        }
        if (mc.method_1561() == null || Esp.mc.method_1561().field_4686 == null) {
            return;
        }
        if (!boxType.get().equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435")) {
            return;
        }
        boolean selPlayers = entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        boolean selFriends = entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f");
        boolean selMobs = entityType.isSelected("\u041c\u043e\u0431\u043e\u0432");
        boolean selAnimals = entityType.isSelected("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445");
        boolean selItems = entityType.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
        boolean selArmor = entityType.isSelected("\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u043d\u0438");
        if (!(selPlayers || selMobs || selAnimals || selItems || selArmor || selFriends)) {
            return;
        }
        this.glowBatchBoxes.clear();
        this.glowBatchColors.clear();
        float tickDelta = mc.method_61966().method_60637(false);
        double renderDistance = Math.sqrt(Projection.getMaxRenderDistanceSq());
        class_238 searchBox = Esp.mc.field_1724.method_5829().method_1014(renderDistance);
        if (selPlayers || selFriends) {
            this.collectPlayerGlowBoxes(tickDelta, selPlayers, selFriends);
        }
        if (selMobs) {
            this.collectLivingGlowBoxes(tickDelta, searchBox, class_1308.class, entity -> !(entity instanceof class_1429));
        }
        if (selAnimals) {
            this.collectLivingGlowBoxes(tickDelta, searchBox, class_1429.class, entity -> true);
        }
        if (selItems) {
            this.collectEntityGlowBoxes(searchBox, class_1542.class);
        }
        if (selArmor) {
            this.collectLivingGlowBoxes(tickDelta, searchBox, class_1531.class, entity -> true);
        }
        if (this.glowBatchBoxes.isEmpty()) {
            return;
        }
        this.renderGlowBatch(e.getStack(), this.glowThickness.getValue());
    }

    private void collectPlayerGlowBoxes(float tickDelta, boolean selPlayers, boolean selFriends) {
        int enemyRgb = color.getColorWithAlpha() & 0xFFFFFF;
        int friendRgb = ColorAssist.getFriendColor() & 0xFFFFFF;
        for (class_1657 player : this.players) {
            class_238 box;
            if (player == null || player.method_5797() != null && player.method_5797().getString().startsWith("Ghost_")) continue;
            boolean friend = FriendUtils.isFriend((class_1297)player);
            if (!selPlayers && (!selFriends || !friend) || (box = this.getInterpolatedBox((class_1309)player, tickDelta)) == null) continue;
            this.glowBatchBoxes.add(box);
            this.glowBatchColors.add(friend ? friendRgb : enemyRgb);
        }
    }

    private <T extends class_1309> void collectLivingGlowBoxes(float tickDelta, class_238 searchBox, Class<T> type, Predicate<T> filter) {
        int enemyRgb = color.getColorWithAlpha() & 0xFFFFFF;
        for (class_1309 entity : Esp.mc.field_1687.method_8390(type, searchBox, filter::test)) {
            class_238 box = this.getInterpolatedBox(entity, tickDelta);
            if (box == null) continue;
            this.glowBatchBoxes.add(box);
            this.glowBatchColors.add(enemyRgb);
        }
    }

    private <T extends class_1297> void collectEntityGlowBoxes(class_238 searchBox, Class<T> type) {
        int enemyRgb = color.getColorWithAlpha() & 0xFFFFFF;
        for (class_1297 entity2 : Esp.mc.field_1687.method_8390(type, searchBox, entity -> true)) {
            class_238 box;
            if (!Projection.isWithinRenderDistance(entity2) || !Projection.canSee(box = entity2.method_5829())) continue;
            this.glowBatchBoxes.add(box);
            this.glowBatchColors.add(enemyRgb);
        }
    }

    private class_238 getInterpolatedBox(class_1309 entity, float tickDelta) {
        if (!Projection.isWithinRenderDistance((class_1297)entity)) {
            return null;
        }
        double interpX = class_3532.method_16436((double)tickDelta, (double)entity.field_6014, (double)entity.method_23317());
        double interpY = class_3532.method_16436((double)tickDelta, (double)entity.field_6036, (double)entity.method_23318());
        double interpZ = class_3532.method_16436((double)tickDelta, (double)entity.field_5969, (double)entity.method_23321());
        class_238 box = entity.method_18377(entity.method_18376()).method_30231(interpX, interpY, interpZ);
        return Projection.canSee(box) ? box : null;
    }

    private void renderGlowBatch(class_4587 matrix, float thickness) {
        Matrix4f positionMatrix = matrix.method_23760().method_23761();
        class_4587.class_4665 lineEntry = matrix.method_23760();
        this.batchDrawWorldBloom(positionMatrix, thickness);
        this.batchDrawBoxFillGradient(positionMatrix);
        for (int layer = 0; layer < GLOW_LAYER_ALPHA.length; ++layer) {
            float width = layer == GLOW_LAYER_ALPHA.length - 1 ? Math.max(1.0f, thickness * GLOW_LAYER_WIDTH_MUL[layer]) : thickness * GLOW_LAYER_WIDTH_MUL[layer];
            this.batchDrawGlowLines(lineEntry, GLOW_LAYER_ALPHA[layer], width);
        }
    }

    private void batchDrawWorldBloom(Matrix4f matrix, float thickness) {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShaderTexture((int)0, (class_2960)BLOOM_TEXTURE);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        for (int pass = 0; pass < BLOOM_PASS_ALPHA.length; ++pass) {
            int passAlpha = BLOOM_PASS_ALPHA[pass];
            float passExpand = BLOOM_PASS_EXPAND[pass];
            class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
            for (int i = 0; i < this.glowBatchBoxes.size(); ++i) {
                this.appendBloomQuads(buffer, matrix, this.glowBatchBoxes.get(i), this.glowBatchColors.get(i), passAlpha, thickness, passExpand);
            }
            class_286.method_43433((class_9801)buffer.method_60800());
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void appendBloomQuads(class_287 buffer, Matrix4f matrix, class_238 box, int rgb, int passAlpha, float thickness, float passExpand) {
        class_238 b = box.method_1014((double)0.001f);
        float x1 = (float)b.field_1323;
        float y1 = (float)b.field_1322;
        float z1 = (float)b.field_1321;
        float x2 = (float)b.field_1320;
        float y2 = (float)b.field_1325;
        float z2 = (float)b.field_1324;
        float mxz = (float)((b.field_1320 - b.field_1323) * 0.6) + thickness * 0.05f;
        float my = (float)((b.field_1325 - b.field_1322) * 0.12) + thickness * 0.05f;
        int c = rgb | passAlpha << 24;
        float ex = mxz * passExpand;
        float ey = my * passExpand;
        float ax1 = x1 - ex;
        float ax2 = x2 + ex;
        float ay1 = y1 - ey;
        float ay2 = y2 + ey;
        float az1 = z1 - ex;
        float az2 = z2 + ex;
        buffer.method_22918(matrix, ax1, ay1, z1).method_22913(0.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, ax2, ay1, z1).method_22913(1.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, ax2, ay2, z1).method_22913(1.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, ax1, ay2, z1).method_22913(0.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, ax1, ay1, z2).method_22913(0.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, ax2, ay1, z2).method_22913(1.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, ax2, ay2, z2).method_22913(1.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, ax1, ay2, z2).method_22913(0.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, x1, ay1, az1).method_22913(0.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, x1, ay1, az2).method_22913(1.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, x1, ay2, az2).method_22913(1.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, x1, ay2, az1).method_22913(0.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, x2, ay1, az1).method_22913(0.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, x2, ay1, az2).method_22913(1.0f, 1.0f).method_39415(c);
        buffer.method_22918(matrix, x2, ay2, az2).method_22913(1.0f, 0.0f).method_39415(c);
        buffer.method_22918(matrix, x2, ay2, az1).method_22913(0.0f, 0.0f).method_39415(c);
    }

    private void batchDrawBoxFillGradient(Matrix4f matrix) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int i = 0; i < this.glowBatchBoxes.size(); ++i) {
            int rgb = this.glowBatchColors.get(i);
            this.appendFillGradientQuads(buffer, matrix, this.glowBatchBoxes.get(i), rgb | 0x46000000, rgb);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    private void appendFillGradientQuads(class_287 buffer, Matrix4f matrix, class_238 box, int bottom, int top) {
        class_238 b = box.method_1014((double)0.001f);
        float x1 = (float)b.field_1323;
        float y1 = (float)b.field_1322;
        float z1 = (float)b.field_1321;
        float x2 = (float)b.field_1320;
        float y2 = (float)b.field_1325;
        float z2 = (float)b.field_1324;
        buffer.method_22918(matrix, x1, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x2, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x2, y1, z2).method_39415(bottom);
        buffer.method_22918(matrix, x1, y1, z2).method_39415(bottom);
        buffer.method_22918(matrix, x1, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x1, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x2, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x2, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x1, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x1, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x2, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x2, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x1, y1, z2).method_39415(bottom);
        buffer.method_22918(matrix, x2, y1, z2).method_39415(bottom);
        buffer.method_22918(matrix, x2, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x1, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x1, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x1, y1, z2).method_39415(bottom);
        buffer.method_22918(matrix, x1, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x1, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x2, y1, z1).method_39415(bottom);
        buffer.method_22918(matrix, x2, y2, z1).method_39415(top);
        buffer.method_22918(matrix, x2, y2, z2).method_39415(top);
        buffer.method_22918(matrix, x2, y1, z2).method_39415(bottom);
    }

    private void batchDrawGlowLines(class_4587.class_4665 entry, int layerAlpha, float width) {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53864);
        RenderSystem.lineWidth((float)width);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
        for (int i = 0; i < this.glowBatchBoxes.size(); ++i) {
            int lineColor = this.glowBatchColors.get(i) | layerAlpha << 24;
            this.appendGlowBoxLines(entry, buffer, this.glowBatchBoxes.get(i), lineColor);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void appendGlowBoxLines(class_4587.class_4665 entry, class_287 buffer, class_238 box, int color) {
        class_238 b = box.method_1014((double)0.001f);
        int topColor = color & 0xFFFFFF;
        float minX = (float)b.field_1323;
        float minY = (float)b.field_1322;
        float minZ = (float)b.field_1321;
        float maxX = (float)b.field_1320;
        float maxY = (float)b.field_1325;
        float maxZ = (float)b.field_1324;
        this.appendGlowLine(entry, buffer, minX, minY, minZ, maxX, minY, minZ, color, color);
        this.appendGlowLine(entry, buffer, maxX, minY, minZ, maxX, minY, maxZ, color, color);
        this.appendGlowLine(entry, buffer, maxX, minY, maxZ, minX, minY, maxZ, color, color);
        this.appendGlowLine(entry, buffer, minX, minY, maxZ, minX, minY, minZ, color, color);
        this.appendGlowLine(entry, buffer, minX, minY, minZ, minX, maxY, minZ, color, topColor);
        this.appendGlowLine(entry, buffer, maxX, minY, minZ, maxX, maxY, minZ, color, topColor);
        this.appendGlowLine(entry, buffer, maxX, minY, maxZ, maxX, maxY, maxZ, color, topColor);
        this.appendGlowLine(entry, buffer, minX, minY, maxZ, minX, maxY, maxZ, color, topColor);
    }

    private void appendGlowLine(class_4587.class_4665 entry, class_287 buffer, float x1, float y1, float z1, float x2, float y2, float z2, int startColor, int endColor) {
        LINE_START.set(x1, y1, z1);
        LINE_END.set(x2, y2, z2);
        Render3D.vertexLine(entry, (class_4588)buffer, LINE_START, LINE_END, startColor, endColor);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (Esp.mc.field_1755 instanceof MenuScreen) {
            return;
        }
        if (Esp.mc.field_1687 == null || Esp.mc.field_1724 == null) {
            return;
        }
        boolean selPlayers = entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        boolean selFriends = entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f");
        boolean selMobs = entityType.isSelected("\u041c\u043e\u0431\u043e\u0432");
        boolean selAnimals = entityType.isSelected("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445");
        boolean selItems = entityType.isSelected("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b");
        boolean selArmor = entityType.isSelected("\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u043d\u0438");
        if (!(selPlayers || selFriends || selMobs || selAnimals || selItems || selArmor || this.potions.isValue())) {
            return;
        }
        class_4587 matrix = e.getDrawContext().method_51448();
        RenderSystem.disableDepthTest();
        matrix.method_22903();
        matrix.method_46416(0.0f, 0.0f, -1000.0f);
        if (selPlayers || selFriends || this.potions.isValue()) {
            for (class_1657 player : this.players) {
                class_243 potionAnchor;
                boolean drawBox;
                if (player == null || player.method_5797() != null && player.method_5797().getString().startsWith("Ghost_")) continue;
                boolean friend = FriendUtils.isFriend((class_1297)player);
                boolean bl = drawBox = (selPlayers || selFriends && friend) && !boxType.get().equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435");
                if (!drawBox && !this.potions.isValue() || !Projection.isWithinRenderDistance((class_1297)player) || !Projection.isInFrontOfCamera(potionAnchor = player.method_30950(mc.method_61966().method_60637(false)).method_1031(0.0, (double)player.method_17682() * 0.5, 0.0)) || !Projection.canSee(player.method_5829())) continue;
                boolean onScreen = Projection.fillVector4D((class_1297)player, this.vecScratch);
                if (this.potions.isValue() && onScreen && Esp.isValidPotionScreenVec(this.vecScratch)) {
                    this.drawPotionPanels(matrix, (class_1309)player, this.vecScratch);
                }
                if (!drawBox || !onScreen) continue;
                this.drawBox(friend, this.vecScratch, (class_1297)player);
            }
        }
        if (boxType.get().equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435")) {
            matrix.method_22909();
            RenderSystem.enableDepthTest();
            return;
        }
        if (!(selPlayers || selFriends || selMobs || selAnimals || selItems || selArmor)) {
            matrix.method_22909();
            RenderSystem.enableDepthTest();
            return;
        }
        double renderDistance = Math.sqrt(Projection.getMaxRenderDistanceSq());
        class_238 searchBox = Esp.mc.field_1724.method_5829().method_1014(renderDistance);
        if (selMobs) {
            for (class_1308 mob : Esp.mc.field_1687.method_8390(class_1308.class, searchBox, entity -> !(entity instanceof class_1429))) {
                if (!Projection.isWithinRenderDistance((class_1297)mob) || !Projection.canSee(mob.method_5829()) || !Projection.fillVector4D((class_1297)mob, this.vecScratch)) continue;
                this.drawBox(false, this.vecScratch, (class_1297)mob);
            }
        }
        if (selAnimals) {
            for (class_1429 animal : Esp.mc.field_1687.method_8390(class_1429.class, searchBox, entity -> true)) {
                if (!Projection.isWithinRenderDistance((class_1297)animal) || !Projection.canSee(animal.method_5829()) || !Projection.fillVector4D((class_1297)animal, this.vecScratch)) continue;
                this.drawBox(false, this.vecScratch, (class_1297)animal);
            }
        }
        if (selItems) {
            for (class_1542 item : Esp.mc.field_1687.method_8390(class_1542.class, searchBox, entity -> true)) {
                if (!Projection.isWithinRenderDistance((class_1297)item) || !Projection.canSee(item.method_5829()) || !Projection.fillVector4D((class_1297)item, this.vecScratch)) continue;
                this.drawBox(false, this.vecScratch, (class_1297)item);
            }
        }
        if (selArmor) {
            for (class_1531 armorStand : Esp.mc.field_1687.method_8390(class_1531.class, searchBox, entity -> true)) {
                if (!Projection.isWithinRenderDistance((class_1297)armorStand) || !Projection.canSee(armorStand.method_5829()) || !Projection.fillVector4D((class_1297)armorStand, this.vecScratch)) continue;
                this.drawBox(false, this.vecScratch, (class_1297)armorStand);
            }
        }
        matrix.method_22909();
        RenderSystem.enableDepthTest();
    }

    public void renderPreviewPotions(class_4587 matrix, class_1309 entity, Vector4d vec) {
        if (!this.potions.isValue() || entity == null || vec == null) {
            return;
        }
        this.drawPotionPanels(matrix, entity, vec);
    }

    private void drawPotionPanels(class_4587 matrix, class_1309 entity, Vector4d vec) {
        float rowY;
        float panelWidth;
        float contentWidth;
        float textWidth;
        String label;
        String timer;
        int level;
        String name;
        int duration;
        Collection effects = entity.method_6026();
        if (effects.isEmpty()) {
            return;
        }
        float boxHeight = (float)Math.max(1.0, vec.w - vec.y);
        float userScale = class_3532.method_15363((float)this.potionScale.getValue(), (float)0.5f, (float)2.0f);
        float distanceScale = class_3532.method_15363((float)(boxHeight / 68.0f), (float)0.32f, (float)1.0f);
        float panelScale = userScale * distanceScale;
        int fontSize = class_3532.method_15340((int)class_3532.method_15375((float)(10.0f * panelScale)), (int)8, (int)20);
        FontRenderer font = Fonts.getSize(fontSize, Fonts.Type.DEFAULT);
        float iconSize = Math.max(6.0f, 8.0f * panelScale);
        float panelGap = 1.5f * panelScale;
        float paddingX = 3.0f * panelScale;
        float iconTextGap = 2.5f * panelScale;
        float verticalPad = 2.0f * panelScale;
        float textHeight = font.getStringHeight("Ag");
        float panelHeight = Math.max(8.0f, Math.max(11.0f * panelScale, textHeight + verticalPad * 2.0f));
        float rowContentHeight = Math.max(iconSize, textHeight);
        float panelX = (float)vec.z + 3.0f * distanceScale;
        float panelY = (float)vec.y;
        int drawn = 0;
        for (class_1293 effect : effects) {
            if (drawn >= 6) break;
            duration = effect.method_5584();
            if (!Esp.isRenderableDuration(duration)) continue;
            name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
            level = effect.method_5578() + 1;
            timer = Esp.formatPotionDuration(duration);
            label = name + " " + level + "  " + timer;
            textWidth = font.getStringWidth(label);
            contentWidth = iconSize + iconTextGap + textWidth;
            panelWidth = paddingX * 2.0f + contentWidth;
            rowY = panelY + (float)drawn * (panelHeight + panelGap);
            Render2D.drawQuad(panelX, rowY, panelWidth, panelHeight, Esp.potionPanelBackground(effect));
            ++drawn;
        }
        Render2D.flushQuads(matrix);
        drawn = 0;
        for (class_1293 effect : effects) {
            if (drawn >= 6) break;
            duration = effect.method_5584();
            if (!Esp.isRenderableDuration(duration)) continue;
            name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
            level = effect.method_5578() + 1;
            timer = Esp.formatPotionDuration(duration);
            label = name + " " + level + "  " + timer;
            textWidth = font.getStringWidth(label);
            contentWidth = iconSize + iconTextGap + textWidth;
            panelWidth = paddingX * 2.0f + contentWidth;
            rowY = panelY + (float)drawn * (panelHeight + panelGap);
            float contentX = panelX + paddingX + (panelWidth - paddingX * 2.0f - contentWidth) * 0.5f;
            float contentY = rowY + (panelHeight - rowContentHeight) * 0.5f;
            class_1058 sprite = mc.method_18505().method_18663(effect.method_5579());
            float iconY = contentY + (rowContentHeight - iconSize) * 0.5f;
            Render2D.drawSprite(matrix, sprite, contentX, iconY, iconSize, (int)iconSize, -1);
            float textX = contentX + iconSize + iconTextGap;
            float textY = font.getCenteredY(contentY, rowContentHeight, label);
            font.drawFlatString(matrix, label, textX, textY, -1);
            ++drawn;
        }
    }

    private static boolean isValidPotionScreenVec(Vector4d vec) {
        if (vec == null) {
            return false;
        }
        float width = (float)(vec.z - vec.x);
        float height = (float)(vec.w - vec.y);
        return width > 0.5f && height > 0.5f;
    }

    private static int potionPanelBackground(class_1293 effect) {
        int effectRgb = ((class_1291)effect.method_5579().comp_349()).method_5556() & 0xFFFFFF;
        int darkened = ColorAssist.multBright(effectRgb | 0xFF000000, 2.55f);
        return ColorAssist.multAlpha(darkened, 0.38f);
    }

    private static boolean isRenderableDuration(int ticks) {
        return ticks == -1 || ticks > 0;
    }

    private static String formatPotionDuration(int ticks) {
        if (ticks == -1 || ticks > 72000) {
            return "**:**";
        }
        int totalSeconds = ticks / 20;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + ":" + String.format("%02d", seconds);
    }

    private void drawBox(boolean friend, Vector4d vec, class_1297 entity) {
        int baseColor = friend ? ColorAssist.getFriendColor() : color.getColorWithAlpha();
        int alpha = (int)(color.getAlpha() * 255.0f);
        int client = baseColor & 0xFFFFFF | alpha << 24;
        int black = ColorAssist.HALF_BLACK;
        float posX = (float)vec.x;
        float posY = (float)vec.y;
        float endPosX = (float)vec.z;
        float endPosY = (float)vec.w;
        float size = (endPosX - posX) / 3.0f;
        if (boxType.get().equals("\u041a\u043e\u0440\u043d\u0435\u0440")) {
            Render2D.drawQuad(posX - 0.5f, posY - 0.5f, size, 1.0f, client);
            Render2D.drawQuad(posX - 0.5f, posY, 1.0f, size + 0.5f, client);
            Render2D.drawQuad(posX - 0.5f, endPosY - size - 0.5f, 1.0f, size, client);
            Render2D.drawQuad(posX - 0.5f, endPosY - 0.5f, size, 1.0f, client);
            Render2D.drawQuad(endPosX - size + 1.0f, posY - 0.5f, size, 1.0f, client);
            Render2D.drawQuad(endPosX - 0.5f, posY, 1.0f, size + 0.5f, client);
            Render2D.drawQuad(endPosX - 0.5f, endPosY - size - 0.5f, 1.0f, size, client);
            Render2D.drawQuad(endPosX - size + 1.0f, endPosY - 0.5f, size, 1.0f, client);
        } else if (boxType.get().equals("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            Render2D.drawQuad(posX - 0.5f, posY - 0.5f, endPosX - posX + 1.0f, 1.0f, client);
            Render2D.drawQuad(posX - 0.5f, posY - 0.5f, 1.0f, endPosY - posY + 1.0f, client);
            Render2D.drawQuad(posX - 0.5f, endPosY - 0.5f, endPosX - posX + 1.0f, 1.0f, client);
            Render2D.drawQuad(endPosX - 0.5f, posY - 0.5f, 1.0f, endPosY - posY + 1.0f, client);
        }
    }

    public void renderPreviewBox(Vector4d vec, boolean friend) {
        if (vec == null) {
            return;
        }
        if (boxType.get().equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435")) {
            return;
        }
        this.drawBox(friend, vec, null);
    }

    public void renderPreviewBox(class_4587 matrix, Vector4d vec, boolean friend, float yaw, float pitch) {
        if (vec == null) {
            return;
        }
        String mode = boxType.get();
        if (mode.equals("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435")) {
            this.drawPreview3dBox(matrix, vec, friend, yaw, pitch, true);
            return;
        }
        this.drawBox(friend, vec, null);
    }

    private int[] previewGlowColors(int rgb, int alpha) {
        int bottom = rgb | alpha << 24;
        int top = rgb;
        return new int[]{top, top, bottom, bottom, top, top, bottom, bottom};
    }

    private void drawPreviewGlowLines(class_4587 matrix, float[] sx, float[] sy, int[][] edges, int[] vertColors, float thickness) {
        Matrix4f m = matrix.method_23760().method_23761();
        float half = thickness * 0.5f;
        float shaderAlpha = RenderSystem.getShaderColor()[3];
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int colorA = ColorAssist.multAlpha(vertColors[a], shaderAlpha);
            int colorB = ColorAssist.multAlpha(vertColors[b], shaderAlpha);
            float dx = sx[b] - sx[a];
            float dy = sy[b] - sy[a];
            float length = (float)Math.sqrt(dx * dx + dy * dy);
            if (length < 1.0E-4f) continue;
            float nx = -dy / length * half;
            float ny = dx / length * half;
            buffer.method_22918(m, sx[a] + nx, sy[a] + ny, 0.0f).method_39415(colorA);
            buffer.method_22918(m, sx[a] - nx, sy[a] - ny, 0.0f).method_39415(colorA);
            buffer.method_22918(m, sx[b] - nx, sy[b] - ny, 0.0f).method_39415(colorB);
            buffer.method_22918(m, sx[b] + nx, sy[b] + ny, 0.0f).method_39415(colorB);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void drawPreview3dBox(class_4587 matrix, Vector4d vec, boolean friend, float yaw, float pitch, boolean glow) {
        int baseColor = friend ? ColorAssist.getFriendColor() : color.getColorWithAlpha();
        int rgb = baseColor & 0xFFFFFF;
        float posX = (float)vec.x;
        float posY = (float)vec.y;
        float endPosX = (float)vec.z;
        float endPosY = (float)vec.w;
        float cx = (posX + endPosX) * 0.5f;
        float cy = (posY + endPosY) * 0.5f;
        float hw = (endPosX - posX) * 0.5f;
        float hh = (endPosY - posY) * 0.5f;
        float hd = hw;
        float[] lx = new float[]{-hw, hw, hw, -hw, -hw, hw, hw, -hw};
        float[] ly = new float[]{-hh, -hh, hh, hh, -hh, -hh, hh, hh};
        float[] lz = new float[]{-hd, -hd, -hd, -hd, hd, hd, hd, hd};
        double yawR = Math.toRadians(yaw);
        double pitchR = Math.toRadians(pitch);
        float cosY = (float)Math.cos(yawR);
        float sinY = (float)Math.sin(yawR);
        float cosP = (float)Math.cos(pitchR);
        float sinP = (float)Math.sin(pitchR);
        float[] sx = new float[8];
        float[] sy = new float[8];
        for (int i = 0; i < 8; ++i) {
            float rx = lx[i] * cosY + lz[i] * sinY;
            float rz = -lx[i] * sinY + lz[i] * cosY;
            float ry = ly[i] * cosP - rz * sinP;
            sx[i] = cx + rx;
            sy[i] = cy + ry;
        }
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
        if (glow) {
            float t = this.glowThickness.getValue();
            float left = posX;
            float right = endPosX;
            float topY = posY;
            float bottomY = endPosY;
            float boxWidth = right - left;
            float boxHeight = bottomY - topY;
            class_2960 bloomTexture = class_2960.method_60654((String)"textures/bloom.png");
            Matrix4f bloomMatrix = matrix.method_23760().method_23761();
            float bloomShaderAlpha = RenderSystem.getShaderColor()[3];
            float bloomMarginX = boxWidth * 1.15f + t * 6.0f;
            float bloomMarginY = boxHeight * 0.22f + t * 6.0f;
            int[] bloomAlpha = new int[]{60, 110, 190};
            float[] bloomSpread = new float[]{1.0f, 0.62f, 0.3f};
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.setShaderTexture((int)0, (class_2960)bloomTexture);
            RenderSystem.setShader((class_10156)class_10142.field_53880);
            for (int p = 0; p < bloomAlpha.length; ++p) {
                float gx1 = left - bloomMarginX * bloomSpread[p];
                float gx2 = right + bloomMarginX * bloomSpread[p];
                float gy1 = topY - bloomMarginY * bloomSpread[p];
                float gy2 = bottomY + bloomMarginY * bloomSpread[p];
                int glowColor = ColorAssist.multAlpha(rgb | bloomAlpha[p] << 24, bloomShaderAlpha);
                class_287 bloomBuffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
                bloomBuffer.method_22918(bloomMatrix, gx1, gy1, 0.0f).method_22913(0.0f, 0.0f).method_39415(glowColor);
                bloomBuffer.method_22918(bloomMatrix, gx1, gy2, 0.0f).method_22913(0.0f, 1.0f).method_39415(glowColor);
                bloomBuffer.method_22918(bloomMatrix, gx2, gy2, 0.0f).method_22913(1.0f, 1.0f).method_39415(glowColor);
                bloomBuffer.method_22918(bloomMatrix, gx2, gy1, 0.0f).method_22913(1.0f, 0.0f).method_39415(glowColor);
                class_286.method_43433((class_9801)bloomBuffer.method_60800());
            }
            RenderSystem.depthMask((boolean)true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            int fillSteps = 12;
            for (int s = 0; s < fillSteps; ++s) {
                float f = (float)s / (float)(fillSteps - 1);
                float stripY = bottomY - boxHeight * ((float)(s + 1) / (float)fillSteps);
                int fillAlpha = (int)(70.0f * (1.0f - f));
                if (fillAlpha <= 1) continue;
                Render2D.drawQuad(left, stripY, boxWidth, boxHeight / (float)fillSteps + 0.75f, rgb | fillAlpha << 24);
            }
            float[] layerThickness = new float[]{t * 3.2f, t * 2.0f, t * 1.1f, Math.max(1.0f, t * 0.5f)};
            int[] layerAlpha = new int[]{45, 85, 140, 235};
            for (int layer = 0; layer < layerThickness.length; ++layer) {
                float lt = layerThickness[layer];
                int baseAlpha = layerAlpha[layer];
                int topAlpha = (int)((float)baseAlpha * 0.12f);
                int midAlpha = (int)((float)baseAlpha * 0.55f);
                Render2D.drawQuad(left - lt * 0.5f, bottomY - lt * 0.5f, boxWidth + lt, lt, rgb | baseAlpha << 24);
                if (topAlpha > 1) {
                    Render2D.drawQuad(left - lt * 0.5f, topY - lt * 0.5f, boxWidth + lt, lt, rgb | topAlpha << 24);
                }
                if (midAlpha <= 1) continue;
                Render2D.drawQuad(left - lt * 0.5f, topY, lt, boxHeight, rgb | midAlpha << 24);
                Render2D.drawQuad(right - lt * 0.5f, topY, lt, boxHeight, rgb | midAlpha << 24);
            }
        } else {
            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                Render2D.drawLine(matrix, sx[a], sy[a], sx[b], sy[b], 2.4f, ColorAssist.HALF_BLACK);
                Render2D.drawLine(matrix, sx[a], sy[a], sx[b], sy[b], 1.2f, rgb | 0xFF000000);
            }
        }
    }
}
