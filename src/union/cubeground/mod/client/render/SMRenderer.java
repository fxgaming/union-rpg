package union.cubeground.mod.client.render;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

public class SMRenderer {
	public float textureWidth;
	public float textureHeight;
	private int textureOffsetX;
	private int textureOffsetY;
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	private boolean compiled;
	private int displayList;
	public boolean mirror;
	public boolean showModel;
	public boolean isHidden;
	public List cubeList;
	public List childModels;
	public final String boxName;
	private ModelBase baseModel;
	public float offsetX;
	public float offsetY;
	public float offsetZ;

	public SMRenderer(ModelBase par1ModelBase, String par2Str) {
		this.textureWidth = 64.0F;
		this.textureHeight = 32.0F;
		this.showModel = true;
		this.cubeList = new ArrayList();
		this.baseModel = par1ModelBase;
		par1ModelBase.boxList.add(this);
		this.boxName = par2Str;
		this.setTextureSize(par1ModelBase.textureWidth, par1ModelBase.textureHeight);
	}

	public SMRenderer(ModelBase par1ModelBase) {
		this(par1ModelBase, (String) null);
	}

	public SMRenderer(ModelBase par1ModelBase, int par2, int par3) {
		this(par1ModelBase);
		this.setTextureOffset(par2, par3);
	}

	public void addChild(SMRenderer par1SMRenderer) {
		if (this.childModels == null) {
			this.childModels = new ArrayList();
		}

		this.childModels.add(par1SMRenderer);
	}

	public SMRenderer setTextureOffset(int par1, int par2) {
		this.textureOffsetX = par1;
		this.textureOffsetY = par2;
		return this;
	}

	public SMRenderer addBox(String par1Str, float par2, float par3, float par4, float par5, float par6, float par7) {
		par1Str = this.boxName + "." + par1Str;
		TextureOffset textureoffset = this.baseModel.getTextureOffset(par1Str);
		this.setTextureOffset(textureoffset.textureOffsetX, textureoffset.textureOffsetY);
		this.cubeList.add((new ModelSuperBox(this, this.textureOffsetX, this.textureOffsetY, par2, par3, par4, par5, par6, par7, 0.0F)).func_78244_a(par1Str));
		return this;
	}

	public SMRenderer addBox(float par1, float par2, float par3, float par4, float par5, float par6) {
		this.cubeList.add(new ModelSuperBox(this, this.textureOffsetX, this.textureOffsetY, par1, par2, par3, par4, par5, par6, 0.0F));
		return this;
	}

	public void addBox(float par1, float par2, float par3, int par4, float par5, float par6, float par7) {
		this.cubeList.add(new ModelSuperBox(this, this.textureOffsetX, this.textureOffsetY, par1, par2, par3, par4, par5, par6, par7));
	}

	public void setRotationPoint(float par1, float par2, float par3) {
		this.rotationPointX = par1;
		this.rotationPointY = par2;
		this.rotationPointZ = par3;
	}

	@SideOnly(Side.CLIENT)
	public void render(float par1) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(par1);
				}
				GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
				int i;
				if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
					if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
						GL11.glCallList(this.displayList);
						if (this.childModels != null) {
							for (i = 0; i < this.childModels.size(); ++i) {
								((SMRenderer) this.childModels.get(i)).render(par1);
							}
						}
					} else {
						GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
						GL11.glCallList(this.displayList);
						if (this.childModels != null) {
							for (i = 0; i < this.childModels.size(); ++i) {
								((SMRenderer) this.childModels.get(i)).render(par1);
							}
						}
						GL11.glTranslatef(-this.rotationPointX * par1, -this.rotationPointY * par1, -this.rotationPointZ * par1);
					}
				} else {
					GL11.glPushMatrix();
					GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
					if (this.rotateAngleZ != 0.0F) {
						GL11.glRotatef(this.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
					}
					if (this.rotateAngleY != 0.0F) {
						GL11.glRotatef(this.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
					}
					if (this.rotateAngleX != 0.0F) {
						GL11.glRotatef(this.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
					}
					GL11.glCallList(this.displayList);
					if (this.childModels != null) {
						for (i = 0; i < this.childModels.size(); ++i) {
							((SMRenderer) this.childModels.get(i)).render(par1);
						}
					}
					GL11.glPopMatrix();
				}
				GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void renderWithRotation(float par1) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(par1);
				}
				GL11.glPushMatrix();
				GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
				if (this.rotateAngleY != 0.0F) {
					GL11.glRotatef(this.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
				}
				if (this.rotateAngleX != 0.0F) {
					GL11.glRotatef(this.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
				}
				if (this.rotateAngleZ != 0.0F) {
					GL11.glRotatef(this.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
				}
				GL11.glCallList(this.displayList);
				GL11.glPopMatrix();
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void postRender(float par1) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					this.compileDisplayList(par1);
				}
				if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
					if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
						GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
					}
				} else {
					GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
					if (this.rotateAngleZ != 0.0F) {
						GL11.glRotatef(this.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
					}
					if (this.rotateAngleY != 0.0F) {
						GL11.glRotatef(this.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
					}
					if (this.rotateAngleX != 0.0F) {
						GL11.glRotatef(this.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private void compileDisplayList(float par1) {
		this.displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(this.displayList, GL11.GL_COMPILE);
		Tessellator tessellator = Tessellator.instance;
		for (int i = 0; i < this.cubeList.size(); ++i) ((ModelSuperBox)this.cubeList.get(i)).render(tessellator, par1);
		GL11.glEndList();
		this.compiled = true;
	}
	public SMRenderer setTextureSize(int par1, int par2) {
		this.textureWidth = (float) par1;
		this.textureHeight = (float) par2;
		return this;
	}
}
