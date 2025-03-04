package net.phazoganon.creakingpalegardentweaks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.phazoganon.creakingpalegardentweaks.CreakingPaleGardenTweaks;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, CreakingPaleGardenTweaks.MODID);
    }
    @Override
    protected void addTags(HolderLookup.Provider p_256485_) {
        this.tag(Tags.Biomes.NO_DEFAULT_MONSTERS).add(Biomes.PALE_GARDEN);
    }
}