package dev.festus.blog.blogpost.model;

import java.time.LocalDate;

public record BlogPostTitle(int id, String title, LocalDate date){
}
