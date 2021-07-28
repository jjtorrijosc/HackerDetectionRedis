package com.hackertest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackertest.model.SigninFailure;

@Repository
public interface SigninFailureRepo extends CrudRepository<SigninFailure, String>{

	@SuppressWarnings("unchecked")
	public SigninFailure save(SigninFailure signinFailure);
	
//	public long countByIp(String ip);
//	public List<SigninFailure> findByIp(String ip);
//	public Optional<SigninFailure> findById(String id);
//	public List<SigninFailure> findAllByIp(String ip);
}
