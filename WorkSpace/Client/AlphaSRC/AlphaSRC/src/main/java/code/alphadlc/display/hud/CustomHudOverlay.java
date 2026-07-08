/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_9779
 */
package code.alphadlc.display.hud;

import code.alphadlc.display.hud.HudDraggableRenderer;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.GuiRenderState;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_332;
import net.minecraft.class_9779;

public final class CustomHudOverlay
implements QuickImports {
    private CustomHudOverlay() {
    }

    public static void render(class_332 context, class_9779 tickCounter, boolean renderDraggables) {
        if (context == null || drawEngine == null || tickCounter == null || CustomHudOverlay.mc.field_1724 == null || CustomHudOverlay.mc.field_1687 == null) {
            return;
        }
        if (CustomHudOverlay.mc.field_1755 != null && !PlayerInteractionHelper.isChat(CustomHudOverlay.mc.field_1755)) {
            return;
        }
        if (Hud.blur.isValue()) {
            KawaseBlur.INSTANCE.render(context.method_51448(), 6, 8.0f);
        }
        SafeEvents.dispatch(() -> new DrawEvent(context, drawEngine, tickCounter.method_60637(false)));
        Render2D.onRender(context);
        context.method_51452();
        if (renderDraggables) {
            HudDraggableRenderer.render(context);
        }
        context.method_51452();
        GuiRenderState.restoreAfterCustomDraw();
    }
}
