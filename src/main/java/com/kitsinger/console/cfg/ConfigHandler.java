package com.kitsinger.console.cfg;

/*
 * @author SwooshyCueb
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

import static cpw.mods.fml.client.config.ConfigGuiType.BOOLEAN;
import static cpw.mods.fml.client.config.ConfigGuiType.INTEGER;
import static cpw.mods.fml.client.config.ConfigGuiType.STRING;

import com.kitsinger.console.cfg.components.GuiEntry;
import com.kitsinger.console.cfg.components.GuiSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;

import com.kitsinger.console.MCConsole;

import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	public static Configuration config;
	public static List<IConfigElement> entryList;
	
	public static final String CATEGORY_GEN = "general";
	public static final String CATEGORY_GEN_langKey = "MCConsole.config.category.general";
	
   public static int CHAT_INPUT_LENGTH_MAX = 150;                   // Maximum input size on the console
   public static int CHAT_INPUT_LENGTH_SERVER_MAX = 100;            // Maximum server message size - splits the input to this length if it is longer
   public static int CHAT_INPUT_HISTORY_MAX = 50;                   // Maximum size of stored input history
   public static int CHAT_OUTPUT_MAX = 200;                              // Maximum number of lines in the output

   public static boolean CHAT_UNPASUE_PAUSE_WITH_MESSAGE = true;   // TODO
   public static boolean CHAT_PRINT_INPUT = true;                   // Prints the input
   public static boolean CHAT_PRINT_OUTPUT = true;                  // Prints the output
   public static String CHAT_INPUT_PREFIX = "> ";                   // Prefix for all input messages
   
   public static boolean CLOSE_ON_SUBMIT;
   public static final boolean CLOSE_ON_SUBMIT_DEFAULT = false;                   // Closes the GUI after the input has been submit
   public static boolean SCROLL_TO_BOTTOM_ON_SUBMIT;
   public static final boolean SCROLL_TO_BOTTOM_ON_SUBMIT_DEFAULT = true;         // Moves the scroll bar to the bottom when input is sumbitted
   public static boolean CLOSE_WITH_OPEN_KEY;
   public static final boolean CLOSE_WITH_OPEN_KEY_DEFAULT = true;                // Closes the GUI if the open key pressed again
   
    public static final String CLOSE_ON_SUBMIT_langKey = "MCConsole.config.general.submitclose";
	public static final String SCROLL_TO_BOTTOM_ON_SUBMIT_langKey = "MCConsole.config.general.submitscroll";
	public static final String CLOSE_WITH_OPEN_KEY_langKey = "MCConsole.config.general.opencloses";

   public static final byte LOGGING_TRACE  = 8;                      // Logging level - Trace
   public static final byte LOGGING_DEBUG  = 4;                      // Logging level - Debug
   public static final byte LOGGING_INPUT  = 2;                      // Logging level - Input
   public static final byte LOGGING_OUTPUT = 1;                      // Logging level - Output
   public static int LOGGING = LOGGING_INPUT + LOGGING_OUTPUT;      // What is currently being logged
   public static long LOG_WRITE_INTERVAL = 1000L;                   // How often (in ms) the logs are written to file

   // The log line separator
   public static final String LINE_BREAK = System.getProperty("line.separator");
   public static final Pattern VALID_MESSAGE = Pattern.compile("\\S");
   
   public static String DATE_FORMAT_LOG = "yyyy-MM-dd hh:mm:ss: ";  // The date format according to SimpleDateFormat
   // The date format filename (uses SimpleDateFormat)
   public static String DATE_FORMAT_FILENAME = "yyyyMMdd_hhmmss'.log'";

   public static long POLL_DELAY = 20L;                             // The amount of time (in ms) to run the thread at

   public static int SCREEN_LINES_PER_SCROLL = 1;                          // The number of lines to scroll in one scroll wheel click

   public static int SCREEN_BORDERSIZE = 2;                         // Size of the border
   public static int SCREEN_PADDING_LEFT = 5;                       // Size of the screen padding - left
   public static int SCREEN_PADDING_TOP = 12;                       // Size of the screen padding - top
   public static int SCREEN_PADDING_RIGHT = 5;                      // Size of the screen padding - right
   public static int SCREEN_PADDING_BOTTOM = 40;                    // Size of the screen padding - bottom

   public static boolean SCREEN_MESSAGE_LENGHT_DISPLAY = true;      // Turn on or off showing chars left for 1 message
   public static boolean SCREEN_AUTOPREVIEW = true;                 // Turn on or off previewing matched words
   public static int SCREEN_AUTOPREVIEWAREA = 140;                  // width of preview area
   
   public static int COLOR_BASE = 0x90000000;                       // Base colour to use for console
   public static int COLOR_SCROLL_BACKGROUND = 0xBB999999;          // Scroll background colour
   public static int COLOR_SCROLL_FOREGROUND = 0xBB404040;          // Scroll foreground colour
   public static int COLOR_INPUT_TEXT = 0xFFE0E0E0;                 // Colour of the input text
   public static int COLOR_TEXT_OUTPUT = 0xFFE0E0E0;                // Colour of the text output
   public static int COLOR_TEXT_TITLE = 0xFFE0E0E0;                 // Colour of the text title
   public static int COLOR_TEXT_HIGHLIGHT = 0xFF2090DD;             // Colour of the text highlighting
   public static int COLOR_SCROLL_ARROW = 0xFFFFFFFF;               // Colour of the scroll arrow
   public static int COLOR_EXIT_BUTTON_TEXT = 0xFFFFFFFF;           // Colour of the exit button label
   public static int COLOR_EXIT_BUTTON = 0xBB999999;                // Colour of the exit button
   public static int COLOR_OUTPUT_BACKGROUND = 0xBB999999;          // Colour of the output background
   public static int COLOR_INPUT_BACKGROUND = 0xBB999999;           // Colour of the input background
   public static int COLOR_MESSAGE_LENGTH_BACKGROUND = 0xBB797979;  // Colour of the message length background

   public static boolean MISC_PASUE_GAME = true;                    // Game is pasued or not when the ocnsole is open
   
   public static String MOD_PATH;                                   // Location of the mod directory
   public static String LOG_PATH;                                   // Location of the console logs
   
   public static File MOD_DIR;                                      // Mod directory
   public static File LOG_DIR;                                      // Log directory
   public static File CFG_FILE;
   
   // Eventually we're going to get rid of these.
   public static File GUI_SETTINGS_FILE;// = new File(MOD_DIR, "gui.properties");
   public static File GUI_SETTINGS_DEFAULT_FILE;// = new File(MOD_DIR, "gui-default.properties");

   public static boolean EMACS_KEYS = false;                        // Use emacs keybindings
   
   public static int KEY_AUTOCOMPLETE = Keyboard.KEY_TAB;           // Autocomlete keybinding
   public static int KEY_AUTOPREV = Keyboard.KEY_LEFT;              // Next match
   public static int KEY_AUTONEXT = Keyboard.KEY_RIGHT;             // Previous match
   
	public static void initConfig(FMLPreInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		MOD_DIR = new File(event.getModConfigurationDirectory().getParent() + "/" + MCConsole.MODID);
		MOD_PATH = MOD_DIR.getAbsolutePath();
		LOG_PATH = MOD_PATH + "/logs";
		LOG_DIR = new File(LOG_PATH);
		CFG_FILE = new File(MOD_DIR.getAbsolutePath() + "/" + MCConsole.MODID + ".cfg");
		config = new Configuration(CFG_FILE);

		populateEntryList();
		loadConfig();
		updateConfig();
	}
	
	public static void populateEntryList()
	{
		entryList = new ArrayList<IConfigElement>();
		
		List<IConfigElement> genList = new ArrayList<IConfigElement>();
		
		genList.add((new GuiEntry<Boolean>("CLOSE_ON_SUBMIT",
				CLOSE_ON_SUBMIT_DEFAULT, BOOLEAN, CLOSE_ON_SUBMIT_langKey)));
		genList.add((new GuiEntry<Boolean>("SCROLL_TO_BOTTOM_ON_SUBMIT",
				SCROLL_TO_BOTTOM_ON_SUBMIT_DEFAULT, BOOLEAN, SCROLL_TO_BOTTOM_ON_SUBMIT_langKey)));
		genList.add((new GuiEntry<Boolean>("CLOSE_WITH_OPEN_KEY",
				CLOSE_WITH_OPEN_KEY_DEFAULT, BOOLEAN, CLOSE_WITH_OPEN_KEY_langKey)));
		
		entryList.add(new GuiSection(CATEGORY_GEN, CATEGORY_GEN_langKey, genList));
	}
	
	
	public static void loadConfig()
	{
		try {
			MCConsole.log.info("Loading configuration");
			config.load();
		} catch (Exception e) {
			MCConsole.log.warn("Could not load configuration. Usually a restart will fix this.");
		}
	}
	
	public static void updateConfig()
	{
		config.addCustomCategoryComment(CATEGORY_GEN, I18n.format(CATEGORY_GEN_langKey + ".tooltip"));
		
		CLOSE_ON_SUBMIT = config.get(CATEGORY_GEN, "CLOSE_ON_SUBMIT",
				CLOSE_ON_SUBMIT_DEFAULT).getBoolean(CLOSE_ON_SUBMIT_DEFAULT);
		SCROLL_TO_BOTTOM_ON_SUBMIT = config.get(CATEGORY_GEN, "SCROLL_TO_BOTTOM_ON_SUBMIT",
				SCROLL_TO_BOTTOM_ON_SUBMIT_DEFAULT).getBoolean(SCROLL_TO_BOTTOM_ON_SUBMIT_DEFAULT);
		CLOSE_WITH_OPEN_KEY = config.get(CATEGORY_GEN, "CLOSE_WITH_OPEN_KEY",
				CLOSE_WITH_OPEN_KEY_DEFAULT).getBoolean(CLOSE_WITH_OPEN_KEY_DEFAULT);
		
		updateEntries();
			
		if (config.hasChanged()) {
			MCConsole.log.info("Saving configuration");
			readGui();
			config.save();
		}
		
	}
	
	public static void updateEntries()
	{
		for (IConfigElement entrySection : entryList)
		{
			/* Right now we only have categories in the entryList
			switch (entrySection.getType())
			{
				case STRING:
				case BOOLEAN:
				case COLOR:
				case MOD_ID:
				case CONFIG_CATEGORY:
				case INTEGER:
				case DOUBLE:
			} */
			
			List<IConfigElement> sectionList = entrySection.getChildElements(); 
			
			for (IConfigElement entry : sectionList)
			{
				/* We don't have some of these yet */
				switch (entry.getType())
				{
					case MOD_ID:
					case CONFIG_CATEGORY:
					case COLOR:
					case DOUBLE:
						break;
					case STRING:
						entry.set(config.get(entrySection.getName(), entry.getName(), (String) entry.getDefault()).getString());
						break;
					case BOOLEAN:
						entry.set(config.get(entrySection.getName(), entry.getName(), (Boolean) entry.getDefault()).getBoolean((Boolean) entry.getDefault()));
						break;
					case INTEGER:
						entry.set(config.get(entrySection.getName(), entry.getName(), (Integer) entry.getDefault()).getInt());
						break;
				}
			}
		}
	}
	
	
	public static void readGui()
	{
		for (IConfigElement entrySection : entryList)
		{
			/* Right now we only have categories in the entryList
			switch (entrySection.getType())
			{
				case STRING:
				case BOOLEAN:
				case COLOR:
				case MOD_ID:
				case CONFIG_CATEGORY:
				case INTEGER:
				case DOUBLE:
			} */
			
			ConfigCategory cat = config.getCategory(entrySection.getName());
			
			List<IConfigElement> sectionList = entrySection.getChildElements();
			
			for (IConfigElement entry : sectionList)
			{
				/* We don't have some of these yet */
				Property entryprop = cat.get(entry.getName());
				switch (entry.getType())
				{
					case MOD_ID:
					case CONFIG_CATEGORY:
					case COLOR:
					case DOUBLE:
						break;
					case STRING:
						entryprop.set((String) entry.get());
						cat.put(entry.getName(), entryprop);
						break;
					case BOOLEAN:
						entryprop.setValue((Boolean) entry.get());
						cat.put(entry.getName(), entryprop);
						break;
					case INTEGER:
						entryprop.setValue((Integer) entry.get());
						cat.put(entry.getName(), entryprop);
						break;
				}
			}
		}
	}	
	
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event)
	{
		if (event.modID.equals(MCConsole.MODID)) {
			readGui();
			updateConfig();
		}
	}
}