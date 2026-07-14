/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lightning.product.D_4024_W;
import lightning.product.H_2506_c;
import lightning.product.Attributes;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.x_282_a;

public final class t_1509_b
implements MinecraftAccess {
    private static final Set<String> n_1700_B = Set.of("Eternity", "Infinity", "Stinger", "Immortal", "Armortality", "Flash", "Cerber");
    private static final Map<String, String> J_1907_R = Map.ofEntries(Map.entry("\u0412\u0438\u0445\u0440\u044c \u043d\u0435 \u0437\u043d\u0430\u0435\u0442 \u043f\u043e\u043a\u043e\u044f", "talisman_vortex"), Map.entry("\u041f\u043e\u0445\u0438\u0442\u0438\u0442\u0435\u043b\u044c \u043f\u0440\u0430\u0437\u0434\u043d\u0438\u043a\u0430 \u043b\u0435\u0433\u043e\u043a", "talisman_grinch"), Map.entry("\u041a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u043d\u0435 \u0437\u043d\u0430\u0435\u0442 \u043f\u043e\u0449\u0430\u0434\u044b", "talisman_crusher"), Map.entry("\u041d\u0435\u0441\u0451\u0442 \u0441\u0442\u0440\u043e\u0433\u0438\u0439 \u043f\u0440\u0438\u0433\u043e\u0432\u043e\u0440", "talisman_punisher"), Map.entry("\u0413\u0430\u0440\u043c\u043e\u043d\u0438\u044f \u0432\u043e \u0432\u0441\u0451\u043c", "talisman_harmony"), Map.entry("\u0414\u0435\u0434\u0430\u043b \u043c\u0430\u0441\u0442\u0435\u0440 \u043b\u0430\u0431\u0438\u0440\u0438\u043d\u0442\u043e\u0432", "talisman_daedalus"), Map.entry("\u0415\u0445\u0438\u0434\u043d\u0430 \u043a\u043e\u0432\u0430\u0440\u043d\u0430", "talisman_echidna"), Map.entry("\u0413\u0440\u0430\u043d\u044c \u043c\u0435\u0436\u0434\u0443 \u0436\u0438\u0437\u043d\u044c\u044e", "talisman_edge"), Map.entry("\u0422\u0440\u0438\u0442\u043e\u043d \u0432\u043b\u0430\u0434\u044b\u043a\u0430 \u043c\u043e\u0440\u0435\u0439", "talisman_triton"), Map.entry("\u0424\u0435\u043d\u0438\u043a\u0441 \u0432\u043e\u0437\u0440\u043e\u0436\u0434\u0430\u0435\u0442\u0441\u044f", "talisman_phoenix"), Map.entry("\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u044b\u0439 \u0441\u0438\u043c\u0432\u043e\u043b", "talisman_crusher"), Map.entry("\u041c\u0440\u0430\u043a \u0441\u0433\u0443\u0449\u0430\u0435\u0442\u0441\u044f \u0440\u044f\u0434\u043e\u043c", "talisman_darkness"), Map.entry("\u041f\u0435\u0447\u0430\u0442\u044c \u0440\u0430\u0437\u0436\u0438\u0433\u0430\u0435\u0442 \u044f\u0440\u043e\u0441\u0442\u044c", "talisman_demon"), Map.entry("\u0420\u0430\u0437\u0434\u043e\u0440 \u0436\u0430\u0436\u0434\u0435\u0442 \u0445\u0430\u043e\u0441\u0430", "talisman_discord"), Map.entry("\u0422\u0438\u0440\u0430\u043d \u043f\u043e\u0434\u0430\u0432\u043b\u044f\u0435\u0442 \u0441\u043b\u0430\u0431\u044b\u0445", "talisman_tyrant"), Map.entry("\u0427\u0438\u0441\u0442\u0430\u044f, \u0434\u0438\u043a\u0430\u044f \u0430\u0433\u0440\u0435\u0441\u0441\u0438\u044f", "talisman_rage"));
    private static final Map<String, String> R_4764_Y = Map.ofEntries(Map.entry("EXPLOSIVE_TRAP", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430"), Map.entry("STUN_STAR", "\u0421\u0442\u0430\u043d"), Map.entry("ALTERNATIVE_TRAP", "\u0422\u0440\u0430\u043f\u043a\u0430"), Map.entry("ExplosiveStuff", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430"), Map.entry("SnowBall", "\u0421\u043d\u0435\u0436\u043e\u043a"), Map.entry("desorientation", "\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f"), Map.entry("sheerdust", "\u041f\u044b\u043b\u044c"), Map.entry("godsaura", "\u0410\u0443\u0440\u0430 \u0431\u043e\u0433\u0430"), Map.entry("trap", "\u0422\u0440\u0430\u043f\u043a\u0430"), Map.entry("explosivetrap", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430"), Map.entry("effect-item-diz", "\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f"), Map.entry("effect-item-dust", "\u041f\u044b\u043b\u044c"), Map.entry("effect-item-god", "\u0410\u0443\u0440\u0430 \u0431\u043e\u0433\u0430"), Map.entry("effect-item-trap", "\u0422\u0440\u0430\u043f\u043a\u0430"), Map.entry("effect-item-explosivetrap", "\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430"), Map.entry("effect-item-snowball", "\u0421\u043d\u0435\u0436\u043e\u043a"), Map.entry("effect-item-stun", "\u0421\u0442\u0430\u043d"));
    private static final Map<String, Integer> G_564_y = Map.ofEntries(Map.entry("ExplosiveStuff", H_2506_c.n_1700_B(255, 0, 0, 255)), Map.entry("STUN_STAR", H_2506_c.n_1700_B(228, 228, 228, 255)), Map.entry("EXPLOSIVE_TRAP", H_2506_c.n_1700_B(127, 131, 165, 255)), Map.entry("ALTERNATIVE_TRAP", H_2506_c.n_1700_B(206, 127, 218, 255)), Map.entry("Eternity", H_2506_c.n_1700_B(205, 0, 120, 255)), Map.entry("Infinity", H_2506_c.n_1700_B(205, 0, 120, 255)), Map.entry("Stinger", H_2506_c.n_1700_B(205, 0, 120, 255)), Map.entry("Immortal", H_2506_c.n_1700_B(119, 0, 223, 255)), Map.entry("Armortality", H_2506_c.n_1700_B(62, 78, 115, 255)), Map.entry("Flash", H_2506_c.n_1700_B(0, 128, 250, 255)), Map.entry("Cerber", H_2506_c.n_1700_B(0, 128, 250, 255)));

    private t_1509_b() {
    }

    public static J_1907_R n_1700_B(Z_1993_T stack) {
        if (stack == null || stack.n_1700_B()) {
            return null;
        }
        U_2912_j root = stack.Q_4569_t();
        if (root == null) {
            return null;
        }
        J_1907_R result = t_1509_b.n_1700_B(root);
        if (result != null) {
            return result;
        }
        result = t_1509_b.J_1907_R(root);
        if (result != null) {
            return result;
        }
        result = t_1509_b.n_1700_B(stack, root);
        if (result != null) {
            return result;
        }
        if (root.R_4764_Y("PublicBukkitValues", 10)) {
            U_2912_j pbv = root.M_182_A("PublicBukkitValues");
            result = t_1509_b.n_1700_B(pbv);
            if (result != null) {
                return result;
            }
            result = t_1509_b.J_1907_R(pbv);
            if (result != null) {
                return result;
            }
            result = t_1509_b.n_1700_B(stack, pbv);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static String n_1700_B(a_3913_L player) {
        boolean fullProtectionSet;
        if (player == null) {
            return "EMPTY";
        }
        Z_1993_T offhand = player.S_4035_N();
        if (offhand.n_1700_B()) {
            return "EMPTY";
        }
        Object skullIdSignature = null;
        U_2912_j tag = offhand.Q_4569_t();
        if (tag != null) {
            int[] id;
            U_2912_j skullOwner;
            skullIdSignature = tag.toString();
            if (offhand.J_1907_R() == Items.C_3560_B && tag.R_4764_Y("SkullOwner", 10) && (skullOwner = tag.M_182_A("SkullOwner")).R_4764_Y("Id", 11) && (id = skullOwner.h_1847_R("Id")).length == 4) {
                skullIdSignature = id[0] + "," + id[1] + "," + id[2] + "," + id[3];
            }
        }
        if (skullIdSignature == null) {
            return "EMPTY";
        }
        double movement = player.n_1700_B(Attributes.G_564_y).J_1907_R();
        double armor = player.n_1700_B(Attributes.t_148_a).u_1723_Y();
        double attackSpeed = player.n_1700_B(Attributes.w_1484_f).u_1723_Y();
        double maxHealth = player.n_1700_B(Attributes.n_1700_B).J_1907_R();
        if (((String)skullIdSignature).contains("-2028481669,-405130400,-1468526862,-1002383415")) {
            return movement == 0.12000000476837158 ? "MYTHIC_SPEED" : "MYTHIC_DAMAGE";
        }
        if (((String)skullIdSignature).contains("-6803891,-487902205,-2132082558,-1336441356")) {
            return "ARMORTALITY";
        }
        if (((String)skullIdSignature).contains("1380367236,-1919012332,-1216974478,-572027876")) {
            return "IMMORTALITY";
        }
        if (((String)skullIdSignature).contains("1404353110,-467519837,-1639700424,-1282774674")) {
            return "CERBERUS";
        }
        if (((String)skullIdSignature).contains("-372484930,-74172931,-1856149270,-1219017950")) {
            return "FLESH";
        }
        boolean isTotem = offhand.J_1907_R() == Items.N_81_X;
        NonNullList<Z_1993_T> armorItems = player.l_1268_F.J_1907_R;
        boolean bl = fullProtectionSet = !(armorItems.size() < 4 || ((Z_1993_T)armorItems.get(0)).J_1907_R() != Items.V_4557_X && ((Z_1993_T)armorItems.get(0)).J_1907_R() != Items.j_2129_E || ((Z_1993_T)armorItems.get(1)).J_1907_R() != Items.A_1603_w && ((Z_1993_T)armorItems.get(1)).J_1907_R() != Items.v_1900_v || ((Z_1993_T)armorItems.get(2)).J_1907_R() != Items.f_508_U && ((Z_1993_T)armorItems.get(2)).J_1907_R() != Items.O_1043_U || ((Z_1993_T)armorItems.get(3)).J_1907_R() != Items.q_4361_M && ((Z_1993_T)armorItems.get(3)).J_1907_R() != Items.u_488_m && ((Z_1993_T)armorItems.get(3)).J_1907_R() != Items.h_3066_J);
        if (!isTotem) {
            if (offhand.J_1907_R() != Items.C_3560_B) {
                return "EMPTY";
            }
            if (movement == (double)0.11f) {
                return "SPEED_1";
            }
            if (movement == 0.12000000476837158) {
                return "SPEED_2";
            }
            if (movement == (double)0.13f) {
                return "SPEED_3";
            }
            if (((String)skullIdSignature).contains("-1,-1891592620,-1,-1891592620")) {
                return "LEGENDARY";
            }
        } else if (fullProtectionSet) {
            if (attackSpeed == 4.800000011920929) {
                return "SATYR";
            }
            if (movement == 0.12000000476837158 && armor - 20.0 == 2.0 && maxHealth - 20.0 == 4.0) {
                return "INFINITY";
            }
            if (movement == (double)0.11f && armor - 20.0 == 2.0 && armor - 20.0 == 2.0) {
                return "STINGER";
            }
            if (movement == 0.12000000476837158 && armor - 20.0 == 2.0) {
                return "ETERNITY";
            }
        }
        return "EMPTY";
    }

    private static J_1907_R n_1700_B(U_2912_j data) {
        String spookyItem = t_1509_b.n_1700_B(data, "PublicBukkitValues", "spookyitems:spooky-item");
        if (spookyItem == null) {
            spookyItem = t_1509_b.n_1700_B(data, "spooky-item");
        }
        if (spookyItem == null) {
            return null;
        }
        n_1700_B category = t_1509_b.n_1700_B(spookyItem);
        return new J_1907_R(category, spookyItem, lightning.product.t_1509_b$R_4764_Y.R_4764_Y);
    }

    private static n_1700_B n_1700_B(String item) {
        if (item == null) {
            return lightning.product.t_1509_b$n_1700_B.P_1922_E;
        }
        if (item.startsWith("effect-item-") || item.startsWith("schematic-item-")) {
            return lightning.product.t_1509_b$n_1700_B.n_1700_B;
        }
        if (item.startsWith("attribute-item-t")) {
            return lightning.product.t_1509_b$n_1700_B.R_4764_Y;
        }
        if (item.startsWith("attribute-item-s")) {
            return lightning.product.t_1509_b$n_1700_B.J_1907_R;
        }
        return lightning.product.t_1509_b$n_1700_B.P_1922_E;
    }

    private static J_1907_R J_1907_R(U_2912_j data) {
        String type;
        U_2912_j py;
        String name;
        if (data.R_4764_Y("pyrotechnic-item", 10) && !(name = (py = data.M_182_A("pyrotechnic-item")).M_588_G("name")).isEmpty()) {
            return new J_1907_R(lightning.product.t_1509_b$n_1700_B.n_1700_B, name, lightning.product.t_1509_b$R_4764_Y.n_1700_B);
        }
        if (data.R_4764_Y("kringeItems", 10) && !(type = data.M_182_A("kringeItems").M_588_G("type")).isEmpty()) {
            return new J_1907_R(lightning.product.t_1509_b$n_1700_B.n_1700_B, type, lightning.product.t_1509_b$R_4764_Y.n_1700_B);
        }
        if (data.R_4764_Y("kringeEffect", 10) && !(type = data.M_182_A("kringeEffect").M_588_G("type")).isEmpty()) {
            return new J_1907_R(lightning.product.t_1509_b$n_1700_B.G_564_y, type, lightning.product.t_1509_b$R_4764_Y.n_1700_B);
        }
        if (data.R_4764_Y("sphereEffect", 10)) {
            U_2912_j sphere = data.M_182_A("sphereEffect");
            name = sphere.M_588_G("name");
            String rankStr = sphere.M_588_G("rank");
            boolean isMascot = sphere.t_1786_h("isMascot");
            G_564_y rank = lightning.product.t_1509_b$G_564_y.n_1700_B(rankStr);
            n_1700_B category = isMascot ? lightning.product.t_1509_b$n_1700_B.R_4764_Y : lightning.product.t_1509_b$n_1700_B.J_1907_R;
            return new J_1907_R(category, name.isEmpty() ? null : name, rank, lightning.product.t_1509_b$R_4764_Y.n_1700_B);
        }
        return null;
    }

    private static J_1907_R n_1700_B(Z_1993_T stack, U_2912_j data) {
        String donItem = t_1509_b.n_1700_B(data, "PublicBukkitValues", "minecraft:don-item");
        if (donItem == null) {
            donItem = t_1509_b.n_1700_B(data, "don-item");
        }
        if (donItem != null) {
            return new J_1907_R(lightning.product.t_1509_b$n_1700_B.n_1700_B, donItem, lightning.product.t_1509_b$R_4764_Y.J_1907_R);
        }
        if (t_1509_b.J_1907_R(stack, data)) {
            String talismanType = t_1509_b.R_4764_Y(data);
            return new J_1907_R(lightning.product.t_1509_b$n_1700_B.R_4764_Y, talismanType, lightning.product.t_1509_b$R_4764_Y.J_1907_R);
        }
        return null;
    }

    private static boolean J_1907_R(Z_1993_T stack, U_2912_j data) {
        return stack != null && stack.J_1907_R() == Items.N_81_X && data.R_4764_Y("AttributeModifiers", 9) && stack.k_2293_S();
    }

    private static String R_4764_Y(U_2912_j data) {
        if (!data.R_4764_Y("display", 10)) {
            return null;
        }
        U_2912_j display = data.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return null;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        if (lore.isEmpty()) {
            return null;
        }
        String firstLore = lore.t_148_a(0);
        for (Map.Entry<String, String> entry : J_1907_R.entrySet()) {
            if (!firstLore.contains(entry.getKey())) continue;
            return entry.getValue();
        }
        return null;
    }

    private static String n_1700_B(U_2912_j data, String compound, String key) {
        if (data == null || !data.R_4764_Y(compound, 10)) {
            return null;
        }
        U_2912_j inner = data.M_182_A(compound);
        if (!inner.R_4764_Y(key, 8)) {
            return null;
        }
        String value = inner.M_588_G(key);
        return value.isEmpty() ? null : value;
    }

    private static String n_1700_B(U_2912_j data, String key) {
        if (data == null || !data.R_4764_Y(key, 8)) {
            return null;
        }
        String value = data.M_588_G(key);
        return value.isEmpty() ? null : value;
    }

    private static String n_1700_B(x_282_a comp) {
        if (comp == null) {
            return "";
        }
        String s = comp.getString();
        if (s == null) {
            return "";
        }
        return s.replace("[", "").replace("]", "").trim();
    }

    private static int J_1907_R(x_282_a comp) {
        D_4024_W tf;
        if (comp == null) {
            return -1;
        }
        try {
            Z_1567_W style = comp.n_1700_B();
            if (style != null && style.n_1700_B() != null) {
                return 0xFF000000 | style.n_1700_B().n_1700_B();
            }
        }
        catch (Throwable style) {
            // empty catch block
        }
        String raw = comp.getString();
        if (raw != null && (tf = D_4024_W.J_1907_R(raw)) != null && tf.G_564_y() != null) {
            return 0xFF000000 | tf.G_564_y();
        }
        return -1;
    }

    public static final class J_1907_R {
        private final n_1700_B n_1700_B;
        private final String J_1907_R;
        private final G_564_y R_4764_Y;
        private final R_4764_Y G_564_y;

        public J_1907_R(n_1700_B category, String itemType, G_564_y rank, R_4764_Y server) {
            this.n_1700_B = category;
            this.J_1907_R = itemType;
            this.R_4764_Y = rank;
            this.G_564_y = server;
        }

        public J_1907_R(n_1700_B category, String itemType, R_4764_Y server) {
            this(category, itemType, null, server);
        }

        public n_1700_B n_1700_B() {
            return this.n_1700_B;
        }

        public R_4764_Y J_1907_R() {
            return this.G_564_y;
        }

        public boolean R_4764_Y() {
            return this.n_1700_B == lightning.product.t_1509_b$n_1700_B.J_1907_R;
        }

        public boolean G_564_y() {
            return this.n_1700_B == lightning.product.t_1509_b$n_1700_B.R_4764_Y;
        }

        public boolean P_1922_E() {
            return this.n_1700_B == lightning.product.t_1509_b$n_1700_B.n_1700_B;
        }

        public boolean u_1723_Y() {
            return this.n_1700_B == lightning.product.t_1509_b$n_1700_B.G_564_y;
        }

        private boolean v_4262_N() {
            return this.J_1907_R != null && n_1700_B.contains(this.J_1907_R);
        }

        public String n_1700_B(Z_1993_T stack) {
            String name;
            if (this.n_1700_B == lightning.product.t_1509_b$n_1700_B.G_564_y) {
                return this.n_1700_B.n_1700_B();
            }
            if (this.n_1700_B == lightning.product.t_1509_b$n_1700_B.J_1907_R || this.n_1700_B == lightning.product.t_1509_b$n_1700_B.R_4764_Y) {
                if (this.v_4262_N()) {
                    return this.n_1700_B.n_1700_B() + " " + this.J_1907_R;
                }
                if (this.R_4764_Y != null) {
                    String rankName = this.n_1700_B == lightning.product.t_1509_b$n_1700_B.J_1907_R ? this.R_4764_Y.n_1700_B() : this.R_4764_Y.J_1907_R();
                    return rankName + " " + this.n_1700_B.n_1700_B().toLowerCase();
                }
                return this.n_1700_B.n_1700_B();
            }
            if (this.n_1700_B == lightning.product.t_1509_b$n_1700_B.n_1700_B && this.J_1907_R != null && (name = R_4764_Y.get(this.J_1907_R)) != null) {
                return name;
            }
            return t_1509_b.n_1700_B(stack != null ? stack.multiplayerClientSuggestionProvider() : null);
        }

        public int J_1907_R(Z_1993_T stack) {
            Integer color;
            if (this.G_564_y == lightning.product.t_1509_b$R_4764_Y.n_1700_B && this.J_1907_R != null && (color = G_564_y.get(this.J_1907_R)) != null) {
                return color;
            }
            return t_1509_b.J_1907_R(stack != null ? stack.multiplayerClientSuggestionProvider() : null);
        }

        public boolean n_1700_B(J_1907_R other) {
            if (other == null) {
                return false;
            }
            return this.n_1700_B == other.n_1700_B && Objects.equals(this.J_1907_R, other.J_1907_R) && this.R_4764_Y == other.R_4764_Y;
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("\u0420\u0430\u0441\u0445\u043e\u0434\u043d\u0438\u043a");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("\u0421\u0444\u0435\u0440\u0430");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("\u0422\u0430\u043b\u0438\u0441\u043c\u0430\u043d");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442");
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u043e");
        private final String u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String displayName) {
            this.u_1723_Y = displayName;
        }

        public String n_1700_B() {
            return this.u_1723_Y;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            v_4262_N = lightning.product.t_1509_b$n_1700_B.J_1907_R();
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y();
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y();
        public static final /* enum */ R_4764_Y G_564_y = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] P_1922_E;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])P_1922_E.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.t_1509_b$R_4764_Y.n_1700_B();
        }
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y("NORMAL", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439");
        public static final /* enum */ G_564_y J_1907_R = new G_564_y("EPIC", "\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0430\u044f", "\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0438\u0439");
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y("LEGENDARY", "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f", "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u044b\u0439");
        public static final /* enum */ G_564_y G_564_y = new G_564_y("MYTHICAL", "\u041c\u0438\u0444\u0438\u0447\u0435\u0441\u043a\u0430\u044f", "\u041c\u0438\u0444\u0438\u0447\u0435\u0441\u043a\u0438\u0439");
        public static final /* enum */ G_564_y P_1922_E = new G_564_y("ETERNITY", "Eternity", "Eternity");
        private final String u_1723_Y;
        private final String v_4262_N;
        private final String w_1484_f;
        private static final /* synthetic */ G_564_y[] t_148_a;

        public static G_564_y[] values() {
            return (G_564_y[])t_148_a.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private G_564_y(String nbtKey, String sphereName, String talismanName) {
            this.u_1723_Y = nbtKey;
            this.v_4262_N = sphereName;
            this.w_1484_f = talismanName;
        }

        public String n_1700_B() {
            return this.v_4262_N;
        }

        public String J_1907_R() {
            return this.w_1484_f;
        }

        public static G_564_y n_1700_B(String rank) {
            if (rank == null) {
                return null;
            }
            for (G_564_y r : lightning.product.t_1509_b$G_564_y.values()) {
                if (!r.u_1723_Y.equalsIgnoreCase(rank)) continue;
                return r;
            }
            return null;
        }

        private static /* synthetic */ G_564_y[] R_4764_Y() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            t_148_a = lightning.product.t_1509_b$G_564_y.R_4764_Y();
        }
    }
}



