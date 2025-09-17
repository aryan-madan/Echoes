package aryan.echoes.enchantment;

import aryan.echoes.enchantment.effect.SonicBurstEnchantmentEffect;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.enchantment.Enchantment;


import java.util.concurrent.CompletableFuture;

import static aryan.echoes.item.ModItems.PRIMORDIAL;

public class EnchantmentGenerator extends FabricDynamicRegistryProvider {
    public EnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        register(entries, ModEnchantmentEffects.SONIC_BURST, Enchantment.builder(
                                Enchantment.definition(
                                        registries.getOrThrow(net.minecraft.registry.RegistryKeys.ITEM).getOrThrow(PRIMORDIAL),
                                        10,
                                        3,
                                        Enchantment.leveledCost(1, 10),
                                        Enchantment.leveledCost(1, 15),
                                        5,
                                        AttributeModifierSlot.HAND
                                )
                        )
                        .addEffect(
                                net.minecraft.component.EnchantmentEffectComponentTypes.POST_ATTACK,
                                net.minecraft.enchantment.effect.EnchantmentEffectTarget.ATTACKER,
                                net.minecraft.enchantment.effect.EnchantmentEffectTarget.VICTIM,
                                new SonicBurstEnchantmentEffect(net.minecraft.enchantment.EnchantmentLevelBasedValue.linear(1.0f, 0.5f))
                        )
        );
    }

    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        entries.add(key, builder.build(key.getValue()));
    }

    @Override
    public String getName() {
        return "Enchantment Generator";
    }
}
