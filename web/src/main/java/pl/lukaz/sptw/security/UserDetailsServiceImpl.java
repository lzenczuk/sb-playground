package pl.lukaz.sptw.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lukaz.sptw.service.user.UserService;
import pl.lukaz.sptw.service.user.model.UserDTO;

import java.util.Optional;

/**
 * @author lzenczuk 01/08/2015
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Loading user "+username);

        Optional<UserDTO> userDTO = userService.getUserByUserName(username);

        log.debug("UserDTO for user "+username+" "+userDTO);

        return userDTO.map(WebUser::new).orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found"));
    }
}
