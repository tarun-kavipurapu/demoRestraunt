    package com.example.demo.service;

    import com.example.demo.model.User;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;

    public class CustomUserDetails extends User implements UserDetails {
        private String username;

        private String password;

        Collection<?extends GrantedAuthority> authorities;


        public CustomUserDetails(User newUser){
            this.username = newUser.getPassword();
            this.password = newUser.getPassword();
            List<GrantedAuthority> auths = new ArrayList<>();
            auths.add(new SimpleGrantedAuthority(newUser.getRole().name()));
            this.authorities=auths;

        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        public boolean isAccountNonExpired(){
            return true;
        }

        public boolean isAccountNonLocked(){
            return true;
        }

        public boolean isCredentialsNonExpired(){
            return true;
        }

        public boolean isEnabled(){
            return true;
        }
    }
