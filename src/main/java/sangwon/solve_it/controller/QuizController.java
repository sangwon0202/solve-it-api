package sangwon.solve_it.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sangwon.solve_it.dto.QuizUploadRequest;
import sangwon.solve_it.service.QuizService;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/quizzes")
    public ResponseEntity<Void> uploadQuiz(@RequestBody @Validated QuizUploadRequest quizUploadRequest) {
        String userId = "sangwon";
        quizService.uploadQuiz(userId, quizUploadRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
