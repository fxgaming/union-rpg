package union.cubeground.mod.client.handler;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.settings.KeyBinding;

public class KeyLoader extends KeyHandler {
	//public static Key chaotics[] = new Key[5];
	public static Key cherryPedia;
	public static KeyBinding[] keys = {
//			chaotics[0] = new Key("Активировать хаотик 1", Keyboard.KEY_Z), 
//			chaotics[1] = new Key("Активировать хаотик 2", Keyboard.KEY_X), 
//			chaotics[2] = new Key("Активировать хаотик 3", Keyboard.KEY_C), 
//			chaotics[3] = new Key("Активировать хаотик 4", Keyboard.KEY_V), 
//			chaotics[4] = new Key("Активировать хаотик 5", Keyboard.KEY_B),
			cherryPedia = new Key("Черрипедия", 25)
			};
	public static boolean[] keysbool = {false, false, false, false, false};

	int keyUp = 0;
	int keyDown = 0;

	public KeyLoader() {
		super(keys, keysbool);
	}

	public String getLabel() {
		return "viod.keyboardclient";
	}

	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (kb.pressed) {
			kb.pressTime++;
		} else {
			kb.pressed = true;
		}
		if (isRepeat && kb.pressTime > 0) {
			kb.pressTime = 0;
		}
	}

	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (kb.pressed) {
			kb.pressed = false;
			kb.pressTime = 0;
		} else if (kb.pressTime > 0) {
			kb.pressTime = 0;
		}
	}
	public EnumSet ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

    public static class Key extends KeyBinding {
        public Key(String par1Str, int par2) {
            super(par1Str, par2);
        }

        public String getKeyButton() {
            try {
                String name = Keyboard.getKeyName(this.keyCode);
                if (name.length() > 1) {
                    if (name.equals("SLASH")) return "/";
                    if (name.equals("BACKSLASH")) return "\\";
                    if (name.equals("COMMA")) return ",";
                    if (name.equals("PERIOD")) return ".";
                    if (name.equals("SEMICOLON")) return ";";
                    if (name.equals("APOSTROPHE")) return "\'";
                    if (name.equals("LBRACKET")) return "[";
                    if (name.equals("RBRACKET")) return "]";
                    if (name.equals("MINUS")) return "-";
                    if (name.equals("EQUALS")) return "=";
                    if (name.equals("BACK")) return "←";
                    if (name.equals("ENTER")) return "Ret";
                    if (name.equals("RMENU")) return "RAlt";
                    if (name.equals("LMENU")) return "LAlt";
                    if (name.equals("RSHIFT")) return "R↑";
                    if (name.equals("LSHIFT")) return "L↑";
                    if (name.equals("RCONTROL")) return "RCtrl";
                    if (name.equals("LCONTROL")) return "LCtrl";
                    if (name.equals("RMETA")) return "RWin";
                    if (name.equals("LMETA")) return "LWin";
                    if (name.equals("RIGHT")) return "►";
                    if (name.equals("LEFT")) return "◄";
                    if (name.equals("UP")) return "▲";
                    if (name.equals("DOWN")) return "▼";
                    if (name.equals("RETURN")) return "NRet";
                    if (name.equals("ADD")) return "NUM+";
                    if (name.equals("DECIMAL")) return "NUM.";
                    if (name.equals("NUMPAD0")) return "NUM0";
                    if (name.equals("NUMPAD1")) return "NUM1";
                    if (name.equals("NUMPAD2")) return "NUM2";
                    if (name.equals("NUMPAD3")) return "NUM3";
                    if (name.equals("NUMPAD4")) return "NUM4";
                    if (name.equals("NUMPAD5")) return "NUM5";
                    if (name.equals("NUMPAD6")) return "NUM6";
                    if (name.equals("NUMPAD7")) return "NUM7";
                    if (name.equals("NUMPAD8")) return "NUM8";
                    if (name.equals("NUMPAD9")) return "NUM9";
                    if (name.equals("SUBTRACT")) return "NUM-";
                    if (name.equals("MULTIPLY")) return "NUM*";
                    if (name.equals("DIVIDE")) return "NUM/";
                    if (name.equals("GRAVE")) return "`";
                    if (name.equals("TAB")) return "TAB";
                    if (name.equals("CAPITAL")) return "Caps";
                    if (name.equals("INSERT")) return "Ins";
                    if (name.equals("HOME")) return "Home";
                    if (name.equals("PRIOR")) return "PgUp";
                    if (name.equals("DELETE")) return "Del";
                    if (name.equals("END")) return "End";
                    if (name.equals("NEXT")) return "PgDn";
                    if (name.equals("SYSRQ")) return "PtSc";
                    if (name.equals("PAUSE")) return "Pau";
                    if (name.equals("SCROLL")) return "ScLk";
                    if (name.startsWith("BUTTON")) return name.replace("BUTTON", "B");
                    return name;
                } else {
                    return name;
                }
            } catch (Exception e) {
                return "@";
            }
        }
    }
}
