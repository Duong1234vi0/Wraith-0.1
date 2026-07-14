package ua.babka.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.ModeSetting;
import ua.babka.util.player.MoveUtil;

@ModuleInformation(moduleName="No Web", moduleDesc="Убирает замедление от паутины", moduleCategory=ModuleCategory.MOVEMENT)
public class NoWeb
extends Module {
    private final ModeSetting mode = new ModeSetting("Мод", "Обычный", "Обычный", "Тест");

    @Subscribe
    private void onUpdate(EventPlayerUpdate e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!(!this.mode.is("Обычный") || this.mc.player.isSneaking() && this.mc.player.isOnGround())) {
            double z2;
            double x2;
            boolean headInWeb = false;
            boolean feetInWeb = false;
            for (x2 = -0.295; x2 <= 0.295; x2 += 0.05) {
                block1: for (z2 = -0.295; z2 <= 0.295; z2 += 0.05) {
                    for (double y2 = (double)this.mc.player.getStandingEyeHeight(); y2 >= 0.0; y2 -= 0.1) {
                        BlockPos headPos = BlockPos.ofFloored((double)(this.mc.player.getX() + x2), (double)(this.mc.player.getY() + y2), (double)(this.mc.player.getZ() + z2));
                        if (this.mc.world.getBlockState(headPos).getBlock() != Blocks.COBWEB) continue;
                        headInWeb = true;
                        continue block1;
                    }
                }
            }
            if (!headInWeb) {
                block3: for (x2 = -0.295; x2 <= 0.295; x2 += 0.05) {
                    for (z2 = -0.295; z2 <= 0.295; z2 += 0.05) {
                        BlockPos pos = BlockPos.ofFloored((double)(this.mc.player.getX() + x2), (double)this.mc.player.getY(), (double)(this.mc.player.getZ() + z2));
                        if (this.mc.world.getBlockState(pos).getBlock() != Blocks.COBWEB) continue;
                        feetInWeb = true;
                        continue block3;
                    }
                }
            }
            BlockPos aboveHeadPos = BlockPos.ofFloored((double)this.mc.player.getX(), (double)(this.mc.player.getY() + (double)this.mc.player.getStandingEyeHeight() + (double)0.2f), (double)this.mc.player.getZ());
            if (!headInWeb && !feetInWeb && this.mc.world.getBlockState(aboveHeadPos).getBlock() == Blocks.COBWEB) {
                headInWeb = true;
            }
            if (headInWeb || feetInWeb) {
                if (this.mc.options.jumpKey.isPressed()) {
                    this.mc.player.setVelocity(0.0, 0.8, 0.0);
                } else if (this.mc.options.sneakKey.isPressed()) {
                    this.mc.player.setVelocity(0.0, -0.8, 0.0);
                } else {
                    this.mc.player.setVelocity(0.0, 0.0, 0.0);
                }
                MoveUtil.setMotion(0.21);
            }
        }
        if (this.mode.is("Тест")) {
            boolean cobweb = false;
            Box box = this.mc.player.getBoundingBox();
            for (BlockPos pos : BlockPos.iterate((int)MathHelper.floor((double)box.minX), (int)MathHelper.floor((double)box.minY), (int)MathHelper.floor((double)box.minZ), (int)MathHelper.floor((double)box.maxX), (int)MathHelper.floor((double)box.maxY), (int)MathHelper.floor((double)box.maxZ))) {
                if (!this.mc.world.getBlockState(pos).isOf(Blocks.COBWEB)) continue;
                cobweb = true;
            }
            if (cobweb) {
                Vec3d velocity = this.mc.player.getVelocity();
                float yaw = this.mc.player.getYaw();
                double forward = 0.0;
                double strafe = 0.0;
                if (this.mc.player.input.playerInput.comp_3159()) {
                    forward += 1.0;
                }
                if (this.mc.player.input.playerInput.comp_3160()) {
                    forward -= 1.0;
                }
                if (this.mc.player.input.playerInput.comp_3161()) {
                    strafe += 1.0;
                }
                if (this.mc.player.input.playerInput.comp_3162()) {
                    strafe -= 1.0;
                }
                if (forward != 0.0 || strafe != 0.0) {
                    if (forward != 0.0) {
                        if (strafe > 0.0) {
                            yaw += (float)(forward > 0.0 ? -45 : 45);
                        } else if (strafe < 0.0) {
                            yaw += (float)(forward > 0.0 ? 45 : -45);
                        }
                        strafe = 0.0;
                        forward = forward > 0.0 ? 1.0 : -1.0;
                    }
                    double movementYaw = Math.toDegrees(Math.atan2(strafe, forward)) + (double)yaw;
                    yaw = (float)((movementYaw % 360.0 + 360.0) % 360.0);
                }
                float result = 0.63f;
                if (!(yaw >= 313.0f && yaw <= 317.0f || yaw >= 223.0f && yaw <= 227.0f || yaw >= 133.0f && yaw <= 137.0f || yaw >= 43.0f && yaw <= 47.0f)) {
                    if (!(yaw >= 311.0f && yaw <= 319.0f || yaw >= 221.0f && yaw <= 229.0f || yaw >= 131.0f && yaw <= 139.0f || yaw >= 41.0f && yaw <= 49.0f)) {
                        if (!(yaw >= 310.8f && yaw <= 320.8f || yaw >= 220.8f && yaw <= 230.8f || yaw >= 130.8f && yaw <= 140.8f || yaw >= 40.8f && yaw <= 50.8f)) {
                            if (!(yaw >= 308.7f && yaw <= 322.7f || yaw >= 218.7f && yaw <= 232.7f || yaw >= 128.7f && yaw <= 142.7f || yaw >= 38.7f && yaw <= 52.7f)) {
                                if (!(yaw >= 306.5f && yaw <= 324.5f || yaw >= 216.5f && yaw <= 234.5f || yaw >= 126.5f && yaw <= 144.5f || yaw >= 36.5f && yaw <= 54.5f)) {
                                    if (yaw >= 304.0f && yaw <= 327.0f || yaw >= 214.0f && yaw <= 237.0f || yaw >= 124.0f && yaw <= 147.0f || yaw >= 34.0f && yaw <= 57.0f) {
                                        result = 0.75f;
                                    }
                                } else {
                                    result = 0.79f;
                                }
                            } else {
                                result = 0.81f;
                            }
                        } else {
                            result = 0.83f;
                        }
                    } else {
                        result = 0.85f;
                    }
                } else {
                    result = 0.88f;
                }
                if (!this.mc.options.jumpKey.isPressed()) {
                    if (this.mc.options.sneakKey.isPressed()) {
                        this.mc.player.setVelocity(velocity.x, -3.6, velocity.z);
                    } else {
                        this.mc.player.setVelocity(velocity.x, 0.0, velocity.z);
                    }
                } else {
                    this.mc.player.setVelocity(velocity.x, forward == 0.0 && strafe == 0.0 ? 1.4 : 1.2, velocity.z);
                }
                MoveUtil.setMotion(result);
            }
        }
    }
}

