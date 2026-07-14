/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_932_S;

public class ListQuadsOverlay {
    private List<c_932_S> listQuads = new ArrayList<c_932_S>();
    private List<K_4074_S> listBlockStates = new ArrayList<K_4074_S>();
    private List<c_932_S> listQuadsSingle = Arrays.asList(new c_932_S[0]);

    public void addQuad(c_932_S quad, K_4074_S blockState) {
        if (quad != null) {
            this.listQuads.add(quad);
            this.listBlockStates.add(blockState);
        }
    }

    public int size() {
        return this.listQuads.size();
    }

    public c_932_S getQuad(int index) {
        return this.listQuads.get(index);
    }

    public K_4074_S getBlockState(int index) {
        return index >= 0 && index < this.listBlockStates.size() ? this.listBlockStates.get(index) : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    public List<c_932_S> getListQuadsSingle(c_932_S quad) {
        this.listQuadsSingle.set(0, quad);
        return this.listQuadsSingle;
    }

    public void clear() {
        this.listQuads.clear();
        this.listBlockStates.clear();
    }
}


