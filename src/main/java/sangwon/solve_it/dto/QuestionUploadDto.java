package sangwon.solve_it.dto;

import lombok.Data;
import sangwon.solve_it.type.QuestionType;

import java.util.List;

@Data
public class QuestionUploadDto {
    private String content;
    private String answer;
    private QuestionType type;
    private int number;
    private List<ChoiceUploadDto> ChoiceList;
}
