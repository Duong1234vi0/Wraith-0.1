/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1738
 *  net.minecraft.class_1741
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_8051
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.display.interfaces.IArmorItem;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1792;
import net.minecraft.class_8051;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1738.class})
public abstract class ArmorItemMixin
implements IArmorItem {
    @Unique
    private class_1741 armorMaterial;
    @Unique
    private class_8051 type;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void hookCatchArgs(class_1741 material, class_8051 type, class_1792.class_1793 settings, CallbackInfo ci) {
        this.armorMaterial = material;
        this.type = type;
    }

    @Override
    public class_1741 zov_pidarok$getMaterial() {
        return this.armorMaterial;
    }

    @Override
    public class_8051 zov_pidarok$getType() {
        return this.type;
    }
}
