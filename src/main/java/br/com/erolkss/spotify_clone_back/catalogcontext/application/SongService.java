package br.com.erolkss.spotify_clone_back.catalogcontext.application;

import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.ReadSongInfoDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SaveSongDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SongContentDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongContentMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.Song;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.SongContent;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongContentRepository;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ReadSongInfoDTO> getAll() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .toList();
    }

    public Optional<SongContentDTO> getOneByPublicId(UUID publicId) {
        Optional<SongContent> songByPublicId = songContentRepository.findOneBySongPublicId(publicId);
        return songByPublicId.map(songContentMapper::songContentToSongContentDTO);
    }

    public List<ReadSongInfoDTO> search(String searchTerm) {
        return songRepository.findByTitleOrAuthorContaining(searchTerm)
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .collect(Collectors.toList());
    }
}
