package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.shader.ShaderUtil;

public class Chams extends Module {
   private static final String MODE_FILL = "Заливка";
   private static final String MODE_OUTLINE = "Контур";
   private static final String MODE_BOTH = "Оба";
   private static final String DRAW_NORMAL = "Обычная";
   private static final String DRAW_SHADER = "Шейдер";
   private static final String DRAW_TOON_INK = "Тун-обводка";
   private static final String COLOR_CLIENT = "Клиент";
   private static final String COLOR_CUSTOM = "Свой";
   private static final String COLOR_RAINBOW = "Радуга";
   private static final String SHADER_AUTO = "Из неба";
   private static final String SHADER_PLASMA = "Плазма";
   private static final String SHADER_BALATRO = "Балатро";
   private static final String LEGACY_MODE_FILL = "Fill";
   private static final String LEGACY_MODE_OUTLINE = "Outline";
   private static final String LEGACY_MODE_BOTH = "Both";
   private static final String LEGACY_DRAW_SHADER = "Shader";
   private static final String LEGACY_DRAW_TOON_INK = "Toon Ink";
   private static final String LEGACY_COLOR_CLIENT = "Client";
   private static final String LEGACY_COLOR_CUSTOM = "Custom";
   private static final String LEGACY_COLOR_RAINBOW = "Rainbow";
   private static final String LEGACY_SHADER_PLASMA = "Plasma";
   private static final String LEGACY_SHADER_BALATRO = "Balatro";
   private static Chams instance;
   private final BooleanSetting showPlayers = new BooleanSetting("Игроки", true);
   private final BooleanSetting showMonsters = new BooleanSetting("Монстры", false);
   private final BooleanSetting showFriends = new BooleanSetting("Друзья", true);
   private final BooleanSetting showAnimals = new BooleanSetting("Животные", false);
   private final BooleanSetting showVillagers = new BooleanSetting("Жители", false);
   private final BooleanSetting showCrystals = new BooleanSetting("Кристаллы", true);
   private final BooleanSetting showSelf = new BooleanSetting("Себя", false);
   private final ModeSetting mode = new ModeSetting("Режим", "Оба", new String[]{"Заливка", "Контур", "Оба"});
   private final ModeSetting drawing = new ModeSetting("Отрисовка", "Обычная", new String[]{"Обычная", "Шейдер", "Тун-обводка"});
   private final ModeSetting shaderMode = new ModeSetting("Шейдер", "Из неба", this::isShaderDrawing, new String[]{"Из неба", "Плазма", "Балатро"});
   private final SliderSetting toonSteps = new SliderSetting("Шаги тона", 3.0F, 2.0F, 5.0F, 1.0F, this::isToonInkDrawing);
   private final SliderSetting toonShadow = new SliderSetting("Тень тона", 0.62F, 0.35F, 0.9F, 0.01F, this::isToonInkDrawing);
   private final ColorSetting inkColor = new ColorSetting("Цвет чернил", true, (new Color(20, 20, 24, 255)).getRGB(), this::isToonInkDrawing);
   private final SliderSetting inkWidth = new SliderSetting("Толщина чернил", 2.2F, 1.0F, 4.0F, 0.1F, this::isToonInkDrawing);
   private final ModeSetting colorMode = new ModeSetting("Цвет", "Клиент", new String[]{"Клиент", "Свой", "Радуга"});
   private final ColorSetting fillColor = new ColorSetting("Цвет заливки", true, (new Color(100, 180, 255, 80)).getRGB(), () -> {
      return this.isColorCustom() && this.wantsFill();
   });
   private final ColorSetting outlineColor = new ColorSetting("Цвет контура", true, (new Color(150, 220, 255, 255)).getRGB(), () -> {
      return this.isColorCustom() && this.wantsOutline();
   });
   private final SliderSetting lineWidth = new SliderSetting("Толщина контура", 1.5F, 0.5F, 5.0F, 0.1F, this::wantsOutline);
   private final BooleanSetting glow = new BooleanSetting("Свечение", true);
   private final SliderSetting glowStrength;
   private final SliderSetting glowLayers;
   private final BooleanSetting pulse;
   private final SliderSetting pulseSpeed;
   private final BooleanSetting hurtEffect;
   private final ColorSetting hurtColor;
   private final BooleanSetting customFriendColor;
   private final ColorSetting friendFillColor;
   private final ColorSetting friendOutlineColor;
   private final BooleanSetting hideOriginal;
   private final BooleanSetting hidePlayers;
   private final BooleanSetting hideSelf;
   private final BooleanSetting throughWalls;
   private long enabledAtMs;

   public Chams() {
      super("Chams", "Подсветка сущностей через стены", Category.Visuals);
      BooleanSetting var10008 = this.glow;
      Objects.requireNonNull(var10008);
      this.glowStrength = new SliderSetting("Сила свечения", 2.0F, 1.0F, 5.0F, 0.1F, var10008::get);
      var10008 = this.glow;
      Objects.requireNonNull(var10008);
      this.glowLayers = new SliderSetting("Слои свечения", 3.0F, 1.0F, 6.0F, 1.0F, var10008::get);
      this.pulse = new BooleanSetting("Пульсация", false);
      var10008 = this.pulse;
      Objects.requireNonNull(var10008);
      this.pulseSpeed = new SliderSetting("Скорость пульсации", 2.0F, 0.5F, 5.0F, 0.1F, var10008::get);
      this.hurtEffect = new BooleanSetting("Эффект урона", true);
      Integer var10005 = (new Color(255, 50, 50, 255)).getRGB();
      BooleanSetting var10006 = this.hurtEffect;
      Objects.requireNonNull(var10006);
      this.hurtColor = new ColorSetting("Цвет урона", false, var10005, var10006::get);
      this.customFriendColor = new BooleanSetting("Свой цвет друзей", false);
      var10005 = (new Color(85, 255, 85, 60)).getRGB();
      var10006 = this.customFriendColor;
      Objects.requireNonNull(var10006);
      this.friendFillColor = new ColorSetting("Заливка друзей", true, var10005, var10006::get);
      var10005 = (new Color(100, 255, 100, 255)).getRGB();
      var10006 = this.customFriendColor;
      Objects.requireNonNull(var10006);
      this.friendOutlineColor = new ColorSetting("Контур друзей", true, var10005, var10006::get);
      this.hideOriginal = new BooleanSetting("Скрывать оригинал", false);
      Boolean var10004 = true;
      BooleanSetting var1 = this.hideOriginal;
      Objects.requireNonNull(var1);
      this.hidePlayers = new BooleanSetting("Скрывать игроков", var10004, var1::get);
      var10004 = false;
      var1 = this.hideOriginal;
      Objects.requireNonNull(var1);
      this.hideSelf = new BooleanSetting("Скрывать себя", var10004, var1::get);
      this.throughWalls = new BooleanSetting("Через стены", true);
      this.enabledAtMs = System.currentTimeMillis();
      instance = this;
      this.addSettings(new Setting[]{this.showPlayers, this.showMonsters, this.showFriends, this.showAnimals, this.showVillagers, this.showCrystals, this.showSelf, this.mode, this.drawing, this.shaderMode, this.toonSteps, this.toonShadow, this.inkColor, this.inkWidth, this.colorMode, this.fillColor, this.outlineColor, this.lineWidth, this.glow, this.glowStrength, this.glowLayers, this.pulse, this.pulseSpeed, this.hurtEffect, this.hurtColor, this.customFriendColor, this.friendFillColor, this.friendOutlineColor, this.hideOriginal, this.hidePlayers, this.hideSelf, this.throughWalls});
   }

   public static Chams getInstance() {
      return instance;
   }

   public void onEnable() {
      super.onEnable();
      this.enabledAtMs = System.currentTimeMillis();
      instance = this;
   }

   @EventTarget
   private void onRender3D(EventRender3D event) {
      this.renderWorld(event.getPartialTicks());
   }

   public boolean shouldHideOriginal(Entity entity) {
      if (this.isEnabled() && (Boolean)this.hideOriginal.get() && entity != null) {
         Minecraft var10001 = mc;
         if (entity == Minecraft.player) {
            return (Boolean)this.hideSelf.get() && (Boolean)this.showSelf.get() && this.isThirdPersonView();
         } else if (!(entity instanceof PlayerEntity)) {
            return false;
         } else {
            return (Boolean)this.hidePlayers.get() && this.isChamsTarget(entity);
         }
      } else {
         return false;
      }
   }

   public boolean isChamsTarget(Entity entity) {
      if (this.isEnabled() && entity != null && !entity.removed) {
         if (entity instanceof EnderCrystalEntity) {
            return (Boolean)this.showCrystals.get();
         } else if (entity instanceof LivingEntity && entity.isAlive()) {
            Minecraft var10001 = mc;
            if (entity != Minecraft.player) {
               if (entity instanceof PlayerEntity) {
                  return this.isFriend((PlayerEntity)entity) ? (Boolean)this.showFriends.get() : (Boolean)this.showPlayers.get();
               } else if (entity instanceof MonsterEntity) {
                  return (Boolean)this.showMonsters.get();
               } else if (entity instanceof AnimalEntity) {
                  return (Boolean)this.showAnimals.get();
               } else {
                  return entity instanceof VillagerEntity ? (Boolean)this.showVillagers.get() : false;
               }
            } else {
               return (Boolean)this.showSelf.get() && this.isThirdPersonView();
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void renderWorld(float partialTicks) {
      if (this.isEnabled()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            this.setupRenderState();

            try {
               Iterator var2 = this.getWorldPlayers().iterator();

               while(var2.hasNext()) {
                  PlayerEntity player = (PlayerEntity)var2.next();
                  if (this.isChamsTarget(player)) {
                     if (player instanceof AbstractClientPlayerEntity) {
                        this.renderPlayer((AbstractClientPlayerEntity)player, partialTicks);
                     } else {
                        this.renderGenericEntity(player, partialTicks);
                     }
                  }
               }

               if (!this.shouldRenderGenericTargets()) {
                  return;
               }

               var2 = this.getWorldEntities().iterator();

               while(var2.hasNext()) {
                  Entity entity = (Entity)var2.next();
                  if (!(entity instanceof PlayerEntity) && this.isChamsTarget(entity)) {
                     this.renderGenericEntity(entity, partialTicks);
                  }
               }

               return;
            } finally {
               this.restoreRenderState();
            }
         }
      }

   }

   private boolean shouldRenderGenericTargets() {
      return (Boolean)this.showMonsters.get() || (Boolean)this.showAnimals.get() || (Boolean)this.showVillagers.get() || (Boolean)this.showCrystals.get();
   }

   private void renderPlayer(AbstractClientPlayerEntity player, float partialTicks) {
      EntityRenderer renderer = mc.getRenderManager().getRenderer(player);
      if (!(renderer instanceof PlayerRenderer playerRenderer)) {
         this.renderGenericEntity(player, partialTicks);
      } else {
         PlayerModel model = (PlayerModel)playerRenderer.getEntityModel();
         this.preparePlayerModel(model, player, partialTicks);
         boolean friend = this.isFriend(player);
         boolean damaged = (Boolean)this.hurtEffect.get() && player.hurtTime > 0;
         Color fill = this.applyPulse(this.resolveFillColor(friend, damaged));
         Color outline = this.applyPulse(this.resolveOutlineColor(friend, damaged));
         if (this.isToonInkDrawing()) {
            fill = this.quantizeToonColor(fill);
            Color ink = new Color((Integer)this.inkColor.get(), true);
            outline = new Color(ink.getRed(), ink.getGreen(), ink.getBlue(), outline.getAlpha());
         }

         ShaderUtil shader = this.wantsShaderFill() ? this.resolveShader() : null;
         boolean shaderFill = shader != null;
         if (shaderFill) {
            this.bindShader(shader, fill, partialTicks);
         }

         Vector3f shaderOrigin = this.getShaderOrigin(player, partialTicks);

         try {
            if ((Boolean)this.glow.get()) {
               int layers = Math.max(1, Math.round((Float)this.glowLayers.get()));
               float strength = (Float)this.glowStrength.get();

               for(int layer = layers; layer >= 0; --layer) {
                  float expand = (float)layer * 0.5F * strength;
                  float alphaMultiplier = layer == 0 ? 1.0F : 0.7F / ((float)layer + 1.0F);
                  this.renderPlayerParts(player, model, partialTicks, expand, this.withAlpha(fill, Math.round((float)fill.getAlpha() * alphaMultiplier)), this.withAlpha(outline, Math.round((float)outline.getAlpha() * alphaMultiplier)), shaderFill, shaderOrigin);
               }
            } else {
               this.renderPlayerParts(player, model, partialTicks, 0.0F, fill, outline, shaderFill, shaderOrigin);
            }
         } finally {
            if (shaderFill) {
               shader.detach();
            }

         }

      }
   }

   private void renderPlayerParts(AbstractClientPlayerEntity player, PlayerModel model, float partialTicks, float expand, Color fill, Color outline, boolean shaderFill, Vector3f shaderOrigin) {
      MatrixStack stack = new MatrixStack();
      this.applyPlayerTransform(stack, player, partialTicks);
      this.renderModelBox(stack, model.bipedHead, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, expand, fill, outline, shaderFill, shaderOrigin);
      this.renderModelBox(stack, model.bipedBody, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, expand, fill, outline, shaderFill, shaderOrigin);
      this.renderModelBox(stack, model.bipedRightArm, -3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, expand, fill, outline, shaderFill, shaderOrigin);
      this.renderModelBox(stack, model.bipedLeftArm, -1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, expand, fill, outline, shaderFill, shaderOrigin);
      this.renderModelBox(stack, model.bipedRightLeg, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, expand, fill, outline, shaderFill, shaderOrigin);
      this.renderModelBox(stack, model.bipedLeftLeg, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, expand, fill, outline, shaderFill, shaderOrigin);
   }

   private void renderModelBox(MatrixStack stack, ModelRenderer part, float offX, float offY, float offZ, float width, float height, float depth, float expand, Color fill, Color outline, boolean shaderFill, Vector3f shaderOrigin) {
      stack.push();
      part.translateRotate(stack);
      float scale = 0.0625F;
      float expandScale = expand * scale;
      float minX = offX * scale - expandScale;
      float minY = offY * scale - expandScale;
      float minZ = offZ * scale - expandScale;
      float maxX = (offX + width) * scale + expandScale;
      float maxY = (offY + height) * scale + expandScale;
      float maxZ = (offZ + depth) * scale + expandScale;
      this.renderBox(stack.getLast().getMatrix(), minX, minY, minZ, maxX, maxY, maxZ, fill, outline, shaderFill, shaderOrigin);
      stack.pop();
   }

   private void renderGenericEntity(Entity entity, float partialTicks) {
      boolean friend = entity instanceof PlayerEntity && this.isFriend((PlayerEntity)entity);
      boolean damaged = (Boolean)this.hurtEffect.get() && entity instanceof LivingEntity && ((LivingEntity)entity).hurtTime > 0;
      Color fill = this.applyPulse(this.resolveFillColor(friend, damaged));
      Color outline = this.applyPulse(this.resolveOutlineColor(friend, damaged));
      if (this.isToonInkDrawing()) {
         fill = this.quantizeToonColor(fill);
         Color ink = new Color((Integer)this.inkColor.get(), true);
         outline = new Color(ink.getRed(), ink.getGreen(), ink.getBlue(), outline.getAlpha());
      }

      ShaderUtil shader = this.wantsShaderFill() ? this.resolveShader() : null;
      boolean shaderFill = shader != null;
      if (shaderFill) {
         this.bindShader(shader, fill, partialTicks);
      }

      try {
         MatrixStack stack = new MatrixStack();
         AxisAlignedBB box = this.getInterpolatedBox(entity, partialTicks);
         Vector3d camera = this.getCameraPosition();
         Vector3f shaderOrigin = this.getShaderOrigin(box, camera);
         stack.translate(-camera.x, -camera.y, -camera.z);
         if ((Boolean)this.glow.get()) {
            int layers = Math.max(1, Math.round((Float)this.glowLayers.get()));
            float strength = (Float)this.glowStrength.get() * 0.015F;

            for(int layer = layers; layer >= 0; --layer) {
               float expand = (float)layer * strength;
               float alphaMultiplier = layer == 0 ? 1.0F : 0.7F / ((float)layer + 1.0F);
               this.renderBox(stack.getLast().getMatrix(), (float)box.minX - expand, (float)box.minY - expand, (float)box.minZ - expand, (float)box.maxX + expand, (float)box.maxY + expand, (float)box.maxZ + expand, this.withAlpha(fill, Math.round((float)fill.getAlpha() * alphaMultiplier)), this.withAlpha(outline, Math.round((float)outline.getAlpha() * alphaMultiplier)), shaderFill, shaderOrigin);
            }
         } else {
            this.renderBox(stack.getLast().getMatrix(), (float)box.minX, (float)box.minY, (float)box.minZ, (float)box.maxX, (float)box.maxY, (float)box.maxZ, fill, outline, shaderFill, shaderOrigin);
         }
      } finally {
         if (shaderFill) {
            shader.detach();
         }

      }

   }

   private void renderBox(Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Color fill, Color outline, boolean shaderFill, Vector3f shaderOrigin) {
      if (this.wantsFill()) {
         if (shaderFill) {
            this.drawShaderFill(matrix, minX, minY, minZ, maxX, maxY, maxZ, shaderOrigin);
         } else {
            this.drawFilledBox(matrix, minX, minY, minZ, maxX, maxY, maxZ, fill);
         }
      }

      if (this.wantsOutline()) {
         float width = this.isToonInkDrawing() ? (Float)this.inkWidth.get() : (Float)this.lineWidth.get();
         GL11.glEnable(2848);
         GL11.glHint(3154, 4354);
         GL11.glLineWidth(width);
         this.drawOutlinedBox(matrix, minX, minY, minZ, maxX, maxY, maxZ, outline);
         if (!this.isToonInkDrawing()) {
            GL11.glDisable(2848);
         }
      }

   }

   private void drawFilledBox(Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Color color) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      this.putQuad(buffer, matrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, color);
      this.putQuad(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, color);
      this.putQuad(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, color);
      this.putQuad(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, minX, minY, maxZ, color);
      this.putQuad(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, minX, minY, minZ, color);
      this.putQuad(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, color);
      tessellator.draw();
   }

   private void drawShaderFill(Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Vector3f shaderOrigin) {
      Vector3f localOrigin = new Vector3f((minX + maxX) * 0.5F, (minY + maxY) * 0.5F, (minZ + maxZ) * 0.5F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      this.putShaderQuad(buffer, matrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, localOrigin);
      this.putShaderQuad(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, localOrigin);
      this.putShaderQuad(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, localOrigin);
      this.putShaderQuad(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, minX, minY, maxZ, localOrigin);
      this.putShaderQuad(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, minX, minY, minZ, localOrigin);
      this.putShaderQuad(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, localOrigin);
      tessellator.draw();
   }

   private void drawOutlinedBox(Matrix4f matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Color color) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      this.putLine(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, color);
      this.putLine(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, color);
      this.putLine(buffer, matrix, maxX, minY, maxZ, minX, minY, maxZ, color);
      this.putLine(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, color);
      this.putLine(buffer, matrix, minX, maxY, minZ, maxX, maxY, minZ, color);
      this.putLine(buffer, matrix, maxX, maxY, minZ, maxX, maxY, maxZ, color);
      this.putLine(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, color);
      this.putLine(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, color);
      this.putLine(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, color);
      this.putLine(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, color);
      this.putLine(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, color);
      this.putLine(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, color);
      tessellator.draw();
   }

   private void putQuad(BufferBuilder buffer, Matrix4f matrix, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, Color color) {
      this.putVertex(buffer, matrix, x0, y0, z0, color);
      this.putVertex(buffer, matrix, x1, y1, z1, color);
      this.putVertex(buffer, matrix, x2, y2, z2, color);
      this.putVertex(buffer, matrix, x3, y3, z3, color);
   }

   private void putShaderQuad(BufferBuilder buffer, Matrix4f matrix, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, Vector3f shaderOrigin) {
      this.putShaderVertex(buffer, matrix, x0, y0, z0, shaderOrigin);
      this.putShaderVertex(buffer, matrix, x1, y1, z1, shaderOrigin);
      this.putShaderVertex(buffer, matrix, x2, y2, z2, shaderOrigin);
      this.putShaderVertex(buffer, matrix, x3, y3, z3, shaderOrigin);
   }

   private void putLine(BufferBuilder buffer, Matrix4f matrix, float x0, float y0, float z0, float x1, float y1, float z1, Color color) {
      this.putVertex(buffer, matrix, x0, y0, z0, color);
      this.putVertex(buffer, matrix, x1, y1, z1, color);
   }

   private void putVertex(BufferBuilder buffer, Matrix4f matrix, float x, float y, float z, Color color) {
      buffer.pos(matrix, x, y, z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
   }

   private void putShaderVertex(BufferBuilder buffer, Matrix4f matrix, float x, float y, float z, Vector3f shaderOrigin) {
      Vector4f vertex = new Vector4f(x, y, z, 1.0F);
      vertex.transform(matrix);
      float dx = x - shaderOrigin.getX();
      float dy = y - shaderOrigin.getY();
      float dz = z - shaderOrigin.getZ();
      float length = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
      if (length > 1.0E-5F) {
         dx /= length;
         dy /= length;
         dz /= length;
      } else {
         dx = 0.0F;
         dy = 1.0F;
         dz = 0.0F;
      }

      int r = MathHelper.clamp(Math.round((dx * 0.5F + 0.5F) * 255.0F), 0, 255);
      int g = MathHelper.clamp(Math.round((dy * 0.5F + 0.5F) * 255.0F), 0, 255);
      int b = MathHelper.clamp(Math.round((dz * 0.5F + 0.5F) * 255.0F), 0, 255);
      buffer.pos((double)vertex.getX(), (double)vertex.getY(), (double)vertex.getZ()).color(r, g, b, 255).endVertex();
   }

   private void preparePlayerModel(PlayerModel model, AbstractClientPlayerEntity player, float partialTicks) {
      float bodyYaw = this.interpolateAngle(partialTicks, player.prevRenderYawOffset, player.renderYawOffset);
      float limbSwingAmount = MathHelper.lerp(partialTicks, player.prevLimbSwingAmount, player.limbSwingAmount);
      float limbSwing = player.limbSwing - player.limbSwingAmount * (1.0F - partialTicks);
      float age = (float)player.ticksExisted + partialTicks;
      float netHeadYaw = this.interpolateAngle(partialTicks, player.prevRotationYawHead, player.rotationYawHead) - bodyYaw;
      float headPitch = MathHelper.lerp(partialTicks, player.prevRotationPitch, player.rotationPitch);
      model.swingProgress = player.getSwingProgress(partialTicks);
      model.isSneak = player.isCrouching();
      model.isSitting = player.isPassenger();
      model.swimAnimation = player.getSwimAnimation(partialTicks);
      model.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
      model.setRotationAngles(player, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
   }

   private void applyPlayerTransform(MatrixStack stack, AbstractClientPlayerEntity player, float partialTicks) {
      Vector3d position = this.getInterpolatedPosition(player, partialTicks);
      Vector3d camera = this.getCameraPosition();
      stack.translate(position.x - camera.x, position.y - camera.y, position.z - camera.z);
      float bodyYaw = this.interpolateAngle(partialTicks, player.prevRenderYawOffset, player.renderYawOffset);
      float swimAnimation = player.getSwimAnimation(partialTicks);
      float swimAngle;
      if (player.isElytraFlying()) {
         stack.rotate(Vector3f.YP.rotationDegrees(180.0F - bodyYaw));
         swimAngle = (float)player.getTicksElytraFlying() + partialTicks;
         float elytraProgress = MathHelper.clamp(swimAngle * swimAngle / 100.0F, 0.0F, 1.0F);
         if (!player.isSpinAttacking()) {
            stack.rotate(Vector3f.XP.rotationDegrees(elytraProgress * (-90.0F - player.rotationPitch)));
         }

         Vector3d look = player.getLook(partialTicks);
         Vector3d motion = player.getMotion();
         double motionHorizontal = motion.x * motion.x + motion.z * motion.z;
         double lookHorizontal = look.x * look.x + look.z * look.z;
         if (motionHorizontal > 0.0 && lookHorizontal > 0.0) {
            double dot = (motion.x * look.x + motion.z * look.z) / Math.sqrt(motionHorizontal * lookHorizontal);
            double cross = motion.x * look.z - motion.z * look.x;
            stack.rotate(Vector3f.YP.rotation((float)(Math.signum(cross) * Math.acos(MathHelper.clamp(dot, -1.0, 1.0)))));
         }
      } else if (swimAnimation > 0.0F) {
         stack.rotate(Vector3f.YP.rotationDegrees(180.0F - bodyYaw));
         swimAngle = player.isInWater() ? -90.0F - player.rotationPitch : -90.0F;
         stack.rotate(Vector3f.XP.rotationDegrees(MathHelper.lerp(swimAnimation, 0.0F, swimAngle)));
         if (player.isActualySwimming()) {
            stack.translate(0.0, -1.0, 0.3);
         }
      } else {
         stack.rotate(Vector3f.YP.rotationDegrees(180.0F - bodyYaw));
      }

      if (player.isCrouching()) {
         stack.translate(0.0, -0.11, 0.0);
      }

      stack.scale(-1.0F, -1.0F, 1.0F);
      stack.scale(0.9375F, 0.9375F, 0.9375F);
      stack.translate(0.0, -1.501, 0.0);
   }

   private AxisAlignedBB getInterpolatedBox(Entity entity, float partialTicks) {
      Vector3d current = entity.getPositionVec();
      Vector3d interpolated = this.getInterpolatedPosition(entity, partialTicks);
      return entity.getBoundingBox().offset(interpolated.subtract(current));
   }

   private Vector3f getShaderOrigin(Entity entity, float partialTicks) {
      return this.getShaderOrigin(this.getInterpolatedBox(entity, partialTicks), this.getCameraPosition());
   }

   private Vector3f getShaderOrigin(AxisAlignedBB box, Vector3d camera) {
      return new Vector3f((float)((box.minX + box.maxX) * 0.5 - camera.x), (float)((box.minY + box.maxY) * 0.5 - camera.y), (float)((box.minZ + box.maxZ) * 0.5 - camera.z));
   }

   private Vector3d getInterpolatedPosition(Entity entity, float partialTicks) {
      double x = MathHelper.lerp((double)partialTicks, entity.lastTickPosX, entity.getPosX());
      double y = MathHelper.lerp((double)partialTicks, entity.lastTickPosY, entity.getPosY());
      double z = MathHelper.lerp((double)partialTicks, entity.lastTickPosZ, entity.getPosZ());
      return new Vector3d(x, y, z);
   }

   private Vector3d getCameraPosition() {
      ActiveRenderInfo camera = mc.getRenderManager().info != null ? mc.getRenderManager().info : mc.gameRenderer.getActiveRenderInfo();
      return camera.getProjectedView();
   }

   private void setupRenderState() {
      RenderSystem.pushMatrix();
      RenderSystem.enableBlend();
      if ((Boolean)this.glow.get()) {
         RenderSystem.blendFuncSeparate(770, 1, 1, 0);
      } else {
         RenderSystem.defaultBlendFunc();
      }

      RenderSystem.disableTexture();
      RenderSystem.disableCull();
      RenderSystem.depthMask(false);
      if ((Boolean)this.throughWalls.get()) {
         RenderSystem.disableDepthTest();
      } else {
         RenderSystem.enableDepthTest();
      }

      GL11.glShadeModel(7425);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void restoreRenderState() {
      GL11.glShadeModel(7424);
      GL11.glLineWidth(1.0F);
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.enableTexture();
      RenderSystem.blendFunc(770, 771);
      RenderSystem.disableBlend();
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.popMatrix();
   }

   private void bindShader(ShaderUtil shader, Color fill, float partialTicks) {
      int theme = this.getClientColor();
      float time = (mc.world == null ? 0.0F : (float)mc.world.getGameTime() + partialTicks) * this.getAnimationSpeed();
      shader.attach();
      shader.setUniformf("u_Color", (float)fill.getRed() / 255.0F, (float)fill.getGreen() / 255.0F, (float)fill.getBlue() / 255.0F, 1.0F);
      shader.setUniformf("u_Scale", this.getShaderScale());
      shader.setUniformf("u_Time", time * 0.08F);
      shader.setUniformf("u_Alpha", (float)fill.getAlpha() / 255.0F);
      shader.setUniformf("color", (float)ColorUtil.red(theme) / 255.0F, (float)ColorUtil.green(theme) / 255.0F, (float)ColorUtil.blue(theme) / 255.0F, 1.0F);
   }

   private ShaderUtil resolveShader() {
      if (this.isSelected(this.shaderMode, "Балатро", "Balatro")) {
         return ShaderUtil.block_overlay_sky_balatro;
      } else if (this.isSelected(this.shaderMode, "Плазма", "Plasma")) {
         return ShaderUtil.block_overlay_sky_plasma;
      } else {
         String ambienceSky = (String)Ambience.skyMode.get();
         if (ambienceSky != null) {
            String normalized = ambienceSky.toLowerCase(Locale.ROOT);
            if (normalized.contains("balatro") || normalized.contains("балатро")) {
               return ShaderUtil.block_overlay_sky_balatro;
            }
         }

         return ShaderUtil.block_overlay_sky_plasma;
      }
   }

   private boolean wantsFill() {
      return this.isSelected(this.mode, "Заливка", "Fill") || this.isSelected(this.mode, "Оба", "Both");
   }

   private boolean wantsOutline() {
      return this.isSelected(this.mode, "Контур", "Outline") || this.isSelected(this.mode, "Оба", "Both");
   }

   private boolean wantsShaderFill() {
      return this.isShaderDrawing() && this.wantsFill();
   }

   private boolean isShaderDrawing() {
      return this.isSelected(this.drawing, "Шейдер", "Shader");
   }

   private boolean isToonInkDrawing() {
      return this.isSelected(this.drawing, "Тун-обводка", "Toon Ink");
   }

   private boolean isColorCustom() {
      return this.isSelected(this.colorMode, "Свой", "Custom");
   }

   private boolean isColorRainbow() {
      return this.isSelected(this.colorMode, "Радуга", "Rainbow");
   }

   private boolean isColorClient() {
      return this.isSelected(this.colorMode, "Клиент", "Client");
   }

   private boolean isSelected(ModeSetting setting, String value, String legacyValue) {
      String current = (String)setting.get();
      return current != null && (current.equalsIgnoreCase(value) || current.equalsIgnoreCase(legacyValue));
   }

   private Color resolveFillColor(boolean friend, boolean damaged) {
      Color rainbow;
      if (damaged) {
         rainbow = new Color((Integer)this.hurtColor.get(), true);
         return new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), 80);
      } else if (friend && (Boolean)this.customFriendColor.get()) {
         return new Color((Integer)this.friendFillColor.get(), true);
      } else if (this.isColorRainbow()) {
         rainbow = Color.getHSBColor((float)(System.currentTimeMillis() % 3000L) / 3000.0F, 0.7F, 1.0F);
         return new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), 60);
      } else if (this.isColorClient()) {
         int client = this.getClientColor();
         return new Color(ColorUtil.red(client), ColorUtil.green(client), ColorUtil.blue(client), 60);
      } else {
         return new Color((Integer)this.fillColor.get(), true);
      }
   }

   private Color resolveOutlineColor(boolean friend, boolean damaged) {
      if (damaged) {
         return new Color((Integer)this.hurtColor.get(), true);
      } else if (friend && (Boolean)this.customFriendColor.get()) {
         return new Color((Integer)this.friendOutlineColor.get(), true);
      } else if (this.isColorRainbow()) {
         return Color.getHSBColor((float)(System.currentTimeMillis() % 3000L) / 3000.0F, 0.8F, 1.0F);
      } else if (this.isColorClient()) {
         int client = this.getClientColor();
         return new Color(ColorUtil.red(client), ColorUtil.green(client), ColorUtil.blue(client), 255);
      } else {
         return new Color((Integer)this.outlineColor.get(), true);
      }
   }

   private Color quantizeToonColor(Color color) {
      int steps = Math.max(2, Math.round((Float)this.toonSteps.get()));
      float shadow = MathHelper.clamp((Float)this.toonShadow.get(), 0.2F, 1.0F);
      float step = 255.0F / (float)(steps - 1);
      int r = MathHelper.clamp(Math.round((float)Math.round((float)color.getRed() * shadow / step) * step), 0, 255);
      int g = MathHelper.clamp(Math.round((float)Math.round((float)color.getGreen() * shadow / step) * step), 0, 255);
      int b = MathHelper.clamp(Math.round((float)Math.round((float)color.getBlue() * shadow / step) * step), 0, 255);
      return new Color(r, g, b, color.getAlpha());
   }

   private Color applyPulse(Color color) {
      if (!(Boolean)this.pulse.get()) {
         return color;
      } else {
         float time = (float)(System.currentTimeMillis() - this.enabledAtMs) / 1000.0F;
         float wave = (float)((Math.sin((double)(time * (Float)this.pulseSpeed.get()) * Math.PI) + 1.0) * 0.5);
         float alpha = 0.3F + 0.7F * wave;
         return this.withAlpha(color, Math.round((float)color.getAlpha() * alpha));
      }
   }

   private Color withAlpha(Color color, int alpha) {
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), MathHelper.clamp(alpha, 0, 255));
   }

   private float interpolateAngle(float partialTicks, float previous, float current) {
      return previous + MathHelper.wrapDegrees(current - previous) * partialTicks;
   }

   private Iterable getWorldPlayers() {
      return mc.world == null ? Collections.emptyList() : mc.world.getPlayers();
   }

   private Iterable getWorldEntities() {
      return (Iterable)(mc.world == null ? Collections.emptyList() : mc.world.getAllEntities());
   }

   private boolean isFriend(PlayerEntity player) {
      return player != null && SkyCore.getInstance().getFriendManager() != null && SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName());
   }

   private boolean isThirdPersonView() {
      return mc.gameSettings.getPointOfView() != PointOfView.FIRST_PERSON;
   }

   private int getClientColor() {
      return ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
   }

   private float getShaderScale() {
      return (Float)Ambience.skyScale.get();
   }

   private float getAnimationSpeed() {
      return (Float)Ambience.skySpeed.get();
   }
}
