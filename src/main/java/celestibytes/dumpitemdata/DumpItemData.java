package celestibytes.dumpitemdata;

import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.command.CommandHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod;


@Mod(modid = "dumpitemdata", name = "DumpItemData", version = "1.0")
public class DumpItemData {

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new BlockClickHandler());
	}

	@EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent e) {
		CommandHandler ch = (CommandHandler)e.getServer().getCommandManager();
		
		//if("true".equalsIgnoreCase(System.getProperty("enableDumpItemDataCommand", "false"))) {
		ch.registerCommand(new CmdDumpItemData());
		ch.registerCommand(new CmdDumpBlockData());
		//}
		
	}
}
