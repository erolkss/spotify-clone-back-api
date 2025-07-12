package br.com.erolkss.spotify_clone_back.catalogcontext.repository;

import br.com.erolkss.spotify_clone_back.catalogcontext.domain.Favorite;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findAllByUserEmailAndSongPublicIdIn(String email, List<UUID> songPublicIds);
}