package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.AlternateBlockRenderer;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.ItemStackAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.LivingEntityAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.DispatcherStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelContext;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.LuaScriptCache;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ScriptHolder;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.ParticleRenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BellRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Locale;

@Mixin(ItemInHandRenderer.class)
public abstract class MixinHandMyItemsHeldItemRenderer {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Unique
    private static boolean hmi$mainHandSwitchEvent;
    @Unique
    private static boolean hmi$offHandSwitchEvent;
    @Unique
    private static Item hmi$previousMainHand = Items.AIR;
    @Unique
    private static Item hmi$previousOffHand = Items.AIR;
    @Unique
    private static boolean hmi$swingMainHand;
    @Unique
    private static boolean hmi$swingOffHand;
    @Unique
    private static float hmi$mainHandSwingProgress;
    @Unique
    private static float hmi$offHandSwingProgress;
    @Unique
    private static long hmi$observedStateGeneration = Long.MIN_VALUE;
    @Unique
    private static int hmi$swingDiagnosticCount;

    @Invoker("renderPlayerArm")
    protected abstract void hmi$renderPlayerArm(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int lightCoords,
        float inverseArmHeight,
        float attack,
        HumanoidArm arm
    );

    @Invoker("renderMap")
    protected abstract void hmi$renderMap(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int lightCoords,
        ItemStack itemStack
    );

    @WrapOperation(
        method = "renderHandsWithItems(FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/player/LocalPlayer;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"
        )
    )
    private void hmi$renderOverhaul(
        ItemInHandRenderer instance,
        AbstractClientPlayer player,
        float tickProgress,
        float pitch,
        InteractionHand hand,
        float vanillaSwingProgress,
        ItemStack renderedItem,
        float equipProgress,
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int light,
        Operation<Void> original
    ) {
        if (!HandMyItemsRuntime.isReady() || !this.hmi$runtimeReady(player, renderedItem)) {
            HandMyItemsRuntime.recordRendererDecision();
            original.call(
                instance,
                player,
                tickProgress,
                pitch,
                hand,
                vanillaSwingProgress,
                renderedItem,
                equipProgress,
                poseStack,
                submitNodeCollector,
                light
            );
            return;
        }
        HandMyItemsRuntime.recordRendererDecision();

        this.hmi$resetForRuntimeStateChange();

        // The target method suppresses all first-person rendering while scoping. Keeping the
        // suppression here avoids entering Wraith's ordinary hand transforms while HMI owns it.
        if (player.isScoping()) {
            return;
        }

        ((ItemStackAccessor) (Object) renderedItem).hMI5_0$setTransform(-1);
        boolean mainHand = hand == InteractionHand.MAIN_HAND;
        ItemStack item = this.hmi$hideOppositeBowHand(player, hand, renderedItem);
        if (mainHand) {
            hmi$mainHandSwitchEvent = item.getItem() != hmi$previousMainHand;
        } else {
            hmi$offHandSwitchEvent = item.getItem() != hmi$previousOffHand;
        }

        LivingEntityAccessor accessor = (LivingEntityAccessor) player;
        hmi$mainHandSwingProgress = accessor.hMI5_0$getMainHandSwingProgress(tickProgress);
        hmi$offHandSwingProgress = accessor.hMI5_0$getOffHandSwingProgress(tickProgress);
        hmi$swingMainHand = accessor.hMI5_0$getMHandEvent();
        hmi$swingOffHand = accessor.hMI5_0$getOHandEvent();
        float swingProgress = mainHand ? hmi$mainHandSwingProgress : hmi$offHandSwingProgress;
        boolean interact = mainHand ? accessor.hMI5_0$getMInteract() : accessor.hMI5_0$getOInteract();
        boolean blockBreaking = accessor.hMI5_0$getBlockBreak();
        HumanoidArm arm = mainHand ? player.getMainArm() : player.getMainArm().getOpposite();
        boolean rightArm = arm == HumanoidArm.RIGHT;

        if (hmi$swingDiagnosticCount < 20
            && (hmi$mainHandSwingProgress > 0.0F || hmi$offHandSwingProgress > 0.0F
                || hmi$swingMainHand || hmi$swingOffHand || interact
                || hmi$mainHandSwitchEvent || hmi$offHandSwitchEvent)) {
            hmi$swingDiagnosticCount++;
            HandMyItemsRuntime.diagnostic(
                "swing state mainProgress=" + hmi$mainHandSwingProgress
                    + " offProgress=" + hmi$offHandSwingProgress
                    + " previousMainProgress=" + accessor.hMI5_0$getMainHandSwingProgressPrevious()
                    + " previousOffProgress=" + accessor.hMI5_0$getOffHandSwingProgressPrevious()
                    + " mainSwinging=" + accessor.hMI5_0$isMainHandSwinging()
                    + " offSwinging=" + accessor.hMI5_0$isOffHandSwinging()
                    + " mainEvent=" + hmi$swingMainHand
                    + " offEvent=" + hmi$swingOffHand
                    + " interact=" + interact
                    + " blockBreaking=" + blockBreaking
                    + " mainSwitch=" + hmi$mainHandSwitchEvent
                    + " offSwitch=" + hmi$offHandSwitchEvent
                    + " deltaTime=" + LuaTestHMI.deltaTime
                    + " activeHand=" + hand
                    + " renderedHand=" + (mainHand ? "MAIN_HAND" : "OFF_HAND")
                    + " item=" + item.getItem()
            );
        }

        ArrayList<Particle> particles = this.hmi$particles();
        try {
            poseStack.pushPose();
            try {
                this.hmi$applyScenePose(
                    poseStack, item, rightArm, swingProgress, equipProgress, player, mainHand, hand,
                    interact, blockBreaking, particles
                );
                this.hmi$renderArm(
                    poseStack, submitNodeCollector, light, item, rightArm, swingProgress, equipProgress,
                    player, mainHand, hand, arm, interact, blockBreaking, particles
                );
                this.hmi$renderItemPath(
                    poseStack, submitNodeCollector, light, item, rightArm, swingProgress, equipProgress,
                    player, mainHand, hand, interact, blockBreaking, tickProgress, particles
                );
            } finally {
                poseStack.popPose();
            }

            if (mainHand) {
                hmi$previousMainHand = item.getItem();
            } else {
                hmi$previousOffHand = item.getItem();
            }

            poseStack.pushPose();
            try {
                ParticleRenderManager.draw(particles, poseStack, submitNodeCollector, "SCREEN", hand, light, player, tickProgress);
            } finally {
                poseStack.popPose();
            }
            LuaTestHMI.tickProgress = tickProgress;
        } catch (LuaScriptCache.ExecutionException exception) {
            // A failed script invalidates the runtime. The matrix was restored above, so the
            // original renderer can safely render this hand without a rigid HMI placeholder.
            original.call(
                instance,
                player,
                tickProgress,
                pitch,
                hand,
                vanillaSwingProgress,
                renderedItem,
                equipProgress,
                poseStack,
                submitNodeCollector,
                light
            );
        } finally {
            GlobalsStorage.offHandItem = Items.AIR.getDefaultInstance();
            GlobalsStorage.mainHandItem = Items.AIR.getDefaultInstance();
        }
    }

    @Unique
    private boolean hmi$runtimeReady(AbstractClientPlayer player, ItemStack item) {
        if (!HandMyItemsRuntime.isReady()
            || !(player instanceof LivingEntityAccessor) || !((Object) item instanceof ItemStackAccessor)) {
            return false;
        }
        if (this.minecraft.level == null
            || !ScriptHolder.primaryScriptsOperational()
            || ScriptHolder.handAddonsCache == null
            || ScriptHolder.handRelativeAddonsCache == null
            || ScriptHolder.itemAddonsCache == null
            || ScriptHolder.itemModelAddonsCache == null) {
            return false;
        }
        if (!((Object) this.minecraft instanceof MixinHandMyItemsMinecraftAccessor minecraftAccessor)) {
            return false;
        }
        return minecraftAccessor.hmi$getBlockModelResolver() instanceof AlternateBlockRenderer;
    }

    @Unique
    private void hmi$resetForRuntimeStateChange() {
        long stateGeneration = HandMyItemsRuntime.stateGeneration();
        if (stateGeneration == hmi$observedStateGeneration) {
            return;
        }

        hmi$mainHandSwitchEvent = false;
        hmi$offHandSwitchEvent = false;
        hmi$previousMainHand = Items.AIR;
        hmi$previousOffHand = Items.AIR;
        hmi$swingMainHand = false;
        hmi$swingOffHand = false;
        hmi$mainHandSwingProgress = 0.0F;
        hmi$offHandSwingProgress = 0.0F;
        hmi$swingDiagnosticCount = 0;
        GlobalsStorage.particles.clear();
        hmi$observedStateGeneration = stateGeneration;
    }

    @Unique
    @SuppressWarnings("unchecked")
    private ArrayList<Particle> hmi$particles() {
        // The original renderer owns one long-lived list. Reusing runtime storage also guarantees
        // that disabling the module clears particles before the next rendered frame.
        return (ArrayList<Particle>) GlobalsStorage.particles;
    }

    @Unique
    private ItemStack hmi$hideOppositeBowHand(AbstractClientPlayer player, InteractionHand hand, ItemStack item) {
        if (!player.isUsingItem() || player.getUsedItemHand() == hand) {
            return item;
        }

        ItemStack activeOtherHand = hand == InteractionHand.MAIN_HAND ? player.getOffhandItem() : player.getMainHandItem();
        ItemUseAnimation animation = activeOtherHand.getUseAnimation();
        if (animation == ItemUseAnimation.BOW
            || animation == ItemUseAnimation.CROSSBOW && !CrossbowItem.isCharged(activeOtherHand)) {
            return Items.AIR.getDefaultInstance();
        }
        return item;
    }

    @Unique
    private void hmi$applyScenePose(
        PoseStack poseStack,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        ArrayList<Particle> particles
    ) {
        ScriptHolder.handScriptCache.execute(
            poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
            LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
            hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
            interact, blockBreaking, particles
        );
        for (LuaScriptCache scriptCache : ScriptHolder.handAddonsCache) {
            scriptCache.execute(
                poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
                LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
                hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
                interact, blockBreaking, particles
            );
        }
        if (!item.isEmpty()) {
            poseStack.translate(0.0, -0.35, 0.2);
        }
    }

    @Unique
    private void hmi$applyHandRelativePose(
        PoseStack poseStack,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        ArrayList<Particle> particles
    ) {
        int side = rightArm ? 1 : -1;
        ScriptHolder.handRelativeScriptCache.execute(
            poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
            LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
            hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
            interact, blockBreaking, particles
        );
        for (LuaScriptCache scriptCache : ScriptHolder.handRelativeAddonsCache) {
            scriptCache.execute(
                poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
                LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
                hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
                interact, blockBreaking, particles
            );
        }
        if (!item.isEmpty()) {
            poseStack.translate(1.5 * side, -0.3, -0.6);
            poseStack.rotateAround(Axis.XP.rotationDegrees(15.0F), 0.5F * side, 0.5F, 0.5F);
            poseStack.rotateAround(Axis.YP.rotationDegrees(35.0F * side), 0.5F * side, 0.5F, 0.5F);
            poseStack.rotateAround(Axis.ZP.rotationDegrees(-65.0F * side), 0.5F * side, 0.5F, 0.5F);
            poseStack.scale(0.9F, 0.9F, 0.9F);
        }
    }

    @Unique
    private void hmi$applyItemPose(
        PoseStack poseStack,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        ArrayList<Particle> particles
    ) {
        int side = rightArm ? 1 : -1;
        poseStack.translate(0.5 * side, -0.15, -0.85);
        poseStack.rotateAround(Axis.XP.rotationDegrees(15.0F), 0.5F, 0.5F, 0.5F);
        poseStack.scale(0.9F, 0.9F, 0.9F);
        ScriptHolder.itemScriptCache.execute(
            poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
            LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
            hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
            interact, blockBreaking, particles
        );
        for (LuaScriptCache scriptCache : ScriptHolder.itemAddonsCache) {
            scriptCache.execute(
                poseStack, rightArm, GlobalsStorage.registry, swingProgress, item, player, hand, mainHand,
                LuaTestHMI.deltaTime, equipProgress, hmi$mainHandSwingProgress, hmi$offHandSwingProgress,
                hmi$mainHandSwitchEvent, hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand,
                interact, blockBreaking, particles
            );
        }
    }

    @Unique
    private void hmi$renderArm(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int light,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        HumanoidArm arm,
        boolean interact,
        boolean blockBreaking,
        ArrayList<Particle> particles
    ) {
        poseStack.pushPose();
        try {
            this.hmi$applyHandRelativePose(
                poseStack, item, rightArm, swingProgress, equipProgress, player, mainHand, hand,
                interact, blockBreaking, particles
            );
            int combinedLight = LightCoordsUtil.lightCoordsWithEmission(
                light,
                Block.byItem(item.getItem()).defaultBlockState().getLightEmission()
            );
            if (player.isInvisible()) {
                this.hmi$applyArmMatrices(poseStack, 0.0F, 0.0F, arm);
            } else {
                this.hmi$renderPlayerArm(poseStack, submitNodeCollector, combinedLight, 0.0F, 0.0F, arm);
            }
        } finally {
            poseStack.popPose();
        }
    }

    @Unique
    private void hmi$applyArmMatrices(PoseStack poseStack, float equipProgress, float swingProgress, HumanoidArm arm) {
        boolean rightArm = arm != HumanoidArm.LEFT;
        float side = rightArm ? 1.0F : -1.0F;
        float rootSwing = Mth.sqrt(swingProgress);
        float x = -0.3F * Mth.sin(rootSwing * Mth.PI);
        float y = 0.4F * Mth.sin(rootSwing * Mth.PI * 2.0F);
        float z = -0.4F * Mth.sin(swingProgress * Mth.PI);
        poseStack.translate(side * (x + 0.64000005F), y - 0.6F + equipProgress * -0.6F, z - 0.71999997F);
        poseStack.mulPose(Axis.YP.rotationDegrees(side * 45.0F));
        float zRotation = Mth.sin(swingProgress * swingProgress * Mth.PI);
        float yRotation = Mth.sin(rootSwing * Mth.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees(side * yRotation * 70.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(side * zRotation * -20.0F));
        poseStack.translate(side * -1.0F, 3.6F, 3.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(side * 120.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(200.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(side * -135.0F));
        poseStack.translate(side * 5.6F, 0.0F, 0.0F);
    }

    @Unique
    private void hmi$renderItemPath(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int light,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        float tickProgress,
        ArrayList<Particle> particles
    ) {
        poseStack.pushPose();
        try {
            BlockState blockState = Block.byItem(item.getItem()).defaultBlockState();
            if (this.hmi$shouldRenderAsBlock(item, blockState)) {
                this.hmi$renderBlockItem(
                    poseStack, submitNodeCollector, light, item, blockState, rightArm, equipProgress,
                    player, mainHand, hand, interact, blockBreaking, tickProgress, particles
                );
                return;
            }

            ItemUseAnimation animation = item.getUseAnimation();
            if (animation == ItemUseAnimation.BLOCK
                || animation == ItemUseAnimation.BRUSH
                || animation == ItemUseAnimation.TRIDENT
                || animation == ItemUseAnimation.SPEAR) {
                this.hmi$renderDirectModelItem(
                    poseStack, submitNodeCollector, light, item, rightArm, swingProgress, equipProgress,
                    player, mainHand, hand, interact, blockBreaking, particles
                );
                return;
            }

            this.hmi$applyItemPose(
                poseStack, item, rightArm, swingProgress, equipProgress, player, mainHand, hand,
                interact, blockBreaking, particles
            );
            if (item.has(DataComponents.MAP_ID)) {
                int side = rightArm ? 1 : -1;
                poseStack.pushPose();
                try {
                    poseStack.translate(-0.05 * side, 0.2, 0.1);
                    poseStack.mulPose(Axis.YP.rotationDegrees(-12.0F * side));
                    this.hmi$renderMap(poseStack, submitNodeCollector, light, item);
                } finally {
                    poseStack.popPose();
                }
            } else {
                ItemStack renderStack = this.hmi$replacementStack(mainHand, item);
                this.hmi$queueItemModel(item, rightArm, swingProgress, equipProgress, player, mainHand, hand, interact, blockBreaking);
                ((ItemInHandRenderer) (Object) this).renderItem(
                    player,
                    renderStack,
                    rightArm ? ItemDisplayContext.THIRD_PERSON_RIGHT_HAND : ItemDisplayContext.THIRD_PERSON_LEFT_HAND,
                    poseStack,
                    submitNodeCollector,
                    light
                );
            }

            if (rightArm) {
                LuaTestHMI.matricesMain.set(poseStack.last().pose());
            } else {
                LuaTestHMI.matricesOff.set(poseStack.last().pose());
            }
            poseStack.pushPose();
            try {
                ParticleRenderManager.draw(particles, poseStack, submitNodeCollector, "ITEM", hand, light, player, tickProgress);
            } finally {
                poseStack.popPose();
            }
        } finally {
            poseStack.popPose();
        }
    }

    @Unique
    private boolean hmi$shouldRenderAsBlock(ItemStack item, BlockState blockState) {
        return blockState.getBlock() != Blocks.AIR
            && !blockState.is(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            && !blockState.is(BlockTags.CROPS)
            && item.getUseAnimation() != ItemUseAnimation.EAT
            && !item.is(Items.REDSTONE)
            && !item.is(ItemTags.BANNERS)
            && !item.is(ItemTags.SKULLS)
            && GlobalsStorage.renderAsBlock.getOrDefault(item.getItem().toString(), true);
    }

    @Unique
    private void hmi$renderBlockItem(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int light,
        ItemStack item,
        BlockState initialState,
        boolean rightArm,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        float tickProgress,
        ArrayList<Particle> particles
    ) {
        int side = rightArm ? 1 : -1;
        this.hmi$applyItemPose(
            poseStack, item, rightArm, 0.0F, equipProgress, player, mainHand, hand,
            interact, blockBreaking, particles
        );
        poseStack.translate(0.22 * side, 0.25, 0.2);
        BlockState blockState = initialState;
        if (item.is(Items.LEVER) || blockState.is(BlockTags.BUTTONS)) {
            blockState = blockState.setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR);
        }
        String displayName = item.getHoverName().toString().toLowerCase(Locale.ROOT);
        if (displayName.contains("torch") || blockState.getBlock() instanceof LanternBlock || blockState.is(BlockTags.ALL_HANGING_SIGNS)) {
            poseStack.translate(-0.05 * side, 0.0, 0.0);
            poseStack.scale(1.75F, 1.75F, 1.75F);
        } else {
            poseStack.translate(-0.25 * side, -0.05, 0.0);
        }

        // This no-op push/pop exists in the reference path and is intentionally retained.
        poseStack.pushPose();
        try {
            poseStack.scale(0.3F, 0.3F, 0.3F);
            poseStack.translate(-0.9 * side, -0.45, -0.7);
        } finally {
            poseStack.popPose();
        }
        if (!rightArm) {
            poseStack.translate(-0.3F, 0.0F, 0.0F);
        }
        poseStack.scale(0.3F, 0.3F, 0.3F);
        poseStack.translate(-0.9 * side, -0.45, -0.7);

        AlternateBlockRenderer renderer = (AlternateBlockRenderer) (Object)
            ((MixinHandMyItemsMinecraftAccessor) (Object) this.minecraft).hmi$getBlockModelResolver();
        if (item.is(Items.BELL)) {
            this.hmi$renderBell(poseStack, submitNodeCollector, light);
            renderer.renderSingleBlockWithEmission(
                Blocks.BELL.defaultBlockState().setValue(BellBlock.ATTACHMENT, BellAttachType.CEILING),
                poseStack,
                submitNodeCollector,
                light,
                this.minecraft.level,
                player
            );
        } else {
            if (blockState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF)) {
                poseStack.pushPose();
                try {
                    poseStack.translate(0.0F, 1.0F, 0.0F);
                    renderer.renderSingleBlockWithEmission(
                        blockState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER),
                        poseStack,
                        submitNodeCollector,
                        light,
                        this.minecraft.level,
                        player
                    );
                } finally {
                    poseStack.popPose();
                }
            }
            renderer.renderSingleBlockWithEmission(
                blockState, poseStack, submitNodeCollector, light, this.minecraft.level, player
            );
        }

        poseStack.pushPose();
        try {
            if (!rightArm) {
                poseStack.translate(1.0F, 0.0F, 0.0F);
            }
            ParticleRenderManager.draw(particles, poseStack, submitNodeCollector, "ITEM", hand, light, player, tickProgress);
        } finally {
            poseStack.popPose();
        }
    }

    @Unique
    private void hmi$renderBell(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light) {
        BellBlockEntity bell = new BellBlockEntity(BlockPos.ZERO, Blocks.BELL.defaultBlockState());
        BlockEntityRenderer<BellBlockEntity, BellRenderState> renderer = this.minecraft
            .getBlockEntityRenderDispatcher()
            .getRenderer(bell);
        if (renderer == null) {
            return;
        }
        BellRenderState state = renderer.createRenderState();
        renderer.extractRenderState(bell, state, 0.0F, Vec3.ZERO, null);
        state.lightCoords = light;
        renderer.submit(state, poseStack, submitNodeCollector, new CameraRenderState());
    }

    @Unique
    private void hmi$renderDirectModelItem(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int light,
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking,
        ArrayList<Particle> particles
    ) {
        this.hmi$applyItemPose(
            poseStack, item, rightArm, swingProgress, equipProgress, player, mainHand, hand,
            interact, blockBreaking, particles
        );
        ItemStack renderStack = new ItemStack(item.getItem(), item.getCount());
        this.hmi$copyAppearanceComponents(item, renderStack);
        ItemStack replacement = mainHand ? GlobalsStorage.mainHandItem : GlobalsStorage.offHandItem;
        if (!replacement.is(Items.AIR)) {
            renderStack = replacement;
        }
        this.hmi$queueItemModel(item, rightArm, swingProgress, equipProgress, player, mainHand, hand, interact, blockBreaking);
        ((ItemInHandRenderer) (Object) this).renderItem(
            player,
            renderStack,
            rightArm ? ItemDisplayContext.THIRD_PERSON_RIGHT_HAND : ItemDisplayContext.THIRD_PERSON_LEFT_HAND,
            poseStack,
            submitNodeCollector,
            light
        );
    }

    @Unique
    private void hmi$queueItemModel(
        ItemStack item,
        boolean rightArm,
        float swingProgress,
        float equipProgress,
        AbstractClientPlayer player,
        boolean mainHand,
        InteractionHand hand,
        boolean interact,
        boolean blockBreaking
    ) {
        DispatcherStorage.setItem(item);
        ItemModelStorage.addData(
            new ItemModelContext(
                rightArm, swingProgress, player, hand, mainHand, LuaTestHMI.deltaTime, equipProgress,
                hmi$mainHandSwingProgress, hmi$offHandSwingProgress, hmi$mainHandSwitchEvent,
                hmi$offHandSwitchEvent, hmi$swingMainHand, hmi$swingOffHand, interact, blockBreaking, item
            ),
            item
        );
    }

    @Unique
    private ItemStack hmi$replacementStack(boolean mainHand, ItemStack original) {
        ItemStack replacement = mainHand ? GlobalsStorage.mainHandItem : GlobalsStorage.offHandItem;
        return replacement.is(Items.AIR) ? original : replacement;
    }

    @Unique
    private void hmi$copyAppearanceComponents(ItemStack source, ItemStack target) {
        if (source.has(DataComponents.ENCHANTMENTS)) {
            target.copyFrom(DataComponents.ENCHANTMENTS, source);
        }
        if (source.has(DataComponents.ITEM_MODEL)) {
            target.copyFrom(DataComponents.ITEM_MODEL, source);
        }
        if (source.has(DataComponents.CUSTOM_MODEL_DATA)) {
            target.copyFrom(DataComponents.CUSTOM_MODEL_DATA, source);
        }
        if (source.has(DataComponents.CUSTOM_DATA)) {
            target.copyFrom(DataComponents.CUSTOM_DATA, source);
        }
    }
}
