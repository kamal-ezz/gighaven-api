package com.kamal.gighaven.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    FREELANCER(Set.of(Permission.FREELANCER_READ, Permission.FREELANCER_CREATE, Permission.FREELANCER_UPDATE, Permission.FREELANCER_DELETE))
    ,RECRUITER(Set.of(Permission.RECRUITER_READ, Permission.RECRUITER_CREATE, Permission.RECRUITER_UPDATE, Permission.RECRUITER_DELETE));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
