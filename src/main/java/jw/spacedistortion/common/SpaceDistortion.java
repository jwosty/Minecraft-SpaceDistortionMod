package jw.spacedistortion.common;

import java.util.HashMap;

import jw.spacedistortion.Pair;
import jw.spacedistortion.StringGrid;
import jw.spacedistortion.client.gui.SDGuiHandler;
import jw.spacedistortion.common.block.SDBlock;
import jw.spacedistortion.common.network.ChannelHandler;
import jw.spacedistortion.common.tileentity.TileEntityEventHorizon;
import jw.spacedistortion.common.tileentity.TileEntityStargateController;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = CommonProxy.MOD_ID, name = CommonProxy.MOD_NAME, version = CommonProxy.MOD_VERSION)
public class SpaceDistortion {
	@Instance(CommonProxy.MOD_ID)
	public static SpaceDistortion instance;
	
	@SidedProxy(clientSide="jw.spacedistortion.client.ClientProxy", serverSide="jw.spacedistortion.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static StringGrid stargateRingShape = new StringGrid(
			"  RCR  ",
			" C   C ",
			"R     R",
			"C     C",
			"R     R",
			" C   C ",
			"  RCR  ");
	public static StringGrid stargateEventHorizonShape = new StringGrid(
			"       ",
			"  EEE  ",
			" EEEEE ",
			" EEEEE ",
			" EEEEE ",
			"  EEE  ",
			"       ");
	public static HashMap<Character, Pair<Block, Boolean>> stargateRingShapeInfo = null;
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		ChannelHandler.initChannels();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		SDBlock.configureBlocks(config);
		SDBlock.registerBlocks();
		config.save();
		
		stargateRingShapeInfo = new HashMap();
		stargateRingShapeInfo.put('R', new Pair(SDBlock.stargateRing, false));
		stargateRingShapeInfo.put('C', new Pair(SDBlock.stargateRingChevron, true));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileEntityEventHorizon.class, "tileEntityEventHorizon");
		GameRegistry.registerTileEntity(TileEntityStargateController.class, "tileEntityStargateController");
		NetworkRegistry.INSTANCE.registerGuiHandler(SpaceDistortion.instance, new SDGuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) { }
}