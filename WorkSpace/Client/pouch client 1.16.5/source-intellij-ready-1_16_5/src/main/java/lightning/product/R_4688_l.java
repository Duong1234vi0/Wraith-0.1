/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.redstones.mediaplayerinfo.IMediaSession
 *  dev.redstones.mediaplayerinfo.MediaInfo
 *  dev.redstones.mediaplayerinfo.MediaPlayerInfo
 *  lombok.Generated
 */
package lightning.product;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaInfo;
import dev.redstones.mediaplayerinfo.MediaPlayerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.F_489_x;
import lightning.product.G_4691_Q;
import lightning.product.H_2506_c;
import lightning.product.J_3635_s;
import lightning.product.T_1114_L;
import lightning.product.V_3441_j;
import lightning.product.Z_3822_q;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.c_4477_a;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.i_2518_W;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lombok.Generated;

public class R_4688_l
implements ServerHandshakePacketListener {
    private static final File P_4830_p = new File(System.getProperty("user.dir"), ".pouch_media_info.lock");
    private static final int h_1847_R = 3;
    private static final boolean Q_4569_t = Boolean.parseBoolean(System.getProperty("pouch.mediaInfo.enabled", "false"));
    private static volatile boolean M_182_A = Q_4569_t && !R_4688_l.J_1907_R();
    private static final AtomicInteger t_1786_h = new AtomicInteger(0);
    private static final long multiplayerClientSuggestionProvider = 350L;
    private static final g_2336_b w_1457_N;
    public static volatile IMediaSession n_1700_B;
    public static volatile g_2336_b J_1907_R;
    public static volatile String R_4764_Y;
    public static volatile String G_564_y;
    public static volatile boolean P_1922_E;
    public static volatile float u_1723_Y;
    public static BooleanSetting v_4262_N;
    public static h_2367_h w_1484_f;
    public static h_2367_h t_148_a;
    public static h_2367_h s_956_w;
    public static h_2367_h u_2550_I;
    public static h_2367_h M_588_G;
    private final J_3635_s Y_601_j;
    private final Z_3822_q.n_1700_B Y_259_p = new Z_3822_q.n_1700_B();
    private final G_4691_Q[] Q_2552_b = new G_4691_Q[4];
    private final ExecutorService C_2741_M = Executors.newSingleThreadExecutor();
    private final AtomicBoolean k_2293_S = new AtomicBoolean(false);
    private volatile long q_2307_F = 0L;
    private String Z_875_P = "";
    private long t_4043_B = 0L;
    private static final long x_607_J = 100L;

    private static boolean J_1907_R() {
        if (P_4830_p.exists()) {
            System.err.println("[Pouch] MediaPlayerInfo native crash detected on previous run \u2014 disabling media info polling. Delete " + P_4830_p.getAbsolutePath() + " to re-enable.");
            return true;
        }
        return false;
    }

    public R_4688_l(J_3635_s dragging) {
        this.Y_601_j = dragging;
        for (int i = 0; i < this.Q_2552_b.length; ++i) {
            this.Q_2552_b[i] = new G_4691_Q(1000L, 2.0f, V_3441_j.n_1700_B);
        }
    }

    @Override
    public void n_1700_B(b_3528_u event) {
        float maxTextWidth;
        float y;
        float x;
        this.G_564_y();
        Animation alphaAnimation = new Animation(0.0f, 10.0f);
        alphaAnimation.n_1700_B(R_4688_l.c_3005_b.Y_1740_V instanceof h_4412_P ? 1.0f : 0.0f);
        float globalAlpha = alphaAnimation.n_1700_B();
        long now = System.currentTimeMillis();
        if (now - this.t_4043_B > 100L) {
            this.Z_875_P = (G_564_y == null ? "" : G_564_y) + " - " + (R_4764_Y == null ? "" : R_4764_Y);
            this.t_4043_B = now;
        }
        g_221_o stack = event.J_1907_R();
        float width = P_1922_E ? 115.0f : 100.0f;
        float height = 15.0f;
        if (this.Y_601_j != null) {
            x = this.Y_601_j.J_1907_R();
            y = this.Y_601_j.R_4764_Y();
            this.Y_601_j.R_4764_Y(width);
            this.Y_601_j.G_564_y(height);
        } else {
            x = (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f - width / 2.0f;
            y = 15.0f;
        }
        F_489_x.n_1700_B(x - 10.0f, y - 10.0f, width + 20.0f, height + 18.0f, 6.0f, (int)((Integer)M_588_G.J_1907_R()), (int)((Integer)M_588_G.J_1907_R()), (int)((Integer)M_588_G.J_1907_R()), (int)((Integer)M_588_G.J_1907_R()), (float)H_2506_c.G_564_y((Integer)M_588_G.J_1907_R()) / 255.0f, 8.0f);
        F_489_x.n_1700_B(x, y, width, height, 5.0f, (int)((Integer)w_1484_f.J_1907_R()), 1.0f);
        F_489_x.J_1907_R(x, y, width, height, 5.0f, (Integer)t_148_a.J_1907_R(), H_2506_c.G_564_y((Integer)t_148_a.J_1907_R()));
        g_2336_b image = J_1907_R != null ? J_1907_R : w_1457_N;
        F_489_x.n_1700_B(image, x + 1.5f, y + 1.0f, 13.0f, 13.0f, 4.0f, 1.0f);
        float textX = x + 17.0f;
        float f = maxTextWidth = P_1922_E ? 70.0f : 66.0f;
        if (l_3370_o.G_564_y[13] != null) {
            if (v_4262_N.t_148_a().booleanValue() && l_3370_o.G_564_y[13].n_1700_B(this.Z_875_P) > maxTextWidth) {
                l_3370_o.G_564_y[13].n_1700_B(stack, this.Z_875_P, textX, y + 6.0f, maxTextWidth, (Integer)s_956_w.J_1907_R(), true, this.Y_259_p);
            } else {
                l_3370_o.G_564_y[13].n_1700_B(stack, this.Z_875_P, textX, y + 6.0f, (Integer)s_956_w.J_1907_R(), maxTextWidth);
            }
        } else {
            R_4688_l.c_3005_b.t_148_a.n_1700_B(stack, this.Z_875_P, textX, y + 6.0f, (int)((Integer)s_956_w.J_1907_R()));
        }
        if (P_1922_E) {
            for (int i = 0; i < 4; ++i) {
                float size = 1.0f + (float)(1.0 + Math.sin((float)i + u_1723_Y * 10.0f) / 2.0) * 6.5f;
                this.Q_2552_b[i].n_1700_B(size);
                this.Q_2552_b[i].n_1700_B(1000L);
                float barHeight = this.Q_2552_b[i].J_1907_R();
                if (!(barHeight > 0.0f)) continue;
                int waveCol = (Integer)u_2550_I.J_1907_R();
                int waveAlpha = waveCol >> 24 & 0xFF;
                if (waveAlpha == 0) {
                    waveCol = 0xFF000000 | waveCol & 0xFFFFFF;
                }
                F_489_x.n_1700_B(x + 105.0f + (float)(i * 2), y + 4.0f + (7.0f - barHeight) / 2.0f, 1.0f, barHeight, 0.5f, waveCol);
            }
        } else {
            for (int i = 0; i < 4; ++i) {
                this.Q_2552_b[i].n_1700_B(2.0f);
                this.Q_2552_b[i].n_1700_B(1000L);
            }
        }
    }

    private static void R_4764_Y() {
        n_1700_B = null;
        R_4764_Y = "Title";
        G_564_y = "Album";
        P_1922_E = false;
        u_1723_Y = 0.0f;
        J_1907_R = w_1457_N;
    }

    private void G_564_y() {
        if (!M_182_A) {
            R_4688_l.R_4764_Y();
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.q_2307_F < 350L) {
            return;
        }
        if (!this.k_2293_S.compareAndSet(false, true)) {
            return;
        }
        this.q_2307_F = now;
        this.C_2741_M.submit(() -> {
            boolean sentinelCreated = false;
            try {
                IMediaSession fallback;
                IMediaSession browserSession;
                if (MediaPlayerInfo.Instance == null) {
                    R_4688_l.R_4764_Y();
                    return;
                }
                try {
                    sentinelCreated = P_4830_p.createNewFile() || P_4830_p.exists();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                List sessions = MediaPlayerInfo.Instance.getMediaSessions();
                t_1786_h.set(0);
                if (sessions == null || sessions.isEmpty()) {
                    R_4688_l.R_4764_Y();
                    return;
                }
                IMediaSession musicAppSession = sessions.stream().filter(s -> s != null && s.getMedia() != null).filter(s -> this.n_1700_B(s.getOwner())).findFirst().orElse(null);
                n_1700_B = musicAppSession != null ? musicAppSession : ((browserSession = (IMediaSession)sessions.stream().filter(s -> s != null && s.getMedia() != null).filter(s -> this.J_1907_R(s.getOwner())).findFirst().orElse(null)) != null ? browserSession : ((fallback = (IMediaSession)sessions.stream().filter(s -> s != null && s.getMedia() != null).filter(s -> !s.getMedia().getArtist().isEmpty() || !s.getMedia().getTitle().isEmpty()).filter(s -> !this.R_4764_Y(s.getOwner())).findFirst().orElse(null)) != null ? fallback : (IMediaSession)sessions.stream().filter(s -> s != null && s.getMedia() != null).findFirst().orElse(null)));
                if (n_1700_B == null || n_1700_B.getMedia() == null) {
                    R_4688_l.R_4764_Y();
                    return;
                }
                MediaInfo media = n_1700_B.getMedia();
                R_4764_Y = media.getTitle().isEmpty() ? "Title" : media.getTitle();
                G_564_y = media.getArtist().isEmpty() ? "Album" : media.getArtist();
                P_1922_E = media.getPlaying();
                u_1723_Y = media.getPosition();
                BufferedImage artwork = media.getArtwork();
                if (artwork == null) {
                    c_3005_b.execute(() -> {
                        J_1907_R = w_1457_N;
                    });
                    return;
                }
                i_2518_W ni = new i_2518_W(artwork.getWidth(), artwork.getHeight(), false);
                for (int y = 0; y < artwork.getHeight(); ++y) {
                    for (int x = 0; x < artwork.getWidth(); ++x) {
                        int rgb = artwork.getRGB(x, y);
                        ni.n_1700_B(x, y, rgb & 0xFF00FF00 | rgb >> 16 & 0xFF | (rgb & 0xFF) << 16);
                    }
                }
                g_2336_b cover = new g_2336_b("pouch", "media/cover");
                c_3005_b.execute(() -> {
                    c_3005_b.G_624_v().n_1700_B(cover, (c_4477_a)new T_1114_L(ni));
                    J_1907_R = cover;
                });
            }
            catch (Throwable t) {
                R_4688_l.R_4764_Y();
                if (t_1786_h.incrementAndGet() >= 3) {
                    M_182_A = false;
                    System.err.println("[Pouch] MediaPlayerInfo failed 3 times in a row \u2014 disabling for this session. Last error: " + String.valueOf(t));
                }
            }
            finally {
                if (sentinelCreated) {
                    try {
                        P_4830_p.delete();
                    }
                    catch (Throwable throwable) {}
                }
                this.k_2293_S.set(false);
            }
        });
    }

    private boolean n_1700_B(String owner) {
        if (owner == null) {
            return false;
        }
        String o = owner.toLowerCase();
        return o.contains("soundcloud") || o.contains("spotify") || o.contains("\u044f\u043d\u0434\u0435\u043a\u0441") || o.contains("yandex") || o.contains("\u043c\u0443\u0437\u044b\u043a\u0430") || o.contains("vk") || o.contains("\u0432\u043a\u043e\u043d\u0442\u0430\u043a\u0442\u0435") || o.contains("apple music") || o.contains("itunes") || o.contains("deezer") || o.contains("tidal") || o.contains("amazon music") || o.contains("foobar") || o.contains("aimp") || o.contains("winamp") || o.contains("music");
    }

    private boolean J_1907_R(String owner) {
        if (owner == null) {
            return false;
        }
        String o = owner.toLowerCase();
        return o.contains("chrome") || o.contains("msedge") || o.contains("edge") || o.contains("firefox") || o.contains("opera") || o.contains("\u044f\u043d\u0434\u0435\u043a\u0441") || o.contains("yandex") || o.contains("brave") || o.contains("vivaldi");
    }

    private boolean R_4764_Y(String owner) {
        if (owner == null) {
            return false;
        }
        String o = owner.toLowerCase();
        return o.contains("youtube");
    }

    @Generated
    public static IMediaSession n_1700_B() {
        return n_1700_B;
    }

    static {
        J_1907_R = w_1457_N = new g_2336_b("minecraft", "textures/item/music_disc_11.png");
        R_4764_Y = "Title";
        G_564_y = "Album";
        P_1922_E = false;
        u_1723_Y = 0.0f;
        v_4262_N = new BooleanSetting("\u041f\u0440\u043e\u043a\u0440\u0443\u0442\u043a\u0430 \u0442\u0435\u043a\u0441\u0442\u0430", true);
        w_1484_f = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 30, 35, 255));
        t_148_a = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(30, 30, 35, 255));
        s_956_w = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(255, 255, 255, 255));
        u_2550_I = new h_2367_h("\u0412\u043e\u043b\u043d\u044b", true, H_2506_c.n_1700_B(180, 140, 255, 255));
        M_588_G = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(30, 30, 35, 255));
    }
}


