package sangwon.solve_it.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangwon.solve_it.dto.QuizUploadRequest;
import sangwon.solve_it.repository.ChoiceRepository;
import sangwon.solve_it.repository.QuestionRepository;
import sangwon.solve_it.repository.QuizRepository;
import sangwon.solve_it.repository.UserRepository;
import sangwon.solve_it.repository.entity.Choice;
import sangwon.solve_it.repository.entity.Question;
import sangwon.solve_it.repository.entity.Quiz;
import sangwon.solve_it.repository.entity.User;
import sangwon.solve_it.type.QuestionType;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @Transactional
    public void uploadQuiz(String userId, QuizUploadRequest quizUploadRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저아이디입니다."));

        Quiz quiz = Quiz.builder()
                .title(quizUploadRequest.getTitle())
                .content(quizUploadRequest.getContent())
                .user(user)
                .build();
        quizRepository.save(quiz);

        quizUploadRequest.getQuestionList().forEach((questionUploadDto) -> {
            Question question = Question.builder()
                    .quiz(quiz)
                    .content(questionUploadDto.getContent())
                    .answer(questionUploadDto.getAnswer())
                    .number(questionUploadDto.getNumber())
                    .type(questionUploadDto.getType())
                    .build();
            questionRepository.save(question);

            if(questionUploadDto.getType() != QuestionType.MULTI_CHOICE) return;

            questionUploadDto.getChoiceList().forEach((choiceUploadDto) -> {
                Choice choice = Choice.builder()
                        .question(question)
                        .content(choiceUploadDto.getContent())
                        .number(choiceUploadDto.getNumber())
                        .build();
                choiceRepository.save(choice);
            });
        });
    }
}
