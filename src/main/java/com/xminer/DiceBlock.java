package com.xminer;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DiceBlock extends Block {

	public static final String BLOCK_ID = "diceblock";

	private static final Logger LOGGER = LogManager.getLogger();

	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
		LOGGER.info("Gift for player");
        event.getPlayer().inventory.addItemStackToInventory(new ItemStack(DiceBlockMod.itemDiceBlock));
    }
    
	public DiceBlock() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1,1).sound(SoundType.BONE));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction d = Direction.getRandomDirection(RANDOM);
		LOGGER.info("Random Dice Roll Face: " + d);
		return getDefaultState().with(FACING, d);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		worldIn.playSound(player, pos, SoundEvents.BLOCK_BONE_BLOCK_PLACE, SoundCategory.BLOCKS, jumpFactor,
				blastResistance);
		worldIn.setBlockState(pos, state.with(FACING, Direction.getRandomDirection(RANDOM)));
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        player.inventory.addItemStackToInventory(new ItemStack(state.getBlock().asItem()));
		super.onBlockHarvested(worldIn, pos, state, player);
	}

}
