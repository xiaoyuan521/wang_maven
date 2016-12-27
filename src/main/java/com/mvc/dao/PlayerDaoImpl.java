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
import com.mvc.model.InformationModel;
import com.mvc.model.PlayerModel;
import com.mysql.jdbc.StringUtils;

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
     * 检索拿牌率
     */
    @Override
    public Double selectNplData(int roleId, int inforId) {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");

        switch (roleId) {
            case 1:
                sql.append(" cast(avg((all_prophet_count)/(all_games_count)*100) as decimal(10,1)) AS NPL ");// 预言家
                break;

            case 2:
                sql.append(" cast(avg((all_witch_count)/(all_games_count)*100) as decimal(10,1)) AS NPL ");// 女巫
                break;
            case 3:
                sql.append(" cast(avg((all_hunter_count)/(all_games_count)*100) as decimal(10,1)) AS NPL ");// 猎人
                break;
            case 4:
                sql.append(" cast(avg((all_werewolf_count)/(all_games_count)*100) as decimal(10,1)) AS NPL ");// 狼人
                break;
            case 5:
                sql.append(" cast(avg((all_civilian_count)/(all_games_count)*100) as decimal(10,1)) AS NPL ");// 平民
                break;
        }
        sql.append(" FROM");
        sql.append("  game_count");
        sql.append(" WHERE");
        sql.append(" 1=1 ");

        if (inforId != 0) {
            sql.append(" AND ");
            sql.append(" game_count.infor_id=? ");
            paramList.add(inforId);
        }

        Double roleList = JdbcTemelate.queryForObject(sql.toString(), paramList.toArray(), Double.class);

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
    public List<PlayerDto> selectPlayerListByCondition(PlayerModel playerModel) {

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

        if (!StringUtils.isNullOrEmpty(playerModel.getInforId())) {
            sql.append(" AND ");
            sql.append("  player.infor_id=? ");
            paramList.add(Integer.valueOf(playerModel.getInforId()));
        }

        if (!StringUtils.isNullOrEmpty(playerModel.getRoleId())) {
            sql.append(" AND ");
            sql.append("  player.role_id=? ");
            paramList.add(Integer.valueOf(playerModel.getRoleId()));
        }

        if (!StringUtils.isNullOrEmpty(playerModel.getDate())) {
            sql.append(" AND ");
            sql.append("  player.date=? ");
            paramList.add(playerModel.getDate());
        }

        if (!StringUtils.isNullOrEmpty(playerModel.getGameStatus())) {
            sql.append(" AND ");
            sql.append("  player.game_status=? ");
            paramList.add(playerModel.getGameStatus());
        }

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
        sql.append(" information.player_name, ");
        sql.append(" game_count.date, ");
        sql.append(" game_count.game_status, ");
        sql.append(" game_count.role_id, ");
        sql.append(" game_count.all_werewolf_count, ");
        sql.append(" game_count.success_werewolf_count, ");
        sql.append(" game_count.all_prophet_count, ");
        sql.append(" game_count.success_prophet_count, ");
        sql.append(" game_count.all_witch_count, ");
        sql.append(" game_count.success_witch_count, ");
        sql.append(" game_count.all_hunter_count, ");
        sql.append(" game_count.success_hunter_count, ");
        sql.append(" game_count.all_civilian_count, ");
        sql.append(" game_count.success_civilian_count, ");
        sql.append(" cast(avg((game_count.success_count)/(game_count.all_games_count)*100)as decimal(10,2)) as rate, ");
        sql.append(" cast(avg((game_count.success_werewolf_count)/(game_count.all_werewolf_count)*100)as decimal(10,2)) as werewolfRate, ");
        sql.append(" cast(avg((game_count.success_prophet_count)/(game_count.all_prophet_count)*100)as decimal(10,2)) as prophetRate, ");
        sql.append(" cast(avg((game_count.success_witch_count)/(game_count.all_witch_count)*100)as decimal(10,2)) as witchRate, ");
        sql.append(" cast(avg((game_count.success_hunter_count)/(game_count.all_hunter_count)*100)as decimal(10,2)) as hunterRate, ");
        sql.append(" cast(avg((game_count.success_civilian_count)/(game_count.all_civilian_count)*100)as decimal(10,2)) as civilianRate ");
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
     * 检索胜率的记录
     */
    @Override
    public List<GameCountDto> selectAllInformationListByCondition(PlayerModel playerModel) {

        List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("  game_count.id, ");
        sql.append(" game_count.infor_id, ");
        sql.append(" game_count.all_games_count, ");
        sql.append(" game_count.success_count, ");
        sql.append(" information.player_name, ");
        sql.append(" game_count.date, ");
        sql.append(" game_count.game_status, ");
        sql.append(" game_count.role_id, ");
        sql.append(" game_count.all_werewolf_count, ");
        sql.append(" game_count.success_werewolf_count, ");
        sql.append(" game_count.all_prophet_count, ");
        sql.append(" game_count.success_prophet_count, ");
        sql.append(" game_count.all_witch_count, ");
        sql.append(" game_count.success_witch_count, ");
        sql.append(" game_count.all_hunter_count, ");
        sql.append(" game_count.success_hunter_count, ");
        sql.append(" game_count.all_civilian_count, ");
        sql.append(" game_count.success_civilian_count, ");
        sql.append(" cast(avg((game_count.success_count)/(game_count.all_games_count)*100)as decimal(10,2)) as rate, ");
        sql.append(" cast(avg((game_count.success_werewolf_count)/(game_count.all_werewolf_count)*100)as decimal(10,2)) as werewolfRate, ");
        sql.append(" cast(avg((game_count.success_prophet_count)/(game_count.all_prophet_count)*100)as decimal(10,2)) as prophetRate, ");
        sql.append(" cast(avg((game_count.success_witch_count)/(game_count.all_witch_count)*100)as decimal(10,2)) as witchRate, ");
        sql.append(" cast(avg((game_count.success_hunter_count)/(game_count.all_hunter_count)*100)as decimal(10,2)) as hunterRate, ");
        sql.append(" cast(avg((game_count.success_civilian_count)/(game_count.all_civilian_count)*100)as decimal(10,2)) as civilianRate ");
        sql.append(" FROM");
        sql.append("  game_count");
        sql.append("  LEFT JOIN");
        sql.append("  information");
        sql.append("  ON");
        sql.append("  game_count.infor_id=information.id");
        sql.append(" WHERE");
        sql.append(" 1=1 ");

        if (!StringUtils.isNullOrEmpty(playerModel.getInforId())) {
            sql.append(" AND ");
            sql.append(" game_count.infor_id=? ");
            paramList.add(playerModel.getInforId());
        }

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
        sql.append(" success_count, ");
        sql.append(" date, ");
        sql.append(" game_status, ");
        sql.append(" role_id, ");
        sql.append(" all_werewolf_count, ");
        sql.append(" success_werewolf_count, ");
        sql.append(" all_prophet_count, ");
        sql.append(" success_prophet_count, ");
        sql.append(" all_witch_count, ");
        sql.append(" success_witch_count, ");
        sql.append(" all_hunter_count, ");
        sql.append(" success_hunter_count, ");
        sql.append(" all_civilian_count, ");
        sql.append(" success_civilian_count ");
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

        Object[] paramer = new Object[]{playerModel.getInforId(), playerModel.getDate(), playerModel.getGameStatus(), playerModel.getRoleId()};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 插入玩家信息
     */
    @Override
    public void insertPlayer(InformationModel informationModel) {

        final StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO ");
        sql.append(" information ");
        sql.append(" ( ");
        sql.append(" player_name, ");
        sql.append(" age, ");
        sql.append(" gender ");
        sql.append(" ) ");
        sql.append(" values ");
        sql.append(" ( ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ? ");
        sql.append(" ) ");

        System.out.println(sql.toString());
        System.out.println(informationModel.getPlayerName());
        Object[] paramer = new Object[]{informationModel.getPlayerName(), informationModel.getAge(), informationModel.getGender()};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 插入游戏统计记录
     */
    @Override
    public void insertInformation(PlayerModel playerModel) {

        final StringBuilder sql = new StringBuilder();
        int successCount = 0;
        int allGamesCount = 1;
        int allWerewolfCount = 0;
        int successWerewolfCount = 0;
        int allProphetCount = 0;
        int successProphetCount = 0;
        int allWitchCount = 0;
        int successWitchCount = 0;
        int allHunterCount = 0;
        int successHunterCount = 0;
        int allCivilianCount = 0;
        int successCivilianCount = 0;
        int inforId = Integer.valueOf(playerModel.getInforId());
        String gameStatus = playerModel.getGameStatus();
        int roleId = Integer.valueOf(playerModel.getRoleId());
        String date = playerModel.getDate();

        sql.append(" INSERT INTO ");
        sql.append(" game_count ");
        sql.append(" ( ");
        sql.append(" infor_id, ");
        sql.append(" all_games_count, ");
        sql.append(" success_count, ");
        sql.append(" date, ");
        sql.append(" game_status, ");
        sql.append(" role_id, ");
        sql.append(" all_werewolf_count, ");
        sql.append(" success_werewolf_count, ");
        sql.append(" all_prophet_count, ");
        sql.append(" success_prophet_count, ");
        sql.append(" all_witch_count, ");
        sql.append(" success_witch_count, ");
        sql.append(" all_hunter_count, ");
        sql.append(" success_hunter_count, ");
        sql.append(" all_civilian_count, ");
        sql.append(" success_civilian_count ");
        sql.append(" ) ");
        sql.append(" values ");
        sql.append(" ( ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ? ");
        sql.append(" ) ");

        if ("success".equals(gameStatus)) {
            successCount = 1;
        } else {
            successCount = 0;
        }

        if (roleId == 1) {
            allProphetCount = 1;
            if ("success".equals(gameStatus)) {
                successProphetCount = 1;
            }
        }

        if (roleId == 2) {
            allWitchCount = 1;
            if ("success".equals(gameStatus)) {
                successWitchCount = 1;
            }
        }

        if (roleId == 3) {
            allHunterCount = 1;
            if ("success".equals(gameStatus)) {
                successHunterCount = 1;
            }
        }

        if (roleId == 4) {
            allWerewolfCount = 1;
            if ("success".equals(gameStatus)) {
                successWerewolfCount = 1;
            }
        }

        if (roleId == 5) {
            allCivilianCount = 1;
            if ("success".equals(gameStatus)) {
                successCivilianCount = 1;
            }
        }

        System.out.println(sql.toString());

        Object[] paramer = new Object[]{inforId, allGamesCount, successCount, date, gameStatus, roleId, allWerewolfCount, successWerewolfCount
                , allProphetCount, successProphetCount, allWitchCount, successWitchCount, allHunterCount, successHunterCount, allCivilianCount, successCivilianCount};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 更新游戏局数记录
     */
    @Override
    public void updateInformation(PlayerModel playerModel) {

        final StringBuilder sql = new StringBuilder();
        int dbSuccessCount = 0;
        String gameStatus = playerModel.getGameStatus();
        int successCount = playerModel.getSuccessCount();
        int allGamesCount = playerModel.getAllGamesCount();
        int inforId = Integer.valueOf(playerModel.getInforId());
        int allWerewolfCount = playerModel.getAllWerewolfCount();
        int successWerewolfCount = playerModel.getSuccessWerewolfCount();
        int allProphetCount = playerModel.getAllProphetCount();
        int successProphetCount = playerModel.getSuccessProphetCount();
        int allWitchCount = playerModel.getAllWitchCount();
        int successWitchCount = playerModel.getSuccessWitchCount();
        int allHunterCount = playerModel.getAllHunterCount();
        int successHunterCount = playerModel.getSuccessHunterCount();
        int allCivilianCount = playerModel.getAllCivilianCount();
        int successCivilianCount = playerModel.getSuccessCivilianCount();
        int roleId = Integer.valueOf(playerModel.getRoleId());
        String date = playerModel.getDate();

        sql.append(" UPDATE  game_count ");
        sql.append(" SET ");
        sql.append(" all_games_count=?, ");
        sql.append(" success_count=?, ");
        sql.append(" date=?, ");
        sql.append(" game_status=?, ");
        sql.append(" role_id=?, ");
        sql.append(" all_werewolf_count=?, ");
        sql.append(" success_werewolf_count=?, ");
        sql.append(" all_prophet_count=?, ");
        sql.append(" success_prophet_count=?, ");
        sql.append(" all_witch_count=?, ");
        sql.append(" success_witch_count=?, ");
        sql.append(" all_hunter_count=?, ");
        sql.append(" success_hunter_count=?, ");
        sql.append(" all_civilian_count=?, ");
        sql.append(" success_civilian_count=? ");

        sql.append(" WHERE");
        sql.append(" infor_id= ? ");
        if ("success".equals(gameStatus)) {
            dbSuccessCount = successCount + 1;
        } else {
            dbSuccessCount = successCount;
        }
        allGamesCount = allGamesCount + 1;

        if (roleId == 1) {
            allProphetCount = allProphetCount + 1;
            if ("success".equals(gameStatus)) {
                successProphetCount = 1;
            }
        }

        if (roleId == 2) {
            allWitchCount = allWitchCount + 1;
            if ("success".equals(gameStatus)) {
                successWitchCount = 1;
            }
        }

        if (roleId == 3) {
            allHunterCount = allHunterCount + 1;
            if ("success".equals(gameStatus)) {
                successHunterCount = 1;
            }
        }

        if (roleId == 4) {
            allWerewolfCount = allHunterCount + 1;
            if ("success".equals(gameStatus)) {
                successWerewolfCount = 1;
            }
        }

        if (roleId == 5) {
            allCivilianCount = allCivilianCount + 1;
            if ("success".equals(gameStatus)) {
                successCivilianCount = allHunterCount + 1;
            }
        }

        System.out.println(sql.toString());

        Object[] paramer = new Object[]{allGamesCount, dbSuccessCount, date, gameStatus, roleId, allWerewolfCount, successWerewolfCount
                , allProphetCount, successProphetCount, allWitchCount, successWitchCount, allHunterCount, successHunterCount, allCivilianCount, successCivilianCount, inforId};
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
            gameCountDto.setDate(rs.getDate("date"));
            gameCountDto.setGamestatus(rs.getString("game_status"));
            gameCountDto.setRoleId(rs.getInt("role_id"));
            gameCountDto.setAllWerewolfCount(rs.getInt("all_werewolf_count"));
            gameCountDto.setSuccessWerewolfCount(rs.getInt("success_werewolf_count"));
            gameCountDto.setAllProphetCount(rs.getInt("all_prophet_count"));
            gameCountDto.setSuccessProphetCount(rs.getInt("success_prophet_count"));
            gameCountDto.setAllWitchCount(rs.getInt("all_witch_count"));
            gameCountDto.setSuccessWitchCount(rs.getInt("success_witch_count"));
            gameCountDto.setAllHunterCount(rs.getInt("all_hunter_count"));
            gameCountDto.setSuccessHunterCount(rs.getInt("success_hunter_count"));
            gameCountDto.setAllCivilianCount(rs.getInt("all_civilian_count"));
            gameCountDto.setSuccessCivilianCount(rs.getInt("success_civilian_count"));

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
            gameCountDto.setDate(rs.getDate("date"));
            gameCountDto.setGamestatus(rs.getString("game_status"));
            gameCountDto.setRoleId(rs.getInt("role_id"));
            gameCountDto.setAllWerewolfCount(rs.getInt("all_werewolf_count"));
            gameCountDto.setSuccessWerewolfCount(rs.getInt("success_werewolf_count"));
            gameCountDto.setAllProphetCount(rs.getInt("all_prophet_count"));
            gameCountDto.setSuccessProphetCount(rs.getInt("success_prophet_count"));
            gameCountDto.setAllWitchCount(rs.getInt("all_witch_count"));
            gameCountDto.setSuccessWitchCount(rs.getInt("success_witch_count"));
            gameCountDto.setAllHunterCount(rs.getInt("all_hunter_count"));
            gameCountDto.setSuccessHunterCount(rs.getInt("success_hunter_count"));
            gameCountDto.setAllCivilianCount(rs.getInt("all_civilian_count"));
            gameCountDto.setSuccessCivilianCount(rs.getInt("success_civilian_count"));
            gameCountDto.setWerewolfRate(rs.getFloat("werewolfRate"));
            gameCountDto.setProphetRate(rs.getFloat("prophetRate"));
            gameCountDto.setWitchRate(rs.getFloat("witchRate"));
            gameCountDto.setHunterRate(rs.getFloat("hunterRate"));
            gameCountDto.setCivilianRate(rs.getFloat("civilianRate"));
            return gameCountDto;
        }
    }
}
