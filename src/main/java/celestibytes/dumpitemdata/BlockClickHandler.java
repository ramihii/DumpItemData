package celestibytes.dumpitemdata;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class BlockClickHandler {
	
	@SubscribeEvent
	public void onBlockClick(PlayerInteractEvent e) {
		if(!e.world.isRemote) {
			if(e.action == Action.RIGHT_CLICK_BLOCK && CmdDumpBlockData.getWaitForBlockClick(e.entityPlayer.getUniqueID())) {
				StringBuilder sb = new StringBuilder();
				Block blc = e.world.getBlock(e.x, e.y, e.z);
				if(blc == null) {
					return;
				}
				
				int meta = e.world.getBlockMetadata(e.x, e.y, e.z);
				
				sb.append("BlockID: " + GameData.getBlockRegistry().getNameForObject(blc) + "(" + GameData.getBlockRegistry().getIDForObject(blc) + ")\n");
				sb.append("Block name: " + blc.getUnlocalizedName() + " = " + blc.getLocalizedName() + "\n");
				sb.append("XYZ: " + e.x + " " + e.y + " " + e.z + "\n");
				sb.append("Metadata: " + meta + "\n");
				sb.append("Lightlevel: " + "block: " + e.world.getSavedLightValue(EnumSkyBlock.Block, e.x, e.y, e.z) + ", sky: " + e.world.getSavedLightValue(EnumSkyBlock.Sky, e.x, e.y, e.z) + "\n");
				if(blc.hasTileEntity(meta)) {
					TileEntity te = e.world.getTileEntity(e.x, e.y, e.z);
					if(te != null) {
						NBTTagCompound nbt = new NBTTagCompound();
						te.writeToNBT(nbt);
						sb.append("NBT: {" + CmdDumpItemData.getNBTString(nbt, 1) + "}");
					}
				}
				
				System.out.println("\n" + sb.toString());
				
				e.setCanceled(true);
			}
		}
	}
}
