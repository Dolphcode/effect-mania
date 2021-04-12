package xyz.dolphcode.effectmania;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.dolphcode.effectmania.effect.ModEffects;

import java.util.ArrayList;
import java.util.Set;

public class EffectMania implements ModInitializer {

    public static final String ID = "effect_mania";

    public static ArrayList<Identifier> LIVING_ENTITIES = new ArrayList<Identifier>();

    @Override
    public void onInitialize() {
        ModEffects.registerStatusEffects();
        ModEffects.registerPotions();

        Registry.ENTITY_TYPE.getIds().forEach((Identifier i) -> {
            SpawnGroup sg = Registry.ENTITY_TYPE.get(i).getSpawnGroup();
            if (!sg.equals(SpawnGroup.MISC)) {
                LIVING_ENTITIES.add(i);
            }
        });
    }
}
