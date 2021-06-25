package union.cubeground.mod.common.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import union.cubeground.mod.common.tile.TileBlockBarrier;

public class BlockTexture extends Block {
	public BlockTexture(int id, int subID) {
		super(id, Material.ground);
		this.setTextureName("cubeground:block" + subID);
		this.setUnlocalizedName("blockTexture" + subID);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
