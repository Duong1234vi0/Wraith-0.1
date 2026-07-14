/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;
import lightning.product.x_268_Y;

public class M_588_G
implements u_1723_Y {
    private c_1514_x n_1700_B = null;
    private b_257_Y J_1907_R = b_257_Y.J_1907_R;

    @Override
    public void n_1700_B(n_1700_B bot) {
        K_4074_S state;
        if (bot == null || bot.R_4764_Y == null || bot.J_1907_R == null || bot.G_564_y == null) {
            return;
        }
        c_1514_x currentBlock = this.J_1907_R(bot);
        if (currentBlock == null || currentBlock.getY() < 0 || currentBlock.getY() >= 256) {
            return;
        }
        if (!bot.J_1907_R.M_588_G(currentBlock)) {
            return;
        }
        if (this.n_1700_B == null || !this.n_1700_B.equals(currentBlock)) {
            this.n_1700_B = currentBlock.toImmutable();
            this.J_1907_R = b_257_Y.J_1907_R;
        }
        if ((state = bot.J_1907_R.getBlockState(currentBlock)).v_4262_N() || !this.n_1700_B(bot, currentBlock, state)) {
            return;
        }
        b_257_Y face = this.n_1700_B(bot, currentBlock);
        bot.u_1723_Y.J_1907_R(currentBlock);
        if (!bot.G_564_y.h_1847_R()) {
            bot.G_564_y.n_1700_B(currentBlock, face);
            bot.G_564_y.J_1907_R(currentBlock, face);
        }
        bot.P_1922_E.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, currentBlock, face));
    }

    private boolean n_1700_B(n_1700_B bot, c_1514_x pos, K_4074_S state) {
        if (!bot.G_564_y.w_1484_f() && state.w_1484_f(bot.J_1907_R, pos) < 0.0f) {
            return false;
        }
        return state.s_956_w(bot.J_1907_R, pos) != x_268_Y.n_1700_B();
    }

    private b_257_Y n_1700_B(n_1700_B bot, c_1514_x target) {
        BlockHitResult result;
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.J_1907_R && target.equals((result = (BlockHitResult)bot.R_4764_Y.v_4262_N).n_1700_B())) {
            this.J_1907_R = result.J_1907_R();
        }
        return this.J_1907_R;
    }

    private c_1514_x J_1907_R(n_1700_B bot) {
        BlockHitResult res;
        c_1514_x pos;
        if (bot.G_564_y.h_1847_R()) {
            return bot.G_564_y.Q_4569_t();
        }
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.J_1907_R && this.J_1907_R(bot, pos = (res = (BlockHitResult)bot.R_4764_Y.v_4262_N).n_1700_B())) {
            this.J_1907_R = res.J_1907_R();
            return pos;
        }
        if (bot.R_4764_Y.v_4262_N != null && bot.R_4764_Y.v_4262_N.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
            c_1514_x pos2;
            e_2866_D eyePos = bot.R_4764_Y.u_2550_I(1.0f);
            e_2866_D lookVec = bot.R_4764_Y.t_148_a(1.0f);
            double reach = bot.G_564_y.R_4764_Y();
            e_2866_D endPos = eyePos.J_1907_R(lookVec.J_1907_R * reach, lookVec.R_4764_Y * reach, lookVec.G_564_y * reach);
            ClipContext context = new ClipContext(eyePos, endPos, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, bot.R_4764_Y);
            BlockHitResult blockTrace = bot.J_1907_R.n_1700_B(context);
            if (blockTrace != null && blockTrace.R_4764_Y() == HitResult.n_1700_B.J_1907_R && this.J_1907_R(bot, pos2 = blockTrace.n_1700_B())) {
                this.J_1907_R = blockTrace.J_1907_R();
                return pos2;
            }
        }
        return null;
    }

    private boolean J_1907_R(n_1700_B bot, c_1514_x pos) {
        double reach;
        e_2866_D center;
        e_2866_D eye = bot.R_4764_Y.u_2550_I(1.0f);
        double distSq = eye.v_4262_N(center = new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5));
        return distSq <= (reach = (double)bot.G_564_y.R_4764_Y() + 0.5) * reach;
    }
}


