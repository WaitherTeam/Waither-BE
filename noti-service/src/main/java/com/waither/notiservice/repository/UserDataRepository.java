package com.waither.notiservice.repository;

import com.waither.notiservice.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
