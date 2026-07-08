/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.impl.render.EspSkeletonRenderer;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

public class EspSkeleton
extends Module {
    private final List<class_1657> players = new ArrayList<class_1657>();
    private final MultiSelectSetting entityType = new MultiSelectSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c", "\u0418\u0433\u0440\u043e\u043a\u0438, \u0434\u043b\u044f \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u0440\u0438\u0441\u0443\u0435\u0442\u0441\u044f \u0441\u043a\u0435\u043b\u0435\u0442\u043e\u043d").value("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", "\u0414\u0440\u0443\u0437\u044c\u044f").selected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
    private final SliderSettings thickness = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439 \u0441\u043a\u0435\u043b\u0435\u0442\u0430").setValue(2.0f).range(0.5f, 5.0f).step(0.5f);
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u043b\u0438\u043d\u0438\u0439 \u0441\u043a\u0435\u043b\u0435\u0442\u043e\u043d\u0430").value(new Color(255, 255, 255, 255).getRGB());

    public static EspSkeleton getInstance() {
        return Instance.get(EspSkeleton.class);
    }

    public EspSkeleton() {
        super("EspSkeleton", "EspSkeleton", ModuleCategory.ESP);
        this.setup(this.entityType, this.thickness, this.color);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.players.clear();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.players.clear();
        if (EspSkeleton.mc.field_1687 == null) {
            return;
        }
        EspSkeleton.mc.field_1687.method_18456().stream().filter(player -> player != EspSkeleton.mc.field_1724).filter(player -> player.method_5797() == null || !player.method_5797().getString().startsWith("Ghost_")).forEach(this.players::add);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (EspSkeleton.mc.field_1724 == null || EspSkeleton.mc.field_1687 == null) {
            return;
        }
        if (EspSkeleton.mc.field_1755 instanceof MenuScreen) {
            return;
        }
        if (mc.method_1561() == null || EspSkeleton.mc.method_1561().field_4686 == null) {
            return;
        }
        boolean selPlayers = this.entityType.isSelected("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        boolean selFriends = this.entityType.isSelected("\u0414\u0440\u0443\u0437\u044c\u044f");
        if (!selPlayers && !selFriends) {
            return;
        }
        ArrayList<class_1657> skeletonTargets = new ArrayList<class_1657>(this.players.size() + 1);
        if (selPlayers) {
            skeletonTargets.add((class_1657)EspSkeleton.mc.field_1724);
        }
        for (class_1657 player : this.players) {
            if (player == null || player.method_5797() != null && player.method_5797().getString().startsWith("Ghost_")) continue;
            boolean friend = FriendUtils.isFriend((class_1297)player);
            if (!selPlayers && (!selFriends || !friend)) continue;
            skeletonTargets.add(player);
        }
        EspSkeletonRenderer.renderPlayers(e.getStack(), skeletonTargets, mc.method_61966().method_60637(false), this.thickness.getValue(), this.color.getColor());
    }
}
