package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import org.joml.Matrix4f;
import ua.babka.event.EventAttack;
import ua.babka.event.EventPopTotem;
import ua.babka.event.EventTick;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;

@ModuleInformation(moduleName="Particles", moduleDesc="Частицы при событиях", moduleCategory=ModuleCategory.RENDER)
public class Particle
extends Module {
    private static final Identifier STAR_TEXTURE = Identifier.of((String)"mre", (String)"images/glow.png");
    private final ModeListSetting reason = new ModeListSetting("Добавлять при", new BooleanSetting("Бездействии", false), new BooleanSetting("Беге", false), new BooleanSetting("Ударе", true), new BooleanSetting("Падении перла", false), new BooleanSetting("Падении трезубца", false), new BooleanSetting("Сносе тотема", true));
    private final SliderSetting count = new SliderSetting("Количество", 10.0, 2.0, 40.0, 1.0);
    private final BooleanSetting glow = new BooleanSetting("Свечение", true);
    private final ArrayList<ParticleData> particles = new ArrayList();
    private final Random rnd = new Random();

    @Override
    public void onDisable() {
        this.particles.clear();
        super.onDisable();
    }

    private int getParticleColor() {
        return ColorProvider.getThemeColor();
    }

    private boolean isPositionInBlock(Vec3d position) {
        if (this.mc.world == null || this.mc.player == null) {
            return true;
        }
        BlockPos blockPos = BlockPos.ofFloored((Position)position);
        if (this.mc.world.getBlockState(blockPos).isSolidBlock((BlockView)this.mc.world, blockPos)) {
            return true;
        }
        BlockHitResult result = this.mc.world.raycast(new RaycastContext(new Vec3d(this.mc.player.getX(), this.mc.player.getY() + (double)this.mc.player.getStandingEyeHeight(), this.mc.player.getZ()), position, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)this.mc.player));
        return result.getType() == HitResult.Type.BLOCK;
    }

    private float random(float min, float max) {
        return min + this.rnd.nextFloat() * (max - min);
    }

    private boolean isMoving() {
        return this.mc.player != null && (this.mc.player.input.playerInput.comp_3159() || this.mc.player.input.playerInput.comp_3160() || this.mc.player.input.playerInput.comp_3161() || this.mc.player.input.playerInput.comp_3162());
    }

    @Subscribe
    public void onAttack(EventAttack event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.reason.isEnabled("Ударе")) {
            return;
        }
        Entity target = event.getEntity();
        if (target == null) {
            return;
        }
        for (int i2 = 0; i2 < 35; ++i2) {
            double tz;
            double ty;
            double tx = target.getX() + (double)this.random(-0.4f, 0.4f);
            if (this.isPositionInBlock(new Vec3d(tx, ty = target.getY() + (double)this.random(-0.4f, target.getHeight() + 0.4f), tz = target.getZ() + (double)this.random(-0.4f, 0.4f)))) continue;
            Vec3d velocity = new Vec3d((double)(this.random(-0.8f, 0.8f) * 2.0f * 0.075f), (double)(this.random(-0.25f, 1.4f) * 0.075f), (double)(this.random(-0.8f, 0.8f) * 2.0f * 0.075f));
            this.addParticle(tx, ty, tz, velocity, this.getParticleColor(), 0.3f, (long)this.random(1000.0f, 1200.0f), 0.5f, 7.0E-4);
        }
    }

    @Subscribe
    public void onPopTotem(EventPopTotem event) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (!this.reason.isEnabled("Сносе тотема")) {
            return;
        }
        PlayerEntity entity = event.getPlayer();
        double cx = entity.getX();
        double cy = entity.getY() + (double)entity.getHeight() / 2.0;
        double cz = entity.getZ();
        for (int i2 = 0; i2 < 50; ++i2) {
            double sz;
            double sy;
            double theta = this.rnd.nextDouble() * 2.0 * Math.PI;
            double phi = this.rnd.nextDouble() * Math.PI;
            double spd = (this.rnd.nextDouble() * 0.5 + 0.5) * 0.1;
            double vx = Math.sin(phi) * Math.cos(theta) * spd;
            double vy = Math.sin(phi) * Math.sin(theta) * spd;
            double vz = Math.cos(phi) * spd;
            double sx = cx + (double)this.random(-0.3f, 0.3f);
            if (this.isPositionInBlock(new Vec3d(sx, sy = cy + (double)this.random(-0.3f, 0.3f), sz = cz + (double)this.random(-0.3f, 0.3f)))) continue;
            int color = this.rnd.nextDouble() < 0.7 ? -16711936 : -256;
            this.addParticle(sx, sy, sz, new Vec3d(vx, vy, vz), color, 0.3f, (long)this.random(1500.0f, 2000.0f), 2.0f, 5.0E-5);
        }
    }

    @Subscribe
    public void onTick(EventTick event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        int particleCount = (int)this.count.getValue();
        if (this.reason.isEnabled("Бездействии")) {
            Vec3d base = new Vec3d(this.mc.player.getX(), this.mc.player.getY() + (double)this.mc.player.getHeight() / 2.0, this.mc.player.getZ());
            for (int i2 = 0; i2 < particleCount; ++i2) {
                double sz;
                double sy;
                double distance = this.random(7.0f, 35.0f);
                double angle = Math.toRadians(this.random(0.0f, 360.0f));
                double height = this.random(-7.0f, 25.0f);
                double sx = base.x + Math.cos(angle) * distance;
                if (this.isPositionInBlock(new Vec3d(sx, sy = base.y + height, sz = base.z + Math.sin(angle) * distance))) continue;
                double spd = this.rnd.nextDouble() < 0.8 ? (double)this.random(0.015f, 0.03f) : 0.125;
                double phi = Math.toRadians(this.random(0.0f, 360.0f));
                Vec3d vel = new Vec3d(Math.cos(phi) * spd, (double)this.random((float)(-spd * 0.1), (float)(spd * 0.1)), Math.sin(phi) * spd);
                this.addParticle(sx, sy, sz, vel, this.getParticleColor(), 0.3f, (long)this.random(1500.0f, 2000.0f), 3.0f, 5.0E-5);
            }
        }
        if (this.reason.isEnabled("Беге") && this.isMoving()) {
            double pz;
            double py;
            Vec3d motion = this.mc.player.getVelocity();
            double spd = Math.sqrt(motion.x * motion.x + motion.z * motion.z);
            Vec3d direction = spd < 0.01 ? this.mc.player.getRotationVector().multiply(-1.0) : (this.mc.player.isGliding() ? motion.normalize().multiply(-1.0) : new Vec3d(-motion.x / spd, 0.0, -motion.z / spd));
            double distBehind = (this.mc.player.isGliding() ? 1.2 : 0.5) + (spd > 0.1 ? spd * 1.5 : 0.0);
            double px = this.mc.player.getX() + direction.x * distBehind + (double)this.random(-0.35f, 0.35f);
            if (!this.isPositionInBlock(new Vec3d(px, py = this.mc.player.isGliding() ? this.mc.player.getY() + (double)this.mc.player.getHeight() / 2.0 + direction.y * distBehind + (double)this.random(-0.35f, 0.35f) : this.mc.player.getY() + (double)this.random(0.2f, this.mc.player.getHeight() + 0.1f), pz = this.mc.player.getZ() + direction.z * distBehind + (double)this.random(-0.35f, 0.35f)))) {
                Vec3d vel = direction.multiply(0.075).add((double)this.random(-0.01f, 0.01f), (double)this.random(-0.05f, 0.01f), (double)this.random(-0.01f, 0.01f)).multiply(0.1);
                this.addParticle(px, py, pz, vel, this.getParticleColor(), 0.3f, (long)this.random(1500.0f, 2000.0f), 3.0f, 5.0E-5);
            }
        }
        boolean trackPearls = this.reason.isEnabled("Падении перла");
        boolean trackTridents = this.reason.isEnabled("Падении трезубца");
        if ((trackPearls || trackTridents) && this.mc.world != null) {
            Box searchBox = this.mc.player.getBoundingBox().expand(100.0);
            List entities = this.mc.world.getOtherEntities(null, searchBox, e2 -> true);
            for (Entity entity : entities) {
                TridentEntity trident;
                EnderPearlEntity pearl;
                if (trackPearls && entity instanceof EnderPearlEntity && !(pearl = (EnderPearlEntity)entity).isOnGround()) {
                    this.createProjectileParticles(pearl.getPos(), 1);
                }
                if (!trackTridents || !(entity instanceof TridentEntity) || !((trident = (TridentEntity)entity).getVelocity().lengthSquared() > 0.01)) continue;
                this.createProjectileParticles(trident.getPos(), 1);
            }
        }
    }

    private void createProjectileParticles(Vec3d position, int cnt) {
        int i2 = 0;
        while ((double)i2 < (double)cnt * 2.5) {
            double dy = this.random(0.1f, 0.35f);
            Vec3d pos = new Vec3d(position.x, position.y + dy, position.z);
            if (!this.isPositionInBlock(pos)) {
                float sMin = this.random(0.015f, 0.0375f);
                float sMax = this.random(0.05f, 0.075f);
                double sf = this.random(sMin, sMax);
                double sfY = sf * 0.4;
                double angle = Math.toRadians(this.random(0.0f, 360.0f));
                Vec3d vel = new Vec3d(Math.cos(angle) * sf, (double)this.random((float)(-sfY), (float)sfY), Math.sin(angle) * sf);
                this.addParticle(pos.x, pos.y, pos.z, vel, this.getParticleColor(), 0.25f, (long)this.random(2400.0f, 2800.0f), 2.0f, 5.0E-5);
            }
            ++i2;
        }
    }
    private void addParticle(double x2, double y2, double z2, Vec3d velocity, int color, float size, long lifeTime, float smooth, double gravity) {
        if (ParticleData.checkCollision(x2, y2, z2, size, this.mc)) {
            ArrayList<ParticleData> arrayList = this.particles;
            synchronized (arrayList) {
                this.particles.add(new ParticleData(new Vec3d(x2, y2, z2), velocity, color, size, lifeTime, smooth, gravity, STAR_TEXTURE));
            }
        }
    }
    @Subscribe
    public void onWorldRender(EventWorldRender event) {
        ArrayList<ParticleData> renderList;
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        ArrayList<ParticleData> arrayList = this.particles;
        synchronized (arrayList) {
            this.particles.removeIf(ParticleData::isDead);
        }
        if (this.particles.isEmpty()) {
            return;
        }
        MatrixStack matrices = event.getMatrixStack();
        Vec3d camera = this.mc.gameRenderer.getCamera().getPos();
        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        if (this.glow.getValue()) {
            RenderSystem.blendFunc((int)770, (int)1);
        } else {
            RenderSystem.defaultBlendFunc();
        }
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.setShaderTexture((int)0, (Identifier)STAR_TEXTURE);
        ArrayList<ParticleData> arrayList2 = this.particles;
        synchronized (arrayList2) {
            renderList = new ArrayList<ParticleData>(this.particles);
        }
        for (ParticleData p2 : renderList) {
            p2.update(this.mc);
            this.renderParticle(matrices, camera, p2);
        }
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private void renderParticle(MatrixStack matrices, Vec3d camera, ParticleData p2) {
        double rx = p2.position.x - camera.x;
        double ry = p2.position.y - camera.y;
        double rz = p2.position.z - camera.z;
        matrices.push();
        matrices.translate((float)rx, (float)ry, (float)rz);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-this.mc.gameRenderer.getCamera().getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(this.mc.gameRenderer.getCamera().getPitch()));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float half = p2.size / 2.0f;
        int alpha = (int)(p2.alpha * 255.0f);
        int r2 = p2.color >> 16 & 0xFF;
        int g2 = p2.color >> 8 & 0xFF;
        int b2 = p2.color & 0xFF;
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        buffer.vertex(matrix, -half, -half, 0.0f).texture(0.0f, 1.0f).color(r2, g2, b2, alpha);
        buffer.vertex(matrix, -half, half, 0.0f).texture(0.0f, 0.0f).color(r2, g2, b2, alpha);
        buffer.vertex(matrix, half, half, 0.0f).texture(1.0f, 0.0f).color(r2, g2, b2, alpha);
        buffer.vertex(matrix, half, -half, 0.0f).texture(1.0f, 1.0f).color(r2, g2, b2, alpha);
        BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        matrices.pop();
    }

    static class ParticleData {
        Vec3d position;
        Vec3d velocity;
        int color;
        float size;
        long lifeTime;
        long birthTime;
        float alpha = 1.0f;
        float smoothFactor;
        long lastUpdateNs;
        double gravity;
        Identifier texture = STAR_TEXTURE;

        ParticleData(Vec3d position, Vec3d velocity, int color, float size, long lifeTime, float smooth, double gravity, Identifier tex) {
            this.position = position;
            this.velocity = velocity;
            this.color = color;
            this.size = size;
            this.lifeTime = lifeTime;
            this.birthTime = System.currentTimeMillis();
            this.lastUpdateNs = System.nanoTime();
            this.smoothFactor = smooth;
            this.gravity = gravity;
            this.texture = tex;
        }

        boolean isDead() {
            return System.currentTimeMillis() - this.birthTime >= this.lifeTime;
        }

        void update(MinecraftClient mc) {
            long nowNs = System.nanoTime();
            double deltaSec = (double)(nowNs - this.lastUpdateNs) / 1.0E9;
            this.lastUpdateNs = nowNs;
            float progress = Math.min(1.0f, (float)(System.currentTimeMillis() - this.birthTime) / (float)this.lifeTime);
            double factor = Math.pow(1.0 - (double)progress, this.smoothFactor);
            double vx = this.velocity.x;
            double vy = this.velocity.y;
            double vz = this.velocity.z;
            double nx = this.position.x;
            double ny = this.position.y;
            double nz = this.position.z;
            double dt = deltaSec * 60.0;
            if (!ParticleData.checkCollision(nx += vx * factor * dt, this.position.y, this.position.z, this.size, mc)) {
                vx = -vx * 0.8;
                nx = this.position.x;
            }
            if (!ParticleData.checkCollision(nx, ny += vy * factor * dt, this.position.z, this.size, mc)) {
                vy = -vy * 1.5;
                ny = this.position.y;
            }
            if (!ParticleData.checkCollision(nx, ny, nz += vz * factor * dt, this.size, mc)) {
                vz = -vz * 0.8;
                nz = this.position.z;
            }
            this.position = new Vec3d(nx, ny, nz);
            this.velocity = new Vec3d(vx * 0.9999, vy * 0.9999 - this.gravity, vz * 0.9999);
            this.alpha = 1.0f - progress;
        }

        static boolean checkCollision(double x2, double y2, double z2, float size, MinecraftClient mc) {
            if (mc.world == null) {
                return false;
            }
            double half = (double)size * 0.5;
            int minX = MathHelper.floor((double)(x2 - half));
            int maxX = MathHelper.floor((double)(x2 + half));
            int minY = MathHelper.floor((double)(y2 - half));
            int maxY = MathHelper.floor((double)(y2 + half));
            int minZ = MathHelper.floor((double)(z2 - half));
            int maxZ = MathHelper.floor((double)(z2 + half));
            BlockPos.Mutable pos = new BlockPos.Mutable();
            for (int bx = minX; bx <= maxX; ++bx) {
                for (int by = minY; by <= maxY; ++by) {
                    for (int bz = minZ; bz <= maxZ; ++bz) {
                        pos.set(bx, by, bz);
                        BlockState state = mc.world.getBlockState((BlockPos)pos);
                        if (state.isAir() || !state.isSolidBlock((BlockView)mc.world, (BlockPos)pos)) continue;
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

