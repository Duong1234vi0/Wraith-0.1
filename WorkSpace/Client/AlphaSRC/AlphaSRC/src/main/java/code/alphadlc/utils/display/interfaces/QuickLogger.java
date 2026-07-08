/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_5250
 */
package code.alphadlc.utils.display.interfaces;

import code.alphadlc.utils.client.chat.ChatMessage;
import java.util.Arrays;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_5250;

public interface QuickLogger {
    public static class_2561 getPrefix() {
        class_5250 text = ChatMessage.brandmessage();
        text.method_10862(text.method_10866().method_10977(class_124.field_1080));
        return text;
    }

    default public void logDirect(class_2561 ... components) {
        class_5250 component = class_2561.method_43470((String)"");
        component.method_10852(QuickLogger.getPrefix());
        component.method_10852((class_2561)class_2561.method_43470((String)" "));
        Arrays.asList(components).forEach(arg_0 -> ((class_5250)component).method_10852(arg_0));
        if (class_310.method_1551().field_1724 != null) {
            class_310.method_1551().field_1705.method_1743().method_1812((class_2561)component);
        }
    }

    default public void logDirect(String message, class_124 color) {
        Stream.of(message.split("\n")).forEach(line -> {
            class_5250 component = class_2561.method_43470((String)line.replace("\t", "    "));
            component.method_10862(component.method_10866().method_10977(color));
            this.logDirect(new class_2561[]{component});
        });
    }

    default public void logDirect(String message) {
        this.logDirect(message, class_124.field_1068);
    }
}
