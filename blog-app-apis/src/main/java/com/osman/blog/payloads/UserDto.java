package com.osman.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name Should be between 2 and 50 characters")
    private String name;
    @Email(message = "Email Address is not valid")
    @NotBlank(message = "Must have to provide Email")
    private String email;
    @NotBlank(message = "Must not be empty")
    private String password;

    @NotBlank
    private String about;
}
