package com.sysone.compress;

import com.sysone.compress.controller.CompressController;
import com.sysone.compress.dto.CompressRequest;
import com.sysone.compress.dto.CompressResponse;
import com.sysone.compress.service.CompressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompressTest {

    private CompressController compressController;

    @Value("${candidate}")
    private String candidate;

    @Autowired
    private CompressService compressService;

    @Before
    public void setup() {
        compressController = new CompressController(compressService);
    }

    @Test
    public void getCandidateTest() {
        assertEquals(candidate, compressController.getCandidate());
    }

    @Test
    public void compressTest() {
        CompressResponse compressResponse = new CompressResponse();
        compressResponse.setCompressed("2A3B");
        CompressRequest compressRequest = new CompressRequest();
        compressRequest.setValue("AABBB");
        assertEquals(compressResponse.getCompressed(), compressController.compress(compressRequest).getCompressed());

        compressResponse.setCompressed("A");
        compressRequest.setValue("A");
        assertEquals(compressResponse.getCompressed(), compressController.compress(compressRequest).getCompressed());

        compressResponse.setCompressed("2AB");
        compressRequest.setValue("AaB");
        assertEquals(compressResponse.getCompressed(), compressController.compress(compressRequest).getCompressed());
    }
}