package xyz.dolphcode.effectmania.screen;

import com.google.common.primitives.UnsignedInteger;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.system.CallbackI;
import xyz.dolphcode.effectmania.EffectMania;
import xyz.dolphcode.effectmania.effect.ModEffects;

import java.awt.*;
import java.util.logging.Logger;

public class RainbowOverlayRenderer {

    public static int R = 255;
    public static int G = 0;
    public static int B = 0;
    public static int COUNTER = 255;
    public static int COLOR_CHANGE = 0;
    public static int FRAME = 0;

    public static void renderRainbowOverlay(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player.hasStatusEffect(ModEffects.RAINBOW)) {
            matrixStack.push();
            matrixStack.translate(0, 0, 0);

            // Change color values every tick
            switch(COLOR_CHANGE) {
                case 0:
                    G += 1;
                    break;
                case 1:
                    R -= 1;
                    break;
                case 2:
                    B += 1;
                    break;
                case 3:
                    G -= 1;
                    break;
                case 4:
                    R += 1;
                    break;
                case 5:
                    B -= 1;
                    break;
            }
            COUNTER--;
            if (COUNTER <= 0) {
                COUNTER = 255;
                COLOR_CHANGE ++;
                if (COLOR_CHANGE > 5) {
                    COLOR_CHANGE = 0;
                }
            }

            // Change frame every tick
            FRAME++;

            int oWidth = client.getWindow().getScaledWidth();
            int sWidth = (int) (oWidth * 1.2);
            int sHeight = client.getWindow().getScaledHeight();

            Identifier id = new Identifier(EffectMania.ID, "textures/gui/spiral.png");
            client.getTextureManager().bindTexture(id);

            // Note to self, always check RenderPhase
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
            DrawableHelper.drawTexture(matrixStack, (oWidth/2) - (sWidth/2), (sHeight/2) - (sWidth/2), sWidth, sWidth, 0, sWidth * (FRAME % 12), sWidth, sWidth, sWidth, sWidth * 12);
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();

            System.out.println("FRAME " + FRAME);
            System.out.println("F " + (FRAME % 12));


            String colorHex = "64" + String.format("%02X", R) + String.format("%02X", G) + String.format("%02X", B);
            int color = (int) Long.parseLong(colorHex, 16);
            DrawableHelper.fill(matrixStack, 0, 0, sWidth, sHeight, color);

            matrixStack.pop();
        }
    }

}
