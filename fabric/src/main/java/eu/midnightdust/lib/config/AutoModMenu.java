package eu.midnightdust.lib.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.core.MidnightLib;
import eu.midnightdust.core.config.MidnightLibConfig;
import net.minecraft.client.gui.screen.Screen;

import java.util.HashMap;
import java.util.Map;

public class AutoModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return new ConfigScreenFactory<Screen>() {
            @Override
            public Screen create(Screen parent) {
                return MidnightLibConfig.getScreen(parent, "midnightlib");
            }
        };
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        HashMap<String, ConfigScreenFactory<?>> map = new HashMap<>();
        MidnightConfig.configClass.forEach((modid, cClass) -> {
            if (!MidnightLib.hiddenMods.contains(modid)) {
                final String id = modid;
                map.put(modid, new ConfigScreenFactory<Screen>() {
                    @Override
                    public Screen create(Screen parent) {
                        return MidnightConfig.getScreen(parent, id);
                    }
                });
            }
        }); 
        return map;
    }
}