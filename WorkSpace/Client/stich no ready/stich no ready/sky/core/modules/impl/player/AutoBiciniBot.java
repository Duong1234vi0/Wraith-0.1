package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalNear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CClickWindowPacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import sky.core.SkyCore;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.impl.combat.AntiBot;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.InventoryUtil;

public class AutoBiciniBot extends Module {
   private static final int PORTAL_X = 131;
   private static final int PORTAL_Y = 64;
   private static final int PORTAL_Z = 47;
   private static final int ZONE_X1 = 54;
   private static final int ZONE_Z1 = 161;
   private static final int ZONE_X2 = 12;
   private static final int ZONE_Z2 = -62;
   private static final List PICKUP_ITEMS = List.of("Формула крабсбургера", "Крабсбургер", "Голова");
   private final Random random = new Random();
   private LivingEntity target = null;
   private final TimeUtil attackTimer = new TimeUtil();
   private final TimeUtil stateTimer = new TimeUtil();
   private final TimeUtil walkTimer = new TimeUtil();
   private final TimeUtil waitTimer = new TimeUtil();
   private final TimeUtil eventCheckTimer = new TimeUtil();
   private static final float ATTACK_DISTANCE = 3.0F;
   private static final float ATTACK_SEARCH_RANGE = 15.0F;
   private static final float APPROACH_DISTANCE = 2.5F;
   private static final long EVENT_CHECK_INTERVAL = 30000L;
   private float lastYaw = 0.0F;
   private float lastPitch = 0.0F;
   private float lastClampedYaw = 0.0F;
   private LivingEntity selectedTarget = null;
   private static final float JITTER_YAW_AMPLITUDE = 0.6F;
   private static final float JITTER_PITCH_AMPLITUDE = 0.3F;
   private static final List ALL_ANARCHIES = new ArrayList();
   private int currentAnarchyIndex = 0;
   private String currentAnarchy = null;
   private boolean waitingForResponse = false;
   private boolean waitingForEventCheck = false;
   private boolean teleported = false;
   private double lastX;
   private double lastY;
   private double lastZ;
   private boolean walkingToPortal = false;
   private int waitSeconds = 0;
   private boolean eating = false;
   private int lastSlot = -1;
   private BotState state;

   public AutoBiciniBot() {
      super("AutoBiciniBot", "Автоматически лутает ивенты бикинибот на спукитайме", Category.Miscellaneous);
      this.state = AutoBiciniBot.BotState.INIT_CHECK;
   }

   public void onEnable() {
      this.selectedTarget = null;
      this.currentAnarchyIndex = 0;
      this.waitingForResponse = false;
      this.waitingForEventCheck = false;
      this.teleported = false;
      this.walkingToPortal = false;
      this.waitSeconds = 0;
      this.currentAnarchy = (String)ALL_ANARCHIES.get(0);
      this.state = AutoBiciniBot.BotState.INIT_CHECK;
      this.stateTimer.reset();
      this.eventCheckTimer.reset();
      this.eating = false;
      this.lastSlot = -1;
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         Minecraft var10001 = mc;
         this.lastX = Minecraft.player.getPosX();
         var10001 = mc;
         this.lastY = Minecraft.player.getPosY();
         var10001 = mc;
         this.lastZ = Minecraft.player.getPosZ();
      }

      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      super.onEnable();
   }

   public void onDisable() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      mc.gameSettings.keyBindForward.setPressed(false);
      mc.gameSettings.keyBindUseItem.setPressed(false);
      this.target = null;
      this.selectedTarget = null;
      this.eating = false;
      this.lastSlot = -1;
      super.onDisable();
   }

   private boolean checkAndEat() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         float health = Minecraft.player.getHealth();
         var10000 = mc;
         float foodLevel = (float)Minecraft.player.getFoodStats().getFoodLevel();
         boolean needHeal = health < 14.0F;
         boolean needFood = foodLevel < 18.0F;
         if (!needHeal && !needFood) {
            if (this.eating) {
               this.stopEating();
            }

            return false;
         } else {
            Minecraft var10001;
            if (this.eating) {
               var10000 = mc;
               if (!Minecraft.player.isHandActive()) {
                  mc.gameSettings.keyBindUseItem.setPressed(true);
                  var10001 = mc;
                  mc.playerController.processRightClick(Minecraft.player, mc.world, Hand.MAIN_HAND);
               }

               return true;
            } else {
               int foodSlot = this.findFoodSlot(needHeal);
               if (foodSlot != -1) {
                  var10001 = mc;
                  this.lastSlot = Minecraft.player.inventory.currentItem;
                  var10000 = mc;
                  Minecraft.player.inventory.currentItem = foodSlot;
                  mc.gameSettings.keyBindUseItem.setPressed(true);
                  var10001 = mc;
                  mc.playerController.processRightClick(Minecraft.player, mc.world, Hand.MAIN_HAND);
                  this.eating = true;
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   private void stopEating() {
      mc.gameSettings.keyBindUseItem.setPressed(false);
      if (this.lastSlot != -1 && this.lastSlot >= 0 && this.lastSlot < 9) {
         Minecraft var10000 = mc;
         Minecraft.player.inventory.currentItem = this.lastSlot;
      }

      this.eating = false;
      this.lastSlot = -1;
   }

   private int findFoodSlot(boolean preferGoldenApple) {
      Minecraft var10000;
      int i;
      ItemStack stack;
      if (preferGoldenApple) {
         for(i = 0; i < 9; ++i) {
            var10000 = mc;
            stack = Minecraft.player.inventory.getStackInSlot(i);
            if (stack.getItem() == Items.GOLDEN_APPLE || stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
               return i;
            }
         }
      }

      for(i = 0; i < 9; ++i) {
         var10000 = mc;
         stack = Minecraft.player.inventory.getStackInSlot(i);
         if (stack.getItem().isFood() && stack.getItem() != Items.SPIDER_EYE && stack.getItem() != Items.ROTTEN_FLESH && stack.getItem() != Items.PUFFERFISH) {
            return i;
         }
      }

      return -1;
   }

   private boolean isInFarmZone() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         int minX = Math.min(54, 12);
         int maxX = Math.max(54, 12);
         int minZ = Math.min(161, -62);
         int maxZ = Math.max(161, -62);
         var10000 = mc;
         double x = Minecraft.player.getPosX();
         var10000 = mc;
         double z = Minecraft.player.getPosZ();
         return x >= (double)minX && x <= (double)maxX && z >= (double)minZ && z <= (double)maxZ;
      }
   }

   private boolean isNearPortal() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         double dx = Minecraft.player.getPosX() - 131.0;
         var10000 = mc;
         double dz = Minecraft.player.getPosZ() - 47.0;
         return Math.sqrt(dx * dx + dz * dz) < 30.0;
      }
   }

   private boolean isValidItem(ItemEntity item) {
      if (item == null) {
         return false;
      } else {
         ItemStack stack = item.getItem();
         Item itemType = stack.getItem();
         if (itemType != Items.PLAYER_HEAD && itemType != Items.ZOMBIE_HEAD && itemType != Items.SKELETON_SKULL && itemType != Items.CREEPER_HEAD && itemType != Items.WITHER_SKELETON_SKULL && itemType != Items.DRAGON_HEAD) {
            ResourceLocation res = Registry.ITEM.getKey(itemType);
            String registryName = res != null ? res.toString().toLowerCase() : "";
            if (!registryName.contains("head") && !registryName.contains("skull")) {
               String displayName = stack.getDisplayName().getString().toLowerCase();
               return displayName.contains("формула крабсбургера") || displayName.contains("крабсбургер") || displayName.contains("голова") || displayName.contains("череп") || displayName.contains("head") || displayName.contains("skull") || displayName.contains("★");
            } else {
               return true;
            }
         } else {
            return true;
         }
      }
   }

   private int parseWaitTime(String message) {
      Pattern secPattern = Pattern.compile("Начнётся через (\\d+) сек");
      Matcher secMatcher = secPattern.matcher(message);
      if (secMatcher.find()) {
         return Integer.parseInt(secMatcher.group(1));
      } else {
         Pattern minSecPattern = Pattern.compile("Начнётся через (\\d+) мин(?:\\s+(\\d+) сек)?");
         Matcher minSecMatcher = minSecPattern.matcher(message);
         if (minSecMatcher.find()) {
            int minutes = Integer.parseInt(minSecMatcher.group(1));
            int seconds = minSecMatcher.group(2) != null ? Integer.parseInt(minSecMatcher.group(2)) : 0;
            return minutes * 60 + seconds;
         } else {
            return 0;
         }
      }
   }

   private boolean isEventEnded(String message) {
      return message.contains("Удаление ивента") || message.contains("До следующего ивента") && !message.contains("Бикини Боттом") || message.contains("[Ивенты]") && !message.contains("Бикини Боттом") && !message.contains("Начнётся через");
   }

   private boolean isEventActive(String message) {
      return message.contains("Бикини Боттом") && (message.contains("Идёт страшный бой") || message.contains("Статус:") && !message.contains("Начнётся через") && !message.contains("Удаление"));
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      if (event.getPacket() instanceof SChatPacket) {
         SChatPacket packet = (SChatPacket)event.getPacket();
         String message = packet.getChatComponent().getString();
         if (message.contains("[Ивенты]")) {
            if (this.waitingForEventCheck) {
               this.waitingForEventCheck = false;
               if (this.isEventEnded(message)) {
                  BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
                  this.state = AutoBiciniBot.BotState.CONNECTING;
                  this.stateTimer.reset();
                  this.currentAnarchyIndex = 0;
                  this.currentAnarchy = (String)ALL_ANARCHIES.get(0);
               }

               return;
            }

            if (this.waitingForResponse) {
               this.waitingForResponse = false;
               if (message.contains("Бикини Боттом")) {
                  if (this.isEventActive(message)) {
                     this.state = AutoBiciniBot.BotState.WARPING;
                     this.stateTimer.reset();
                  } else if (message.contains("Начнётся через")) {
                     this.waitSeconds = this.parseWaitTime(message);
                     if (this.waitSeconds > 0) {
                        this.state = AutoBiciniBot.BotState.WAITING_EVENT_START;
                        this.waitTimer.reset();
                        this.stateTimer.reset();
                     } else {
                        this.state = AutoBiciniBot.BotState.WARPING;
                        this.stateTimer.reset();
                     }
                  } else if (message.contains("Удаление ивента")) {
                     this.nextAnarchy();
                  } else {
                     this.state = AutoBiciniBot.BotState.WARPING;
                     this.stateTimer.reset();
                  }
               } else {
                  this.nextAnarchy();
               }
            }
         }
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (!this.checkAndEat()) {
            switch (this.state) {
               case INIT_CHECK:
                  this.handleInitCheck();
                  break;
               case CONNECTING:
                  this.handleConnecting();
                  break;
               case CHECKING_EVENT:
                  this.handleCheckingEvent();
                  break;
               case WAITING_EVENT_START:
                  this.handleWaitingEventStart();
                  break;
               case WARPING:
                  this.handleWarping();
                  break;
               case WAITING_TELEPORT:
                  this.handleWaitingTeleport();
                  break;
               case GOING_TO_PORTAL:
                  this.handleGoingToPortal();
                  break;
               case ENTERING_PORTAL:
                  this.handleEnteringPortal();
                  break;
               case FARMING:
                  this.handleFarming();
            }

         }
      }
   }

   private void handleInitCheck() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      if (this.isInFarmZone()) {
         this.state = AutoBiciniBot.BotState.FARMING;
         this.stateTimer.reset();
         this.eventCheckTimer.reset();
      } else {
         this.state = AutoBiciniBot.BotState.CHECKING_EVENT;
         this.stateTimer.reset();
      }

   }

   private void handleConnecting() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      if (this.stateTimer.hasTimeElapsed(100L)) {
         Minecraft var10000 = mc;
         Minecraft.player.sendChatMessage("/" + this.currentAnarchy);
         this.state = AutoBiciniBot.BotState.CHECKING_EVENT;
         this.stateTimer.reset();
      }

   }

   private void handleCheckingEvent() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      if (!this.waitingForResponse && this.stateTimer.hasTimeElapsed(1500L)) {
         Minecraft var10000 = mc;
         Minecraft.player.sendChatMessage("/event delay");
         this.waitingForResponse = true;
         this.stateTimer.reset();
      }

      if (this.waitingForResponse && this.stateTimer.hasTimeElapsed(3000L)) {
         this.waitingForResponse = false;
         this.nextAnarchy();
      }

   }

   private void handleWaitingEventStart() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      long waitedMs = this.waitTimer.getElapsedTime();
      long needToWaitMs = (long)(this.waitSeconds + 2) * 1000L;
      if (waitedMs >= needToWaitMs) {
         this.state = AutoBiciniBot.BotState.WARPING;
         this.stateTimer.reset();
      }

   }

   private void handleWarping() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      if (this.isNearPortal()) {
         this.state = AutoBiciniBot.BotState.GOING_TO_PORTAL;
         this.stateTimer.reset();
      } else {
         if (this.stateTimer.hasTimeElapsed(1000L)) {
            Minecraft var10001 = mc;
            this.lastX = Minecraft.player.getPosX();
            var10001 = mc;
            this.lastY = Minecraft.player.getPosY();
            var10001 = mc;
            this.lastZ = Minecraft.player.getPosZ();
            this.teleported = false;
            Minecraft var10000 = mc;
            Minecraft.player.sendChatMessage("/warp BikiniBottom");
            this.state = AutoBiciniBot.BotState.WAITING_TELEPORT;
            this.stateTimer.reset();
         }

      }
   }

   private void handleWaitingTeleport() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      Minecraft var10000 = mc;
      double dx = Minecraft.player.getPosX() - this.lastX;
      var10000 = mc;
      double dy = Minecraft.player.getPosY() - this.lastY;
      var10000 = mc;
      double dz = Minecraft.player.getPosZ() - this.lastZ;
      double moved = Math.sqrt(dx * dx + dy * dy + dz * dz);
      if (moved > 10.0) {
         this.teleported = true;
         this.walkingToPortal = false;
         this.state = AutoBiciniBot.BotState.GOING_TO_PORTAL;
         this.stateTimer.reset();
      }

      if (this.stateTimer.hasTimeElapsed(5000L) && !this.teleported) {
         var10000 = mc;
         Minecraft.player.sendChatMessage("/warp BikiniBottom");
         Minecraft var10001 = mc;
         this.lastX = Minecraft.player.getPosX();
         var10001 = mc;
         this.lastY = Minecraft.player.getPosY();
         var10001 = mc;
         this.lastZ = Minecraft.player.getPosZ();
         this.stateTimer.reset();
      }

   }

   private void handleGoingToPortal() {
      if (this.stateTimer.hasTimeElapsed(1000L)) {
         double distToPortal = this.getDistanceToPortal();
         if (distToPortal < 2.0) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
            this.state = AutoBiciniBot.BotState.ENTERING_PORTAL;
            this.stateTimer.reset();
            this.walkTimer.reset();
         } else if (!BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() || !this.walkingToPortal) {
            this.walkingToPortal = true;
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(131, 64, 47));
         }

      }
   }

   private void handleEnteringPortal() {
      BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
      Minecraft var10001 = mc;
      double dx = 131.5 - Minecraft.player.getPosX();
      var10001 = mc;
      double dz = 47.5 - Minecraft.player.getPosZ();
      double dist = Math.sqrt(dx * dx + dz * dz);
      if (!(dist < 0.5) && !this.isInFarmZone()) {
         float targetYaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
         Minecraft var10000 = mc;
         Minecraft.player.rotationYaw = targetYaw;
         mc.gameSettings.keyBindForward.setPressed(true);
         if (this.walkTimer.hasTimeElapsed(5000L)) {
            mc.gameSettings.keyBindForward.setPressed(false);
            this.state = AutoBiciniBot.BotState.FARMING;
            this.stateTimer.reset();
            this.eventCheckTimer.reset();
         }

      } else {
         mc.gameSettings.keyBindForward.setPressed(false);
         this.state = AutoBiciniBot.BotState.FARMING;
         this.stateTimer.reset();
         this.eventCheckTimer.reset();
      }
   }

   private double getDistanceToPortal() {
      Minecraft var10000 = mc;
      double dx = Minecraft.player.getPosX() - 131.0;
      var10000 = mc;
      double dz = Minecraft.player.getPosZ() - 47.0;
      return Math.sqrt(dx * dx + dz * dz);
   }

   private void nextAnarchy() {
      ++this.currentAnarchyIndex;
      if (this.currentAnarchyIndex >= ALL_ANARCHIES.size()) {
         this.currentAnarchyIndex = 0;
      }

      this.currentAnarchy = (String)ALL_ANARCHIES.get(this.currentAnarchyIndex);
      this.state = AutoBiciniBot.BotState.CONNECTING;
      this.stateTimer.reset();
   }

   private void handleFarming() {
      mc.gameSettings.keyBindForward.setPressed(false);
      Minecraft var10000;
      if (this.eventCheckTimer.hasTimeElapsed(30000L) && !this.waitingForEventCheck) {
         var10000 = mc;
         Minecraft.player.sendChatMessage("/event delay");
         this.waitingForEventCheck = true;
         this.eventCheckTimer.reset();
      }

      this.target = this.findTargetForAttack();
      Minecraft var10002;
      if (this.target != null) {
         var10000 = mc;
         float distanceToTarget = Minecraft.player.getDistance(this.target);
         Minecraft var10001 = mc;
         var10002 = mc;
         float[] rots = this.calculateSpookyRotation(Minecraft.player.rotationYaw, Minecraft.player.rotationPitch, this.target);
         var10000 = mc;
         float prevYaw = Minecraft.player.rotationYaw;
         var10000 = mc;
         float prevPitch = Minecraft.player.rotationPitch;
         var10000 = mc;
         Minecraft.player.rotationYaw = rots[0];
         var10000 = mc;
         Minecraft.player.rotationPitch = rots[1];
         var10000 = mc;
         Minecraft.player.prevRotationYaw = prevYaw;
         var10000 = mc;
         Minecraft.player.prevRotationPitch = prevPitch;
         if (distanceToTarget > 3.0F) {
            this.goToTarget(this.target);
         } else if (this.isLookingAtTarget()) {
            this.attack();
         }

      } else {
         var10002 = mc;
         ItemEntity bestItem = (ItemEntity)mc.world.getEntitiesWithinAABB(ItemEntity.class, Minecraft.player.getBoundingBox().grow(50.0)).stream().filter(this::isInZone).filter(this::isValidItem).min(Comparator.comparingDouble((item) -> {
            Minecraft var10000 = mc;
            return Minecraft.player.getDistanceSq(item);
         })).orElse((Object)null);
         var10002 = mc;
         ZombieEntity bestZombie = (ZombieEntity)mc.world.getEntitiesWithinAABB(ZombieEntity.class, Minecraft.player.getBoundingBox().grow(50.0)).stream().filter(this::isInZone).filter(Entity::isAlive).min(Comparator.comparingDouble((zombie) -> {
            Minecraft var10000 = mc;
            return Minecraft.player.getDistanceSq(zombie);
         })).orElse((Object)null);
         if (bestItem != null) {
            this.goToEntity(bestItem);
         } else if (bestZombie != null) {
            this.goToEntity(bestZombie);
         } else {
            if (!BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
               this.walkInRectangle();
            }

         }
      }
   }

   private void walkInRectangle() {
      int minX = Math.min(54, 12);
      int maxX = Math.max(54, 12);
      int minZ = Math.min(161, -62);
      int maxZ = Math.max(161, -62);
      int destX = minX + this.random.nextInt(maxX - minX + 1);
      int destZ = minZ + this.random.nextInt(maxZ - minZ + 1);
      Minecraft var10000 = mc;
      int destY = (int)Minecraft.player.getPosY();
      BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(destX, destY, destZ));
   }

   private void goToTarget(LivingEntity target) {
      if (target != null) {
         BlockPos targetPos = target.getPosition();
         Goal currentGoal = BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().getGoal();
         boolean needNewPath = currentGoal == null || !BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing();
         if (!needNewPath && currentGoal instanceof GoalNear) {
            GoalNear goalNear = (GoalNear)currentGoal;
            double distToOldGoal = Math.sqrt(Math.pow((double)(goalNear.getGoalPos().getX() - targetPos.getX()), 2.0) + Math.pow((double)(goalNear.getGoalPos().getZ() - targetPos.getZ()), 2.0));
            if (distToOldGoal > 3.0) {
               needNewPath = true;
            }
         }

         if (needNewPath) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalNear(targetPos, 2));
         }

      }
   }

   private void goToEntity(Entity entity) {
      if (entity != null) {
         BlockPos pos = entity.getPosition();
         Goal currentGoal = BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().getGoal();
         boolean needNewPath = currentGoal == null || !BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing();
         int radius = entity instanceof ItemEntity ? 0 : 1;
         if (!needNewPath && currentGoal instanceof GoalNear) {
            GoalNear goalNear = (GoalNear)currentGoal;
            double dist = Math.sqrt(Math.pow((double)(goalNear.getGoalPos().getX() - pos.getX()), 2.0) + Math.pow((double)(goalNear.getGoalPos().getZ() - pos.getZ()), 2.0));
            if (entity instanceof ItemEntity && dist > 1.0) {
               needNewPath = true;
            } else if (dist > 5.0) {
               needNewPath = true;
            }
         }

         if (needNewPath) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalNear(pos, radius));
         }

      }
   }

   private float[] calculateSpookyRotation(float currentYaw, float currentPitch, LivingEntity target) {
      if (target == null) {
         return new float[]{currentYaw, currentPitch};
      } else {
         float gcd = this.getGCDValue();
         float neckHeight = target.getEyeHeight() - 0.3F;
         Vector3d targetPos = target.getPositionVec().add(0.0, (double)neckHeight, 0.0);
         float yawToTarget;
         if (this.selectedTarget == target) {
            float randomOffsetX = (this.random.nextFloat() - 0.5F) * 0.1F;
            yawToTarget = (this.random.nextFloat() - 0.5F) * 0.1F;
            targetPos = targetPos.add((double)randomOffsetX, 0.0, (double)yawToTarget);
         }

         Minecraft var10001 = mc;
         Vector3d vecToNeck = targetPos.subtract(Minecraft.player.getEyePosition(1.0F));
         yawToTarget = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vecToNeck.z, vecToNeck.x)) - 90.0);
         float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(vecToNeck.y, Math.hypot(vecToNeck.x, vecToNeck.z))));
         float yawDelta = MathHelper.wrapDegrees(yawToTarget - currentYaw);
         float pitchDelta = MathHelper.wrapDegrees(pitchToTarget - currentPitch);
         float clampedYaw = Math.min(Math.max(Math.abs(yawDelta), 1.0E-4F), 22.5F);
         float clampedPitch = Math.min(Math.max(Math.abs(pitchDelta), 1.0E-4F), 17.0F);
         float randomYawFactor = this.random.nextFloat() * 2.5F - 1.5F;
         float randomPitchFactor = this.random.nextFloat() * 2.5F - 1.0F;
         float randomThreshold = this.random.nextFloat() * 2.5F;
         float randomAddition = this.random.nextFloat() * 3.5F + 2.5F;
         if (this.selectedTarget != target) {
            clampedPitch = Math.max(Math.abs(pitchDelta), 1.0F);
         } else {
            clampedPitch /= 3.0F;
         }

         if (Math.abs(clampedYaw - this.lastClampedYaw) <= randomThreshold) {
            clampedYaw = this.lastClampedYaw + randomAddition;
         }

         clampedYaw += randomYawFactor;
         clampedPitch += randomPitchFactor;
         float yaw = currentYaw + (yawDelta > 0.0F ? clampedYaw : -clampedYaw);
         float pitch = MathHelper.clamp(currentPitch + (pitchDelta > 0.0F ? clampedPitch : -clampedPitch), -80.0F, 70.0F);
         Minecraft var10000 = mc;
         float t = (float)Minecraft.player.ticksExisted;
         float jitterYaw = (float)(Math.sin((double)(t * 0.5F)) * 0.6000000238418579 + Math.sin((double)(t * 2.3F)) * 0.1);
         float jitterPitch = (float)(Math.sin((double)(t * 0.65F)) * 0.30000001192092896 + Math.sin((double)(t * 2.8F)) * 0.05);
         yaw += jitterYaw;
         pitch += jitterPitch;
         yaw -= (yaw - currentYaw) % gcd;
         pitch -= (pitch - currentPitch) % gcd;
         this.lastClampedYaw = clampedYaw;
         this.lastYaw = yaw;
         this.lastPitch = pitch;
         this.selectedTarget = target;
         return new float[]{yaw, pitch};
      }
   }

   private float getGCDValue() {
      float sensitivity = (float)(mc.gameSettings.mouseSensitivity * 0.6000000238418579 + 0.20000000298023224);
      return sensitivity * sensitivity * sensitivity * 1.2F;
   }

   private boolean isLookingAtTarget() {
      if (this.target == null) {
         return false;
      } else {
         Vector3d targetPos = this.target.getPositionVec().add(0.0, (double)(this.target.getEyeHeight() - 0.3F), 0.0);
         Minecraft var10001 = mc;
         Vector3d vecToTarget = targetPos.subtract(Minecraft.player.getEyePosition(1.0F));
         float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vecToTarget.z, vecToTarget.x)) - 90.0);
         float targetPitch = (float)(-Math.toDegrees(Math.atan2(vecToTarget.y, Math.hypot(vecToTarget.x, vecToTarget.z))));
         var10001 = mc;
         float yawDiff = Math.abs(MathHelper.wrapDegrees(targetYaw - Minecraft.player.rotationYaw));
         var10001 = mc;
         float pitchDiff = Math.abs(targetPitch - Minecraft.player.rotationPitch);
         return yawDiff <= 15.0F && pitchDiff <= 15.0F;
      }
   }

   private void attack() {
      if (this.canAttack() && this.target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player.getDistance(this.target) <= 3.0F) {
            Minecraft var10001 = mc;
            mc.playerController.attackEntity(Minecraft.player, this.target);
            var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
            this.attackTimer.reset();
            this.shieldBreaker();
         }
      }

   }

   public boolean canAttack() {
      boolean var1;
      if (this.attackTimer.hasTimeElapsed(400L)) {
         Minecraft var10000 = mc;
         if (Minecraft.player.getCooledAttackStrength(0.5F) > 0.92F) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   private boolean shieldBreaker() {
      int axeSlot = InventoryUtil.findAxeSlot();
      if (this.target != null && this.target.getActiveItemStack().getItem() == Items.SHIELD && axeSlot != -1) {
         Minecraft var10000;
         Minecraft var10001;
         ClientPlayNetHandler var2;
         Minecraft var10003;
         if (axeSlot < 9) {
            mc.getConnection().sendPacket(new CHeldItemChangePacket(axeSlot));
            var10001 = mc;
            mc.playerController.attackEntity(Minecraft.player, this.target);
            var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
            var2 = mc.getConnection();
            var10003 = mc;
            var2.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
         } else {
            var2 = mc.getConnection();
            var10003 = mc;
            Minecraft var10005 = mc;
            Minecraft var10008 = mc;
            Minecraft var10009 = mc;
            var2.sendPacket(new CClickWindowPacket(Minecraft.player.container.windowId, axeSlot, Minecraft.player.inventory.currentItem, ClickType.SWAP, ItemStack.EMPTY, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
            var2 = mc.getConnection();
            var10003 = mc;
            var2.sendPacket(new CCloseWindowPacket(Minecraft.player.container.windowId));
            var10001 = mc;
            mc.playerController.attackEntity(Minecraft.player, this.target);
            var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
            var2 = mc.getConnection();
            var10003 = mc;
            var10005 = mc;
            var10008 = mc;
            var10009 = mc;
            var2.sendPacket(new CClickWindowPacket(Minecraft.player.container.windowId, axeSlot, Minecraft.player.inventory.currentItem, ClickType.SWAP, ItemStack.EMPTY, Minecraft.player.openContainer.getNextTransactionID(Minecraft.player.inventory)));
            var2 = mc.getConnection();
            var10003 = mc;
            var2.sendPacket(new CCloseWindowPacket(Minecraft.player.container.windowId));
         }

         return true;
      } else {
         return false;
      }
   }

   private LivingEntity findTargetForAttack() {
      Minecraft var10002 = mc;
      return (LivingEntity)mc.world.getEntitiesWithinAABB(ZombieEntity.class, Minecraft.player.getBoundingBox().grow(15.0)).stream().filter(this::isTarget).filter(this::isInZone).min(Comparator.comparingDouble((e) -> {
         Minecraft var10001 = mc;
         return (double)e.getDistance(Minecraft.player);
      })).orElse((Object)null);
   }

   private boolean isTarget(LivingEntity entity) {
      if (entity != null && entity.isAlive()) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player && !(entity instanceof ArmorStandEntity)) {
            if (entity instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity)entity;
               if (SkyCore.getInstance().getModuleManager().getModule(AntiBot.class).isEnabled() && AntiBot.bot.contains(player)) {
                  return false;
               }
            }

            return true;
         }
      }

      return false;
   }

   private boolean isInZone(Entity entity) {
      int minX = Math.min(54, 12);
      int maxX = Math.max(54, 12);
      int minZ = Math.min(161, -62);
      int maxZ = Math.max(161, -62);
      return entity.getPosX() >= (double)minX && entity.getPosX() <= (double)maxX && entity.getPosZ() >= (double)minZ && entity.getPosZ() <= (double)maxZ;
   }

   static {
      int i;
      for(i = 103; i <= 108; ++i) {
         ALL_ANARCHIES.add("an" + i);
      }

      for(i = 202; i <= 216; ++i) {
         ALL_ANARCHIES.add("an" + i);
      }

      for(i = 301; i <= 306; ++i) {
         ALL_ANARCHIES.add("an" + i);
      }

      for(i = 501; i <= 505; ++i) {
         ALL_ANARCHIES.add("an" + i);
      }

      for(i = 601; i <= 605; ++i) {
         ALL_ANARCHIES.add("an" + i);
      }

   }

   private static enum BotState {
      INIT_CHECK,
      CONNECTING,
      CHECKING_EVENT,
      WAITING_EVENT_START,
      WARPING,
      WAITING_TELEPORT,
      GOING_TO_PORTAL,
      ENTERING_PORTAL,
      FARMING;

      // $FF: synthetic method
      private static BotState[] $values() {
         return new BotState[]{INIT_CHECK, CONNECTING, CHECKING_EVENT, WAITING_EVENT_START, WARPING, WAITING_TELEPORT, GOING_TO_PORTAL, ENTERING_PORTAL, FARMING};
      }
   }
}
