package sangwon.solve_it.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import sangwon.solve_it.type.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Question {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="content")
    private String content;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @Column(name="number")
    private int number;
    @Column(name="answer")
    private String answer;
    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Choice> choices;
    @Builder
    public Question(String content, QuestionType type, int number, String answer, Quiz quiz) {
        this.content = content;
        this.type = type;
        this.number = number;
        this.answer = answer;
        this.quiz = quiz;
        this.choices = new ArrayList<>();
    }
}
