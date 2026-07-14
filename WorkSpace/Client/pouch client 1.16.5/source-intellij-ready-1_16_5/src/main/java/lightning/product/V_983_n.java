/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lightning.product.ItemTransforms;
import lightning.product.Module;
import lightning.product.Z_1993_T;
import lightning.product.g_221_o;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.ModeSetting;
import lightning.product.SwordItem;
import lightning.product.ModuleCategory;
import lightning.product.z_2759_Q;

public class V_983_n
extends Module {
    private static final n_1700_B[] v_4262_N = V_983_n.h_1847_R();
    private static final Map<String, n_1700_B> w_1484_f = V_983_n.Q_4569_t();
    private static V_983_n t_148_a;
    private final ModeSetting s_956_w;
    private String u_2550_I;
    private n_1700_B M_588_G;

    private static n_1700_B[] h_1847_R() {
        String[][] data = new String[][]{{"Abominable Blade", "abominableblade"}, {"Abominable Greatsaber", "abominablegreatsaber"}, {"Abominable Scythe", "abominablescythe"}, {"Acid Demon", "aciddemon"}, {"Amethyst Shuriken", "amethyst_shuriken"}, {"Ancient Royal Greatsword", "ancient_royal_great_sword"}, {"Aquantic Sacred Blade", "aquantic_sacred_blade"}, {"Aquantic Trident", "aquantictrident"}, {"Arcanethyst", "arcanethyst"}, {"Ashura Blade", "ashura_blade"}, {"Awakened Lichblade", "awakened_lichblade"}, {"Blood Edge", "bloodedge"}, {"Bloody Death", "bloodydeath"}, {"Bramblethorn", "bramblethorn"}, {"Brimstone Claymore", "brimstone_claymore"}, {"Carian Sword", "cariansword"}, {"Chrono Blade", "chrono_blade"}, {"Corrupted Mythic Blade", "corruptedmythicblade"}, {"Creation Splitter", "creationsplitter"}, {"Crescent Rose", "crescentrose"}, {"Cyber Katana", "cyberkatana"}, {"Cyber Mantis Blade", "cybermantisblade"}, {"Cybernetic Katana", "cybernetickatana"}, {"Cybernetic Knife", "cyberneticknife"}, {"Cybernetic Sawblade", "cyberneticsawblade"}, {"Cyber Sword", "cybersword"}, {"Dainsleif", "dainsleif"}, {"Dark Blade", "dark_blade"}, {"Dark Cleaver", "dark_cleaver"}, {"Death Knight Dagger", "death_knight_dagger"}, {"Death Knight Sword", "death_knight_sword"}, {"Demigod's Unholy Blade", "demigodsunholyblade"}, {"Demigod's Unholy Halberd", "demigodsunholyhalberd"}, {"Demonic Blade", "demonicblade"}, {"Demonic Cleaver", "demoniccleaver"}, {"Demon Lord's Great Axe", "demonlordsgreataxe"}, {"Demon Lord's Sword", "demonlordsword"}, {"Divine Justice", "divine_justice"}, {"Divine Reaper", "divine_reaper"}, {"Divine Axe Rhitta", "divineaxerhitta"}, {"Divine Punisher", "divinepunisher"}, {"Dragon Slaying Blade", "dragonslayingblade"}, {"Edge of the Astral Plane", "edgeoftheastralplane"}, {"Emberblade", "emberblade"}, {"Enigma", "enigma"}, {"Epic Sword", "epicsword"}, {"Estoc", "estoc"}, {"Excalibur", "excalibur"}, {"Fallen God Spear", "fallengodspear"}, {"Fallen God Sword", "fallengodsword"}, {"Floral Longsword", "floral_longsword"}, {"Floral Sabre", "floral_sabre"}, {"Forest Guardian Glaive", "forest_guardian_glaive"}, {"Frost Axe", "frostaxe"}, {"Frostblade", "frostblade"}, {"Frost Scythe", "frostscythe"}, {"Green Scythe", "greenscythe"}, {"Hearthflame", "hearthflame"}, {"Hero Sword", "herosword"}, {"Holy Moonlight Sword", "holymoonlightsword"}, {"Hornet's Needle", "hornetsneedle"}, {"Ice Whisper", "icewhisper"}, {"Jade Halberd", "jadehalberd"}, {"Katana", "katana"}, {"Legendary Sword", "legendarysword"}, {"Longsword", "longsword"}, {"Magi Scythe", "magiscythe"}, {"Masamune", "masamune"}, {"Mjolnir", "mjolnir"}, {"Molten Blade", "moltenblade"}, {"Molten Sword", "moltensword"}, {"Muramasa", "muramasa"}, {"Mystical Spellblade", "mysticalspellblade"}, {"Mythic Blade", "mythicblade"}, {"Partisan", "partisan"}, {"Pharaoh's Treasure", "pharaohs_treasure"}, {"Phoenix Grace", "pheonixgrace"}, {"Powerfuse Hammer", "powerfusehammer"}, {"Powerfuse Sword", "powerfusesword"}, {"Requiem of Hell", "requiem_of_hell"}, {"Ribbon Cleaver", "ribboncleaver"}, {"Righteous Relic", "righteous_relic"}, {"Rivers of Blood", "riversofblood"}, {"Royal Chakram", "royalchakram"}, {"Royal Rapier", "royalrapier"}, {"Sabre", "sabre"}, {"Scissor Blade", "scissorblade"}, {"Sculk Cleaver", "sculkcleaver"}, {"Sculk Scythe", "sculkscythe"}, {"Sculk Sword", "sculksword"}, {"Sentinel's Will", "sentinels_will"}, {"Silverine Blade", "silverine_blade"}, {"Soul Claws", "soulclaws"}, {"Soul Edge", "souledge"}, {"Soul Harvester", "soulharvester"}, {"Soul Render", "soulrender"}, {"Soul Stealer", "soulstealer"}, {"Soul Collector", "soul_collector"}, {"Soul Devourer", "soul_devourer"}, {"Star's Edge", "stars_edge"}, {"Steel Sword", "steelsword"}, {"Stop Sign", "stop_sign"}, {"Stormbringer", "stormbringer"}, {"Storm's Edge", "storms_edge"}, {"Sunbreak", "sunbreak"}, {"Tengen's Blade", "tengensblade"}, {"Terrablade", "terrablade"}, {"Thousand Demon Daggers", "thousanddemondaggers"}, {"Thunderbrand", "thunderbrand"}, {"Thunderbringer", "thunderbringer"}, {"Toxic Longsword", "toxic_longsword"}, {"Vampiric Needle", "vampiricneedle"}, {"Wakizashi", "wakizashi"}, {"Watcher Claymore", "watcher_claymore"}, {"Watching Warglaive", "watching_warglaive"}, {"Waxweaver", "waxweaver"}, {"Whisperwind", "whisperwind"}, {"Wickpiercer", "wickpiercer"}, {"Yoru", "yoru"}};
        n_1700_B[] variants = new n_1700_B[data.length];
        for (int i = 0; i < data.length; ++i) {
            variants[i] = new n_1700_B(data[i][0], data[i][1]);
        }
        return variants;
    }

    private static Map<String, n_1700_B> Q_4569_t() {
        HashMap<String, n_1700_B> variants = new HashMap<String, n_1700_B>(v_4262_N.length * 2);
        for (n_1700_B variant : v_4262_N) {
            variants.put(variant.n_1700_B.toLowerCase(Locale.ROOT), variant);
        }
        return variants;
    }

    public V_983_n() {
        super("Item Replacer", "Replaces sword model without resource pack reload", ModuleCategory.P_1922_E);
        this.s_956_w = new ModeSetting("\u0412\u0438\u0434 \u043c\u0435\u0447\u0430", V_983_n.v_4262_N[0].n_1700_B, V_983_n.t_1786_h());
        this.M_588_G = v_4262_N[0];
        t_148_a = this;
        this.n_1700_B(this.s_956_w);
    }

    public static boolean n_1700_B(Z_1993_T stack, ItemTransforms.J_1907_R transformType, g_221_o matrixStack, o_3091_w buffer, int combinedLight, int combinedOverlay) {
        if (stack == null || stack.n_1700_B() || !(stack.J_1907_R() instanceof SwordItem)) {
            return false;
        }
        V_983_n replacer = t_148_a;
        if (replacer == null) {
            t_148_a = replacer = (V_983_n)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(V_983_n.class);
        }
        if (replacer == null || !replacer.w_1484_f()) {
            return false;
        }
        n_1700_B variant = replacer.M_182_A();
        variant.J_1907_R.n_1700_B(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay);
        return true;
    }

    private n_1700_B M_182_A() {
        String selected = (String)this.s_956_w.J_1907_R();
        if (selected == null) {
            return v_4262_N[0];
        }
        if (this.u_2550_I != null && selected.equalsIgnoreCase(this.u_2550_I)) {
            return this.M_588_G;
        }
        n_1700_B variant = w_1484_f.get(selected.toLowerCase(Locale.ROOT));
        if (variant == null) {
            variant = v_4262_N[0];
        }
        this.u_2550_I = selected;
        this.M_588_G = variant;
        return variant;
    }

    private static String[] t_1786_h() {
        String[] result = new String[v_4262_N.length];
        for (int i = 0; i < v_4262_N.length; ++i) {
            result[i] = V_983_n.v_4262_N[i].n_1700_B;
        }
        return result;
    }

    private static class n_1700_B {
        final String n_1700_B;
        final z_2759_Q J_1907_R;

        n_1700_B(String label, String modelName) {
            this.n_1700_B = label;
            this.J_1907_R = new z_2759_Q(modelName);
        }
    }
}



