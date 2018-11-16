package com.songzi.simplechat.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Message {
    private long id;
    /** ENTER = "1"; SPEAK = "2"; QUIT = "3";
     */
    private int type;
    private String username;
    private String content;
    private Integer onlinecount;

    public Message(int type, String username, String content, Integer onlinecount) {
        this.type = type;
        this.username = username;
        this.content = content;
        this.onlinecount = onlinecount;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "onlinecount")
    public Integer getOnlinecount() {
        return onlinecount;
    }

    public void setOnlinecount(Integer onlinecount) {
        this.onlinecount = onlinecount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                type == message.type &&
                Objects.equals(username, message.username) &&
                Objects.equals(content, message.content) &&
                Objects.equals(onlinecount, message.onlinecount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, username, content, onlinecount);
    }


    public static String jsonStr(int type, String username, String msg, int onlineTotal) {
        return JSON.toJSONString(new Message(type, username, msg, onlineTotal));
    }
}
