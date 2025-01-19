package michu.michu.web.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseEvaluationItemDto {
    private Long purchaseId;
    private String itemName;
}
