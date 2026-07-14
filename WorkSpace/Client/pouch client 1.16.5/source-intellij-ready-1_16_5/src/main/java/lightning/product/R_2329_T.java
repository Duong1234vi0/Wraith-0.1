/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Random;

public class R_2329_T {
    private final int n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private float[][] G_564_y;
    private float[][] P_1922_E;
    private float[] u_1723_Y;
    private float[][] v_4262_N;
    private float[] w_1484_f;
    private float[] t_148_a;
    private float[] s_956_w;
    private float[][] u_2550_I;
    private float[][] M_588_G;
    private float[][] P_4830_p;
    private float[][] h_1847_R;
    private float[][] Q_4569_t;
    private float[][] M_182_A;
    private float[] t_1786_h;
    private float[] multiplayerClientSuggestionProvider;
    private float[] w_1457_N;
    private float[] Y_601_j;
    private int Y_259_p;
    private final Random Q_2552_b = new Random();

    public R_2329_T(int inputSize, int hiddenSize, int outputSize) {
        int j;
        int i;
        this.n_1700_B = inputSize;
        this.J_1907_R = hiddenSize;
        this.R_4764_Y = outputSize;
        int G = 4 * hiddenSize;
        this.G_564_y = new float[G][inputSize];
        this.P_1922_E = new float[G][hiddenSize];
        this.u_1723_Y = new float[G];
        this.v_4262_N = new float[outputSize][hiddenSize];
        this.w_1484_f = new float[outputSize];
        float sX = (float)Math.sqrt(1.0 / (double)inputSize);
        float sH = (float)Math.sqrt(1.0 / (double)hiddenSize);
        float sY = (float)Math.sqrt(2.0 / (double)(hiddenSize + outputSize));
        for (i = 0; i < G; ++i) {
            for (j = 0; j < inputSize; ++j) {
                this.G_564_y[i][j] = (float)this.Q_2552_b.nextGaussian() * sX;
            }
            for (j = 0; j < hiddenSize; ++j) {
                this.P_1922_E[i][j] = (float)this.Q_2552_b.nextGaussian() * sH;
            }
        }
        for (i = 0; i < hiddenSize; ++i) {
            this.u_1723_Y[i] = 1.0f;
        }
        for (i = 0; i < outputSize; ++i) {
            for (j = 0; j < hiddenSize; ++j) {
                this.v_4262_N[i][j] = (float)this.Q_2552_b.nextGaussian() * sY;
            }
        }
        this.t_148_a = new float[hiddenSize];
        this.s_956_w = new float[hiddenSize];
    }

    public void n_1700_B() {
        this.t_148_a = new float[this.J_1907_R];
        this.s_956_w = new float[this.J_1907_R];
    }

    public float[] n_1700_B(float[] x) {
        int H = this.J_1907_R;
        float[] newC = new float[H];
        float[] newH = new float[H];
        for (int i = 0; i < H; ++i) {
            int j;
            float sf = this.u_1723_Y[i];
            float si = this.u_1723_Y[H + i];
            float sg = this.u_1723_Y[2 * H + i];
            float so = this.u_1723_Y[3 * H + i];
            for (j = 0; j < this.n_1700_B; ++j) {
                sf += this.G_564_y[i][j] * x[j];
                si += this.G_564_y[H + i][j] * x[j];
                sg += this.G_564_y[2 * H + i][j] * x[j];
                so += this.G_564_y[3 * H + i][j] * x[j];
            }
            for (j = 0; j < H; ++j) {
                sf += this.P_1922_E[i][j] * this.t_148_a[j];
                si += this.P_1922_E[H + i][j] * this.t_148_a[j];
                sg += this.P_1922_E[2 * H + i][j] * this.t_148_a[j];
                so += this.P_1922_E[3 * H + i][j] * this.t_148_a[j];
            }
            float f = this.n_1700_B(sf);
            float in_ = this.n_1700_B(si);
            float g = this.J_1907_R(sg);
            float o = this.n_1700_B(so);
            newC[i] = f * this.s_956_w[i] + in_ * g;
            newH[i] = o * this.J_1907_R(newC[i]);
        }
        this.s_956_w = newC;
        this.t_148_a = newH;
        float[] y = new float[this.R_4764_Y];
        for (int i = 0; i < this.R_4764_Y; ++i) {
            float s = this.w_1484_f[i];
            for (int j = 0; j < H; ++j) {
                s += this.v_4262_N[i][j] * this.t_148_a[j];
            }
            y[i] = s;
        }
        return y;
    }

    public float n_1700_B(float[][] inputs, float[][] targets, float lr, int seqLen) {
        int T = inputs.length;
        if (T == 0) {
            return 0.0f;
        }
        this.R_4764_Y();
        int H = this.J_1907_R;
        int G = 4 * H;
        float totalLoss = 0.0f;
        float[] hRun = new float[H];
        float[] cRun = new float[H];
        for (int start = 0; start < T; start += seqLen) {
            int end = Math.min(start + seqLen, T);
            int len = end - start;
            float[][] sH = new float[len + 1][H];
            float[][] sC = new float[len + 1][H];
            float[][] sF = new float[len][H];
            float[][] sI = new float[len][H];
            float[][] sG = new float[len][H];
            float[][] sO = new float[len][H];
            float[][] sTC = new float[len][H];
            float[][] sY = new float[len][this.R_4764_Y];
            System.arraycopy(hRun, 0, sH[0], 0, H);
            System.arraycopy(cRun, 0, sC[0], 0, H);
            for (int t = 0; t < len; ++t) {
                int i;
                float[] x = inputs[start + t];
                for (i = 0; i < H; ++i) {
                    int j;
                    float sf = this.u_1723_Y[i];
                    float si = this.u_1723_Y[H + i];
                    float sg = this.u_1723_Y[2 * H + i];
                    float so = this.u_1723_Y[3 * H + i];
                    for (j = 0; j < this.n_1700_B; ++j) {
                        sf += this.G_564_y[i][j] * x[j];
                        si += this.G_564_y[H + i][j] * x[j];
                        sg += this.G_564_y[2 * H + i][j] * x[j];
                        so += this.G_564_y[3 * H + i][j] * x[j];
                    }
                    for (j = 0; j < H; ++j) {
                        sf += this.P_1922_E[i][j] * sH[t][j];
                        si += this.P_1922_E[H + i][j] * sH[t][j];
                        sg += this.P_1922_E[2 * H + i][j] * sH[t][j];
                        so += this.P_1922_E[3 * H + i][j] * sH[t][j];
                    }
                    sF[t][i] = this.n_1700_B(sf);
                    sI[t][i] = this.n_1700_B(si);
                    sG[t][i] = this.J_1907_R(sg);
                    sO[t][i] = this.n_1700_B(so);
                    sC[t + 1][i] = sF[t][i] * sC[t][i] + sI[t][i] * sG[t][i];
                    sTC[t][i] = this.J_1907_R(sC[t + 1][i]);
                    sH[t + 1][i] = sO[t][i] * sTC[t][i];
                }
                for (i = 0; i < this.R_4764_Y; ++i) {
                    float s = this.w_1484_f[i];
                    for (int j = 0; j < H; ++j) {
                        s += this.v_4262_N[i][j] * sH[t + 1][j];
                    }
                    sY[t][i] = s;
                }
                for (i = 0; i < this.R_4764_Y; ++i) {
                    float d = sY[t][i] - targets[start + t][i];
                    totalLoss += d * d;
                }
            }
            System.arraycopy(sH[len], 0, hRun, 0, H);
            System.arraycopy(sC[len], 0, cRun, 0, H);
            float[][] gWx = new float[G][this.n_1700_B];
            float[][] gWh = new float[G][H];
            float[] gb = new float[G];
            float[][] gWy = new float[this.R_4764_Y][H];
            float[] gby = new float[this.R_4764_Y];
            float[] dhN = new float[H];
            float[] dcN = new float[H];
            for (int t = len - 1; t >= 0; --t) {
                int i;
                int i2;
                int j;
                int i3;
                float[] x = inputs[start + t];
                float[] dy = new float[this.R_4764_Y];
                for (i3 = 0; i3 < this.R_4764_Y; ++i3) {
                    dy[i3] = (sY[t][i3] - targets[start + t][i3]) / (float)len;
                }
                for (i3 = 0; i3 < this.R_4764_Y; ++i3) {
                    int n = i3;
                    gby[n] = gby[n] + dy[i3];
                    for (j = 0; j < H; ++j) {
                        float[] fArray = gWy[i3];
                        int n2 = j;
                        fArray[n2] = fArray[n2] + dy[i3] * sH[t + 1][j];
                    }
                }
                float[] dh = new float[H];
                for (j = 0; j < H; ++j) {
                    for (i2 = 0; i2 < this.R_4764_Y; ++i2) {
                        int n = j;
                        dh[n] = dh[n] + this.v_4262_N[i2][j] * dy[i2];
                    }
                    int n = j;
                    dh[n] = dh[n] + dhN[j];
                }
                float[] dc = new float[H];
                for (i2 = 0; i2 < H; ++i2) {
                    dc[i2] = dh[i2] * sO[t][i2] * (1.0f - sTC[t][i2] * sTC[t][i2]) + dcN[i2];
                }
                float[] df = new float[H];
                float[] di = new float[H];
                float[] dg = new float[H];
                float[] dout = new float[H];
                for (i = 0; i < H; ++i) {
                    df[i] = dc[i] * sC[t][i] * sF[t][i] * (1.0f - sF[t][i]);
                    di[i] = dc[i] * sG[t][i] * sI[t][i] * (1.0f - sI[t][i]);
                    dg[i] = dc[i] * sI[t][i] * (1.0f - sG[t][i] * sG[t][i]);
                    dout[i] = dh[i] * sTC[t][i] * sO[t][i] * (1.0f - sO[t][i]);
                }
                for (i = 0; i < H; ++i) {
                    int j2;
                    int n = i;
                    gb[n] = gb[n] + df[i];
                    int n3 = H + i;
                    gb[n3] = gb[n3] + di[i];
                    int n4 = 2 * H + i;
                    gb[n4] = gb[n4] + dg[i];
                    int n5 = 3 * H + i;
                    gb[n5] = gb[n5] + dout[i];
                    for (j2 = 0; j2 < this.n_1700_B; ++j2) {
                        float[] fArray = gWx[i];
                        int n6 = j2;
                        fArray[n6] = fArray[n6] + df[i] * x[j2];
                        float[] fArray2 = gWx[H + i];
                        int n7 = j2;
                        fArray2[n7] = fArray2[n7] + di[i] * x[j2];
                        float[] fArray3 = gWx[2 * H + i];
                        int n8 = j2;
                        fArray3[n8] = fArray3[n8] + dg[i] * x[j2];
                        float[] fArray4 = gWx[3 * H + i];
                        int n9 = j2;
                        fArray4[n9] = fArray4[n9] + dout[i] * x[j2];
                    }
                    for (j2 = 0; j2 < H; ++j2) {
                        float[] fArray = gWh[i];
                        int n10 = j2;
                        fArray[n10] = fArray[n10] + df[i] * sH[t][j2];
                        float[] fArray5 = gWh[H + i];
                        int n11 = j2;
                        fArray5[n11] = fArray5[n11] + di[i] * sH[t][j2];
                        float[] fArray6 = gWh[2 * H + i];
                        int n12 = j2;
                        fArray6[n12] = fArray6[n12] + dg[i] * sH[t][j2];
                        float[] fArray7 = gWh[3 * H + i];
                        int n13 = j2;
                        fArray7[n13] = fArray7[n13] + dout[i] * sH[t][j2];
                    }
                }
                dhN = new float[H];
                for (int j3 = 0; j3 < H; ++j3) {
                    for (int i4 = 0; i4 < H; ++i4) {
                        int n = j3;
                        dhN[n] = dhN[n] + (this.P_1922_E[i4][j3] * df[i4] + this.P_1922_E[H + i4][j3] * di[i4] + this.P_1922_E[2 * H + i4][j3] * dg[i4] + this.P_1922_E[3 * H + i4][j3] * dout[i4]);
                    }
                }
                dcN = new float[H];
                for (i = 0; i < H; ++i) {
                    dcN[i] = dc[i] * sF[t][i];
                }
            }
            this.J_1907_R(gWx, gWh, gb, gWy, gby, 5.0f);
            this.n_1700_B(gWx, gWh, gb, gWy, gby, lr);
        }
        return totalLoss / (float)T;
    }

    private void R_4764_Y() {
        if (this.u_2550_I != null) {
            return;
        }
        int G = 4 * this.J_1907_R;
        this.u_2550_I = new float[G][this.n_1700_B];
        this.M_588_G = new float[G][this.n_1700_B];
        this.P_4830_p = new float[G][this.J_1907_R];
        this.h_1847_R = new float[G][this.J_1907_R];
        this.t_1786_h = new float[G];
        this.multiplayerClientSuggestionProvider = new float[G];
        this.Q_4569_t = new float[this.R_4764_Y][this.J_1907_R];
        this.M_182_A = new float[this.R_4764_Y][this.J_1907_R];
        this.w_1457_N = new float[this.R_4764_Y];
        this.Y_601_j = new float[this.R_4764_Y];
        this.Y_259_p = 0;
    }

    private void n_1700_B(float[][] gWx, float[][] gWh, float[] gb, float[][] gWy, float[] gby, float lr) {
        int j;
        int i;
        float b1 = 0.9f;
        float b2 = 0.999f;
        float eps = 1.0E-8f;
        ++this.Y_259_p;
        float b1c = 1.0f - (float)Math.pow(b1, this.Y_259_p);
        float b2c = 1.0f - (float)Math.pow(b2, this.Y_259_p);
        int G = 4 * this.J_1907_R;
        for (i = 0; i < G; ++i) {
            for (j = 0; j < this.n_1700_B; ++j) {
                this.G_564_y[i][j] = this.n_1700_B(this.G_564_y[i][j], gWx[i][j], this.u_2550_I[i], this.M_588_G[i], j, lr, b1, b2, b1c, b2c, eps);
            }
            for (j = 0; j < this.J_1907_R; ++j) {
                this.P_1922_E[i][j] = this.n_1700_B(this.P_1922_E[i][j], gWh[i][j], this.P_4830_p[i], this.h_1847_R[i], j, lr, b1, b2, b1c, b2c, eps);
            }
            this.u_1723_Y[i] = this.J_1907_R(this.u_1723_Y[i], gb[i], this.t_1786_h, this.multiplayerClientSuggestionProvider, i, lr, b1, b2, b1c, b2c, eps);
        }
        for (i = 0; i < this.R_4764_Y; ++i) {
            for (j = 0; j < this.J_1907_R; ++j) {
                this.v_4262_N[i][j] = this.n_1700_B(this.v_4262_N[i][j], gWy[i][j], this.Q_4569_t[i], this.M_182_A[i], j, lr, b1, b2, b1c, b2c, eps);
            }
            this.w_1484_f[i] = this.J_1907_R(this.w_1484_f[i], gby[i], this.w_1457_N, this.Y_601_j, i, lr, b1, b2, b1c, b2c, eps);
        }
    }

    private float n_1700_B(float w, float g, float[] m, float[] v, int j, float lr, float b1, float b2, float b1c, float b2c, float eps) {
        m[j] = b1 * m[j] + (1.0f - b1) * g;
        v[j] = b2 * v[j] + (1.0f - b2) * g * g;
        return w - lr * (m[j] / b1c) / ((float)Math.sqrt(v[j] / b2c) + eps);
    }

    private float J_1907_R(float w, float g, float[] m, float[] v, int i, float lr, float b1, float b2, float b1c, float b2c, float eps) {
        m[i] = b1 * m[i] + (1.0f - b1) * g;
        v[i] = b2 * v[i] + (1.0f - b2) * g * g;
        return w - lr * (m[i] / b1c) / ((float)Math.sqrt(v[i] / b2c) + eps);
    }

    private void J_1907_R(float[][] gWx, float[][] gWh, float[] gb, float[][] gWy, float[] gby, float maxNorm) {
        int j;
        float[] r;
        int n;
        float norm = 0.0f;
        float[][] object = gWx;
        int n2 = object.length;
        for (n = 0; n < n2; ++n) {
            for (float v : r = object[n]) {
                norm += v * v;
            }
        }
        float[][] fArray = gWh;
        n2 = fArray.length;
        for (n = 0; n < n2; ++n) {
            for (float v : r = fArray[n]) {
                norm += v * v;
            }
        }
        for (float v : gb) {
            norm += v * v;
        }
        for (float[] r2 : gWy) {
            for (float v : r2) {
                norm += v * v;
            }
        }
        for (float v : gby) {
            norm += v * v;
        }
        if ((norm = (float)Math.sqrt(norm)) <= maxNorm) {
            return;
        }
        float f = maxNorm / norm;
        for (float[] r3 : gWx) {
            j = 0;
            while (j < r3.length) {
                int n3 = j++;
                r3[n3] = r3[n3] * f;
            }
        }
        for (float[] r2 : gWh) {
            j = 0;
            while (j < r2.length) {
                int n4 = j++;
                r2[n4] = r2[n4] * f;
            }
        }
        int i = 0;
        while (i < gb.length) {
            int n5 = i++;
            gb[n5] = gb[n5] * f;
        }
        for (float[] r2 : gWy) {
            j = 0;
            while (j < r2.length) {
                int n6 = j++;
                r2[n6] = r2[n6] * f;
            }
        }
        i = 0;
        while (i < gby.length) {
            int n7 = i++;
            gby[n7] = gby[n7] * f;
        }
    }

    private float n_1700_B(float x) {
        return 1.0f / (1.0f + (float)Math.exp(-x));
    }

    private float J_1907_R(float x) {
        return (float)Math.tanh(x);
    }

    public JsonObject J_1907_R() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "lstm");
        obj.addProperty("inputSize", (Number)this.n_1700_B);
        obj.addProperty("hiddenSize", (Number)this.J_1907_R);
        obj.addProperty("outputSize", (Number)this.R_4764_Y);
        obj.add("Wx", (JsonElement)R_2329_T.n_1700_B(this.G_564_y));
        obj.add("Wh", (JsonElement)R_2329_T.n_1700_B(this.P_1922_E));
        obj.add("b", (JsonElement)R_2329_T.J_1907_R(this.u_1723_Y));
        obj.add("Wy", (JsonElement)R_2329_T.n_1700_B(this.v_4262_N));
        obj.add("by", (JsonElement)R_2329_T.J_1907_R(this.w_1484_f));
        return obj;
    }

    public static R_2329_T n_1700_B(JsonObject obj) {
        int in = obj.get("inputSize").getAsInt();
        int hid = obj.get("hiddenSize").getAsInt();
        int out = obj.get("outputSize").getAsInt();
        R_2329_T net = new R_2329_T(in, hid, out);
        net.G_564_y = R_2329_T.n_1700_B(obj.getAsJsonArray("Wx"));
        net.P_1922_E = R_2329_T.n_1700_B(obj.getAsJsonArray("Wh"));
        net.u_1723_Y = R_2329_T.J_1907_R(obj.getAsJsonArray("b"));
        net.v_4262_N = R_2329_T.n_1700_B(obj.getAsJsonArray("Wy"));
        net.w_1484_f = R_2329_T.J_1907_R(obj.getAsJsonArray("by"));
        return net;
    }

    private static JsonArray n_1700_B(float[][] m) {
        JsonArray a = new JsonArray();
        for (float[] row : m) {
            JsonArray r = new JsonArray();
            for (float v : row) {
                r.add((Number)Float.valueOf(v));
            }
            a.add((JsonElement)r);
        }
        return a;
    }

    private static JsonArray J_1907_R(float[] v) {
        JsonArray a = new JsonArray();
        for (float f : v) {
            a.add((Number)Float.valueOf(f));
        }
        return a;
    }

    private static float[][] n_1700_B(JsonArray a) {
        float[][] m = new float[a.size()][];
        for (int i = 0; i < a.size(); ++i) {
            JsonArray row = a.get(i).getAsJsonArray();
            m[i] = new float[row.size()];
            for (int j = 0; j < row.size(); ++j) {
                m[i][j] = row.get(j).getAsFloat();
            }
        }
        return m;
    }

    private static float[] J_1907_R(JsonArray a) {
        float[] v = new float[a.size()];
        for (int i = 0; i < a.size(); ++i) {
            v[i] = a.get(i).getAsFloat();
        }
        return v;
    }
}


