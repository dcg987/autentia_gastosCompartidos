package com.autentia.service;

import com.autentia.model.Friend;
import com.autentia.repository.FriendRepository;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@MicronautTest
class FriendServiceTest {

    @Mock
    private final FriendRepository mockRepository = mock(FriendRepository.class);
    private final FriendService sut = new FriendService(mockRepository);
    private final int ID = 5;
    private final String NAME = "Miguel";

    @Test
    void shouldBeFindAllFriends() {
        try {
            final List<Friend> friends = new ArrayList<>();
            friends.add(new Friend(ID, NAME, ID));
            friends.add(new Friend((ID + 1), NAME + " Angel", ID));
            doReturn(friends).when(mockRepository).findAllFriends();
            final List<Friend> friends2 = sut.findAllFriends();
            assertThat(friends2, is(equalTo(friends)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeFindFriendById() {
        try {
            final Friend friend = new Friend(ID, NAME, ID);
            doReturn(friend).when(mockRepository).findFriendById(ID);
            final Friend friend2 = sut.findFriendById(ID);
            assertThat(friend2, is(equalTo(friend)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeAddFriend() {
        try {
            final Friend friend = new Friend(ID, NAME, ID);
            Friend friend1 = doReturn(friend).when(mockRepository).addFriend(friend);
            final Friend friend2 = sut.addFriend(ID, NAME, ID);
            assertThat(friend2, is(equalTo(friend1)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}