package union.cubeground.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import union.cubeground.mod.common.CommonProxy;
import union.cubeground.mod.common.CommonTickHandler;
import union.cubeground.mod.common.commands.CommandFly;
import union.cubeground.mod.common.commands.CommandSpeed;
import union.cubeground.mod.common.handler.BlockHandler;
import union.cubeground.mod.common.handler.EventHandler;
import union.cubeground.mod.common.handler.GuiHandler;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.handler.PacketHandler;

@Mod(modid = AMod.MODID, version = AMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"arpg"}, packetHandler = PacketHandler.class)
public class AMod {
    public static final boolean isServer = true;
    public static final String MODID = "RPGMod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static AMod INSTANCE;
    @SidedProxy(clientSide = "union.cubeground.mod.client.ClientProxy", serverSide = "union.cubeground.mod.common.CommonProxy")
    public static CommonProxy PROXY;
    public static EventHandler eventHandler;

    @Mod.EventHandler
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        INSTANCE = this;
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        ItemHandler.init();
        BlockHandler.init();
        PROXY.init();
    }
    
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent fmlServerStartingEvent) {
    	fmlServerStartingEvent.registerServerCommand(new CommandFly());
    	fmlServerStartingEvent.registerServerCommand(new CommandSpeed());
    }
}
