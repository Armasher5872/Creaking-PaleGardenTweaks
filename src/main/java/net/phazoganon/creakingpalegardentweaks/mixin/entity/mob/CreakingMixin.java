package net.phazoganon.creakingpalegardentweaks.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.phazoganon.creakingpalegardentweaks.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Creaking.class)
public abstract class CreakingMixin extends Monster {
    protected CreakingMixin(EntityType<? extends Creaking> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Inject(method = "customServerAiStep", at = @At(value = "HEAD"))
    private void customServerAiStep(ServerLevel p_379858_, CallbackInfo ci) {
        if ((this.tickCount + this.getId()) % 120 == 0) {
            creaking_PaleGardenTweaks$applyHazinessAround(p_379858_, this.position(), this);
        }
    }
    @Unique
    private static void creaking_PaleGardenTweaks$applyHazinessAround(ServerLevel level, Vec3 pos, @Nullable Entity source) {
        MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.HAZINESS_EFFECT, 400, 0, false, true);
        MobEffectUtil.addEffectToPlayersAround(level, source, pos, 20, mobeffectinstance, 400);
    }
}