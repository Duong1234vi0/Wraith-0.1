/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package code.alphadlc.events.block;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public record BlockUpdateEvent(class_2680 state, class_2338 pos, Type type) implements Event
{

    public static enum Type {
        LOAD,
        UNLOAD,
        UPDATE;

    }
}
