package com.mvc.dao;

import java.util.List;

import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.PlayerModel;

public interface PlayerDao {

    public List<InformationDto> selectAllPlayer();
    public List<RoleDto> selectAllRole();
    public void insert(PlayerModel playerModel);
    public List<PlayerDto> selectPlayerList();
    public List<GameCountDto> selectInformationList(int inforId);
    public void insertInformation(PlayerModel playerModel);
    public void updateInformation(PlayerModel playerModel);
    public List<GameCountDto> selectAllInformationList();
    public List<GameCountDto> selectAllInformationListByCondition(PlayerModel playerModel);
    public List<PlayerDto> selectPlayerListByCondition(PlayerModel playerModel);

}
