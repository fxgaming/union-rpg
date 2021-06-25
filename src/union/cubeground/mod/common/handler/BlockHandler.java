package union.cubeground.mod.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemStack;
import union.cubeground.mod.common.blocks.BlockBarrier;
import union.cubeground.mod.common.blocks.BlockTexture;
import union.cubeground.mod.common.tile.TileBlockBarrier;

public class BlockHandler {
    public static BlockBarrier blockBarrier = new BlockBarrier(750);
    public static BlockTexture[] blockTexture = new BlockTexture[16];
    
    public static void init() {
        GameRegistry.registerBlock(blockBarrier, "blockBarrier");
        GameRegistry.registerTileEntity(TileBlockBarrier.class, "blockBarrier");
        LanguageRegistry.addName(blockBarrier, "Барьер");
        for (int i = 0; i != blockTexture.length; i++) {
        	GameRegistry.registerBlock(blockTexture[i] = new BlockTexture(751 + i, i), "blockTexture" + i);
        	LanguageRegistry.addName(blockTexture[i], "Блок " + (i + 1));
        }
    }
}
