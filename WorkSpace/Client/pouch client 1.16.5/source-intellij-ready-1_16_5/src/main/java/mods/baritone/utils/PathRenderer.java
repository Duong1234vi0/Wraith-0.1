/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package mods.baritone.utils;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lightning.product.D_1098_v;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.R_4531_p;
import lightning.product.Z_3903_F;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.event.events.RenderEvent;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPath;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalComposite;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalGetToBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalInverted;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalTwoBlocks;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.interfaces.IGoalRenderPos;
import mods.baritone.behavior.PathingBehavior;
import mods.baritone.pathing.path.PathExecutor;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.GuiClick;
import mods.baritone.utils.IRenderer;
import org.lwjgl.opengl.GL11;

public final class PathRenderer
implements IRenderer {
    private static final g_2336_b TEXTURE_BEACON_BEAM = new g_2336_b("textures/entity/beacon_beam.png");

    private PathRenderer() {
    }

    public static double posX() {
        return renderManager.renderPosX();
    }

    public static double posY() {
        return renderManager.renderPosY();
    }

    public static double posZ() {
        return renderManager.renderPosZ();
    }

    public static void render(RenderEvent event, PathingBehavior behavior) {
        Z_3903_F currentRenderViewDimension;
        IPlayerContext ctx = behavior.ctx;
        if (ctx.world() == null) {
            return;
        }
        if (ctx.minecraft().Y_1740_V instanceof GuiClick) {
            ((GuiClick)ctx.minecraft().Y_1740_V).onRender(event.getModelViewStack(), event.getProjectionMatrix());
        }
        float partialTicks = event.getPartialTicks();
        Goal goal = behavior.getGoal();
        Z_3903_F thisPlayerDimension = ctx.world().G_624_v();
        if (thisPlayerDimension != (currentRenderViewDimension = BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext().world().G_624_v())) {
            return;
        }
        if (goal != null && ((Boolean)PathRenderer.settings.renderGoal.value).booleanValue()) {
            PathRenderer.drawGoal(event.getModelViewStack(), ctx, goal, partialTicks, (Color)PathRenderer.settings.colorGoalBox.value);
        }
        if (!((Boolean)PathRenderer.settings.renderPath.value).booleanValue()) {
            return;
        }
        PathExecutor current = behavior.getCurrent();
        PathExecutor next = behavior.getNext();
        if (current != null && ((Boolean)PathRenderer.settings.renderSelectionBoxes.value).booleanValue()) {
            PathRenderer.drawManySelectionBoxes(event.getModelViewStack(), ctx.player(), current.toBreak(), (Color)PathRenderer.settings.colorBlocksToBreak.value);
            PathRenderer.drawManySelectionBoxes(event.getModelViewStack(), ctx.player(), current.toPlace(), (Color)PathRenderer.settings.colorBlocksToPlace.value);
            PathRenderer.drawManySelectionBoxes(event.getModelViewStack(), ctx.player(), current.toWalkInto(), (Color)PathRenderer.settings.colorBlocksToWalkInto.value);
        }
        if (current != null && current.getPath() != null) {
            int renderBegin = Math.max(current.getPosition() - 3, 0);
            PathRenderer.drawPath(event.getModelViewStack(), current.getPath(), renderBegin, (Color)PathRenderer.settings.colorCurrentPath.value, (Boolean)PathRenderer.settings.fadePath.value, 10, 20);
        }
        if (next != null && next.getPath() != null) {
            PathRenderer.drawPath(event.getModelViewStack(), next.getPath(), 0, (Color)PathRenderer.settings.colorNextPath.value, (Boolean)PathRenderer.settings.fadePath.value, 10, 20);
        }
        behavior.getInProgress().ifPresent(currentlyRunning -> {
            currentlyRunning.bestPathSoFar().ifPresent(p -> PathRenderer.drawPath(event.getModelViewStack(), p, 0, (Color)PathRenderer.settings.colorBestPathSoFar.value, (Boolean)PathRenderer.settings.fadePath.value, 10, 20));
            currentlyRunning.pathToMostRecentNodeConsidered().ifPresent(mr -> {
                PathRenderer.drawPath(event.getModelViewStack(), mr, 0, (Color)PathRenderer.settings.colorMostRecentConsidered.value, (Boolean)PathRenderer.settings.fadePath.value, 10, 20);
                PathRenderer.drawManySelectionBoxes(event.getModelViewStack(), ctx.player(), Collections.singletonList(mr.getDest()), (Color)PathRenderer.settings.colorMostRecentConsidered.value);
            });
        });
    }

    private static void drawPath(g_221_o stack, IPath path, int startIndex, Color color, boolean fadeOut, int fadeStart0, int fadeEnd0) {
        IRenderer.startLines(color, ((Float)PathRenderer.settings.pathRenderLineWidthPixels.value).floatValue(), (Boolean)PathRenderer.settings.renderPathIgnoreDepth.value);
        int fadeStart = fadeStart0 + startIndex;
        int fadeEnd = fadeEnd0 + startIndex;
        List<BetterBlockPos> positions = path.positions();
        int i = startIndex;
        while (i < positions.size() - 1) {
            BetterBlockPos start = positions.get(i);
            int next = i + 1;
            BetterBlockPos end = positions.get(next);
            int dirX = end.x - start.x;
            int dirY = end.y - start.y;
            int dirZ = end.z - start.z;
            while (!(next + 1 >= positions.size() || fadeOut && next + 1 >= fadeStart || dirX != positions.get((int)(next + 1)).x - end.x || dirY != positions.get((int)(next + 1)).y - end.y || dirZ != positions.get((int)(next + 1)).z - end.z)) {
                end = positions.get(++next);
            }
            if (fadeOut) {
                float alpha;
                if (i <= fadeStart) {
                    alpha = 0.4f;
                } else {
                    if (i > fadeEnd) break;
                    alpha = 0.4f * (1.0f - (float)(i - fadeStart) / (float)(fadeEnd - fadeStart));
                }
                IRenderer.glColor(color, alpha);
            }
            PathRenderer.emitLine(stack, start.x, start.y, start.z, end.x, end.y, end.z);
            i = next;
        }
        IRenderer.endLines((Boolean)PathRenderer.settings.renderPathIgnoreDepth.value);
    }

    private static void emitLine(g_221_o stack, double x1, double y1, double z1, double x2, double y2, double z2) {
        D_1098_v matrix4f = stack.R_4764_Y().n_1700_B();
        double vpX = PathRenderer.posX();
        double vpY = PathRenderer.posY();
        double vpZ = PathRenderer.posZ();
        boolean renderPathAsFrickinThingy = (Boolean)PathRenderer.settings.renderPathAsLine.value == false;
        buffer.n_1700_B(matrix4f, (float)(x1 + 0.5 - vpX), (float)(y1 + 0.5 - vpY), (float)(z1 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)(x2 + 0.5 - vpX), (float)(y2 + 0.5 - vpY), (float)(z2 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        if (renderPathAsFrickinThingy) {
            buffer.n_1700_B(matrix4f, (float)(x2 + 0.5 - vpX), (float)(y2 + 0.5 - vpY), (float)(z2 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)(x2 + 0.5 - vpX), (float)(y2 + 0.53 - vpY), (float)(z2 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)(x2 + 0.5 - vpX), (float)(y2 + 0.53 - vpY), (float)(z2 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)(x1 + 0.5 - vpX), (float)(y1 + 0.53 - vpY), (float)(z1 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)(x1 + 0.5 - vpX), (float)(y1 + 0.53 - vpY), (float)(z1 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)(x1 + 0.5 - vpX), (float)(y1 + 0.5 - vpY), (float)(z1 + 0.5 - vpZ)).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        }
    }

    public static void drawManySelectionBoxes(g_221_o stack, N_4263_v player, Collection<c_1514_x> positions, Color color) {
        IRenderer.startLines(color, ((Float)PathRenderer.settings.pathRenderLineWidthPixels.value).floatValue(), (Boolean)PathRenderer.settings.renderSelectionBoxesIgnoreDepth.value);
        BlockStateInterface bsi = new BlockStateInterface(BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext());
        positions.forEach(pos -> {
            K_4074_S state = bsi.get0((c_1514_x)pos);
            s_1395_c shape = state.s_956_w(player.O_508_d, (c_1514_x)pos);
            I_4817_s toDraw = shape.J_1907_R() ? x_268_Y.J_1907_R().n_1700_B() : shape.n_1700_B();
            toDraw = toDraw.offset((c_1514_x)pos);
            IRenderer.emitAABB(stack, toDraw, 0.002);
        });
        IRenderer.endLines((Boolean)PathRenderer.settings.renderSelectionBoxesIgnoreDepth.value);
    }

    private static void drawGoal(g_221_o stack, IPlayerContext ctx, Goal goal, float partialTicks, Color color) {
        PathRenderer.drawGoal(stack, ctx, goal, partialTicks, color, true);
    }

    private static void drawGoal(g_221_o stack, IPlayerContext ctx, Goal goal, float partialTicks, Color color, boolean setupRender) {
        double renderPosX = PathRenderer.posX();
        double renderPosY = PathRenderer.posY();
        double renderPosZ = PathRenderer.posZ();
        double y = (Boolean)PathRenderer.settings.renderGoalAnimated.value == false ? (double)0.999f : (double)u_530_F.J_1907_R((float)((double)((float)(System.nanoTime() / 100000L % 20000L) / 20000.0f) * Math.PI * 2.0));
        if (goal instanceof IGoalRenderPos) {
            c_1514_x goalPos = ((IGoalRenderPos)((Object)goal)).getGoalPos();
            double minX = (double)goalPos.getX() + 0.002 - renderPosX;
            double maxX = (double)(goalPos.getX() + 1) - 0.002 - renderPosX;
            double minZ = (double)goalPos.getZ() + 0.002 - renderPosZ;
            double maxZ = (double)(goalPos.getZ() + 1) - 0.002 - renderPosZ;
            if (goal instanceof GoalGetToBlock || goal instanceof GoalTwoBlocks) {
                y /= 2.0;
            }
            double y1 = 1.0 + y + (double)goalPos.getY() - renderPosY;
            double y2 = 1.0 - y + (double)goalPos.getY() - renderPosY;
            double minY = (double)goalPos.getY() - renderPosY;
            double maxY = minY + 2.0;
            if (goal instanceof GoalGetToBlock || goal instanceof GoalTwoBlocks) {
                y1 -= 0.5;
                y2 -= 0.5;
                maxY -= 1.0;
            }
            PathRenderer.drawDankLitGoalBox(stack, color, minX, maxX, minZ, maxZ, minY, maxY, y1, y2, setupRender);
        } else if (goal instanceof GoalXZ) {
            GoalXZ goalPos = (GoalXZ)goal;
            if (((Boolean)PathRenderer.settings.renderGoalXZBeacon.value).booleanValue()) {
                GL11.glPushAttrib((int)64);
                textureManager.n_1700_B(TEXTURE_BEACON_BEAM);
                if (((Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value).booleanValue()) {
                    c_4037_x.t_1786_h();
                }
                stack.n_1700_B();
                stack.n_1700_B((double)goalPos.getX() - renderPosX, -renderPosY, (double)goalPos.getZ() - renderPosZ);
                R_4531_p.n_1700_B(stack, ctx.minecraft().j_1564_a().J_1907_R(), TEXTURE_BEACON_BEAM, (Boolean)PathRenderer.settings.renderGoalAnimated.value != false ? partialTicks : 0.0f, 1.0f, (Boolean)PathRenderer.settings.renderGoalAnimated.value != false ? ctx.world().X_933_l() : 0L, 0, 256, color.getColorComponents(null), 0.2f, 0.25f);
                stack.J_1907_R();
                if (((Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value).booleanValue()) {
                    c_4037_x.multiplayerClientSuggestionProvider();
                }
                GL11.glPopAttrib();
                return;
            }
            double minX = (double)goalPos.getX() + 0.002 - renderPosX;
            double maxX = (double)(goalPos.getX() + 1) - 0.002 - renderPosX;
            double minZ = (double)goalPos.getZ() + 0.002 - renderPosZ;
            double maxZ = (double)(goalPos.getZ() + 1) - 0.002 - renderPosZ;
            double y1 = 0.0;
            double y2 = 0.0;
            double minY = 0.0 - renderPosY;
            double maxY = 256.0 - renderPosY;
            PathRenderer.drawDankLitGoalBox(stack, color, minX, maxX, minZ, maxZ, minY, maxY, y1, y2, setupRender);
        } else if (goal instanceof GoalComposite) {
            boolean batch = Arrays.stream(((GoalComposite)goal).goals()).allMatch(IGoalRenderPos.class::isInstance);
            if (batch) {
                IRenderer.startLines(color, ((Float)PathRenderer.settings.goalRenderLineWidthPixels.value).floatValue(), (Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value);
            }
            for (Goal g : ((GoalComposite)goal).goals()) {
                PathRenderer.drawGoal(stack, ctx, g, partialTicks, color, !batch);
            }
            if (batch) {
                IRenderer.endLines((Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value);
            }
        } else if (goal instanceof GoalInverted) {
            PathRenderer.drawGoal(stack, ctx, ((GoalInverted)goal).origin, partialTicks, (Color)PathRenderer.settings.colorInvertedGoalBox.value);
        } else if (goal instanceof GoalYLevel) {
            GoalYLevel goalpos = (GoalYLevel)goal;
            double minX = ctx.player().s_4990_V().J_1907_R - (Double)PathRenderer.settings.yLevelBoxSize.value - renderPosX;
            double minZ = ctx.player().s_4990_V().G_564_y - (Double)PathRenderer.settings.yLevelBoxSize.value - renderPosZ;
            double maxX = ctx.player().s_4990_V().J_1907_R + (Double)PathRenderer.settings.yLevelBoxSize.value - renderPosX;
            double maxZ = ctx.player().s_4990_V().G_564_y + (Double)PathRenderer.settings.yLevelBoxSize.value - renderPosZ;
            double minY = (double)((GoalYLevel)goal).level - renderPosY;
            double maxY = minY + 2.0;
            double y1 = 1.0 + y + (double)goalpos.level - renderPosY;
            double y2 = 1.0 - y + (double)goalpos.level - renderPosY;
            PathRenderer.drawDankLitGoalBox(stack, color, minX, maxX, minZ, maxZ, minY, maxY, y1, y2, setupRender);
        }
    }

    private static void drawDankLitGoalBox(g_221_o stack, Color colorIn, double minX, double maxX, double minZ, double maxZ, double minY, double maxY, double y1, double y2, boolean setupRender) {
        if (setupRender) {
            IRenderer.startLines(colorIn, ((Float)PathRenderer.settings.goalRenderLineWidthPixels.value).floatValue(), (Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value);
        }
        PathRenderer.renderHorizontalQuad(stack, minX, maxX, minZ, maxZ, y1);
        PathRenderer.renderHorizontalQuad(stack, minX, maxX, minZ, maxZ, y2);
        D_1098_v matrix4f = stack.R_4764_Y().n_1700_B();
        buffer.n_1700_B(matrix4f, (float)minX, (float)minY, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)minX, (float)maxY, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)maxX, (float)minY, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)maxX, (float)maxY, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)maxX, (float)minY, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)maxX, (float)maxY, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)minX, (float)minY, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        buffer.n_1700_B(matrix4f, (float)minX, (float)maxY, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        if (setupRender) {
            IRenderer.endLines((Boolean)PathRenderer.settings.renderGoalIgnoreDepth.value);
        }
    }

    private static void renderHorizontalQuad(g_221_o stack, double minX, double maxX, double minZ, double maxZ, double y) {
        if (y != 0.0) {
            D_1098_v matrix4f = stack.R_4764_Y().n_1700_B();
            buffer.n_1700_B(matrix4f, (float)minX, (float)y, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)maxX, (float)y, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)maxX, (float)y, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)maxX, (float)y, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)maxX, (float)y, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)minX, (float)y, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)minX, (float)y, (float)maxZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
            buffer.n_1700_B(matrix4f, (float)minX, (float)y, (float)minZ).n_1700_B(color[0], color[1], color[2], color[3]).endVertex();
        }
    }
}


