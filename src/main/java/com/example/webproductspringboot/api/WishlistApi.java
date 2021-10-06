package com.example.webproductspringboot.api;

import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IWishlistService;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.WishlistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/wish-list")
public class WishlistApi extends AbstractApi {

    @Autowired
    private IWishlistService _iWishlistService;

    protected WishlistApi(HttpServletRequest request) {
        super(request);
    }

    @GetMapping(params = "single")
    private ResponseEntity<?> getAllByUserLogin() {
        return ResponseEntity.ok(_iWishlistService.findAllByUserLogin());
    }

    @PostMapping
    private ResponseEntity<?> saveWishtlist(@RequestBody @Validated WishlistVo wishlistVo, Errors errors) {
        System.out.println(wishlistVo);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "wishlist", errors.getFieldErrors().get(0).getDefaultMessage()));
        WishlistVo wishlistFake = _iWishlistService.existWishlist(wishlistVo.getIdProduct());
        if (wishlistFake != null) {
            _iWishlistService.delete(wishlistFake);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(_iWishlistService.save(wishlistVo));
        }
    }

}
