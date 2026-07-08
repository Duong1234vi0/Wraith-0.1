/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_2596
 *  net.minecraft.class_2708
 *  net.minecraft.class_2743
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_6373
 */
package code.alphadlc.features.impl.combat;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.lang.reflect.Field;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_6373;

public class Velocity
extends Module {
    private final RadioSetting mode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c \u0443\u043c\u0435\u043d\u044c\u0448\u0435\u043d\u0438\u044f \u043e\u0442\u0434\u0430\u0447\u0438", new String[]{"\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c", "\u0421\u0442\u0430\u0440\u044b\u0439 \u0413\u0440\u0438\u043c", "\u041c\u0430\u0442\u0440\u0438\u043a\u0441", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439"}, "\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c");
    private boolean flag;
    private int grimTicks;
    private int ccCooldown;

    public static Velocity getInstance() {
        return Instance.get(Velocity.class);
    }

    public Velocity() {
        super("Velocity", ModuleCategory.COMBAT);
        this.setup(this.mode);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        String modeVal;
        class_2743 pac;
        if (!this.state) {
            return;
        }
        if (e.getType() != PacketEvent.Type.RECEIVE) {
            return;
        }
        if (Velocity.mc.field_1724 == null || Velocity.mc.field_1724.method_5799() || Velocity.mc.field_1724.method_5869() || Velocity.mc.field_1724.method_5771()) {
            return;
        }
        if (this.ccCooldown > 0) {
            --this.ccCooldown;
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_2743 && (pac = (class_2743)class_25962).method_11818() == Velocity.mc.field_1724.method_5628()) {
            String modeVal2;
            switch (modeVal2 = this.mode.get()) {
                case "\u041c\u0430\u0442\u0440\u0438\u043a\u0441": {
                    if (!this.flag) {
                        e.setCancelled(true);
                        this.flag = true;
                        break;
                    }
                    this.flag = false;
                    this.setVelocityX(pac, (int)(pac.method_11815() * -0.1));
                    this.setVelocityZ(pac, (int)(pac.method_11819() * -0.1));
                    break;
                }
                case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                    e.setCancelled(true);
                    break;
                }
                case "\u0421\u0442\u0430\u0440\u044b\u0439 \u0413\u0440\u0438\u043c": {
                    e.setCancelled(true);
                    this.grimTicks = 6;
                    break;
                }
                case "\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c": {
                    e.setCancelled(true);
                    this.flag = true;
                }
            }
        }
        if ((modeVal = this.mode.get()).equals("\u0421\u0442\u0430\u0440\u044b\u0439 \u0413\u0440\u0438\u043c") && e.getPacket() instanceof class_6373 && this.grimTicks > 0) {
            e.setCancelled(true);
            --this.grimTicks;
        }
        if (e.getPacket() instanceof class_2708 && modeVal.equals("\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c")) {
            this.ccCooldown = 5;
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (!this.state || Velocity.mc.field_1724 == null || Velocity.mc.field_1724.method_5799() || Velocity.mc.field_1724.method_5869()) {
            return;
        }
        String modeVal = this.mode.get();
        if (modeVal.equals("\u041c\u0430\u0442\u0440\u0438\u043a\u0441") && Velocity.mc.field_1724.field_6235 > 0 && !Velocity.mc.field_1724.method_24828()) {
            double var3 = Velocity.mc.field_1724.method_36454() * ((float)Math.PI / 180);
            double var5 = Math.sqrt(Velocity.mc.field_1724.method_18798().field_1352 * Velocity.mc.field_1724.method_18798().field_1352 + Velocity.mc.field_1724.method_18798().field_1350 * Velocity.mc.field_1724.method_18798().field_1350);
            Velocity.mc.field_1724.method_18800(-Math.sin(var3) * var5, Velocity.mc.field_1724.method_18798().field_1351, Math.cos(var3) * var5);
            Velocity.mc.field_1724.method_5728(Velocity.mc.field_1724.field_6012 % 2 != 0);
        }
        if (modeVal.equals("\u041d\u043e\u0432\u044b\u0439 \u0413\u0440\u0438\u043c") && this.flag) {
            if (this.ccCooldown <= 0) {
                Velocity.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2830(Velocity.mc.field_1724.method_23317(), Velocity.mc.field_1724.method_23318(), Velocity.mc.field_1724.method_23321(), Velocity.mc.field_1724.method_36454(), Velocity.mc.field_1724.method_36455(), Velocity.mc.field_1724.method_24828(), false));
                Velocity.mc.field_1724.field_3944.method_52787((class_2596)new class_2846(class_2846.class_2847.field_12973, class_2338.method_49638((class_2374)Velocity.mc.field_1724.method_19538()), class_2350.field_11033));
            }
            this.flag = false;
        }
        if (this.grimTicks > 0) {
            --this.grimTicks;
        }
    }

    @Override
    public void activate() {
        super.activate();
        this.grimTicks = 0;
        this.flag = false;
        this.ccCooldown = 0;
    }

    private void setVelocityX(class_2743 packet, int value) {
        try {
            Field field = class_2743.class.getDeclaredField("velocityX");
            field.setAccessible(true);
            field.setInt(packet, value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setVelocityZ(class_2743 packet, int value) {
        try {
            Field field = class_2743.class.getDeclaredField("velocityZ");
            field.setAccessible(true);
            field.setInt(packet, value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setGrimTicks(int grimTicks) {
        this.grimTicks = grimTicks;
    }

    public void setCcCooldown(int ccCooldown) {
        this.ccCooldown = ccCooldown;
    }

    public RadioSetting getMode() {
        return this.mode;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public int getGrimTicks() {
        return this.grimTicks;
    }

    public int getCcCooldown() {
        return this.ccCooldown;
    }
}
