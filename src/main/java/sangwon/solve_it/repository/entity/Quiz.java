package sangwon.solve_it.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @ManyToOne
    @JoinColumn(name="writer_id")
    private User user;
    @OneToMany(mappedBy = "quiz", fetch=FetchType.LAZY)
    private List<Question> questions;

    @Builder
    public Quiz(String content, String title, User user) {
        this.content = content;
        this.title = title;
        this.user = user;
        this.questions = new ArrayList<>();
    }
}
