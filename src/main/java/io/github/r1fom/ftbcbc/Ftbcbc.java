package io.github.r1fom.ftbcbc;

import com.mojang.logging.LogUtils;
import dev.ftb.mods.ftbteams.api.event.TeamEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Ftbcbc.MODID)
public class Ftbcbc {
    public static final String MODID = "ftbcbc";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Ftbcbc(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        // Register events
        modEventBus.addListener(Config::onLoad);
        TeamEvent.COLLECT_PROPERTIES.register(FTBCBCProperties::onCollectTeamProperties);
    }
}
