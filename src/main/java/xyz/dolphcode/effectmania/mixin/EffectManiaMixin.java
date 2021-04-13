package xyz.dolphcode.effectmania.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.dolphcode.effectmania.EffectMania;

@Mixin(PlayerEntity.class)
public class EffectManiaMixin {

    @Inject(method="damage", at=@At("RETURN"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue() == true) {
            if (Math.random() <= 0.5) {
                int randomEffect = (int) Math.round(Math.random() * ((double) Registry.STATUS_EFFECT.getIds().size()));
                int randomDuration = (((int) Math.round(Math.random() * 30.0)) + 10) * 20;
                ((PlayerEntity) (Object) this).addStatusEffect(new StatusEffectInstance(Registry.STATUS_EFFECT.get(randomEffect), randomDuration));
            } else {
                //int randomEntity = (int) Math.round(Math.random() * ((double) EffectMania.LIVING_ENTITIES.size()));
                //Entity e = new Entity(Registry.ENTITY_TYPE.get(EffectMania.LIVING_ENTITIES.get(randomEntity)), ((PlayerEntity) (Object) this).world);
                //((PlayerEntity) (Object) this).world.spawnEntity(e);
            }

        }
    }
}
