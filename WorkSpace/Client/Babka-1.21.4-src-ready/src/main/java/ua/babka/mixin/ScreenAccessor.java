package ua.babka.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Screen.class})
public interface ScreenAccessor {
    @Invoker(value="addDrawableChild")
    public <T extends Element & Drawable> T invokeAddDrawableChild(T var1);
}

