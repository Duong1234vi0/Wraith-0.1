/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.bridge.Bridge
 *  com.mojang.bridge.game.GameSession
 *  com.mojang.bridge.game.GameVersion
 *  com.mojang.bridge.game.Language
 *  com.mojang.bridge.game.PerformanceMetrics
 *  com.mojang.bridge.game.RunningGame
 *  com.mojang.bridge.launcher.Launcher
 *  com.mojang.bridge.launcher.SessionEventListener
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.bridge.Bridge;
import com.mojang.bridge.game.GameSession;
import com.mojang.bridge.game.GameVersion;
import com.mojang.bridge.game.Language;
import com.mojang.bridge.game.PerformanceMetrics;
import com.mojang.bridge.game.RunningGame;
import com.mojang.bridge.launcher.Launcher;
import com.mojang.bridge.launcher.SessionEventListener;
import java.util.Iterator;
import javax.annotation.Nullable;
import lightning.product.Session;
import lightning.product.SharedConstants;
import lightning.product.J_1907_R;
import lightning.product.MinecraftClient;
import lightning.product.c_3005_b;
import lightning.product.k_4690_i;

public class q_3575_f
implements RunningGame {
    private final MinecraftClient n_1700_B;
    @Nullable
    private final Launcher J_1907_R;
    private SessionEventListener R_4764_Y = SessionEventListener.NONE;

    public q_3575_f(MinecraftClient gameInstance) {
        this.n_1700_B = gameInstance;
        this.J_1907_R = Bridge.getLauncher();
        if (this.J_1907_R != null) {
            this.J_1907_R.registerGame((RunningGame)this);
        }
    }

    public GameVersion getVersion() {
        return SharedConstants.n_1700_B();
    }

    public Language getSelectedLanguage() {
        return this.n_1700_B.e_2887_G().J_1907_R();
    }

    @Nullable
    public GameSession getCurrentSession() {
        k_4690_i clientworld = this.n_1700_B.Y_601_j;
        return clientworld == null ? null : new Session(clientworld, this.n_1700_B.Y_259_p, this.n_1700_B.Y_259_p.n_1700_B);
    }

    @Nullable
    public GameSession n_1700_B() {
        lightning.product.n_1700_B bot;
        Iterator<lightning.product.n_1700_B> var1 = lightning.product.J_1907_R.n_1700_B.iterator();
        do {
            if (!var1.hasNext()) {
                return null;
            }
            bot = var1.next();
        } while (this.n_1700_B.C_2741_M != bot.P_1922_E.Q_2552_b);
        c_3005_b clientworld = bot.P_1922_E.G_564_y();
        return new Session(clientworld, bot.P_1922_E.Q_2552_b, bot.P_1922_E.Q_2552_b.n_1700_B);
    }

    public PerformanceMetrics getPerformanceMetrics() {
        return new n_1700_B(0, 0, 0, 0);
    }

    public void setSessionEventListener(SessionEventListener p_setSessionEventListener_1_) {
        this.R_4764_Y = p_setSessionEventListener_1_;
    }

    public void J_1907_R() {
        this.R_4764_Y.onStartGameSession(this.getCurrentSession());
    }

    public void R_4764_Y() {
        this.R_4764_Y.onLeaveGameSession(this.getCurrentSession());
    }

    public void G_564_y() {
        this.R_4764_Y.onStartGameSession(this.getCurrentSession());
    }

    public void P_1922_E() {
        this.R_4764_Y.onLeaveGameSession(this.getCurrentSession());
    }

    static class n_1700_B
    implements PerformanceMetrics {
        private final int n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(int minTime, int maxTime, int averageTime, int sampleCount) {
            this.n_1700_B = minTime;
            this.J_1907_R = maxTime;
            this.R_4764_Y = averageTime;
            this.G_564_y = sampleCount;
        }

        public int getMinTime() {
            return this.n_1700_B;
        }

        public int getMaxTime() {
            return this.J_1907_R;
        }

        public int getAverageTime() {
            return this.R_4764_Y;
        }

        public int getSampleCount() {
            return this.G_564_y;
        }
    }
}



