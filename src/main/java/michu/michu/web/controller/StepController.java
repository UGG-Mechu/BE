package michu.michu.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import michu.michu.service.StepAnswerService;
import michu.michu.service.StepQuestionService;
import michu.michu.web.dto.StepAnswerDto;
import michu.michu.web.dto.StepQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 5개의 질문 단계 조회
     *
     * @param evaluationId 평가 ID
     * @return List<StepQuestionDto>
     */
    @GetMapping("/questions")
    public List<StepQuestionDto> getFiveQuestions(@RequestParam Long evaluationId) {
        List<StepQuestionDto> allQuestions = stepQuestionService.getAllQuestions();
        List<StepAnswerDto> userAnswers = stepAnswerService.getUserAnswers(evaluationId); // 사용자의 선택을 가져옴

        // 사용자의 선택을 포함하여 5개의 질문 반환
        return allQuestions.stream()
                .limit(5)
                .map(question -> {
                    String selectedOption = userAnswers.stream()
                            .filter(answer -> answer.getStep() == question.getStep())
                            .map(StepAnswerDto::getSelectedOption)
                            .findFirst()
                            .orElse(null); // 사용자의 선택이 없으면 null

                    return StepQuestionDto.builder()
                            .step(question.getStep())
                            .question(question.getQuestion())
                            .options(question.getOptions())
                            .selectedOption(selectedOption) // 사용자의 선택 포함
                            .score(question.getScore())
                            .build();
                })
                .collect(Collectors.toList());
    }


    /**
     * 사용자 응답 저장
     *
     * @param dto StepAnswerDTO
     */
    @Operation(summary = "사용자 응답 저장", description = "사용자의 선택과 추가 입력값을 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/answers")
    public void saveAnswer(@RequestBody StepAnswerDto dto) {
        stepAnswerService.saveAnswer(dto);
    }

    /**
     * 질문 등록
     *
     * @param dto StepQuestionDto
     * @return 등록된 질문의 ID
     */
    @Operation(summary = "질문 등록", description = "새로운 질문을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "질문 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/questions")
    public ResponseEntity<Long> createQuestion(@RequestBody StepQuestionDto dto) {
        Long questionId = stepQuestionService.createQuestion(dto);
        return ResponseEntity.status(201).body(questionId);
    }

}