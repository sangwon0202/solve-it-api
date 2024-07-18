package sangwon.solve_it.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
public class QuizUploadRequest {
    @Size(min = 1, max = 40)
    private String title;
    @Size(min = 1, max = 100)
    private String content;
    @Size(min = 1)
    @Valid
    private List<QuestionUploadDto> questionList;
}
