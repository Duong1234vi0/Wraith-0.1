package net.ccbluex.liquidbounce.injection.mixins.minecraft.text;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(MutableComponent.class)
public interface MixinMutableTextAccessor {

    /**
     * @param siblings should be mutable list, unless you are sure it will not be modified!
     */
    @Invoker("<init>")
    static MutableComponent create(ComponentContents content, List<Component> siblings, Style style) {
        throw new AssertionError();
    }

}
