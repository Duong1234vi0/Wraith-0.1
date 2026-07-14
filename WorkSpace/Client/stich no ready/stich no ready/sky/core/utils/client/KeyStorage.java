package sky.core.utils.client;

import java.util.HashMap;
import java.util.Map;

public class KeyStorage {
   private static final Map KEY_NAMES = new HashMap() {
      {
         this.put(32, "Space");
         this.put(39, "'");
         this.put(44, ",");
         this.put(45, "-");
         this.put(46, ".");
         this.put(47, "/");
         this.put(48, "0");
         this.put(49, "1");
         this.put(50, "2");
         this.put(51, "3");
         this.put(52, "4");
         this.put(53, "5");
         this.put(54, "6");
         this.put(55, "7");
         this.put(56, "8");
         this.put(57, "9");
         this.put(59, ";");
         this.put(61, "=");
         this.put(65, "A");
         this.put(66, "B");
         this.put(67, "C");
         this.put(68, "D");
         this.put(69, "E");
         this.put(70, "F");
         this.put(71, "G");
         this.put(72, "H");
         this.put(73, "I");
         this.put(74, "J");
         this.put(75, "K");
         this.put(76, "L");
         this.put(77, "M");
         this.put(78, "N");
         this.put(79, "O");
         this.put(80, "P");
         this.put(81, "Q");
         this.put(82, "R");
         this.put(83, "S");
         this.put(84, "T");
         this.put(85, "U");
         this.put(86, "V");
         this.put(87, "W");
         this.put(88, "X");
         this.put(89, "Y");
         this.put(90, "Z");
         this.put(91, "[");
         this.put(92, "\\");
         this.put(93, "]");
         this.put(96, "`");
         this.put(256, "Esc");
         this.put(257, "Enter");
         this.put(258, "Tab");
         this.put(259, "Back");
         this.put(260, "Ins");
         this.put(261, "Del");
         this.put(262, "Right");
         this.put(263, "Left");
         this.put(264, "Down");
         this.put(265, "Up");
         this.put(266, "PgUp");
         this.put(267, "PgDn");
         this.put(268, "Home");
         this.put(269, "End");
         this.put(280, "Caps");
         this.put(281, "Scroll");
         this.put(282, "Num");
         this.put(283, "Print");
         this.put(284, "Pause");
         this.put(290, "F1");
         this.put(291, "F2");
         this.put(292, "F3");
         this.put(293, "F4");
         this.put(294, "F5");
         this.put(295, "F6");
         this.put(296, "F7");
         this.put(297, "F8");
         this.put(298, "F9");
         this.put(299, "F10");
         this.put(300, "F11");
         this.put(301, "F12");
         this.put(320, "Num0");
         this.put(321, "Num1");
         this.put(322, "Num2");
         this.put(323, "Num3");
         this.put(324, "Num4");
         this.put(325, "Num5");
         this.put(326, "Num6");
         this.put(327, "Num7");
         this.put(328, "Num8");
         this.put(329, "Num9");
         this.put(330, "Num.");
         this.put(331, "Num/");
         this.put(332, "Num*");
         this.put(333, "Num-");
         this.put(334, "Num+");
         this.put(335, "NumEnter");
         this.put(340, "LShift");
         this.put(341, "LCtrl");
         this.put(342, "LAlt");
         this.put(343, "LWin");
         this.put(344, "RShift");
         this.put(345, "RCtrl");
         this.put(346, "RAlt");
         this.put(347, "RWin");
         this.put(348, "Menu");
      }
   };

   public static String getKey(int keyCode) {
      if (keyCode != -1 && keyCode != 0 && keyCode != -100) {
         if (keyCode >= 0 && keyCode <= 8) {
            String var10000;
            switch (keyCode) {
               case 0:
                  var10000 = "LMB";
                  break;
               case 1:
                  var10000 = "RMB";
                  break;
               case 2:
                  var10000 = "MMB";
                  break;
               default:
                  var10000 = "MB" + keyCode;
            }

            return var10000;
         } else {
            String keyName = (String)KEY_NAMES.get(keyCode);
            return keyName != null ? keyName : "Key" + keyCode;
         }
      } else {
         return "None";
      }
   }
}
