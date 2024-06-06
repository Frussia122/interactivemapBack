package com.example.apiWithDb.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data

public class CheckEmail {
    private String email;
    private String code;
    private String password;
    private String confirmPassword;
};
