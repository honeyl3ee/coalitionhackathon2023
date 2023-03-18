package com.ft.modumoa.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    @OneToOne
    private Category category;

    private String title;
    @Lob
    private String content;
    private int max;
    private int current;

    @CreationTimestamp
    private LocalDateTime create_at;

    private LocalDateTime deadline;

}
