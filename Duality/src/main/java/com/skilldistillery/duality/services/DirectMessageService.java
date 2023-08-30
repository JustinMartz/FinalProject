package com.skilldistillery.duality.services;

import java.util.List;
import com.skilldistillery.duality.entities.DirectMessage;

public interface DirectMessageService {

   
	List<DirectMessage> listAllDirectMessagesByUser(int userId);

    DirectMessage findById(int dmId);

    DirectMessage createDirectMessage(DirectMessage directMessage);

    DirectMessage updateDirectMessage(int dmId, DirectMessage directMessage);

    boolean deleteDirectMessage(int dmId);

}
