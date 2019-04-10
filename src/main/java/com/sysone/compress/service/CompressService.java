package com.sysone.compress.service;

import com.sysone.compress.dto.CompressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompressService {

    @Value("${candidate}")
    private String candidate;
    
    public CompressResponse compress(String toCompress) {
        String compressUpperCase = toCompress.toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        char charToCompare = compressUpperCase.charAt(0);
        int repetitionQuantity = 1;
        for(int i=1; i < compressUpperCase.length(); i++){
            if(compressUpperCase.charAt(i) == charToCompare){
                repetitionQuantity++;
            }else {
                addToStream(stringBuilder, charToCompare, repetitionQuantity);
                charToCompare = compressUpperCase.charAt(i);
                repetitionQuantity = 1;
            }
        }
        addToStream(stringBuilder, charToCompare, repetitionQuantity);
        CompressResponse compressResponse = new CompressResponse();
        compressResponse.setCompressed(stringBuilder.toString());

        return compressResponse;
    }

    private void addToStream(StringBuilder stringBuilder, char charToCompare, int repetitionQuantity) {
        if(repetitionQuantity>1){
            stringBuilder.append(repetitionQuantity);
        }
        stringBuilder.append(charToCompare);
    }

    public String getCandidate() {
        return candidate;
    }
}
