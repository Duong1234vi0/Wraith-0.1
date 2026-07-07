package unique.core.features.modules.misc;

import java.awt.Color;
import java.util.ArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2404;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2846;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventAttackBlock;
import unique.core.events.impl.EventSetBlockState;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.player.SpeedMine;
import unique.core.setting.Setting;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.ItemSelectSetting;
import unique.core.utility.Timer;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.world.ExplosionUtility;

public class Nuker extends Module {
   public final Setting<ItemSelectSetting> selectedBlocks = new Setting<>("SelectedBlocks", new ItemSelectSetting(new ArrayList<>()));
   private final Setting<Nuker.Mode> mode = new Setting<>("Mode", Nuker.Mode.Default);
   private final Setting<Integer> delay = new Setting<>("Delay", 25, 0, 1000);
   private final Setting<Nuker.BlockSelection> blocks = new Setting<>("Blocks", Nuker.BlockSelection.Select);
   private final Setting<Boolean> ignoreWalls = new Setting<>("IgnoreWalls", false);
   private final Setting<Boolean> flatten = new Setting<>("Flatten", false);
   private final Setting<Boolean> creative = new Setting<>("Creative", false);
   private final Setting<Boolean> avoidLava = new Setting<>("AvoidLava", false);
   private final Setting<Float> range = new Setting<>("Range", 4.2F, 1.5F, 25.0F);
   private final Setting<Nuker.ColorMode> colorMode = new Setting<>("ColorMode", Nuker.ColorMode.Sync);
   public final Setting<ColorSetting> color = new Setting<>("Color", new ColorSetting(575714484), v -> this.colorMode.getValue() == Nuker.ColorMode.Custom);
   private class_2248 targetBlockType;
   private Nuker.BlockData blockData;
   private Timer breakTimer = new Timer();
   private Nuker.NukerThread nukerThread = new Nuker.NukerThread();
   private float rotationYaw;
   private float rotationPitch;

   public Nuker() {
      super("Nuker", Module.Category.MISC);
   }

   @Override
   public void onEnable() {
      this.nukerThread = new Nuker.NukerThread();
      this.nukerThread.setName("UniqueCore-NukerThread");
      this.nukerThread.setDaemon(true);
      this.nukerThread.start();
   }

   @Override
   public void onDisable() {
      this.nukerThread.interrupt();
   }

   @Override
   public void onUpdate() {
      if (!this.nukerThread.isAlive()) {
         this.nukerThread = new Nuker.NukerThread();
         this.nukerThread.setName("UniqueCore-NukerThread");
         this.nukerThread.setDaemon(true);
         this.nukerThread.start();
      }
   }

   @EventHandler
   public void onBlockInteract(EventAttackBlock e) {
      if (!mc.field_1687.method_22347(e.getBlockPos())) {
         if (this.blocks.getValue().equals(Nuker.BlockSelection.Select) && this.targetBlockType != mc.field_1687.method_8320(e.getBlockPos()).method_26204()) {
            this.targetBlockType = mc.field_1687.method_8320(e.getBlockPos()).method_26204();
            this.sendMessage(
               ClientSettings.isRu()
                  ? "Выбран блок: " + class_124.field_1075 + this.targetBlockType.method_9518().getString()
                  : "Selected block: " + class_124.field_1075 + this.targetBlockType.method_9518().getString()
            );
         }
      }
   }

   @EventHandler
   public void onBlockDestruct(EventSetBlockState e) {
      if (this.blockData != null && e.getPos() == this.blockData.bp && e.getState().method_26215()) {
         this.blockData = null;
         new Thread(
               () -> {
                  if ((this.targetBlockType != null || this.blocks.getValue().equals(Nuker.BlockSelection.All))
                     && !mc.field_1690.field_1886.method_1434()
                     && this.blockData == null) {
                     this.blockData = this.getNukerBlockPos();
                  }
               }
            )
            .start();
      }
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (this.rotationYaw != -999.0F) {
         mc.field_1724.method_36456(this.rotationYaw);
         mc.field_1724.method_36457(this.rotationPitch);
         this.rotationYaw = -999.0F;
      }
   }

   @EventHandler
   public void onPlayerUpdate(PlayerUpdateEvent e) {
      if (this.blockData != null
         && (
            mc.field_1687.method_8320(this.blockData.bp).method_26204() != this.targetBlockType && this.blocks.getValue().equals(Nuker.BlockSelection.Select)
               || PlayerUtility.squaredDistanceFromEyes(this.blockData.bp.method_46558()) > this.range.getPow2Value()
               || mc.field_1687.method_22347(this.blockData.bp)
         )) {
         this.blockData = null;
      }

      if (this.blockData != null && !mc.field_1690.field_1886.method_1434()) {
         float[] angle = InteractionUtility.calculateAngle(this.blockData.vec3d);
         this.rotationYaw = angle[0];
         this.rotationPitch = angle[1];
         ModuleManager.rotations.fixRotation = this.rotationYaw;
         if (this.mode.getValue() == Nuker.Mode.Default) {
            this.breakBlock();
         }

         if (this.mode.getValue() == Nuker.Mode.FastAF) {
            int intRange = (int)(Math.floor(this.range.getValue().floatValue()) + 1.0);

            for (class_2338 b : class_2338.method_25996(
               new class_2338(class_2338.method_49638(mc.field_1724.method_19538()).method_10084()), intRange, intRange, intRange
            )) {
               if ((!this.flatten.getValue() || !(b.method_10264() < mc.field_1724.method_23318())) && (!this.avoidLava.getValue() || !this.checkLava(b))) {
                  class_2680 state = mc.field_1687.method_8320(b);
                  if (PlayerUtility.squaredDistanceFromEyes(b.method_46558()) <= this.range.getPow2Value() && this.isAllowed(state.method_26204())) {
                     try {
                        this.sendSequencedPacket(id -> new class_2846(class_2847.field_12968, b, class_2350.field_11036, id));
                        mc.field_1761.method_2899(b);
                        mc.field_1724.method_6104(class_1268.field_5808);
                     } catch (Exception var9) {
                     }
                  }
               }
            }
         }
      }
   }

   public synchronized void breakBlock() {
      if (this.blockData != null && !mc.field_1690.field_1886.method_1434()) {
         if (!ModuleManager.speedMine.isEnabled() || ModuleManager.speedMine.mode.getValue() != SpeedMine.Mode.Packet) {
            class_2338 cache = this.blockData.bp;
            mc.field_1761.method_2902(this.blockData.bp, this.blockData.dir);
            mc.field_1724.method_6104(class_1268.field_5808);
            if (this.creative.getValue()) {
               mc.field_1761.method_2899(cache);
            }
         } else if (!ModuleManager.speedMine.alreadyActing(this.blockData.bp)) {
            mc.field_1761.method_2910(this.blockData.bp, this.blockData.dir);
            mc.field_1724.method_6104(class_1268.field_5808);
         }
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      class_2338 renderBp = null;
      if (this.blockData != null && this.blockData.bp != null) {
         renderBp = this.blockData.bp;
      }

      if (renderBp != null) {
         Color color1 = this.colorMode.getValue() == Nuker.ColorMode.Sync ? HudEditor.getColor(1) : this.color.getValue().getColorObject();
         Render3DEngine.drawBoxOutline(new class_238(this.blockData.bp), color1, 2.0F);
         Render3DEngine.drawFilledBox(stack, new class_238(this.blockData.bp), Render2DEngine.injectAlpha(color1, 100));
      }

      if (this.mode.getValue() == Nuker.Mode.Fast && this.breakTimer.passedMs(this.delay.getValue().intValue())) {
         this.breakBlock();
         this.breakTimer.reset();
      }
   }

   public Nuker.BlockData getNukerBlockPos() {
      int intRange = (int)(Math.floor(this.range.getValue().floatValue()) + 1.0);

      for (class_2338 b : class_2338.method_25996(
         new class_2338(class_2338.method_49638(mc.field_1724.method_19538()).method_10084()), intRange, intRange, intRange
      )) {
         class_2680 state = mc.field_1687.method_8320(b);
         if ((!this.flatten.getValue() || !(b.method_10264() < mc.field_1724.method_23318()))
            && PlayerUtility.squaredDistanceFromEyes(b.method_46558()) <= this.range.getPow2Value()
            && (!this.avoidLava.getValue() || !this.checkLava(b))
            && this.isAllowed(state.method_26204())) {
            if (this.ignoreWalls.getValue()) {
               class_3965 result = ExplosionUtility.rayCastBlock(
                  new class_3959(InteractionUtility.getEyesPos(mc.field_1724), b.method_46558(), class_3960.field_17558, class_242.field_1348, mc.field_1724),
                  b
               );
               if (result != null) {
                  return new Nuker.BlockData(b, result.method_17784(), result.method_17780());
               }
            } else {
               for (float x1 = 0.0F; x1 <= 1.0F; x1 += 0.2F) {
                  for (float y1 = 0.0F; y1 <= 1.0F; y1 += 0.2F) {
                     for (float z1 = 0.0F; z1 <= 1.0F; z1 += 0.2F) {
                        class_243 p = new class_243(b.method_10263() + x1, b.method_10264() + y1, b.method_10260() + z1);
                        class_3965 bhr = mc.field_1687
                           .method_17742(
                              new class_3959(InteractionUtility.getEyesPos(mc.field_1724), p, class_3960.field_17559, class_242.field_1348, mc.field_1724)
                           );
                        if (bhr != null && bhr.method_17783() == class_240.field_1332 && bhr.method_17777().equals(b)) {
                           return new Nuker.BlockData(b, p, bhr.method_17780());
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   private boolean checkLava(class_2338 base) {
      for (class_2350 dir : class_2350.values()) {
         if (mc.field_1687.method_8320(base.method_10093(dir)).method_26204() == class_2246.field_10164) {
            return true;
         }
      }

      return false;
   }

   private boolean isAllowed(class_2248 block) {
      boolean allowed = this.selectedBlocks.getValue().getItemsById().contains(block.method_9539().replace("block.minecraft.", ""));

      return switch ((Nuker.BlockSelection)this.blocks.getValue()) {
         case Select -> block == this.targetBlockType;
         case All -> block != class_2246.field_9987 && block != class_2246.field_10124 && block != class_2246.field_10543 && !(block instanceof class_2404);
         default -> !allowed
            && block != class_2246.field_9987
            && block != class_2246.field_10124
            && block != class_2246.field_10543
            && !(block instanceof class_2404);
         case WhiteList -> allowed;
      };
   }

   public record BlockData(class_2338 bp, class_243 vec3d, class_2350 dir) {
   }

   private static enum BlockSelection {
      Select,
      All,
      BlackList,
      WhiteList;
   }

   private static enum ColorMode {
      Custom,
      Sync;
   }

   private static enum Mode {
      Default,
      Fast,
      FastAF;
   }

   public class NukerThread extends Thread {
      @Override
      public void run() {
         while (!Thread.currentThread().isInterrupted()) {
            try {
               if (Module.fullNullCheck()) {
                  Thread.yield();
               } else {
                  while (Managers.ASYNC.ticking.get()) {
                  }

                  if ((Nuker.this.targetBlockType != null || !Nuker.this.blocks.getValue().equals(Nuker.BlockSelection.Select))
                     && !Module.mc.field_1690.field_1886.method_1434()
                     && Nuker.this.blockData == null) {
                     Nuker.this.blockData = Nuker.this.getNukerBlockPos();
                  }
               }
            } catch (Exception var2) {
            }
         }
      }
   }
}
