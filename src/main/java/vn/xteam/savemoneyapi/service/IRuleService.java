package vn.xteam.savemoneyapi.service;

import vn.xteam.savemoneyapi.entities.v1.RuleEntity;

import java.util.List;
import java.util.Optional;

public interface IRuleService {
    List<RuleEntity> findAll();

    Optional<RuleEntity> findById(Integer id);

    void save(RuleEntity product);

    void remove(RuleEntity product);
}
