/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.stream.Collectors;
import lightning.product.References;

public class z_2529_n
extends DataFix {
    private static final Int2ObjectMap<String> n_1700_B = (Int2ObjectMap)DataFixUtils.make((Object)new Int2ObjectOpenHashMap(), p_206279_0_ -> {
        p_206279_0_.put(0, (Object)"key.unknown");
        p_206279_0_.put(11, (Object)"key.0");
        p_206279_0_.put(2, (Object)"key.1");
        p_206279_0_.put(3, (Object)"key.2");
        p_206279_0_.put(4, (Object)"key.3");
        p_206279_0_.put(5, (Object)"key.4");
        p_206279_0_.put(6, (Object)"key.5");
        p_206279_0_.put(7, (Object)"key.6");
        p_206279_0_.put(8, (Object)"key.7");
        p_206279_0_.put(9, (Object)"key.8");
        p_206279_0_.put(10, (Object)"key.9");
        p_206279_0_.put(30, (Object)"key.a");
        p_206279_0_.put(40, (Object)"key.apostrophe");
        p_206279_0_.put(48, (Object)"key.b");
        p_206279_0_.put(43, (Object)"key.backslash");
        p_206279_0_.put(14, (Object)"key.backspace");
        p_206279_0_.put(46, (Object)"key.c");
        p_206279_0_.put(58, (Object)"key.caps.lock");
        p_206279_0_.put(51, (Object)"key.comma");
        p_206279_0_.put(32, (Object)"key.d");
        p_206279_0_.put(211, (Object)"key.delete");
        p_206279_0_.put(208, (Object)"key.down");
        p_206279_0_.put(18, (Object)"key.e");
        p_206279_0_.put(207, (Object)"key.end");
        p_206279_0_.put(28, (Object)"key.enter");
        p_206279_0_.put(13, (Object)"key.equal");
        p_206279_0_.put(1, (Object)"key.escape");
        p_206279_0_.put(33, (Object)"key.f");
        p_206279_0_.put(59, (Object)"key.f1");
        p_206279_0_.put(68, (Object)"key.f10");
        p_206279_0_.put(87, (Object)"key.f11");
        p_206279_0_.put(88, (Object)"key.f12");
        p_206279_0_.put(100, (Object)"key.f13");
        p_206279_0_.put(101, (Object)"key.f14");
        p_206279_0_.put(102, (Object)"key.f15");
        p_206279_0_.put(103, (Object)"key.f16");
        p_206279_0_.put(104, (Object)"key.f17");
        p_206279_0_.put(105, (Object)"key.f18");
        p_206279_0_.put(113, (Object)"key.f19");
        p_206279_0_.put(60, (Object)"key.f2");
        p_206279_0_.put(61, (Object)"key.f3");
        p_206279_0_.put(62, (Object)"key.f4");
        p_206279_0_.put(63, (Object)"key.f5");
        p_206279_0_.put(64, (Object)"key.f6");
        p_206279_0_.put(65, (Object)"key.f7");
        p_206279_0_.put(66, (Object)"key.f8");
        p_206279_0_.put(67, (Object)"key.f9");
        p_206279_0_.put(34, (Object)"key.g");
        p_206279_0_.put(41, (Object)"key.grave.accent");
        p_206279_0_.put(35, (Object)"key.h");
        p_206279_0_.put(199, (Object)"key.home");
        p_206279_0_.put(23, (Object)"key.i");
        p_206279_0_.put(210, (Object)"key.insert");
        p_206279_0_.put(36, (Object)"key.j");
        p_206279_0_.put(37, (Object)"key.k");
        p_206279_0_.put(82, (Object)"key.keypad.0");
        p_206279_0_.put(79, (Object)"key.keypad.1");
        p_206279_0_.put(80, (Object)"key.keypad.2");
        p_206279_0_.put(81, (Object)"key.keypad.3");
        p_206279_0_.put(75, (Object)"key.keypad.4");
        p_206279_0_.put(76, (Object)"key.keypad.5");
        p_206279_0_.put(77, (Object)"key.keypad.6");
        p_206279_0_.put(71, (Object)"key.keypad.7");
        p_206279_0_.put(72, (Object)"key.keypad.8");
        p_206279_0_.put(73, (Object)"key.keypad.9");
        p_206279_0_.put(78, (Object)"key.keypad.add");
        p_206279_0_.put(83, (Object)"key.keypad.decimal");
        p_206279_0_.put(181, (Object)"key.keypad.divide");
        p_206279_0_.put(156, (Object)"key.keypad.enter");
        p_206279_0_.put(141, (Object)"key.keypad.equal");
        p_206279_0_.put(55, (Object)"key.keypad.multiply");
        p_206279_0_.put(74, (Object)"key.keypad.subtract");
        p_206279_0_.put(38, (Object)"key.l");
        p_206279_0_.put(203, (Object)"key.left");
        p_206279_0_.put(56, (Object)"key.left.alt");
        p_206279_0_.put(26, (Object)"key.left.bracket");
        p_206279_0_.put(29, (Object)"key.left.control");
        p_206279_0_.put(42, (Object)"key.left.shift");
        p_206279_0_.put(219, (Object)"key.left.win");
        p_206279_0_.put(50, (Object)"key.m");
        p_206279_0_.put(12, (Object)"key.minus");
        p_206279_0_.put(49, (Object)"key.n");
        p_206279_0_.put(69, (Object)"key.num.lock");
        p_206279_0_.put(24, (Object)"key.o");
        p_206279_0_.put(25, (Object)"key.p");
        p_206279_0_.put(209, (Object)"key.page.down");
        p_206279_0_.put(201, (Object)"key.page.up");
        p_206279_0_.put(197, (Object)"key.pause");
        p_206279_0_.put(52, (Object)"key.period");
        p_206279_0_.put(183, (Object)"key.print.screen");
        p_206279_0_.put(16, (Object)"key.q");
        p_206279_0_.put(19, (Object)"key.r");
        p_206279_0_.put(205, (Object)"key.right");
        p_206279_0_.put(184, (Object)"key.right.alt");
        p_206279_0_.put(27, (Object)"key.right.bracket");
        p_206279_0_.put(157, (Object)"key.right.control");
        p_206279_0_.put(54, (Object)"key.right.shift");
        p_206279_0_.put(220, (Object)"key.right.win");
        p_206279_0_.put(31, (Object)"key.s");
        p_206279_0_.put(70, (Object)"key.scroll.lock");
        p_206279_0_.put(39, (Object)"key.semicolon");
        p_206279_0_.put(53, (Object)"key.slash");
        p_206279_0_.put(57, (Object)"key.space");
        p_206279_0_.put(20, (Object)"key.t");
        p_206279_0_.put(15, (Object)"key.tab");
        p_206279_0_.put(22, (Object)"key.u");
        p_206279_0_.put(200, (Object)"key.up");
        p_206279_0_.put(47, (Object)"key.v");
        p_206279_0_.put(17, (Object)"key.w");
        p_206279_0_.put(45, (Object)"key.x");
        p_206279_0_.put(21, (Object)"key.y");
        p_206279_0_.put(44, (Object)"key.z");
    });

    public z_2529_n(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsKeyLwjgl3Fix", this.getInputSchema().getType(References.P_1922_E), p_207423_0_ -> p_207423_0_.update(DSL.remainderFinder(), p_207424_0_ -> p_207424_0_.getMapValues().map(p_209663_1_ -> p_207424_0_.createMap(p_209663_1_.entrySet().stream().map(p_209661_0_ -> {
            if (((Dynamic)p_209661_0_.getKey()).asString("").startsWith("key_")) {
                int i = Integer.parseInt(((Dynamic)p_209661_0_.getValue()).asString(""));
                if (i < 0) {
                    int j = i + 100;
                    Object s1 = j == 0 ? "key.mouse.left" : (j == 1 ? "key.mouse.right" : (j == 2 ? "key.mouse.middle" : "key.mouse." + (j + 1)));
                    return Pair.of((Object)((Dynamic)p_209661_0_.getKey()), (Object)((Dynamic)p_209661_0_.getValue()).createString((String)s1));
                }
                String s = (String)n_1700_B.getOrDefault(i, (Object)"key.unknown");
                return Pair.of((Object)((Dynamic)p_209661_0_.getKey()), (Object)((Dynamic)p_209661_0_.getValue()).createString(s));
            }
            return Pair.of((Object)((Dynamic)p_209661_0_.getKey()), (Object)((Dynamic)p_209661_0_.getValue()));
        }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)))).result().orElse(p_207424_0_)));
    }
}


