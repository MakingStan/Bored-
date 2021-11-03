package com.main;

import com.code.BoredPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BoredGeneratorMain
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BoredPlugin.class);
		RuneLite.main(args);
	}
}