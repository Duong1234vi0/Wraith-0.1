/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import java.awt.Color;
import java.util.Collections;
import lightning.product.D_1098_v;
import lightning.product.D_4024_W;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.V_772_m;
import lightning.product.Z_2491_A;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.i_2909_p;
import lightning.product.k_2603_m;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.utils.IRenderer;
import mods.baritone.utils.PathRenderer;

public class GuiClick
extends k_2603_m
implements Helper {
    private D_1098_v projectionViewMatrix;
    private c_1514_x clickStart;
    private c_1514_x currentMouseOver;

    public GuiClick() {
        super(new U_2871_b("CLICK"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(g_221_o stack, int mouseX, int mouseY, float partialTicks) {
        double mx = GuiClick.mc.h_1847_R.G_564_y();
        double my = GuiClick.mc.h_1847_R.P_1922_E();
        my = (double)mc.RealmsServerPing().h_1847_R() - my;
        e_2866_D near = this.toWorld(mx *= (double)mc.RealmsServerPing().u_2550_I() / (double)mc.RealmsServerPing().P_4830_p(), my *= (double)mc.RealmsServerPing().M_588_G() / (double)mc.RealmsServerPing().h_1847_R(), 0.0);
        e_2866_D far = this.toWorld(mx, my, 1.0);
        if (near != null && far != null) {
            e_2866_D viewerPos = new e_2866_D(PathRenderer.posX(), PathRenderer.posY(), PathRenderer.posZ());
            V_772_m player = BaritoneAPI.getProvider().getPrimaryBaritone().getPlayerContext().player();
            BlockHitResult result = player.O_508_d.n_1700_B(new ClipContext(near.P_1922_E(viewerPos), far.P_1922_E(viewerPos), ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, player));
            if (result != null && ((HitResult)result).R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
                this.currentMouseOver = result.n_1700_B();
            }
        }
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (this.currentMouseOver != null) {
            if (mouseButton == 0) {
                if (this.clickStart != null && !this.clickStart.equals(this.currentMouseOver)) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().removeAllSelections();
                    BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().addSelection(BetterBlockPos.from(this.clickStart), BetterBlockPos.from(this.currentMouseOver));
                    U_2871_b component = new U_2871_b("Selection made! For usage: " + (String)Baritone.settings().prefix.value + "help sel");
                    component.n_1700_B(component.n_1700_B().n_1700_B(D_4024_W.M_182_A).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "help sel")));
                    Helper.HELPER.logDirect(component);
                    this.clickStart = null;
                } else {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.currentMouseOver));
                }
            } else if (mouseButton == 1) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.currentMouseOver.up()));
            }
        }
        this.clickStart = null;
        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        this.clickStart = this.currentMouseOver;
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onRender(g_221_o modelViewStack, D_1098_v projectionMatrix) {
        this.projectionViewMatrix = projectionMatrix.u_1723_Y();
        this.projectionViewMatrix.n_1700_B(modelViewStack.R_4764_Y().n_1700_B());
        this.projectionViewMatrix.P_1922_E();
        if (this.currentMouseOver != null) {
            N_4263_v e = mc.g_2268_R();
            PathRenderer.drawManySelectionBoxes(modelViewStack, e, Collections.singletonList(this.currentMouseOver), Color.CYAN);
            if (this.clickStart != null && !this.clickStart.equals(this.currentMouseOver)) {
                IRenderer.startLines(Color.RED, ((Float)Baritone.settings().pathRenderLineWidthPixels.value).floatValue(), true);
                BetterBlockPos a = new BetterBlockPos(this.currentMouseOver);
                BetterBlockPos b = new BetterBlockPos(this.clickStart);
                IRenderer.emitAABB(modelViewStack, new I_4817_s(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z), Math.max(a.x, b.x) + 1, Math.max(a.y, b.y) + 1, Math.max(a.z, b.z) + 1));
                IRenderer.endLines(true);
            }
        }
    }

    private e_2866_D toWorld(double x, double y, double z) {
        if (this.projectionViewMatrix == null) {
            return null;
        }
        x /= (double)mc.RealmsServerPing().u_2550_I();
        y /= (double)mc.RealmsServerPing().M_588_G();
        x = x * 2.0 - 1.0;
        y = y * 2.0 - 1.0;
        Z_2491_A pos = new Z_2491_A((float)x, (float)y, (float)z, 1.0f);
        pos.n_1700_B(this.projectionViewMatrix);
        if (pos.G_564_y() == 0.0f) {
            return null;
        }
        pos.u_1723_Y();
        return new e_2866_D(pos.n_1700_B(), pos.J_1907_R(), pos.R_4764_Y());
    }
}


