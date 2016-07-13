package cf.brforgers.core.lib;

import cf.brforgers.core.lib.utils.Function;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A Common Helper Library for Mods
 * @author TheFreeHigh
 */
public class Utils {
	public static final Pattern formattingRemover = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
	static List<EventBus> eventHandlers = new ArrayList<EventBus>() {{
		add(FMLCommonHandler.instance().bus());
		add(MinecraftForge.EVENT_BUS);
	}};

    public static <P> Function<P> toFunction(final java.lang.Runnable runnable, Class<P> type) {
        return new Function<P>() {
            @Override
			public void run(P parameter) {
				runnable.run();
			}
		};
	}
	
	/**
	 * Get if we're on Client-side
	 * @return true when in client-side, and false if server-side
	 */
	public static boolean isClient()
	{
		return FMLCommonHandler.instance().getSide().isClient();
	}
	
	/**
	 * Get if we're on Server-side
	 * @return true when in server-side, and false if client-side
	 */
	public static boolean isServer()
	{
		return FMLCommonHandler.instance().getSide().isServer();
	}

	/**
	 * Get the Client Player Name (or a empty string if we're on server)
	 * @return Client Player Name
	 */
	@SideOnly(Side.CLIENT)
	public static String getPlayerName()
	{
		return isClient() ? Minecraft.getMinecraft().thePlayer.getDisplayName() : "";
	}

	/**
	 * Register All Events in all EventBusses
	 * @param events The Events
     */
	public static void registerEvents(Object... events)
	{
		for (EventBus bus : eventHandlers) for (Object event : events) bus.register(event);
	}
	
	/**
	 * Remove the Minecraft formatting
	 * @param str Input String
	 * @return Output String
	 */
	public static String removeFormatting(String str)
	{
		return formattingRemover.matcher(str).replaceAll("");
	}
}
