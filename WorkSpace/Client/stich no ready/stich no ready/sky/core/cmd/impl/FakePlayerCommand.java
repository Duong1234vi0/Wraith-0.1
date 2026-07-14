package sky.core.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import sky.core.cmd.Command;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;

public class FakePlayerCommand extends Command implements Wrapper {
   private static final int FAKE_PLAYER_ID = -1337;

   public FakePlayerCommand() {
      super("fp", "fakeplayer");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((context) -> {
         ChatUtil.addText((new StringTextComponent("Ошибка в использовании")).mergeStyle(TextFormatting.GRAY));
         ChatUtil.addText((new StringTextComponent(".fp add ")).mergeStyle(TextFormatting.WHITE).append((new StringTextComponent("- добавить бота")).mergeStyle(TextFormatting.GRAY)));
         ChatUtil.addText((new StringTextComponent(".fp remove ")).mergeStyle(TextFormatting.WHITE).append((new StringTextComponent("- удалить бота")).mergeStyle(TextFormatting.GRAY)));
         return 1;
      });
      builder.then(literal("add").executes((context) -> {
         if (mc.world != null) {
            Minecraft var10000 = mc;
            if (Minecraft.player != null) {
               try {
                  Minecraft var10003 = mc;
                  RemoteClientPlayerEntity fakePlayer = new RemoteClientPlayerEntity(mc.world, Minecraft.player.getGameProfile());
                  Minecraft var10001 = mc;
                  fakePlayer.inventory = Minecraft.player.inventory;
                  var10001 = mc;
                  fakePlayer.copyLocationAndAnglesFrom(Minecraft.player);
                  var10001 = mc;
                  fakePlayer.rotationYawHead = Minecraft.player.rotationYawHead;
                  mc.world.addEntity(-1337, fakePlayer);
                  ChatUtil.addText((new StringTextComponent("Бот успешно добавлен.")).mergeStyle(TextFormatting.GREEN));
               } catch (Exception var2) {
                  ChatUtil.addText((new StringTextComponent("Произошла ошибка при создании бота.")).mergeStyle(TextFormatting.RED));
                  var2.printStackTrace();
               }

               return 1;
            }
         }

         return 0;
      }));
      builder.then(literal("remove").executes((context) -> {
         if (mc.world == null) {
            return 0;
         } else {
            try {
               mc.world.removeEntityFromWorld(-1337);
               ChatUtil.addText((new StringTextComponent("Бот успешно удален.")).mergeStyle(TextFormatting.GRAY));
            } catch (Exception var2) {
               ChatUtil.addText((new StringTextComponent("Ошибка при удалении бота.")).mergeStyle(TextFormatting.RED));
            }

            return 1;
         }
      }));
   }
}
