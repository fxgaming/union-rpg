package by.fxg.garbageapi;

import org.lwjgl.opengl.GL11;

public class GL {
	public static void pushBlend() {
		GL11.glEnable(GL11.GL_BLEND);
	}
	
	public static void pushBlend(int sfactor, int dfactor) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(sfactor, dfactor);
	}
	
	public static void pushBlendAlpha() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
	}
	
	public static void blendAlpha() {
		GL11.glBlendFunc(770, 771);
	}
	
	public static void popBlend() {
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void pushMatrix() {
		GL11.glPushMatrix();
	}
	
	public static void popMatrix() {
		GL11.glPopMatrix();
	}
}
