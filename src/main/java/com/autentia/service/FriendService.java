package com.autentia.service;

import com.autentia.model.Friend;
import com.autentia.repository.FriendRepository;
import io.micronaut.http.exceptions.HttpStatusException;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class FriendService {

    private FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> findAllFriends() {
        List<Friend> friends = null;
        try {
            friends = friendRepository.findAllFriends();
        } catch (SQLException findAllFriendsException) {
            System.out.println("ERROR: friends could not be found. \n" + findAllFriendsException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return friends;
    }

    public Friend findFriendById(long id) {
        Friend friend = null;
        try {
            friend = friendRepository.findFriendById(id);
        } catch (SQLException findFriendByIdException) {
            System.out.println("ERROR: friend " + id + " could not be found. \n" + findFriendByIdException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return friend;
    }

    public Friend addFriend(long id, String friendName, long groupId) {
        Friend friend = null;
        try {
            friend = friendRepository.addFriend(new Friend((int)id, friendName, (int)groupId));
        } catch (SQLException addFriendException) {
            System.out.println("ERROR: friend could not be added. \n" + addFriendException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return friend;
    }

}
