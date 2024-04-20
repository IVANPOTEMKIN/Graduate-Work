package ru.skypro.homework.logging.repository;

import org.aspectj.lang.annotation.Pointcut;

public class CRUDRepositoryPointCuts {

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void save() {
    }

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.delete(..))")
    public void delete() {
    }

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.findById(..))")
    public void findById() {
    }

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.findAll(..))")
    public void findAll() {
    }
}