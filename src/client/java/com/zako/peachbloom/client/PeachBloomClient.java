package com.zako.peachbloom.client;

import com.zako.peachbloom.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public final class PeachBloomClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelLayerRegistry.registerModelLayer(CloudMonsterModel.LAYER_LOCATION, CloudMonsterModel::createBodyLayer);
		EntityRendererRegistry.register(ModEntities.CLOUD_MONSTER, CloudMonsterRenderer::new);
	}
}
