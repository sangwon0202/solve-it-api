package sangwon.solve_it.dto;


import lombok.Data;
import sangwon.solve_it.repository.entity.Quiz;
import sangwon.solve_it.util.NumberedListUtil;


import java.util.List;

@Data
public class QuizDto {
    private String title;
    private String content;
    private String userId;
    private List<QuestionDto> questionList;

    public QuizDto(Quiz quiz) {
        this.title = quiz.getTitle();
        this.content = quiz.getContent();
        this.userId = quiz.getUser().getId();
        this.questionList = quiz.getQuestions().stream().map(QuestionDto::new).toList();
        this.questionList = NumberedListUtil.sortNumberedList(this.questionList);
    }
}
