/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.UUID;
import lightning.product.j_3341_s;

public class U_253_b {
    private static final String[] n_1700_B = new String[]{"Slim", "Far", "River", "Silly", "Fat", "Thin", "Fish", "Bat", "Dark", "Oak", "Sly", "Bush", "Zen", "Bark", "Cry", "Slack", "Soup", "Grim", "Hook", "Dirt", "Mud", "Sad", "Hard", "Crook", "Sneak", "Stink", "Weird", "Fire", "Soot", "Soft", "Rough", "Cling", "Scar"};
    private static final String[] J_1907_R = new String[]{"Fox", "Tail", "Jaw", "Whisper", "Twig", "Root", "Finder", "Nose", "Brow", "Blade", "Fry", "Seek", "Wart", "Tooth", "Foot", "Leaf", "Stone", "Fall", "Face", "Tongue", "Voice", "Lip", "Mouth", "Snail", "Toe", "Ear", "Hair", "Beard", "Shirt", "Fist"};

    public static String n_1700_B(UUID uuid) {
        Random random = U_253_b.J_1907_R(uuid);
        return U_253_b.n_1700_B(random, n_1700_B) + U_253_b.n_1700_B(random, J_1907_R);
    }

    private static String n_1700_B(Random rand, String[] strings) {
        return j_3341_s.n_1700_B(strings, rand);
    }

    private static Random J_1907_R(UUID uuid) {
        return new Random(uuid.hashCode() >> 2);
    }
}

