package sangwon.solve_it.dto;


import lombok.Data;
import sangwon.solve_it.repository.entity.Quiz;

@Data
public class QuizRowDto {
    private int quizId;
    private String title;
    private String name;

    public QuizRowDto(Quiz quiz) {
        this.quizId = quiz.getId();
        this.title = quiz.getTitle();
        this.name = quiz.getUser().getName();
    }
}
