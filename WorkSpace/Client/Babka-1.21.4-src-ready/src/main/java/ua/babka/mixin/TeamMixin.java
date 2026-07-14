package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ua.babka.Babka;
import ua.babka.module.impl.player.StreamerMode;
import ua.babka.render.ReplaceUtil;

@Mixin(value={Team.class})
public class TeamMixin {
    @ModifyArg(method={"decorateName(Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;"}, at=@At(value="INVOKE", target="Lnet/minecraft/text/MutableText;append(Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;", ordinal=0), index=0)
    private Text modify(Text original) {
        String replaced;
        if (MinecraftClient.getInstance().player == null) {
            return original;
        }
        String me = MinecraftClient.getInstance().player.getNameForScoreboard();
        if (me.equals(replaced = Babka.getInstance().getModuleStorage().get(StreamerMode.class).getCustomName(me))) {
            return original;
        }
        return ReplaceUtil.replaceLiteral(original, me, replaced);
    }
}

