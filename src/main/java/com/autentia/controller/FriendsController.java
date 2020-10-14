package com.autentia.controller;

import com.autentia.service.FriendService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import com.autentia.model.Friend;

import javax.inject.Inject;
import java.util.List;

@Controller("/friends")
public class FriendsController {

    private final FriendService serv;

    @Inject
    public FriendsController(FriendService serv) {
        this.serv = serv;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<Friend> findAllFriends() {
        return serv.findAllFriends();
    }

    @Get("/{friendId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Friend findFriendById(long friendId) {
        return serv.findFriendById(friendId);
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    public Friend addFriend(@Body Friend friend) {
        return serv.addFriend(friend.getId(), friend.getNombre(), friend.getId_grupo());
    }

}
