package ch.supsi.webapp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private BlogPostService blogService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ch.supsi.webapp.web.model.entity.User user = blogService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(user.getRole().getName());
        return new User(username, user.getPassword(),
                true,
                true,
                true,
                true, auth);
    }
}
