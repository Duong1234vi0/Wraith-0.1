/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_476
 *  net.minecraft.class_9290
 *  net.minecraft.class_9323
 *  net.minecraft.class_9334
 *  org.apache.commons.lang3.StringUtils
 */
package code.alphadlc.utils.features.price;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_476;
import net.minecraft.class_9290;
import net.minecraft.class_9323;
import net.minecraft.class_9334;
import org.apache.commons.lang3.StringUtils;

public class PriceParser {
    private final Pattern funTimePricePattern = Pattern.compile("\\$\\s*(\\d+(?:[,\\s]\\d{3})*(?:\\.\\d{2})?)");

    public int getPrice(class_1799 stack) {
        class_9290 lore;
        class_9323 tag = stack.method_57353();
        if (tag == null) {
            return -1;
        }
        String componentString = tag.toString();
        String price = StringUtils.substringBetween((String)componentString, (String)"literal{ $", (String)"}[style={color=green}]");
        if ((price == null || price.isEmpty()) && (lore = (class_9290)stack.method_57824(class_9334.field_49632)) != null && !lore.comp_2400().isEmpty()) {
            String lastFoundPrice = null;
            for (class_2561 line : lore.comp_2400()) {
                String text = line.getString();
                Matcher matcher = this.funTimePricePattern.matcher(text);
                while (matcher.find()) {
                    lastFoundPrice = matcher.group(1);
                }
            }
            if (lastFoundPrice != null && !lastFoundPrice.isEmpty()) {
                price = lastFoundPrice;
            }
        }
        if (price == null || price.isEmpty()) {
            return -1;
        }
        try {
            price = price.replaceAll("[\\s,]", "");
            return Integer.parseInt(price);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getSpookyTimePrice(class_1799 stack, class_476 screen) {
        if (screen == null) {
            return -1;
        }
        String title = screen.method_25440().getString();
        if (!title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") && !title.contains("\u041f\u043e\u0438\u0441\u043a:")) {
            return -1;
        }
        class_9290 lore = (class_9290)stack.method_57824(class_9334.field_49632);
        if (lore != null && !lore.comp_2400().isEmpty()) {
            for (class_2561 line : lore.comp_2400()) {
                String text = line.getString();
                if (!text.contains("\u0426\u0435\u043d\u0430:") && !text.contains("Price:")) continue;
                try {
                    int priceIndex = Math.max(text.indexOf("\u0426\u0435\u043d\u0430:"), text.indexOf("Price:"));
                    if (priceIndex != -1) {
                        String priceText = text.substring(priceIndex);
                        String priceStr = priceText.replaceAll("[^0-9.]", "").trim();
                        try {
                            return (int)Double.parseDouble(priceStr);
                        }
                        catch (NumberFormatException e) {
                            try {
                                return Integer.parseInt(priceStr);
                            }
                            catch (NumberFormatException e2) {
                                return -1;
                            }
                        }
                    }
                    return -1;
                }
                catch (Exception e) {
                    return -1;
                }
            }
        }
        return -1;
    }
}
