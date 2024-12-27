package michu.michu.service;

import michu.michu.domain.PurchaseEvaluation;
import michu.michu.domain.StepAnswer;
import michu.michu.repository.PurchaseEvaluationRepository;
import michu.michu.repository.StepAnswerRepository;
import michu.michu.web.dto.StepAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StepAnswerService {

    @Autowired
    private StepAnswerRepository stepAnswerRepository;

    @Autowired
    private PurchaseEvaluationRepository purchaseEvaluationRepository;

    /**
     * StepAnswerDto를 StepAnswer로 변환
     *
     * @param dto        StepAnswerDto
     * @param evaluation PurchaseEvaluation 객체
     * @return StepAnswer
     */
    private StepAnswer convertToEntity(StepAnswerDto dto, PurchaseEvaluation evaluation) {
        return StepAnswer.builder()
                .step(dto.getStep())
                .selectedOption(dto.getSelectedOption())
                .evaluation(evaluation)
                .build();
    }


    /**
     * 사용자 응답 저장
     *
     * @param dto StepAnswerDto
     */
    public void saveAnswer(StepAnswerDto dto) {
        PurchaseEvaluation evaluation = purchaseEvaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(() -> new RuntimeException("평가 데이터를 찾을 수 없습니다."));

        StepAnswer answer = convertToEntity(dto, evaluation);
        stepAnswerRepository.save(answer);
    }

    // 사용자의 선택을 조회하는 메서드 추가
    public List<StepAnswerDto> getUserAnswers(Long evaluationId) {
        List<StepAnswer> answers = stepAnswerRepository.findByEvaluationId(evaluationId);
        return answers.stream()
                .map(answer -> StepAnswerDto.builder()
                        .step(answer.getStep())
                        .selectedOption(answer.getSelectedOption())
                        .evaluationId(answer.getEvaluation().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
