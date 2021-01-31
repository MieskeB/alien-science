package nl.michelbijnen.minecraft.alien.science.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.entity.AlienEntity;
import nl.michelbijnen.minecraft.alien.science.entity.model.AlienEntityModel;

public class AlienEntityRenderer extends MobEntityRenderer<AlienEntity, AlienEntityModel> {
    public AlienEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new AlienEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(AlienEntity entity) {
        return new Identifier(Constants.MODID, Constants.Entities.Alien.PATH_TO_ALIEN);
    }
}
