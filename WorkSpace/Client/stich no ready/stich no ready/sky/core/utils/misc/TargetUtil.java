package sky.core.utils.misc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.impl.combat.AntiBot;
import sky.core.utils.Wrapper;

public class TargetUtil {
   public static boolean isPlayerTarget(Entity entity, MultiBooleanSetting settings, boolean considerNaked) {
      if (entity instanceof PlayerEntity player) {
         Minecraft var10001 = Wrapper.mc;
         if (entity != Minecraft.player) {
            boolean isFriend = SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName());
            boolean isNaked = player.getTotalArmorValue() == 0;
            if (isFriend) {
               return settings.is("Друзей");
            }

            if (considerNaked && isNaked) {
               return settings.is("Голых");
            }

            return settings.is("Игроков");
         }
      }

      return false;
   }

   public static boolean isPlayerTarget(Entity entity, MultiBooleanSetting settings) {
      return isPlayerTarget(entity, settings, true);
   }

   public static boolean isVillagerTarget(LivingEntity entity, MultiBooleanSetting settings) {
      return entity instanceof VillagerEntity && settings.is("Жителей");
   }

   public LivingEntity sortEntitiesByFov(LivingEntity target, float distance, float preDistance, MultiBooleanSetting targets) {
      List validTargets = this.validEntities(distance, preDistance, targets);
      LivingEntity selectedTarget = (LivingEntity)validTargets.stream().filter((e) -> {
         Minecraft var10000 = Wrapper.mc;
         return Minecraft.player.canEntityBeSeen(e) || !(Boolean)SkyCore.getInstance().getModuleManager().getAttackAura().getThroughWalls().get();
      }).min(Comparator.comparing((e) -> {
         return e != target;
      }).thenComparingDouble(this::getAimDelta).thenComparingDouble((e) -> {
         Minecraft var10001 = Wrapper.mc;
         return e.getDistanceSq(Minecraft.player);
      }).thenComparingInt(Entity::getEntityId)).orElse((Object)null);
      return selectedTarget;
   }

   private float getCameraYaw() {
      return MathHelper.wrapDegrees(Wrapper.mc.gameRenderer.getActiveRenderInfo().getYaw() + (float)(Wrapper.mc.gameRenderer.getActiveRenderInfo().isThirdPersonReverse() ? 180 : 0));
   }

   private double getAimDelta(LivingEntity entity) {
      Vector2f rotation = this.rotationEntityAngles(entity);
      float yaw = this.getCameraYaw();
      float pitch = this.getCameraPitch();
      double yawDelta = (double)MathHelper.wrapDegrees(rotation.x - yaw);
      double pitchDelta = (double)MathHelper.wrapDegrees(rotation.y - pitch);
      return Math.hypot(yawDelta, pitchDelta);
   }

   public Vector2f rotationEntityAngles(Entity entity) {
      Minecraft var10000 = Wrapper.mc;
      Vector3d playerEyes = Minecraft.player.getEyePosition(1.0F);
      Vector3d entityPos = entity.getPositionVec().add(0.0, (double)entity.getHeight() / 2.0, 0.0);
      Vector3d direction = entityPos.subtract(playerEyes).normalize();
      float yaw = (float)Math.toDegrees(Math.atan2(-direction.x, direction.z));
      float pitch = (float)Math.toDegrees(Math.asin(direction.y));
      return new Vector2f(yaw, -pitch);
   }

   private float getCameraPitch() {
      return (float)(Wrapper.mc.gameRenderer.getActiveRenderInfo().isThirdPersonReverse() ? -1 : 1) * Wrapper.mc.gameRenderer.getActiveRenderInfo().getPitch();
   }

   private List validEntities(float distance, float preDistance, MultiBooleanSetting targets) {
      List validTargets = new ArrayList();
      Iterator var5 = Wrapper.mc.world.getAllEntities().iterator();

      while(true) {
         Entity entity;
         boolean player;
         boolean mobs;
         boolean naked;
         boolean friends;
         boolean creative;
         LivingEntity living;
         do {
            do {
               if (!var5.hasNext()) {
                  return validTargets;
               }

               entity = (Entity)var5.next();
               player = targets.is("Players");
               mobs = targets.is("Mobs");
               naked = targets.is("Naked");
               friends = targets.is("Friends");
               creative = targets.is("Creative");
            } while(!(entity instanceof LivingEntity));

            living = (LivingEntity)entity;
         } while(!((double)living.getHealth() > 0.0));

         if (entity instanceof PlayerEntity playerEntity) {
            if (entity != Wrapper.mc.world.getEntityByID(1337)) {
               boolean isFriend = !SkyCore.getInstance().getFriendManager().isFriend(playerEntity.getName().getString()) || friends;
               boolean isNaked = playerEntity.getTotalArmorValue() > 0 || naked;
               boolean isCreative = !playerEntity.isCreative() || creative;
               if (this.isValidEntities(playerEntity, distance, preDistance) && isFriend && isNaked && isCreative && player) {
                  validTargets.add(playerEntity);
               }
            }
         }

         if (entity instanceof PlayerEntity playerEntity) {
            if (friends && SkyCore.getInstance().getFriendManager().isFriend(playerEntity.getName().getString()) && this.isValidEntities(playerEntity, distance, preDistance)) {
               validTargets.add(playerEntity);
            }
         }

         if (entity instanceof MobEntity mobEntity) {
            if (mobs && this.isValidEntities(mobEntity, distance, preDistance)) {
               validTargets.add(mobEntity);
            }
         }
      }
   }

   private boolean isValidEntities(LivingEntity entity, float distance, float preDistance) {
      if (AntiBot.bot.contains(entity)) {
         return false;
      } else {
         Minecraft var10001 = Wrapper.mc;
         if (entity == Minecraft.player) {
            return false;
         } else {
            Minecraft var10000 = Wrapper.mc;
            return Minecraft.player.getDistance(entity) <= distance + preDistance;
         }
      }
   }

   public static boolean isAnimalTarget(LivingEntity entity, MultiBooleanSetting settings) {
      return entity instanceof AnimalEntity && settings.is("Животных");
   }

   public static boolean isMobTarget(LivingEntity entity, MultiBooleanSetting settings) {
      return entity instanceof MobEntity && settings.is("Мобов");
   }

   public static boolean isMonsterTarget(LivingEntity entity, MultiBooleanSetting settings) {
      return entity instanceof MobEntity && !(entity instanceof AnimalEntity) && !(entity instanceof VillagerEntity) && settings.is("Монстров");
   }

   public static boolean isSelfTarget(Entity entity, MultiBooleanSetting settings) {
      Minecraft var10001 = Wrapper.mc;
      return entity == Minecraft.player && settings.is("Себя") && !Wrapper.mc.gameSettings.getPointOfView().firstPerson();
   }

   public static boolean isItemTarget(Entity entity, MultiBooleanSetting settings) {
      return entity instanceof ItemEntity && settings.is("Предметы");
   }

   public static boolean isEntityTarget(Entity entity, MultiBooleanSetting settings) {
      if (!(entity instanceof LivingEntity livingEntity)) {
         return isItemTarget(entity, settings);
      } else {
         return isSelfTarget(entity, settings) || isPlayerTarget(entity, settings, true) || isVillagerTarget(livingEntity, settings) || isAnimalTarget(livingEntity, settings) || isMonsterTarget(livingEntity, settings);
      }
   }
}
