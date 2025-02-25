package North.MaceItem.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class Main extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        registerItems();
    }

    private void registerItems() {
        ItemStack mace = createCustomItem("Mace of Xeon", Material.NETHERITE_AXE, "mace_xeon");
        ItemStack windCharge = createCustomItem("Wind Charge", Material.FIREWORK_STAR, "wind_charge_item");

        NamespacedKey maceKey = new NamespacedKey(this, "mace_xeon");
        ShapedRecipe maceRecipe = new ShapedRecipe(maceKey, mace);
        maceRecipe.shape(" I ", " S ", " S ");
        maceRecipe.setIngredient('I', Material.NETHERITE_INGOT);
        maceRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(maceRecipe);
    }

    private ItemStack createCustomItem(String name, Material material, String key) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.getPersistentDataContainer().set(new NamespacedKey(this, key), PersistentDataType.STRING, key);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            String key = item.getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(this, "wind_charge_item"), PersistentDataType.STRING);
            if ("wind_charge_item".equals(key)) {
                event.getPlayer().launchProjectile(Projectile.class).setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() instanceof org.bukkit.entity.Player) {
            projectile.getWorld().createExplosion(projectile.getLocation(), 2F);
        }
    }
}