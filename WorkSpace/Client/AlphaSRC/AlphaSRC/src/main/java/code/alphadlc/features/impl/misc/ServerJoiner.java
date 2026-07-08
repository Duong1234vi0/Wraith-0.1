/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2661
 *  net.minecraft.class_2886
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2661;
import net.minecraft.class_2886;
import net.minecraft.class_437;
import net.minecraft.class_476;
import org.lwjgl.glfw.GLFW;

public class ServerJoiner
extends Module {
    private final SelectSetting serverSelection = new SelectSetting("\u0421\u0435\u0440\u0432\u0435\u0440", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0446\u0435\u043b\u0435\u0432\u043e\u0439 \u0441\u0435\u0440\u0432\u0435\u0440").value("\u0420\u0438\u043b\u043b\u0438\u0412\u043e\u0440\u043b\u0434", "\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438").selected("\u0420\u0438\u043b\u043b\u0438\u0412\u043e\u0440\u043b\u0434");
    private final SliderSettings griefSelection = new SliderSettings("\u041d\u043e\u043c\u0435\u0440 \u0433\u0440\u0438\u0444\u0430", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u043d\u043e\u043c\u0435\u0440 \u0441\u0435\u0440\u0432\u0435\u0440\u0430 \u0434\u043b\u044f \u0433\u0440\u0438\u0444\u0430").setValue(1.0f).range(1, 54).visible(() -> this.serverSelection.isSelected("\u0420\u0438\u043b\u043b\u0438\u0412\u043e\u0440\u043b\u0434"));
    private final TextSetting spookyCompassName = new TextSetting("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043a\u043e\u043c\u043f\u0430\u0441\u0430", "\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043a\u043e\u043c\u043f\u0430\u0441\u0430 \u0434\u043b\u044f SpookyTime").setText("\u0432\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c").setMin(1).setMax(64).visible(() -> this.serverSelection.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438"));
    private long lastActionTime;
    private boolean isToggling;
    private boolean retryDuels;

    public ServerJoiner() {
        super("ServerJoiner", "ServerJoiner", ModuleCategory.MISC);
        this.setup(this.serverSelection, this.griefSelection, this.spookyCompassName);
    }

    public static ServerJoiner getInstance() {
        return Instance.get(ServerJoiner.class);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.state) {
            return;
        }
        if (GLFW.glfwGetKey((long)mc.method_22683().method_4490(), (int)260) == 1) {
            this.setState(false);
            return;
        }
        if (ServerJoiner.mc.field_1755 == null && ServerJoiner.mc.field_1724 != null) {
            if (this.serverSelection.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438")) {
                if (System.currentTimeMillis() - this.lastActionTime > 250L) {
                    this.useSpookyCompassOrDisable();
                }
            } else if (ServerJoiner.mc.field_1724.field_6012 < 5) {
                InventoryTask.selectCompass();
                ServerJoiner.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, ServerJoiner.mc.field_1724.method_31548().field_7545, ServerJoiner.mc.field_1724.method_36454(), ServerJoiner.mc.field_1724.method_36455()));
            }
            return;
        }
        class_437 class_4372 = ServerJoiner.mc.field_1755;
        if (!(class_4372 instanceof class_476)) {
            return;
        }
        class_476 container = (class_476)class_4372;
        for (int i = 0; i < ((class_1707)container.method_17577()).field_7761.size(); ++i) {
            String title;
            String slotName = ((class_1735)((class_1707)container.method_17577()).field_7761.get(i)).method_7677().method_7964().getString().toLowerCase();
            if (this.serverSelection.isSelected("\u0420\u0438\u043b\u043b\u0438\u0412\u043e\u0440\u043b\u0434") && Network.isReallyWorld()) {
                int numberGrief;
                if (slotName.contains("\u0433\u0440\u0438\u0444\u0435\u0440\u0441\u043a\u043e\u0435 \u0432\u044b\u0436\u0438\u0432\u0430\u043d\u0438\u0435") && this.canClick()) {
                    InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                    this.lastActionTime = System.currentTimeMillis();
                }
                if (!slotName.contains("\u0433\u0440\u0438\u0444 #" + (numberGrief = (int)this.griefSelection.getValue())) || !this.canClick()) continue;
                InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                this.lastActionTime = System.currentTimeMillis();
                continue;
            }
            if (!this.serverSelection.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438") || !(title = container.method_25440().getString().toLowerCase()).contains("\u0432\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0440\u0435\u0436\u0438\u043c") || !this.canClick()) continue;
            InventoryTask.clickSlot(14, 0, class_1713.field_7794, false);
            this.lastActionTime = System.currentTimeMillis();
        }
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_2661 packet;
        String message;
        if (!this.state || event.getType() != PacketEvent.Type.RECEIVE) {
            return;
        }
        class_2596<?> class_25962 = event.getPacket();
        if (class_25962 instanceof class_2661 && ((message = (packet = (class_2661)class_25962).comp_2325().getString().toLowerCase()).contains("\u043a \u0441\u043e\u0436\u0430\u043b\u0435\u043d\u0438\u044e \u0441\u0435\u0440\u0432\u0435\u0440 \u043f\u0435\u0440\u0435\u043f\u043e\u043b\u043d\u0435\u043d") || message.contains("\u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435 20 \u0441\u0435\u043a\u0443\u043d\u0434!") || message.contains("\u0432\u044b \u0443\u0436\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u044b \u043d\u0430 \u044d\u0442\u043e\u0442 \u0441\u0435\u0440\u0432\u0435\u0440!") || message.contains("\u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435 \u043d\u0435\u0441\u043a\u043e\u043b\u044c\u043a\u043e \u0441\u0435\u043a\u0443\u043d\u0434 \u043f\u0435\u0440\u0435\u0434 \u043f\u043e\u0432\u0442\u043e\u0440\u043d\u044b\u043c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435\u043c!") || message.contains("\u0432\u044b \u0431\u044b\u043b\u0438 \u043a\u0438\u043a\u043d\u0443\u0442\u044b \u0441 \u0441\u0435\u0440\u0432\u0435\u0440\u0430 1duels:") || message.contains("\u0432\u044b \u0431\u044b\u043b\u0438 \u043a\u0438\u043a\u043d\u0443\u0442\u044b") || message.contains("\u0431\u043e\u043b\u044c\u0448\u043e\u0439 \u043f\u043e\u0442\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432") || message.contains("\u0441\u0435\u0440\u0432\u0435\u0440 \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d!"))) {
            if (this.serverSelection.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438")) {
                this.useSpookyCompassOrDisable();
            } else {
                InventoryTask.selectCompass();
                ServerJoiner.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, ServerJoiner.mc.field_1724.method_31548().field_7545, ServerJoiner.mc.field_1724.method_36454(), ServerJoiner.mc.field_1724.method_36455()));
            }
        }
    }

    @Override
    public void activate() {
        super.activate();
        this.isToggling = false;
        this.retryDuels = false;
        this.lastActionTime = 0L;
        if (this.serverSelection.isSelected("\u0421\u043f\u0443\u043a\u0438\u0422\u0430\u0439\u043c \u0434\u0443\u044d\u043b\u0438")) {
            this.useSpookyCompassOrDisable();
        } else if (ServerJoiner.mc.field_1724 != null && ServerJoiner.mc.field_1724.field_3944 != null) {
            InventoryTask.selectCompass();
            ServerJoiner.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, ServerJoiner.mc.field_1724.method_31548().field_7545, ServerJoiner.mc.field_1724.method_36454(), ServerJoiner.mc.field_1724.method_36455()));
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.lastActionTime = 0L;
        this.isToggling = false;
        this.retryDuels = false;
    }

    private boolean canClick() {
        return System.currentTimeMillis() - this.lastActionTime > 50L;
    }

    private void useSpookyCompassOrDisable() {
        if (ServerJoiner.mc.field_1724 == null || ServerJoiner.mc.field_1724.field_3944 == null) {
            return;
        }
        class_1735 compassSlot = this.findSpookyCompassSlot();
        if (compassSlot == null) {
            Notifications.getInstance().addList("\u041a\u043e\u043c\u043f\u0430\u0441 \u0441 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435\u043c " + this.spookyCompassName.getText() + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", 2500);
            this.setState(false);
            return;
        }
        InventoryTask.swapHand(compassSlot, class_1268.field_5808, false, true);
        ServerJoiner.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, ServerJoiner.mc.field_1724.method_31548().field_7545, ServerJoiner.mc.field_1724.method_36454(), ServerJoiner.mc.field_1724.method_36455()));
        this.lastActionTime = System.currentTimeMillis();
    }

    private class_1735 findSpookyCompassSlot() {
        String searchName = ServerJoiner.normalize(this.spookyCompassName.getText());
        return InventoryTask.getSlot(slot -> {
            if (!slot.method_7677().method_31574(class_1802.field_8251)) {
                return false;
            }
            if (searchName.isEmpty()) {
                return true;
            }
            return ServerJoiner.normalize(InventoryTask.getCleanName(slot.method_7677().method_7964())).contains(searchName);
        });
    }

    private static String normalize(String text) {
        return text == null ? "" : text.toLowerCase().trim();
    }
}
