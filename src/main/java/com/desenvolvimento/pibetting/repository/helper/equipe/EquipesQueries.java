package com.desenvolvimento.pibetting.repository.helper.equipe;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.repository.filter.EquipeFilter;

import java.util.List;

public interface EquipesQueries {

    public List<Equipe> filtrar (EquipeFilter equipe);
}
