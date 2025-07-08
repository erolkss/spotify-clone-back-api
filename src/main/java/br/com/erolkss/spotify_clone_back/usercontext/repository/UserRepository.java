package br.com.erolkss.spotify_clone_back.usercontext.repository;

import br.com.erolkss.spotify_clone_back.usercontext.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
}