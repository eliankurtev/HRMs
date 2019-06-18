package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.SecurityData;
import com.hr.hrsystem.repository.SecurityDataRepository;
import com.hr.hrsystem.service.SecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityDataServiceImpl implements SecurityDataService {
    @Autowired
    private SecurityDataRepository securityDataRepository;

    @Override
    public SecurityData save(SecurityData securityData){
        return securityDataRepository.save(securityData);
    }
}
