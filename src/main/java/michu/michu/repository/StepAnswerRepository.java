package michu.michu.repository;

import michu.michu.domain.StepAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepAnswerRepository extends JpaRepository<StepAnswer, Long> {
    // 특정 PurchaseEvaluation ID에 대한 사용자의 선택을 조회하는 메서드
    List<StepAnswer> findByEvaluationId(Long evaluationId);
}