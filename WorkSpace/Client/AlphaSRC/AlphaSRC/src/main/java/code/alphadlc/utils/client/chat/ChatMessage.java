/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_303
 *  net.minecraft.class_310
 *  net.minecraft.class_338
 *  net.minecraft.class_5250
 *  net.minecraft.class_7591
 */
package code.alphadlc.utils.client.chat;

import code.alphadlc.mixins.acc.InsertChatMixin;
import code.alphadlc.utils.client.text.TextHelper;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_303;
import net.minecraft.class_310;
import net.minecraft.class_338;
import net.minecraft.class_5250;
import net.minecraft.class_7591;

public class ChatMessage {
    public static class_5250 brandmessage() {
        class_5250 AlphaDLC = (class_5250)TextHelper.applyPredefinedGradient("AlphaDLC", "blue", true);
        class_5250 arrow = class_2561.method_43470((String)" > ").method_10862(class_2583.field_24360.method_10977(class_124.field_1063));
        return AlphaDLC.method_10852((class_2561)arrow);
    }

    public static void send(class_2561 message) {
        if (QuickImports.mc.field_1705 == null || QuickImports.mc.field_1705.method_1743() == null) {
            return;
        }
        class_2561 prefix = TextHelper.applyPredefinedGradient("AlphaDLC", "blue", true);
        class_5250 arrow = class_2561.method_43470((String)" >").method_10862(class_2583.field_24360.method_10977(class_124.field_1063));
        class_5250 fullMessage = prefix.method_27661().method_10852((class_2561)arrow).method_27693(" ").method_10852(message);
        int tick = QuickImports.mc.field_1705.method_1738();
        class_303 line = new class_303(tick, (class_2561)fullMessage, null, class_7591.method_44751());
        class_338 chatHud = QuickImports.mc.field_1705.method_1743();
        ((InsertChatMixin)chatHud).invokeAddMessage(line);
        ((InsertChatMixin)chatHud).invokeAddVisibleMessage(line);
    }

    public static void brandmessage(String message) {
        if (class_310.method_1551().field_1724 != null) {
            class_5250 AlphaDLC = (class_5250)TextHelper.applyPredefinedGradient("AlphaDLC", "blue", true);
            class_5250 arrow = class_2561.method_43470((String)" > ").method_10862(class_2583.field_24360.method_10977(class_124.field_1063));
            class_5250 formattedMessage = AlphaDLC.method_10852((class_2561)arrow).method_10852((class_2561)class_2561.method_43470((String)message));
            class_310.method_1551().field_1724.method_7353((class_2561)formattedMessage, false);
        }
    }

    public static void brandmessage(class_2561 message) {
        if (class_310.method_1551().field_1724 != null) {
            class_5250 AlphaDLC = (class_5250)TextHelper.applyPredefinedGradient("AlphaDLC", "blue", true);
            class_5250 arrow = class_2561.method_43470((String)" > ").method_10862(class_2583.field_24360.method_10977(class_124.field_1063));
            class_5250 formattedMessage = AlphaDLC.method_10852((class_2561)arrow).method_10852(message);
            class_310.method_1551().field_1724.method_7353((class_2561)formattedMessage, false);
        }
    }
}
