package com.ilummc.tutor;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class FlatGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        // 创建区块数据
        ChunkData chunkData = createChunkData(world);
        // 一个区块的大小为 16*16，高度为 0-255
        // 将这个区块的 (0,0) 到 (16,16) 的最低两层填充为基岩
        chunkData.setRegion(0, 0, 0, 16, 2, 16, Material.BEDROCK);
        // 将第三层填充为草方块
        chunkData.setRegion(0, 2, 0, 16, 3, 16, Material.GRASS_BLOCK);
        // 将整个区块的生物群系设置为平原（PLAINS）
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                biome.setBiome(i, j, Biome.PLAINS);
            }
        }
        return chunkData;
    }

}
