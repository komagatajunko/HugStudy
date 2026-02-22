package com.example.tukatta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tukatta.dto.TukattaRequest;
import com.example.tukatta.dto.TukattaUpdateRequest;
import com.example.tukatta.entity.TukattaEntity;
import com.example.tukatta.repository.TukattaRepository;

/**
* 支出情報 Service
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class TukattaService {

    /**
     * 支出情報 Repository
     */
    @Autowired
    private TukattaRepository tukattaRepository;

    /**
     * 支出情報 全検索
     * @return  検索結果
     */
    public List<TukattaEntity> searchAll() {
        return tukattaRepository.findAll();
    }
    
    /**
     * 支出情報 主キー検索
     * @return 検索結果
     */
    public TukattaEntity findById(Integer id) {
      return tukattaRepository.getOne(id);
    }


    /**
     * 支出情報 新規登録
     * @param 
     */
    public void create(TukattaRequest tukattaRequest) {
      TukattaEntity sisyutu = new TukattaEntity();
      sisyutu.setPayDate(tukattaRequest.getPayDate());
      sisyutu.setAmount(tukattaRequest.getAmount());
      sisyutu.setMethod(tukattaRequest.getMethod());
      sisyutu.setShop(tukattaRequest.getShop());
      tukattaRepository.save(sisyutu);
    }
    
    /**
     * 支出情報 更新
     * @param 
     */
    public void update(TukattaUpdateRequest tukattaUpdateRequest) {
      TukattaEntity sisyutu = findById(tukattaUpdateRequest.getId());
      sisyutu.setPayDate(tukattaUpdateRequest.getPayDate());
      sisyutu.setAmount(tukattaUpdateRequest.getAmount());
      sisyutu.setMethod(tukattaUpdateRequest.getMethod());
      sisyutu.setShop(tukattaUpdateRequest.getShop());
      tukattaRepository.save(sisyutu);
    }
    
    /**
     * 支出情報 物理削除
     * @param id ユーザーID
     */
    public void delete(Integer id) {
        TukattaEntity sisyutu = findById(id);
        tukattaRepository.delete(sisyutu);
    }
}
