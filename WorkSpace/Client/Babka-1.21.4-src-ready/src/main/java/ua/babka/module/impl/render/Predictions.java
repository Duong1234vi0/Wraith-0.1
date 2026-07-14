package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.joml.Matrix4f;
import ua.babka.combat.RaytraceUtil;
import ua.babka.event.EventHUD;
import ua.babka.event.EventWorldRender;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.ProjectionUtil;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.MsdfFont;

@ModuleInformation(moduleName="Predictions", moduleDesc="Предсказание траектории снарядов", moduleCategory=ModuleCategory.RENDER)
public class Predictions
extends Module {
    private static final Identifier GLOW = Identifier.of((String)"mre", (String)"images/glow.png");
    private final ModeListSetting projectileFilter = new ModeListSetting("Снаряды", new BooleanSetting("Стрелы", true), new BooleanSetting("Перлы", true), new BooleanSetting("Трезубцы", true));
    private final List<SimulatedPath> pearlLandings = new ArrayList<SimulatedPath>();
    private final Set<Integer> friendPearlIds = new HashSet<Integer>();

    @Subscribe
    public void onWorldRender(EventWorldRender e2) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        float tickDelta = e2.getTickDelta();
        long now = System.currentTimeMillis();
        this.pearlLandings.clear();
        this.friendPearlIds.removeIf(id -> this.mc.world.getEntityById(id.intValue()) == null);
        MatrixStack matrices = e2.getMatrixStack();
        Camera camera = this.mc.gameRenderer.getCamera();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SrcFactor)GlStateManager.SrcFactor.SRC_ALPHA, (GlStateManager.DstFactor)GlStateManager.DstFactor.ONE);
        RenderSystem.setShaderTexture((int)0, (Identifier)GLOW);
        RenderSystem.setShader((ShaderProgramKey)ShaderProgramKeys.POSITION_TEX_COLOR);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        BufferBuilder buffer = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        for (Entity entity : this.getProjectiles()) {
            List<Vec3d> smoothPath;
            SimulatedPath simulated = this.simulatePath(entity, tickDelta);
            if (simulated.rawPoints.size() < 2 || (smoothPath = this.smoothPath(simulated.rawPoints)).size() < 2) continue;
            int startColor = this.getStartColor(entity);
            int endColor = this.getEndColor(entity);
            this.renderGlowPath(buffer, matrices, camera, smoothPath, startColor, endColor, now);
            if (simulated.hitPos == null) continue;
            this.renderImpactGlow(buffer, matrices, camera, simulated.hitPos, endColor, now);
            if (!(entity instanceof EnderPearlEntity)) continue;
            this.pearlLandings.add(simulated);
        }
        try {
            BufferRenderer.drawWithGlobalProgram((BuiltBuffer)buffer.end());
        }
        catch (Exception exception) {
            // empty catch block
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    @Subscribe
    private void onHUD(EventHUD e2) {
        MsdfFont font = Fonts.SFMEDIUM.get();
        for (SimulatedPath pearl : this.pearlLandings) {
            Vector2f screen;
            if (pearl.hitPos == null || pearl.hitTicks <= 0 || (screen = ProjectionUtil.project(pearl.hitPos)).getX() == Float.MAX_VALUE) continue;
            float sx = screen.getX();
            float sy = screen.getY();
            float seconds = (float)pearl.hitTicks / 20.0f;
            String text = String.format("%.1f сек.", Float.valueOf(seconds));
            float textWidth = font.getWidth(text, 7.0f);
            float totalWidth = textWidth + 11.0f + 11.0f;
            float posX = sx - totalWidth / 2.0f;
            float posY = sy;
            DrawUtil.drawRound(posX + 3.0f, posY + 2.0f - 3.0f, totalWidth - 4.0f, 13.0f, 0.0f, this.withAlpha(0x181818, 80));
            e2.getDrawContext().getMatrices().push();
            e2.getDrawContext().getMatrices().translate(posX + 5.0f, posY + 2.0f + 2.4f - 4.0f, 0.0f);
            e2.getDrawContext().getMatrices().scale(0.6875f, 0.6875f, 1.0f);
            e2.getDrawContext().drawItem(Items.ENDER_PEARL.getDefaultStack(), 0, 0);
            e2.getDrawContext().getMatrices().pop();
            DrawUtil.drawText(font, text, posX + 18.0f, posY + 6.5f - 4.0f, -1, 7.0f);
        }
    }

    private SimulatedPath simulatePath(Entity entity, float tickDelta) {
        ArrayList<Vec3d> path = new ArrayList<Vec3d>();
        Vec3d renderPos = this.getInterpolatedPos(entity, tickDelta);
        Vec3d pos = entity.getPos();
        Vec3d motion = entity.getVelocity();
        Vec3d hitPos = null;
        int hitTicks = 0;
        this.addIfFar(path, renderPos);
        if (renderPos.squaredDistanceTo(pos) > 1.0E-5) {
            this.addIfFar(path, renderPos.lerp(pos, 0.35));
            this.addIfFar(path, renderPos.lerp(pos, 0.7));
        }
        this.addIfFar(path, pos);
        for (int i2 = 0; i2 < 220; ++i2) {
            Vec3d prevPos = pos;
            pos = pos.add(motion);
            motion = this.calculateMotion(entity, prevPos, motion);
            BlockHitResult result = RaytraceUtil.raycast(prevPos, pos, RaycastContext.ShapeType.COLLIDER, entity);
            if (result.getType() != HitResult.Type.MISS) {
                pos = result.getPos();
            }
            this.addIfFar(path, pos);
            boolean hitEntity = this.hitsEntity(entity, prevPos, pos);
            if (result.getType() != HitResult.Type.BLOCK && result.getType() != HitResult.Type.ENTITY && !hitEntity && !(pos.y < -128.0)) continue;
            hitPos = pos;
            hitTicks = i2 + 1;
            break;
        }
        return new SimulatedPath(path, hitPos, hitTicks);
    }

    private Vec3d getInterpolatedPos(Entity entity, float tickDelta) {
        return new Vec3d(MathHelper.lerp((double)tickDelta, (double)entity.prevX, (double)entity.getX()), MathHelper.lerp((double)tickDelta, (double)entity.prevY, (double)entity.getY()), MathHelper.lerp((double)tickDelta, (double)entity.prevZ, (double)entity.getZ()));
    }

    private void addIfFar(List<Vec3d> list, Vec3d point) {
        if (list.isEmpty() || list.get(list.size() - 1).squaredDistanceTo(point) > 1.0E-6) {
            list.add(point);
        }
    }

    private List<Vec3d> smoothPath(List<Vec3d> raw) {
        if (raw.size() < 2) {
            return raw;
        }
        ArrayList<Vec3d> out = new ArrayList<Vec3d>();
        out.add(raw.get(0));
        for (int i2 = 0; i2 < raw.size() - 1; ++i2) {
            Vec3d p0 = raw.get(Math.max(0, i2 - 1));
            Vec3d p1 = raw.get(i2);
            Vec3d p2 = raw.get(i2 + 1);
            Vec3d p3 = raw.get(Math.min(raw.size() - 1, i2 + 2));
            double dist = p1.distanceTo(p2);
            int steps = MathHelper.clamp((int)((int)Math.ceil(dist / 0.08)), (int)3, (int)8);
            for (int j2 = 1; j2 <= steps; ++j2) {
                float t2 = (float)j2 / (float)steps;
                out.add(this.catmullRom(p0, p1, p2, p3, t2));
            }
        }
        return out;
    }

    private Vec3d catmullRom(Vec3d p0, Vec3d p1, Vec3d p2, Vec3d p3, float t2) {
        double t22 = t2 * t2;
        double t3 = t22 * (double)t2;
        double x2 = 0.5 * (2.0 * p1.x + (-p0.x + p2.x) * (double)t2 + (2.0 * p0.x - 5.0 * p1.x + 4.0 * p2.x - p3.x) * t22 + (-p0.x + 3.0 * p1.x - 3.0 * p2.x + p3.x) * t3);
        double y2 = 0.5 * (2.0 * p1.y + (-p0.y + p2.y) * (double)t2 + (2.0 * p0.y - 5.0 * p1.y + 4.0 * p2.y - p3.y) * t22 + (-p0.y + 3.0 * p1.y - 3.0 * p2.y + p3.y) * t3);
        double z2 = 0.5 * (2.0 * p1.z + (-p0.z + p2.z) * (double)t2 + (2.0 * p0.z - 5.0 * p1.z + 4.0 * p2.z - p3.z) * t22 + (-p0.z + 3.0 * p1.z - 3.0 * p2.z + p3.z) * t3);
        return new Vec3d(x2, y2, z2);
    }

    private boolean hitsEntity(Entity projectile, Vec3d from, Vec3d to) {
        if (this.mc.world == null) {
            return false;
        }
        Box box = new Box(from, to).expand(0.3);
        return !this.mc.world.getOtherEntities(projectile, box, ent -> {
            LivingEntity living;
            return ent instanceof LivingEntity && (living = (LivingEntity)ent) != this.mc.player && living.isAlive();
        }).isEmpty();
    }

    private void renderGlowPath(BufferBuilder buffer, MatrixStack matrices, Camera camera, List<Vec3d> path, int startColor, int endColor, long now) {
        if (path.size() < 2) {
            return;
        }
        float anim = (float)(now % 100000L) / 1000.0f;
        int lastIndex = path.size() - 1;
        for (int i2 = 0; i2 < path.size(); ++i2) {
            Vec3d pos = path.get(i2);
            float progress = (float)i2 / (float)lastIndex;
            float fade = 1.0f - progress;
            fade = fade * fade * (3.0f - 2.0f * fade);
            float shimmer = 1.0f + 0.025f * MathHelper.sin((float)(anim * 5.0f + progress * 10.0f));
            float size = (0.065f + fade * 0.11f) * shimmer;
            int color = this.interpolateColor(startColor, endColor, progress);
            int bright = this.interpolateColor(color, -1, 0.3f);
            this.drawGlow(buffer, matrices, camera, pos, size * 2.7f, this.withAlpha(color, (int)(fade * 24.0f)));
            this.drawGlow(buffer, matrices, camera, pos, size * 1.55f, this.withAlpha(color, (int)(fade * 72.0f)));
            this.drawGlow(buffer, matrices, camera, pos, size * 0.78f, this.withAlpha(bright, (int)(fade * 150.0f)));
        }
    }

    private void renderImpactGlow(BufferBuilder buffer, MatrixStack matrices, Camera camera, Vec3d pos, int color, long now) {
        float pulse = 1.0f + 0.08f * MathHelper.sin((float)((float)(now % 3000L) / 120.0f));
        int bright = this.interpolateColor(color, -1, 0.45f);
        this.drawGlow(buffer, matrices, camera, pos, 0.7f * pulse, this.withAlpha(color, 45));
        this.drawGlow(buffer, matrices, camera, pos, 0.42f * pulse, this.withAlpha(color, 115));
        this.drawGlow(buffer, matrices, camera, pos, 0.22f * pulse, this.withAlpha(bright, 220));
    }

    private void drawGlow(BufferBuilder buffer, MatrixStack matrices, Camera camera, Vec3d worldPos, float scale, int color) {
        Vec3d camPos = camera.getPos();
        matrices.push();
        matrices.translate(worldPos.x - camPos.x, worldPos.y - camPos.y, worldPos.z - camPos.z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        Matrix4f mat = matrices.peek().getPositionMatrix();
        buffer.vertex(mat, -scale, scale, 0.0f).texture(0.0f, 1.0f).color(color);
        buffer.vertex(mat, scale, scale, 0.0f).texture(1.0f, 1.0f).color(color);
        buffer.vertex(mat, scale, -scale, 0.0f).texture(1.0f, 0.0f).color(color);
        buffer.vertex(mat, -scale, -scale, 0.0f).texture(0.0f, 0.0f).color(color);
        matrices.pop();
    }

    private int getStartColor(Entity entity) {
        if (this.isFriendPearl(entity)) {
            return 0x66FF66;
        }
        return ColorProvider.getThemeColor();
    }

    private int getEndColor(Entity entity) {
        if (this.isFriendPearl(entity)) {
            return 51283;
        }
        return ColorProvider.getThemeColorTwo();
    }

    private boolean isFriendPearl(Entity entity) {
        if (!(entity instanceof EnderPearlEntity)) {
            return false;
        }
        EnderPearlEntity pearl = (EnderPearlEntity)entity;
        if (this.friendPearlIds.contains(pearl.getId())) {
            return true;
        }
        if (this.detectFriendPearl(pearl)) {
            this.friendPearlIds.add(pearl.getId());
            return true;
        }
        return false;
    }

    private boolean detectFriendPearl(EnderPearlEntity pearl) {
        PlayerEntity owner = this.resolvePearlOwner(pearl);
        if (owner == null) {
            return false;
        }
        String profileName = owner.getGameProfile() != null ? owner.getGameProfile().getName() : null;
        String displayName = owner.getName().getString();
        return this.isFriendName(profileName) || this.isFriendName(displayName);
    }

    private PlayerEntity resolvePearlOwner(EnderPearlEntity pearl) {
        Entity directOwner = pearl.getOwner();
        if (directOwner instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)directOwner;
            return player;
        }
        if (this.mc.world == null) {
            return null;
        }
        PlayerEntity best = null;
        double bestScore = Double.MAX_VALUE;
        Vec3d pearlPos = pearl.getPos();
        Vec3d motion = pearl.getVelocity();
        Vec3d motionNorm = motion.lengthSquared() > 1.0E-5 ? motion.normalize() : Vec3d.ZERO;
        for (PlayerEntity player : this.mc.world.getPlayers()) {
            Vec3d toPearl;
            Vec3d eyePos;
            double dist;
            if (player == null || !player.isAlive() || (dist = (eyePos = player.getEyePos()).distanceTo(pearlPos)) > 4.25) continue;
            double score = dist;
            if (motionNorm.lengthSquared() > 0.0 && (toPearl = pearlPos.subtract(eyePos)).lengthSquared() > 1.0E-5) {
                Vec3d toPearlNorm = toPearl.normalize();
                double dot = motionNorm.dotProduct(toPearlNorm);
                score -= dot * 1.75;
            }
            if (!(score < bestScore)) continue;
            bestScore = score;
            best = player;
        }
        return best;
    }

    private boolean isFriendName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (FriendRepository.isFriend(name)) {
            return true;
        }
        return FriendRepository.getFriends().stream().anyMatch(friend -> friend.name().equalsIgnoreCase(name));
    }

    private int withAlpha(int color, int alpha) {
        alpha = MathHelper.clamp((int)alpha, (int)0, (int)255);
        return alpha << 24 | color & 0xFFFFFF;
    }

    private int interpolateColor(int start, int end, float t2) {
        t2 = MathHelper.clamp((float)t2, (float)0.0f, (float)1.0f);
        int sr = start >> 16 & 0xFF;
        int sg = start >> 8 & 0xFF;
        int sb = start & 0xFF;
        int er = end >> 16 & 0xFF;
        int eg = end >> 8 & 0xFF;
        int eb = end & 0xFF;
        int r2 = (int)((float)sr + (float)(er - sr) * t2);
        int g2 = (int)((float)sg + (float)(eg - sg) * t2);
        int b2 = (int)((float)sb + (float)(eb - sb) * t2);
        return 0xFF000000 | r2 << 16 | g2 << 8 | b2;
    }

    public List<Entity> getProjectiles() {
        return StreamSupport.stream(this.mc.world.getEntities().spliterator(), false).filter(e2 -> {
            if (this.visible((Entity)e2)) {
                return false;
            }
            if (e2 instanceof TridentEntity) {
                return this.projectileFilter.isEnabled("Трезубцы");
            }
            if (e2 instanceof PersistentProjectileEntity) {
                return this.projectileFilter.isEnabled("Стрелы");
            }
            if (e2 instanceof EnderPearlEntity) {
                return this.projectileFilter.isEnabled("Перлы");
            }
            return false;
        }).toList();
    }

    public Vec3d calculateMotion(Entity entity, Vec3d prevPos, Vec3d motion) {
        boolean isInWater = this.mc.world.getBlockState(BlockPos.ofFloored((Position)prevPos)).getFluidState().isIn(FluidTags.WATER);
        float multiplier;
        if (entity instanceof TridentEntity) {
            multiplier = 0.99f;
        } else if (entity instanceof PersistentProjectileEntity) {
            multiplier = isInWater ? 0.6f : 0.99f;
        } else {
            multiplier = isInWater ? 0.8f : 0.99f;
        }
        return motion.multiply((double)multiplier).add(0.0, -entity.getFinalGravity(), 0.0);
    }

    private boolean visible(Entity entity) {
        return entity.getX() == entity.prevX && entity.getY() == entity.prevY && entity.getZ() == entity.prevZ;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.friendPearlIds.clear();
        this.pearlLandings.clear();
    }

    private static class SimulatedPath {
        private final List<Vec3d> rawPoints;
        private final Vec3d hitPos;
        private final int hitTicks;

        private SimulatedPath(List<Vec3d> rawPoints, Vec3d hitPos) {
            this(rawPoints, hitPos, 0);
        }

        private SimulatedPath(List<Vec3d> rawPoints, Vec3d hitPos, int hitTicks) {
            this.rawPoints = rawPoints;
            this.hitPos = hitPos;
            this.hitTicks = hitTicks;
        }
    }
}

