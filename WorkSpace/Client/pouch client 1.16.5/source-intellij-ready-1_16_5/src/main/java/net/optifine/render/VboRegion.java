/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import lightning.product.E_688_b;
import lightning.product.X_933_l;
import lightning.product.o_2576_A;
import net.optifine.Config;
import net.optifine.render.VboRange;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.LinkedList;

public class VboRegion {
    private o_2576_A layer = null;
    private int glBufferId = X_933_l.t_1786_h();
    private int capacity = 4096;
    private int positionTop = 0;
    private int sizeUsed;
    private LinkedList<VboRange> rangeList = new LinkedList();
    private VboRange compactRangeLast = null;
    private IntBuffer bufferIndexVertex = Config.createDirectIntBuffer(this.capacity);
    private IntBuffer bufferCountVertex = Config.createDirectIntBuffer(this.capacity);
    private final int vertexBytes = E_688_b.w_1484_f.J_1907_R();
    private int drawMode = 7;
    private boolean isShaders = Config.isShaders();

    public VboRegion(o_2576_A layer) {
        this.layer = layer;
        this.bindBuffer();
        long i = this.toBytes(this.capacity);
        X_933_l.n_1700_B(X_933_l.v_4262_N, i, X_933_l.w_1484_f);
        this.unbindBuffer();
    }

    public void bufferData(ByteBuffer data, VboRange range) {
        if (this.glBufferId >= 0) {
            int i = range.getPosition();
            int j = range.getSize();
            int k = this.toVertex(data.limit());
            if (k <= 0) {
                if (i >= 0) {
                    range.setPosition(-1);
                    range.setSize(0);
                    this.rangeList.remove(range.getNode());
                    this.sizeUsed -= j;
                }
            } else {
                if (k > j) {
                    range.setPosition(this.positionTop);
                    range.setSize(k);
                    this.positionTop += k;
                    if (i >= 0) {
                        this.rangeList.remove(range.getNode());
                    }
                    this.rangeList.addLast(range.getNode());
                }
                range.setSize(k);
                this.sizeUsed += k - j;
                this.checkVboSize(range.getPositionNext());
                long l = this.toBytes(range.getPosition());
                this.bindBuffer();
                X_933_l.n_1700_B(X_933_l.v_4262_N, l, data);
                this.unbindBuffer();
                if (this.positionTop > this.sizeUsed * 11 / 10) {
                    this.compactRanges(1);
                }
            }
        }
    }

    private void compactRanges(int countMax) {
        if (!this.rangeList.isEmpty()) {
            VboRange vborange = this.compactRangeLast;
            if (vborange == null || !this.rangeList.contains(vborange.getNode())) {
                vborange = this.rangeList.getFirst().getItem();
            }
            int i = vborange.getPosition();
            VboRange vborange1 = vborange.getPrev();
            i = vborange1 == null ? 0 : vborange1.getPositionNext();
            for (int j = 0; vborange != null && j < countMax; ++j) {
                if (vborange.getPosition() == i) {
                    i += vborange.getSize();
                    vborange = vborange.getNext();
                    continue;
                }
                int k = vborange.getPosition() - i;
                if (vborange.getSize() <= k) {
                    this.copyVboData(vborange.getPosition(), i, vborange.getSize());
                    vborange.setPosition(i);
                    i += vborange.getSize();
                    vborange = vborange.getNext();
                    continue;
                }
                this.checkVboSize(this.positionTop + vborange.getSize());
                this.copyVboData(vborange.getPosition(), this.positionTop, vborange.getSize());
                vborange.setPosition(this.positionTop);
                this.positionTop += vborange.getSize();
                VboRange vborange2 = vborange.getNext();
                this.rangeList.remove(vborange.getNode());
                this.rangeList.addLast(vborange.getNode());
                vborange = vborange2;
            }
            if (vborange == null) {
                this.positionTop = this.rangeList.getLast().getItem().getPositionNext();
            }
            this.compactRangeLast = vborange;
        }
    }

    private void checkRanges() {
        int i = 0;
        int j = 0;
        for (VboRange vborange = this.rangeList.getFirst().getItem(); vborange != null; vborange = vborange.getNext()) {
            ++i;
            j += vborange.getSize();
            if (vborange.getPosition() < 0 || vborange.getSize() <= 0 || vborange.getPositionNext() > this.positionTop) {
                throw new RuntimeException("Invalid range: " + String.valueOf(vborange));
            }
            VboRange vborange1 = vborange.getPrev();
            if (vborange1 != null && vborange.getPosition() < vborange1.getPositionNext()) {
                throw new RuntimeException("Invalid range: " + String.valueOf(vborange));
            }
            VboRange vborange2 = vborange.getNext();
            if (vborange2 == null || vborange.getPositionNext() <= vborange2.getPosition()) continue;
            throw new RuntimeException("Invalid range: " + String.valueOf(vborange));
        }
        if (i != this.rangeList.getSize()) {
            throw new RuntimeException("Invalid count: " + i + " <> " + this.rangeList.getSize());
        }
        if (j != this.sizeUsed) {
            throw new RuntimeException("Invalid size: " + j + " <> " + this.sizeUsed);
        }
    }

    private void checkVboSize(int sizeMin) {
        if (this.capacity < sizeMin) {
            this.expandVbo(sizeMin);
        }
    }

    private void copyVboData(int posFrom, int posTo, int size) {
        long i = this.toBytes(posFrom);
        long j = this.toBytes(posTo);
        long k = this.toBytes(size);
        X_933_l.v_4262_N(X_933_l.P_1922_E, this.glBufferId);
        X_933_l.v_4262_N(X_933_l.u_1723_Y, this.glBufferId);
        X_933_l.n_1700_B(X_933_l.P_1922_E, X_933_l.u_1723_Y, i, j, k);
        Config.checkGlError("Copy VBO range");
        X_933_l.v_4262_N(X_933_l.P_1922_E, 0);
        X_933_l.v_4262_N(X_933_l.u_1723_Y, 0);
    }

    private void expandVbo(int sizeMin) {
        int i = this.capacity * 6 / 4;
        while (i < sizeMin) {
            i = i * 6 / 4;
        }
        long j = this.toBytes(this.capacity);
        long k = this.toBytes(i);
        int l = X_933_l.t_1786_h();
        X_933_l.v_4262_N(X_933_l.v_4262_N, l);
        X_933_l.n_1700_B(X_933_l.v_4262_N, k, X_933_l.w_1484_f);
        Config.checkGlError("Expand VBO");
        X_933_l.v_4262_N(X_933_l.v_4262_N, 0);
        X_933_l.v_4262_N(X_933_l.P_1922_E, this.glBufferId);
        X_933_l.v_4262_N(X_933_l.u_1723_Y, l);
        X_933_l.n_1700_B(X_933_l.P_1922_E, X_933_l.u_1723_Y, 0L, 0L, j);
        Config.checkGlError("Copy VBO: " + k);
        X_933_l.v_4262_N(X_933_l.P_1922_E, 0);
        X_933_l.v_4262_N(X_933_l.u_1723_Y, 0);
        X_933_l.s_956_w(this.glBufferId);
        this.bufferIndexVertex = Config.createDirectIntBuffer(i);
        this.bufferCountVertex = Config.createDirectIntBuffer(i);
        this.glBufferId = l;
        this.capacity = i;
    }

    public void bindBuffer() {
        X_933_l.v_4262_N(X_933_l.v_4262_N, this.glBufferId);
    }

    public void drawArrays(int drawMode, VboRange range) {
        if (this.drawMode != drawMode) {
            if (this.bufferIndexVertex.position() > 0) {
                throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + drawMode);
            }
            this.drawMode = drawMode;
        }
        this.bufferIndexVertex.put(range.getPosition());
        this.bufferCountVertex.put(range.getSize());
    }

    public void finishDraw() {
        this.bindBuffer();
        E_688_b.w_1484_f.n_1700_B(0L);
        if (this.isShaders) {
            ShadersRender.setupArrayPointersVbo();
        }
        ((Buffer)this.bufferIndexVertex).flip();
        ((Buffer)this.bufferCountVertex).flip();
        X_933_l.n_1700_B(this.drawMode, this.bufferIndexVertex, this.bufferCountVertex);
        ((Buffer)this.bufferIndexVertex).limit(this.bufferIndexVertex.capacity());
        ((Buffer)this.bufferCountVertex).limit(this.bufferCountVertex.capacity());
        if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
        }
    }

    public void unbindBuffer() {
        X_933_l.v_4262_N(X_933_l.v_4262_N, 0);
    }

    public void deleteGlBuffers() {
        if (this.glBufferId >= 0) {
            X_933_l.s_956_w(this.glBufferId);
            this.glBufferId = -1;
        }
    }

    private long toBytes(int vertex) {
        return (long)vertex * (long)this.vertexBytes;
    }

    private int toVertex(long bytes) {
        return (int)(bytes / (long)this.vertexBytes);
    }

    public int getPositionTop() {
        return this.positionTop;
    }
}

