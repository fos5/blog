package dev.festus.blog.blogpost.model;

import dev.festus.blog.appUser.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor  @AllArgsConstructor @Builder
@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String post;
    private String blogType;
    private LocalDateTime timeStamp;
    private String readingDuration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private AppUser appUser;

}
