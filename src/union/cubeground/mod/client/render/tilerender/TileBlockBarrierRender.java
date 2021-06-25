package union.cubeground.mod.client.render.tilerender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import union.cubeground.mod.common.tile.TileBlockBarrier;

public class TileBlockBarrierRender extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float t) {
        this.render((TileBlockBarrier) tile, x, y, z, t);
    }

    private void render(TileBlockBarrier tile, double x, double y, double z, float t) {
        if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
            Vec3 vec = Vec3.createVectorHelper(tile.xCoord, tile.yCoord, tile.zCoord);
            if (Minecraft.getMinecraft().thePlayer.getPosition(1.0F).squareDistanceTo(vec) < 16.0D) {
                Minecraft.getMinecraft().theWorld.spawnParticle("smoke", tile.xCoord + 0.5D, tile.yCoord + 0.5D, tile.zCoord + 0.5D, 0.0D, -0.05D, 0.0D);
            }
        }
    }
}

