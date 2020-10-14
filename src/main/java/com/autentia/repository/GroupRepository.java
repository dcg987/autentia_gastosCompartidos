package com.autentia.repository;

import com.autentia.model.Group;
import com.autentia.utils.ConnectionBBDD;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class GroupRepository {

    private ConnectionBBDD bbdd = new ConnectionBBDD();
    private Connection connect;
    private Statement stm;
    private ResultSet rs;
    private String query;

    @Executable
    public List<Group> findAllGroups() throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * FROM grupos";
        rs = stm.executeQuery(query);
        ArrayList<Group> group = new ArrayList<>();
        while(rs.next()){
            group.add(new Group(rs.getInt("id_grupo"), rs.getString("nombre_grupo")));
        }
        connect.close();
        rs.close();
        stm.close();
        return group;
    }

    @Executable
    public Group findGroupById(long id) throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * FROM grupos WHERE id_grupo=" + id;
        rs = stm.executeQuery(query);
        Group group = null;
        while(rs.next()){
            group = new Group(rs.getInt("id_grupo"), rs.getString("nombre_grupo"));
        }
        connect.close();
        rs.close();
        stm.close();
        return group;
    }

    @Executable
    public Group addGroup(Group group) throws SQLException {

        connect = bbdd.connect();
        query = "INSERT INTO grupos (id_grupo, nombre_grupo) VALUES (?, ?)";
        PreparedStatement ps = connect.prepareStatement(query);
        ps.setInt(1, group.getId_grupo());
        ps.setString(2, group.getNombre_grupo());
        ps.executeUpdate();
        connect.close();
        ps.close();
        return group;
    }

}
