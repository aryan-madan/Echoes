package aryan.echoes.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public record SonicBurstEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<SonicBurstEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(SonicBurstEnchantmentEffect::amount)
            ).apply(instance, SonicBurstEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (context.owner() != null && context.owner() instanceof PlayerEntity player) {
            float particleCount = this.amount.getValue(level);

            for (int i = 0; i < particleCount; i++) {
                world.spawnParticles(
                        ParticleTypes.SONIC_BOOM,
                        target.getX(), target.getBodyY(0.5), target.getZ(),
                        1, 1, 1, 1, 0.1
                );
            }

            world.playSound(
                    null,
                    target.getBlockPos(),
                    SoundEvents.ENTITY_WARDEN_ATTACK_IMPACT,
                    net.minecraft.sound.SoundCategory.HOSTILE,
                    1.0f,
                    1.0f
            );

            double radius = 3.0;

            var entitiesInRange = world.getEntitiesByClass(
                    LivingEntity.class,
                    target.getBoundingBox().expand(radius),
                    e -> e != context.owner()
            );

            float damageAmount = level * 2.0f;

            for (LivingEntity entity : entitiesInRange) {
                entity.damage(world, player.getDamageSources().magic(), damageAmount);
            }
        }
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
