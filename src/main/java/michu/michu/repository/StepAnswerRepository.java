package michu.michu.repository;

import michu.michu.domain.StepAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepAnswerRepository extends JpaRepository<StepAnswer, Long> {}