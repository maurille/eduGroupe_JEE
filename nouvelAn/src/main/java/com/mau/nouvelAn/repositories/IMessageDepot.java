package com.mau.nouvelAn.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mau.nouvelAn.metier.Message;

public interface IMessageDepot {

	List<Message> findAll();
	
	Message save(Message m);

}