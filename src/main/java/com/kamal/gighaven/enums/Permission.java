package com.kamal.gighaven.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    FREELANCER_READ("freelancer:read"), FREELANCER_CREATE("freelancer:create"), FREELANCER_UPDATE("freelancer:update"), FREELANCER_DELETE("freelancer:delete"),
    RECRUITER_READ("recruiter:read"), RECRUITER_CREATE("recruiter:create"), RECRUITER_UPDATE("recruiter:update"), RECRUITER_DELETE("recruiter:delete");

    @Getter
    private final String permission;
}
