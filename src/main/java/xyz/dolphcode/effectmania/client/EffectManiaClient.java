package xyz.dolphcode.effectmania.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import xyz.dolphcode.effectmania.screen.RainbowOverlayRenderer;

@Environment(EnvType.CLIENT)
public class EffectManiaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(RainbowOverlayRenderer::renderRainbowOverlay);

    }
}
