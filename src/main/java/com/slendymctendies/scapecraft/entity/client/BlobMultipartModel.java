package com.slendymctendies.scapecraft.entity.client;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.slendymctendies.scapecraft.entity.inferno.BlobEntity;

public class BlobMultipartModel extends AdvancedEntityModel<BlobEntity> {

    public final AdvancedModelBox root;
    public final AdvancedModelBox body_front;
    public final AdvancedModelBox body_back;

    public BlobMultipartModel(){
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this);
        root.setPos(0.0f, 1.0f, 0.0f);

        body_front = new AdvancedModelBox(this);
        body_front.setPos(0f, 0f, 0f);
        root.addChild(body_front);

        body_back = new AdvancedModelBox(this);
        body_back.setPos(1f, 0f, 0f);
        body_front.addChild(body_back);
    }
    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body_front, body_back);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public void setupAnim(BlobEntity blobEntity, float v, float v1, float v2, float v3, float v4) {

    }
}
