package legobrosbuild.coordsender.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.Vector3d;


@Environment(EnvType.CLIENT)
public class CoordSenderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandManager.DISPATCHER.register(
            ClientCommandManager.literal("coords")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getClient().player;
                    assert player != null;
                    Vector3d coords = new Vector3d(player.getBlockX(), player.getBlockY(), player.getBlockZ());
                    String pos = coords.x + ", " + coords.y + ", " + coords.z;
//                    final Text text = new LiteralText(player.getEntityName() + ": " +pos).formatted(Formatting.AQUA);
//                      for colors ^. Only with servers
                    player.sendChatMessage(pos);
                    return 1;
                })
        );
    }
}
