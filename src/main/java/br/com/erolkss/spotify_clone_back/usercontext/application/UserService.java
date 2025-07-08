package br.com.erolkss.spotify_clone_back.usercontext.application;

import br.com.erolkss.spotify_clone_back.usercontext.domain.User;
import br.com.erolkss.spotify_clone_back.usercontext.mapper.UserMapper;
import br.com.erolkss.spotify_clone_back.usercontext.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private void updateUser(User user) {
        Optional<User> userToUpdateOpt = userRepository.findOneByEmail(user.getEmail());
        if (userToUpdateOpt.isPresent()) {
            User userToUpdate = userToUpdateOpt.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setFirstName(user.getFirstName());
            userRepository.saveAndFlush(userToUpdate);
        }
    }

    private User mapOauth2AttributesToUser(Map<String, Object> attributes) {
        User user = new User();
        String sub = String.valueOf(attributes.get("sub"));

        String username = null;

        if (attributes.get("preferred_username") != null) {
            username = ((String) attributes.get("preferred_username")).toLowerCase();
        }

        if (attributes.get("given_name") != null) {
            user.setFirstName((String) attributes.get("given_name"));
        } else if (attributes.get("name") != null) {
            user.setFirstName((String) attributes.get("name"));
        }

        if (attributes.get("family_name") != null) {
            user.setLastName((String) attributes.get("family_name"));
        }

        if (attributes.get("email") != null) {
            user.setEmail((String) attributes.get("email"));
        } else if (sub.contains("|") && (username != null && username.contains("@"))) {
            user.setEmail(username);
        } else {
            user.setEmail(sub);
        }

        if (attributes.get("picture") != null) {
            user.setImageUrl((String) attributes.get("picture"));
        }

        return user;
    }

}
