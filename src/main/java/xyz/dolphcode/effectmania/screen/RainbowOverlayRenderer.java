package xyz.dolphcode.effectmania.screen;

import com.google.common.primitives.UnsignedInteger;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;
import java.util.logging.Logger;

public class RainbowOverlayRenderer {

    public static int R = 255;
    public static int G = 0;
    public static int B = 0;
    public static int COUNTER = 255;
    public static int COLOR_CHANGE = 0;

    public static void renderRainbowOverlay(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player.hasStatusEffect(StatusEffects.SPEED)) {
            matrixStack.push();
            matrixStack.translate(0, 0, 0);

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

            String colorHex = "96" + String.format("%02X", R) + String.format("%02X", G) + String.format("%02X", B);
            System.out.println(colorHex);
            int color = (int) Long.parseLong(colorHex, 16);
            DrawableHelper.fill(matrixStack, 0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), color);
            matrixStack.pop();
        }
    }

}
