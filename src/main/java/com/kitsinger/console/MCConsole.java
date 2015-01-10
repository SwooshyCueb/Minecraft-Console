package com.kitsinger.console;

/**
 * 
 * @author simo_415, Vayner, SwooshyCueb
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */
import java.util.Arrays;
import java.util.Collections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;
import org.apache.logging.log4j.Logger;

//import com.sijobe.console.GuiConsole;
//import com.vayner.console.guiapi.ConsoleSettings;
import com.kitsinger.console.cfg.ConfigHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod
(
	modid						= MCConsole.MODID,
	name						= MCConsole.MODNAME,
	version						= MCConsole.VERSION,
	acceptedMinecraftVersions	= "[1.7.10,1.8,1.8.0,1.8.1,)",
	dependencies				= "required-after:Forge@[10.13.2.1240,)",
	guiFactory 					= "com.kitsinger.console.cfg.ConfigGuiFactory",
	canBeDeactivated 			= false // We'll change this to true once we know how to handle it
)

public class MCConsole {
	
	public static final String MODID = "MCConsole";
    public static final String MODNAME = "Ingame Console";
    public static final String VERSION = "1.3.6.2-ors0";
    public static ModMetadata modMetadata;
   
    public static KeyBinding openKey;
    public static Logger log;
    
    @Mod.Instance("MCConsole")
    public static MCConsole instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	log = event.getModLog();
    	log.info("Starting Minecraft Console");
    	modMetadata = event.getModMetadata();
    	modMetadata.modId		= MODID;
    	modMetadata.name		= MODNAME;
    	modMetadata.version		= VERSION;
    	modMetadata.description	= "An ingame console.";
    	//modMetadata.url			= "";
    	//modMetadata.updateUrl	= "";
    	modMetadata.authorList	= Arrays.asList (new String[] { "simo_415", "Vayner", "tellefma", "SwooshyCueb" });
    	//modMetadata.credits		= "";
    	//modMetadata.logoFile    = "/logo.png";
    	
    	ConfigHandler.initConfig(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(instance);
    	
    	// Keybinding
    	openKey = new KeyBinding("Console", Keyboard.KEY_BACKSLASH, "key.categories.multiplayer");
    	ClientRegistry.registerKeyBinding(openKey);
    	
    }
   
   /*@Override
   public void keyboardEvent(KeyBinding event) {
      if(event.keyCode == openKey.keyCode)
         openConsole();
   } */
   
   /**
    * Passes on messages sent by the client
    */
   /* @Override
   public void clientChat(String var1) {
      GuiConsole.getInstance().addClientMessage(var1);
	} */
   
   /**
    * Passes on messages received from the server
    */
   /* @Override
   public void serverChat(NetServerHandler var1, String var2) {
      GuiConsole.getInstance().addServerMessage(var1, var2);
   } */
   
   /* public static void openConsole() {
      Minecraft game = ModLoader.getMinecraftInstance();
      if(game.currentScreen == null) {
         game.displayGuiScreen(GuiConsole.getInstance());
      }
   }
   
   public static void closeConsole() {
      Minecraft game = ModLoader.getMinecraftInstance();
      if(game.currentScreen.equals(GuiConsole.getInstance())) {
         game.displayGuiScreen(null);
      }
   } */
   
   public String getVersion() {
      return VERSION;
   }      
}