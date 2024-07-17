package sangwon.solve_it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sangwon.solve_it.repository.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
