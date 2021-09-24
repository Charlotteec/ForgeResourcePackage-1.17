package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class GrumboBoyRenderer extends ZombieRenderer {

    public GrumboBoyRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/zombo.png");
    }

}
