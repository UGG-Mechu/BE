package michu.michu.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import michu.michu.service.PurchaseEvaluationService;
import michu.michu.web.dto.Details.EvaluationDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final PurchaseEvaluationService purchaseEvaluationService;

    public EvaluationController(PurchaseEvaluationService purchaseEvaluationService ) {
        this.purchaseEvaluationService = purchaseEvaluationService;
    }

    /**
     * 특정 평가 ID의 전체 데이터 조회
     */
    @GetMapping("/{evaluationId}/details")
    @Operation(summary = "평가 상세 조회", description = "특정 평가 ID에 대한 전체 데이터를 조회합니다.")
    public ResponseEntity<EvaluationDetailsDto> getEvaluationDetails(@PathVariable Long evaluationId) {
        EvaluationDetailsDto evaluationDetails = purchaseEvaluationService.getEvaluationDetails(evaluationId);
        return ResponseEntity.ok(evaluationDetails);
    }
}
