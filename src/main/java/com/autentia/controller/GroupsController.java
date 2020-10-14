package com.autentia.controller;

import com.autentia.model.Group;
import com.autentia.service.GroupService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller("/groups")
public class GroupsController {

    private final GroupService serv;

    @Inject
    public GroupsController(GroupService serv) {
        this.serv = serv;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<Group> findAllGroups() {
        return serv.findAllGroups();
    }

    @Get("/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Group findGroupById(long groupId) {
        return serv.findGroupById(groupId);
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    public Group addGroup(@Body Group group) {
        return serv.addGroup(group.getId_grupo(), group.getNombre_grupo());
    }
}
