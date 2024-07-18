package sangwon.solve_it.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sangwon.solve_it.type.QuestionType;
import sangwon.solve_it.validator.Numbered;

import java.util.List;

@Data
public class QuestionUploadDto implements Numbered {
    @Size(min = 1, max = 100)
    private String content;
    @Size(min = 1, max = 100)
    private String answer;
    @NotNull
    private QuestionType type;
    @Min(1)
    private int number;
    @Valid
    private List<ChoiceUploadDto> ChoiceList;
}
