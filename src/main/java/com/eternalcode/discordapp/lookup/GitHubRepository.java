package com.eternalcode.discordapp.lookup;

import com.eternalcode.discordapp.util.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a git repository.
 */
public final class GitHubRepository {

    private static final String FULL_NAME_FORMAT = "%s/%s";

    private final String owner;
    private final String name;

    /**
     * Creates a new instance of {@link GitHubRepository} with the given owner and name.
     *
     * @see #of(String, String)
     * @param owner the owner of the repository
     * @param name the name of the repository
     */
    private GitHubRepository(@NotNull String owner, @NotNull String name) {
        Preconditions.notNull(owner, "owner");
        Preconditions.notNull(name, "name");
        Preconditions.notEmpty(owner, "owner");
        Preconditions.notEmpty(name, "name");

        this.owner = owner;
        this.name = name;
    }

    @NotNull
    public String getOwner() {
        return this.owner;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getFullName() {
        return String.format(FULL_NAME_FORMAT, this.owner, this.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof GitHubRepository that)) {
            return false;
        }

        return this.owner.equals(that.owner) && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.owner, this.name);
    }

    /**
     * Creates a new instance of {@link GitHubRepository} with the given owner and name.
     *
     * @param owner the owner of the repository
     * @param name the name of the repository
     * @return repository of the given owner and name
     */
    public static GitHubRepository of(@NotNull String owner, @NotNull String name) {
        return new GitHubRepository(owner, name);
    }

}
