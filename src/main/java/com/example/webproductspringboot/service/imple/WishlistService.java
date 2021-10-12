package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.entity.WishlistEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.reponsitory.IWishlistReponsitory;
import com.example.webproductspringboot.service.intf.IWishlistService;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.WishlistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WishlistService extends AbstractService implements IWishlistService {

    @Autowired
    private IWishlistReponsitory _iWishlistReponsitory;

    protected WishlistService(HttpServletRequest request) {
        super(request);
    }

    @Override
    public List<WishlistVo> findAllByUserLogin(String id) {
        return _iWishlistReponsitory.findAllByUserLogin(id).stream().map(e -> (WishlistVo) map(e)).collect(Collectors.toList());
    }

    @Override
    public WishlistVo save(WishlistVo wishlistVo) {
        WishlistEntity entity = (WishlistEntity) map(wishlistVo);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userLogin = getUserLogin();
        if (userLogin == null) throw new IllegalArgumentException("Vui lòng đăng nhập.");
        entity.setId(UUID.randomUUID().toString());
        entity.setIdVisit(userLogin);
        entity = _iWishlistReponsitory.save(entity);
        return (WishlistVo) map(entity);
    }

    @Override
    public void delete(WishlistVo wishlistVo) {
        _iWishlistReponsitory.deleteById(wishlistVo.getId());
    }

    @Override
    public WishlistVo existWishlist(String idProduct) {
        UserEntity userLogin = getUserLogin();
        if (userLogin == null) throw new IllegalArgumentException("Vui lòng đăng nhập.");
        Optional<WishlistEntity> optional = _iWishlistReponsitory.existWishlist(idProduct, userLogin.getId());
        return optional.isEmpty() ? null : (WishlistVo) map(optional.get());
    }
}
