package union.cubeground.mod.common.commands;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandSpeed implements ICommand {
	public String getCommandName() {
		return "speed";
	}

	public String getCommandUsage(ICommandSender icommandsender) {
		return "sets player speed";
	}

	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (icommandsender instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)icommandsender;
			try {
				if (astring.length >= 1) {
					if (astring.length == 1) {
						float speed = Float.valueOf(astring[0]);
						if (p.capabilities.isFlying) {
							p.capabilities.setFlySpeed(speed);
							p.addChatMessage("settd fly speed " + speed);
						} else {
							p.capabilities.setPlayerWalkSpeed(speed);
							p.addChatMessage("settd walk speed " + speed);
						}
					} else {
						float speed = Float.valueOf(astring[1]);
						if (astring[0].toLowerCase().startsWith("f")) {
							p.capabilities.setFlySpeed(speed);
							p.addChatMessage("setted fly speed " + speed);
						} else if (astring[0].toLowerCase().startsWith("w")) {
							p.capabilities.setPlayerWalkSpeed(speed);
							p.addChatMessage("setted walk speed " + speed);
						}
					}
				}
			} catch (Exception e) {
				p.addChatMessage("Usage: /speed ((fly/walk & speed value)/speed value)");
			}
		}
	}
	
	public List getCommandAliases() {return null;}
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {return true;}
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {return null;}
	public boolean isUsernameIndex(String[] astring, int i) {return false;}
	public int compareTo(Object arg0) {return 0;}
}
