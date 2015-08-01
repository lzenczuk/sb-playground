package pl.lukaz.sptw.service.user;

import pl.lukaz.sptw.service.user.model.UserDTO;

import java.util.Optional;

/**
 * @author lzenczuk 31/07/2015
 */
public interface UserService {
    Optional<UserDTO> getUserByUserName(String userName);
}
