package michu.michu.web.controller;

import michu.michu.service.EvaluationService;
import michu.michu.web.dto.EvaluationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/questions/{userId}")
    public EvaluationResponseDto getQuestions(@PathVariable Long userId) {
        return evaluationService.getQuestions(userId);
    }

    @PostMapping("/submit")
    public void submitEvaluation(@RequestBody EvaluationResponseDto dto) {
        evaluationService.saveEvaluation(dto);
    }
}