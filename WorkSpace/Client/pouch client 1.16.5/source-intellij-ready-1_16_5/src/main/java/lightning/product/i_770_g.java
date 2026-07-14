/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.MobEffects;
import lightning.product.M_2562_s;
import lightning.product.V_772_m;
import lightning.product.MinecraftAccess;
import lightning.product.c_1514_x;
import lombok.Generated;

public class i_770_g
implements MinecraftAccess {
    private double n_1700_B;
    private double J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public double n_1700_B(M_2562_s move, boolean damageBoost, boolean hasTime, boolean autoJump, float damageSpeed, float normalAirSpeed) {
        float speedBoost;
        boolean isOnGround = i_770_g.c_3005_b.Y_259_p.M_1641_O();
        boolean willBeOnGround = move.G_564_y();
        boolean isJumping = move.R_4764_Y().R_4764_Y > 0.0;
        float baseSpeed = this.n_1700_B(i_770_g.c_3005_b.Y_259_p);
        float groundFriction = this.n_1700_B(i_770_g.c_3005_b.Y_259_p, move);
        float airFriction = i_770_g.c_3005_b.Y_259_p.J_1907_R(MobEffects.w_1484_f) && i_770_g.c_3005_b.Y_259_p.Y_601_j() ? 0.88f : 0.91f;
        float currentFriction = isOnGround ? groundFriction : airFriction;
        float accelerationFactor = 0.16277136f / (currentFriction * currentFriction * currentFriction);
        if (isOnGround) {
            speedBoost = baseSpeed * accelerationFactor;
            if (isJumping) {
                speedBoost += 0.2f;
            }
        } else {
            speedBoost = damageBoost && hasTime && (autoJump || i_770_g.c_3005_b.P_4830_p.Ping.G_564_y()) ? damageSpeed : normalAirSpeed;
        }
        double newSpeed = this.n_1700_B + (double)speedBoost;
        double slowdownPenalty = i_770_g.c_3005_b.Y_259_p.J_1907_R(MobEffects.w_1484_f) && i_770_g.c_3005_b.Y_259_p.Y_601_j() ? 0.3 : 0.019;
        double speedDecrease = this.P_1922_E++ % 2 == 0 ? 0.001 : 0.002;
        newSpeed = this.u_1723_Y > 3 ? -slowdownPenalty : Math.max(0.25, newSpeed) - speedDecrease;
        this.J_1907_R = currentFriction;
        if (!willBeOnGround && !isOnGround) {
            this.R_4764_Y = true;
        }
        if (!isOnGround && !willBeOnGround) {
            boolean bl = this.G_564_y = !i_770_g.c_3005_b.Y_259_p.z_1333_t();
        }
        if (willBeOnGround && isOnGround) {
            this.G_564_y = false;
        }
        return newSpeed;
    }

    public void n_1700_B(double horizontal) {
        this.n_1700_B = horizontal * this.J_1907_R;
    }

    private float n_1700_B(V_772_m contextPlayer) {
        boolean prevSprinting = contextPlayer.o_2341_D();
        contextPlayer.b_(false);
        float speed = contextPlayer.l_2995_s() * 1.3f;
        contextPlayer.b_(prevSprinting);
        return speed;
    }

    private float n_1700_B(V_772_m contextPlayer, M_2562_s move) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        blockpos$mutable.n_1700_B(move.n_1700_B().J_1907_R, move.P_1922_E().minY - 1.0, move.n_1700_B().G_564_y);
        return contextPlayer.O_508_d.getBlockState(blockpos$mutable).J_1907_R().h_1847_R() * 0.91f;
    }

    @Generated
    public double n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public double J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public boolean G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public int P_1922_E() {
        return this.P_1922_E;
    }

    @Generated
    public int u_1723_Y() {
        return this.u_1723_Y;
    }

    @Generated
    public void J_1907_R(double oldSpeed) {
        this.n_1700_B = oldSpeed;
    }

    @Generated
    public void R_4764_Y(double contextFriction) {
        this.J_1907_R = contextFriction;
    }

    @Generated
    public void n_1700_B(boolean needSwap) {
        this.R_4764_Y = needSwap;
    }

    @Generated
    public void J_1907_R(boolean needSprintState) {
        this.G_564_y = needSprintState;
    }

    @Generated
    public void n_1700_B(int counter) {
        this.P_1922_E = counter;
    }

    @Generated
    public void J_1907_R(int noSlowTicks) {
        this.u_1723_Y = noSlowTicks;
    }
}



