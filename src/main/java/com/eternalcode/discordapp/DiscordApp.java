package com.eternalcode.discordapp;

import com.eternalcode.discordapp.command.AvatarCommand;
import com.eternalcode.discordapp.command.BanCommand;
import com.eternalcode.discordapp.command.BotInfoCommand;
import com.eternalcode.discordapp.command.ClearCommand;
import com.eternalcode.discordapp.command.CooldownCommand;
import com.eternalcode.discordapp.command.EmbedCommand;
import com.eternalcode.discordapp.command.KickCommand;
import com.eternalcode.discordapp.command.MinecraftServerInfoCommand;
import com.eternalcode.discordapp.command.PingCommand;
import com.eternalcode.discordapp.command.RemoveForcePushCommand;
import com.eternalcode.discordapp.command.SayCommand;
import com.eternalcode.discordapp.command.ServerCommand;
import com.eternalcode.discordapp.config.DiscordAppConfig;
import com.eternalcode.discordapp.config.DiscordAppConfigManager;
import com.eternalcode.discordapp.listener.ForcePushListener;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;

public class DiscordApp {

    public static void main(String... args) {
        DiscordAppConfigManager configManager = new DiscordAppConfigManager(new File("config"));
        DiscordAppConfig config = new DiscordAppConfig();
        configManager.load(config);

        CommandClientBuilder builder = new CommandClientBuilder()
                .addSlashCommands(
                        new AvatarCommand(config),
                        new BanCommand(config),
                        new BotInfoCommand(config),
                        new ClearCommand(config),
                        new CooldownCommand(config),
                        new EmbedCommand(),
                        new KickCommand(config),
                        new PingCommand(config),
                        new ServerCommand(config),
                        new MinecraftServerInfoCommand(),
                        new SayCommand(),
                        new RemoveForcePushCommand())
                .setOwnerId(config.topOwnerId)
                .forceGuildOnly(config.guildId)
                .setActivity(Activity.playing("IntelliJ IDEA"));
        CommandClient commandClient = builder.build();

        JDABuilder.createDefault(config.token)
                .addEventListeners(
                        commandClient,
                        new ForcePushListener()
                )
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.SCHEDULED_EVENTS
                )
                .build();
    }

}