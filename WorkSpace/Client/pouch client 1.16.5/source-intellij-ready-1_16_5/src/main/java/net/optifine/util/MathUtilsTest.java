/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.u_530_F;
import net.optifine.util.MathUtils;

public class MathUtilsTest {
    public static void main(String[] args) throws Exception {
        OPER[] amathutilstest$oper = OPER.values();
        for (int i = 0; i < amathutilstest$oper.length; ++i) {
            OPER mathutilstest$oper = amathutilstest$oper[i];
            MathUtilsTest.dbg("******** " + String.valueOf((Object)mathutilstest$oper) + " ***********");
            MathUtilsTest.test(mathutilstest$oper, false);
        }
    }

    private static void test(OPER oper, boolean fast) {
        double d1;
        double d0;
        u_530_F.u_1723_Y = fast;
        switch (oper.ordinal()) {
            case 0: 
            case 1: {
                d0 = -u_530_F.J_1907_R;
                d1 = u_530_F.J_1907_R;
                break;
            }
            case 2: 
            case 3: {
                d0 = -1.0;
                d1 = 1.0;
                break;
            }
            default: {
                return;
            }
        }
        int i = 10;
        for (int j = 0; j <= i; ++j) {
            float f1;
            float f;
            double d2 = d0 + (double)j * (d1 - d0) / (double)i;
            switch (oper.ordinal()) {
                case 0: {
                    f = (float)Math.sin(d2);
                    f1 = u_530_F.n_1700_B((float)d2);
                    break;
                }
                case 1: {
                    f = (float)Math.cos(d2);
                    f1 = u_530_F.J_1907_R((float)d2);
                    break;
                }
                case 2: {
                    f = (float)Math.asin(d2);
                    f1 = MathUtils.asin((float)d2);
                    break;
                }
                case 3: {
                    f = (float)Math.acos(d2);
                    f1 = MathUtils.acos((float)d2);
                    break;
                }
                default: {
                    return;
                }
            }
            MathUtilsTest.dbg(String.format("%.2f, Math: %f, Helper: %f, diff: %f", d2, Float.valueOf(f), Float.valueOf(f1), Float.valueOf(Math.abs(f - f1))));
        }
    }

    public static void dbg(String str) {
        System.out.println(str);
    }

    private static enum OPER {
        SIN,
        COS,
        ASIN,
        ACOS;

    }
}

