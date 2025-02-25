package North.MaceItem.item.Wind;

import North.MaceItem.entity.WindCharge;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class Wind extends ItemStack {

    public Wind() {
        super(Material.FIREWORK_STAR);
    }

    public int getMaxStackSize() {
        return 64;
    }

    public Projectile createEntity(Location location, Player thrower) {
        Projectile projectile = location.getWorld().spawn(location, WindCharge.class);
        projectile.setVelocity(thrower.getLocation().getDirection().multiply(getThrowForce()));
        projectile.setShooter((ProjectileSource) thrower);
        return projectile;
    }

    public float getThrowForce() {
        return 1.5f;
    }

    public int getCooldownTicks() {
        return 10;
    }

    public String getCooldownTag() {
        return "wind_charge";
    }
}