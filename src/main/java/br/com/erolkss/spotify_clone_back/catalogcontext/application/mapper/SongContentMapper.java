package br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper;

import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SaveSongDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SongContentDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.SongContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongContentMapper {

    @Mapping(source = "song.publicId", target = "publicId")
    SongContentDTO songContentToSongContentDTO(SongContent songContent);

    SongContent saveSongDTOToSong(SaveSongDTO songDTO);
}