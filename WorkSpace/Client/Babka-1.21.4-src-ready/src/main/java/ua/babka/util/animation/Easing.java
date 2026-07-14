package ua.babka.util.animation;

import ua.babka.util.math.MathUtil;

public interface Easing {
    public static final Easing SMOOTH_STEP = (t2, b2, c2, d2) -> {
        float x2 = c2 * t2 / d2 + b2;
        return (float)(-2.0 * Math.pow(x2, 3.0) + 3.0 * Math.pow(x2, 2.0));
    };
    public static final Easing BOTH_CUBIC = (t2, b2, c2, d2) -> {
        float x2 = c2 * t2 / d2 + b2;
        return (double)x2 < 0.5 ? 4.0f * x2 * x2 * x2 : (float)(1.0 - Math.pow(-2.0f * x2 + 2.0f, 3.0) / 2.0);
    };
    public static final Easing LINEAR = (t2, b2, c2, d2) -> c2 * t2 / d2 + b2;
    public static final Easing QUAD_IN = (t2, b2, c2, d2) -> c2 * (t2 /= d2) * t2 + b2;
    public static final Easing QUAD_OUT = (t2, b2, c2, d2) -> -c2 * (t2 /= d2) * (t2 - 2.0f) + b2;
    public static final Easing QUAD_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return c2 / 2.0f * t2 * t2 + b2;
        }
        return -c2 / 2.0f * ((t2 -= 1.0f) * (t2 - 2.0f) - 1.0f) + b2;
    };
    public static final Easing CUBIC_IN = (t2, b2, c2, d2) -> c2 * (t2 /= d2) * t2 * t2 + b2;
    public static final Easing CUBIC_OUT = (t2, b2, c2, d2) -> {
        t2 = t2 / d2 - 1.0f;
        return c2 * (t2 * t2 * t2 + 1.0f) + b2;
    };
    public static final Easing CUBIC_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return c2 / 2.0f * t2 * t2 * t2 + b2;
        }
        return c2 / 2.0f * ((t2 -= 2.0f) * t2 * t2 + 2.0f) + b2;
    };
    public static final Easing QUARTIC_IN = (t2, b2, c2, d2) -> c2 * (t2 /= d2) * t2 * t2 * t2 + b2;
    public static final Easing QUARTIC_OUT = (t2, b2, c2, d2) -> {
        t2 = t2 / d2 - 1.0f;
        return -c2 * (t2 * t2 * t2 * t2 - 1.0f) + b2;
    };
    public static final Easing QUARTIC_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return c2 / 2.0f * t2 * t2 * t2 * t2 + b2;
        }
        return -c2 / 2.0f * ((t2 -= 2.0f) * t2 * t2 * t2 - 2.0f) + b2;
    };
    public static final Easing QUINTIC_IN = (t2, b2, c2, d2) -> c2 * (t2 /= d2) * t2 * t2 * t2 * t2 + b2;
    public static final Easing QUINTIC_OUT = (t2, b2, c2, d2) -> {
        t2 = t2 / d2 - 1.0f;
        return c2 * (t2 * t2 * t2 * t2 * t2 + 1.0f) + b2;
    };
    public static final Easing QUINTIC_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return c2 / 2.0f * t2 * t2 * t2 * t2 * t2 + b2;
        }
        return c2 / 2.0f * ((t2 -= 2.0f) * t2 * t2 * t2 * t2 + 2.0f) + b2;
    };
    public static final Easing SINE_IN = (t2, b2, c2, d2) -> -c2 * (float)MathUtil.cos((double)(t2 / d2) * 1.5707963267948966) + c2 + b2;
    public static final Easing SINE_OUT = (t2, b2, c2, d2) -> c2 * (float)MathUtil.sin((double)(t2 / d2) * 1.5707963267948966) + b2;
    public static final Easing SINE_IN_OUT = (t2, b2, c2, d2) -> -c2 / 2.0f * ((float)MathUtil.cos(Math.PI * (double)t2 / (double)d2) - 1.0f) + b2;
    public static final Easing EXPO_IN = (t2, b2, c2, d2) -> t2 == 0.0f ? b2 : c2 * (float)Math.pow(2.0, 10.0f * (t2 / d2 - 1.0f)) + b2;
    public static final Easing EXPO_OUT = (t2, b2, c2, d2) -> t2 == d2 ? b2 + c2 : c2 * (-((float)Math.pow(2.0, -10.0f * t2 / d2)) + 1.0f) + b2;
    public static final Easing EXPO_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        if (t2 == 0.0f) {
            return b2;
        }
        if (t2 == d2) {
            return b2 + c2;
        }
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return c2 / 2.0f * (float)Math.pow(2.0, 10.0f * (t2 - 1.0f)) + b2;
        }
        return c2 / 2.0f * (-((float)Math.pow(2.0, -10.0f * (t2 -= 1.0f))) + 2.0f) + b2;
    };
    public static final Easing CIRC_IN = (t2, b2, c2, d2) -> -c2 * ((float)Math.sqrt(1.0f - (t2 /= d2) * t2) - 1.0f) + b2;
    public static final Easing CIRC_OUT = (t2, b2, c2, d2) -> {
        t2 = t2 / d2 - 1.0f;
        return c2 * (float)Math.sqrt(1.0f - t2 * t2) + b2;
    };
    public static final Easing CIRC_IN_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2 / 2.0f;
        if (f2 < 1.0f) {
            return -c2 / 2.0f * ((float)Math.sqrt(1.0f - t2 * t2) - 1.0f) + b2;
        }
        return c2 / 2.0f * ((float)Math.sqrt(1.0f - (t2 -= 2.0f) * t2) + 1.0f) + b2;
    };
    public static final Elastic ELASTIC_IN = new ElasticIn();
    public static final Elastic ELASTIC_OUT = new ElasticOut();
    public static final Elastic ELASTIC_IN_OUT = new ElasticInOut();
    public static final Back BACK_IN = new BackIn();
    public static final Back BACK_OUT = new BackOut();
    public static final Back BACK_OUT_BIGGY_OVERSHOOT = new BackOut(5.70158f);
    public static final Back BACK_IN_OUT = new BackInOut();
    public static final Easing BOUNCE_OUT = (t2, b2, c2, d2) -> {
        float f2;
        t2 /= d2;
        if (f2 < 0.36363637f) {
            return c2 * (7.5625f * t2 * t2) + b2;
        }
        if (t2 < 0.72727275f) {
            return c2 * (7.5625f * (t2 -= 0.54545456f) * t2 + 0.75f) + b2;
        }
        if (t2 < 0.90909094f) {
            return c2 * (7.5625f * (t2 -= 0.8181818f) * t2 + 0.9375f) + b2;
        }
        return c2 * (7.5625f * (t2 -= 0.95454544f) * t2 + 0.984375f) + b2;
    };
    public static final Easing BOUNCE_IN = (t2, b2, c2, d2) -> c2 - BOUNCE_OUT.ease(d2 - t2, 0.0f, c2, d2) + b2;
    public static final Easing BOUNCE_IN_OUT = (t2, b2, c2, d2) -> {
        if (t2 < d2 / 2.0f) {
            return BOUNCE_IN.ease(t2 * 2.0f, 0.0f, c2, d2) * 0.5f + b2;
        }
        return BOUNCE_OUT.ease(t2 * 2.0f - d2, 0.0f, c2, d2) * 0.5f + c2 * 0.5f + b2;
    };

    public float ease(float var1, float var2, float var3, float var4);

    public static class ElasticIn
    extends Elastic {
        public ElasticIn(float amplitude, float period) {
            super(amplitude, period);
        }

        public ElasticIn() {
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float a2 = this.getAmplitude();
            float p2 = this.getPeriod();
            if (t2 == 0.0f) {
                return b2;
            }
            if ((t2 /= d2) == 1.0f) {
                return b2 + c2;
            }
            if (p2 == 0.0f) {
                p2 = d2 * 0.3f;
            }
            float s2 = 0.0f;
            if (a2 < Math.abs(c2)) {
                a2 = c2;
                s2 = p2 / 4.0f;
            } else {
                s2 = p2 / ((float)Math.PI * 2) * (float)Math.asin(c2 / a2);
            }
            return -(a2 * (float)Math.pow(2.0, 10.0f * (t2 -= 1.0f)) * (float)MathUtil.sin((double)(t2 * d2 - s2) * (Math.PI * 2) / (double)p2)) + b2;
        }
    }

    public static abstract class Elastic
    implements Easing {
        private float amplitude;
        private float period;

        public Elastic(float amplitude, float period) {
            this.amplitude = amplitude;
            this.period = period;
        }

        public Elastic() {
            this(-1.0f, 0.0f);
        }

        public void setAmplitude(float amplitude) {
            this.amplitude = amplitude;
        }

        public void setPeriod(float period) {
            this.period = period;
        }

        public float getAmplitude() {
            return this.amplitude;
        }

        public float getPeriod() {
            return this.period;
        }
    }

    public static class ElasticOut
    extends Elastic {
        public ElasticOut(float amplitude, float period) {
            super(amplitude, period);
        }

        public ElasticOut() {
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float a2 = this.getAmplitude();
            float p2 = this.getPeriod();
            if (t2 == 0.0f) {
                return b2;
            }
            if ((t2 /= d2) == 1.0f) {
                return b2 + c2;
            }
            if (p2 == 0.0f) {
                p2 = d2 * 0.3f;
            }
            float s2 = 0.0f;
            if (a2 < Math.abs(c2)) {
                a2 = c2;
                s2 = p2 / 4.0f;
            } else {
                s2 = p2 / ((float)Math.PI * 2) * (float)Math.asin(c2 / a2);
            }
            return a2 * (float)Math.pow(2.0, -10.0f * t2) * (float)MathUtil.sin((double)(t2 * d2 - s2) * (Math.PI * 2) / (double)p2) + c2 + b2;
        }
    }

    public static class ElasticInOut
    extends Elastic {
        public ElasticInOut(float amplitude, float period) {
            super(amplitude, period);
        }

        public ElasticInOut() {
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float a2 = this.getAmplitude();
            float p2 = this.getPeriod();
            if (t2 == 0.0f) {
                return b2;
            }
            if ((t2 /= d2 / 2.0f) == 2.0f) {
                return b2 + c2;
            }
            if (p2 == 0.0f) {
                p2 = d2 * 0.45000002f;
            }
            float s2 = 0.0f;
            if (a2 < Math.abs(c2)) {
                a2 = c2;
                s2 = p2 / 4.0f;
            } else {
                s2 = p2 / ((float)Math.PI * 2) * (float)Math.asin(c2 / a2);
            }
            if (t2 < 1.0f) {
                return -0.5f * (a2 * (float)Math.pow(2.0, 10.0f * (t2 -= 1.0f)) * (float)MathUtil.sin((double)(t2 * d2 - s2) * (Math.PI * 2) / (double)p2)) + b2;
            }
            return a2 * (float)Math.pow(2.0, -10.0f * (t2 -= 1.0f)) * (float)MathUtil.sin((double)(t2 * d2 - s2) * (Math.PI * 2) / (double)p2) * 0.5f + c2 + b2;
        }
    }

    public static class BackIn
    extends Back {
        public BackIn() {
        }

        public BackIn(float overshoot) {
            super(overshoot);
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float s2 = this.getOvershoot();
            return c2 * (t2 /= d2) * t2 * ((s2 + 1.0f) * t2 - s2) + b2;
        }
    }

    public static abstract class Back
    implements Easing {
        public static final float DEFAULT_OVERSHOOT = 1.70158f;
        private float overshoot;

        public Back() {
            this(1.70158f);
        }

        public Back(float overshoot) {
            this.overshoot = overshoot;
        }

        public void setOvershoot(float overshoot) {
            this.overshoot = overshoot;
        }

        public float getOvershoot() {
            return this.overshoot;
        }
    }

    public static class BackOut
    extends Back {
        public BackOut() {
        }

        public BackOut(float overshoot) {
            super(overshoot);
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float s2 = this.getOvershoot();
            t2 = t2 / d2 - 1.0f;
            return c2 * (t2 * t2 * ((s2 + 1.0f) * t2 + s2) + 1.0f) + b2;
        }
    }

    public static class BackInOut
    extends Back {
        public BackInOut() {
        }

        public BackInOut(float overshoot) {
            super(overshoot);
        }

        @Override
        public float ease(float t2, float b2, float c2, float d2) {
            float f2;
            float s2 = this.getOvershoot();
            t2 /= d2 / 2.0f;
            if (f2 < 1.0f) {
                return c2 / 2.0f * (t2 * t2 * (((s2 *= 1.525f) + 1.0f) * t2 - s2)) + b2;
            }
            return c2 / 2.0f * ((t2 -= 2.0f) * t2 * (((s2 *= 1.525f) + 1.0f) * t2 + s2) + 2.0f) + b2;
        }
    }
}

