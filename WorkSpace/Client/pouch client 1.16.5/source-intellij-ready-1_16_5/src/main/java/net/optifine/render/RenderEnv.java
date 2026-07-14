/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ByteLinkedOpenHashMap
 */
package net.optifine.render;

import it.unimi.dsi.fastutil.longs.Long2ByteLinkedOpenHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import lightning.product.LeavesBlock;
import lightning.product.K_4074_S;
import lightning.product.W_571_B;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.o_2576_A;
import lightning.product.ChunkBufferBuilderPack;
import net.optifine.BlockPosM;
import net.optifine.Config;
import net.optifine.model.BakedQuadRetextured;
import net.optifine.model.ListQuadsOverlay;

public class RenderEnv {
    private K_4074_S blockState;
    private c_1514_x blockPos;
    private int blockId = -1;
    private int metadata = -1;
    private int breakingAnimation = -1;
    private int smartLeaves = -1;
    private float[] quadBounds = new float[b_257_Y.v_4262_N.length * 2];
    private BitSet boundsFlags = new BitSet(3);
    private W_571_B.n_1700_B aoFace = new W_571_B.n_1700_B();
    private BlockPosM colorizerBlockPosM = null;
    private boolean[] borderFlags = null;
    private boolean[] borderFlags2 = null;
    private boolean[] borderFlags3 = null;
    private b_257_Y[] borderDirections = null;
    private List<c_932_S> listQuadsCustomizer = new ArrayList<c_932_S>();
    private List<c_932_S> listQuadsCtmMultipass = new ArrayList<c_932_S>();
    private c_932_S[] arrayQuadsCtm1 = new c_932_S[1];
    private c_932_S[] arrayQuadsCtm2 = new c_932_S[2];
    private c_932_S[] arrayQuadsCtm3 = new c_932_S[3];
    private c_932_S[] arrayQuadsCtm4 = new c_932_S[4];
    private ChunkBufferBuilderPack regionRenderCacheBuilder = null;
    private ListQuadsOverlay[] listsQuadsOverlay = new ListQuadsOverlay[o_2576_A.N_2525_X.length];
    private boolean overlaysRendered = false;
    private Long2ByteLinkedOpenHashMap renderSideMap = new Long2ByteLinkedOpenHashMap();
    private static final int UNKNOWN = -1;
    private static final int FALSE = 0;
    private static final int TRUE = 1;

    public RenderEnv(K_4074_S blockState, c_1514_x blockPos) {
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public void reset(K_4074_S blockStateIn, c_1514_x blockPosIn) {
        if (this.blockState != blockStateIn || this.blockPos != blockPosIn) {
            this.blockState = blockStateIn;
            this.blockPos = blockPosIn;
            this.blockId = -1;
            this.metadata = -1;
            this.breakingAnimation = -1;
            this.smartLeaves = -1;
            this.boundsFlags.clear();
        }
    }

    public int getBlockId() {
        if (this.blockId < 0) {
            this.blockId = this.blockState.multiplayerClientSuggestionProvider();
        }
        return this.blockId;
    }

    public int getMetadata() {
        if (this.metadata < 0) {
            this.metadata = this.blockState.w_1457_N();
        }
        return this.metadata;
    }

    public float[] getQuadBounds() {
        return this.quadBounds;
    }

    public BitSet getBoundsFlags() {
        return this.boundsFlags;
    }

    public W_571_B.n_1700_B getAoFace() {
        return this.aoFace;
    }

    public boolean isBreakingAnimation(List listQuads) {
        if (this.breakingAnimation == -1 && listQuads.size() > 0) {
            this.breakingAnimation = listQuads.get(0) instanceof BakedQuadRetextured ? 1 : 0;
        }
        return this.breakingAnimation == 1;
    }

    public boolean isBreakingAnimation(c_932_S quad) {
        if (this.breakingAnimation < 0) {
            this.breakingAnimation = quad instanceof BakedQuadRetextured ? 1 : 0;
        }
        return this.breakingAnimation == 1;
    }

    public boolean isBreakingAnimation() {
        return this.breakingAnimation == 1;
    }

    public K_4074_S getBlockState() {
        return this.blockState;
    }

    public BlockPosM getColorizerBlockPosM() {
        if (this.colorizerBlockPosM == null) {
            this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
        }
        return this.colorizerBlockPosM;
    }

    public boolean[] getBorderFlags() {
        if (this.borderFlags == null) {
            this.borderFlags = new boolean[4];
        }
        return this.borderFlags;
    }

    public boolean[] getBorderFlags2() {
        if (this.borderFlags2 == null) {
            this.borderFlags2 = new boolean[4];
        }
        return this.borderFlags2;
    }

    public boolean[] getBorderFlags3() {
        if (this.borderFlags3 == null) {
            this.borderFlags3 = new boolean[4];
        }
        return this.borderFlags3;
    }

    public b_257_Y[] getBorderDirections() {
        if (this.borderDirections == null) {
            this.borderDirections = new b_257_Y[4];
        }
        return this.borderDirections;
    }

    public b_257_Y[] getBorderDirections(b_257_Y dir0, b_257_Y dir1, b_257_Y dir2, b_257_Y dir3) {
        b_257_Y[] adirection = this.getBorderDirections();
        adirection[0] = dir0;
        adirection[1] = dir1;
        adirection[2] = dir2;
        adirection[3] = dir3;
        return adirection;
    }

    public boolean isSmartLeaves() {
        if (this.smartLeaves == -1) {
            this.smartLeaves = Config.isTreesSmart() && this.blockState.J_1907_R() instanceof LeavesBlock ? 1 : 0;
        }
        return this.smartLeaves == 1;
    }

    public List<c_932_S> getListQuadsCustomizer() {
        return this.listQuadsCustomizer;
    }

    public c_932_S[] getArrayQuadsCtm(c_932_S quad) {
        this.arrayQuadsCtm1[0] = quad;
        return this.arrayQuadsCtm1;
    }

    public c_932_S[] getArrayQuadsCtm(c_932_S quad0, c_932_S quad1) {
        this.arrayQuadsCtm2[0] = quad0;
        this.arrayQuadsCtm2[1] = quad1;
        return this.arrayQuadsCtm2;
    }

    public c_932_S[] getArrayQuadsCtm(c_932_S quad0, c_932_S quad1, c_932_S quad2) {
        this.arrayQuadsCtm3[0] = quad0;
        this.arrayQuadsCtm3[1] = quad1;
        this.arrayQuadsCtm3[2] = quad2;
        return this.arrayQuadsCtm3;
    }

    public c_932_S[] getArrayQuadsCtm(c_932_S quad0, c_932_S quad1, c_932_S quad2, c_932_S quad3) {
        this.arrayQuadsCtm4[0] = quad0;
        this.arrayQuadsCtm4[1] = quad1;
        this.arrayQuadsCtm4[2] = quad2;
        this.arrayQuadsCtm4[3] = quad3;
        return this.arrayQuadsCtm4;
    }

    public List<c_932_S> getListQuadsCtmMultipass(c_932_S[] quads) {
        this.listQuadsCtmMultipass.clear();
        if (quads != null) {
            for (int i = 0; i < quads.length; ++i) {
                c_932_S bakedquad = quads[i];
                this.listQuadsCtmMultipass.add(bakedquad);
            }
        }
        return this.listQuadsCtmMultipass;
    }

    public ChunkBufferBuilderPack getRegionRenderCacheBuilder() {
        return this.regionRenderCacheBuilder;
    }

    public void setRegionRenderCacheBuilder(ChunkBufferBuilderPack regionRenderCacheBuilder) {
        this.regionRenderCacheBuilder = regionRenderCacheBuilder;
    }

    public ListQuadsOverlay getListQuadsOverlay(o_2576_A layer) {
        ListQuadsOverlay listquadsoverlay = this.listsQuadsOverlay[layer.G_564_y()];
        if (listquadsoverlay == null) {
            this.listsQuadsOverlay[layer.G_564_y()] = listquadsoverlay = new ListQuadsOverlay();
        }
        return listquadsoverlay;
    }

    public boolean isOverlaysRendered() {
        return this.overlaysRendered;
    }

    public void setOverlaysRendered(boolean overlaysRendered) {
        this.overlaysRendered = overlaysRendered;
    }

    public Long2ByteLinkedOpenHashMap getRenderSideMap() {
        return this.renderSideMap;
    }
}


