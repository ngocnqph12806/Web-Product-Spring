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
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/wish-list")
public class WishlistApi extends AbstractApi {

    @Autowired
    private IWishlistService _iWishlistService;

    protected WishlistApi(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @GetMapping(path = "/get-by-user-login", params = "id")
    private ResponseEntity<?> getAllWishListByUserLogin(@RequestParam("id") String id) {
        return ResponseEntity.ok(_iWishlistService.findAllByUserLogin(id));
    }

    @PostMapping
    private ResponseEntity<?> saveWishtlist(@RequestBody @Validated WishlistVo wishlistVo, Errors errors) {
        System.out.println(wishlistVo);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "wishlist", errors.getFieldErrors().get(0).getDefaultMessage()));
        WishlistVo wishlistFake = _iWishlistService.existWishlist(wishlistVo.getIdProduct());
        if (wishlistFake != null) {
            _iWishlistService.delete(wishlistFake);
            return ResponseEntity.ok(false);
        } else {
            _iWishlistService.save(wishlistVo);
            return ResponseEntity.ok(true);
        }
    }

}
