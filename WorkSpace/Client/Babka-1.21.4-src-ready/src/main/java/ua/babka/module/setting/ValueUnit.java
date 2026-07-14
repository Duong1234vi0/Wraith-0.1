package ua.babka.module.setting;

public final class ValueUnit {
    private final Kind kind;
    private final String one;
    private final String few;
    private final String many;
    private final String raw;

    private ValueUnit(Kind kind, String one, String few, String many, String raw) {
        this.kind = kind;
        this.one = one;
        this.few = few;
        this.many = many;
        this.raw = raw;
    }

    public static ValueUnit countable(String one, String few, String many) {
        return new ValueUnit(Kind.COUNTABLE, one, few, many, null);
    }

    public static ValueUnit abbreviation(String raw) {
        return new ValueUnit(Kind.ABBREVIATION, null, null, null, raw);
    }

    public String format(double value) {
        if (this.kind == Kind.ABBREVIATION) {
            return this.raw;
        }
        double abs = Math.abs(value);
        double fraction = abs - Math.floor(abs);
        if (fraction > 0.0) {
            return fraction < 0.5 ? this.few : this.many;
        }
        int v2 = (int)abs % 100;
        int v1 = v2 % 10;
        if (v2 >= 11 && v2 <= 19) {
            return this.many;
        }
        if (v1 == 1) {
            return this.one;
        }
        if (v1 >= 2 && v1 <= 4) {
            return this.few;
        }
        return this.many;
    }

    public static enum Kind {
        COUNTABLE,
        ABBREVIATION;

    }
}

