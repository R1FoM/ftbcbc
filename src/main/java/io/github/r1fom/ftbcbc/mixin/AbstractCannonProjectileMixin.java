package io.github.r1fom.ftbcbc.mixin;

import io.github.r1fom.ftbcbc.FTBCBCHandler;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rbasamoyai.createbigcannons.munitions.AbstractCannonProjectile;
import rbasamoyai.createbigcannons.munitions.ProjectileContext;

@Mixin(value = AbstractCannonProjectile.class)
public abstract class AbstractCannonProjectileMixin {
    @Shadow protected abstract AbstractCannonProjectile.ImpactResult calculateBlockPenetration(ProjectileContext projectileContext, BlockState state, BlockHitResult blockHitResult);

    @Redirect(method = "clipAndDamage", at = @At(value = "INVOKE", target = "Lrbasamoyai/createbigcannons/munitions/AbstractCannonProjectile;calculateBlockPenetration(Lrbasamoyai/createbigcannons/munitions/ProjectileContext;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/BlockHitResult;)Lrbasamoyai/createbigcannons/munitions/AbstractCannonProjectile$ImpactResult;"))
    private AbstractCannonProjectile.ImpactResult ftbcbc$onCalculateBlockPenetration(AbstractCannonProjectile instance, ProjectileContext projectileContext, BlockState state, BlockHitResult blockHitResult) {
        if (!FTBCBCHandler.isDestructionAllowed(instance.level(), blockHitResult.getBlockPos())) {
            instance.discard();
            return new AbstractCannonProjectile.ImpactResult(AbstractCannonProjectile.ImpactResult.KinematicOutcome.STOP, true);
        }
        return this.calculateBlockPenetration(projectileContext, state, blockHitResult);
    }
}
