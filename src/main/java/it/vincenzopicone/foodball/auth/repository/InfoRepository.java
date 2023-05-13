package it.vincenzopicone.foodball.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.vincenzopicone.foodball.auth.entity.Info;

public interface InfoRepository extends CrudRepository<Info, Long>, PagingAndSortingRepository<Info, Long> {

}
