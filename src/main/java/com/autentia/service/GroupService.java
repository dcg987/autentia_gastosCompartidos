package com.autentia.service;


import com.autentia.model.Group;
import com.autentia.repository.GroupRepository;
import io.micronaut.http.exceptions.HttpStatusException;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class GroupService {

    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {

        this.groupRepository = groupRepository;
    }

    public List<Group> findAllGroups() {

        List<Group> groups = null;
        try {
            groups = groupRepository.findAllGroups();
        } catch (SQLException findAllGroupsException) {
            System.out.println("ERROR: groups could not be found. \n" + findAllGroupsException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return groups;
    }

    public Group findGroupById(long id) {

        Group group = null;
        try {
            group = groupRepository.findGroupById(id);
        } catch (SQLException findGroupByIdException) {
            System.out.println("ERROR: group " + id + " could not be found. \n" + findGroupByIdException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return group;
    }

    public Group addGroup(long groupId, String groupName) {

        Group group = null;
        try {
            group = groupRepository.addGroup(new Group((int) groupId, groupName));
        } catch (SQLException addGroupException) {
            System.out.println("ERROR: group could not be added. \n" + addGroupException.getMessage());
        } catch (HttpStatusException statusException) {
            statusException.getStatus();
        }
        return group;
    }

}
