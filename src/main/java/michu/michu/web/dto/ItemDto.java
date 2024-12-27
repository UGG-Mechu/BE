package michu.michu.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;

    private String itemName;

    private Integer price;

    private String itemDetail;

    private String reviews;
}
