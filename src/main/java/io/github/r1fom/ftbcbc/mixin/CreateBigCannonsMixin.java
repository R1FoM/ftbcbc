package io.github.r1fom.ftbcbc.mixin;

import io.github.r1fom.ftbcbc.FTBCBCHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rbasamoyai.createbigcannons.CreateBigCannons;

@Mixin(value = CreateBigCannons.class, remap = false)
public abstract class CreateBigCannonsMixin {
    @Inject(method = "handleCustomExplosion", at = @At("HEAD"), cancellable = true)
    private static void ftbcbc$onHandleCustomExplosion(Level level, Explosion explosion, CallbackInfo ci) {
        BlockPos pos = BlockPos.containing(explosion.center());
        if (!FTBCBCHandler.isDestructionAllowed(level, pos)) {
            ci.cancel();
        }
    }
}
