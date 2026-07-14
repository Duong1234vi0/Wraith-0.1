package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.modules.impl.player.AutoLeave;
import sky.core.modules.impl.visuals.Interface;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.staffmanager.Staff;
import sky.core.utils.managers.impl.staffmanager.StaffManager;

public class StaffHandler implements Wrapper {
   private static StaffHandler instance;
   private static final Locale LOCALE;
   private static final Pattern NAME_PATTERN;
   private final List staff = new ArrayList();

   public StaffHandler() {
      instance = this;
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if ((Minecraft.player.ticksExisted & 9) == 0) {
            if (SkyCore.getInstance().getModuleManager().getModule(Interface.class).isEnabled() && Interface.elements.is("Администрация онлайн") || SkyCore.getInstance().getModuleManager().getModule(AutoLeave.class).isEnabled()) {
               this.update();
            }

         }
      }
   }

   public void update() {
      List online = this.getOnlineStaff();
      List vanished = getVanishedStaff();
      online.sort(Comparator.comparing(Staff::getName, String.CASE_INSENSITIVE_ORDER));
      vanished.sort(Comparator.comparing(Staff::getName, String.CASE_INSENSITIVE_ORDER));
      synchronized(this.staff) {
         this.staff.clear();
         this.staff.addAll(online);
         this.staff.addAll(vanished);
      }
   }

   private List getOnlineStaff() {
      if (mc.world != null && mc.getConnection() != null) {
         StaffManager staffManager = SkyCore.getInstance().getStaffManager();
         Set staffNamesLower = staffManager != null ? (Set)staffManager.getStaff().stream().map(StaffManager.StaffEntry::getName).filter(Objects::nonNull).map((name) -> {
            return name.toLowerCase(LOCALE);
         }).collect(Collectors.toSet()) : Collections.emptySet();
         List list = new ArrayList();
         Iterator var4 = mc.getConnection().getPlayerInfoMap().iterator();

         while(true) {
            String username;
            ITextComponent rawPrefix;
            do {
               do {
                  NetworkPlayerInfo info;
                  do {
                     if (!var4.hasNext()) {
                        return list;
                     }

                     info = (NetworkPlayerInfo)var4.next();
                  } while(info == null);

                  username = info.getGameProfile().getName();
               } while(username == null);

               rawPrefix = StringTextComponent.EMPTY;
               Iterator var8 = mc.world.getScoreboard().getTeams().iterator();

               while(var8.hasNext()) {
                  ScorePlayerTeam team = (ScorePlayerTeam)var8.next();
                  if (team.getMembershipCollection().contains(username)) {
                     rawPrefix = team.getPrefix();
                     break;
                  }
               }
            } while(!staffNamesLower.contains(username.toLowerCase(LOCALE)) && !isStaffPrefix(rawPrefix.getString()));

            list.add(new Staff(username, rawPrefix, false));
         }
      } else {
         return Collections.emptyList();
      }
   }

   public static List getVanishedStaff() {
      if (mc.world != null && mc.getConnection() != null) {
         Set onlineNames = (Set)mc.getConnection().getPlayerInfoMap().stream().filter(Objects::nonNull).map((info) -> {
            return info.getGameProfile().getName();
         }).filter(Objects::nonNull).collect(Collectors.toSet());
         Scoreboard scoreboard = mc.world.getScoreboard();
         List list = new ArrayList();
         Iterator var3 = scoreboard.getTeams().stream().sorted(Comparator.comparing(Team::getName)).toList().iterator();

         while(var3.hasNext()) {
            ScorePlayerTeam team = (ScorePlayerTeam)var3.next();
            String name = (String)team.getMembershipCollection().stream().findFirst().orElse((Object)null);
            if (name != null && NAME_PATTERN.matcher(name).matches() && !onlineNames.contains(name) && !team.getDisplayName().getString().trim().startsWith("npc") && !team.getDisplayName().getString().trim().startsWith("collideRule_") && !team.getDisplayName().getString().trim().startsWith("FS_") && !team.getDisplayName().getString().trim().startsWith("STAFF-") && !team.getDisplayName().getString().trim().startsWith("HELPER-") && !team.getDisplayName().getString().trim().startsWith("ADMIN-") && !team.getDisplayName().getString().trim().startsWith("MODER-")) {
               ITextComponent rawPrefix = team.getPrefix();
               list.add(new Staff(name, rawPrefix, true));
            }
         }

         return list;
      } else {
         return Collections.emptyList();
      }
   }

   private static boolean isStaffPrefix(String rawPrefix) {
      String p = TextFormatting.getTextWithoutFormattingCodes(rawPrefix);
      if (p != null && !p.isEmpty()) {
         p = p.toLowerCase(LOCALE);
         return p.contains("helper") || p.contains("moder") || p.contains("admin") || p.contains("curator") || p.contains("builder") || p.contains("yt") || p.contains("кур") || p.contains("тех.админ") || p.contains("s.kurator") || p.contains("поддержка") || p.contains("сотруд") || p.contains("ꔓ") || p.contains("ꔉ") || p.contains("ꔅ") || p.contains("ꔁ") || p.contains("ꔷ") || p.contains("ꔳ") || p.contains("ꔩ") || p.contains("ꔥ") || p.contains("ꔡ") || p.contains("ꔗ");
      } else {
         return false;
      }
   }

   public List getStaff() {
      return this.staff;
   }

   public static StaffHandler getInstance() {
      return instance;
   }

   static {
      LOCALE = Locale.ROOT;
      NAME_PATTERN = Pattern.compile("^\\w{3,16}$");
   }
}
