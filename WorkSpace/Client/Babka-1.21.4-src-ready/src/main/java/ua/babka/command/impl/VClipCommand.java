package ua.babka.command.impl;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import ua.babka.command.CommandException;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;

public class VClipCommand
extends Command {
    public VClipCommand() {
        super("vclip");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        double yOffset;
        args.requireMin(1);
        String input = args.getString();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        ClientWorld world = MinecraftClient.getInstance().world;
        switch (input.toLowerCase()) {
            case "up": {
                yOffset = this.findOffset(player.getBlockPos(), true, world);
                break;
            }
            case "down": {
                yOffset = this.findOffset(player.getBlockPos(), false, world);
                break;
            }
            default: {
                try {
                    yOffset = Double.parseDouble(input);
                    break;
                }
                catch (NumberFormatException e2) {
                    this.logDirect(String.valueOf(Formatting.RED) + input + " не является числом.");
                    return;
                }
            }
        }
        if (yOffset == 0.0) {
            this.logDirect(String.valueOf(Formatting.RED) + "Не удалось выполнить телепортацию.");
            return;
        }
        double x2 = player.getX();
        double y2 = player.getY();
        double z2 = player.getZ();
        for (int i2 = 0; i2 < 3; ++i2) {
            player.networkHandler.sendPacket((Packet)new PlayerMoveC2SPacket.OnGroundOnly(player.isOnGround(), player.horizontalCollision));
        }
        player.networkHandler.sendPacket((Packet)new PlayerMoveC2SPacket.PositionAndOnGround(x2, y2 + yOffset, z2, false, player.horizontalCollision));
        player.setPosition(x2, y2 + yOffset, z2);
        this.logDirect("Телепортировано на " + (int)yOffset + " блоков по вертикали");
    }

    private double findOffset(BlockPos pos, boolean toUp, ClientWorld world) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (toUp) {
            for (int i2 = 3; i2 < 255; ++i2) {
                BlockPos base = pos.add(0, i2, 0);
                BlockPos head = base.up();
                if (!world.getBlockState(base).isAir() || !world.getBlockState(head).isAir()) continue;
                return (double)base.getY() - player.getY();
            }
        } else {
            for (int i3 = -1; i3 > -255; --i3) {
                BlockPos solid = pos.add(0, i3, 0);
                BlockPos air1 = solid.down();
                BlockPos air2 = air1.down();
                boolean isSolid = !world.getBlockState(solid).isAir();
                boolean isAirBelow1 = world.getBlockState(air1).isAir();
                boolean isAirBelow2 = world.getBlockState(air2).isAir();
                if (!isSolid || !isAirBelow1 || !isAirBelow2) continue;
                return (double)air2.getY() - player.getY();
            }
        }
        return 0.0;
    }

    @Override
    public String getShortDesc() {
        return "Телепорт по вертикали";
    }

    @Override
    public List<String> getLongDesc() {
        return List.of("Телепортирует игрока вверх или вниз", "", "> vclip <расстояние> — телепорт на определенное количество блоков", "> vclip up — вверх до свободного блока", "> vclip down — вниз до свободного блока");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        if (args.hasExactlyOne()) {
            return Stream.of("up", "down");
        }
        return Stream.empty();
    }
}

