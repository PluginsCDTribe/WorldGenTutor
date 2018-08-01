package com.ilummc.tutor;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGenTutor extends JavaPlugin {

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        switch (id) {
            case "flat":
                return new TutorChunkGenerator();
            default:
                return null;
        }
    }

}
