package com.ssm.controller;

import com.ssm.service.HistoryService;
import com.ssm.vo.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HistoryController {

  @Resource
  private HistoryService historyService;

  @ResponseBody
  @RequestMapping(value = "/getHistory", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R getHistory(@RequestHeader("token") String token){
    if (token != null && token != "") return R.ok().setData(historyService.getTotalHistoryByUname(token));
    else return R.error().setMessage("用户未登录");
  }

  @ResponseBody
  @RequestMapping(value = "/addHistory", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
  public R addHistory(@RequestHeader("token") String token, int g_id){
    if (historyService.addHistory(g_id, token) != 0) return R.ok();
    else return R.error().setMessage("添加历史记录失败");
  }

  @ResponseBody
  @RequestMapping(value = "/deleteHistory", method = RequestMethod.POST)
  public R deleteHistory(@RequestHeader("token") String token, @RequestBody List<Integer> delList){
    if (historyService.deleteHistory(delList, token) != 0) return R.ok();
    else return R.error().setMessage("删除历史记录失败");
  }
}
