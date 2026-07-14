/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringEscapeUtils
 */
package net.optifine.config;

import java.util.Arrays;
import java.util.regex.Pattern;
import lightning.product.D_908_R;
import lightning.product.StringTag;
import lightning.product.L_3985_e;
import lightning.product.T_2717_K;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.a_969_m;
import lightning.product.q_2567_I;
import lightning.product.q_2896_o;
import lightning.product.IntTag;
import net.optifine.Config;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue {
    private String[] parents = null;
    private String name = null;
    private boolean negative = false;
    private int type = 0;
    private String value = null;
    private int valueFormat = 0;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_PATTERN = 1;
    private static final int TYPE_IPATTERN = 2;
    private static final int TYPE_REGEX = 3;
    private static final int TYPE_IREGEX = 4;
    private static final String PREFIX_PATTERN = "pattern:";
    private static final String PREFIX_IPATTERN = "ipattern:";
    private static final String PREFIX_REGEX = "regex:";
    private static final String PREFIX_IREGEX = "iregex:";
    private static final int FORMAT_DEFAULT = 0;
    private static final int FORMAT_HEX_COLOR = 1;
    private static final String PREFIX_HEX_COLOR = "#";
    private static final Pattern PATTERN_HEX_COLOR = Pattern.compile("^#[0-9a-f]{6}+$");

    public NbtTagValue(String tag, String value) {
        String[] astring = Config.tokenize(tag, ".");
        this.parents = Arrays.copyOfRange(astring, 0, astring.length - 1);
        this.name = astring[astring.length - 1];
        if (value.startsWith("!")) {
            this.negative = true;
            value = value.substring(1);
        }
        if (value.startsWith(PREFIX_PATTERN)) {
            this.type = 1;
            value = value.substring(PREFIX_PATTERN.length());
        } else if (value.startsWith(PREFIX_IPATTERN)) {
            this.type = 2;
            value = value.substring(PREFIX_IPATTERN.length()).toLowerCase();
        } else if (value.startsWith(PREFIX_REGEX)) {
            this.type = 3;
            value = value.substring(PREFIX_REGEX.length());
        } else if (value.startsWith(PREFIX_IREGEX)) {
            this.type = 4;
            value = value.substring(PREFIX_IREGEX.length()).toLowerCase();
        } else {
            this.type = 0;
        }
        value = StringEscapeUtils.unescapeJava((String)value);
        if (this.type == 0 && PATTERN_HEX_COLOR.matcher(value).matches()) {
            this.valueFormat = 1;
        }
        this.value = value;
    }

    public boolean matches(U_2912_j nbt) {
        if (this.negative) {
            return !this.matchesCompound(nbt);
        }
        return this.matchesCompound(nbt);
    }

    public boolean matchesCompound(U_2912_j nbt) {
        if (nbt == null) {
            return false;
        }
        Tag inbt = nbt;
        for (int i = 0; i < this.parents.length; ++i) {
            String s = this.parents[i];
            if ((inbt = NbtTagValue.getChildTag(inbt, s)) != null) continue;
            return false;
        }
        if (this.name.equals("*")) {
            return this.matchesAnyChild(inbt);
        }
        Tag inbt1 = NbtTagValue.getChildTag(inbt, this.name);
        if (inbt1 == null) {
            return false;
        }
        return this.matchesBase(inbt1);
    }

    private boolean matchesAnyChild(Tag tagBase) {
        if (tagBase instanceof U_2912_j) {
            U_2912_j compoundnbt = (U_2912_j)tagBase;
            for (String s : compoundnbt.G_564_y()) {
                Tag inbt = compoundnbt.R_4764_Y(s);
                if (!this.matchesBase(inbt)) continue;
                return true;
            }
        }
        if (tagBase instanceof q_2896_o) {
            q_2896_o listnbt = (q_2896_o)tagBase;
            int i = listnbt.size();
            for (int j = 0; j < i; ++j) {
                Tag inbt1 = listnbt.s_956_w(j);
                if (!this.matchesBase(inbt1)) continue;
                return true;
            }
        }
        return false;
    }

    private static Tag getChildTag(Tag tagBase, String tag) {
        if (tagBase instanceof U_2912_j) {
            U_2912_j compoundnbt = (U_2912_j)tagBase;
            return compoundnbt.R_4764_Y(tag);
        }
        if (tagBase instanceof q_2896_o) {
            q_2896_o listnbt = (q_2896_o)tagBase;
            if (tag.equals("count")) {
                return IntTag.n_1700_B(listnbt.size());
            }
            int i = Config.parseInt(tag, -1);
            return i >= 0 && i < listnbt.size() ? listnbt.s_956_w(i) : null;
        }
        return null;
    }

    public boolean matchesBase(Tag nbtBase) {
        if (nbtBase == null) {
            return false;
        }
        String s = NbtTagValue.getNbtString(nbtBase, this.valueFormat);
        return this.matchesValue(s);
    }

    public boolean matchesValue(String nbtValue) {
        if (nbtValue == null) {
            return false;
        }
        switch (this.type) {
            case 0: {
                return nbtValue.equals(this.value);
            }
            case 1: {
                return this.matchesPattern(nbtValue, this.value);
            }
            case 2: {
                return this.matchesPattern(nbtValue.toLowerCase(), this.value);
            }
            case 3: {
                return this.matchesRegex(nbtValue, this.value);
            }
            case 4: {
                return this.matchesRegex(nbtValue.toLowerCase(), this.value);
            }
        }
        throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
    }

    private boolean matchesPattern(String str, String pattern) {
        return StrUtils.equalsMask(str, pattern, '*', '?');
    }

    private boolean matchesRegex(String str, String regex) {
        return str.matches(regex);
    }

    private static String getNbtString(Tag nbtBase, int format) {
        if (nbtBase == null) {
            return null;
        }
        if (!(nbtBase instanceof StringTag)) {
            if (nbtBase instanceof IntTag) {
                IntTag intnbt = (IntTag)nbtBase;
                return format == 1 ? PREFIX_HEX_COLOR + StrUtils.fillLeft(Integer.toHexString(intnbt.u_1723_Y()), 6, '0') : Integer.toString(intnbt.u_1723_Y());
            }
            if (nbtBase instanceof L_3985_e) {
                L_3985_e bytenbt = (L_3985_e)nbtBase;
                return Byte.toString(bytenbt.w_1484_f());
            }
            if (nbtBase instanceof a_969_m) {
                a_969_m shortnbt = (a_969_m)nbtBase;
                return Short.toString(shortnbt.v_4262_N());
            }
            if (nbtBase instanceof q_2567_I) {
                q_2567_I longnbt = (q_2567_I)nbtBase;
                return Long.toString(longnbt.P_1922_E());
            }
            if (nbtBase instanceof T_2717_K) {
                T_2717_K floatnbt = (T_2717_K)nbtBase;
                return Float.toString(floatnbt.s_956_w());
            }
            if (nbtBase instanceof D_908_R) {
                D_908_R doublenbt = (D_908_R)nbtBase;
                return Double.toString(doublenbt.t_148_a());
            }
            return nbtBase.toString();
        }
        StringTag stringnbt = (StringTag)nbtBase;
        String s = stringnbt.M_588_G();
        if (s.startsWith("{") && s.endsWith("}")) {
            s = NbtTagValue.getMergedJsonText(s);
        } else if (s.startsWith("[{") && s.endsWith("}]")) {
            s = NbtTagValue.getMergedJsonText(s);
        }
        return s;
    }

    private static String getMergedJsonText(String text) {
        StringBuilder stringbuilder = new StringBuilder();
        String s = "\"text\":\"";
        int i = -1;
        while ((i = text.indexOf(s, i + 1)) >= 0) {
            String s1 = NbtTagValue.parseString(text, i + s.length());
            if (s1 == null) continue;
            stringbuilder.append(s1);
        }
        return stringbuilder.toString();
    }

    private static String parseString(String text, int pos) {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = false;
        for (int i = pos; i < text.length(); ++i) {
            char c0 = text.charAt(i);
            if (flag) {
                if (c0 == 'b') {
                    stringbuilder.append('\b');
                } else if (c0 == 'f') {
                    stringbuilder.append('\f');
                } else if (c0 == 'n') {
                    stringbuilder.append('\n');
                } else if (c0 == 'r') {
                    stringbuilder.append('\r');
                } else if (c0 == 't') {
                    stringbuilder.append('\t');
                } else {
                    stringbuilder.append(c0);
                }
                flag = false;
                continue;
            }
            if (c0 == '\\') {
                flag = true;
                continue;
            }
            if (c0 == '\"') break;
            stringbuilder.append(c0);
        }
        return stringbuilder.toString();
    }

    public String toString() {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < this.parents.length; ++i) {
            String s = this.parents[i];
            if (i > 0) {
                stringbuffer.append(".");
            }
            stringbuffer.append(s);
        }
        if (stringbuffer.length() > 0) {
            stringbuffer.append(".");
        }
        stringbuffer.append(this.name);
        stringbuffer.append(" = ");
        stringbuffer.append(this.value);
        return stringbuffer.toString();
    }
}


