package michu.michu.service;

import lombok.RequiredArgsConstructor;
import michu.michu.domain.PurchaseEvaluation;
import michu.michu.domain.StepAnswer;
import michu.michu.domain.StepQuestion;
import michu.michu.domain.User;
import michu.michu.repository.PurchaseEvaluationRepository;
import michu.michu.repository.StepAnswerRepository;
import michu.michu.repository.StepQuestionRepository;
import michu.michu.repository.UserRepository;
import michu.michu.web.dto.Details.EvaluationDetailsDto;
import michu.michu.web.dto.Details.QuestionDetailsDto;
import michu.michu.web.dto.PurchaseEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseEvaluationService {

    private final PurchaseEvaluationRepository purchaseEvaluationRepository;

    private final StepAnswerService stepAnswerService;

    private final StepQuestionRepository stepQuestionRepository;
    private final StepAnswerRepository stepAnswerRepository;
    private final UserRepository userRepository;

    public Long createEvaluationItem(String itemName, Long userId) {
        //사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 조회 안됌"));

        PurchaseEvaluation evaluation = PurchaseEvaluation.builder()
                .itemName(itemName)
                .evaluationResult(null) // 평가 결과는 나중에 계산
                .totalScore(0) // 초기 점수는 0
                .user(user)
                .build();

        return purchaseEvaluationRepository.save(evaluation).getId();
    }

    public Long createEvaluation(PurchaseEvaluationDto dto) {
        //사용자 조회
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 조회 안됌"));

        // PurchaseEvaluation 생성
        PurchaseEvaluation evaluation = PurchaseEvaluation.builder()
                .itemName(dto.getItemName())
                .evaluationResult(dto.getEvaluationResult())
                .user(user)
                .build();

        // StepAnswer 생성 및 연계
        List<StepAnswer> stepAnswers = dto.getStepAnswers().stream()
                .map(stepDto -> StepAnswer.builder()
                        .step(stepDto.getStep())
                        .selectedOption(stepDto.getSelectedOption())
                        .evaluation(evaluation)
                        .build())
                .collect(Collectors.toList());

        evaluation.setStepAnswers(stepAnswers);

        // 데이터 저장
        purchaseEvaluationRepository.save(evaluation);
        stepAnswerRepository.saveAll(stepAnswers);

        return evaluation.getId();

    }

    public void generateEvaluationResult(Long evaluationId) {
        PurchaseEvaluation evaluation = purchaseEvaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));

        // 평가 항목에 대한 모든 답변 가져오기
        List<StepAnswer> answers = stepAnswerRepository.findByEvaluationId(evaluationId);

        // 총 점수 계산
        int totalScore = stepAnswerService.calculateTotalScore(evaluationId);


        // 결과를 점수 범위에 따라 설정
        String evaluationResult;
        if (totalScore >= 17) {
            evaluationResult = "좋은 소비입니다.";
        } else if (totalScore >= 10) {
            evaluationResult = "적절하지만 고려해야 하는 소비입니다.";
        } else {
            evaluationResult = "과소비입니다.";
        }

        // 평가 항목 업데이트
        evaluation.setTotalScore(totalScore);
        evaluation.setEvaluationResult(evaluationResult);

        // 저장
        purchaseEvaluationRepository.save(evaluation);
    }

    /**
     * 특정 평가 ID의 전체 데이터를 조회
     */
    public EvaluationDetailsDto getEvaluationDetails(Long evaluationId) {
        // 평가 정보 조회
        PurchaseEvaluation evaluation = purchaseEvaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("평가 데이터를 찾을 수 없습니다."));

        // 질문 목록 조회
        List<StepQuestion> questions = stepQuestionRepository.findAll();

        // 평가에 대한 답변 목록 조회
        List<StepAnswer> answers = stepAnswerRepository.findByEvaluationId(evaluationId);

        // 질문-답변 데이터 매핑
        List<QuestionDetailsDto> questionDetails = questions.stream()
                .map(question -> {
                    // 해당 질문에 대한 답변 찾기
                    StepAnswer answer = answers.stream()
                            .filter(a -> a.getStep() == question.getStep())
                            .findFirst()
                            .orElse(null);

                    return QuestionDetailsDto.builder()
                            .step(question.getStep())
                            .question(question.getQuestion())
                            .options(question.getOptions())
                            .selectedOption(answer != null ? answer.getSelectedOption() : null)
                            .score(answer != null ? answer.getScore() : 0)
                            .build();
                })
                .collect(Collectors.toList());

        return EvaluationDetailsDto.builder()
                .evaluationId(evaluation.getId())
                .itemName(evaluation.getItemName())
                .evaluationResult(evaluation.getEvaluationResult())
                .totalScore(evaluation.getTotalScore())
                .questions(questionDetails)
                .build();
    }


}
