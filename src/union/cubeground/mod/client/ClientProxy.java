package union.cubeground.mod.client;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import api.player.model.ModelPlayerAPI;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import union.cubeground.mod.AMod;
import union.cubeground.mod.client.handler.KeyLoader;
import union.cubeground.mod.client.handler.OverlayHandler;
import union.cubeground.mod.client.handler.PlayerModelHandler;
import union.cubeground.mod.client.models.accessories.ModelKNYBox;
import union.cubeground.mod.client.render.item.RenderAccessoriesItem;
import union.cubeground.mod.client.render.item.RenderBArmor;
import union.cubeground.mod.client.render.item.RenderBAxe;
import union.cubeground.mod.client.render.item.RenderBSword;
import union.cubeground.mod.client.render.item.RenderChaotic;
import union.cubeground.mod.client.render.tilerender.TileBlockBarrierRender;
import union.cubeground.mod.common.CommonProxy;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.tile.TileBlockBarrier;
import union.cubeground.mod.common.utils.ModelSpec;

public class ClientProxy extends CommonProxy {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static RenderItem renderItem = new RenderItem();
    public static List<ModelSpec> accessoriesModels = new ArrayList<ModelSpec>();
    public static MethodHandle canRenderName;
    public OverlayHandler overlayHandler;

    public void init() {
        Display.setTitle(AMod.MODID);
        KeyBindingRegistry.registerKeyBinding(new KeyLoader());
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new OverlayHandler());
        ModelPlayerAPI.register("AMod", PlayerModelHandler.class);

        //предметы
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemSword.itemID, new RenderBSword());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemAxe.itemID, new RenderBAxe());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemHelmet.itemID, new RenderBArmor());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemChestplate.itemID, new RenderBArmor());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemLeggins.itemID, new RenderBArmor());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemBoots.itemID, new RenderBArmor());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemChaotic.itemID, new RenderChaotic());
        MinecraftForgeClient.registerItemRenderer(ItemHandler.itemAccessories.itemID, new RenderAccessoriesItem());
        accessoriesModels.add(new ModelKNYBox());


        //тайлы
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockBarrier.class, new TileBlockBarrierRender());
    }

    static {
        try {
            Method m = ReflectionHelper.findMethod(RendererLivingEntity.class, null, new String[]{"canRenderName", "func_110813_b"}, new Class[]{EntityLivingBase.class});
            canRenderName = MethodHandles.publicLookup().unreflect(m);
        } catch (IllegalAccessException var1) {
            throw new RuntimeException(var1);
        }
    }
}
