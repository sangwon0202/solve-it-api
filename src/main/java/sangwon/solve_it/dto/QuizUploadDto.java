package sangwon.solve_it.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sangwon.solve_it.repository.entity.Quiz;
import sangwon.solve_it.repository.entity.User;

import java.util.List;

@Data
public class QuizUploadDto {
    @Size(min = 1, max = 40)
    private String title;
    @Size(min = 1, max = 100)
    private String content;
    @Size(min = 1)
    @Valid
    private List<QuestionDto> questionList;

    public Quiz toEntity(User user) {
        return Quiz.builder()
                .title(this.title)
                .content(content)
                .user(user)
                .build();
    }
}
