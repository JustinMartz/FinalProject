package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.DirectMessage;
import com.skilldistillery.duality.repositories.DirectMessageRepository;

@Service
public class DirectMessageServiceImpl implements DirectMessageService {

    @Autowired
    private DirectMessageRepository dmRepo;

    @Override
    public List<DirectMessage> listAllDirectMessagesByUser(int userId) {
    	
        return dmRepo.findByUser_Id(userId);
    }

    @Override
    public DirectMessage findById(int dmId) {
        Optional<DirectMessage> dmOpt = dmRepo.findById(dmId);
        if (dmOpt.isPresent()) {
            return dmOpt.get();
        }
        return null;
    }

    @Override
    public DirectMessage createDirectMessage(DirectMessage directMessage) {
        return dmRepo.saveAndFlush(directMessage);
    }

    @Override
    public DirectMessage updateDirectMessage(int dmId, DirectMessage directMessage) {
        DirectMessage existingDM = null;
        Optional<DirectMessage> existingOpt = dmRepo.findById(dmId);
        if (existingOpt.isPresent()) {
            existingDM = existingOpt.get();
            existingDM.setMessage(directMessage.getMessage());
            existingDM.setSender(directMessage.getSender());
            existingDM.setRecipient(directMessage.getRecipient());
            existingDM.setActive(directMessage.getActive());
            dmRepo.saveAndFlush(existingDM);
        }
        return existingDM;
    }

    @Override
    public boolean deleteDirectMessage(int dmId) {
        boolean deleted = false;
        Optional<DirectMessage> toDeleteOpt = dmRepo.findById(dmId);
        if (toDeleteOpt.isPresent()) {
            dmRepo.delete(toDeleteOpt.get());
            deleted = true;
        }
        return deleted;
    }
}
