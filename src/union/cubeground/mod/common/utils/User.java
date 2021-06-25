package union.cubeground.mod.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.player.ExtendedPlayer;

public class User {
	public static Triple<ExtendedPlayer, Integer, Integer> setGold(EntityPlayer p, int gold) {
		if (AMod.isServer) {
			ExtendedPlayer ep = ExtendedPlayer.get(p);
			ep.gold = gold;
			return new Triple<ExtendedPlayer, Integer, Integer>(ep, ep.gold, gold);
		}
		return null;
	}
	
	public static Triple<ExtendedPlayer, Integer, Integer> increaseGold(EntityPlayer p, int gold) {
		if (AMod.isServer) {
			ExtendedPlayer ep = ExtendedPlayer.get(p);
			ep.gold = ep.gold + gold;
			return new Triple<ExtendedPlayer, Integer, Integer>(ep, ep.gold, gold);
		}
		return null;
	}
	
	
	public static Triple<ExtendedPlayer, Integer, Integer> decreaseGold(EntityPlayer p, int gold) {
		if (AMod.isServer) {
			ExtendedPlayer ep = ExtendedPlayer.get(p);
			ep.gold = ep.gold - gold;
			return new Triple<ExtendedPlayer, Integer, Integer>(ep, ep.gold, gold);
		}
		return null;
	}
	
	
	public static Pair<ExtendedPlayer, Integer> getGold(EntityPlayer p) {
		if (AMod.isServer) {
			ExtendedPlayer ep = ExtendedPlayer.get(p);
			return new Pair<ExtendedPlayer, Integer>(ep, ep.gold);
		}
		return null;
	}
}
