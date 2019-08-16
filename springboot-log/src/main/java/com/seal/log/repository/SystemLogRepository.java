package com.seal.log.repository;

import com.seal.log.model.SystemLog;
import org.springframework.data.repository.CrudRepository;


public interface SystemLogRepository extends CrudRepository<SystemLog,Long> {
}
