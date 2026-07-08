/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2586
 */
package code.alphadlc.events.block;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_2586;

public record BlockEntityProgressEvent(class_2586 blockEntity, Type type) implements Event
{

    public static enum Type {
        ADD,
        REMOVE;

    }
}
