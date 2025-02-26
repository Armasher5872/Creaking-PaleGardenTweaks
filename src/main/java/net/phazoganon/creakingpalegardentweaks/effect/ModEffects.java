package net.phazoganon.creakingpalegardentweaks.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.phazoganon.creakingpalegardentweaks.CreakingPaleGardenTweaks;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CreakingPaleGardenTweaks.MODID);
    public static final Holder<MobEffect> HAZINESS_EFFECT = MOB_EFFECT_DEFERRED_REGISTER.register("haziness",
            () -> new HazinessEffect(MobEffectCategory.HARMFUL, 0x7F7F7F));
    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
}