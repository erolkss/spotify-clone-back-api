package br.com.erolkss.spotify_clone_back.catalogcontext.application;

import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.FavoriteSongDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.ReadSongInfoDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SaveSongDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.dto.SongContentDTO;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongContentMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.application.mapper.SongMapper;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.Favorite;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.FavoriteId;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.Song;
import br.com.erolkss.spotify_clone_back.catalogcontext.domain.SongContent;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.FavoriteRepository;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongContentRepository;
import br.com.erolkss.spotify_clone_back.catalogcontext.repository.SongRepository;
import br.com.erolkss.spotify_clone_back.infraestructure.service.dto.State;
import br.com.erolkss.spotify_clone_back.infraestructure.service.dto.StateBuilder;
import br.com.erolkss.spotify_clone_back.usercontext.ReadUserDTO;
import br.com.erolkss.spotify_clone_back.usercontext.application.UserService;
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
    private final UserService userService;
    private final FavoriteRepository favoriteRepository;

    public SongService(SongMapper songMapper, SongRepository songRepository, SongContentMapper songContentMapper, SongContentRepository songContentRepository, UserService userService, FavoriteRepository favoriteRepository) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.songContentMapper = songContentMapper;
        this.songContentRepository = songContentRepository;
        this.userService = userService;
        this.favoriteRepository = favoriteRepository;
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

    public State<FavoriteSongDTO, String> addOrRemoveFromFavorite(FavoriteSongDTO favoriteSongDTO, String email) {
        StateBuilder<FavoriteSongDTO, String> builder = State.builder();
        Optional<Song> songToLikeOpt = songRepository.findOneByPublicId(favoriteSongDTO.publicId());
        if (songToLikeOpt.isEmpty()) {
            return builder.forError("Song public id doesn't exist").build();
        }

        Song songToLike = songToLikeOpt.get();

        ReadUserDTO userWhoLikedSong = userService.getByEmail(email).orElseThrow();

        if (favoriteSongDTO.favorite()) {
            Favorite favorite = new Favorite();
            favorite.setSongPublicId(songToLike.getPublicId());
            favorite.setUserEmail(userWhoLikedSong.email());
            favoriteRepository.save(favorite);
        } else {
            FavoriteId favoriteId = new FavoriteId(songToLike.getPublicId(), userWhoLikedSong.email());
            favoriteRepository.deleteById(favoriteId);
            favoriteSongDTO = new FavoriteSongDTO(false, songToLike.getPublicId());
        }

        return builder.forSuccess(favoriteSongDTO).build();
    }

    public List<ReadSongInfoDTO> fetchFavoriteSongs(String email) {
        return songRepository.findAllFavoriteByUserEmail(email)
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .toList();
    }
}
