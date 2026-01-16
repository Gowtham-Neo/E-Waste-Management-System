package com.ey.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ey.exception.UserNotFoundException;
import com.ey.model.Collector;
import com.ey.model.Recycler;
import com.ey.repository.CollectorRepository;
import com.ey.repository.RecyclerRepository;
import com.ey.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;
    private final RecyclerRepository recyclerRepo;
    private final CollectorRepository collectorRepo;

    public CustomUserDetailsService(UserRepository userRepo,
                                    RecyclerRepository recyclerRepo,
                                    CollectorRepository collectorRepo) {
        this.userRepo = userRepo;
        this.recyclerRepo = recyclerRepo;
        this.collectorRepo = collectorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String compoundKey) {

       
        String[] parts = compoundKey.split(":");
        String type = parts[0];
        String identifier = parts[1];

        switch (type) {

            case "USER" -> {
                var user = userRepo.findByEmail(identifier)
                						.orElseThrow(()->new UserNotFoundException("User not found"));

                return User
                        .withUsername(compoundKey)
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build();
            }

            case "RECYCLER" -> {
                Recycler recycler = recyclerRepo.findByEmail(identifier)
                        .orElseThrow(() -> new UserNotFoundException("Recycler not found"));

                System.out.println(recycler);
                return User
                        .withUsername(compoundKey)
                        .password(recycler.getPassword())
                        .roles("RECYCLER")
                        .build();
            }

            case "COLLECTOR" -> {
                Collector collector = collectorRepo.findByEmail(identifier)
                        .orElseThrow(() -> new UserNotFoundException("Collector not found"));

                return User
                        .withUsername(compoundKey)
                        .password(collector.getPassword())
                        .roles("COLLECTOR")
                        .build();
            }
        }

        throw new UserNotFoundException("Invalid principal type");
    }
}
