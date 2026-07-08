/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_1304
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.movement.autoMystGlider.controllers;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.features.impl.misc.ElytraHelper;
import code.alphadlc.features.impl.movement.autoMystGlider.handers.ConnectionHandler;
import code.alphadlc.features.impl.movement.autoMystGlider.handers.NoClipHandler;
import code.alphadlc.features.impl.movement.autoMystGlider.handers.StealHandler;
import code.alphadlc.features.impl.movement.autoMystGlider.receivers.AutoMystGliderModule;
import code.alphadlc.features.impl.movement.autoMystGlider.states.ConnectionState;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlidePhase;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlideState;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_10185;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_243;

public class AutoGliderController
implements QuickImports {
    private final ConnectionHandler connectionHandler;
    private final StealHandler stealHandler;
    private final NoClipHandler noClipHandler;
    private final GlideState state;

    public AutoGliderController(class_243 vec3d, AutoMystGliderModule module) {
        this.state = new GlideState(vec3d);
        this.stealHandler = new StealHandler(module);
        this.connectionHandler = new ConnectionHandler(this.state, this.stealHandler, module);
        this.noClipHandler = new NoClipHandler(this.state, this.connectionHandler.getConnection());
    }

    public void onRotationUpdate(RotationUpdateEvent rotationUpdateEvent) {
        GlidePhase phase = this.getPhase();
        if (phase == GlidePhase.LOOTING) {
            this.stealHandler.onRotationUpdate(rotationUpdateEvent);
        }
    }

    public void onInput(InputEvent inputEvent) {
        if (AutoGliderController.mc.field_1724 == null || this.state.vec3d() == null || !this.connectionHandler.getConnection().isConnected()) {
            return;
        }
        GlidePhase phase = this.getPhase();
        if (phase == GlidePhase.FLYING) {
            this.handleFlyingInput(inputEvent);
        }
    }

    private void handleFlyingInput(InputEvent inputEvent) {
        if (this.state.done() && this.state.shouldLand()) {
            inputEvent.setSneak(true);
            return;
        }
        if (this.state.verticalGlide()) {
            inputEvent.setJumping(true);
        }
        if (this.state.horizontalGlide()) {
            class_10185 playerInput = this.state.selectInput();
            inputEvent.setDirectional(playerInput.comp_3159(), playerInput.comp_3160(), playerInput.comp_3161(), playerInput.comp_3162());
        }
    }

    public void onTick() {
        if (AutoGliderController.mc.field_1724 == null) {
            return;
        }
        GlidePhase phase = this.getPhase();
        ConnectionState connection = this.connectionHandler.getConnection();
        if (phase == GlidePhase.LOOTING && !this.connectionHandler.isElytraSwaped() && connection.getAnarchy() != -1 && AutoGliderController.mc.field_1724.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833) && System.currentTimeMillis() - this.connectionHandler.getElytraChangeTime() > 2000L) {
            ElytraHelper elytraHelper = AlphaDLC.getInstance().getModuleProvider().get(ElytraHelper.class);
            elytraHelper.changeChestPlate();
            this.connectionHandler.setElytraSwaped(true);
            this.connectionHandler.setElytraChangeTime(System.currentTimeMillis());
        }
        switch (phase) {
            case FLYING: {
                this.connectionHandler.onTick();
                if (connection.isConnected() && this.canEnableNoClip()) {
                    this.noClipHandler.onTick();
                    break;
                }
                this.noClipHandler.end();
                break;
            }
            case LOOTING: {
                this.stealHandler.onTick();
                break;
            }
            default: {
                this.connectionHandler.onTick();
            }
        }
    }

    public void onMove(MoveEvent moveEvent) {
        if (AutoGliderController.mc.field_1724 == null || !this.connectionHandler.getConnection().isConnected()) {
            return;
        }
        GlidePhase phase = this.getPhase();
        if (phase == GlidePhase.FLYING) {
            if (this.canEnableNoClip()) {
                this.noClipHandler.onMove(moveEvent);
            } else if (this.state.done() && !this.isAlignedToTarget()) {
                this.setMoveMotion(moveEvent, 0.1);
            }
        }
    }

    private boolean isAlignedToTarget() {
        class_243 target = this.state.vec3d();
        class_243 pos = AutoGliderController.mc.field_1724.method_19538();
        double dx = Math.abs(target.field_1352 - pos.field_1352);
        double dz = Math.abs(target.field_1350 - pos.field_1350);
        return dx < 0.3 && dz < 0.3;
    }

    private void setMoveMotion(MoveEvent move, double motion) {
        if (AutoGliderController.mc.field_1724 == null) {
            return;
        }
        ChatMessage.brandmessage("\u0437\u0430\u043c\u0435\u0434\u043b\u044f\u044e\u0441\u044c");
        class_243 target = this.state.vec3d();
        class_243 pos = AutoGliderController.mc.field_1724.method_19538();
        double dx = target.field_1352 - pos.field_1352;
        double dz = target.field_1350 - pos.field_1350;
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist < 0.05) {
            move.setMovement(new class_243(0.0, move.getMovement().field_1351, 0.0));
        } else {
            double nx = dx / dist * motion;
            double nz = dz / dist * motion;
            move.setMovement(new class_243(nx, move.getMovement().field_1351, nz));
        }
    }

    public void onPacket(PacketEvent packetEvent) {
        if (AutoGliderController.mc.field_1724 == null || this.state.vec3d() == null || !this.connectionHandler.getConnection().isConnected()) {
            return;
        }
        GlidePhase phase = this.getPhase();
        if (phase == GlidePhase.FLYING && this.canEnableNoClip()) {
            this.noClipHandler.onPacket(packetEvent);
        }
    }

    public void reload() {
        this.connectionHandler.reload();
        this.noClipHandler.end();
        TurnsConnection.INSTANCE.setRotation(null);
    }

    public boolean canEnableNoClip() {
        return this.state.done() && this.state.solidBlockInRadius(2);
    }

    private GlidePhase getPhase() {
        return this.connectionHandler.getConnection().getPhase();
    }

    public ConnectionHandler getConnectionHandler() {
        return this.connectionHandler;
    }

    public StealHandler getStealHandler() {
        return this.stealHandler;
    }

    public NoClipHandler getNoClipHandler() {
        return this.noClipHandler;
    }

    public GlideState getState() {
        return this.state;
    }
}
