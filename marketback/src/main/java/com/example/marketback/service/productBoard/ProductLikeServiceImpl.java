package com.example.marketback.service.productBoard;

import com.example.marketback.entity.member.Member;
import com.example.marketback.entity.productBoard.ProductBoard;
import com.example.marketback.entity.productBoard.ProductLike;
import com.example.marketback.repository.member.MemberRepository;
import com.example.marketback.repository.productBoard.ProductLikeRepository;
import com.example.marketback.repository.productBoard.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductLikeServiceImpl implements ProductLikeService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductLikeRepository productLikeRepository;

    @Override
    public boolean register(Long productNo, Long memberNo, ProductLike productLike) {
        ProductBoard productBoard = productRepository.findById(productNo).orElseThrow();
        Member member = memberRepository.findById(memberNo).orElseThrow();

        if (productLikeRepository.findByMemberAndProductBoard(member, productBoard).isEmpty()) {
            productLike.setMember(member);
            productLike.setProductBoard(productBoard);
            productLikeRepository.save(productLike);
            return true;
        } else {
            Optional<ProductLike> maybeLikes = productLikeRepository.findByMemberAndProductBoard(member, productBoard);
            productLikeRepository.deleteById(maybeLikes.get().getLikeNo());
            return false;
        }
    }

    @Override
    public ProductLike likes(Long productNo, Long memberNo) {
        Optional<ProductLike> maybeReadLikes = productLikeRepository.findLikesProductNoAndMemberNo(Long.valueOf(productNo), Long.valueOf(memberNo));
        if (maybeReadLikes.equals(Optional.empty())) {
            return null;
        }
        return maybeReadLikes.get();
    }

    @Override
    public List<ProductLike> list(Long productNo) {
        return  productLikeRepository.findByProductNo(Long.valueOf(productNo));
    }

    @Override
    public List<ProductLike> myLikes(Long memberNo) {
        return productLikeRepository.findByMemberNo(Long.valueOf(memberNo));
    }
}
