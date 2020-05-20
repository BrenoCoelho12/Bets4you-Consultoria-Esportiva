package com.desenvolvimento.bets4you.repository.helper.equipe;

import com.desenvolvimento.bets4you.model.Equipe;
import com.desenvolvimento.bets4you.repository.filter.EquipeFilter;

import java.util.List;

public interface EquipesQueries {

    public List<Equipe> filtrar (EquipeFilter equipe);
}
