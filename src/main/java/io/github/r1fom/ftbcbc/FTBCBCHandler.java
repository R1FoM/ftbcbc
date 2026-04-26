package io.github.r1fom.ftbcbc;

import dev.ftb.mods.ftbchunks.api.FTBChunksAPI;
import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.ftb.mods.ftblibrary.math.ChunkDimPos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class FTBCBCHandler {
    /**
     * Checks if CBC destruction (explosions/block breaking) is allowed at the given position.
     * 
     * @param level The level
     * @param pos The block position
     * @return true if destruction is allowed (either unclaimed or allowed by team property), false otherwise.
     */
    public static boolean isDestructionAllowed(Level level, BlockPos pos) {
        // Only check on server side
        if (level.isClientSide()) return true;
        
        ChunkDimPos chunkDimPos = new ChunkDimPos(level, pos);
        ClaimedChunk chunk = FTBChunksAPI.api().getManager().getChunk(chunkDimPos);
        if (chunk == null) {
            return true; // Not claimed, destruction allowed
        }
        
        // Check the team property
        return chunk.getTeamData().getTeam().getProperty(FTBCBCProperties.ALLOW_CBC_GRIEFING);
    }
}


