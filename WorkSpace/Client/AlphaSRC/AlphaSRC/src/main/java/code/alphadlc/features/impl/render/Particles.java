/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1667
 *  net.minecraft.class_1684
 *  net.minecraft.class_1685
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2663
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  org.joml.Vector4i
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.AttackEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.animation.util.Animation;
import code.alphadlc.utils.animation.util.Easings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.math.projection.Projection;
import code.alphadlc.utils.math.time.TimerUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1667;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import org.joml.Vector4i;

public class Particles
extends Module
implements QuickImports {
    private static final int TOTEM_COLOR_GREEN = 65280;
    private static final int TOTEM_COLOR_YELLOW = 0xFFFF00;
    private static Particles instance;
    public final MultiSelectSetting typep = new MultiSelectSetting("\u0421\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u043f\u0440\u0438", "\u041a\u043e\u0433\u0434\u0430 \u0441\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u044b").value("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438", "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u041a\u0440\u0438\u0442\u0435", "\u0411\u0440\u043e\u0441\u043a\u0435", "\u0422\u043e\u0442\u0435\u043c\u0435").selected("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438", "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u041a\u0440\u0438\u0442\u0435", "\u0422\u043e\u0442\u0435\u043c\u0435");
    private final SliderSettings countAFK = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u0438 \u0431\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438").range(1, 25).setValue(5.0f).step(1.0f).visible(() -> this.typep.isSelected("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438"));
    private final SliderSettings countAttack = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435").range(1, 50).setValue(2.0f).step(1.0f).visible(() -> this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435"));
    private final SliderSettings countMove = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438").range(1, 25).setValue(2.0f).step(1.0f).visible(() -> this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438"));
    private final SliderSettings moveLifetime = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438 \u0432 \u043c\u0441").range(500, 5000).setValue(3500.0f).step(250.0f).visible(() -> this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438"));
    private final SliderSettings moveSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438").range(0.1f, 4.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438"));
    private final SliderSettings moveSize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438").range(0.0f, 1.0f).setValue(0.5f).step(0.1f).visible(() -> this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438"));
    private final SliderSettings moveOpacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438").range(0.1f, 1.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438"));
    private final SliderSettings size = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").range(0.0f, 1.0f).setValue(0.5f).step(0.1f);
    private final SliderSettings range = new SliderSettings("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0441\u043f\u0430\u0432\u043d\u0430").range(4, 32).setValue(16.0f).step(1.0f);
    private final SliderSettings duration = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438", "\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").range(500, 5000).setValue(3500.0f).step(250.0f);
    private final SliderSettings strength = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").range(0.1f, 2.0f).setValue(1.0f).step(0.1f);
    private final SliderSettings opacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").range(0.1f, 1.0f).setValue(1.0f).step(0.1f);
    private final SliderSettings critSize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435").range(0.0f, 1.0f).setValue(0.5f).step(0.1f).visible(() -> this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435"));
    private final SliderSettings critDuration = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435", "\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435").range(500, 5000).setValue(3500.0f).step(250.0f).visible(() -> this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435"));
    private final SliderSettings critStrength = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435").range(0.1f, 4.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435"));
    private final SliderSettings critOpacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u043a\u0440\u0438\u0442\u0435").range(0.1f, 1.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435"));
    private final SliderSettings totemCount = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435").range(1, 200).setValue(75.0f).step(1.0f).visible(() -> this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435"));
    private final SliderSettings totemSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435").range(0.1f, 10.0f).setValue(4.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435"));
    private final SliderSettings totemLifetime = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435", "\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435 \u0432 \u043c\u0441").range(500, 10000).setValue(4000.0f).step(100.0f).visible(() -> this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435"));
    private final SliderSettings totemSize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435").range(0.0f, 1.0f).setValue(0.25f).step(0.1f).visible(() -> this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435"));
    private final SliderSettings totemOpacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0442\u043e\u0442\u0435\u043c\u0435").range(0.1f, 1.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435"));
    private final SliderSettings throwCount = new SliderSettings("\u041a\u043e\u043b-\u0432\u043e \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435", "\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435").range(1, 25).setValue(2.0f).step(1.0f).visible(() -> this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435"));
    private final SliderSettings throwLifetime = new SliderSettings("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435", "\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435 \u0432 \u043c\u0441").range(500, 5000).setValue(3500.0f).step(250.0f).visible(() -> this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435"));
    private final SliderSettings throwSpeed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435").range(0.1f, 4.0f).setValue(2.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435"));
    private final SliderSettings throwSize = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435", "\u0420\u0430\u0437\u043c\u0435\u0440 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435").range(0.0f, 1.0f).setValue(0.4f).step(0.1f).visible(() -> this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435"));
    private final SliderSettings throwOpacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435", "\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432 \u043f\u0440\u0438 \u0431\u0440\u043e\u0441\u043a\u0435").range(0.1f, 1.0f).setValue(1.0f).step(0.1f).visible(() -> this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435"));
    private final SelectSetting particleMode = new SelectSetting("\u0422\u0438\u043f \u0447\u0430\u0441\u0442\u0438\u0446", "\u0422\u0438\u043f \u0447\u0430\u0441\u0442\u0438\u0446").value("\u0428\u0430\u0440\u0438\u043a\u0438", "\u041a\u0443\u0431\u044b", "\u0414\u043e\u043b\u043b\u0430\u0440\u044b", "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438", "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438", "\u0417\u0432\u0435\u0437\u0434\u044b", "\u0417\u0432\u0435\u0437\u0434\u044b2", "\u0420\u0430\u043d\u0434\u043e\u043c").selected("\u0428\u0430\u0440\u0438\u043a\u0438");
    private final BooleanSetting glowing = new BooleanSetting("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", "\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").setValue(true).visible(() -> this.particleMode.isSelected("\u041a\u0443\u0431\u044b"));
    private final BooleanSetting onlyMove = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", "\u0421\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438").setValue(false);
    private final BooleanSetting ground = new BooleanSetting("\u0421\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", "\u0421\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u044b \u043d\u0430 \u0437\u0435\u043c\u043b\u0435").setValue(false);
    private final BooleanSetting physic = new BooleanSetting("\u0424\u0438\u0437\u0438\u043a\u0430", "\u0424\u0438\u0437\u0438\u043a\u0430 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").setValue(false);
    private final SelectSetting colorMode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430", "\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430 \u043f\u0430\u0440\u0442\u0438\u043a\u043b\u043e\u0432").value("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439", "\u041a\u0430\u0441\u0442\u043e\u043c").selected("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439");
    public final ColorSetting customMainColor = new ColorSetting("\u0413\u043b\u0430\u0432\u043d\u044b\u0439 \u0446\u0432\u0435\u0442", "\u0413\u043b\u0430\u0432\u043d\u044b\u0439 \u0446\u0432\u0435\u0442").value(-1).visible(() -> this.colorMode.isSelected("\u041a\u0430\u0441\u0442\u043e\u043c"));
    public final ColorSetting customSecondaryColor = new ColorSetting("\u0412\u0442\u043e\u0440\u0438\u0447\u043d\u044b\u0439 \u0446\u0432\u0435\u0442", "\u0412\u0442\u043e\u0440\u0438\u0447\u043d\u044b\u0439 \u0446\u0432\u0435\u0442").value(-1).visible(() -> this.colorMode.isSelected("\u041a\u0430\u0441\u0442\u043e\u043c"));
    private final List<Particle> particles = new ArrayList<Particle>();
    private final List<ParticleAttack> targetParticles = new ArrayList<ParticleAttack>();
    private final List<ParticleAttack> flameParticles = new ArrayList<ParticleAttack>();
    private final List<ParticleAttack> thrownParticles = new ArrayList<ParticleAttack>();
    private final List<ParticleAttack> totemParticles = new ArrayList<ParticleAttack>();
    private double lastPlayerX = 0.0;
    private double lastPlayerY = 0.0;
    private double lastPlayerZ = 0.0;
    private boolean positionInitialized = false;
    private static final double CUBE_ROTATION_RAD_PER_MS = 1.2E-8;
    private static final double CUBE_GLOW_DISTANCE_SQ = 2304.0;
    private static final float CUBE_LINE_WIDTH = 1.5f;
    private static final float CUBE_DIAG_WIDTH = 1.0f;
    private static final double[][] CUBE_OFFSETS;
    private static final int[][] CUBE_EDGES;
    private static final int[][] CUBE_DIAGONALS;
    private final class_243[] cubeCornerBuffer = new class_243[8];

    public Particles() {
        super("Particles", ModuleCategory.RENDER);
        this.setup(this.particleMode, this.typep, this.countAFK, this.range, this.duration, this.strength, this.opacity, this.size, this.onlyMove, this.ground, this.countMove, this.moveLifetime, this.moveSpeed, this.moveSize, this.moveOpacity, this.countAttack, this.critSize, this.critDuration, this.critStrength, this.critOpacity, this.throwCount, this.throwLifetime, this.throwSpeed, this.throwSize, this.throwOpacity, this.totemCount, this.totemSpeed, this.totemLifetime, this.totemSize, this.totemOpacity, this.glowing, this.physic, this.colorMode, this.customMainColor, this.customSecondaryColor);
        instance = this;
    }

    public static Particles getInstance() {
        return instance;
    }

    private void clear() {
        this.particles.clear();
        this.targetParticles.clear();
        this.flameParticles.clear();
        this.thrownParticles.clear();
        this.totemParticles.clear();
    }

    @Override
    public void setState(boolean state) {
        super.setState(state);
        if (!state) {
            this.clear();
        }
    }

    @EventHandler
    public void onAttack(AttackEvent e) {
        if (!this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435")) {
            return;
        }
        if (Particles.mc.field_1724.field_6017 == 0.0f) {
            return;
        }
        class_1297 target = e.getEntity();
        if (target == null) {
            return;
        }
        float motion = this.strength.getValue();
        for (int i = 0; i < this.countAttack.getInt(); ++i) {
            this.spawnParticleAttack(this.targetParticles, new class_243(target.method_23317(), target.method_23318() + this.randomValue(0.0, target.method_17682()), target.method_23321()), new class_243(this.randomValue(-motion, motion), this.randomValue(-motion, motion / 4.0f), this.randomValue(-motion, motion)));
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        class_243 pos;
        if (Particles.mc.field_1724 == null || Particles.mc.field_1687 == null) {
            return;
        }
        if (this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438") && this.hasPlayerMoved() && !Particles.mc.field_1690.method_31044().method_31034()) {
            for (int i = 0; i < this.countMove.getInt(); ++i) {
                class_243 playerVel = Particles.mc.field_1724.method_18798();
                this.spawnParticleAttack(this.flameParticles, new class_243(Particles.mc.field_1724.method_23317() + this.randomValue(-0.5, 0.5), Particles.mc.field_1724.method_23318() + this.randomValue(0.0, Particles.mc.field_1724.method_17682()), Particles.mc.field_1724.method_23321() + this.randomValue(-0.5, 0.5)), new class_243(playerVel.field_1352 + this.randomValue(-0.25, 0.25), this.randomValue(-0.15, 0.15), playerVel.field_1350 + this.randomValue(-0.25, 0.25)).method_1021((double)this.moveSpeed.getValue()));
            }
        }
        if (this.typep.isSelected("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438") && (!this.onlyMove.isValue() || this.hasPlayerMoved())) {
            int r = this.range.getInt();
            for (int i = 0; i < this.countAFK.getInt(); ++i) {
                class_243 additional = Particles.mc.field_1724.method_19538().method_1031(this.randomValue(-r, r), 0.0, this.randomValue(-r, r));
                pos = Particles.mc.field_1687.method_8598(class_2902.class_2903.field_13197, class_2338.method_49638((class_2374)additional));
                this.spawnParticle(new class_243((double)pos.method_10263() + this.randomValue(0.0, 1.0), this.ground.isValue() ? (double)pos.method_10264() : Particles.mc.field_1724.method_23318() + this.randomValue(Particles.mc.field_1724.method_17682(), r), (double)pos.method_10260() + this.randomValue(0.0, 1.0)), new class_243(0.0, this.randomValue(0.0, this.strength.getValue()) * (double)(this.ground.isValue() ? 1 : -1), 0.0));
            }
        }
        if (this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435")) {
            for (class_1297 entity : Particles.mc.field_1687.method_18112()) {
                boolean isMoving;
                class_1685 trident;
                if (!(entity instanceof class_1684) && !(entity instanceof class_1667) && !(entity instanceof class_1685) || entity instanceof class_1685 && (trident = (class_1685)entity).method_24921() != null) continue;
                boolean bl = isMoving = entity.field_6014 != entity.method_23317() || entity.field_6036 != entity.method_23318() || entity.field_5969 != entity.method_23321();
                if (!isMoving) continue;
                pos = entity.method_19538();
                class_243 entityVel = entity.method_18798();
                for (int i = 0; i < this.throwCount.getInt(); ++i) {
                    this.spawnParticleThrown(new class_243(pos.field_1352 + this.randomValue(-0.2, 0.2), pos.field_1351 + this.randomValue(-0.2, 0.2), pos.field_1350 + this.randomValue(-0.2, 0.2)), new class_243(entityVel.field_1352 * 0.1 + this.randomValue(-0.1, 0.1), entityVel.field_1351 * 0.1 + this.randomValue(-0.1, 0.1), entityVel.field_1350 * 0.1 + this.randomValue(-0.1, 0.1)));
                }
            }
        }
        this.removeExpiredParticlesAttack(this.targetParticles, (long)this.critDuration.getValue());
        this.removeExpiredParticlesAttack(this.flameParticles, (long)this.moveLifetime.getValue());
        this.removeExpiredParticlesAttack(this.thrownParticles, (long)this.throwLifetime.getValue());
        this.removeExpiredParticlesAttack(this.totemParticles, (long)this.totemLifetime.getValue());
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_1297 entity;
        class_2663 packet;
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_2663 && (packet = (class_2663)class_25962).method_11470() == 35 && (entity = packet.method_11469((class_1937)Particles.mc.field_1687)) instanceof class_1309 && this.isState() && this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435")) {
            this.createTotemEffect(entity.method_23317(), entity.method_23318() + (double)(entity.method_17682() / 2.0f), entity.method_23321());
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (!this.isState()) {
            return;
        }
        class_4587 matrix = e.getStack();
        float partialTicks = e.getPartialTicks();
        this.setupRenderState();
        if (this.typep.isSelected("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438")) {
            this.renderParticles(matrix, this.particles, this.duration.getValue(), this.duration.getMin(), partialTicks);
        }
        if (this.typep.isSelected("\u041a\u0440\u0438\u0442\u0435")) {
            this.renderParticlesAttack(matrix, this.targetParticles, 500L, 2000L, partialTicks);
        }
        if (this.typep.isSelected("\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0438") && !Particles.mc.field_1690.method_31044().method_31034()) {
            this.renderParticlesAttack(matrix, this.flameParticles, 500L, (long)this.moveLifetime.getValue(), partialTicks);
        }
        if (this.typep.isSelected("\u0411\u0440\u043e\u0441\u043a\u0435")) {
            this.renderParticlesAttack(matrix, this.thrownParticles, 500L, (long)this.throwLifetime.getValue(), partialTicks);
        }
        if (this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435")) {
            this.renderParticlesAttack(matrix, this.totemParticles, 1000L, (long)this.totemLifetime.getValue(), partialTicks);
        }
        this.resetRenderState();
    }

    private void renderParticles(class_4587 matrix, List<Particle> particles, double lifetime, double duration, float partialTicks) {
        this.removeExpiredParticles(particles, lifetime + duration);
        if (particles.isEmpty()) {
            return;
        }
        matrix.method_22903();
        RenderSystem.enableDepthTest();
        for (Particle particle : particles) {
            particle.update(this.physic.isValue());
            Animation animation = particle.animation();
            animation.update();
            float alpha = animation.get();
            if (alpha != this.opacity.getValue() && !particle.time().isReached((long)duration)) {
                animation.run((double)this.opacity.getValue(), duration / 1000.0, Easings.CUBIC_OUT);
            }
            if (alpha != 0.0f && particle.time().isReached((long)lifetime)) {
                animation.run(0.0, duration / 1000.0, Easings.CUBIC_OUT);
            }
            int color = ColorAssist.multAlpha(ColorAssist.replAlpha(particle.color(), alpha), (float)((Math.sin((double)(System.currentTimeMillis() - particle.spawnTime()) / 200.0) + 1.0) / 2.0));
            class_243 vec = particle.position();
            float x = (float)vec.field_1352;
            float y = (float)vec.field_1351;
            float z = (float)vec.field_1350;
            this.renderParticle(matrix, particle, x, y, z, color, partialTicks);
        }
        matrix.method_22909();
    }

    private void removeExpiredParticles(List<Particle> particles, double lifespan) {
        particles.removeIf(particle -> !Projection.canSee(particle.box));
        particles.removeIf(particle -> particle.time().isReached((long)lifespan));
    }

    private void removeExpiredParticlesAttack(List<ParticleAttack> particles, long lifespan) {
        if (particles == this.totemParticles || particles == this.targetParticles) {
            particles.removeIf(particle -> particle.time().isReached(lifespan));
        } else if (particles == this.flameParticles) {
            particles.removeIf(particle -> particle.time().isReached(lifespan));
        } else {
            particles.removeIf(particle -> !Projection.canSee(particle.box));
            particles.removeIf(particle -> particle.time().isReached(lifespan));
        }
    }

    private void renderParticle(class_4587 matrix, Particle particle, float x, float y, float z, int color, float partialTicks) {
        if (particle.type() == ParticleType.cube) {
            class_243 prevPos = particle.prevPosition();
            class_243 pos = particle.position();
            double renderX = prevPos.field_1352 + (pos.field_1352 - prevPos.field_1352) * (double)partialTicks;
            double renderY = prevPos.field_1351 + (pos.field_1351 - prevPos.field_1351) * (double)partialTicks;
            double renderZ = prevPos.field_1350 + (pos.field_1350 - prevPos.field_1350) * (double)partialTicks;
            class_243 prevRot = particle.prevRotateVec();
            class_243 baseRot = particle.rotateVec();
            long elapsed = particle.time().getTime();
            double timeY = (double)elapsed * 1.2E-8;
            double timeX = (double)elapsed * 1.2E-8 * 0.6;
            double rotX = prevRot.field_1352 + (baseRot.field_1352 - prevRot.field_1352) * (double)partialTicks + timeX;
            double rotY = prevRot.field_1351 + (baseRot.field_1351 - prevRot.field_1351) * (double)partialTicks + timeY;
            this.renderCube(renderX, renderY, renderZ, rotX, rotY, particle.size * 2.0f, color, this.glowing.isValue());
            return;
        }
        float pos = particle.size;
        class_4184 camera = Particles.mc.field_1773.method_19418();
        class_243 camPos = camera.method_19326();
        double posX = (double)x - camPos.field_1352;
        double posY = (double)y - camPos.field_1351;
        double posZ = (double)z - camPos.field_1350;
        class_4587 matrices = new class_4587();
        matrices.method_22903();
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrices.method_22904(posX, posY, posZ);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        if (particle.type() != ParticleType.star) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(180.0f));
        }
        if (particle.type() == ParticleType.heart) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(90.0f));
        }
        if (particle.type() == ParticleType.dollar) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(90.0f));
        }
        if (particle.type().rotatable()) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)particle.rotate()));
        }
        matrices.method_46416(0.0f, -pos, -pos);
        if (particle.type() == ParticleType.shariki) {
            Render3D.drawTexture(matrices.method_23760().method_56822(), ParticleType.shariki.texture(), -pos * 4.0f, -pos * 4.0f, pos * 8.0f, pos * 8.0f, new Vector4i(ColorAssist.multAlpha(color, 0.1f)), true);
        }
        Render3D.drawTexture(matrices.method_23760().method_56822(), particle.type().texture(), -pos, -pos, pos * 2.0f, pos * 2.0f, new Vector4i(color), true);
        if (particle.type.equals((Object)ParticleType.shariki)) {
            Render3D.drawTexture(matrices.method_23760().method_56822(), particle.type().texture(), -pos / 2.0f, -pos / 2.0f, pos, pos, new Vector4i(color), true);
        }
        matrices.method_22909();
    }

    private void setupRenderState() {
        RenderSystem.enableBlend();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
    }

    private void resetRenderState() {
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
    }

    private void renderParticlesAttack(class_4587 matrix, List<ParticleAttack> particles, long fadeInTime, long fadeOutTime, float partialTicks) {
        if (particles.isEmpty()) {
            return;
        }
        matrix.method_22903();
        RenderSystem.enableDepthTest();
        for (ParticleAttack particle : particles) {
            float targetOpacity;
            long lifetime;
            boolean usePhysics = this.physic.isValue() || particles == this.totemParticles;
            particle.update(usePhysics);
            particle.animation.update();
            if (particles == this.targetParticles) {
                lifetime = (long)this.critDuration.getValue();
                targetOpacity = this.critOpacity.getValue();
            } else if (particles == this.flameParticles) {
                lifetime = (long)this.moveLifetime.getValue();
                targetOpacity = this.moveOpacity.getValue();
            } else if (particles == this.thrownParticles) {
                lifetime = (long)this.throwLifetime.getValue();
                targetOpacity = this.throwOpacity.getValue();
            } else if (particles == this.totemParticles) {
                lifetime = (long)this.totemLifetime.getValue();
                targetOpacity = this.totemOpacity.getValue();
            } else {
                lifetime = fadeOutTime;
                targetOpacity = this.opacity.getValue();
            }
            if (particle.animation().get() != targetOpacity) {
                if (!particle.time().isReached(fadeInTime)) {
                    particle.animation().run((double)targetOpacity, 0.5, Easings.CUBIC_OUT);
                } else if (particle.animation().get() == 0.0f && particle.time().getTime() < fadeInTime) {
                    particle.animation().run((double)targetOpacity, 0.5, Easings.CUBIC_OUT);
                }
            }
            long fadeOutStart = lifetime - 1000L;
            if (particle.animation().get() != 0.0f && particle.time().isReached(fadeOutStart)) {
                particle.animation().run(0.0, 1.0, Easings.CUBIC_OUT);
            }
            int color = ColorAssist.replAlpha(particle.color(), particle.animation.get());
            class_243 vec = particle.position();
            float x = (float)vec.field_1352;
            float y = (float)vec.field_1351;
            float z = (float)vec.field_1350;
            this.renderParticleAttack(matrix, particle, x, y, z, particle.size, color, partialTicks);
        }
        matrix.method_22909();
    }

    private void renderParticleAttack(class_4587 matrix, ParticleAttack particle, float x, float y, float z, float pos, int color, float partialTicks) {
        if (particle.type() == ParticleType.cube) {
            class_243 prevPos = particle.prevPosition();
            class_243 position = particle.position();
            double renderX = prevPos.field_1352 + (position.field_1352 - prevPos.field_1352) * (double)partialTicks;
            double renderY = prevPos.field_1351 + (position.field_1351 - prevPos.field_1351) * (double)partialTicks;
            double renderZ = prevPos.field_1350 + (position.field_1350 - prevPos.field_1350) * (double)partialTicks;
            class_243 prevRot = particle.prevRotateVec();
            class_243 baseRot = particle.rotateVec();
            long elapsed = particle.time().getTime();
            double timeY = (double)elapsed * 1.2E-8;
            double timeX = (double)elapsed * 1.2E-8 * 0.6;
            double rotX = prevRot.field_1352 + (baseRot.field_1352 - prevRot.field_1352) * (double)partialTicks + timeX;
            double rotY = prevRot.field_1351 + (baseRot.field_1351 - prevRot.field_1351) * (double)partialTicks + timeY;
            this.renderCube(renderX, renderY, renderZ, rotX, rotY, particle.size * 2.0f, color, this.glowing.isValue());
            return;
        }
        class_4184 camera = Particles.mc.field_1773.method_19418();
        class_243 camPos = camera.method_19326();
        double posX = (double)x - camPos.field_1352;
        double posY = (double)y - camPos.field_1351;
        double posZ = (double)z - camPos.field_1350;
        class_4587 matrices = new class_4587();
        matrices.method_22903();
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrices.method_22904(posX, posY, posZ);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        if (particle.type() != ParticleType.star) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(180.0f));
        }
        if (particle.type() == ParticleType.heart) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(90.0f));
        }
        if (particle.type() == ParticleType.dollar) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees(90.0f));
        }
        if (particle.type().rotatable()) {
            matrices.method_22907(class_7833.field_40718.rotationDegrees((float)particle.rotate()));
        }
        matrices.method_46416(0.0f, -pos, 0.0f);
        if (particle.type() == ParticleType.shariki) {
            Render3D.drawTexture(matrices.method_23760().method_56822(), ParticleType.shariki.texture(), -pos * 4.0f, -pos * 4.0f, pos * 8.0f, pos * 8.0f, new Vector4i(ColorAssist.multAlpha(color, 0.1f)), true);
        }
        Render3D.drawTexture(matrices.method_23760().method_56822(), particle.type().texture(), -pos, -pos, pos * 2.0f, pos * 2.0f, new Vector4i(color), true);
        if (particle.type.equals((Object)ParticleType.shariki)) {
            Render3D.drawTexture(matrices.method_23760().method_56822(), particle.type().texture(), -pos / 2.0f, -pos / 2.0f, pos, pos, new Vector4i(color), true);
        }
        matrices.method_22909();
    }

    private void fillCubeCorners(double px, double py, double pz, double rotX, double rotY, float half) {
        double cosY = Math.cos(rotY);
        double sinY = Math.sin(rotY);
        double cosX = Math.cos(rotX);
        double sinX = Math.sin(rotX);
        for (int i = 0; i < 8; ++i) {
            double ox = CUBE_OFFSETS[i][0] * (double)half;
            double oy = CUBE_OFFSETS[i][1] * (double)half;
            double oz = CUBE_OFFSETS[i][2] * (double)half;
            double rx = ox * cosY - oz * sinY;
            double rz = ox * sinY + oz * cosY;
            double ry = oy * cosX - rz * sinX;
            rz = oy * sinX + rz * cosX;
            this.cubeCornerBuffer[i] = new class_243(px + rx, py + ry, pz + rz);
        }
    }

    private void renderCube(double px, double py, double pz, double rotX, double rotY, float scale, int color, boolean withGlow) {
        float half = scale * 0.5f;
        this.fillCubeCorners(px, py, pz, rotX, rotY, half);
        if (withGlow) {
            class_4184 class_41842 = Particles.mc.field_1773.method_19418();
            class_243 camPos = class_41842.method_19326();
            double dx = px - camPos.field_1352;
            double dy = py - camPos.field_1351;
            double dz = pz - camPos.field_1350;
            if (dx * dx + dy * dy + dz * dz <= 2304.0) {
                class_4587 glowMatrix = new class_4587();
                glowMatrix.method_22907(class_7833.field_40714.rotationDegrees(class_41842.method_19329()));
                glowMatrix.method_22907(class_7833.field_40716.rotationDegrees(class_41842.method_19330() + 180.0f));
                glowMatrix.method_22904(dx, dy, dz);
                glowMatrix.method_22907(class_7833.field_40716.rotationDegrees(-class_41842.method_19330()));
                glowMatrix.method_22907(class_7833.field_40714.rotationDegrees(class_41842.method_19329()));
                float glowSize = scale * 0.85f;
                Render3D.drawTexture(glowMatrix.method_23760().method_56822(), ParticleType.shariki.texture(), -glowSize, -glowSize, glowSize * 2.0f, glowSize * 2.0f, new Vector4i(ColorAssist.multAlpha(color, 0.15f)), true);
            }
        }
        for (int[] edge : CUBE_EDGES) {
            Render3D.drawLine(this.cubeCornerBuffer[edge[0]], this.cubeCornerBuffer[edge[1]], color, 1.5f, true);
        }
        int n = ColorAssist.multAlpha(color, 0.5f);
        for (int[] diag : CUBE_DIAGONALS) {
            Render3D.drawLine(this.cubeCornerBuffer[diag[0]], this.cubeCornerBuffer[diag[1]], n, 1.0f, true);
        }
    }

    private void spawnParticle(class_243 position, class_243 velocity) {
        float size = 0.05f + this.size.getValue() * 0.2f;
        int color = switch (this.colorMode.getSelected()) {
            case "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439" -> ColorAssist.fade(this.particles.size() * 100);
            case "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439" -> ColorAssist.rainbow(10, this.particles.size() * 100, 0.5f, 1.0f, 1.0f);
            default -> {
                int mainColor = this.customMainColor.getColor();
                int secondaryColor = this.customSecondaryColor.getColor();
                float factor = (float)(this.particles.size() % 100) / 100.0f;
                yield ColorAssist.interpolateColor(mainColor, secondaryColor, factor);
            }
        };
        ParticleType type = switch (this.particleMode.getSelected()) {
            case "\u0428\u0430\u0440\u0438\u043a\u0438" -> ParticleType.shariki;
            case "\u041a\u0443\u0431\u044b" -> ParticleType.cube;
            case "\u0417\u0432\u0435\u0437\u0434\u044b" -> ParticleType.star;
            case "\u0417\u0432\u0435\u0437\u0434\u044b2" -> ParticleType.startwo;
            case "\u0414\u043e\u043b\u043b\u0430\u0440\u044b" -> ParticleType.dollar;
            case "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438" -> ParticleType.heart;
            case "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438" -> ParticleType.snowflake;
            default -> ParticleType.getRandom();
        };
        this.particles.add(new Particle(type, position.method_1031(0.0, (double)size, 0.0), velocity, this.particles.size(), color, size, (int)(Math.round(this.randomValue(0.0, 360.0) / 15.0) * 15L)));
    }

    private void spawnParticleAttack(List<ParticleAttack> particles, class_243 position, class_243 velocity) {
        ParticleType type;
        float size = particles == this.targetParticles ? 0.05f + this.critSize.getValue() * 0.2f : (particles == this.flameParticles ? 0.05f + this.moveSize.getValue() * 0.2f : 0.05f + this.size.getValue() * 0.2f);
        int color = switch (this.colorMode.getSelected()) {
            case "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439" -> ColorAssist.fade(particles.size() * 100);
            case "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439" -> ColorAssist.rainbow(4, particles.size() * 100, 0.5f, 1.0f, 1.0f);
            default -> {
                int mainColor = this.customMainColor.getColor();
                int secondaryColor = this.customSecondaryColor.getColor();
                yield ColorAssist.interpolateColor(mainColor, secondaryColor, (float)(particles.size() % 100) / 100.0f);
            }
        };
        switch (this.particleMode.getSelected()) {
            case "\u0428\u0430\u0440\u0438\u043a\u0438": {
                ParticleType particleType = ParticleType.shariki;
                break;
            }
            case "\u041a\u0443\u0431\u044b": {
                ParticleType particleType = ParticleType.cube;
                break;
            }
            case "\u0417\u0432\u0435\u0437\u0434\u044b": {
                ParticleType particleType = ParticleType.star;
                break;
            }
            case "\u0417\u0432\u0435\u0437\u0434\u044b2": {
                ParticleType particleType = ParticleType.startwo;
                break;
            }
            case "\u0414\u043e\u043b\u043b\u0430\u0440\u044b": {
                ParticleType particleType = ParticleType.dollar;
                break;
            }
            case "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438": {
                ParticleType particleType = ParticleType.heart;
                break;
            }
            case "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438": {
                ParticleType particleType = ParticleType.snowflake;
                break;
            }
            default: {
                ParticleType particleType = type = ParticleType.getRandom();
            }
        }
        class_243 motion = particles == this.targetParticles ? velocity.method_1021((double)(this.critStrength.getValue() * 5.0f)) : (particles == this.flameParticles ? velocity.method_1021((double)this.moveSpeed.getValue()) : velocity.method_1021((double)this.strength.getValue()));
        ParticleAttack particle = new ParticleAttack(type, position.method_1031(0.0, (double)size, 0.0), motion, particles.size(), (int)(Math.round(this.randomValue(0.0, 360.0) / 15.0) * 15L), color, size);
        particles.add(particle);
    }

    private void spawnParticleThrown(class_243 position, class_243 velocity) {
        float size = 0.05f + this.throwSize.getValue() * 0.2f;
        int color = switch (this.colorMode.getSelected()) {
            case "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439" -> ColorAssist.fade(this.thrownParticles.size() * 100);
            case "\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439" -> ColorAssist.rainbow(4, this.thrownParticles.size() * 100, 0.5f, 1.0f, 1.0f);
            default -> {
                int mainColor = this.customMainColor.getColor();
                int secondaryColor = this.customSecondaryColor.getColor();
                yield ColorAssist.interpolateColor(mainColor, secondaryColor, (float)(this.thrownParticles.size() % 100) / 100.0f);
            }
        };
        ParticleType type = switch (this.particleMode.getSelected()) {
            case "\u0428\u0430\u0440\u0438\u043a\u0438" -> ParticleType.shariki;
            case "\u041a\u0443\u0431\u044b" -> ParticleType.cube;
            case "\u0417\u0432\u0435\u0437\u0434\u044b" -> ParticleType.star;
            case "\u0417\u0432\u0435\u0437\u0434\u044b2" -> ParticleType.startwo;
            case "\u0414\u043e\u043b\u043b\u0430\u0440\u044b" -> ParticleType.dollar;
            case "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438" -> ParticleType.heart;
            case "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438" -> ParticleType.snowflake;
            default -> ParticleType.getRandom();
        };
        class_243 motion = velocity.method_1021((double)this.throwSpeed.getValue());
        this.thrownParticles.add(new ParticleAttack(type, position.method_1031(0.0, (double)size, 0.0), motion, this.thrownParticles.size(), (int)(Math.round(this.randomValue(0.0, 360.0) / 15.0) * 15L), color, size));
    }

    private void spawnTotemParticle(class_243 position, class_243 velocity, int color) {
        ParticleType type = switch (this.particleMode.getSelected()) {
            case "\u0428\u0430\u0440\u0438\u043a\u0438" -> ParticleType.shariki;
            case "\u041a\u0443\u0431\u044b" -> ParticleType.cube;
            case "\u0417\u0432\u0435\u0437\u0434\u044b" -> ParticleType.star;
            case "\u0417\u0432\u0435\u0437\u0434\u044b2" -> ParticleType.startwo;
            case "\u0414\u043e\u043b\u043b\u0430\u0440\u044b" -> ParticleType.dollar;
            case "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438" -> ParticleType.heart;
            case "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438" -> ParticleType.snowflake;
            default -> ParticleType.getRandom();
        };
        float size = 0.05f + this.totemSize.getValue() * 0.2f;
        if (type == ParticleType.star || type == ParticleType.heart) {
            size = 0.1f;
        }
        this.totemParticles.add(new ParticleAttack(type, position.method_1031(this.randomValue(-0.3, 0.3), this.randomValue(-0.3, 0.3), this.randomValue(-0.3, 0.3)), velocity.method_1021((double)this.totemSpeed.getValue()), this.totemParticles.size(), (int)(Math.round(this.randomValue(0.0, 360.0) / 15.0) * 15L), color, size));
    }

    private boolean hasPlayerMoved() {
        boolean moved;
        if (Particles.mc.field_1724 == null) {
            return false;
        }
        if (!this.positionInitialized) {
            this.lastPlayerX = Particles.mc.field_1724.method_23317();
            this.lastPlayerY = Particles.mc.field_1724.method_23318();
            this.lastPlayerZ = Particles.mc.field_1724.method_23321();
            this.positionInitialized = true;
            return false;
        }
        boolean bl = moved = Math.abs(Particles.mc.field_1724.method_23317() - this.lastPlayerX) > 0.001 || Math.abs(Particles.mc.field_1724.method_23318() - this.lastPlayerY) > 0.001 || Math.abs(Particles.mc.field_1724.method_23321() - this.lastPlayerZ) > 0.001;
        if (moved) {
            this.lastPlayerX = Particles.mc.field_1724.method_23317();
            this.lastPlayerY = Particles.mc.field_1724.method_23318();
            this.lastPlayerZ = Particles.mc.field_1724.method_23321();
        }
        return moved;
    }

    private double randomValue(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }

    public void createTotemEffect(double x, double y, double z) {
        if (!this.isState() || !this.typep.isSelected("\u0422\u043e\u0442\u0435\u043c\u0435")) {
            return;
        }
        for (int i = 0; i < this.totemCount.getInt(); ++i) {
            double angleXZ = Math.random() * Math.PI * 2.0;
            double angleY = Math.random() * Math.PI;
            double strength = 0.5 + Math.random() * 0.5;
            class_243 velocity = new class_243(Math.sin(angleXZ) * Math.sin(angleY) * strength, Math.cos(angleY) * strength, Math.cos(angleXZ) * Math.sin(angleY) * strength);
            int color = Math.random() < 0.7 ? 65280 : 0xFFFF00;
            this.spawnTotemParticle(new class_243(x, y, z), velocity, color);
        }
    }

    static {
        CUBE_OFFSETS = new double[][]{{-1.0, -1.0, -1.0}, {1.0, -1.0, -1.0}, {1.0, 1.0, -1.0}, {-1.0, 1.0, -1.0}, {-1.0, -1.0, 1.0}, {1.0, -1.0, 1.0}, {1.0, 1.0, 1.0}, {-1.0, 1.0, 1.0}};
        CUBE_EDGES = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}};
        CUBE_DIAGONALS = new int[][]{{0, 6}, {1, 7}, {2, 4}, {3, 5}};
    }

    public static class Particle {
        private final long spawnTime = System.currentTimeMillis();
        private final ParticleType type;
        private final class_238 box;
        private final int rotate;
        private final int index;
        private final int color;
        private final float size;
        private final TimerUtil time = new TimerUtil();
        private final Animation animation = new Animation();
        private class_243 position;
        private class_243 prevPosition;
        private class_243 velocity;
        private class_243 rotateVec;
        private class_243 prevRotateVec;
        private class_243 rotateMotion;

        public Particle(ParticleType type, class_243 position, class_243 velocity, int index, int color, float size, int rotate) {
            this.type = type;
            this.rotate = rotate;
            this.box = new class_238(position, position).method_1014((double)size);
            this.position = position;
            this.prevPosition = position;
            this.velocity = velocity.method_1021((double)0.01f);
            this.index = index;
            this.color = ColorAssist.clampSaturation(color, 0.0f, 0.8f);
            this.size = size;
            this.time.resetCounter();
            this.rotateVec = class_243.field_1353;
            this.prevRotateVec = class_243.field_1353;
            this.rotateMotion = new class_243(Particle.randomDouble(-1.0, 1.0) * 0.04, Particle.randomDouble(-1.0, 1.0) * 0.04, Particle.randomDouble(-1.0, 1.0) * 0.04);
        }

        private static double randomDouble(double min, double max) {
            return min + (max - min) * new Random().nextDouble();
        }

        public void update(boolean physic) {
            this.prevPosition = this.position;
            this.prevRotateVec = this.rotateVec;
            if (physic) {
                if (this.isBlockSolid(this.position.field_1352, this.position.field_1351, this.position.field_1350 + this.velocity.field_1350)) {
                    this.velocity = new class_243(this.velocity.field_1352, this.velocity.field_1351, -this.velocity.field_1350 * 0.8);
                }
                if (this.isBlockSolid(this.position.field_1352, this.position.field_1351 + this.velocity.field_1351, this.position.field_1350)) {
                    this.velocity = new class_243(this.velocity.field_1352 * 0.999, -this.velocity.field_1351 * 0.6, this.velocity.field_1350 * 0.999);
                }
                if (this.isBlockSolid(this.position.field_1352 + this.velocity.field_1352, this.position.field_1351, this.position.field_1350)) {
                    this.velocity = new class_243(-this.velocity.field_1352 * 0.8, this.velocity.field_1351, this.velocity.field_1350);
                }
                this.velocity = this.velocity.method_1021(0.999999).method_1023(0.0, 5.0E-5, 0.0);
            }
            this.position = this.position.method_1019(this.velocity);
            this.rotateVec = this.rotateVec.method_1019(this.rotateMotion);
            this.rotateMotion = this.rotateMotion.method_1021(0.98);
        }

        private boolean isBlockSolid(double x, double y, double z) {
            class_2338 pos = class_2338.method_49637((double)x, (double)y, (double)z);
            return QuickImports.mc.field_1687 != null && !QuickImports.mc.field_1687.method_8320(pos).method_26220((class_1922)QuickImports.mc.field_1687, pos).method_1110();
        }

        public long spawnTime() {
            return this.spawnTime;
        }

        public ParticleType type() {
            return this.type;
        }

        public class_238 box() {
            return this.box;
        }

        public int rotate() {
            return this.rotate;
        }

        public int index() {
            return this.index;
        }

        public int color() {
            return this.color;
        }

        public float size() {
            return this.size;
        }

        public TimerUtil time() {
            return this.time;
        }

        public Animation animation() {
            return this.animation;
        }

        public class_243 position() {
            return this.position;
        }

        public class_243 prevPosition() {
            return this.prevPosition;
        }

        public class_243 velocity() {
            return this.velocity;
        }

        public class_243 rotateVec() {
            return this.rotateVec;
        }

        public class_243 prevRotateVec() {
            return this.prevRotateVec;
        }

        public class_243 rotateMotion() {
            return this.rotateMotion;
        }
    }

    public static enum ParticleType {
        shariki("glow", false),
        cube(null, true),
        dollar("bucks1", false),
        heart("heart1", false),
        snowflake("show1", false),
        star("star1", false),
        startwo("sparkle", false);

        private final class_2960 texture;
        private final boolean rotatable;

        private ParticleType(String name, boolean rotatable) {
            this.texture = name != null ? class_2960.method_60654((String)("textures/particles/" + name + ".png")) : null;
            this.rotatable = rotatable;
        }

        public static ParticleType getRandom() {
            ParticleType result;
            ParticleType[] values = ParticleType.values();
            while ((result = values[new Random().nextInt(values.length)]) == cube) {
            }
            return result;
        }

        public class_2960 texture() {
            return this.texture;
        }

        public boolean rotatable() {
            return this.rotatable;
        }
    }

    public static class ParticleAttack {
        private final ParticleType type;
        private final int index;
        private final int rotate;
        private final int color;
        private final float size;
        private final TimerUtil time = new TimerUtil();
        private final Animation animation = new Animation();
        private class_238 box;
        private class_243 position;
        private class_243 prevPosition;
        private class_243 velocity;
        private class_243 rotateVec;
        private class_243 prevRotateVec;
        private class_243 rotateMotion;

        public ParticleAttack(ParticleType type, class_243 position, class_243 velocity, int index, int rotate, int color, float size) {
            this.box = new class_238(position, position).method_1014((double)size);
            this.type = type;
            this.position = position;
            this.prevPosition = position;
            this.velocity = velocity.method_1021((double)0.01f);
            this.index = index;
            this.rotate = rotate;
            this.color = color;
            this.size = size;
            this.time.resetCounter();
            this.animation.set(0.0);
            this.rotateVec = class_243.field_1353;
            this.prevRotateVec = class_243.field_1353;
            Random rand = new Random();
            this.rotateMotion = new class_243((rand.nextDouble() * 2.0 - 1.0) * 0.04, (rand.nextDouble() * 2.0 - 1.0) * 0.04, (rand.nextDouble() * 2.0 - 1.0) * 0.04);
        }

        public void update(boolean physic) {
            this.prevPosition = this.position;
            this.prevRotateVec = this.rotateVec;
            if (physic) {
                if (this.isBlockSolid(this.position.field_1352, this.position.field_1351, this.position.field_1350 + this.velocity.field_1350)) {
                    this.velocity = new class_243(this.velocity.field_1352, this.velocity.field_1351, -this.velocity.field_1350 * 0.8);
                }
                if (this.isBlockSolid(this.position.field_1352, this.position.field_1351 + this.velocity.field_1351, this.position.field_1350)) {
                    this.velocity = new class_243(this.velocity.field_1352 * 0.999, -this.velocity.field_1351 * 0.6, this.velocity.field_1350 * 0.999);
                }
                if (this.isBlockSolid(this.position.field_1352 + this.velocity.field_1352, this.position.field_1351, this.position.field_1350)) {
                    this.velocity = new class_243(-this.velocity.field_1352 * 0.8, this.velocity.field_1351, this.velocity.field_1350);
                }
                this.velocity = this.velocity.method_1021(0.999999).method_1023(0.0, 5.0E-5, 0.0);
            }
            this.position = this.position.method_1019(this.velocity);
            this.box = new class_238(this.position, this.position).method_1014((double)this.size);
            this.rotateVec = this.rotateVec.method_1019(this.rotateMotion);
            this.rotateMotion = this.rotateMotion.method_1021(0.98);
        }

        private boolean isBlockSolid(double x, double y, double z) {
            class_2338 pos = class_2338.method_49637((double)x, (double)y, (double)z);
            return QuickImports.mc.field_1687 != null && !QuickImports.mc.field_1687.method_8320(pos).method_26220((class_1922)QuickImports.mc.field_1687, pos).method_1110();
        }

        public ParticleType type() {
            return this.type;
        }

        public int index() {
            return this.index;
        }

        public int rotate() {
            return this.rotate;
        }

        public int color() {
            return this.color;
        }

        public float size() {
            return this.size;
        }

        public TimerUtil time() {
            return this.time;
        }

        public Animation animation() {
            return this.animation;
        }

        public class_238 box() {
            return this.box;
        }

        public class_243 position() {
            return this.position;
        }

        public class_243 prevPosition() {
            return this.prevPosition;
        }

        public class_243 velocity() {
            return this.velocity;
        }

        public class_243 rotateVec() {
            return this.rotateVec;
        }

        public class_243 prevRotateVec() {
            return this.prevRotateVec;
        }

        public class_243 rotateMotion() {
            return this.rotateMotion;
        }
    }
}
