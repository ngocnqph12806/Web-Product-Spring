package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.entity.*;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IHistoryReponsitory;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
import com.example.webproductspringboot.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public abstract class AbstractService {

    @Autowired
    private IUserReponsitory _iUserReponsitory;
    @Autowired
    private IHistoryReponsitory _iHistoryReponsitory;
    protected final HttpServletRequest request;
    private final String urlGit = "https://raw.githubusercontent.com/ngocnqph12806/Repo_File/main/";

    protected AbstractService(HttpServletRequest request) {
        this.request = request;
    }

    protected Sort sortAZByCreated() {
        return Sort.by(Sort.Direction.DESC, "created");
    }

    protected Sort sortAZ(String type) {
        return Sort.by(Sort.Direction.DESC, type);
    }

    protected Sort sortZA(String type) {
        return Sort.by(Sort.Direction.ASC, type);
    }

    protected void saveHistory(UserEntity user, String description, String details) {
        HistoryEntity entity = HistoryEntity.builder()
                .id(UUID.randomUUID().toString())
                .idStaff(user)
                .details(details)
                .description(description)
                .created(new Date(System.currentTimeMillis()))
                .build();
        _iHistoryReponsitory.save(entity);
    }

    protected UserEntity getUserLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> optional = _iUserReponsitory.findByUserNameOrEmail(username);
        if (optional.isEmpty()) throw new NotFoundException("Vui lòng đăng nhập trước khi thao tác");
        return optional.get();
    }

    protected Object map(Object data) {
        if (data == null) return null;
//        WISHLIST
        if (data instanceof WishlistVo) {
            WishlistVo dto = (WishlistVo) data;
            return WishlistEntity.builder()
                    .id(dto.getId())
                    .idProduct(ProductEntity.builder().id(dto.getIdProduct()).build())
                    .idVisit(UserEntity.builder().id(dto.getIdVisit()).build())
                    .build();
        } else if (data instanceof WishlistEntity) {
            WishlistEntity entity = (WishlistEntity) data;
            return WishlistVo.builder()
                    .id(entity.getId())
                    .idProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getId() : null)
                    .idVisit(entity.getIdVisit() != null
                            ? entity.getIdVisit().getId() : null)
                    .build();
        }
        //BANNER
        else if (data instanceof BannerDto) {
            BannerDto dto = (BannerDto) data;
            return BannerEntity.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .pathImage(dto.getPathImage().contains(urlGit)
                            ? dto.getPathImage().substring(dto.getPathImage().lastIndexOf(urlGit) + urlGit.length())
                            : dto.getPathImage())
                    .pathLink(dto.getLink())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .build();
        } else if (data instanceof BannerEntity) {
            BannerEntity entity = (BannerEntity) data;
            return BannerDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .pathImage(urlGit + entity.getPathImage())
                    .link(entity.getPathLink())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .build();
        }
//        BRAND
        else if (data instanceof BrandDto) {
            BrandDto dto = (BrandDto) data;
            return BrandEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .address(dto.getAddress())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof BrandEntity) {
            BrandEntity entity = (BrandEntity) data;
            return BrandDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .phoneNumber(entity.getPhoneNumber())
                    .email(entity.getEmail())
                    .address(entity.getAddress())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .countProducts(entity.getLstProduct() != null
                            ? entity.getLstProduct().size() : 0)
                    .collectionBrands(entity.getLstCollectionBrandEntities() != null
                            ? entity.getLstCollectionBrandEntities().stream()
                            .map(e -> (CollectionBrandVo) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
//        COLLECTOIN - BRAND
        else if (data instanceof CollectionBrandVo) {
            CollectionBrandVo dto = (CollectionBrandVo) data;
            return CollectionBrandEntity.builder()
                    .id(dto.getId())
                    .idBrand(BrandEntity.builder().id(dto.getIdBrand()).build())
                    .idCollection(CollectionEntity.builder().id(dto.getIdCollection()).build())
                    .build();
        } else if (data instanceof CollectionBrandEntity) {
            CollectionBrandEntity entity = (CollectionBrandEntity) data;
            return CollectionBrandVo.builder()
                    .id(entity.getId())
                    .idBrand(entity.getIdBrand() != null
                            ? entity.getIdBrand().getId() : null)
                    .nameBrand(entity.getIdBrand() != null
                            ? entity.getIdBrand().getName() : null)
                    .idCollection(entity.getIdCollection() != null
                            ? entity.getIdCollection().getId() : null)
                    .nameCollection(entity.getIdCollection() != null
                            ? entity.getIdCollection().getName() : null)
                    .build();
        }
        // CATEGORY
        else if (data instanceof CategoryDto) {
            CategoryDto dto = (CategoryDto) data;
            return CategoryEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .banner(dto.getBanner().contains(urlGit)
                            ? dto.getBanner().substring(dto.getBanner().lastIndexOf(urlGit) + urlGit.length())
                            : dto.getBanner())
                    .pathUrl(dto.getPath())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .build();
        } else if (data instanceof CategoryEntity) {
            CategoryEntity entity = (CategoryEntity) data;
            return CategoryDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .banner(urlGit + entity.getBanner())
                    .path(entity.getPathUrl())
                    .idPath(entity.getIdUrl())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .countProducts(entity.getLstProductEntities() != null
                            ? entity.getLstProductEntities().size() : 0)
                    .products(entity.getLstProductEntities() != null
                            ? entity.getLstProductEntities().stream()
                            .map(e -> (ProductDto) map(e)).collect(Collectors.toList())
                            : null)
                    .collectionCategories(entity.getLstCollectionCategoryEntities() != null
                            ? entity.getLstCollectionCategoryEntities().stream()
                            .map(e -> (CollectionCategoryVo) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
        //        COLLECTOIN - BRAND
        else if (data instanceof CollectionCategoryVo) {
            CollectionCategoryVo dto = (CollectionCategoryVo) data;
            return CollectionCategoryEntity.builder()
                    .id(dto.getId())
                    .idCategory(CategoryEntity.builder().id(dto.getIdCategory()).build())
                    .idCollection(CollectionEntity.builder().id(dto.getIdCollection()).build())
                    .build();
        } else if (data instanceof CollectionCategoryEntity) {
            CollectionCategoryEntity entity = (CollectionCategoryEntity) data;
            return CollectionCategoryVo.builder()
                    .id(entity.getId())
                    .idCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getId() : null)
                    .nameCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getName() : null)
                    .idPathCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getIdUrl() : null)
                    .pathCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getPathUrl() : null)
                    .idCollection(entity.getIdCollection() != null
                            ? entity.getIdCollection().getId() : null)
                    .nameCollection(entity.getIdCollection() != null
                            ? entity.getIdCollection().getName() : null)
                    .build();
        }
//        COLELCTION
        else if (data instanceof CollectionDto) {
            CollectionDto dto = (CollectionDto) data;
            return CollectionEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof CollectionEntity) {
            CollectionEntity entity = (CollectionEntity) data;
            return CollectionDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .countProducts(entity.getLstCollectionCategoryEntities() != null
                            ? entity.getLstCollectionCategoryEntities().stream()
                            .mapToInt(e -> e.getIdCategory().getLstProductEntities() != null
                                    ? e.getIdCategory().getLstProductEntities().size() : 0)
                            .sum()
                            : 0)
                    .lstCollectionCategoryVos(entity.getLstCollectionCategoryEntities() != null
                            ? entity.getLstCollectionCategoryEntities().stream()
                            .map(e -> (CollectionCategoryVo) map(e)).collect(Collectors.toList()) : null)
                    .build();
        }
//        RETURN
        else if (data instanceof ReturnDto) {
            ReturnDto dto = (ReturnDto) data;
            return CustomersReturnEntity.builder()
                    .id(dto.getId())
                    .idOrder(OrderEntity.builder().id(dto.getIdOrder()).build())
                    .idStaff(UserEntity.builder().id(dto.getIdStaff()).build())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof CustomersReturnEntity) {
            CustomersReturnEntity entity = (CustomersReturnEntity) data;
            return ReturnDto.builder()
                    .id(entity.getId())
                    .idOrder(entity.getIdOrder() != null
                            ? entity.getIdOrder().getId() : null)
                    .dateOrder(entity.getIdOrder() != null
                            ? entity.getIdOrder().getCreated() : null)
                    .nameUser(entity.getIdOrder() != null
                            ? entity.getIdOrder().getFullName() : null)
                    .idStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getId() : null)
                    .nameStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getFullName() : null)
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .totalMoney(entity.getLstCustomersReturnDetailsEntities() != null
                            ? entity.getLstCustomersReturnDetailsEntities().stream()
                            .mapToLong(e -> (e.getQuantity() * e.getIdOrderDtails().getPrice())).sum()
                            : Long.parseLong("0"))
                    .details(entity.getLstCustomersReturnDetailsEntities() != null
                            ? entity.getLstCustomersReturnDetailsEntities().stream()
                            .map(e -> (ReturnDetailDto) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
        // RETURN DETAIL
        else if (data instanceof ReturnDetailDto) {
            ReturnDetailDto dto = (ReturnDetailDto) data;
            return CustomersReturnDetailsEntity.builder()
                    .id(dto.getId())
                    .idCustomersReturn(CustomersReturnEntity.builder().id(dto.getIdReturn()).build())
                    .idOrderDtails(OrderDetailsEntity.builder().id(dto.getIdOrderDetail()).build())
                    .quantity(dto.getQuantity())
                    .build();
        } else if (data instanceof CustomersReturnDetailsEntity) {
            CustomersReturnDetailsEntity entity = (CustomersReturnDetailsEntity) data;
            return ReturnDetailDto.builder()
                    .id(entity.getId())
                    .idReturn(entity.getIdCustomersReturn() != null
                            ? entity.getIdCustomersReturn().getId() : null)
                    .idOrderDetail(entity.getIdOrderDtails() != null
                            ? entity.getIdOrderDtails().getId() : null)
                    .quantity(entity.getQuantity())
                    .build();
        }
//        HISTORY
//        else if (data instanceof HistoryVo) {
//            HistoryVo dto = (HistoryVo) data;
//            return HistoryEntity.builder()
//                    .id(dto.getId())
//                    .idStaff(UserEntity.builder().id(dto.getIdStaff()).build())
//                    .description(dto.getDescription())
//                    .created(dto.getDateCreated())
//                    .build();
//        }
        else if (data instanceof HistoryEntity) {
            HistoryEntity entity = (HistoryEntity) data;
            return HistoryVo.builder()
                    .id(entity.getId())
                    .idStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getId() : null)
                    .nameStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getFullName() : null)
                    .description(entity.getDescription())
                    .detail(entity.getDetails())
                    .dateCreated(entity.getCreated())
                    .build();
        }
//      INVOICE
        else if (data instanceof InvoiceDto) {
            InvoiceDto dto = (InvoiceDto) data;
            return InvoiceEntity.builder()
                    .id(dto.getId())
                    .idStaffCreate(UserEntity.builder().id(dto.getIdCreator()).build())
                    .idStaffCheck(UserEntity.builder().id(dto.getIdChecker()).build())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof InvoiceEntity) {
            InvoiceEntity entity = (InvoiceEntity) data;
            return InvoiceDto.builder()
                    .id(entity.getId())
                    .idCreator(entity.getIdStaffCreate() != null
                            ? entity.getIdStaffCreate().getId() : null)
                    .nameCreator(entity.getIdStaffCreate() != null
                            ? entity.getIdStaffCreate().getFullName() : null)
                    .idChecker(entity.getIdStaffCheck() != null
                            ? entity.getIdStaffCheck().getId() : null)
                    .nameChecker(entity.getIdStaffCheck() != null
                            ? entity.getIdStaffCheck().getFullName() : null)
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .totalPrice(entity.getLstInvoiceDetailsEntities() != null
                            ? entity.getLstInvoiceDetailsEntities().stream()
                            .mapToLong(e -> e.getPrice() * e.getQuantity()).sum()
                            : Long.parseLong("0"))
                    .invoiceDetails(entity.getLstInvoiceDetailsEntities() != null
                            ? entity.getLstInvoiceDetailsEntities().stream()
                            .map(e -> (InvoiceDetailDto) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
        //     INVOICE DETAIL
        else if (data instanceof InvoiceDetailDto) {
            InvoiceDetailDto dto = (InvoiceDetailDto) data;
            return InvoiceDetailsEntity.builder()
                    .id(dto.getId())
                    .idInvoice(InvoiceEntity.builder().id(dto.getIdInvoice()).build())
                    .idProduct(ProductEntity.builder().id(dto.getIdProduct()).build())
                    .price(dto.getPrice())
                    .quantity(dto.getQuantity())
                    .build();
        } else if (data instanceof InvoiceDetailsEntity) {
            InvoiceDetailsEntity entity = (InvoiceDetailsEntity) data;
            return InvoiceDetailDto.builder()
                    .id(entity.getId())
                    .idInvoice(entity.getIdInvoice() != null
                            ? entity.getIdInvoice().getId() : null)
                    .idProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getId() : null)
                    .nameProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getName() : null)
                    .price(entity.getPrice())
                    .quantity(entity.getQuantity())
                    .build();
        }
//        ORDER
        else if (data instanceof OrderDto) {
            OrderDto dto = (OrderDto) data;
            return OrderEntity.builder()
                    .id(dto.getId())
                    .idVisit(dto.getIdUser() != null
                            ? UserEntity.builder().id(dto.getIdUser()).build()
                            : null)
                    .idVoucher(dto.getIdVoucher() != null
                            ? VoucherEntity.builder().id(dto.getIdVoucher()).code(dto.getCodeVoucher()).build()
                            : null)
                    .staffCreate(UserEntity.builder().id(dto.getIdCreator()).build())
                    .staffSales(UserEntity.builder().id(dto.getIdSaller()).build())
                    .paymentMethod(dto.getPaymentMethod())
                    .paymentStatus(dto.getPaymentStatus())
                    .fullName(dto.getFullName())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .village(dto.getVillage())
                    .ward(dto.getWard())
                    .district(dto.getDistrict())
                    .city(dto.getCity())
                    .note(dto.getNote())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof ChechoutDto) {
            ChechoutDto dto = (ChechoutDto) data;
            return OrderEntity.builder()
                    .id(dto.getId())
                    .idVoucher(dto.getIdVoucher() != null
                            ? VoucherEntity.builder().id(dto.getIdVoucher()).code(dto.getCodeVoucher()).build()
                            : null)
                    .paymentMethod(dto.getPaymentMethod())
                    .paymentStatus(dto.getPaymentStatus())
                    .fullName(dto.getFullName())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .village(dto.getVillage())
                    .ward(dto.getWard())
                    .district(dto.getDistrict())
                    .city(dto.getCity())
                    .note(dto.getNote())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof OrderEntity) {
            OrderEntity entity = (OrderEntity) data;
            return OrderDto.builder()
                    .id(entity.getId())
                    .idUser(entity.getIdVisit() != null
                            ? entity.getIdVisit().getId() : null)
                    .nameUser(entity.getIdVisit() != null
                            ? entity.getIdVisit().getFullName() : null)
                    .idVoucher(entity.getIdVoucher() != null
                            ? entity.getIdVoucher().getId() : null)
                    .codeVoucher(entity.getIdVoucher() != null
                            ? entity.getIdVoucher().getCode() : null)
                    .priceVoucher(entity.getIdVoucher() != null
                            ? entity.getIdVoucher().getPriceSale() : null)
                    .idCreator(entity.getStaffCreate() != null
                            ? entity.getStaffCreate().getId() : null)
                    .nameCreator(entity.getStaffCreate() != null
                            ? entity.getStaffCreate().getFullName() : null)
                    .idSaller(entity.getStaffSales() != null
                            ? entity.getStaffSales().getId() : null)
                    .nameSaller(entity.getStaffSales() != null
                            ? entity.getStaffSales().getFullName() : null)
                    .paymentMethod(entity.getPaymentMethod())
                    .paymentStatus(entity.getPaymentStatus())
                    .fullName(entity.getFullName())
                    .phoneNumber(entity.getPhoneNumber())
                    .email(entity.getEmail())
                    .village(entity.getVillage())
                    .ward(entity.getWard())
                    .district(entity.getDistrict())
                    .city(entity.getCity())
                    .note(entity.getNote())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .totalPrice(entity.getLstOrderDetailsEntities() != null
                            ? entity.getLstOrderDetailsEntities().stream()
                            .mapToLong(e -> (e.getPrice() * e.getQuantity()) - e.getPriceSale()).sum()
                            - (entity.getIdVoucher() != null ? entity.getIdVoucher().getPriceSale() : 0)
                            : Long.parseLong("0"))
                    .details(entity.getLstOrderDetailsEntities() != null
                            ? entity.getLstOrderDetailsEntities().stream()
                            .map(e -> (OrderDetailDto) map(e)).collect(Collectors.toList())
                            : null)
                    .transports(entity.getLstTransportEntities() != null
                            ? entity.getLstTransportEntities().stream()
                            .map(e -> (TransportDto) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
//ODER DETAIL
        else if (data instanceof OrderDetailDto) {
            OrderDetailDto dto = (OrderDetailDto) data;
            return OrderDetailsEntity.builder()
                    .id(dto.getId())
                    .idProduct(ProductEntity.builder().id(dto.getIdProduct()).build())
                    .idOrder(OrderEntity.builder().id(dto.getIdOrder()).build())
                    .price(dto.getPrice())
                    .priceSale(dto.getPriceSale())
                    .quantity(dto.getQuantity())
                    .build();
        } else if (data instanceof OrderDetailsEntity) {
            OrderDetailsEntity entity = (OrderDetailsEntity) data;
            return OrderDetailDto.builder()
                    .id(entity.getId())
                    .idOrder(entity.getIdOrder() != null
                            ? entity.getIdOrder().getId() : null)
                    .idProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getId() : null)
                    .nameProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getName() : null)
                    .pathProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getPathUrl() : null)
                    .idPathProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getIdUrl() : null)
                    .imageProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getLstProductImageEntities() != null
                            && !entity.getIdProduct().getLstProductImageEntities().isEmpty()
                            ? urlGit + entity.getIdProduct().getLstProductImageEntities().get(0).getPath()
                            : null : null)
                    .price(entity.getPrice())
                    .priceSale(entity.getPriceSale())
                    .quantity(entity.getQuantity())
                    .quantityReturn(entity.getLstCustomersReturnDetailsEntities() != null
                            ? entity.getLstCustomersReturnDetailsEntities().stream()
                            .mapToInt(CustomersReturnDetailsEntity::getQuantity).sum()
                            : 0)
                    .returns(entity.getLstCustomersReturnDetailsEntities() != null
                            ? entity.getLstCustomersReturnDetailsEntities().stream()
                            .map(e -> (ReturnDetailDto) map(e)).collect(Collectors.toList())
                            : null)
                    .build();
        }
//        PRODUCT
        else if (data instanceof ProductDto) {
            ProductDto dto = (ProductDto) data;
            return ProductEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .idBrand(BrandEntity.builder().id(dto.getIdBrand()).build())
                    .idCategory(CategoryEntity.builder().id(dto.getIdCategory()).build())
                    .price(dto.getPrice())
                    .priceSale(dto.getPriceSale())
                    .quantity(dto.getQuantity())
                    .color(dto.getColor())
                    .categoryDetails(dto.getCategoryDetails())
                    .location(dto.getLocation())
                    .pathUrl(dto.getPath())
                    .idUrl(dto.getIdPath())
                    .pathUserManual(dto.getPathUserManual().contains(urlGit)
                            ? dto.getPathUserManual().substring(dto.getPathUserManual().lastIndexOf(urlGit) + urlGit.length())
                            : dto.getPathUserManual())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof ProductEntity) {
            ProductEntity entity = (ProductEntity) data;
            return ProductDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .idBrand(entity.getIdBrand() != null
                            ? entity.getIdBrand().getId() : null)
                    .nameBrand(entity.getIdBrand() != null
                            ? entity.getIdBrand().getName() : null)
                    .idCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getId() : null)
                    .nameCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getName() : null)
                    .pathCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getPathUrl() : null)
                    .idPathCategory(entity.getIdCategory() != null
                            ? entity.getIdCategory().getIdUrl() : null)
                    .price(entity.getPrice())
                    .priceSale(entity.getPriceSale())
                    .quantity(entity.getQuantity())
                    .color(entity.getColor())
                    .categoryDetails(entity.getCategoryDetails())
                    .location(entity.getLocation())
                    .path(entity.getPathUrl())
                    .idPath(entity.getIdUrl())
                    .pathUserManual(urlGit + entity.getPathUserManual())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .pointReview(entity.getLstReviewProductEntities() != null
                            ? entity.getLstReviewProductEntities().stream()
                            .mapToInt(e -> e.getPoint())
                            .summaryStatistics().getAverage()
                            : 0)
//                    .details(entity.getLstProductDetailsEntities() != null
//                            ? entity.getLstProductDetailsEntities().stream()
//                            .map(e -> (ProductDetailsVo) map(e)).collect(Collectors.toList())
//                            : null)
                    .images(entity.getLstProductImageEntities() != null
                            ? entity.getLstProductImageEntities().stream()
                            .map(e -> (ProductImageVo) map(e)).collect(Collectors.toList())
                            : null)
                    .reviews(entity.getLstReviewProductEntities() != null
                            ? entity.getLstReviewProductEntities().stream()
                            .map(e -> (ReviewDto) map(e)).collect(Collectors.toList())
                            : null)
                    .countWishlist(entity.getLstWishlistEntities() != null
                            ? entity.getLstWishlistEntities().size() : 0)
                    .build();
        }
//        PRODUCT IMAGE
        else if (data instanceof ProductImageVo) {
            ProductImageVo dto = (ProductImageVo) data;
            return ProductImageEntity.builder()
                    .id(dto.getId())
                    .path(dto.getPath().contains(urlGit)
                            ? dto.getPath().substring(dto.getPath().lastIndexOf(urlGit) + urlGit.length())
                            : dto.getPath())
                    .idProduct(ProductEntity.builder().id(dto.getIdProduct()).build())
                    .build();
        } else if (data instanceof ProductImageEntity) {
            ProductImageEntity entity = (ProductImageEntity) data;
            return ProductImageVo.builder()
                    .id(entity.getId())
                    .path(urlGit + entity.getPath())
                    .idProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getId() : null)
                    .build();
        }
//         TRANSPORT
        else if (data instanceof TransportDto) {
            TransportDto dto = (TransportDto) data;
            return TransportEntity.builder()
                    .id(dto.getId())
                    .idOrder(OrderEntity.builder().id(dto.getIdOrder()).build())
                    .description(dto.getDescription())
                    .statusTransport(dto.getStatusTransport())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof TransportEntity) {
            TransportEntity entity = (TransportEntity) data;
            return TransportDto.builder()
                    .id(entity.getId())
                    .idOrder(entity.getIdOrder() != null
                            ? entity.getIdOrder().getId() : null)
                    .fullName(entity.getIdOrder() != null
                            ? entity.getIdOrder().getFullName() : null)
                    .phoneNumber(entity.getIdOrder() != null
                            ? entity.getIdOrder().getPhoneNumber() : null)
                    .address(entity.getIdOrder() != null
                            ? entity.getIdOrder().getVillage()
                            + " " + entity.getIdOrder().getWard()
                            + " " + entity.getIdOrder().getDistrict()
                            + " " + entity.getIdOrder().getCity()
                            : null)
                    .description(entity.getDescription())
                    .statusTransport(entity.getStatusTransport())
                    .dateCreated(entity.getCreated())
                    .build();
        }
        // USER
        else if (data instanceof UserDto) {
            UserDto dto = (UserDto) data;
            return UserEntity.builder()
                    .id(dto.getId())
                    .fullName(dto.getFullName())
                    .dateOfBirth(dto.getDateOfBirth())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .address(dto.getAddress())
                    .avatar(dto.getAvatar().contains(urlGit)
                            ? dto.getAvatar().substring(dto.getAvatar().lastIndexOf(urlGit) + urlGit.length())
                            : dto.getAvatar())
                    .role(dto.getRole())
                    .status(dto.getStatus())
                    .block(dto.getBlock())
                    .build();
        } else if (data instanceof UserEntity) {
            UserEntity entity = (UserEntity) data;
            return UserDto.builder()
                    .id(entity.getId())
                    .fullName(entity.getFullName())
                    .dateOfBirth(entity.getDateOfBirth())
                    .phoneNumber(entity.getPhoneNumber())
                    .email(entity.getEmail())
                    .username(entity.getUsername())
                    .address(entity.getAddress())
                    .avatar(urlGit + entity.getAvatar())
                    .role(entity.getRole())
                    .status(entity.getStatus())
                    .block(entity.getBlock())
                    .dateCreated(entity.getCreated())
                    .orders(entity.getLstOrderEntities() != null
                            ? entity.getLstOrderEntities().stream()
                            .map(e -> (OrderDto) map(e)).collect(Collectors.toList())
                            : null)
//                    .returns(entity.getLstCustomersReturnEntitiesWithVisit() != null
//                            ? entity.getLstCustomersReturnEntitiesWithVisit().stream()
//                            .map(e -> (ReturnDto) map(e)).collect(Collectors.toList())
//                            : null)
                    .reviews(entity.getLstReviewProductEntities() != null
                            ? entity.getLstReviewProductEntities().stream()
                            .map(e -> (ReviewDto) map(e)).collect(Collectors.toList())
                            : null)
                    .countWishlist(entity.getLstWishlistEntities() != null
                            ? entity.getLstWishlistEntities().size() : 0)
                    .build();
        } else if (data instanceof ChangeUserDto) {
            ChangeUserDto dto = (ChangeUserDto) data;
            return UserEntity.builder()
                    .id(dto.getId())
                    .fullName(dto.getFullName())
                    .dateOfBirth(dto.getDateOfBirth())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .address(dto.getAddress())
                    .avatar(dto.getAvatar())
                    .role(dto.getRole())
                    .block(dto.getBlock())
                    .status(dto.getStatus())
                    .build();
        }
        //  VOUCHER
        else if (data instanceof VoucherDto) {
            VoucherDto dto = (VoucherDto) data;
            return VoucherEntity.builder()
                    .id(dto.getId())
                    .code(dto.getCode())
                    .title(dto.getTitle())
                    .idStaff(UserEntity.builder().id(dto.getIdStaff()).build())
                    .quantity(dto.getQuantity())
                    .priceSale(dto.getPriceSale())
                    .dateStart(dto.getDateStart())
                    .dateEnd(dto.getDateEnd())
                    .description(dto.getDescription())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof VoucherEntity) {
            VoucherEntity entity = (VoucherEntity) data;
            return VoucherDto.builder()
                    .id(entity.getId())
                    .code(entity.getCode())
                    .title(entity.getTitle())
                    .idStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getId() : null)
                    .nameStaff(entity.getIdStaff() != null
                            ? entity.getIdStaff().getFullName() : null)
                    .quantity(entity.getQuantity())
                    .priceSale(entity.getPriceSale())
                    .dateStart(entity.getDateStart())
                    .dateEnd(entity.getDateEnd())
                    .description(entity.getDescription())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .quantityUsed(entity.getLstVoucherUseEntities() != null
                            ? entity.getLstVoucherUseEntities().size() : 0)
                    .build();
        }
        // REVIEW
        else if (data instanceof ReviewDto) {
            ReviewDto dto = (ReviewDto) data;
            return ReviewProductEntity.builder()
                    .id(dto.getId())
                    .idProduct(ProductEntity.builder().id(dto.getIdProduct()).build())
                    .idVisit(UserEntity.builder().id(dto.getIdUser()).build())
                    .point(dto.getPoint())
                    .description(dto.getDescription())
                    .introduce(dto.getIntroduce())
                    .status(dto.getStatus())
                    .created(dto.getDateCreated())
                    .build();
        } else if (data instanceof ReviewProductEntity) {
            ReviewProductEntity entity = (ReviewProductEntity) data;
            return ReviewDto.builder()
                    .id(entity.getId())
                    .idProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getId() : null)
                    .nameProduct(entity.getIdProduct() != null
                            ? entity.getIdProduct().getName() : null)
                    .idUser(entity.getIdVisit() != null
                            ? entity.getIdVisit().getId() : null)
                    .nameUser(entity.getIdVisit() != null
                            ? entity.getIdVisit().getFullName() : null)
                    .point(entity.getPoint())
                    .description(entity.getDescription())
                    .introduce(entity.getIntroduce())
                    .status(entity.getStatus())
                    .dateCreated(entity.getCreated())
                    .images(entity.getLstReviewImageEntities() != null
                            ? entity.getLstReviewImageEntities().stream()
                            .map(e -> urlGit + e.getPathImage()).collect(Collectors.toList())
                            : null)
                    .build();
        }
        return null;
    }

}
