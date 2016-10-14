package com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.PlayerModel;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private JdbcTemplate JdbcTemelate = null;

    /**
     * 检索
     */
    @Override
    public List<InformationDto> selectAllPlayer() {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" id, ");
        sql.append(" player_name ");
        sql.append(" FROM");
        sql.append("  information");
        sql.append(" WHERE");
        sql.append(" 1=1 ");

        List<InformationDto> informationList = JdbcTemelate.query(sql.toString(), paramList.toArray(), new InformationRowMapper());

        return informationList;
    }

    /**
     * 检索
     */
    @Override
    public List<RoleDto> selectAllRole() {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" id, ");
        sql.append(" role ");
        sql.append(" FROM");
        sql.append("  role");
        sql.append(" WHERE");
        sql.append(" 1=1 ");

        List<RoleDto> roleList = JdbcTemelate.query(sql.toString(), paramList.toArray(), new RoleRowMapper());

        return roleList;
    }

    /**
     * 检索插入的数据
     */
    @Override
    public List<PlayerDto> selectPlayerList() {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("  player.id, ");
        sql.append(" player.infor_id, ");
        sql.append(" player.date, ");
        sql.append(" player.game_status, ");
        sql.append(" information.player_name as inforName, ");
        sql.append(" role.role as roleName, ");
        sql.append(" player.role_id ");
        sql.append(" FROM");
        sql.append("  player");
        sql.append("  LEFT JOIN");
        sql.append("  information");
        sql.append("  ON");
        sql.append("  player.infor_id=information.id");
        sql.append("  LEFT JOIN");
        sql.append("  role");
        sql.append("  ON");
        sql.append("  player.role_id=role.id");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        sql.append(" order by player.id desc ");

        System.out.println(sql.toString());
        System.out.println(paramList);

        List<PlayerDto> playerList = JdbcTemelate.query(sql.toString(), paramList.toArray(), new PlayerListRowMapper());

        System.out.println(sql.toString());

        return playerList;
    }

    /**
     * 检索胜率的记录
     */
    @Override
    public List<GameCountDto> selectAllInformationList() {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("  game_count.id, ");
        sql.append(" game_count.infor_id, ");
        sql.append(" game_count.all_games_count, ");
        sql.append(" game_count.success_count, ");
        sql.append(" cast(avg((game_count.success_count)/(game_count.all_games_count)*100)as decimal(10,2)) as rate, ");
        sql.append(" information.player_name ");
        sql.append(" FROM");
        sql.append("  game_count");
        sql.append("  LEFT JOIN");
        sql.append("  information");
        sql.append("  ON");
        sql.append("  game_count.infor_id=information.id");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        sql.append(" group by information.player_name ");
        sql.append(" order by game_count.id asc ");

        System.out.println(sql.toString());

        List<GameCountDto> playerList = JdbcTemelate.query(sql.toString(), paramList.toArray(), new GameCountRowMapper());

        System.out.println(sql.toString());

        return playerList;
    }

    /**
     * 查询是否存在玩的场数的玩家信息
     */
    @Override
    public List<GameCountDto> selectInformationList(int inforId) {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" id, ");
        sql.append(" infor_id, ");
        sql.append(" all_games_count, ");
        sql.append(" success_count ");
        sql.append(" FROM");
        sql.append("  game_count");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        sql.append(" AND ");
        sql.append(" infor_id=? ");
        paramList.add(inforId);
        System.out.println(sql.toString());

        List<GameCountDto> informationList = JdbcTemelate.query(sql.toString(), paramList.toArray(), new informationListRowMapper());

        System.out.println(sql.toString());

        return informationList;
    }

    /**
     * 插入游戏记录
     */
    @Override
    public void insert(PlayerModel playerModel) {

        final StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO ");
        sql.append(" player ");
        sql.append(" ( ");
        sql.append(" infor_id, ");
        sql.append(" date, ");
        sql.append(" game_status, ");
        sql.append(" role_id ");
        sql.append(" ) ");
        sql.append(" values ");
        sql.append(" ( ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ? ");
        sql.append(" ) ");

        Object[] paramer = new Object[]{playerModel.getInforId(),playerModel.getDate(),playerModel.getGameStatus(),playerModel.getRoleId()};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 插入游戏统计记录
     */
    @Override
    public void insertInformation(int inforId,String gameStatus){

        final StringBuilder sql = new StringBuilder();
        int successCount=0;
        int allGamesCount=1;

        sql.append(" INSERT INTO ");
        sql.append(" game_count ");
        sql.append(" ( ");
        sql.append(" infor_id, ");
        sql.append(" all_games_count, ");
        sql.append(" success_count ");
        sql.append(" ) ");
        sql.append(" values ");
        sql.append(" ( ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ? ");
        sql.append(" ) ");

        if ( "success" .equals(gameStatus) ) {
            successCount=1;
        }else{
            successCount=0;
        }

        System.out.println(sql.toString());

        Object[] paramer = new Object[]{inforId,allGamesCount,successCount};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 更新游戏局数记录
     */
    @Override
    public void updateInformation(int inforId,String gameStatus,int successCount,int allGamesCount){

        final StringBuilder sql = new StringBuilder();
        int dbSuccessCount=0;
        sql.append(" UPDATE  game_count ");
        sql.append(" SET ");
        sql.append(" all_games_count=?, ");
        sql.append(" success_count=? ");
        sql.append(" WHERE");
        sql.append(" infor_id= ? ");
        if ( "success" .equals(gameStatus) ) {
            dbSuccessCount = successCount+1;
        }else{
            dbSuccessCount = successCount;
        }
        allGamesCount=allGamesCount+1;
        System.out.println(sql.toString());

        Object[] paramer = new Object[]{allGamesCount,dbSuccessCount,inforId};
        JdbcTemelate.update(sql.toString(), paramer);

    }



    protected class RoleRowMapper implements RowMapper<RoleDto> {

        @Override
        public RoleDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            RoleDto roleDto = new RoleDto();
            roleDto.setId(rs.getInt("id"));
            roleDto.setRole(rs.getString("role"));
            return roleDto;
        }
    }

    protected class InformationRowMapper implements RowMapper<InformationDto> {

        @Override
        public InformationDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            InformationDto informationDto = new InformationDto();
            informationDto.setId(rs.getInt("id"));
            informationDto.setPlayerName(rs.getString("player_name"));
            return informationDto;
        }
    }



    protected class PlayerRowMapper implements RowMapper<PlayerDto> {

        @Override
        public PlayerDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(rs.getInt("id"));
            playerDto.setRoleId(rs.getInt("role_id"));
            playerDto.setDate(rs.getDate("date"));
            playerDto.setGamestatus(rs.getString("game_status"));
            return playerDto;
        }
    }



    protected class PlayerCountListRowMapper implements RowMapper<PlayerDto> {

        @Override
        public PlayerDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            PlayerDto playerDto = new PlayerDto();
            playerDto.setSuccessCount(rs.getInt("count(player.game_status)"));

            return playerDto;
        }
    }

    protected class PlayerListRowMapper implements RowMapper<PlayerDto> {

        @Override
        public PlayerDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(rs.getInt("id"));
            playerDto.setRoleId(rs.getInt("role_id"));
            playerDto.setInforId(rs.getInt("infor_id"));
            playerDto.setDate(rs.getDate("date"));
            playerDto.setGamestatus(rs.getString("game_status"));
            playerDto.setInforName(rs.getString("inforName"));
            playerDto.setRoleName(rs.getString("roleName"));
            return playerDto;
        }
    }

    protected class informationListRowMapper implements RowMapper<GameCountDto> {

        @Override
        public GameCountDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            GameCountDto gameCountDto = new GameCountDto();
            gameCountDto.setId(rs.getInt("id"));
            gameCountDto.setInforId(rs.getInt("infor_id"));
            gameCountDto.setAllGamesCount(rs.getInt("all_games_count"));
            gameCountDto.setSuccessCount(rs.getInt("success_count"));
            return gameCountDto;
        }
    }

    protected class GameCountRowMapper implements RowMapper<GameCountDto> {

        @Override
        public GameCountDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            GameCountDto gameCountDto = new GameCountDto();
            gameCountDto.setId(rs.getInt("id"));
            gameCountDto.setInforId(rs.getInt("infor_id"));
            gameCountDto.setInforName(rs.getString("player_name"));
            gameCountDto.setAllGamesCount(rs.getInt("all_games_count"));
            gameCountDto.setSuccessCount(rs.getInt("success_count"));
            gameCountDto.setRate(rs.getFloat("rate"));
            return gameCountDto;
        }
    }

}
