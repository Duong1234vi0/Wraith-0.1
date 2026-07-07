package unique.core.features.modules;

import com.mojang.logging.LogUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.class_124;
import net.minecraft.class_1713;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3675;
import net.minecraft.class_4587;
import net.minecraft.class_7202;
import net.minecraft.class_7204;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.core.manager.client.CommandManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.client.ClientSettings;
import unique.core.features.modules.client.UnHook;
import unique.core.features.modules.client.Windows;
import unique.core.gui.notification.Notification;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;

public abstract class Module {
   private final Setting<Bind> bind = new Setting<>("Keybind", new Bind(-1, false, false));
   private final Setting<Boolean> drawn = new Setting<>("Drawn", true);
   private final Setting<Boolean> enabled = new Setting<>("Enabled", false);
   private final String description;
   private final Module.Category category;
   private final String displayName;
   private final List<String> ignoreSoundList = Arrays.asList("ClickGui", "ThunderGui", "HudEditor");
   public static final class_310 mc = class_310.method_1551();

   public Module(@NotNull String name, @NotNull Module.Category category) {
      this.displayName = name;
      this.description = "descriptions." + category.getName().toLowerCase() + "." + name.toLowerCase();
      this.category = category;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void onLogin() {
   }

   public void onLogout() {
   }

   public void onUpdate() {
   }

   public void onRender2D(class_332 event) {
   }

   public void onRender3D(class_4587 event) {
   }

   public void onUnload() {
   }

   public boolean isToggleable() {
      return true;
   }

   protected void sendPacket(class_2596<?> packet) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(packet);
      }
   }

   protected void sendPacketSilent(class_2596<?> packet) {
      if (mc.method_1562() != null) {
         UniqueCore.core.silentPackets.add(packet);
         mc.method_1562().method_52787(packet);
      }
   }

   protected void sendSequencedPacket(class_7204 packetCreator) {
      if (mc.method_1562() != null && mc.field_1687 != null) {
         class_7202 pendingUpdateManager = mc.field_1687.method_41925().method_41937();

         try {
            int i = pendingUpdateManager.method_41942();
            mc.method_1562().method_52787(packetCreator.predict(i));
         } catch (Throwable var6) {
            if (pendingUpdateManager != null) {
               try {
                  pendingUpdateManager.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (pendingUpdateManager != null) {
            pendingUpdateManager.close();
         }
      }
   }

   public String getDisplayInfo() {
      return null;
   }

   public boolean isOn() {
      return this.enabled.getValue();
   }

   public boolean isOff() {
      return !this.enabled.getValue();
   }

   public void setEnabled(boolean enabled) {
      this.enabled.setValue(enabled);
   }

   public void onThread() {
   }

   public void enable() {
      if (!(this instanceof UnHook)) {
         this.enabled.setValue(true);
      }

      if (!fullNullCheck() || this instanceof UnHook || this instanceof Windows) {
         this.onEnable();
      }

      if (this.isOn()) {
         UniqueCore.EVENT_BUS.subscribe(this);
      }

      if (!fullNullCheck()) {
         LogUtils.getLogger().info("[UniqueCore] Enabled " + this.getName());
         Managers.MODULE.sortModules();
         if (!this.ignoreSoundList.contains(this.getDisplayName())) {
            Managers.NOTIFICATION.publicity(this.getDisplayName(), ClientSettings.isRu() ? "Модуль включен!" : "Was Enabled!", 2, Notification.Type.ENABLED);
            Managers.SOUND.playEnable();
         }
      }
   }

   public void disable(String reason) {
      this.sendMessage(reason);
      this.disable();
   }

   public void disable() {
      try {
         UniqueCore.EVENT_BUS.unsubscribe(this);
      } catch (Exception var2) {
      }

      this.enabled.setValue(false);
      Managers.MODULE.sortModules();
      if (!fullNullCheck()) {
         this.onDisable();
         UniqueCore.LOGGER.info("[UniqueCore] Disabled {}", this.getName());
         if (!this.ignoreSoundList.contains(this.getDisplayName())) {
            Managers.NOTIFICATION.publicity(this.getDisplayName(), ClientSettings.isRu() ? "Модуль выключен!" : "Was Disabled!", 2, Notification.Type.DISABLED);
            Managers.SOUND.playDisable();
         }
      }
   }

   public void toggle() {
      if (this.enabled.getValue()) {
         this.disable();
      } else {
         this.enable();
      }
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean isDrawn() {
      return this.drawn.getValue();
   }

   public void setDrawn(boolean d) {
      this.drawn.setValue(d);
   }

   public Module.Category getCategory() {
      return this.category;
   }

   public Bind getBind() {
      return this.bind.getValue();
   }

   public void setBind(int key, boolean mouse, boolean hold) {
      this.setBind(new Bind(key, mouse, hold));
   }

   public void setBind(Bind b) {
      this.bind.setValue(b);
   }

   public boolean listening() {
      return this.isOn();
   }

   public String getFullArrayString() {
      return this.getDisplayName()
         + class_124.field_1080
         + (this.getDisplayInfo() != null ? " [" + class_124.field_1068 + this.getDisplayInfo() + class_124.field_1080 + "]" : "");
   }

   public static boolean fullNullCheck() {
      return mc.field_1724 == null || mc.field_1687 == null || ModuleManager.unHook.isEnabled();
   }

   public String getName() {
      return this.getDisplayName();
   }

   public List<Setting<?>> getSettings() {
      ArrayList<Setting<?>> settingList = new ArrayList<>();

      for (Class<?> currentSuperclass = this.getClass(); currentSuperclass != null; currentSuperclass = currentSuperclass.getSuperclass()) {
         for (Field field : currentSuperclass.getDeclaredFields()) {
            if (Setting.class.isAssignableFrom(field.getType())) {
               try {
                  field.setAccessible(true);
                  settingList.add((Setting<?>)field.get(this));
               } catch (IllegalAccessException var8) {
                  UniqueCore.LOGGER.warn(var8.getMessage());
               }
            }
         }
      }

      settingList.forEach(s -> s.setModule(this));
      return settingList;
   }

   public boolean isEnabled() {
      return this.isOn();
   }

   public boolean isDisabled() {
      return !this.isEnabled();
   }

   public static void clickSlot(int id) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   public static void clickSlot(int id, class_1713 type) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, type, mc.field_1724);
      }
   }

   public static void clickSlot(int id, int button, class_1713 type) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, button, type, mc.field_1724);
      }
   }

   public void sendMessage(String message) {
      if (!fullNullCheck() && ClientSettings.clientMessages.getValue() && !ModuleManager.unHook.isEnabled()) {
         if (mc.method_18854()) {
            mc.field_1724
               .method_43496(
                  class_2561.method_30163(
                     CommandManager.getClientMessage()
                        + " "
                        + class_124.field_1080
                        + "["
                        + class_124.field_1064
                        + this.getDisplayName()
                        + class_124.field_1080
                        + "] "
                        + message
                  )
               );
         } else {
            mc.method_40000(
               () -> mc.field_1724
                  .method_43496(
                     class_2561.method_30163(
                        CommandManager.getClientMessage()
                           + " "
                           + class_124.field_1080
                           + "["
                           + class_124.field_1064
                           + this.getDisplayName()
                           + class_124.field_1080
                           + "] "
                           + message
                     )
                  )
            );
         }
      }
   }

   public void sendChatMessage(String message) {
      if (!fullNullCheck()) {
         mc.method_1562().method_45729(message);
      }
   }

   public void sendChatCommand(String command) {
      if (!fullNullCheck()) {
         mc.method_1562().method_45730(command);
      }
   }

   public void debug(String message) {
      if (!fullNullCheck() && ClientSettings.debug.getValue()) {
         if (mc.method_18854()) {
            mc.field_1724
               .method_43496(
                  class_2561.method_30163(
                     CommandManager.getClientMessage()
                        + " "
                        + class_124.field_1080
                        + "["
                        + class_124.field_1064
                        + this.getDisplayName()
                        + class_124.field_1080
                        + "] [\ud83d\udd27] "
                        + message
                  )
               );
         } else {
            mc.method_40000(
               () -> mc.field_1724
                  .method_43496(
                     class_2561.method_30163(
                        CommandManager.getClientMessage()
                           + " "
                           + class_124.field_1080
                           + "["
                           + class_124.field_1064
                           + this.getDisplayName()
                           + class_124.field_1080
                           + "] [\ud83d\udd27] "
                           + message
                     )
                  )
            );
         }
      }
   }

   public boolean isKeyPressed(int button) {
      if (button != -1 && !ModuleManager.unHook.isEnabled()) {
         if (Managers.MODULE.activeMouseKeys.contains(button)) {
            Managers.MODULE.activeMouseKeys.clear();
            return true;
         } else {
            return button < 10 ? false : class_3675.method_15987(mc.method_22683().method_4490(), button);
         }
      } else {
         return false;
      }
   }

   public boolean isKeyPressed(Setting<Bind> bind) {
      return bind.getValue().getKey() != -1 && !ModuleManager.unHook.isEnabled() ? this.isKeyPressed(bind.getValue().getKey()) : false;
   }

   @Nullable
   public Setting<?> getSettingByName(String name) {
      for (Setting<?> setting : this.getSettings()) {
         if (setting.getName().equalsIgnoreCase(name)) {
            return setting;
         }
      }

      return null;
   }

   public static class Category {
      private final String name;
      private static final Map<String, Module.Category> CATEGORIES = new LinkedHashMap<>();
      public static final Module.Category COMBAT = new Module.Category("Combat");
      public static final Module.Category MOVEMENT = new Module.Category("Movement");
      public static final Module.Category RENDER = new Module.Category("Render");
      public static final Module.Category PLAYER = new Module.Category("Player");
      public static final Module.Category MISC = new Module.Category("Misc");
      public static final Module.Category CLIENT = new Module.Category("Client");
      public static final Module.Category HUD = new Module.Category("HUD");

      private Category(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public static Module.Category getCategory(String name) {
         return CATEGORIES.computeIfAbsent(name, Module.Category::new);
      }

      public static Collection<Module.Category> values() {
         return CATEGORIES.values();
      }

      public static boolean isCustomCategory(Module.Category category) {
         Set<String> predefinedCategoryNames = Set.of("Combat", "Movement", "Render", "Player", "Misc", "Client", "HUD");
         return !predefinedCategoryNames.contains(category.getName());
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Module.Category category = (Module.Category)o;
            return Objects.equals(this.name, category.name);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.name);
      }

      static {
         CATEGORIES.put("Combat", COMBAT);
         CATEGORIES.put("Movement", MOVEMENT);
         CATEGORIES.put("Render", RENDER);
         CATEGORIES.put("Player", PLAYER);
         CATEGORIES.put("Misc", MISC);
         CATEGORIES.put("Client", CLIENT);
         CATEGORIES.put("HUD", HUD);
      }
   }
}
