package sky.core.utils.math;

import java.util.Locale;
import sky.core.utils.Wrapper;

public class NumberUtil implements Wrapper {
   public static String toRomanNumeral(int number) {
      if (number >= 1 && number <= 3999) {
         String[] thousands = new String[]{"", "M", "MM", "MMM"};
         String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
         String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
         String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
         return thousands[number / 1000] + hundreds[number % 1000 / 100] + tens[number % 100 / 10] + ones[number % 10];
      } else {
         return String.valueOf(number);
      }
   }

   public static String formatThousands(String input) {
      if (input != null && !input.isEmpty()) {
         String digitsOnly = input.replaceAll("[^0-9]", "");
         digitsOnly = digitsOnly.replaceFirst("^0+(?!$)", "");
         if (digitsOnly.isEmpty()) {
            return "";
         } else {
            try {
               long value = Long.parseLong(digitsOnly);
               return String.format(Locale.US, "%,d", value);
            } catch (NumberFormatException var4) {
               return "";
            }
         }
      } else {
         return "";
      }
   }

   public static int countDigits(String input) {
      if (input != null && !input.isEmpty()) {
         int cnt = 0;

         for(int i = 0; i < input.length(); ++i) {
            if (Character.isDigit(input.charAt(i))) {
               ++cnt;
            }
         }

         return cnt;
      } else {
         return 0;
      }
   }
}
