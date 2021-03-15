package in.hocg.zeus.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Created by hocgin on 2020/11/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexController {

    @RequestMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestParam(value = "redirectUrl", required = false, defaultValue = "http://hocg.in") String redirectUrl) {
        return Mono.just(ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(redirectUrl))
            .build());
    }

    @RequestMapping("/logout")
    public Mono<ResponseEntity<?>> logout(@RequestParam(value = "redirectUrl", required = false, defaultValue = "http://hocg.in") String redirectUrl) {
        return Mono.just(ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(redirectUrl))
            .build());
    }
}
