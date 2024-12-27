package michu.michu.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import michu.michu.service.StepAnswerService;
import michu.michu.service.StepQuestionService;
import michu.michu.web.dto.StepAnswerDto;
import michu.michu.web.dto.StepQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
public class StepController {

    @Autowired
    private StepQuestionService stepQuestionService;

    @Autowired
    private StepAnswerService stepAnswerService;

    /**
     * 모든 질문 단계 조회
     *
     * @return List<StepQuestionDTO>
     */
    @Operation(summary = "모든 질문 단계 조회", description = "저장된 모든 질문을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "질문 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/questions")
    public List<StepQuestionDto> getAllQuestions() {
        return stepQuestionService.getAllQuestions();
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
}