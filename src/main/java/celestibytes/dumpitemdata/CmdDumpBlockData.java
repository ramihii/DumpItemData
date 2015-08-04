package celestibytes.dumpitemdata;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CmdDumpBlockData extends CommandBase {
	
	private static Map<UUID, Boolean> waiting = new HashMap<UUID, Boolean>();
	
	public static boolean getWaitForBlockClick(UUID plr) {
		if(waiting.get(plr) != null) {
			waiting.remove(plr);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender cs) {
		return cs instanceof EntityPlayer && super.canCommandSenderUseCommand(cs);
	}

	@Override
	public String getCommandName() {
		return "dumpblockdata";
	}

	@Override
	public String getCommandUsage(ICommandSender cs) {
		return "dumpblockdata";
	}

	@Override
	public void processCommand(ICommandSender cs, String[] cmd) {
		if(cs instanceof EntityPlayer) {
			if(!waiting.containsKey(((EntityPlayer) cs).getUniqueID())) {
				waiting.put(((EntityPlayer) cs).getUniqueID(), true);
			}
		}
	}
	
}
