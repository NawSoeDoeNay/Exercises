package com.startup.location.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startup.location.model.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{

}
