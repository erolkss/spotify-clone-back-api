package br.com.erolkss.spotify_clone_back.usercontext.mapper;

import br.com.erolkss.spotify_clone_back.usercontext.ReadUserDTO;
import br.com.erolkss.spotify_clone_back.usercontext.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO readUserDTOToUser(User entity);
}