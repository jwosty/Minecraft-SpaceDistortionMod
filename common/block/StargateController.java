package jw.spacedistortion.common.block;

import jw.spacedistortion.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class StargateController extends Block {
	// The coordinate at which the textures for this block starts
	private int blockIndexInTexture;
	private int textureTop = 2;
	
	public StargateController(int id, int _blockIndexInTexture, Material material) {
		super(id, material);
		blockIndexInTexture = _blockIndexInTexture;
	}
	
	@Override
	public String getTextureFile() {
		return CommonProxy.TEXTURES_PNG;
	}
	
	@Override
	public int getBlockTextureFromSide(int side) {
		switch (side) {
			case 3:		return 1;
			default:	return 0;
		}
	}
}
