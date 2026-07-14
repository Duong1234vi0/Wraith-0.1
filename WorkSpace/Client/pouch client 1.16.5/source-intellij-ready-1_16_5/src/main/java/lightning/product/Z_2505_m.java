/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Z_2505_m
implements DynamicMBean {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final G_564_y J_1907_R;
    private final MBeanInfo R_4764_Y;
    private final Map<String, n_1700_B> G_564_y = Stream.of(new n_1700_B("tickTimes", this::R_4764_Y, "Historical tick times (ms)", long[].class), new n_1700_B("averageTickTime", this::J_1907_R, "Current average tick time (ms)", Long.TYPE)).collect(Collectors.toMap(p_233492_0_ -> p_233492_0_.n_1700_B, Function.identity()));

    private Z_2505_m(G_564_y p_i231479_1_) {
        this.J_1907_R = p_i231479_1_;
        MBeanAttributeInfo[] ambeanattributeinfo = (MBeanAttributeInfo[])this.G_564_y.values().stream().map(p_233489_0_ -> p_233489_0_.n_1700_B()).toArray(MBeanAttributeInfo[]::new);
        this.R_4764_Y = new MBeanInfo(Z_2505_m.class.getSimpleName(), "metrics for dedicated server", ambeanattributeinfo, null, null, new MBeanNotificationInfo[0]);
    }

    public static void n_1700_B(G_564_y p_233490_0_) {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(new Z_2505_m(p_233490_0_), new ObjectName("net.minecraft.server:type=Server"));
        }
        catch (InstanceAlreadyExistsException | MBeanRegistrationException | MalformedObjectNameException | NotCompliantMBeanException malformedobjectnameexception) {
            n_1700_B.warn("Failed to initialise server as JMX bean", (Throwable)malformedobjectnameexception);
        }
    }

    private float J_1907_R() {
        return this.J_1907_R.r_3651_U();
    }

    private long[] R_4764_Y() {
        return this.J_1907_R.v_4262_N;
    }

    @Nullable
    public Object n_1700_B(String p_getAttribute_1_) {
        n_1700_B serverinfombean$attribute = this.G_564_y.get(p_getAttribute_1_);
        return serverinfombean$attribute == null ? null : serverinfombean$attribute.J_1907_R.get();
    }

    public void n_1700_B(Attribute p_setAttribute_1_) {
    }

    public AttributeList n_1700_B(String[] p_getAttributes_1_) {
        List<Attribute> list = Arrays.stream(p_getAttributes_1_).map(this.G_564_y::get).filter(Objects::nonNull).map(p_233488_0_ -> new Attribute(p_233488_0_.n_1700_B, p_233488_0_.J_1907_R.get())).collect(Collectors.toList());
        return new AttributeList(list);
    }

    public AttributeList n_1700_B(AttributeList p_setAttributes_1_) {
        return new AttributeList();
    }

    @Nullable
    public Object n_1700_B(String p_invoke_1_, Object[] p_invoke_2_, String[] p_invoke_3_) {
        return null;
    }

    public MBeanInfo n_1700_B() {
        return this.R_4764_Y;
    }

    static final class n_1700_B {
        private final String n_1700_B;
        private final Supplier<Object> J_1907_R;
        private final String R_4764_Y;
        private final Class<?> G_564_y;

        private n_1700_B(String p_i231480_1_, Supplier<Object> p_i231480_2_, String p_i231480_3_, Class<?> p_i231480_4_) {
            this.n_1700_B = p_i231480_1_;
            this.J_1907_R = p_i231480_2_;
            this.R_4764_Y = p_i231480_3_;
            this.G_564_y = p_i231480_4_;
        }

        private MBeanAttributeInfo n_1700_B() {
            return new MBeanAttributeInfo(this.n_1700_B, this.G_564_y.getSimpleName(), this.R_4764_Y, true, false, false);
        }
    }
}

