package com.example.caludetestapp.service;

import com.example.caludetestapp.domain.Data;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public Data getData() {
        return new Data("This is dummy content from TestService");
    }
}
