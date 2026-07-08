/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_5250
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.common.repository.staff.StaffRepository;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.datatypes.StaffDataType;
import code.alphadlc.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.Paginator;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class StaffCommand
extends Command {
    public StaffCommand() {
        super("staff");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
            case "add": {
                args.requireMin(1);
                String name = args.getString();
                if (!StaffRepository.isStaff(name)) {
                    StaffRepository.addStaff(name);
                    this.save();
                    this.logDirect("\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0434\u043e\u0431\u0430\u0432\u0438\u043b\u0438 " + String.valueOf(class_124.field_1060) + name + String.valueOf(class_124.field_1080) + " \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430!");
                    break;
                }
                this.logDirect(String.valueOf(class_124.field_1061) + name + " \u0443\u0436\u0435 \u0435\u0441\u0442\u044c \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430!", class_124.field_1061);
                break;
            }
            case "remove": {
                args.requireMin(1);
                String name = args.getString();
                if (StaffRepository.isStaff(name)) {
                    StaffRepository.removeStaff(name);
                    this.save();
                    this.logDirect("\u0412\u044b \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0443\u0434\u0430\u043b\u0438\u043b\u0438 " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430!");
                    break;
                }
                this.logDirect(String.valueOf(class_124.field_1061) + name + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430", class_124.field_1061);
                break;
            }
            case "list": {
                args.requireMax(1);
                Paginator.paginate(args, new Paginator<StaffRepository.Staff>(StaffRepository.getStaff()), () -> this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430:"), staff -> {
                    class_5250 namesComponent = class_2561.method_43470((String)staff.getName());
                    namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
                    return namesComponent;
                }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
                break;
            }
            case "clear": {
                args.requireMax(0);
                StaffRepository.clear();
                this.save();
                this.logDirect("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430 \u043e\u0447\u0438\u0449\u0435\u043d.");
                break;
            }
            default: {
                this.logDirect("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0430\u044f \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u0430. \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435: add, remove, list, clear");
            }
        }
    }

    private void save() {
        try {
            Method saveMethod = StaffRepository.class.getDeclaredMethod("save", new Class[0]);
            saveMethod.setAccessible(true);
            saveMethod.invoke(null, new Object[0]);
        }
        catch (ReflectiveOperationException e) {
            throw new RuntimeException("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u0441\u043f\u0438\u0441\u043a\u0430 \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430 \u0447\u0435\u0440\u0435\u0437 \u0440\u0435\u0444\u043b\u0435\u043a\u0441\u0438\u044e", e);
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).stream();
        }
        if (args.hasAtMost(2)) {
            String arg = args.peekString(0).toLowerCase(Locale.US);
            if (arg.equals("add")) {
                return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
            }
            if (arg.equals("remove")) {
                return args.tabCompleteDatatype(StaffDataType.INSTANCE);
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0441\u043f\u0438\u0441\u043a\u043e\u043c \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u042d\u0442\u0430 \u043a\u043e\u043c\u0430\u043d\u0434\u0430 \u043f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0443\u043f\u0440\u0430\u0432\u043b\u044f\u0442\u044c \u0441\u043f\u0438\u0441\u043a\u043e\u043c \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> staff add <\u043d\u0438\u043a> - \u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u0430 \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.", "> staff remove <\u043d\u0438\u043a> - \u0423\u0434\u0430\u043b\u044f\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u0430 \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.", "> staff list - \u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.", "> staff clear - \u041e\u0447\u0438\u0449\u0430\u0435\u0442 \u0441\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430.");
    }
}
