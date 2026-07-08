/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.repository.rct.RCTRepository;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.features.impl.misc.HolyWorldAutoJoin;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class RCTCommand
extends Command
implements QuickImports {
    private final RCTRepository repository;

    protected RCTCommand(AlphaDLC main) {
        super("rct");
        this.repository = main.getRCTRepository();
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        if (!(Network.isHolyWorld() || Network.isReallyWorld() || Network.isSpookyTime() || Network.isFunTime())) {
            Notifications.getInstance().addList("[RCT] \u041d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442 \u043d\u0430 \u044d\u0442\u043e\u043c " + String.valueOf(class_124.field_1061) + "\u0441\u0435\u0440\u0432\u0435\u0440\u0435", 3000);
            return;
        }
        if (Network.isPvp()) {
            Notifications.getInstance().addList("[RCT] \u0412\u044b \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0435\u0441\u044c \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 " + String.valueOf(class_124.field_1061) + "\u043f\u0432\u043f", 3000);
            return;
        }
        if (Network.isHolyWorld()) {
            HolyWorldAutoJoin autoJoin = AlphaDLC.getInstance().getHolyWorldAutoJoin();
            if (autoJoin == null) {
                Notifications.getInstance().addList("[RCT] HolyWorldAutoJoin \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", 3000);
                return;
            }
            if (args.hasAny()) {
                args.requireMin(1);
                int anarchy = args.getArgs().getFirst().getAs(Integer.class);
                autoJoin.reset();
                autoJoin.setTargetAnarchy(anarchy);
                autoJoin.setActive(true);
            } else {
                int currentAnarchy = Network.getAnarchy();
                if (currentAnarchy > 0) {
                    if (!autoJoin.isActive()) {
                        autoJoin.setActive(true);
                    } else {
                        autoJoin.reset();
                    }
                } else {
                    Notifications.getInstance().addList("[RCT] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c \u043d\u043e\u043c\u0435\u0440 " + String.valueOf(class_124.field_1061) + "\u0430\u043d\u0430\u0440\u0445\u0438\u0438", 3000);
                }
            }
            return;
        }
        if (args.hasAny()) {
            args.requireMin(1);
            int anarchy = args.getArgs().getFirst().getAs(Integer.class);
            if (Network.isFunTime() || Network.isSpookyTime()) {
                if (RCTCommand.mc.field_1724 != null && RCTCommand.mc.field_1724.field_3944 != null) {
                    RCTCommand.mc.field_1724.field_3944.method_45729("/an" + anarchy);
                    Notifications.getInstance().addList("[RCT] \u041f\u0435\u0440\u0435\u0445\u043e\u0434 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e #" + String.valueOf(class_124.field_1078) + anarchy + String.valueOf(class_124.field_1070), 3000);
                }
            } else {
                this.repository.reconnect(anarchy);
            }
        } else {
            int currentAnarchy = Network.getAnarchy();
            if (currentAnarchy > 0) {
                this.repository.reconnect(currentAnarchy);
            } else {
                Notifications.getInstance().addList("[RCT] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c \u043d\u043e\u043c\u0435\u0440 " + String.valueOf(class_124.field_1061) + "\u0430\u043d\u0430\u0440\u0445\u0438\u0438", 3000);
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u043f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434\u0438\u0442 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434\u0438\u0442 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> rct <anarchy> - \u0417\u0430\u0445\u043e\u0434\u0438\u0442 \u043d\u0430 <anarchy>", "> rct - \u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434\u0438\u0442 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e \u0433\u0434\u0435 \u0432\u044b \u0442\u043e\u043b\u044c\u043a\u043e \u0447\u0442\u043e \u0431\u044b\u043b\u0438");
    }
}
