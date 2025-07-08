package br.com.erolkss.spotify_clone_back.catalogcontext.application.vo;


import jakarta.validation.constraints.NotBlank;

public record SongAuthorVO(@NotBlank String value) {
}