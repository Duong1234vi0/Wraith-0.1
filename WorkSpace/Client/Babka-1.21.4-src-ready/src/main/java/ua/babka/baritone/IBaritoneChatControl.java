package ua.babka.baritone;

import java.util.UUID;

public interface IBaritoneChatControl {
    public static final String FORCE_COMMAND_PREFIX = String.format("<<%s>>", UUID.randomUUID());
}

