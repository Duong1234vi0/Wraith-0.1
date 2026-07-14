package ua.babka.baritone;

import java.util.Map;
import java.util.stream.Stream;
import ua.babka.baritone.IDatatypeContext;
import ua.babka.baritone.IDatatypeFor;
import ua.babka.command.CommandException;
import ua.babka.command.TabCompleteHelper;
import ua.babka.util.KeyStorage;

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
        return KeyDataType.getKeys().entrySet().stream().filter(s2 -> ((String)s2.getKey()).equalsIgnoreCase(key)).findFirst().orElse(null);
    }

    private static Map<String, Integer> getKeys() {
        return KeyStorage.keyMap;
    }
}

