package com.sysone.compress.controller;

import com.sysone.compress.dto.CompressRequest;
import com.sysone.compress.dto.CompressResponse;
import com.sysone.compress.service.CompressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CompressController {

    private final CompressService compressService;

    @Autowired
    public CompressController(CompressService compressService) {
        this.compressService = compressService;
    }

    @GetMapping(value = "/candidate")
    public String getCandidate(){
        return compressService.getCandidate();
    }

    @PostMapping(value = "/compress")
    public CompressResponse compress(@Valid @RequestBody CompressRequest compressRequest) {
        return compressService.compress(compressRequest.getValue());
    }

}
