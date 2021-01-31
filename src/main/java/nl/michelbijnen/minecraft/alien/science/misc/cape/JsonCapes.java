
package nl.michelbijnen.minecraft.alien.science.misc.cape;

import net.minecraft.util.Identifier;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.api.capes.CapeListener;
import nl.michelbijnen.minecraft.alien.science.api.capes.models.CapeModel;
import nl.michelbijnen.minecraft.alien.science.api.capes.models.CapePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class JsonCapes implements CapeListener {

    private final Map<UUID, CapePlayer> capePlayers = new HashMap<>();

    private boolean capesLoaded = false;

    public Map<UUID, CapePlayer> getCapePlayers() {
        return this.capePlayers;
    }

    @Override
    public void loadCapes(CapeModel capesModel) {
        for (CapePlayer player : capesModel.getPlayers()) {
            capePlayers.put(player.getUuid(), player);
        }

        this.capesLoaded = true;
    }

    public boolean areCapesLoaded() {
        return this.capesLoaded;
    }

    public enum Cape {
        DEVELOPER("developer"),
        ;

        final String key;

        Cape(String key) {
            this.key = key;
        }

        public static Cape valueOfIgnoreCase(String key) {
            return valueOf(key.toUpperCase());
        }

        public boolean equals(String key) {
            return this.key.equals(key);
        }

        public boolean equals(Cape cape) {
            return this.key.equals(cape.key);
        }

        public Identifier getTexture() {
            return new Identifier(Constants.MODID, "textures/cape/cape_" + this.key + ".png");
        }
    }
}
