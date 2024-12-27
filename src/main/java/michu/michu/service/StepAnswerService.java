package michu.michu.service;

import michu.michu.domain.PurchaseEvaluation;
import michu.michu.domain.StepAnswer;
import michu.michu.repository.PurchaseEvaluationRepository;
import michu.michu.repository.StepAnswerRepository;
import michu.michu.web.dto.StepAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepAnswerService {

    @Autowired
    private StepAnswerRepository stepAnswerRepository;

    @Autowired
    private PurchaseEvaluationRepository purchaseEvaluationRepository;

    /**
     * 사용자 응답 저장
     *
     * @param dto StepAnswerDto
     */
    public void saveAnswer(StepAnswerDto dto) {
        PurchaseEvaluation evaluation = purchaseEvaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(() -> new RuntimeException("평가 데이터를 찾을 수 없습니다."));

        StepAnswer answer = StepAnswer.builder()
                .step(dto.getStep())
                .selectedOption(dto.getSelectedOption())
                .evaluation(evaluation) // PurchaseEvaluation 연계
                .build();

        stepAnswerRepository.save(answer);
    }
}
