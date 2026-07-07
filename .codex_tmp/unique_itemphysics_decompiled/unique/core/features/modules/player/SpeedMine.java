package unique.core.features.modules.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_2815;
import net.minecraft.class_2846;
import net.minecraft.class_2868;
import net.minecraft.class_4587;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_2846.class_2847;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.core.manager.player.PlayerManager;
import unique.core.events.impl.EventAttackBlock;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.AutoCrystal;
import unique.core.injection.accesors.IInteractionManager;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.world.ExplosionUtility;

public final class SpeedMine extends Module {
   public final Setting<SpeedMine.Mode> mode = new Setting<>("Mode", SpeedMine.Mode.Packet);
   public final Setting<Boolean> doubleMine = new Setting<>("DoubleMine", false);
   private final Setting<SpeedMine.StartMode> startMode = new Setting<>(
      "StartMode", SpeedMine.StartMode.StartAbort, v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue()
   );
   private final Setting<SpeedMine.SwitchMode> switchMode = new Setting<>(
      "SwitchMode", SpeedMine.SwitchMode.Alternative, v -> this.mode.not(SpeedMine.Mode.Damage)
   );
   private final Setting<Integer> swapDelay = new Setting<>("SwapDelay", 50, 0, 1000, v -> this.mode.getValue() != SpeedMine.Mode.Damage);
   private final Setting<Float> factor = new Setting<>("Factor", 1.0F, 0.5F, 2.0F, v -> this.mode.getValue() != SpeedMine.Mode.Damage);
   private final Setting<Float> speed = new Setting<>("Speed", 0.5F, 0.0F, 1.0F, v -> this.mode.getValue() == SpeedMine.Mode.Damage);
   public final Setting<Float> range = new Setting<>("Range", 4.2F, 3.0F, 10.0F, v -> this.mode.getValue() != SpeedMine.Mode.Damage);
   private final Setting<Boolean> rotate = new Setting<>("Rotate", false, v -> this.mode.getValue() != SpeedMine.Mode.Damage);
   private final Setting<Boolean> placeCrystal = new Setting<>("PlaceCrystal", true);
   private final Setting<Boolean> resetOnSwitch = new Setting<>("ResetOnSwitch", true, v -> this.mode.getValue() != SpeedMine.Mode.Damage);
   private final Setting<Integer> breakAttempts = new Setting<>("BreakAttempts", 10, 1, 50, v -> this.mode.getValue() == SpeedMine.Mode.Packet);
   private final Setting<Boolean> pauseEat = new Setting<>("Pause On Eat", false);
   private final Setting<Boolean> clientRemove = new Setting<>("ClientRemove", true);
   private final Setting<SettingGroup> packets = new Setting<>(
      "Packets", new SettingGroup(false, 0), v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue()
   );
   private final Setting<Boolean> stop = new Setting<>("Stop", true, v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue())
      .addToGroup(this.packets);
   private final Setting<Boolean> abort = new Setting<>("Abort", true, v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue())
      .addToGroup(this.packets);
   private final Setting<Boolean> start = new Setting<>("Start", true, v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue())
      .addToGroup(this.packets);
   private final Setting<Boolean> stop2 = new Setting<>("Stop2", true, v -> this.mode.is(SpeedMine.Mode.Packet) && !this.doubleMine.getValue())
      .addToGroup(this.packets);
   private final Setting<BooleanSettingGroup> render = new Setting<>(
      "Render", new BooleanSettingGroup(false), v -> this.mode.getValue() != SpeedMine.Mode.Damage
   );
   private final Setting<Boolean> smooth = new Setting<>("Smooth", true, v -> this.mode.getValue() != SpeedMine.Mode.Damage).addToGroup(this.render);
   private final Setting<SpeedMine.RenderMode> renderMode = new Setting<>(
         "Render Mode", SpeedMine.RenderMode.Shrink, v -> this.mode.getValue() != SpeedMine.Mode.Damage
      )
      .addToGroup(this.render);
   private final Setting<ColorSetting> startLineColor = new Setting<>(
         "Start Line Color", new ColorSetting(new Color(255, 0, 0, 200)), v -> this.mode.getValue() != SpeedMine.Mode.Damage
      )
      .addToGroup(this.render);
   private final Setting<ColorSetting> endLineColor = new Setting<>(
         "End Line Color", new ColorSetting(new Color(47, 255, 0, 200)), v -> this.mode.getValue() != SpeedMine.Mode.Damage
      )
      .addToGroup(this.render);
   private final Setting<Integer> lineWidth = new Setting<>("Line Width", 2, 1, 10, v -> this.mode.getValue() != SpeedMine.Mode.Damage).addToGroup(this.render);
   private final Setting<ColorSetting> startFillColor = new Setting<>(
         "Start Fill Color", new ColorSetting(new Color(255, 0, 0, 120)), v -> this.mode.getValue() != SpeedMine.Mode.Damage
      )
      .addToGroup(this.render);
   private final Setting<ColorSetting> endFillColor = new Setting<>(
         "End Fill Color", new ColorSetting(new Color(47, 255, 0, 120)), v -> this.mode.getValue() != SpeedMine.Mode.Damage
      )
      .addToGroup(this.render);
   public ArrayList<SpeedMine.MineAction> actions = new ArrayList<>();

   public SpeedMine() {
      super("SpeedMine", Module.Category.PLAYER);
   }

   @Override
   public void onDisable() {
      this.actions.forEach(SpeedMine.MineAction::reset);
      this.actions.clear();
   }

   @Override
   public void onEnable() {
      this.actions.forEach(SpeedMine.MineAction::reset);
      this.actions.clear();
   }

   @Override
   public void onUpdate() {
      if (!fullNullCheck() && !mc.field_1724.method_31549().field_7477) {
         if (!PlayerUtility.isEating() || !this.pauseEat.getValue()) {
            if (this.mode.getValue() == SpeedMine.Mode.Damage && ((IInteractionManager)mc.field_1761).getCurBlockDamageMP() < this.speed.getValue()) {
               ((IInteractionManager)mc.field_1761).setCurBlockDamageMP(this.speed.getValue());
            }

            this.actions.removeIf(SpeedMine.MineAction::update);
         }
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (!this.mode.is(SpeedMine.Mode.Damage) && mc.field_1687 != null) {
         this.actions
            .forEach(
               a -> {
                  if (!mc.field_1687.method_22347(a.getPos())) {
                     float noom = (float)MathUtility.clamp(
                        Render2DEngine.interpolate(a.getPrevProgress(), a.getProgress(), Render3DEngine.getTickDelta()), 0.0, 1.0
                     );

                     class_238 renderBox = switch ((SpeedMine.RenderMode)this.renderMode.getValue()) {
                        case Block -> new class_238(a.getPos());
                        case Shrink -> new class_238(
                              a.getPos().method_10263(),
                              a.getPos().method_10264(),
                              a.getPos().method_10260(),
                              a.getPos().method_10263(),
                              a.getPos().method_10264(),
                              a.getPos().method_10260()
                           )
                           .method_1002(noom, noom, noom)
                           .method_989(0.5 + noom * 0.5, 0.5 + noom * 0.5, 0.5 + noom * 0.5);
                        case Grow -> new class_238(
                           a.getPos().method_10263(),
                           a.getPos().method_10264(),
                           a.getPos().method_10260(),
                           a.getPos().method_10263() + 1,
                           a.getPos().method_10264() + noom,
                           a.getPos().method_10260() + 1
                        );
                     };
                     Render3DEngine.FILLED_QUEUE
                        .add(
                           new Render3DEngine.FillAction(
                              renderBox,
                              Render2DEngine.getColor(
                                 this.startFillColor.getValue().getColorObject(),
                                 this.endFillColor.getValue().getColorObject(),
                                 a.getProgress(),
                                 this.smooth.getValue()
                              )
                           )
                        );
                     Render3DEngine.OUTLINE_QUEUE
                        .add(
                           new Render3DEngine.OutlineAction(
                              renderBox,
                              Render2DEngine.getColor(
                                 this.startLineColor.getValue().getColorObject(),
                                 this.endLineColor.getValue().getColorObject(),
                                 a.getProgress(),
                                 this.smooth.getValue()
                              ),
                              this.lineWidth.getValue().intValue()
                           )
                        );
                  }
               }
            );
      }
   }

   @EventHandler
   public void onAttackBlock(@NotNull EventAttackBlock event) {
      if (!fullNullCheck() && this.canBreak(event.getBlockPos()) && !mc.field_1724.method_31549().field_7477 && !this.mode.is(SpeedMine.Mode.Damage)) {
         if (!this.alreadyActing(event.getBlockPos())) {
            if ((!this.doubleMine.getValue() || this.actions.size() >= 2) && !this.actions.isEmpty()) {
               this.actions.removeFirst().cancel();
            }

            this.actions.add(new SpeedMine.MineAction(event.getBlockPos(), event.getEnumFacing()));
         }

         event.cancel();
      }
   }

   public boolean alreadyActing(class_2338 blockPos) {
      return this.actions.stream().anyMatch(a -> a.pos.equals(blockPos));
   }

   @EventHandler(
      priority = -100
   )
   private void onSync(EventSync event) {
      this.actions.forEach(SpeedMine.MineAction::onSync);
   }

   @EventHandler
   private void onPacketSend(@NotNull PacketEvent.SendPost e) {
      if (e.getPacket() instanceof class_2868
         && this.resetOnSwitch.getValue()
         && !this.switchMode.is(SpeedMine.SwitchMode.Silent)
         && !this.mode.is(SpeedMine.Mode.GrimInstant)) {
         this.actions.forEach(SpeedMine.MineAction::reset);
      }
   }

   private void closeScreen() {
      if (mc.field_1724 != null) {
         this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
      }
   }

   public float getBlockStrength(@NotNull class_2680 state, class_2338 position) {
      if (state == class_2246.field_10124.method_9564()) {
         return 0.02F;
      } else {
         float hardness = state.method_26214(mc.field_1687, position);
         return hardness < 0.0F ? 0.0F : this.getDigSpeed(state, position) / hardness / (this.canBreak(position) ? 30.0F : 100.0F);
      }
   }

   private float getDestroySpeed(class_2338 position, class_2680 state) {
      float destroySpeed = 1.0F;
      int slot = this.getTool(position);
      if (mc.field_1724 == null) {
         return 0.0F;
      } else {
         if (slot != -1 && mc.field_1724.method_31548().method_5438(slot) != null && !mc.field_1724.method_31548().method_5438(slot).method_7960()) {
            destroySpeed *= mc.field_1724.method_31548().method_5438(slot).method_7924(state);
         }

         return destroySpeed;
      }
   }

   public float getDigSpeed(class_2680 state, class_2338 position) {
      if (mc.field_1724 == null) {
         return 0.0F;
      } else {
         float digSpeed = this.getDestroySpeed(position, state);
         if (digSpeed > 1.0F) {
            int slot = this.getTool(position);
            if (slot != -1) {
               class_1799 itemstack = mc.field_1724.method_31548().method_5438(slot);
               int efficiencyModifier = class_1890.method_8225(
                  (class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9131.method_58273()).method_40264(class_1893.field_9131).get(),
                  itemstack
               );
               if (efficiencyModifier > 0 && !itemstack.method_7960()) {
                  digSpeed += (float)(StrictMath.pow(efficiencyModifier, 2.0) + 1.0);
               }
            }
         }

         if (mc.field_1724.method_6059(class_1294.field_5917)) {
            digSpeed *= 1.0F + (Objects.requireNonNull(mc.field_1724.method_6112(class_1294.field_5917)).method_5578() + 1) * 0.2F;
         }

         if (mc.field_1724.method_6059(class_1294.field_5901)) {
            digSpeed *= (float)Math.pow(0.3F, Objects.requireNonNull(mc.field_1724.method_6112(class_1294.field_5901)).method_5578() + 1);
         }

         if (mc.field_1724.method_5869()) {
            digSpeed *= (float)mc.field_1724.method_5996(class_5134.field_51576).method_6194();
         }

         if (!mc.field_1724.method_24828() && ModuleManager.freeCam.isDisabled()) {
            digSpeed /= 5.0F;
         }

         return digSpeed < 0.0F ? 0.0F : digSpeed * this.factor.getValue();
      }
   }

   public int getTool(class_2338 pos) {
      int index = -1;
      float currentFastest = 1.0F;
      if (mc.field_1687 != null && mc.field_1724 != null && !(mc.field_1687.method_8320(pos).method_26204() instanceof class_2189)) {
         for (int i = 9; i < 45; i++) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i >= 36 ? i - 36 : i);
            if (stack != class_1799.field_8037 && stack.method_7936() - stack.method_7919() > 10) {
               float digSpeed = class_1890.method_8225(
                  (class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9131).get(), stack
               );
               float destroySpeed = stack.method_7924(mc.field_1687.method_8320(pos));
               if (digSpeed + destroySpeed > currentFastest) {
                  currentFastest = digSpeed + destroySpeed;
                  index = i;
               }
            }
         }

         return index >= 36 ? index - 36 : index;
      } else {
         return -1;
      }
   }

   private boolean canBreak(class_2338 pos) {
      if (mc.field_1687 != null && !(PlayerUtility.squaredDistanceFromEyes(pos.method_46558()) > this.range.getPow2Value())) {
         class_2680 blockState = mc.field_1687.method_8320(pos);
         class_2248 block = blockState.method_26204();
         return block.method_36555() != -1.0F;
      } else {
         return false;
      }
   }

   public void placeCrystal() {
      if (AutoCrystal.target != null) {
         AutoCrystal.PlaceData data = this.getCevData();
         if (data == null) {
            data = this.getBestData();
         }

         if (data != null) {
            ModuleManager.autoCrystal.placeCrystal(data.bhr(), true, false);
            this.debug("placing..");
            ModuleManager.autoTrap.pause();
         }
      }
   }

   @Nullable
   public AutoCrystal.PlaceData getCevData() {
      for (SpeedMine.MineAction action : this.actions) {
         if (mc.field_1687.method_22347(action.getPos().method_10074())) {
            if (ExplosionUtility.getSelfExplosionDamage(action.getPos().method_46558().method_1031(0.0, 0.5, 0.0), 0, false)
               > ModuleManager.autoCrystal.maxSelfDamage.getValue()) {
               return null;
            }

            return ModuleManager.autoCrystal.getPlaceData(action.getPos(), null, mc.field_1724.method_19538());
         }
      }

      return null;
   }

   @Nullable
   public AutoCrystal.PlaceData getBestData() {
      for (SpeedMine.MineAction action : this.actions) {
         class_2680 prevState = mc.field_1687.method_8320(action.getPos());
         mc.field_1687.method_8501(action.getPos(), class_2246.field_10124.method_9564());

         for (class_2350 dir : class_2350.values()) {
            if (dir != class_2350.field_11036
               && dir != class_2350.field_11033
               && !(
                  ExplosionUtility.getSelfExplosionDamage(action.getPos().method_10074().method_10093(dir).method_46558().method_1031(0.0, 0.5, 0.0), 0, false)
                     > ModuleManager.autoCrystal.maxSelfDamage.getValue()
               )) {
               AutoCrystal.PlaceData autoMineData = ModuleManager.autoCrystal
                  .getPlaceData(action.getPos().method_10074().method_10093(dir), null, mc.field_1724.method_19538());
               if (autoMineData != null) {
                  mc.field_1687.method_8501(action.getPos(), prevState);
                  return autoMineData;
               }
            }
         }

         float selfDmg = ExplosionUtility.getSelfExplosionDamage(action.getPos().method_46558().method_1031(0.0, 0.5, 0.0), 0, false);
         mc.field_1687.method_8501(action.getPos(), prevState);
         AutoCrystal.PlaceData autoMineData = ModuleManager.autoCrystal.getPlaceData(action.getPos(), null, mc.field_1724.method_19538());
         if (!(selfDmg > ModuleManager.autoCrystal.maxSelfDamage.getValue())) {
            return autoMineData;
         }
      }

      return null;
   }

   public boolean isBlockDrop(class_1297 ent) {
      if (ent instanceof class_1542 && this.isOn() && ent.field_6012 < 3) {
         for (SpeedMine.MineAction a : this.actions) {
            if (a.getPos().method_46558().method_1025(ent.method_19538()) <= 1.0) {
               return true;
            }
         }
      }

      return false;
   }

   public class MineAction {
      @NotNull
      private final class_2338 pos;
      private float progress;
      private float prevProgress;
      private int mineBreaks;
      private final Timer attackTimer = new Timer();

      public MineAction(@NotNull class_2338 pos, class_2350 direction) {
         this.pos = pos;
         this.progress = 0.0F;
         this.mineBreaks = 0;
         this.start(direction);
      }

      public void start(class_2350 direction) {
         class_2350 startDirection = direction == null ? Module.mc.field_1724.method_5735() : direction;
         if (startDirection != null) {
            if (SpeedMine.this.doubleMine.getValue()) {
               SpeedMine.this.sendPacket(new class_2846(class_2847.field_12973, this.pos, startDirection));
               SpeedMine.this.sendPacket(new class_2846(class_2847.field_12968, this.pos, startDirection));
               SpeedMine.this.sendPacket(new class_2846(class_2847.field_12973, this.pos, startDirection));
            } else {
               SpeedMine.this.sendPacket(new class_2846(class_2847.field_12968, this.pos, startDirection));
               SpeedMine.this.sendPacket(
                  new class_2846(
                     SpeedMine.this.startMode.getValue() == SpeedMine.StartMode.StartAbort ? class_2847.field_12971 : class_2847.field_12973,
                     this.pos,
                     startDirection
                  )
               );
            }
         }
      }

      public boolean update() {
         class_2350 dir = InteractionUtility.getStrictDirections(this.pos).stream().findFirst().orElse(Module.mc.field_1724.method_5735());
         if (this.mineBreaks >= SpeedMine.this.breakAttempts.getValue() && SpeedMine.this.mode.not(SpeedMine.Mode.GrimInstant)) {
            return true;
         } else if (PlayerUtility.squaredDistanceFromEyes(this.pos.method_46558()) > SpeedMine.this.range.getPow2Value()) {
            this.cancel();
            return true;
         } else if (Module.mc.field_1687.method_22347(this.pos)) {
            this.progress = 0.0F;
            this.prevProgress = -1.0F;
            return false;
         } else {
            if (this.progress == 0.0F && this.prevProgress == -1.0F && SpeedMine.this.mode.is(SpeedMine.Mode.Packet) && this.attackTimer.every(800L)) {
               this.start(dir);
               Module.mc.field_1724.method_6104(class_1268.field_5808);
            }

            int pickSlot = SpeedMine.this.getTool(this.pos);
            int prevSlot = Module.mc.field_1724.method_31548().field_7545;
            if (pickSlot == -1) {
               return false;
            } else {
               boolean instant = this.mineBreaks > 0 && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant);
               if (!(this.progress >= 1.0F) && !instant) {
                  this.prevProgress = this.progress;
                  this.progress = this.progress + SpeedMine.this.getBlockStrength(Module.mc.field_1687.method_8320(this.pos), this.pos);
               } else {
                  if (SpeedMine.this.placeCrystal.getValue()) {
                     SpeedMine.this.placeCrystal();
                  }

                  this.switchTo(pickSlot, -1);
                  if (SpeedMine.this.mode.getValue() != SpeedMine.Mode.GrimInstant && !SpeedMine.this.doubleMine.getValue()) {
                     if (SpeedMine.this.stop.getValue()) {
                        SpeedMine.this.sendPacket(new class_2846(class_2847.field_12973, this.pos, dir));
                     }

                     if (SpeedMine.this.abort.getValue()) {
                        SpeedMine.this.sendPacket(new class_2846(class_2847.field_12971, this.pos, dir));
                     }

                     if (SpeedMine.this.start.getValue()) {
                        SpeedMine.this.sendPacket(new class_2846(class_2847.field_12968, this.pos, dir));
                     }

                     if (SpeedMine.this.stop2.getValue()) {
                        SpeedMine.this.sendPacket(new class_2846(class_2847.field_12973, this.pos, dir));
                     }
                  } else {
                     SpeedMine.this.sendPacket(new class_2846(class_2847.field_12973, this.pos, dir));
                  }

                  if (SpeedMine.this.clientRemove.getValue()) {
                     Module.mc.field_1761.method_2899(this.pos);
                  }

                  int delay = SpeedMine.this.doubleMine.getValue() ? 100 : SpeedMine.this.swapDelay.getValue();
                  if (delay != 0) {
                     Managers.ASYNC.run(() -> this.switchTo(prevSlot, pickSlot), delay);
                  } else {
                     this.switchTo(prevSlot, pickSlot);
                  }

                  this.mineBreaks++;
                  this.progress = this.prevProgress = 0.0F;
                  if (SpeedMine.this.doubleMine.getValue() && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant) && SpeedMine.this.actions.size() >= 2) {
                     return true;
                  }
               }

               this.fixMovement();
               return false;
            }
         }
      }

      private void switchTo(int slot, int from) {
         if (SpeedMine.this.switchMode.getValue() != SpeedMine.SwitchMode.Alternative && slot < 9) {
            if (SpeedMine.this.switchMode.is(SpeedMine.SwitchMode.Silent)) {
               InventoryUtility.switchToSilent(slot);
            } else {
               InventoryUtility.switchTo(slot);
            }
         } else {
            if (from == -1) {
               Module.clickSlot(slot < 9 ? slot + 36 : slot, Module.mc.field_1724.method_31548().field_7545, class_1713.field_7791);
            } else {
               Module.clickSlot(from < 9 ? from + 36 : from, Module.mc.field_1724.method_31548().field_7545, class_1713.field_7791);
            }

            SpeedMine.this.closeScreen();
         }
      }

      public void fixMovement() {
         if (SpeedMine.this.rotate.getValue() && this.progress > 0.95) {
            ModuleManager.rotations.fixRotation = PlayerManager.calcAngle(Module.mc.field_1724.method_33571(), this.pos.method_46558())[0];
         }
      }

      public class_2338 getPos() {
         return this.pos;
      }

      public float getPrevProgress() {
         return this.prevProgress;
      }

      public float getProgress() {
         return this.progress;
      }

      public void onSync() {
         if (SpeedMine.this.rotate.getValue() && this.progress > 0.95) {
            float[] angle = PlayerManager.calcAngle(Module.mc.field_1724.method_33571(), this.pos.method_46558().method_1031(0.0, -0.25, 0.0));
            Module.mc.field_1724.method_36456(angle[0]);
            Module.mc.field_1724.method_36457(angle[1]);
         }
      }

      public void reset() {
         if (this.progress != 0.0F) {
            this.prevProgress = this.progress = 0.0F;
            class_2350 dir = InteractionUtility.getStrictDirections(this.pos).stream().findFirst().orElse(Module.mc.field_1724.method_5735());
            SpeedMine.this.sendPacket(new class_2846(class_2847.field_12971, this.pos, dir));
            this.start(dir);
         }
      }

      public void cancel() {
         if (this.progress != 0.0F) {
            SpeedMine.this.sendPacket(new class_2846(class_2847.field_12971, this.pos, class_2350.field_11033));
         }
      }

      public boolean instantBreaking() {
         return this.mineBreaks > 0 && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant);
      }
   }

   public static enum Mode {
      Packet,
      GrimInstant,
      Damage;
   }

   public static enum RenderMode {
      Block,
      Shrink,
      Grow;
   }

   public static enum StartMode {
      StartAbort,
      StartStop;
   }

   public static enum SwitchMode {
      Silent,
      Normal,
      Alternative;
   }
}
