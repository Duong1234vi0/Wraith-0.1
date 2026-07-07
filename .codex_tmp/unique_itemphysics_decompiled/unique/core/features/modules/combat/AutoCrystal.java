package unique.core.features.modules.combat;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import meteordevelopment.orbit.EventHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1303;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1774;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1810;
import net.minecraft.class_1821;
import net.minecraft.class_1829;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2604;
import net.minecraft.class_2606;
import net.minecraft.class_2664;
import net.minecraft.class_2815;
import net.minecraft.class_2824;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2828.class_2830;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.core.manager.player.CombatManager;
import unique.core.core.manager.world.CrystalManager;
import unique.core.events.impl.EventBreakBlock;
import unique.core.events.impl.EventEntitySpawnPost;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.EventTick;
import unique.core.events.impl.PacketEvent;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.ColorSetting;
import unique.core.utility.TickTimer;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.math.PredictUtility;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;
import unique.core.utility.player.SearchInvResult;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.world.ExplosionUtility;

public class AutoCrystal extends Module {
   private static final Setting<AutoCrystal.Pages> page = new Setting<>("Page", AutoCrystal.Pages.Main);
   private final Setting<Boolean> await = new Setting<>("Await", true, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<AutoCrystal.Timing> timing = new Setting<>("Timing", AutoCrystal.Timing.NORMAL, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<AutoCrystal.Sequential> sequential = new Setting<>("Sequential", AutoCrystal.Sequential.Strong, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<AutoCrystal.Rotation> rotate = new Setting<>("Rotate", AutoCrystal.Rotation.CC, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<BooleanSettingGroup> yawStep = new Setting<>(
      "YawStep", new BooleanSettingGroup(false), v -> !this.rotate.is(AutoCrystal.Rotation.OFF) && page.is(AutoCrystal.Pages.Main)
   );
   private final Setting<Float> yawAngle = new Setting<>(
         "YawAngle", 180.0F, 1.0F, 180.0F, v -> !this.rotate.is(AutoCrystal.Rotation.OFF) && page.is(AutoCrystal.Pages.Main)
      )
      .addToGroup(this.yawStep);
   private final Setting<CombatManager.TargetBy> targetLogic = new Setting<>(
      "TargetLogic", CombatManager.TargetBy.Distance, v -> page.is(AutoCrystal.Pages.Main)
   );
   private final Setting<Float> targetRange = new Setting<>("TargetRange", 10.0F, 1.0F, 15.0F, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<Integer> extrapolation = new Setting<>("Extrapolation", 0, 0, 20, v -> page.is(AutoCrystal.Pages.Main));
   private final Setting<AutoCrystal.Interact> interact = new Setting<>("Interact", AutoCrystal.Interact.Default, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Boolean> strictCenter = new Setting<>(
      "ССStrict", true, v -> page.is(AutoCrystal.Pages.Place) && this.interact.getValue() == AutoCrystal.Interact.Strict
   );
   private final Setting<Boolean> rayTraceBypass = new Setting<>("RayTraceBypass", false, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Boolean> oldVer = new Setting<>("1.12", false, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Integer> placeDelay = new Setting<>("PlaceDelay", 0, 0, 20, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Integer> lowPlaceDelay = new Setting<>("LowPlaceDelay", 11, 0, 20, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Float> placeRange = new Setting<>("PlaceRange", 5.0F, 1.0F, 6.0F, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Float> placeWallRange = new Setting<>("PlaceWallRange", 3.5F, 0.0F, 6.0F, v -> page.is(AutoCrystal.Pages.Place));
   private final Setting<Boolean> inhibit = new Setting<>("Inhibit", true, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Integer> breakDelay = new Setting<>("BreakDelay", 0, 0, 20, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Integer> lowBreakDelay = new Setting<>("LowBreakDelay", 11, 0, 20, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Float> explodeRange = new Setting<>("BreakRange", 5.0F, 1.0F, 6.0F, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Float> explodeWallRange = new Setting<>("BreakWallRange", 3.5F, 0.0F, 6.0F, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Integer> crystalAge = new Setting<>("CrystalAge", 0, 0, 20, v -> page.is(AutoCrystal.Pages.Break));
   private final Setting<Boolean> mining = new Setting<>("Mining", true, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Boolean> eating = new Setting<>("Eating", true, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Boolean> aura = new Setting<>("Aura", false, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Boolean> middleClick = new Setting<>("MiddleClick", true, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Boolean> inventory = new Setting<>("Inventory", false, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Float> pauseHP = new Setting<>("HP", 8.0F, 2.0F, 10.0F, v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<BooleanSettingGroup> switchPause = new Setting<>("SwitchPause", new BooleanSettingGroup(true), v -> page.is(AutoCrystal.Pages.Pause));
   private final Setting<Integer> switchDelay = new Setting<>("SwitchDelay", 100, 0, 1000, v -> page.is(AutoCrystal.Pages.Pause)).addToGroup(this.switchPause);
   public final Setting<Boolean> assumeBestArmor = new Setting<>("AssumeBestArmor", false, v -> page.is(AutoCrystal.Pages.Damages));
   public final Setting<Float> minDamage = new Setting<>("MinDamage", 6.0F, 2.0F, 20.0F, v -> page.is(AutoCrystal.Pages.Damages));
   public final Setting<Float> maxSelfDamage = new Setting<>("MaxSelfDamage", 10.0F, 2.0F, 20.0F, v -> page.is(AutoCrystal.Pages.Damages));
   private final Setting<BooleanSettingGroup> efficiency = new Setting<>("Efficiency", new BooleanSettingGroup(false), v -> page.is(AutoCrystal.Pages.Damages));
   private final Setting<Float> efficiencyFactor = new Setting<>("EfficiencyFactor", 1.0F, 0.1F, 5.0F, v -> page.is(AutoCrystal.Pages.Damages))
      .addToGroup(this.efficiency);
   public final Setting<Boolean> protectFriends = new Setting<>("ProtectFriends", true, v -> page.is(AutoCrystal.Pages.Damages));
   private final Setting<BooleanSettingGroup> overrideSelfDamage = new Setting<>(
      "OverrideSelfDamage", new BooleanSettingGroup(true), v -> page.is(AutoCrystal.Pages.Damages)
   );
   public final Setting<Boolean> sacrificeTotem = new Setting<>("SacrificeTotem", true, v -> page.is(AutoCrystal.Pages.Damages))
      .addToGroup(this.overrideSelfDamage);
   private final Setting<BooleanSettingGroup> armorBreaker = new Setting<>(
      "ArmorBreaker", new BooleanSettingGroup(true), v -> page.is(AutoCrystal.Pages.Damages)
   );
   private final Setting<Float> armorScale = new Setting<>("Armor %", 5.0F, 0.0F, 40.0F, v -> page.is(AutoCrystal.Pages.Damages)).addToGroup(this.armorBreaker);
   private final Setting<Float> facePlaceHp = new Setting<>("FacePlaceHp", 5.0F, 0.0F, 20.0F, v -> page.is(AutoCrystal.Pages.Damages));
   private final Setting<Bind> facePlaceButton = new Setting<>("FacePlaceBtn", new Bind(340, false, false), v -> page.is(AutoCrystal.Pages.Damages));
   public final Setting<Boolean> ignoreTerrain = new Setting<>("IgnoreTerrain", true, v -> page.is(AutoCrystal.Pages.Damages));
   private final Setting<Boolean> autoGapple = new Setting<>("AutoGapple", true, v -> page.is(AutoCrystal.Pages.Switch));
   private final Setting<AutoCrystal.Switch> autoSwitch = new Setting<>("Switch", AutoCrystal.Switch.NORMAL, v -> page.is(AutoCrystal.Pages.Switch));
   private final Setting<AutoCrystal.Switch> antiWeakness = new Setting<>("AntiWeakness", AutoCrystal.Switch.SILENT, v -> page.is(AutoCrystal.Pages.Switch));
   public final Setting<Boolean> placeFailsafe = new Setting<>("PlaceFailsafe", true, v -> page.is(AutoCrystal.Pages.FailSafe));
   public final Setting<Boolean> breakFailsafe = new Setting<>("BreakFailsafe", true, v -> page.is(AutoCrystal.Pages.FailSafe));
   public final Setting<Integer> attempts = new Setting<>("MaxAttempts", 5, 1, 30, v -> page.is(AutoCrystal.Pages.FailSafe));
   public final Setting<Boolean> idPredict = new Setting<>("IDPredict", false, v -> page.is(AutoCrystal.Pages.IDPredict));
   public final Setting<Integer> idAttacks = new Setting<>("IDAttacks", 3, 1, 10, v -> page.is(AutoCrystal.Pages.IDPredict));
   public final Setting<Boolean> idPredictOnlyDuels = new Setting<>("IDPredictOnlyDuels", false, v -> page.is(AutoCrystal.Pages.IDPredict));
   private final Setting<AutoCrystal.Swing> swingMode = new Setting<>("Swing", AutoCrystal.Swing.Place, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<Boolean> render = new Setting<>("Render", true, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<BooleanSettingGroup> renderExtrapolation = new Setting<>(
      "RExtrapolation", new BooleanSettingGroup(false), v -> page.is(AutoCrystal.Pages.Render)
   );
   private final Setting<ColorSetting> extrapolationColor = new Setting<>(
         "ExtrapolationColor", new ColorSetting(Color.white), v -> page.is(AutoCrystal.Pages.Render)
      )
      .addToGroup(this.renderExtrapolation);
   private final Setting<BooleanSettingGroup> renderInteractVector = new Setting<>(
      "RInteractVector", new BooleanSettingGroup(true), v -> page.is(AutoCrystal.Pages.Render)
   );
   private final Setting<ColorSetting> interactColor = new Setting<>("InteractColor", new ColorSetting(Color.red), v -> page.is(AutoCrystal.Pages.Render))
      .addToGroup(this.renderInteractVector);
   private final Setting<AutoCrystal.Render> renderMode = new Setting<>("RenderMode", AutoCrystal.Render.Fade, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<Boolean> rselfDamage = new Setting<>("SelfDamage", true, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<Boolean> drawDamage = new Setting<>("RenderDamage", true, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<ColorSetting> fillColor = new Setting<>(
      "Block Fill Color", new ColorSetting(HudEditor.getColor(0)), v -> page.is(AutoCrystal.Pages.Render)
   );
   private final Setting<ColorSetting> lineColor = new Setting<>(
      "Block Line Color", new ColorSetting(HudEditor.getColor(0)), v -> page.is(AutoCrystal.Pages.Render)
   );
   private final Setting<Integer> lineWidth = new Setting<>("Block Line Width", 2, 1, 10, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<Integer> slideDelay = new Setting<>("Slide Delay", 200, 1, 1000, v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<ColorSetting> textColor = new Setting<>("Text Color", new ColorSetting(Color.WHITE), v -> page.is(AutoCrystal.Pages.Render));
   private final Setting<Boolean> targetName = new Setting<>("TargetName", false, v -> page.is(AutoCrystal.Pages.Info));
   private final Setting<Boolean> currentSide = new Setting<>("CurrentSide", true, v -> page.is(AutoCrystal.Pages.Info));
   private final Setting<Boolean> speed = new Setting<>("Speed", true, v -> page.is(AutoCrystal.Pages.Info));
   private final Setting<Boolean> confirmInfo = new Setting<>("ConfirmTime", true, v -> page.is(AutoCrystal.Pages.Info));
   private final Setting<Boolean> calcInfo = new Setting<>("CalcInfo", false, v -> page.is(AutoCrystal.Pages.Info));
   public static class_1657 target;
   private AutoCrystal.PlaceData currentData;
   private class_3965 bestPosition;
   private class_1511 bestCrystal;
   private class_1511 secondaryCrystal;
   private final TickTimer placeTimer = new TickTimer();
   private final TickTimer breakTimer = new TickTimer();
   private final TickTimer calcTimer = new TickTimer();
   private final TickTimer placeSyncTimer = new TickTimer();
   private final Timer pauseTimer = new Timer();
   private final CrystalManager crystalManager = new CrystalManager();
   public float renderDamage;
   public float renderSelfDamage;
   public float rotationYaw;
   public float rotationPitch;
   private int prevCrystalsAmount;
   private int crystalSpeed;
   private int invTimer;
   private int rotationTicks;
   private boolean rotated;
   private boolean rotating;
   private boolean facePlacing;
   private boolean placedOnSpawn;
   private long confirmTime;
   private long calcTime;
   private class_2338 renderPos;
   private class_2338 prevRenderPos;
   private long renderMultiplier;
   private long currentId;
   private final Map<class_2338, Long> renderPositions = new ConcurrentHashMap<>();
   private AutoCrystal.RotationVec rotationVec;
   public AutoCrystal.State currentState = AutoCrystal.State.NoTarget;

   public AutoCrystal() {
      super("AutoCrystal", Module.Category.COMBAT);
   }

   @Override
   public void onEnable() {
      this.resetVars();
   }

   @Override
   public void onDisable() {
      this.resetVars();
   }

   private void resetVars() {
      this.facePlacing = false;
      this.rotated = false;
      this.renderDamage = 0.0F;
      this.renderSelfDamage = 0.0F;
      this.renderMultiplier = 0L;
      this.confirmTime = 0L;
      this.renderPositions.clear();
      this.crystalManager.reset();
      this.breakTimer.reset();
      this.placeTimer.reset();
      this.calcTimer.reset();
      this.bestCrystal = null;
      this.bestPosition = null;
      this.currentData = null;
      this.renderPos = null;
      this.prevRenderPos = null;
      this.currentState = AutoCrystal.State.NoTarget;
      target = null;
      this.rotationVec = null;
   }

   @EventHandler
   public void onPlayerUpdate(PlayerUpdateEvent event) {
      if (!this.rotate.is(AutoCrystal.Rotation.OFF)) {
         this.calcRotations();
      }
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         target = Managers.COMBAT.getTarget(this.targetRange.getValue(), this.targetLogic.getValue());
         if (target == null || !target.method_29504() && !(target.method_6032() < 0.0F)) {
            if (this.placeTimer.passedTicks(20L)) {
               this.renderDamage = 0.0F;
            }

            if (mc.field_1724.method_6079().method_7909() != class_1802.field_8301
               && this.autoGapple.getValue()
               && mc.field_1690.field_1904.method_1434()
               && mc.field_1724.method_6047().method_7909() == class_1802.field_8301) {
               InventoryUtility.findItemInHotBar(class_1802.field_8367).switchTo();
            }

            if (this.invTimer++ >= 20) {
               this.crystalSpeed = MathUtility.clamp(this.prevCrystalsAmount - InventoryUtility.getItemCount(class_1802.field_8301), 0, 255);
               this.prevCrystalsAmount = InventoryUtility.getItemCount(class_1802.field_8301);
               this.invTimer = 0;
            }

            if (!this.rotate.is(AutoCrystal.Rotation.OFF) && mc.field_1724 != null && this.rotating) {
               boolean hitVisible = this.bestCrystal == null || PlayerUtility.canSee(this.bestCrystal.method_19538());
               boolean placeVisible = this.bestPosition == null || PlayerUtility.canSee(this.bestPosition.method_17784());
               if (mc.field_1724.field_6012 % 5 != 0 || !this.rayTraceBypass.getValue() || hitVisible && placeVisible) {
                  mc.field_1724.method_36457(this.rotationPitch);
               } else {
                  mc.field_1724.method_36457(-90.0F);
               }

               mc.field_1724.method_36456(this.rotationYaw);
            }
         } else {
            target = null;
            this.currentState = AutoCrystal.State.NoTarget;
         }
      }
   }

   @EventHandler(
      priority = 200
   )
   public void onTick(EventTick e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1724 != null
            && (!this.await.getValue() || this.placeTimer.passedTicks(20L) || this.calcTimer.passedTicks((long)(Managers.SERVER.getPing() / 25.0F)))) {
            this.calcPosition(this.placeRange.getValue(), mc.field_1724.method_19538());
         }

         this.getCrystalToExplode();
         if (this.timing.is(AutoCrystal.Timing.NORMAL)) {
            this.doAction();
         }
      }
   }

   @EventHandler
   public void onPostSync(EventPostSync e) {
      if (this.timing.is(AutoCrystal.Timing.SEQUENTIAL)) {
         this.doAction();
      }
   }

   private void doAction() {
      if (!this.sequential.is(AutoCrystal.Sequential.Off) && !this.rotate.is(AutoCrystal.Rotation.MATRIX)) {
         if (this.bestCrystal != null
            && this.breakTimer.passedTicks((this.facePlacing ? this.lowBreakDelay.getValue() : this.breakDelay.getValue()).intValue())) {
            this.attackCrystal(this.bestCrystal);
         }

         if (this.bestPosition != null
            && this.placeTimer.passedTicks((this.facePlacing ? this.lowPlaceDelay.getValue() : this.placeDelay.getValue()).intValue())
            && !this.placedOnSpawn) {
            this.placeCrystal(this.bestPosition, false, false);
         }
      } else if (this.bestCrystal != null
         && this.breakTimer.passedTicks((this.facePlacing ? this.lowBreakDelay.getValue() : this.breakDelay.getValue()).intValue())) {
         this.attackCrystal(this.bestCrystal);
      } else if (this.bestPosition != null
         && this.placeTimer.passedTicks((this.facePlacing ? this.lowPlaceDelay.getValue() : this.placeDelay.getValue()).intValue())
         && !this.placedOnSpawn) {
         this.placeCrystal(this.bestPosition, false, false);
      }

      this.placedOnSpawn = false;
   }

   @Override
   public String getDisplayInfo() {
      StringBuilder info = new StringBuilder();
      class_2350 side = this.bestPosition == null ? null : this.bestPosition.method_17780();
      if (side != null) {
         if (this.targetName.getValue() && target != null) {
            info.append(target.method_5477().getString()).append(" | ");
         }

         if (this.speed.getValue()) {
            info.append(this.crystalSpeed).append(" c/s").append(" | ");
         }

         if (this.currentSide.getValue()) {
            info.append(side.toString().toUpperCase()).append(" | ");
         }

         if (this.confirmInfo.getValue()) {
            info.append("c: ").append(this.confirmTime).append(" | ");
         }

         if (this.calcInfo.getValue()) {
            info.append("calc: ").append(this.calcTime).append(" | ");
         }
      }

      return info.length() < 4 ? info.toString() : info.substring(0, info.length() - 3);
   }

   @EventHandler
   public void onCrystalSpawn(@NotNull EventEntitySpawnPost e) {
      if (e.getEntity() instanceof class_1511 cr && this.crystalAge.is(0)) {
         long now = System.currentTimeMillis();

         for (Entry<class_2338, CrystalManager.Attempt> entry : this.crystalManager.getAwaitingPositions().entrySet()) {
            class_2338 bp = entry.getKey();
            CrystalManager.Attempt attempt = entry.getValue();
            if (cr.method_5707(bp.method_46558()) < 0.3) {
               this.confirmTime = now - attempt.getTime();
               this.crystalManager.confirmSpawn(bp);
               if (this.breakTimer.passedTicks((this.facePlacing ? this.lowBreakDelay.getValue() : this.breakDelay.getValue()).intValue())) {
                  this.handleSpawn(cr);
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (e.getPacket() instanceof class_2606 spawn) {
            this.processSpawnPacket(spawn.method_11183());
         }

         if (e.getPacket() instanceof class_2604 spawn) {
            this.processSpawnPacket(spawn.method_11167());
         }

         if (e.getPacket() instanceof class_2664 explosion) {
            for (class_1297 ent : Lists.newArrayList(mc.field_1687.method_18112())) {
               if (ent instanceof class_1511 crystal
                  && crystal.method_5649(explosion.method_11475(), explosion.method_11477(), explosion.method_11478()) <= 144.0
                  && !this.crystalManager.isDead(crystal.method_5628())) {
                  this.crystalManager.setDead(crystal.method_5628(), System.currentTimeMillis());
               }
            }
         }
      }
   }

   @EventHandler
   public void onBlockBreakClient(EventBreakBlock e) {
      if (target != null && target.method_5707(e.getPos().method_46558()) <= 4.0) {
         this.calcPosition(2.0F, e.getPos().method_46558());
      }
   }

   private void handleSpawn(class_1511 crystal) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.canAttackCrystal(crystal)) {
            this.attackCrystal(crystal);
            if (this.sequential.is(AutoCrystal.Sequential.Strong)
               && this.placeTimer.passedTicks((this.facePlacing ? this.lowPlaceDelay.getValue() : this.placeDelay.getValue()).intValue())) {
               Managers.ASYNC.run(() -> {
                  this.calcPosition(this.placeRange.getValue(), mc.field_1724.method_19538());
                  if (this.bestPosition != null) {
                     this.placeCrystal(this.bestPosition, false, true);
                  }
               });
            }
         }
      }
   }

   public void calcRotations() {
      if (!this.rotate.is(AutoCrystal.Rotation.OFF)
         && !this.shouldPause()
         && (!this.rotate.getValue().needSeparate() ? this.bestPosition != null || this.bestCrystal != null : this.rotationVec != null)
         && mc.field_1724 != null) {
         if (this.rotationTicks-- < 0 && this.rotate.getValue().needSeparate()) {
            this.rotationVec = null;
            return;
         }

         class_243 vec = !this.rotate.getValue().needSeparate()
            ? (this.bestPosition == null ? this.bestCrystal.method_19538() : this.rotate.getValue().getVector(this.bestPosition))
            : (this.rotationVec.hitVec() == null ? this.rotationVec.vec() : this.rotate.getValue().getVector(this.rotationVec.hitVec()));
         float yawDelta = class_3532.method_15393(
            (float)class_3532.method_15338(
                  Math.toDegrees(Math.atan2(vec.field_1350 - mc.field_1724.method_23321(), vec.field_1352 - mc.field_1724.method_23317())) - 90.0
               )
               - this.rotationYaw
         );
         float pitchDelta = (float)(
               -Math.toDegrees(
                  Math.atan2(
                     vec.field_1351 - (mc.field_1724.method_19538().field_1351 + mc.field_1724.method_18381(mc.field_1724.method_18376())),
                     Math.sqrt(Math.pow(vec.field_1352 - mc.field_1724.method_23317(), 2.0) + Math.pow(vec.field_1350 - mc.field_1724.method_23321(), 2.0))
                  )
               )
            )
            - this.rotationPitch;
         float angleToRad = (float)Math.toRadians(27 * (mc.field_1724.field_6012 % 30));
         yawDelta = (float)(yawDelta + Math.sin(angleToRad) * 3.0) + MathUtility.random(-1.0F, 1.0F);
         pitchDelta += MathUtility.random(-0.6F, 0.6F);
         if (yawDelta > 180.0F) {
            yawDelta -= 180.0F;
         }

         float yawStepVal = 180.0F;
         if (this.yawStep.getValue().isEnabled()) {
            yawStepVal = this.yawAngle.getValue();
         }

         float clampedYawDelta = class_3532.method_15363(class_3532.method_15379(yawDelta), -yawStepVal, yawStepVal);
         float clampedPitchDelta = class_3532.method_15363(pitchDelta, -45.0F, 45.0F);
         this.rotated = class_3532.method_15379(yawDelta) <= yawStepVal || !this.yawStep.getValue().isEnabled();
         float newYaw = this.rotationYaw + (yawDelta > 0.0F ? clampedYawDelta : -clampedYawDelta);
         float newPitch = class_3532.method_15363(this.rotationPitch + clampedPitchDelta, -90.0F, 90.0F);
         double gcdFix = Math.pow((Double)mc.field_1690.method_42495().method_41753() * 0.6 + 0.2, 3.0) * 1.2;
         this.rotationYaw = (float)(newYaw - (newYaw - this.rotationYaw) % gcdFix);
         this.rotationPitch = (float)(newPitch - (newPitch - this.rotationPitch) % gcdFix);
         ModuleManager.rotations.fixRotation = this.rotationYaw;
         this.rotating = true;
      } else {
         this.rotationYaw = mc.field_1724.method_36454();
         this.rotationPitch = mc.field_1724.method_36455();
         this.rotating = false;
      }
   }

   @Override
   public void onRender3D(class_4587 stack) {
      this.crystalManager.update();
      if (this.render.getValue()) {
         Map<class_2338, Long> cache = new ConcurrentHashMap<>(this.renderPositions);
         cache.forEach((pos, time) -> {
            if (System.currentTimeMillis() - time > 500L) {
               this.renderPositions.remove(pos);
            }
         });
         String dmg = MathUtility.round2(this.renderDamage) + (this.rselfDamage.getValue() ? " / " + MathUtility.round2(this.renderSelfDamage) : "");
         if (this.renderMode.is(AutoCrystal.Render.Fade)) {
            cache.forEach(
               (pos, time) -> {
                  if (System.currentTimeMillis() - time < 500L) {
                     int alpha = (int)(100.0F * (1.0F - (float)(System.currentTimeMillis() - time) / 500.0F));
                     Render3DEngine.FILLED_QUEUE
                        .add(new Render3DEngine.FillAction(new class_238(pos), Render2DEngine.injectAlpha(this.fillColor.getValue().getColorObject(), alpha)));
                     Render3DEngine.OUTLINE_QUEUE
                        .add(
                           new Render3DEngine.OutlineAction(
                              new class_238(pos),
                              Render2DEngine.injectAlpha(this.lineColor.getValue().getColorObject(), alpha),
                              this.lineWidth.getValue().intValue()
                           )
                        );
                     if (this.drawDamage.getValue()) {
                        Render3DEngine.drawTextIn3D(
                           dmg, pos.method_46558(), 0.0, 0.1, 0.0, Render2DEngine.applyOpacity(this.textColor.getValue().getColorObject(), alpha / 100.0F)
                        );
                     }
                  }
               }
            );
         } else if (this.renderMode.getValue() == AutoCrystal.Render.Slide && this.renderPos != null) {
            if (this.prevRenderPos == null) {
               this.prevRenderPos = this.renderPos;
            }

            if (this.renderPositions.isEmpty()) {
               return;
            }

            float mult = MathUtility.clamp((float)(System.currentTimeMillis() - this.renderMultiplier) / this.slideDelay.getValue().intValue(), 0.0F, 1.0F);
            class_238 interpolatedBox = Render3DEngine.interpolateBox(new class_238(this.prevRenderPos), new class_238(this.renderPos), mult);
            this.renderBox(dmg, interpolatedBox);
         } else if (this.renderPos != null) {
            if (this.renderPositions.isEmpty()) {
               return;
            }

            this.renderBox(dmg, new class_238(this.renderPos));
         }
      }

      if (target != null && this.renderExtrapolation.getValue().isEnabled()) {
         Render3DEngine.OUTLINE_QUEUE
            .add(
               new Render3DEngine.OutlineAction(
                  PredictUtility.predictBox(target, this.extrapolation.getValue()), this.extrapolationColor.getValue().getColorObject(), 1.0F
               )
            );
      }

      if (target != null && this.bestPosition != null && this.renderInteractVector.getValue().isEnabled()) {
         class_243 vec = this.bestPosition.method_17784();
         class_238 b = new class_238(
            vec.method_10216() - 0.05,
            vec.method_10214() - 0.05,
            vec.method_10215() - 0.05,
            vec.method_10216() + 0.05,
            vec.method_10214() + 0.05,
            vec.method_10215() + 0.05
         );
         Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(b, this.interactColor.getValue().getColorObject(), 1.0F));
         Render3DEngine.FILLED_QUEUE.add(new Render3DEngine.FillAction(b, Render2DEngine.applyOpacity(this.interactColor.getValue().getColorObject(), 0.6F)));
      }
   }

   private void renderBox(String dmg, class_238 box) {
      Render3DEngine.FILLED_QUEUE.add(new Render3DEngine.FillAction(box, this.fillColor.getValue().getColorObject()));
      Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(box, this.lineColor.getValue().getColorObject(), this.lineWidth.getValue().intValue()));
      if (this.drawDamage.getValue()) {
         Render3DEngine.drawTextIn3D(dmg, box.method_1005(), 0.0, 0.1, 0.0, this.textColor.getValue().getColorObject());
      }
   }

   public boolean shouldPause() {
      if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
         boolean offhand = mc.field_1724.method_6079().method_7909() instanceof class_1774;
         boolean mainHand = mc.field_1724.method_6047().method_7909() instanceof class_1774;
         if (!this.pauseTimer.passedMs(1000L)) {
            this.currentState = AutoCrystal.State.ExternalPause;
            return true;
         } else if (mc.field_1761.method_2923() && !offhand && this.mining.getValue()) {
            this.currentState = AutoCrystal.State.Mining;
            return true;
         } else if (this.autoSwitch.is(AutoCrystal.Switch.NONE) && !offhand && !mainHand) {
            this.currentState = AutoCrystal.State.NoCrystalls;
            return true;
         } else if ((this.autoSwitch.is(AutoCrystal.Switch.SILENT) || this.autoSwitch.is(AutoCrystal.Switch.NORMAL))
            && !InventoryUtility.findItemInHotBar(class_1802.field_8301).found()
            && !offhand) {
            this.currentState = AutoCrystal.State.NoCrystalls;
            return true;
         } else if (this.autoSwitch.is(AutoCrystal.Switch.INVENTORY) && !InventoryUtility.findItemInInventory(class_1802.field_8301).found() && !offhand) {
            this.currentState = AutoCrystal.State.NoCrystalls;
            return true;
         } else if (mc.field_1724.method_6115() && this.eating.getValue()) {
            this.currentState = AutoCrystal.State.Eating;
            return true;
         } else if (this.rotationMarkedDirty()) {
            this.currentState = AutoCrystal.State.ExternalPause;
            return true;
         } else if (mc.field_1724.method_6032() + mc.field_1724.method_6067() < this.pauseHP.getValue()) {
            this.currentState = AutoCrystal.State.LowHP;
            return true;
         } else if (!offhand
            && this.autoGapple.getValue()
            && mc.field_1690.field_1904.method_1434()
            && mc.field_1724.method_6047().method_7909() == class_1802.field_8367) {
            this.currentState = AutoCrystal.State.Eating;
            return true;
         } else {
            boolean silentWeakness = this.antiWeakness.getValue() == AutoCrystal.Switch.SILENT || this.antiWeakness.getValue() == AutoCrystal.Switch.INVENTORY;
            boolean silent = this.autoSwitch.getValue() == AutoCrystal.Switch.SILENT || this.autoSwitch.getValue() == AutoCrystal.Switch.INVENTORY;
            boolean switchPause1 = this.switchPause.getValue().isEnabled()
               && !Managers.PLAYER.switchTimer.passedMs(this.switchDelay.getValue().intValue())
               && !silent
               && !silentWeakness;
            if (!switchPause1) {
               this.currentState = AutoCrystal.State.Active;
            }

            return switchPause1;
         }
      } else {
         return true;
      }
   }

   public boolean rotationMarkedDirty() {
      if (ModuleManager.middleClick.isEnabled() && mc.field_1690.field_1871.method_1434() && this.middleClick.getValue()) {
         return true;
      } else if (this.inventory.getValue() && Managers.PLAYER.inInventory) {
         return true;
      } else {
         return ModuleManager.autoTrap.isEnabled() ? true : ModuleManager.aura.isEnabled() && Aura.target != null && this.aura.getValue();
      }
   }

   public void attackCrystal(class_1511 crystal) {
      if (mc.field_1724 != null
         && mc.field_1687 != null
         && mc.field_1761 != null
         && crystal != null
         && (!this.crystalManager.isDead(crystal.method_5628()) || !this.inhibit.getValue())) {
         if (!this.shouldPause() && target != null) {
            class_1293 weaknessEffect = mc.field_1724.method_6112(class_1294.field_5911);
            class_1293 strengthEffect = mc.field_1724.method_6112(class_1294.field_5910);
            int prevSlot = -1;
            SearchInvResult antiWeaknessResult = InventoryUtility.getAntiWeaknessItem();
            SearchInvResult antiWeaknessResultInv = InventoryUtility.findInInventory(
               itemStack -> itemStack.method_7909() instanceof class_1829
                  || itemStack.method_7909() instanceof class_1810
                  || itemStack.method_7909() instanceof class_1743
                  || itemStack.method_7909() instanceof class_1821
            );
            if (this.antiWeakness.getValue() != AutoCrystal.Switch.NONE
               && weaknessEffect != null
               && (strengthEffect == null || strengthEffect.method_5578() < weaknessEffect.method_5578())) {
               prevSlot = this.switchTo(antiWeaknessResult, antiWeaknessResultInv, this.antiWeakness);
            }

            this.sendPacket(class_2824.method_34206(crystal, mc.field_1724.method_5715()));
            this.swingHand(false, true);
            if (this.rotate.getValue().needSeparate()
               && !Managers.PLAYER.checkRtx(this.rotationYaw, this.rotationPitch, this.explodeRange.getValue(), this.explodeWallRange.getValue(), crystal)) {
               this.rotationVec = new AutoCrystal.RotationVec(crystal.method_5829().method_1005(), null, false);
            }

            this.breakTimer.reset();
            this.crystalManager.onAttack(crystal);
            this.rotationTicks = 10;

            for (class_1297 ent : Lists.newArrayList(mc.field_1687.method_18112())) {
               if (ent instanceof class_1511 exCrystal
                  && exCrystal.method_5649(crystal.method_23317(), crystal.method_23318(), crystal.method_23321()) <= 144.0
                  && !this.crystalManager.isDead(exCrystal.method_5628())) {
                  this.crystalManager.setDead(exCrystal.method_5628(), System.currentTimeMillis());
               }
            }

            if (prevSlot != -1) {
               if (this.antiWeakness.getValue() == AutoCrystal.Switch.SILENT) {
                  mc.field_1724.method_31548().field_7545 = prevSlot;
                  this.sendPacket(new class_2868(prevSlot));
               }

               if (this.antiWeakness.getValue() == AutoCrystal.Switch.INVENTORY) {
                  mc.field_1761
                     .method_2906(mc.field_1724.field_7512.field_7763, prevSlot, mc.field_1724.method_31548().field_7545, class_1713.field_7791, mc.field_1724);
                  this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               }
            }
         }
      }
   }

   private boolean canAttackCrystal(class_1511 cr) {
      if (this.crystalAge.getValue() != 0 && cr.field_6012 < this.crystalAge.getValue()) {
         return false;
      } else if (PlayerUtility.squaredDistanceFromEyes(cr.method_5829().method_1005())
         > (InteractionUtility.canSee(cr) ? this.explodeRange.getPow2Value() : this.explodeWallRange.getPow2Value())) {
         return false;
      } else if (!cr.method_5805()) {
         return false;
      } else {
         float damage = ExplosionUtility.getAutoCrystalDamage(cr.method_19538(), target, this.getPredictTicks(), false);
         float selfDamage = ExplosionUtility.getSelfExplosionDamage(cr.method_19538(), this.getSelfPredictTicks(), false);
         boolean overrideDamage = this.shouldOverrideMaxSelfDmg(damage, selfDamage);
         if (this.protectFriends.getValue()) {
            for (class_1657 pl : Lists.newArrayList(mc.field_1687.method_18456())) {
               if (Managers.FRIEND.isFriend(pl)) {
                  float fdamage = ExplosionUtility.getAutoCrystalDamage(cr.method_19538(), pl, this.getPredictTicks(), false);
                  if (fdamage > selfDamage) {
                     selfDamage = fdamage;
                  }
               }
            }
         }

         return !(selfDamage > this.maxSelfDamage.getValue()) || overrideDamage;
      }
   }

   private int switchTo(SearchInvResult result, SearchInvResult resultInv, @NotNull Setting<AutoCrystal.Switch> switchMode) {
      if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
         int prevSlot = mc.field_1724.method_31548().field_7545;
         switch ((AutoCrystal.Switch)switchMode.getValue()) {
            case NONE:
            default:
               break;
            case NORMAL:
               result.switchTo();
               break;
            case SILENT:
               result.switchToSilent();
               break;
            case INVENTORY:
               if (resultInv.found()) {
                  prevSlot = resultInv.slot();
                  mc.field_1761
                     .method_2906(mc.field_1724.field_7512.field_7763, prevSlot, mc.field_1724.method_31548().field_7545, class_1713.field_7791, mc.field_1724);
                  this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               }
         }

         return prevSlot;
      } else {
         return -1;
      }
   }

   public void placeCrystal(class_3965 bhr, boolean packetRotate, boolean onSpawn) {
      if (!this.shouldPause() && mc.field_1724 != null && bhr != null) {
         int prevSlot = -1;
         SearchInvResult crystalResult = InventoryUtility.findItemInHotBar(class_1802.field_8301);
         SearchInvResult crystalResultInv = InventoryUtility.findItemInInventory(class_1802.field_8301);
         boolean offhand = mc.field_1724.method_6079().method_7909() instanceof class_1774;
         boolean holdingCrystal = mc.field_1724.method_6047().method_7909() instanceof class_1774 || offhand;
         if (!this.rotate.is(AutoCrystal.Rotation.OFF)) {
            this.rotationVec = new AutoCrystal.RotationVec(bhr.method_17784(), bhr, true);
            if (packetRotate) {
               float[] angle = InteractionUtility.calculateAngle(bhr.method_17784());
               this.sendPacket(
                  new class_2830(
                     mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), angle[0], angle[1], mc.field_1724.method_24828()
                  )
               );
            } else if (!this.rotated && !this.rotate.getValue().needSeparate()) {
               return;
            }
         }

         if (!this.isPositionBlockedByEntity(bhr.method_17777(), false)) {
            if (this.autoSwitch.getValue() != AutoCrystal.Switch.NONE && !holdingCrystal) {
               prevSlot = this.switchTo(crystalResult, crystalResultInv, this.autoSwitch);
            }

            if (mc.field_1724.method_6047().method_7909() instanceof class_1774 || offhand || this.autoSwitch.getValue() == AutoCrystal.Switch.SILENT) {
               this.sendSequencedPacket(id -> new class_2885(offhand ? class_1268.field_5810 : class_1268.field_5808, bhr, id));
               this.swingHand(offhand, false);
               if (this.breakTimer.passedTicks(this.breakDelay.getValue().intValue())
                  && this.idPredict.getValue()
                  && (!this.idPredictOnlyDuels.getValue() || mc.field_1687.method_8621().method_11965() == 100.0)) {
                  this.predictAttack();
               }

               this.placeTimer.reset();
               this.rotationTicks = 10;
               if (!bhr.method_17777().equals(this.renderPos)) {
                  this.renderMultiplier = System.currentTimeMillis();
                  this.prevRenderPos = this.renderPos;
                  this.renderPos = bhr.method_17777();
               }

               this.crystalManager.addAwaitingPos(bhr.method_17777());
               this.renderPositions.put(bhr.method_17777(), System.currentTimeMillis());
               this.postPlaceSwitch(prevSlot);
               if (onSpawn) {
                  this.placedOnSpawn = true;
                  this.placeSyncTimer.reset();
               }
            }
         }
      }
   }

   private void postPlaceSwitch(int slot) {
      if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
         if (this.autoSwitch.getValue() == AutoCrystal.Switch.SILENT && slot != -1) {
            mc.field_1724.method_31548().field_7545 = slot;
            this.sendPacket(new class_2868(slot));
         }

         if (this.autoSwitch.getValue() == AutoCrystal.Switch.INVENTORY && slot != -1) {
            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slot, mc.field_1724.method_31548().field_7545, class_1713.field_7791, mc.field_1724);
            this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
         }
      }
   }

   public void calcPosition(float range, class_243 center) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         long currentTime = System.currentTimeMillis();
         this.calcTimer.reset();
         if (target == null) {
            this.renderPos = null;
            this.prevRenderPos = null;
            this.bestPosition = null;
            this.currentData = null;
         } else {
            List<AutoCrystal.PlaceData> list = this.getPossibleBlocks(target, center, range)
               .stream()
               .filter(data -> this.isSafe(data.damage, data.selfDamage, data.overrideDamage))
               .toList();
            this.bestPosition = list.isEmpty() ? null : this.filterPositions(list);
            this.calcTime = System.currentTimeMillis() - currentTime;
         }
      }
   }

   @NotNull
   private List<AutoCrystal.PlaceData> getPossibleBlocks(class_1657 target, class_243 center, float range) {
      List<AutoCrystal.PlaceData> blocks = new ArrayList<>();
      class_2338 playerPos = class_2338.method_49638(center);
      class_243 predictedPlayerPos = PredictUtility.predictPosition(mc.field_1724, this.getSelfPredictTicks());
      int r = (int)Math.ceil(range);

      for (int x = playerPos.method_10263() - r; x <= playerPos.method_10263() + r; x++) {
         for (int y = playerPos.method_10264() - r; y <= playerPos.method_10264() + r; y++) {
            for (int z = playerPos.method_10260() - r; z <= playerPos.method_10260() + r; z++) {
               AutoCrystal.PlaceData data = this.getPlaceData(new class_2338(x, y, z), target, predictedPlayerPos);
               if (data != null) {
                  blocks.add(data);
               }
            }
         }
      }

      return blocks;
   }

   @NotNull
   private List<AutoCrystal.CrystalData> getPossibleCrystals(class_1657 target) {
      List<AutoCrystal.CrystalData> crystals = new ArrayList<>();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         for (class_1297 ent : Lists.newArrayList(mc.field_1687.method_18112())) {
            if (ent instanceof class_1511 cr
               && !this.crystalManager.isBlocked(cr.method_5628())
               && (this.crystalAge.getValue() == 0 || cr.field_6012 >= this.crystalAge.getValue())
               && (!this.crystalManager.isDead(cr.method_5628()) || !this.inhibit.getValue())
               && !(
                  PlayerUtility.squaredDistanceFromEyes(ent.method_5829().method_1005())
                     > (InteractionUtility.canSee(cr) ? this.explodeRange.getPow2Value() : this.explodeWallRange.getPow2Value())
               )
               && ent.method_5805()) {
               float damage = ExplosionUtility.getAutoCrystalDamage(ent.method_19538(), target, this.getPredictTicks(), false);
               float selfDamage = ExplosionUtility.getSelfExplosionDamage(ent.method_19538(), this.getSelfPredictTicks(), false);
               boolean overrideDamage = this.shouldOverrideMaxSelfDmg(damage, selfDamage);
               if (this.protectFriends.getValue()) {
                  for (class_1657 pl : Lists.newArrayList(mc.field_1687.method_18456())) {
                     if (Managers.FRIEND.isFriend(pl)) {
                        float fdamage = ExplosionUtility.getAutoCrystalDamage(ent.method_19538(), pl, this.getPredictTicks(), false);
                        if (fdamage > selfDamage) {
                           selfDamage = fdamage;
                        }
                     }
                  }
               }

               if (!(damage < 1.5F) && (!(selfDamage > this.maxSelfDamage.getValue()) || overrideDamage)) {
                  crystals.add(new AutoCrystal.CrystalData((class_1511)ent, damage, selfDamage, overrideDamage));
               }
            }
         }

         return crystals;
      } else {
         return crystals;
      }
   }

   public void getCrystalToExplode() {
      if (target == null) {
         this.bestCrystal = null;
      } else if (this.secondaryCrystal != null) {
         if (this.canAttackCrystal(this.secondaryCrystal)) {
            this.bestCrystal = this.secondaryCrystal;
            this.debug("Secondary Crystal Accepted");
         } else {
            this.debug("Secondary Crystal Declined");
         }

         this.secondaryCrystal = null;
      } else {
         List<AutoCrystal.CrystalData> list = this.getPossibleCrystals(target)
            .stream()
            .filter(data -> this.isSafe(data.damage, data.selfDamage, data.overrideDamage))
            .toList();
         this.bestCrystal = list.isEmpty() ? null : this.filterCrystals(list);
      }
   }

   public boolean isSafe(float damage, float selfDamage, boolean overrideDamage) {
      if (mc.field_1724 == null || mc.field_1687 == null) {
         return false;
      } else if (overrideDamage) {
         return true;
      } else if (selfDamage + 0.5 > mc.field_1724.method_6032() + mc.field_1724.method_6067()) {
         return false;
      } else {
         return this.efficiency.getValue().isEnabled() ? damage / selfDamage >= this.efficiencyFactor.getValue() : true;
      }
   }

   @Nullable
   private class_3965 filterPositions(@NotNull List<AutoCrystal.PlaceData> clearedList) {
      AutoCrystal.PlaceData bestData = null;
      float bestVal = 0.0F;

      for (AutoCrystal.PlaceData data : clearedList) {
         if ((this.shouldOverrideMinDmg(data.damage) || data.damage > this.minDamage.getValue())
            && (
               bestData == null
                  || !data.overrideDamage
                  || !(target.method_6067() + target.method_6032() < bestData.damage)
                  || !(bestData.selfDamage < data.selfDamage)
            )) {
            boolean shouldStopOverride = bestData != null
               && bestData.overrideDamage
               && data.damage > target.method_6032() + target.method_6067()
               && data.selfDamage < bestData.selfDamage;
            float safetyComparatorDelta = shouldStopOverride ? 10.0F : 1.0F;
            if (bestData != null && Math.abs(bestData.damage - data.damage) < safetyComparatorDelta && Math.abs(bestData.selfDamage - data.selfDamage) > 1.0F) {
               if (bestData.selfDamage >= data.selfDamage) {
                  bestData = data;
                  bestVal = data.damage;
               }
            } else if (bestVal < data.damage) {
               bestData = data;
               bestVal = data.damage;
            }
         }
      }

      if (bestData == null) {
         return null;
      } else {
         this.facePlacing = bestData.damage < this.minDamage.getValue();
         this.renderDamage = bestData.damage;
         this.renderSelfDamage = bestData.selfDamage;
         this.currentData = bestData;
         return bestData.bhr;
      }
   }

   @Nullable
   private class_1511 filterCrystals(@NotNull List<AutoCrystal.CrystalData> clearedList) {
      AutoCrystal.CrystalData bestData = null;
      float bestVal = 0.0F;

      for (AutoCrystal.CrystalData data : clearedList) {
         if ((this.shouldOverrideMinDmg(data.damage) || data.damage > this.minDamage.getValue())
            && (
               bestData == null
                  || !data.overrideDamage
                  || !(target.method_6067() + target.method_6032() < bestData.damage)
                  || !(bestData.selfDamage < data.selfDamage)
            )) {
            boolean shouldStopOverride = bestData != null
               && bestData.overrideDamage
               && data.damage > target.method_6032() + target.method_6067()
               && data.selfDamage < bestData.selfDamage;
            float safetyComparatorDelta = shouldStopOverride ? 10.0F : 1.0F;
            if (bestData != null && Math.abs(bestData.damage - data.damage) < safetyComparatorDelta && Math.abs(bestData.selfDamage - data.selfDamage) > 1.0F) {
               if (bestData.selfDamage >= data.selfDamage) {
                  bestData = data;
                  bestVal = data.damage;
               }
            } else if (bestVal < data.damage) {
               bestData = data;
               bestVal = data.damage;
            }
         }
      }

      if (bestData == null) {
         return null;
      } else {
         this.renderDamage = bestData.damage();
         this.renderSelfDamage = bestData.selfDamage();
         return bestData.crystal;
      }
   }

   @Nullable
   public AutoCrystal.PlaceData getPlaceData(class_2338 bp, class_1657 target, class_243 predictedPlayerPos) {
      if (mc.field_1724 == null || mc.field_1687 == null) {
         return null;
      } else if (this.crystalManager.isPositionBlocked(bp)) {
         return null;
      } else if (!this.predictCrystalSpawn(bp, predictedPlayerPos)) {
         return null;
      } else if (target != null && target.method_19538().method_1025(bp.method_46558().method_1031(0.0, 0.5, 0.0)) > 144.0) {
         return null;
      } else {
         class_2248 base = mc.field_1687.method_8320(bp).method_26204();
         if (base != class_2246.field_10540 && base != class_2246.field_9987) {
            return null;
         } else {
            boolean freeSpace = mc.field_1687.method_22347(bp.method_10084());
            if (freeSpace && (!this.oldVer.getValue() || mc.field_1687.method_22347(bp.method_10084().method_10084()))) {
               if (this.isPositionBlockedByEntity(bp, true)) {
                  return null;
               } else {
                  class_243 crystalVec = new class_243(0.5F + bp.method_10263(), 1.0F + bp.method_10264(), 0.5F + bp.method_10260());
                  float damage = target == null ? 10.0F : ExplosionUtility.getAutoCrystalDamage(crystalVec, target, this.getPredictTicks(), false);
                  if (damage < 1.5F) {
                     return null;
                  } else {
                     float selfDamage = ExplosionUtility.getSelfExplosionDamage(crystalVec, this.getSelfPredictTicks(), false);
                     boolean overrideDamage = this.shouldOverrideMaxSelfDmg(damage, selfDamage);
                     if (this.protectFriends.getValue()) {
                        for (class_1657 pl : Lists.newArrayList(mc.field_1687.method_18456())) {
                           if (Managers.FRIEND.isFriend(pl)) {
                              float fdamage = ExplosionUtility.getAutoCrystalDamage(crystalVec, pl, this.getPredictTicks(), false);
                              if (fdamage > selfDamage) {
                                 selfDamage = fdamage;
                              }
                           }
                        }
                     }

                     if (selfDamage > this.maxSelfDamage.getValue() && !overrideDamage) {
                        return null;
                     } else {
                        class_3965 interactResult = this.getInteractResult(bp, crystalVec);
                        return interactResult == null ? null : new AutoCrystal.PlaceData(interactResult, damage, selfDamage, overrideDamage);
                     }
                  }
               }
            } else {
               return null;
            }
         }
      }
   }

   public boolean predictCrystalSpawn(class_2338 bp, class_243 predictedPlayerPos) {
      class_243 predictedPos = bp.method_46558().method_1031(0.0, 1.5, 0.0);
      float distance = (float)predictedPlayerPos.method_1031(0.0, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0).method_1025(predictedPos);
      return InteractionUtility.canSee(predictedPos) ? distance <= this.explodeRange.getPow2Value() : distance <= this.explodeWallRange.getPow2Value();
   }

   public class_3965 getInteractResult(class_2338 bp, class_243 crystalVec) {
      class_3965 interactResult = null;
      switch ((AutoCrystal.Interact)this.interact.getValue()) {
         case Default:
            interactResult = this.getDefaultInteract(crystalVec, bp);
            break;
         case Strict:
            interactResult = this.getStrictInteract(bp);
      }

      return interactResult;
   }

   public boolean isPositionBlockedByEntity(@NotNull class_2338 base, boolean calcPhase) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 box = new class_238(base.method_10084());
         if (mc.method_1562() == null || mc.method_1562().method_45734() == null || !mc.method_1562().method_45734().field_3761.contains("crystalpvp.cc")) {
            box = box.method_1009(0.0, 1.0, 0.0);
         }

         Iterator var4 = Lists.newArrayList(mc.field_1687.method_18112()).iterator();

         while (true) {
            if (!var4.hasNext()) {
               return false;
            }

            class_1297 ent = (class_1297)var4.next();
            if (ent != null && ent.method_5829().method_994(box) && !(ent instanceof class_1303) && !ModuleManager.speedMine.isBlockDrop(ent)) {
               if (!(ent instanceof class_1511 cr)) {
                  break;
               }

               if (!this.crystalManager.isDead(cr.method_5628())) {
                  if (this.crystalManager.isBlocked(cr.method_5628())) {
                     return true;
                  }

                  if (calcPhase) {
                     if (this.canAttackCrystal(cr)) {
                        continue;
                     }
                     break;
                  }

                  if (cr.method_19538().method_1025(box.method_1005()) > 0.3) {
                     this.secondaryCrystal = cr;
                     this.debug("secondary crystal created");
                  }
                  break;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isPositionBlockedByCrystal(@NotNull class_2338 base) {
      class_238 box = new class_238(base.method_10084());
      return Lists.newArrayList(mc.field_1687.method_18112())
         .stream()
         .anyMatch(ent -> ent != null && ent.method_5829().method_994(box) && ent instanceof class_1511);
   }

   public boolean shouldOverrideMaxSelfDmg(float damage, float selfDamage) {
      if (!this.overrideSelfDamage.getValue().isEnabled() || target == null) {
         return false;
      } else if (mc.field_1724 != null && mc.field_1687 != null) {
         boolean targetSafe = target.method_6079().method_7909() == class_1802.field_8288 || target.method_6047().method_7909() == class_1802.field_8288;
         boolean playerSafe = mc.field_1724.method_6079().method_7909() == class_1802.field_8288
            || mc.field_1724.method_6047().method_7909() == class_1802.field_8288;
         float targetHp = target.method_6032() + target.method_6067();
         float playerHp = mc.field_1724.method_6032() + mc.field_1724.method_6067();
         boolean canPop = damage > targetHp && targetSafe;
         boolean canKill = damage > targetHp && !targetSafe;
         boolean canPopSelf = selfDamage > playerHp && playerSafe;
         boolean canKillSelf = selfDamage > playerHp && !playerSafe;
         return canPopSelf && canKill && this.sacrificeTotem.getValue()
            ? true
            : selfDamage > this.maxSelfDamage.getValue() && (canPop || canKill) && !canKillSelf && !canPopSelf;
      } else {
         return false;
      }
   }

   public boolean shouldOverrideMinDmg(float damage) {
      if (target == null) {
         return false;
      } else if (this.isKeyPressed(this.facePlaceButton)) {
         return true;
      } else if (target.method_6032() + target.method_6067() - damage < 0.0F) {
         return true;
      } else {
         if (this.armorBreaker.getValue().isEnabled()) {
            for (class_1799 armor : target.method_5661()) {
               if (armor != null
                  && !armor.method_7909().equals(class_1802.field_8162)
                  && (float)(armor.method_7936() - armor.method_7919()) / armor.method_7936() * 100.0F < this.armorScale.getValue()) {
                  return true;
               }
            }
         }

         return target.method_6032() + target.method_6067() <= this.facePlaceHp.getValue();
      }
   }

   @Nullable
   private class_3965 getDefaultInteract(class_243 crystalVector, class_2338 bp) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (PlayerUtility.squaredDistanceFromEyes(crystalVector) > this.placeRange.getPow2Value()) {
            return null;
         } else {
            class_3965 wallCheck = mc.field_1687
               .method_17742(
                  new class_3959(InteractionUtility.getEyesPos(mc.field_1724), crystalVector, class_3960.field_17558, class_242.field_1348, mc.field_1724)
               );
            return wallCheck != null
                  && wallCheck.method_17783() == class_240.field_1332
                  && wallCheck.method_17777() != bp
                  && PlayerUtility.squaredDistanceFromEyes(crystalVector) > this.placeWallRange.getPow2Value()
               ? null
               : new class_3965(crystalVector, mc.field_1687.method_24794(bp.method_10084()) ? class_2350.field_11036 : class_2350.field_11033, bp, false);
         }
      } else {
         return null;
      }
   }

   @Nullable
   public class_3965 getStrictInteract(@NotNull class_2338 bp) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         float bestDistance = Float.MAX_VALUE;
         class_2350 bestDirection = null;
         float upPoint = this.strictCenter.getValue() ? (float)bp.method_46558().method_10214() : bp.method_10084().method_10264();
         if (mc.field_1724.method_33571().method_10214() > upPoint) {
            bestDirection = class_2350.field_11036;
         } else if (mc.field_1724.method_33571().method_10214() < bp.method_10264() && mc.field_1687.method_22347(bp.method_10074())) {
            bestDirection = class_2350.field_11033;
         } else {
            for (class_2350 dir : InteractionUtility.getStrictBlockDirections(bp)) {
               if (dir != class_2350.field_11036 && dir != class_2350.field_11033) {
                  class_243 directionVec = new class_243(
                     bp.method_10263() + 0.5 + dir.method_10163().method_10263() * 0.5,
                     bp.method_10264() + 0.99,
                     bp.method_10260() + 0.5 + dir.method_10163().method_10260() * 0.5
                  );
                  if (mc.field_1687.method_8320(bp.method_10093(dir)).method_45474()) {
                     float distance = PlayerUtility.squaredDistanceFromEyes(directionVec);
                     if (bestDistance > distance) {
                        bestDirection = dir;
                        bestDistance = distance;
                     }
                  }
               }
            }
         }

         if (bestDirection == null) {
            return null;
         } else {
            class_243 vec = InteractionUtility.getVisibleDirectionPoint(bestDirection, bp, this.placeWallRange.getValue(), this.placeRange.getValue());
            return vec == null ? null : new class_3965(vec, bestDirection, bp, false);
         }
      } else {
         return null;
      }
   }

   private void swingHand(boolean offHand, boolean attack) {
      switch ((AutoCrystal.Swing)this.swingMode.getValue()) {
         case Both:
            mc.field_1724.method_6104(offHand ? class_1268.field_5810 : class_1268.field_5808);
            break;
         case Place:
            if (!attack) {
               mc.field_1724.method_6104(offHand ? class_1268.field_5810 : class_1268.field_5808);
            } else {
               this.sendPacket(new class_2879(offHand ? class_1268.field_5810 : class_1268.field_5808));
            }
            break;
         case Break:
            if (attack) {
               mc.field_1724.method_6104(class_1268.field_5808);
            } else {
               this.sendPacket(new class_2879(class_1268.field_5808));
            }
      }
   }

   public void predictAttack() {
      for (int i = 1; i <= this.idAttacks.getValue(); i++) {
         int id = (int)(this.currentId + i);
         class_1297 entity = mc.field_1687.method_8469(id);
         if (entity == null || entity instanceof class_1511) {
            class_2824 attackPacket = class_2824.method_34206(mc.field_1724, mc.field_1724.method_5715());
            changeId(attackPacket, id);
            this.sendPacket(attackPacket);
            this.sendPacket(new class_2879(class_1268.field_5808));
         }
      }
   }

   public static void changeId(class_2824 packet, int id) {
      try {
         Field field = class_2824.class.getDeclaredField(FabricLoader.getInstance().isDevelopmentEnvironment() ? "entityId" : "field_12870");
         field.setAccessible(true);
         field.setInt(packet, id);
      } catch (Exception var3) {
      }
   }

   public void processSpawnPacket(int id) {
      if (id > this.currentId) {
         this.currentId = id;
      }
   }

   public AutoCrystal.PlaceData getCurrentData() {
      return this.currentData;
   }

   public long getCalcTime() {
      return this.calcTime;
   }

   public void pause() {
      this.pauseTimer.reset();
   }

   private int getPredictTicks() {
      return this.extrapolation.getValue();
   }

   private int getSelfPredictTicks() {
      return (int)Math.ceil(Managers.SERVER.getPing() * 1.5F / 50.0F);
   }

   public String getPauseState() {
      return this.currentState.name();
   }

   private record CrystalData(class_1511 crystal, float damage, float selfDamage, boolean overrideDamage) {
   }

   private static enum Interact {
      Default,
      Strict;
   }

   private static enum Pages {
      Main,
      Place,
      Break,
      Damages,
      Pause,
      Render,
      Switch,
      FailSafe,
      Info,
      IDPredict;
   }

   public record PlaceData(class_3965 bhr, float damage, float selfDamage, boolean overrideDamage) {
   }

   public static enum Render {
      Fade,
      Slide,
      Default;
   }

   public static enum Rotation {
      OFF(false, false),
      CC(false, true),
      NCP(false, false),
      GRIM(false, true),
      MATRIX(true, true);

      final boolean needSeparate;
      final boolean vecMod;

      private Rotation(boolean needSeparate, boolean vecMod) {
         this.needSeparate = needSeparate;
         this.vecMod = vecMod;
      }

      public class_243 getVector(class_3965 vec) {
         return this.vecMod ? vec.method_17777().method_46558().method_1031(0.0, 0.475, 0.0) : vec.method_17784();
      }

      public boolean needSeparate() {
         return this.needSeparate;
      }
   }

   private record RotationVec(class_243 vec, class_3965 hitVec, boolean place) {
   }

   public static enum Sequential {
      Off,
      Strict,
      Strong;
   }

   public static enum State {
      Active,
      Eating,
      LowHP,
      NoTarget,
      NoCrystalls,
      ExternalPause,
      Mining;
   }

   public static enum Swing {
      Both,
      Place,
      Break,
      ServerSide;
   }

   private static enum Switch {
      NONE,
      NORMAL,
      SILENT,
      INVENTORY;
   }

   private static enum Timing {
      NORMAL,
      SEQUENTIAL;
   }
}
