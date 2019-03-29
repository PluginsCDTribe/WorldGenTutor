package com.ilummc.tutor;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGenTutor extends JavaPlugin {

    @Override
    public void onEnable() {
        // 南瓜生成器
        // getServer().getPluginManager().registerEvents(new ExtendVanillaGenerator(), this);
        getCommand("wgt").setExecutor(this);
        LiveReloadGenerator.INSTANCE.reload();
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
            case "script":
                return LiveReloadGenerator.INSTANCE;
            default:
                return null;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        long time = System.currentTimeMillis();
        if (args.length == 1 && args[0].equalsIgnoreCase("r")) {
            LiveReloadGenerator.INSTANCE.reload();
            sender.sendMessage("Done in " + (((double) System.currentTimeMillis()) - ((double) time)) / 1000d + "s.");
        }
        if (args.length == 1 && args[0].startsWith("w")) {
            int size;
            try {
                size = Integer.parseInt(args[0].substring(2));
            } catch (Exception e) {
                size = 1;
            }
            Chunk chunk = ((Player) sender).getLocation().getChunk();
            for (int i = -size; i <= size; i++) {
                for (int j = -size; j <= size; j++) {
                    ((Player) sender).getWorld().regenerateChunk(chunk.getX() + i, chunk.getZ() + j);
                }
            }
            sender.sendMessage("Done in " + (((double) System.currentTimeMillis()) - ((double) time)) / 1000d + "s.");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("rw")) {
            LiveReloadGenerator.INSTANCE.reload();
            for (Chunk chunk : ((Player) sender).getWorld().getLoadedChunks()) {
                ((Player) sender).getWorld().regenerateChunk(chunk.getX(), chunk.getZ());
            }
            sender.sendMessage("Done in " + (((double) System.currentTimeMillis()) - ((double) time)) / 1000d + "s.");
        }
        return true;
    }
}
