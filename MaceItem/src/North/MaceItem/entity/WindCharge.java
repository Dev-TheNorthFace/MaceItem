package North.MaceItem.entity.WindCharge;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;
import java.util.List;

public class WindCharge implements Projectile {

    private final Location location;
    private final Entity shooter;

    public WindCharge(Location location, Entity shooter) {
        this.location = location;
        this.shooter = shooter;
    }

    public void onHit() {
        location.getWorld().spawnParticle(Particle.CLOUD, location, 20, 0.5, 0.5, 0.5, 0.1);
        List<Entity> nearbyEntities = location.getWorld().getNearbyEntities(location, 1.5, 1.5, 1.5);
        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != shooter.getUniqueId()) {
                ((LivingEntity) entity).damage(1, shooter);
                applyKnockBack((LivingEntity) entity);
            }
        }
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }

    private void applyKnockBack(LivingEntity entity) {
        Vector direction = entity.getLocation().toVector().subtract(location.toVector()).normalize().multiply(2.5);
        direction.setY(1);
        entity.setVelocity(direction);
    }
}