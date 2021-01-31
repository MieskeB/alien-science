package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import nl.michelbijnen.minecraft.alien.science.entity.AlienScienceEntityTypes;
import nl.michelbijnen.minecraft.alien.science.entity.render.AlienEntityRenderer;
import nl.michelbijnen.minecraft.alien.science.misc.cape.CapeLoader;
import nl.michelbijnen.minecraft.alien.science.misc.cape.JsonCapes;

@Environment(EnvType.CLIENT)
public class AlienScienceClient implements ClientModInitializer {

    public static JsonCapes jsonCapes;
    public static CapeLoader capeLoader;

    @Override
    public void onInitializeClient() {
        capeLoader = new CapeLoader();
        jsonCapes = new JsonCapes();
        capeLoader.register(jsonCapes);
        capeLoader.load();

        EntityRendererRegistry.INSTANCE.register(AlienScienceEntityTypes.ALIEN, (dispatcher, context) -> {
            return new AlienEntityRenderer(dispatcher);
        });
    }
}
