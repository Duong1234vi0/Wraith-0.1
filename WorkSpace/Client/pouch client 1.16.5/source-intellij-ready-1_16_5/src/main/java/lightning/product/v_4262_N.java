/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.V_3137_a;
import lightning.product.Objective;
import lightning.product.Y_259_p;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.i_4895_l;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.u_1723_Y;
import lightning.product.v_4839_y;
import lightning.product.w_1484_f;
import lightning.product.x_268_Y;

public class v_4262_N
implements u_1723_Y {
    private n_1700_B n_1700_B = lightning.product.v_4262_N$n_1700_B.n_1700_B;
    private c_1514_x J_1907_R = null;
    private b_257_Y R_4764_Y = b_257_Y.J_1907_R;
    private int G_564_y = 0;
    private final Y_259_p P_1922_E = new Y_259_p(null);
    private int u_1723_Y = -1;
    private boolean v_4262_N = false;
    private static final int w_1484_f = 150000;
    private static final int t_148_a = 100;
    private int s_956_w = 0;
    private int u_2550_I = 0;
    private boolean M_588_G = false;

    public v_4262_N() {
        this.n_1700_B = lightning.product.v_4262_N$n_1700_B.n_1700_B;
    }

    @Override
    public void n_1700_B(lightning.product.n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null || bot.J_1907_R == null || bot.G_564_y == null) {
            return;
        }
        if (this.P_1922_E.n_1700_B() == null) {
            this.P_1922_E.n_1700_B(bot);
        }
        switch (this.n_1700_B.ordinal()) {
            case 0: {
                this.J_1907_R(bot);
                break;
            }
            case 1: {
                this.R_4764_Y(bot);
                break;
            }
            case 2: {
                this.G_564_y(bot);
                break;
            }
            case 3: {
                this.P_1922_E(bot);
                break;
            }
            case 4: {
                this.u_1723_Y(bot);
                break;
            }
            case 5: {
                this.v_4262_N(bot);
            }
        }
    }

    private void J_1907_R(lightning.product.n_1700_B bot) {
        ++this.s_956_w;
        c_1514_x block = this.t_148_a(bot);
        if (block == null) {
            return;
        }
        this.s_956_w = 0;
        this.J_1907_R = block.toImmutable();
        this.R_4764_Y = this.n_1700_B(bot, this.J_1907_R);
        bot.u_1723_Y.J_1907_R(this.J_1907_R);
        if (!this.v_4262_N) {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.R_4764_Y;
            this.u_2550_I = 0;
            this.M_588_G = false;
        } else {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.J_1907_R;
        }
        this.G_564_y = 0;
    }

    private void R_4764_Y(lightning.product.n_1700_B bot) {
        K_4074_S state;
        if (this.J_1907_R == null) {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.n_1700_B;
            return;
        }
        if (++this.G_564_y >= 150000) {
            this.u_1723_Y = this.w_1484_f(bot);
            if (this.u_1723_Y > 0) {
                bot.P_1922_E.Q_2552_b.n_1700_B("/sellwood");
                this.n_1700_B = lightning.product.v_4262_N$n_1700_B.G_564_y;
                this.G_564_y = 0;
            } else {
                this.G_564_y = 0;
            }
            return;
        }
        c_1514_x currentBlock = this.t_148_a(bot);
        if (currentBlock == null) {
            return;
        }
        int y = currentBlock.getY();
        if (y < 0 || y >= 256 || !bot.J_1907_R.M_588_G(currentBlock)) {
            return;
        }
        if (!this.J_1907_R.equals(currentBlock)) {
            this.J_1907_R = currentBlock.toImmutable();
            this.R_4764_Y = this.n_1700_B(bot, this.J_1907_R);
        }
        if ((state = bot.J_1907_R.getBlockState(this.J_1907_R)).v_4262_N() || !this.n_1700_B(bot, this.J_1907_R, state)) {
            return;
        }
        b_257_Y face = this.n_1700_B(bot, this.J_1907_R);
        bot.u_1723_Y.J_1907_R(this.J_1907_R);
        if (!bot.G_564_y.h_1847_R()) {
            bot.G_564_y.n_1700_B(this.J_1907_R, face);
            bot.G_564_y.J_1907_R(this.J_1907_R, face);
        }
        bot.P_1922_E.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, this.J_1907_R, face));
    }

    private void G_564_y(lightning.product.n_1700_B bot) {
        int ticksToBreak;
        if (this.J_1907_R == null) {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.n_1700_B;
            this.M_588_G = false;
            return;
        }
        K_4074_S state = bot.J_1907_R.getBlockState(this.J_1907_R);
        if (state.v_4262_N()) {
            ++this.G_564_y;
            this.v_4262_N = true;
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.J_1907_R;
            this.J_1907_R = null;
            this.u_2550_I = 0;
            this.M_588_G = false;
            return;
        }
        int y = this.J_1907_R.getY();
        if (y < 0 || y >= 256 || !bot.J_1907_R.M_588_G(this.J_1907_R)) {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.n_1700_B;
            this.M_588_G = false;
            return;
        }
        if (!this.n_1700_B(bot, this.J_1907_R, state)) {
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.J_1907_R;
            this.M_588_G = false;
            return;
        }
        b_257_Y face = this.n_1700_B(bot, this.J_1907_R);
        bot.u_1723_Y.J_1907_R(this.J_1907_R);
        if (!this.M_588_G) {
            bot.P_1922_E.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, this.J_1907_R, face));
            this.M_588_G = true;
            this.u_2550_I = 0;
            return;
        }
        ++this.u_2550_I;
        float hardness = state.n_1700_B(bot.R_4764_Y, bot.J_1907_R, this.J_1907_R);
        int n = ticksToBreak = hardness <= 0.0f ? 100 : Math.max(10, (int)(1.0f / hardness));
        if (this.u_2550_I >= ticksToBreak) {
            bot.P_1922_E.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, this.J_1907_R, face));
            this.v_4262_N = true;
            this.u_2550_I = 0;
            this.M_588_G = false;
            this.J_1907_R = null;
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.J_1907_R;
        }
    }

    private void P_1922_E(lightning.product.n_1700_B bot) {
        if (++this.G_564_y >= 100) {
            bot.P_1922_E.Q_2552_b.n_1700_B("/hub");
            this.n_1700_B = lightning.product.v_4262_N$n_1700_B.P_1922_E;
            this.G_564_y = 0;
        }
    }

    private void u_1723_Y(lightning.product.n_1700_B bot) {
        if (++this.G_564_y >= 100) {
            bot.n_1700_B(new w_1484_f(this.u_1723_Y));
        }
    }

    private void v_4262_N(lightning.product.n_1700_B bot) {
    }

    private int w_1484_f(lightning.product.n_1700_B bot) {
        try {
            if (bot == null || bot.J_1907_R == null) {
                return -1;
            }
            i_4895_l scoreboard = bot.J_1907_R.Q_4569_t();
            if (scoreboard == null) {
                return -1;
            }
            for (int slot = 0; slot <= 2; ++slot) {
                String nums;
                String upper;
                String cleanTitle;
                Objective objective = scoreboard.n_1700_B(slot);
                if (objective == null) continue;
                String title = objective.G_564_y().getString();
                if (title != null && (cleanTitle = D_4024_W.n_1700_B(title)) != null && ((upper = cleanTitle.toUpperCase()).contains("\u0413\u0420\u0418\u0424") || upper.contains("GRIEF")) && !(nums = cleanTitle.replaceAll("[^0-9]", "")).isEmpty()) {
                    return Integer.parseInt(nums);
                }
                for (v_4839_y score : scoreboard.n_1700_B(objective)) {
                    String nums2;
                    String upper2;
                    String cleanLine;
                    String line = score.P_1922_E();
                    if (line == null || line.isEmpty() || (cleanLine = D_4024_W.n_1700_B(line)) == null || !(upper2 = cleanLine.toUpperCase()).contains("\u0413\u0420\u0418\u0424") && !upper2.contains("GRIEF") || (nums2 = cleanLine.replaceAll("[^0-9]", "")).isEmpty()) continue;
                    return Integer.parseInt(nums2);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return -1;
    }

    private boolean n_1700_B(lightning.product.n_1700_B bot, c_1514_x pos, K_4074_S state) {
        if (!bot.G_564_y.w_1484_f() && state.w_1484_f(bot.J_1907_R, pos) < 0.0f) {
            return false;
        }
        if (state.s_956_w(bot.J_1907_R, pos) == x_268_Y.n_1700_B()) {
            return false;
        }
        return this.n_1700_B(state);
    }

    private boolean n_1700_B(K_4074_S state) {
        g_2336_b blockId = V_3137_a.q_4610_l.J_1907_R(state.J_1907_R());
        if (blockId == null) {
            return false;
        }
        String blockName = blockId.toString();
        return blockName.contains("_log") || blockName.contains("_wood") || blockName.equals("minecraft:crimson_stem") || blockName.equals("minecraft:warped_stem") || blockName.equals("minecraft:stripped_crimson_stem") || blockName.equals("minecraft:stripped_warped_stem");
    }

    private b_257_Y n_1700_B(lightning.product.n_1700_B bot, c_1514_x target) {
        BlockHitResult result;
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.J_1907_R && target.equals((result = (BlockHitResult)bot.R_4764_Y.v_4262_N).n_1700_B())) {
            return result.J_1907_R();
        }
        return b_257_Y.J_1907_R;
    }

    private c_1514_x t_148_a(lightning.product.n_1700_B bot) {
        K_4074_S state;
        BlockHitResult res;
        c_1514_x pos;
        K_4074_S state2;
        c_1514_x currentBreaking;
        if (bot.G_564_y.h_1847_R() && (currentBreaking = bot.G_564_y.Q_4569_t()) != null && this.n_1700_B(state2 = bot.J_1907_R.getBlockState(currentBreaking))) {
            return currentBreaking;
        }
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.J_1907_R && this.J_1907_R(bot, pos = (res = (BlockHitResult)bot.R_4764_Y.v_4262_N).n_1700_B()) && this.n_1700_B(state = bot.J_1907_R.getBlockState(pos))) {
            return pos;
        }
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
            K_4074_S state3;
            c_1514_x pos2;
            e_2866_D eyePos = bot.R_4764_Y.u_2550_I(1.0f);
            e_2866_D lookVec = bot.R_4764_Y.t_148_a(1.0f);
            double reach = bot.G_564_y.R_4764_Y();
            e_2866_D endPos = eyePos.J_1907_R(lookVec.J_1907_R * reach, lookVec.R_4764_Y * reach, lookVec.G_564_y * reach);
            ClipContext context = new ClipContext(eyePos, endPos, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, bot.R_4764_Y);
            BlockHitResult blockTrace = bot.J_1907_R.n_1700_B(context);
            if (blockTrace != null && blockTrace.R_4764_Y() == HitResult.n_1700_B.J_1907_R && this.J_1907_R(bot, pos2 = blockTrace.n_1700_B()) && this.n_1700_B(state3 = bot.J_1907_R.getBlockState(pos2))) {
                return pos2;
            }
        }
        return null;
    }

    private boolean J_1907_R(lightning.product.n_1700_B bot, c_1514_x pos) {
        double reach;
        e_2866_D center;
        e_2866_D eye = bot.R_4764_Y.u_2550_I(1.0f);
        double distSq = eye.v_4262_N(center = new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5));
        return distSq <= (reach = (double)bot.G_564_y.R_4764_Y() + 0.5) * reach;
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            v_4262_N = lightning.product.v_4262_N$n_1700_B.n_1700_B();
        }
    }
}


