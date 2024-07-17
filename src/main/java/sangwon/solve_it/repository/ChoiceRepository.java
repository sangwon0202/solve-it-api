package sangwon.solve_it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sangwon.solve_it.repository.entity.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
}
