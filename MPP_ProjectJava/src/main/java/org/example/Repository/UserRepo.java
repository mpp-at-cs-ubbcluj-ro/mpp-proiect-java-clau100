package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserRepo implements IRepo<User, Long>{
    private final JDBCUtils jdbcUtils;

    private static final Logger logger = LogManager.getLogger();
    public UserRepo(Properties props){
        logger.info("Initializing new UserRepo with properties: {}", props);
        jdbcUtils =new JDBCUtils(props);
    }
    @Override
    public List<User> getAll() {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        List<User> lst = new LinkedList<>();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM User")){
            try(ResultSet result = preStm.executeQuery()){
                while(result.next()){
                    long id = result.getLong("id");
                    String username = result.getString("Username");
                    String password = result.getString("Password");
                    lst.add(new User(id, username, password));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.traceExit();
        return lst;
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> add(User toAdd) {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User toUpdate) {
        return Optional.empty();
    }
}
