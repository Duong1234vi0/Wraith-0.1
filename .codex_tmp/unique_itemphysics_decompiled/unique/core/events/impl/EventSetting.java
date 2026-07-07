package unique.core.events.impl;

import unique.core.events.Event;
import unique.core.setting.Setting;

public class EventSetting extends Event {
   final Setting<?> setting;

   public EventSetting(Setting<?> setting) {
      this.setting = setting;
   }

   public Setting<?> getSetting() {
      return this.setting;
   }
}
