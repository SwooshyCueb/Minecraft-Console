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
import java.util.Collections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.sijobe.console.GuiConsole;
import com.vayner.console.guiapi.ConsoleSettings;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod
(
	modid						= MCConsole.MODID,
	name						= MCConsole.MODNAME,
	version						= MCConsole.VERSION,
	acceptedMinecraftVersions	= "[1.7.10,1.8,1.8.0,1.8.1,)",
	dependencies				= "required-after:Forge@[10.13.2.1240,)",
	canBeDeactivated 			= false // We'll change this to true once we know how to handle it
)

public class MCConsole {
	
	public static final String MODID = "MCConsole";
    public static final String MODNAME = "Ingame Console";
    public static final String VERSION = "1.3.6.2-ors0";
   
    public static KeyBinding openKey;
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {    	
    	// Keybinding
    	openKey = new KeyBinding("Console", Keyboard.KEY_BACKSLASH, "key.categories.multiplayer");
    	ClientRegistry.registerKeyBinding(openKey);
    	
    }
    
   private static boolean guiApiInstalled = false;
   private static boolean SPCInstalled = false;
   private static boolean MCP = false;
   
   public mod_Console() {
      GuiConsole.getInstance();
      openKey = new KeyBinding("Console", Keyboard.KEY_BACKSLASH);
      ModLoader.registerKey(this, openKey, false);
   }
   
   @Override
   public void keyboardEvent(KeyBinding event) {
      if(event.keyCode == openKey.keyCode)
         openConsole();
   }
   
   @Override
   public void load() {
      String playername = ModLoader.getMinecraftInstance().session.username;
      GuiConsole.getInstance();
      if(playername.equals("MCPTEST")) {
         System.out.println("[MCC] Username is MCPTEST, assuming running via eclipse with all prerequisits installed");
         SPCInstalled = true;
         guiApiInstalled = true;
         MCP = true;
         return;
      } else if (playername.equals("GUIAPI")) {
         System.out.println("[MCC] Username is GUIAPI, assuming running via eclipse with ModLoader and GuiApi installed");
         SPCInstalled = false;
         guiApiInstalled = true;
         MCP = true;
         return;
      } else if (playername.equals("MODLOADER")) {
         System.out.println("[MCC] Username is MODLOADER, assuming running via eclipse with only ModLoader installed");
         SPCInstalled = false;
         guiApiInstalled = false;
         MCP = true;
         return;
      }
      
      try {
         Class helper = Class.forName("PlayerHelper");
         SPCInstalled = true;
      } catch (ClassNotFoundException e) {
         System.out.println("[MCC] Single Player Commands 'PlayerHelper.class' not found, unable to retrive commands for SPC");
         SPCInstalled = false;
      }
      
      try {
         Class test = Class.forName("ModSettings");
         guiApiInstalled = true;
      } catch (ClassNotFoundException e) {
         System.out.println("[MCC] GuiApi not installed, settings adjustment ingame will not be avaiable");
         guiApiInstalled = false;
      }
   }
   
   /**
    * Passes on messages sent by the client
    */
   @Override
   public void clientChat(String var1) {
      GuiConsole.getInstance().addClientMessage(var1);
	}
   
   /**
    * Passes on messages received from the server
    */
   @Override
   public void serverChat(NetServerHandler var1, String var2) {
      GuiConsole.getInstance().addServerMessage(var1, var2);
   }
   
   public static void openConsole() {
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
   }
   
   public void modsLoaded() {
      if(guiApiInstalled)
         ConsoleSettings.init();
   }
   
   public static boolean GuiApiInstalled() {
      return guiApiInstalled;
   }
   
   public static boolean SPCInstalled() {
      return SPCInstalled;
   }
   
   public static boolean MCPtesting() {
      return MCP;
   }
   
   public String getVersion() {
      return GuiConsole.VERSION;
   }      
}