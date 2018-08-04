package com.ilummc.tutor;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;

import java.util.Random;

public class NoiseGenerator extends ChunkGenerator {

    public static void main(String[] args) {
        PerlinNoiseGenerator n2 = new PerlinNoiseGenerator(new Random());
        int counter = 0;
        boolean flag = false;
        for (int i = 0; i < 1000; i++) {
            System.out.println(Math.sqrt(Math.max(n2.noise(i * 0.007D, 8, 2.0, 0.5) - 0.3D, 0D)) * 2.0);
        }
        System.out.println(counter);
    }

    private final Random random;
    private final PerlinNoiseGenerator n1, n2, n3, n4, n5;

    public NoiseGenerator(String name) {
        this.random = new Random(name.hashCode());
        n1 = new PerlinNoiseGenerator(random);
        n2 = new PerlinNoiseGenerator(random);
        n3 = new PerlinNoiseGenerator(random);
        n4 = new PerlinNoiseGenerator(random);
        n5 = new PerlinNoiseGenerator(random);
    }


    @Override
    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
        // 高度
        int[][] elevation = new int[16][16];
        ChunkData data = createChunkData(world);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double e = n1.noise((cx * 16 + x) * 0.01F, (cz * 16 + z) * 0.01F, 6, 2.0F, 0.5F);

                e = e >= 0 ? Math.pow(e, 2.5) : -Math.pow(-e, 2);

                e += Math.pow(Math.max(n2.noise((cx * 16 + x) * 0.007F, (cz * 16 + z) * 0.007F,
                        8, 2.0, 0.5), 0F), 4F) * 6.0;
                elevation[x][z] = (int) (64 + e * 64F);
            }
        }
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                data.setBlock(x, 0, z, Material.BEDROCK);
                int height = Math.max(elevation[x][z], 60);
                for (int y = 1; y < height; y++) {
                    if (y > elevation[x][z])
                        data.setBlock(x, y, z, Material.WATER);
                    else if (y < elevation[x][z] - 2)
                        data.setBlock(x, y, z, Material.STONE);
                    else if (y >= elevation[x][z] - 2 && y <= elevation[x][z]) {
                        if (y < 60 && y < elevation[x][z]) data.setBlock(x, y, z, Material.STONE);
                        else if (y < elevation[x][z]) data.setBlock(x, y, z, Material.DIRT);
                    }
                }
                data.setBlock(x, elevation[x][z] - 2, z, Material.DIRT);
                data.setBlock(x, elevation[x][z] - 1, z, Material.DIRT);
                data.setBlock(x, elevation[x][z], z, Material.GRASS_BLOCK);
            }
        }
        return data;
    }

}
