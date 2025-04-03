package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmail(String email);
}
