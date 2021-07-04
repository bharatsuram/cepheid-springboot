package com.cepheid.cloud.skel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("SELECT i FROM Item i WHERE i.name = ?1")
	List<Item> findByName(String name);

	@Query("SELECT i FROM Item i WHERE i.state = ?1")
	List<Item> findByState(State state);

}
