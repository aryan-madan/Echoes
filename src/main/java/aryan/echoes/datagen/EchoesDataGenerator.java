package aryan.echoes.datagen;

import aryan.echoes.enchantment.EnchantmentGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EchoesDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        System.out.println("Initializer called");

        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(EnchantmentGenerator::new);
    }
}
