package sky.core.utils.misc;

import net.minecraft.client.util.InputMappings;
import sky.core.utils.Wrapper;

public enum KeyMapper {
   A("A", 65),
   B("B", 66),
   C("C", 67),
   D("D", 68),
   E("E", 69),
   F("F", 70),
   G("G", 71),
   H("H", 72),
   I("I", 73),
   J("J", 74),
   K("K", 75),
   L("L", 76),
   M("M", 77),
   N("N", 78),
   O("O", 79),
   P("P", 80),
   Q("Q", 81),
   R("R", 82),
   S("S", 83),
   T("T", 84),
   U("U", 85),
   V("V", 86),
   W("W", 87),
   X("X", 88),
   Y("Y", 89),
   Z("Z", 90),
   ZERO("0", 48),
   ONE("1", 49),
   TWO("2", 50),
   THREE("3", 51),
   FOUR("4", 52),
   FIVE("5", 53),
   SIX("6", 54),
   SEVEN("7", 55),
   EIGHT("8", 56),
   NINE("9", 57),
   F1("F1", 290),
   F2("F2", 291),
   F3("F3", 292),
   F4("F4", 293),
   F5("F5", 294),
   F6("F6", 295),
   F7("F7", 296),
   F8("F8", 297),
   F9("F9", 298),
   F10("F10", 299),
   F11("F11", 300),
   F12("F12", 301),
   NUM1("NUM1", 321),
   NUM2("NUM2", 322),
   NUM3("NUM3", 323),
   NUM4("NUM4", 324),
   NUM5("NUM5", 325),
   NUM6("NUM6", 326),
   NUM7("NUM7", 327),
   NUM8("NUM8", 328),
   NUM9("NUM9", 329),
   SPACE("SPCE", 32),
   ENTER("ENTR", 257),
   ESC("ESC", 256),
   LSHIFT("LSHF", 340),
   RSHIFT("RSHF", 344),
   LCTRL("LCTR", 341),
   RCTRL("RCTR", 345),
   LALT("LALT", 342),
   RALT("RALT", 346),
   LSUPER("LSUP", 343),
   RSUPER("RSUP", 347),
   UP("UP", 265),
   DOWN("DOWN", 264),
   LEFT("LEFT", 263),
   RIGHT("RIGHT", 262),
   BACK("BACK", 259),
   HOME("HOME", 268),
   INS("INS", 260),
   DEL("DEL", 261),
   END("END", 269),
   PUP("PUP", 266),
   TAB("TAB", 258),
   PDOWN("PDWN", 267),
   MENU("MENU", 348),
   CAPS("CAPS", 280),
   NUM("NUM", 282),
   SCROL("SCRL", 281),
   KP_DECIMAL("DCML", 330),
   KP_DIVIDE("DVDE", 331),
   KP_MULTIPLY("MULT", 332),
   KP_SUBTRACT("SUBT", 333),
   KP_PLUS("PLUS", 334),
   KP_ENTER("ENTR", 335),
   KP_EQUAL("EQUL", 336),
   APOSTROPHE("'", 39),
   SLASH("/", 47),
   MINUS("-", 45),
   PLUS("+", 61),
   BACKSLASH("SLSH", 92),
   PERIOD(".", 46),
   COMMA("COMA", 44),
   PAUSE("PAUS", 284),
   GRAVE("`", 96),
   MOUSE_MIDDLE("MMC", 2),
   MOUSE_BUTTON_4("M3", 3),
   MOUSE_BUTTON_5("M4", 4);

   private final String name;
   private final int keyCode;

   private KeyMapper(String name, int keyCode) {
      this.name = name;
      this.keyCode = keyCode;
   }

   public static String getKey(int keyCode) {
      KeyMapper[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         KeyMapper key = var1[var3];
         if (key.keyCode == keyCode) {
            return key.name;
         }
      }

      return "unk";
   }

   public static boolean isKeyDown(int keyCode) {
      return keyCode >= 0 && InputMappings.isKeyDown(Wrapper.mc.getMainWindow().getHandle(), keyCode);
   }

   public static KeyMapper fromName(String name) {
      try {
         return valueOf(name.toUpperCase());
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   public String getName() {
      return this.name;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   // $FF: synthetic method
   private static KeyMapper[] $values() {
      return new KeyMapper[]{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, NUM9, SPACE, ENTER, ESC, LSHIFT, RSHIFT, LCTRL, RCTRL, LALT, RALT, LSUPER, RSUPER, UP, DOWN, LEFT, RIGHT, BACK, HOME, INS, DEL, END, PUP, TAB, PDOWN, MENU, CAPS, NUM, SCROL, KP_DECIMAL, KP_DIVIDE, KP_MULTIPLY, KP_SUBTRACT, KP_PLUS, KP_ENTER, KP_EQUAL, APOSTROPHE, SLASH, MINUS, PLUS, BACKSLASH, PERIOD, COMMA, PAUSE, GRAVE, MOUSE_MIDDLE, MOUSE_BUTTON_4, MOUSE_BUTTON_5};
   }
}
