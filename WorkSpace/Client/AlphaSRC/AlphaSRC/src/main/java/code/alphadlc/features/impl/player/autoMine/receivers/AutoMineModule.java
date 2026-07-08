/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.player.autoMine.receivers;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.movement.autoMystGlider.parsers.SpookyParser;
import code.alphadlc.features.impl.player.autoMine.controllers.AutoMineController;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

public class AutoMineModule
extends Module {
    private final AutoMineController controller = new AutoMineController();
    private final TextSetting anarchyNumber = new TextSetting("Anarchy", "").setText("Anarchy").setMin(3).setMax(4);
    private final TextSetting homeName = new TextSetting("Home", "").setText("Home").setMax(16).setMin(0);

    public AutoMineModule() {
        super("AutoMine", ModuleCategory.PLAYER);
        this.setup(this.anarchyNumber, this.homeName);
    }

    @EventHandler
    public void tickEventReceive(TickEvent tickEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        this.controller.onTick();
    }

    @EventHandler
    public void rotationUpdateEventReceive(RotationUpdateEvent rotationUpdateEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        this.controller.onRotationUpdate(rotationUpdateEvent);
    }

    @EventHandler
    public void worldChangeEventReceive(WorldLoadEvent worldLoadEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        if (this.controller != null) {
            this.controller.onWorldChange();
        }
    }

    @EventHandler
    public void packetEventReceive(PacketEvent packetEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        if (packetEvent.getType() != PacketEvent.Type.RECEIVE) {
            return;
        }
        if (AutoMineModule.mc.field_1724 == null || this.controller == null) {
            return;
        }
        class_2596<?> class_25962 = packetEvent.getPacket();
        if (class_25962 instanceof class_7439) {
            class_7439 gameMessage = (class_7439)class_25962;
            class_2561 content = gameMessage.comp_763();
            if (content == null) {
                return;
            }
            String message = content.getString();
            if (message != null && (message.contains("\u0412\u044b \u0443\u0436\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u044b \u043d\u0430 \u044d\u0442\u043e\u0442 \u0441\u0435\u0440\u0432\u0435\u0440!") || message.contains("\u0412\u044b \u0443\u0436\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u044b \u043a \u044d\u0442\u043e\u043c\u0443 \u0441\u0435\u0440\u0432\u0435\u0440\u0443!"))) {
                this.controller.notifyAlreadyConnected();
            }
        }
    }

    @EventHandler
    public void drawEventReceive(DrawEvent drawEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        this.controller.onDraw(drawEvent);
    }

    @EventHandler
    public void inputEventReceive(InputEvent inputEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        this.controller.onInput(inputEvent);
    }

    @EventHandler
    public void worldRenderEventReceive(WorldRenderEvent worldRenderEvent) {
        if (SpookyParser.isAnarchy(-1)) {
            return;
        }
        this.controller.onWorldRender(worldRenderEvent);
    }

    public AutoMineController getController() {
        return this.controller;
    }

    public TextSetting getAnarchyNumber() {
        return this.anarchyNumber;
    }

    public TextSetting getHomeName() {
        return this.homeName;
    }
}
