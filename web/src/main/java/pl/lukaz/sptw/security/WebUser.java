package pl.lukaz.sptw.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lukaz.sptw.service.user.model.UserDTO;

import java.util.Collection;

/**
 * @author lzenczuk 01/08/2015
 */
public class WebUser extends UserDTO implements UserDetails {

    public WebUser(UserDTO userDTO) {
        super(userDTO.isActive(), userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.isConfirmed(), userDTO.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(getRoles().toArray(new String[getRoles().size()]));
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
