package io.github.r1fom.ftbcbc.mixin;

import io.github.r1fom.ftbcbc.FTBCBCHandler;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rbasamoyai.createbigcannons.munitions.AbstractCannonProjectile;

@Mixin(value = AbstractCannonProjectile.class, remap = false)
public abstract class AbstractCannonProjectileMixin {
    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    protected void ftbcbc$onHitBlock(BlockHitResult result, CallbackInfo ci) {
        AbstractCannonProjectile projectile = (AbstractCannonProjectile) (Object) this;
        if (!FTBCBCHandler.isDestructionAllowed(projectile.level(), result.getBlockPos())) {
            projectile.discard();
            ci.cancel();
        }
    }
}
