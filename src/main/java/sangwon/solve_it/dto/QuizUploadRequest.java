package sangwon.solve_it.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuizUploadRequest {
    private String title;
    private String content;
    private List<QuestionUploadDto> questionList;
}
