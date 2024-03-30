package org.example.mpp_ui.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.mpp_ui.Domain.Concurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ConcursRepo implements IRepo<Concurs, Long>, IConcursRepo {
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
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Concurs c LEFT JOIN Inscrieri i on c.id = i.concurs")){
            try(ResultSet result = preStm.executeQuery()){
                while(result.next()){
                    long id = result.getLong("id");
                    AtomicReference<Concurs> c = new AtomicReference<>();
                    c.set(null);
                    lst.forEach((concurs) -> {
                        if(concurs.getId() == id){
                            c.set(concurs);
                        }
                    });
                    if(c.get() == null){
                        logger.info("Found new concurs with id="+id);
                        String proba = result.getString("Proba");
                        int varstaMin = result.getInt("VarstaMin");
                        int varstaMax = result.getInt("VarstaMax");
                        long participantId = result.getLong("participant");
                        c.set(new Concurs(id, proba, varstaMin, varstaMax, new LinkedList<>(List.of(participantId))));
                        lst.add(c.get());
                    }else{
                        logger.info("Found new participant for concurs with id="+id);
                        long participantId = result.getLong("participant");
                        List<Long> participanti = c.get().getParticipanti();
                        participanti.add(participantId);
                        c.get().setParticipanti(participanti);
                    }
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
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Concurs c LEFT JOIN Inscrieri i on c.id = i.concurs WHERE id = ?")){
            preStm.setLong(1, id);
            try(ResultSet result = preStm.executeQuery()){
                Concurs c = null;
                while(result.next()) {
                    if(c == null){
                        String proba = result.getString("Proba");
                        int varstaMin = result.getInt("VarstaMin");
                        int varstaMax = result.getInt("VarstaMax");
                        c = new Concurs(id, proba, varstaMin, varstaMax, new LinkedList<>());
                    }
                    List<Long> lst = c.getParticipanti();
                    long participantId = result.getLong("participant");
                    lst.add(participantId);
                    c.setParticipanti(lst);
                }
                logger.traceExit();
                if(c == null){
                    return Optional.empty();
                }
                return Optional.of(c);
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

    public void Enroll(long participantId, long concursId){
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStm = con.prepareStatement("INSERT INTO Inscrieri(concurs, participant) VALUES (?, ?)")){
            preStm.setLong(1, concursId);
            preStm.setLong(2, participantId);
            int result = preStm.executeUpdate();
            if(result <= 0){
                throw new SQLException("Could not insert!");
            }
        }catch(SQLException e){
            logger.error(e);
            return;
        }
        logger.info("ENROLLED!");
    }
}
