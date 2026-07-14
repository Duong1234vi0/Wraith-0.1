/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.ProfileLookupCallback
 *  com.mojang.authlib.yggdrasil.ProfileNotFoundException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.G_4584_Z;
import lightning.product.H_1468_N;
import lightning.product.H_4757_Q;
import lightning.product.ServerOpList;
import lightning.product.I_1965_o;
import lightning.product.BanListEntry;
import lightning.product.UserBanListEntry;
import lightning.product.V_4604_M;
import lightning.product.IpBanList;
import lightning.product.a_3913_L;
import lightning.product.g_1995_W;
import lightning.product.q_1829_g;
import lightning.product.UserWhiteListEntry;
import lightning.product.IpBanListEntry;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class d_2511_z {
    private static final Logger P_1922_E = LogManager.getLogger();
    public static final File n_1700_B = new File("banned-ips.txt");
    public static final File J_1907_R = new File("banned-players.txt");
    public static final File R_4764_Y = new File("ops.txt");
    public static final File G_564_y = new File("white-list.txt");

    static List<String> n_1700_B(File inFile, Map<String, String[]> read) throws IOException {
        List list = Files.readLines((File)inFile, (Charset)StandardCharsets.UTF_8);
        for (String s : list) {
            if ((s = s.trim()).startsWith("#") || s.length() < 1) continue;
            String[] astring = s.split("\\|");
            read.put(astring[0].toLowerCase(Locale.ROOT), astring);
        }
        return list;
    }

    private static void n_1700_B(G_564_y server, Collection<String> names, ProfileLookupCallback callback) {
        String[] astring = (String[])names.stream().filter(p_201150_0_ -> !H_1468_N.J_1907_R(p_201150_0_)).toArray(String[]::new);
        if (server.Z_976_R()) {
            server.PlayerInfo().findProfilesByNames(astring, Agent.MINECRAFT, callback);
        } else {
            for (String s : astring) {
                UUID uuid = a_3913_L.n_1700_B(new GameProfile((UUID)null, s));
                GameProfile gameprofile = new GameProfile(uuid, s);
                callback.onProfileLookupSucceeded(gameprofile);
            }
        }
    }

    public static boolean n_1700_B(final G_564_y server) {
        final q_1829_g banlist = new q_1829_g(g_1995_W.n_1700_B);
        if (J_1907_R.exists() && J_1907_R.isFile()) {
            if (banlist.J_1907_R().exists()) {
                try {
                    banlist.u_1723_Y();
                }
                catch (IOException ioexception1) {
                    P_1922_E.warn("Could not load existing file {}", (Object)banlist.J_1907_R().getName(), (Object)ioexception1);
                }
            }
            try {
                final HashMap map = Maps.newHashMap();
                d_2511_z.n_1700_B(J_1907_R, map);
                ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){

                    public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                        server.V_1225_t().n_1700_B(p_onProfileLookupSucceeded_1_);
                        String[] astring = (String[])map.get(p_onProfileLookupSucceeded_1_.getName().toLowerCase(Locale.ROOT));
                        if (astring == null) {
                            P_1922_E.warn("Could not convert user banlist entry for {}", (Object)p_onProfileLookupSucceeded_1_.getName());
                            throw new n_1700_B("Profile not in the conversionlist");
                        }
                        Date date = astring.length > 1 ? d_2511_z.n_1700_B(astring[1], (Date)null) : null;
                        String s = astring.length > 2 ? astring[2] : null;
                        Date date1 = astring.length > 3 ? d_2511_z.n_1700_B(astring[3], (Date)null) : null;
                        String s1 = astring.length > 4 ? astring[4] : null;
                        banlist.n_1700_B(new UserBanListEntry(p_onProfileLookupSucceeded_1_, date, s, date1, s1));
                    }

                    public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                        P_1922_E.warn("Could not lookup user banlist entry for {}", (Object)p_onProfileLookupFailed_1_.getName(), (Object)p_onProfileLookupFailed_2_);
                        if (!(p_onProfileLookupFailed_2_ instanceof ProfileNotFoundException)) {
                            throw new n_1700_B("Could not request user " + p_onProfileLookupFailed_1_.getName() + " from backend systems", p_onProfileLookupFailed_2_);
                        }
                    }
                };
                d_2511_z.n_1700_B(server, map.keySet(), profilelookupcallback);
                banlist.P_1922_E();
                d_2511_z.J_1907_R(J_1907_R);
                return true;
            }
            catch (IOException ioexception) {
                P_1922_E.warn("Could not read old user banlist to convert it!", (Throwable)ioexception);
                return false;
            }
            catch (n_1700_B preyggdrasilconverter$conversionerror) {
                P_1922_E.error("Conversion failed, please try again later", (Throwable)preyggdrasilconverter$conversionerror);
                return false;
            }
        }
        return true;
    }

    public static boolean J_1907_R(G_564_y server) {
        IpBanList ipbanlist = new IpBanList(g_1995_W.J_1907_R);
        if (n_1700_B.exists() && n_1700_B.isFile()) {
            if (ipbanlist.J_1907_R().exists()) {
                try {
                    ipbanlist.u_1723_Y();
                }
                catch (IOException ioexception1) {
                    P_1922_E.warn("Could not load existing file {}", (Object)ipbanlist.J_1907_R().getName(), (Object)ioexception1);
                }
            }
            try {
                HashMap map = Maps.newHashMap();
                d_2511_z.n_1700_B(n_1700_B, map);
                for (String s : map.keySet()) {
                    String[] astring = (String[])map.get(s);
                    Date date = astring.length > 1 ? d_2511_z.n_1700_B(astring[1], (Date)null) : null;
                    String s1 = astring.length > 2 ? astring[2] : null;
                    Date date1 = astring.length > 3 ? d_2511_z.n_1700_B(astring[3], (Date)null) : null;
                    String s2 = astring.length > 4 ? astring[4] : null;
                    ipbanlist.n_1700_B(new IpBanListEntry(s, date, s1, date1, s2));
                }
                ipbanlist.P_1922_E();
                d_2511_z.J_1907_R(n_1700_B);
                return true;
            }
            catch (IOException ioexception) {
                P_1922_E.warn("Could not parse old ip banlist to convert it!", (Throwable)ioexception);
                return false;
            }
        }
        return true;
    }

    public static boolean R_4764_Y(final G_564_y server) {
        final ServerOpList oplist = new ServerOpList(g_1995_W.R_4764_Y);
        if (R_4764_Y.exists() && R_4764_Y.isFile()) {
            if (oplist.J_1907_R().exists()) {
                try {
                    oplist.u_1723_Y();
                }
                catch (IOException ioexception1) {
                    P_1922_E.warn("Could not load existing file {}", (Object)oplist.J_1907_R().getName(), (Object)ioexception1);
                }
            }
            try {
                List list = Files.readLines((File)R_4764_Y, (Charset)StandardCharsets.UTF_8);
                ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){

                    public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                        server.V_1225_t().n_1700_B(p_onProfileLookupSucceeded_1_);
                        oplist.n_1700_B(new I_1965_o(p_onProfileLookupSucceeded_1_, server.t_1786_h(), false));
                    }

                    public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                        P_1922_E.warn("Could not lookup oplist entry for {}", (Object)p_onProfileLookupFailed_1_.getName(), (Object)p_onProfileLookupFailed_2_);
                        if (!(p_onProfileLookupFailed_2_ instanceof ProfileNotFoundException)) {
                            throw new n_1700_B("Could not request user " + p_onProfileLookupFailed_1_.getName() + " from backend systems", p_onProfileLookupFailed_2_);
                        }
                    }
                };
                d_2511_z.n_1700_B(server, list, profilelookupcallback);
                oplist.P_1922_E();
                d_2511_z.J_1907_R(R_4764_Y);
                return true;
            }
            catch (IOException ioexception) {
                P_1922_E.warn("Could not read old oplist to convert it!", (Throwable)ioexception);
                return false;
            }
            catch (n_1700_B preyggdrasilconverter$conversionerror) {
                P_1922_E.error("Conversion failed, please try again later", (Throwable)preyggdrasilconverter$conversionerror);
                return false;
            }
        }
        return true;
    }

    public static boolean G_564_y(final G_564_y server) {
        final G_4584_Z whitelist = new G_4584_Z(g_1995_W.G_564_y);
        if (G_564_y.exists() && G_564_y.isFile()) {
            if (whitelist.J_1907_R().exists()) {
                try {
                    whitelist.u_1723_Y();
                }
                catch (IOException ioexception1) {
                    P_1922_E.warn("Could not load existing file {}", (Object)whitelist.J_1907_R().getName(), (Object)ioexception1);
                }
            }
            try {
                List list = Files.readLines((File)G_564_y, (Charset)StandardCharsets.UTF_8);
                ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){

                    public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                        server.V_1225_t().n_1700_B(p_onProfileLookupSucceeded_1_);
                        whitelist.n_1700_B(new UserWhiteListEntry(p_onProfileLookupSucceeded_1_));
                    }

                    public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                        P_1922_E.warn("Could not lookup user whitelist entry for {}", (Object)p_onProfileLookupFailed_1_.getName(), (Object)p_onProfileLookupFailed_2_);
                        if (!(p_onProfileLookupFailed_2_ instanceof ProfileNotFoundException)) {
                            throw new n_1700_B("Could not request user " + p_onProfileLookupFailed_1_.getName() + " from backend systems", p_onProfileLookupFailed_2_);
                        }
                    }
                };
                d_2511_z.n_1700_B(server, list, profilelookupcallback);
                whitelist.P_1922_E();
                d_2511_z.J_1907_R(G_564_y);
                return true;
            }
            catch (IOException ioexception) {
                P_1922_E.warn("Could not read old whitelist to convert it!", (Throwable)ioexception);
                return false;
            }
            catch (n_1700_B preyggdrasilconverter$conversionerror) {
                P_1922_E.error("Conversion failed, please try again later", (Throwable)preyggdrasilconverter$conversionerror);
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static UUID n_1700_B(final G_564_y server, String username) {
        if (!H_1468_N.J_1907_R(username) && username.length() <= 16) {
            GameProfile gameprofile = server.V_1225_t().n_1700_B(username);
            if (gameprofile != null && gameprofile.getId() != null) {
                return gameprofile.getId();
            }
            if (!server.T_2506_i() && server.Z_976_R()) {
                final ArrayList list = Lists.newArrayList();
                ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){

                    public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                        server.V_1225_t().n_1700_B(p_onProfileLookupSucceeded_1_);
                        list.add(p_onProfileLookupSucceeded_1_);
                    }

                    public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                        P_1922_E.warn("Could not lookup user whitelist entry for {}", (Object)p_onProfileLookupFailed_1_.getName(), (Object)p_onProfileLookupFailed_2_);
                    }
                };
                d_2511_z.n_1700_B(server, Lists.newArrayList((Object[])new String[]{username}), profilelookupcallback);
                return !list.isEmpty() && ((GameProfile)list.get(0)).getId() != null ? ((GameProfile)list.get(0)).getId() : null;
            }
            return a_3913_L.n_1700_B(new GameProfile((UUID)null, username));
        }
        try {
            return UUID.fromString(username);
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    public static boolean n_1700_B(final V_4604_M server) {
        final File file1 = d_2511_z.v_4262_N(server);
        final File file2 = new File(file1.getParentFile(), "playerdata");
        final File file3 = new File(file1.getParentFile(), "unknownplayers");
        if (file1.exists() && file1.isDirectory()) {
            File[] afile = file1.listFiles();
            ArrayList list = Lists.newArrayList();
            for (File file4 : afile) {
                String s1;
                String s = file4.getName();
                if (!s.toLowerCase(Locale.ROOT).endsWith(".dat") || (s1 = s.substring(0, s.length() - ".dat".length())).isEmpty()) continue;
                list.add(s1);
            }
            try {
                Object[] astring = list.toArray(new String[list.size()]);
                ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback(){
                    final /* synthetic */ String[] P_1922_E;
                    {
                        this.P_1922_E = stringArray;
                    }

                    public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                        server.V_1225_t().n_1700_B(p_onProfileLookupSucceeded_1_);
                        UUID uuid = p_onProfileLookupSucceeded_1_.getId();
                        if (uuid == null) {
                            throw new n_1700_B("Missing UUID for user profile " + p_onProfileLookupSucceeded_1_.getName());
                        }
                        this.n_1700_B(file2, this.n_1700_B(p_onProfileLookupSucceeded_1_), uuid.toString());
                    }

                    public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                        P_1922_E.warn("Could not lookup user uuid for {}", (Object)p_onProfileLookupFailed_1_.getName(), (Object)p_onProfileLookupFailed_2_);
                        if (!(p_onProfileLookupFailed_2_ instanceof ProfileNotFoundException)) {
                            throw new n_1700_B("Could not request user " + p_onProfileLookupFailed_1_.getName() + " from backend systems", p_onProfileLookupFailed_2_);
                        }
                        String s2 = this.n_1700_B(p_onProfileLookupFailed_1_);
                        this.n_1700_B(file3, s2, s2);
                    }

                    private void n_1700_B(File p_152743_1_, String p_152743_2_, String p_152743_3_) {
                        File file5 = new File(file1, p_152743_2_ + ".dat");
                        File file6 = new File(p_152743_1_, p_152743_3_ + ".dat");
                        d_2511_z.n_1700_B(p_152743_1_);
                        if (!file5.renameTo(file6)) {
                            throw new n_1700_B("Could not convert file for " + p_152743_2_);
                        }
                    }

                    private String n_1700_B(GameProfile p_152744_1_) {
                        String s2 = null;
                        for (String s3 : this.P_1922_E) {
                            if (s3 == null || !s3.equalsIgnoreCase(p_152744_1_.getName())) continue;
                            s2 = s3;
                            break;
                        }
                        if (s2 == null) {
                            throw new n_1700_B("Could not find the filename for " + p_152744_1_.getName() + " anymore");
                        }
                        return s2;
                    }
                };
                d_2511_z.n_1700_B(server, Lists.newArrayList((Object[])astring), profilelookupcallback);
                return true;
            }
            catch (n_1700_B preyggdrasilconverter$conversionerror) {
                P_1922_E.error("Conversion failed, please try again later", (Throwable)preyggdrasilconverter$conversionerror);
                return false;
            }
        }
        return true;
    }

    private static void n_1700_B(File dir) {
        if (dir.exists() ? !dir.isDirectory() : !dir.mkdirs()) {
            throw new n_1700_B("Can't create directory " + dir.getName() + " in world save directory.");
        }
    }

    public static boolean P_1922_E(G_564_y p_219587_0_) {
        boolean flag = d_2511_z.n_1700_B();
        return flag && d_2511_z.u_1723_Y(p_219587_0_);
    }

    private static boolean n_1700_B() {
        boolean flag = false;
        if (J_1907_R.exists() && J_1907_R.isFile()) {
            flag = true;
        }
        boolean flag1 = false;
        if (n_1700_B.exists() && n_1700_B.isFile()) {
            flag1 = true;
        }
        boolean flag2 = false;
        if (R_4764_Y.exists() && R_4764_Y.isFile()) {
            flag2 = true;
        }
        boolean flag3 = false;
        if (G_564_y.exists() && G_564_y.isFile()) {
            flag3 = true;
        }
        if (!(flag || flag1 || flag2 || flag3)) {
            return true;
        }
        P_1922_E.warn("**** FAILED TO START THE SERVER AFTER ACCOUNT CONVERSION!");
        P_1922_E.warn("** please remove the following files and restart the server:");
        if (flag) {
            P_1922_E.warn("* {}", (Object)J_1907_R.getName());
        }
        if (flag1) {
            P_1922_E.warn("* {}", (Object)n_1700_B.getName());
        }
        if (flag2) {
            P_1922_E.warn("* {}", (Object)R_4764_Y.getName());
        }
        if (flag3) {
            P_1922_E.warn("* {}", (Object)G_564_y.getName());
        }
        return false;
    }

    private static boolean u_1723_Y(G_564_y p_219589_0_) {
        File file1 = d_2511_z.v_4262_N(p_219589_0_);
        if (!file1.exists() || !file1.isDirectory() || file1.list().length <= 0 && file1.delete()) {
            return true;
        }
        P_1922_E.warn("**** DETECTED OLD PLAYER DIRECTORY IN THE WORLD SAVE");
        P_1922_E.warn("**** THIS USUALLY HAPPENS WHEN THE AUTOMATIC CONVERSION FAILED IN SOME WAY");
        P_1922_E.warn("** please restart the server and if the problem persists, remove the directory '{}'", (Object)file1.getPath());
        return false;
    }

    private static File v_4262_N(G_564_y p_219585_0_) {
        return p_219585_0_.n_1700_B(H_4757_Q.G_564_y).toFile();
    }

    private static void J_1907_R(File convertedFile) {
        File file1 = new File(convertedFile.getName() + ".converted");
        convertedFile.renameTo(file1);
    }

    private static Date n_1700_B(String input, Date defaultValue) {
        Date date;
        try {
            date = BanListEntry.n_1700_B.parse(input);
        }
        catch (ParseException parseexception) {
            date = defaultValue;
        }
        return date;
    }

    static class n_1700_B
    extends RuntimeException {
        private n_1700_B(String message, Throwable cause) {
            super(message, cause);
        }

        private n_1700_B(String message) {
            super(message);
        }
    }
}


