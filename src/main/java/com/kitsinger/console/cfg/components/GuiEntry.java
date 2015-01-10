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

import java.util.regex.Pattern;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.ConfigGuiType;
import net.minecraft.client.resources.I18n;

import static cpw.mods.fml.client.config.ConfigGuiType.STRING;
import static cpw.mods.fml.client.config.ConfigGuiType.BOOLEAN;
import static cpw.mods.fml.client.config.ConfigGuiType.COLOR;
import static cpw.mods.fml.client.config.ConfigGuiType.MOD_ID;
import static cpw.mods.fml.client.config.ConfigGuiType.CONFIG_CATEGORY;
import static cpw.mods.fml.client.config.ConfigGuiType.INTEGER;
import static cpw.mods.fml.client.config.ConfigGuiType.DOUBLE;


public class GuiEntry<T> extends DummyConfigElement<T>
{
    protected boolean visibleInGui = true;
    
    @SuppressWarnings("unchecked")
    public GuiEntry(String name, T defaultValue, ConfigGuiType type, String langKey, String[] validValues, Pattern validStringPattern, T minValue, T maxValue)
    {
    	super(name, defaultValue, type, langKey, validValues, validStringPattern, minValue, maxValue);
    	switch (type)
    	{
    		case STRING:
    		case BOOLEAN:
    		case COLOR:
    		case MOD_ID:
    		case CONFIG_CATEGORY:
    			break;
    		case INTEGER:
    			if ((minValue != null) && ((Integer) defaultValue < (Integer) minValue))
    	    	{
    	    		this.defaultValue = minValue;
    	    		this.value = minValue;
    	    	} else if ((maxValue != null) && ((Integer) defaultValue > (Integer) maxValue)) {
    	    		this.defaultValue = maxValue;
    	    		this.value = maxValue;
    	    	}
    			break;
    		case DOUBLE:
    			if ((minValue != null) && ((Double) defaultValue < (Double) minValue))
    	    	{
    	    		this.defaultValue = minValue;
    	    		this.value = minValue;
    	    	} else if ((maxValue != null) && ((Double) defaultValue > (Double) maxValue)) {
    	    		this.defaultValue = maxValue;
    	    		this.value = maxValue;
    	    	}
    			break;
    	}
    }
    
    public GuiEntry(String name, T value, T defaultValue, ConfigGuiType type, String langKey, String[] validValues, Pattern validStringPattern, T minValue, T maxValue)
    {
        this(name, defaultValue, type, langKey, validValues, validStringPattern, minValue, maxValue);
        this.value = value;
        switch (type)
    	{
    		case STRING:
    		case BOOLEAN:
    		case COLOR:
    		case MOD_ID:
    		case CONFIG_CATEGORY:
    			break;
    		case INTEGER:
    			if ((minValue != null) && ((Integer) value < (Integer) minValue))
    	    	{
    	    		this.value = minValue;
    	    	} else if ((maxValue != null) && ((Integer) value > (Integer) maxValue)) {
    	    		this.value = maxValue;
    	    	}
    			break;
    		case DOUBLE:
    			if ((minValue != null) && ((Double) value < (Double) minValue))
    	    	{
    	    		this.value = minValue;
    	    	} else if ((maxValue != null) && ((Double) value > (Double) maxValue)) {
    	    		this.value = maxValue;
    	    	}
    			break;
    	}
    }

    public GuiEntry(String name, T value, T defaultValue, ConfigGuiType type, String langKey, Pattern validStringPattern)
    {
        this(name, value, defaultValue, type, langKey, null, validStringPattern, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T value, T defaultValue, ConfigGuiType type, String langKey, String[] validValues)
    {
        this(name, value, defaultValue, type, langKey, validValues, (Pattern) null, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T value, T defaultValue, ConfigGuiType type, String langKey)
    {
    	this(name, value, defaultValue, type, langKey, (String[]) null, (Pattern) null, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T value, T defaultValue, ConfigGuiType type, String langKey, T minValue, T maxValue)
    {
    	this(name, value, defaultValue, type, langKey, (String[]) null, (Pattern) null, minValue, maxValue);
    }
    
    public GuiEntry(String name, T defaultValue, ConfigGuiType type, String langKey, Pattern validStringPattern)
    {
        this(name, defaultValue, type, langKey, (String[]) null, validStringPattern, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T defaultValue, ConfigGuiType type, String langKey, String[] validValues)
    {
        this(name, defaultValue, type, langKey, validValues, (Pattern) null, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T defaultValue, ConfigGuiType type, String langKey)
    {
        this(name, defaultValue, type, langKey, (String[]) null, (Pattern) null, (T) null, (T) null);
    }
    
    public GuiEntry(String name, T defaultValue, ConfigGuiType type, String langKey, T minValue, T maxValue)
    {
        this(name, defaultValue, type, langKey, (String[]) null, (Pattern) null, minValue, maxValue);
    }
    
    public void setName(String newname)
    {
        name = newname;
    }

    public String getTitle()
    {
        return I18n.format(langKey);
    }

    public void setLanguageKey(String newlk)
    {
        langKey = newlk;
    }

    @Override
    public boolean showInGui()
    {
        return visibleInGui;
    }

    public void setVisibility(boolean visible)
    {
        visibleInGui = visible;
    }

    @Override
    public void set(T val)
    {
        value = val;
    }

    @Override
    public void set(T[] aVal)
    {
        values = aVal;
    }
    
    public void setDefault(T value)
    {
        defaultValue = value;
    }

    public void setDefaults(T[] aVal)
    {
        defaultValues = aVal;
    }

    public void setMinValue(T value)
    {
        minValue = value;
    }

    public void setMaxValue(T value)
    {
        maxValue = value;
    }
}