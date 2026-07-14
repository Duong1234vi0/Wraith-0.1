/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.listener;

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
import mods.baritone.api.api.java.baritone.api.event.listener.IGameEventListener;

public interface AbstractGameEventListener
extends IGameEventListener {
    @Override
    default public void onTick(TickEvent event) {
    }

    @Override
    default public void onPlayerUpdate(PlayerUpdateEvent event) {
    }

    @Override
    default public void onSendChatMessage(ChatEvent event) {
    }

    @Override
    default public void onPreTabComplete(TabCompleteEvent event) {
    }

    @Override
    default public void onChunkEvent(ChunkEvent event) {
    }

    @Override
    default public void onRenderPass(RenderEvent event) {
    }

    @Override
    default public void onWorldEvent(WorldEvent event) {
    }

    @Override
    default public void onSendPacket(PacketEvent event) {
    }

    @Override
    default public void onReceivePacket(PacketEvent event) {
    }

    @Override
    default public void onPlayerSprintState(SprintStateEvent event) {
    }

    @Override
    default public void onBlockInteract(BlockInteractEvent event) {
    }

    @Override
    default public void onPlayerDeath() {
    }

    @Override
    default public void onPathEvent(PathEvent event) {
    }
}

