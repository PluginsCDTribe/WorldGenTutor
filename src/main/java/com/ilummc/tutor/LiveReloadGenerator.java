package com.ilummc.tutor;

import groovy.lang.GroovyClassLoader;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * 方便调试用，热加载的生成器
 */
public class LiveReloadGenerator extends ChunkGenerator {

    static LiveReloadGenerator INSTANCE = new LiveReloadGenerator();

    private GroovyClassLoader cl = new GroovyClassLoader(LiveReloadGenerator.class.getClassLoader());

    private ChunkGenerator inner;

    public void reload() {
        try {
            File file = new File("Gen.groovy");
            if (!file.exists()) {
                file.createNewFile();
            }
            Object o = cl.parseClass(file).newInstance();
            inner = ((ChunkGenerator) o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        return inner.generateChunkData(world, random, x, z, biome);
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return inner.getDefaultPopulators(world);
    }
}
