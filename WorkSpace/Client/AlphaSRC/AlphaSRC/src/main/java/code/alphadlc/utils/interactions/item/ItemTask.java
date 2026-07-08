/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1839
 */
package code.alphadlc.utils.interactions.item;

import code.alphadlc.display.hud.CoolDowns;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1839;

public final class ItemTask
implements QuickImports {
    public static int maxUseTick(class_1792 item) {
        return ItemTask.maxUseTick(item.method_7854());
    }

    public static int maxUseTick(class_1799 stack) {
        return switch (stack.method_7976()) {
            case class_1839.field_8950, class_1839.field_8946 -> 32;
            case class_1839.field_8947, class_1839.field_8951 -> 10;
            case class_1839.field_8953 -> 20;
            case class_1839.field_8949 -> 0;
            default -> stack.method_7935((class_1309)ItemTask.mc.field_1724);
        };
    }

    public static float getCooldownProgress(class_1792 item) {
        CoolDowns coolDowns = CoolDowns.getInstance();
        if (coolDowns != null) {
            return coolDowns.list.stream().filter(c -> c.item().equals(item)).findFirst().map(coolDown -> {
                if (ItemTask.mc.field_1724.method_7357().method_7904(item.method_7854())) {
                    return Float.valueOf((float)((double)(-coolDown.time().elapsedTime()) / 1000.0));
                }
                return Float.valueOf(0.0f);
            }).orElse(Float.valueOf(0.0f)).floatValue();
        }
        return 0.0f;
    }

    private ItemTask() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
