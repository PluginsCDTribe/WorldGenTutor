package com.ilummc.tutor;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGenTutor extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ExtendVanillaGenerator(), this);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        switch (id) {
            case "flat":
                return new FlatGenerator();
            case "normal":
                return new NormalGenerator();
            case "noise":
                return new NoiseGenerator(worldName);
            default:
                return null;
        }
    }

}
