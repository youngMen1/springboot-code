package com.seal.log.service.impl;

import com.seal.log.model.SystemLog;
import com.seal.log.repository.SystemLogRepository;
import com.seal.log.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    SystemLogRepository systemLogRepository;

    @Override
    public List<SystemLog> findAll() {
        return null;
    }

    @Override
    public void saveUser(SystemLog log) {
        systemLogRepository.save(log);
    }

    @Override
    public SystemLog findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
