/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.objects.Object2LongMap
 *  it.unimi.dsi.fastutil.objects.Object2LongMaps
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.ObjectUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongMaps;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import lightning.product.SharedConstants;
import lightning.product.ProfileResults;
import lightning.product.ResultField;
import lightning.product.j_3341_s;
import lightning.product.ProfilerPathEntry;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class T_4971_s
implements ProfileResults {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final ProfilerPathEntry J_1907_R = new ProfilerPathEntry(){

        @Override
        public long n_1700_B() {
            return 0L;
        }

        @Override
        public long J_1907_R() {
            return 0L;
        }

        @Override
        public Object2LongMap<String> R_4764_Y() {
            return Object2LongMaps.emptyMap();
        }
    };
    private static final Splitter R_4764_Y = Splitter.on((char)'\u001e');
    private static final Comparator<Map.Entry<String, n_1700_B>> G_564_y = Map.Entry.comparingByValue(Comparator.comparingLong(p_230096_0_ -> p_230096_0_.J_1907_R)).reversed();
    private final Map<String, ? extends ProfilerPathEntry> P_1922_E;
    private final long u_1723_Y;
    private final int v_4262_N;
    private final long w_1484_f;
    private final int t_148_a;
    private final int s_956_w;

    public T_4971_s(Map<String, ? extends ProfilerPathEntry> p_i50407_1_, long p_i50407_2_, int p_i50407_4_, long p_i50407_5_, int p_i50407_7_) {
        this.P_1922_E = p_i50407_1_;
        this.u_1723_Y = p_i50407_2_;
        this.v_4262_N = p_i50407_4_;
        this.w_1484_f = p_i50407_5_;
        this.t_148_a = p_i50407_7_;
        this.s_956_w = p_i50407_7_ - p_i50407_4_;
    }

    private ProfilerPathEntry R_4764_Y(String p_230104_1_) {
        ProfilerPathEntry iprofilersection = this.P_1922_E.get(p_230104_1_);
        return iprofilersection != null ? iprofilersection : J_1907_R;
    }

    @Override
    public List<ResultField> n_1700_B(String sectionPath) {
        String s = sectionPath;
        ProfilerPathEntry iprofilersection = this.R_4764_Y("root");
        long i = iprofilersection.n_1700_B();
        ProfilerPathEntry iprofilersection1 = this.R_4764_Y((String)sectionPath);
        long j = iprofilersection1.n_1700_B();
        long k = iprofilersection1.J_1907_R();
        ArrayList list = Lists.newArrayList();
        if (!((String)sectionPath).isEmpty()) {
            sectionPath = (String)sectionPath + "\u001e";
        }
        long l = 0L;
        for (String s1 : this.P_1922_E.keySet()) {
            if (!T_4971_s.n_1700_B((String)sectionPath, s1)) continue;
            l += this.R_4764_Y(s1).n_1700_B();
        }
        float f = l;
        if (l < j) {
            l = j;
        }
        if (i < l) {
            i = l;
        }
        for (String s2 : this.P_1922_E.keySet()) {
            if (!T_4971_s.n_1700_B((String)sectionPath, s2)) continue;
            ProfilerPathEntry iprofilersection2 = this.R_4764_Y(s2);
            long i1 = iprofilersection2.n_1700_B();
            double d0 = (double)i1 * 100.0 / (double)l;
            double d1 = (double)i1 * 100.0 / (double)i;
            String s3 = s2.substring(((String)sectionPath).length());
            list.add(new ResultField(s3, d0, d1, iprofilersection2.J_1907_R()));
        }
        if ((float)l > f) {
            list.add(new ResultField("unspecified", (double)((float)l - f) * 100.0 / (double)l, (double)((float)l - f) * 100.0 / (double)i, k));
        }
        Collections.sort(list);
        list.add(0, new ResultField(s, 100.0, (double)l * 100.0 / (double)i, k));
        return list;
    }

    private static boolean n_1700_B(String p_230097_0_, String p_230097_1_) {
        return p_230097_1_.length() > p_230097_0_.length() && p_230097_1_.startsWith(p_230097_0_) && p_230097_1_.indexOf(30, p_230097_0_.length() + 1) < 0;
    }

    private Map<String, n_1700_B> v_4262_N() {
        TreeMap map = Maps.newTreeMap();
        this.P_1922_E.forEach((p_230101_1_, p_230101_2_) -> {
            Object2LongMap<String> object2longmap = p_230101_2_.R_4764_Y();
            if (!object2longmap.isEmpty()) {
                List list = R_4764_Y.splitToList((CharSequence)p_230101_1_);
                object2longmap.forEach((p_230103_2_, p_230103_3_) -> map.computeIfAbsent(p_230103_2_, p_230105_0_ -> new n_1700_B()).n_1700_B(list.iterator(), (long)p_230103_3_));
            }
        });
        return map;
    }

    @Override
    public long n_1700_B() {
        return this.u_1723_Y;
    }

    @Override
    public int J_1907_R() {
        return this.v_4262_N;
    }

    @Override
    public long R_4764_Y() {
        return this.w_1484_f;
    }

    @Override
    public int G_564_y() {
        return this.t_148_a;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean n_1700_B(File p_219919_1_) {
        boolean bl;
        p_219919_1_.getParentFile().mkdirs();
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter((OutputStream)new FileOutputStream(p_219919_1_), StandardCharsets.UTF_8);
            writer.write(this.n_1700_B(this.u_1723_Y(), this.P_1922_E()));
            bl = true;
        }
        catch (Throwable throwable) {
            boolean flag;
            try {
                n_1700_B.error("Could not save profiler results to {}", (Object)p_219919_1_, (Object)throwable);
                flag = false;
            }
            catch (Throwable throwable2) {
                IOUtils.closeQuietly(writer);
                throw throwable2;
            }
            IOUtils.closeQuietly((Writer)writer);
            return flag;
        }
        IOUtils.closeQuietly((Writer)writer);
        return bl;
    }

    protected String n_1700_B(long p_219929_1_, int p_219929_3_) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("---- Minecraft Profiler Results ----\n");
        stringbuilder.append("// ");
        stringbuilder.append(T_4971_s.w_1484_f());
        stringbuilder.append("\n\n");
        stringbuilder.append("Version: ").append(SharedConstants.n_1700_B().getId()).append('\n');
        stringbuilder.append("Time span: ").append(p_219929_1_ / 1000000L).append(" ms\n");
        stringbuilder.append("Tick span: ").append(p_219929_3_).append(" ticks\n");
        stringbuilder.append("// This is approximately ").append(String.format(Locale.ROOT, "%.2f", Float.valueOf((float)p_219929_3_ / ((float)p_219929_1_ / 1.0E9f)))).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
        stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        this.n_1700_B(0, "root", stringbuilder);
        stringbuilder.append("--- END PROFILE DUMP ---\n\n");
        Map<String, n_1700_B> map = this.v_4262_N();
        if (!map.isEmpty()) {
            stringbuilder.append("--- BEGIN COUNTER DUMP ---\n\n");
            this.n_1700_B(map, stringbuilder, p_219929_3_);
            stringbuilder.append("--- END COUNTER DUMP ---\n\n");
        }
        return stringbuilder.toString();
    }

    private static StringBuilder n_1700_B(StringBuilder p_230098_0_, int p_230098_1_) {
        p_230098_0_.append(String.format("[%02d] ", p_230098_1_));
        for (int i = 0; i < p_230098_1_; ++i) {
            p_230098_0_.append("|   ");
        }
        return p_230098_0_;
    }

    private void n_1700_B(int p_219928_1_, String p_219928_2_, StringBuilder p_219928_3_) {
        List<ResultField> list = this.n_1700_B(p_219928_2_);
        Object2LongMap<String> object2longmap = ((ProfilerPathEntry)ObjectUtils.firstNonNull((Object[])new ProfilerPathEntry[]{this.P_1922_E.get(p_219928_2_), J_1907_R})).R_4764_Y();
        object2longmap.forEach((p_230100_3_, p_230100_4_) -> T_4971_s.n_1700_B(p_219928_3_, p_219928_1_).append('#').append((String)p_230100_3_).append(' ').append(p_230100_4_).append('/').append(p_230100_4_ / (long)this.s_956_w).append('\n'));
        if (list.size() >= 3) {
            for (int i = 1; i < list.size(); ++i) {
                ResultField datapoint = list.get(i);
                T_4971_s.n_1700_B(p_219928_3_, p_219928_1_).append(datapoint.G_564_y).append('(').append(datapoint.R_4764_Y).append('/').append(String.format(Locale.ROOT, "%.0f", Float.valueOf((float)datapoint.R_4764_Y / (float)this.s_956_w))).append(')').append(" - ").append(String.format(Locale.ROOT, "%.2f", datapoint.n_1700_B)).append("%/").append(String.format(Locale.ROOT, "%.2f", datapoint.J_1907_R)).append("%\n");
                if ("unspecified".equals(datapoint.G_564_y)) continue;
                try {
                    this.n_1700_B(p_219928_1_ + 1, p_219928_2_ + "\u001e" + datapoint.G_564_y, p_219928_3_);
                    continue;
                }
                catch (Exception exception) {
                    p_219928_3_.append("[[ EXCEPTION ").append(exception).append(" ]]");
                }
            }
        }
    }

    private void n_1700_B(int p_230095_1_, String p_230095_2_, n_1700_B p_230095_3_, int p_230095_4_, StringBuilder p_230095_5_) {
        T_4971_s.n_1700_B(p_230095_5_, p_230095_1_).append(p_230095_2_).append(" total:").append(p_230095_3_.n_1700_B).append('/').append(p_230095_3_.J_1907_R).append(" average: ").append(p_230095_3_.n_1700_B / (long)p_230095_4_).append('/').append(p_230095_3_.J_1907_R / (long)p_230095_4_).append('\n');
        p_230095_3_.R_4764_Y.entrySet().stream().sorted(G_564_y).forEach(p_230094_4_ -> this.n_1700_B(p_230095_1_ + 1, (String)p_230094_4_.getKey(), (n_1700_B)p_230094_4_.getValue(), p_230095_4_, p_230095_5_));
    }

    private void n_1700_B(Map<String, n_1700_B> p_230102_1_, StringBuilder p_230102_2_, int p_230102_3_) {
        p_230102_1_.forEach((p_230099_3_, p_230099_4_) -> {
            p_230102_2_.append("-- Counter: ").append((String)p_230099_3_).append(" --\n");
            this.n_1700_B(0, "root", p_230099_4_.R_4764_Y.get("root"), p_230102_3_, p_230102_2_);
            p_230102_2_.append("\n\n");
        });
    }

    private static String w_1484_f() {
        String[] astring = new String[]{"Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server."};
        try {
            return astring[(int)(j_3341_s.R_4764_Y() % (long)astring.length)];
        }
        catch (Throwable throwable) {
            return "Witty comment unavailable :(";
        }
    }

    @Override
    public int P_1922_E() {
        return this.s_956_w;
    }

    static class n_1700_B {
        private long n_1700_B;
        private long J_1907_R;
        private final Map<String, n_1700_B> R_4764_Y = Maps.newHashMap();

        private n_1700_B() {
        }

        public void n_1700_B(Iterator<String> p_230112_1_, long p_230112_2_) {
            this.J_1907_R += p_230112_2_;
            if (!p_230112_1_.hasNext()) {
                this.n_1700_B += p_230112_2_;
            } else {
                this.R_4764_Y.computeIfAbsent(p_230112_1_.next(), p_230111_0_ -> new n_1700_B()).n_1700_B(p_230112_1_, p_230112_2_);
            }
        }
    }
}


