package com.mvc.service;

import java.util.List;

import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.PlayerModel;

public interface PlayerService {

    public List<InformationDto> selectPlayer();

    public List<RoleDto> selectRole();

    public void addPlayer(PlayerModel playerModel);

    public List<PlayerDto> selectPlayerList();

    public List<GameCountDto> selectInformationList(String inforId);

    public void addInformation(String inforId, String gameStatus);

    public void updateInformation(String inforId,String gameStatus, int successCount,int allGamesCount);

    public List<GameCountDto> selectInformationList();

}
