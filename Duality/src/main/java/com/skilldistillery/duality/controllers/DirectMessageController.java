package com.skilldistillery.duality.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.DirectMessage;
import com.skilldistillery.duality.services.DirectMessageService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class DirectMessageController {

    @Autowired
    private DirectMessageService directMessageService;

    @GetMapping("directMessage")
    public List<DirectMessage> listDirectMessages() {
        return directMessageService.listAllDirectMessages();
    }

    @GetMapping("directMessages/{id}")
    public DirectMessage getDirectMessageById(@PathVariable("id") Integer dmId, HttpServletResponse res) {
        DirectMessage dm = directMessageService.findById(dmId);
        if (dm == null) {
            res.setStatus(404);
        }
        return dm;
    }

    @PostMapping("directMessages")
    public DirectMessage createDirectMessage(@RequestBody DirectMessage directMessage, HttpServletResponse res, HttpServletRequest req) {
        try {
            directMessage = directMessageService.createDirectMessage(directMessage);
            res.setStatus(201);
            StringBuffer url = req.getRequestURL();
            url.append("/").append(directMessage.getId());
            res.setHeader("Location", url.toString());
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            directMessage = null;
        }
        return directMessage;
    }

    @PutMapping("directMessages/{dmId}")
    public DirectMessage updateDirectMessage(@PathVariable int dmId, @RequestBody DirectMessage directMessage, HttpServletResponse res) {
        try {
            directMessage = directMessageService.updateDirectMessage(dmId, directMessage);
            if (directMessage == null) {
                res.setStatus(404);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
        }
        return directMessage;
    }

    @DeleteMapping("directMessages/{dmId}")
    public void deleteDirectMessage(@PathVariable("dmId") Integer dmId, HttpServletResponse res) {
        try {
            if (directMessageService.deleteDirectMessage(dmId)) {
                res.setStatus(200);
            } else {
                res.setStatus(404);
            }
        } catch (Exception e) {
            res.setStatus(400);
            e.printStackTrace();
        }
    }
}
