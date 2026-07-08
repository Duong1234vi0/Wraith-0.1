/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_641
 *  net.minecraft.class_642
 *  net.minecraft.class_642$class_8678
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package code.alphadlc.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.List;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_641;
import net.minecraft.class_642;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_641.class})
public class ServerListMixin {
    @Unique
    private final List<class_642> sponsorServers = List.of(new class_642("\u041b\u0443\u0447\u0448\u0438\u0439 HvH \u0441\u0435\u0440\u0432\u0435\u0440!", "dddd", class_642.class_8678.field_45610), new class_642("\u041c\u043d\u043e\u0433\u043e \u0440\u0435\u0436\u0438\u043c\u043e\u0432 + \u043f\u043e\u0434\u0430\u0440\u043a\u0438!", "ddd", class_642.class_8678.field_45610));
    @Shadow
    @Final
    private List<class_642> field_3749;

    @Redirect(method={"saveFile"}, at=@At(value="INVOKE", target="Lnet/minecraft/nbt/NbtList;add(Ljava/lang/Object;)Z", ordinal=0))
    private boolean saveFileHook(class_2499 instance, Object o, @Local(ordinal=0) class_642 info) {
        if (this.sponsorServers.contains(info)) {
            return true;
        }
        return instance.add((Object)((class_2520)o));
    }
}
