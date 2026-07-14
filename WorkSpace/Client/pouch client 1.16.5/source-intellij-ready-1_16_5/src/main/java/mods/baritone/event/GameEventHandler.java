/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.H_1748_a;
import lightning.product.b_4507_u;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.event.events.BlockInteractEvent;
import mods.baritone.api.api.java.baritone.api.event.events.ChatEvent;
import mods.baritone.api.api.java.baritone.api.event.events.ChunkEvent;
import mods.baritone.api.api.java.baritone.api.event.events.PacketEvent;
import mods.baritone.api.api.java.baritone.api.event.events.PathEvent;
import mods.baritone.api.api.java.baritone.api.event.events.PlayerUpdateEvent;
import mods.baritone.api.api.java.baritone.api.event.events.RenderEvent;
import mods.baritone.api.api.java.baritone.api.event.events.SprintStateEvent;
import mods.baritone.api.api.java.baritone.api.event.events.TabCompleteEvent;
import mods.baritone.api.api.java.baritone.api.event.events.TickEvent;
import mods.baritone.api.api.java.baritone.api.event.events.WorldEvent;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;
import mods.baritone.api.api.java.baritone.api.event.listener.IEventBus;
import mods.baritone.api.api.java.baritone.api.event.listener.IGameEventListener;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.cache.WorldProvider;
import mods.baritone.utils.BlockStateInterface;

public final class GameEventHandler
implements IEventBus,
Helper {
    private final Baritone baritone;
    private final List<IGameEventListener> listeners = new CopyOnWriteArrayList<IGameEventListener>();

    public GameEventHandler(Baritone baritone) {
        this.baritone = baritone;
    }

    @Override
    public final void onTick(TickEvent event) {
        if (event.getType() == TickEvent.Type.IN) {
            try {
                this.baritone.bsi = new BlockStateInterface(this.baritone.getPlayerContext(), true);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.baritone.bsi = null;
            }
        } else {
            this.baritone.bsi = null;
        }
        this.listeners.forEach(l -> l.onTick(event));
    }

    @Override
    public final void onPlayerUpdate(PlayerUpdateEvent event) {
        this.listeners.forEach(l -> l.onPlayerUpdate(event));
    }

    @Override
    public final void onSendChatMessage(ChatEvent event) {
        this.listeners.forEach(l -> l.onSendChatMessage(event));
    }

    @Override
    public void onPreTabComplete(TabCompleteEvent event) {
        this.listeners.forEach(l -> l.onPreTabComplete(event));
    }

    @Override
    public final void onChunkEvent(ChunkEvent event) {
        boolean isPreUnload;
        EventState state = event.getState();
        ChunkEvent.Type type = event.getType();
        boolean isPostPopulate = state == EventState.POST && (type == ChunkEvent.Type.POPULATE_FULL || type == ChunkEvent.Type.POPULATE_PARTIAL);
        b_4507_u world = this.baritone.getPlayerContext().world();
        boolean bl = isPreUnload = state == EventState.PRE && type == ChunkEvent.Type.UNLOAD && world.q_2307_F().J_1907_R(event.getX(), event.getZ(), null, false) != null;
        if (isPostPopulate || isPreUnload) {
            this.baritone.getWorldProvider().ifWorldLoaded(worldData -> {
                H_1748_a chunk = world.u_1723_Y(event.getX(), event.getZ());
                worldData.getCachedWorld().queueForPacking(chunk);
            });
        }
        this.listeners.forEach(l -> l.onChunkEvent(event));
    }

    @Override
    public final void onRenderPass(RenderEvent event) {
        this.listeners.forEach(l -> l.onRenderPass(event));
    }

    @Override
    public final void onWorldEvent(WorldEvent event) {
        WorldProvider cache = this.baritone.getWorldProvider();
        if (event.getState() == EventState.POST) {
            cache.closeWorld();
            if (event.getWorld() != null) {
                cache.initWorld(event.getWorld());
            }
        }
        this.listeners.forEach(l -> l.onWorldEvent(event));
    }

    @Override
    public final void onSendPacket(PacketEvent event) {
        this.listeners.forEach(l -> l.onSendPacket(event));
    }

    @Override
    public final void onReceivePacket(PacketEvent event) {
        this.listeners.forEach(l -> l.onReceivePacket(event));
    }

    @Override
    public void onPlayerSprintState(SprintStateEvent event) {
        this.listeners.forEach(l -> l.onPlayerSprintState(event));
    }

    @Override
    public void onBlockInteract(BlockInteractEvent event) {
        this.listeners.forEach(l -> l.onBlockInteract(event));
    }

    @Override
    public void onPlayerDeath() {
        this.listeners.forEach(IGameEventListener::onPlayerDeath);
    }

    @Override
    public void onPathEvent(PathEvent event) {
        this.listeners.forEach(l -> l.onPathEvent(event));
    }

    @Override
    public final void registerEventListener(IGameEventListener listener) {
        this.listeners.add(listener);
    }
}

