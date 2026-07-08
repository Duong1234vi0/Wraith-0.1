/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2828$class_5911
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class VClipCommand
extends Command {
    public VClipCommand() {
        super("vclip", "vc");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String direction;
        args.requireMin(1);
        args.requireMax(1);
        if (VClipCommand.mc.field_1724 == null || VClipCommand.mc.field_1687 == null) {
            this.logDirect(String.valueOf(class_124.field_1061) + "\u0418\u0433\u0440\u043e\u043a \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d");
            return;
        }
        class_2338 playerPos = VClipCommand.mc.field_1724.method_24515();
        float yOffset = switch (direction = args.getString().toLowerCase()) {
            case "up" -> this.findOffset(playerPos, true);
            case "down" -> this.findOffset(playerPos, false);
            default -> this.parseOffset(direction);
        };
        if (yOffset != 0.0f) {
            boolean hasElytra;
            int elytraSlot = this.getElytraSlot();
            boolean bl = hasElytra = elytraSlot != -1 && elytraSlot != -2;
            if (hasElytra) {
                this.switchElytra(elytraSlot, true);
            }
            this.teleport(yOffset, hasElytra);
            if (hasElytra) {
                this.switchElytra(elytraSlot, false);
            }
        } else {
            this.logDirect(String.valueOf(class_124.field_1061) + "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0432\u044b\u043f\u043e\u043b\u043d\u0438\u0442\u044c \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044e.");
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("up", "down").filterPrefix(args.peekString()).stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u0432\u0432\u0435\u0440\u0445/\u0432\u043d\u0438\u0437 \u043f\u043e \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u0438";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u0443\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u0430 \u0432\u0432\u0435\u0440\u0445 \u0438\u043b\u0438 \u0432\u043d\u0438\u0437 \u043f\u043e \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u0438.", "", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435:", "> vclip up - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u0432\u0432\u0435\u0440\u0445 \u0434\u043e \u0431\u043b\u0438\u0436\u0430\u0439\u0448\u0435\u0433\u043e \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0433\u043e \u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0441\u0442\u0432\u0430", "> vclip down - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u0432\u043d\u0438\u0437 \u0434\u043e \u0431\u043b\u0438\u0436\u0430\u0439\u0448\u0435\u0433\u043e \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0433\u043e \u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0441\u0442\u0432\u0430", "> vclip <distance> - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u043d\u0430 \u0443\u043a\u0430\u0437\u0430\u043d\u043d\u043e\u0435 \u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435", "", "\u041f\u0440\u0438\u043c\u0435\u0440\u044b:", "> vclip 10 - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u043d\u0430 10 \u0431\u043b\u043e\u043a\u043e\u0432 \u0432\u0432\u0435\u0440\u0445", "> vclip -5 - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044f \u043d\u0430 5 \u0431\u043b\u043e\u043a\u043e\u0432 \u0432\u043d\u0438\u0437");
    }

    private float findOffset(class_2338 playerPos, boolean toUp) {
        int startY = toUp ? 3 : -1;
        int endY = toUp ? 255 : -255;
        int step = toUp ? 1 : -1;
        for (int i = startY; i != endY; i += step) {
            class_2338 targetPos = playerPos.method_10069(0, i, 0);
            if (VClipCommand.mc.field_1687.method_8320(targetPos).method_26215()) {
                return i + (toUp ? 1 : -1);
            }
            if (!VClipCommand.mc.field_1687.method_8320(targetPos).method_27852(class_2246.field_9987) || toUp) continue;
            this.logDirect(String.valueOf(class_124.field_1061) + "\u0422\u0443\u0442 \u043d\u0435\u043b\u044c\u0437\u044f \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e\u0434 \u0437\u0435\u043c\u043b\u044e.");
            return 0.0f;
        }
        return 0.0f;
    }

    private void teleport(float yOffset, boolean elytra) {
        if (elytra) {
            for (int i = 0; i < 2; ++i) {
                VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(VClipCommand.mc.field_1724.method_23317(), VClipCommand.mc.field_1724.method_23318(), VClipCommand.mc.field_1724.method_23321(), false, false));
            }
            VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)VClipCommand.mc.field_1724, class_2848.class_2849.field_12982));
            VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(VClipCommand.mc.field_1724.method_23317(), VClipCommand.mc.field_1724.method_23318() + (double)yOffset, VClipCommand.mc.field_1724.method_23321(), false, false));
            VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)VClipCommand.mc.field_1724, class_2848.class_2849.field_12982));
            VClipCommand.mc.field_1724.method_5814(VClipCommand.mc.field_1724.method_23317(), VClipCommand.mc.field_1724.method_23318() + (double)yOffset, VClipCommand.mc.field_1724.method_23321());
            String blockUnit = this.getBlockUnit(yOffset);
            this.logDirect(String.valueOf(class_124.field_1080) + "\u041f\u043e\u043f\u044b\u0442\u043a\u0430 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f \u0441 \u044d\u043b\u0438\u0442\u0440\u043e\u0439...");
            this.logDirect(String.format("\u0412\u044b \u0431\u044b\u043b\u0438 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u043d\u0430 %.1f %s \u043f\u043e \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u0438", Float.valueOf(yOffset), blockUnit));
            return;
        }
        int packetsCount = this.calculatePacketsCount(yOffset);
        for (int i = 0; i < packetsCount; ++i) {
            VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_5911(VClipCommand.mc.field_1724.method_24828(), false));
        }
        VClipCommand.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(VClipCommand.mc.field_1724.method_23317(), VClipCommand.mc.field_1724.method_23318() + (double)yOffset, VClipCommand.mc.field_1724.method_23321(), false, false));
        VClipCommand.mc.field_1724.method_5814(VClipCommand.mc.field_1724.method_23317(), VClipCommand.mc.field_1724.method_23318() + (double)yOffset, VClipCommand.mc.field_1724.method_23321());
        String blockUnit = this.getBlockUnit(yOffset);
        this.logDirect(String.format("\u0412\u044b \u0431\u044b\u043b\u0438 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u043d\u0430 %.1f %s \u043f\u043e \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u0438", Float.valueOf(yOffset), blockUnit));
    }

    private String getBlockUnit(float offset) {
        float absOffset = Math.abs(offset);
        if (absOffset == 1.0f) {
            return "\u0431\u043b\u043e\u043a";
        }
        if (absOffset >= 2.0f && absOffset <= 4.0f) {
            return "\u0431\u043b\u043e\u043a\u0430";
        }
        return "\u0431\u043b\u043e\u043a\u043e\u0432";
    }

    private float parseOffset(String distance) {
        try {
            return Float.parseFloat(distance);
        }
        catch (NumberFormatException e) {
            this.logDirect(String.valueOf(class_124.field_1061) + distance + String.valueOf(class_124.field_1080) + " \u043d\u0435 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0447\u0438\u0441\u043b\u043e\u043c!");
            return 0.0f;
        }
    }

    private int calculatePacketsCount(float yOffset) {
        return Math.max((int)(Math.abs(yOffset) / 10.0f), 3);
    }

    private void switchElytra(int elytraSlot, boolean equip) {
        int chestplateSlot = 6;
        if (equip) {
            VClipCommand.mc.field_1761.method_2906(VClipCommand.mc.field_1724.field_7512.field_7763, elytraSlot, 0, class_1713.field_7790, (class_1657)VClipCommand.mc.field_1724);
            VClipCommand.mc.field_1761.method_2906(VClipCommand.mc.field_1724.field_7512.field_7763, 6, 0, class_1713.field_7790, (class_1657)VClipCommand.mc.field_1724);
        } else {
            VClipCommand.mc.field_1761.method_2906(VClipCommand.mc.field_1724.field_7512.field_7763, 6, 0, class_1713.field_7790, (class_1657)VClipCommand.mc.field_1724);
            VClipCommand.mc.field_1761.method_2906(VClipCommand.mc.field_1724.field_7512.field_7763, elytraSlot, 0, class_1713.field_7790, (class_1657)VClipCommand.mc.field_1724);
        }
    }

    private int getElytraSlot() {
        class_1799 chestStack = VClipCommand.mc.field_1724.method_31548().method_7372(2);
        if (chestStack != null && chestStack.method_31574(class_1802.field_8833)) {
            return -2;
        }
        int slot = -1;
        for (int i = 0; i < 36; ++i) {
            class_1799 stack = VClipCommand.mc.field_1724.method_31548().method_5438(i);
            if (!stack.method_31574(class_1802.field_8833)) continue;
            slot = i;
            break;
        }
        if (slot != -1 && slot < 9) {
            slot += 36;
        }
        return slot;
    }
}
