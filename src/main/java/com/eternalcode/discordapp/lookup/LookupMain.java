package com.eternalcode.discordapp.lookup;

import com.eternalcode.discordapp.util.Constants;

import java.nio.file.Path;
import java.util.Set;

public class LookupMain {

    public static void main(String[] args) throws InterruptedException {
        LookupService lookupService = new LookupService(
            Path.of("run"),
            Constants.JAVA_FILE_PATTERN,
            Set.of(
                GitHubRepository.of("EternalCodeTeam", "ParcelLockers")
            )
        );

        Thread.sleep(1000);
    }

}
