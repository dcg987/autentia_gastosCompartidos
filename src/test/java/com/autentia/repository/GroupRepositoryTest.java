package com.autentia.repository;

import com.autentia.model.Group;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class GroupRepositoryTest {

    @Inject
    private GroupRepository groupRepository;

    @Test
    void findAllGroups() throws SQLException {

        List<Group> groupList = groupRepository.findAllGroups();
        assertFalse(groupList.isEmpty());
    }

    @Test
    void findGroupById() throws SQLException {

        List<Group> groupList = groupRepository.findAllGroups();
        groupList = Collections.singletonList(groupRepository.findGroupById(1));
        assertTrue(groupList.get(0).getId_grupo() == 1);
    }

    @Test
    void addGroup() throws SQLException {

        List<Group> groupList = groupRepository.findAllGroups();
        Group group = new Group(5, "GrupoNuevo");
        groupRepository.addGroup(group);
        assertFalse(groupList.isEmpty());
    }

}