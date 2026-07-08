/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.managers.api.draggable.DraggableRepository;
import code.alphadlc.utils.client.managers.file.FileController;
import code.alphadlc.utils.client.managers.file.impl.ElementsFile;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class DragCommand
extends Command
implements QuickImports {
    protected DragCommand() {
        super("drag");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        Logger.info("[DragCommand] \u041a\u043e\u043c\u0430\u043d\u0434\u0430 execute \u0432\u044b\u0437\u0432\u0430\u043d\u0430, label: " + label);
        Logger.info("[DragCommand] args.hasAny() = " + args.hasAny());
        try {
            if (!args.hasAny()) {
                Logger.info("[DragCommand] \u0410\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u044b \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u044e\u0442");
                ChatMessage.brandmessage("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435: .drag reset");
                return;
            }
            Logger.info("[DragCommand] \u0410\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u044b \u0435\u0441\u0442\u044c, \u043f\u043e\u043b\u0443\u0447\u0430\u044e \u043f\u0435\u0440\u0432\u0443\u044e \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u0443...");
            String subCommand = args.getString();
            Logger.info("[DragCommand] \u041f\u043e\u043b\u0443\u0447\u0435\u043d\u0430 \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u0430: '" + subCommand + "'");
            if (subCommand.equalsIgnoreCase("reset")) {
                Logger.info("[DragCommand] \u0412\u044b\u043f\u043e\u043b\u043d\u044f\u0435\u0442\u0441\u044f reset");
                this.resetAllDraggables();
                Notifications.getInstance().addList("\u041f\u043e\u0437\u0438\u0446\u0438\u0438 \u0432\u0441\u0435\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 HUD " + String.valueOf(class_124.field_1060) + "\u0441\u0431\u0440\u043e\u0448\u0435\u043d\u044b", 3000);
                Logger.info("[DragCommand] Reset \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d \u0443\u0441\u043f\u0435\u0448\u043d\u043e");
            } else {
                Logger.warn("[DragCommand] \u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u0430: " + subCommand);
                ChatMessage.brandmessage("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u0430: " + subCommand);
                ChatMessage.brandmessage("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435: .drag reset");
            }
        }
        catch (Exception e) {
            Logger.error("[DragCommand] \u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0438 \u043a\u043e\u043c\u0430\u043d\u0434\u044b: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void resetAllDraggables() {
        DraggableRepository repo = AlphaDLC.getInstance().getDraggableRepository();
        Logger.info("[DragCommand] \u041d\u0430\u0447\u0430\u043b\u043e \u0441\u0431\u0440\u043e\u0441\u0430 \u043f\u043e\u0437\u0438\u0446\u0438\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432. \u0412\u0441\u0435\u0433\u043e \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432: " + repo.draggable().size());
        int resetCount = 0;
        for (AbstractDraggable draggable : repo.draggable()) {
            String name = draggable.getName();
            int oldX = draggable.getX();
            int oldY = draggable.getY();
            int newX = oldX;
            int newY = oldY;
            Logger.info("[DragCommand] \u041e\u0431\u0440\u0430\u0431\u043e\u0442\u043a\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430: '" + name + "' (\u0442\u0435\u043a\u0443\u0449\u0430\u044f \u043f\u043e\u0437\u0438\u0446\u0438\u044f: " + oldX + ", " + oldY + ")");
            if (name.equals("Info")) {
                newX = 0;
                newY = 0;
            } else if (name.equals("Watermark")) {
                newX = 10;
                newY = 10;
            } else if (name.equals("Target Hud")) {
                newX = 10;
                newY = 80;
            } else if (name.equals("Keybinds")) {
                newX = 300;
                newY = 40;
            } else if (name.equals("Armor")) {
                newX = 10;
                newY = 120;
            } else if (name.equals("Cooldowns")) {
                newX = 10;
                newY = 40;
            } else if (name.equals("Potions")) {
                newX = 10;
                newY = 200;
            } else if (name.equals("Binds")) {
                newX = 10;
                newY = 180;
            } else if (name.equals("Notifications")) {
                newX = 0;
                newY = 350;
            } else if (name.equals("ArrayList")) {
                newX = 10;
                newY = 120;
            } else if (name.equals("Inventory")) {
                newX = 385;
                newY = 40;
            } else {
                Logger.warn("[DragCommand] \u042d\u043b\u0435\u043c\u0435\u043d\u0442 '" + name + "' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u0434\u043b\u044f \u0441\u0431\u0440\u043e\u0441\u0430");
                continue;
            }
            if (newX != oldX || newY != oldY) {
                draggable.setX(newX);
                draggable.setY(newY);
                ++resetCount;
                Logger.info("[DragCommand] \u041f\u043e\u0437\u0438\u0446\u0438\u044f \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 '" + name + "' \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0430 \u0441 (" + oldX + ", " + oldY + ") \u043d\u0430 (" + newX + ", " + newY + ")");
                continue;
            }
            Logger.info("[DragCommand] \u041f\u043e\u0437\u0438\u0446\u0438\u044f \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 '" + name + "' \u0443\u0436\u0435 \u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0430\u044f (" + newX + ", " + newY + ")");
        }
        Logger.info("[DragCommand] \u0421\u0431\u0440\u043e\u0448\u0435\u043d\u043e \u043f\u043e\u0437\u0438\u0446\u0438\u0439: " + resetCount + " \u0438\u0437 " + repo.draggable().size());
        try {
            FileController fileController = AlphaDLC.getInstance().getFileController();
            Logger.info("[DragCommand] \u0421\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0435 \u0444\u0430\u0439\u043b\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432...");
            fileController.saveFile(ElementsFile.class);
            Logger.info("[DragCommand] \u0424\u0430\u0439\u043b \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d");
        }
        catch (Exception e) {
            Logger.error("[DragCommand] \u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u0444\u0430\u0439\u043b\u0430 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny() && args.getArgs().size() == 1) {
            return Stream.of("reset");
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043f\u043e\u0437\u0438\u0446\u0438\u044f\u043c\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 HUD";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043f\u043e\u0437\u0438\u0446\u0438\u044f\u043c\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 HUD", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> drag reset - \u0421\u0431\u0440\u0430\u0441\u044b\u0432\u0430\u0435\u0442 \u043f\u043e\u0437\u0438\u0446\u0438\u0438 \u0432\u0441\u0435\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 HUD \u043d\u0430 \u0434\u0435\u0444\u043e\u043b\u0442\u043d\u044b\u0435");
    }
}
