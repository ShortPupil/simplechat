package com.songzi.simplechat.controller;

import com.songzi.simplechat.entity.UserEntity;
import com.songzi.simplechat.repository.ParticipantRepository;
import com.songzi.simplechat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 
 * @author Cliff
 */
@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private UserRepository userRepository;

    private static final String LOGIN = "/app/chat.login";
    private static final String LOGOUT = "/app/chat.logout";
    
    @RequestMapping(value = "login", method= RequestMethod.POST)
    public String login(HttpServletRequest httpRequest, UserEntity user) throws ServletException {

        httpRequest.getSession().setAttribute("user", user);
        messagingTemplate.convertAndSend(LOGIN, user);
        if(participantRepository.getActiveSessions().containsKey(httpRequest.getSession().getId())){
            messagingTemplate.convertAndSend(LOGOUT, participantRepository.getActiveSessions().get(httpRequest.getSession().getId()));
        }
        participantRepository.add(httpRequest.getSession().getId(), user);
        userRepository.save(user);
        return "redirect:/chart";
    }

}
