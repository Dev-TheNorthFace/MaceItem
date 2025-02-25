package North.MaceItem.item.Mace;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class Mace extends ItemStack {

    public Mace() {
        super(Material.NETHERITE_SWORD);
    }

    public int getDamage() {
        return 6;
    }

    public boolean onAttackEntity(Entity victim, EntityDamageByEntityEvent event) {
        if (victim instanceof LivingEntity) {
            event.setDamage(getDamage());
            return true;
        }
        return false;
    }
}