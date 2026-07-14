/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.types.Type
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.A_4091_N;
import lightning.product.A_4313_D;
import lightning.product.A_4605_O;
import lightning.product.D_4899_Z;
import lightning.product.BlockGetter;
import lightning.product.F_997_G;
import lightning.product.G_2722_I;
import lightning.product.SmokerBlockEntity;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.References;
import lightning.product.N_295_T;
import lightning.product.O_2639_P;
import lightning.product.T_1368_k;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.a_433_S;
import lightning.product.c_1514_x;
import lightning.product.c_1869_W;
import lightning.product.f_395_A;
import lightning.product.g_2336_b;
import lightning.product.h_113_g;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.BedBlockEntity;
import lightning.product.j_2644_e;
import lightning.product.j_3341_s;
import lightning.product.l_3848_Y;
import lightning.product.m_1551_m;
import lightning.product.DropperBlockEntity;
import lightning.product.JigsawBlockEntity;
import lightning.product.r_4889_F;
import lightning.product.s_3081_t;
import lightning.product.JukeboxBlockEntity;
import lightning.product.t_693_s;
import lightning.product.DaylightDetectorBlockEntity;
import lightning.product.BlastFurnaceBlockEntity;
import lightning.product.ComparatorBlockEntity;
import lightning.product.w_748_f;
import lightning.product.BarrelBlockEntity;
import lightning.product.FurnaceBlockEntity;
import lightning.product.x_3974_Q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockEntityType<T extends i_2154_H> {
    private static final Logger n_3318_d = LogManager.getLogger();
    public static final BlockEntityType<FurnaceBlockEntity> n_1700_B = BlockEntityType.n_1700_B("furnace", lightning.product.BlockEntityType$n_1700_B.n_1700_B(FurnaceBlockEntity::new, a_3742_W.P_925_e));
    public static final BlockEntityType<t_693_s> J_1907_R = BlockEntityType.n_1700_B("chest", lightning.product.BlockEntityType$n_1700_B.n_1700_B(t_693_s::new, a_3742_W.L_1362_X));
    public static final BlockEntityType<f_395_A> R_4764_Y = BlockEntityType.n_1700_B("trapped_chest", lightning.product.BlockEntityType$n_1700_B.n_1700_B(f_395_A::new, a_3742_W.NumberSetting));
    public static final BlockEntityType<s_3081_t> G_564_y = BlockEntityType.n_1700_B("ender_chest", lightning.product.BlockEntityType$n_1700_B.n_1700_B(s_3081_t::new, a_3742_W.k_2348_i));
    public static final BlockEntityType<JukeboxBlockEntity> P_1922_E = BlockEntityType.n_1700_B("jukebox", lightning.product.BlockEntityType$n_1700_B.n_1700_B(JukeboxBlockEntity::new, a_3742_W.r_2478_U));
    public static final BlockEntityType<l_3848_Y> u_1723_Y = BlockEntityType.n_1700_B("dispenser", lightning.product.BlockEntityType$n_1700_B.n_1700_B(l_3848_Y::new, a_3742_W.Ops));
    public static final BlockEntityType<DropperBlockEntity> v_4262_N = BlockEntityType.n_1700_B("dropper", lightning.product.BlockEntityType$n_1700_B.n_1700_B(DropperBlockEntity::new, a_3742_W.c_1732_c));
    public static final BlockEntityType<A_4313_D> w_1484_f = BlockEntityType.n_1700_B("sign", lightning.product.BlockEntityType$n_1700_B.n_1700_B(A_4313_D::new, a_3742_W.X_4895_T, a_3742_W.L_103_L, a_3742_W.n_3197_X, a_3742_W.P_2947_S, a_3742_W.O_4761_U, a_3742_W.w_2705_t, a_3742_W.k_2302_P, a_3742_W.t_3452_g, a_3742_W.V_118_c, a_3742_W.I_1407_m, a_3742_W.o_2767_H, a_3742_W.d_2545_n, a_3742_W.b_1430_k, a_3742_W.b_4067_I, a_3742_W.n_3115_n, a_3742_W.E_4068_x));
    public static final BlockEntityType<SpawnerBlockEntity> t_148_a = BlockEntityType.n_1700_B("mob_spawner", lightning.product.BlockEntityType$n_1700_B.n_1700_B(SpawnerBlockEntity::new, a_3742_W.j_306_t));
    public static final BlockEntityType<N_295_T> s_956_w = BlockEntityType.n_1700_B("piston", lightning.product.BlockEntityType$n_1700_B.n_1700_B(N_295_T::new, a_3742_W.O_2151_c));
    public static final BlockEntityType<A_4091_N> u_2550_I = BlockEntityType.n_1700_B("brewing_stand", lightning.product.BlockEntityType$n_1700_B.n_1700_B(A_4091_N::new, a_3742_W.e_837_t));
    public static final BlockEntityType<c_1869_W> M_588_G = BlockEntityType.n_1700_B("enchanting_table", lightning.product.BlockEntityType$n_1700_B.n_1700_B(c_1869_W::new, a_3742_W.E_453_w));
    public static final BlockEntityType<TheEndPortalBlockEntity> P_4830_p = BlockEntityType.n_1700_B("end_portal", lightning.product.BlockEntityType$n_1700_B.n_1700_B(TheEndPortalBlockEntity::new, a_3742_W.M_2562_s));
    public static final BlockEntityType<x_3974_Q> h_1847_R = BlockEntityType.n_1700_B("beacon", lightning.product.BlockEntityType$n_1700_B.n_1700_B(x_3974_Q::new, a_3742_W.k_578_l));
    public static final BlockEntityType<O_2639_P> Q_4569_t = BlockEntityType.n_1700_B("skull", lightning.product.BlockEntityType$n_1700_B.n_1700_B(O_2639_P::new, a_3742_W.D_3612_q, a_3742_W.R_2822_N, a_3742_W.BooleanSetting, a_3742_W.SoundEventRegistration, a_3742_W.H_1491_c, a_3742_W.h_2367_h, a_3742_W.Module, a_3742_W.ModuleManager, a_3742_W.ModuleCategory, a_3742_W.p_1458_L, a_3742_W.Setting, a_3742_W.KeyBindSetting));
    public static final BlockEntityType<DaylightDetectorBlockEntity> M_182_A = BlockEntityType.n_1700_B("daylight_detector", lightning.product.BlockEntityType$n_1700_B.n_1700_B(DaylightDetectorBlockEntity::new, a_3742_W.k_1608_N));
    public static final BlockEntityType<w_748_f> t_1786_h = BlockEntityType.n_1700_B("hopper", lightning.product.BlockEntityType$n_1700_B.n_1700_B(w_748_f::new, a_3742_W.p_3749_n));
    public static final BlockEntityType<ComparatorBlockEntity> multiplayerClientSuggestionProvider = BlockEntityType.n_1700_B("comparator", lightning.product.BlockEntityType$n_1700_B.n_1700_B(ComparatorBlockEntity::new, a_3742_W.N_4006_T));
    public static final BlockEntityType<r_4889_F> w_1457_N = BlockEntityType.n_1700_B("banner", lightning.product.BlockEntityType$n_1700_B.n_1700_B(r_4889_F::new, a_3742_W.t_2598_a, a_3742_W.RussianRoulette, a_3742_W.SRPSpoof, a_3742_W.ScoreboardHealth, a_3742_W.Spammer, a_3742_W.AhHelper, a_3742_W.TPLoot, a_3742_W.TapeMouse, a_3742_W.ToggleSounds, a_3742_W.TrashTalk, a_3742_W.UseTracker, a_3742_W.VoiceChat, a_3742_W.a_2587_Z, a_3742_W.D_3097_e, a_3742_W.q_3386_W, a_3742_W.n_3932_q, a_3742_W.w_3483_v, a_3742_W.R_1148_E, a_3742_W.m_2594_d, a_3742_W.o_1343_U, a_3742_W.p_4879_r, a_3742_W.w_1672_Y, a_3742_W.f_360_U, a_3742_W.M_1321_u, a_3742_W.Y_4144_v, a_3742_W.I_1654_f, a_3742_W.C_2712_Y, a_3742_W.a_1255_F, a_3742_W.AirJump, a_3742_W.AirStuck, a_3742_W.AutoJump, a_3742_W.Blink));
    public static final BlockEntityType<j_2644_e> Y_601_j = BlockEntityType.n_1700_B("structure_block", lightning.product.BlockEntityType$n_1700_B.n_1700_B(j_2644_e::new, a_3742_W.l_14_c));
    public static final BlockEntityType<A_4605_O> Y_259_p = BlockEntityType.n_1700_B("end_gateway", lightning.product.BlockEntityType$n_1700_B.n_1700_B(A_4605_O::new, a_3742_W.ItemRelease));
    public static final BlockEntityType<T_1368_k> Q_2552_b = BlockEntityType.n_1700_B("command_block", lightning.product.BlockEntityType$n_1700_B.n_1700_B(T_1368_k::new, a_3742_W.N_260_m, a_3742_W.ItemsCooldown, a_3742_W.ItemScroller));
    public static final BlockEntityType<a_433_S> C_2741_M = BlockEntityType.n_1700_B("shulker_box", lightning.product.BlockEntityType$n_1700_B.n_1700_B(a_433_S::new, a_3742_W.k_1052_R, a_3742_W.EntityESP, a_3742_W.Crosshair, a_3742_W.CrystalESP, a_3742_W.ChatBubbles, a_3742_W.BlockOverlay, a_3742_W.DistantAlpha, a_3742_W.ArmorDurability, a_3742_W.Chams, a_3742_W.AspectRatio, a_3742_W.AnomalyESP, a_3742_W.x_555_z, a_3742_W.BlockESP, a_3742_W.Cosmetics, a_3742_W.Emotions, a_3742_W.Ambience, a_3742_W.Arrows));
    public static final BlockEntityType<BedBlockEntity> k_2293_S = BlockEntityType.n_1700_B("bed", lightning.product.BlockEntityType$n_1700_B.n_1700_B(BedBlockEntity::new, a_3742_W.F_1410_V, a_3742_W.S_4022_R, a_3742_W.H_1083_k, a_3742_W.R_3908_n, a_3742_W.RealmsWorldResetDto, a_3742_W.M_1641_O, a_3742_W.ValueObject, a_3742_W.dtoRealmsServerAddress, a_3742_W.RealmsWorldOptions, a_3742_W.RealmsServerPing, a_3742_W.q_1982_R, a_3742_W.U_1241_n, a_3742_W.j_1564_a, a_3742_W.RegionPingResult, a_3742_W.V_1225_t, a_3742_W.w_612_n));
    public static final BlockEntityType<m_1551_m> q_2307_F = BlockEntityType.n_1700_B("conduit", lightning.product.BlockEntityType$n_1700_B.n_1700_B(m_1551_m::new, a_3742_W.V_3441_j));
    public static final BlockEntityType<BarrelBlockEntity> Z_875_P = BlockEntityType.n_1700_B("barrel", lightning.product.BlockEntityType$n_1700_B.n_1700_B(BarrelBlockEntity::new, a_3742_W.y_254_d));
    public static final BlockEntityType<SmokerBlockEntity> c_3005_b = BlockEntityType.n_1700_B("smoker", lightning.product.BlockEntityType$n_1700_B.n_1700_B(SmokerBlockEntity::new, a_3742_W.H_2506_c));
    public static final BlockEntityType<BlastFurnaceBlockEntity> H_2857_Y = BlockEntityType.n_1700_B("blast_furnace", lightning.product.BlockEntityType$n_1700_B.n_1700_B(BlastFurnaceBlockEntity::new, a_3742_W.F_2052_z));
    public static final BlockEntityType<D_4899_Z> A_4115_X = BlockEntityType.n_1700_B("lectern", lightning.product.BlockEntityType$n_1700_B.n_1700_B(D_4899_Z::new, a_3742_W.F_489_x));
    public static final BlockEntityType<h_113_g> Y_1740_V = BlockEntityType.n_1700_B("bell", lightning.product.BlockEntityType$n_1700_B.n_1700_B(h_113_g::new, a_3742_W.l_3370_o));
    public static final BlockEntityType<JigsawBlockEntity> t_4043_B = BlockEntityType.n_1700_B("jigsaw", lightning.product.BlockEntityType$n_1700_B.n_1700_B(JigsawBlockEntity::new, a_3742_W.q_2034_t));
    public static final BlockEntityType<G_2722_I> x_607_J = BlockEntityType.n_1700_B("campfire", lightning.product.BlockEntityType$n_1700_B.n_1700_B(G_2722_I::new, a_3742_W.k_1366_K, a_3742_W.y_4842_Z));
    public static final BlockEntityType<F_997_G> e_4240_b = BlockEntityType.n_1700_B("beehive", lightning.product.BlockEntityType$n_1700_B.n_1700_B(F_997_G::new, a_3742_W.w_4866_k, a_3742_W.t_4057_p));
    private final Supplier<? extends T> d_2427_y;
    private final Set<T_2915_h> z_1737_N;
    private final Type<?> v_4276_D;

    @Nullable
    public static g_2336_b n_1700_B(BlockEntityType<?> tileEntityTypeIn) {
        return V_3137_a.X_933_l.J_1907_R(tileEntityTypeIn);
    }

    private static <T extends i_2154_H> BlockEntityType<T> n_1700_B(String key, n_1700_B<T> builder) {
        if (builder.J_1907_R.isEmpty()) {
            n_3318_d.warn("Block entity type {} requires at least one valid block to be defined!", (Object)key);
        }
        Type<?> type = j_3341_s.n_1700_B(References.u_2550_I, key);
        return V_3137_a.n_1700_B(V_3137_a.X_933_l, key, builder.n_1700_B(type));
    }

    public BlockEntityType(Supplier<? extends T> factoryIn, Set<T_2915_h> validBlocksIn, Type<?> dataFixerType) {
        this.d_2427_y = factoryIn;
        this.z_1737_N = validBlocksIn;
        this.v_4276_D = dataFixerType;
    }

    @Nullable
    public T n_1700_B() {
        return (T)((i_2154_H)this.d_2427_y.get());
    }

    public boolean n_1700_B(T_2915_h blockIn) {
        return this.z_1737_N.contains(blockIn);
    }

    @Nullable
    public T n_1700_B(BlockGetter blockReader, c_1514_x pos) {
        i_2154_H tileentity = blockReader.getTileEntity(pos);
        return (T)(tileentity != null && tileentity.z_1737_N() == this ? tileentity : null);
    }

    public static final class n_1700_B<T extends i_2154_H> {
        private final Supplier<? extends T> n_1700_B;
        private final Set<T_2915_h> J_1907_R;

        private n_1700_B(Supplier<? extends T> factoryIn, Set<T_2915_h> validBlocks) {
            this.n_1700_B = factoryIn;
            this.J_1907_R = validBlocks;
        }

        public static <T extends i_2154_H> n_1700_B<T> n_1700_B(Supplier<? extends T> factoryIn, T_2915_h ... validBlocks) {
            return new n_1700_B<T>(factoryIn, (Set<T_2915_h>)ImmutableSet.copyOf((Object[])validBlocks));
        }

        public BlockEntityType<T> n_1700_B(Type<?> datafixerType) {
            return new BlockEntityType<T>(this.n_1700_B, this.J_1907_R, datafixerType);
        }
    }
}



