/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 */
package code.alphadlc.events.block;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public record BlockBreakingEvent(class_2338 blockPos, class_2350 direction) implements Event
{
}
