package unique.core.utility.player;

import net.minecraft.class_1294;
import net.minecraft.class_3532;
import unique.core.core.Managers;
import unique.core.events.impl.EventMove;
import unique.core.features.modules.Module;

public final class MovementUtility {
   public static boolean isMoving() {
      return Module.mc.field_1724 != null
         && Module.mc.field_1687 != null
         && Module.mc.field_1724.field_3913 != null
         && (Module.mc.field_1724.field_3913.field_3905 != 0.0 || Module.mc.field_1724.field_3913.field_3907 != 0.0);
   }

   public static double getSpeed() {
      return Math.hypot(Module.mc.field_1724.method_18798().field_1352, Module.mc.field_1724.method_18798().field_1350);
   }

   public static double[] forward(double d) {
      float f = Module.mc.field_1724.field_3913.field_3905;
      float f2 = Module.mc.field_1724.field_3913.field_3907;
      float f3 = Module.mc.field_1724.method_36454();
      if (f != 0.0F) {
         if (f2 > 0.0F) {
            f3 += f > 0.0F ? -45 : 45;
         } else if (f2 < 0.0F) {
            f3 += f > 0.0F ? 45 : -45;
         }

         f2 = 0.0F;
         if (f > 0.0F) {
            f = 1.0F;
         } else if (f < 0.0F) {
            f = -1.0F;
         }
      }

      double d2 = Math.sin(Math.toRadians(f3 + 90.0F));
      double d3 = Math.cos(Math.toRadians(f3 + 90.0F));
      double d4 = f * d * d3 + f2 * d * d2;
      double d5 = f * d * d2 - f2 * d * d3;
      return new double[]{d4, d5};
   }

   public static void setMotion(double speed) {
      double forward = Module.mc.field_1724.field_3913.field_3905;
      double strafe = Module.mc.field_1724.field_3913.field_3907;
      float yaw = Module.mc.field_1724.method_36454();
      if (forward == 0.0 && strafe == 0.0) {
         Module.mc.field_1724.method_18800(0.0, Module.mc.field_1724.method_18798().field_1351, 0.0);
      } else {
         if (forward != 0.0) {
            if (strafe > 0.0) {
               yaw += forward > 0.0 ? -45 : 45;
            } else if (strafe < 0.0) {
               yaw += forward > 0.0 ? 45 : -45;
            }

            strafe = 0.0;
            if (forward > 0.0) {
               forward = 1.0;
            } else if (forward < 0.0) {
               forward = -1.0;
            }
         }

         double sin = class_3532.method_15374((float)Math.toRadians(yaw + 90.0F));
         double cos = class_3532.method_15362((float)Math.toRadians(yaw + 90.0F));
         Module.mc
            .field_1724
            .method_18800(
               forward * speed * cos + strafe * speed * sin, Module.mc.field_1724.method_18798().field_1351, forward * speed * sin - strafe * speed * cos
            );
      }
   }

   public static float getMoveDirection() {
      double forward = Module.mc.field_1724.field_3913.field_3905;
      double strafe = Module.mc.field_1724.field_3913.field_3907;
      if (strafe > 0.0) {
         strafe = 1.0;
      } else if (strafe < 0.0) {
         strafe = -1.0;
      }

      float yaw = Module.mc.field_1724.method_36454();
      if (forward == 0.0 && strafe == 0.0) {
         return yaw;
      } else {
         if (forward != 0.0) {
            if (strafe > 0.0) {
               yaw += forward > 0.0 ? -45.0F : -135.0F;
            } else if (strafe < 0.0) {
               yaw += forward > 0.0 ? 45.0F : 135.0F;
            } else if (forward < 0.0) {
               yaw += 180.0F;
            }
         }

         if (forward == 0.0) {
            if (strafe > 0.0) {
               yaw -= 90.0F;
            } else if (strafe < 0.0) {
               yaw += 90.0F;
            }
         }

         return yaw;
      }
   }

   public static double[] forwardWithoutStrafe(double d) {
      float f3 = Module.mc.field_1724.method_36454();
      double d4 = d * Math.cos(Math.toRadians(f3 + 90.0F));
      double d5 = d * Math.sin(Math.toRadians(f3 + 90.0F));
      return new double[]{d4, d5};
   }

   public static double getJumpSpeed() {
      double jumpSpeed = 0.39999995F;
      if (Module.mc.field_1724.method_6059(class_1294.field_5913)) {
         double amplifier = Module.mc.field_1724.method_6112(class_1294.field_5913).method_5578();
         jumpSpeed += (amplifier + 1.0) * 0.1;
      }

      return jumpSpeed;
   }

   public static void modifyEventSpeed(EventMove event, double d) {
      double d2 = Module.mc.field_1724.field_3913.field_3905;
      double d3 = Module.mc.field_1724.field_3913.field_3907;
      float f = Module.mc.field_1724.method_36454();
      if (d2 == 0.0 && d3 == 0.0) {
         event.setX(0.0);
         event.setZ(0.0);
      } else {
         if (d2 != 0.0) {
            if (d3 > 0.0) {
               f += d2 > 0.0 ? -45 : 45;
            } else if (d3 < 0.0) {
               f += d2 > 0.0 ? 45 : -45;
            }

            d3 = 0.0;
            if (d2 > 0.0) {
               d2 = 1.0;
            } else if (d2 < 0.0) {
               d2 = -1.0;
            }
         }

         double sin = Math.sin(Math.toRadians(f + 90.0F));
         double cos = Math.cos(Math.toRadians(f + 90.0F));
         event.setX(d2 * d * cos + d3 * d * sin);
         event.setZ(d2 * d * sin - d3 * d * cos);
      }
   }

   public static double getBaseMoveSpeed() {
      double d = 0.2873;
      if (Module.fullNullCheck()) {
         return d;
      } else {
         if (Module.mc.field_1724.method_6059(class_1294.field_5904)) {
            int n = Module.mc.field_1724.method_6112(class_1294.field_5904).method_5578();
            d *= 1.0 + 0.2 * (n + 1);
         }

         if (Module.mc.field_1724.method_6059(class_1294.field_5913)) {
            int n = Module.mc.field_1724.method_6112(class_1294.field_5913).method_5578();
            d /= 1.0 + 0.2 * (n + 1);
         }

         if (Module.mc.field_1724.method_6059(class_1294.field_5909)) {
            int n = Module.mc.field_1724.method_6112(class_1294.field_5909).method_5578();
            d /= 1.0 + 0.2 * (n + 1);
         }

         return d;
      }
   }

   public static boolean sprintIsLegit(float yaw) {
      return Math.abs(Math.abs(class_3532.method_15393(yaw)) - Math.abs(class_3532.method_15393(Managers.PLAYER.yaw))) < 40.0F;
   }
}
