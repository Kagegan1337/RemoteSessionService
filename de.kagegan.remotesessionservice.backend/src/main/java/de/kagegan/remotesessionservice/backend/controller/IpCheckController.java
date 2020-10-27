package de.kagegan.remotesessionservice.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/apiv2")
public class IpCheckController {

    @GetMapping(value = "/myIP")
    public ResponseEntity<String> getMyIp(HttpServletRequest request){
        return ResponseEntity.ok(request.getRemoteAddr());
    }
}
