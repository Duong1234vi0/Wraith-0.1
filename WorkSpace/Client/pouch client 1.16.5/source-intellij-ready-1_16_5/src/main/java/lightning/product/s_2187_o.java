/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.shorts.ShortList
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.C_3615_s;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.StructureFeature;
import lightning.product.LongArrayTag;
import lightning.product.H_1748_a;
import lightning.product.Fluids;
import lightning.product.SharedConstants;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.R_1900_x;
import lightning.product.DataLayer;
import lightning.product.T_2915_h;
import lightning.product.T_3975_o;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.ImposterProtoChunk;
import lightning.product.Y_1387_d;
import lightning.product.a_969_m;
import lightning.product.b_2085_h;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_2754_J;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.n_1254_X;
import lightning.product.BiomeSource;
import lightning.product.q_2896_o;
import lightning.product.r_3634_h;
import lightning.product.Fluid;
import lightning.product.t_5_h;
import lightning.product.ProtoTickList;
import lightning.product.x_4674_u;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_2187_o {
    private static final Logger n_1700_B = LogManager.getLogger();

    public static n_1254_X n_1700_B(e_3591_l worldIn, b_2085_h templateManagerIn, b_4946_z poiManager, Y_1387_d pos, U_2912_j compound) {
        ChunkAccess ichunk;
        z_1753_f chunkgenerator = worldIn.Y_259_p().t_148_a();
        BiomeSource biomeprovider = chunkgenerator.G_564_y();
        U_2912_j compoundnbt = compound.M_182_A("Level");
        Y_1387_d chunkpos = new Y_1387_d(compoundnbt.w_1484_f("xPos"), compoundnbt.w_1484_f("zPos"));
        if (!Objects.equals(pos, chunkpos)) {
            n_1700_B.error("Chunk file at {} is in the wrong location; relocating. (Expected {}, got {})", (Object)pos, (Object)pos, (Object)chunkpos);
        }
        c_1108_W biomecontainer = new c_1108_W(worldIn.t_1786_h().J_1907_R(V_3137_a.PlayerInfo), pos, biomeprovider, compoundnbt.R_4764_Y("Biomes", 11) ? compoundnbt.h_1847_R("Biomes") : null);
        r_3634_h upgradedata = compoundnbt.R_4764_Y("UpgradeData", 10) ? new r_3634_h(compoundnbt.M_182_A("UpgradeData")) : r_3634_h.n_1700_B;
        ProtoTickList<T_2915_h> chunkprimerticklist = new ProtoTickList<T_2915_h>(p_222652_0_ -> p_222652_0_ == null || p_222652_0_.multiplayerClientSuggestionProvider().v_4262_N(), pos, compoundnbt.G_564_y("ToBeTicked", 9));
        ProtoTickList<Fluid> chunkprimerticklist1 = new ProtoTickList<Fluid>(p_222646_0_ -> p_222646_0_ == null || p_222646_0_ == Fluids.n_1700_B, pos, compoundnbt.G_564_y("LiquidsToBeTicked", 9));
        boolean flag = compoundnbt.t_1786_h("isLightOn");
        q_2896_o listnbt = compoundnbt.G_564_y("Sections", 10);
        int i = 16;
        P_3550_Z[] achunksection = new P_3550_Z[16];
        boolean flag1 = worldIn.G_624_v().J_1907_R();
        C_3615_s abstractchunkprovider = worldIn.Y_259_p();
        R_1900_x worldlightmanager = ((ChunkSource)abstractchunkprovider).G_564_y();
        if (flag) {
            worldlightmanager.J_1907_R(pos, true);
        }
        for (int j = 0; j < listnbt.size(); ++j) {
            U_2912_j compoundnbt1 = listnbt.n_1700_B(j);
            byte k = compoundnbt1.u_1723_Y("Y");
            if (compoundnbt1.R_4764_Y("Palette", 9) && compoundnbt1.R_4764_Y("BlockStates", 12)) {
                P_3550_Z chunksection = new P_3550_Z(k << 4);
                chunksection.t_148_a().n_1700_B(compoundnbt1.G_564_y("Palette", 10), compoundnbt1.Q_4569_t("BlockStates"));
                chunksection.w_1484_f();
                if (!chunksection.R_4764_Y()) {
                    achunksection[k] = chunksection;
                }
                poiManager.n_1700_B(pos, chunksection);
            }
            if (!flag) continue;
            if (compoundnbt1.R_4764_Y("BlockLight", 7)) {
                worldlightmanager.n_1700_B(K_4719_o.J_1907_R, SectionPos.n_1700_B(pos, (int)k), new DataLayer(compoundnbt1.P_4830_p("BlockLight")), true);
            }
            if (!flag1 || !compoundnbt1.R_4764_Y("SkyLight", 7)) continue;
            worldlightmanager.n_1700_B(K_4719_o.n_1700_B, SectionPos.n_1700_B(pos, (int)k), new DataLayer(compoundnbt1.P_4830_p("SkyLight")), true);
        }
        long k1 = compoundnbt.t_148_a("InhabitedTime");
        ChunkStatus.G_564_y chunkstatus$type = s_2187_o.n_1700_B(compound);
        if (chunkstatus$type == ChunkStatus.G_564_y.J_1907_R) {
            TickList<T_2915_h> iticklist = compoundnbt.R_4764_Y("TileTicks", 9) ? x_4674_u.n_1700_B(compoundnbt.G_564_y("TileTicks", 10), V_3137_a.q_4610_l::J_1907_R, V_3137_a.q_4610_l::n_1700_B) : chunkprimerticklist;
            TickList<Fluid> iticklist1 = compoundnbt.R_4764_Y("LiquidTicks", 9) ? x_4674_u.n_1700_B(compoundnbt.G_564_y("LiquidTicks", 10), V_3137_a.G_624_v::J_1907_R, V_3137_a.G_624_v::n_1700_B) : chunkprimerticklist1;
            ichunk = new H_1748_a(worldIn.J_1907_R(), pos, biomecontainer, upgradedata, iticklist, iticklist1, k1, achunksection, p_222648_1_ -> s_2187_o.n_1700_B(compoundnbt, p_222648_1_));
        } else {
            n_1254_X chunkprimer = new n_1254_X(pos, upgradedata, achunksection, chunkprimerticklist, chunkprimerticklist1);
            chunkprimer.n_1700_B(biomecontainer);
            ichunk = chunkprimer;
            chunkprimer.setInhabitedTime(k1);
            chunkprimer.n_1700_B(ChunkStatus.n_1700_B(compoundnbt.M_588_G("Status")));
            if (chunkprimer.getStatus().J_1907_R(ChunkStatus.t_148_a)) {
                chunkprimer.n_1700_B(worldlightmanager);
            }
            if (!flag && chunkprimer.getStatus().J_1907_R(ChunkStatus.s_956_w)) {
                for (c_1514_x c_1514_x2 : c_1514_x.getAllInBoxMutable(pos.J_1907_R(), 0, pos.R_4764_Y(), pos.G_564_y(), 255, pos.P_1922_E())) {
                    if (ichunk.getBlockState(c_1514_x2).u_1723_Y() == 0) continue;
                    chunkprimer.n_1700_B(c_1514_x2);
                }
            }
        }
        ichunk.setLight(flag);
        U_2912_j compoundnbt3 = compoundnbt.M_182_A("Heightmaps");
        EnumSet<z_2963_s.n_1700_B> enumset = EnumSet.noneOf(z_2963_s.n_1700_B.class);
        for (z_2963_s.n_1700_B heightmap$type : ichunk.getStatus().w_1484_f()) {
            String s = heightmap$type.J_1907_R();
            if (compoundnbt3.R_4764_Y(s, 12)) {
                ichunk.setHeightmap(heightmap$type, compoundnbt3.Q_4569_t(s));
                continue;
            }
            enumset.add(heightmap$type);
        }
        z_2963_s.n_1700_B(ichunk, enumset);
        U_2912_j u_2912_j = compoundnbt.M_182_A("Structures");
        ichunk.setStructureStarts(s_2187_o.n_1700_B(templateManagerIn, u_2912_j, worldIn.n_1700_B()));
        ichunk.setStructureReferences(s_2187_o.n_1700_B(pos, u_2912_j));
        if (compoundnbt.t_1786_h("shouldSave")) {
            ichunk.setModified(true);
        }
        q_2896_o listnbt3 = compoundnbt.G_564_y("PostProcessing", 9);
        for (int l1 = 0; l1 < listnbt3.size(); ++l1) {
            q_2896_o listnbt1 = listnbt3.J_1907_R(l1);
            for (int l = 0; l < listnbt1.size(); ++l) {
                ichunk.J_1907_R(listnbt1.G_564_y(l), l1);
            }
        }
        if (chunkstatus$type == ChunkStatus.G_564_y.J_1907_R) {
            return new ImposterProtoChunk((H_1748_a)ichunk);
        }
        n_1254_X chunkprimer1 = (n_1254_X)ichunk;
        q_2896_o listnbt4 = compoundnbt.G_564_y("Entities", 10);
        for (int i2 = 0; i2 < listnbt4.size(); ++i2) {
            chunkprimer1.n_1700_B(listnbt4.n_1700_B(i2));
        }
        q_2896_o listnbt5 = compoundnbt.G_564_y("TileEntities", 10);
        for (int i1 = 0; i1 < listnbt5.size(); ++i1) {
            U_2912_j compoundnbt2 = listnbt5.n_1700_B(i1);
            ichunk.addTileEntity(compoundnbt2);
        }
        q_2896_o listnbt6 = compoundnbt.G_564_y("Lights", 9);
        for (int j2 = 0; j2 < listnbt6.size(); ++j2) {
            q_2896_o listnbt2 = listnbt6.J_1907_R(j2);
            for (int j1 = 0; j1 < listnbt2.size(); ++j1) {
                chunkprimer1.n_1700_B(listnbt2.G_564_y(j1), j2);
            }
        }
        U_2912_j compoundnbt5 = compoundnbt.M_182_A("CarvingMasks");
        for (String s1 : compoundnbt5.G_564_y()) {
            T_3975_o.n_1700_B generationstage$carving = T_3975_o.n_1700_B.valueOf(s1);
            chunkprimer1.n_1700_B(generationstage$carving, BitSet.valueOf(compoundnbt5.P_4830_p(s1)));
        }
        return chunkprimer1;
    }

    public static U_2912_j n_1700_B(e_3591_l worldIn, ChunkAccess chunkIn) {
        c_1108_W biomecontainer;
        Y_1387_d chunkpos = chunkIn.getPos();
        U_2912_j compoundnbt = new U_2912_j();
        U_2912_j compoundnbt1 = new U_2912_j();
        compoundnbt.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
        compoundnbt.n_1700_B("Level", compoundnbt1);
        compoundnbt1.J_1907_R("xPos", chunkpos.J_1907_R);
        compoundnbt1.J_1907_R("zPos", chunkpos.R_4764_Y);
        compoundnbt1.n_1700_B("LastUpdate", worldIn.X_933_l());
        compoundnbt1.n_1700_B("InhabitedTime", chunkIn.getInhabitedTime());
        compoundnbt1.n_1700_B("Status", chunkIn.getStatus().G_564_y());
        r_3634_h upgradedata = chunkIn.getUpgradeData();
        if (!upgradedata.n_1700_B()) {
            compoundnbt1.n_1700_B("UpgradeData", upgradedata.J_1907_R());
        }
        P_3550_Z[] achunksection = chunkIn.getSections();
        q_2896_o listnbt = new q_2896_o();
        e_2754_J worldlightmanager = worldIn.Y_259_p().R_4764_Y();
        boolean flag = chunkIn.hasLight();
        for (int i = -1; i < 17; ++i) {
            int j = i;
            P_3550_Z chunksection = Arrays.stream(achunksection).filter(p_222657_1_ -> p_222657_1_ != null && p_222657_1_.v_4262_N() >> 4 == j).findFirst().orElse(H_1748_a.EMPTY_SECTION);
            DataLayer nibblearray = worldlightmanager.n_1700_B(K_4719_o.J_1907_R).n_1700_B(SectionPos.n_1700_B(chunkpos, j));
            DataLayer nibblearray1 = worldlightmanager.n_1700_B(K_4719_o.n_1700_B).n_1700_B(SectionPos.n_1700_B(chunkpos, j));
            if (chunksection == H_1748_a.EMPTY_SECTION && nibblearray == null && nibblearray1 == null) continue;
            T_3975_o.n_1700_B[] compoundnbt2 = new U_2912_j();
            compoundnbt2.n_1700_B("Y", (byte)(j & 0xFF));
            if (chunksection != H_1748_a.EMPTY_SECTION) {
                chunksection.t_148_a().n_1700_B((U_2912_j)compoundnbt2, "Palette", "BlockStates");
            }
            if (nibblearray != null && !nibblearray.R_4764_Y()) {
                compoundnbt2.n_1700_B("BlockLight", nibblearray.n_1700_B());
            }
            if (nibblearray1 != null && !nibblearray1.R_4764_Y()) {
                compoundnbt2.n_1700_B("SkyLight", nibblearray1.n_1700_B());
            }
            listnbt.add(compoundnbt2);
        }
        compoundnbt1.n_1700_B("Sections", listnbt);
        if (flag) {
            compoundnbt1.n_1700_B("isLightOn", true);
        }
        if ((biomecontainer = chunkIn.getBiomes()) != null) {
            compoundnbt1.n_1700_B("Biomes", biomecontainer.n_1700_B());
        }
        q_2896_o listnbt1 = new q_2896_o();
        for (c_1514_x blockpos : chunkIn.getTileEntitiesPos()) {
            U_2912_j compoundnbt4 = chunkIn.getTileEntityNBT(blockpos);
            if (compoundnbt4 == null) continue;
            listnbt1.add(compoundnbt4);
        }
        compoundnbt1.n_1700_B("TileEntities", listnbt1);
        q_2896_o listnbt2 = new q_2896_o();
        if (chunkIn.getStatus().v_4262_N() == ChunkStatus.G_564_y.J_1907_R) {
            H_1748_a chunk = (H_1748_a)chunkIn;
            chunk.setHasEntities(false);
            for (int k = 0; k < chunk.getEntityLists().length; ++k) {
                for (N_4263_v entity : chunk.getEntityLists()[k]) {
                    U_2912_j compoundnbt3;
                    if (!entity.G_564_y(compoundnbt3 = new U_2912_j())) continue;
                    chunk.setHasEntities(true);
                    listnbt2.add(compoundnbt3);
                }
            }
        } else {
            n_1254_X chunkprimer = (n_1254_X)chunkIn;
            listnbt2.addAll(chunkprimer.R_4764_Y());
            compoundnbt1.n_1700_B("Lights", s_2187_o.n_1700_B(chunkprimer.n_1700_B()));
            U_2912_j compoundnbt5 = new U_2912_j();
            for (T_3975_o.n_1700_B generationstage$carving : T_3975_o.n_1700_B.values()) {
                BitSet bitset = chunkprimer.n_1700_B(generationstage$carving);
                if (bitset == null) continue;
                compoundnbt5.n_1700_B(generationstage$carving.toString(), bitset.toByteArray());
            }
            compoundnbt1.n_1700_B("CarvingMasks", compoundnbt5);
        }
        compoundnbt1.n_1700_B("Entities", listnbt2);
        TickList<T_2915_h> iticklist = chunkIn.getBlocksToBeTicked();
        if (iticklist instanceof ProtoTickList) {
            compoundnbt1.n_1700_B("ToBeTicked", ((ProtoTickList)iticklist).n_1700_B());
        } else if (iticklist instanceof x_4674_u) {
            compoundnbt1.n_1700_B("TileTicks", ((x_4674_u)iticklist).n_1700_B());
        } else {
            compoundnbt1.n_1700_B("TileTicks", worldIn.Q_2552_b().n_1700_B(chunkpos));
        }
        TickList<Fluid> iticklist1 = chunkIn.getFluidsToBeTicked();
        if (iticklist1 instanceof ProtoTickList) {
            compoundnbt1.n_1700_B("LiquidsToBeTicked", ((ProtoTickList)iticklist1).n_1700_B());
        } else if (iticklist1 instanceof x_4674_u) {
            compoundnbt1.n_1700_B("LiquidTicks", ((x_4674_u)iticklist1).n_1700_B());
        } else {
            compoundnbt1.n_1700_B("LiquidTicks", worldIn.C_2741_M().n_1700_B(chunkpos));
        }
        compoundnbt1.n_1700_B("PostProcessing", s_2187_o.n_1700_B(chunkIn.getPackedPositions()));
        U_2912_j compoundnbt6 = new U_2912_j();
        for (Map.Entry<z_2963_s.n_1700_B, z_2963_s> entry : chunkIn.getHeightmaps()) {
            if (!chunkIn.getStatus().w_1484_f().contains(entry.getKey())) continue;
            compoundnbt6.n_1700_B(entry.getKey().J_1907_R(), new LongArrayTag(entry.getValue().n_1700_B()));
        }
        compoundnbt1.n_1700_B("Heightmaps", compoundnbt6);
        compoundnbt1.n_1700_B("Structures", s_2187_o.n_1700_B(chunkpos, chunkIn.getStructureStarts(), chunkIn.getStructureReferences()));
        return compoundnbt;
    }

    public static ChunkStatus.G_564_y n_1700_B(@Nullable U_2912_j chunkNBT) {
        ChunkStatus chunkstatus;
        if (chunkNBT != null && (chunkstatus = ChunkStatus.n_1700_B(chunkNBT.M_182_A("Level").M_588_G("Status"))) != null) {
            return chunkstatus.v_4262_N();
        }
        return ChunkStatus.G_564_y.n_1700_B;
    }

    private static void n_1700_B(U_2912_j compound, H_1748_a chunkIn) {
        q_2896_o listnbt = compound.G_564_y("Entities", 10);
        b_4507_u world = chunkIn.getWorld();
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            t_5_h.n_1700_B(compoundnbt, world, (N_4263_v p_222655_1_) -> {
                chunkIn.addEntity((N_4263_v)p_222655_1_);
                return p_222655_1_;
            });
            chunkIn.setHasEntities(true);
        }
        q_2896_o listnbt1 = compound.G_564_y("TileEntities", 10);
        for (int j = 0; j < listnbt1.size(); ++j) {
            U_2912_j compoundnbt1 = listnbt1.n_1700_B(j);
            boolean flag = compoundnbt1.t_1786_h("keepPacked");
            if (flag) {
                chunkIn.addTileEntity(compoundnbt1);
                continue;
            }
            c_1514_x blockpos = new c_1514_x(compoundnbt1.w_1484_f("x"), compoundnbt1.w_1484_f("y"), compoundnbt1.w_1484_f("z"));
            i_2154_H tileentity = i_2154_H.J_1907_R(chunkIn.getBlockState(blockpos), compoundnbt1);
            if (tileentity == null) continue;
            chunkIn.addTileEntity(tileentity);
        }
    }

    private static U_2912_j n_1700_B(Y_1387_d pos, Map<StructureFeature<?>, StructureStart<?>> p_222649_1_, Map<StructureFeature<?>, LongSet> p_222649_2_) {
        U_2912_j compoundnbt = new U_2912_j();
        U_2912_j compoundnbt1 = new U_2912_j();
        for (Map.Entry<StructureFeature<?>, StructureStart<?>> entry : p_222649_1_.entrySet()) {
            compoundnbt1.n_1700_B(entry.getKey().v_4262_N(), entry.getValue().n_1700_B(pos.J_1907_R, pos.R_4764_Y));
        }
        compoundnbt.n_1700_B("Starts", compoundnbt1);
        U_2912_j compoundnbt2 = new U_2912_j();
        for (Map.Entry<StructureFeature<?>, LongSet> entry1 : p_222649_2_.entrySet()) {
            compoundnbt2.n_1700_B(entry1.getKey().v_4262_N(), new LongArrayTag(entry1.getValue()));
        }
        compoundnbt.n_1700_B("References", compoundnbt2);
        return compoundnbt;
    }

    private static Map<StructureFeature<?>, StructureStart<?>> n_1700_B(b_2085_h p_235967_0_, U_2912_j p_235967_1_, long p_235967_2_) {
        HashMap map = Maps.newHashMap();
        U_2912_j compoundnbt = p_235967_1_.M_182_A("Starts");
        for (String s : compoundnbt.G_564_y()) {
            String s1 = s.toLowerCase(Locale.ROOT);
            StructureFeature structure = (StructureFeature)StructureFeature.n_1700_B.get((Object)s1);
            if (structure == null) {
                n_1700_B.error("Unknown structure start: {}", (Object)s1);
                continue;
            }
            StructureStart<?> structurestart = StructureFeature.n_1700_B(p_235967_0_, compoundnbt.M_182_A(s), p_235967_2_);
            if (structurestart == null) continue;
            map.put(structure, structurestart);
        }
        return map;
    }

    private static Map<StructureFeature<?>, LongSet> n_1700_B(Y_1387_d p_227075_0_, U_2912_j p_227075_1_) {
        HashMap map = Maps.newHashMap();
        U_2912_j compoundnbt = p_227075_1_.M_182_A("References");
        for (String s : compoundnbt.G_564_y()) {
            map.put((StructureFeature)StructureFeature.n_1700_B.get((Object)s.toLowerCase(Locale.ROOT)), new LongOpenHashSet(Arrays.stream(compoundnbt.Q_4569_t(s)).filter(p_227074_2_ -> {
                Y_1387_d chunkpos = new Y_1387_d(p_227074_2_);
                if (chunkpos.n_1700_B(p_227075_0_) > 8) {
                    n_1700_B.warn("Found invalid structure reference [ {} @ {} ] for chunk {}.", (Object)s, (Object)chunkpos, (Object)p_227075_0_);
                    return false;
                }
                return true;
            }).toArray()));
        }
        return map;
    }

    public static q_2896_o n_1700_B(ShortList[] list) {
        q_2896_o listnbt = new q_2896_o();
        for (ShortList shortlist : list) {
            q_2896_o listnbt1 = new q_2896_o();
            if (shortlist != null) {
                for (Short oshort : shortlist) {
                    listnbt1.add(a_969_m.n_1700_B(oshort));
                }
            }
            listnbt.add(listnbt1);
        }
        return listnbt;
    }
}


