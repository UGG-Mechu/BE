package michu.michu.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class URLController { // AI로 URL 보내주기

    String url = "https://www.elandmall.co.kr/i/item?itemNo=2409456545&lowerVendNo=LV22035423&pageId=1735318326304&preCornerNo=R01401001_seasonExhibitionTop";

    @GetMapping("/URL")
    public String getURL(){
        return url;
    }

}
