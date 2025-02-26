package net.phazoganon.creakingpalegardentweaks.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HazinessEffect extends MobEffect {
    protected HazinessEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}