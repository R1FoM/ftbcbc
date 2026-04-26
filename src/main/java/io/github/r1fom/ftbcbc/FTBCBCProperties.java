package io.github.r1fom.ftbcbc;

import dev.ftb.mods.ftbteams.api.event.TeamCollectPropertiesEvent;
import dev.ftb.mods.ftbteams.api.property.BooleanProperty;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;

public class FTBCBCProperties {
    public static final BooleanProperty ALLOW_CBC_GRIEFING = new BooleanProperty(
            ResourceLocation.fromNamespaceAndPath(Ftbcbc.MODID, "allow_cbc_griefing"),
            false
    );

    @SubscribeEvent
    public static void onCollectTeamProperties(TeamCollectPropertiesEvent event) {
        event.add(ALLOW_CBC_GRIEFING);
    }
}
