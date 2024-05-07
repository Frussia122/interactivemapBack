package com.example.apiWithDb.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data

public class AuthDto {
    private String email;
    private String password;
};
