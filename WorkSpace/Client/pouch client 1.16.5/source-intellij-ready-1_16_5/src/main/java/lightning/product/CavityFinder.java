/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.AirBlock;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;
import mods.baritone.utils.IRenderer;

public class CavityFinder
extends Module {
    private volatile List<c_1514_x> v_4262_N = new ArrayList<c_1514_x>();
    private final NumberSetting radiusSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441", 30.0f, 10.0f, 128.0f, 1.0f);
    private final NumberSetting minBlokovSetting = new NumberSetting("\u041c\u0438\u043d. \u0431\u043b\u043e\u043a\u043e\u0432", 3.0f, 1.0f, 50.0f, 1.0f);
    private final NumberSetting maksBlokovSetting = new NumberSetting("\u041c\u0430\u043a\u0441. \u0431\u043b\u043e\u043a\u043e\u0432", 50.0f, 10.0f, 200.0f, 5.0f);
    private final NumberSetting minVysotaSetting = new NumberSetting("\u041c\u0438\u043d. \u0432\u044b\u0441\u043e\u0442\u0430", 2.0f, 1.0f, 30.0f, 1.0f);
    private final NumberSetting minShirinaSetting = new NumberSetting("\u041c\u0438\u043d. \u0448\u0438\u0440\u0438\u043d\u0430", 2.0f, 1.0f, 30.0f, 1.0f);
    private volatile boolean P_4830_p = false;
    private final ExecutorService h_1847_R = Executors.newSingleThreadExecutor();

    public CavityFinder() {
        super("CavityFinder", ModuleCategory.R_4764_Y);
        this.addSettings(this.radiusSetting, this.minBlokovSetting, this.maksBlokovSetting, this.minVysotaSetting, this.minShirinaSetting);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (CavityFinder.c_3005_b.Y_259_p == null || CavityFinder.c_3005_b.Y_601_j == null) {
            this.J_1907_R(false);
            return;
        }
        v_1900_v.n_1700_B("\u0421\u043a\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435... \u041b\u0443\u0447\u0448\u0435 \u0432\u0441\u0435\u0433\u043e \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442 \u0432 \u0430\u0434\u0443 \u0438\u0437-\u0437\u0430 \u043e\u0441\u043e\u0431\u0435\u043d\u043d\u043e\u0441\u0442\u0435\u0439 AntiXray", new Object[0]);
        this.v_4262_N.clear();
        this.P_4830_p = true;
        this.h_1847_R.submit(this::h_1847_R);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.P_4830_p = false;
        this.v_4262_N.clear();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (CavityFinder.c_3005_b.Y_601_j == null || CavityFinder.c_3005_b.Y_259_p == null) {
            return;
        }
        ArrayList<c_1514_x> positions = new ArrayList<c_1514_x>(this.v_4262_N);
        if (positions.isEmpty()) {
            return;
        }
        g_221_o stack = new g_221_o();
        IRenderer.startLines(Color.WHITE, 1.0f, 2.0f, true);
        for (c_1514_x pos : positions) {
            IRenderer.emitAABB(stack, new I_4817_s(pos, pos.add(1, 1, 1)));
        }
        IRenderer.endLines(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void h_1847_R() {
        try {
            HashSet<c_1514_x> visited = new HashSet<c_1514_x>();
            ArrayList<ArrayList<c_1514_x>> holes = new ArrayList<ArrayList<c_1514_x>>();
            int rangeVal = ((Float)this.radiusSetting.getValue()).intValue();
            int startX = (int)Math.floor(CavityFinder.c_3005_b.Y_259_p.O_3598_v() - (double)rangeVal);
            int endX = (int)Math.ceil(CavityFinder.c_3005_b.Y_259_p.O_3598_v() + (double)rangeVal);
            int startY = 1;
            int endY = 255;
            int startZ = (int)Math.floor(CavityFinder.c_3005_b.Y_259_p.l_2647_k() - (double)rangeVal);
            int endZ = (int)Math.ceil(CavityFinder.c_3005_b.Y_259_p.l_2647_k() + (double)rangeVal);
            for (int x = startX; x <= endX; ++x) {
                for (int y = startY; y <= endY; ++y) {
                    for (int z = startZ; z <= endZ; ++z) {
                        if (!this.w_1484_f() || !this.P_4830_p) {
                            return;
                        }
                        c_1514_x start = new c_1514_x(x, y, z);
                        if (visited.contains(start) || !this.n_1700_B(start)) continue;
                        ArrayList<c_1514_x> airCluster = new ArrayList<c_1514_x>();
                        this.n_1700_B(start, airCluster, visited, startX, endX, startY, endY, startZ, endZ);
                        int minYPos = Integer.MAX_VALUE;
                        int maxYPos = Integer.MIN_VALUE;
                        int minXPos = Integer.MAX_VALUE;
                        int maxXPos = Integer.MIN_VALUE;
                        int minZPos = Integer.MAX_VALUE;
                        int maxZPos = Integer.MIN_VALUE;
                        for (c_1514_x pos : airCluster) {
                            minXPos = Math.min(minXPos, pos.getX());
                            maxXPos = Math.max(maxXPos, pos.getX());
                            minYPos = Math.min(minYPos, pos.getY());
                            maxYPos = Math.max(maxYPos, pos.getY());
                            minZPos = Math.min(minZPos, pos.getZ());
                            maxZPos = Math.max(maxZPos, pos.getZ());
                        }
                        int height = maxYPos - minYPos + 1;
                        int width = Math.max(maxXPos - minXPos + 1, maxZPos - minZPos + 1);
                        if (!((float)height >= ((Float)this.minVysotaSetting.getValue()).floatValue()) || !((float)width >= ((Float)this.minShirinaSetting.getValue()).floatValue()) || !((float)airCluster.size() >= ((Float)this.minBlokovSetting.getValue()).floatValue()) || !((float)airCluster.size() <= ((Float)this.maksBlokovSetting.getValue()).floatValue()) || !this.J_1907_R(airCluster)) continue;
                        holes.add(airCluster);
                    }
                }
            }
            this.v_4262_N = holes.stream().flatMap(Collection::stream).collect(Collectors.toList());
            if (CavityFinder.c_3005_b.Y_259_p != null) {
                v_1900_v.n_1700_B("\u041d\u0430\u0439\u0434\u0435\u043d\u043e " + holes.size() + " \u0441\u043a\u0440\u044b\u0442\u044b\u0445 \u043f\u043e\u043b\u043e\u0441\u0442\u0435\u0439 (" + this.v_4262_N.size() + " \u0431\u043b\u043e\u043a\u043e\u0432)", new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.P_4830_p = false;
        }
    }

    private void n_1700_B(c_1514_x pos, List<c_1514_x> result, Set<c_1514_x> visited, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        LinkedList<c_1514_x> queue = new LinkedList<c_1514_x>();
        queue.add(pos);
        while (!queue.isEmpty()) {
            c_1514_x current = (c_1514_x)queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);
            result.add(current);
            if ((float)result.size() > ((Float)this.maksBlokovSetting.getValue()).floatValue()) break;
            for (b_257_Y dir : b_257_Y.values()) {
                c_1514_x neighbor = current.offset(dir);
                if (visited.contains(neighbor) || neighbor.getX() < minX || neighbor.getX() > maxX || neighbor.getY() < minY || neighbor.getY() > maxY || neighbor.getZ() < minZ || neighbor.getZ() > maxZ || !this.n_1700_B(neighbor)) continue;
                queue.add(neighbor);
            }
        }
    }

    private boolean J_1907_R(List<c_1514_x> airBlocks) {
        HashSet<c_1514_x> set = new HashSet<c_1514_x>(airBlocks);
        for (c_1514_x pos : airBlocks) {
            for (b_257_Y dir : b_257_Y.values()) {
                c_1514_x neighbor = pos.offset(dir);
                if (set.contains(neighbor) || !this.n_1700_B(neighbor)) continue;
                return false;
            }
        }
        return true;
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (CavityFinder.c_3005_b.Y_601_j == null) {
            return false;
        }
        try {
            return CavityFinder.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() instanceof AirBlock;
        }
        catch (Exception e) {
            return false;
        }
    }
}



