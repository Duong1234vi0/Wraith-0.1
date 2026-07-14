/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.selection;

import java.awt.Color;
import lightning.product.I_4817_s;
import lightning.product.g_221_o;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.event.events.RenderEvent;
import mods.baritone.api.api.java.baritone.api.event.listener.AbstractGameEventListener;
import mods.baritone.api.api.java.baritone.api.selection.ISelection;
import mods.baritone.selection.SelectionManager;
import mods.baritone.utils.IRenderer;

public class SelectionRenderer
implements AbstractGameEventListener,
IRenderer {
    public static final double SELECTION_BOX_EXPANSION = 0.005;
    private final SelectionManager manager;

    SelectionRenderer(Baritone baritone, SelectionManager manager) {
        this.manager = manager;
        baritone.getGameEventHandler().registerEventListener(this);
    }

    public static void renderSelections(g_221_o stack, ISelection[] selections) {
        float opacity = ((Float)SelectionRenderer.settings.selectionOpacity.value).floatValue();
        boolean ignoreDepth = (Boolean)SelectionRenderer.settings.renderSelectionIgnoreDepth.value;
        float lineWidth = ((Float)SelectionRenderer.settings.selectionLineWidth.value).floatValue();
        if (!((Boolean)SelectionRenderer.settings.renderSelection.value).booleanValue() || selections.length == 0) {
            return;
        }
        IRenderer.startLines((Color)SelectionRenderer.settings.colorSelection.value, opacity, lineWidth, ignoreDepth);
        for (ISelection selection : selections) {
            IRenderer.emitAABB(stack, selection.aabb(), 0.005);
        }
        if (((Boolean)SelectionRenderer.settings.renderSelectionCorners.value).booleanValue()) {
            IRenderer.glColor((Color)SelectionRenderer.settings.colorSelectionPos1.value, opacity);
            for (ISelection selection : selections) {
                IRenderer.emitAABB(stack, new I_4817_s(selection.pos1(), selection.pos1().add(1, 1, 1)));
            }
            IRenderer.glColor((Color)SelectionRenderer.settings.colorSelectionPos2.value, opacity);
            for (ISelection selection : selections) {
                IRenderer.emitAABB(stack, new I_4817_s(selection.pos2(), selection.pos2().add(1, 1, 1)));
            }
        }
        IRenderer.endLines(ignoreDepth);
    }

    @Override
    public void onRenderPass(RenderEvent event) {
        SelectionRenderer.renderSelections(event.getModelViewStack(), this.manager.getSelections());
    }
}

