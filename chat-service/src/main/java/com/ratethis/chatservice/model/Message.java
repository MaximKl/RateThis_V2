package com.ratethis.chatservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "user_message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(true)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long messageId;

    @Basic
    @Column(name = "message_body")
    private String messageBody;

    @Basic
    @Column(name = "message_time")
    private Timestamp messageTime;

    @Basic
    @Column(name = "message_from")
    private long messageFrom;

    @Basic
    @Column(name = "message_to")
    private long messageTo;

    @Basic
    @Column(name = "message_is_read")
    private boolean messageIsRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return (message.messageFrom + message.messageTo) == (messageFrom + messageTo);
    }

    @Override
    public int hashCode() {
        return (int) (messageFrom + messageTo);
    }
}
