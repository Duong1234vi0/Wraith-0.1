/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.j_1376_w;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;

public class B_707_U
implements ServerHandshakePacketListener {
    private final J_3635_s n_1700_B;
    private final Animation J_1907_R = new Animation(1.0f, 10.0f);
    private String R_4764_Y = "";
    private String G_564_y = "";
    private String P_1922_E = "";

    @Override
    public void n_1700_B(b_3528_u event) {
        if (B_707_U.c_3005_b.Y_259_p == null || B_707_U.c_3005_b.Y_601_j == null) {
            return;
        }
        this.n_1700_B();
        g_221_o ms = event.J_1907_R();
        float posX = this.n_1700_B.J_1907_R();
        float posY = this.n_1700_B.R_4764_Y();
        this.J_1907_R.n_1700_B(1.0f);
        float globalAlpha = this.J_1907_R.n_1700_B();
        float width = 88.0f;
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float height = headerHeight + 3.0f * itemSpacing + 5.0f;
        int glow = q_3148_R.n_1700_B(K_1200_E.q_2307_F);
        float glowAlpha = q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, height + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, width, height, 5.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, headerHeight, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), q_3148_R.n_1700_B(K_1200_E.n_1700_B), globalAlpha);
        int outline = q_3148_R.n_1700_B(K_1200_E.h_1847_R);
        float outlineAlphaValue = q_3148_R.J_1907_R(K_1200_E.h_1847_R) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, height, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        MutableComponent gradientTitle = j_1376_w.n_1700_B("Events", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientTitle, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("Q", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("Q") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        int itemTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), globalAlpha);
        int timeColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        float baseY = posY + headerHeight + 4.0f;
        l_3370_o.J_1907_R[12].n_1700_B(ms, "AirDrop", (double)(posX + 4.5f), (double)(baseY + 1.0f), itemTextColor);
        l_3370_o.J_1907_R[12].n_1700_B(ms, this.R_4764_Y, (double)(posX + width - 4.5f - l_3370_o.J_1907_R[12].n_1700_B(this.R_4764_Y)), (double)(baseY + 1.0f), timeColor);
        l_3370_o.J_1907_R[12].n_1700_B(ms, "Mascot", (double)(posX + 4.5f), (double)(baseY + itemSpacing + 1.0f), itemTextColor);
        l_3370_o.J_1907_R[12].n_1700_B(ms, this.G_564_y, (double)(posX + width - 4.5f - l_3370_o.J_1907_R[12].n_1700_B(this.G_564_y)), (double)(baseY + itemSpacing + 1.0f), timeColor);
        l_3370_o.J_1907_R[12].n_1700_B(ms, "Chest", (double)(posX + 4.5f), (double)(baseY + itemSpacing * 2.0f + 1.0f), itemTextColor);
        l_3370_o.J_1907_R[12].n_1700_B(ms, this.P_1922_E, (double)(posX + width - 4.5f - l_3370_o.J_1907_R[12].n_1700_B(this.P_1922_E)), (double)(baseY + itemSpacing * 2.0f + 1.0f), timeColor);
        this.n_1700_B.R_4764_Y(width);
        this.n_1700_B.G_564_y(height);
    }

    private void n_1700_B() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        List<LocalDateTime> airDropSchedule = Arrays.asList(now.withHour(9).withMinute(0).withSecond(0), now.withHour(11).withMinute(0).withSecond(0), now.withHour(13).withMinute(0).withSecond(0), now.withHour(15).withMinute(0).withSecond(0), now.withHour(17).withMinute(0).withSecond(0), now.withHour(19).withMinute(0).withSecond(0), now.withHour(21).withMinute(0).withSecond(0), now.withHour(23).withMinute(0).withSecond(0));
        LocalDateTime nextAirDrop = airDropSchedule.stream().filter(t -> t.isAfter(now)).findFirst().orElse(now.plusDays(1L).withHour(9).withMinute(0).withSecond(0));
        this.R_4764_Y = this.n_1700_B(now, nextAirDrop);
        LocalDateTime mascotTime = now.withHour(15).withMinute(30).withSecond(0);
        if (now.isAfter(mascotTime)) {
            mascotTime = mascotTime.plusDays(1L);
        }
        this.G_564_y = this.n_1700_B(now, mascotTime);
        LocalDateTime chestTime = now.withHour(now.getHour() / 6 * 6).withMinute(0).withSecond(0);
        if (!now.isBefore(chestTime)) {
            chestTime = chestTime.plusHours(6L);
        }
        this.P_1922_E = this.n_1700_B(now, chestTime);
    }

    private String n_1700_B(LocalDateTime now, LocalDateTime target) {
        long hours = ChronoUnit.HOURS.between(now, target);
        long minutes = ChronoUnit.MINUTES.between(now, target) % 60L;
        long seconds = ChronoUnit.SECONDS.between(now, target) % 60L;
        StringBuilder sb = new StringBuilder();
        if (hours > 0L) {
            sb.append(hours).append("h ");
        }
        if (minutes > 0L || hours > 0L) {
            sb.append(minutes).append("m ");
        }
        sb.append(seconds).append("s");
        return sb.toString().trim();
    }

    @Generated
    public B_707_U(J_3635_s dragging) {
        this.n_1700_B = dragging;
    }
}


