package ua.babka.ui.hud;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import ua.babka.Babka;
import ua.babka.event.EventHUD;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.QuadRadiusState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.Fonts;
import ua.babka.util.IMinecraft;

public final class GpsRenderer
implements IMinecraft {
    private static final GpsRenderer INSTANCE = new GpsRenderer();
    private double targetX;
    private double targetZ;
    private boolean enabled;

    public static GpsRenderer get() {
        return INSTANCE;
    }

    private GpsRenderer() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    public void setTarget(double x2, double z2) {
        this.targetX = x2;
        this.targetZ = z2;
    }

    @Subscribe
    private void onHud(EventHUD e2) {
        if (!this.enabled) {
            return;
        }
        if (MinecraftClient.getInstance().player == null) {
            return;
        }
        MatrixStack ms = e2.getDrawContext().getMatrices();
        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        Vec3d pos = GpsRenderer.mc.player.getPos();
        double dx = this.targetX - pos.x;
        double dz = this.targetZ - pos.z;
        int dist = (int)Math.sqrt(dx * dx + dz * dz);
        float angle = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float rot = MathHelper.wrapDegrees((float)(angle - GpsRenderer.mc.player.getYaw()));
        String distTxt = dist + "m";
        int white = ColorProvider.rgba(255, 255, 255, 255.0f);
        double cx = (double)sw / 2.0;
        double cy = (double)sh / 2.0 - 80.0;
        ms.push();
        ms.translate(cx, cy, 0.0);
        ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rot));
        AbstractTexture tex = mc.getTextureManager().getTexture(Identifier.of((String)"mre", (String)"images/triangle.png"));
        int id = tex.getGlId();
        int theme = ColorProvider.getColorClient();
        BuiltTexture arrow2 = (BuiltTexture)Builder.texture().size(new SizeState(18.0f, 18.0f)).radius(QuadRadiusState.NO_ROUND).color(new QuadColorState(theme)).texture(0.0f, 0.0f, 1.0f, 1.0f, id).smoothness(2.0f).build();
        arrow2.render(ms.peek().getPositionMatrix(), -9.0f, -9.0f);
        BuiltTexture arrow = (BuiltTexture)Builder.texture().size(new SizeState(18.0f, 18.0f)).radius(QuadRadiusState.NO_ROUND).color(new QuadColorState(theme)).texture(0.0f, 0.0f, 1.0f, 1.0f, id).smoothness(1.0f).build();
        arrow.render(ms.peek().getPositionMatrix(), -9.0f, -9.0f);
        ms.pop();
        float w1 = Fonts.SFMEDIUM.get().getWidth(distTxt, 7.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), distTxt, (float)(cx - (double)(w1 / 2.0f)), (float)(cy + 8.0), white, 7.0f);
        String navText = "Навигатор";
        float w2 = Fonts.SFMEDIUM.get().getWidth(navText, 7.0f);
        DrawUtil.drawText(Fonts.SFMEDIUM.get(), navText, (float)(cx - (double)(w2 / 2.0f)), (float)(cy + 18.0), white, 7.0f);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

