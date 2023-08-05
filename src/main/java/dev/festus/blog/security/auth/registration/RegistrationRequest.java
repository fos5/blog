package dev.festus.blog.security.auth.registration;


import dev.festus.blog.appUser.UserRole;
import lombok.Builder;

@Builder
public record RegistrationRequest(String firstName, String lastName, String email, String password, UserRole role) {
}
