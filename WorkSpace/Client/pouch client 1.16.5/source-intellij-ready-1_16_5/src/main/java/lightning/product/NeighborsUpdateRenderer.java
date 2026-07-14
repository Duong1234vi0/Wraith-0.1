/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Ordering
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import lightning.product.D_4792_h;
import lightning.product.I_4817_s;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.n_4915_r;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.z_883_p;

public class NeighborsUpdateRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private final Map<Long, Map<c_1514_x, Integer>> J_1907_R = Maps.newTreeMap((Comparator)Ordering.natural().reverse());

    NeighborsUpdateRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    public void n_1700_B(long worldTime, c_1514_x pos) {
        Map map = this.J_1907_R.computeIfAbsent(worldTime, p_241730_0_ -> Maps.newHashMap());
        int i = map.getOrDefault(pos, 0);
        map.put(pos, i + 1);
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        long i = this.n_1700_B.Y_601_j.X_933_l();
        int j = 200;
        double d0 = 0.0025;
        HashSet set = Sets.newHashSet();
        HashMap map = Maps.newHashMap();
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.C_2741_M());
        Iterator<Map.Entry<Long, Map<c_1514_x, Integer>>> iterator = this.J_1907_R.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Map<c_1514_x, Integer>> entry = iterator.next();
            Long olong = entry.getKey();
            Map<c_1514_x, Integer> map1 = entry.getValue();
            long k = i - olong;
            if (k > 200L) {
                iterator.remove();
                continue;
            }
            for (Map.Entry<c_1514_x, Integer> entry1 : map1.entrySet()) {
                c_1514_x blockpos = entry1.getKey();
                Integer integer = entry1.getValue();
                if (!set.add(blockpos)) continue;
                I_4817_s axisalignedbb = new I_4817_s(c_1514_x.ZERO).grow(0.002).shrink(0.0025 * (double)k).offset(blockpos.getX(), blockpos.getY(), blockpos.getZ()).offset(-camX, -camY, -camZ);
                z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ, 1.0f, 1.0f, 1.0f, 1.0f);
                map.put(blockpos, integer);
            }
        }
        for (Map.Entry entry2 : map.entrySet()) {
            c_1514_x blockpos1 = (c_1514_x)entry2.getKey();
            Integer integer1 = (Integer)entry2.getValue();
            n_4915_r.n_1700_B(String.valueOf(integer1), blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), -1);
        }
    }
}



