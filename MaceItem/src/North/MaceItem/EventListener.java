package North.MaceItem.EventListener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;

public final class EventListener implements Listener {

    private final Map<String, Double> playerFallDistance = new HashMap<>();
    private final JavaPlugin plugin;

    public EventListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double currentY = event.getTo().getY();
        double previousY = event.getFrom().getY();

        if (currentY < previousY) {
            playerFallDistance.put(player.getName(), playerFallDistance.getOrDefault(player.getName(), 0.0) + (previousY - currentY));
        }

        if (player.isOnGround()) {
            playerFallDistance.remove(player.getName());
        }
    }

    @EventHandler
    public void maceLogic(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.NETHERITE_SWORD) {
            double fallDistance = playerFallDistance.getOrDefault(player.getName(), 0.0);
            if (fallDistance > 2) {
                event.setDamage(event.getDamage() + 5 * (fallDistance - 1));
                playerFallDistance.remove(player.getName());

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.getWorld().playSound(player.getLocation(), "mace.heavy_smash_ground", 1.0f, 1.0f);
                    }
                }.runTask(plugin);
            }
        }
    }
}