package io.itaiit.service.impl;

import io.itaiit.mapper.TestMapper;
import io.itaiit.service.TestMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author itaiit
 * @Date 2019/11/17 20:52
 * @Version 1.0
 */
@Service
public class TestMapperServiceImpl implements TestMapperService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String getTime() {
        return testMapper.getTime();
    }
}
