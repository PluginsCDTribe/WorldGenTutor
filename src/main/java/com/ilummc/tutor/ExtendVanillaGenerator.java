package com.ilummc.tutor;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class ExtendVanillaGenerator implements Listener {

    @EventHandler
    public void onInit(WorldInitEvent event) {
        if ("world".equals(event.getWorld().getName())) {
            event.getWorld().getPopulators().add(new PumpkinPopulator());
        }
    }

    private static class PumpkinPopulator extends BlockPopulator {

        @Override
        public void populate(World world, Random random, Chunk chunk) {
            // 随机生成一些南瓜的数量
            int amount = random.nextInt(8);
            for (int i = 0; i < amount; i++) {
                // 随机位置
                int x = random.nextInt(16);
                int z = random.nextInt(16);
                for (int y = 255; y >= 0; y--) {
                    if (chunk.getBlock(x, y, z).getType() != Material.AIR) {
                        // 只让南瓜生成在草方块上
                        if (chunk.getBlock(x, y, z).getType() == Material.GRASS_BLOCK
                                && chunk.getBlock(x, y + 1, z).getType() == Material.AIR)
                            chunk.getBlock(x, y + 1, z).setType(Material.PUMPKIN);
                        break;
                    }
                }
            }
        }
    }

}
