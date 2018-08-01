package com.ilummc.tutor;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class TutorChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        chunkData.setRegion(0,0,0,16, 2, 16, Material.BEDROCK);
        chunkData.setRegion(0,2,0,16, 3, 16, Material.GRASS_BLOCK);
        return chunkData;
    }

}
