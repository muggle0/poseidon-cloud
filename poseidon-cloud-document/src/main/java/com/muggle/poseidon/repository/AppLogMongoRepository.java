package com.muggle.poseidon.repository;

import com.muggle.poseidon.entity.LogMessageDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: poseidon-cloud-document
 * @description:
 * @author: muggle
 * @create: 2020-03-17 14:10
 */
@Repository
public interface AppLogMongoRepository extends MongoRepository<LogMessageDO,String> {

    List<LogMessageDO> findByAppName(String test);
}
