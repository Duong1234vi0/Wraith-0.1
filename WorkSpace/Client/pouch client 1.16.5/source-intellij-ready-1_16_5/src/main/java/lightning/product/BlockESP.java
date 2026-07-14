/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lightning.product.H_1748_a;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_3550_Z;
import lightning.product.Q_584_o;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Module;
import lightning.product.Y_1387_d;
import lightning.product.Y_1740_V;
import lightning.product.Z_3822_q;
import lightning.product.a_3742_W;
import lightning.product.b_3528_u;
import lightning.product.c_1514_x;
import lightning.product.MinecartChest;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.SpawnerBlockEntity;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.Minecart;
import lightning.product.t_5_h;
import lightning.product.v_2826_q;
import lightning.product.ModuleCategory;
import lightning.product.z_3000_g;
import mods.baritone.utils.IRenderer;
import org.joml.Vector2f;

public class BlockESP
extends Module {
    private final Map<Integer, Set<c_1514_x>> v_4262_N = new ConcurrentHashMap<Integer, Set<c_1514_x>>();
    private final Set<Y_1387_d> w_1484_f = ConcurrentHashMap.newKeySet();
    private final ExecutorService t_148_a = Executors.newSingleThreadExecutor();
    private final AtomicBoolean s_956_w = new AtomicBoolean(false);
    private int u_2550_I = 0;
    private long M_588_G = 0L;
    private long P_4830_p = 0L;
    private static final long h_1847_R = 500L;
    private static final long Q_4569_t = 5000L;
    private static final int M_182_A = 7;
    private Map<T_2915_h, Integer> t_1786_h = new HashMap<T_2915_h, Integer>();
    private final List<c_1514_x> multiplayerClientSuggestionProvider = new ArrayList<c_1514_x>();

    public BlockESP() {
        super("Block ESP", ModuleCategory.R_4764_Y);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.v_4262_N.clear();
        this.w_1484_f.clear();
        this.M_588_G = 0L;
        this.P_4830_p = 0L;
        this.u_2550_I = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.v_4262_N.clear();
        this.w_1484_f.clear();
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R eventRender3D) {
        long now;
        if (BlockESP.c_3005_b.Y_601_j == null || BlockESP.c_3005_b.Y_259_p == null) {
            return;
        }
        g_221_o stack = new g_221_o();
        this.n_1700_B(stack);
        z_3000_g manager = ClientBootstrap.Y_601_j().u_2550_I();
        List<z_3000_g.n_1700_B> entries = manager.M_588_G();
        if (entries.isEmpty()) {
            return;
        }
        int listHash = this.J_1907_R(entries);
        if (listHash != this.u_2550_I) {
            this.u_2550_I = listHash;
            this.R_4764_Y(entries);
            this.v_4262_N.clear();
            this.w_1484_f.clear();
        }
        if ((now = System.currentTimeMillis()) - this.P_4830_p > 5000L) {
            this.P_4830_p = now;
            this.w_1484_f.clear();
        }
        if (now - this.M_588_G > 500L && !this.s_956_w.get()) {
            this.M_588_G = now;
            this.Q_4569_t();
        }
        this.J_1907_R(stack);
    }

    private void n_1700_B(g_221_o stack) {
        for (N_4263_v entity : BlockESP.c_3005_b.Y_601_j.J_1907_R()) {
            if (entity instanceof MinecartChest) {
                this.n_1700_B(stack, entity, new Color(255, 215, 0));
                continue;
            }
            if (!(entity instanceof Minecart)) continue;
            this.n_1700_B(stack, entity, new Color(128, 128, 128));
        }
    }

    private void n_1700_B(g_221_o stack, N_4263_v entity, Color color) {
        try {
            I_4817_s box = entity.i_601_W();
            IRenderer.startLines(color, 2.5f, true);
            IRenderer.emitAABB(stack, box.grow(0.002));
            IRenderer.endLines(true);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private int J_1907_R(List<z_3000_g.n_1700_B> entries) {
        int hash = 1;
        for (z_3000_g.n_1700_B e : entries) {
            String n = e.n_1700_B();
            hash = 31 * hash + (n == null ? 0 : n.trim().toLowerCase(Locale.ROOT).hashCode());
            hash = 31 * hash + e.J_1907_R();
        }
        return hash;
    }

    private void R_4764_Y(List<z_3000_g.n_1700_B> entries) {
        this.t_1786_h.clear();
        for (z_3000_g.n_1700_B entry : entries) {
            Object s = entry.n_1700_B().trim().toLowerCase(Locale.ROOT);
            if (!((String)s).contains(":")) {
                s = "minecraft:" + (String)s;
            }
            g_2336_b rl = new g_2336_b((String)s);
            V_3137_a.q_4610_l.J_1907_R(rl).ifPresent(b -> this.t_1786_h.put((T_2915_h)b, entry.J_1907_R()));
        }
    }

    private void Q_4569_t() {
        if (this.t_1786_h.isEmpty()) {
            return;
        }
        this.s_956_w.set(true);
        this.t_148_a.submit(() -> {
            try {
                this.M_182_A();
            }
            finally {
                this.s_956_w.set(false);
            }
        });
    }

    private void M_182_A() {
        if (BlockESP.c_3005_b.Y_601_j == null || BlockESP.c_3005_b.Y_259_p == null) {
            return;
        }
        c_1514_x playerPos = BlockESP.c_3005_b.Y_259_p.b_2312_j();
        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;
        HashSet<Y_1387_d> validChunks = new HashSet<Y_1387_d>();
        for (int cx = -7; cx <= 7; ++cx) {
            for (int cz = -7; cz <= 7; ++cz) {
                validChunks.add(new Y_1387_d(playerChunkX + cx, playerChunkZ + cz));
            }
        }
        this.w_1484_f.removeIf(cp -> !validChunks.contains(cp));
        for (Set<c_1514_x> positions : this.v_4262_N.values()) {
            positions.removeIf(pos -> {
                Y_1387_d cp = new Y_1387_d((c_1514_x)pos);
                return !validChunks.contains(cp);
            });
        }
        for (Y_1387_d cp2 : validChunks) {
            if (this.w_1484_f.contains(cp2)) continue;
            if (BlockESP.c_3005_b.Y_601_j == null) break;
            try {
                this.n_1700_B(cp2);
                this.w_1484_f.add(cp2);
            }
            catch (Exception exception) {}
        }
    }

    private void n_1700_B(Y_1387_d chunkPos) {
        H_1748_a chunk;
        if (BlockESP.c_3005_b.Y_601_j == null) {
            return;
        }
        try {
            chunk = BlockESP.c_3005_b.Y_601_j.u_1723_Y(chunkPos.J_1907_R, chunkPos.R_4764_Y);
        }
        catch (Exception e) {
            return;
        }
        if (chunk == null || chunk.isEmpty()) {
            return;
        }
        P_3550_Z[] sections = chunk.getSections();
        int baseX = chunkPos.J_1907_R << 4;
        int baseZ = chunkPos.R_4764_Y << 4;
        for (int sectionY = 0; sectionY < sections.length; ++sectionY) {
            P_3550_Z section = sections[sectionY];
            if (section == null || section.R_4764_Y()) continue;
            int baseY = sectionY << 4;
            for (int x = 0; x < 16; ++x) {
                for (int y = 0; y < 16; ++y) {
                    for (int z = 0; z < 16; ++z) {
                        T_2915_h block;
                        Integer color;
                        K_4074_S state = section.n_1700_B(x, y, z);
                        if (state.v_4262_N() || (color = this.t_1786_h.get(block = state.J_1907_R())) == null) continue;
                        c_1514_x pos = new c_1514_x(baseX + x, baseY + y, baseZ + z);
                        this.v_4262_N.computeIfAbsent(color, k -> ConcurrentHashMap.newKeySet()).add(pos);
                    }
                }
            }
        }
    }

    public List<c_1514_x> h_1847_R() {
        ArrayList<c_1514_x> result = new ArrayList<c_1514_x>();
        for (Set<c_1514_x> positions : this.v_4262_N.values()) {
            result.addAll(positions);
        }
        return result;
    }

    private void J_1907_R(g_221_o stack) {
        c_1514_x playerPos = BlockESP.c_3005_b.Y_259_p.b_2312_j();
        double maxDistSq = 12544.0;
        this.multiplayerClientSuggestionProvider.clear();
        for (Map.Entry<Integer, Set<c_1514_x>> e : this.v_4262_N.entrySet()) {
            int c = e.getKey();
            Set<c_1514_x> positions = e.getValue();
            if (positions.isEmpty()) continue;
            Color color = new Color(c >> 16 & 0xFF, c >> 8 & 0xFF, c & 0xFF);
            IRenderer.startLines(color, 2.5f, true);
            for (c_1514_x p : positions) {
                block5: {
                    if (playerPos.distanceSq(p) > maxDistSq) continue;
                    try {
                        K_4074_S state = BlockESP.c_3005_b.Y_601_j.getBlockState(p);
                        if (state.v_4262_N()) {
                            positions.remove(p);
                            continue;
                        }
                        if (state.J_1907_R() != a_3742_W.j_306_t) break block5;
                        this.multiplayerClientSuggestionProvider.add(p.toImmutable());
                    }
                    catch (Exception ignored) {
                        continue;
                    }
                }
                IRenderer.emitAABB(stack, new I_4817_s(p, p.add(1, 1, 1)).grow(0.002));
            }
            IRenderer.endLines(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.J_1907_R event) {
        if (BlockESP.c_3005_b.Y_601_j == null || BlockESP.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.multiplayerClientSuggestionProvider.isEmpty()) {
            return;
        }
        this.n_1700_B(event.J_1907_R(), this.multiplayerClientSuggestionProvider);
    }

    private void n_1700_B(g_221_o matrix, List<c_1514_x> spawnerPositions) {
        if (spawnerPositions.isEmpty()) {
            return;
        }
        if (BlockESP.c_3005_b.O_508_d().J_1907_R == null) {
            return;
        }
        Z_3822_q font = l_3370_o.G_564_y[14];
        for (c_1514_x pos : spawnerPositions) {
            try {
                e_2866_D worldPos;
                Vector2f screenPos;
                double distance;
                t_5_h<?> entityType;
                i_2154_H te = BlockESP.c_3005_b.Y_601_j.getTileEntity(pos);
                if (!(te instanceof SpawnerBlockEntity)) continue;
                SpawnerBlockEntity spawnerTE = (SpawnerBlockEntity)te;
                Q_584_o spawner = spawnerTE.v_4262_N();
                if (spawner == null) continue;
                String entityName = null;
                int spawnDelay = -1;
                N_4263_v cachedEntity = spawner.G_564_y();
                if (cachedEntity != null && (entityType = cachedEntity.f_4016_n()) != null) {
                    entityName = this.n_1700_B(entityType);
                }
                try {
                    U_2912_j spawnData;
                    U_2912_j nbt = spawnerTE.n_1700_B(new U_2912_j());
                    if (nbt.P_1922_E("Delay")) {
                        spawnDelay = nbt.v_4262_N("Delay");
                    }
                    if ((entityName == null || entityName.isEmpty()) && nbt.P_1922_E("SpawnData") && (spawnData = nbt.M_182_A("SpawnData")).P_1922_E("id")) {
                        String id = spawnData.M_588_G("id");
                        entityName = this.R_4764_Y(id);
                    }
                }
                catch (Exception nbt) {
                    // empty catch block
                }
                if (entityName == null || entityName.isEmpty() || (distance = BlockESP.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5))) > 64.0 || (screenPos = v_2826_q.n_1700_B(worldPos = new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 1.3, (double)pos.getZ() + 0.5))) == null || screenPos.x == Float.MAX_VALUE) continue;
                this.n_1700_B(matrix, font, screenPos.x, screenPos.y, entityName, spawnDelay, (float)distance);
            }
            catch (Exception exception) {}
        }
    }

    private String R_4764_Y(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        String path = id.contains(":") ? id.split(":")[1] : id;
        return this.G_564_y(path);
    }

    private void n_1700_B(g_221_o matrix, Z_3822_q font, float x, float y, String name, int spawnDelayTicks, float distance) {
        int fontSize = distance < 10.0f ? 16 : (distance < 20.0f ? 14 : (distance < 35.0f ? 12 : 10));
        Z_3822_q dynamicFont = l_3370_o.G_564_y[fontSize];
        String timeText = "";
        if (spawnDelayTicks >= 0) {
            float seconds = (float)spawnDelayTicks / 20.0f;
            timeText = String.format(" \u00a77(\u00a7f%.1f\u0441\u00a77)", Float.valueOf(seconds));
        }
        String fullText = name + timeText;
        float textWidth = dynamicFont.n_1700_B(fullText);
        float textHeight = dynamicFont.h_1847_R();
        dynamicFont.n_1700_B(matrix, name, (double)(x - textWidth / 2.0f), (double)(y - textHeight / 2.0f + 1.0f), new Color(255, 255, 255).getRGB());
        if (spawnDelayTicks >= 0) {
            float nameWidth = dynamicFont.n_1700_B(name);
            float seconds = (float)spawnDelayTicks / 20.0f;
            String timeOnly = String.format("(%.1f\u0441)", Float.valueOf(seconds));
            int timeColor = seconds <= 1.0f ? new Color(100, 255, 100).getRGB() : (seconds <= 5.0f ? new Color(255, 255, 100).getRGB() : new Color(180, 180, 180).getRGB());
            dynamicFont.n_1700_B(matrix, " " + timeOnly, (double)(x - textWidth / 2.0f + nameWidth), (double)(y - textHeight / 2.0f + 1.0f), timeColor);
        }
    }

    private String n_1700_B(t_5_h<?> type) {
        g_2336_b loc = V_3137_a.g_221_o.J_1907_R(type);
        if (loc == null) {
            return "Unknown";
        }
        return this.G_564_y(loc.J_1907_R());
    }

    private String G_564_y(String path) {
        if (path == null || path.isEmpty()) {
            return "Unknown";
        }
        return switch (path) {
            case "zombie" -> "\u0417\u043e\u043c\u0431\u0438";
            case "skeleton" -> "\u0421\u043a\u0435\u043b\u0435\u0442";
            case "spider" -> "\u041f\u0430\u0443\u043a";
            case "cave_spider" -> "\u041f\u0435\u0449\u0435\u0440\u043d\u044b\u0439 \u043f\u0430\u0443\u043a";
            case "blaze" -> "\u0418\u0444\u0440\u0438\u0442";
            case "silverfish" -> "\u0427\u0435\u0448\u0443\u0439\u043d\u0438\u0446\u0430";
            case "magma_cube" -> "\u041c\u0430\u0433\u043c\u043e\u0432\u044b\u0439 \u043a\u0443\u0431";
            case "pig" -> "\u0421\u0432\u0438\u043d\u044c\u044f";
            case "chicken" -> "\u041a\u0443\u0440\u0438\u0446\u0430";
            case "cow" -> "\u041a\u043e\u0440\u043e\u0432\u0430";
            case "sheep" -> "\u041e\u0432\u0446\u0430";
            case "creeper" -> "\u041a\u0440\u0438\u043f\u0435\u0440";
            case "enderman" -> "\u042d\u043d\u0434\u0435\u0440\u043c\u0435\u043d";
            case "witch" -> "\u0412\u0435\u0434\u044c\u043c\u0430";
            case "slime" -> "\u0421\u043b\u0438\u0437\u0435\u043d\u044c";
            case "ghast" -> "\u0413\u0430\u0441\u0442";
            case "wither_skeleton" -> "\u0412\u0438\u0437\u0435\u0440-\u0441\u043a\u0435\u043b\u0435\u0442";
            case "piglin" -> "\u041f\u0438\u0433\u043b\u0438\u043d";
            case "hoglin" -> "\u0425\u043e\u0433\u043b\u0438\u043d";
            case "zombified_piglin" -> "\u0417\u043e\u043c\u0431\u0438-\u043f\u0438\u0433\u043b\u0438\u043d";
            case "strider" -> "\u0421\u0442\u0440\u0430\u0439\u0434\u0435\u0440";
            case "drowned" -> "\u0423\u0442\u043e\u043f\u043b\u0435\u043d\u043d\u0438\u043a";
            case "husk" -> "\u041a\u0430\u0434\u0430\u0432\u0440";
            case "stray" -> "\u0417\u0438\u043c\u043e\u0433\u043e\u0440";
            case "phantom" -> "\u0424\u0430\u043d\u0442\u043e\u043c";
            case "guardian" -> "\u0421\u0442\u0440\u0430\u0436";
            case "elder_guardian" -> "\u0414\u0440\u0435\u0432\u043d\u0438\u0439 \u0441\u0442\u0440\u0430\u0436";
            case "shulker" -> "\u0428\u0430\u043b\u043a\u0435\u0440";
            case "vindicator" -> "\u041f\u043e\u0431\u043e\u0440\u043d\u0438\u043a";
            case "evoker" -> "\u0412\u044b\u0437\u044b\u0432\u0430\u0442\u0435\u043b\u044c";
            case "pillager" -> "\u0420\u0430\u0437\u0431\u043e\u0439\u043d\u0438\u043a";
            case "ravager" -> "\u0420\u0430\u0437\u043e\u0440\u0438\u0442\u0435\u043b\u044c";
            case "vex" -> "\u0412\u0440\u0435\u0434\u0438\u043d\u0430";
            case "iron_golem" -> "\u0416\u0435\u043b\u0435\u0437\u043d\u044b\u0439 \u0433\u043e\u043b\u0435\u043c";
            case "snow_golem" -> "\u0421\u043d\u0435\u0436\u043d\u044b\u0439 \u0433\u043e\u043b\u0435\u043c";
            case "wolf" -> "\u0412\u043e\u043b\u043a";
            case "fox" -> "\u041b\u0438\u0441\u0430";
            case "bee" -> "\u041f\u0447\u0435\u043b\u0430";
            case "bat" -> "\u041b\u0435\u0442\u0443\u0447\u0430\u044f \u043c\u044b\u0448\u044c";
            case "endermite" -> "\u042d\u043d\u0434\u0435\u0440\u043c\u0438\u0442";
            case "warden" -> "\u0425\u0440\u0430\u043d\u0438\u0442\u0435\u043b\u044c";
            default -> this.P_1922_E(path.replace("_", " "));
        };
    }

    private String P_1922_E(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}



