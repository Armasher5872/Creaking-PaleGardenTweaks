package net.phazoganon.creakingpalegardentweaks;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.phazoganon.creakingpalegardentweaks.effect.ModEffects;

@Mod(CreakingPaleGardenTweaks.MODID)
public class CreakingPaleGardenTweaks {
    public static final String MODID = "creakingpalegardentweaks";
    public CreakingPaleGardenTweaks(IEventBus eventBus) {
        ModEffects.register(eventBus);
    }
}