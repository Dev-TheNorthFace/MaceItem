package North.MaceItem.ExtraItems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;

public final class ExtraItems {

    private static final Map<String, ItemStack> registry = new HashMap<>();

    private ExtraItems() {}

    private static void register(String name, ItemStack item) {
        registry.put(name, item);
    }

    public static Map<String, ItemStack> getAll() {
        return new HashMap<>(registry);
    }

    public static void setup(JavaPlugin plugin) {
        ItemStack mace = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack wind = new ItemStack(Material.FEATHER);
        register("mace", mace);
        register("wind", wind);
        NamespacedKey maceKey = new NamespacedKey(plugin, "mace");
        ShapedRecipe maceRecipe = new ShapedRecipe(maceKey, mace);
        maceRecipe.shape(" N ", " N ", " S ");
        maceRecipe.setIngredient('N', Material.NETHERITE_INGOT);
        maceRecipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(maceRecipe);
    }
}