package com.hackertest.dao;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hackertest.model.SigninFailure;

@Repository
public class SigninFailureDaoImpl implements SigninFailureDao {

	@Autowired
	SigninFailureRepo signinFailureRepo;
	
	@Override
	public void save(SigninFailure signinFailure) {
		signinFailureRepo.save(signinFailure);
	}

	@Override
	public long countLastSigninFailures(String ip, long timestamp) {
		
		//Redis hashes does not support count and queries by another field than id
		//we solve this problem with Streams, but performance may be worse
		 return StreamSupport.stream(
							signinFailureRepo.findAll().spliterator(),
							Boolean.FALSE)
				.filter(e -> e != null 
								&& e instanceof SigninFailure
								&& e.getIp().equals(ip) 
								&& e.getTimestamp() > timestamp)
				.count();
	}

	@Override
	public SigninFailure getFirstSigninFailureSince(String ip, long timestamp) {
		
		SigninFailure firstSigninSince = null;
		Optional<SigninFailure> optionalFirstSigninSince = 
			StreamSupport.stream(
						signinFailureRepo.findAll().spliterator(),
						Boolean.FALSE)
				.filter(e -> e != null 
								&& e instanceof SigninFailure
								&& e.getIp().equals(ip) 
								&& e.getTimestamp() > timestamp)
				.sorted(Comparator.comparing(SigninFailure::getTimestamp))
				.findFirst();
		if (optionalFirstSigninSince.isPresent()) {
			firstSigninSince = optionalFirstSigninSince.get();
		}
		return firstSigninSince;
	}

}
