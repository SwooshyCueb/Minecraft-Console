package com.kitsinger.console.cfg.components;

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

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.config.ConfigGuiType;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.config.GuiConfigEntries.IConfigEntry;

import com.kitsinger.console.cfg.components.GuiEntry;

public class GuiSection<T> extends GuiEntry<T>
{
    @SuppressWarnings("rawtypes")
    public GuiSection(String name, String langKey, List<IConfigElement> childElements)
    {
        this(name, langKey, childElements, (Class<? extends IConfigEntry>) null);
    }
        
    @SuppressWarnings("rawtypes")
    public GuiSection(String name, String langKey, Class<? extends IConfigEntry> customListEntryClass)
    {
        this(name, langKey, new ArrayList<IConfigElement>(), customListEntryClass);
    }
    
    @SuppressWarnings("rawtypes")
    public GuiSection(String name, String langKey, List<IConfigElement> childElements, Class<? extends IConfigEntry> customListEntryClass)
    {
        super(name, (T) null, (T) null, ConfigGuiType.CONFIG_CATEGORY, langKey);
        this.childElements = childElements;
        this.configEntryClass = customListEntryClass;
        isProperty = false;
    }
}