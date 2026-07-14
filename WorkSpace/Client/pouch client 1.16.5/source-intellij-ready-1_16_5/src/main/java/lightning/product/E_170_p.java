/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.h_384_L;
import lightning.product.k_4690_i;
import lightning.product.m_229_F;
import lightning.product.ClientBootstrap;
import lightning.product.u_530_F;
import lightning.product.Particular;

public final class E_170_p {
    private static final Map<Integer, ArrayDeque<Double>> n_1700_B = new HashMap<Integer, ArrayDeque<Double>>();

    private E_170_p() {
    }

    public static boolean n_1700_B() {
        try {
            Module module = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Particular.class);
            return module != null && module.w_1484_f();
        }
        catch (Exception ignored) {
            return false;
        }
    }

    public static void J_1907_R() {
        if (!E_170_p.n_1700_B()) {
            return;
        }
        if (MinecraftClient.A_4115_X().Y_601_j == null) {
            return;
        }
        E_170_p.G_564_y();
    }

    public static void R_4764_Y() {
        n_1700_B.clear();
    }

    public static void n_1700_B(N_4263_v entity) {
        if (!E_170_p.n_1700_B() || entity == null || !entity.O_508_d.Y_259_p || entity instanceof h_384_L) {
            return;
        }
        float baseY = u_530_F.R_4764_Y(entity.X_2960_b());
        boolean foundSurface = false;
        FluidState previous = Fluids.n_1700_B.w_1484_f();
        c_1514_x basePos = entity.b_2312_j();
        for (int i = 0; i < 5; ++i) {
            FluidState next = entity.O_508_d.getFluidState(basePos.up(i));
            if (previous.n_1700_B() == Fluids.R_4764_Y && next.R_4764_Y()) {
                baseY += (float)(i - 1);
                foundSurface = true;
                break;
            }
            previous = next;
        }
        if (!foundSurface || previous.R_4764_Y()) {
            return;
        }
        ArrayDeque<Double> speeds = n_1700_B.get(entity.j_276_v());
        double maxSpeed = Math.abs(entity.I_4348_c().R_4764_Y);
        if (speeds != null) {
            for (Double speed : speeds) {
                if (speed == null || !(speed > maxSpeed)) continue;
                maxSpeed = speed;
            }
        }
        entity.O_508_d.n_1700_B(m_229_F.R_4764_Y, entity.O_3598_v(), (double)(baseY + previous.G_564_y()), entity.l_2647_k(), (double)entity.C_415_h(), maxSpeed, 0.0);
    }

    public static void n_1700_B(b_4507_u world, double x, double y, double z, c_1514_x pos, FluidState fluidState) {
        if (!(E_170_p.n_1700_B() && world != null && world.Y_259_p && fluidState.n_1700_B(FluidTags.J_1907_R))) {
            return;
        }
        float waterY = (float)pos.getY() + fluidState.n_1700_B((BlockGetter)world, pos);
        if (y < (double)waterY) {
            world.n_1700_B(m_229_F.J_1907_R, x, (double)waterY, z, 0.0, 0.0, 0.0);
        }
    }

    private static void G_564_y() {
        k_4690_i world = MinecraftClient.A_4115_X().Y_601_j;
        if (world == null) {
            return;
        }
        HashSet<Integer> seen = new HashSet<Integer>();
        for (N_4263_v entity : world.J_1907_R()) {
            if (entity == null) continue;
            seen.add(entity.j_276_v());
            ArrayDeque deque = n_1700_B.computeIfAbsent(entity.j_276_v(), key -> new ArrayDeque());
            deque.offer(Math.abs(entity.I_4348_c().R_4764_Y));
            while (deque.size() > 4) {
                deque.poll();
            }
        }
        Iterator<Map.Entry<Integer, ArrayDeque<Double>>> iterator = n_1700_B.entrySet().iterator();
        while (iterator.hasNext()) {
            if (seen.contains(iterator.next().getKey())) continue;
            iterator.remove();
        }
    }
}



