/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_268
 *  net.minecraft.class_269
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_355
 *  net.minecraft.class_5250
 *  net.minecraft.class_640
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.emotions.ClientBadgeRenderer;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_5250;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_355.class})
public abstract class PlayerListHudMixin {
    private static final Pattern NAME_PATTERN = Pattern.compile("^\\w{3,16}$");

    @Shadow
    protected abstract List<class_640> method_48213();

    @Shadow
    public abstract class_2561 method_1918(class_640 var1);

    @Inject(method={"collectPlayerEntries"}, at={@At(value="RETURN")}, cancellable=true)
    private void addVanishedEntries(CallbackInfoReturnable<List<class_640>> cir) {
        class_310 client = class_310.method_1551();
        List originalList = (List)cir.getReturnValue();
        ArrayList<class_640> vanishedList = new ArrayList<class_640>();
        class_269 scoreboard = client.field_1687.method_8428();
        ArrayList<class_268> teams = new ArrayList<class_268>(scoreboard.method_1159());
        teams.sort(Comparator.comparing(class_268::method_1197));
        Collection online = client.field_1724.field_3944.method_2880();
        for (class_268 team : teams) {
            boolean present;
            String name;
            Collection members = team.method_1204();
            if (members.size() != 1 || !NAME_PATTERN.matcher(name = (String)members.iterator().next()).matches() || (present = online.stream().anyMatch(e -> e.method_2966() != null && name.equals(e.method_2966().getName())))) continue;
            class_5250 displayName = class_2561.method_43473().method_10852((class_2561)class_2561.method_43470((String)"[").method_27692(class_124.field_1080)).method_10852((class_2561)class_2561.method_43470((String)"V").method_27692(class_124.field_1061)).method_10852((class_2561)class_2561.method_43470((String)"] ").method_27692(class_124.field_1080)).method_10852(team.method_1144()).method_10852((class_2561)class_2561.method_43470((String)name).method_27692(class_124.field_1080));
            GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), name);
            class_640 fake = new class_640(fakeProfile, client.method_1542());
            fake.method_2962((class_2561)displayName);
            fake.method_62153(Integer.MIN_VALUE);
            vanishedList.add(fake);
        }
        ArrayList<class_640> finalList = new ArrayList<class_640>();
        finalList.addAll(vanishedList);
        finalList.addAll(originalList);
        cir.setReturnValue(finalList);
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"))
    private int AlphaDLC$renderPlayerNameWithClientBadge(class_332 context, class_327 textRenderer, class_2561 text, int x, int y, int color, @Local class_640 entry) {
        GameProfile profile = entry.method_2966();
        if (profile == null || !ClientBadgeRenderer.hasCompatibleClient(profile.getId())) {
            return context.method_27535(textRenderer, text, x, y, color);
        }
        int iconSize = 7;
        int gap = 2;
        ClientBadgeRenderer.drawRoundedBadge(context, x, (float)y + 1.0f, iconSize);
        return context.method_27535(textRenderer, text, x + iconSize + gap, y, color);
    }
}
