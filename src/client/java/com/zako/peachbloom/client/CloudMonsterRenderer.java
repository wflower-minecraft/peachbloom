package com.zako.peachbloom.client;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.entity.CloudClusterMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class CloudMonsterRenderer extends MobRenderer<CloudClusterMonster, LivingEntityRenderState, CloudMonsterModel> {
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(PeachBloom.MOD_ID, "textures/entity/cloud_monster.png");

	public CloudMonsterRenderer(EntityRendererProvider.Context context) {
		super(context, new CloudMonsterModel(context.bakeLayer(CloudMonsterModel.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return TEXTURE;
	}
}
