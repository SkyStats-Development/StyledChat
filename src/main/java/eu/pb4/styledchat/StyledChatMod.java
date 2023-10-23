package eu.pb4.styledchat;

import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.playerdata.api.PlayerDataApi;
import eu.pb4.styledchat.config.ConfigManager;
import eu.pb4.styledchat.other.GenericModInfo;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.message.MessageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StyledChatMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Styled Chat");
	public static final ModContainer CONTAINER = FabricLoader.getInstance().getModContainer("styledchat").get();
	public static MinecraftServer server = null;

	public static boolean USE_FABRIC_API = true;

	public static RegistryKey<MessageType> MESSAGE_TYPE_ID = RegistryKey.of(RegistryKeys.MESSAGE_TYPE, new Identifier("styled_chat", "generic_hack"));

	public static MessageType getMessageType() {
		return server.getRegistryManager().get(RegistryKeys.MESSAGE_TYPE).getOrThrow(MESSAGE_TYPE_ID);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Styled Chat Loaded || Using Cardboard Patch Version Developed by @axle.coffee");
		GenericModInfo.build(CONTAINER);
		PlayerDataApi.register(StyledChatUtils.PLAYER_DATA);
		Placeholders.registerChangeEvent((id, removed) -> ConfigManager.clearCached());
	}


	public static void serverStarting(MinecraftServer s) {
		LOGGER.info("Styled Chat Loaded || Using Cardboard Patch Version Developed by @axle.coffee");
		ConfigManager.loadConfig();
		server = s;
	}

	public static void serverStopped(MinecraftServer s) {
		server = null;
	}
}
