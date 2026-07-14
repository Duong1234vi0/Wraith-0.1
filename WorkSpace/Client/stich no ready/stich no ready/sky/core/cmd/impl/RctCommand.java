package sky.core.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import sky.core.cmd.Command;
import sky.core.handlers.impl.HolyWorldJoinHandler;
import sky.core.handlers.impl.ReallyWorldJoinHandler;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.ServerUtil;

public class RctCommand extends Command implements Wrapper {
   public RctCommand() {
      super("rct");
   }

   public void run(LiteralArgumentBuilder builder) {
      builder.executes((ctx) -> {
         if (ServerUtil.isConnectedToServer("reallyworld")) {
            if (ReallyWorldJoinHandler.getGrief() == -1) {
               ChatUtil.addText((new StringTextComponent("Гриф не найден!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            } else {
               ReallyWorldJoinHandler.clearManualGrief();
               ReallyWorldJoinHandler.startRejoin();
            }
         }

         if (ServerUtil.isConnectedToServer("holyworld")) {
            if (HolyWorldJoinHandler.getGrief() == -1) {
               ChatUtil.addText((new StringTextComponent("Лайт не найден!")).setStyle(Style.EMPTY.applyFormatting(TextFormatting.GRAY)));
            } else if (!ServerUtil.isPvP()) {
               HolyWorldJoinHandler.startRejoin();
            } else {
               ChatUtil.addText("Нельзя использовать .rct в режиме PVP!");
            }
         }

         return 1;
      });
   }
}
