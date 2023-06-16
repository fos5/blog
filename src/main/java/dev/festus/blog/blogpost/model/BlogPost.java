package dev.festus.blog.blogpost.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Getter @Setter
@Entity
public class BlogPost {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NonNull
    private String title;
    @JsonFormat(pattern = "dd:MM:YY")
    private LocalDate date;
    @NonNull
    private String post;
    private String readTime;
}
