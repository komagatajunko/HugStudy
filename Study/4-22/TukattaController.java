package com.example.tukatta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tukatta.dto.TukattaRequest;
import com.example.tukatta.dto.TukattaUpdateRequest;
import com.example.tukatta.entity.TukattaEntity;
import com.example.tukatta.service.TukattaService;

/**
* 支出情報 Controller
*/
@Controller
public class TukattaController {

    /**
     * 支出情報 Service
     */
       //使用クラスのインスタンス化
     @Autowired
     TukattaService tukattaService;
    
    /**
     * 支出情報一覧画面を表示
     * @param model Model
     * @return 支出情報一覧画面のHTML
     */
    @RequestMapping("/sisyutu/list")
    public String sisyutuList(Model model) {
      List<TukattaEntity> sisyutulist = tukattaService.searchAll();
      model.addAttribute("sisyutulist", sisyutulist);
      return "sisyutu/list";
    }
    
    /**
     * 詳細画面を表示
     * @param id 表示するユーザーID
     * @param model Model
     * @return 詳細画面
     */
    @GetMapping("/sisyutu/{id}")
    public String sisyutuDetail(@PathVariable Integer id, Model model) {
      TukattaEntity sisyutu = tukattaService.findById(id);
      model.addAttribute("sisyutuData", sisyutu);
      return "sisyutu/view";
    }

    
    /**
     * 新規登録画面を表示
     * @param model Model
     * @return 
     */
    @RequestMapping("/sisyutu/add")
    public String sisyutuRegister(Model model) {
      model.addAttribute("tukattaRequest", new TukattaRequest());
      return "sisyutu/add";
    }
    
    /**
     * 新規登録
     * @param tukattaRequest リクエストデータ
     * @param model Model
     * @return 一覧画面
     */
    @RequestMapping("/sisyutu/create")
    public String sisyutuCreate(@Validated @ModelAttribute TukattaRequest tukattaRequest, BindingResult result, Model model) {
      if (result.hasErrors()) {
        // 入力チェックエラーの場合
        List<String> errorList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
          errorList.add(error.getDefaultMessage());
        }
        //エラー判定後の画面遷移
        model.addAttribute("validationError", errorList);
        return "sisyutu/add";
      }
      // ユーザー情報の登録
      tukattaService.create(tukattaRequest);
      return "redirect:/sisyutu/list";
    }
    
    /**
     * 編集画面を表示
     * @param id 表示するID
     * @param model Model
     * @return 編集画面
     */
    @GetMapping("/sisyutu/{id}/edit")
    public String sisyutuEdit(@PathVariable Integer id, Model model) {
      /**
      * 編集対象のユーザー情報を取得
      */
      TukattaEntity sisyutu = tukattaService.findById(id);
      // 編集画面用のDTOに格納
      TukattaUpdateRequest tukattaUpdateRequest = new TukattaUpdateRequest();
      tukattaUpdateRequest.setId(sisyutu.getId());
      tukattaUpdateRequest.setPayDate(sisyutu.getPayDate());
      tukattaUpdateRequest.setAmount(sisyutu.getAmount());
      tukattaUpdateRequest.setMethod(sisyutu.getMethod());
      tukattaUpdateRequest.setShop(sisyutu.getShop());
      model.addAttribute("tukattaUpdateRequest", tukattaUpdateRequest);
      return "sisyutu/edit";
    }
    /**
     * 更新
     * @param userRequest リクエストデータ
     * @param model Model
     * @return 詳細画面
     */
    @RequestMapping("/sisyutu/update")
    public String sisyutuUpdate(@Validated @ModelAttribute TukattaUpdateRequest tukattaUpdateRequest, BindingResult result, Model model) {
      if (result.hasErrors()) {
        List<String> errorList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
          errorList.add(error.getDefaultMessage());
        }
        model.addAttribute("validationError", errorList);
        return "sisyutu/edit";
      }
      // ユーザー情報の更新
      tukattaService.update(tukattaUpdateRequest);
      return String.format("redirect:/sisyutu/%d", tukattaUpdateRequest.getId());
    }
    
    /**
     * 支出情報削除
     * @param id 表示するID
     * @param model Model
     * @return 詳細画面
     */
    @GetMapping("/sisyutu/{id}/delete")
    public String sisyutuDelete(@PathVariable Integer id, Model model) {
        // ユーザー情報の削除
        tukattaService.delete(id);
        return "redirect:/sisyutu/list";
    }

}
