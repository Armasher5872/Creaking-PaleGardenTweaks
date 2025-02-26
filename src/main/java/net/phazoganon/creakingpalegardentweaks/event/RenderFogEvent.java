package net.phazoganon.creakingpalegardentweaks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.phazoganon.creakingpalegardentweaks.CreakingPaleGardenTweaks;
import net.phazoganon.creakingpalegardentweaks.effect.ModEffects;

@EventBusSubscriber(modid = CreakingPaleGardenTweaks.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderFogEvent {
    private static boolean active;
    private static boolean hasHaziness;
    private static float distanceGardenClose = 600F;
    private static float distanceGardenFar = 1600F;
    private static float minGardenDistanceFar = 16F;
    private static float minGardenDistanceClose = 6F;
    @SubscribeEvent
    private static void onFogRender(ViewportEvent.RenderFog renderFog) {
        if (distanceGardenFar >= minGardenDistanceFar) {
            if (active) {
                float shiftClose = 0.5F*(distanceGardenClose/80);
                float shiftFar = 0.5F*(distanceGardenFar/100);
                distanceGardenClose -= shiftClose;
                distanceGardenFar -= shiftFar;
                renderFog.setNearPlaneDistance(distanceGardenClose);
                renderFog.setFarPlaneDistance(distanceGardenFar);
                renderFog.setCanceled(true);
            }
        }
        if (distanceGardenFar < minGardenDistanceFar) {
            if (active) {
                if (hasHaziness) {
                    minGardenDistanceFar = 3F;
                    minGardenDistanceClose = 0F;
                }
                distanceGardenClose = minGardenDistanceClose;
                distanceGardenFar = minGardenDistanceFar;
            }
        }
        if (distanceGardenFar < 1600F) {
            if (!active) {
                float shiftClose = 0.5F*(distanceGardenClose/80);
                float shift = 0.5F*(distanceGardenFar/100);
                distanceGardenClose += shiftClose;
                distanceGardenFar += shift;
                renderFog.setNearPlaneDistance(distanceGardenClose);
                renderFog.setFarPlaneDistance(distanceGardenFar);
                renderFog.setCanceled(true);
            }
        }
        if (distanceGardenFar > 300F) {
            if (!active) {
                distanceGardenFar = 1600F;
                distanceGardenClose = 600F;
            }
        }
    }
    @SubscribeEvent
    private static void onComputeFogColor(ViewportEvent.ComputeFogColor computeFogColor) {
        if (distanceGardenFar >= 16F) {
            if (active) {
                computeFogColor.setRed(1F);
                computeFogColor.setGreen(1F);
                computeFogColor.setBlue(1F);
            }
        }
        if (distanceGardenFar < 1600F) {
            if (!active) {
                computeFogColor.setRed(1F);
                computeFogColor.setGreen(1F);
                computeFogColor.setBlue(1F);
            }
        }
    }
    @SubscribeEvent
    private static void onPlayerTickPre(PlayerTickEvent.Pre playerTickEvent) {
        if (playerTickEvent.getEntity() != Minecraft.getInstance().player) {
            return;
        }
        Player player = playerTickEvent.getEntity();
        Level level = player.level();
        BlockPos blockpos = player.blockPosition();
        Holder<Biome> biomeHolder = level.getBiome(blockpos);
        active = !player.hasEffect(MobEffects.NIGHT_VISION) && biomeHolder.is(Biomes.PALE_GARDEN);
        hasHaziness = player.hasEffect(ModEffects.HAZINESS_EFFECT);
    }
}
