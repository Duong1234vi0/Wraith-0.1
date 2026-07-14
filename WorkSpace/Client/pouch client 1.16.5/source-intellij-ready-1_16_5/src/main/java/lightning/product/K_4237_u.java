/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import lightning.product.H_2506_c;

public class K_4237_u {
    private static final Map<Integer, String> n_1700_B = new HashMap<Integer, String>();
    private static final Map<Integer, Integer> J_1907_R = new HashMap<Integer, Integer>();
    private static final int[] R_4764_Y = new int[]{42240, 42244, 42248, 42258, 42262, 42272, 42276, 42280, 42336, 42290, 42294, 42308, 42326, 42312, 42304, 42322, 42249, 42259, 42263, 42273, 42277, 42281, 42291, 42295, 42241, 42245, 42305, 42313};

    public static String n_1700_B(int codePoint) {
        return n_1700_B.get(codePoint);
    }

    public static int n_1700_B(int codePoint, int charIndex, int totalChars, float alpha, int currentColor) {
        boolean isRank = false;
        for (int rank : R_4764_Y) {
            if (rank != codePoint) continue;
            isRank = true;
            break;
        }
        if (isRank) {
            Integer baseColor = J_1907_R.get(codePoint);
            int endColor = H_2506_c.J_1907_R((int)baseColor, 0.8f);
            float ratio = (float)charIndex / (float)(totalChars - 1);
            int interpolatedColor = H_2506_c.n_1700_B((int)baseColor, endColor, ratio);
            return H_2506_c.n_1700_B(interpolatedColor, alpha);
        }
        return H_2506_c.n_1700_B(currentColor, alpha);
    }

    static {
        n_1700_B.put(9889, "");
        n_1700_B.put(9733, "");
        n_1700_B.put(42240, "PLAYER");
        n_1700_B.put(42244, "HERO");
        n_1700_B.put(42248, "TITAN");
        n_1700_B.put(42258, "AVENGER");
        n_1700_B.put(42262, "OVERLORD");
        n_1700_B.put(42272, "MAGISTER");
        n_1700_B.put(42276, "IMPERATOR");
        n_1700_B.put(42280, "DRAGON");
        n_1700_B.put(42336, "D.HELPER");
        n_1700_B.put(42290, "BULL");
        n_1700_B.put(42294, "TIGER");
        n_1700_B.put(42308, "DRACULA");
        n_1700_B.put(42326, "BUNNY");
        n_1700_B.put(42312, "COBRA");
        n_1700_B.put(42304, "HYDRA");
        n_1700_B.put(42322, "RABBIT");
        n_1700_B.put(42249, "HELPER");
        n_1700_B.put(42259, "ML.MODER");
        n_1700_B.put(42263, "MODER");
        n_1700_B.put(42273, "MODER+");
        n_1700_B.put(42277, "ST.MODER");
        n_1700_B.put(42281, "GL.MODER");
        n_1700_B.put(42291, "ML.ADMIN");
        n_1700_B.put(42295, "ADMIN");
        n_1700_B.put(42241, "MEDIA");
        n_1700_B.put(42245, "YT");
        n_1700_B.put(42305, "GOD");
        n_1700_B.put(42313, "PEGAS");
        n_1700_B.put(42309, "VAMPIRE");
        n_1700_B.put(42246, "VAMPIRE");
        n_1700_B.put(7424, "A");
        n_1700_B.put(665, "B");
        n_1700_B.put(7428, "C");
        n_1700_B.put(7429, "D");
        n_1700_B.put(7431, "E");
        n_1700_B.put(42800, "F");
        n_1700_B.put(610, "G");
        n_1700_B.put(668, "H");
        n_1700_B.put(618, "I");
        n_1700_B.put(7434, "J");
        n_1700_B.put(7435, "K");
        n_1700_B.put(671, "L");
        n_1700_B.put(7437, "M");
        n_1700_B.put(628, "N");
        n_1700_B.put(7439, "O");
        n_1700_B.put(7448, "P");
        n_1700_B.put(491, "Q");
        n_1700_B.put(640, "R");
        n_1700_B.put(7451, "T");
        n_1700_B.put(7452, "U");
        n_1700_B.put(42801, "S");
        n_1700_B.put(7456, "V");
        n_1700_B.put(7457, "W");
        n_1700_B.put(7521, "X");
        n_1700_B.put(655, "Y");
        n_1700_B.put(7458, "Z");
        J_1907_R.put(42313, H_2506_c.n_1700_B(255, 140, 0));
        J_1907_R.put(42240, H_2506_c.n_1700_B(120, 120, 120));
        J_1907_R.put(42244, H_2506_c.n_1700_B(100, 113, 251));
        J_1907_R.put(42248, H_2506_c.n_1700_B(214, 200, 42));
        J_1907_R.put(42258, H_2506_c.n_1700_B(101, 189, 56));
        J_1907_R.put(42262, H_2506_c.n_1700_B(64, 151, 214));
        J_1907_R.put(42272, H_2506_c.n_1700_B(202, 130, 60));
        J_1907_R.put(42276, H_2506_c.n_1700_B(202, 60, 60));
        J_1907_R.put(42280, H_2506_c.n_1700_B(245, 51, 238));
        J_1907_R.put(42336, H_2506_c.n_1700_B(214, 200, 42));
        J_1907_R.put(42290, H_2506_c.n_1700_B(121, 81, 202));
        J_1907_R.put(42294, H_2506_c.n_1700_B(202, 130, 60));
        J_1907_R.put(42308, H_2506_c.n_1700_B(202, 60, 60));
        J_1907_R.put(42326, H_2506_c.n_1700_B(68, 65, 66));
        J_1907_R.put(42312, H_2506_c.n_1700_B(127, 214, 86));
        J_1907_R.put(42304, H_2506_c.n_1700_B(92, 120, 7));
        J_1907_R.put(42322, H_2506_c.n_1700_B(120, 120, 120));
        J_1907_R.put(42249, H_2506_c.n_1700_B(214, 200, 42));
        J_1907_R.put(42259, H_2506_c.n_1700_B(100, 113, 251));
        J_1907_R.put(42263, H_2506_c.n_1700_B(100, 113, 251));
        J_1907_R.put(42273, H_2506_c.n_1700_B(121, 81, 202));
        J_1907_R.put(42277, H_2506_c.n_1700_B(100, 113, 251));
        J_1907_R.put(42281, H_2506_c.n_1700_B(121, 81, 202));
        J_1907_R.put(42291, H_2506_c.n_1700_B(64, 151, 214));
        J_1907_R.put(42295, H_2506_c.n_1700_B(202, 60, 60));
        J_1907_R.put(42241, H_2506_c.n_1700_B(121, 81, 202));
        J_1907_R.put(42245, H_2506_c.n_1700_B(255, 255, 255));
        J_1907_R.put(42305, H_2506_c.n_1700_B(214, 200, 42));
    }
}

