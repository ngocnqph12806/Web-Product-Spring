package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.vo.WishlistVo;

import java.util.List;

public interface IWishlistService {

    List<WishlistVo> findAllByUserLogin();

    WishlistVo save(WishlistVo wishlistVo);

    void delete(WishlistVo wishlistVo);

    WishlistVo existWishlist(String idProduct);
}
