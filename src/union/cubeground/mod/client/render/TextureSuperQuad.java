package union.cubeground.mod.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class TextureSuperQuad {
	public PositionTextureVertex[] vertexPositions;
	public int nVertices;
	private boolean invertNormal;

	public TextureSuperQuad(PositionTextureVertex[] par1ArrayOfPositionTextureVertex) {
		this.vertexPositions = par1ArrayOfPositionTextureVertex;
		this.nVertices = par1ArrayOfPositionTextureVertex.length;
	}

	public TextureSuperQuad(PositionTextureVertex[] par1ArrayOfPositionTextureVertex, int par2, float par3, float par4, float par5, float par6, float par7) {
		this(par1ArrayOfPositionTextureVertex);
		float f2 = 0.0F / par6;
		float f3 = 0.0F / par7;
		par1ArrayOfPositionTextureVertex[0] = par1ArrayOfPositionTextureVertex[0].setTexturePosition((float) par4 / par6 - f2, (float) par3 / par7 + f3);
		par1ArrayOfPositionTextureVertex[1] = par1ArrayOfPositionTextureVertex[1].setTexturePosition((float) par2 / par6 + f2, (float) par3 / par7 + f3);
		par1ArrayOfPositionTextureVertex[2] = par1ArrayOfPositionTextureVertex[2].setTexturePosition((float) par2 / par6 + f2, (float) par5 / par7 - f3);
		par1ArrayOfPositionTextureVertex[3] = par1ArrayOfPositionTextureVertex[3].setTexturePosition((float) par4 / par6 - f2, (float) par5 / par7 - f3);
	}

	public void flipFace() {
		PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.vertexPositions.length];

		for (int i = 0; i < this.vertexPositions.length; ++i) {
			apositiontexturevertex[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
		}

		this.vertexPositions = apositiontexturevertex;
	}

	public void draw(Tessellator par1Tessellator, float par2) {
		Vec3 vec3 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[0].vector3D);
		Vec3 vec31 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[2].vector3D);
		Vec3 vec32 = vec31.crossProduct(vec3).normalize();
		par1Tessellator.startDrawingQuads();

		if (this.invertNormal) {
			par1Tessellator.setNormal(-((float) vec32.xCoord), -((float) vec32.yCoord), -((float) vec32.zCoord));
		} else {
			par1Tessellator.setNormal((float) vec32.xCoord, (float) vec32.yCoord, (float) vec32.zCoord);
		}

		for (int i = 0; i < 4; ++i) {
			PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
			par1Tessellator.addVertexWithUV((double) ((float) positiontexturevertex.vector3D.xCoord * par2), (double) ((float) positiontexturevertex.vector3D.yCoord * par2), (double) ((float) positiontexturevertex.vector3D.zCoord * par2), (double) positiontexturevertex.texturePositionX, (double) positiontexturevertex.texturePositionY);
		}

		par1Tessellator.draw();
	}
}
