package com.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.dto.GameCountDto;
import com.mvc.dto.InformationDto;
import com.mvc.dto.PlayerDto;
import com.mvc.dto.RoleDto;
import com.mvc.model.InformationModel;
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

    @RequestMapping(value = "/killerAdd")
    public String killerAdd() {
        return "killerAdd";
    }

    @RequestMapping(value = "/barGraph")
    public String barGraph() {
        return "barGraph";
    }

    @RequestMapping(value = "/pieGraph")
    public String pieGraph() {
        return "pieGraph";
    }

    @RequestMapping(value = "/lineGraph")
    public String lineGraph() {
        return "lineGraph";
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
     * 新增玩家信息
     *
     * @param studentModel
     * @return
     */
    @RequestMapping(value = "/killerAddInit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> killerAddInit(@RequestBody InformationModel informationModel) {

        playerService.addKiller(informationModel);

        Map<String, Object> resultMap = new HashMap<String, Object>();

        return bulidReturnMap("ok", null);

    }

    /**
     * 新增游戏记录
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
     * 检索游戏胜率信息
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "chartGraphSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> chartGraphSearch(@RequestBody PlayerModel playerModel) {
        List<RoleDto> roleDtoList = playerService.selectRole();

        List<RoleDto> chartGraphList = new ArrayList<RoleDto>();

        RoleDto chartGraphData;
        for (RoleDto roleListData : roleDtoList) {
            chartGraphData = new RoleDto();
            chartGraphData.setRole(roleListData.getRole());
            Double npl = playerService.getNplData(roleListData.getId(), playerModel.getInforId());

            if(npl==null){
                chartGraphData.setNPL(0);
            }else{
                chartGraphData.setNPL(npl);
                chartGraphList.add(chartGraphData);
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("chartGraphList", chartGraphList);

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

    /**
     * player一览
     *
     * @param playerModel
     * @return
     */
    @RequestMapping(value = "killing/barGraphInit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> barGraphInit() {

        List<GameCountDto> informationRateList = playerService.selectInformationList();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("informationRateList", informationRateList);

        return bulidReturnMap("ok", resultMap);

    }

    /**
     * 头像上传
     *
     * @param playerModel
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException, FileUploadException {
        String filename = file.getOriginalFilename();
        String projpath = "";
        projpath = request.getSession().getServletContext().getRealPath("/WEB-INF/views/upload");
        String responseString = filename;

        if (ServletFileUpload.isMultipartContent(request)) {

            File savedFile = new File(projpath, filename);

            if (savedFile.exists()) {
                savedFile.delete();
            }

            if (!savedFile.exists()) {
                savedFile.mkdirs();
            }

            // 保存
            try {
                file.transferTo(savedFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            byte[] responseBytes = responseString.getBytes();
            response.setContentLength(responseBytes.length);
            ServletOutputStream output = response.getOutputStream();
            output.write(responseBytes);
            output.flush();

        }

        Map<String, Object> resultMap = new HashMap<String, Object>();

        return bulidReturnMap("ok", resultMap);

    }

    public Map<String, Object> bulidReturnMap(String code, Object result) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("code", code);
        returnMap.put("result", result);

        return returnMap;

    }

}
