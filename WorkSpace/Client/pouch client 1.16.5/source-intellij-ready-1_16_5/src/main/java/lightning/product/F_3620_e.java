/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.G_3165_y;
import lightning.product.J_2020_G;
import lightning.product.N_3369_p;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.Z_3903_F;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.d_830_N;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.l_4118_l;
import lightning.product.q_2896_o;
import lightning.product.LevelAccessor;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_740_d;
import lightning.product.SavedData;
import lightning.product.z_555_N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F_3620_e
extends SavedData {
    private static final Logger u_2550_I = LogManager.getLogger();
    public int n_1700_B;
    public int J_1907_R;
    public f_2392_k<b_4507_u> R_4764_Y;
    public boolean G_564_y;
    public boolean P_1922_E;
    public byte u_1723_Y;
    public byte[] v_4262_N = new byte[16384];
    public boolean w_1484_f;
    public final List<n_1700_B> t_148_a = Lists.newArrayList();
    private final Map<a_3913_L, n_1700_B> M_588_G = Maps.newHashMap();
    private final Map<String, z_555_N> P_4830_p = Maps.newHashMap();
    public final Map<String, J_2020_G> s_956_w = Maps.newLinkedHashMap();
    private final Map<String, d_830_N> h_1847_R = Maps.newHashMap();

    public F_3620_e(String mapname) {
        super(mapname);
    }

    public void n_1700_B(int x, int z, int scale, boolean trackingPosition, boolean unlimitedTracking, f_2392_k<b_4507_u> dimension) {
        this.u_1723_Y = (byte)scale;
        this.n_1700_B(x, (double)z, (int)this.u_1723_Y);
        this.R_4764_Y = dimension;
        this.G_564_y = trackingPosition;
        this.P_1922_E = unlimitedTracking;
        this.R_4764_Y();
    }

    public void n_1700_B(double x, double z, int mapScale) {
        int i = 128 * (1 << mapScale);
        int j = u_530_F.R_4764_Y((x + 64.0) / (double)i);
        int k = u_530_F.R_4764_Y((z + 64.0) / (double)i);
        this.n_1700_B = j * i + i / 2 - 64;
        this.J_1907_R = k * i + i / 2 - 64;
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        this.R_4764_Y = (f_2392_k)Z_3903_F.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)nbt.R_4764_Y("dimension"))).resultOrPartial(arg_0 -> ((Logger)u_2550_I).error(arg_0)).orElseThrow(() -> new IllegalArgumentException("Invalid map dimension: " + String.valueOf(nbt.R_4764_Y("dimension"))));
        this.n_1700_B = nbt.w_1484_f("xCenter");
        this.J_1907_R = nbt.w_1484_f("zCenter");
        this.u_1723_Y = (byte)u_530_F.n_1700_B((int)nbt.u_1723_Y("scale"), 0, 4);
        this.G_564_y = !nbt.R_4764_Y("trackingPosition", 1) || nbt.t_1786_h("trackingPosition");
        this.P_1922_E = nbt.t_1786_h("unlimitedTracking");
        this.w_1484_f = nbt.t_1786_h("locked");
        this.v_4262_N = nbt.P_4830_p("colors");
        if (this.v_4262_N.length != 16384) {
            this.v_4262_N = new byte[16384];
        }
        q_2896_o listnbt = nbt.G_564_y("banners", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            z_555_N mapbanner = z_555_N.n_1700_B(listnbt.n_1700_B(i));
            this.P_4830_p.put(mapbanner.P_1922_E(), mapbanner);
            this.n_1700_B(mapbanner.J_1907_R(), null, mapbanner.P_1922_E(), mapbanner.n_1700_B().getX(), mapbanner.n_1700_B().getZ(), 180.0, mapbanner.R_4764_Y());
        }
        q_2896_o listnbt1 = nbt.G_564_y("frames", 10);
        for (int j = 0; j < listnbt1.size(); ++j) {
            d_830_N mapframe = d_830_N.n_1700_B(listnbt1.n_1700_B(j));
            this.h_1847_R.put(mapframe.P_1922_E(), mapframe);
            this.n_1700_B(J_2020_G.n_1700_B.J_1907_R, null, "frame-" + mapframe.G_564_y(), mapframe.J_1907_R().getX(), mapframe.J_1907_R().getZ(), mapframe.R_4764_Y(), null);
        }
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        g_2336_b.n_1700_B.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.R_4764_Y.n_1700_B()).resultOrPartial(arg_0 -> ((Logger)u_2550_I).error(arg_0)).ifPresent(dimNBT -> compound.n_1700_B("dimension", (Tag)dimNBT));
        compound.J_1907_R("xCenter", this.n_1700_B);
        compound.J_1907_R("zCenter", this.J_1907_R);
        compound.n_1700_B("scale", this.u_1723_Y);
        compound.n_1700_B("colors", this.v_4262_N);
        compound.n_1700_B("trackingPosition", this.G_564_y);
        compound.n_1700_B("unlimitedTracking", this.P_1922_E);
        compound.n_1700_B("locked", this.w_1484_f);
        q_2896_o listnbt = new q_2896_o();
        for (z_555_N mapbanner : this.P_4830_p.values()) {
            listnbt.add(mapbanner.G_564_y());
        }
        compound.n_1700_B("banners", listnbt);
        q_2896_o listnbt1 = new q_2896_o();
        for (d_830_N mapframe : this.h_1847_R.values()) {
            listnbt1.add(mapframe.n_1700_B());
        }
        compound.n_1700_B("frames", listnbt1);
        return compound;
    }

    public void n_1700_B(F_3620_e mapDataIn) {
        this.w_1484_f = true;
        this.n_1700_B = mapDataIn.n_1700_B;
        this.J_1907_R = mapDataIn.J_1907_R;
        this.P_4830_p.putAll(mapDataIn.P_4830_p);
        this.s_956_w.putAll(mapDataIn.s_956_w);
        System.arraycopy(mapDataIn.v_4262_N, 0, this.v_4262_N, 0, mapDataIn.v_4262_N.length);
        this.R_4764_Y();
    }

    public void n_1700_B(a_3913_L player, Z_1993_T mapStack) {
        U_2912_j compoundnbt;
        if (!this.M_588_G.containsKey(player)) {
            n_1700_B mapdata$mapinfo = new n_1700_B(player);
            this.M_588_G.put(player, mapdata$mapinfo);
            this.t_148_a.add(mapdata$mapinfo);
        }
        if (!player.l_1268_F.w_1484_f(mapStack)) {
            this.s_956_w.remove(player.O_1309_Q().getString());
        }
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            n_1700_B mapdata$mapinfo1 = this.t_148_a.get(i);
            String s = mapdata$mapinfo1.n_1700_B.O_1309_Q().getString();
            if (!mapdata$mapinfo1.n_1700_B.t_4219_U && (mapdata$mapinfo1.n_1700_B.l_1268_F.w_1484_f(mapStack) || mapStack.q_2307_F())) {
                if (mapStack.q_2307_F() || mapdata$mapinfo1.n_1700_B.O_508_d.g_2268_R() != this.R_4764_Y || !this.G_564_y) continue;
                this.n_1700_B(J_2020_G.n_1700_B.n_1700_B, mapdata$mapinfo1.n_1700_B.O_508_d, s, mapdata$mapinfo1.n_1700_B.O_3598_v(), mapdata$mapinfo1.n_1700_B.l_2647_k(), mapdata$mapinfo1.n_1700_B.p_178_J, null);
                continue;
            }
            this.M_588_G.remove(mapdata$mapinfo1.n_1700_B);
            this.t_148_a.remove(mapdata$mapinfo1);
            this.s_956_w.remove(s);
        }
        if (mapStack.q_2307_F() && this.G_564_y) {
            y_740_d itemframeentity = mapStack.Z_875_P();
            c_1514_x blockpos = itemframeentity.u_2550_I();
            d_830_N mapframe1 = this.h_1847_R.get(d_830_N.n_1700_B(blockpos));
            if (mapframe1 != null && itemframeentity.j_276_v() != mapframe1.G_564_y() && this.h_1847_R.containsKey(mapframe1.P_1922_E())) {
                this.s_956_w.remove("frame-" + mapframe1.G_564_y());
            }
            d_830_N mapframe = new d_830_N(blockpos, itemframeentity.o_2767_H().G_564_y() * 90, itemframeentity.j_276_v());
            this.n_1700_B(J_2020_G.n_1700_B.J_1907_R, player.O_508_d, "frame-" + itemframeentity.j_276_v(), blockpos.getX(), blockpos.getZ(), itemframeentity.o_2767_H().G_564_y() * 90, null);
            this.h_1847_R.put(mapframe.P_1922_E(), mapframe);
        }
        if ((compoundnbt = mapStack.Q_4569_t()) != null && compoundnbt.R_4764_Y("Decorations", 9)) {
            q_2896_o listnbt = compoundnbt.G_564_y("Decorations", 10);
            for (int j = 0; j < listnbt.size(); ++j) {
                U_2912_j compoundnbt1 = listnbt.n_1700_B(j);
                if (this.s_956_w.containsKey(compoundnbt1.M_588_G("id"))) continue;
                this.n_1700_B(J_2020_G.n_1700_B.n_1700_B(compoundnbt1.u_1723_Y("type")), player.O_508_d, compoundnbt1.M_588_G("id"), compoundnbt1.u_2550_I("x"), compoundnbt1.u_2550_I("z"), compoundnbt1.u_2550_I("rot"), null);
            }
        }
    }

    public static void n_1700_B(Z_1993_T map, c_1514_x target, String decorationName, J_2020_G.n_1700_B type) {
        q_2896_o listnbt;
        if (map.h_1847_R() && map.Q_4569_t().R_4764_Y("Decorations", 9)) {
            listnbt = map.Q_4569_t().G_564_y("Decorations", 10);
        } else {
            listnbt = new q_2896_o();
            map.n_1700_B("Decorations", listnbt);
        }
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("type", type.n_1700_B());
        compoundnbt.n_1700_B("id", decorationName);
        compoundnbt.n_1700_B("x", (double)target.getX());
        compoundnbt.n_1700_B("z", (double)target.getZ());
        compoundnbt.n_1700_B("rot", 180.0);
        listnbt.add(compoundnbt);
        if (type.R_4764_Y()) {
            U_2912_j compoundnbt1 = map.n_1700_B("display");
            compoundnbt1.J_1907_R("MapColor", type.G_564_y());
        }
    }

    private void n_1700_B(J_2020_G.n_1700_B type, @Nullable LevelAccessor worldIn, String decorationName, double worldX, double worldZ, double rotationIn, @Nullable x_282_a name) {
        byte b2;
        int i = 1 << this.u_1723_Y;
        float f = (float)(worldX - (double)this.n_1700_B) / (float)i;
        float f1 = (float)(worldZ - (double)this.J_1907_R) / (float)i;
        byte b0 = (byte)((double)(f * 2.0f) + 0.5);
        byte b1 = (byte)((double)(f1 * 2.0f) + 0.5);
        int j = 63;
        if (f >= -63.0f && f1 >= -63.0f && f <= 63.0f && f1 <= 63.0f) {
            b2 = (byte)((rotationIn += rotationIn < 0.0 ? -8.0 : 8.0) * 16.0 / 360.0);
            if (this.R_4764_Y == b_4507_u.v_4262_N && worldIn != null) {
                int l = (int)(worldIn.k_2293_S().u_1723_Y() / 10L);
                b2 = (byte)(l * l * 34187121 + l * 121 >> 15 & 0xF);
            }
        } else {
            if (type != J_2020_G.n_1700_B.n_1700_B) {
                this.s_956_w.remove(decorationName);
                return;
            }
            int k = 320;
            if (Math.abs(f) < 320.0f && Math.abs(f1) < 320.0f) {
                type = J_2020_G.n_1700_B.v_4262_N;
            } else {
                if (!this.P_1922_E) {
                    this.s_956_w.remove(decorationName);
                    return;
                }
                type = J_2020_G.n_1700_B.w_1484_f;
            }
            b2 = 0;
            if (f <= -63.0f) {
                b0 = -128;
            }
            if (f1 <= -63.0f) {
                b1 = -128;
            }
            if (f >= 63.0f) {
                b0 = 127;
            }
            if (f1 >= 63.0f) {
                b1 = 127;
            }
        }
        this.s_956_w.put(decorationName, new J_2020_G(type, b0, b1, b2, name));
    }

    @Nullable
    public Packet<?> n_1700_B(Z_1993_T mapStack, BlockGetter worldIn, a_3913_L player) {
        n_1700_B mapdata$mapinfo = this.M_588_G.get(player);
        return mapdata$mapinfo == null ? null : mapdata$mapinfo.n_1700_B(mapStack);
    }

    public void n_1700_B(int x, int y) {
        this.R_4764_Y();
        for (n_1700_B mapdata$mapinfo : this.t_148_a) {
            mapdata$mapinfo.n_1700_B(x, y);
        }
    }

    public n_1700_B n_1700_B(a_3913_L player) {
        n_1700_B mapdata$mapinfo = this.M_588_G.get(player);
        if (mapdata$mapinfo == null) {
            mapdata$mapinfo = new n_1700_B(player);
            this.M_588_G.put(player, mapdata$mapinfo);
            this.t_148_a.add(mapdata$mapinfo);
        }
        return mapdata$mapinfo;
    }

    public void n_1700_B(LevelAccessor world, c_1514_x pos) {
        double d0 = (double)pos.getX() + 0.5;
        double d1 = (double)pos.getZ() + 0.5;
        int i = 1 << this.u_1723_Y;
        double d2 = (d0 - (double)this.n_1700_B) / (double)i;
        double d3 = (d1 - (double)this.J_1907_R) / (double)i;
        int j = 63;
        boolean flag = false;
        if (d2 >= -63.0 && d3 >= -63.0 && d2 <= 63.0 && d3 <= 63.0) {
            z_555_N mapbanner = z_555_N.n_1700_B(world, pos);
            if (mapbanner == null) {
                return;
            }
            boolean flag1 = true;
            if (this.P_4830_p.containsKey(mapbanner.P_1922_E()) && this.P_4830_p.get(mapbanner.P_1922_E()).equals(mapbanner)) {
                this.P_4830_p.remove(mapbanner.P_1922_E());
                this.s_956_w.remove(mapbanner.P_1922_E());
                flag1 = false;
                flag = true;
            }
            if (flag1) {
                this.P_4830_p.put(mapbanner.P_1922_E(), mapbanner);
                this.n_1700_B(mapbanner.J_1907_R(), world, mapbanner.P_1922_E(), d0, d1, 180.0, mapbanner.R_4764_Y());
                flag = true;
            }
            if (flag) {
                this.R_4764_Y();
            }
        }
    }

    public void n_1700_B(BlockGetter reader, int x, int z) {
        Iterator<z_555_N> iterator = this.P_4830_p.values().iterator();
        while (iterator.hasNext()) {
            z_555_N mapbanner1;
            z_555_N mapbanner = iterator.next();
            if (mapbanner.n_1700_B().getX() != x || mapbanner.n_1700_B().getZ() != z || mapbanner.equals(mapbanner1 = z_555_N.n_1700_B(reader, mapbanner.n_1700_B()))) continue;
            iterator.remove();
            this.s_956_w.remove(mapbanner.P_1922_E());
        }
    }

    public void n_1700_B(c_1514_x pos, int entityIdIn) {
        this.s_956_w.remove("frame-" + entityIdIn);
        this.h_1847_R.remove(d_830_N.n_1700_B(pos));
    }

    public class n_1700_B {
        public final a_3913_L n_1700_B;
        private boolean G_564_y = true;
        private int P_1922_E;
        private int u_1723_Y;
        private int v_4262_N = 127;
        private int w_1484_f = 127;
        private int t_148_a;
        public int J_1907_R;

        public n_1700_B(a_3913_L player) {
            this.n_1700_B = player;
        }

        @Nullable
        public Packet<?> n_1700_B(Z_1993_T stack) {
            if (this.G_564_y) {
                this.G_564_y = false;
                return new N_3369_p(G_3165_y.G_564_y(stack), F_3620_e.this.u_1723_Y, F_3620_e.this.G_564_y, F_3620_e.this.w_1484_f, F_3620_e.this.s_956_w.values(), F_3620_e.this.v_4262_N, this.P_1922_E, this.u_1723_Y, this.v_4262_N + 1 - this.P_1922_E, this.w_1484_f + 1 - this.u_1723_Y);
            }
            return this.t_148_a++ % 5 == 0 ? new N_3369_p(G_3165_y.G_564_y(stack), F_3620_e.this.u_1723_Y, F_3620_e.this.G_564_y, F_3620_e.this.w_1484_f, F_3620_e.this.s_956_w.values(), F_3620_e.this.v_4262_N, 0, 0, 0, 0) : null;
        }

        public void n_1700_B(int x, int y) {
            if (this.G_564_y) {
                this.P_1922_E = Math.min(this.P_1922_E, x);
                this.u_1723_Y = Math.min(this.u_1723_Y, y);
                this.v_4262_N = Math.max(this.v_4262_N, x);
                this.w_1484_f = Math.max(this.w_1484_f, y);
            } else {
                this.G_564_y = true;
                this.P_1922_E = x;
                this.u_1723_Y = y;
                this.v_4262_N = x;
                this.w_1484_f = y;
            }
        }
    }
}


