package sangwon.solve_it.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sangwon.solve_it.repository.entity.Question;
import sangwon.solve_it.repository.entity.Quiz;
import sangwon.solve_it.type.QuestionType;
import sangwon.solve_it.util.Numbered;
import sangwon.solve_it.util.NumberedListUtil;

import java.util.List;

@Data
public class QuestionDto implements Numbered {
    @Size(min = 1, max = 100)
    private String content;
    @Size(min = 1, max = 100)
    private String answer;
    @NotNull
    private QuestionType type;
    @Min(1)
    private int number;
    @Valid
    private List<ChoiceDto> ChoiceList;

    public QuestionDto(Question question) {
        this.content = question.getContent();
        this.answer = question.getAnswer();
        this.type = question.getType();
        this.number = question.getNumber();
        this.ChoiceList = question.getChoices().stream().map(ChoiceDto::new).toList();
        if(this.type == QuestionType.MULTI_CHOICE)
            this.ChoiceList = NumberedListUtil.sortNumberedList(this.ChoiceList);
    }

    public Question toEntity(Quiz quiz) {
        return Question.builder()
                .quiz(quiz)
                .content(content)
                .answer(answer)
                .number(number)
                .type(type)
                .build();
    }
}
