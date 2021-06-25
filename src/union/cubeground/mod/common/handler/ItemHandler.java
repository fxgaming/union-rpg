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
        addName(itemRPG.itemID, 0, "Карта");
        addName(itemRPG.itemID, 1, "Тетрадь");
        addName(itemRPG.itemID, 2, "Часы");
        addName(itemRPG.itemID, 3, "Клевер");
        addName(itemRPG.itemID, 4, "Алмаз");
        addName(itemRPG.itemID, 5, "Полотно");
        addName(itemRPG.itemID, 6, "Перо");
        addName(itemRPG.itemID, 7, "Лист");
        addName(itemRPG.itemID, 8, "Кость");
        addName(itemRPG.itemID, 9, "Книга морей");
        addName(itemRPG.itemID, 10, "Книга космоса");
        addName(itemRPG.itemID, 11, "Книга расписаний");
        addName(itemRPG.itemID, 12, "Книга исскуств");
        addName(itemRPG.itemID, 13, "Некромоникон");
        addName(itemRPG.itemID, 14, "Книга крови");
        addName(itemRPG.itemID, 15, "Книга секретов");
        addName(itemRPG.itemID, 16, "Книга силуэтов");
        addName(itemRPG.itemID, 17, "Книга");
        addName(itemRPG.itemID, 18, "Книга путеводов");
        addName(itemRPG.itemID, 19, "Книга карт");
        addName(itemRPG.itemID, 20, "Книга X");
        addName(itemRPG.itemID, 21, "Книга древностей");
        addName(itemRPG.itemID, 22, "Книга воспоминаний");
        addName(itemRPG.itemID, 23, "Книга боевых исскуств");
        addName(itemRPG.itemID, 24, "Песчаник с гравировкой");
        addName(itemRPG.itemID, 25, "Книга шифров");
        addName(itemRPG.itemID, 26, "Каменная плита с гравировкой");
        addName(itemRPG.itemID, 27, "Ткань");
        addName(itemRPG.itemID, 28, "???");
        addName(itemRPG.itemID, 29, "Бронзовая плита");
        addName(itemRPG.itemID, 30, "Телескоп");
        addName(itemRPG.itemID, 31, "Глаз");
        addName(itemRPG.itemID, 32, "Лягушачья ножка");
        addName(itemRPG.itemID, 33, "Краска");
        addName(itemRPG.itemID, 34, "Золотой слиток");
        addName(itemRPG.itemID, 35, "Оловяный слиток");
        addName(itemRPG.itemID, 36, "Синий кристалл");
        addName(itemRPG.itemID, 37, "Красный кристалл");
        addName(itemRPG.itemID, 38, "Чёрный кристалл");
        addName(itemRPG.itemID, 39, "Книга");
        addName(itemRPG.itemID, 40, "Книга");
        addName(itemRPG.itemID, 41, "Книга");
        addName(itemRPG.itemID, 42, "Книга");
        addName(itemRPG.itemID, 43, "Книга");
        addName(itemRPG.itemID, 44, "Книга");
        addName(itemRPG.itemID, 45, "Книга");
        addName(itemRPG.itemID, 46, "???");
        addName(itemRPG.itemID, 47, "???");
        GameRegistry.registerItem(itemKey = new ItemMulti(1009, 7, "keyItem"), "keyItem");
        addName(itemKey.itemID, 0, "Бронзовый ключ");
        addName(itemKey.itemID, 1, "Железный ключ");
        addName(itemKey.itemID, 2, "Золотой ключ");
        addName(itemKey.itemID, 3, "Деревянный ключ");
        addName(itemKey.itemID, 4, "Алмазный ключ");
        addName(itemKey.itemID, 5, "Каменный ключ");
        addName(itemKey.itemID, 6, "??? ключ");
    }
    
    private static void addName(Object obj, String name) {
    	LanguageRegistry.addName(obj, name);
    }
    
    private static void addName(int itemid, int itemdamage, String name) {
    	LanguageRegistry.addName(new ItemStack(itemid, 1, itemdamage), name);
    }
}
