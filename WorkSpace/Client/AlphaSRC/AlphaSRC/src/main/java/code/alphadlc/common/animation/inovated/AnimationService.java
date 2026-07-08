/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 */
package code.alphadlc.common.animation.inovated;

import net.minecraft.class_310;
import net.minecraft.class_3532;

public final class AnimationService {
    public static double delta;

    public static float animation(float animation, float target, float speedTarget) {
        float dif = (target - animation) / Math.max((float)class_310.method_1551().method_47599(), 5.0f) * 15.0f;
        if (dif > 0.0f) {
            dif = Math.max(speedTarget, dif);
            dif = Math.min(target - animation, dif);
        } else if (dif < 0.0f) {
            dif = Math.min(-speedTarget, dif);
            dif = Math.max(target - animation, dif);
        }
        return animation + dif;
    }

    public static float animationSpeed(float animation, float target, float speedTarget) {
        float dif = (target - animation) / Math.max((float)class_310.method_1551().method_47599(), 5.0f) * 15.0f;
        if (dif > 0.0f) {
            dif = speedTarget;
            dif = Math.min(target - animation, dif);
        } else if (dif < 0.0f) {
            dif = -speedTarget;
            dif = Math.max(target - animation, dif);
        }
        return animation + dif;
    }

    public static float getAnimationState(float animation, float finalState, float speed) {
        float add = (float)(delta * (double)(speed / 1000.0f));
        animation = animation < finalState ? (animation + add < finalState ? (animation += add) : finalState) : (animation - add > finalState ? (animation -= add) : finalState);
        return animation;
    }

    public static double interpolateAnimation(double start, double end, double step) {
        return start + (end - start) * step;
    }

    public static float move(float from, float to, float minstep, float maxstep, float factor) {
        float f = (to - from) * class_3532.method_15363((float)factor, (float)0.0f, (float)1.0f);
        f = f < 0.0f ? class_3532.method_15363((float)f, (float)(-maxstep), (float)(-minstep)) : class_3532.method_15363((float)f, (float)minstep, (float)maxstep);
        if (Math.abs(f) > Math.abs(to - from)) {
            return to;
        }
        return from + f;
    }

    private AnimationService() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
