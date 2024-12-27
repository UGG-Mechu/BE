package michu.michu.repository;

import michu.michu.domain.StepQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepQuestionRepository extends JpaRepository<StepQuestion, Long> {}
