package aryan.echoes;
import aryan.echoes.block.ModBlocks;
import aryan.echoes.enchantment.ModEnchantmentEffects;
import aryan.echoes.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Echoes implements ModInitializer {

	public static final String MOD_ID = "echoes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModEnchantmentEffects.registerModEnchantmentEffects();
	}

}
