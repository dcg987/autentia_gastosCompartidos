package com.autentia.repository;

import com.autentia.model.Friend;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class FriendRepositoryTest {

    @Inject
    FriendRepository friendRepository;

    @Test
    void findAllFriends() throws SQLException {

        List<Friend> friendList = friendRepository.findAllFriends();
        assertFalse(friendList.isEmpty());
    }

    @Test
    void findFriendById() throws SQLException {

        List<Friend> friendList = friendRepository.findAllFriends();
        friendList = Collections.singletonList(friendRepository.findFriendById(1));
        assertTrue(friendList.get(0).getId_grupo() == 1);
    }

    @Test
    void addFriend() throws SQLException {
        List<Friend> friendList = friendRepository.findAllFriends();
        Friend friend = new Friend(5, "Patricia", 1);
        friendRepository.addFriend(friend);
        assertFalse(friendList.isEmpty());
    }
}