package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Items;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.math.AuraUtil;
import sky.core.utils.player.InventoryUtil;

public class Test extends Module {
   private final SliderSetting placeRange = new SliderSetting("Дистанция установки", 4.0F, 1.0F, 5.0F, 0.1F);
   private final SliderSetting breakRange = new SliderSetting("Дистанция удара", 3.5F, 1.0F, 4.5F, 0.1F);
   private final SliderSetting wallRange = new SliderSetting("Дистанция стен", 3.0F, 1.0F, 4.0F, 0.1F);
   private final SliderSetting placeDelay = new SliderSetting("Задержка установки", 100.0F, 0.0F, 500.0F, 10.0F);
   private final SliderSetting breakDelay = new SliderSetting("Задержка удара", 100.0F, 0.0F, 500.0F, 10.0F);
   private final SliderSetting minDamage = new SliderSetting("Мин. урон", 5.0F, 1.0F, 36.0F, 0.5F);
   private final SliderSetting maxSelfDamage = new SliderSetting("Макс. самоурон", 8.0F, 1.0F, 36.0F, 0.5F);
   private final BooleanSetting prioritizeLow = new BooleanSetting("Приоритет ниже", true);
   private final BooleanSetting facePlace = new BooleanSetting("FacePlace", true);
   private final SliderSetting facePlaceHP;
   private final BooleanSetting antiSuicide;
   private final BooleanSetting checkRegion;
   private final ModeSetting rotateMode;
   private final SliderSetting rotateSpeed;
   private final BooleanSetting strictMotion;
   private final BooleanSetting rayTrace;
   private final BooleanSetting swing;
   private final BooleanSetting render;
   private long lastPlaceTime;
   private long lastBreakTime;
   private BlockPos bestPlacePos;
   private EnderCrystalEntity targetCrystal;
   private LivingEntity target;
   private boolean isRotating;
   private final List renderPositions;
   private Field overlayMessageField;

   public Test() {
      super("CrystalAura", "Универсальный авто кристалл", Category.Combat);
      BooleanSetting var10008 = this.facePlace;
      Objects.requireNonNull(var10008);
      this.facePlaceHP = new SliderSetting("FacePlace HP", 8.0F, 1.0F, 20.0F, 0.5F, var10008::get);
      this.antiSuicide = new BooleanSetting("Анти-суицид", true);
      this.checkRegion = new BooleanSetting("Проверка региона", true);
      this.rotateMode = new ModeSetting("Ротация", "Плавная", new String[]{"Плавная", "Мгновенная"});
      this.rotateSpeed = new SliderSetting("Скорость ротации", 45.0F, 10.0F, 180.0F, 5.0F, () -> {
         return this.rotateMode.is("Плавная");
      });
      this.strictMotion = new BooleanSetting("Строгий спринт", true);
      this.rayTrace = new BooleanSetting("RayTrace", true);
      this.swing = new BooleanSetting("Свинг", true);
      this.render = new BooleanSetting("Подсветка", true);
      this.lastPlaceTime = 0L;
      this.lastBreakTime = 0L;
      this.bestPlacePos = null;
      this.targetCrystal = null;
      this.target = null;
      this.isRotating = false;
      this.renderPositions = new ArrayList();
      this.overlayMessageField = null;
      this.addSettings(new Setting[]{this.placeRange, this.breakRange, this.wallRange, this.placeDelay, this.breakDelay, this.minDamage, this.maxSelfDamage, this.prioritizeLow, this.facePlace, this.facePlaceHP, this.antiSuicide, this.checkRegion, this.rotateMode, this.rotateSpeed, this.strictMotion, this.rayTrace, this.swing, this.render});
   }

   public void onDisable() {
      this.bestPlacePos = null;
      this.targetCrystal = null;
      this.target = null;
      this.isRotating = false;
      this.renderPositions.clear();
      super.onDisable();
   }

   private boolean isBot(PlayerEntity p) {
      if (p == null) {
         return false;
      } else {
         AntiBot ab = (AntiBot)SkyCore.getInstance().getModuleManager().getModule(AntiBot.class);
         return ab != null && ab.isEnabled() && AntiBot.bot.contains(p);
      }
   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if ((Boolean)this.checkRegion.get() && this.isInProtectedRegion()) {
            this.reset();
         } else {
            this.renderPositions.clear();
            this.bestPlacePos = null;
            this.targetCrystal = null;
            this.target = this.findTarget();
            if (this.target == null) {
               this.isRotating = false;
            } else {
               List validPositions = this.findValidPlacePositions();
               if ((Boolean)this.render.get()) {
                  this.renderPositions.addAll(validPositions);
               }

               this.targetCrystal = this.findBestCrystal();
               this.bestPlacePos = this.findBestPlacePosition(validPositions);
               Vector3d rotationTarget = null;
               boolean attacking = false;
               long currentTime = System.currentTimeMillis();
               if (this.targetCrystal != null && (float)(currentTime - this.lastBreakTime) >= (Float)this.breakDelay.get()) {
                  if (this.canAttackCrystal(this.targetCrystal)) {
                     rotationTarget = this.targetCrystal.getPositionVec().add(0.0, 0.5, 0.0);
                     attacking = true;
                  }
               } else if (this.bestPlacePos != null && (float)(currentTime - this.lastPlaceTime) >= (Float)this.placeDelay.get()) {
                  var10000 = mc;
                  rotationTarget = AuraUtil.getClosestVec(Minecraft.player.getEyePosition(1.0F), new AxisAlignedBB(this.bestPlacePos));
                  attacking = false;
               }

               if (rotationTarget != null) {
                  this.isRotating = true;
                  var10000 = mc;
                  Vector3d eyeVec = Minecraft.player.getEyePosition(1.0F);
                  Vector3d offset = rotationTarget.subtract(eyeVec);
                  float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(offset.z, offset.x)) - 90.0);
                  float targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.y, Math.hypot(offset.x, offset.z))));
                  RotationComponent.update(new Rotation(targetYaw, targetPitch), (Float)this.rotateSpeed.get(), 1, 6);
                  if (this.isLookingAt(rotationTarget, targetYaw, targetPitch)) {
                     if (attacking && this.targetCrystal != null && this.targetCrystal.isAlive()) {
                        this.performAttack(this.targetCrystal);
                     } else if (!attacking && this.bestPlacePos != null) {
                        this.performPlace(this.bestPlacePos, rotationTarget);
                     }
                  }

                  if ((Boolean)this.strictMotion.get()) {
                     Minecraft var10001 = mc;
                     if (Math.abs(MathHelper.wrapDegrees(targetYaw - Minecraft.player.rotationYaw)) > 20.0F) {
                        var10000 = mc;
                        Minecraft.player.setSprinting(false);
                     }
                  }
               } else {
                  this.isRotating = false;
               }

            }
         }
      }
   }

   private boolean isLookingAt(Vector3d t, float y, float p) {
      if (!(Boolean)this.rayTrace.get()) {
         Minecraft var10001 = mc;
         boolean var5;
         if (Math.abs(MathHelper.wrapDegrees(y - Minecraft.player.rotationYaw)) < 5.0F) {
            var10001 = mc;
            if (Math.abs(p - Minecraft.player.rotationPitch) < 5.0F) {
               var5 = true;
               return var5;
            }
         }

         var5 = false;
         return var5;
      } else {
         ClientWorld var10000 = mc.world;
         Minecraft var10003 = mc;
         Vector3d var6 = Minecraft.player.getEyePosition(1.0F);
         Minecraft var10007 = mc;
         RayTraceResult r = var10000.rayTraceBlocks(new RayTraceContext(var6, t, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player));
         return r == null || r.getType() == Type.MISS || r.getHitVec().squareDistanceTo(t) < 1.0;
      }
   }

   private boolean isInProtectedRegion() {
      try {
         if (this.overlayMessageField == null) {
            this.overlayMessageField = IngameGui.class.getDeclaredField("overlayMessage");
            this.overlayMessageField.setAccessible(true);
         }

         String t = (String)this.overlayMessageField.get(mc.ingameGUI);
         if (t != null && !t.isEmpty()) {
            String c = TextFormatting.getTextWithoutFormattingCodes(t).toLowerCase();
            return c.contains("регион в привате") || c.contains("запривачено");
         }
      } catch (Exception var3) {
      }

      return false;
   }

   private void performAttack(EnderCrystalEntity c) {
      if (c != null && c.isAlive()) {
         Minecraft var10001 = mc;
         mc.playerController.attackEntity(Minecraft.player, c);
         if ((Boolean)this.swing.get()) {
            Minecraft var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
         }

         this.lastBreakTime = System.currentTimeMillis();
      }
   }

   private void performPlace(BlockPos p, Vector3d h) {
      if (p != null && this.isValidPlacePosition(p)) {
         int s = InventoryUtil.getItemSlot(Items.END_CRYSTAL);
         Minecraft var10000 = mc;
         boolean o = Minecraft.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
         var10000 = mc;
         boolean m = Minecraft.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
         if (s != -1 || o || m) {
            Minecraft var10001 = mc;
            Vector3d off = h.subtract(Minecraft.player.getEyePosition(1.0F)).inverse();
            BlockRayTraceResult res = new BlockRayTraceResult(h, Direction.getFacingFromVector(off.x, off.y, off.z), p, false);
            if (m) {
               var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.MAIN_HAND, res);
               if ((Boolean)this.swing.get()) {
                  var10000 = mc;
                  Minecraft.player.swingArm(Hand.MAIN_HAND);
               }
            } else if (o) {
               var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.OFF_HAND, res);
               if ((Boolean)this.swing.get()) {
                  var10000 = mc;
                  Minecraft.player.swingArm(Hand.OFF_HAND);
               }
            } else if (s != -1) {
               int ws = s < 9 ? s + 36 : s;
               Minecraft var10005 = mc;
               mc.playerController.windowClick(0, ws, 40, ClickType.SWAP, Minecraft.player);
               var10001 = mc;
               mc.playerController.func_217292_a(Minecraft.player, mc.world, Hand.OFF_HAND, res);
               if ((Boolean)this.swing.get()) {
                  var10000 = mc;
                  Minecraft.player.swingArm(Hand.OFF_HAND);
               }

               var10005 = mc;
               mc.playerController.windowClick(0, ws, 40, ClickType.SWAP, Minecraft.player);
            }

            this.lastPlaceTime = System.currentTimeMillis();
         }
      }
   }

   private boolean isValidPlacePosition(BlockPos p) {
      if (p == null) {
         return false;
      } else {
         Block b = mc.world.getBlockState(p).getBlock();
         if ((b == Blocks.OBSIDIAN || b == Blocks.BEDROCK) && mc.world.getBlockState(p.up()).isAir()) {
            AxisAlignedBB box = new AxisAlignedBB((double)p.getX(), (double)(p.getY() + 1), (double)p.getZ(), (double)(p.getX() + 1), (double)(p.getY() + 3), (double)(p.getZ() + 1));
            Iterator var4 = mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, box).iterator();

            Entity e;
            do {
               if (!var4.hasNext()) {
                  return true;
               }

               e = (Entity)var4.next();
            } while(e == null || !e.isAlive() || e.isSpectator());

            return false;
         } else {
            return false;
         }
      }
   }

   private List findValidPlacePositions() {
      List l = new ArrayList();
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         int r = (int)Math.ceil((double)(Float)this.placeRange.get());
         var10000 = mc;
         BlockPos pp = Minecraft.player.getPosition();

         for(int x = -r; x <= r; ++x) {
            for(int y = -r; y <= r; ++y) {
               for(int z = -r; z <= r; ++z) {
                  BlockPos p = pp.add(x, y, z);
                  var10000 = mc;
                  if (Minecraft.player.getDistanceSq((double)p.getX() + 0.5, (double)p.getY() + 0.5, (double)p.getZ() + 0.5) <= (double)((Float)this.placeRange.get() * (Float)this.placeRange.get()) && this.isValidPlacePosition(p)) {
                     l.add(p);
                  }
               }
            }
         }

         return l;
      } else {
         return l;
      }
   }

   private boolean canPlaceAt(BlockPos p) {
      if (!this.isValidPlacePosition(p)) {
         return false;
      } else {
         Vector3d c = new Vector3d((double)p.getX() + 0.5, (double)(p.getY() + 1), (double)p.getZ() + 0.5);
         if ((Boolean)this.rayTrace.get()) {
            ClientWorld var10000 = mc.world;
            Minecraft var10003 = mc;
            Vector3d var6 = Minecraft.player.getEyePosition(1.0F);
            Minecraft var10007 = mc;
            RayTraceResult r = var10000.rayTraceBlocks(new RayTraceContext(var6, c, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player));
            if (r.getType() == Type.BLOCK) {
               BlockRayTraceResult br = (BlockRayTraceResult)r;
               if (!br.getPos().equals(p) && !br.getPos().equals(p.up())) {
                  Minecraft var5 = mc;
                  if (Minecraft.player.getDistanceSq(c) > (double)((Float)this.wallRange.get() * (Float)this.wallRange.get())) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private LivingEntity findTarget() {
      double range = (double)((Float)this.placeRange.get() + 8.0F);
      double closest = Double.MAX_VALUE;
      LivingEntity t = null;
      Iterator var6 = mc.world.getAllEntities().iterator();

      while(var6.hasNext()) {
         Entity e = (Entity)var6.next();
         if (e instanceof PlayerEntity) {
            Minecraft var10001 = mc;
            if (e != Minecraft.player && e.isAlive() && !SkyCore.getInstance().getFriendManager().isFriend(e.getName().getString()) && !this.isBot((PlayerEntity)e)) {
               Minecraft var10000 = mc;
               double d = (double)Minecraft.player.getDistance(e);
               if (d < closest && d <= range) {
                  closest = d;
                  t = (LivingEntity)e;
               }
            }
         }
      }

      return t;
   }

   private EnderCrystalEntity findBestCrystal() {
      if (this.target == null) {
         return null;
      } else {
         EnderCrystalEntity best = null;
         float max = 0.0F;
         Iterator var3 = mc.world.getAllEntities().iterator();

         while(true) {
            Entity e;
            float dmg;
            float self;
            float var8;
            do {
               do {
                  do {
                     do {
                        do {
                           if (!var3.hasNext()) {
                              return best;
                           }

                           e = (Entity)var3.next();
                        } while(!(e instanceof EnderCrystalEntity));
                     } while(!e.isAlive());
                  } while(e.ticksExisted < 2);

                  Minecraft var10000 = mc;
               } while(Minecraft.player.getDistance(e) > (Float)this.breakRange.get());

               dmg = this.calculateDamage(e.getPositionVec(), this.target);
               Vector3d var10001 = e.getPositionVec();
               Minecraft var10002 = mc;
               self = this.calculateDamage(var10001, Minecraft.player);
               if (!(Boolean)this.antiSuicide.get()) {
                  break;
               }

               Minecraft var7 = mc;
               var8 = Minecraft.player.getHealth();
               var10002 = mc;
            } while(self >= var8 + Minecraft.player.getAbsorptionAmount());

            if (!(dmg < this.getMinDamage()) && !(self > (Float)this.maxSelfDamage.get()) && dmg > max) {
               max = dmg;
               best = (EnderCrystalEntity)e;
            }
         }
      }
   }

   private BlockPos findBestPlacePosition(List l) {
      if (this.target != null && !l.isEmpty()) {
         BlockPos best = null;
         double score = -1.0;
         Iterator var5 = l.iterator();

         while(true) {
            BlockPos p;
            float dmg;
            float self;
            float var12;
            do {
               do {
                  if (!var5.hasNext()) {
                     return best;
                  }

                  p = (BlockPos)var5.next();
               } while(!this.canPlaceAt(p));

               Vector3d v = new Vector3d((double)p.getX() + 0.5, (double)(p.getY() + 1), (double)p.getZ() + 0.5);
               dmg = this.calculateDamage(v, this.target);
               Minecraft var10002 = mc;
               self = this.calculateDamage(v, Minecraft.player);
               if (!(Boolean)this.antiSuicide.get()) {
                  break;
               }

               Minecraft var10001 = mc;
               var12 = Minecraft.player.getHealth();
               var10002 = mc;
            } while(self >= var12 + Minecraft.player.getAbsorptionAmount());

            if (!(dmg < this.getMinDamage()) && !(self > (Float)this.maxSelfDamage.get())) {
               double s = (double)dmg - (double)self * 0.5;
               if ((Boolean)this.prioritizeLow.get() && (double)(p.getY() + 1) < this.target.getPosY()) {
                  s += 2.0;
               }

               if (s > score) {
                  score = s;
                  best = p;
               }
            }
         }
      } else {
         return null;
      }
   }

   private float getMinDamage() {
      return (Boolean)this.facePlace.get() && this.target != null && this.target.getHealth() + this.target.getAbsorptionAmount() <= (Float)this.facePlaceHP.get() ? 2.0F : (Float)this.minDamage.get();
   }

   private boolean canAttackCrystal(EnderCrystalEntity c) {
      if (c != null && c.isAlive()) {
         if ((Boolean)this.rayTrace.get()) {
            ClientWorld var10000 = mc.world;
            Minecraft var10003 = mc;
            Vector3d var4 = Minecraft.player.getEyePosition(1.0F);
            Vector3d var10004 = c.getPositionVec().add(0.0, 0.5, 0.0);
            Minecraft var10007 = mc;
            RayTraceResult r = var10000.rayTraceBlocks(new RayTraceContext(var4, var10004, BlockMode.COLLIDER, FluidMode.NONE, Minecraft.player));
            if (r.getType() == Type.BLOCK) {
               Minecraft var3 = mc;
               if (Minecraft.player.getDistance(c) > (Float)this.wallRange.get()) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private float calculateDamage(Vector3d p, LivingEntity t) {
      if (t == null) {
         return 0.0F;
      } else {
         double d = t.getPositionVec().distanceTo(p);
         if (d > 12.0) {
            return 0.0F;
         } else {
            double ex = (double)Explosion.getBlockDensity(p, t);
            double im = (1.0 - d / 12.0) * ex;
            float dmg = (float)((int)((im * im + im) / 2.0 * 7.0 * 12.0 + 1.0));
            dmg = CombatRules.getDamageAfterAbsorb(dmg, (float)t.getTotalArmorValue(), (float)t.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
            return Math.max(dmg - (float)EnchantmentHelper.getEnchantmentModifierDamage(t.getArmorInventoryList(), DamageSource.causeExplosionDamage((LivingEntity)null)), 0.0F);
         }
      }
   }

   private void reset() {
      this.bestPlacePos = null;
      this.targetCrystal = null;
      this.isRotating = false;
      this.renderPositions.clear();
   }

   @EventTarget
   private void onRender(EventRender3D e) {
      if ((Boolean)this.render.get() && !this.renderPositions.isEmpty()) {
         Vector3d c = mc.getRenderManager().info.getProjectedView();
         RenderSystem.pushMatrix();
         RenderSystem.translated(-c.x, -c.y, -c.z);
         RenderSystem.enableBlend();
         RenderSystem.disableTexture();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         GL11.glEnable(2848);
         Iterator var3 = this.renderPositions.iterator();

         while(true) {
            BlockPos p;
            boolean b;
            boolean cp;
            do {
               if (!var3.hasNext()) {
                  GL11.glDisable(2848);
                  RenderSystem.depthMask(true);
                  RenderSystem.enableTexture();
                  RenderSystem.disableBlend();
                  RenderSystem.enableDepthTest();
                  RenderSystem.enableCull();
                  RenderSystem.popMatrix();
                  return;
               }

               p = (BlockPos)var3.next();
               b = p.equals(this.bestPlacePos);
               cp = this.canPlaceAt(p);
            } while(!cp && !b);

            int col = b ? -16711936 : -256;
            AxisAlignedBB bb = new AxisAlignedBB(p);
            this.drawBox(bb, col, b ? 0.4F : 0.2F);
            this.drawOutline(bb, col, 1.5F);
         }
      }
   }

   private void drawBox(AxisAlignedBB bb, int c, float a) {
      float r = (float)(c >> 16 & 255) / 255.0F;
      float g = (float)(c >> 8 & 255) / 255.0F;
      float b = (float)(c & 255) / 255.0F;
      GL11.glColor4f(r, g, b, a);
      GL11.glBegin(7);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glEnd();
   }

   private void drawOutline(AxisAlignedBB bb, int c, float w) {
      float r = (float)(c >> 16 & 255) / 255.0F;
      float g = (float)(c >> 8 & 255) / 255.0F;
      float b = (float)(c & 255) / 255.0F;
      GL11.glColor4f(r, g, b, 1.0F);
      GL11.glLineWidth(w);
      GL11.glBegin(1);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
      GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
      GL11.glEnd();
   }
}
