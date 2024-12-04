package dev.tonimatas.ethylene.mixins.server;

import dev.tonimatas.ethylene.interfaces.server.EthyleneIRecipe;
import net.minecraft.item.crafting.IRecipe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IRecipe.class)
public interface IRecipeMixin extends EthyleneIRecipe {
}
