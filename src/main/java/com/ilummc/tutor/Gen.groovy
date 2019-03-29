package com.ilummc.tutor

import groovy.transform.CompileStatic
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.util.noise.SimplexOctaveGenerator

@CompileStatic
class Gen extends ChunkGenerator {

    SimplexOctaveGenerator g1, g2, g3

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        []
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
        if (g1 == null || g2 == null) {
            def rand = new Random(world.seed)
            g1 = new SimplexOctaveGenerator(rand, 4)
            g2 = new SimplexOctaveGenerator(rand, 5)
            g3 = new SimplexOctaveGenerator(rand, 1)
        }

        def data = createChunkData(world)

        g1.scale = 0.008d
        g2.scale = 0.01d
        g3.scale = 0.01d

        for (x in 0..15) {
            for (z in 0..15) {
                def cover = (int) (85 + 10 * g1.noise(cx * 16D + x, cz * 16D + z, 2.0D, 0.5D))
                def bottom = (int) (100 + 64 * g2.noise(cx * 16D + x, cz * 16D + z, 2.0D, 0.7D))
                def grass = (int) (3 + 4 * g3.noise(cx * 16D + x, cz * 16D + z, 2.0D, 0.5D))
                if (bottom < cover && bottom < 95)
                    for (h in bottom..cover) {
                        if (h + grass < cover) data.setBlock(x, h, z, Material.STONE)
                        else if (h == cover) data.setBlock(x, h, z, Material.GRASS_BLOCK)
                        else data.setBlock(x, h, z, Material.DIRT)
                    }
            }
        }

        data
    }

}
