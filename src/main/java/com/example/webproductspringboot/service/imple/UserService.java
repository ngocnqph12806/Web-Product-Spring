package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.dto.UserRegisterDto;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.ContainsUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.utils.MapperModelUtils;
import com.example.webproductspringboot.vo.InfoCheckoutVo;
import com.example.webproductspringboot.vo.SearchProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService extends AbstractService implements IUserService, UserDetailsService {

    private final IUserReponsitory _iUserReponsitory;
    private final PasswordEncoder _passwordEncoder;

    protected UserService(HttpServletRequest request, IUserReponsitory iUserReponsitory, PasswordEncoder passwordEncoder) {
        super(request);
        _iUserReponsitory = iUserReponsitory;
        _passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = _iUserReponsitory.findByUserNameOrEmail(username);
        if (optional.isEmpty()) {
            log.error("Người dùng không tồn tại trong database");
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        } else log.error("Người dùng tồn tại trong database: {}", username);
        UserEntity user = optional.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> lst = _iUserReponsitory.findAll(sortAZByCreated());
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllStaff() {
        List<UserEntity> lst = _iUserReponsitory.findAllStaff(sortAZByCreated());
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllVisit() {
        List<UserEntity> lst = _iUserReponsitory.findAllVisit(sortAZByCreated());
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<UserDto>> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> entities = _iUserReponsitory.findAll(pageable);
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                entities.getContent().stream().map(e -> (UserDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public PageDto<List<UserDto>> findStaffByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<UserEntity> entities = _iUserReponsitory.findStaffByPage(pageable);
        List<UserEntity> lst = entities.getContent();
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public PageDto<List<UserDto>> findVisitByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<UserEntity> entities = _iUserReponsitory.findVisitByPage(pageable);
        List<UserEntity> lst = entities.getContent();
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public UserDto findById(String id) {
        Optional<UserEntity> optional = _iUserReponsitory.findById(id);
        if (optional.isEmpty())
            throw new InternalServerException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto findByUserName(String username) {
        Optional<UserEntity> optional = _iUserReponsitory.findByUserNameOrEmail(username);
        if (optional.isEmpty()) {
            log.error("User not fount in the database");
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        } else log.error("User fount in the database: {}", username);
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto save(UserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(false);
        entity.setBlock(false);
//        entity.setPassword(_passwordEncoder.encode(entity.getPassword()));
        entity.setPassword(_passwordEncoder.encode("123445678"));
        entity.setCreated(new Date());
        _iUserReponsitory.save(entity);
        saveHistory(userEntity, "Thêm người dùng", entity.toString());
        return (UserDto) map(entity);
    }

    @Override
    public UserDto saveRegister(UserRegisterDto dto) {
        UserEntity entity = mapRegisterToEntity(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        if (dto.getPassword() == null || dto.getPassword().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "user", "password.not.found"));
        if (!dto.getPassword().equals(dto.getRegisterPassword()))
            throw new InternalServerException(CookieUtils.get().errorsProperties(request, "user", "password.not.equal"));
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(false);
        entity.setBlock(false);
        entity.setPassword(_passwordEncoder.encode(entity.getPassword()));
        entity.setCreated(new Date());
        _iUserReponsitory.save(entity);
        saveHistory(null, "Đăng ký người dùng mới", entity.toString());
        return (UserDto) map(entity);
    }

    @Override
    public List<String> getAllEmail() {
        return _iUserReponsitory.getAllEmail();
    }

    @Override
    public UserDto update(UserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<UserEntity> optional = _iUserReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new InternalServerException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        UserEntity fake = optional.get();
        entity.setCreated(fake.getCreated());
        if (entity.getBlock() == null) {
            entity.setBlock(fake.getBlock());
        }
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setPassword(fake.getPassword());
        _iUserReponsitory.save(entity);
        saveHistory(userEntity, "Sửa thông tin người dùng", fake + "\n" + entity);
        return (UserDto) map(entity);
    }

    @Override
    public UserDto confirmUser(String id) {
        Optional<UserEntity> optional = _iUserReponsitory.findById(id);
        if (optional.isEmpty())
            throw new InternalServerException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        UserEntity entity = optional.get();
        entity.setStatus(true);
        _iUserReponsitory.save(entity);
        saveHistory(null, "Xác nhận đăng ký tài khoản", entity.toString());
        return (UserDto) map(entity);
    }

    @Override
    public InfoCheckoutVo getInfoCheckoutByUserLogin(String id) {
        Optional<UserEntity> optional = _iUserReponsitory.findById(id);
        if (optional.isEmpty())
            throw new InternalServerException(CookieUtils.get().errorsProperties(request, "user", "user.not.found"));
        return mapEntityToInfoCheckout(optional.get());
    }

    @Override
    public String getIdByUserName(String username) {
        return _iUserReponsitory.getIdByUserName(username);
    }

    private InfoCheckoutVo mapEntityToInfoCheckout(UserEntity entity) {
        return (InfoCheckoutVo) MapperModelUtils.get().toDto(entity, InfoCheckoutVo.class);
    }

    private UserEntity mapRegisterToEntity(UserRegisterDto dto) {
        return UserEntity.builder()
                .fullName(dto.getFullName())
                .dateOfBirth(dto.getDateOfBirth())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .address(dto.getAddress())
                .avatar(dto.getAvatar())
                .role(ContainsUtils.ROLE_USER)
                .password(dto.getPassword())
                .build();
    }

}
