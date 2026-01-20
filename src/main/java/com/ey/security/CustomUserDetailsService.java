package com.ey.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ey.exception.UserNotFoundException;
import com.ey.repository.CollectorRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepo;
	@Autowired
    private RecyclerRepository recyclerRepo;
	@Autowired
    private CollectorRepository collectorRepo;

   

    @Override
    public UserDetails loadUserByUsername(String email) {

    	return userRepo.findByEmail(email)
                .map(user -> User.withUsername(email)
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build())
                
                .or(() -> recyclerRepo.findByEmail(email)
                        .map(recycler -> User.withUsername(email)
                                .password(recycler.getPassword())
                                .roles("RECYCLER")
                                .build()))
                
                .or(() -> collectorRepo.findByEmail(email)
                        .map(collector -> User.withUsername(email)
                                .password(collector.getPassword())
                                .roles("COLLECTOR")
                                .build()))
                
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with email: " + email));
    }
}
