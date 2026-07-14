package sky.core.utils.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import sky.core.utils.Wrapper;

public final class PlayerUtil implements Wrapper {
   public static final Pattern NAME_REGEX = Pattern.compile("^[A-zА-я0-9_]{3,16}$");

   public static boolean isInvalidName(String name) {
      return !NAME_REGEX.matcher(name).matches();
   }

   public static boolean nullCheck() {
      Minecraft var10000 = mc;
      return Minecraft.player == null || mc.world == null;
   }

   public static int getAnarchy() {
      String SCOREBOARD_NAME = "TAB-Scoreboard";
      String ANARCHY_PREFIX = "анархия";
      if (mc.world == null) {
         return -1;
      } else {
         ScoreObjective objective = mc.world.getScoreboard().getObjective("TAB-Scoreboard");
         if (objective == null) {
            return -1;
         } else {
            String displayName = objective.getDisplayName().getString().toLowerCase();
            int prefixIndex = displayName.indexOf("анархия");
            if (prefixIndex == -1) {
               return -1;
            } else {
               try {
                  String numberStr = displayName.substring(prefixIndex + "анархия".length()).replaceAll("\\D", "");
                  if (!numberStr.isEmpty()) {
                     return Integer.parseInt(numberStr);
                  }
               } catch (NumberFormatException var6) {
               }

               return -1;
            }
         }
      }
   }

   public static boolean collideWith(LivingEntity entity, float grow) {
      Minecraft var10000 = mc;
      AxisAlignedBB box = Minecraft.player.getBoundingBox();
      AxisAlignedBB targetbox = entity.getBoundingBox().grow((double)grow, 0.0, (double)grow);
      return box.maxX > targetbox.minX && box.maxY > targetbox.minY && box.maxZ > targetbox.minZ && box.minX < targetbox.maxX && box.minY < targetbox.maxY && box.minZ < targetbox.maxZ;
   }

   public static boolean collideWith(LivingEntity entity) {
      return collideWith(entity, 0.0F);
   }

   public static String getServerIp() {
      return mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP != null ? mc.getCurrentServerData().serverIP : (mc.world != null ? "local" : "none");
   }

   public static int getPing(AbstractClientPlayerEntity player) {
      return mc.getConnection() != null && mc.getConnection().getPlayerInfo(player.getUniqueID()) != null ? mc.getConnection().getPlayerInfo(player.getUniqueID()).getResponseTime() : -1;
   }

   public static double getEntityArmor(PlayerEntity entity) {
      double totalArmor = 0.0;
      Iterator var3 = entity.inventory.armorInventory.iterator();

      while(var3.hasNext()) {
         ItemStack armorStack = (ItemStack)var3.next();
         if (armorStack != null && armorStack.getItem() instanceof ArmorItem) {
            totalArmor += getProtectionLvl(armorStack);
         }
      }

      return totalArmor;
   }

   public static double getProtectionLvl(ItemStack stack) {
      double damageReduce = 0.0;
      Item var4 = stack.getItem();
      if (var4 instanceof ArmorItem armor) {
         damageReduce = (double)armor.getDamageReduceAmount();
         if (stack.isEnchanted()) {
            damageReduce += (double)EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack) * 0.25;
         }
      }

      return damageReduce;
   }

   public static Block blockRelativeToPlayer(double offsetX, double offsetY, double offsetZ) {
      Minecraft var10001 = mc;
      return mc.world.getBlockState(Minecraft.player.getPosition().add(offsetX, offsetY, offsetZ)).getBlock();
   }

   public static Block block(BlockPos pos) {
      return mc.world.getBlockState(pos).getBlock();
   }

   public static Block block(double x, double y, double z) {
      return mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
   }

   public static Block getBlock() {
      return getBlock(0.0, 0.0, 0.0);
   }

   public static Block getBlock(double x, double y, double z) {
      Minecraft var10001 = mc;
      return mc.world.getBlockState(Minecraft.player.getPosition().add(x, y, z)).getBlock();
   }

   public static BlockPos getBlock(float distance, Block block) {
      return (BlockPos)getSphere(getPlayerPosLocal(), distance, 6.0F, false, true, 0, false).stream().filter((position) -> {
         return mc.world.getBlockState(position).getBlock() == block;
      }).min(Comparator.comparing((blockPos) -> {
         Minecraft var10000 = mc;
         return getDistanceOfEntityToBlock(Minecraft.player, blockPos);
      })).orElse((Object)null);
   }

   public static BlockPos getBlock(BlockPos centerPos, float distance, Block block) {
      return (BlockPos)getCube(centerPos, distance, distance).stream().filter((pos) -> {
         return mc.world.getBlockState(pos).equals(block);
      }).findFirst().orElse((Object)null);
   }

   public static List getSphere(BlockPos center, float radius, float height, boolean hollow, boolean fromBottom, int yOffset, boolean cube) {
      List positions = new ArrayList();
      int centerX = center.getX();
      int centerY = center.getY();
      int centerZ = center.getZ();

      for(int x = centerX - (int)radius; (float)x <= (float)centerX + radius; ++x) {
         for(int z = centerZ - (int)radius; (float)z <= (float)centerZ + radius; ++z) {
            int yStart = fromBottom ? centerY - (int)radius : centerY;
            int yEnd = fromBottom ? centerY + (int)radius : centerY + (int)height;

            for(int y = yStart; y < yEnd; ++y) {
               if (isPositionWithinSphere(centerX, centerY, centerZ, x, y, z, radius, hollow) || cube) {
                  positions.add(new BlockPos(x, y + yOffset, z));
               }
            }
         }
      }

      return positions;
   }

   public static List getCube(BlockPos center, float radiusXZ, float radiusY) {
      List positions = new ArrayList();
      int centerX = center.getX();
      int centerY = center.getY();
      int centerZ = center.getZ();

      for(int x = centerX - (int)radiusXZ; (float)x <= (float)centerX + radiusXZ; ++x) {
         for(int z = centerZ - (int)radiusXZ; (float)z <= (float)centerZ + radiusXZ; ++z) {
            for(int y = centerY - (int)radiusY; (float)y < (float)centerY + radiusY; ++y) {
               positions.add(new BlockPos(x, y, z));
            }
         }
      }

      return positions;
   }

   public static BlockPos getPlayerPosLocal() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return BlockPos.ZERO;
      } else {
         Minecraft var10002 = mc;
         double var0 = Math.floor(Minecraft.player.getPosX());
         Minecraft var10003 = mc;
         double var1 = Math.floor(Minecraft.player.getPosY());
         Minecraft var10004 = mc;
         return new BlockPos(var0, var1, Math.floor(Minecraft.player.getPosZ()));
      }
   }

   public static double getDistanceOfEntityToBlock(Entity entity, BlockPos blockPos) {
      return MathHelper.getDistance(entity.getPosX(), entity.getPosY(), entity.getPosZ(), (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
   }

   public static boolean isPositionWithinSphere(int centerX, int centerY, int centerZ, int x, int y, int z, float radius, boolean hollow) {
      double distanceSq = Math.pow((double)(centerX - x), 2.0) + Math.pow((double)(centerZ - z), 2.0) + Math.pow((double)(centerY - y), 2.0);
      return distanceSq < Math.pow((double)radius, 2.0) && (!hollow || distanceSq >= Math.pow((double)(radius - 1.0F), 2.0));
   }

   public static boolean isBlockUnder(double height) {
      for(int offset = 0; (double)offset < height; ++offset) {
         if (blockRelativeToPlayer(0.0, (double)(-offset), 0.0).getDefaultState().isCollisionShapeLargerThanFullBlock()) {
            return true;
         }
      }

      return false;
   }

   public static boolean isBlockSolid(BlockPos pos) {
      return block(pos).getDefaultState().getMaterial().isSolid();
   }

   public static boolean isBlockSolid(double x, double y, double z) {
      return block(new BlockPos(x, y, z)).getDefaultState().getMaterial().isSolid();
   }

   public static boolean isPlayerInWeb() {
      Minecraft var10000 = mc;
      boolean var0;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.isInWeb) {
            var0 = true;
            return var0;
         }
      }

      var0 = false;
      return var0;
   }

   public static boolean isPlayerInSoulSand() {
      Minecraft var10000 = mc;
      AxisAlignedBB playerBox = Minecraft.player.getBoundingBox();
      var10000 = mc;
      BlockPos playerPosition = Minecraft.player.getPosition();
      return getNearbyBlockPositions(playerPosition).stream().anyMatch((pos) -> {
         return isBlockSoulSand(playerBox, pos);
      });
   }

   private static List getNearbyBlockPositions(BlockPos center) {
      List positions = new ArrayList();

      for(int x = center.getX() - 2; x <= center.getX() + 2; ++x) {
         for(int y = center.getY() - 1; y <= center.getY() + 4; ++y) {
            for(int z = center.getZ() - 2; z <= center.getZ() + 2; ++z) {
               positions.add(new BlockPos(x, y, z));
            }
         }
      }

      return positions;
   }

   private static boolean isBlockCobweb(AxisAlignedBB playerBox, BlockPos blockPos) {
      return playerBox.intersects(new AxisAlignedBB(blockPos)) && mc.world.getBlockState(blockPos).getBlock() == Blocks.COBWEB;
   }

   private static boolean isBlockSoulSand(AxisAlignedBB playerBox, BlockPos blockPos) {
      return playerBox.intersects(new AxisAlignedBB(blockPos)) && mc.world.getBlockState(blockPos).getBlock() == Blocks.SOUL_SAND;
   }

   public static boolean isBlockAboveHead() {
      if (nullCheck()) {
         return false;
      } else {
         Minecraft var10000 = mc;
         float width = Minecraft.player.getWidth() / 2.0F;
         Minecraft var10002 = mc;
         double var4 = Minecraft.player.getPosX() - (double)width;
         Minecraft var10003 = mc;
         double var5 = Minecraft.player.getPosY();
         Minecraft var10004 = mc;
         var5 += (double)Minecraft.player.getEyeHeight();
         var10004 = mc;
         double var6 = Minecraft.player.getPosZ() + (double)width;
         Minecraft var10005 = mc;
         double var2 = Minecraft.player.getPosX() + (double)width;
         Minecraft var10006 = mc;
         double var3 = Minecraft.player.getPosY() + 2.5;
         Minecraft var10007 = mc;
         AxisAlignedBB axisAlignedBB = new AxisAlignedBB(var4, var5, var6, var2, var3, Minecraft.player.getPosZ() - (double)width);
         Minecraft var10001 = mc;
         return !mc.world.getCollisionShapes(Minecraft.player, axisAlignedBB).findAny().isEmpty();
      }
   }

   private PlayerUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
