package com.cloudcc.terrariacraft;

/**
 * Created by Tobias on 07.08.2016.
 */
public class Reference
{

    public static final String MOD_ID = "terrariacraft";
    public static final String NAME = "Terrariacraft";
    public static final String VERSION = "0.2";
    public static final String ACCEPTED_VERSIONS = "[1.10.2]";

    public static final String CLIENT_PROXY_CLASS = "com.cloudcc.terrariacraft.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.cloudcc.terrariacraft.proxy.ServerProxy";

    public static enum TerrariacraftItems {
        //XXXX("unlocalizedName","registryName//.json")
        IRONCOALINGOT("ironcoalingot","ItemIronCoalIngot"),
        ADAMANTIT_DUST("adamantit_dust","ItemAdamantitDust"),
        ADAMANTIT_INGOT("adamantit_ingot","ItemAdamantitIngot"),
        MITHRIL_INGOT("mithril_ingot","ItemMithrilIngot"),
        MITHRIL_DUST("mithril_dust","ItemMithrilDust"),


        MITHRIL_SWORD("mithril_sword","ItemMithrilSword"),
        ADAMANTIT_SWORD("adamantit_sword","ItemAdamantitSword");


        private String unlocalizedName;
        private String registryName;

        TerrariacraftItems(String unlocalizedName, String registryName){
            this.unlocalizedName = unlocalizedName;
            this.registryName = registryName;
        }
        public String getUnlocalizedName(){
            return unlocalizedName;
        }

        public String getRegistryName(){
            return registryName;
        }
    }



    public static enum TerrariacraftBlocks {


        COW_HEAD("cowhead","BlockCowHead"),
        multi_block("multiblock","BlockMultiBlock"),


        YELLOW_FURNACE("yellow_furnace","yellow_furnace"),
        STORAGE_BLOCK("storage_block","storage_block"),
        COOKING_POT("cooking_pot","cooking_pot"),
        GRINDER("grinder","BlockGrinder"),
        JAR("jar","BlockJar");

        private String unlocalizedName;
        private String registryName;

        TerrariacraftBlocks(String unlocalizedName, String registryName){
            this.unlocalizedName = unlocalizedName;
            this.registryName = registryName;
        }
        public String getUnlocalizedName(){
            return unlocalizedName;
        }

        public String getRegistryName() {
            return registryName;
        }
    }

    public static enum TerrariacraftOres {
        ADAMANTIT_ORE("adamantit_ore", "BlockAdamantitOre"),
        MITHRIL_ORE("mithril_ore","BlockMithrilOre");

        private String unlocalizedName;
        private String registryName;

        TerrariacraftOres(String unlocalizedName, String registryName){
            this.unlocalizedName = unlocalizedName;
            this.registryName = registryName;
        }

        public String getUnlocalizedName() { return unlocalizedName; }

        public String getRegistryName() {
            return registryName;
        }
    }




}
