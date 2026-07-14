/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2506_c;
import lightning.product.HitResult;
import lightning.product.TridentItem;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_1200_E;
import lightning.product.K_4096_w;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.BowItem;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.Enchantments;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_3148_R;
import lightning.product.Items;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.EntityHitResult;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class Trajectory
extends Module
implements MinecraftAccess {
    private final MultiBooleanSetting predmetyOptions = new MultiBooleanSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", new BooleanSetting("\u041b\u0443\u043a", true), new BooleanSetting("\u0410\u0440\u0431\u0430\u043b\u0435\u0442", true), new BooleanSetting("\u042d\u043d\u0434\u0435\u0440 \u041f\u0451\u0440\u043b", true), new BooleanSetting("\u0421\u043d\u0435\u0436\u043e\u043a", true), new BooleanSetting("\u042f\u0439\u0446\u043e", true), new BooleanSetting("\u0417\u0435\u043b\u044c\u0435", true), new BooleanSetting("\u041e\u043f\u044b\u0442", true), new BooleanSetting("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", true));
    private final BooleanSetting vremyaPadeniyaEnabled = new BooleanSetting("\u0412\u0440\u0435\u043c\u044f \u043f\u0430\u0434\u0435\u043d\u0438\u044f", false);
    private static final DecimalFormat t_148_a = new DecimalFormat("0.0");
    private List<n_1700_B> s_956_w = new ArrayList<n_1700_B>();
    private e_2866_D u_2550_I = e_2866_D.n_1700_B;
    private float M_588_G = 0.0f;
    private float P_4830_p = 0.0f;

    public Trajectory() {
        super("Trajectory", ModuleCategory.R_4764_Y);
        this.addSettings(this.predmetyOptions, this.vremyaPadeniyaEnabled);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (Trajectory.c_3005_b.Y_259_p == null || Trajectory.c_3005_b.Y_601_j == null) {
            return;
        }
        Z_1993_T stack = Trajectory.c_3005_b.Y_259_p.A_2714_y();
        if (stack.n_1700_B()) {
            this.s_956_w = Collections.emptyList();
            return;
        }
        List<n_1700_B> trajectories = this.n_1700_B(stack, e.J_1907_R());
        if (trajectories.isEmpty()) {
            this.s_956_w = Collections.emptyList();
            return;
        }
        this.s_956_w = trajectories;
        h_3572_K camera = Trajectory.c_3005_b.s_956_w.M_588_G();
        e_2866_D cameraPos = camera.J_1907_R();
        g_221_o matrix = new g_221_o();
        c_4037_x.v_4276_D();
        c_4037_x.n_1700_B(matrix.R_4764_Y().n_1700_B());
        c_4037_x.J_1907_R(-cameraPos.J_1907_R, -cameraPos.R_4764_Y, -cameraPos.G_564_y);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.t_1786_h();
        GL11.glEnable((int)2848);
        c_4037_x.G_564_y(2.0f);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        for (n_1700_B data : trajectories) {
            this.n_1700_B(data);
        }
        Y_1740_V.J_1907_R();
        c_4037_x.G_564_y(1.0f);
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        for (n_1700_B data : trajectories) {
            if (!data.u_1723_Y || data.v_4262_N == null) continue;
            int color = data.t_148_a != null ? H_2506_c.n_1700_B(255, 60, 60, 220) : q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            this.n_1700_B(data.v_4262_N, color);
            if (!(data.s_956_w > 0.0f)) continue;
            this.n_1700_B(data.v_4262_N, data.s_956_w, color);
        }
        Y_1740_V.J_1907_R();
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 1);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.J_1907_R(false);
        c_4037_x.q_2307_F();
        c_4037_x.u_2550_I();
        c_4037_x.x_607_J();
        g_2336_b glowTexture = new g_2336_b("minecraft:Pouch/icons/world_render/glow.png");
        c_3005_b.G_624_v().n_1700_B(glowTexture);
        g_221_o ms = new g_221_o();
        ms.n_1700_B();
        ms.n_1700_B(-cameraPos.J_1907_R, -cameraPos.R_4764_Y, -cameraPos.G_564_y);
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.k_2293_S);
        for (n_1700_B data : trajectories) {
            if (!data.u_1723_Y || data.v_4262_N == null) continue;
            int color = data.t_148_a != null ? H_2506_c.n_1700_B(255, 60, 60, 220) : q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            this.n_1700_B(buffer, ms, data.v_4262_N, color);
        }
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        c_4037_x.e_4240_b();
        ms.J_1907_R();
        c_4037_x.J_1907_R(true);
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.Y_259_p();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        GL11.glDisable((int)2848);
        c_4037_x.J_1907_R(cameraPos.J_1907_R, cameraPos.R_4764_Y, cameraPos.G_564_y);
        c_4037_x.d_2461_k();
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        if (!this.vremyaPadeniyaEnabled.isEnabled().booleanValue() || Trajectory.c_3005_b.Y_259_p == null || Trajectory.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.s_956_w == null || this.s_956_w.isEmpty()) {
            return;
        }
        for (n_1700_B data : this.s_956_w) {
            if (!data.u_1723_Y || data.v_4262_N == null) continue;
            e_2866_D pos = data.v_4262_N;
            Vector2f screen = v_2826_q.n_1700_B(pos.J_1907_R, pos.R_4764_Y, pos.G_564_y);
            if (screen.x == Float.MAX_VALUE && screen.y == Float.MAX_VALUE) continue;
            float x = screen.x;
            float y = screen.y + 3.0f;
            String timeText = t_148_a.format(data.w_1484_f) + " \u0441\u0435\u043a";
            float timeWidth = l_3370_o.J_1907_R[14].n_1700_B(timeText);
            float padding = 6.0f;
            float rectWidth = timeWidth + padding * 2.0f;
            l_3370_o.J_1907_R[14].n_1700_B(e.J_1907_R(), timeText, (double)(x - timeWidth / 2.0f), (double)(y + 10.0f), -1);
        }
    }

    private List<n_1700_B> n_1700_B(Z_1993_T stack, float partialTicks) {
        q_1613_l item = stack.J_1907_R();
        ArrayList<n_1700_B> result = new ArrayList<n_1700_B>();
        double lerpX = u_530_F.G_564_y((double)partialTicks, Trajectory.c_3005_b.Y_259_p.r_715_M, Trajectory.c_3005_b.Y_259_p.O_3598_v());
        double lerpY = u_530_F.G_564_y((double)partialTicks, Trajectory.c_3005_b.Y_259_p.A_1038_p, Trajectory.c_3005_b.Y_259_p.X_2960_b());
        double lerpZ = u_530_F.G_564_y((double)partialTicks, Trajectory.c_3005_b.Y_259_p.i_1637_u, Trajectory.c_3005_b.Y_259_p.l_2647_k());
        double eyeHeight = Trajectory.c_3005_b.Y_259_p.X_1313_W();
        e_2866_D eyePos = new e_2866_D(lerpX, lerpY + eyeHeight, lerpZ);
        boolean isFirstPerson = Trajectory.c_3005_b.P_4830_p.P_4830_p().n_1700_B();
        float currentYaw = u_530_F.v_4262_N(partialTicks, Trajectory.c_3005_b.Y_259_p.j_276_v, Trajectory.c_3005_b.Y_259_p.p_178_J);
        float currentPitch = u_530_F.v_4262_N(partialTicks, Trajectory.c_3005_b.Y_259_p.UploadStatus, Trajectory.c_3005_b.Y_259_p.f_4016_n);
        if (this.M_588_G == 0.0f && this.P_4830_p == 0.0f) {
            this.M_588_G = currentYaw;
            this.P_4830_p = currentPitch;
        }
        float yawDelta = u_530_F.v_4262_N(currentYaw - this.M_588_G);
        float pitchDelta = currentPitch - this.P_4830_p;
        float smoothFactor = isFirstPerson ? 0.1f : 0.2f;
        this.M_588_G += yawDelta * smoothFactor;
        this.P_4830_p += pitchDelta * smoothFactor;
        float baseYaw = this.M_588_G;
        float basePitch = this.P_4830_p;
        e_2866_D currentMotion = Trajectory.c_3005_b.Y_259_p.I_4348_c();
        if (this.u_2550_I.equals(e_2866_D.n_1700_B)) {
            this.u_2550_I = currentMotion;
        }
        e_2866_D playerMotion = new e_2866_D(u_530_F.G_564_y(0.5, this.u_2550_I.J_1907_R, currentMotion.J_1907_R), u_530_F.G_564_y(0.5, this.u_2550_I.R_4764_Y, currentMotion.R_4764_Y), u_530_F.G_564_y(0.5, this.u_2550_I.G_564_y, currentMotion.G_564_y));
        this.u_2550_I = new e_2866_D(u_530_F.G_564_y(0.3, this.u_2550_I.J_1907_R, currentMotion.J_1907_R), u_530_F.G_564_y(0.3, this.u_2550_I.R_4764_Y, currentMotion.R_4764_Y), u_530_F.G_564_y(0.3, this.u_2550_I.G_564_y, currentMotion.G_564_y));
        if (item instanceof BowItem && this.predmetyOptions.isOptionEnabled("\u041b\u0443\u043a").booleanValue()) {
            if (!Trajectory.c_3005_b.Y_259_p.Y_601_j() || Trajectory.c_3005_b.Y_259_p.B_2580_P() != stack) {
                return result;
            }
            int useTime = Trajectory.c_3005_b.Y_259_p.U_144_f();
            int maxUseTime = stack.u_2550_I();
            int usedTime = maxUseTime - useTime;
            float pull = BowItem.n_1700_B(usedTime);
            if (pull < 0.1f) {
                pull = 0.1f;
            }
            float speed = 3.0f * pull;
            result.add(this.n_1700_B(eyePos, baseYaw, basePitch, speed, 0.05f, playerMotion));
            return result;
        }
        if (item instanceof Z_1630_j && this.predmetyOptions.isOptionEnabled("\u0410\u0440\u0431\u0430\u043b\u0435\u0442").booleanValue()) {
            boolean multishot;
            if (!Z_1630_j.G_564_y(stack)) {
                return result;
            }
            float speed = 3.15f;
            float gravity = 0.05f;
            boolean bl = multishot = K_4096_w.n_1700_B(Enchantments.n_3318_d, stack) > 0;
            if (multishot) {
                result.add(this.n_1700_B(eyePos, baseYaw, basePitch, speed, gravity, playerMotion));
                result.add(this.n_1700_B(eyePos, baseYaw - 10.0f, basePitch, speed, gravity, playerMotion));
                result.add(this.n_1700_B(eyePos, baseYaw + 10.0f, basePitch, speed, gravity, playerMotion));
                return result;
            }
            result.add(this.n_1700_B(eyePos, baseYaw, basePitch, speed, gravity, playerMotion));
            return result;
        }
        if (item == Items.v_2746_S && this.predmetyOptions.isOptionEnabled("\u042d\u043d\u0434\u0435\u0440 \u041f\u0451\u0440\u043b") != false || item == Items.i_770_g && this.predmetyOptions.isOptionEnabled("\u0421\u043d\u0435\u0436\u043e\u043a") != false || item == Items.s_4405_m && this.predmetyOptions.isOptionEnabled("\u042f\u0439\u0446\u043e").booleanValue()) {
            result.add(this.n_1700_B(eyePos, baseYaw, basePitch, 1.5f, 0.03f, playerMotion));
            return result;
        }
        if (item instanceof TridentItem && this.predmetyOptions.isOptionEnabled("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue()) {
            result.add(this.n_1700_B(eyePos, baseYaw, basePitch, 2.5f, 0.05f, playerMotion));
            return result;
        }
        if (item == Items.s_3084_y && this.predmetyOptions.isOptionEnabled("\u041e\u043f\u044b\u0442").booleanValue()) {
            result.add(this.J_1907_R(eyePos, baseYaw, basePitch, 0.7f, 0.07f, playerMotion));
            return result;
        }
        if ((item == Items.NetherrackBlock || item == Items.g_2492_v) && this.predmetyOptions.isOptionEnabled("\u0417\u0435\u043b\u044c\u0435").booleanValue()) {
            n_1700_B data = this.J_1907_R(eyePos, baseYaw, basePitch, 0.5f, 0.05f, playerMotion);
            data.s_956_w = item == Items.g_2492_v ? 4.0f : 3.0f;
            result.add(data);
            return result;
        }
        return result;
    }

    private e_2866_D n_1700_B(float yaw, float pitch) {
        float yawRad = (float)Math.toRadians(yaw);
        float pitchRad = (float)Math.toRadians(pitch);
        float x = -u_530_F.n_1700_B(yawRad) * u_530_F.J_1907_R(pitchRad);
        float y = -u_530_F.n_1700_B(pitchRad);
        float z = u_530_F.J_1907_R(yawRad) * u_530_F.J_1907_R(pitchRad);
        return new e_2866_D(x, y, z).G_564_y();
    }

    private n_1700_B n_1700_B(e_2866_D eyePos, float yaw, float pitch, float speed, float gravity, e_2866_D playerMotion) {
        e_2866_D dir = this.n_1700_B(yaw, pitch);
        e_2866_D start = eyePos;
        e_2866_D motion = dir.n_1700_B((double)speed).n_1700_B(playerMotion.J_1907_R, Trajectory.c_3005_b.Y_259_p.M_1641_O() ? 0.0 : playerMotion.R_4764_Y, playerMotion.G_564_y);
        return new n_1700_B(start, motion, gravity, 0.99f, 160);
    }

    private n_1700_B J_1907_R(e_2866_D eyePos, float yaw, float pitch, float speed, float gravity, e_2866_D playerMotion) {
        float pitchRad = (float)Math.toRadians(pitch);
        float yawRad = (float)Math.toRadians(yaw);
        float rollRad = (float)Math.toRadians(-20.0);
        float x = -u_530_F.n_1700_B(yawRad) * u_530_F.J_1907_R(pitchRad);
        float y = -u_530_F.n_1700_B(pitchRad + rollRad);
        float z = u_530_F.J_1907_R(yawRad) * u_530_F.J_1907_R(pitchRad);
        e_2866_D dir = new e_2866_D(x, y, z).G_564_y();
        e_2866_D start = eyePos;
        e_2866_D motion = dir.n_1700_B((double)speed).n_1700_B(playerMotion.J_1907_R, Trajectory.c_3005_b.Y_259_p.M_1641_O() ? 0.0 : playerMotion.R_4764_Y, playerMotion.G_564_y);
        return new n_1700_B(start, motion, gravity, 0.99f, 160);
    }

    private void n_1700_B(n_1700_B data) {
        e_2866_D position = data.n_1700_B;
        e_2866_D motion = data.J_1907_R;
        int themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int themeColorDark = H_2506_c.J_1907_R(themeColor, 0.5f);
        N_4263_v hitEntity = null;
        for (int i = 0; i <= data.P_1922_E; ++i) {
            boolean inLava;
            c_1514_x currentPos;
            I_4817_s searchBox;
            EntityHitResult entityHitResult;
            e_2866_D lastPosition = position;
            position = position.P_1922_E(motion);
            e_2866_D segmentStart = lastPosition;
            e_2866_D segmentEnd = position;
            boolean hitSomething = false;
            ClipContext rayTraceContext = new ClipContext(segmentStart, position, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, Trajectory.c_3005_b.Y_259_p);
            BlockHitResult blockHitResult = Trajectory.c_3005_b.Y_601_j.n_1700_B(rayTraceContext);
            if (blockHitResult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
                segmentEnd = blockHitResult.P_1922_E();
                hitSomething = true;
            }
            if ((entityHitResult = this.n_1700_B(segmentStart, position, searchBox = new I_4817_s(segmentStart, position).grow(0.3), (N_4263_v entity) -> entity != Trajectory.c_3005_b.Y_259_p && entity.C_290_v())) != null) {
                e_2866_D entityHitPos = entityHitResult.P_1922_E();
                double entityDistSq = entityHitPos.v_4262_N(segmentStart);
                double currentDistSq = segmentEnd.v_4262_N(segmentStart);
                if (!hitSomething || entityDistSq < currentDistSq) {
                    segmentEnd = entityHitPos;
                    hitSomething = true;
                    hitEntity = entityHitResult.n_1700_B();
                }
            }
            if (segmentEnd.R_4764_Y <= 0.0) {
                segmentEnd = new e_2866_D(segmentEnd.J_1907_R, 0.0, segmentEnd.G_564_y);
                hitSomething = true;
            }
            boolean inWater = Trajectory.c_3005_b.Y_601_j.getBlockState(currentPos = new c_1514_x(position)).J_1907_R() == a_3742_W.c_3005_b;
            boolean bl = inLava = Trajectory.c_3005_b.Y_601_j.getBlockState(currentPos).J_1907_R() == a_3742_W.H_2857_Y;
            if (inWater || inLava) {
                float dragMultiplier = inWater ? 0.8f : 0.6f;
                motion = motion.n_1700_B((double)dragMultiplier);
            } else {
                motion = motion.n_1700_B((double)data.G_564_y);
            }
            motion = motion.J_1907_R(0.0, -data.R_4764_Y, 0.0);
            int color = H_2506_c.J_1907_R(3, i * 8, themeColor, themeColorDark);
            A_4115_X.pos(lastPosition.J_1907_R, lastPosition.R_4764_Y, lastPosition.G_564_y).n_1700_B(color).endVertex();
            A_4115_X.pos(segmentEnd.J_1907_R, segmentEnd.R_4764_Y, segmentEnd.G_564_y).n_1700_B(color).endVertex();
            if (!hitSomething) continue;
            data.v_4262_N = segmentEnd;
            data.w_1484_f = (float)i * 0.05f;
            data.u_1723_Y = true;
            data.t_148_a = hitEntity;
            break;
        }
    }

    private EntityHitResult n_1700_B(e_2866_D start, e_2866_D end, I_4817_s box, Predicate<N_4263_v> filter) {
        double closestDist = Double.MAX_VALUE;
        N_4263_v closestEntity = null;
        e_2866_D closestHit = null;
        for (N_4263_v entity : Trajectory.c_3005_b.Y_601_j.J_1907_R((N_4263_v)Trajectory.c_3005_b.Y_259_p, box, filter::test)) {
            double dist;
            I_4817_s entityBox = entity.i_601_W().grow(0.3);
            Optional<e_2866_D> hitResult = entityBox.rayTrace(start, end);
            if (!hitResult.isPresent() || !((dist = start.v_4262_N(hitResult.get())) < closestDist)) continue;
            closestDist = dist;
            closestEntity = entity;
            closestHit = hitResult.get();
        }
        if (closestEntity != null) {
            return new EntityHitResult(closestEntity, closestHit);
        }
        return null;
    }

    private void n_1700_B(D_3318_r buffer, g_221_o ms, e_2866_D pos, int color) {
        h_3572_K camera = Trajectory.c_3005_b.s_956_w.M_588_G();
        e_2866_D cameraPos = new e_2866_D(c_3005_b.O_508_d().renderPosX(), c_3005_b.O_508_d().renderPosY(), c_3005_b.O_508_d().renderPosZ());
        double distance = pos.u_1723_Y(cameraPos);
        float glowSize = (float)(0.1 * distance);
        glowSize = Math.max(glowSize, 0.5f);
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = 204;
        ms.n_1700_B();
        ms.n_1700_B(pos.J_1907_R - cameraPos.J_1907_R, pos.R_4764_Y - cameraPos.R_4764_Y, pos.G_564_y - cameraPos.G_564_y);
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-camera.P_1922_E()));
        ms.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(camera.G_564_y()));
        float halfSize = glowSize / 2.0f;
        D_1098_v matrix = ms.R_4764_Y().n_1700_B();
        buffer.n_1700_B(matrix, -halfSize * 2.0f, -halfSize * 2.0f, 0.0f).tex(1.0f, 1.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
        buffer.n_1700_B(matrix, -halfSize * 2.0f, halfSize * 2.0f, 0.0f).tex(1.0f, 0.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
        buffer.n_1700_B(matrix, halfSize * 2.0f, halfSize * 2.0f, 0.0f).tex(0.0f, 0.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
        buffer.n_1700_B(matrix, halfSize * 2.0f, -halfSize * 2.0f, 0.0f).tex(0.0f, 1.0f).color(r, g, b, (int)((float)a * 0.3f)).endVertex();
        buffer.n_1700_B(matrix, -halfSize, -halfSize, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, -halfSize, halfSize, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, halfSize, halfSize, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, halfSize, -halfSize, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        ms.J_1907_R();
    }

    private void n_1700_B(e_2866_D center, float radius, int color) {
        int segments = 64;
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f * 0.6f;
        for (int i = 0; i < segments; ++i) {
            double angle1 = Math.PI * 2 * (double)i / (double)segments;
            double angle2 = Math.PI * 2 * (double)(i + 1) / (double)segments;
            double x1 = center.J_1907_R + Math.cos(angle1) * (double)radius;
            double z1 = center.G_564_y + Math.sin(angle1) * (double)radius;
            double x2 = center.J_1907_R + Math.cos(angle2) * (double)radius;
            double z2 = center.G_564_y + Math.sin(angle2) * (double)radius;
            double y1 = this.n_1700_B(x1, z1, center.R_4764_Y);
            double y2 = this.n_1700_B(x2, z2, center.R_4764_Y);
            A_4115_X.pos(x1, y1 + 0.01, z1).n_1700_B(r, g, b, a).endVertex();
            A_4115_X.pos(x2, y2 + 0.01, z2).n_1700_B(r, g, b, a).endVertex();
        }
    }

    private double n_1700_B(double x, double z, double startY) {
        c_1514_x pos = new c_1514_x(x, startY, z);
        for (int y = (int)startY; y >= (int)startY - 10; --y) {
            c_1514_x checkPos = new c_1514_x(x, (double)y, z);
            if (Trajectory.c_3005_b.Y_601_j.getBlockState(checkPos).v_4262_N() || !Trajectory.c_3005_b.Y_601_j.getBlockState(checkPos).R_4764_Y().J_1907_R()) continue;
            return (double)checkPos.getY() + 1.0;
        }
        ClipContext context = new ClipContext(new e_2866_D(x, startY + 5.0, z), new e_2866_D(x, startY - 10.0, z), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, Trajectory.c_3005_b.Y_259_p);
        BlockHitResult result = Trajectory.c_3005_b.Y_601_j.n_1700_B(context);
        if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            return result.P_1922_E().R_4764_Y;
        }
        return Math.floor(startY);
    }

    private void n_1700_B(e_2866_D pos, int color) {
        int[][] edges;
        e_2866_D cameraPos = Trajectory.c_3005_b.s_956_w.M_588_G().J_1907_R();
        double distance = pos.u_1723_Y(cameraPos);
        float size = (float)(0.015 * distance);
        size = Math.max(size, 0.1f);
        double time = (double)System.currentTimeMillis() / 1000.0;
        double rotX = time * 90.0;
        double rotY = time * 120.0;
        double rotZ = time * 75.0;
        float cosX = (float)Math.cos(Math.toRadians(rotX));
        float sinX = (float)Math.sin(Math.toRadians(rotX));
        float cosY = (float)Math.cos(Math.toRadians(rotY));
        float sinY = (float)Math.sin(Math.toRadians(rotY));
        float cosZ = (float)Math.cos(Math.toRadians(rotZ));
        float sinZ = (float)Math.sin(Math.toRadians(rotZ));
        float[][] corners = new float[][]{{-size, -size, -size}, {size, -size, -size}, {size, -size, size}, {-size, -size, size}, {-size, size, -size}, {size, size, -size}, {size, size, size}, {-size, size, size}};
        float[][] rotated = new float[8][3];
        for (int i = 0; i < 8; ++i) {
            float x = corners[i][0];
            float y = corners[i][1];
            float z = corners[i][2];
            float y1 = y * cosX - z * sinX;
            float z1 = y * sinX + z * cosX;
            float x2 = x * cosY + z1 * sinY;
            float z2 = -x * sinY + z1 * cosY;
            float x3 = x2 * cosZ - y1 * sinZ;
            float y3 = x2 * sinZ + y1 * cosZ;
            rotated[i][0] = (float)pos.J_1907_R + x3;
            rotated[i][1] = (float)pos.R_4764_Y + y3;
            rotated[i][2] = (float)pos.G_564_y + z2;
        }
        for (int[] e : edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {0, 4}, {1, 5}, {2, 6}, {3, 7}}) {
            A_4115_X.pos(rotated[e[0]][0], rotated[e[0]][1], rotated[e[0]][2]).n_1700_B(color).endVertex();
            A_4115_X.pos(rotated[e[1]][0], rotated[e[1]][1], rotated[e[1]][2]).n_1700_B(color).endVertex();
        }
    }

    private static class n_1700_B {
        final e_2866_D n_1700_B;
        final e_2866_D J_1907_R;
        final float R_4764_Y;
        final float G_564_y;
        final int P_1922_E;
        boolean u_1723_Y;
        e_2866_D v_4262_N;
        float w_1484_f;
        N_4263_v t_148_a;
        float s_956_w;

        n_1700_B(e_2866_D startPos, e_2866_D velocity, float gravity, float drag, int maxSteps) {
            this.n_1700_B = startPos;
            this.J_1907_R = velocity;
            this.R_4764_Y = gravity;
            this.G_564_y = drag;
            this.P_1922_E = maxSteps;
            this.s_956_w = 0.0f;
        }
    }
}



