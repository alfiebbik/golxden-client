package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class ExampleMod implements ModInitializer {
    public static boolean enabled = false;
    private static KeyBinding guiKey;
    private static final List<BlockPos> foundBlocks = new ArrayList<>();

    @Override
    public void onInitialize() {
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open Sus Menu", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "Sus Finder"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            while (guiKey.wasPressed()) {
                client.setScreen(new SusGui());
            }

            if (enabled) {
                foundBlocks.clear();
                BlockPos targetPos = client.player.getBlockPos().down(6);
                if (client.world.getBlockState(targetPos).isOf(Blocks.COBBLED_DEEPSLATE)) {
                    foundBlocks.add(targetPos);
                    client.player.sendMessage(Text.literal("§b[!] SUS CHUNK DETECTED"), true);
                }
            } else {
                foundBlocks.clear();
            }
        });

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context -> {
            if (!enabled || foundBlocks.isEmpty()) return;
            for (BlockPos pos : foundBlocks) {
                DebugRenderer.drawBox(context.matrixStack(), new Box(pos), 0, 0, 1, 0.5f);
            }
        });
    }
}