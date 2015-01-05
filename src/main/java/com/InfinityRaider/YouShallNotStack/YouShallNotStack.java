package com.InfinityRaider.YouShallNotStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mod(modid = YouShallNotStack.modId, name = YouShallNotStack.modId,version = "1.1")
public class YouShallNotStack {
    public static final String modId = "YouShallNotStack";
    public static String[] rawData;

    @Mod.Instance(modId)
    public static YouShallNotStack instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
       //Nope
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        for (int i = 0; i < rawData.length; i++) {
            String data[] = IOHelper.getData(rawData[i]);
            Item item = (Item) Item.itemRegistry.getObject(data[0]);
            Block block = (Block) Block.blockRegistry.getObject(data[0]);
            int size = Integer.parseInt(data[1]) <= 0 ? 0 : Integer.parseInt(data[1]) >= 64 ? 64 : Integer.parseInt(data[1]);
            if (item != null) {
                item.setMaxStackSize(size);
                LogHelper.info("Setting stacksize of " + Item.itemRegistry.getNameForObject(item) + " to " + size);
            } else if (block != null) {
                Item.getItemFromBlock(block).setMaxStackSize(size);
                LogHelper.info("Setting stacksize of " + Block.blockRegistry.getNameForObject(block) + " to " + size);
            } else {
                LogHelper.info("Something went wrong on line " + i + ": " + data[0] + "," + data[1]);
            }
        }
        rawData = null;
    }

}
