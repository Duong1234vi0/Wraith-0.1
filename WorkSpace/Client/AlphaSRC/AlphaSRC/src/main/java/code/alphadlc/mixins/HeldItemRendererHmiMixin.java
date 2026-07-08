/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
 *  net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1747
 *  net.minecraft.class_1764
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1803
 *  net.minecraft.class_1828
 *  net.minecraft.class_1839
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2286
 *  net.minecraft.class_2394
 *  net.minecraft.class_2398
 *  net.minecraft.class_243
 *  net.minecraft.class_2459
 *  net.minecraft.class_2462
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_310
 *  net.minecraft.class_3481
 *  net.minecraft.class_3489
 *  net.minecraft.class_3532
 *  net.minecraft.class_3922
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5134
 *  net.minecraft.class_742
 *  net.minecraft.class_759
 *  net.minecraft.class_776
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  net.minecraft.class_9285
 *  net.minecraft.class_9285$class_9287
 *  net.minecraft.class_9334
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.SwingAnimations;
import code.alphadlc.features.impl.render.ViewModel;
import code.alphadlc.mixins.HeldItemRendererAccessor;
import code.alphadlc.utils.client.Instance;
import java.util.Random;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1747;
import net.minecraft.class_1764;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1803;
import net.minecraft.class_1828;
import net.minecraft.class_1839;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2286;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2459;
import net.minecraft.class_2462;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_310;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3922;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5134;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_776;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_759.class})
public abstract class HeldItemRendererHmiMixin {
    private boolean repPower = false;
    private float prevAge = 0.0f;
    private double previousRotation = 0.0;
    private float swingAngleY = 0.0f;
    private float swingAngleX = 0.0f;
    private float swingVelocityY = 0.0f;
    private float swingVelocityX = 0.0f;
    private float swingVelocityZ = 0.0f;
    private static final float GRAVITY = 0.1f;
    private static final float DAMPING = 0.88f;
    private static final float SENSITIVITY = 0.015f;
    private float vertAngleY = 0.0f;
    private float vertVelocityY = 0.0f;
    private float vertVelocityYSlime = 0.0f;
    private float vertAngleYSlime = 0.0f;
    private float riptideCounter = 0.0f;
    private float netherCounter = 0.0f;
    @Shadow
    private class_1799 field_4047;
    @Shadow
    @Final
    private class_310 field_4050;
    private float fallCounter = 0.0f;
    private float inWaterCounter = 0.0f;
    private float inspect = 0.0f;
    private float tilt = 0.0f;
    private float freezeCounter = 0.0f;
    private float clCount = 0.0f;
    private float crawlCount = 0.0f;
    private float directionalCrawlCount = 0.0f;
    private float climbCount = 0.0f;
    private float mouseHolding = 1.0f;
    private boolean isSwinging = false;
    private float swingProgress = 0.0f;
    private boolean isForward = false;
    private boolean isAttacking = false;
    private boolean left = false;
    private long alphaLastFrameNano = 0L;
    private double frameDelta = 0.0;
    @Shadow
    private float field_4043;
    @Shadow
    private float field_4053;
    @Shadow
    private float field_4051;
    @Shadow
    private float field_4052;
    @Shadow
    private class_1799 field_4048;

    private float easeInOutBack(float x) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;
        return (float)((double)x < 0.5 ? Math.pow(2.0f * x, 2.0) * (double)((c2 + 1.0f) * 2.0f * x - c2) / 2.0 : (Math.pow(2.0f * x - 2.0f, 2.0) * (double)((c2 + 1.0f) * (x * 2.0f - 2.0f) + c2) + 2.0) / 2.0);
    }

    private float[] resolveHmiSwingValues(SwingAnimations config, float swingProgress) {
        float progress = config.remapHmiSwingProgress(swingProgress);
        if (progress <= 0.0f) {
            return new float[]{0.0f, 0.0f};
        }
        float targetSwingRot = (double)progress < 0.6 ? class_3532.method_15374((float)(class_3532.method_15363((float)progress, (float)0.0f, (float)0.12506f) * 12.56f)) : class_3532.method_15374((float)(class_3532.method_15363((float)progress, (float)0.62532f, (float)0.75038f) * 12.56f));
        float targetSwing = this.easeInOutBack(class_3532.method_15374((float)(progress * (float)Math.PI)));
        return new float[]{targetSwing, targetSwingRot};
    }

    private float getAttackDamage(class_1799 stack) {
        class_9285 modifiers = (class_9285)stack.method_57353().method_57829(class_9334.field_49636);
        if (modifiers == null) {
            return 0.0f;
        }
        float totalDamage = 0.0f;
        for (class_9285.class_9287 entry : modifiers.comp_2393()) {
            if (entry.comp_2395().comp_349() != class_5134.field_23721.comp_349()) continue;
            totalDamage += (float)entry.comp_2396().comp_2449();
        }
        return totalDamage;
    }

    private boolean shouldRenderItemStackModel(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        if (stack.method_57826(class_9334.field_49637)) {
            return true;
        }
        if (stack.method_57826(class_9334.field_54199)) {
            return true;
        }
        if (stack.method_57826(class_9334.field_49617)) {
            return true;
        }
        if (stack.method_57826(class_9334.field_49628)) {
            return true;
        }
        if (stack.method_57826(class_9334.field_49644)) {
            return true;
        }
        return stack.method_57826(class_9334.field_49651);
    }

    private boolean isSkullItem(class_1799 stack) {
        return stack.method_31574(class_1802.field_8575) || stack.method_31574(class_1802.field_8470) || stack.method_31574(class_1802.field_8398) || stack.method_31574(class_1802.field_8791) || stack.method_31574(class_1802.field_8681) || stack.method_31574(class_1802.field_8712) || stack.method_31574(class_1802.field_41304);
    }

    private void applyTotemLikeHandPose(class_4587 matrices, int side) {
        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(5 * side)));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0f));
        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * side)));
        matrices.method_46416(0.0f, -0.05f, -0.1f);
        matrices.method_22905(0.7f, 0.7f, 0.7f);
    }

    private void renderSkullInHand(class_742 player, class_1799 item, class_4587 matrices, class_4597 vertexConsumers, int light, int side, boolean rightHand) {
        matrices.method_22903();
        if (!rightHand) {
            matrices.method_46416(-0.4f, 0.0f, 0.0f);
        }
        matrices.method_22905(0.59f, 0.59f, 0.59f);
        matrices.method_22904(-0.5 * (double)side, -0.25, 0.1);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(90.0f * (float)side));
        HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
        if (this.shouldRenderItemStackModel(item)) {
            acc.invokeRenderItem((class_1309)player, item, class_811.field_4319, !rightHand, matrices, vertexConsumers, light);
        } else {
            class_1792 class_17922 = item.method_7909();
            if (class_17922 instanceof class_1747) {
                class_1747 blockItem = (class_1747)class_17922;
                class_310.method_1551().method_1541().method_3353(blockItem.method_7711().method_9564(), matrices, vertexConsumers, light, class_4608.field_21444);
            }
        }
        matrices.method_22909();
    }

    private boolean isSharpAnimation(SwingAnimations config) {
        return config != null && config.isSharpAnimation();
    }

    private void altSwing(class_4587 matrices, class_1306 arm, float swingProgress, class_1799 item) {
        int i = arm == class_1306.field_6183 ? 1 : -1;
        float f = class_3532.method_15374((float)(swingProgress * 3.14f));
        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * (45.0f + f * 0.0f)));
        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0f));
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderFirstPersonItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        SwingAnimations swings = SwingAnimations.getInstance();
        if (swings == null || !swings.isState() || !swings.isHmiMode()) {
            return;
        }
        boolean isMainHand = hand == class_1268.field_5808;
        class_1306 arm = isMainHand ? player.method_6068() : player.method_6068().method_5928();
        float sideFactor = isMainHand ? 1.0f : -1.0f;
        this.renderCustomFirstPersonItem(player, tickDelta, pitch, hand, arm, sideFactor, swingProgress, item, equipProgress, matrices, vertexConsumers, light);
        ci.cancel();
    }

    private void renderCustomFirstPersonItem(class_742 player, float tickDelta, float pitch, class_1268 hand, class_1306 arm, float sideFactor, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light) {
        SwingAnimations swings = SwingAnimations.getInstance();
        if (swings != null && swings.isState() && swings.isHmiMode() && !player.method_31550()) {
            boolean sharpSword;
            SwingAnimations config = SwingAnimations.getInstance();
            float yaw = player.method_36454();
            double radians = Math.toRadians(yaw);
            double forwardX = -Math.sin(radians);
            double forwardZ = Math.cos(radians);
            class_243 horizontalVelocity = player.method_18798();
            double dotProduct = horizontalVelocity.field_1352 * forwardX + horizontalVelocity.field_1350 * forwardZ;
            double crossProduct = player.method_18798().method_61890().field_1352 * forwardZ - horizontalVelocity.field_1350 * forwardX;
            float al = player.method_36455() != 0.0f ? 90.0f / player.method_36455() / 10.0f : 1.0f;
            if (al > 1.0f) {
                al = 1.0f;
            }
            if (al < 0.0f) {
                al = 1.0f;
            }
            boolean bl = hand == class_1268.field_5808;
            matrices.method_22903();
            matrices.method_22903();
            ViewModel viewModel = Instance.get(ViewModel.class);
            if (viewModel != null && viewModel.isState()) {
                viewModel.applyHandPosition(matrices, arm);
            }
            long alphaNow = System.nanoTime();
            this.frameDelta = this.alphaLastFrameNano == 0L ? 0.0 : Math.min(0.05, Math.max(0.0, (double)(alphaNow - this.alphaLastFrameNano) / 1.0E9));
            this.alphaLastFrameNano = alphaNow;
            double tt = this.frameDelta * 30.0;
            float[] swingValues = this.resolveHmiSwingValues(config, swingProgress);
            float swing = swingValues[0];
            float swing_rot = swingValues[1];
            boolean bl2 = sharpSword = item.method_31573(class_3489.field_42611) && this.isSharpAnimation(config);
            if ((item.method_31574(class_1802.field_8287) || item.method_31574(class_1802.field_49098) || item.method_31574(class_1802.field_8803) || item.method_31574(class_1802.field_8449) || item.method_31574(class_1802.field_8543) || item.method_7909() instanceof class_1828 || item.method_7909() instanceof class_1803) && player.method_6079().method_7960() && item.method_7976() != class_1839.field_8951 && !item.method_31574(class_1802.field_8814) && !player.method_5681() && !player.method_20448() && !player.method_6101()) {
                if (player.method_6068() == class_1306.field_6182) {
                    bl = !bl;
                }
                matrices.method_22903();
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-25.0f * sideFactor));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0f));
                matrices.method_22907(class_7833.field_40716.rotationDegrees(25.0f * sideFactor * swing));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f * swing));
                matrices.method_22904(-0.15 * (double)sideFactor, 0.1, 0.1);
                matrices.method_22904(0.0, -0.55 * (double)swing, 0.4 * (double)swing * (double)3.14f);
                HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, 0.0f, arm.method_5928());
                matrices.method_22909();
            }
            if (this.field_4050.field_1690.field_1886.method_1434() && !this.isAttacking && (double)swingProgress == 0.0 && config.shouldAlternateClassicSwordSwing()) {
                boolean bl3 = this.left = !this.left;
            }
            if (!item.method_7960()) {
                if (player.method_6068() == class_1306.field_6182) {
                    bl = !bl;
                }
                boolean useLeftSwing = this.left;
                if (item.method_31573(class_3489.field_42611) && !sharpSword) {
                    useLeftSwing = config.resolveClassicSwordLeft(this.left);
                }
                if ((useLeftSwing || item.method_31573(class_3489.field_42612) || item.method_7976() == class_1839.field_8951 || item.method_7976() == class_1839.field_8949) && !item.method_31573(class_3489.field_42615)) {
                    if (sharpSword) {
                        matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.5 * (double)swing);
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                    } else if (!item.method_31573(class_3489.field_42611) && !item.method_31573(class_3489.field_42612)) {
                        if (item.method_7976() == class_1839.field_8951) {
                            matrices.method_22904(0.0, 0.0, 0.45 * (double)swing_rot);
                            matrices.method_22904(-0.25 * (double)sideFactor * (double)swing, -0.35 * (double)swing_rot, -0.6 * (double)swing);
                            matrices.method_22904(0.0, 0.1 * (double)swing, 0.0);
                            matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0f * swing_rot * sideFactor));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees(30.0f * swing_rot * sideFactor));
                        } else if (item.method_31573(ConventionalItemTags.TOOLS) && item.method_7976() != class_1839.field_8949 && !item.method_31573(class_3489.field_42615)) {
                            matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.5 * (double)swing);
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0f * swing_rot * sideFactor));
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                        } else if (item.method_7976() != class_1839.field_8949) {
                            matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.1 * (double)swing);
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0f * swing_rot * sideFactor));
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0f * swing * sideFactor));
                        } else {
                            matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.2 * (double)swing);
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(-10.0f * swing_rot));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0f * swing_rot * sideFactor));
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(20.0f * swing));
                        }
                    } else {
                        matrices.method_22904(0.8 * (double)sideFactor * (double)swing_rot, 0.3 * (double)swing_rot, -0.5 * (double)swing);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-20.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-70.0f * swing_rot * sideFactor));
                        if (item.method_31573(class_3489.field_42611)) {
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                        } else {
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(30.0f * swing));
                        }
                    }
                } else if (!item.method_31573(class_3489.field_42615)) {
                    if (sharpSword) {
                        matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.5 * (double)swing);
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                    } else if (item.method_31573(class_3489.field_42611)) {
                        matrices.method_22904(-0.55 * (double)sideFactor * (double)swing_rot, -0.8 * (double)swing_rot, -0.77 * (double)swing);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(5.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(70.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(50.0f * swing));
                    } else if (item.method_31573(ConventionalItemTags.TOOLS) && !item.method_31573(class_3489.field_42615)) {
                        matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.5 * (double)swing);
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-20.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                    } else {
                        matrices.method_22904(0.1 * (double)sideFactor * (double)swing_rot, 0.1 * (double)swing_rot, -0.1 * (double)swing);
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(-30.0f * swing_rot));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-10.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40713.rotationDegrees(40.0f * swing));
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0f * swing * sideFactor));
                    }
                } else if (item.method_31573(class_3489.field_42615)) {
                    matrices.method_22904(0.0, 0.15 * (double)swing_rot, -0.25 * (double)swing_rot);
                    matrices.method_22904(0.0, 0.0, -0.2 * (double)swing);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees(15.0f * swing_rot));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(-35.0f * swing_rot));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f * swing));
                }
            } else if (!(class_2248.method_9503((class_1792)item.method_7909()) == class_2246.field_10124 || item.method_31573(ConventionalItemTags.TOOLS) && !item.method_31573(class_3489.field_41890) && !item.method_31573(class_3489.field_40109) && item.method_7976() != class_1839.field_8950 && item.method_7923() || item.method_7976() == class_1839.field_8953 || item.method_7976() == class_1839.field_27079 || this.getAttackDamage(item) != 0.0f || item.method_7976() == class_1839.field_8949 || item.method_31574(class_1802.field_23254) || item.method_31574(class_1802.field_8184) || item.method_31574(class_1802.field_8378) || item.method_31574(class_1802.field_8868))) {
                if ((swingProgress = (float)((double)swingProgress * 1.2)) > 1.0f) {
                    swingProgress = 0.0f;
                }
            } else if (!item.method_31573(class_3489.field_42615) && (swingProgress = (float)((double)swingProgress * 1.5)) > 1.0f) {
                swingProgress = 0.0f;
            }
            if (player.method_18798().method_1033() >= 0.08) {
                this.crawlCount = (float)((double)this.crawlCount + 0.1 * player.method_18798().method_1033() * 2.0 * tt);
                this.directionalCrawlCount = (float)((double)this.directionalCrawlCount + 0.1 * dotProduct * 4.0 * tt);
                this.directionalCrawlCount = (float)((double)this.directionalCrawlCount + (dotProduct > 0.0 ? 0.1 * Math.abs(crossProduct) * 4.0 * tt : 0.1 * Math.abs(crossProduct) * -1.0 * 4.0 * tt));
            }
            if (player.method_18798().method_10214() > 0.0) {
                this.climbCount = (float)((double)this.climbCount + 0.1 * tt);
            }
            if (player.method_18798().method_10214() < 0.0) {
                this.climbCount = (float)((double)this.climbCount - 0.1 * tt);
            }
            if ((player.method_20448() && config.isClimbAndCrawl() || player.method_6101() && !player.method_24828() && Math.abs(player.method_18798().method_10214()) > 0.0 && config.isClimbAndCrawl()) && !player.method_6115() && swingProgress == 0.0f) {
                this.clCount = (float)((double)this.clCount + 0.1 * tt);
                if (this.clCount > 1.0f) {
                    this.clCount = 1.0f;
                }
                if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(-20.0f * this.clCount));
                }
            } else {
                this.clCount = (float)((double)this.clCount * Math.pow(0.88f, tt));
            }
            if (swingProgress == 0.0f) {
                matrices.method_46416(bl ? player.method_36455() / 650.0f * this.clCount * -1.0f : player.method_36455() / 650.0f * this.clCount, 0.0f, 0.0f);
                matrices.method_22907(class_7833.field_40714.rotationDegrees(player.method_36455() * this.clCount));
            }
            if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
                matrices.method_46416(0.0f, 0.0f, player.method_36455() / 120.0f * this.clCount);
            } else if (swingProgress == 0.0f) {
                matrices.method_46416(0.0f, 0.0f, player.method_36455() / 80.0f * this.clCount);
            }
            if (player.method_6101() && config.isClimbAndCrawl() && !player.method_24828() && !item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016) && !player.method_6115()) {
                matrices.method_22904(0.0, 0.1, -0.2);
            }
            if ((player.method_52535() || player.field_27857) && !player.method_5681() && !player.method_5869()) {
                this.inWaterCounter = (float)((double)this.inWaterCounter + 0.1 * tt);
                if (this.inWaterCounter >= 1.0f) {
                    this.inWaterCounter = 1.0f;
                }
            } else {
                this.inWaterCounter = (float)((double)this.inWaterCounter * Math.pow(0.88f, tt));
            }
            this.freezeCounter = player.field_27857 && (double)player.method_32313() > 0.1 ? (float)((double)this.freezeCounter + 0.1 * tt) : (float)((double)this.freezeCounter * Math.pow(0.88f, tt));
            matrices.method_22904(0.0, 0.02 * (double)this.inWaterCounter, 0.0);
            matrices.method_22907(class_7833.field_40718.rotationDegrees(8.0f * sideFactor * this.inWaterCounter));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(0.3f * class_3532.method_15374((float)(this.freezeCounter * 5.0f))));
            if (player.method_18798().method_10214() < -0.85 && item.method_31574(class_1802.field_49814) && player.method_6047() == item) {
                this.fallCounter = (float)((double)this.fallCounter + 0.1 * tt);
                if (this.fallCounter >= 1.0f) {
                    this.fallCounter = 1.0f;
                }
            } else {
                this.fallCounter = (float)((double)this.fallCounter * Math.pow(0.88f, tt));
            }
            if (bl) {
                matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0f * this.fallCounter));
                matrices.method_22904(0.0, -0.2 * (double)this.fallCounter, 0.0);
            }
            this.vertAngleY = (float)((double)this.vertAngleY + player.method_18798().method_10214() * (double)0.015f * tt);
            this.vertAngleY = (float)((double)this.vertAngleY - (double)(0.1f * this.vertAngleY) * tt);
            this.vertAngleY = (float)((double)this.vertAngleY * Math.pow(0.88f, tt));
            this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + player.method_18798().method_10214() * (double)0.015f * tt);
            this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime - (double)(0.1f * this.vertAngleYSlime) * tt);
            this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime * Math.pow(0.88f, tt));
            this.vertAngleYSlime = (float)((double)this.vertAngleYSlime + (double)this.vertVelocityYSlime * tt);
            matrices.method_46416(0.0f, this.vertAngleY * -1.0f, 0.0f);
            matrices.method_22904(0.0, Math.sin((double)player.field_6012 * 0.1) * 0.007 * (double)sideFactor, 0.0);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(0.15f * class_3532.method_15374((float)((float)player.field_6012 * 0.15f)) * sideFactor));
            if (!item.method_7960() || player.method_20448() || player.method_6101() && !player.method_24828() || player.method_5681()) {
                if (player.method_6068() == class_1306.field_6182) {
                    boolean bl4 = bl = !bl;
                }
                if (item.method_7976() == class_1839.field_8949) {
                    matrices.method_46416(0.0f, 0.0f, 0.0f);
                } else {
                    matrices.method_22904(0.0, -0.1, 0.1);
                }
            }
            if (item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016) || item.method_31573(class_3489.field_40108)) {
                matrices.method_22904(0.0, 0.1, 0.0);
                if (player.method_5681()) {
                    matrices.method_22904(0.0, -0.1, 0.1);
                }
            }
            if (player.method_5681() && swingProgress == 0.0f && config.isSwimmingAnimation()) {
                double a;
                double distance = this.crawlCount;
                double swingAmplitude = 1.5;
                double frequency = 2.0;
                double s = distance * frequency;
                double handRotation = Math.sin(s) * swingAmplitude;
                double smoothRotation = handRotation * 0.8 + this.previousRotation * 0.2;
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(bl ? smoothRotation : -smoothRotation)));
                matrices.method_22904(0.0, 0.0, smoothRotation * (double)0.2f);
                double k = this.crawlCount * 2.0f;
                double b = a = Math.cos(k);
                if (a <= 0.0) {
                    b = a * 0.5;
                }
                matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(bl ? b * 30.0 : b * 30.0 * -1.0)));
                matrices.method_22904(0.0, 0.0, a * (double)0.2f);
                if (item.method_7960() && !bl && !player.method_5767()) {
                    matrices.method_22904((double)(1.0f * sideFactor), 0.0 - (double)equipProgress * 0.3, 0.3);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0f * sideFactor));
                    matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0f * sideFactor));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                    this.altSwing(matrices, arm, swingProgress, item);
                    float c = class_3532.method_15374((float)(equipProgress * 3.14f));
                    matrices.method_22905(0.9f, 0.9f, 0.9f);
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                }
                this.previousRotation = smoothRotation;
            }
            if ((player.method_6101() && !player.method_24828() || player.method_20448() && swingProgress == 0.0f) && !player.method_6115()) {
                double s = this.climbCount;
                float v = (float)player.method_18798().method_10214();
                float a = class_3532.method_15362((float)((float)s * 2.0f));
                if (player.method_6101()) {
                    if (!item.method_31574(class_1802.field_16539) && !item.method_31574(class_1802.field_22016)) {
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(20.0f * a * sideFactor));
                    } else {
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(1.0f * a * sideFactor));
                    }
                }
                if (player.method_20448() && !player.method_6115() && swingProgress == 0.0f) {
                    float crawlProgress = class_3532.method_15374((float)(this.directionalCrawlCount * 4.0f * this.mouseHolding));
                    float upAndDown = class_3532.method_15362((float)(this.directionalCrawlCount * 4.0f * this.mouseHolding));
                    if (item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016)) {
                        crawlProgress *= 0.14f;
                        upAndDown *= 0.14f;
                    }
                    matrices.method_22904(0.2 * (double)crawlProgress, 0.3 * (double)crawlProgress * (double)sideFactor, -0.2 * (double)crawlProgress * (double)sideFactor * (double)al);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees(25.0f * crawlProgress));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_15363((float)(20.0f * upAndDown * sideFactor), (float)0.0f, (float)20.0f)));
                }
                if (item.method_7960() && !bl && !player.method_5767() && (!player.method_24828() && player.method_6101() || player.method_20448())) {
                    matrices.method_22904((double)(1.0f * sideFactor), 0.0 - (double)equipProgress * 0.3, 0.3);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0f * sideFactor));
                    matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0f * sideFactor));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                    this.altSwing(matrices, arm, swingProgress, item);
                    matrices.method_22905(0.9f, 0.9f, 0.9f);
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                }
            }
            if (item.method_7960()) {
                if (!player.method_5767()) {
                    if (!(!player.method_24828() && player.method_6101() || player.method_5681() || player.method_20448())) {
                        if (player.method_6068() == class_1306.field_6182) {
                            bl = !bl;
                        }
                        matrices.method_22904(0.0, 0.2 * (double)swing_rot, 0.15 * (double)swing_rot);
                        matrices.method_22904(0.1 * (double)sideFactor * (double)swing, 0.15 * (double)swing, -0.45 * (double)swing);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(35.0f * swing * sideFactor));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(-30.0f * swing));
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(-10.0f * swing_rot * sideFactor));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0f * swing_rot));
                        HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                        acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                    } else {
                        matrices.method_22904((double)(1.0f * sideFactor), 0.0 - (double)equipProgress * 0.3, 0.3);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0f * sideFactor));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0f * sideFactor));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                        this.altSwing(matrices, arm, swingProgress, item);
                        float c = class_3532.method_15374((float)(equipProgress * 3.14f));
                        matrices.method_22905(0.9f, 0.9f, 0.9f);
                        HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                        acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                    }
                }
            } else if (item.method_57826(class_9334.field_49646)) {
                if (bl && this.field_4047.method_7960()) {
                    matrices.method_22904(0.0, 0.1, 0.0);
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderMapInBothHands(matrices, vertexConsumers, light, pitch, equipProgress, swingProgress);
                } else {
                    matrices.method_22904(bl ? -0.1 : 0.1, 0.1, 0.0);
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderMapInOneHand(matrices, vertexConsumers, light, equipProgress, arm, swingProgress, item);
                }
            } else if (item.method_7976() == class_1839.field_8947) {
                int i;
                matrices.method_22903();
                boolean bl22 = class_1764.method_7781((class_1799)item);
                boolean bl3 = arm == class_1306.field_6183;
                int n = i = bl3 ? 1 : -1;
                if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeApplyEquipOffset(matrices, arm, equipProgress);
                    matrices.method_46416((float)i * -0.4785682f, -0.24387f, 0.05731531f);
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(-11.935f));
                    matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 65.3f));
                    matrices.method_22907(class_7833.field_40718.rotationDegrees((float)i * 9.785f));
                    float f = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                    float g = f / (float)class_1764.method_7775((class_1799)item, (class_1309)player);
                    if (g > 1.0f) {
                        g = 1.0f;
                    }
                    if (g > 0.1f) {
                        float h = class_3532.method_15374((float)((f - 0.1f) * 1.3f));
                        float j = g - 0.1f;
                        float k = h * j;
                        matrices.method_46416(k * 0.0f, k * 0.004f, k * 0.0f);
                    }
                    matrices.method_46416(g * 0.0f, g * 0.0f, g * 0.04f);
                    matrices.method_22905(1.0f, 1.0f, 1.0f);
                    matrices.method_22907(class_7833.field_40715.rotationDegrees((float)i * 45.0f));
                } else {
                    ((HeldItemRendererAccessor)((Object)this)).invokeSwingArm(swingProgress, equipProgress, matrices, i, arm);
                    if (bl22 && swingProgress < 0.001f && bl) {
                        matrices.method_46416((float)i * -0.341864f, 0.0f, 0.0f);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)i * 10.0f));
                    }
                }
                matrices.method_46416(0.0f, 0.0f, -1.0f);
                matrices.method_22904(-0.45 * (double)i, 0.45, 1.7);
                matrices.method_22904((double)(1.0f * sideFactor), 0.0 - (double)equipProgress * 0.3, 0.3);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0f * sideFactor));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(-40.0f * sideFactor));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                this.altSwing(matrices, arm, swingProgress, item);
                float c = class_3532.method_15374((float)(equipProgress * 3.14f));
                matrices.method_22905(0.9f, 0.9f, 0.9f);
                HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                matrices.method_22904(-0.25 * (double)i, 1.25, 0.05);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-90 * i)));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(77.0f));
                matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(85 * i)));
                matrices.method_22905(1.2f, 1.2f, 1.2f);
                matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0f));
                matrices.method_22904(0.0, -0.15, 0.15);
                acc.invokeRenderItem((class_1309)player, item, bl3 ? class_811.field_4322 : class_811.field_4321, !bl3, matrices, vertexConsumers, light);
                matrices.method_22909();
                if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
                    float f = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                    float g = f / (float)class_1764.method_7775((class_1799)item, (class_1309)player);
                    if (g > 1.0f) {
                        g = 1.0f;
                    }
                    if (g > 0.1f) {
                        float h = class_3532.method_15374((float)((f - 0.1f) * 1.3f));
                        float j = g - 0.1f;
                        float k = h * j;
                        matrices.method_46416(k * 0.0f, k * 0.004f, k * 0.0f);
                    }
                    matrices.method_22907(class_7833.field_40715.rotationDegrees((double)g <= 0.2 ? 75.0f * g * 5.0f * (float)i : (float)(75 * i)));
                    matrices.method_22907(class_7833.field_40713.rotationDegrees(10.0f * g * 1.5f));
                    matrices.method_22904(-0.37 * (double)i, 0.0, 0.6);
                    matrices.method_22904(0.15 * (double)g * (double)i, 0.0, 0.0);
                    acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm.method_5928());
                }
            } else {
                int l;
                boolean bl23 = arm == class_1306.field_6183;
                int n = l = bl23 ? 1 : -1;
                if (player.method_6115() && player.method_6014() > 0 && player.method_6058() == hand) {
                    switch (item.method_7976()) {
                        case field_8952: {
                            HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                            acc.invokeApplyEquipOffset(matrices, arm, equipProgress);
                            break;
                        }
                        case field_8950: 
                        case field_8946: {
                            float u = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                            float y = u / 5.0f;
                            if (y > 1.0f) {
                                y = 1.0f;
                            }
                            float q = class_3532.method_15374((float)(u / 2.0f * 3.14f));
                            matrices.method_22904((double)(1 * l), 0.1, 0.3);
                            matrices.method_22904(0.2 * (double)l * (double)y, -0.7 * (double)y, -0.2 * (double)y);
                            matrices.method_22904(0.0, -0.2 * (double)(q /= 10.0f), -0.2 * (double)q);
                            matrices.method_22904(0.0, 0.1 * (double)this.easeInOutBack(class_3532.method_15374((float)(y * 3.14f))), 0.0);
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * l)));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * l)));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                            this.altSwing(matrices, arm, swingProgress, item);
                            float c = class_3532.method_15374((float)(equipProgress * 3.14f));
                            matrices.method_22905(0.9f, 0.9f, 0.9f);
                            matrices.method_22907(class_7833.field_40716.rotationDegrees(45.0f * y * (float)l));
                            HeldItemRendererAccessor acc4 = (HeldItemRendererAccessor)((Object)this);
                            acc4.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, swingProgress, arm);
                            break;
                        }
                        case field_8949: {
                            float k = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                            float s = k / 4.0f;
                            float s2 = k / 6.0f;
                            if (s > 1.0f) {
                                s = 1.0f;
                            }
                            if (s2 > 1.0f) {
                                s2 = 1.0f;
                            }
                            matrices.method_22904(0.0, -0.2, 0.0);
                            matrices.method_22904((double)(1 * l), 0.0, 0.3);
                            matrices.method_22904(0.7 * (double)s * (double)l, 0.0, -1.3 * (double)s);
                            matrices.method_22904(-0.2 * (double)l * (double)s2, 0.0, 0.0);
                            matrices.method_22907(class_7833.field_40714.rotationDegrees((float)(10.0 * Math.sin((double)s2 * 3.14))));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees(70.0f * s * (float)l));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * l)));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * l)));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(5 * l) * s));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(-10.0f * s));
                            matrices.method_22904(0.0, 0.0, -0.2 * (double)s);
                            this.altSwing(matrices, arm, swingProgress, item);
                            matrices.method_22905(0.9f, 0.9f, 0.9f);
                            HeldItemRendererAccessor acc5 = (HeldItemRendererAccessor)((Object)this);
                            acc5.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, swingProgress, arm);
                            matrices.method_22904(0.35 * (double)l, -0.13, -0.12);
                            matrices.method_22907(class_7833.field_40718.rotationDegrees(10.0f * (float)l));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees(10.0f * (float)l));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(0.0f));
                            matrices.method_22904(-0.2 * (double)l, -0.04, 0.15);
                            matrices.method_22905(1.0f, 1.0f, 1.0f);
                            break;
                        }
                        case field_8953: {
                            matrices.method_22903();
                            if (player.method_6068() == class_1306.field_6182) {
                                bl = !bl;
                            }
                            float m1 = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                            float f1 = m1 / 20.0f;
                            float f = (f1 * f1 + f1 * 2.0f) / 3.0f;
                            if (f1 > 1.0f) {
                                f1 = 1.0f;
                            }
                            if (f1 > 0.1f) {
                                float g1 = class_3532.method_15374((float)((m1 - 0.1f) * 1.3f));
                                float j1 = g1 * f1;
                                matrices.method_46416(j1 * 0.0f, j1 * 0.004f, j1 * 0.0f);
                            }
                            matrices.method_22904(bl ? -0.1 : 0.1, 0.0, (double)f1 * 0.15);
                            HeldItemRendererAccessor acc1 = (HeldItemRendererAccessor)((Object)this);
                            acc1.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                            matrices.method_22909();
                            matrices.method_22904(bl ? -0.5 : 0.5, -0.45, 0.1);
                            matrices.method_22907(class_7833.field_40714.rotation(0.3f));
                            if (bl) {
                                matrices.method_22907(class_7833.field_40717.rotation(-0.3f));
                                matrices.method_22907(class_7833.field_40715.rotation(1.0f));
                            } else {
                                matrices.method_22907(class_7833.field_40718.rotation(-0.3f));
                                matrices.method_22907(class_7833.field_40716.rotation(1.0f));
                            }
                            acc1.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm.method_5928());
                            if (bl) {
                                matrices.method_22907(class_7833.field_40715.rotation(2.5f));
                            } else {
                                matrices.method_22907(class_7833.field_40716.rotation(2.5f));
                            }
                            matrices.method_22904(bl ? -0.65 : 0.65, -0.35, 0.27);
                            if (f1 > 1.0f) {
                                f1 = 1.0f;
                            }
                            matrices.method_22909();
                            if (config.isMb3DCompat()) {
                                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(10 * l)));
                            }
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(75.0f));
                            matrices.method_22907(class_7833.field_40717.rotationDegrees((float)(-15 * l)));
                            matrices.method_22904(0.8 * (double)l, (double)(0.0f - equipProgress * 0.3f), -0.1);
                            if (f > 0.1f) {
                                float g1 = class_3532.method_15374((float)((m1 - 0.1f) * 1.3f));
                                float h1 = f1 - 0.1f;
                                float j1 = g1 * h1;
                                matrices.method_46416(j1 * 0.0f, j1 * 0.004f, j1 * 0.0f);
                            }
                            matrices.method_22903();
                            break;
                        }
                        case field_8951: {
                            float m = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                            float f = m / 10.0f;
                            if (f > 1.0f) {
                                f = 1.0f;
                            }
                            if (f > 0.1f) {
                                float g = class_3532.method_15374((float)((m - 0.1f) * 1.3f));
                                float h = f - 0.1f;
                                float j = g * h;
                                matrices.method_46416(j * 0.0f, j * 0.004f, j * 0.0f);
                            }
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0f));
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * l)));
                            matrices.method_22904(0.2 * (double)l, 0.0, 0.8);
                            HeldItemRendererAccessor acc0 = (HeldItemRendererAccessor)((Object)this);
                            acc0.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(135.0f));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-65 * l)));
                            matrices.method_22904((double)(0.65f * (float)l), -1.0, -0.6);
                            break;
                        }
                        case field_42717: {
                            float f5 = player.method_6014() % 10;
                            float g5 = f5 - tickDelta + 1.0f;
                            float h5 = 1.0f - g5 / 10.0f;
                            float n2 = -15.0f + 75.0f * class_3532.method_15362((float)(h5 * 2.0f * (float)Math.PI));
                            float z = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                            float x = z / 4.0f;
                            if (x > 1.0f) {
                                x = 1.0f;
                            }
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * l) * x));
                            matrices.method_22904((double)(0.3f * (float)l * x), 0.3 * (double)x, 0.1 * (double)x);
                            if (x == 1.0f) {
                                matrices.method_22907(class_7833.field_40716.rotationDegrees(n2 / 20.0f));
                            }
                            HeldItemRendererAccessor acc78 = (HeldItemRendererAccessor)((Object)this);
                            acc78.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                            break;
                        }
                        case field_55494: {
                            matrices.method_22904((double)(1 * l), 0.0 - (double)equipProgress * 0.3, 0.3);
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * l)));
                            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * l)));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                            this.altSwing(matrices, arm, swingProgress, item);
                            matrices.method_22905(0.9f, 0.9f, 0.9f);
                            HeldItemRendererAccessor acc67 = (HeldItemRendererAccessor)((Object)this);
                            acc67.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                        }
                    }
                } else if (player.method_6123() && item.method_7976() == class_1839.field_8951) {
                    this.riptideCounter = (float)((double)this.riptideCounter + 0.15 * tt);
                    float m = (float)item.method_7935((class_1309)player) - ((float)player.method_6014() - tickDelta + 1.0f);
                    float f = m / 10.0f;
                    if (f > 1.0f) {
                        f = 1.0f;
                    }
                    if (f > 0.1f) {
                        float g = class_3532.method_15374((float)((m - 0.1f) * 1.3f));
                        float h = f - 0.1f;
                        float j = g * h;
                        matrices.method_46416(j * 0.0f, j * 0.004f, j * 0.0f);
                    }
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(45.0f - this.riptideCounter * 2.0f));
                    matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(25 * l)));
                    matrices.method_22904(0.2 * (double)l, 0.0, 0.75);
                    matrices.method_22904(0.0, 0.0, 0.01 * (double)class_3532.method_15374((float)(this.riptideCounter * 6.28f)));
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(135.0f));
                    matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-65 * l)));
                    matrices.method_22904((double)(0.65f * (float)l), -1.0, -0.6);
                } else {
                    this.riptideCounter = 0.0f;
                    if (!(item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016) || item.method_31573(class_3489.field_40108))) {
                        if (item.method_7976() == class_1839.field_8949) {
                            matrices.method_22904(0.0, -0.2, 0.0);
                        }
                    } else {
                        matrices.method_22904(0.1 * (double)l, 0.0, -0.1);
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0f));
                    }
                    matrices.method_22904((double)(1 * l), 0.0 - (double)equipProgress * 0.3, 0.3);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * l)));
                    matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(-40 * l)));
                    matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f));
                    this.altSwing(matrices, arm, swingProgress, item);
                    matrices.method_22905(0.9f, 0.9f, 0.9f);
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderArmHoldingItem(matrices, vertexConsumers, light, 0.0f, 0.0f, arm);
                }
                matrices.method_22904(-0.3 * (double)l, 0.65, -0.1);
                matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-65 * l)));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(10.0f));
                if (item.method_31573(class_3489.field_15542)) {
                    matrices.method_22904(0.2 * (double)l, -0.1, 0.0);
                }
                if (class_2248.method_9503((class_1792)item.method_7909()) != class_2246.field_10124 && item.method_7976() != class_1839.field_8950 && !item.method_31573(ConventionalItemTags.BUCKETS)) {
                    if (item.method_7964().toString().toLowerCase().contains("TORCH".toLowerCase())) {
                        matrices.method_22905(1.5f, 1.5f, 1.5f);
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(25 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(5.0f));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * l)));
                        matrices.method_22904(0.2 * (double)l, 0.2, 0.05);
                    } else if ((item.method_31574(class_1802.field_8276) || item.method_31574(class_1802.field_8725) || item.method_31574(class_1802.field_8865) || item.method_31574(class_1802.field_8366) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(ConventionalBlockTags.GLASS_PANES) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15463) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_22414) || item.method_31573(class_3489.field_15553)) && !class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15503) && !class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_43170) && !class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15501)) {
                        matrices.method_22904(0.0, 0.0, -0.1);
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(5 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0f));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * l)));
                    } else if (!(item.method_31574(class_1802.field_16539) || item.method_31574(class_1802.field_22016) || item.method_31573(class_3489.field_40108))) {
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(25 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(5.0f));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * l)));
                        matrices.method_22904(0.2 * (double)l, 0.2, 0.05);
                        if (class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15501)) {
                            matrices.method_22904(-0.2 * (double)l, 0.0, 0.0);
                            matrices.method_22905(1.1f, 1.1f, 1.1f);
                        }
                    } else {
                        float dt = (float)(this.frameDelta * 30.0);
                        float yawDelta = player.field_6259 - player.method_5791();
                        float pitchDelta = player.field_6004 - player.method_36455();
                        this.swingVelocityY += yawDelta * 0.015f * dt;
                        this.swingVelocityY += swingProgress * 2.0f * dt;
                        this.swingVelocityX += pitchDelta * 0.015f * dt;
                        this.swingVelocityY -= 0.1f * this.swingAngleY * dt;
                        this.swingVelocityX -= 0.1f * this.swingAngleX * dt;
                        this.swingVelocityY = (float)((double)this.swingVelocityY * Math.pow(0.88f, dt));
                        this.swingVelocityX = (float)((double)this.swingVelocityX * Math.pow(0.88f, dt));
                        this.swingAngleY += this.swingVelocityY * dt;
                        this.swingAngleX += this.swingVelocityX * dt;
                        double currentSpeed = player.method_18798().method_1033();
                        this.swingVelocityZ = (float)((double)this.swingVelocityZ + (bl ? (currentSpeed * -1.0 * 15.0 - (double)this.swingVelocityZ) * (double)0.1f * (double)dt : (currentSpeed * 15.0 - (double)this.swingVelocityZ) * (double)0.1f * (double)dt));
                        if ((currentSpeed > 0.09 && player.method_24828() || player.method_6101() && !player.method_24828()) && ((Boolean)this.field_4050.field_1690.method_42448().method_41753()).booleanValue()) {
                            Random random = new Random();
                            boolean randomBoolean = random.nextBoolean();
                            this.swingVelocityY += (float)(randomBoolean ? -5.5 * currentSpeed * (double)dt : 5.5 * currentSpeed * (double)dt);
                        }
                        matrices.method_22904(0.0, 0.0, -0.1);
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(35 * l) + this.swingAngleY));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(15.0f + this.swingAngleX));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(75 * l) + this.swingVelocityZ));
                        if (item.method_31573(class_3489.field_40108)) {
                            matrices.method_22904(0.0, -0.1, 0.0);
                            matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-45 * l)));
                        }
                        matrices.method_22904(0.3 * (double)l, -0.35, 0.0);
                        matrices.method_22904(0.0, 0.0, 0.1);
                        matrices.method_22905(1.5f, 1.5f, 1.5f);
                    }
                } else if (!this.isSkullItem(item)) {
                    if (!(item.method_31573(ConventionalItemTags.TOOLS) && !item.method_31573(class_3489.field_41890) && !item.method_31573(class_3489.field_40109) && item.method_7976() != class_1839.field_8950 && item.method_7923() || item.method_7976() == class_1839.field_8953 || item.method_7976() == class_1839.field_27079 || this.getAttackDamage(item) != 0.0f || item.method_7976() == class_1839.field_8949 || item.method_31574(class_1802.field_23254) || item.method_31574(class_1802.field_8184) || item.method_31574(class_1802.field_8378) || item.method_31574(class_1802.field_8868) || item.method_31573(class_3489.field_42613) || config.isMb3DCompat())) {
                        if (item.method_7976() == class_1839.field_42717) {
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(25.0f));
                            matrices.method_22904(bl ? 0.0 : 0.35, bl ? 0.0 : 0.25, bl ? 0.0 : 0.37);
                            if (!bl) {
                                matrices.method_22905(0.75f, 0.75f, 0.75f);
                            }
                            matrices.method_22907(class_7833.field_40717.rotationDegrees((float)(-75 * l)));
                            matrices.method_22907(class_7833.field_40713.rotationDegrees(35.0f));
                            matrices.method_22904(bl ? -0.05 : 0.85, bl ? 0.0 : 0.05, bl ? 0.08 : -0.2);
                        } else {
                            this.applyTotemLikeHandPose(matrices, l);
                        }
                        if (item.method_31574(class_1802.field_8153) || item.method_31574(class_1802.field_8777) || item.method_31574(class_1802.field_8323)) {
                            this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + (double)swingProgress * 0.03 * this.frameDelta * 30.0);
                            if ((player.method_18798().method_1033() > 0.09 && player.method_24828() || player.method_5681() || player.method_20448() || player.method_6101() && !player.method_24828()) && ((Boolean)this.field_4050.field_1690.method_42448().method_41753()).booleanValue()) {
                                Random random = new Random();
                                boolean randomBoolean = random.nextBoolean();
                                this.vertVelocityYSlime += (float)(-0.05 * player.method_18798().method_1033() * this.frameDelta * 30.0);
                            }
                            matrices.method_22905(1.0f, 1.0f + this.vertAngleYSlime * -2.0f, 1.0f);
                        }
                    } else if (item.method_7976() == class_1839.field_8949 && item.method_7976() != class_1839.field_8951) {
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(160 * l)));
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-60 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(-70.0f));
                        matrices.method_22905(0.75f, 0.75f, 0.75f);
                        matrices.method_22904(0.15 * (double)l, bl ? 0.35 : 0.45, bl ? -0.15 : -0.1);
                        matrices.method_22904(0.17 * (double)l, 0.0, 0.3);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(-90 * l)));
                    } else if (item.method_7976() == class_1839.field_8951) {
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(75 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(45 * l)));
                        matrices.method_46416(-0.3f * (float)l, 0.0f, 0.0f);
                    } else if (item.method_7976() != class_1839.field_8951) {
                        matrices.method_22907(class_7833.field_40715.rotationDegrees((float)(75 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(70.0f));
                        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)(45 * l)));
                    }
                    if (item.method_7976() != class_1839.field_8949) {
                        matrices.method_22905(1.2f, 1.2f, 1.2f);
                    }
                    if (item.method_7976() == class_1839.field_8953 && !player.method_6115()) {
                        matrices.method_22904(-0.1 * (double)l, -0.2, 0.0);
                    }
                    if (item.method_31574(class_1802.field_49814)) {
                        if (config.isMb3DCompat()) {
                            matrices.method_22904(-0.08, 0.17, 0.0);
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(40.0f));
                        }
                        matrices.method_22904(0.1 * (double)l, 0.0, 0.0);
                        matrices.method_22905(0.9f, 0.9f, 0.9f);
                    }
                }
                if (this.isSkullItem(item)) {
                    this.renderSkullInHand(player, item, matrices, vertexConsumers, light, l, bl23);
                } else if (!(!(item.method_7909() instanceof class_1747) || (item.method_31573(ConventionalItemTags.BUCKETS) || item.method_7976() == class_1839.field_8950 || item.method_31573(class_3489.field_15556) || item.method_31574(class_1802.field_8276) || item.method_31574(class_1802.field_8725) || item.method_31574(class_1802.field_8865) || item.method_31574(class_1802.field_8366) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(ConventionalBlockTags.GLASS_PANES) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15463) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_22414) || item.method_31573(class_3489.field_15553)) && !class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15503) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_43170))) {
                    class_1747 blockItem = (class_1747)item.method_7909();
                    class_776 blockRenderManager = class_310.method_1551().method_1541();
                    blockRenderManager.method_3349(blockItem.method_7711().method_9564());
                    matrices.method_22903();
                    if (!bl23) {
                        matrices.method_46416(-0.4f, 0.0f, 0.0f);
                    }
                    matrices.method_22905(0.59f, 0.59f, 0.59f);
                    matrices.method_22904(-0.5 * (double)l, -0.25, 0.1);
                    matrices.method_22907(class_7833.field_40716.rotationDegrees(90.0f * (float)l));
                    if (class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15493)) {
                        matrices.method_22904(0.2 * (double)l, -0.15, -0.2);
                    }
                    if (class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_24076)) {
                        matrices.method_22904(0.0, 0.1, 0.0);
                    }
                    if (item.method_31574(class_1802.field_8828) || item.method_31574(class_1802.field_21086) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_20339) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15503) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_15462) || class_2248.method_9503((class_1792)item.method_7909()).method_9564().method_26164(class_3481.field_44469)) {
                        this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + (double)swingProgress * 0.03 * this.frameDelta * 30.0);
                        if ((player.method_18798().method_1033() > 0.09 && player.method_24828() || player.method_6101() && !player.method_24828()) && ((Boolean)this.field_4050.field_1690.method_42448().method_41753()).booleanValue()) {
                            Random random = new Random();
                            boolean randomBoolean = random.nextBoolean();
                            this.vertVelocityYSlime += (float)(-0.05 * player.method_18798().method_1033() * this.frameDelta * 30.0);
                        }
                        matrices.method_22905(1.0f, 1.0f + this.vertAngleYSlime * -2.0f, 1.0f);
                    }
                    class_2680 blockState = blockItem.method_7711().method_9564();
                    if ((float)player.field_6012 - this.prevAge >= 100.0f) {
                        this.repPower = !this.repPower;
                        this.prevAge = player.field_6012;
                    }
                    if (blockItem.method_7711() == class_2246.field_10450 && this.repPower) {
                        blockState = (class_2680)blockState.method_11657((class_2769)class_2462.field_10911, (Comparable)Boolean.valueOf(true));
                    }
                    if (blockItem.method_7711() == class_2246.field_10377 && this.repPower) {
                        blockState = (class_2680)blockState.method_11657((class_2769)class_2286.field_10911, (Comparable)Boolean.valueOf(true));
                    }
                    if (blockItem.method_7711() == class_2246.field_10523 && player.method_5869()) {
                        blockState = (class_2680)blockState.method_11657((class_2769)class_2459.field_11446, (Comparable)Boolean.valueOf(false));
                    }
                    if ((blockItem.method_7711() == class_2246.field_17350 || blockItem.method_7711() == class_2246.field_23860) && player.method_5869()) {
                        blockState = (class_2680)blockState.method_11657((class_2769)class_3922.field_17352, (Comparable)Boolean.valueOf(false));
                    }
                    if (item.method_31573(class_3489.field_16444)) {
                        if (bl) {
                            matrices.method_22904(0.9, 0.0, 0.8);
                        }
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(90 * l)));
                    }
                    if (this.shouldRenderItemStackModel(item)) {
                        HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                        acc.invokeRenderItem((class_1309)player, item, class_811.field_4319, !bl23, matrices, vertexConsumers, light);
                    } else {
                        blockRenderManager.method_3353(blockState, matrices, vertexConsumers, light, class_4608.field_21444);
                    }
                    matrices.method_22909();
                } else {
                    if (!this.isSkullItem(item) && (item.method_31573(ConventionalItemTags.TOOLS) && !item.method_31573(class_3489.field_41890) && !item.method_31573(class_3489.field_40109) && item.method_7976() != class_1839.field_8950 && item.method_7923() || item.method_7976() == class_1839.field_8953 || item.method_7976() == class_1839.field_27079 || this.getAttackDamage(item) != 0.0f || item.method_7976() == class_1839.field_8949 || item.method_31574(class_1802.field_23254) || item.method_31574(class_1802.field_8184) || item.method_31574(class_1802.field_8378) || item.method_31574(class_1802.field_8868))) {
                        if (item.method_31573(class_3489.field_42611) && !sharpSword) {
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(-60.0f * swing));
                            matrices.method_22904(0.0, 0.1 * (double)swing, -0.1 * (double)swing);
                        }
                        if (item.method_31573(class_3489.field_42615)) {
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(-80.0f * swing_rot));
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(30.0f * swing));
                        } else if (item.method_7976() == class_1839.field_8951) {
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(-40.0f * swing_rot));
                            matrices.method_22904(0.0, 0.1 * (double)swing_rot, -0.1 * (double)swing_rot);
                        } else if (item.method_7976() != class_1839.field_8949) {
                            matrices.method_22907(class_7833.field_40714.rotationDegrees(-25.0f * swing));
                            matrices.method_22904(0.0, 0.05 * (double)swing, -0.05 * (double)swing);
                        }
                    }
                    if (!(item.method_31574(class_1802.field_8137) || item.method_31574(class_1802.field_8301) && config.isMb3DCompat())) {
                        this.netherCounter = 0.0f;
                    } else {
                        this.netherCounter = (float)((double)this.netherCounter + 0.9 * tt);
                        matrices.method_22904(0.0, 0.25 + 0.02 * (double)class_3532.method_15374((float)(this.netherCounter * 0.1f)), 0.0);
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(3.0f * class_3532.method_15374((float)(this.netherCounter * 0.2f))));
                        matrices.method_22905(1.0f + 0.01f * class_3532.method_15374((float)this.netherCounter), 1.0f + 0.01f * class_3532.method_15374((float)this.netherCounter), 1.0f + 0.01f * class_3532.method_15374((float)this.netherCounter));
                    }
                    if (config.isMb3DCompat()) {
                        if (item.method_31573(class_3489.field_42611)) {
                            matrices.method_22904(0.0, 0.2, 0.0);
                        }
                        if (item.method_31574(class_1802.field_8153) || item.method_31574(class_1802.field_8777) || item.method_31574(class_1802.field_8323)) {
                            this.vertVelocityYSlime = (float)((double)this.vertVelocityYSlime + (double)swingProgress * 0.03 * this.frameDelta * 30.0);
                            if ((player.method_18798().method_1033() > 0.09 && player.method_24828() || player.method_5681() || player.method_20448() || player.method_6101() && !player.method_24828()) && ((Boolean)this.field_4050.field_1690.method_42448().method_41753()).booleanValue()) {
                                Random random = new Random();
                                boolean randomBoolean = random.nextBoolean();
                                this.vertVelocityYSlime += (float)(-0.05 * player.method_18798().method_1033() * this.frameDelta * 30.0);
                            }
                            matrices.method_22905(1.0f, 1.0f + this.vertAngleYSlime * -2.0f, 1.0f);
                        }
                    }
                    if (item.method_31573(class_3489.field_42615)) {
                        matrices.method_22904(0.07 * (double)l, 0.0, 0.05);
                        matrices.method_22907(class_7833.field_40716.rotationDegrees((float)(90 * l)));
                        matrices.method_22907(class_7833.field_40714.rotationDegrees(-15.0f));
                    }
                    if (item.method_31574(class_1802.field_8810)) {
                        player.method_37908().method_8406((class_2394)class_2398.field_11246, player.method_19538().method_10216(), player.method_19538().method_10214(), player.method_19538().method_10215(), 0.1, 0.1, 0.1);
                    }
                    HeldItemRendererAccessor acc = (HeldItemRendererAccessor)((Object)this);
                    acc.invokeRenderItem((class_1309)player, item, bl23 ? class_811.field_4322 : class_811.field_4321, !bl23, matrices, vertexConsumers, light);
                }
            }
            matrices.method_22909();
            matrices.method_22909();
            this.isAttacking = this.field_4050.field_1690.field_1886.method_1434();
        }
    }

    @Shadow
    protected abstract void method_3228(class_742 var1, float var2, float var3, class_1268 var4, float var5, class_1799 var6, float var7, class_4587 var8, class_4597 var9, int var10);

    @Shadow
    protected abstract void method_65816(float var1, float var2, class_4587 var3, int var4, class_1306 var5);
}
