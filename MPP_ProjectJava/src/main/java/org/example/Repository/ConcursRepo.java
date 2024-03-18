package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Domain.Concurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ConcursRepo implements IRepo<Concurs, Long> , IConcursRepo{
    private final JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();
    public ConcursRepo(Properties props){
        logger.info("Initializing new ConcursRepo with properties: {}", props);
        jdbcUtils = new JDBCUtils(props);
    }
    @Override
    public List<Concurs> getAll() {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        List<Concurs> lst = new LinkedList<>();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Concurs")){
            try(ResultSet result = preStm.executeQuery()){
                while(result.next()){
                    long id = result.getLong("id");
                    String proba = result.getString("Proba");
                    int varstaMin = result.getInt("VarstaMin");
                    int varstaMax = result.getInt("VarstaMax");
                    lst.add(new Concurs(id, proba, varstaMin, varstaMax, new LinkedList<>()));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.traceExit();
        return lst;
    }

    @Override
    public Optional<Concurs> find(Long id) {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Concurs WHERE id = ?")){
            preStm.setLong(1, id);
            try(ResultSet result = preStm.executeQuery()){
                if(result.next()) {
                    String proba = result.getString("Proba");
                    int varstaMin = result.getInt("VarstaMin");
                    int varstaMax = result.getInt("VarstaMax");
                    logger.traceExit();
                    return Optional.of(new Concurs(id, proba, varstaMin, varstaMax, new LinkedList<>()));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.warn("Could not find Concurs with id={}",id);
        return Optional.empty();
    }

    @Override
    public Optional<Concurs> add(Concurs toAdd) {
        return Optional.empty();
    }

    @Override
    public Optional<Concurs> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Concurs> update(Concurs toUpdate) {
        return Optional.empty();
    }

    @Override
    public List<Concurs> FindAllForAge(int age) {
        List<Concurs> lst = new LinkedList<>();
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Concurs WHERE ? >= VarstaMin and ? <= VarstaMax")){
            preStm.setInt(1, age);
            preStm.setInt(2, age);
            try(ResultSet results = preStm.executeQuery()){
                while(results.next()){
                    Long id = results.getLong("id");
                    String proba = results.getString("Proba");
                    int varstaMin = results.getInt("VarstaMin");
                    int varstaMax = results.getInt("VarstaMax");
                    lst.add(new Concurs(id, proba, varstaMin, varstaMax, new LinkedList<>()));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        return lst;
    }
}
