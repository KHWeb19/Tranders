package com.example.marketback.service.search;

import com.example.marketback.entity.jpa.community.CommunityBoard;
import com.example.marketback.entity.manager.BanMember;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.jpa.community.CommunityBoardRepository;
import com.example.marketback.repository.manager.BanMemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import com.example.marketback.request.KeyWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchKeyWordServiceImpl implements SearchKeyWordService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private BanMemberRepository banMemberRepository;

    @Override
    public List<ProductBoard> searchProduct(KeyWordRequest keyWordRequest) {
        Pageable pageable = PageRequest.of(keyWordRequest.getPage(), 3, Sort.Direction.DESC, "regDate");

        if(keyWordRequest.getRegion().charAt(keyWordRequest.getRegion().length() -1) == '동'){
            return productRepository.findAllByVillageNameContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }else if(keyWordRequest.getRegion().charAt(keyWordRequest.getRegion().length() -1) == '구'){
            return productRepository.findAllByDistrictContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }else {
            return productRepository.findAllByCityContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }
    }

    @Override
    public List<CommunityBoard> searchAllComm(KeyWordRequest keyWordRequest) {
        Pageable pageable = PageRequest.of(keyWordRequest.getPage(), 3, Sort.Direction.DESC, "createdDate");
        List<CommunityBoard> communityBoardList;

        List<CommunityBoard> response = new ArrayList<>();

        if(keyWordRequest.getRegion().charAt(keyWordRequest.getRegion().length() -1) == '동'){
            communityBoardList = communityBoardRepository.findAllByVillageNameContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }else if(keyWordRequest.getRegion().charAt(keyWordRequest.getRegion().length() -1) == '구'){
            communityBoardList = communityBoardRepository.findAllByDistrictContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }else {
            communityBoardList = communityBoardRepository.findAllByCityContentContain(keyWordRequest.getKeyWord(), keyWordRequest.getRegion(), pageable);
        }

        for(CommunityBoard communityBoard : communityBoardList){
            communityBoard.setNear(null);
            response.add(communityBoard);
        }

        return response;
    }

    /*int checkRegion(String region){
        if(region.charAt(region.length() -1) == '동'){
            return 2;
        }else if(region.charAt(region.length() -1) == '구'){
            return 1;
        }else {
            return 0;
        }
    }*/
}
