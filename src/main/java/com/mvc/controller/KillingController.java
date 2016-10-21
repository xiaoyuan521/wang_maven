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
        List<GameCountDto> informationList = playerService.selectInformationList(inforId);
        if (informationList.size() == 0) {
            playerService.addInformation(playerModel);
        } else {
            int successCount = informationList.get(0).getSuccessCount();
            int allGamesCount = informationList.get(0).getAllGamesCount();
            int allWerewolfCount = informationList.get(0).getAllWerewolfCount();
            int successWerewolfCount = informationList.get(0).getSuccessWerewolfCount();
            int allProphetCount = informationList.get(0).getAllProphetCount();
            int successProphetCount = informationList.get(0).getSuccessProphetCount();
            int allWitchCount = informationList.get(0).getAllWitchCount();
            int successWitchCount = informationList.get(0).getSuccessWitchCount();
            int allHunterCount = informationList.get(0).getAllHunterCount();
            int successHunterCount = informationList.get(0).getSuccessHunterCount();
            int allCivilianCount = informationList.get(0).getAllCivilianCount();
            int successCivilianCount = informationList.get(0).getSuccessCivilianCount();

            playerModel.setSuccessCount(successCount);
            playerModel.setAllGamesCount(allGamesCount);
            playerModel.setAllWerewolfCount(allWerewolfCount);
            playerModel.setSuccessWerewolfCount(successWerewolfCount);
            playerModel.setAllProphetCount(allProphetCount);
            playerModel.setSuccessProphetCount(successProphetCount);
            playerModel.setAllWitchCount(allWitchCount);
            playerModel.setSuccessWitchCount(successWitchCount);
            playerModel.setAllHunterCount(allHunterCount);
            playerModel.setSuccessHunterCount(successHunterCount);
            playerModel.setAllCivilianCount(allCivilianCount);
            playerModel.setSuccessCivilianCount(successCivilianCount);
            playerService.updateInformation(playerModel);
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

        List<GameCountDto> informationDtoList = playerService.selectInformationListByCondition(playerModel);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("informationDtoList", informationDtoList);

        return bulidReturnMap("ok", resultMap);

    }

    /**
     * 检索游戏记录查询
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "playerRecordSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> playerRecordSearch(@RequestBody PlayerModel playerModel) {

        List<PlayerDto> playerListList = playerService.selectPlayerListByCondition(playerModel);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("playerListList", playerListList);

        return bulidReturnMap("ok", resultMap);

    }

    public Map<String, Object> bulidReturnMap(String code, Object result) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("code", code);
        returnMap.put("result", result);

        return returnMap;

    }

}
