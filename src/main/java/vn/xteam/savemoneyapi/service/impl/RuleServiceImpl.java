package vn.xteam.savemoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.xteam.savemoneyapi.dao.RuleDao;
import vn.xteam.savemoneyapi.entities.v1.RuleEntity;
import vn.xteam.savemoneyapi.service.IRuleService;

import java.util.List;
import java.util.Optional;

@Service
public class RuleServiceImpl implements IRuleService {
    private final RuleDao ruleDao;
    @Autowired
    public RuleServiceImpl(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }
    @Override
    public List<RuleEntity> findAll() {
        return ruleDao.findAll();
    }

    @Override
    public Optional<RuleEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(RuleEntity rule) {
        System.out.println(rule);
        ruleDao.create(rule);
    }

    @Override
    public void remove(RuleEntity product) {

    }
}
