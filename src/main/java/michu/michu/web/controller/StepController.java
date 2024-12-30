package michu.michu.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import michu.michu.service.PurchaseEvaluationService;
import michu.michu.service.StepAnswerService;
import michu.michu.service.StepQuestionService;
import michu.michu.web.dto.StepAnswerDto;
import michu.michu.web.dto.StepQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/steps")
public class StepController {

    @Autowired
    private StepQuestionService stepQuestionService;

    @Autowired
    private StepAnswerService stepAnswerService;

    @Autowired
    private PurchaseEvaluationService purchaseEvaluationService;

    @PostMapping
    @Operation(summary = "평가 항목 등록", description = "평가 항목(PurchaseEvaluation)을 등록합니다.")
    public ResponseEntity<Long> createEvaluation(
            @RequestParam("itemName") String itemName,
            @RequestParam("userId") Long userId
    ) {
        Long evaluationId = purchaseEvaluationService.createEvaluationItem(itemName, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationId);
    }


    /**
     * 사용자 응답 저장
     *
     * 파라미터 => StepAnswerDto 필드값
     */
    @Operation(summary = "답변 저장", description = "평가 항목의 특정 질문에 대한 답변을 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/answers")
    public void saveAnswer(
            @RequestParam("step") int step,
            @RequestParam("selected_option") String selectedOption,
            @RequestParam("evaluationId") Long evaluationId,
            @RequestParam("score") int score
    ) {
        StepAnswerDto dto = StepAnswerDto.builder()
                .step(step)
                .selectedOption(selectedOption)
                .evaluationId(evaluationId)
                .score(score)
                .build();
        stepAnswerService.saveAnswer(dto);
    }

    /**
     * 질문 등록
     * @return 등록된 질문의 ID
     */
    @Operation(summary = "질문 등록", description = "새로운 질문을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "질문 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })

    @Parameters({
            @Parameter(name = "step", description = "질문 단계 번호입니다."),
            @Parameter(name = "question", description = "구체적인 질문 내용입니다."),
            @Parameter(name = "options", description = "선택지입니다. 기본은 총 5개의 선택지이며 구분은 ,(쉼표)로 합니다"),
            @Parameter(name = "evaluationId", description = "평가 항목 아이디 입니다."),
    })


    @PostMapping("/questions")
    public ResponseEntity<Long> createQuestion(
            @RequestParam("step") int step,
            @RequestParam("question") String question,
            @RequestParam("options") List<String> options,
            @RequestParam("evaluationId") Long evaluationId
    ) {
        StepQuestionDto dto = StepQuestionDto.builder()
                .step(step)
                .question(question)
                .options(options)
                .evaluationId(evaluationId)
                .build();

        Long questionId = stepQuestionService.createQuestion(dto);
        return ResponseEntity.status(201).body(questionId);
    }

    @PostMapping("/evaluate")
    @Operation(summary = "평가 결과 생성", description = "모든 답변을 기반으로 평가 결과를 생성합니다.")
    public ResponseEntity<Void> generateEvaluationResult(@RequestParam("evaluationId") Long evaluationId) {
        purchaseEvaluationService.generateEvaluationResult(evaluationId);
        return ResponseEntity.ok().build();
    }

}