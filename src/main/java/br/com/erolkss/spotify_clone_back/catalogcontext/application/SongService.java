package br.com.erolkss.spotify_clone_back.catalogcontext.application;

import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.ReadSongInfoDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SaveSongDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongContentMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.Song;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.SongContent;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongContentRepository;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SongService {

    private final SongMapper songMapper;
    private final SongRepository songRepository;
    private final SongContentMapper songContentMapper;
    private final SongContentRepository songContentRepository;

    public SongService(SongMapper songMapper, SongRepository songRepository, SongContentMapper songContentMapper, SongContentRepository songContentRepository) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.songContentMapper = songContentMapper;
        this.songContentRepository = songContentRepository;
    }

    public ReadSongInfoDTO create(SaveSongDTO saveSongDTO) {
        Song song = songMapper.saveSongDTOToSong(saveSongDTO);
        Song songSaved = songRepository.save(song);

        SongContent songContent = songContentMapper.saveSongDTOToSong(saveSongDTO);
        songContent.setSong(songSaved);

        songContentRepository.save(songContent);
        return songMapper.songToReadSongInfoDTO(songSaved);
    }
}
