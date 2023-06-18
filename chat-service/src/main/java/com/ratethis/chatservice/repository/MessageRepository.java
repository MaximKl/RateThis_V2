package com.ratethis.chatservice.repository;

import com.ratethis.chatservice.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository  extends CrudRepository<Message,Long> {
    List<Message> findAllByMessageToAndMessageFrom(long mesTo, long mesFrom);
    List<Message> findAllByMessageFrom(long mesFrom);
    List<Message> findAllByMessageTo(long mesTo);
}
