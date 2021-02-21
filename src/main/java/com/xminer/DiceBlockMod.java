package com.xminer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DiceBlockMod.MODID)
public class DiceBlockMod {
	public static final String MODID = "diceblockmod";
	
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	// get a reference to the event bus for this mod; Registration events are fired
	// on this bus.
	public static IEventBus MOD_EVENT_BUS;

	public static DiceBlock diceBlock;
	public static BlockItem itemDiceBlock;

	@SuppressWarnings("deprecation")
	public DiceBlockMod() {
		MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
		registerCommonEvents();
		DistExecutor.runWhenOn(Dist.CLIENT, () -> DiceBlockMod::registerClientOnlyEvents);
	}

	public static void registerCommonEvents() {
		MOD_EVENT_BUS.register(DiceBlockMod.class);
	}

	public static void registerClientOnlyEvents() {
		MOD_EVENT_BUS.register(DiceBlockMod.class);
	}

	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(diceBlock, RenderType.getSolid());
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	@SubscribeEvent
	public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
		diceBlock = (DiceBlock) (new DiceBlock().setRegistryName(MODID, DiceBlock.BLOCKID));
		blockRegisterEvent.getRegistry().register(diceBlock);
	}

	@SubscribeEvent
	public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
		// We need to create a BlockItem so the player can carry this block in their
		// hand and it can appear in the inventory
		final int MAXIMUM_STACK_SIZE = 1; // player can only hold 1 dice block in their hand at once

		Item.Properties itemSimpleProperties = new Item.Properties()
				.maxStackSize(MAXIMUM_STACK_SIZE)
				.group(ItemGroup.BUILDING_BLOCKS); // which inventory tab?
		itemDiceBlock = new BlockItem(diceBlock, itemSimpleProperties);
		itemDiceBlock.setRegistryName(diceBlock.getRegistryName());
		itemRegisterEvent.getRegistry().register(itemDiceBlock);
	}
}
