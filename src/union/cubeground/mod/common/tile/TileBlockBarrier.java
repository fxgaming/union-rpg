package union.cubeground.mod.common.tile;

import net.minecraft.tileentity.TileEntity;

//пустой блять необновляемый тайл
public class TileBlockBarrier extends TileEntity {
    public boolean canUpdate()
    {
        return false;
    }
}