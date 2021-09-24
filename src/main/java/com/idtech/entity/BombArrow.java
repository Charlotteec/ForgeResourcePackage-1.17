package com.idtech.entity;

import com.idtech.item.BombArrowItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BombArrow extends AbstractArrow {

    public BombArrow(EntityType<? extends Arrow> type, Level level) {
        super(type, level);
    }

    public BombArrow(Level level, double x, double y, double z) {
        super(EntityType.ARROW, x, y, z, level);
    }

    public BombArrow(Level p_36866_, LivingEntity p_36867_) {
        super(EntityType.ARROW, p_36867_, p_36866_);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(BombArrowItem.INSTANCE);
    }


    int count = 0;
    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        Vec3 loc = hitResult.getLocation();
        if (count < 1) {
            level.explode(null, loc.x, loc.y, loc.z, 10.0f, Explosion.BlockInteraction.DESTROY);
            count ++;
        }
    }




}
