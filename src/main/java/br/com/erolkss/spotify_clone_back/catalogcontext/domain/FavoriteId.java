package br.com.erolkss.spotify_clone_back.catalogcontext.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FavoriteId implements Serializable {

    UUID songPublicId;

    String userEmail;

    public FavoriteId() {
    }

    public FavoriteId(UUID songPublicId, String userEmail) {
        this.songPublicId = songPublicId;
        this.userEmail = userEmail;
    }

    public UUID getSongPublicId() {
        return songPublicId;
    }

    public void setSongPublicId(UUID songPublicId) {
        this.songPublicId = songPublicId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FavoriteId that)) return false;
        return Objects.equals(songPublicId, that.songPublicId) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songPublicId, userEmail);
    }
}
