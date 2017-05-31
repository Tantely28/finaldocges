package org.opendevup.dao;

import org.opendevup.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository1 extends CrudRepository<Message, Long> {

}
