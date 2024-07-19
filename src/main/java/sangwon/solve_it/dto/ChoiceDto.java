package sangwon.solve_it.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sangwon.solve_it.repository.entity.Choice;
import sangwon.solve_it.repository.entity.Question;
import sangwon.solve_it.util.Numbered;

@Data
public class ChoiceDto implements Numbered {
    @Size(min=1, max=100)
    private String content;
    @Min(1)
    private int number;
    public ChoiceDto(Choice choice) {
        this.content = choice.getContent();
        this.number = choice.getNumber();
    }

    public Choice toEntity(Question question) {
        return Choice.builder()
                .question(question)
                .content(content)
                .number(number)
                .build();
    }
}
