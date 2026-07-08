/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.math.task.TaskPriority;
import net.minecraft.class_310;

public class Strafe
extends Module {
    private static final class_310 mc = class_310.method_1551();
    public SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0438\u043f \u0441\u0442\u0440\u0435\u0439\u0444\u043e\u0432").value("\u041c\u0430\u0442\u0440\u0438\u043a\u0441", "\u0413\u0440\u0438\u043c").selected("\u041c\u0430\u0442\u0440\u0438\u043a\u0441");
    SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u043b\u044f \u0441\u0442\u0440\u0435\u0439\u0444\u0430").setValue(0.42f).range(0.0f, 1.0f);
    private float lastYaw;
    private float lastPitch;
    private final Turns rot = new Turns(0.0f, 0.0f);

    public Strafe() {
        super("Strafe", "Strafe", ModuleCategory.MOVEMENT);
        this.setup(this.mode, this.speed);
    }

    public static Strafe getInstance() {
        return Instance.get(Strafe.class);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (Strafe.mc.field_1724 == null || Strafe.mc.field_1687 == null) {
            return;
        }
        boolean moving = Simulations.hasPlayerMovement();
        float yaw = Strafe.mc.field_1724.method_36454();
        String modeVal = this.mode.getSelected();
        if (modeVal.equals("\u041c\u0430\u0442\u0440\u0438\u043a\u0441")) {
            if (moving) {
                yaw = Simulations.moveYaw(Strafe.mc.field_1724.method_36454());
                double motion = this.speed.getValue() * 1.5f;
                Simulations.setVelocity(motion);
            } else {
                Simulations.setVelocity(0.0);
            }
            Strafe.mc.field_1724.method_18800(Strafe.mc.field_1724.method_18798().field_1352, Strafe.mc.field_1724.method_18798().field_1351, Strafe.mc.field_1724.method_18798().field_1350);
        } else if (modeVal.equals("\u0413\u0440\u0438\u043c") && moving) {
            TurnsConfig.freeCorrection = true;
            yaw = Simulations.moveYaw(Strafe.mc.field_1724.method_36454());
            this.rot.setYaw(yaw);
            this.rot.setPitch(Strafe.mc.field_1724.method_36455());
            TurnsConnection.INSTANCE.rotateTo(this.rot, TurnsConfig.DEFAULT, TaskPriority.LOW_PRIORITY, this);
        }
        this.lastYaw = yaw;
        this.lastPitch = 0.0f;
    }

    @Override
    public void activate() {
        super.activate();
        this.lastYaw = Strafe.mc.field_1724 != null ? Strafe.mc.field_1724.method_36454() : 0.0f;
        this.lastPitch = Strafe.mc.field_1724 != null ? Strafe.mc.field_1724.method_36455() : 0.0f;
    }
}
