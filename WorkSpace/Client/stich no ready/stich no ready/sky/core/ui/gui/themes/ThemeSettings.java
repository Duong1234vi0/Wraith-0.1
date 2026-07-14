package sky.core.ui.gui.themes;

public enum ThemeSettings {
   MAIN("Основной"),
   MODULE_VISUAL("Визуальные модули"),
   TEXT("Текст"),
   TEXT_INACTIVE("Неактивный текст"),
   HEADER("Текст заголовков"),
   PREMIUM("Премиум текст"),
   SLIDER("Слайдер"),
   SLIDER_CIRCLE("Круг слайдера"),
   SLIDER_WINDOW("Окно слайдера"),
   INDICATOR("Индикатор"),
   INDICATOR_INACTIVE("Неактивный индикатор"),
   BUTTON("Кнопка"),
   BUTTON_INACTIVE("Неактивная кнопка"),
   OUTLINE("Обводка"),
   SEPARATOR("Разделитель"),
   SCROLL_BAR("Скролл бар"),
   FIELD("Поле"),
   FIELD_INACTIVE("Неактивное поле"),
   LOGO("Лого"),
   LOGO_TEXT("Текст в лого"),
   TOOLTIP_BG("Фон в подсказках"),
   TOOLTIP("Текст в подсказках"),
   WINDOW_BG("Фон окон"),
   WINDOW_TEXT("Текст окон");

   private final String displayName;

   private ThemeSettings(String displayName) {
      this.displayName = displayName;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   // $FF: synthetic method
   private static ThemeSettings[] $values() {
      return new ThemeSettings[]{MAIN, MODULE_VISUAL, TEXT, TEXT_INACTIVE, HEADER, PREMIUM, SLIDER, SLIDER_CIRCLE, SLIDER_WINDOW, INDICATOR, INDICATOR_INACTIVE, BUTTON, BUTTON_INACTIVE, OUTLINE, SEPARATOR, SCROLL_BAR, FIELD, FIELD_INACTIVE, LOGO, LOGO_TEXT, TOOLTIP_BG, TOOLTIP, WINDOW_BG, WINDOW_TEXT};
   }
}
