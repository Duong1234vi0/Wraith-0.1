/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.TextSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

public class AutoAuth
extends Module {
    private final TextSetting password = new TextSetting("\u041f\u0430\u0440\u043e\u043b\u044c", "\u041f\u0430\u0440\u043e\u043b\u044c \u0434\u043b\u044f \u0430\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u043e\u0439 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438/\u0432\u0445\u043e\u0434\u0430").setText("bee1892");

    public AutoAuth() {
        super("AutoAuth", "AutoAuth", ModuleCategory.MISC);
        this.setup(this.password);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        if (!this.state || AutoAuth.mc.field_1724 == null || AutoAuth.mc.field_1687 == null) {
            return;
        }
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_7439) {
            class_7439 chatPacket = (class_7439)class_25962;
            String chatMessage = chatPacket.comp_763().getString();
            String pass = this.password.getText();
            if ((chatMessage.contains("\u0412\u043e\u0439\u0434\u0438\u0442\u0435") || chatMessage.contains("/login")) && AutoAuth.mc.field_1724.field_3944 != null) {
                AutoAuth.mc.field_1724.field_3944.method_45730("login " + pass);
            }
            if ((chatMessage.contains("\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u0443\u0439\u0442\u0435\u0441\u044c") || chatMessage.contains("/reg")) && pass != null && pass.length() >= 4 && AutoAuth.mc.field_1724.field_3944 != null) {
                AutoAuth.mc.field_1724.field_3944.method_45730("reg " + pass + " " + pass);
            }
        }
    }
}
