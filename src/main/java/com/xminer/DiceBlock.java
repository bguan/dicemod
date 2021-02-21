package com.xminer;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DiceBlock extends Block {

	public static final String BLOCKID = "diceblock";
	
	public DiceBlock() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10, 1).sound(SoundType.BONE));
	}

}
