package sangwon.solve_it.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Choice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="content")
    private String content;
    private int number;
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @Builder
    public Choice(String content, Question question, int number) {
        this.content = content;
        this.question = question;
        this.number = number;
    }
}
