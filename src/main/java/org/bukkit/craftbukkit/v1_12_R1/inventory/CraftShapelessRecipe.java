package org.bukkit.craftbukkit.v1_12_R1.inventory;

import java.util.List;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftShapelessRecipe extends ShapelessRecipe implements CraftRecipe {
    // TODO: Could eventually use this to add a matches() method or some such
    private ShapelessRecipes recipe;

    public CraftShapelessRecipe(NamespacedKey key, ItemStack result) {
        super(key, result);
    }

    public CraftShapelessRecipe(ItemStack result, ShapelessRecipes recipe) {
        this(CraftNamespacedKey.fromMinecraft(recipe.key), result);
        this.recipe = recipe;
    }

    public static CraftShapelessRecipe fromBukkitRecipe(ShapelessRecipe recipe) {
        if (recipe instanceof CraftShapelessRecipe) {
            return (CraftShapelessRecipe) recipe;
        }
        CraftShapelessRecipe ret = new CraftShapelessRecipe(recipe.getKey(), recipe.getResult());
        for (ItemStack ingred : recipe.getIngredientList()) {
            ret.addIngredient(ingred.getType(), ingred.getDurability());
        }
        return ret;
    }

    public void addToCraftingManager() {
        List<ItemStack> ingred = this.getIngredientList();
        NonNullList<Ingredient> data = NonNullList.withSize(ingred.size(), Ingredient.EMPTY);
        for (int i = 0; i < ingred.size(); i++) {
            data.set(i, Ingredient.fromStacks(new net.minecraft.item.ItemStack[]{CraftItemStack.asNMSCopy(ingred.get(i))}));
        }
        
        CraftingManager.register(CraftNamespacedKey.toMinecraft(this.getKey()), new ShapelessRecipes("", CraftItemStack.asNMSCopy(this.getResult()), data));
    }
}
