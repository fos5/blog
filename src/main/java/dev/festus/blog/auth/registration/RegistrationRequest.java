package dev.festus.blog.auth.registration;


import dev.festus.blog.appUser.UserRole;

public record RegistrationRequest(String firstName, String lastName, String email, String password, UserRole role) {
}