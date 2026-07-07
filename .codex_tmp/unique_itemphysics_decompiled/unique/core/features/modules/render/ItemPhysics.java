package unique.core.features.modules.render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.class_1087;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_777;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import unique.core.features.modules.Module;

public class ItemPhysics extends Module {
   private final Random random = new Random();
   private static final Map<UUID, Float> rotationMapY = new HashMap<>();
   private static final Map<UUID, Float> rotationMapX = new HashMap<>();
   private static final Map<UUID, Float> speedYMap = new HashMap<>();
   private static final Map<UUID, Float> speedXMap = new HashMap<>();
   private static final Map<UUID, Float> speedZMap = new HashMap<>();

   public ItemPhysics() {
      super("ItemPhysics", Module.Category.RENDER);
   }

   public void onRenderItemEntity(class_1542 entity, class_4587 matrices, class_4597 vertexConsumers, int light, float tickDelta, List<class_777> quads) {
      if (quads != null) {
         ItemPhysics.ModelInfo info = this.getInfo(quads);
         matrices.method_22903();
         this.offsetInFluidOrBlock(matrices, entity, info);
         boolean isFlat = info.flat;
         class_310 mc = class_310.method_1551();
         class_1799 stack = entity.method_6983();
         class_1087 model = mc.method_1480().method_4019(stack, mc.field_1687, null, entity.method_5628());
         ItemPhysics.RotationPair rotation = this.getRotationPair(entity, tickDelta, !isFlat);
         double cx = (entity.method_5829().field_1323 + entity.method_5829().field_1320) / 2.0 - entity.method_23317();
         double cy = (entity.method_5829().field_1322 + entity.method_5829().field_1325) / 2.0 - entity.method_23318();
         double cz = (entity.method_5829().field_1321 + entity.method_5829().field_1324) / 2.0 - entity.method_23321();
         matrices.method_22904(cx, cy, cz);
         boolean onGround = entity.method_24828();
         boolean inWater = entity.method_5799() || entity.method_5869();
         if (isFlat) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
            if (!onGround && !inWater) {
               matrices.method_22907(class_7833.field_40718.rotationDegrees(rotation.rotZ * 1.5F));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(rotation.rotY));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(rotation.rotX * 0.7F));
            }
         } else if (!onGround && !inWater) {
            matrices.method_22907(class_7833.field_40714.rotationDegrees(rotation.rotX));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(rotation.rotY));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(rotation.rotZ * 0.8F));
         }

         matrices.method_22904(-cx, -cy, -cz);
         int count = stack.method_7947();
         int rendered = count > 48 ? 5 : (count > 32 ? 4 : (count > 16 ? 3 : (count > 1 ? 2 : 1)));

         for (int i = 0; i < rendered; i++) {
            matrices.method_22903();
            this.random.setSeed(entity.method_5667().getMostSignificantBits() + i);
            if ((onGround || inWater) && i > 0) {
               matrices.method_46416((this.random.nextFloat() * 2.0F - 1.0F) * 0.015F, i * 0.015F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.015F);
            }

            mc.method_1480().method_23179(stack, class_811.field_4318, false, matrices, vertexConsumers, light, class_4608.field_21444, model);
            matrices.method_22909();
         }

         matrices.method_22909();
      }
   }

   private ItemPhysics.RotationPair getRotationPair(class_1542 entity, float tickDelta, boolean isBlock) {
      UUID id = entity.method_5667();
      boolean onGround = entity.method_24828();
      boolean inWater = entity.method_5799() || entity.method_5869();
      if (!onGround && !inWater) {
         float speedY = speedYMap.computeIfAbsent(id, uuid -> this.random.nextFloat() * 3.0F + 2.0F);
         float speedX = speedXMap.computeIfAbsent(id, uuid -> this.random.nextFloat() * 2.5F + 1.5F);
         float speedZ = speedZMap.computeIfAbsent(id, uuid -> this.random.nextFloat() * 3.5F + 1.5F);
         float prevY = rotationMapY.getOrDefault(id, 0.0F);
         float prevX = rotationMapX.getOrDefault(id, 0.0F);
         float velocityFactor = (float)Math.sqrt(entity.method_18798().method_37268()) * 5.0F + 1.0F;
         float rotY = prevY + tickDelta * speedY * velocityFactor;
         float rotX = prevX + tickDelta * speedX * velocityFactor;
         float rotZ = prevY + tickDelta * speedZ * velocityFactor;
         rotationMapY.put(id, rotY);
         rotationMapX.put(id, rotX);
         return new ItemPhysics.RotationPair(rotY, rotX, rotZ);
      } else {
         rotationMapY.put(id, 0.0F);
         rotationMapX.put(id, 0.0F);
         speedYMap.remove(id);
         speedXMap.remove(id);
         speedZMap.remove(id);
         return new ItemPhysics.RotationPair(0.0F, 0.0F, 0.0F);
      }
   }

   private void offsetInFluidOrBlock(class_4587 matrices, class_1542 entity, ItemPhysics.ModelInfo info) {
      if (entity.method_5799()) {
         if (!info.flat) {
            matrices.method_22904(0.0, 0.025, 0.0);
         }
      } else if (info.flat && !entity.method_24828()) {
         matrices.method_22904(0.0, info.offsetY, 0.0);
      }
   }

   private ItemPhysics.ModelInfo getInfo(List<class_777> quads) {
      float minX = Float.MAX_VALUE;
      float minY = Float.MAX_VALUE;
      float minZ = Float.MAX_VALUE;
      float maxX = Float.MIN_VALUE;
      float maxY = Float.MIN_VALUE;
      float maxZ = Float.MIN_VALUE;

      for (class_777 quad : quads) {
         int[] v = quad.method_3357();

         for (int i = 0; i < 4; i++) {
            float x = Float.intBitsToFloat(v[i * 8]);
            float y = Float.intBitsToFloat(v[i * 8 + 1]);
            float z = Float.intBitsToFloat(v[i * 8 + 2]);
            class_2350 d = quad.method_3358();
            switch (d) {
               case field_11033:
                  minY = Math.min(minY, y);
                  break;
               case field_11036:
                  maxY = Math.max(maxY, y);
                  break;
               case field_11043:
                  minZ = Math.min(minZ, z);
                  break;
               case field_11035:
                  maxZ = Math.max(maxZ, z);
                  break;
               case field_11039:
                  minX = Math.min(minX, x);
                  break;
               case field_11034:
                  maxX = Math.max(maxX, x);
            }
         }
      }

      if (minX == Float.MAX_VALUE) {
         minZ = 0.0F;
         minY = 0.0F;
         minX = 0.0F;
      }

      if (maxX == Float.MIN_VALUE) {
         maxZ = 1.0F;
         maxY = 1.0F;
         maxX = 1.0F;
      }

      float x = maxX - minX;
      float y = maxY - minY;
      float z = maxZ - minZ;
      boolean flat = x > 0.0625F && y > 0.0625F && z <= 0.0625F;
      float offsetY = flat ? 0.5F - minY : 0.0F;
      return new ItemPhysics.ModelInfo(flat, offsetY, minZ - minY);
   }

   private record ModelInfo(boolean flat, float offsetY, float offsetZ) {
   }

   private record RotationPair(float rotY, float rotX, float rotZ) {
   }
}
