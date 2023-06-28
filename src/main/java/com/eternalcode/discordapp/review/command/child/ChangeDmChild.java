package com.eternalcode.discordapp.review.command.child;

import com.eternalcode.discordapp.review.GitHubReviewService;
import com.eternalcode.discordapp.review.GitHubReviewUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class ChangeDmChild extends SlashCommand {

    private final GitHubReviewService gitHubReviewService;

    public ChangeDmChild(GitHubReviewService gitHubReviewService) {
        this.name = "changedm";
        this.help = "Change the DM message for a review";

        this.userPermissions = new Permission[]{ Permission.ADMINISTRATOR };

        this.options = List.of(
            new OptionData(OptionType.USER, "user", "User to change review DM message")
                .setRequired(true)
        );

        this.gitHubReviewService = gitHubReviewService;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        try {
            Long discordId = event.getOption("user").getAsUser().getIdLong();

            GitHubReviewUser reviewUser = this.gitHubReviewService.getReviewUserByDiscordId(discordId);

            if (reviewUser == null) {
                event.reply( "User does not exist, nothing to remove").setEphemeral(true).queue();
            }

            reviewUser.setRecivingDMs(!reviewUser.isRecivingDMs());

            event.reply("User reciving dms message is not set to: "+reviewUser.isRecivingDMs()).setEphemeral(true).queue();
        }
        catch (Exception exception) {
            event.reply("An error occurred while changing dm settings to the user from the system").setEphemeral(true).queue();
            exception.printStackTrace();
        }
    }

}
