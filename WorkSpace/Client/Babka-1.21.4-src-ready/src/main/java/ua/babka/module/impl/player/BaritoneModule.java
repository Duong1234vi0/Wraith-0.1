package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import ua.babka.event.EventHUD;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.MsdfFont;

@ModuleInformation(moduleName="Baritone", moduleDesc="Baritone AI — #mine #goto #follow", moduleCategory=ModuleCategory.MISC)
public class BaritoneModule
extends Module {
    private static final String BARITONE_API_CLASS = "baritone.api.BaritoneAPI";
    private static final float FONT_SIZE = 7.0f;
    private final BooleanSetting showHud = new BooleanSetting("HUD статус", true);
    private Object baritone;
    private boolean baritoneAvailable;

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            Class<?> apiClass = Class.forName(BARITONE_API_CLASS);
            Object provider = apiClass.getMethod("getProvider", new Class[0]).invoke(null, new Object[0]);
            this.baritone = provider.getClass().getMethod("getPrimaryBaritone", new Class[0]).invoke(provider, new Object[0]);
            this.baritoneAvailable = true;
        }
        catch (Exception e2) {
            this.baritone = null;
            this.baritoneAvailable = false;
        }
    }

    @Override
    public void onDisable() {
        if (this.baritone != null) {
            try {
                Object pathing = this.baritone.getClass().getMethod("getPathingBehavior", new Class[0]).invoke(this.baritone, new Object[0]);
                pathing.getClass().getMethod("cancelEverything", new Class[0]).invoke(pathing, new Object[0]);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        super.onDisable();
    }

    @Subscribe
    public void onRender2D(EventHUD event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (!this.baritoneAvailable || this.baritone == null) {
            return;
        }
        if (!this.showHud.getValue()) {
            return;
        }
        try {
            Object pathing = this.baritone.getClass().getMethod("getPathingBehavior", new Class[0]).invoke(this.baritone, new Object[0]);
            boolean isPathing = (Boolean)pathing.getClass().getMethod("isPathing", new Class[0]).invoke(pathing, new Object[0]);
            String status = isPathing ? "§aРаботает" : "§7Ожидание";
            String action = this.getCurrentAction();
            MsdfFont font = Fonts.SFMEDIUM.get();
            float x2 = 4.0f;
            float y2 = 30.0f;
            DrawUtil.drawText(font, "§lBaritone§r: " + status, x2, y2, ColorProvider.rgba(255, 255, 255, 255.0f), 7.0f);
            y2 += 10.0f;
            if (isPathing && !action.isEmpty()) {
                DrawUtil.drawText(font, "§7" + action, x2, y2, ColorProvider.rgba(200, 200, 200, 255.0f), 6.0f);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String getCurrentAction() {
        try {
            Object goal;
            Object customGoal = this.baritone.getClass().getMethod("getCustomGoalProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (customGoal != null && (goal = customGoal.getClass().getMethod("getGoal", new Class[0]).invoke(customGoal, new Object[0])) != null) {
                return "goto " + String.valueOf(goal);
            }
            Object mine = this.baritone.getClass().getMethod("getMineProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (mine != null && ((Boolean)mine.getClass().getMethod("isActive", new Class[0]).invoke(mine, new Object[0])).booleanValue()) {
                return "mine (копает)";
            }
            Object follow = this.baritone.getClass().getMethod("getFollowProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (follow != null && ((Boolean)follow.getClass().getMethod("isActive", new Class[0]).invoke(follow, new Object[0])).booleanValue()) {
                return "follow (следует)";
            }
            Object build = this.baritone.getClass().getMethod("getBuilderProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (build != null && ((Boolean)build.getClass().getMethod("isActive", new Class[0]).invoke(build, new Object[0])).booleanValue()) {
                return "build (строит)";
            }
            Object explore = this.baritone.getClass().getMethod("getExploreProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (explore != null && ((Boolean)explore.getClass().getMethod("isActive", new Class[0]).invoke(explore, new Object[0])).booleanValue()) {
                return "explore (исследует)";
            }
            Object farm = this.baritone.getClass().getMethod("getFarmProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (farm != null && ((Boolean)farm.getClass().getMethod("isActive", new Class[0]).invoke(farm, new Object[0])).booleanValue()) {
                return "farm (ферма)";
            }
            Object getToBlock = this.baritone.getClass().getMethod("getGetToBlockProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (getToBlock != null && ((Boolean)getToBlock.getClass().getMethod("isActive", new Class[0]).invoke(getToBlock, new Object[0])).booleanValue()) {
                return "goto block (идёт к блоку)";
            }
            Object elytra = this.baritone.getClass().getMethod("getElytraProcess", new Class[0]).invoke(this.baritone, new Object[0]);
            if (elytra != null && ((Boolean)elytra.getClass().getMethod("isActive", new Class[0]).invoke(elytra, new Object[0])).booleanValue()) {
                return "elytra (элитра)";
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return "";
    }
}

