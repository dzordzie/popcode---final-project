package com.greenfox.avuspopcode.repositories;

import com.greenfox.avuspopcode.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}