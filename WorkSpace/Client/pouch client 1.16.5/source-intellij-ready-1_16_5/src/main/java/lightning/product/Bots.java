/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lightning.product.NumberSetting;
import lightning.product.J_1907_R;
import lightning.product.ClientboundLoginPacket;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.MultiBooleanSetting;
import lightning.product.O_3016_i;
import lightning.product.P_4526_H;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.R_4764_Y;
import lightning.product.U_2871_b;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Y_2080_q;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.n_1700_B;
import lightning.product.BooleanSetting;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class Bots
extends Module
implements MinecraftAccess {
    public MultiBooleanSetting otobrazhatOptions = new MultiBooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new BooleanSetting("\u0427\u0430\u0442", true), new BooleanSetting("\u0411\u043e\u0441\u0441\u0411\u0430\u0440", false));
    public BooleanSetting pozvolyatBotamDvigatsyaEnabled = new BooleanSetting("\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0442\u044c \u0431\u043e\u0442\u0430\u043c \u0434\u0432\u0438\u0433\u0430\u0442\u044c\u0441\u044f", true);
    public O_3016_i t_148_a = new O_3016_i("\u041d\u0438\u043a \u0431\u043e\u0442\u0430");
    public NumberSetting zaderzhkaSekSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0435\u043a", 8.0f, 3.0f, 60.0f, 1.0f);
    public BooleanSetting spuferResurspakaEnabled = new BooleanSetting("\u0421\u043f\u0443\u0444\u0435\u0440 \u0440\u0435\u0441\u0443\u0440\u0441\u043f\u0430\u043a\u0430", true);
    public O_3016_i M_588_G = new O_3016_i("\u041f\u0430\u0440\u043e\u043b\u044c \u0431\u043e\u0442\u043e\u0432");
    public BooleanSetting zakryvatMenyuRwEnabled = new BooleanSetting("\u0417\u0430\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u043c\u0435\u043d\u044e RW", true);
    public BooleanSetting pereklyuchatEkranEnabled = new BooleanSetting("\u041f\u0435\u0440\u0435\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u044d\u043a\u0440\u0430\u043d", true);
    private static final Object Q_4569_t = new Object();
    private static final DateTimeFormatter M_182_A = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int t_1786_h = -1;

    public Bots() {
        super("Bots", ModuleCategory.P_1922_E);
        this.t_148_a.n_1700_B("Rickstone");
        this.M_588_G.n_1700_B("Rick123");
        this.addSettings(this.otobrazhatOptions, this.pozvolyatBotamDvigatsyaEnabled, this.spuferResurspakaEnabled, this.t_148_a, this.M_588_G, this.zaderzhkaSekSetting, this.zakryvatMenyuRwEnabled, this.pereklyuchatEkranEnabled);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (this.pozvolyatBotamDvigatsyaEnabled.isEnabled().booleanValue()) {
            lightning.product.J_1907_R.G_564_y();
            lightning.product.J_1907_R.R_4764_Y();
        }
        lightning.product.J_1907_R.n_1700_B((String)this.M_588_G.J_1907_R());
        if (this.t_1786_h > 0) {
            --this.t_1786_h;
        } else if (this.t_1786_h == 0) {
            this.t_1786_h = -1;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (bot == null || bot.M_588_G == null) continue;
                bot.n_1700_B(bot.M_588_G);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(String botName, String server) {
        if (botName == null || botName.isEmpty() || server == null || server.isEmpty()) {
            return;
        }
        String timestamp = LocalDateTime.now().format(M_182_A);
        String line = String.format("[%s] %s -> %s%n", timestamp, botName, server);
        Path dir = Path.of("Pouch", new String[0]);
        Path file = dir.resolve("bot_connections.txt");
        try {
            Object object = Q_4569_t;
            synchronized (object) {
                if (!Files.exists(dir, new LinkOption[0])) {
                    Files.createDirectories(dir, new FileAttribute[0]);
                }
                Files.write(file, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Y_1740_V
    public void n_1700_B(Y_2080_q event) {
        ClientboundOpenScreenPacket packet;
        if (event.P_1922_E() instanceof ClientboundRespawnPacket || event.P_1922_E() instanceof ClientboundLoginPacket) {
            this.t_1786_h = 20;
        }
        if (this.zakryvatMenyuRwEnabled.isEnabled().booleanValue() && event.P_1922_E() instanceof ClientboundOpenScreenPacket && (packet = (ClientboundOpenScreenPacket)event.P_1922_E()).G_564_y().getString().contains("\ua201\ua000\ua202\ua301\ua202\ua001\u00a70\ua203\ua100")) {
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (bot.R_4764_Y == null || bot.R_4764_Y.RealmsWorldResetDto >= 200) continue;
                v_1900_v.n_1700_B(new U_2871_b("\u0441\u043f\u0430\u0440\u0441\u0438\u043b \u0441\u0438\u043c\u0432\u043e\u043b\u044b \u0434\u043b\u044f " + bot.R_4764_Y.O_1309_Q().getString()), new Object[0]);
                bot.R_4764_Y.n_1700_B.n_1700_B(new P_4526_H(packet.J_1907_R()));
            }
            v_1900_v.n_1700_B(new U_2871_b("\u0437\u0430\u043a\u0440\u044b\u0432\u0430\u044e \u043e\u043a\u043d\u043e"), new Object[0]);
            event.n_1700_B();
        }
    }

    @Override
    public void onDisable() {
        if (!lightning.product.J_1907_R.n_1700_B.isEmpty()) {
            lightning.product.R_4764_Y.P_1922_E();
        }
        super.onDisable();
    }

    @Generated
    public MultiBooleanSetting h_1847_R() {
        return this.otobrazhatOptions;
    }
}



