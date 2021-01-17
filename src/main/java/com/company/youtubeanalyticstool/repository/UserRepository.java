package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO findByUsername(String username);
}
