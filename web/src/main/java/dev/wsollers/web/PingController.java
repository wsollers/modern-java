package dev.wsollers.web;

import dev.wsollers.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
class PingController {
    private final static Logger logger = LogFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    Map<String, String> ping() {
        logger.debug("Ping Initiated\n");
        return Map.of("status", "ok");
    }
}
