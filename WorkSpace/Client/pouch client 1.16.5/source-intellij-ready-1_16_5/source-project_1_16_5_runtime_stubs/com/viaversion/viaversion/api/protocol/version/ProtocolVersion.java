package com.viaversion.viaversion.api.protocol.version;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ProtocolVersion implements Comparable<ProtocolVersion> {
    private static final Map<Integer, ProtocolVersion> BY_ID = new LinkedHashMap<>();
    private static final List<ProtocolVersion> PROTOCOLS = new ArrayList<>();

    public static final ProtocolVersion v1_7_2 = register(4, "1.7.2-1.7.5");
    public static final ProtocolVersion v1_7_6 = register(5, "1.7.6-1.7.10");
    public static final ProtocolVersion v1_8 = register(47, "1.8.x");
    public static final ProtocolVersion v1_9 = register(107, "1.9");
    public static final ProtocolVersion v1_9_1 = register(108, "1.9.1");
    public static final ProtocolVersion v1_9_2 = register(109, "1.9.2");
    public static final ProtocolVersion v1_9_3 = register(110, "1.9.3-1.9.4");
    public static final ProtocolVersion v1_10 = register(210, "1.10.x");
    public static final ProtocolVersion v1_11 = register(315, "1.11");
    public static final ProtocolVersion v1_11_1 = register(316, "1.11.1-1.11.2");
    public static final ProtocolVersion v1_12 = register(335, "1.12");
    public static final ProtocolVersion v1_12_1 = register(338, "1.12.1");
    public static final ProtocolVersion v1_12_2 = register(340, "1.12.2");
    public static final ProtocolVersion v1_13 = register(393, "1.13");
    public static final ProtocolVersion v1_13_1 = register(401, "1.13.1");
    public static final ProtocolVersion v1_13_2 = register(404, "1.13.2");
    public static final ProtocolVersion v1_14 = register(477, "1.14");
    public static final ProtocolVersion v1_14_1 = register(480, "1.14.1");
    public static final ProtocolVersion v1_14_2 = register(485, "1.14.2");
    public static final ProtocolVersion v1_14_3 = register(490, "1.14.3");
    public static final ProtocolVersion v1_14_4 = register(498, "1.14.4");
    public static final ProtocolVersion v1_15 = register(573, "1.15");
    public static final ProtocolVersion v1_15_1 = register(575, "1.15.1");
    public static final ProtocolVersion v1_15_2 = register(578, "1.15.2");
    public static final ProtocolVersion v1_16 = register(735, "1.16");
    public static final ProtocolVersion v1_16_1 = register(736, "1.16.1");
    public static final ProtocolVersion v1_16_2 = register(751, "1.16.2");
    public static final ProtocolVersion v1_16_3 = register(753, "1.16.3");
    public static final ProtocolVersion v1_16_4 = register(754, "1.16.4-1.16.5");
    public static final ProtocolVersion v1_16_5 = v1_16_4;
    public static final ProtocolVersion v1_17 = register(755, "1.17");
    public static final ProtocolVersion v1_17_1 = register(756, "1.17.1");
    public static final ProtocolVersion v1_18 = register(757, "1.18-1.18.1");
    public static final ProtocolVersion v1_18_2 = register(758, "1.18.2");
    public static final ProtocolVersion v1_19 = register(759, "1.19");
    public static final ProtocolVersion v1_19_1 = register(760, "1.19.1-1.19.2");
    public static final ProtocolVersion v1_19_3 = register(761, "1.19.3");
    public static final ProtocolVersion v1_19_4 = register(762, "1.19.4");
    public static final ProtocolVersion v1_20 = register(763, "1.20-1.20.1");
    public static final ProtocolVersion v1_20_2 = register(764, "1.20.2");
    public static final ProtocolVersion v1_20_3 = register(765, "1.20.3-1.20.4");
    public static final ProtocolVersion v1_20_5 = register(766, "1.20.5-1.20.6");
    public static final ProtocolVersion v1_21 = register(767, "1.21-1.21.1");
    public static final ProtocolVersion v1_21_2 = register(768, "1.21.2-1.21.3");
    public static final ProtocolVersion v1_21_4 = register(769, "1.21.4");
    public static final ProtocolVersion v1_21_5 = register(770, "1.21.5");

    private final int version;
    private final String name;

    private ProtocolVersion(int version, String name) {
        this.version = version;
        this.name = name;
    }

    private static ProtocolVersion register(int version, String name) {
        ProtocolVersion protocol = new ProtocolVersion(version, name);
        BY_ID.put(version, protocol);
        PROTOCOLS.add(protocol);
        return protocol;
    }

    public static ProtocolVersion getProtocol(int version) {
        ProtocolVersion protocol = BY_ID.get(version);
        return protocol != null ? protocol : new ProtocolVersion(version, String.valueOf(version));
    }

    public static List<ProtocolVersion> getProtocols() {
        return Collections.unmodifiableList(PROTOCOLS);
    }

    public int getVersion() {
        return this.version;
    }

    public String getName() {
        return this.name;
    }

    public boolean newerThan(ProtocolVersion other) {
        return other != null && this.version > other.version;
    }

    public boolean newerThanOrEqualTo(ProtocolVersion other) {
        return other == null || this.version >= other.version;
    }

    public boolean olderThan(ProtocolVersion other) {
        return other != null && this.version < other.version;
    }

    public boolean olderThanOrEqualTo(ProtocolVersion other) {
        return other == null || this.version <= other.version;
    }

    @Override
    public int compareTo(ProtocolVersion other) {
        return Integer.compare(this.version, other.version);
    }

    @Override
    public String toString() {
        return this.name;
    }
}