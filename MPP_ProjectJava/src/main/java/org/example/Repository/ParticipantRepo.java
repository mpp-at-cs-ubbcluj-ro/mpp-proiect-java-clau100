package org.example.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Domain.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ParticipantRepo implements IRepo<Participant, Long>, IParticipantRepo{
    private final JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();
    public ParticipantRepo(Properties props){
        logger.info("Initializing new ParticipantRepo with properties :{}", props);
        jdbcUtils = new JDBCUtils(props);
    }
    @Override
    public List<Participant> getAll() {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        List<Participant> lst = new LinkedList<>();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Participant")){
            try(ResultSet result = preStm.executeQuery()){
                while(result.next()){
                    long id = result.getLong("id");
                    int varsta = result.getInt("Varsta");
                    lst.add(new Participant(id, varsta, new LinkedList<>()));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.traceExit();
        return lst;
    }

    @Override
    public Optional<Participant> find(Long id) {
        logger.traceEntry();
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM PARTICIPANT WHERE id = ?")){
            preStm.setLong(1, id);
            try(ResultSet result = preStm.executeQuery()){
                if(result.next()){
                    int varsta = result.getInt("Varsta");
                    logger.traceExit();
                    return Optional.of(new Participant(id, varsta, new LinkedList<>()));
                }
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.warn("No Participant found with id={}", id);
        return Optional.empty();
    }

    @Override
    public Optional<Participant> add(Participant toAdd) {
        return Optional.empty();
    }

    @Override
    public Optional<Participant> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Participant> update(Participant toUpdate) {
        return Optional.empty();
    }

    @Override
    public List<Participant> FindAllFromList(List<Long> participantIDs) {
        logger.traceEntry();
        List<Participant> lst = new LinkedList<>();
        participantIDs.forEach(p -> {
            Connection con = jdbcUtils.getConnection();
            try(PreparedStatement preStm = con.prepareStatement("SELECT * FROM Participant WHERE id = ?")){
                preStm.setLong(1, p);
                try(ResultSet result = preStm.executeQuery()){
                    if(result.next()){
                        int varsta = result.getInt("Varsta");
                        lst.add(new Participant(p, varsta, new LinkedList<>()));
                        return;
                    }
                    logger.error("Could not find Participant with id="+p);
                }
            }catch(SQLException e){
                logger.error(e);
            }
        });
        return lst;
    }
}
