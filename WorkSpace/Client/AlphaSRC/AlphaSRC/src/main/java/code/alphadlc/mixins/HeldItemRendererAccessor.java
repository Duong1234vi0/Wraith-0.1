/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_759
 *  net.minecraft.class_811
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package code.alphadlc.mixins;

import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_759;
import net.minecraft.class_811;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_759.class})
public interface HeldItemRendererAccessor {
    @Invoker(value="renderItem")
    public void alphadlc$renderItem(class_1309 var1, class_1799 var2, class_811 var3, boolean var4, class_4587 var5, class_4597 var6, int var7);

    @Invoker(value="renderArmHoldingItem")
    public void invokeRenderArmHoldingItem(class_4587 var1, class_4597 var2, int var3, float var4, float var5, class_1306 var6);

    @Invoker(value="applyEquipOffset")
    public void invokeApplyEquipOffset(class_4587 var1, class_1306 var2, float var3);

    @Invoker(value="swingArm")
    public void invokeSwingArm(float var1, float var2, class_4587 var3, int var4, class_1306 var5);

    @Invoker(value="renderMapInBothHands")
    public void invokeRenderMapInBothHands(class_4587 var1, class_4597 var2, int var3, float var4, float var5, float var6);

    @Invoker(value="renderMapInOneHand")
    public void invokeRenderMapInOneHand(class_4587 var1, class_4597 var2, int var3, float var4, class_1306 var5, float var6, class_1799 var7);

    @Invoker(value="renderItem")
    public void invokeRenderItem(class_1309 var1, class_1799 var2, class_811 var3, boolean var4, class_4587 var5, class_4597 var6, int var7);
}
