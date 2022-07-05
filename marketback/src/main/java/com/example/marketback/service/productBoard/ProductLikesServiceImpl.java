package com.example.marketback.service.productBoard;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLikes;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductLikesRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductLikesServiceImpl implements ProductLikesService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductLikesRepository productLikesRepository;

    @Override
    public boolean register(Long productNo, Long memberNo, ProductLikes productLikes) {
        ProductBoard productBoard = productRepository.findById(productNo).orElseThrow();
        Member member = memberRepository.findById(memberNo).orElseThrow();

        if (productLikesRepository.findByMemberAndProductBoard(member, productBoard).isEmpty()) {
            productLikes.setMember(member);
            productLikes.setProductBoard(productBoard);
            productLikesRepository.save(productLikes);

            return true;
        } else {
            Optional<ProductLikes> maybeProductLikes = productLikesRepository.findByMemberAndProductBoard(member, productBoard);
            productLikesRepository.deleteById(maybeProductLikes.get().getProductLikeNo());

            return false;
        }
    }

    @Override
    public ProductLikes productLikes(Long productNo, Long memberNo) {
        Optional<ProductLikes> maybeProductReadLikes = productLikesRepository.findLikesProductBoardAndMemberNo(Long.valueOf(productNo), Long.valueOf(memberNo));

        if (maybeProductReadLikes.equals(Optional.empty())) {
            return null;
        }
        return maybeProductReadLikes.get();
    }

    @Override
    public List<ProductLikes> list(Long productNo) {
        return  productLikesRepository.findByProductNo(Long.valueOf(productNo));
    }

    @Override
    public List<ProductLikes> myLikes(Long memberNo) {
        return productLikesRepository.findByMemberNo(Long.valueOf(memberNo));
    }
}
