package xyz.dolphcode.effectmania.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.dolphcode.effectmania.EffectMania;

public class ModEffects {

    // Status Effects
    public static final StatusEffect RAINBOW = new RainbowStatusEffect();

    // Potions
    public static final Potion RAINBOW_POTION = new Potion("rainbow", new StatusEffectInstance[]{new StatusEffectInstance(RAINBOW, 1200)});
    public static final Potion RAINBOW_POTION_LONG = new Potion("rainbow", new StatusEffectInstance[]{new StatusEffectInstance(RAINBOW, 3600)});

    public static void registerStatusEffects() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(EffectMania.ID, "rainbow"), RAINBOW);
    }

    public static void registerPotions() {
        Registry.register(Registry.POTION, new Identifier(EffectMania.ID, "rainbow"), RAINBOW_POTION);
        Registry.register(Registry.POTION, new Identifier(EffectMania.ID, "rainbow_long"), RAINBOW_POTION_LONG);
    }


}
