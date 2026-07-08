/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.minecraft.UserApiService
 *  net.minecraft.class_310
 *  net.minecraft.class_320
 *  net.minecraft.class_5520
 *  net.minecraft.class_7574
 *  net.minecraft.class_7853
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package code.alphadlc.mixins;

import com.mojang.authlib.minecraft.UserApiService;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_5520;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_310.class})
public interface IMinecraftClient {
    @Accessor(value="itemUseCooldown")
    public int getUseCooldown();

    @Accessor(value="itemUseCooldown")
    public void setUseCooldown(int var1);

    @Invoker(value="doItemUse")
    public void idoItemUse();

    @Invoker(value="doAttack")
    public boolean idoAttack();

    @Mutable
    @Accessor(value="profileKeys")
    public void setProfileKeys(class_7853 var1);

    @Mutable
    @Accessor(value="session")
    public void setSessionT(class_320 var1);

    @Mutable
    @Accessor
    public void setUserApiService(UserApiService var1);

    @Mutable
    @Accessor(value="socialInteractionsManager")
    public void setSocialInteractionsManagerT(class_5520 var1);

    @Mutable
    @Accessor(value="abuseReportContext")
    public void setAbuseReportContextT(class_7574 var1);
}
