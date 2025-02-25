package North.MaceItem.particle.WindParticle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class WindParticle {

    public static void spawn(Location location) {
        World world = location.getWorld();
        if (world != null) {
            world.spawnParticle(Particle.CLOUD, location, 20, 0.5, 0.5, 0.5, 0.1);
        }
    }
}