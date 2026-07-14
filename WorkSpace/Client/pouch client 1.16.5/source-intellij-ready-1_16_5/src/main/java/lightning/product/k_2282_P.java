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

public class k_2282_P {
    private final int[] n_1700_B;
    private float[][][] J_1907_R;
    private float[][] R_4764_Y;
    private final Random G_564_y = new Random();

    public k_2282_P(int ... layers) {
        this.n_1700_B = layers;
        this.J_1907_R = new float[layers.length - 1][][];
        this.R_4764_Y = new float[layers.length - 1][];
        for (int l = 0; l < layers.length - 1; ++l) {
            int fanIn = layers[l];
            int fanOut = layers[l + 1];
            float scale = (float)Math.sqrt(2.0 / (double)fanIn);
            this.J_1907_R[l] = new float[fanOut][fanIn];
            this.R_4764_Y[l] = new float[fanOut];
            for (int i = 0; i < fanOut; ++i) {
                for (int j = 0; j < fanIn; ++j) {
                    this.J_1907_R[l][i][j] = (float)this.G_564_y.nextGaussian() * scale;
                }
            }
        }
    }

    public float[] n_1700_B(float[] input) {
        float[] x = input;
        for (int l = 0; l < this.J_1907_R.length; ++l) {
            float[] y = new float[this.J_1907_R[l].length];
            for (int i = 0; i < y.length; ++i) {
                float sum = this.R_4764_Y[l][i];
                for (int j = 0; j < x.length; ++j) {
                    sum += this.J_1907_R[l][i][j] * x[j];
                }
                y[i] = l < this.J_1907_R.length - 1 ? (float)Math.tanh(sum) : sum;
            }
            x = y;
        }
        return x;
    }

    public float n_1700_B(float[][] inputs, float[][] targets, float lr, int batchSize) {
        int n = inputs.length;
        int[] idx = this.n_1700_B(n);
        float totalLoss = 0.0f;
        for (int b = 0; b < n; b += batchSize) {
            int end = Math.min(b + batchSize, n);
            float[][][] wg = this.J_1907_R();
            float[][] bg = this.R_4764_Y();
            for (int s = b; s < end; ++s) {
                float[][] acts = this.J_1907_R(inputs[idx[s]]);
                float[] err = new float[this.n_1700_B[this.n_1700_B.length - 1]];
                for (int i = 0; i < err.length; ++i) {
                    err[i] = acts[this.n_1700_B.length - 1][i] - targets[idx[s]][i];
                    totalLoss += err[i] * err[i];
                }
                this.n_1700_B(acts, err, wg, bg);
            }
            this.n_1700_B(wg, bg, lr / (float)(end - b));
        }
        return totalLoss / (float)n;
    }

    private float[][] J_1907_R(float[] input) {
        float[][] acts = new float[this.n_1700_B.length][];
        acts[0] = input;
        for (int l = 0; l < this.J_1907_R.length; ++l) {
            acts[l + 1] = new float[this.J_1907_R[l].length];
            for (int i = 0; i < acts[l + 1].length; ++i) {
                float sum = this.R_4764_Y[l][i];
                for (int j = 0; j < acts[l].length; ++j) {
                    sum += this.J_1907_R[l][i][j] * acts[l][j];
                }
                acts[l + 1][i] = l < this.J_1907_R.length - 1 ? (float)Math.tanh(sum) : sum;
            }
        }
        return acts;
    }

    private void n_1700_B(float[][] acts, float[] err, float[][][] wg, float[][] bg) {
        float[] delta = err;
        for (int l = this.J_1907_R.length - 1; l >= 0; --l) {
            float[] next = new float[this.n_1700_B[l]];
            for (int i = 0; i < this.J_1907_R[l].length; ++i) {
                float d = l < this.J_1907_R.length - 1 ? delta[i] * (1.0f - acts[l + 1][i] * acts[l + 1][i]) : delta[i];
                float[] fArray = bg[l];
                int n = i;
                fArray[n] = fArray[n] + d;
                for (int j = 0; j < this.J_1907_R[l][i].length; ++j) {
                    float[] fArray2 = wg[l][i];
                    int n2 = j;
                    fArray2[n2] = fArray2[n2] + d * acts[l][j];
                    int n3 = j;
                    next[n3] = next[n3] + d * this.J_1907_R[l][i][j];
                }
            }
            delta = next;
        }
    }

    private void n_1700_B(float[][][] wg, float[][] bg, float scale) {
        for (int l = 0; l < this.J_1907_R.length; ++l) {
            for (int i = 0; i < this.J_1907_R[l].length; ++i) {
                float[] fArray = this.R_4764_Y[l];
                int n = i;
                fArray[n] = fArray[n] - bg[l][i] * scale;
                for (int j = 0; j < this.J_1907_R[l][i].length; ++j) {
                    float[] fArray2 = this.J_1907_R[l][i];
                    int n2 = j;
                    fArray2[n2] = fArray2[n2] - wg[l][i][j] * scale;
                }
            }
        }
    }

    private float[][][] J_1907_R() {
        float[][][] g = new float[this.J_1907_R.length][][];
        for (int l = 0; l < this.J_1907_R.length; ++l) {
            g[l] = new float[this.J_1907_R[l].length][this.J_1907_R[l][0].length];
        }
        return g;
    }

    private float[][] R_4764_Y() {
        float[][] g = new float[this.R_4764_Y.length][];
        for (int l = 0; l < this.R_4764_Y.length; ++l) {
            g[l] = new float[this.R_4764_Y[l].length];
        }
        return g;
    }

    private int[] n_1700_B(int n) {
        int i;
        int[] a = new int[n];
        for (i = 0; i < n; ++i) {
            a[i] = i;
        }
        for (i = n - 1; i > 0; --i) {
            int j = this.G_564_y.nextInt(i + 1);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        return a;
    }

    public JsonObject n_1700_B() {
        JsonObject obj = new JsonObject();
        JsonArray la = new JsonArray();
        for (int l : this.n_1700_B) {
            la.add((Number)l);
        }
        obj.add("layers", (JsonElement)la);
        JsonArray wa = new JsonArray();
        for (float[][] wl : this.J_1907_R) {
            JsonArray layer = new JsonArray();
            for (float[] row : wl) {
                JsonArray r = new JsonArray();
                for (float v : row) {
                    r.add((Number)Float.valueOf(v));
                }
                layer.add((JsonElement)r);
            }
            wa.add((JsonElement)layer);
        }
        obj.add("weights", (JsonElement)wa);
        JsonArray ba = new JsonArray();
        for (float[] bl : this.R_4764_Y) {
            JsonArray layer = new JsonArray();
            for (float v : bl) {
                layer.add((Number)Float.valueOf(v));
            }
            ba.add((JsonElement)layer);
        }
        obj.add("biases", (JsonElement)ba);
        return obj;
    }

    public static k_2282_P n_1700_B(JsonObject obj) {
        JsonArray la = obj.getAsJsonArray("layers");
        int[] layers = new int[la.size()];
        for (int i = 0; i < layers.length; ++i) {
            layers[i] = la.get(i).getAsInt();
        }
        k_2282_P nn = new k_2282_P(layers);
        JsonArray wa = obj.getAsJsonArray("weights");
        for (int l = 0; l < nn.J_1907_R.length; ++l) {
            JsonArray layer = wa.get(l).getAsJsonArray();
            for (int i = 0; i < nn.J_1907_R[l].length; ++i) {
                JsonArray row = layer.get(i).getAsJsonArray();
                for (int j = 0; j < nn.J_1907_R[l][i].length; ++j) {
                    nn.J_1907_R[l][i][j] = row.get(j).getAsFloat();
                }
            }
        }
        JsonArray ba = obj.getAsJsonArray("biases");
        for (int l = 0; l < nn.R_4764_Y.length; ++l) {
            JsonArray layer = ba.get(l).getAsJsonArray();
            for (int i = 0; i < nn.R_4764_Y[l].length; ++i) {
                nn.R_4764_Y[l][i] = layer.get(i).getAsFloat();
            }
        }
        return nn;
    }
}

