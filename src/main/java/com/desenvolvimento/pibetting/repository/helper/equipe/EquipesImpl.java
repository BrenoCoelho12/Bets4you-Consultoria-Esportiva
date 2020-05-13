package com.desenvolvimento.pibetting.repository.helper.equipe;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.repository.filter.EquipeFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EquipesImpl implements  EquipesQueries{

    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Equipe> filtrar(EquipeFilter equipe) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Equipe.class);

        if(equipe != null){
            if (!StringUtils.isEmpty(equipe.getNome())) {
                criteria.add(Restrictions.ilike("nome", equipe.getNome(), MatchMode.ANYWHERE));
            }

            if(!StringUtils.isEmpty(equipe.getNacionalidade())){
                criteria.add(Restrictions.eq("nacionalidade", equipe.getNacionalidade()));
            }
        }

        return criteria.list();

    }
}
