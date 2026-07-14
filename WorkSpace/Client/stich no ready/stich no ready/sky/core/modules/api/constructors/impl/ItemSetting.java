package sky.core.modules.api.constructors.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.ItemStack;
import sky.core.modules.api.constructors.Setting;

public class ItemSetting extends Setting {
   private final ItemStack itemStack;
   private String nbt = "";
   private long maxPrice = 0L;
   private boolean sellEnabled = false;
   private int sellPercent = 0;
   private final List requiredNbtParameters = new ArrayList();
   private Map requiredEnchantments = new HashMap();
   private boolean requireUnbreakable = false;
   private String searchQuery = null;
   private Mode mode;

   public ItemSetting(ItemStack itemStack, String name, Boolean defaultVal) {
      super(name, defaultVal);
      this.mode = ItemSetting.Mode.BOTH;
      this.itemStack = itemStack;
   }

   public ItemSetting mode(Mode mode) {
      this.mode = mode;
      return this;
   }

   public boolean matchesMode(String currentMode) {
      if (this.mode == ItemSetting.Mode.BOTH) {
         return true;
      } else if ("Холиворлд".equalsIgnoreCase(currentMode) && this.mode == ItemSetting.Mode.HOLIWORLD) {
         return true;
      } else {
         return "Спукитайм".equalsIgnoreCase(currentMode) && this.mode == ItemSetting.Mode.SPOOKYTIME;
      }
   }

   public ItemSetting addNBTparametr(String... params) {
      if (params == null) {
         return this;
      } else {
         String[] var2 = params;
         int var3 = params.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String p = var2[var4];
            if (p != null) {
               String trimmed = p.trim();
               if (!trimmed.isEmpty()) {
                  this.requiredNbtParameters.add(trimmed);
               }
            }
         }

         return this;
      }
   }

   public ItemSetting addEnchantment(String enchantName, int level) {
      if (this.requiredEnchantments == null) {
         this.requiredEnchantments = new HashMap();
      }

      this.requiredEnchantments.put(enchantName.toLowerCase(), level);
      return this;
   }

   public ItemSetting requireUnbreakable() {
      this.requireUnbreakable = true;
      return this;
   }

   public ItemSetting setSearchQuery(String query) {
      this.searchQuery = query == null ? null : query.trim();
      return this;
   }

   public ItemSetting sellPercent(int percent) {
      this.sellPercent = percent;
      return this;
   }

   public ItemSetting sellEnabled(boolean enabled) {
      this.sellEnabled = enabled;
      return this;
   }

   public void setMaxPriceFromString(String priceText) {
      if (priceText == null) {
         this.maxPrice = 0L;
      } else {
         String digits = priceText.replaceAll("[^0-9]", "");
         if (digits.isEmpty()) {
            this.maxPrice = 0L;
         } else {
            try {
               this.maxPrice = Long.parseLong(digits);
            } catch (NumberFormatException var4) {
               this.maxPrice = 0L;
            }

         }
      }
   }

   public void setSellPercentFromString(String percentText) {
      if (percentText == null) {
         this.sellPercent = 0;
      } else {
         String digits = percentText.replaceAll("[^0-9]", "");
         if (digits.isEmpty()) {
            this.sellPercent = 0;
         } else {
            try {
               this.sellPercent = Integer.parseInt(digits);
            } catch (NumberFormatException var4) {
               this.sellPercent = 0;
            }

         }
      }
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public String getNbt() {
      return this.nbt;
   }

   public long getMaxPrice() {
      return this.maxPrice;
   }

   public boolean isSellEnabled() {
      return this.sellEnabled;
   }

   public int getSellPercent() {
      return this.sellPercent;
   }

   public List getRequiredNbtParameters() {
      return this.requiredNbtParameters;
   }

   public Map getRequiredEnchantments() {
      return this.requiredEnchantments;
   }

   public boolean isRequireUnbreakable() {
      return this.requireUnbreakable;
   }

   public String getSearchQuery() {
      return this.searchQuery;
   }

   public Mode getMode() {
      return this.mode;
   }

   public void setNbt(String nbt) {
      this.nbt = nbt;
   }

   public void setMaxPrice(long maxPrice) {
      this.maxPrice = maxPrice;
   }

   public void setSellEnabled(boolean sellEnabled) {
      this.sellEnabled = sellEnabled;
   }

   public void setSellPercent(int sellPercent) {
      this.sellPercent = sellPercent;
   }

   public void setRequiredEnchantments(Map requiredEnchantments) {
      this.requiredEnchantments = requiredEnchantments;
   }

   public void setRequireUnbreakable(boolean requireUnbreakable) {
      this.requireUnbreakable = requireUnbreakable;
   }

   public void setMode(Mode mode) {
      this.mode = mode;
   }

   public static enum Mode {
      HOLIWORLD,
      SPOOKYTIME,
      BOTH;

      // $FF: synthetic method
      private static Mode[] $values() {
         return new Mode[]{HOLIWORLD, SPOOKYTIME, BOTH};
      }
   }
}
