/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.util.Arrays;
import java.util.List;
import lightning.product.E_270_p;
import lightning.product.o_2576_A;

public class RenderStateManager {
    private static boolean cacheEnabled;
    private static final E_270_p[] PENDING_CLEAR_STATES;

    public static void setupRenderStates(List<E_270_p> renderStates) {
        if (cacheEnabled) {
            RenderStateManager.setupCached(renderStates);
        } else {
            for (int i = 0; i < renderStates.size(); ++i) {
                E_270_p renderstate = renderStates.get(i);
                renderstate.n_1700_B();
            }
        }
    }

    public static void clearRenderStates(List<E_270_p> renderStates) {
        if (cacheEnabled) {
            RenderStateManager.clearCached(renderStates);
        } else {
            for (int i = 0; i < renderStates.size(); ++i) {
                E_270_p renderstate = renderStates.get(i);
                renderstate.J_1907_R();
            }
        }
    }

    private static void setupCached(List<E_270_p> renderStates) {
        for (int i = 0; i < renderStates.size(); ++i) {
            E_270_p renderstate = renderStates.get(i);
            RenderStateManager.setupCached(renderstate, i);
        }
    }

    private static void clearCached(List<E_270_p> renderStates) {
        for (int i = 0; i < renderStates.size(); ++i) {
            E_270_p renderstate = renderStates.get(i);
            RenderStateManager.clearCached(renderstate, i);
        }
    }

    private static void setupCached(E_270_p state, int index) {
        E_270_p renderstate = PENDING_CLEAR_STATES[index];
        if (renderstate != null) {
            if (state == renderstate) {
                RenderStateManager.PENDING_CLEAR_STATES[index] = null;
                return;
            }
            renderstate.J_1907_R();
            RenderStateManager.PENDING_CLEAR_STATES[index] = null;
        }
        state.n_1700_B();
    }

    private static void clearCached(E_270_p state, int index) {
        E_270_p renderstate = PENDING_CLEAR_STATES[index];
        if (renderstate != null) {
            renderstate.J_1907_R();
        }
        RenderStateManager.PENDING_CLEAR_STATES[index] = state;
    }

    public static void enableCache() {
        if (!cacheEnabled) {
            cacheEnabled = true;
            Arrays.fill(PENDING_CLEAR_STATES, null);
        }
    }

    public static void disableCache() {
        if (cacheEnabled) {
            cacheEnabled = false;
            for (int i = 0; i < PENDING_CLEAR_STATES.length; ++i) {
                E_270_p renderstate = PENDING_CLEAR_STATES[i];
                if (renderstate == null) continue;
                renderstate.J_1907_R();
            }
            Arrays.fill(PENDING_CLEAR_STATES, null);
        }
    }

    static {
        PENDING_CLEAR_STATES = new E_270_p[o_2576_A.e_4240_b()];
    }
}

