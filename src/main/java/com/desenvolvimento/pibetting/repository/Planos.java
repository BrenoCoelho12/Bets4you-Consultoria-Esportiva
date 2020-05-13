package com.desenvolvimento.pibetting.repository;

import com.desenvolvimento.pibetting.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Planos  extends JpaRepository<Plano, Long> {


}
