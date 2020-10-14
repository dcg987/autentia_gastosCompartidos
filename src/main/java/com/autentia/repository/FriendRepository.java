package com.autentia.repository;

import com.autentia.model.Friend;
import com.autentia.utils.ConnectionBBDD;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class FriendRepository {

    private ConnectionBBDD bbdd = new ConnectionBBDD();
    private Connection connect;
    private Statement stm;
    private ResultSet rs;
    private String query;

    @Executable
    public List<Friend> findAllFriends() throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * from amigos";
        rs = stm.executeQuery(query);
        ArrayList<Friend> friends = new ArrayList<>();
        while(rs.next()){
            friends.add(new Friend(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_grupo")));
        }
        connect.close();
        rs.close();
        stm.close();
        return friends;
    }

    @Executable
    public Friend findFriendById(long id) throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * FROM amigos WHERE id=" + id;
        rs = stm.executeQuery(query);
        Friend friend = null;
        while(rs.next()){
            friend = new Friend(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_grupo"));
        }
        connect.close();
        rs.close();
        stm.close();
        return friend;
    }

    @Executable
    public Friend addFriend(Friend friend) throws SQLException {

        connect = bbdd.connect();
        query = "INSERT INTO amigos (id, nombre, id_grupo) VALUES (?, ?, ?)";
        PreparedStatement ps = connect.prepareStatement(query);
        ps.setInt(1, friend.getId());
        ps.setString(2, friend.getNombre());
        ps.setInt(3, friend.getId_grupo());
        ps.executeUpdate();
        connect.close();
        ps.close();
        return friend;
    }

}
