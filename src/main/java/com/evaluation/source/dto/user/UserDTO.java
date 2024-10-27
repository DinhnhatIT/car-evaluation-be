package com.evaluation.source.dto.user;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String password;
    private String permission;
}
