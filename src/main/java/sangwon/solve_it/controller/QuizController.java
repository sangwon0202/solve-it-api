package sangwon.solve_it.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sangwon.solve_it.dto.QuizDto;
import sangwon.solve_it.dto.QuizRowDto;
import sangwon.solve_it.dto.QuizUploadDto;
import sangwon.solve_it.service.QuizService;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/quizzes")
    public ResponseEntity<Void> uploadQuiz(@RequestBody @Validated QuizUploadDto quizUploadDto) {
        String userId = "sangwon";
        quizService.uploadQuiz(userId, quizUploadDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable int quizId) {
        QuizDto quizDto = quizService.getQuiz(quizId);
        return new ResponseEntity<>(quizDto, HttpStatus.OK);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<PagedModel<QuizRowDto>> getQuizList(@PageableDefault(size = 10) final Pageable pageable) {
        PagedModel<QuizRowDto> pagedModel = new PagedModel<QuizRowDto>(quizService.getQuizPage(pageable));
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);

    }
}
