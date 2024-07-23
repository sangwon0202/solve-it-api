package sangwon.solve_it.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangwon.solve_it.dto.ChoiceDto;
import sangwon.solve_it.dto.QuizDto;
import sangwon.solve_it.dto.QuizRowDto;
import sangwon.solve_it.dto.QuizUploadDto;
import sangwon.solve_it.exception.CustomException;
import sangwon.solve_it.repository.ChoiceRepository;
import sangwon.solve_it.repository.QuestionRepository;
import sangwon.solve_it.repository.QuizRepository;
import sangwon.solve_it.repository.UserRepository;
import sangwon.solve_it.repository.entity.Choice;
import sangwon.solve_it.repository.entity.Question;
import sangwon.solve_it.repository.entity.Quiz;
import sangwon.solve_it.repository.entity.User;
import sangwon.solve_it.type.QuestionType;
import sangwon.solve_it.util.NumberedListUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @Transactional
    public void uploadQuiz(String userId, QuizUploadDto quizUploadDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("존재하지 않는 유저아이디입니다.", HttpStatus.BAD_REQUEST));

        NumberedListUtil.validateListNumbering(quizUploadDto.getQuestionList());
        Quiz quiz = quizUploadDto.toEntity(user);
        quizRepository.save(quiz);

        quizUploadDto.getQuestionList().forEach((questionDto) -> {
            Question question = questionDto.toEntity(quiz);
            questionRepository.save(question);

            if(questionDto.getType() == QuestionType.MULTI_CHOICE) {
                validateChoiceListAndAnswer(questionDto.getChoiceList(), questionDto.getAnswer());
                questionDto.getChoiceList().forEach((choiceDto) -> {
                    Choice choice = choiceDto.toEntity(question);
                    choiceRepository.save(choice);
                });
            }
        });
    }

    public QuizDto getQuiz(int quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new CustomException("존재하지 않는 퀴즈입니다.", HttpStatus.BAD_REQUEST));
        return new QuizDto(quiz);
    }

    public Page<QuizRowDto> getQuizPage(Pageable pageable) {
        return quizRepository.findAll(pageable).map(QuizRowDto::new);
    }

    public void deleteQuiz(int quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new CustomException("존재하지 않는 퀴즈입니다.", HttpStatus.BAD_REQUEST));
        quizRepository.delete(quiz);
    }


    private void validateChoiceListAndAnswer(List<ChoiceDto> choiceList, String answer) {
        if(choiceList == null || choiceList.isEmpty())
            throw new CustomException("선택지의 개수는 1 이상이여야합니다.", HttpStatus.BAD_REQUEST);
        NumberedListUtil.validateListNumbering(choiceList);
        try {
            if(Integer.parseInt(answer) > choiceList.size())
                throw new CustomException("객관식 답은 선택지 번호 중 하나여야합니다.", HttpStatus.BAD_REQUEST);
        }
        catch(NumberFormatException e) {
            throw new CustomException("객관식 정답은 숫자여야합니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
