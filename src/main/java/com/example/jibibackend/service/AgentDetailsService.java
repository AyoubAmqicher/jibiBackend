//package com.example.jibibackend.service;
//
//import com.example.jibibackend.model.Agent;
//import com.example.jibibackend.repository.AgentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AgentDetailsService implements UserDetailsService {
//    @Autowired
//    private AgentRepository agentRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        Agent agent = agentRepository.findById(id).orElseThrow(() -> new
//                UsernameNotFoundException("Agent not found: " + id));
//        return new User(agent.getId(),agent.getPassword(),new ArrayList<>());
//    }
//}
