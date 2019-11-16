package com.imooc.service.impl;

import com.imooc.service.StuService;
import com.imooc.service.TestTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransServiceImpl implements TestTransService {


    @Autowired
    private StuService stuService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTrans() {
        stuService.saveParent();

        try {
            // save point
            stuService.saveChildren();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete
        // update
        stuService.saveParent();

//        int a = 1 / 0;
    }
}
