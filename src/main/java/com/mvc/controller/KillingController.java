package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.PlayerModel;
import com.mvc.service.PlayerService;

@Controller
public class KillingController {

    @Autowired
    PlayerService playerService = null;

    @RequestMapping(value = "/killing")
    public String killing() {
        return "killing";
    }

    @RequestMapping(value = "/killRateSearch")
    public String killRateSearch() {
        return "killRateSearch";
    }

    /**
     * player一览
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "killing/init", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> init() {

        List<InformationDto> informationDtoList = playerService.selectPlayer();
        List<RoleDto> roleDtoList = playerService.selectRole();
        List<PlayerDto> playerDtoList = playerService.selectPlayerList();
        List<GameCountDto> informationRateList = playerService.selectInformationList();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("informationDtoList", informationDtoList);
        resultMap.put("roleDtoList", roleDtoList);
        resultMap.put("playerDtoList", playerDtoList);
        resultMap.put("informationRateList", informationRateList);

        return bulidReturnMap("ok", resultMap);

    }

    /**
     * 新增player信息
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "playerAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> playerAdd(@RequestBody PlayerModel playerModel) {

        playerService.addPlayer(playerModel);

        String inforId = playerModel.getInforId();
        String gameStatus = playerModel.getGameStatus();
        List<GameCountDto> informationList = playerService.selectInformationList(inforId);
        if (informationList.size() == 0) {
            playerService.addInformation(inforId, gameStatus);
        } else {
            int successCount = informationList.get(0).getSuccessCount();
            int allGamesCount = informationList.get(0).getAllGamesCount();
            playerService.updateInformation(inforId, gameStatus, successCount, allGamesCount);
        }
        List<PlayerDto> playerDtoList = playerService.selectPlayerList();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("playerDtoList", playerDtoList);

        return bulidReturnMap("ok", resultMap);

    }

    /**
     * 检索游戏胜率信息
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "gameCountSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> gameCountSearch(@RequestBody PlayerModel playerModel) {

        List<GameCountDto> informationDtoList = playerService.selectInformationList();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("informationDtoList", informationDtoList);

        return bulidReturnMap("ok", resultMap);

    }

    public Map<String, Object> bulidReturnMap(String code, Object result) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("code", code);
        returnMap.put("result", result);

        return returnMap;

    }

}
