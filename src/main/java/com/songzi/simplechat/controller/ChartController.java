package com.songzi.simplechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import com.songzi.simplechat.entity.UserEntity;
import com.songzi.simplechat.repository.ParticipantRepository;
import java.util.Collection;
/**
 * 
 * @author Cliff
 */
@Controller
public class ChartController {
    
    @Autowired
    ParticipantRepository participantRepository;
    
    @RequestMapping(value="/chart", method= RequestMethod.GET)
    public String chartPage(HttpServletRequest request, Model model) throws AccessDeniedException{
        if(request.getSession().getAttribute("user") == null){
            throw new AccessDeniedException("login please");
        }
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        model.addAttribute("userage", user.getAge());
        model.addAttribute("usersex", user.getSex());
        return "chart";
    }
    
    @SubscribeMapping("/chat.participants")
    public Collection<UserEntity> retrieveParticipants() {
        return participantRepository.getActiveSessions().values();
    }

}
