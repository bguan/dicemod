package com.xminer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DiceBlockMod.MOD_ID)
public class DiceBlockMod {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "diceblockmod";
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
	public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
		LOGGER.info("Registering Dice Block");
		diceBlock = (DiceBlock) (new DiceBlock().setRegistryName(MOD_ID, DiceBlock.BLOCK_ID));
		blockRegisterEvent.getRegistry().register(diceBlock);
	}

	@SubscribeEvent
	public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
		LOGGER.info("Registering Dice Item");
		Item.Properties itemProps = new Item.Properties().maxStackSize(999).group(ItemGroup.MISC);
		itemDiceBlock = new BlockItem(diceBlock, itemProps);
		itemDiceBlock.setRegistryName(diceBlock.getRegistryName());
		itemRegisterEvent.getRegistry().register(itemDiceBlock);
	}

}
