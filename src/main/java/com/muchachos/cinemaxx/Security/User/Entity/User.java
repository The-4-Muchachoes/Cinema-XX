package com.muchachos.cinemaxx.Security.User.Entity;

import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Security.User.DTO.LoginRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private boolean enabled = true;

    @Column(unique = true)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(LoginRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void addAuthority(Role authority) {
        authorities.add(authority);
    }

}
