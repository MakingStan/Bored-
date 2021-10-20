package com.code;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;
import java.awt.image.BufferedImage;

@ConfigGroup("BoredGenerator")
public interface BoredConfig extends Config {

	@ConfigItem
			(
					position = 0,
					keyName = "Cengineer sound.",
					name = "C engineer completed.",
					description = "Turn the c engineer sound on or off."

			)
	default boolean CengineerC() { return false; }
	@ConfigItem
			(
					position = 1,
					keyName = "Autodetecting skills.",
					name = "Autodetecting skills.",
					description = "Autodetects your combat lvl and your skills in order to give you the most optimal task!"
			)
	default boolean AutoDetect() { return true; }


}

