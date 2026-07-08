/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2797
 *  net.minecraft.class_7439
 *  net.minecraft.class_7472
 */
package code.alphadlc.features.impl.movement.autoMystGlider.receivers;

import code.alphadlc.display.hud.DynamicIsland;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.movement.autoMystGlider.EventStatusTracker;
import code.alphadlc.features.impl.movement.autoMystGlider.controllers.AutoGliderController;
import code.alphadlc.features.impl.movement.autoMystGlider.states.ConnectionState;
import code.alphadlc.features.impl.movement.autoMystGlider.states.GlidePhase;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2797;
import net.minecraft.class_7439;
import net.minecraft.class_7472;

public class AutoMystGliderModule
extends Module {
    private AutoGliderController autoGliderController;
    private final BindSetting bindSetting = new BindSetting("Point", "").setKey(86);
    private final TextSetting anarchyText = new TextSetting("Anarchy", "").setText("502").setMax(3).setMin(3);
    private final TextSetting homeName = new TextSetting("Home", "").setText("hata");

    public AutoMystGliderModule() {
        super("AutoMystGlider", ModuleCategory.MOVEMENT);
        this.setup(this.bindSetting, this.anarchyText, this.homeName);
    }

    @EventHandler
    public void inputEventReceive(InputEvent inputEvent) {
        if (AutoMystGliderModule.mc.field_1724 == null || this.autoGliderController == null) {
            return;
        }
        this.autoGliderController.onInput(inputEvent);
    }

    @EventHandler
    public void tickEventReceive(TickEvent tickEvent) {
        if (AutoMystGliderModule.mc.field_1724 == null || this.autoGliderController == null) {
            return;
        }
        this.autoGliderController.onTick();
    }

    @EventHandler
    public void moveEventReceive(MoveEvent moveEvent) {
        if (AutoMystGliderModule.mc.field_1724 == null || this.autoGliderController == null) {
            return;
        }
        this.autoGliderController.onMove(moveEvent);
    }

    @EventHandler
    public void packetEventReceive(PacketEvent packetEvent) {
        Object cleanedMessage;
        class_2596<?> class_25962 = packetEvent.getPacket();
        if (class_25962 instanceof class_7439) {
            class_7439 p = (class_7439)class_25962;
            String s = p.comp_763().getString();
            cleanedMessage = s.replaceAll("\u00a7.", "");
            boolean isEventStatusMessage = ((String)cleanedMessage).toLowerCase().contains("\u0438\u0432\u0435\u043d\u0442") && (((String)cleanedMessage).toLowerCase().contains("\u0447\u0435\u0440\u0435\u0437") || ((String)cleanedMessage).toLowerCase().contains("\u0443\u0436\u0435 \u043d\u0430\u0447\u0430\u043b\u0441\u044f"));
            EventStatusTracker.updateEventStatus((String)cleanedMessage);
            String eventStatus = EventStatusTracker.getDisplayStatus();
            if (eventStatus != null && !eventStatus.isEmpty() && isEventStatusMessage) {
                DynamicIsland.addNotification(eventStatus, DynamicIsland.NotificationType.INFO);
                packetEvent.cancel();
                return;
            }
            Pattern pattern = Pattern.compile("(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)");
            for (String line : ((String)cleanedMessage).split("\n")) {
                Matcher matcher;
                if (!line.toLowerCase().contains("\u043f\u043e\u044f\u0432\u0438\u043b\u0441\u044f \u043d\u0430 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u0445") && !line.toLowerCase().contains("\u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b:") || !(matcher = pattern.matcher(line)).find()) continue;
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                int z = Integer.parseInt(matcher.group(3));
                this.mystLocation(new class_243((double)x, (double)y, (double)z));
            }
        }
        if (packetEvent.getType() == PacketEvent.Type.SEND) {
            cleanedMessage = packetEvent.getPacket();
            if (cleanedMessage instanceof class_7472) {
                class_7472 commandPacket = (class_7472)cleanedMessage;
                command = commandPacket.comp_808();
                if (command != null && ((String)command).startsWith("an") && ((String)command).length() > 2 && mc.method_1562() != null) {
                    mc.method_1562().method_45731("event delay");
                }
            } else {
                class_2797 chatPacket;
                String message;
                command = packetEvent.getPacket();
                if (command instanceof class_2797 && (message = (chatPacket = (class_2797)command).comp_945()) != null && message.startsWith("/an") && message.length() > 3 && mc.method_1562() != null) {
                    mc.method_1562().method_45731("event delay");
                }
            }
        }
        if (AutoMystGliderModule.mc.field_1724 == null || this.autoGliderController == null) {
            return;
        }
        this.autoGliderController.onPacket(packetEvent);
    }

    @EventHandler
    public void rotationUpdateReceive(RotationUpdateEvent rotationUpdateEvent) {
        if (AutoMystGliderModule.mc.field_1724 == null || this.autoGliderController == null) {
            return;
        }
        this.autoGliderController.onRotationUpdate(rotationUpdateEvent);
    }

    @EventHandler
    public void keyEventReceive(KeyEvent keyEvent) {
        if (keyEvent.key() == this.bindSetting.getKey() && keyEvent.action() == 1) {
            this.mystLocation(AutoMystGliderModule.mc.field_1724.method_19538());
        }
    }

    private void mystLocation(class_243 vec3d) {
        if (this.autoGliderController != null) {
            GlidePhase phase = this.autoGliderController.getConnectionHandler().getConnection().getPhase();
            if (phase == GlidePhase.WAITING_COORDS) {
                ChatMessage.brandmessage("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u044b! \u041b\u0435\u0442\u0438\u043c \u043d\u0430 " + String.valueOf(vec3d));
            }
            this.autoGliderController.reload();
        }
        this.autoGliderController = new AutoGliderController(vec3d, this);
        this.logDirect("Place marker on coordinate: " + vec3d.toString());
    }

    @Override
    public void deactivate() {
        if (this.autoGliderController != null) {
            this.autoGliderController.reload();
        }
        super.deactivate();
    }

    @Override
    public void activate() {
        if (this.autoGliderController != null) {
            this.autoGliderController.reload();
        }
        this.autoGliderController = new AutoGliderController(new class_243(0.0, 0.0, 0.0), this);
        ConnectionState conn = this.autoGliderController.getConnectionHandler().getConnection();
        conn.setConnected(false);
        conn.transitionTo(GlidePhase.HUB_TO_NEXT);
        if (mc.method_1562() != null) {
            mc.method_1562().method_45731("hub");
        }
        ChatMessage.brandmessage("[AutoMyst] \u041c\u043e\u0434\u0443\u043b\u044c \u0432\u043a\u043b\u044e\u0447\u0451\u043d. \u0424\u0430\u0437\u0430: HUB_TO_NEXT \u2014 \u0438\u0449\u0435\u043c \u0438\u0432\u0435\u043d\u0442 \u0438\u0437 API...");
        super.activate();
    }

    public AutoGliderController getAutoGliderController() {
        return this.autoGliderController;
    }

    public BindSetting getBindSetting() {
        return this.bindSetting;
    }

    public TextSetting getAnarchyText() {
        return this.anarchyText;
    }

    public TextSetting getHomeName() {
        return this.homeName;
    }
}
