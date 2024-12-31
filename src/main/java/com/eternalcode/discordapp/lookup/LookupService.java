package com.eternalcode.discordapp.lookup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class LookupService {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/";

    private final Path cacheDirectory;
    private final Pattern fileNamePattern;
    private final Set<GitHubRepository> repositories;

    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    private final Map<String, JsonObject> classCache = new HashMap<>();
    private final Map<String, JsonObject> hashCache = new HashMap<>();

    private final File classFile;
    private final File hashFile;

    public LookupService(Path cacheDirectory, Pattern fileNamePattern, Set<GitHubRepository> repositories) {
        this.cacheDirectory = cacheDirectory;
        this.fileNamePattern = fileNamePattern;
        this.repositories = repositories;

        this.classFile = new File(this.cacheDirectory.toFile(), "classes.json");
        this.hashFile = new File(this.cacheDirectory.toFile(), "hashes.json");

        this.loadCaches();
    }






    private void loadCaches() {
        if (classFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(classFile))) {
                JsonObject[] cachedClasses = gson.fromJson(reader, JsonObject[].class);

                if (cachedClasses != null) {
                    for (JsonObject classObject : cachedClasses) {
                        String className = classObject.get("className").getAsString();
                        hashCache.put(className, classObject);
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
                // Jeśli jest błąd odczytu, możesz zareagować odpowiednio, np. logować błędy lub wyzerować cache.
            }
        }
    }

    private void saveCaches() {
        try {
            String classCache = gson.toJson(this.hashCache);
            Files.writeString(this.classFile.toPath(), classCache);

            String hashCache = gson.toJson(this.hashCache);
            Files.writeString(this.classFile.toPath(), hashCache);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
