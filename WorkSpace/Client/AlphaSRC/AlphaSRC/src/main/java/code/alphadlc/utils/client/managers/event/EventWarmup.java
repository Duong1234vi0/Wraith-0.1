/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package code.alphadlc.utils.client.managers.event;

import code.alphadlc.events.block.BlockBreakingEvent;
import code.alphadlc.events.block.BlockCollisionEvent;
import code.alphadlc.events.block.BlockEntityProgressEvent;
import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.events.block.BreakBlockEvent;
import code.alphadlc.events.block.PushEvent;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.chat.TabCompleteEvent;
import code.alphadlc.events.container.CloseScreenEvent;
import code.alphadlc.events.container.HandledScreenEvent;
import code.alphadlc.events.container.SetScreenEvent;
import code.alphadlc.events.item.ClickSlotEvent;
import code.alphadlc.events.item.HandAnimationEvent;
import code.alphadlc.events.item.HandOffsetEvent;
import code.alphadlc.events.item.SwingDurationEvent;
import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.keyboard.HotBarScrollEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.keyboard.MouseRotationEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.player.BoundingBoxControlEvent;
import code.alphadlc.events.player.DeathScreenEvent;
import code.alphadlc.events.player.EntityDeathEvent;
import code.alphadlc.events.player.EntitySpawnEvent;
import code.alphadlc.events.player.EventNoSlow;
import code.alphadlc.events.player.FireworkEvent;
import code.alphadlc.events.player.GameUpdateEvent;
import code.alphadlc.events.player.HeldItemRendererEvent;
import code.alphadlc.events.player.HotBarUpdateEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.JumpEvent;
import code.alphadlc.events.player.MotionEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.player.PlayerCollisionEvent;
import code.alphadlc.events.player.PlayerTravelEvent;
import code.alphadlc.events.player.PlayerVelocityStrafeEvent;
import code.alphadlc.events.player.PostMotionEvent;
import code.alphadlc.events.player.PostTickEvent;
import code.alphadlc.events.player.RotatedMovementInputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.SprintEvent;
import code.alphadlc.events.player.SwimmingEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.AspectRatioEvent;
import code.alphadlc.events.render.CameraEvent;
import code.alphadlc.events.render.CameraPositionEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.EntityColorEvent;
import code.alphadlc.events.render.FogEvent;
import code.alphadlc.events.render.FovEvent;
import code.alphadlc.events.render.ItemRendererEvent;
import code.alphadlc.events.render.TextFactoryEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EventWarmup {
    private static final Logger LOGGER = LogManager.getLogger((String)"AlphaDLC");
    private static final Class<?>[] EVENT_TYPES = new Class[]{AttackEvent.class, AspectRatioEvent.class, BlockBreakingEvent.class, BlockCollisionEvent.class, BlockEntityProgressEvent.class, BlockUpdateEvent.class, BreakBlockEvent.class, BoundingBoxControlEvent.class, CameraEvent.class, CameraPositionEvent.class, ChatEvent.class, ClickSlotEvent.class, CloseScreenEvent.class, DeathScreenEvent.class, DrawEvent.class, EntityColorEvent.class, EntityDeathEvent.class, EntitySpawnEvent.class, EventNoSlow.class, FireworkEvent.class, FogEvent.class, FovEvent.class, GameUpdateEvent.class, HandAnimationEvent.class, HandOffsetEvent.class, HandledScreenEvent.class, HeldItemRendererEvent.class, HotBarScrollEvent.class, HotBarUpdateEvent.class, InputEvent.class, ItemRendererEvent.class, JumpEvent.class, KeyEvent.class, MotionEvent.class, MouseRotationEvent.class, MoveEvent.class, PacketEvent.class, PlayerCollisionEvent.class, PlayerTravelEvent.class, PlayerVelocityStrafeEvent.class, PostMotionEvent.class, PostTickEvent.class, PushEvent.class, RotatedMovementInputEvent.class, RotationUpdateEvent.class, SetScreenEvent.class, SprintEvent.class, SwimmingEvent.class, SwingDurationEvent.class, TabCompleteEvent.class, TextFactoryEvent.class, TickEvent.class, UsingItemEvent.class, WorldLoadEvent.class, WorldRenderEvent.class};

    private EventWarmup() {
    }

    public static void warmup() {
        for (Class<?> type : EVENT_TYPES) {
            EventWarmup.touch(type);
        }
        LOGGER.debug("Preloaded {} event types", (Object)EVENT_TYPES.length);
    }

    private static void touch(Class<?> type) {
        try {
            Class.forName(type.getName(), true, type.getClassLoader());
        }
        catch (ClassNotFoundException error) {
            LOGGER.error("Missing event class: {}", (Object)type.getName(), (Object)error);
        }
        catch (LinkageError error) {
            LOGGER.error("Failed to initialize event class: {}", (Object)type.getName(), (Object)error);
        }
    }
}
