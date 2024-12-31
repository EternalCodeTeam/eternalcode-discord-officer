package com.eternalcode.discordapp.lookup;

import net.dv8tion.jda.api.entities.MessageEmbed;

public record Lookup(String name, LookupType lookupType, String description, String packageReference) {

    public static MessageEmbed asMessageEmbed() {
        return null; // TODO
    }

}
