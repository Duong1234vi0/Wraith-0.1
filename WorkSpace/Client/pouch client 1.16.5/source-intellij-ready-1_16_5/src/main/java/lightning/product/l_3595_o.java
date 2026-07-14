/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.net.IDN;
import java.util.Hashtable;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class l_3595_o {
    private final String n_1700_B;
    private final int J_1907_R;

    private l_3595_o(String address, int port) {
        this.n_1700_B = address;
        this.J_1907_R = port;
    }

    public String n_1700_B() {
        try {
            return IDN.toASCII(this.n_1700_B);
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return "";
        }
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public static l_3595_o n_1700_B(String addrString) {
        int j;
        int i;
        if (addrString == null) {
            return null;
        }
        String[] astring = addrString.split(":");
        if (addrString.startsWith("[") && (i = addrString.indexOf("]")) > 0) {
            String s = addrString.substring(1, i);
            String s1 = addrString.substring(i + 1).trim();
            if (s1.startsWith(":") && !s1.isEmpty()) {
                s1 = s1.substring(1);
                astring = new String[]{s, s1};
            } else {
                astring = new String[]{s};
            }
        }
        if (astring.length > 2) {
            astring = new String[]{addrString};
        }
        String s2 = astring[0];
        int n = j = astring.length > 1 ? l_3595_o.n_1700_B(astring[1], 25565) : 25565;
        if (j == 25565) {
            Pair<String, Integer> pair = l_3595_o.J_1907_R(s2);
            s2 = (String)pair.getFirst();
            j = (Integer)pair.getSecond();
        }
        return new l_3595_o(s2, j);
    }

    private static Pair<String, Integer> J_1907_R(String p_241677_0_) {
        try {
            String s = "com.sun.jndi.dns.DnsContextFactory";
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            hashtable.put("java.naming.provider.url", "dns:");
            hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
            InitialDirContext dircontext = new InitialDirContext(hashtable);
            Attributes attributes = dircontext.getAttributes("_minecraft._tcp." + p_241677_0_, new String[]{"SRV"});
            Attribute attribute = attributes.get("srv");
            if (attribute != null) {
                String[] astring = attribute.get().toString().split(" ", 4);
                return Pair.of((Object)astring[3], (Object)l_3595_o.n_1700_B(astring[2], 25565));
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return Pair.of((Object)p_241677_0_, (Object)25565);
    }

    private static int n_1700_B(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        }
        catch (Exception exception) {
            return defaultValue;
        }
    }
}

