package sky.core.modules;

import com.darkmagician6.eventapi.EventManager;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.impl.miscellaneous.ToggleSounds;
import sky.core.modules.impl.visuals.Interface;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;
import sky.core.utils.misc.SoundUtil;

public abstract class Module implements Wrapper {
   String name;
   String description;
   Category category;
   int bind;
   boolean enabled;
   private ToggleMode toggleMode;
   private boolean keybindvisible;
   List settings;
   private final AnimationUtil animation;
   private static boolean suppressToggleEffects = false;

   public Module(String name, Category category) {
      this(name, "NULL", category);
   }

   public Module(String name, String description, Category category) {
      this.toggleMode = Module.ToggleMode.TOGGLE;
      this.keybindvisible = true;
      this.settings = new ObjectArrayList();
      this.animation = new AnimationUtil(0.0F, 8.0F, Easings.LINEAR);
      this.name = name;
      this.description = description;
      this.category = category;
      this.bind = -100;
   }

   public void addSettings(Setting... settings) {
      this.settings.addAll(List.of(settings));
   }

   public void onEnable() {
      this.animation.update(1.0F);
      if (!suppressToggleEffects && this.keybindvisible) {
         SoundUtil.playSound(ToggleSounds.getSoundFile(true));
         if ((Boolean)NotificationRender.module.get()) {
            if (Interface.isOldHud()) {
               NotificationManager.addNotification((String)"J", (String)(this.name + " enabled!"), -1);
            } else {
               String categoryIcon = NotificationRender.getCategoryIcon(this);
               NotificationManager.addNotification((String)categoryIcon, (String)("Module " + this.name + " enabled!"), -1);
            }
         }
      }

      EventManager.register(this);
   }

   public void onDisable() {
      this.animation.update(0.0F);
      if (!suppressToggleEffects && this.keybindvisible) {
         SoundUtil.playSound(ToggleSounds.getSoundFile(false));
         if ((Boolean)NotificationRender.module.get()) {
            if (Interface.isOldHud()) {
               NotificationManager.addNotification((String)"K", (String)(this.name + " disabled!"), -1);
            } else {
               String categoryIcon = NotificationRender.getCategoryIcon(this);
               NotificationManager.addNotification((String)categoryIcon, (String)("Module " + this.name + " disabled!"), -1);
            }
         }
      }

      EventManager.unregister(this);
   }

   public final void toggle() {
      this.settoggled(!this.enabled);
   }

   public final void settoggled(boolean toggle) {
      if (this.enabled != toggle) {
         this.enabled = toggle;
         if (this.enabled) {
            this.onEnable();
         } else {
            this.onDisable();
         }

      }
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public Category getCategory() {
      return this.category;
   }

   public int getBind() {
      return this.bind;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public ToggleMode getToggleMode() {
      return this.toggleMode;
   }

   public boolean isKeybindvisible() {
      return this.keybindvisible;
   }

   public List getSettings() {
      return this.settings;
   }

   public AnimationUtil getAnimation() {
      return this.animation;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public void setBind(int bind) {
      this.bind = bind;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public void setToggleMode(ToggleMode toggleMode) {
      this.toggleMode = toggleMode;
   }

   public void setKeybindvisible(boolean keybindvisible) {
      this.keybindvisible = keybindvisible;
   }

   public void setSettings(List settings) {
      this.settings = settings;
   }

   public static boolean isSuppressToggleEffects() {
      return suppressToggleEffects;
   }

   public static void setSuppressToggleEffects(boolean suppressToggleEffects) {
      Module.suppressToggleEffects = suppressToggleEffects;
   }

   public static enum ToggleMode {
      TOGGLE,
      HOLD;

      // $FF: synthetic method
      private static ToggleMode[] $values() {
         return new ToggleMode[]{TOGGLE, HOLD};
      }
   }
}
