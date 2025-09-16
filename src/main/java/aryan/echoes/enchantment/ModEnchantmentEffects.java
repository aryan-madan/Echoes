package aryan.echoes.enchantment;

import aryan.echoes.Echoes;
import aryan.echoes.enchantment.effect.SonicBurstEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.mojang.serialization.MapCodec;

public class ModEnchantmentEffects {
    public static final RegistryKey<Enchantment> SONIC_BURST = of("sonic_burst");
    public static MapCodec<SonicBurstEnchantmentEffect> BURST_EFFECT;

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Identifier.of(Echoes.MOD_ID, path);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }

    public static void registerModEnchantmentEffects() {
        Echoes.LOGGER.info("Registering EnchantmentEffects for " + Echoes.MOD_ID);

        BURST_EFFECT = Registry.register(
                Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                Identifier.of(Echoes.MOD_ID, "burst_effect"),
                SonicBurstEnchantmentEffect.CODEC
        );
    }
}
