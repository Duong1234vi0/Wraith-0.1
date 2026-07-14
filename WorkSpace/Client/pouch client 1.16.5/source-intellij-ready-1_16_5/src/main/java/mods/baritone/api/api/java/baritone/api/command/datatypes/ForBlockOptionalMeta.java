/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.v_3760_Q;
import mods.baritone.api.api.java.baritone.api.command.datatypes.BlockById;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeFor;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;

public enum ForBlockOptionalMeta implements IDatatypeFor<BlockOptionalMeta>
{
    INSTANCE;

    private static Pattern PATTERN;

    @Override
    public BlockOptionalMeta get(IDatatypeContext ctx) throws CommandException {
        return new BlockOptionalMeta(ctx.getConsumer().getString());
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) throws CommandException {
        String arg = ctx.getConsumer().peekString();
        if (!PATTERN.matcher(arg).matches()) {
            ctx.getConsumer().getString();
            return Stream.empty();
        }
        if (arg.endsWith("]")) {
            ctx.getConsumer().getString();
            return Stream.empty();
        }
        if (!arg.contains("[")) {
            return ctx.getConsumer().tabCompleteDatatype(BlockById.INSTANCE);
        }
        ctx.getConsumer().getString();
        String[] parts = ForBlockOptionalMeta.splitLast(arg, '[');
        String blockId = parts[0];
        String properties = parts[1];
        T_2915_h block = V_3137_a.q_4610_l.J_1907_R(new g_2336_b(blockId)).orElse(null);
        if (block == null) {
            return Stream.empty();
        }
        String[] parts2 = ForBlockOptionalMeta.splitLast(properties, ',');
        String leadingProperties = parts2[0];
        String lastProperty = parts2[1];
        if (!lastProperty.contains("=")) {
            Set usedProps = Stream.of(leadingProperties.split(",")).map(pair -> pair.split("=")[0]).collect(Collectors.toSet());
            String prefix = arg.substring(0, arg.length() - lastProperty.length());
            return new TabCompleteHelper().append(block.t_1786_h().G_564_y().stream().map(v_3760_Q::P_1922_E)).filter(prop -> !usedProps.contains(prop)).filterPrefix(lastProperty).sortAlphabetically().map(prop -> prefix + prop).stream();
        }
        String[] parts3 = ForBlockOptionalMeta.splitLast(lastProperty, '=');
        String lastName = parts3[0];
        String lastValue = parts3[1];
        String prefix = arg.substring(0, arg.length() - lastValue.length());
        v_3760_Q<?> property = block.t_1786_h().n_1700_B(lastName);
        if (property == null) {
            return Stream.empty();
        }
        return new TabCompleteHelper().append(ForBlockOptionalMeta.getValues(property)).filterPrefix(lastValue).sortAlphabetically().map(val -> prefix + val).stream();
    }

    private static String[] splitLast(String string, char chr) {
        int idx = string.lastIndexOf(chr);
        if (idx == -1) {
            return new String[]{"", string};
        }
        return new String[]{string.substring(0, idx), string.substring(idx + 1)};
    }

    private static <T extends Comparable<T>> Stream<String> getValues(v_3760_Q<T> property) {
        return property.n_1700_B().stream().map(property::n_1700_B);
    }

    static {
        PATTERN = Pattern.compile("(?:[a-z0-9_.-]+:)?(?:[a-z0-9/_.-]+(?:\\[(?:(?:[a-z0-9_.-]+=[a-z0-9_.-]+,)*(?:[a-z0-9_.-]+(?:=(?:[a-z0-9_.-]+(?:\\])?)?)?)?|\\])?)?)?");
    }
}

