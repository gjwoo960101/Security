package com.spring.security.controller;

import com.spring.security.dto.JoinDTO;
import com.spring.security.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private final JoinService joinService;
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }


    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(JoinDTO joinDTO){

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }


}
