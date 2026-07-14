/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import lightning.product.B_4088_l;
import lightning.product.BossEvent;
import lightning.product.m_1761_s;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class ServerBossEvent
extends BossEvent {
    private final Set<B_4088_l> n_1700_B = Sets.newHashSet();
    private final Set<B_4088_l> J_1907_R = Collections.unmodifiableSet(this.n_1700_B);
    private boolean s_956_w = true;

    public ServerBossEvent(x_282_a nameIn, BossEvent.n_1700_B colorIn, BossEvent.J_1907_R overlayIn) {
        super(u_530_F.n_1700_B(), nameIn, colorIn, overlayIn);
    }

    @Override
    public void n_1700_B(float percentIn) {
        if (percentIn != this.G_564_y) {
            super.n_1700_B(percentIn);
            this.n_1700_B(m_1761_s.n_1700_B.R_4764_Y);
        }
    }

    @Override
    public void n_1700_B(BossEvent.n_1700_B colorIn) {
        if (colorIn != this.P_1922_E) {
            super.n_1700_B(colorIn);
            this.n_1700_B(m_1761_s.n_1700_B.P_1922_E);
        }
    }

    @Override
    public void n_1700_B(BossEvent.J_1907_R overlayIn) {
        if (overlayIn != this.u_1723_Y) {
            super.n_1700_B(overlayIn);
            this.n_1700_B(m_1761_s.n_1700_B.P_1922_E);
        }
    }

    @Override
    public BossEvent n_1700_B(boolean darkenSkyIn) {
        if (darkenSkyIn != this.v_4262_N) {
            super.n_1700_B(darkenSkyIn);
            this.n_1700_B(m_1761_s.n_1700_B.u_1723_Y);
        }
        return this;
    }

    @Override
    public BossEvent J_1907_R(boolean playEndBossMusicIn) {
        if (playEndBossMusicIn != this.w_1484_f) {
            super.J_1907_R(playEndBossMusicIn);
            this.n_1700_B(m_1761_s.n_1700_B.u_1723_Y);
        }
        return this;
    }

    @Override
    public BossEvent R_4764_Y(boolean createFogIn) {
        if (createFogIn != this.t_148_a) {
            super.R_4764_Y(createFogIn);
            this.n_1700_B(m_1761_s.n_1700_B.u_1723_Y);
        }
        return this;
    }

    @Override
    public void n_1700_B(x_282_a nameIn) {
        if (!Objects.equal((Object)nameIn, (Object)this.R_4764_Y)) {
            super.n_1700_B(nameIn);
            this.n_1700_B(m_1761_s.n_1700_B.G_564_y);
        }
    }

    private void n_1700_B(m_1761_s.n_1700_B operationIn) {
        if (this.s_956_w) {
            m_1761_s supdatebossinfopacket = new m_1761_s(operationIn, this);
            for (B_4088_l serverplayerentity : this.n_1700_B) {
                serverplayerentity.n_1700_B.n_1700_B(supdatebossinfopacket);
            }
        }
    }

    public void n_1700_B(B_4088_l player) {
        if (this.n_1700_B.add(player) && this.s_956_w) {
            player.n_1700_B.n_1700_B(new m_1761_s(m_1761_s.n_1700_B.n_1700_B, this));
        }
    }

    public void J_1907_R(B_4088_l player) {
        if (this.n_1700_B.remove(player) && this.s_956_w) {
            player.n_1700_B.n_1700_B(new m_1761_s(m_1761_s.n_1700_B.J_1907_R, this));
        }
    }

    public void R_4764_Y() {
        if (!this.n_1700_B.isEmpty()) {
            for (B_4088_l serverplayerentity : Lists.newArrayList(this.n_1700_B)) {
                this.J_1907_R(serverplayerentity);
            }
        }
    }

    public boolean Q_4569_t() {
        return this.s_956_w;
    }

    public void G_564_y(boolean visibleIn) {
        if (visibleIn != this.s_956_w) {
            this.s_956_w = visibleIn;
            for (B_4088_l serverplayerentity : this.n_1700_B) {
                serverplayerentity.n_1700_B.n_1700_B(new m_1761_s(visibleIn ? m_1761_s.n_1700_B.n_1700_B : m_1761_s.n_1700_B.J_1907_R, this));
            }
        }
    }

    public Collection<B_4088_l> M_182_A() {
        return this.J_1907_R;
    }
}


