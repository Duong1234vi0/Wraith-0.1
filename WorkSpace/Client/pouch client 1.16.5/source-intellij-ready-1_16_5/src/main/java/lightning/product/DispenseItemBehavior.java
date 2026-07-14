/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.B_3217_H;
import lightning.product.BoneMealItem;
import lightning.product.B_4088_l;
import lightning.product.Projectile;
import lightning.product.C_4998_y;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.D_686_b;
import lightning.product.F_666_T;
import lightning.product.F_997_G;
import lightning.product.BlockHitResult;
import lightning.product.Arrow;
import lightning.product.ThrownExperienceBottle;
import lightning.product.I_4817_s;
import lightning.product.J_3992_v;
import lightning.product.Snowball;
import lightning.product.K_4074_S;
import lightning.product.L_1875_m;
import lightning.product.AbstractProjectileDispenseBehavior;
import lightning.product.Saddleable;
import lightning.product.N_4263_v;
import lightning.product.O_2639_P;
import lightning.product.P_2605_j;
import lightning.product.Q_552_a;
import lightning.product.R_2515_i;
import lightning.product.Potions;
import lightning.product.BaseFireBlock;
import lightning.product.S_3458_C;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Position;
import lightning.product.U_2534_D;
import lightning.product.U_4243_e;
import lightning.product.SoundEvents;
import lightning.product.V_883_W;
import lightning.product.W_3443_Y;
import lightning.product.SkullBlock;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpectralArrow;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.g_1462_f;
import lightning.product.h_384_L;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.WitherSkullBlock;
import lightning.product.l_3848_Y;
import lightning.product.o_3946_o;
import lightning.product.BlockTags;
import lightning.product.p_1553_v;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_4438_s;
import lightning.product.Fluid;
import lightning.product.PrimedTnt;
import lightning.product.t_5_h;
import lightning.product.BoatDispenseItemBehavior;
import lightning.product.v_1577_d;
import lightning.product.OptionalDispenseItemBehavior;
import lightning.product.ThrownEgg;
import lightning.product.BlockSource;
import lightning.product.SpawnEggItem;
import lightning.product.z_127_w;

public interface DispenseItemBehavior {
    public static final DispenseItemBehavior n_1700_B = (source, stack) -> stack;

    public Z_1993_T dispense(BlockSource var1, Z_1993_T var2);

    public static void n_1700_B() {
        S_3458_C.n_1700_B(Items.g_24_p, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                Arrow arrowentity = new Arrow(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y());
                arrowentity.R_4764_Y = h_384_L.n_1700_B.J_1907_R;
                return arrowentity;
            }
        });
        S_3458_C.n_1700_B(Items.NetherWartBlock, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                Arrow arrowentity = new Arrow(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y());
                arrowentity.J_1907_R(stackIn);
                arrowentity.R_4764_Y = h_384_L.n_1700_B.J_1907_R;
                return arrowentity;
            }
        });
        S_3458_C.n_1700_B(Items.g_2783_J, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                SpectralArrow abstractarrowentity = new SpectralArrow(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y());
                abstractarrowentity.R_4764_Y = h_384_L.n_1700_B.J_1907_R;
                return abstractarrowentity;
            }
        });
        S_3458_C.n_1700_B(Items.s_4405_m, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                return j_3341_s.n_1700_B(new ThrownEgg(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y()), (T egg) -> egg.J_1907_R(stackIn));
            }
        });
        S_3458_C.n_1700_B(Items.i_770_g, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                return j_3341_s.n_1700_B(new Snowball(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y()), (T snowball) -> snowball.J_1907_R(stackIn));
            }
        });
        S_3458_C.n_1700_B(Items.s_3084_y, new AbstractProjectileDispenseBehavior(){

            @Override
            protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                return j_3341_s.n_1700_B(new ThrownExperienceBottle(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y()), (T experienceBottle) -> experienceBottle.J_1907_R(stackIn));
            }

            @Override
            protected float J_1907_R() {
                return super.J_1907_R() * 0.5f;
            }

            @Override
            protected float R_4764_Y() {
                return super.R_4764_Y() * 1.25f;
            }
        });
        S_3458_C.n_1700_B(Items.g_2492_v, new DispenseItemBehavior(){

            @Override
            public Z_1993_T dispense(BlockSource p_dispense_1_, Z_1993_T p_dispense_2_) {
                return new AbstractProjectileDispenseBehavior(this){

                    @Override
                    protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                        return j_3341_s.n_1700_B(new F_666_T(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y()), (T potion) -> potion.J_1907_R(stackIn));
                    }

                    @Override
                    protected float J_1907_R() {
                        return super.J_1907_R() * 0.5f;
                    }

                    @Override
                    protected float R_4764_Y() {
                        return super.R_4764_Y() * 1.25f;
                    }
                }.dispense(p_dispense_1_, p_dispense_2_);
            }
        });
        S_3458_C.n_1700_B(Items.NetherrackBlock, new DispenseItemBehavior(){

            @Override
            public Z_1993_T dispense(BlockSource p_dispense_1_, Z_1993_T p_dispense_2_) {
                return new AbstractProjectileDispenseBehavior(this){

                    @Override
                    protected Projectile n_1700_B(b_4507_u worldIn, Position position, Z_1993_T stackIn) {
                        return j_3341_s.n_1700_B(new F_666_T(worldIn, position.n_1700_B(), position.J_1907_R(), position.R_4764_Y()), (T potion) -> potion.J_1907_R(stackIn));
                    }

                    @Override
                    protected float J_1907_R() {
                        return super.J_1907_R() * 0.5f;
                    }

                    @Override
                    protected float R_4764_Y() {
                        return super.R_4764_Y() * 1.25f;
                    }
                }.dispense(p_dispense_1_, p_dispense_2_);
            }
        });
        DefaultDispenseItemBehavior defaultdispenseitembehavior = new DefaultDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                t_5_h<?> entitytype = ((SpawnEggItem)stack.J_1907_R()).n_1700_B(stack.Q_4569_t());
                entitytype.n_1700_B(source.v_4262_N(), stack, null, source.G_564_y().offset(direction), a_3160_D.Q_4569_t, direction != b_257_Y.J_1907_R, false);
                stack.v_4262_N(1);
                return stack;
            }
        };
        for (SpawnEggItem spawneggitem : SpawnEggItem.v_4262_N()) {
            S_3458_C.n_1700_B(spawneggitem, defaultdispenseitembehavior);
        }
        S_3458_C.n_1700_B(Items.p_1168_n, new DefaultDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                c_1514_x blockpos = source.G_564_y().offset(direction);
                e_3591_l world = source.v_4262_N();
                D_686_b armorstandentity = new D_686_b(world, (double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5);
                t_5_h.n_1700_B(world, (a_3913_L)null, armorstandentity, stack.Q_4569_t());
                armorstandentity.p_178_J = direction.Q_4569_t();
                world.a_(armorstandentity);
                stack.v_4262_N(1);
                return stack;
            }
        });
        S_3458_C.n_1700_B(Items.Z_361_l, new OptionalDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                List<r_4811_B> list = source.v_4262_N().n_1700_B(r_4811_B.class, new I_4817_s(blockpos), entity -> {
                    if (!(entity instanceof Saddleable)) {
                        return false;
                    }
                    Saddleable iequipable = (Saddleable)((Object)entity);
                    return !iequipable.G_564_y() && iequipable.n_1700_B();
                });
                if (!list.isEmpty()) {
                    ((Saddleable)((Object)list.get(0))).n_1700_B(D_38_f.P_1922_E);
                    stack.v_4262_N(1);
                    this.n_1700_B(true);
                    return stack;
                }
                return super.n_1700_B(source, stack);
            }
        });
        OptionalDispenseItemBehavior defaultdispenseitembehavior1 = new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                for (U_2534_D abstracthorseentity : source.v_4262_N().n_1700_B(U_2534_D.class, new I_4817_s(blockpos), horse -> horse.RealmsLongRunningMcoTaskScreen() && horse.N_4006_T())) {
                    if (!abstracthorseentity.M_588_G(stack) || abstracthorseentity.k_1608_N() || !abstracthorseentity.o_4117_e()) continue;
                    abstracthorseentity.n_1700_B(401, stack.n_1700_B(1));
                    this.n_1700_B(true);
                    return stack;
                }
                return super.n_1700_B(source, stack);
            }
        };
        S_3458_C.n_1700_B(Items.HoneyBlock, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.GravelBlock, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.GrindstoneBlock, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.HayBlock, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.s_4447_V, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AttackAura, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.HoleFill, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.NoEntityTrace, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.NoFriendDamage, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.Velocity, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoTrap, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.NoServerDesync, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoCrystal, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.s_4054_j, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoSwap, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoAnchor, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoTotem, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.KBDisplacement, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.r_4217_P, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.AutoExplosion, defaultdispenseitembehavior1);
        S_3458_C.n_1700_B(Items.o_1800_r, new OptionalDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                for (W_3443_Y abstractchestedhorseentity : source.v_4262_N().n_1700_B(W_3443_Y.class, new I_4817_s(blockpos), chestedHorse -> chestedHorse.RealmsLongRunningMcoTaskScreen() && !chestedHorse.V_1176_p())) {
                    if (!abstractchestedhorseentity.o_4117_e() || !abstractchestedhorseentity.n_1700_B(499, stack)) continue;
                    stack.v_4262_N(1);
                    this.n_1700_B(true);
                    return stack;
                }
                return super.n_1700_B(source, stack);
            }
        });
        S_3458_C.n_1700_B(Items.FenceBlock, new DefaultDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                J_3992_v fireworkrocketentity = new J_3992_v((b_4507_u)source.v_4262_N(), stack, source.n_1700_B(), source.J_1907_R(), source.n_1700_B(), true);
                DispenseItemBehavior.n_1700_B(source, fireworkrocketentity, direction);
                fireworkrocketentity.R_4764_Y(direction.t_148_a(), direction.s_956_w(), direction.u_2550_I(), 0.5f, 1.0f);
                source.v_4262_N().a_(fireworkrocketentity);
                stack.v_4262_N(1);
                return stack;
            }

            @Override
            protected void n_1700_B(BlockSource source) {
                source.v_4262_N().R_4764_Y(1004, source.G_564_y(), 0);
            }
        });
        S_3458_C.n_1700_B(Items.CraftingTableBlock, new DefaultDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                Position iposition = S_3458_C.n_1700_B(source);
                double d0 = iposition.n_1700_B() + (double)((float)direction.t_148_a() * 0.3f);
                double d1 = iposition.J_1907_R() + (double)((float)direction.s_956_w() * 0.3f);
                double d2 = iposition.R_4764_Y() + (double)((float)direction.u_2550_I() * 0.3f);
                e_3591_l world = source.v_4262_N();
                Random random = world.w_1457_N;
                double d3 = random.nextGaussian() * 0.05 + (double)direction.t_148_a();
                double d4 = random.nextGaussian() * 0.05 + (double)direction.s_956_w();
                double d5 = random.nextGaussian() * 0.05 + (double)direction.u_2550_I();
                world.a_(j_3341_s.n_1700_B(new s_4438_s(world, d0, d1, d2, d3, d4, d5), (T fireball) -> fireball.J_1907_R(stack)));
                stack.v_4262_N(1);
                return stack;
            }

            @Override
            protected void n_1700_B(BlockSource source) {
                source.v_4262_N().R_4764_Y(1018, source.G_564_y(), 0);
            }
        });
        S_3458_C.n_1700_B(Items.m_1628_s, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.n_1700_B));
        S_3458_C.n_1700_B(Items.ObserverBlock, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.J_1907_R));
        S_3458_C.n_1700_B(Items.OreBlock, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.R_4764_Y));
        S_3458_C.n_1700_B(Items.IronBarsBlock, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.G_564_y));
        S_3458_C.n_1700_B(Items.M_1398_d, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.u_1723_Y));
        S_3458_C.n_1700_B(Items.h_4152_b, new BoatDispenseItemBehavior(g_1462_f.J_1907_R.P_1922_E));
        DefaultDispenseItemBehavior idispenseitembehavior1 = new DefaultDispenseItemBehavior(){
            private final DefaultDispenseItemBehavior J_1907_R = new DefaultDispenseItemBehavior();

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                B_3217_H bucketitem = (B_3217_H)stack.J_1907_R();
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                e_3591_l world = source.v_4262_N();
                if (bucketitem.n_1700_B((a_3913_L)null, (b_4507_u)world, blockpos, (BlockHitResult)null)) {
                    bucketitem.n_1700_B((b_4507_u)world, stack, blockpos);
                    return new Z_1993_T(Items.G_1539_D);
                }
                return this.J_1907_R.dispense(source, stack);
            }
        };
        S_3458_C.n_1700_B(Items.u_1934_K, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.W_2770_z, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.j_1376_w, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.W_3801_h, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.F_2052_z, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.v_2826_q, idispenseitembehavior1);
        S_3458_C.n_1700_B(Items.G_1539_D, new DefaultDispenseItemBehavior(){
            private final DefaultDispenseItemBehavior J_1907_R = new DefaultDispenseItemBehavior();

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                c_1514_x blockpos;
                e_3591_l iworld = source.v_4262_N();
                K_4074_S blockstate = iworld.getBlockState(blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p)));
                T_2915_h block = blockstate.J_1907_R();
                if (block instanceof o_3946_o) {
                    Fluid fluid = ((o_3946_o)((Object)block)).J_1907_R(iworld, blockpos, blockstate);
                    if (!(fluid instanceof U_4243_e)) {
                        return super.n_1700_B(source, stack);
                    }
                    q_1613_l item = fluid.n_1700_B();
                    stack.v_4262_N(1);
                    if (stack.n_1700_B()) {
                        return new Z_1993_T(item);
                    }
                    if (((l_3848_Y)source.u_1723_Y()).n_1700_B(new Z_1993_T(item)) < 0) {
                        this.J_1907_R.dispense(source, new Z_1993_T(item));
                    }
                    return stack;
                }
                return super.n_1700_B(source, stack);
            }
        });
        S_3458_C.n_1700_B(Items.S_1165_y, new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                e_3591_l world = source.v_4262_N();
                this.n_1700_B(true);
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                c_1514_x blockpos = source.G_564_y().offset(direction);
                K_4074_S blockstate = world.getBlockState(blockpos);
                if (BaseFireBlock.n_1700_B((b_4507_u)world, blockpos, direction)) {
                    world.J_1907_R(blockpos, BaseFireBlock.n_1700_B(world, blockpos));
                } else if (C_4998_y.t_148_a(blockstate)) {
                    world.J_1907_R(blockpos, (K_4074_S)blockstate.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, true));
                } else if (blockstate.J_1907_R() instanceof V_883_W) {
                    V_883_W.n_1700_B(world, blockpos);
                    world.n_1700_B(blockpos, false);
                } else {
                    this.n_1700_B(false);
                }
                if (this.J_1907_R() && stack.n_1700_B(1, world.w_1457_N, (B_4088_l)null)) {
                    stack.P_1922_E(0);
                }
                return stack;
            }
        });
        S_3458_C.n_1700_B(Items.r_1970_q, new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                this.n_1700_B(true);
                e_3591_l world = source.v_4262_N();
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                if (!BoneMealItem.n_1700_B(stack, world, blockpos) && !BoneMealItem.n_1700_B(stack, world, blockpos, null)) {
                    this.n_1700_B(false);
                } else if (!world.Y_259_p) {
                    world.R_4764_Y(2005, blockpos, 0);
                }
                return stack;
            }
        });
        S_3458_C.n_1700_B(a_3742_W.TextRenderingUtils, new DefaultDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                e_3591_l world = source.v_4262_N();
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                PrimedTnt tntentity = new PrimedTnt(world, (double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5, null);
                world.a_(tntentity);
                ((b_4507_u)world).n_1700_B((a_3913_L)null, tntentity.O_3598_v(), tntentity.X_2960_b(), tntentity.l_2647_k(), SoundEvents.S_3458_C, D_38_f.P_1922_E, 1.0f, 1.0f);
                stack.v_4262_N(1);
                return stack;
            }
        });
        OptionalDispenseItemBehavior idispenseitembehavior = new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                this.n_1700_B(R_2515_i.n_1700_B(source, stack));
                return stack;
            }
        };
        S_3458_C.n_1700_B(Items.EndPortalBlock, idispenseitembehavior);
        S_3458_C.n_1700_B(Items.EndGatewayBlock, idispenseitembehavior);
        S_3458_C.n_1700_B(Items.EndPortalFrameBlock, idispenseitembehavior);
        S_3458_C.n_1700_B(Items.DragonEggBlock, idispenseitembehavior);
        S_3458_C.n_1700_B(Items.C_3560_B, idispenseitembehavior);
        S_3458_C.n_1700_B(Items.DropperBlock, new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                e_3591_l world = source.v_4262_N();
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                c_1514_x blockpos = source.G_564_y().offset(direction);
                if (world.u_1723_Y(blockpos) && WitherSkullBlock.J_1907_R(world, blockpos, stack)) {
                    world.n_1700_B(blockpos, (K_4074_S)a_3742_W.ModuleCategory.multiplayerClientSuggestionProvider().n_1700_B(SkullBlock.P_4830_p, direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? 0 : direction.u_1723_Y().G_564_y() * 4), 3);
                    i_2154_H tileentity = world.getTileEntity(blockpos);
                    if (tileentity instanceof O_2639_P) {
                        WitherSkullBlock.n_1700_B((b_4507_u)world, blockpos, (O_2639_P)tileentity);
                    }
                    stack.v_4262_N(1);
                    this.n_1700_B(true);
                } else {
                    this.n_1700_B(R_2515_i.n_1700_B(source, stack));
                }
                return stack;
            }
        });
        S_3458_C.n_1700_B(a_3742_W.X_2048_Y, new OptionalDispenseItemBehavior(){

            @Override
            protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                e_3591_l world = source.v_4262_N();
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                z_127_w carvedpumpkinblock = (z_127_w)a_3742_W.X_2048_Y;
                if (world.u_1723_Y(blockpos) && carvedpumpkinblock.n_1700_B((T_1316_M)world, blockpos)) {
                    if (!world.Y_259_p) {
                        world.n_1700_B(blockpos, carvedpumpkinblock.multiplayerClientSuggestionProvider(), 3);
                    }
                    stack.v_4262_N(1);
                    this.n_1700_B(true);
                } else {
                    this.n_1700_B(R_2515_i.n_1700_B(source, stack));
                }
                return stack;
            }
        });
        S_3458_C.n_1700_B(a_3742_W.k_1052_R.u_1723_Y(), new Q_552_a());
        for (e_933_M dyecolor : e_933_M.values()) {
            S_3458_C.n_1700_B(Y_3462_U.n_1700_B(dyecolor).u_1723_Y(), new Q_552_a());
        }
        S_3458_C.n_1700_B(Items.Y_3588_g.u_1723_Y(), new OptionalDispenseItemBehavior(){
            private final DefaultDispenseItemBehavior J_1907_R = new DefaultDispenseItemBehavior();

            private Z_1993_T n_1700_B(BlockSource source, Z_1993_T empty, Z_1993_T filled) {
                empty.v_4262_N(1);
                if (empty.n_1700_B()) {
                    return filled.t_148_a();
                }
                if (((l_3848_Y)source.u_1723_Y()).n_1700_B(filled.t_148_a()) < 0) {
                    this.J_1907_R.dispense(source, filled.t_148_a());
                }
                return empty;
            }

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                this.n_1700_B(false);
                e_3591_l serverworld = source.v_4262_N();
                c_1514_x blockpos = source.G_564_y().offset(source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
                K_4074_S blockstate = serverworld.getBlockState(blockpos);
                if (blockstate.n_1700_B(BlockTags.Ping, (q_4293_E.n_1700_B state) -> state.J_1907_R(v_1577_d.h_1847_R)) && blockstate.R_4764_Y(v_1577_d.h_1847_R) >= 5) {
                    ((v_1577_d)blockstate.J_1907_R()).n_1700_B((b_4507_u)serverworld, blockstate, blockpos, (a_3913_L)null, F_997_G.J_1907_R.J_1907_R);
                    this.n_1700_B(true);
                    return this.n_1700_B(source, stack, new Z_1993_T(Items.StructureBlock));
                }
                if (serverworld.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R)) {
                    this.n_1700_B(true);
                    return this.n_1700_B(source, stack, L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), Potions.J_1907_R));
                }
                return super.n_1700_B(source, stack);
            }
        });
        S_3458_C.n_1700_B(Items.Q_2753_H, new OptionalDispenseItemBehavior(){

            @Override
            public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
                b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
                c_1514_x blockpos = source.G_564_y().offset(direction);
                e_3591_l world = source.v_4262_N();
                K_4074_S blockstate = world.getBlockState(blockpos);
                this.n_1700_B(true);
                if (blockstate.n_1700_B(a_3742_W.WrappedMinMaxBounds)) {
                    if (blockstate.R_4764_Y(P_2605_j.P_4830_p) != 4) {
                        P_2605_j.n_1700_B((b_4507_u)world, blockpos, blockstate);
                        stack.v_4262_N(1);
                    } else {
                        this.n_1700_B(false);
                    }
                    return stack;
                }
                return super.n_1700_B(source, stack);
            }
        });
        S_3458_C.n_1700_B(Items.LightPredicate.u_1723_Y(), new p_1553_v());
    }

    public static void n_1700_B(BlockSource source, N_4263_v entity, b_257_Y direction) {
        entity.J_1907_R(source.n_1700_B() + (double)direction.t_148_a() * (0.5000099999997474 - (double)entity.C_415_h() / 2.0), source.J_1907_R() + (double)direction.s_956_w() * (0.5000099999997474 - (double)entity.v_165_F() / 2.0) - (double)entity.v_165_F() / 2.0, source.R_4764_Y() + (double)direction.u_2550_I() * (0.5000099999997474 - (double)entity.C_415_h() / 2.0));
    }
}



