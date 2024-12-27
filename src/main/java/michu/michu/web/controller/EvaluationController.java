package michu.michu.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import michu.michu.service.EvaluationService;
import michu.michu.web.dto.EvaluationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * 질문 제공
     *
     * @param userId 사용자 ID
     * @return EvaluationResponseDTO 질문 목록 및 초기 응답 구조
     */
    @Operation(summary = "질문 제공", description = "사용자 ID를 기반으로 질문과 초기 응답 구조를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "질문 반환 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EvaluationResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/questions/{userId}")
    public EvaluationResponseDto getQuestions(@PathVariable Long userId) {
        return evaluationService.getQuestions(userId);
    }

    /**
     * 응답 저장
     *
     * @param dto 사용자 선택한 점수 및 의견
     */
    @Operation(summary = "응답 저장", description = "사용자가 선택한 점수와 의견을 데이터베이스에 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 저장 성공"),
            @ApiResponse(responseCode = "400", description = "요청 데이터가 잘못됨",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/submit")
    public void submitEvaluation(@RequestBody EvaluationResponseDto dto) {
        evaluationService.saveEvaluation(dto);
    }
}