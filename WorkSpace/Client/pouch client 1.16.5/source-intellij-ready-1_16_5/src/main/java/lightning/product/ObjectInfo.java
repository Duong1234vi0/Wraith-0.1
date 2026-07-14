/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.K_1200_E;
import lightning.product.ClientboundCustomSoundPacket;
import lightning.product.Q_2753_H;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.ClientboundSoundPacket;
import lightning.product.Y_1740_V;
import lightning.product.Z_3822_q;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.BooleanSetting;
import lightning.product.ClientGamePacketListener;
import lightning.product.q_1613_l;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;

public class ObjectInfo
extends Module {
    private final ModeSetting chasticyMode = new ModeSetting("\u0427\u0430\u0441\u0442\u0438\u0446\u044b", "\u0413\u0440\u0430\u0432\u0438\u0442\u0430\u0446\u0438\u044f", "\u0413\u0440\u0430\u0432\u0438\u0442\u0430\u0446\u0438\u044f", "\u0420\u0430\u0437\u0431\u0440\u043e\u0441");
    private final BooleanSetting pokazyvatChasticyEnabled = new BooleanSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0447\u0430\u0441\u0442\u0438\u0446\u044b", true);
    private final BooleanSetting _3dKubStanaEnabled = new BooleanSetting("3D \u041a\u0443\u0431 \u0441\u0442\u0430\u043d\u0430", true);
    private final Map<c_1514_x, J_1907_R> s_956_w = new ConcurrentHashMap<c_1514_x, J_1907_R>();
    private final List<n_1700_B> u_2550_I = new ArrayList<n_1700_B>();
    private static final Random M_588_G = new Random();

    public ObjectInfo() {
        super("ObjectInfo", ModuleCategory.R_4764_Y);
        this.addSettings(this.chasticyMode, this.pokazyvatChasticyEnabled, this._3dKubStanaEnabled);
    }

    @Override
    public void onDisable() {
        this.s_956_w.clear();
        this.u_2550_I.clear();
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        Packet<ClientGamePacketListener> sound;
        if (!event.J_1907_R()) {
            return;
        }
        Packet<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof ClientboundSoundPacket) {
            sound = (ClientboundSoundPacket)t_3138_Z2;
            this.n_1700_B(((ClientboundSoundPacket)sound).J_1907_R().n_1700_B().toString(), ((ClientboundSoundPacket)sound).G_564_y(), ((ClientboundSoundPacket)sound).P_1922_E(), ((ClientboundSoundPacket)sound).u_1723_Y(), ((ClientboundSoundPacket)sound).v_4262_N(), ((ClientboundSoundPacket)sound).w_1484_f());
        }
        if ((t_3138_Z2 = event.G_564_y()) instanceof ClientboundCustomSoundPacket) {
            sound = (ClientboundCustomSoundPacket)t_3138_Z2;
            this.n_1700_B(((ClientboundCustomSoundPacket)sound).J_1907_R().toString(), ((ClientboundCustomSoundPacket)sound).G_564_y(), ((ClientboundCustomSoundPacket)sound).P_1922_E(), ((ClientboundCustomSoundPacket)sound).u_1723_Y(), ((ClientboundCustomSoundPacket)sound).v_4262_N(), ((ClientboundCustomSoundPacket)sound).w_1484_f());
        }
    }

    private void n_1700_B(String soundName, double x, double y, double z, float volume, float pitch) {
        c_1514_x pos;
        if ((soundName.contains("minecraft:entity.wither.break_block") || soundName.contains("minecraft:block.anvil.place")) && volume == 0.7f || volume == 0.2f && pitch == 1.0f) {
            pos = new c_1514_x((int)x, (int)y, (int)z);
            this.s_956_w.put(pos, new J_1907_R(pos.up(), lightning.product.ObjectInfo$R_4764_Y.n_1700_B));
        }
        if (!(!soundName.contains("minecraft:block.anvil.place") || volume != 0.5f && volume != 0.7f || pitch != 1.1f && pitch != 0.5f)) {
            pos = new c_1514_x((int)x, (int)y, (int)z);
            this.s_956_w.put(pos, new J_1907_R(pos.up(), lightning.product.ObjectInfo$R_4764_Y.G_564_y));
        }
        if (soundName.contains("minecraft:entity.generic.explode") && (volume == 1.0f || pitch == 1.0f)) {
            pos = new c_1514_x((int)x, (int)y, (int)z);
            this.s_956_w.put(pos, new J_1907_R(pos, lightning.product.ObjectInfo$R_4764_Y.R_4764_Y));
            this.s_956_w.put(pos.up(), new J_1907_R(pos.up(), lightning.product.ObjectInfo$R_4764_Y.J_1907_R));
        }
        if (soundName.contains("minecraft:block.beacon.deactivate") && ((double)volume == 1.5 || pitch == 1.0f)) {
            pos = new c_1514_x((int)x, (int)y, (int)z);
            this.s_956_w.put(pos, new J_1907_R(pos, lightning.product.ObjectInfo$R_4764_Y.R_4764_Y));
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (ObjectInfo.c_3005_b.Y_259_p == null || ObjectInfo.c_3005_b.Y_601_j == null) {
            return;
        }
        g_221_o matrices = event.J_1907_R();
        for (J_1907_R info : this.s_956_w.values()) {
            info.n_1700_B(matrices);
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (ObjectInfo.c_3005_b.Y_259_p == null || ObjectInfo.c_3005_b.Y_601_j == null) {
            return;
        }
        c_1514_x toRemove = null;
        for (Map.Entry<c_1514_x, J_1907_R> entry : this.s_956_w.entrySet()) {
            J_1907_R info = entry.getValue();
            if (!info.R_4764_Y.n_1700_B((double)info.J_1907_R.v_4262_N)) continue;
            if (this.pokazyvatChasticyEnabled.isEnabled().booleanValue()) {
                this.J_1907_R(info);
            }
            toRemove = entry.getKey();
        }
        if (toRemove != null) {
            this.s_956_w.remove(toRemove);
        }
        if (this._3dKubStanaEnabled.isEnabled().booleanValue()) {
            for (J_1907_R info : this.s_956_w.values()) {
                if (info.J_1907_R != lightning.product.ObjectInfo$R_4764_Y.R_4764_Y) continue;
                this.n_1700_B(info);
            }
        }
        if (this.pokazyvatChasticyEnabled.isEnabled().booleanValue()) {
            Iterator<n_1700_B> particleIterator = this.u_2550_I.iterator();
            while (particleIterator.hasNext()) {
                n_1700_B particle = particleIterator.next();
                if (particle.G_564_y()) {
                    particleIterator.remove();
                    continue;
                }
                particle.n_1700_B();
                particle.P_1922_E();
            }
        }
    }

    private void n_1700_B(J_1907_R info) {
        e_2866_D center = new e_2866_D((double)info.n_1700_B.getX() + 0.5, (double)info.n_1700_B.getY() + 0.5, (double)info.n_1700_B.getZ() + 0.5);
        float[][] lines = new float[][]{{15.0f, -15.0f, 15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, -15.0f, 15.0f, 0.0f, 1.0f, 0.0f}, {15.0f, -15.0f, -15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, -15.0f, -15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, 15.0f, 15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, -15.0f, 15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, 15.0f, -15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, -15.0f, -15.0f, 1.0f, 0.0f, 0.0f}, {15.0f, 15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {-15.0f, 15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {15.0f, -15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {-15.0f, -15.0f, -15.0f, 0.0f, 0.0f, 1.0f}};
        int color = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        g_2336_b texture = new g_2336_b("Pouch/icons/world_render/bloom.png");
        for (float[] line : lines) {
            for (float i = 0.0f; i < 30.0f; i += 0.5f) {
                float localX = (line[0] + i * line[3]) / 15.0f;
                float localY = (line[1] + i * line[4]) / 15.0f;
                float localZ = (line[2] + i * line[5]) / 15.0f;
                float px = (float)center.J_1907_R + localX;
                float py = (float)center.R_4764_Y + localY;
                float pz = (float)center.G_564_y + localZ;
                F_489_x.n_1700_B(texture, px, py, pz, 0.1f, 0.1f, color, true);
            }
        }
    }

    private void J_1907_R(J_1907_R info) {
        float[][] lines;
        if (info.J_1907_R != lightning.product.ObjectInfo$R_4764_Y.R_4764_Y) {
            return;
        }
        G_564_y center = new G_564_y((double)info.n_1700_B.getX() + 0.5, (double)info.n_1700_B.getY() + 0.5, (double)info.n_1700_B.getZ() + 0.5);
        for (float[] line : lines = new float[][]{{15.0f, -15.0f, 15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, -15.0f, 15.0f, 0.0f, 1.0f, 0.0f}, {15.0f, -15.0f, -15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, -15.0f, -15.0f, 0.0f, 1.0f, 0.0f}, {-15.0f, 15.0f, 15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, -15.0f, 15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, 15.0f, -15.0f, 1.0f, 0.0f, 0.0f}, {-15.0f, -15.0f, -15.0f, 1.0f, 0.0f, 0.0f}, {15.0f, 15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {-15.0f, 15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {15.0f, -15.0f, -15.0f, 0.0f, 0.0f, 1.0f}, {-15.0f, -15.0f, -15.0f, 0.0f, 0.0f, 1.0f}}) {
            for (float i = 0.0f; i < 30.0f; i += 0.5f) {
                float localX = (line[0] + i * line[3]) / 15.0f;
                float localY = (line[1] + i * line[4]) / 15.0f;
                float localZ = (line[2] + i * line[5]) / 15.0f;
                float velo = 0.005f;
                float vx = (M_588_G.nextFloat() - 0.5f) * 2.0f * velo;
                float vy = (M_588_G.nextFloat() - 0.5f) * 2.0f * velo;
                float vz = (M_588_G.nextFloat() - 0.5f) * 2.0f * velo;
                this.u_2550_I.add(new n_1700_B(this, center, localX, localY, localZ, vx, vy, vz, ((String)this.chasticyMode.getValue()).equals("\u0413\u0440\u0430\u0432\u0438\u0442\u0430\u0446\u0438\u044f")));
            }
        }
    }

    private void n_1700_B(g_221_o matrices, Z_3822_q font, String text, float centerX, float y, int color) {
        if (font == null) {
            return;
        }
        float textWidth = font.n_1700_B(text);
        font.n_1700_B(matrices, text, (double)(centerX - textWidth / 2.0f), (double)y, color);
    }

    private void n_1700_B(g_221_o matrices, float centerX, float centerY, float radius, float thickness, float progress, int color) {
        int segments = 64;
        float startAngle = -90.0f;
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(thickness);
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(3, E_688_b.Y_601_j);
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        int i = 0;
        while ((float)i <= (float)segments * progress) {
            float angle = (float)Math.toRadians(startAngle + 360.0f / (float)segments * (float)i);
            float x = centerX + (float)Math.cos(angle) * radius;
            float y = centerY + (float)Math.sin(angle) * radius;
            buffer.pos(x, y, 0.0).n_1700_B(r, g, b, a).endVertex();
            ++i;
        }
        l_3747_P.n_1700_B().J_1907_R();
        c_4037_x.x_607_J();
    }

    class J_1907_R {
        final c_1514_x n_1700_B;
        final R_4764_Y J_1907_R;
        final V_4557_X R_4764_Y = new V_4557_X();

        J_1907_R(c_1514_x pos, R_4764_Y type) {
            this.n_1700_B = pos;
            this.J_1907_R = type;
            this.R_4764_Y.n_1700_B();
        }

        void n_1700_B(g_221_o matrices) {
            long elapsed = this.R_4764_Y.J_1907_R();
            int remained = (int)((float)(this.J_1907_R.v_4262_N - elapsed) / 1000.0f);
            if (remained < 0) {
                return;
            }
            e_2866_D renderPos = new e_2866_D((double)this.n_1700_B.getX() + 0.5, (double)this.n_1700_B.getY() + 1.5, (double)this.n_1700_B.getZ() + 0.5);
            Vector2f screenPos = v_2826_q.n_1700_B(renderPos.J_1907_R, renderPos.R_4764_Y, renderPos.G_564_y);
            if (screenPos != null) {
                int smallFontSize;
                float distance = (float)MinecraftAccess.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(renderPos);
                float scale = u_530_F.n_1700_B(1.0f - distance / 40.0f, 0.3f, 1.0f);
                float progress = 1.0f - (float)elapsed / (float)this.J_1907_R.v_4262_N;
                String timeText = "0:" + String.valueOf(remained < 10 ? "0" + remained : Integer.valueOf(remained));
                float width = 80.0f * scale;
                float height = 80.0f * scale;
                float x = screenPos.x - width / 2.0f;
                float y = screenPos.y - height / 2.0f;
                matrices.n_1700_B();
                F_489_x.n_1700_B(x, y, width, height, 8.0f * scale, H_2506_c.n_1700_B(20, 20, 25, 180));
                int progressColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
                ObjectInfo.this.n_1700_B(matrices, screenPos.x, screenPos.y, 28.0f * scale, 3.0f * scale, progress, progressColor);
                float itemSize = 24.0f * scale;
                F_489_x.n_1700_B(this.J_1907_R.u_1723_Y.Y_601_j(), screenPos.x - itemSize / 2.0f, screenPos.y - itemSize / 2.0f - 5.0f * scale, itemSize / 16.0f);
                int fontSize = Math.max(6, (int)(10.0f * scale));
                if (fontSize < l_3370_o.G_564_y.length && l_3370_o.G_564_y[fontSize] != null) {
                    ObjectInfo.this.n_1700_B(matrices, l_3370_o.G_564_y[fontSize], timeText, screenPos.x, screenPos.y + 15.0f * scale, -1);
                }
                if ((smallFontSize = Math.max(6, (int)(8.0f * scale))) < l_3370_o.G_564_y.length && l_3370_o.G_564_y[smallFontSize] != null) {
                    ObjectInfo.this.n_1700_B(matrices, l_3370_o.G_564_y[smallFontSize], this.J_1907_R.P_1922_E, screenPos.x, y + height - 12.0f * scale, H_2506_c.n_1700_B(180, 180, 180));
                }
                matrices.J_1907_R();
            }
        }
    }

    static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y("\u0422\u0440\u0430\u043f\u043a\u0430", Items.m_396_H, 15000L);
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y("\u0412\u0437\u0440\u044b\u0432 \u0442\u0440\u0430\u043f\u043a\u0430", Items.v_4620_e, 11000L);
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y("\u0421\u0442\u0430\u043d", Items.FallingBlock, 15000L);
        public static final /* enum */ R_4764_Y G_564_y = new R_4764_Y("\u041f\u043b\u0430\u0441\u0442", Items.MinMaxBounds, 20000L);
        final String P_1922_E;
        final q_1613_l u_1723_Y;
        final long v_4262_N;
        private static final /* synthetic */ R_4764_Y[] w_1484_f;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])w_1484_f.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private R_4764_Y(String name, q_1613_l item, long time) {
            this.P_1922_E = name;
            this.u_1723_Y = item;
            this.chasticyMode = time;
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            w_1484_f = lightning.product.ObjectInfo$R_4764_Y.n_1700_B();
        }
    }

    class n_1700_B {
        double n_1700_B;
        double J_1907_R;
        double R_4764_Y;
        float G_564_y;
        float P_1922_E;
        float u_1723_Y;
        long v_4262_N;
        float w_1484_f;
        long t_148_a;
        boolean s_956_w;
        float u_2550_I;
        static final float M_588_G = 0.4f;

        n_1700_B(ObjectInfo this$0, G_564_y center, float localX, float localY, float localZ, float vx, float vy, float vz, boolean useGravity) {
            this.n_1700_B = center.n_1700_B + (double)localX;
            this.J_1907_R = center.J_1907_R + (double)localY;
            this.R_4764_Y = center.R_4764_Y + (double)localZ;
            this.G_564_y = vx;
            this.P_1922_E = vy;
            this.u_1723_Y = vz;
            this.chasticyMode = System.currentTimeMillis();
            this.s_956_w = useGravity;
            this.pokazyvatChasticyEnabled = 2.0E-4f + M_588_G.nextFloat() * 4.0E-4f;
            this._3dKubStanaEnabled = 2000 + M_588_G.nextInt(2000);
            this.u_2550_I = useGravity ? 0.9999f : 0.999f;
        }

        void n_1700_B() {
            if (this.s_956_w) {
                this.P_1922_E -= this.pokazyvatChasticyEnabled;
            }
            this.G_564_y *= this.u_2550_I;
            this.P_1922_E *= this.u_2550_I;
            this.u_1723_Y *= this.u_2550_I;
            double nextX = this.n_1700_B + (double)this.G_564_y;
            double nextY = this.J_1907_R + (double)this.P_1922_E;
            double nextZ = this.R_4764_Y + (double)this.u_1723_Y;
            if (this.s_956_w && MinecraftAccess.c_3005_b.Y_601_j != null) {
                c_1514_x nextPosZ;
                c_1514_x nextPosX;
                c_1514_x nextPosY = new c_1514_x(this.n_1700_B, nextY - 0.5, this.R_4764_Y);
                if (!MinecraftAccess.c_3005_b.Y_601_j.getBlockState(nextPosY).v_4262_N()) {
                    this.P_1922_E = -this.P_1922_E * 0.4f;
                    nextY = this.J_1907_R;
                }
                if (!MinecraftAccess.c_3005_b.Y_601_j.getBlockState(nextPosX = new c_1514_x(nextX, this.J_1907_R, this.R_4764_Y)).v_4262_N()) {
                    this.G_564_y = -this.G_564_y * 0.4f;
                    nextX = this.n_1700_B;
                }
                if (!MinecraftAccess.c_3005_b.Y_601_j.getBlockState(nextPosZ = new c_1514_x(this.n_1700_B, this.J_1907_R, nextZ)).v_4262_N()) {
                    this.u_1723_Y = -this.u_1723_Y * 0.4f;
                    nextZ = this.R_4764_Y;
                }
            }
            this.n_1700_B = nextX;
            this.J_1907_R = nextY;
            this.R_4764_Y = nextZ;
        }

        float J_1907_R() {
            return u_530_F.n_1700_B((float)(System.currentTimeMillis() - this.chasticyMode) / (float)this._3dKubStanaEnabled, 0.0f, 1.0f);
        }

        float R_4764_Y() {
            return 1.0f - this.J_1907_R();
        }

        boolean G_564_y() {
            return System.currentTimeMillis() - this.chasticyMode > this._3dKubStanaEnabled;
        }

        void P_1922_E() {
            int color = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int alpha = (int)(this.R_4764_Y() * 255.0f);
            color = H_2506_c.n_1700_B(color, alpha);
            g_2336_b texture = new g_2336_b("Pouch/icons/world_render/bloom.png");
            F_489_x.n_1700_B(texture, (float)this.n_1700_B, (float)this.J_1907_R, (float)this.R_4764_Y, 0.08f, 0.08f, color, true);
        }
    }

    static class G_564_y {
        final double n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;

        G_564_y(double x, double y, double z) {
            this.n_1700_B = x;
            this.J_1907_R = y;
            this.R_4764_Y = z;
        }
    }
}



