package unique.core.utility.world;

import java.util.Objects;
import net.minecraft.class_1267;
import net.minecraft.class_1280;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_1927.class_4179;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.apache.commons.lang3.mutable.MutableInt;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IExplosion;
import unique.core.utility.math.PredictUtility;

public final class ExplosionUtility {
   public static boolean terrainIgnore = false;
   public static class_1927 explosion;

   public static float getAutoCrystalDamage(class_243 crystalPos, class_1657 target, int predictTicks, boolean optimized) {
      return predictTicks == 0
         ? getExplosionDamage(crystalPos, target, optimized)
         : getExplosionDamageWPredict(crystalPos, target, PredictUtility.predictBox(target, predictTicks), optimized);
   }

   public static float getSelfExplosionDamage(class_243 explosionPos, int predictTicks, boolean optimized) {
      return getAutoCrystalDamage(explosionPos, Module.mc.field_1724, predictTicks, optimized);
   }

   public static float getExplosionDamage(class_243 explosionPos, class_1657 target, boolean optimized) {
      if (Module.mc.field_1687.method_8407() != class_1267.field_5801 && target != null) {
         if (explosion == null) {
            explosion = new class_1927(Module.mc.field_1687, Module.mc.field_1724, 1.0, 33.0, 7.0, 6.0F, false, class_4179.field_18687);
         }

         ((IExplosion)explosion).setX(explosionPos.field_1352);
         ((IExplosion)explosion).setY(explosionPos.field_1351);
         ((IExplosion)explosion).setZ(explosionPos.field_1350);
         if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) {
            ((IExplosion)explosion).setWorld(Module.mc.field_1687);
         }

         if (!new class_238(
               class_3532.method_15357(explosionPos.field_1352 - 11.0),
               class_3532.method_15357(explosionPos.field_1351 - 11.0),
               class_3532.method_15357(explosionPos.field_1350 - 11.0),
               class_3532.method_15357(explosionPos.field_1352 + 13.0),
               class_3532.method_15357(explosionPos.field_1351 + 13.0),
               class_3532.method_15357(explosionPos.field_1350 + 13.0)
            )
            .method_994(target.method_5829())) {
            return 0.0F;
         } else {
            if (!target.method_5659(explosion) && !target.method_5655()) {
               double distExposure = (float)target.method_5707(explosionPos) / 144.0;
               if (distExposure <= 1.0) {
                  terrainIgnore = ModuleManager.autoCrystal.ignoreTerrain.getValue();
                  double exposure = getExposure(explosionPos, target.method_5829(), optimized);
                  terrainIgnore = false;
                  double finalExposure = (1.0 - distExposure) * exposure;
                  float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0 * 7.0 * 12.0 + 1.0);
                  if (Module.mc.field_1687.method_8407() == class_1267.field_5805) {
                     toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage);
                  } else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) {
                     toDamage = toDamage * 3.0F / 2.0F;
                  }

                  toDamage = class_1280.method_5496(
                     target,
                     toDamage,
                     ((IExplosion)explosion).getDamageSource(),
                     target.method_6096(),
                     (float)target.method_5996(class_5134.field_23725).method_6194()
                  );
                  if (target.method_6059(class_1294.field_5907)) {
                     int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
                     float resistance_1 = toDamage * resistance;
                     toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
                  }

                  if (toDamage <= 0.0F) {
                     toDamage = 0.0F;
                  } else {
                     float protAmount = ModuleManager.autoCrystal.assumeBestArmor.getValue() ? 32.0F : getProtectionAmount(target.method_5661());
                     if (protAmount > 0.0F) {
                        toDamage = class_1280.method_5497(toDamage, protAmount);
                     }
                  }

                  return toDamage;
               }
            }

            return 0.0F;
         }
      } else {
         return 0.0F;
      }
   }

   public static float getExplosionDamageWPredict(class_243 explosionPos, class_1657 target, class_238 predict, boolean optimized) {
      if (Module.mc.field_1687.method_8407() == class_1267.field_5801) {
         return 0.0F;
      } else if (target != null && predict != null) {
         if (explosion == null) {
            explosion = new class_1927(Module.mc.field_1687, Module.mc.field_1724, 1.0, 33.0, 7.0, 6.0F, false, class_4179.field_18687);
         }

         ((IExplosion)explosion).setX(explosionPos.field_1352);
         ((IExplosion)explosion).setY(explosionPos.field_1351);
         ((IExplosion)explosion).setZ(explosionPos.field_1350);
         if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) {
            ((IExplosion)explosion).setWorld(Module.mc.field_1687);
         }

         if (!new class_238(
               class_3532.method_15357(explosionPos.field_1352 - 11.0),
               class_3532.method_15357(explosionPos.field_1351 - 11.0),
               class_3532.method_15357(explosionPos.field_1350 - 11.0),
               class_3532.method_15357(explosionPos.field_1352 + 13.0),
               class_3532.method_15357(explosionPos.field_1351 + 13.0),
               class_3532.method_15357(explosionPos.field_1350 + 13.0)
            )
            .method_994(predict)) {
            return 0.0F;
         } else {
            if (!target.method_5659(explosion) && !target.method_5655()) {
               double distExposure = predict.method_1005().method_1031(0.0, -0.9, 0.0).method_1025(explosionPos) / 144.0;
               if (distExposure <= 1.0) {
                  terrainIgnore = ModuleManager.autoCrystal.ignoreTerrain.getValue();
                  double exposure = getExposure(explosionPos, predict, optimized);
                  terrainIgnore = false;
                  double finalExposure = (1.0 - distExposure) * exposure;
                  float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0 * 7.0 * 12.0 + 1.0);
                  if (Module.mc.field_1687.method_8407() == class_1267.field_5805) {
                     toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage);
                  } else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) {
                     toDamage = toDamage * 3.0F / 2.0F;
                  }

                  toDamage = class_1280.method_5496(
                     target,
                     toDamage,
                     ((IExplosion)explosion).getDamageSource(),
                     target.method_6096(),
                     (float)Objects.requireNonNull(target.method_5996(class_5134.field_23725)).method_6194()
                  );
                  if (target.method_6059(class_1294.field_5907)) {
                     int resistance = 25 - (Objects.requireNonNull(target.method_6112(class_1294.field_5907)).method_5578() + 1) * 5;
                     float resistance_1 = toDamage * resistance;
                     toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
                  }

                  if (toDamage <= 0.0F) {
                     toDamage = 0.0F;
                  } else {
                     float protAmount = ModuleManager.autoCrystal.assumeBestArmor.getValue() ? 32.0F : getProtectionAmount(target.method_5661());
                     if (protAmount > 0.0F) {
                        toDamage = class_1280.method_5497(toDamage, protAmount);
                     }
                  }

                  return toDamage;
               }
            }

            return 0.0F;
         }
      } else {
         return 0.0F;
      }
   }

   public static class_3965 rayCastBlock(class_3959 context, class_2338 block) {
      return (class_3965)class_1922.method_17744(
         context.method_17750(),
         context.method_17747(),
         context,
         (raycastContext, blockPos) -> {
            class_2680 blockState;
            if (!blockPos.equals(block)) {
               blockState = class_2246.field_10124.method_9564();
            } else {
               blockState = class_2246.field_10540.method_9564();
            }

            class_243 vec3d = raycastContext.method_17750();
            class_243 vec3d2 = raycastContext.method_17747();
            class_265 voxelShape = raycastContext.method_17748(blockState, Module.mc.field_1687, blockPos);
            class_3965 blockHitResult = Module.mc.field_1687.method_17745(vec3d, vec3d2, blockPos, voxelShape, blockState);
            class_265 voxelShape2 = class_259.method_1073();
            class_3965 blockHitResult2 = voxelShape2.method_1092(vec3d, vec3d2, blockPos);
            double d = blockHitResult == null ? Double.MAX_VALUE : raycastContext.method_17750().method_1025(blockHitResult.method_17784());
            double e = blockHitResult2 == null ? Double.MAX_VALUE : raycastContext.method_17750().method_1025(blockHitResult2.method_17784());
            return d <= e ? blockHitResult : blockHitResult2;
         },
         raycastContext -> {
            class_243 vec3d = raycastContext.method_17750().method_1020(raycastContext.method_17747());
            return class_3965.method_17778(
               raycastContext.method_17747(),
               class_2350.method_10142(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350),
               class_2338.method_49638(raycastContext.method_17747())
            );
         }
      );
   }

   public static float getDamageOfGhostBlock(class_243 explosionPos, class_1657 target, class_2338 bp) {
      if (Module.mc.field_1687.method_8407() == class_1267.field_5801) {
         return 0.0F;
      } else {
         if (explosion == null) {
            explosion = new class_1927(Module.mc.field_1687, Module.mc.field_1724, 1.0, 33.0, 7.0, 6.0F, false, class_4179.field_18687);
         }

         ((IExplosion)explosion).setX(explosionPos.field_1352);
         ((IExplosion)explosion).setY(explosionPos.field_1351);
         ((IExplosion)explosion).setZ(explosionPos.field_1350);
         if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) {
            ((IExplosion)explosion).setWorld(Module.mc.field_1687);
         }

         double maxDist = 12.0;
         if (!new class_238(
               class_3532.method_15357(explosionPos.field_1352 - maxDist - 1.0),
               class_3532.method_15357(explosionPos.field_1351 - maxDist - 1.0),
               class_3532.method_15357(explosionPos.field_1350 - maxDist - 1.0),
               class_3532.method_15357(explosionPos.field_1352 + maxDist + 1.0),
               class_3532.method_15357(explosionPos.field_1351 + maxDist + 1.0),
               class_3532.method_15357(explosionPos.field_1350 + maxDist + 1.0)
            )
            .method_994(target.method_5829())) {
            return 0.0F;
         } else {
            if (!target.method_5659(explosion) && !target.method_5655()) {
               double distExposure = target.method_5707(explosionPos) / 144.0;
               if (distExposure <= 1.0) {
                  terrainIgnore = ModuleManager.autoCrystal.ignoreTerrain.getValue();
                  double exposure = getExposureGhost(explosionPos, target, bp);
                  terrainIgnore = false;
                  double finalExposure = (1.0 - distExposure) * exposure;
                  float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0 * 7.0 * maxDist + 1.0);
                  if (Module.mc.field_1687.method_8407() == class_1267.field_5805) {
                     toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage);
                  } else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) {
                     toDamage = toDamage * 3.0F / 2.0F;
                  }

                  toDamage = class_1280.method_5496(
                     target,
                     toDamage,
                     ((IExplosion)explosion).getDamageSource(),
                     target.method_6096(),
                     (float)target.method_5996(class_5134.field_23725).method_6194()
                  );
                  if (target.method_6059(class_1294.field_5907)) {
                     int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
                     float resistance_1 = toDamage * resistance;
                     toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
                  }

                  if (toDamage <= 0.0F) {
                     toDamage = 0.0F;
                  } else {
                     float protAmount = ModuleManager.autoCrystal.assumeBestArmor.getValue() ? 32.0F : getProtectionAmount(target.method_5661());
                     if (protAmount > 0.0F) {
                        toDamage = class_1280.method_5497(toDamage, protAmount);
                     }
                  }

                  return toDamage;
               }
            }

            return 0.0F;
         }
      }
   }

   private static float getExposureGhost(class_243 source, class_1297 entity, class_2338 pos) {
      class_238 box = entity.method_5829();
      double d = 1.0 / ((box.field_1320 - box.field_1323) * 2.0 + 1.0);
      double e = 1.0 / ((box.field_1325 - box.field_1322) * 2.0 + 1.0);
      double f = 1.0 / ((box.field_1324 - box.field_1321) * 2.0 + 1.0);
      double g = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
      double h = (1.0 - Math.floor(1.0 / f) * f) / 2.0;
      if (!(d < 0.0) && !(e < 0.0) && !(f < 0.0)) {
         int i = 0;
         int j = 0;

         for (double k = 0.0; k <= 1.0; k += d) {
            for (double l = 0.0; l <= 1.0; l += e) {
               for (double m = 0.0; m <= 1.0; m += f) {
                  double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
                  double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
                  double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
                  class_243 vec3d = new class_243(n + g, o, p + h);
                  if (raycastGhost(new class_3959(vec3d, source, class_3960.field_17558, class_242.field_1348, entity), pos).method_17783()
                     == class_240.field_1333) {
                     i++;
                  }

                  j++;
               }
            }
         }

         return (float)i / j;
      } else {
         return 0.0F;
      }
   }

   public static float getExposure(class_243 source, class_238 box, boolean optimized) {
      if (!optimized) {
         return getExposure(source, box);
      } else {
         int miss = 0;
         int hit = 0;

         for (int k = 0; k <= 1; k++) {
            for (int l = 0; l <= 1; l++) {
               for (int m = 0; m <= 1; m++) {
                  double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
                  double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
                  double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
                  class_243 vec3d = new class_243(n, o, p);
                  if (raycast(vec3d, source, ModuleManager.autoCrystal.ignoreTerrain.getValue()) == class_240.field_1333) {
                     miss++;
                  }

                  hit++;
               }
            }
         }

         return (float)miss / hit;
      }
   }

   public static float getExposure(class_243 source, class_238 box) {
      double d = 0.4545454446934474;
      double e = 0.21739130885479366;
      double f = 0.4545454446934474;
      int i = 0;
      int j = 0;

      for (double k = 0.0; k <= 1.0; k += d) {
         for (double l = 0.0; l <= 1.0; l += e) {
            for (double m = 0.0; m <= 1.0; m += f) {
               double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
               double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
               double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
               class_243 vec3d = new class_243(n + 0.045454555306552624, o, p + 0.045454555306552624);
               if (raycast(vec3d, source, ModuleManager.autoCrystal.ignoreTerrain.getValue()) == class_240.field_1333) {
                  i++;
               }

               j++;
            }
         }
      }

      return (float)i / j;
   }

   private static class_3965 raycastGhost(class_3959 context, class_2338 bPos) {
      return (class_3965)class_1922.method_17744(
         context.method_17750(),
         context.method_17747(),
         context,
         (innerContext, pos) -> {
            class_243 vec3d = innerContext.method_17750();
            class_243 vec3d2 = innerContext.method_17747();
            class_2680 blockState;
            if (!pos.equals(bPos)) {
               blockState = Module.mc.field_1687.method_8320(bPos);
            } else {
               blockState = class_2246.field_10540.method_9564();
            }

            class_265 voxelShape = innerContext.method_17748(blockState, Module.mc.field_1687, pos);
            class_3965 blockHitResult = Module.mc.field_1687.method_17745(vec3d, vec3d2, pos, voxelShape, blockState);
            class_3965 blockHitResult2 = class_259.method_1073().method_1092(vec3d, vec3d2, pos);
            double d = blockHitResult == null ? Double.MAX_VALUE : innerContext.method_17750().method_1025(blockHitResult.method_17784());
            double e = blockHitResult2 == null ? Double.MAX_VALUE : innerContext.method_17750().method_1025(blockHitResult2.method_17784());
            return d <= e ? blockHitResult : blockHitResult2;
         },
         innerContext -> {
            class_243 vec3d = innerContext.method_17750().method_1020(innerContext.method_17747());
            return class_3965.method_17778(
               innerContext.method_17747(),
               class_2350.method_10142(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350),
               class_2338.method_49638(innerContext.method_17747())
            );
         }
      );
   }

   public static class_240 raycast(class_243 start, class_243 end, boolean ignoreTerrain) {
      return (class_240)class_1922.method_17744(start, end, null, (innerContext, blockPos) -> {
         class_2680 blockState = Module.mc.field_1687.method_8320(blockPos);
         if (blockState.method_26204().method_9520() < 600.0F && ignoreTerrain) {
            return null;
         } else {
            class_3965 hitResult = blockState.method_26220(Module.mc.field_1687, blockPos).method_1092(start, end, blockPos);
            return hitResult == null ? null : hitResult.method_17783();
         }
      }, innerContext -> class_240.field_1333);
   }

   public static int getProtectionAmount(Iterable<class_1799> equipment) {
      MutableInt mutableInt = new MutableInt();
      equipment.forEach(i -> mutableInt.add(getProtectionAmount(i)));
      return mutableInt.intValue();
   }

   public static int getProtectionAmount(class_1799 stack) {
      int modifierBlast = class_1890.method_8225(
         (class_6880)Module.mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9107).get(), stack
      );
      int modifier = class_1890.method_8225(
         (class_6880)Module.mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9111).get(), stack
      );
      return modifierBlast * 2 + modifier;
   }
}
