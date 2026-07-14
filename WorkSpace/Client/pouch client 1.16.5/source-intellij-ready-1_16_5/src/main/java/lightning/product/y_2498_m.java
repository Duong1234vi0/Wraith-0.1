/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.ResultConsumer
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.ResultConsumer;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.CommandSource;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.Z_3903_F;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.EntityAnchorArgument;
import net.minecraft.server.G_564_y;

public class y_2498_m
implements V_4217_p {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("permissions.requires.player"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("permissions.requires.entity"));
    private final CommandSource R_4764_Y;
    private final e_2866_D G_564_y;
    private final e_3591_l P_1922_E;
    private final int u_1723_Y;
    private final String v_4262_N;
    private final x_282_a w_1484_f;
    private final G_564_y t_148_a;
    private final boolean s_956_w;
    @Nullable
    private final N_4263_v u_2550_I;
    private final ResultConsumer<y_2498_m> M_588_G;
    private final EntityAnchorArgument.n_1700_B P_4830_p;
    private final P_3504_Q h_1847_R;

    public y_2498_m(CommandSource sourceIn, e_2866_D posIn, P_3504_Q rotationIn, e_3591_l worldIn, int permissionLevelIn, String nameIn, x_282_a displayNameIn, G_564_y serverIn, @Nullable N_4263_v entityIn) {
        this(sourceIn, posIn, rotationIn, worldIn, permissionLevelIn, nameIn, displayNameIn, serverIn, entityIn, false, (ResultConsumer<y_2498_m>)((ResultConsumer)(p_197032_0_, p_197032_1_, p_197032_2_) -> {}), EntityAnchorArgument.n_1700_B.n_1700_B);
    }

    protected y_2498_m(CommandSource sourceIn, e_2866_D posIn, P_3504_Q rotationIn, e_3591_l worldIn, int permissionLevelIn, String nameIn, x_282_a displayNameIn, G_564_y serverIn, @Nullable N_4263_v entityIn, boolean feedbackDisabledIn, ResultConsumer<y_2498_m> resultConsumerIn, EntityAnchorArgument.n_1700_B entityAnchorTypeIn) {
        this.R_4764_Y = sourceIn;
        this.G_564_y = posIn;
        this.P_1922_E = worldIn;
        this.s_956_w = feedbackDisabledIn;
        this.u_2550_I = entityIn;
        this.u_1723_Y = permissionLevelIn;
        this.v_4262_N = nameIn;
        this.w_1484_f = displayNameIn;
        this.t_148_a = serverIn;
        this.M_588_G = resultConsumerIn;
        this.P_4830_p = entityAnchorTypeIn;
        this.h_1847_R = rotationIn;
    }

    public y_2498_m n_1700_B(N_4263_v entityIn) {
        return this.u_2550_I == entityIn ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, this.u_1723_Y, entityIn.O_1309_Q().getString(), entityIn.c_(), this.t_148_a, entityIn, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m n_1700_B(e_2866_D posIn) {
        return this.G_564_y.equals(posIn) ? this : new y_2498_m(this.R_4764_Y, posIn, this.h_1847_R, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m n_1700_B(P_3504_Q pitchYawIn) {
        return this.h_1847_R.n_1700_B(pitchYawIn) ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, pitchYawIn, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m n_1700_B(ResultConsumer<y_2498_m> resultConsumerIn) {
        return this.M_588_G.equals(resultConsumerIn) ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, resultConsumerIn, this.P_4830_p);
    }

    public y_2498_m n_1700_B(ResultConsumer<y_2498_m> resultConsumerIn, BinaryOperator<ResultConsumer<y_2498_m>> resultConsumerSelector) {
        ResultConsumer resultconsumer = (ResultConsumer)resultConsumerSelector.apply(this.M_588_G, resultConsumerIn);
        return this.n_1700_B((ResultConsumer<y_2498_m>)resultconsumer);
    }

    public y_2498_m s_956_w() {
        return this.s_956_w ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, true, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m J_1907_R(int level) {
        return level == this.u_1723_Y ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, level, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m R_4764_Y(int level) {
        return level <= this.u_1723_Y ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, level, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m n_1700_B(EntityAnchorArgument.n_1700_B entityAnchorTypeIn) {
        return entityAnchorTypeIn == this.P_4830_p ? this : new y_2498_m(this.R_4764_Y, this.G_564_y, this.h_1847_R, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, entityAnchorTypeIn);
    }

    public y_2498_m n_1700_B(e_3591_l worldIn) {
        if (worldIn == this.P_1922_E) {
            return this;
        }
        double d0 = Z_3903_F.n_1700_B(this.P_1922_E.G_624_v(), worldIn.G_624_v());
        e_2866_D vector3d = new e_2866_D(this.G_564_y.J_1907_R * d0, this.G_564_y.R_4764_Y, this.G_564_y.G_564_y * d0);
        return new y_2498_m(this.R_4764_Y, vector3d, this.h_1847_R, worldIn, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.u_2550_I, this.s_956_w, this.M_588_G, this.P_4830_p);
    }

    public y_2498_m n_1700_B(N_4263_v entityIn, EntityAnchorArgument.n_1700_B anchorType) throws CommandSyntaxException {
        return this.J_1907_R(anchorType.n_1700_B(entityIn));
    }

    public y_2498_m J_1907_R(e_2866_D lookPos) throws CommandSyntaxException {
        e_2866_D vector3d = this.P_4830_p.n_1700_B(this);
        double d0 = lookPos.J_1907_R - vector3d.J_1907_R;
        double d1 = lookPos.R_4764_Y - vector3d.R_4764_Y;
        double d2 = lookPos.G_564_y - vector3d.G_564_y;
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        float f = u_530_F.v_4262_N((float)(-(u_530_F.G_564_y(d1, d3) * 57.2957763671875)));
        float f1 = u_530_F.v_4262_N((float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f);
        return this.n_1700_B(new P_3504_Q(f, f1));
    }

    public x_282_a u_2550_I() {
        return this.w_1484_f;
    }

    public String M_588_G() {
        return this.v_4262_N;
    }

    @Override
    public boolean n_1700_B(int level) {
        return this.u_1723_Y >= level;
    }

    public e_2866_D P_4830_p() {
        return this.G_564_y;
    }

    public e_3591_l h_1847_R() {
        return this.P_1922_E;
    }

    @Nullable
    public N_4263_v Q_4569_t() {
        return this.u_2550_I;
    }

    public N_4263_v M_182_A() throws CommandSyntaxException {
        if (this.u_2550_I == null) {
            throw J_1907_R.create();
        }
        return this.u_2550_I;
    }

    public B_4088_l t_1786_h() throws CommandSyntaxException {
        if (!(this.u_2550_I instanceof B_4088_l)) {
            throw n_1700_B.create();
        }
        return (B_4088_l)this.u_2550_I;
    }

    public P_3504_Q multiplayerClientSuggestionProvider() {
        return this.h_1847_R;
    }

    public G_564_y w_1457_N() {
        return this.t_148_a;
    }

    public EntityAnchorArgument.n_1700_B Y_601_j() {
        return this.P_4830_p;
    }

    public void n_1700_B(x_282_a message, boolean allowLogging) {
        if (this.R_4764_Y.O_508_d() && !this.s_956_w) {
            this.R_4764_Y.n_1700_B(message, j_3341_s.J_1907_R);
        }
        if (allowLogging && this.R_4764_Y.A_1038_p() && !this.s_956_w) {
            this.J_1907_R(message);
        }
    }

    private void J_1907_R(x_282_a message) {
        MutableComponent itextcomponent = new F_2904_S("chat.type.admin", this.u_2550_I(), message).n_1700_B(D_4024_W.w_1484_f, D_4024_W.Y_259_p);
        if (this.t_148_a.y_1700_S().J_1907_R(A_2352_Z.h_1847_R)) {
            for (B_4088_l serverplayerentity : this.t_148_a.p_178_J().w_1457_N()) {
                if (serverplayerentity == this.R_4764_Y || !this.t_148_a.p_178_J().u_1723_Y(serverplayerentity.y_4642_Y())) continue;
                serverplayerentity.n_1700_B((x_282_a)itextcomponent, j_3341_s.J_1907_R);
            }
        }
        if (this.R_4764_Y != this.t_148_a && this.t_148_a.y_1700_S().J_1907_R(A_2352_Z.u_2550_I)) {
            this.t_148_a.n_1700_B(itextcomponent, j_3341_s.J_1907_R);
        }
    }

    public void n_1700_B(x_282_a message) {
        if (this.R_4764_Y.r_715_M() && !this.s_956_w) {
            this.R_4764_Y.n_1700_B(new U_2871_b("").n_1700_B(message).n_1700_B(D_4024_W.P_4830_p), j_3341_s.J_1907_R);
        }
    }

    public void n_1700_B(CommandContext<y_2498_m> context, boolean success, int result) {
        if (this.M_588_G != null) {
            this.M_588_G.onCommandComplete(context, success, result);
        }
    }

    @Override
    public Collection<String> n_1700_B() {
        return Lists.newArrayList((Object[])this.t_148_a.w_1484_f());
    }

    @Override
    public Collection<String> R_4764_Y() {
        return this.t_148_a.S_4022_R().G_564_y();
    }

    @Override
    public Collection<g_2336_b> G_564_y() {
        return V_3137_a.d_2461_k.G_564_y();
    }

    @Override
    public Stream<g_2336_b> P_1922_E() {
        return this.t_148_a.ValueObject().R_4764_Y();
    }

    @Override
    public CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> context, SuggestionsBuilder suggestionsBuilder) {
        return null;
    }

    @Override
    public Set<f_2392_k<b_4507_u>> w_1484_f() {
        return this.t_148_a.e_4240_b();
    }

    @Override
    public r_4097_j t_148_a() {
        return this.t_148_a.g_4106_L();
    }
}


