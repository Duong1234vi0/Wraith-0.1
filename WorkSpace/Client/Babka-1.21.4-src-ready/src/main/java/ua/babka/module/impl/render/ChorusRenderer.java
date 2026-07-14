package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;

@ModuleInformation(moduleName="Chorus Renderer", moduleDesc="Показывает блоки куда может тепнуть хорус", moduleCategory=ModuleCategory.RENDER)
public class ChorusRenderer
extends Module {
    private static final int RANGE = 8;
    private static final int SIZE = 17;
    private static final int MAX_FALL = 4;
    private BlockPos lastCenter;
    private final List<BlockPos> validCache = new ArrayList<BlockPos>();
    private final float[] densityCache = new float[4913];
    private int maxDensity;

    @Subscribe
    public void onWorldRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.mc.player.getMainHandStack().getItem() != Items.CHORUS_FRUIT && this.mc.player.getOffHandStack().getItem() != Items.CHORUS_FRUIT) {
            return;
        }
        BlockPos center = BlockPos.ofFloored((Position)this.mc.player.getPos());
        if (!center.equals((Object)this.lastCenter)) {
            this.lastCenter = center;
            this.recalc(center);
        }
        if (this.validCache.isEmpty()) {
            return;
        }
        Vec3d cam = this.mc.gameRenderer.getCamera().getPos();
        MatrixStack ms = e2.getMatrixStack();
        Matrix4f mat = ms.peek().getPositionMatrix();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_COLOR);
        BufferBuilder buf = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        Vec3d playerPos = this.mc.player.getPos();
        double maxDist = Math.sqrt(3.0) * 8.0;
        for (int i2 = 0; i2 < this.validCache.size(); ++i2) {
            BlockPos pos = this.validCache.get(i2);
            double dist = Math.sqrt(pos.getSquaredDistance((Position)playerPos));
            float chance = 0.5f * (1.0f - (float)(dist / maxDist)) + 0.5f * (this.maxDensity > 0 ? this.densityCache[i2] / (float)this.maxDensity : 0.0f);
            float r2 = 2.0f * (1.0f - chance);
            float g2 = 2.0f * chance;
            float b2 = 0.0f;
            float a2 = MathHelper.clamp((float)(0.15f + chance * 0.35f), (float)0.0f, (float)0.5f);
            float x1 = (float)((double)pos.getX() - cam.x);
            float y1 = (float)((double)pos.getY() - cam.y);
            float z1 = (float)((double)pos.getZ() - cam.z);
            buf.vertex(mat, x1, y1 + 1.0f, z1).color(r2, g2, b2, a2);
            buf.vertex(mat, x1, y1 + 1.0f, z1 + 1.0f).color(r2, g2, b2, a2);
            buf.vertex(mat, x1 + 1.0f, y1 + 1.0f, z1 + 1.0f).color(r2, g2, b2, a2);
            buf.vertex(mat, x1 + 1.0f, y1 + 1.0f, z1).color(r2, g2, b2, a2);
        }
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buf.end());
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private boolean boxContainsLiquid(Box box) {
        int minX = (int)Math.floor(box.minX);
        int minY = (int)Math.floor(box.minY);
        int minZ = (int)Math.floor(box.minZ);
        int maxX = (int)Math.ceil(box.maxX);
        int maxY = (int)Math.ceil(box.maxY);
        int maxZ = (int)Math.ceil(box.maxZ);
        for (int x2 = minX; x2 < maxX; ++x2) {
            for (int y2 = minY; y2 < maxY; ++y2) {
                for (int z2 = minZ; z2 < maxZ; ++z2) {
                    if (this.mc.world.getFluidState(new BlockPos(x2, y2, z2)).isEmpty()) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private BlockPos findGroundBelow(BlockPos pos) {
        int y2 = pos.getY();
        for (int i2 = 0; i2 <= 4; ++i2) {
            BlockPos check = new BlockPos(pos.getX(), y2 - i2, pos.getZ());
            BlockState bs = this.mc.world.getBlockState(check);
            if (!bs.blocksMovement() || bs.isLiquid()) continue;
            return check;
        }
        return null;
    }

    private void recalc(BlockPos center) {
        this.validCache.clear();
        this.maxDensity = 0;
        boolean[][][] validGrid = new boolean[17][17][17];
        HashSet<BlockPos> dedup = new HashSet<BlockPos>();
        Box playerBox = this.mc.player.getBoundingBox();
        Vec3d playerPos = this.mc.player.getPos();
        for (int dx = 0; dx < 17; ++dx) {
            for (int dz = 0; dz < 17; ++dz) {
                for (int dy = 0; dy < 17; ++dy) {
                    double targetZ;
                    double targetY;
                    double targetX;
                    Box testBox;
                    BlockPos pos = center.add(dx - 8, dy - 8, dz - 8);
                    BlockPos ground = this.findGroundBelow(pos);
                    if (ground == null || dedup.contains(ground) || !this.mc.world.isSpaceEmpty((Entity)this.mc.player, testBox = playerBox.offset((targetX = (double)ground.getX() + 0.5) - playerPos.x, (targetY = (double)ground.getY() + 1.0) - playerPos.y, (targetZ = (double)ground.getZ() + 0.5) - playerPos.z)) || this.boxContainsLiquid(testBox)) continue;
                    int gx = ground.getX() - center.getX() + 8;
                    int gz = ground.getZ() - center.getZ() + 8;
                    int gy = ground.getY() - center.getY() + 8;
                    if (gx < 0 || gx >= 17 || gz < 0 || gz >= 17 || gy < 0 || gy >= 17) continue;
                    validGrid[gx][gz][gy] = true;
                    dedup.add(ground);
                    this.validCache.add(ground);
                    this.densityCache[this.validCache.size() - 1] = 0.0f;
                }
            }
        }
        for (int i2 = 0; i2 < this.validCache.size(); ++i2) {
            BlockPos pos = this.validCache.get(i2);
            int px = pos.getX() - center.getX() + 8;
            int pz = pos.getZ() - center.getZ() + 8;
            int py = pos.getY() - center.getY() + 8;
            int count = 0;
            for (int dx = -2; dx <= 2; ++dx) {
                int nx = px + dx;
                if (nx < 0 || nx >= 17) continue;
                for (int dz = -2; dz <= 2; ++dz) {
                    int nz = pz + dz;
                    if (nz < 0 || nz >= 17) continue;
                    for (int dy = -2; dy <= 2; ++dy) {
                        int ny;
                        if (dx == 0 && dy == 0 && dz == 0 || (ny = py + dy) < 0 || ny >= 17 || !validGrid[nx][nz][ny]) continue;
                        ++count;
                    }
                }
            }
            this.densityCache[i2] = count;
            if (count <= this.maxDensity) continue;
            this.maxDensity = count;
        }
    }
}

