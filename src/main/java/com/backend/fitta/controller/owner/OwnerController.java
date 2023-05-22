package com.backend.fitta.controller.owner;

import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerApiService ownerApiService;


}
