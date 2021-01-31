package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import nl.michelbijnen.minecraft.alien.science.entity.AlienScienceEntityTypes;
import nl.michelbijnen.minecraft.alien.science.entity.render.AlienEntityRenderer;

@Environment(EnvType.CLIENT)
public class AlienScienceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(AlienScienceEntityTypes.ALIEN, (dispatcher, context) -> {
            return new AlienEntityRenderer(dispatcher);
        });
    }
}
