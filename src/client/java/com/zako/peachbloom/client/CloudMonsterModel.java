package com.zako.peachbloom.client;

import com.zako.peachbloom.PeachBloom;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

/**
 * 云簇怪模型：蓬松的云团身体 + 小脑袋。
 */
public class CloudMonsterModel extends EntityModel<LivingEntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
		Identifier.fromNamespaceAndPath(PeachBloom.MOD_ID, "cloud_monster"), "main"
	);

	public CloudMonsterModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild(
			"body",
			CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 8.0F, -5.0F, 10, 8, 10),
			PartPose.offset(0.0F, 8.0F, 0.0F)
		);
		root.addOrReplaceChild(
			"head",
			CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6),
			PartPose.offset(0.0F, 12.0F, -5.5F)
		);
		root.addOrReplaceChild(
			"left_foot",
			CubeListBuilder.create().texOffs(28, 18).addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4),
			PartPose.offset(-3.0F, 16.0F, 0.0F)
		);
		root.addOrReplaceChild(
			"right_foot",
			CubeListBuilder.create().texOffs(28, 18).addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4),
			PartPose.offset(3.0F, 16.0F, 0.0F)
		);
		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
	}
}
