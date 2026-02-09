package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
