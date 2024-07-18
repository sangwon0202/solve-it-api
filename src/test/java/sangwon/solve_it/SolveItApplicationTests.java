package sangwon.solve_it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sangwon.solve_it.dto.QuizUploadRequest;
import sangwon.solve_it.repository.UserRepository;
import sangwon.solve_it.repository.entity.User;
import sangwon.solve_it.service.QuizService;

@SpringBootTest
class SolveItApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuizService quizService;
	@Test
	void contextLoads() {

	}

}
