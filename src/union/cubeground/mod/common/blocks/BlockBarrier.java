package union.cubeground.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import union.cubeground.mod.common.tile.TileBlockBarrier;

public class BlockBarrier extends Block implements ITileEntityProvider {
    public BlockBarrier(int id) {
        super(id, Material.grass);
        this.setTextureName("barrier");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public int getRenderType() {
        return -1;
    }

    public TileEntity createNewTileEntity(World w, int i) {
        return new TileBlockBarrier();
    }

	public TileEntity createNewTileEntity(World world) {
		return new TileBlockBarrier();
	}
}
