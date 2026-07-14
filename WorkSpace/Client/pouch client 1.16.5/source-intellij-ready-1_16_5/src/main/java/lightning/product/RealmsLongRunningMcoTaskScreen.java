/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.HashSet;
import javax.annotation.Nullable;
import lightning.product.LongRunningTask;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.RealmsDefaultUncaughtExceptionHandler;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;
import lightning.product.ErrorCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsLongRunningMcoTaskScreen
extends RealmsScreen
implements ErrorCallback {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final k_2603_m R_4764_Y;
    private volatile x_282_a G_564_y = U_2871_b.R_4764_Y;
    @Nullable
    private volatile x_282_a P_1922_E;
    private volatile boolean u_1723_Y;
    private int v_4262_N;
    private final LongRunningTask w_1484_f;
    private final int t_148_a = 212;
    public static final String[] n_1700_B = new String[]{"\u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583", "_ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584", "_ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585", "_ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586", "_ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587", "_ _ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588", "_ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587", "_ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586", "_ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585", "_ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584", "\u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583", "\u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _", "\u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _", "\u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _", "\u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _", "\u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _ _", "\u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _", "\u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _", "\u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _", "\u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _"};

    public RealmsLongRunningMcoTaskScreen(k_2603_m p_i232209_1_, LongRunningTask p_i232209_2_) {
        this.R_4764_Y = p_i232209_1_;
        this.w_1484_f = p_i232209_2_;
        p_i232209_2_.n_1700_B(this);
        Thread thread = new Thread((Runnable)p_i232209_2_, "Realms-long-running-task");
        thread.setUncaughtExceptionHandler(new RealmsDefaultUncaughtExceptionHandler(J_1907_R));
        thread.start();
    }

    @Override
    public void tick() {
        super.tick();
        NarrationHelper.J_1907_R(this.G_564_y.getString());
        ++this.v_4262_N;
        this.w_1484_f.J_1907_R();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.J_1907_R();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void init() {
        this.w_1484_f.R_4764_Y();
        this.addButton(new Button(this.width / 2 - 106, RealmsLongRunningMcoTaskScreen.G_564_y(12), 212, 20, CommonComponents.G_564_y, p_237852_1_ -> this.J_1907_R()));
    }

    private void J_1907_R() {
        this.u_1723_Y = true;
        this.w_1484_f.G_564_y();
        this.minecraft.n_1700_B(this.R_4764_Y);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RealmsLongRunningMcoTaskScreen.drawCenteredString(matrixStack, this.font, this.G_564_y, this.width / 2, RealmsLongRunningMcoTaskScreen.G_564_y(3), 0xFFFFFF);
        x_282_a itextcomponent = this.P_1922_E;
        if (itextcomponent == null) {
            RealmsLongRunningMcoTaskScreen.drawCenteredString(matrixStack, this.font, n_1700_B[this.v_4262_N % n_1700_B.length], this.width / 2, RealmsLongRunningMcoTaskScreen.G_564_y(8), 0x808080);
        } else {
            RealmsLongRunningMcoTaskScreen.drawCenteredString(matrixStack, this.font, itextcomponent, this.width / 2, RealmsLongRunningMcoTaskScreen.G_564_y(8), 0xFF0000);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void n_1700_B(x_282_a p_230434_1_) {
        this.P_1922_E = p_230434_1_;
        NarrationHelper.n_1700_B(p_230434_1_.getString());
        this.R_4764_Y();
        this.addButton(new Button(this.width / 2 - 106, this.height / 4 + 120 + 12, 200, 20, CommonComponents.w_1484_f, p_237851_1_ -> this.J_1907_R()));
    }

    private void R_4764_Y() {
        HashSet set = Sets.newHashSet((Iterable)this.buttons);
        this.children.removeIf(set::contains);
        this.buttons.clear();
    }

    public void J_1907_R(x_282_a p_224234_1_) {
        this.G_564_y = p_224234_1_;
    }

    public boolean n_1700_B() {
        return this.u_1723_Y;
    }
}


