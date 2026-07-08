/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2678
 *  net.minecraft.class_2724
 *  net.minecraft.class_2828
 *  net.minecraft.class_5498
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.MoveEvent;
import code.alphadlc.events.render.CameraPositionEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.math.calc.Calculate;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2828;
import net.minecraft.class_5498;

public class FreeCam
extends Module {
    private final SliderSettings speedSetting = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043a\u0430\u043c\u0435\u0440\u044b \u043e\u0442\u043b\u0430\u0434\u043a\u0438").setValue(2.0f).range(0.5f, 5.0f);
    private final BooleanSetting freezeSetting = new BooleanSetting("\u041e\u0442\u043c\u0435\u043d\u044f\u0442\u044c \u043f\u0430\u043a\u0435\u0442", "\u0412\u044b \u0437\u0430\u043c\u043e\u0440\u0430\u0436\u0438\u0432\u0430\u0435\u0442\u0435\u0441\u044c \u043d\u0430 \u043c\u0435\u0441\u0442\u0435").setValue(false);
    public class_243 pos;
    public class_243 prevPos;

    public static FreeCam getInstance() {
        return Instance.get(FreeCam.class);
    }

    public FreeCam() {
        super("FreeCam", "FreeCam", ModuleCategory.MISC);
        this.setup(this.speedSetting, this.freezeSetting);
    }

    @Override
    public void activate() {
        this.prevPos = this.pos = new class_243(FreeCam.mc.method_1561().field_4686.method_19326().method_46409());
        super.activate();
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        block5: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2828.class, class_2724.class, class_2678.class}, class_25963, n)) {
                case 0: {
                    class_2828 move = (class_2828)class_25963;
                    if (!this.freezeSetting.isValue()) {
                        n = 1;
                        continue block5;
                    }
                    e.cancel();
                    break block5;
                }
                case 1: {
                    class_2724 respawn = (class_2724)class_25963;
                    this.setState(false);
                    break block5;
                }
                case 2: {
                    class_2678 join = (class_2678)class_25963;
                    this.setState(false);
                    break block5;
                }
            }
            break;
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        Render3D.drawBox(FreeCam.mc.field_1724.method_5829().method_997(Calculate.interpolate((class_1297)FreeCam.mc.field_1724).method_1020(FreeCam.mc.field_1724.method_19538())), ColorAssist.getClientColor(), 1.0f);
    }

    @EventHandler
    public void onMove(MoveEvent e) {
        if (this.freezeSetting.isValue()) {
            e.setMovement(class_243.field_1353);
        }
    }

    @EventHandler
    public void onInput(InputEvent e) {
        float speed = this.speedSetting.getValue();
        double[] motion = Simulations.calculateDirection(e.forward(), e.sideways(), speed);
        this.prevPos = this.pos;
        this.pos = this.pos.method_1031(motion[0], e.getInput().comp_3163() ? (double)speed : (e.getInput().comp_3164() ? (double)(-speed) : 0.0), motion[1]);
        e.inputNone();
    }

    @EventHandler
    public void onCameraPosition(CameraPositionEvent e) {
        e.setPos(Calculate.interpolate(this.prevPos, this.pos));
        FreeCam.mc.field_1690.method_31043(class_5498.field_26664);
    }
}
