package aryan.echoes.item;

import aryan.echoes.Echoes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final TagKey<Item> REPAIRS_CHORUS = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Echoes.MOD_ID, "repairs_chorus"));

    public static final ToolMaterial CHORUS_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            3.0F,
            1.5F,
            22,
            REPAIRS_CHORUS
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Echoes.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static final Item CHORUS_SWORD = register(
            "chorus_sword",
            Item::new,
            new Item.Settings().sword(CHORUS_TOOL_MATERIAL, 3f, -2.4f)
    );

    public static final Item CHORUS_PICKAXE = register(
            "chorus_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(CHORUS_TOOL_MATERIAL, 1f, -2.8f)
    );

    public static final Item CHORUS_AXE = register("chorus_axe",
            setting -> new AxeItem(CHORUS_TOOL_MATERIAL, 5f, -3f, setting),
            new Item.Settings());

    public static final Item CHORUS_HOE = register("chorus_hoe",
            setting -> new HoeItem(CHORUS_TOOL_MATERIAL, -1f, -0f, setting),
            new Item.Settings());

    public static final Item CHORUS_SHOVEL = register("chorus_shovel",
            setting -> new ShovelItem(CHORUS_TOOL_MATERIAL, 1.5f, -3f, setting),
            new Item.Settings());


    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add(CHORUS_SWORD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.add(CHORUS_AXE);
            content.add(CHORUS_PICKAXE);
            content.add(CHORUS_SHOVEL);
            content.add(CHORUS_HOE);
        });

    }
}
