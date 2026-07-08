/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  net.minecraft.class_3675$class_307
 */
package code.alphadlc.utils.client.managers.api.command.datatypes;

import code.alphadlc.utils.client.chat.StringHelper;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeContext;
import code.alphadlc.utils.client.managers.api.command.datatypes.IDatatypeFor;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.class_3675;

public enum KeyDataType implements IDatatypeFor<Map.Entry<String, Integer>>
{
    INSTANCE;


    @Override
    public Stream<String> tabComplete(IDatatypeContext datatypeContext) throws CommandException {
        Stream<String> keys = KeyDataType.getKeys().keySet().stream();
        String context = datatypeContext.getConsumer().getString();
        return new TabCompleteHelper().append(keys).filterPrefix(context).sortAlphabetically().stream();
    }

    @Override
    public Map.Entry<String, Integer> get(IDatatypeContext datatypeContext) throws CommandException {
        String key = datatypeContext.getConsumer().getString();
        return KeyDataType.getKeys().entrySet().stream().filter(s -> ((String)s.getKey()).equalsIgnoreCase(key)).findFirst().orElse(null);
    }

    private static Map<String, Integer> getKeys() {
        HashMap<String, Integer> keys = new HashMap<String, Integer>();
        for (Int2ObjectMap.Entry entry : class_3675.class_307.field_1668.field_1674.int2ObjectEntrySet()) {
            int keyCode = entry.getIntKey();
            String bindName = StringHelper.getBindName(keyCode).toLowerCase();
            keys.put(bindName, keyCode);
        }
        return keys;
    }
}
