package sangwon.solve_it.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sangwon.solve_it.validator.Numbered;

@Data
public class ChoiceUploadDto implements Numbered {
    @Size(min=1, max=100)
    private String Content;
    @Min(1)
    private int number;
}
