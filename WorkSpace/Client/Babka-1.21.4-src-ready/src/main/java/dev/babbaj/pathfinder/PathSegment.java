package dev.babbaj.pathfinder;

public class PathSegment {
    public final boolean finished;
    public final long[] packed;

    public PathSegment(boolean bl, long[] lArray) {
        this.finished = bl;
        this.packed = lArray;
    }
}

