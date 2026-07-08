/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_2371
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2645
 *  net.minecraft.class_2678
 *  net.minecraft.class_2724
 *  net.minecraft.class_2815
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_3944
 *  net.minecraft.class_437
 *  net.minecraft.class_465
 *  net.minecraft.class_476
 *  net.minecraft.class_490
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.container.CloseScreenEvent;
import code.alphadlc.events.container.SetScreenEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import java.lang.runtime.SwitchBootstraps;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2815;
import net.minecraft.class_2846;
import net.minecraft.class_3944;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_476;
import net.minecraft.class_490;

public class EnderChestPlus
extends Module {
    private class_465<?> screen;
    private final BindSetting bindSetting = new BindSetting("\u0421\u043b\u043e\u0436\u0438\u0442\u044c", "\u041f\u043e\u043c\u0435\u0449\u0430\u0435\u0442 \u0432\u0441\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0432 \u044d\u043d\u0434\u0435\u0440-\u0441\u0443\u043d\u0434\u0443\u043a");

    public EnderChestPlus() {
        super("EnderChestPlus", "Ender-ChestPlus", ModuleCategory.PLAYER);
        this.setup(this.bindSetting);
    }

    @Override
    public void deactivate() {
        if (this.screen != null) {
            this.screen = null;
            InventoryTask.closeScreen(true);
            Notifications.getInstance().addList("Ender Chest - " + String.valueOf(class_124.field_1061) + "\u0437\u0430\u043a\u0440\u044b\u0442", 5000);
        }
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        if (e.isKeyDown(this.bindSetting.getKey()) && this.screen != null) {
            class_2371 slots = EnderChestPlus.mc.field_1724.field_7512.field_7761;
            slots.stream().filter(arg_0 -> EnderChestPlus.lambda$onKey$0((List)slots, arg_0)).findFirst().ifPresent(s -> InventoryTask.swapHand(s, class_1268.field_5810, false));
            slots.stream().filter(arg_0 -> EnderChestPlus.lambda$onKey$2((List)slots, arg_0)).forEach(s -> InventoryTask.clickSlot(s, 0, class_1713.field_7794, false));
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (this.screen != null && EnderChestPlus.mc.field_1724 != null) {
            class_2596<?> class_25962 = e.getPacket();
            Objects.requireNonNull(class_25962);
            class_2596<?> class_25963 = class_25962;
            int n = 0;
            block8: while (true) {
                switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2678.class, class_3944.class, class_2645.class, class_2724.class, class_2815.class, class_2846.class}, class_25963, n)) {
                    case 0: {
                        class_2678 join = (class_2678)class_25963;
                        this.deactivate();
                        break block8;
                    }
                    case 1: {
                        class_3944 open = (class_3944)class_25963;
                        this.deactivate();
                        break block8;
                    }
                    case 2: {
                        class_2645 close = (class_2645)class_25963;
                        this.deactivate();
                        break block8;
                    }
                    case 3: {
                        class_2724 respawn = (class_2724)class_25963;
                        this.deactivate();
                        break block8;
                    }
                    case 4: {
                        class_2815 close = (class_2815)class_25963;
                        e.cancel();
                        break block8;
                    }
                    case 5: {
                        class_2846 player = (class_2846)class_25963;
                        if (!player.method_12363().equals((Object)class_2846.class_2847.field_12969)) {
                            n = 6;
                            continue block8;
                        }
                        InventoryTask.swapHand(InventoryTask.mainHandSlot(), class_1268.field_5810, false);
                        e.cancel();
                        break block8;
                    }
                }
                break;
            }
        }
    }

    @EventHandler
    public void onSetScreen(SetScreenEvent e) {
        if (e.getScreen() instanceof class_490 && this.screen != null) {
            e.setScreen((class_437)this.screen);
        }
    }

    @EventHandler
    public void onCloseScreen(CloseScreenEvent e) {
        class_476 scr;
        class_437 class_4372 = e.getScreen();
        if (class_4372 instanceof class_476 && (scr = (class_476)class_4372).method_25440().getString().contains(class_2561.method_43471((String)"container.enderchest").getString())) {
            this.screen = scr;
        }
        if (this.screen != null) {
            mc.method_1507(null);
            e.cancel();
        }
    }

    private static /* synthetic */ boolean lambda$onKey$2(List slots, class_1735 s) {
        return s.field_7874 >= slots.size() - 36 && !s.method_7677().method_7960();
    }

    private static /* synthetic */ boolean lambda$onKey$0(List slots, class_1735 s) {
        return s.field_7874 < slots.size() - 36 && s.method_7677().method_7960();
    }
}
