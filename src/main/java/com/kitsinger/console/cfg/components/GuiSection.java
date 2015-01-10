package com.kitsinger.console.cfg.components;

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