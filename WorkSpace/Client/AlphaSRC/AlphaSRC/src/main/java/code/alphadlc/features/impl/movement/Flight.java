/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1802
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.movement;

import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Flight
extends Module {
    private static final double GLIDE_FALL = -0.005;
    private final SelectSetting mode = new SelectSetting("\u0422\u0438\u043f", "\u0420\u0435\u0436\u0438\u043c \u043f\u043e\u043b\u0451\u0442\u0430").value("\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435", "\u041f\u0440\u044b\u0436\u043a\u0438", "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "ReallyWorld Dragon", "\u042d\u043b\u0438\u0442\u0440\u0430 Y", "Grim Glide");
    private final SliderSettings speedXZ = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0413\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c").setValue(1.5f).range(0.1f, 10.0f).step(0.1f).visible(() -> !this.mode.isSelected("\u042d\u043b\u0438\u0442\u0440\u0430 Y") && !this.mode.isSelected("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.mode.isSelected("ReallyWorld Dragon") && !this.mode.isSelected("Grim Glide"));
    private final SliderSettings speedY = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", "\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c").setValue(1.5f).range(0.1f, 10.0f).step(0.1f).visible(() -> !this.mode.isSelected("\u042d\u043b\u0438\u0442\u0440\u0430 Y") && !this.mode.isSelected("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.mode.isSelected("ReallyWorld Dragon") && !this.mode.isSelected("Grim Glide"));
    private final SliderSettings elytraYBoost = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", "\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0431\u0443\u0441\u0442 \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u042d\u043b\u0438\u0442\u0440\u0430 Y").setValue(0.1f).range(0.05f, 0.5f).step(0.05f).visible(() -> this.mode.isSelected("\u042d\u043b\u0438\u0442\u0440\u0430 Y"));

    public Flight() {
        super("Flight", "Flight", ModuleCategory.MOVEMENT);
        this.setup(this.mode, this.speedXZ, this.speedY, this.elytraYBoost);
    }

    @EventHandler
    public void onMove(MoveEvent event) {
        if (!this.state || PlayerInteractionHelper.nullCheck()) {
            return;
        }
        boolean sneak = Flight.mc.field_1690.field_1832.method_1434();
        boolean jump = Flight.mc.field_1690.field_1903.method_1434();
        float horizontal = this.speedXZ.getValue();
        float vertical = this.speedY.getValue();
        switch (this.mode.getSelected()) {
            case "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435": {
                this.applyGlide(sneak, jump, vertical, horizontal);
                break;
            }
            case "\u041f\u0440\u044b\u0436\u043a\u0438": {
                this.applyJumpGlide(sneak, jump, vertical, horizontal);
                break;
            }
            case "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435": {
                this.applyMovementGlide(sneak, jump, vertical, horizontal);
                break;
            }
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                if (!jump) break;
                Flight.mc.field_1724.method_6043();
                break;
            }
            case "ReallyWorld Dragon": {
                this.applyDragon(sneak, jump);
                break;
            }
            case "\u042d\u043b\u0438\u0442\u0440\u0430 Y": {
                this.applyElytraY(jump);
                break;
            }
            case "Grim Glide": {
                this.applyGrimGlide(sneak, jump);
                break;
            }
        }
    }

    private void applyGlide(boolean sneak, boolean jump, float verticalSpeed, float horizontalSpeed) {
        double y = -0.005;
        if (sneak) {
            y = -verticalSpeed;
        } else if (jump) {
            y = verticalSpeed;
        }
        if (Flight.mc.field_1724.method_24828()) {
            Flight.mc.field_1724.method_6043();
        }
        this.setHorizontalSpeed(horizontalSpeed);
        class_243 velocity = Flight.mc.field_1724.method_18798();
        Flight.mc.field_1724.method_18800(velocity.field_1352, y, velocity.field_1350);
    }

    private void applyJumpGlide(boolean sneak, boolean jump, float verticalSpeed, float horizontalSpeed) {
        double y = 0.0;
        if (sneak) {
            y = -verticalSpeed;
        } else if (jump) {
            y = verticalSpeed;
        }
        if (Flight.mc.field_1724.method_24828()) {
            Flight.mc.field_1724.method_6043();
        }
        this.setHorizontalSpeed(horizontalSpeed);
        class_243 velocity = Flight.mc.field_1724.method_18798();
        Flight.mc.field_1724.method_18800(velocity.field_1352, y, velocity.field_1350);
    }

    private void applyMovementGlide(boolean sneak, boolean jump, float verticalSpeed, float horizontalSpeed) {
        double y = Flight.mc.field_1724.method_18798().field_1351;
        if (sneak) {
            y = -verticalSpeed;
        } else if (jump) {
            y = verticalSpeed;
        }
        this.setHorizontalSpeed(horizontalSpeed);
        class_243 velocity = Flight.mc.field_1724.method_18798();
        Flight.mc.field_1724.method_18800(velocity.field_1352, y, velocity.field_1350);
    }

    private void applyDragon(boolean sneak, boolean jump) {
        if (!Flight.mc.field_1724.method_31549().field_7479) {
            return;
        }
        Flight.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        boolean grim = Network.isReallyWorld();
        boolean moving = sneak || jump;
        double y = 0.0;
        if (sneak) {
            y = grim ? -0.08 : -1.0;
        } else if (jump) {
            double d = y = grim ? 0.08 : 1.0;
        }
        if (grim) {
            this.setHorizontalSpeed(moving ? 0.08f : 0.04f);
        }
        class_243 velocity = Flight.mc.field_1724.method_18798();
        Flight.mc.field_1724.method_18800(velocity.field_1352, y, velocity.field_1350);
    }

    private void applyElytraY(boolean jump) {
        if (!Flight.mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833) || !Flight.mc.field_1724.method_6128()) {
            return;
        }
        double y = Flight.mc.field_1724.method_18798().field_1351 + (double)this.elytraYBoost.getValue();
        Flight.mc.field_1724.method_18800(0.0, y, 0.0);
        Flight.mc.field_1690.field_1903.method_23481(false);
    }

    private void applyGrimGlide(boolean sneak, boolean jump) {
        if (!Flight.mc.field_1724.method_31549().field_7479) {
            return;
        }
        Flight.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        boolean grim = Network.isReallyWorld();
        boolean moving = sneak || jump;
        double y = 0.0;
        if (sneak) {
            y = grim ? -0.08 : -1.0;
        } else if (jump) {
            double d = y = grim ? 0.08 : 1.0;
        }
        if (grim) {
            this.setHorizontalSpeed(moving ? 0.08f : 0.04f);
        }
        class_243 velocity = Flight.mc.field_1724.method_18798();
        Flight.mc.field_1724.method_18800(velocity.field_1352, y, velocity.field_1350);
    }

    private void setHorizontalSpeed(float speed) {
        float yaw = Flight.mc.field_1724.method_36454();
        float forward = Flight.mc.field_1724.field_3913.field_3905;
        float strafe = Flight.mc.field_1724.field_3913.field_3907;
        if (forward == 0.0f && strafe == 0.0f) {
            return;
        }
        float yawRad = yaw * ((float)Math.PI / 180);
        double x = -class_3532.method_15374((float)yawRad) * speed * forward + class_3532.method_15362((float)yawRad) * speed * strafe;
        double z = class_3532.method_15362((float)yawRad) * speed * forward + class_3532.method_15374((float)yawRad) * speed * strafe;
        Flight.mc.field_1724.method_18800(x, Flight.mc.field_1724.method_18798().field_1351, z);
    }
}
