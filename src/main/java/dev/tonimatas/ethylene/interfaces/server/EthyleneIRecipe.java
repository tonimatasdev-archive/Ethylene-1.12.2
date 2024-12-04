package dev.tonimatas.ethylene.interfaces.server;

import net.minecraft.util.ResourceLocation;

public interface EthyleneIRecipe {
    org.bukkit.inventory.Recipe toBukkitRecipe(); // CraftBukkit

    void setKey(ResourceLocation key); // CraftBukkit
}
