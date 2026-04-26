package io.github.r1fom.ftbcbc.mixin;

import io.github.r1fom.ftbcbc.FTBCBCHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rbasamoyai.createbigcannons.munitions.ImpactExplosion;
import rbasamoyai.createbigcannons.munitions.ShellExplosion;
import rbasamoyai.createbigcannons.munitions.big_cannon.mortar_stone.MortarStoneExplosion;

@Mixin(value = {ImpactExplosion.class, ShellExplosion.class, MortarStoneExplosion.class}, remap = false)
public abstract class CBCExplosionMixin {
    @Inject(method = "editBlock", at = @At("HEAD"), cancellable = true)
    private void ftbcbc$onEditBlock(Level level, BlockPos pos, BlockState blockState, FluidState fluidState, float power, CallbackInfo ci) {
        if (!FTBCBCHandler.isDestructionAllowed(level, pos)) {
            ci.cancel();
        }
    }
}
