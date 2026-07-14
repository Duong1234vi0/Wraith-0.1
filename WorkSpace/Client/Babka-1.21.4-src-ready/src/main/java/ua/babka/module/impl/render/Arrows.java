package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import ua.babka.event.EventHUD;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.ui.component.FreeLookComponent;

@ModuleInformation(moduleName="Arrows", moduleDesc="Стрелки, указывающие на игроков", moduleCategory=ModuleCategory.RENDER)
public class Arrows
extends Module {
    private static final Identifier ARROW_TEXTURE = Identifier.of((String)"mre", (String)"images/triangle.png");
    private final SliderSetting radius = new SliderSetting("Радиус", 58.0, 30.0, 120.0, 1.0);
    private final SliderSetting size = new SliderSetting("Размер", 13.0, 8.0, 28.0, 0.5);
    private final Map<UUID, ArrowState> states = new HashMap<UUID, ArrowState>();
    private final Set<UUID> seenPlayers = new HashSet<UUID>();

    @Subscribe
    public void onHUD(EventHUD event) {
        if (this.mc.player == null || this.mc.world == null || this.mc.options.hudHidden) {
            this.states.clear();
            return;
        }
        if (this.mc.options.getPerspective() != Perspective.FIRST_PERSON) {
            this.fadeAllStates();
            return;
        }
        float partialTicks = event.getRenderTickCounter().getTickDelta(true);
        float centerX = (float)this.mc.getWindow().getScaledWidth() * 0.5f;
        float centerY = (float)this.mc.getWindow().getScaledHeight() * 0.5f;
        float arrowSize = (float)this.size.getValue();
        float arrowRadius = (float)this.radius.getValue();
        float y2 = centerY - arrowRadius;
        float playerYaw = this.getReferenceYaw(partialTicks);
        Vec3d selfPos = this.getReferencePos(partialTicks);
        MatrixStack matrices = event.getDrawContext().getMatrices();
        this.seenPlayers.clear();
        for (AbstractClientPlayerEntity player : this.mc.world.getPlayers()) {
            if (player == this.mc.player || !player.isAlive() || player.isSpectator() || this.isGhostPlayer(player)) continue;
            UUID uuid = player.getUuid();
            ArrowState state = this.states.computeIfAbsent(uuid, id -> new ArrowState());
            this.seenPlayers.add(uuid);
            int color = this.getPlayerColor(player);
            float targetYaw = this.getRelativeYaw(player, partialTicks, playerYaw, selfPos);
            state.rotation = this.interpolateAngle(state.rotation, targetYaw, 0.18f);
            state.alpha = this.approach(state.alpha, 1.0f, 0.12f);
            float alpha = MathHelper.clamp((float)state.alpha, (float)0.0f, (float)1.0f);
            if (alpha <= 0.01f) continue;
            int drawColor = Arrows.applyAlpha(color, alpha);
            this.renderArrow(matrices, centerX, centerY, y2, arrowSize, state.rotation, drawColor);
        }
        this.states.entrySet().removeIf(entry -> {
            if (this.seenPlayers.contains(entry.getKey())) {
                return false;
            }
            ArrowState state = (ArrowState)entry.getValue();
            state.alpha = this.approach(state.alpha, 0.0f, 0.1f);
            return state.alpha <= 0.02f;
        });
    }

    private void renderArrow(MatrixStack matrices, float centerX, float centerY, float y2, float arrowSize, float rotation, int color) {
        AbstractTexture tex = this.mc.getTextureManager().getTexture(ARROW_TEXTURE);
        matrices.push();
        matrices.translate(centerX, centerY, 0.0f);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation));
        matrices.translate(-centerX, -centerY, 0.0f);
        float x2 = centerX - arrowSize * 0.5f;
        BuiltTexture arrow = (BuiltTexture)Builder.texture().size(new SizeState(arrowSize, arrowSize)).radius(QuadRadiusState.NO_ROUND).color(new QuadColorState(color)).texture(0.0f, 0.0f, 1.0f, 1.0f, tex).smoothness(1.0f).build();
        arrow.render(matrices.peek().getPositionMatrix(), x2, y2);
        matrices.pop();
    }

    private void fadeAllStates() {
        this.states.entrySet().removeIf(entry -> {
            ArrowState state = (ArrowState)entry.getValue();
            state.alpha = this.approach(state.alpha, 0.0f, 0.1f);
            return state.alpha <= 0.02f;
        });
    }

    private float approach(float current, float target, float factor) {
        factor = MathHelper.clamp((float)factor, (float)0.0f, (float)1.0f);
        return MathHelper.lerp((float)factor, (float)current, (float)target);
    }

    private int getPlayerColor(AbstractClientPlayerEntity player) {
        String name = player.getName().getString();
        boolean isFriend = FriendRepository.isFriend(name);
        return isFriend ? ColorProvider.rgb(80, 255, 100) : ColorProvider.getThemeColor();
    }

    private float getRelativeYaw(AbstractClientPlayerEntity entity, float partialTicks, float playerYaw, Vec3d selfPos) {
        double ex = MathHelper.lerp((double)partialTicks, (double)entity.prevX, (double)entity.getX());
        double ez = MathHelper.lerp((double)partialTicks, (double)entity.prevZ, (double)entity.getZ());
        double dx = ex - selfPos.x;
        double dz = ez - selfPos.z;
        float yaw = (float)(-Math.toDegrees(Math.atan2(dx, dz)));
        return MathHelper.wrapDegrees((float)(yaw - playerYaw));
    }

    private float getReferenceYaw(float partialTicks) {
        if (FreeLookComponent.isActive()) {
            return FreeLookComponent.getFreeYaw();
        }
        return MathHelper.lerp((float)partialTicks, (float)this.mc.player.prevYaw, (float)this.mc.player.getYaw());
    }

    private Vec3d getReferencePos(float partialTicks) {
        if (FreeLookComponent.isActive() && this.mc.gameRenderer != null && this.mc.gameRenderer.getCamera() != null) {
            return this.mc.gameRenderer.getCamera().getPos();
        }
        double x2 = MathHelper.lerp((double)partialTicks, (double)this.mc.player.prevX, (double)this.mc.player.getX());
        double y2 = MathHelper.lerp((double)partialTicks, (double)this.mc.player.prevY, (double)this.mc.player.getY());
        double z2 = MathHelper.lerp((double)partialTicks, (double)this.mc.player.prevZ, (double)this.mc.player.getZ());
        return new Vec3d(x2, y2, z2);
    }

    private float interpolateAngle(float current, float target, float factor) {
        float delta = MathHelper.wrapDegrees((float)(target - current));
        return current + delta * factor;
    }

    private boolean isGhostPlayer(AbstractClientPlayerEntity player) {
        String name;
        if (player.getCustomName() != null && (name = player.getCustomName().getString()) != null && name.startsWith("Ghost_")) {
            return true;
        }
        return "OtherClientPlayerEntity".equals(player.getClass().getSimpleName()) && player.getPitch() == -30.0f;
    }

    private static int applyAlpha(int color, float alpha) {
        int a2 = MathHelper.clamp((int)((int)(alpha * 255.0f)), (int)0, (int)255);
        return a2 << 24 | color & 0xFFFFFF;
    }

    private static final class ArrowState {
        float alpha;
        float rotation;

        private ArrowState() {
        }
    }
}

