package sky.core.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.FrameRateUtil;

public class MathUtil implements Wrapper {
   private static final double[] TRIG_TABLE = new double[65536];

   public static double interpolate(double start, double end, double factor) {
      return start + (end - start) * factor;
   }

   public static float interpolate(float start, float end, double factor) {
      return (float)((double)start + (double)(end - start) * factor);
   }

   public static int interpolate(int start, int end, double factor) {
      return (int)((double)start + (double)(end - start) * factor);
   }

   public static float randomizeFloat(float min, float max) {
      return (float)(Math.random() * (double)(max - min)) + min;
   }

   public static float calculateGaussian(float x, float sigma) {
      double PI = Math.PI;
      double output = 1.0 / Math.sqrt(2.0 * PI * (double)(sigma * sigma));
      return (float)(output * Math.exp((double)(-(x * x)) / (2.0 * (double)(sigma * sigma))));
   }

   public static int randomInRange(int min, int max) {
      return (int)(Math.random() * (double)(max - min)) + min;
   }

   public static double cos(double radians) {
      int index = (int)(radians * 10430.378350470453 + 16384.0) & '\uffff';
      return TRIG_TABLE[index];
   }

   public static Vector3d interpolate(Vector3d end, Vector3d start, float multiple) {
      return new Vector3d(interpolate(end.getX(), start.getX(), (double)multiple), interpolate(end.getY(), start.getY(), (double)multiple), interpolate(end.getZ(), start.getZ(), (double)multiple));
   }

   public static double getBps(Entity entity, int decimal) {
      double x = entity.getPosX() - entity.prevPosX;
      double y = entity.getPosY() - entity.prevPosY;
      double z = entity.getPosZ() - entity.prevPosZ;
      double speed = Math.sqrt(x * x + y * y + z * z) * 20.0;
      return roundHalfUp(speed, (double)decimal);
   }

   public static double sin(double radians) {
      int index = (int)(radians * 10430.378350470453) & '\uffff';
      return TRIG_TABLE[index];
   }

   public static double roundHalfUp(double num, double increment) {
      double v = (double)Math.round(num / increment) * increment;
      BigDecimal bd = new BigDecimal(v);
      bd = bd.setScale(2, RoundingMode.HALF_UP);
      return bd.doubleValue();
   }

   public static double getDistance(BlockPos pos1, BlockPos pos2) {
      double deltaX = MathHelper.calcDiff((double)pos1.getX(), (double)pos2.getX());
      double deltaY = MathHelper.calcDiff((double)pos1.getY(), (double)pos2.getY());
      double deltaZ = MathHelper.calcDiff((double)pos1.getZ(), (double)pos2.getZ());
      return (double)MathHelper.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
   }

   public static float calculateDelta(float a, float b) {
      return a - b;
   }

   public static Vector2f getRotationToVector(Vector3d eyePos, Vector3d targetPoint) {
      Vector3d diff = targetPoint.subtract(eyePos);
      double distance = Math.sqrt(diff.x * diff.x + diff.z * diff.z);
      float yaw = (float)Math.toDegrees(Math.atan2(diff.z, diff.x)) - 90.0F;
      float pitch = (float)(-Math.toDegrees(Math.atan2(diff.y, distance)));
      return new Vector2f(yaw, pitch);
   }

   public static float interpolate(float current, float old, float scale) {
      return old + (current - old) * scale;
   }

   public static float lerp(float end, float start, float multiple) {
      return (float)((double)end + (double)(start - end) * MathHelper.clamp(deltaTime() * (double)multiple, 0.0, 1.0));
   }

   public static double lerp(double end, double start, double multiple) {
      return end + (start - end) * MathHelper.clamp(deltaTime() * multiple, 0.0, 1.0);
   }

   public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height) {
      return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
   }

   public static Vector2f rotationToEntity(Entity target) {
      Vector3d var10000 = target.getPositionVec();
      Minecraft.getInstance();
      Vector3d vector3d = var10000.subtract(Minecraft.player.getPositionVec());
      double magnitude = Math.hypot(vector3d.x, vector3d.z);
      return new Vector2f((float)Math.toDegrees(Math.atan2(vector3d.z, vector3d.x)) - 90.0F, (float)(-Math.toDegrees(Math.atan2(vector3d.y, magnitude))));
   }

   public static double round(double num, double increment) {
      double v = (double)Math.round(num / increment) * increment;
      BigDecimal bd = new BigDecimal(v);
      bd = bd.setScale(2, RoundingMode.HALF_UP);
      return bd.doubleValue();
   }

   public static float randomtest(float min, float max) {
      return (new CustRandom()).randomNumber(0.0F, 1.0F, false) * (max - min) + min;
   }

   public static float round(float num, float increment) {
      float v = (float)Math.round(num / increment) * increment;
      BigDecimal bd = BigDecimal.valueOf((double)v);
      bd = bd.setScale(2, RoundingMode.HALF_UP);
      return bd.floatValue();
   }

   public static float fast(float end, float start, float multiple) {
      return (1.0F - MathHelper.clamp((float)(deltaTime() * (double)multiple), 0.0F, 1.0F)) * end + MathHelper.clamp((float)(deltaTime() * (double)multiple), 0.0F, 1.0F) * start;
   }

   public static Vector3d fast(Vector3d end, Vector3d start, float multiple) {
      return new Vector3d((double)fast((float)end.getX(), (float)start.getX(), multiple), (double)fast((float)end.getY(), (float)start.getY(), multiple), (double)fast((float)end.getZ(), (float)start.getZ(), multiple));
   }

   public static double step(double value, double steps) {
      double roundedValue = (double)Math.round(value / steps) * steps;
      return (double)Math.round(roundedValue * 100.0) / 100.0;
   }

   public static float random(float min, float max) {
      return (float)(Math.random() * (double)(max - min) + (double)min);
   }

   public static double deltaTime() {
      return FrameRateUtil.getFps() > 5 ? (double)(1.0F / (float)FrameRateUtil.getFps()) : 0.01600000075995922;
   }

   public static Vector3d interpolate(Entity entity, float partialTicks) {
      double posX = Vector3d.lerp(entity.lastTickPosX, entity.getPosX(), (double)partialTicks);
      double posY = Vector3d.lerp(entity.lastTickPosY, entity.getPosY(), (double)partialTicks);
      double posZ = Vector3d.lerp(entity.lastTickPosZ, entity.getPosZ(), (double)partialTicks);
      return new Vector3d(posX, posY, posZ);
   }
}
