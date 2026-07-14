/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DataFixUtils
 *  it.unimi.dsi.fastutil.longs.LongSets
 *  it.unimi.dsi.fastutil.longs.LongSets$EmptySet
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mojang.datafixers.DataFixUtils;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_1098_v;
import lightning.product.ChunkStatus;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.E_688_b;
import lightning.product.BlockHitResult;
import lightning.product.H_1748_a;
import lightning.product.HitResult;
import lightning.product.SharedConstants;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.L_3848_p;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.R_1900_x;
import lightning.product.R_3197_Z;
import lightning.product.V_3137_a;
import lightning.product.Y_1387_d;
import lightning.product.Y_4083_F;
import lightning.product.Z_749_F;
import lightning.product.Z_976_R;
import lightning.product.b_257_Y;
import lightning.product.BetterMinecraft;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_1633_k;
import lightning.product.FluidState;
import lightning.product.c_4037_x;
import lightning.product.FreeCam;
import lightning.product.e_3591_l;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.FrameTimer;
import lightning.product.l_3747_P;
import lightning.product.Transformation;
import lightning.product.ClientBootstrap;
import lightning.product.o_2840_r;
import lightning.product.p_752_J;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.v_3760_Q;
import lightning.product.x_2151_q;
import lightning.product.z_2963_s;
import net.optifine.Config;
import net.optifine.SmartAnimations;
import net.optifine.TextureAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.util.GuiPoint;
import net.optifine.util.GuiRect;
import net.optifine.util.GuiUtils;
import net.optifine.util.MemoryMonitor;
import net.optifine.util.NativeMemory;

public class a_1765_z
extends C_2701_A {
    private static final Map<z_2963_s.n_1700_B, String> n_1700_B = j_3341_s.n_1700_B(new EnumMap(z_2963_s.n_1700_B.class), (T p_lambda$static$0_0_) -> {
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.n_1700_B, "SW");
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.J_1907_R, "S");
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.R_4764_Y, "OW");
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.G_564_y, "O");
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.P_1922_E, "M");
        p_lambda$static$0_0_.put(z_2963_s.n_1700_B.u_1723_Y, "ML");
    });
    private final MinecraftClient J_1907_R;
    private final Y_4083_F R_4764_Y;
    private HitResult G_564_y;
    private HitResult P_1922_E;
    @Nullable
    private Y_1387_d u_1723_Y;
    @Nullable
    private H_1748_a v_4262_N;
    @Nullable
    private CompletableFuture<H_1748_a> w_1484_f;
    private String t_148_a = null;
    private List<String> s_956_w = null;
    private List<String> u_2550_I = null;
    private long M_588_G = 0L;
    private long P_4830_p = 0L;

    public a_1765_z(MinecraftClient mc) {
        this.J_1907_R = mc;
        this.R_4764_Y = mc.t_148_a;
    }

    public void n_1700_B() {
        this.w_1484_f = null;
        this.v_4262_N = null;
    }

    public void n_1700_B(g_221_o p_194818_1_) {
        this.J_1907_R.PlayerInfo().n_1700_B("debug");
        c_4037_x.v_4276_D();
        N_4263_v entity = this.J_1907_R.g_2268_R();
        this.G_564_y = entity.n_1700_B(20.0, 0.0f, false);
        this.P_1922_E = entity.n_1700_B(20.0, 0.0f, true);
        this.J_1907_R(p_194818_1_);
        this.R_4764_Y(p_194818_1_);
        c_4037_x.d_2461_k();
        if (this.J_1907_R.P_4830_p.LongRunningTask) {
            int i = this.J_1907_R.RealmsServerPing().Q_4569_t();
            this.n_1700_B(p_194818_1_, this.J_1907_R.i_1637_u(), 0, i / 2, true);
            R_3197_Z integratedserver = this.J_1907_R.n_3318_d();
            if (integratedserver != null) {
                this.n_1700_B(p_194818_1_, integratedserver.RowButton(), i - Math.min(i / 2, 240), i / 2, false);
            }
        }
        this.J_1907_R.PlayerInfo().R_4764_Y();
    }

    protected void J_1907_R(g_221_o p_230024_1_) {
        boolean betterF3Enabled;
        List<String> list = this.s_956_w;
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        boolean bl = betterF3Enabled = betterMinecraft != null && betterMinecraft.Q_4569_t();
        if (list == null || System.currentTimeMillis() > this.M_588_G) {
            list = this.J_1907_R();
            if (betterF3Enabled) {
                list = this.n_1700_B(list);
            }
            list.add("");
            boolean flag = this.J_1907_R.n_3318_d() != null;
            list.add("Debug: Pie [shift]: " + (this.J_1907_R.P_4830_p.RowButton ? "visible" : "hidden") + (flag ? " FPS + TPS" : " FPS") + " [alt]: " + (this.J_1907_R.P_4830_p.LongRunningTask ? "visible" : "hidden"));
            list.add("For help: press F3 + Q");
            this.s_956_w = list;
            this.M_588_G = System.currentTimeMillis() + 100L;
        }
        GuiPoint[] aguipoint = new GuiPoint[list.size()];
        GuiRect[] aguirect = new GuiRect[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            String s = list.get(i);
            if (Strings.isNullOrEmpty((String)s)) continue;
            int j = 9;
            String sWithoutFormatting = D_4024_W.n_1700_B(s);
            int k = this.R_4764_Y.J_1907_R(sWithoutFormatting);
            int l = 2;
            int i1 = 2 + j * i;
            aguirect[i] = new GuiRect(1, i1 - 1, 2 + k + 1, i1 + j - 1);
            aguipoint[i] = new GuiPoint(2, i1);
        }
        GuiUtils.fill(p_230024_1_.R_4764_Y().n_1700_B(), aguirect, -1873784752);
        this.R_4764_Y.n_1700_B(list, aguipoint, 0xE0E0E0, p_230024_1_.R_4764_Y().n_1700_B(), false, this.R_4764_Y.n_1700_B());
    }

    protected void R_4764_Y(g_221_o p_230025_1_) {
        boolean betterF3Enabled;
        List<String> list = this.u_2550_I;
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        boolean bl = betterF3Enabled = betterMinecraft != null && betterMinecraft.Q_4569_t();
        if (list == null || System.currentTimeMillis() > this.P_4830_p) {
            list = this.G_564_y();
            if (betterF3Enabled) {
                list = this.J_1907_R(list);
            }
            this.u_2550_I = list;
            this.P_4830_p = System.currentTimeMillis() + 100L;
        }
        GuiPoint[] aguipoint = new GuiPoint[list.size()];
        GuiRect[] aguirect = new GuiRect[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            String s = list.get(i);
            if (Strings.isNullOrEmpty((String)s)) continue;
            int j = 9;
            String sWithoutFormatting = D_4024_W.n_1700_B(s);
            int k = this.R_4764_Y.J_1907_R(sWithoutFormatting);
            int l = this.J_1907_R.RealmsServerPing().Q_4569_t() - 2 - k;
            int i1 = 2 + j * i;
            aguirect[i] = new GuiRect(l - 1, i1 - 1, l + k + 1, i1 + j - 1);
            aguipoint[i] = new GuiPoint(l, i1);
        }
        GuiUtils.fill(p_230025_1_.R_4764_Y().n_1700_B(), aguirect, -1873784752);
        this.R_4764_Y.n_1700_B(list, aguipoint, 0xE0E0E0, p_230025_1_.R_4764_Y().n_1700_B(), false, this.R_4764_Y.n_1700_B());
    }

    protected List<String> J_1907_R() {
        if (this.J_1907_R.e_4240_b != this.t_148_a) {
            StringBuffer stringbuffer = new StringBuffer(this.J_1907_R.e_4240_b);
            int i = Config.getChunkUpdates();
            int j = this.J_1907_R.e_4240_b.indexOf("T: ");
            if (j >= 0) {
                stringbuffer.insert(j, "(" + i + " chunk updates) ");
            }
            int k = Config.getFpsMin();
            int l = this.J_1907_R.e_4240_b.indexOf(" fps ");
            if (l >= 0) {
                stringbuffer.replace(0, l + 4, Config.getFpsString());
            }
            if (Config.isSmoothFps()) {
                stringbuffer.append(" sf");
            }
            if (Config.isFastRender()) {
                stringbuffer.append(" fr");
            }
            if (Config.isAnisotropicFiltering()) {
                stringbuffer.append(" af");
            }
            if (Config.isAntialiasing()) {
                stringbuffer.append(" aa");
            }
            if (Config.isRenderRegions()) {
                stringbuffer.append(" reg");
            }
            if (Config.isShaders()) {
                stringbuffer.append(" sh");
            }
            this.t_148_a = this.J_1907_R.e_4240_b = stringbuffer.toString();
        }
        List<String> list = this.R_4764_Y();
        StringBuilder stringbuilder = new StringBuilder();
        L_3848_p atlastexture = Config.getTextureMap();
        stringbuilder.append(", A: ");
        if (SmartAnimations.isActive()) {
            stringbuilder.append(atlastexture.v_4262_N() + TextureAnimations.getCountAnimationsActive());
            stringbuilder.append("/");
        }
        stringbuilder.append(atlastexture.u_1723_Y() + TextureAnimations.getCountAnimations());
        String s1 = stringbuilder.toString();
        for (int i1 = 0; i1 < list.size(); ++i1) {
            Object s = list.get(i1);
            if (s == null || !((String)s).startsWith("P: ")) continue;
            s = (String)s + s1;
            list.set(i1, (String)s);
            break;
        }
        return list;
    }

    protected List<String> R_4764_Y() {
        x_2151_q shadergroup;
        b_4507_u world;
        R_3197_Z integratedserver = this.J_1907_R.n_3318_d();
        c_1633_k networkmanager = this.J_1907_R.k_2293_S().getNetworkManager();
        float f = networkmanager.P_4830_p();
        float f1 = networkmanager.M_588_G();
        String s = integratedserver != null ? String.format("Integrated server @ %.0f ms ticks, %.0f tx, %.0f rx", Float.valueOf(integratedserver.r_3651_U()), Float.valueOf(f), Float.valueOf(f1)) : String.format("\"%s\" server, %.0f tx, %.0f rx", this.J_1907_R.Y_259_p.h_1847_R(), Float.valueOf(f), Float.valueOf(f1));
        c_1514_x blockpos = this.J_1907_R.g_2268_R().b_2312_j();
        if (this.J_1907_R.UploadStatus()) {
            return Lists.newArrayList((Object[])new String[]{"Minecraft " + SharedConstants.n_1700_B().getName() + " (" + this.J_1907_R.P_1922_E() + "/" + p_752_J.n_1700_B() + ")", this.J_1907_R.e_4240_b, s, this.J_1907_R.u_1723_Y.v_4262_N(), this.J_1907_R.u_1723_Y.t_148_a(), "P: " + this.J_1907_R.v_4262_N.G_564_y() + ". T: " + this.J_1907_R.Y_601_j.P_1922_E(), this.J_1907_R.Y_601_j.e_2887_G(), "", String.format("Chunk-relative: %d %d %d", blockpos.getX() & 0xF, blockpos.getY() & 0xF, blockpos.getZ() & 0xF)});
        }
        N_4263_v entity = this.J_1907_R.g_2268_R();
        b_257_Y direction = entity.o_2767_H();
        String s1 = switch (direction) {
            case b_257_Y.R_4764_Y -> "Towards negative Z";
            case b_257_Y.G_564_y -> "Towards positive Z";
            case b_257_Y.P_1922_E -> "Towards negative X";
            case b_257_Y.u_1723_Y -> "Towards positive X";
            default -> "Invalid";
        };
        Y_1387_d chunkpos = new Y_1387_d(blockpos);
        if (!Objects.equals(this.u_1723_Y, chunkpos)) {
            this.u_1723_Y = chunkpos;
            this.n_1700_B();
        }
        LongSets.EmptySet longset = (world = this.v_4262_N()) instanceof e_3591_l ? ((e_3591_l)world).Ping() : LongSets.EMPTY_SET;
        ArrayList list = Lists.newArrayList((Object[])new String[]{"Minecraft " + SharedConstants.n_1700_B().getName() + " (" + this.J_1907_R.P_1922_E() + "/" + p_752_J.n_1700_B() + (String)("release".equalsIgnoreCase(this.J_1907_R.u_1723_Y()) ? "" : "/" + this.J_1907_R.u_1723_Y()) + ")", this.J_1907_R.e_4240_b, s, this.J_1907_R.u_1723_Y.v_4262_N(), this.J_1907_R.u_1723_Y.t_148_a(), "P: " + this.J_1907_R.v_4262_N.G_564_y() + ". T: " + this.J_1907_R.Y_601_j.P_1922_E(), this.J_1907_R.Y_601_j.e_2887_G()});
        String s2 = this.u_1723_Y();
        if (s2 != null) {
            list.add(s2);
        }
        list.add(String.valueOf(this.J_1907_R.Y_601_j.g_2268_R().n_1700_B()) + " FC: " + longset.size());
        list.add("");
        FreeCam freeCam = (FreeCam)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class);
        if (freeCam != null && freeCam.w_1484_f() && this.J_1907_R.Y_259_p != null) {
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.J_1907_R.Y_259_p.O_3598_v(), this.J_1907_R.Y_259_p.X_2960_b(), this.J_1907_R.Y_259_p.l_2647_k()));
            c_1514_x camBlock = new c_1514_x(this.J_1907_R.Y_259_p.O_3598_v(), this.J_1907_R.Y_259_p.X_2960_b(), this.J_1907_R.Y_259_p.l_2647_k());
            list.add(String.format("Block: %d %d %d", camBlock.getX(), camBlock.getY(), camBlock.getZ()));
        } else {
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.J_1907_R.g_2268_R().O_3598_v(), this.J_1907_R.g_2268_R().X_2960_b(), this.J_1907_R.g_2268_R().l_2647_k()));
            list.add(String.format("Block: %d %d %d", blockpos.getX(), blockpos.getY(), blockpos.getZ()));
        }
        list.add(String.format("Chunk: %d %d %d in %d %d %d", blockpos.getX() & 0xF, blockpos.getY() & 0xF, blockpos.getZ() & 0xF, blockpos.getX() >> 4, blockpos.getY() >> 4, blockpos.getZ() >> 4));
        list.add(String.format(Locale.ROOT, "Facing: %s (%s) (%.1f / %.1f)", direction, s1, Float.valueOf(u_530_F.v_4262_N(entity.p_178_J)), Float.valueOf(u_530_F.v_4262_N(entity.f_4016_n))));
        if (this.J_1907_R.Y_601_j != null) {
            if (this.J_1907_R.Y_601_j.M_588_G(blockpos)) {
                H_1748_a chunk = this.t_148_a();
                if (chunk.isEmpty()) {
                    list.add("Waiting for chunk...");
                } else {
                    int i = this.J_1907_R.Y_601_j.v_4262_N().G_564_y().J_1907_R(blockpos, 0);
                    int j = this.J_1907_R.Y_601_j.getLightFor(K_4719_o.n_1700_B, blockpos);
                    int k = this.J_1907_R.Y_601_j.getLightFor(K_4719_o.J_1907_R, blockpos);
                    list.add("Client Light: " + i + " (" + j + " sky, " + k + " block)");
                    H_1748_a chunk1 = this.w_1484_f();
                    if (chunk1 != null) {
                        R_1900_x worldlightmanager = world.q_2307_F().G_564_y();
                        list.add("Server Light: (" + worldlightmanager.n_1700_B(K_4719_o.n_1700_B).n_1700_B(blockpos) + " sky, " + worldlightmanager.n_1700_B(K_4719_o.J_1907_R).n_1700_B(blockpos) + " block)");
                    } else {
                        list.add("Server Light: (?? sky, ?? block)");
                    }
                    StringBuilder stringbuilder = new StringBuilder("CH");
                    for (z_2963_s.n_1700_B heightmap$type : z_2963_s.n_1700_B.values()) {
                        if (!heightmap$type.R_4764_Y()) continue;
                        stringbuilder.append(" ").append(n_1700_B.get(heightmap$type)).append(": ").append(chunk.getTopBlockY(heightmap$type, blockpos.getX(), blockpos.getZ()));
                    }
                    list.add(stringbuilder.toString());
                    stringbuilder.setLength(0);
                    stringbuilder.append("SH");
                    for (z_2963_s.n_1700_B heightmap$type1 : z_2963_s.n_1700_B.values()) {
                        if (!heightmap$type1.G_564_y()) continue;
                        stringbuilder.append(" ").append(n_1700_B.get(heightmap$type1)).append(": ");
                        if (chunk1 != null) {
                            stringbuilder.append(chunk1.getTopBlockY(heightmap$type1, blockpos.getX(), blockpos.getZ()));
                            continue;
                        }
                        stringbuilder.append("??");
                    }
                    list.add(stringbuilder.toString());
                    if (blockpos.getY() >= 0 && blockpos.getY() < 256) {
                        list.add("Biome: " + String.valueOf(this.J_1907_R.Y_601_j.t_1786_h().J_1907_R(V_3137_a.PlayerInfo).J_1907_R(this.J_1907_R.Y_601_j.P_1922_E(blockpos))));
                        long i1 = 0L;
                        float f2 = 0.0f;
                        if (chunk1 != null) {
                            f2 = world.Y_1740_V();
                            i1 = chunk1.getInhabitedTime();
                        }
                        DifficultyInstance difficultyinstance = new DifficultyInstance(world.x_607_J(), world.Z_976_R(), i1, f2);
                        list.add(String.format(Locale.ROOT, "Local Difficulty: %.2f // %.2f (Day %d)", Float.valueOf(difficultyinstance.J_1907_R()), Float.valueOf(difficultyinstance.R_4764_Y()), this.J_1907_R.Y_601_j.Z_976_R() / 24000L));
                    }
                }
            } else {
                list.add("Outside of world...");
            }
        } else {
            list.add("Outside of world...");
        }
        e_3591_l serverworld = this.P_1922_E();
        if (serverworld != null) {
            u_743_i.n_1700_B worldentityspawner$entitydensitymanager = serverworld.Y_259_p().P_4830_p();
            if (worldentityspawner$entitydensitymanager != null) {
                Object2IntMap<Z_749_F> object2intmap = worldentityspawner$entitydensitymanager.J_1907_R();
                int l = worldentityspawner$entitydensitymanager.n_1700_B();
                list.add("SC: " + l + ", " + Stream.of(Z_749_F.values()).map(p_lambda$getInfoLeft$1_1_ -> Character.toUpperCase(p_lambda$getInfoLeft$1_1_.J_1907_R().charAt(0)) + ": " + object2intmap.getInt(p_lambda$getInfoLeft$1_1_)).collect(Collectors.joining(", ")));
            } else {
                list.add("SC: N/A");
            }
        }
        if ((shadergroup = this.J_1907_R.s_956_w.v_4262_N()) != null) {
            list.add("Shader: " + shadergroup.n_1700_B());
        }
        list.add(this.J_1907_R.Z_976_R().u_1723_Y() + String.format(" (Mood %d%%)", Math.round(this.J_1907_R.Y_259_p.R_4764_Y() * 100.0f)));
        return list;
    }

    @Nullable
    private e_3591_l P_1922_E() {
        R_3197_Z integratedserver = this.J_1907_R.n_3318_d();
        return integratedserver != null ? integratedserver.n_1700_B(this.J_1907_R.Y_601_j.g_2268_R()) : null;
    }

    @Nullable
    private String u_1723_Y() {
        e_3591_l serverworld = this.P_1922_E();
        return serverworld != null ? serverworld.e_2887_G() : null;
    }

    private b_4507_u v_4262_N() {
        return (b_4507_u)DataFixUtils.orElse(Optional.ofNullable(this.J_1907_R.n_3318_d()).flatMap(p_lambda$getWorld$2_1_ -> Optional.ofNullable(p_lambda$getWorld$2_1_.n_1700_B(this.J_1907_R.Y_601_j.g_2268_R()))), (Object)this.J_1907_R.Y_601_j);
    }

    @Nullable
    private H_1748_a w_1484_f() {
        if (this.w_1484_f == null) {
            e_3591_l serverworld = this.P_1922_E();
            if (serverworld != null) {
                this.w_1484_f = serverworld.Y_259_p().n_1700_B(this.u_1723_Y.J_1907_R, this.u_1723_Y.R_4764_Y, ChunkStatus.P_4830_p, false).thenApply(p_lambda$getServerChunk$5_0_ -> (H_1748_a)p_lambda$getServerChunk$5_0_.map(p_lambda$null$3_0_ -> (H_1748_a)p_lambda$null$3_0_, p_lambda$null$4_0_ -> null));
            }
            if (this.w_1484_f == null) {
                this.w_1484_f = CompletableFuture.completedFuture(this.t_148_a());
            }
        }
        return this.w_1484_f.getNow(null);
    }

    private H_1748_a t_148_a() {
        if (this.v_4262_N == null) {
            this.v_4262_N = this.J_1907_R.Y_601_j.u_1723_Y(this.u_1723_Y.J_1907_R, this.u_1723_Y.R_4764_Y);
        }
        return this.v_4262_N;
    }

    protected List<String> G_564_y() {
        N_4263_v entity;
        long i = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long k = Runtime.getRuntime().freeMemory();
        long l = j - k;
        ArrayList list = Lists.newArrayList((Object[])new String[]{String.format("Java: %s %dbit", System.getProperty("java.version"), this.J_1907_R.B_1668_F() ? 64 : 32), String.format("Mem: % 2d%% %03d/%03dMB", l * 100L / i, a_1765_z.n_1700_B(l), a_1765_z.n_1700_B(i)), String.format("Allocated: % 2d%% %03dMB", j * 100L / i, a_1765_z.n_1700_B(j)), "", String.format("CPU: %s", Z_976_R.J_1907_R()), "", String.format("Display: %dx%d (%s)", MinecraftClient.A_4115_X().RealmsServerPing().u_2550_I(), MinecraftClient.A_4115_X().RealmsServerPing().M_588_G(), Z_976_R.n_1700_B()), Z_976_R.R_4764_Y(), Z_976_R.G_564_y()});
        long i1 = NativeMemory.getBufferAllocated();
        long j1 = NativeMemory.getBufferMaximum();
        long k1 = NativeMemory.getImageAllocated();
        String s = "Native: " + a_1765_z.n_1700_B(i1) + "/" + a_1765_z.n_1700_B(j1) + "+" + a_1765_z.n_1700_B(k1) + "MB";
        list.add(3, s);
        list.set(4, "Allocation: " + MemoryMonitor.getAllocationRateAvgMb() + "MB/s");
        if (Reflector.BrandingControl_getBrandings.exists()) {
            list.add("");
            for (String s1 : (Set)Reflector.call(Reflector.BrandingControl_getBrandings, true, false)) {
                if (s1.startsWith("Minecraft ")) continue;
                list.add(s1);
            }
        }
        if (this.J_1907_R.UploadStatus()) {
            return list;
        }
        if (this.G_564_y.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            Object entry2;
            c_1514_x blockpos = ((BlockHitResult)this.G_564_y).n_1700_B();
            K_4074_S blockstate = this.J_1907_R.Y_601_j.getBlockState(blockpos);
            list.add("");
            list.add(String.valueOf((Object)D_4024_W.Y_601_j) + "Targeted Block: " + blockpos.getX() + ", " + blockpos.getY() + ", " + blockpos.getZ());
            list.add(String.valueOf(V_3137_a.q_4610_l.J_1907_R(blockstate.J_1907_R())));
            for (Object entry2 : blockstate.q_2307_F().entrySet()) {
                list.add(this.n_1700_B((Map.Entry<v_3760_Q<?>, Comparable<?>>)entry2));
            }
            Collection<Object> collection1 = Reflector.IForgeBlock_getTags.exists() ? (Collection)Reflector.call(blockstate.J_1907_R(), Reflector.IForgeBlock_getTags, new Object[0]) : this.J_1907_R.k_2293_S().u_2550_I().n_1700_B().n_1700_B(blockstate.J_1907_R());
            entry2 = collection1.iterator();
            while (entry2.hasNext()) {
                g_2336_b resourcelocation = (g_2336_b)entry2.next();
                list.add("#" + String.valueOf(resourcelocation));
            }
        }
        if (this.P_1922_E.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            c_1514_x blockpos1 = ((BlockHitResult)this.P_1922_E).n_1700_B();
            FluidState fluidstate = this.J_1907_R.Y_601_j.getFluidState(blockpos1);
            list.add("");
            list.add(String.valueOf((Object)D_4024_W.Y_601_j) + "Targeted Fluid: " + blockpos1.getX() + ", " + blockpos1.getY() + ", " + blockpos1.getZ());
            list.add(String.valueOf(V_3137_a.G_624_v.J_1907_R(fluidstate.n_1700_B())));
            for (Map.Entry entry1 : fluidstate.q_2307_F().entrySet()) {
                list.add(this.n_1700_B(entry1));
            }
            Collection<g_2336_b> collection2 = Reflector.ForgeFluid_getTags.exists() ? (Collection<g_2336_b>)Reflector.call(fluidstate.n_1700_B(), Reflector.ForgeFluid_getTags, new Object[0]) : this.J_1907_R.k_2293_S().u_2550_I().R_4764_Y().n_1700_B(fluidstate.n_1700_B());
            for (g_2336_b resourcelocation1 : collection2) {
                list.add("#" + String.valueOf(resourcelocation1));
            }
        }
        if ((entity = this.J_1907_R.q_2307_F) != null) {
            list.add("");
            list.add(String.valueOf((Object)D_4024_W.Y_601_j) + "Targeted Entity");
            list.add(String.valueOf(V_3137_a.g_221_o.J_1907_R(entity.f_4016_n())));
            if (Reflector.ForgeEntityType_getTags.exists()) {
                Collection collection = (Collection)Reflector.call(entity.f_4016_n(), Reflector.ForgeEntityType_getTags, new Object[0]);
                collection.forEach(p_lambda$getDebugInfoRight$6_1_ -> list.add("#" + String.valueOf(p_lambda$getDebugInfoRight$6_1_)));
            }
        }
        return list;
    }

    private String n_1700_B(Map.Entry<v_3760_Q<?>, Comparable<?>> entryIn) {
        v_3760_Q<?> property = entryIn.getKey();
        Comparable<?> comparable = entryIn.getValue();
        Object s = j_3341_s.n_1700_B(property, comparable);
        if (Boolean.TRUE.equals(comparable)) {
            s = String.valueOf((Object)D_4024_W.u_2550_I) + (String)s;
        } else if (Boolean.FALSE.equals(comparable)) {
            s = String.valueOf((Object)D_4024_W.P_4830_p) + (String)s;
        }
        return property.P_1922_E() + ": " + (String)s;
    }

    private void n_1700_B(g_221_o p_238509_1_, FrameTimer p_238509_2_, int p_238509_3_, int p_238509_4_, boolean p_238509_5_) {
        if (!p_238509_5_) {
            int i = (int)(512.0 / this.J_1907_R.RealmsServerPing().w_1457_N());
            p_238509_3_ = Math.max(p_238509_3_, i);
            p_238509_4_ = this.J_1907_R.RealmsServerPing().Q_4569_t() - p_238509_3_;
            c_4037_x.t_1786_h();
            int j = p_238509_2_.n_1700_B();
            int k = p_238509_2_.J_1907_R();
            long[] along = p_238509_2_.R_4764_Y();
            int l = p_238509_3_;
            int i1 = Math.max(0, along.length - p_238509_4_);
            int j1 = along.length - i1;
            int k1 = p_238509_2_.n_1700_B(j + i1);
            long l1 = 0L;
            int i2 = Integer.MAX_VALUE;
            int j2 = Integer.MIN_VALUE;
            for (int k2 = 0; k2 < j1; ++k2) {
                int l2 = (int)(along[p_238509_2_.n_1700_B(k1 + k2)] / 1000000L);
                i2 = Math.min(i2, l2);
                j2 = Math.max(j2, l2);
                l1 += (long)l2;
            }
            int l4 = this.J_1907_R.RealmsServerPing().M_182_A();
            a_1765_z.fill(p_238509_1_, p_238509_3_, l4 - 60, p_238509_3_ + j1, l4, -1873784752);
            D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
            c_4037_x.Y_601_j();
            c_4037_x.e_4240_b();
            c_4037_x.s_2632_s();
            bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
            D_1098_v matrix4f = Transformation.n_1700_B().R_4764_Y();
            while (k1 != k) {
                int i3 = p_238509_2_.n_1700_B(along[k1], p_238509_5_ ? 30 : 60, p_238509_5_ ? 60 : 20);
                int j3 = p_238509_5_ ? 100 : 60;
                int k3 = this.n_1700_B(u_530_F.n_1700_B(i3, 0, j3), 0, j3 / 2, j3);
                int l3 = k3 >> 24 & 0xFF;
                int i4 = k3 >> 16 & 0xFF;
                int j4 = k3 >> 8 & 0xFF;
                int k4 = k3 & 0xFF;
                bufferbuilder.n_1700_B(matrix4f, (float)(l + 1), (float)l4, 0.0f).color(i4, j4, k4, l3).endVertex();
                bufferbuilder.n_1700_B(matrix4f, (float)(l + 1), (float)(l4 - i3 + 1), 0.0f).color(i4, j4, k4, l3).endVertex();
                bufferbuilder.n_1700_B(matrix4f, (float)l, (float)(l4 - i3 + 1), 0.0f).color(i4, j4, k4, l3).endVertex();
                bufferbuilder.n_1700_B(matrix4f, (float)l, (float)l4, 0.0f).color(i4, j4, k4, l3).endVertex();
                ++l;
                k1 = p_238509_2_.n_1700_B(k1 + 1);
            }
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
            if (p_238509_5_) {
                a_1765_z.fill(p_238509_1_, p_238509_3_ + 1, l4 - 30 + 1, p_238509_3_ + 14, l4 - 30 + 10, -1873784752);
                this.R_4764_Y.J_1907_R(p_238509_1_, "60 FPS", (float)(p_238509_3_ + 2), (float)(l4 - 30 + 2), 0xE0E0E0);
                this.hLine(p_238509_1_, p_238509_3_, p_238509_3_ + j1 - 1, l4 - 30, -1);
                a_1765_z.fill(p_238509_1_, p_238509_3_ + 1, l4 - 60 + 1, p_238509_3_ + 14, l4 - 60 + 10, -1873784752);
                this.R_4764_Y.J_1907_R(p_238509_1_, "30 FPS", (float)(p_238509_3_ + 2), (float)(l4 - 60 + 2), 0xE0E0E0);
                this.hLine(p_238509_1_, p_238509_3_, p_238509_3_ + j1 - 1, l4 - 60, -1);
            } else {
                a_1765_z.fill(p_238509_1_, p_238509_3_ + 1, l4 - 60 + 1, p_238509_3_ + 14, l4 - 60 + 10, -1873784752);
                this.R_4764_Y.J_1907_R(p_238509_1_, "20 TPS", (float)(p_238509_3_ + 2), (float)(l4 - 60 + 2), 0xE0E0E0);
                this.hLine(p_238509_1_, p_238509_3_, p_238509_3_ + j1 - 1, l4 - 60, -1);
            }
            this.hLine(p_238509_1_, p_238509_3_, p_238509_3_ + j1 - 1, l4 - 1, -1);
            this.vLine(p_238509_1_, p_238509_3_, l4 - 60, l4, -1);
            this.vLine(p_238509_1_, p_238509_3_ + j1 - 1, l4 - 60, l4, -1);
            if (p_238509_5_ && this.J_1907_R.P_4830_p.G_564_y > 0 && this.J_1907_R.P_4830_p.G_564_y <= 250) {
                this.hLine(p_238509_1_, p_238509_3_, p_238509_3_ + j1 - 1, l4 - 1 - (int)(1800.0 / (double)this.J_1907_R.P_4830_p.G_564_y), -16711681);
            }
            String s = i2 + " ms min";
            String s1 = l1 / (long)j1 + " ms avg";
            String s2 = j2 + " ms max";
            this.R_4764_Y.n_1700_B(p_238509_1_, s, (float)(p_238509_3_ + 2), (float)(l4 - 60 - 9), 0xE0E0E0);
            this.R_4764_Y.n_1700_B(p_238509_1_, s1, (float)(p_238509_3_ + j1 / 2 - this.R_4764_Y.J_1907_R(s1) / 2), (float)(l4 - 60 - 9), 0xE0E0E0);
            this.R_4764_Y.n_1700_B(p_238509_1_, s2, (float)(p_238509_3_ + j1 - this.R_4764_Y.J_1907_R(s2)), (float)(l4 - 60 - 9), 0xE0E0E0);
            c_4037_x.multiplayerClientSuggestionProvider();
        }
    }

    private int n_1700_B(int height, int heightMin, int heightMid, int heightMax) {
        return height < heightMid ? this.n_1700_B(-16711936, -256, (float)height / (float)heightMid) : this.n_1700_B(-256, -65536, (float)(height - heightMid) / (float)(heightMax - heightMid));
    }

    private int n_1700_B(int col1, int col2, float factor) {
        int i = col1 >> 24 & 0xFF;
        int j = col1 >> 16 & 0xFF;
        int k = col1 >> 8 & 0xFF;
        int l = col1 & 0xFF;
        int i1 = col2 >> 24 & 0xFF;
        int j1 = col2 >> 16 & 0xFF;
        int k1 = col2 >> 8 & 0xFF;
        int l1 = col2 & 0xFF;
        int i2 = u_530_F.n_1700_B((int)u_530_F.v_4262_N(factor, i, i1), 0, 255);
        int j2 = u_530_F.n_1700_B((int)u_530_F.v_4262_N(factor, j, j1), 0, 255);
        int k2 = u_530_F.n_1700_B((int)u_530_F.v_4262_N(factor, k, k1), 0, 255);
        int l2 = u_530_F.n_1700_B((int)u_530_F.v_4262_N(factor, l, l1), 0, 255);
        return i2 << 24 | j2 << 16 | k2 << 8 | l2;
    }

    private static long n_1700_B(long bytes) {
        return bytes / 1024L / 1024L;
    }

    private List<String> n_1700_B(List<String> list) {
        ArrayList formatted = Lists.newArrayList();
        for (String line : list) {
            Object formattedLine;
            block83: {
                if (line == null || line.isEmpty()) {
                    formatted.add(line);
                    continue;
                }
                formattedLine = line;
                if (line.contains(" fps")) {
                    try {
                        int fpsIndex = line.indexOf(" fps");
                        String beforeFps = line.substring(0, fpsIndex);
                        String afterFps = line.substring(fpsIndex);
                        String[] parts = beforeFps.split(" ");
                        if (parts.length > 0) {
                            try {
                                int fps = Integer.parseInt(parts[parts.length - 1]);
                                D_4024_W fpsColor = fps > 120 ? D_4024_W.u_2550_I : (fps < 100 ? (fps < 60 ? D_4024_W.P_4830_p : D_4024_W.Q_4569_t) : D_4024_W.Q_4569_t);
                                formattedLine = beforeFps.substring(0, beforeFps.lastIndexOf(" ")) + " " + fpsColor.toString() + parts[parts.length - 1] + D_4024_W.Q_2552_b.toString() + D_4024_W.Q_4569_t.toString() + afterFps + D_4024_W.Q_2552_b.toString();
                            }
                            catch (NumberFormatException ignored) {
                                formattedLine = D_4024_W.Q_4569_t.toString() + line + D_4024_W.Q_2552_b.toString();
                            }
                            break block83;
                        }
                        formattedLine = D_4024_W.Q_4569_t.toString() + line + D_4024_W.Q_2552_b.toString();
                    }
                    catch (Exception ignored) {
                        formattedLine = D_4024_W.Q_4569_t.toString() + line + D_4024_W.Q_2552_b.toString();
                    }
                } else if (line.contains("XYZ:")) {
                    try {
                        String[] parts = line.split(":");
                        if (parts.length < 2) break block83;
                        coords = parts[1].trim();
                        coordParts = coords.split(" / ");
                        if (coordParts.length == 3) {
                            formattedLine = D_4024_W.M_588_G.toString() + "XYZ:" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.P_4830_p.toString() + coordParts[0] + D_4024_W.Q_2552_b.toString() + "  " + D_4024_W.u_2550_I.toString() + coordParts[1] + D_4024_W.Q_2552_b.toString() + "  " + D_4024_W.s_956_w.toString() + coordParts[2] + D_4024_W.Q_2552_b.toString();
                            break block83;
                        }
                        formattedLine = D_4024_W.M_588_G.toString() + "XYZ:" + D_4024_W.Q_2552_b.toString() + " " + coords;
                    }
                    catch (Exception e) {
                        formattedLine = line;
                    }
                } else if (line.contains("Block:") && !line.contains("Targeted")) {
                    try {
                        String[] parts = line.split(":");
                        if (parts.length < 2) break block83;
                        coords = parts[1].trim();
                        coordParts = coords.split(" ");
                        if (coordParts.length == 3) {
                            formattedLine = D_4024_W.w_1484_f.toString() + "Block:" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.P_4830_p.toString() + coordParts[0] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.u_2550_I.toString() + coordParts[1] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.s_956_w.toString() + coordParts[2] + D_4024_W.Q_2552_b.toString();
                            break block83;
                        }
                        formattedLine = D_4024_W.w_1484_f.toString() + "Block:" + D_4024_W.Q_2552_b.toString() + " " + coords;
                    }
                    catch (Exception e) {
                        formattedLine = line;
                    }
                } else if (line.contains("Chunk:") && line.contains("in")) {
                    try {
                        String[] split;
                        String chunkInfo;
                        String[] parts = line.split(":");
                        if (parts.length < 2 || !(chunkInfo = parts[1].trim()).contains("in") || (split = chunkInfo.split(" in ")).length != 2) break block83;
                        String[] relative = split[0].split(" ");
                        String[] chunk = split[1].split(" ");
                        if (relative.length == 3 && chunk.length == 3) {
                            formattedLine = D_4024_W.w_1484_f.toString() + "Chunk:" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.P_4830_p.toString() + relative[0] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.u_2550_I.toString() + relative[1] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.s_956_w.toString() + relative[2] + D_4024_W.Q_2552_b.toString() + " in " + D_4024_W.P_4830_p.toString() + chunk[0] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.u_2550_I.toString() + chunk[1] + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.s_956_w.toString() + chunk[2] + D_4024_W.Q_2552_b.toString();
                        }
                    }
                    catch (Exception e) {
                        formattedLine = D_4024_W.w_1484_f.toString() + line + D_4024_W.Q_2552_b.toString();
                    }
                } else if (line.contains("Facing:")) {
                    try {
                        String[] parts = line.split(":");
                        if (parts.length >= 2) {
                            String facing = parts[1].trim();
                            formattedLine = D_4024_W.w_1484_f.toString() + "Facing:" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_588_G.toString() + facing + D_4024_W.Q_2552_b.toString();
                        }
                    }
                    catch (Exception e) {
                        formattedLine = D_4024_W.w_1484_f.toString() + line + D_4024_W.Q_2552_b.toString();
                    }
                } else if (line.contains("Rotation:") || line.contains("Yaw:") && line.contains("Pitch:")) {
                    int colonIndex = line.indexOf(":");
                    formattedLine = colonIndex >= 0 ? D_4024_W.w_1484_f.toString() + line.substring(0, colonIndex) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(colonIndex + 1) : line;
                } else if (line.contains("Light:") || line.contains("\u0421\u0432\u0435\u0442:")) {
                    int colonIndex = line.indexOf(":");
                    formattedLine = colonIndex >= 0 ? D_4024_W.w_1484_f.toString() + line.substring(0, colonIndex) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(colonIndex + 1) : line;
                } else if (line.contains("Biome:") || line.contains("\u0411\u0438\u043e\u043c:")) {
                    try {
                        String[] parts = line.split(":");
                        if (parts.length >= 2) {
                            String biome = parts[1].trim();
                            formattedLine = D_4024_W.w_1484_f.toString() + parts[0] + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_588_G.toString() + biome + D_4024_W.Q_2552_b.toString();
                        }
                    }
                    catch (Exception e) {
                        formattedLine = line;
                    }
                } else if (line.contains("Render Distance:") || line.contains("\u0414\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u043e\u0440\u0438\u0441\u043e\u0432\u043a\u0438:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Graphics:") || line.contains("\u0413\u0440\u0430\u0444\u0438\u043a\u0430:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Clouds:") || line.contains("\u041e\u0431\u043b\u0430\u043a\u0430:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Biome Blend") || line.contains("\u0421\u043c\u0435\u0448\u0438\u0432\u0430\u043d\u0438\u0435 \u0431\u0438\u043e\u043c\u043e\u0432:")) {
                    int colonIndex = line.indexOf(":");
                    formattedLine = colonIndex >= 0 ? D_4024_W.w_1484_f.toString() + line.substring(0, colonIndex) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(colonIndex + 1).trim() + D_4024_W.Q_2552_b.toString() : line;
                } else if (line.contains("server @") || line.contains("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0442\u0438\u043a\u0430") || line.contains("ms ticks")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf("@")) + "@" + D_4024_W.Q_2552_b.toString() + line.substring(line.indexOf("@") + 1);
                } else if (line.contains("tx,") || line.contains("rx") || line.contains("\u041e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u043e \u043f\u0430\u043a\u0435\u0442\u043e\u0432:") || line.contains("\u041f\u043e\u043b\u0443\u0447\u0435\u043d\u043e \u043f\u0430\u043a\u0435\u0442\u043e\u0432:")) {
                    int colonIndex = line.indexOf(":");
                    formattedLine = colonIndex >= 0 ? D_4024_W.w_1484_f.toString() + line.substring(0, colonIndex) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(colonIndex + 1).trim() + D_4024_W.Q_2552_b.toString() : line;
                } else if (line.contains("Chunk Sections:") || line.contains("\u0421\u0435\u043a\u0446\u0438\u0438 \u0427\u0430\u043d\u043a\u043e\u0432") || line.contains("\u0421\u0435\u043a\u0446\u0438\u0438 \u0447\u0430\u043d\u043a\u043e\u0432")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Chunk Culling:") || line.contains("\u041e\u0442\u0431\u0440\u0430\u043a\u043e\u0432\u043a\u0430 \u0447\u0430\u043d\u043a\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Pending Chunks:") || line.contains("\u041e\u0436\u0438\u0434\u0430\u0435\u0442\u0441\u044f \u0427\u0430\u043d\u043a\u043e\u0432:") || line.contains("\u041e\u0436\u0438\u0434\u0430\u0435\u0442\u0441\u044f \u0447\u0430\u043d\u043a\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Pending Uploads") || line.contains("\u041e\u0436\u0438\u0434\u0430\u044e\u0442 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438") || line.contains("\u041e\u0436\u0438\u0434\u0430\u044e\u0442 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 \u043d\u0430 \u0413\u041f:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Available Buffers:") || line.contains("\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u0431\u0443\u0444\u0435\u0440\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Client Chunk Cache:") || line.contains("\u041a\u044d\u0448 \u0447\u0430\u043d\u043a\u043e\u0432 \u043a\u043b\u0438\u0435\u043d\u0442\u0430:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Loaded Chunks:") || line.contains("\u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u043e \u0427\u0430\u043d\u043a\u043e\u0432:") || line.contains("\u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u043e \u0447\u0430\u043d\u043a\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Unloaded Chunks:") || line.contains("\u0412\u044b\u0433\u0440\u0443\u0436\u0435\u043d\u043d\u044b\u0445 \u0427\u0430\u043d\u043a\u043e\u0432:") || line.contains("\u0412\u044b\u0433\u0440\u0443\u0436\u0435\u043d\u043d\u044b\u0445 \u0447\u0430\u043d\u043a\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Chunks in Front:") || line.contains("\u0427\u0430\u043d\u043a\u043e\u0432 \u0441\u043f\u0435\u0440\u0435\u0434\u0438:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("minecraft:") && (line.contains("FC:") || line.contains("\u0418\u0437\u043c\u0435\u0440\u0435\u043d\u0438\u0435:"))) {
                    if (line.contains("FC:")) {
                        String[] parts = line.split(" FC:");
                        formattedLine = D_4024_W.w_1484_f.toString() + "\u0418\u0437\u043c\u0435\u0440\u0435\u043d\u0438\u0435:" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_588_G.toString() + parts[0].trim() + D_4024_W.Q_2552_b.toString() + (String)(parts.length > 1 ? " FC: " + parts[1].trim() : "");
                    } else {
                        formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_588_G.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                    }
                } else if (line.contains("Local Difficulty:") || line.contains("\u041b\u043e\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0441\u043b\u043e\u0436\u043d\u043e\u0441\u0442\u044c:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Days Played:") || line.contains("\u0414\u043d\u0435\u0439 \u0441\u044b\u0433\u0440\u0430\u043d\u043e:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.startsWith("CH")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, 2) + D_4024_W.Q_2552_b.toString() + line.substring(2);
                } else if (line.startsWith("SH")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, 2) + D_4024_W.Q_2552_b.toString() + line.substring(2);
                } else if (line.contains(":")) {
                    try {
                        int colonIndex = line.indexOf(":");
                        String label = line.substring(0, colonIndex);
                        String value = line.substring(colonIndex + 1).trim();
                        formattedLine = D_4024_W.w_1484_f.toString() + label + ":" + D_4024_W.Q_2552_b.toString() + " " + value;
                    }
                    catch (Exception e) {
                        formattedLine = line;
                    }
                }
            }
            formatted.add(formattedLine);
        }
        return formatted;
    }

    private List<String> J_1907_R(List<String> list) {
        ArrayList formatted = Lists.newArrayList();
        for (String line : list) {
            Object formattedLine;
            block41: {
                if (line == null || line.isEmpty()) {
                    formatted.add(line);
                    continue;
                }
                formattedLine = line;
                if (line.contains("Mem:") || line.contains("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u041e\u0417\u0423:")) {
                    try {
                        int percentIndex = line.indexOf("%");
                        if (percentIndex <= 0) break block41;
                        String memPart = line.substring(0, percentIndex);
                        String percentStr = memPart.substring(memPart.lastIndexOf(" ") + 1).trim();
                        try {
                            int percent = Integer.parseInt(percentStr);
                            D_4024_W memColor = percent < 50 ? D_4024_W.u_2550_I : (percent < 80 ? D_4024_W.Q_4569_t : D_4024_W.P_4830_p);
                            int memIndex = line.indexOf("Mem:");
                            if (memIndex < 0) {
                                memIndex = line.indexOf("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435 \u041e\u0417\u0423:");
                            }
                            if (memIndex >= 0) {
                                String before = line.substring(0, memIndex);
                                String after = line.substring(percentIndex);
                                formattedLine = before + D_4024_W.w_1484_f.toString() + "Mem:" + D_4024_W.Q_2552_b.toString() + " " + memColor.toString() + percentStr + "%" + D_4024_W.Q_2552_b.toString() + after;
                            }
                        }
                        catch (NumberFormatException ignored) {
                            formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                        }
                    }
                    catch (Exception ignored) {
                        formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                    }
                } else if (line.contains("Allocated:") || line.contains("\u0420\u0430\u0441\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u043e \u043f\u0430\u043c\u044f\u0442\u0438:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Java:") || line.contains("CPU:") || line.contains("Display:") || line.contains("GPU:") || line.contains("\u0426\u041f:") || line.contains("\u041e\u043a\u043d\u043e:") || line.contains("\u0413\u041f:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Targeted Block:") || line.contains("Targeted Fluid:") || line.contains("Targeted Entity") || line.contains("\u0426\u0435\u043b\u0435\u0432\u043e\u0439 \u0431\u043b\u043e\u043a:") || line.contains("\u0426\u0435\u043b\u0435\u0432\u0430\u044f \u0436\u0438\u0434\u043a\u043e\u0441\u0442\u044c:") || line.contains("\u0426\u0435\u043b\u0435\u0432\u0430\u044f \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u044c")) {
                    formattedLine = D_4024_W.Y_601_j.toString() + D_4024_W.M_588_G.toString() + line + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Entities:") || line.contains("\u0421\u0443\u0449\u043d\u043e\u0441\u0442\u0438:") || line.contains("\u0421\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("\u043f\u043e\u043a\u0430\u0437\u0430\u043d\u043e/\u0432\u0441\u0435\u0433\u043e") || line.contains("shown/total")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Monsters:") || line.contains("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Creatures:") || line.contains("\u0421\u0443\u0449\u0435\u0441\u0442\u0432:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Ambient:") || line.contains("\u041e\u043a\u0440\u0443\u0436\u0435\u043d\u0438\u0435:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Water Creatures:") || line.contains("\u0412\u043e\u0434\u043d\u044b\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0430:") || line.contains("\u0412\u043e\u0434\u043d\u044b\u0435:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Water Ambient:") || line.contains("\u0412\u043e\u0434\u043d\u043e\u0435 \u043e\u043a\u0440\u0443\u0436\u0435\u043d\u0438\u0435:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Misc:") || line.contains("\u041f\u0440\u043e\u0447\u0438\u0435:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains("Sounds:") || line.contains("\u0417\u0432\u0443\u043a\u0438:") || line.contains("\u0417\u0432\u0443\u043a\u0438 \u043e\u043a\u0440\u0443\u0436\u0435\u043d\u0438\u044f")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Ambient Sounds:") || line.contains("\u0417\u0432\u0443\u043a\u0438 \u043e\u043a\u0440\u0443\u0436\u0435\u043d\u0438\u044f:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + line.substring(line.indexOf(":") + 1);
                } else if (line.contains("Mood:") || line.contains("\u0422\u0440\u0438\u0433\u0433\u0435\u0440 \u043e\u043a\u0440\u0443\u0436\u0435\u043d\u0438\u044f:")) {
                    formattedLine = D_4024_W.w_1484_f.toString() + line.substring(0, line.indexOf(":")) + ":" + D_4024_W.Q_2552_b.toString() + " " + D_4024_W.M_182_A.toString() + line.substring(line.indexOf(":") + 1).trim() + D_4024_W.Q_2552_b.toString();
                } else if (line.contains(":")) {
                    try {
                        int colonIndex = line.indexOf(":");
                        String label = line.substring(0, colonIndex);
                        String value = line.substring(colonIndex + 1).trim();
                        formattedLine = D_4024_W.w_1484_f.toString() + label + ":" + D_4024_W.Q_2552_b.toString() + " " + value;
                    }
                    catch (Exception e) {
                        formattedLine = line;
                    }
                }
            }
            formatted.add(formattedLine);
        }
        return formatted;
    }
}



