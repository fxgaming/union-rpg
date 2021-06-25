package union.cubeground.mod.common;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.entity.player.EntityPlayer;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.player.ExtendedPlayer;

public class CommonTickHandler implements ITickHandler {
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (AMod.isServer) {
			if (tickData.length > 0 && tickData[0] != null && tickData[0] instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)tickData[0];
				ExtendedPlayer ep = ExtendedPlayer.get(player);
	            ep.player.getFoodStats().addStats(10, 17.5F);
	            ep.tick++;
	            if (ep.tick % 20 == 0) {
	                ep.tick = 0;
	                ExtendedPlayer.Stats s = ep.stats;
	                int hp = s.hp;
	                int mana = s.mana;
	                if (s.hp < s.maxHP && (s.maxHP / 200) < 1) hp++;
	                else if (s.hp < s.maxHP && (s.maxHP / 200) >= 1) hp += (s.maxHP / 200);
	                ep.stats.hp = hp;
	                if (mana < s.maxMana && (s.maxMana / 100) < 1) mana++;
	                else if (s.mana < s.maxMana && (s.maxMana / 100) >= 1) mana += (s.maxMana / 100);
	                ep.stats.mana = mana;
	            }
			}
		}
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER, TickType.SERVER);
	}

	public String getLabel() {
		return "CTH";
	}
}
