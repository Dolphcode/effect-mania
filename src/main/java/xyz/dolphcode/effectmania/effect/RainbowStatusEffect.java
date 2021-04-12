package xyz.dolphcode.effectmania.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class RainbowStatusEffect extends StatusEffect {

    public RainbowStatusEffect() {
        super(StatusEffectType.BENEFICIAL, 0xFF40C2);
    }

    // This method is called every tick to check weather it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
