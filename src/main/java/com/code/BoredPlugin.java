package com.code;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "BoredGenerator",
	description = "A plugin to use when your bored",
	tags = {"whattodo", "idk", "bored","Generator","generator"}
)
public class  BoredPlugin extends Plugin
{
	public static Image ICON;
	private BoredPanel panel;
	private NavigationButton navButton;

	@Inject
	private Client client;

	@Inject
	private BoredConfig config;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private KeyManager keyManager;

	@Override
	protected void startUp() throws Exception
	{
		final BufferedImage panelIcon = ImageUtil.loadImageResource(getClass(), "/img.png");

		panel = injector.getInstance(BoredPanel.class);
		ICON = new ImageIcon(panelIcon).getImage();
		navButton = NavigationButton.builder()
				.tooltip("BoredGenerator")
				.icon(panelIcon)
				.priority(6)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(navButton);
	}

	@Provides
	BoredConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BoredConfig.class);
	}
}
