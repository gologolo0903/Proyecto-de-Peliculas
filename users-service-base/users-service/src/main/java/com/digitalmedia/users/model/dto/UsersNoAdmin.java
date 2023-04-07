package com.digitalmedia.users.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersNoAdmin {
    @Schema(example = "username")
    private String username;
    private String email;
}
