package union.cubeground.mod.common.commands;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandFly implements ICommand {
	public String getCommandName() {
		return "fly";
	}

	public String getCommandUsage(ICommandSender icommandsender) {
		return "sets player fly";
	}

	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (icommandsender instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)icommandsender;
			if (p.capabilities.allowFlying) {
				p.capabilities.allowFlying = false;
				p.addChatMessage("flying disabled");
			} else {
				p.capabilities.allowFlying = true;
				p.addChatMessage("flying enabled");
			}
		}
	}
	
	public List getCommandAliases() {return null;}
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {return true;}
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {return null;}
	public boolean isUsernameIndex(String[] astring, int i) {return false;}
	public int compareTo(Object arg0) {return 0;}
}
