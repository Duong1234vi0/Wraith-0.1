/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3871_I;
import lightning.product.E_688_b;
import lightning.product.L_972_x;
import lightning.product.b_257_Y;
import net.minecraftforge.client.model.pipeline.IVertexConsumer;
import net.minecraftforge.client.model.pipeline.IVertexProducer;
import net.optifine.Config;
import net.optifine.model.BakedQuadRetextured;
import net.optifine.model.QuadBounds;
import net.optifine.reflect.Reflector;
import net.optifine.render.QuadVertexPositions;
import net.optifine.render.VertexPosition;

public class c_932_S
implements IVertexProducer {
    protected int[] vertexData;
    protected final int tintIndex;
    protected b_257_Y face;
    protected B_3871_I sprite;
    private final boolean applyDiffuseLighting;
    private int[] vertexDataSingle = null;
    private QuadBounds quadBounds;
    private boolean quadEmissiveChecked;
    private c_932_S quadEmissive;
    private QuadVertexPositions quadVertexPositions;

    public c_932_S(int[] vertexData, int tintIndex, b_257_Y face, B_3871_I sprite, boolean applyDiffuseLighting) {
        this.vertexData = vertexData;
        this.tintIndex = tintIndex;
        this.face = face;
        this.sprite = sprite;
        this.applyDiffuseLighting = applyDiffuseLighting;
        this.fixVertexData();
    }

    public int[] getVertexData() {
        this.fixVertexData();
        return this.vertexData;
    }

    public boolean hasTintIndex() {
        return this.tintIndex != -1;
    }

    public int getTintIndex() {
        return this.tintIndex;
    }

    public b_257_Y getFace() {
        if (this.face == null) {
            this.face = L_972_x.n_1700_B(this.getVertexData());
        }
        return this.face;
    }

    public boolean applyDiffuseLighting() {
        return this.applyDiffuseLighting;
    }

    public B_3871_I getSprite() {
        if (this.sprite == null) {
            this.sprite = c_932_S.getSpriteByUv(this.getVertexData());
        }
        return this.sprite;
    }

    public int[] getVertexDataSingle() {
        if (this.vertexDataSingle == null) {
            this.vertexDataSingle = c_932_S.makeVertexDataSingle(this.getVertexData(), this.getSprite());
        }
        if (this.vertexDataSingle.length != this.getVertexData().length) {
            this.vertexDataSingle = c_932_S.makeVertexDataSingle(this.getVertexData(), this.getSprite());
        }
        return this.vertexDataSingle;
    }

    private static int[] makeVertexDataSingle(int[] p_makeVertexDataSingle_0_, B_3871_I p_makeVertexDataSingle_1_) {
        int[] aint = (int[])p_makeVertexDataSingle_0_.clone();
        int i = aint.length / 4;
        for (int j = 0; j < 4; ++j) {
            int k = j * i;
            float f = Float.intBitsToFloat(aint[k + 4]);
            float f1 = Float.intBitsToFloat(aint[k + 4 + 1]);
            float f2 = p_makeVertexDataSingle_1_.R_4764_Y(f);
            float f3 = p_makeVertexDataSingle_1_.G_564_y(f1);
            aint[k + 4] = Float.floatToRawIntBits(f2);
            aint[k + 4 + 1] = Float.floatToRawIntBits(f3);
        }
        return aint;
    }

    @Override
    public void pipe(IVertexConsumer p_pipe_1_) {
        Reflector.callVoid(Reflector.LightUtil_putBakedQuad, p_pipe_1_, this);
    }

    private static B_3871_I getSpriteByUv(int[] p_getSpriteByUv_0_) {
        float f = 1.0f;
        float f1 = 1.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = p_getSpriteByUv_0_.length / 4;
        for (int j = 0; j < 4; ++j) {
            int k = j * i;
            float f4 = Float.intBitsToFloat(p_getSpriteByUv_0_[k + 4]);
            float f5 = Float.intBitsToFloat(p_getSpriteByUv_0_[k + 4 + 1]);
            f = Math.min(f, f4);
            f1 = Math.min(f1, f5);
            f2 = Math.max(f2, f4);
            f3 = Math.max(f3, f5);
        }
        float f6 = (f + f2) / 2.0f;
        float f7 = (f1 + f3) / 2.0f;
        return Config.getTextureMap().n_1700_B(f6, f7);
    }

    protected void fixVertexData() {
        if (Config.isShaders()) {
            if (this.vertexData.length == E_688_b.M_588_G) {
                this.vertexData = c_932_S.fixVertexDataSize(this.vertexData, E_688_b.P_4830_p);
            }
        } else if (this.vertexData.length == E_688_b.P_4830_p) {
            this.vertexData = c_932_S.fixVertexDataSize(this.vertexData, E_688_b.M_588_G);
        }
    }

    private static int[] fixVertexDataSize(int[] p_fixVertexDataSize_0_, int p_fixVertexDataSize_1_) {
        int i = p_fixVertexDataSize_0_.length / 4;
        int j = p_fixVertexDataSize_1_ / 4;
        int[] aint = new int[j * 4];
        for (int k = 0; k < 4; ++k) {
            int l = Math.min(i, j);
            System.arraycopy(p_fixVertexDataSize_0_, k * i, aint, k * j, l);
        }
        return aint;
    }

    public QuadBounds getQuadBounds() {
        if (this.quadBounds == null) {
            this.quadBounds = new QuadBounds(this.getVertexData());
        }
        return this.quadBounds;
    }

    public float getMidX() {
        QuadBounds quadbounds = this.getQuadBounds();
        return (quadbounds.getMaxX() + quadbounds.getMinX()) / 2.0f;
    }

    public double getMidY() {
        QuadBounds quadbounds = this.getQuadBounds();
        return (quadbounds.getMaxY() + quadbounds.getMinY()) / 2.0f;
    }

    public double getMidZ() {
        QuadBounds quadbounds = this.getQuadBounds();
        return (quadbounds.getMaxZ() + quadbounds.getMinZ()) / 2.0f;
    }

    public boolean isFaceQuad() {
        QuadBounds quadbounds = this.getQuadBounds();
        return quadbounds.isFaceQuad(this.face);
    }

    public boolean isFullQuad() {
        QuadBounds quadbounds = this.getQuadBounds();
        return quadbounds.isFullQuad(this.face);
    }

    public boolean isFullFaceQuad() {
        return this.isFullQuad() && this.isFaceQuad();
    }

    public c_932_S getQuadEmissive() {
        if (this.quadEmissiveChecked) {
            return this.quadEmissive;
        }
        if (this.quadEmissive == null && this.sprite != null && this.sprite.h_1847_R != null) {
            this.quadEmissive = new BakedQuadRetextured(this, this.sprite.h_1847_R);
        }
        this.quadEmissiveChecked = true;
        return this.quadEmissive;
    }

    public VertexPosition[] getVertexPositions(int p_getVertexPositions_1_) {
        if (this.quadVertexPositions == null) {
            this.quadVertexPositions = new QuadVertexPositions();
        }
        return (VertexPosition[])this.quadVertexPositions.get(p_getVertexPositions_1_);
    }

    public String toString() {
        return "vertexData: " + this.vertexData.length + ", tint: " + this.tintIndex + ", facing: " + String.valueOf(this.face) + ", sprite: " + String.valueOf(this.sprite);
    }
}

