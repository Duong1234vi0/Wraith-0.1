package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import ua.babka.combat.BestPoint;
import ua.babka.combat.RotationUtil;
import ua.babka.core.Rotation;
import ua.babka.event.EventEntitySpawn;
import ua.babka.event.EventObsidianPlace;
import ua.babka.event.EventRightClickBlock;
import ua.babka.event.EventTick;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.ui.component.RotationComponent;
import ua.babka.util.animation.SlownessManager;
import ua.babka.util.player.InventoryUtil;

@ModuleInformation(moduleName="Auto Explosion", moduleDesc="Автоматический взрывает кристал", moduleCategory=ModuleCategory.COMBAT)
public class AutoExplosion
extends Module {
    private final BooleanSetting saveSelf = new BooleanSetting("Не взрывать себя", false);
    private final BooleanSetting saveFriend = new BooleanSetting("Не взрывать друзей", false);
    private final BooleanSetting saveResources = new BooleanSetting("Не бахать ресы", false);
    private final BooleanSetting crystalOnRMB = new BooleanSetting("Взрывать по ПКМ", true);
    private BlockPos obsidianPos;
    private Entity entityToAttack;
    private int prevSlot = -1;
    private int ticksToDisableRightClicks;
    private final Set<BlockPos> myCrystalPlaces = new HashSet<BlockPos>();

    @Subscribe
    private void onObsidianPlace(EventObsidianPlace e2) {
        int crystalSlot = InventoryUtil.searchItemHotbar(Items.END_CRYSTAL);
        if (crystalSlot == -1) {
            return;
        }
        if (this.obsidianPos == null && this.condition(e2.getBlockPos())) {
            this.obsidianPos = e2.getBlockPos();
            this.ticksToDisableRightClicks = 5;
        }
    }

    @Subscribe
    private void onTick(EventTick e2) {
        Rotation rotation;
        if (this.ticksToDisableRightClicks > 0) {
            --this.ticksToDisableRightClicks;
        }
        if (this.entityToAttack != null) {
            if (this.mc.player.getEyePos().distanceTo(BestPoint.getNearestPoint(this.entityToAttack)) < 3.0) {
                rotation = new Rotation(RotationUtil.sample(BestPoint.getNearestPoint(this.entityToAttack)));
                RotationComponent.update(rotation, 360.0f, 360.0f, 360.0f, 360.0f, 0, 55, false);
                SlownessManager.addTimeTask(new SlownessManager.TimeTask(50L, () -> {
                    if (this.entityToAttack == null) {
                        return;
                    }
                    this.mc.interactionManager.attackEntity((PlayerEntity)this.mc.player, this.entityToAttack);
                    this.mc.player.swingHand(Hand.MAIN_HAND);
                    this.entityToAttack = null;
                }, true));
            } else {
                this.entityToAttack = null;
            }
        }
        if (this.obsidianPos == null) {
            return;
        }
        rotation = new Rotation(Vec3d.ofCenter((Vec3i)this.obsidianPos));
        RotationComponent.update(rotation, 360.0f, 360.0f, 360.0f, 360.0f, 0, 55, false);
        int slot = InventoryUtil.searchItemHotbar(Items.END_CRYSTAL);
        SlownessManager.addTimeTask(new SlownessManager.TimeTask(5L, () -> {
            HitResult patt0$temp;
            if (slot != -1 && (patt0$temp = this.mc.crosshairTarget) instanceof BlockHitResult) {
                BlockHitResult hitResult = (BlockHitResult)patt0$temp;
                if (this.prevSlot == -1) {
                    this.prevSlot = this.mc.player.getInventory().selectedSlot;
                }
                this.mc.player.getInventory().selectedSlot = slot;
                BlockPos placePos = hitResult.getBlockPos();
                this.myCrystalPlaces.add(placePos);
                this.mc.interactionManager.interactBlock(this.mc.player, Hand.MAIN_HAND, hitResult);
                SlownessManager.addTimeTask(new SlownessManager.TimeTask(5L, () -> {
                    if (this.prevSlot != -1) {
                        this.mc.player.getInventory().selectedSlot = this.prevSlot;
                    }
                    this.prevSlot = -1;
                }, true));
                this.obsidianPos = null;
            }
        }, true));
    }

    @Subscribe
    private void onRightClick(EventRightClickBlock e2) {
        if (this.mc.player.getMainHandStack().getItem() == Items.END_CRYSTAL) {
            BlockPos placePos = e2.getHitResult().getBlockPos().up();
            this.myCrystalPlaces.add(placePos);
            return;
        }
        if (!this.crystalOnRMB.getValue()) {
            return;
        }
        BlockPos clicked = e2.getHitResult().getBlockPos();
        if (this.mc.world.getBlockState(clicked).getBlock() != Blocks.OBSIDIAN) {
            return;
        }
        if (!this.mc.world.getBlockState(clicked.up()).isAir()) {
            return;
        }
        if (InventoryUtil.searchItemHotbar(Items.END_CRYSTAL) == -1) {
            return;
        }
        e2.setCancelled(true);
        this.obsidianPos = clicked;
        this.ticksToDisableRightClicks = 5;
    }

    @Subscribe
    private void onEntitySpawn(EventEntitySpawn e2) {
        Entity entity = e2.getEntity();
        if (!(entity instanceof EndCrystalEntity)) {
            return;
        }
        EndCrystalEntity crystal = (EndCrystalEntity)entity;
        BlockPos crystalPos = crystal.getBlockPos();
        if (!this.myCrystalPlaces.contains(crystalPos)) {
            return;
        }
        this.entityToAttack = crystal;
        this.myCrystalPlaces.remove(crystalPos);
    }

    private boolean condition(BlockPos blockPos) {
        if (this.saveSelf.getValue() && this.mc.player.getY() > (double)blockPos.getY()) {
            return false;
        }
        for (Entity entity : this.mc.world.getEntities()) {
            PlayerEntity player;
            if (entity == this.mc.player) continue;
            if (this.saveResources.getValue() && entity instanceof ItemEntity) {
                ItemEntity item = (ItemEntity)entity;
                double dx = Math.abs(item.getX() - (double)blockPos.getX());
                double dy = Math.abs(item.getY() - (double)blockPos.getY());
                double dz = Math.abs(item.getZ() - (double)blockPos.getZ());
                if (dx < 4.0 && dy < 2.0 && dz < 4.0) {
                    return false;
                }
            }
            if (!(entity instanceof PlayerEntity) || !FriendRepository.isFriend((player = (PlayerEntity)entity).getNameForScoreboard()) || !this.saveFriend.getValue() || !(player.getY() > (double)blockPos.getY())) continue;
            return false;
        }
        return true;
    }

    @Override
    public void onDisable() {
        this.obsidianPos = null;
        this.entityToAttack = null;
        this.ticksToDisableRightClicks = 0;
        super.onDisable();
    }

    public int getTicksToDisableRightClicks() {
        return this.ticksToDisableRightClicks;
    }
}

