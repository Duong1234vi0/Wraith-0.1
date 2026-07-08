/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.class_124;
import net.minecraft.class_2561;

public class Panic
extends Module {
    private static boolean panicMode = false;
    private static String recoveryCode = "";
    private boolean executed = false;
    private final Map<String, Boolean> savedModuleStates = new HashMap<String, Boolean>();
    private boolean countdownActive = false;
    private int countdownValue = 5;
    private long lastCountdownTime = 0L;
    private boolean codeShown = false;

    public Panic() {
        super("Panic", ModuleCategory.MISC);
        AlphaDLC.getInstance().getEventManager();
        EventManager.register(this);
    }

    public static boolean isPanicMode() {
        return panicMode;
    }

    public static String getRecoveryCode() {
        return recoveryCode;
    }

    @Override
    public void activate() {
        this.executed = false;
        this.codeShown = false;
        this.countdownActive = false;
        this.countdownValue = 5;
    }

    @EventHandler
    public void onTick(TickEvent event) {
        long currentTime;
        if (this.isState() && !this.executed && Panic.mc.field_1724 != null && Panic.mc.field_1687 != null) {
            this.startPanicSequence();
            this.executed = true;
        }
        if (this.countdownActive && Panic.mc.field_1724 != null && (currentTime = System.currentTimeMillis()) - this.lastCountdownTime >= 1000L) {
            this.lastCountdownTime = currentTime;
            if (this.countdownValue > 0) {
                Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1067) + this.countdownValue)), false);
                --this.countdownValue;
            } else {
                this.countdownActive = false;
                this.activatePanicMode();
            }
        }
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        if (panicMode && event.getMessage().equals(recoveryCode)) {
            event.cancel();
            this.restoreClient();
        }
    }

    private void startPanicSequence() {
        recoveryCode = String.format("%06d", new Random().nextInt(1000000));
        this.savedModuleStates.clear();
        AlphaDLC.getInstance().getModuleRepository().modules().forEach(module -> this.savedModuleStates.put(module.getName(), module.isState()));
        if (Panic.mc.field_1724 != null) {
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)""), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1065) + String.valueOf(class_124.field_1067) + "\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1067) + "PANIC MODE ACTIVATED")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)""), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1054) + "\u041a\u043e\u0434 \u0432\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f: " + String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1067) + recoveryCode)), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1080) + "\u0417\u0430\u043f\u043e\u043c\u043d\u0438\u0442\u0435 \u043a\u043e\u0434 \u0434\u043b\u044f \u0432\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f!")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1065) + String.valueOf(class_124.field_1067) + "\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)""), false);
        }
        this.codeShown = true;
        this.countdownActive = true;
        this.countdownValue = 5;
        this.lastCountdownTime = System.currentTimeMillis();
    }

    private void activatePanicMode() {
        panicMode = true;
        AlphaDLC.getInstance().getModuleRepository().modules().forEach(module -> {
            if (module.isState() && !(module instanceof Panic)) {
                module.setState(false);
            }
        });
        if (Panic.mc.field_1705 != null && Panic.mc.field_1705.method_1743() != null) {
            Panic.mc.field_1705.method_1743().method_1808(false);
        }
        this.setState(false);
    }

    private void restoreClient() {
        panicMode = false;
        recoveryCode = "";
        if (Panic.mc.field_1724 != null) {
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)""), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1067) + "\u2713 \u041a\u043e\u0434 \u043f\u0440\u0438\u043d\u044f\u0442!")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)(String.valueOf(class_124.field_1054) + "\u041a\u043b\u0438\u0435\u043d\u0442 \u0432\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d!")), false);
            Panic.mc.field_1724.method_7353((class_2561)class_2561.method_43470((String)""), false);
        }
        AlphaDLC.getInstance().getModuleRepository().modules().forEach(module -> {
            Boolean savedState = this.savedModuleStates.get(module.getName());
            if (savedState != null && savedState.booleanValue() != module.isState()) {
                module.setState(savedState);
            }
        });
        this.savedModuleStates.clear();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.countdownActive = false;
        this.countdownValue = 5;
        this.executed = false;
        this.codeShown = false;
    }

    @Override
    public void setState(boolean state) {
        if (state != this.state) {
            this.state = state;
            if (state) {
                this.activate();
            } else {
                this.deactivate();
            }
        }
    }
}
