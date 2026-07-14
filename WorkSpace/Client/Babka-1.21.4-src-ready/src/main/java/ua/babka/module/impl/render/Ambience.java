package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import java.time.LocalTime;
import net.minecraft.client.gl.Defines;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Identifier;
import ua.babka.event.EventPacket;
import ua.babka.event.EventWorldRender;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ColorSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="Ambience", moduleDesc="Позволяет изменить атмосферу игры", moduleCategory=ModuleCategory.RENDER)
public class Ambience
extends Module {
    private static final ShaderProgramKey skyShader = new ShaderProgramKey(Identifier.of((String)"mre", (String)"core/ambience_sky/ambience_sky"), VertexFormats.POSITION, Defines.EMPTY);
    public final ModeSetting timeMode = new ModeSetting("Время", "Не менять", "Рассвет", "Утро", "День", "Вечер", "Заход солнца", "Ночь", "Время из реальной жизни", "Не менять");
    public final ModeSetting fogMode = new ModeSetting("Туман", "Ничего не делать", "Ничего не делать", "Очистить", "Переопределить");
    public final ColorSetting fogColor = new ColorSetting("Цвет тумана", -1).setVisible(() -> this.fogMode.is("Переопределить"));
    public final SliderSetting fogEnd = new SliderSetting("Конец тумана", 1.0, 0.1, 1.5, 0.1).setVisible(() -> this.fogMode.is("Переопределить"));
    public final SliderSetting fogStart = new SliderSetting("Начало тумана", 0.5, 0.1, 1.5, 0.1).setVisible(() -> this.fogMode.is("Переопределить"));
    public final ModeSetting skyMode = new ModeSetting("Небо", "Default", "Default", "Shader");
    public final ModeSetting skyType = new ModeSetting("Тип шейдера", "Туманность", "Туманность", "Лето", "Плазма", "Балатро").setVisible(() -> this.skyMode.is("Shader"));
    public final BooleanSetting skyThroughWalls = new BooleanSetting("Сквозь стены", false).setVisible(() -> this.skyMode.is("Shader"));
    public final SliderSetting skySpeed = new SliderSetting("Скорость", 1.0, 0.1, 1.0, 0.1).setVisible(() -> this.skyMode.is("Shader"));
    public final SliderSetting skyScale = new SliderSetting("Масштаб", 5.0, 1.0, 4.0, 0.5).setVisible(() -> this.skyMode.is("Shader"));
    public final SliderSetting skyAlpha = new SliderSetting("Прозрачность", 1.0, 0.3, 1.0, 0.05).setVisible(() -> this.skyMode.is("Shader"));

    @Subscribe
    private void onPacket(EventPacket e2) {
        if (e2.getType() != EventPacket.Type.RECEIVE) {
            return;
        }
        if (e2.getPacket() instanceof WorldTimeUpdateS2CPacket && !this.timeMode.is("Не менять")) {
            e2.cancelEvent();
        }
    }

    @Subscribe
    private void onRender3D(EventWorldRender event) {
    }

    private void setUniformFloat(ShaderProgram shader, String name, float value) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(value);
        }
    }

    private void setUniformVec2(ShaderProgram shader, String name, float x2, float y2) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(x2, y2);
        }
    }

    private void setUniformVec3(ShaderProgram shader, String name, float x2, float y2, float z2) {
        GlUniform uniform = shader.getUniform(name);
        if (uniform != null) {
            uniform.set(x2, y2, z2);
        }
    }

    private long getRealWorldTime() {
        LocalTime now = LocalTime.now();
        int totalSeconds = now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond();
        int offsetSeconds = (totalSeconds - 21600) % 86400;
        if (offsetSeconds < 0) {
            offsetSeconds += 86400;
        }
        return (long)((double)offsetSeconds / 86400.0 * 24000.0);
    }

    public long getCustomTime() {
        return switch (this.timeMode.getValue()) {
            case "Рассвет" -> 23000L;
            case "Утро" -> 1000L;
            case "День" -> 6000L;
            case "Вечер" -> 12000L;
            case "Заход солнца" -> 13000L;
            case "Ночь" -> 18000L;
            case "Время из реальной жизни" -> this.getRealWorldTime();
            default -> 6000L;
        };
    }

    public boolean isFogEnabled() {
        return !this.fogMode.is("Ничего не делать");
    }

    public float getFogDistance() {
        return (float)(this.fogEnd.getValue() * 256.0);
    }

    public float getFogStart() {
        return (float)(this.fogStart.getValue() * 256.0);
    }

    public boolean isFogClear() {
        return this.fogMode.is("Очистить");
    }
}

