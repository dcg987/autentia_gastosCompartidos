package com.autentia.service;

import com.autentia.model.Group;
import com.autentia.repository.GroupRepository;
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
class GroupServiceTest {

    @Mock
    private final GroupRepository mockRepository = mock(GroupRepository.class);
    private final GroupService sut = new GroupService(mockRepository);
    private final int ID = 1;
    private final String NAME = "NewGroup";

    @Test
    void shouldBeFindAllGroups() {
        try {
            final List<Group> groups = new ArrayList<>();
            groups.add(new Group(ID, NAME));
            groups.add(new Group((ID + 1), NAME));
            doReturn(groups).when(mockRepository).findAllGroups();
            final List<Group> groups2 = sut.findAllGroups();
            assertThat(groups2, is(equalTo(groups)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeFindGroupById() {
        try {
            final Group group = new Group(ID, NAME);
            doReturn(group).when(mockRepository).findGroupById(ID);
            final Group group2 = sut.findGroupById(ID);
            assertThat(group2, is(equalTo(group)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeAddGroup() {
        try {
            final Group group = new Group(ID, NAME);
            Group group1 = doReturn(group).when(mockRepository).addGroup(group);
            final Group group2 = sut.addGroup(ID, NAME);
            assertThat(group2, is(equalTo(group1)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}