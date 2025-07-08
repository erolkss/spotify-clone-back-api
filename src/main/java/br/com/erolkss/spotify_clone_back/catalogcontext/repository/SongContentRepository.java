package br.com.erolkss.spotify_clone_back.catalogcontext.repository;

import br.com.erolkss.spotify_clone_back.catalogcontext.domain.SongContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongContentRepository extends JpaRepository<SongContent, Long> {
}