package com.example.marketback.service.productBoard;
import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void register(ProductBoard productBoard, Long memberNo) {
        Optional<Member> maybeMember = memberRepository.findById(Long.valueOf(memberNo));
        productBoard.setMember(maybeMember.get());
        productRepository.save(productBoard);
    }

    @Override
    public List<ProductBoard> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "viewCnt"));
    }


    @Override
    public ProductBoard read(Integer productNo) {
        Optional<ProductBoard> maybeReadProductBoard = productRepository.findById(Long.valueOf(productNo));

        if (maybeReadProductBoard.equals(Optional.empty())) {
            return null;
        } else {
            ProductBoard productBoard = maybeReadProductBoard.get();
            productBoard.increaseViewCnt();;
            productRepository.save(productBoard);
            return maybeReadProductBoard.get();
        }
    }

    @Override
    public void modify(ProductBoard productBoard, Long memberNo) {
        Optional<Member> maybeMember = memberRepository.findById(Long.valueOf(memberNo));
        productBoard.setMember(maybeMember.get());
        productRepository.save(productBoard);
    }

    @Override
    public void remove(Long productNo) {
        productRepository.deleteById(Long.valueOf(productNo));
    }

    @Override
    public List<ProductBoard> searchList(String keyWord) {
        List<ProductBoard> findSearchList = productRepository.findByContentContaining(keyWord);

        return findSearchList;
    }

    @Override
    public List<ProductBoard> productListOfMember(Long memberNo) {
        return productRepository.findByMemberNoInProductBoard(Long.valueOf(memberNo));
    }

    @Override
    public List<ProductBoard> buyerList(Long memberNo) {
        return productRepository.findByBuyer(Long.valueOf(memberNo));

    }
}
