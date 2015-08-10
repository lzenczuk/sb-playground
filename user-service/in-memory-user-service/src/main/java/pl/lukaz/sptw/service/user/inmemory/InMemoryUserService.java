package pl.lukaz.sptw.service.user.inmemory;

import org.springframework.stereotype.Service;
import pl.lukaz.sptw.service.user.UserService;
import pl.lukaz.sptw.service.user.inmemory.model.Roles;
import pl.lukaz.sptw.service.user.inmemory.model.User;
import pl.lukaz.sptw.service.user.model.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author lzenczuk 31/07/2015
 */
@Service
public class InMemoryUserService implements UserService {

    // Passwords calculated using BCrypt
    private final static String PASSWORD_123 = "$2a$04$Z9tSJnU/3FFCkF3A37KC9.i1zVv0WbSixCx5T5Y/HWIoDAPWnIxPO";
    private final static String PASSWORD_PASSWORD = "$2a$04$EEmraTa0JmgWct/nhRJtY.UwisOP06oLK.LbMSc.D3fIak.PxWFHq";

    private static Function<User, UserDTO> userToUserDTO = u -> new UserDTO(u.isActive(), u.getId(), u.getName(), u.getPassword(), u.isConfirmed(), u.getRoles());

    private Map<String, User> storage = new HashMap<>();

    public InMemoryUserService() {
        storage.put("lucjan", User.activeAndConfirmed("1", "lucjan", PASSWORD_123, Roles.ADMIN, Roles.USER, Roles.PROJECTS_MANAGER));
        storage.put("tom", User.activeAndConfirmed("2", "tom", PASSWORD_123, Roles.USER));
        storage.put("mark", User.newAdded("3", "mark", PASSWORD_PASSWORD, Roles.USER));
    }

    @Override
    public Optional<UserDTO> getUserByUserName(String userName) {
        return Optional.ofNullable(storage.get(userName)).map(userToUserDTO);
    }

}
