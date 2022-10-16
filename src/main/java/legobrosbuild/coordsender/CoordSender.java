package legobrosbuild.coordsender;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;

public class CoordSender implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            dispatcher.register(literal("coords")
                .executes(context -> {
                    Entity player = context.getSource().getEntity();
                    assert player != null;
                    Vector3d coords = new Vector3d(player.getBlockX(), player.getBlockY(), player.getBlockZ());
                    String pos = coords.x + ", " + coords.y + ", " + coords.z;
                    final Text text = new LiteralText(player.getEntityName() + ": " + pos).formatted(Formatting.AQUA);
                    context.getSource().sendFeedback(text, true);
                    return 1;
                })
            );
        }));
    }
}
