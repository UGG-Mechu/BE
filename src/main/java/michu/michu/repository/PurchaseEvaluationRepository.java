package michu.michu.repository;

import michu.michu.domain.PurchaseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseEvaluationRepository extends JpaRepository<PurchaseEvaluation, Long> {

    // 특정 사용자와 연계된 구매 평가 목록 조회
    List<PurchaseEvaluation> findByUserId(Long userId);
}