/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.RotationArgument;
import lightning.product.Y_1387_d;
import lightning.product.WorldCoordinates;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.Coordinates;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_4556_r;
import lightning.product.TicketType;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.u_1579_Y;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;
import lightning.product.EntityAnchorArgument;

public class t_1690_w {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.teleport.invalidPosition"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralCommandNode literalcommandnode = dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("teleport").requires(p_198816_0_ -> p_198816_0_.n_1700_B(2))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("location", u_1579_Y.n_1700_B()).executes(p_198807_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198807_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198807_0_, "targets"), ((y_2498_m)p_198807_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_198807_0_, "location"), null, null))).then(Q_2241_p.n_1700_B("rotation", RotationArgument.n_1700_B()).executes(p_198811_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198811_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198811_0_, "targets"), ((y_2498_m)p_198811_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_198811_0_, "location"), RotationArgument.n_1700_B((CommandContext<y_2498_m>)p_198811_0_, "rotation"), null)))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("facing").then(Q_2241_p.n_1700_B("entity").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("facingEntity", i_4556_r.n_1700_B()).executes(p_198806_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198806_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198806_0_, "targets"), ((y_2498_m)p_198806_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_198806_0_, "location"), null, new n_1700_B(i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_198806_0_, "facingEntity"), EntityAnchorArgument.n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("facingAnchor", EntityAnchorArgument.n_1700_B()).executes(p_198812_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198812_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198812_0_, "targets"), ((y_2498_m)p_198812_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_198812_0_, "location"), null, new n_1700_B(i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_198812_0_, "facingEntity"), EntityAnchorArgument.n_1700_B((CommandContext<y_2498_m>)p_198812_0_, "facingAnchor")))))))).then(Q_2241_p.n_1700_B("facingLocation", u_1579_Y.n_1700_B()).executes(p_198805_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198805_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198805_0_, "targets"), ((y_2498_m)p_198805_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_198805_0_, "location"), null, new n_1700_B(u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198805_0_, "facingLocation")))))))).then(Q_2241_p.n_1700_B("destination", i_4556_r.n_1700_B()).executes(p_198814_0_ -> t_1690_w.n_1700_B((y_2498_m)p_198814_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198814_0_, "targets"), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_198814_0_, "destination")))))).then(Q_2241_p.n_1700_B("location", u_1579_Y.n_1700_B()).executes(p_200560_0_ -> t_1690_w.n_1700_B((y_2498_m)p_200560_0_.getSource(), Collections.singleton(((y_2498_m)p_200560_0_.getSource()).M_182_A()), ((y_2498_m)p_200560_0_.getSource()).h_1847_R(), u_1579_Y.J_1907_R((CommandContext<y_2498_m>)p_200560_0_, "location"), WorldCoordinates.G_564_y(), null)))).then(Q_2241_p.n_1700_B("destination", i_4556_r.n_1700_B()).executes(p_200562_0_ -> t_1690_w.n_1700_B((y_2498_m)p_200562_0_.getSource(), Collections.singleton(((y_2498_m)p_200562_0_.getSource()).M_182_A()), i_4556_r.n_1700_B((CommandContext<y_2498_m>)p_200562_0_, "destination")))));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("tp").requires(p_200556_0_ -> p_200556_0_.n_1700_B(2))).redirect((CommandNode)literalcommandnode));
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, N_4263_v destination) throws CommandSyntaxException {
        for (N_4263_v n_4263_v : targets) {
            t_1690_w.n_1700_B(source, n_4263_v, (e_3591_l)destination.O_508_d, destination.O_3598_v(), destination.X_2960_b(), destination.l_2647_k(), EnumSet.noneOf(ClientboundPlayerPositionPacket.n_1700_B.class), destination.p_178_J, destination.f_4016_n, null);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.teleport.success.entity.single", targets.iterator().next().c_(), destination.c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.teleport.success.entity.multiple", targets.size(), destination.c_()), true);
        }
        return targets.size();
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, e_3591_l worldIn, Coordinates position, @Nullable Coordinates rotationIn, @Nullable n_1700_B facing) throws CommandSyntaxException {
        e_2866_D vector3d = position.n_1700_B(source);
        P_3504_Q vector2f = rotationIn == null ? null : rotationIn.J_1907_R(source);
        EnumSet<ClientboundPlayerPositionPacket.n_1700_B> set = EnumSet.noneOf(ClientboundPlayerPositionPacket.n_1700_B.class);
        if (position.n_1700_B()) {
            set.add(ClientboundPlayerPositionPacket.n_1700_B.n_1700_B);
        }
        if (position.J_1907_R()) {
            set.add(ClientboundPlayerPositionPacket.n_1700_B.J_1907_R);
        }
        if (position.R_4764_Y()) {
            set.add(ClientboundPlayerPositionPacket.n_1700_B.R_4764_Y);
        }
        if (rotationIn == null) {
            set.add(ClientboundPlayerPositionPacket.n_1700_B.P_1922_E);
            set.add(ClientboundPlayerPositionPacket.n_1700_B.G_564_y);
        } else {
            if (rotationIn.n_1700_B()) {
                set.add(ClientboundPlayerPositionPacket.n_1700_B.P_1922_E);
            }
            if (rotationIn.J_1907_R()) {
                set.add(ClientboundPlayerPositionPacket.n_1700_B.G_564_y);
            }
        }
        for (N_4263_v n_4263_v : targets) {
            if (rotationIn == null) {
                t_1690_w.n_1700_B(source, n_4263_v, worldIn, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, set, n_4263_v.p_178_J, n_4263_v.f_4016_n, facing);
                continue;
            }
            t_1690_w.n_1700_B(source, n_4263_v, worldIn, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, set, vector2f.s_956_w, vector2f.t_148_a, facing);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.teleport.success.location.single", targets.iterator().next().c_(), vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.teleport.success.location.multiple", targets.size(), vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y), true);
        }
        return targets.size();
    }

    private static void n_1700_B(y_2498_m source, N_4263_v entityIn, e_3591_l worldIn, double x, double y, double z, Set<ClientboundPlayerPositionPacket.n_1700_B> relativeList, float yaw, float pitch, @Nullable n_1700_B facing) throws CommandSyntaxException {
        c_1514_x blockpos = new c_1514_x(x, y, z);
        if (!b_4507_u.h_1847_R(blockpos)) {
            throw n_1700_B.create();
        }
        if (entityIn instanceof B_4088_l) {
            Y_1387_d chunkpos = new Y_1387_d(new c_1514_x(x, y, z));
            worldIn.Y_259_p().n_1700_B(TicketType.v_4262_N, chunkpos, 1, entityIn.j_276_v());
            entityIn.A_3959_N();
            if (((B_4088_l)entityIn).z_2372_L()) {
                ((B_4088_l)entityIn).n_1700_B(true, true);
            }
            if (worldIn == entityIn.O_508_d) {
                ((B_4088_l)entityIn).n_1700_B.n_1700_B(x, y, z, yaw, pitch, relativeList);
            } else {
                ((B_4088_l)entityIn).n_1700_B(worldIn, x, y, z, yaw, pitch);
            }
            entityIn.h_1847_R(yaw);
        } else {
            float f1 = u_530_F.v_4262_N(yaw);
            float f = u_530_F.v_4262_N(pitch);
            f = u_530_F.n_1700_B(f, -90.0f, 90.0f);
            if (worldIn == entityIn.O_508_d) {
                entityIn.J_1907_R(x, y, z, f1, f);
                entityIn.h_1847_R(f1);
            } else {
                entityIn.Ping();
                N_4263_v entity = entityIn;
                entityIn = entityIn.f_4016_n().n_1700_B(worldIn);
                if (entityIn == null) {
                    return;
                }
                entityIn.w_1457_N(entity);
                entityIn.J_1907_R(x, y, z, f1, f);
                entityIn.h_1847_R(f1);
                worldIn.v_4262_N(entityIn);
                entity.t_4219_U = true;
            }
        }
        if (facing != null) {
            facing.n_1700_B(source, entityIn);
        }
        if (!(entityIn instanceof r_4811_B) || !((r_4811_B)entityIn).k_578_l()) {
            entityIn.v_4262_N(entityIn.I_4348_c().G_564_y(1.0, 0.0, 1.0));
            entityIn.u_1723_Y(true);
        }
        if (entityIn instanceof PathfinderMob) {
            ((PathfinderMob)entityIn).e_4240_b().h_1847_R();
        }
    }

    static class n_1700_B {
        private final e_2866_D n_1700_B;
        private final N_4263_v J_1907_R;
        private final EntityAnchorArgument.n_1700_B R_4764_Y;

        public n_1700_B(N_4263_v entityIn, EntityAnchorArgument.n_1700_B anchorIn) {
            this.J_1907_R = entityIn;
            this.R_4764_Y = anchorIn;
            this.n_1700_B = anchorIn.n_1700_B(entityIn);
        }

        public n_1700_B(e_2866_D positionIn) {
            this.J_1907_R = null;
            this.n_1700_B = positionIn;
            this.R_4764_Y = null;
        }

        public void n_1700_B(y_2498_m source, N_4263_v entityIn) {
            if (this.J_1907_R != null) {
                if (entityIn instanceof B_4088_l) {
                    ((B_4088_l)entityIn).n_1700_B(source.Y_601_j(), this.J_1907_R, this.R_4764_Y);
                } else {
                    entityIn.n_1700_B(source.Y_601_j(), this.n_1700_B);
                }
            } else {
                entityIn.n_1700_B(source.Y_601_j(), this.n_1700_B);
            }
        }
    }
}


