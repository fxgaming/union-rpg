package union.cubeground.mod.common.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import union.cubeground.mod.common.items.ItemAccessories;
import union.cubeground.mod.common.items.ItemBArmor;
import union.cubeground.mod.common.items.ItemBAxe;
import union.cubeground.mod.common.items.ItemBSword;
import union.cubeground.mod.common.items.ItemChaotic;
import union.cubeground.mod.common.items.ItemMulti;

public class ItemHandler {
    public static CreativeTabs tabAccessories = new CreativeTabs("Accessories") {
        @SideOnly(Side.CLIENT) public Item getTabIconItem() { return Item.helmetLeather; }
    };
    public static CreativeTabs tabItems = new CreativeTabs("tabItems") {
        @SideOnly(Side.CLIENT) public Item getTabIconItem() { return Item.diamond; }
    };

    public static ItemAccessories itemAccessories;
    public static ItemChaotic itemChaotic;
    public static ItemBSword itemSword;
    public static ItemBAxe itemAxe;
    public static ItemBArmor itemHelmet;
    public static ItemBArmor itemChestplate;
    public static ItemBArmor itemLeggins;
    public static ItemBArmor itemBoots;
    public static ItemMulti itemRPG;
    public static ItemMulti itemKey;

    public static void init() {
        //ItemArtefacts.add(null);
        GameRegistry.registerItem(itemAccessories = new ItemAccessories(1000, 1), "itemAccessories");
        addName(itemAccessories.itemID, 0, "Kimetsu No Yaiba box");
        GameRegistry.registerItem(itemChaotic = new ItemChaotic(1001), "itemChaotic");
        GameRegistry.registerItem(itemSword = new ItemBSword(1002), "itemBSword");
        GameRegistry.registerItem(itemAxe = new ItemBAxe(1003), "itemBAxe");
        GameRegistry.registerItem(itemHelmet = new ItemBArmor(1004, 0), "itemHelmet");
        GameRegistry.registerItem(itemChestplate = new ItemBArmor(1005, 1), "itemChestplate");
        GameRegistry.registerItem(itemLeggins = new ItemBArmor(1006, 2), "itemLeggins");
        GameRegistry.registerItem(itemBoots = new ItemBArmor(1007, 3), "itemBoots");
        GameRegistry.registerItem(itemRPG = new ItemMulti(1008, 46, "rpgItem"), "rpgItem");
        addName(itemRPG.itemID, 0, "??????????");
        addName(itemRPG.itemID, 1, "??????????????");
        addName(itemRPG.itemID, 2, "????????");
        addName(itemRPG.itemID, 3, "????????????");
        addName(itemRPG.itemID, 4, "??????????");
        addName(itemRPG.itemID, 5, "??????????????");
        addName(itemRPG.itemID, 6, "????????");
        addName(itemRPG.itemID, 7, "????????");
        addName(itemRPG.itemID, 8, "??????????");
        addName(itemRPG.itemID, 9, "?????????? ??????????");
        addName(itemRPG.itemID, 10, "?????????? ??????????????");
        addName(itemRPG.itemID, 11, "?????????? ????????????????????");
        addName(itemRPG.itemID, 12, "?????????? ????????????????");
        addName(itemRPG.itemID, 13, "????????????????????????");
        addName(itemRPG.itemID, 14, "?????????? ??????????");
        addName(itemRPG.itemID, 15, "?????????? ????????????????");
        addName(itemRPG.itemID, 16, "?????????? ????????????????");
        addName(itemRPG.itemID, 17, "??????????");
        addName(itemRPG.itemID, 18, "?????????? ??????????????????");
        addName(itemRPG.itemID, 19, "?????????? ????????");
        addName(itemRPG.itemID, 20, "?????????? X");
        addName(itemRPG.itemID, 21, "?????????? ????????????????????");
        addName(itemRPG.itemID, 22, "?????????? ????????????????????????");
        addName(itemRPG.itemID, 23, "?????????? ???????????? ????????????????");
        addName(itemRPG.itemID, 24, "???????????????? ?? ??????????????????????");
        addName(itemRPG.itemID, 25, "?????????? ????????????");
        addName(itemRPG.itemID, 26, "???????????????? ?????????? ?? ??????????????????????");
        addName(itemRPG.itemID, 27, "??????????");
        addName(itemRPG.itemID, 28, "???");
        addName(itemRPG.itemID, 29, "?????????????????? ??????????");
        addName(itemRPG.itemID, 30, "????????????????");
        addName(itemRPG.itemID, 31, "????????");
        addName(itemRPG.itemID, 32, "?????????????????? ??????????");
        addName(itemRPG.itemID, 33, "????????????");
        addName(itemRPG.itemID, 34, "?????????????? ????????????");
        addName(itemRPG.itemID, 35, "???????????????? ????????????");
        addName(itemRPG.itemID, 36, "?????????? ????????????????");
        addName(itemRPG.itemID, 37, "?????????????? ????????????????");
        addName(itemRPG.itemID, 38, "???????????? ????????????????");
        addName(itemRPG.itemID, 39, "??????????");
        addName(itemRPG.itemID, 40, "??????????");
        addName(itemRPG.itemID, 41, "??????????");
        addName(itemRPG.itemID, 42, "??????????");
        addName(itemRPG.itemID, 43, "??????????");
        addName(itemRPG.itemID, 44, "??????????");
        addName(itemRPG.itemID, 45, "??????????");
        addName(itemRPG.itemID, 46, "???");
        addName(itemRPG.itemID, 47, "???");
        GameRegistry.registerItem(itemKey = new ItemMulti(1009, 7, "keyItem"), "keyItem");
        addName(itemKey.itemID, 0, "?????????????????? ????????");
        addName(itemKey.itemID, 1, "???????????????? ????????");
        addName(itemKey.itemID, 2, "?????????????? ????????");
        addName(itemKey.itemID, 3, "???????????????????? ????????");
        addName(itemKey.itemID, 4, "???????????????? ????????");
        addName(itemKey.itemID, 5, "???????????????? ????????");
        addName(itemKey.itemID, 6, "??? ????????");
    }
    
    private static void addName(Object obj, String name) {
    	LanguageRegistry.addName(obj, name);
    }
    
    private static void addName(int itemid, int itemdamage, String name) {
    	LanguageRegistry.addName(new ItemStack(itemid, 1, itemdamage), name);
    }
}
