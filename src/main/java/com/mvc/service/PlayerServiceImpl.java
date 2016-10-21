package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.PlayerDao;
import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.PlayerModel;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerDao playerDao = null;

    @Override
    public List<InformationDto> selectPlayer() {
        return playerDao.selectAllPlayer();
    }

    @Override
    public List<RoleDto> selectRole() {
        return playerDao.selectAllRole();
    }


    /**
     * 新增player
     */
    @Override
    public void addPlayer(PlayerModel playerModel) {

        playerDao.insert(playerModel);

    }

    /**
     * 新增玩家游戏记录
     */
    @Override
    public void addInformation(PlayerModel playerModel) {

        playerDao.insertInformation(playerModel);

    }

    /**
     * 更新玩家游戏记录
     */
    @Override
    public void updateInformation(PlayerModel playerModel) {

        playerDao.updateInformation(playerModel);

    }

    @Override
    public List<PlayerDto> selectPlayerList() {
        return playerDao.selectPlayerList();
    }

    @Override
    public List<GameCountDto> selectInformationList(String inforId) {
        return playerDao.selectInformationList(Integer.valueOf(inforId));
    }

    @Override
    public List<GameCountDto> selectInformationList() {
        return playerDao.selectAllInformationList();
    }

    @Override
    public List<GameCountDto> selectInformationListByCondition(PlayerModel playerModel){
        return playerDao.selectAllInformationListByCondition(playerModel);
    }

    @Override
    public List<PlayerDto> selectPlayerListByCondition(PlayerModel playerModel) {
        return playerDao.selectPlayerListByCondition(playerModel);
    }

}
