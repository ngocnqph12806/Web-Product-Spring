package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ChangeUserDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
import com.example.webproductspringboot.service.intf.IUserService;
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
            log.error("User not fount in the database");
            throw new NotFoundException("Người dùng không tồn tại");
        } else {
            log.error("User fount in the database: {}", username);
        }
        UserEntity user = optional.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> lst = _iUserReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllStaff() {
        List<UserEntity> lst = _iUserReponsitory.findAllStaff();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllVisit() {
        List<UserEntity> lst = _iUserReponsitory.findAllVisit();
        Collections.reverse(lst);
        return lst.stream().map(e -> (UserDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<UserDto>> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page + 1, size);
        Page<UserEntity> entities = _iUserReponsitory.findAll(pageable);
        return new PageDto<List<UserDto>>(entities.getTotalPages(), entities.getTotalPages(),
                entities.getContent().stream().map(e -> (UserDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public UserDto findById(String id) {
        Optional<UserEntity> optional = _iUserReponsitory.findById(id);
        if (optional.isEmpty()) throw new InternalServerException("Người dùng không tồn tại");
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto findByUserName(String username) {
        Optional<UserEntity> optional = _iUserReponsitory.findByUserName(username);
        if (optional.isEmpty()) {
            log.error("User not fount in the database");
            throw new NotFoundException("Người dùng không tồn tại");
        } else {
            log.error("User fount in the database: {}", username);
        }
        return (UserDto) map(optional.get());
    }

    @Override
    public UserDto save(ChangeUserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setBlock(false);
        entity.setPassword(_passwordEncoder.encode(entity.getPassword()));
        entity.setCreated(new Date());
        _iUserReponsitory.save(entity);
        saveHistory(userEntity, "Thêm người dùng: \n" + entity);
        return (UserDto) map(entity);
    }

    @Override
    public UserDto update(ChangeUserDto dto) {
        UserEntity entity = (UserEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        if (entity.getId() == null || entity.getId().isEmpty() || entity.getId().isBlank())
            throw new BadRequestException("Người dùng không đúng");
        Optional<UserEntity> fake = _iUserReponsitory.findById(entity.getId());
        if (fake.isEmpty()) throw new InternalServerException("Người dùng không tồn tại");
        UserEntity entityFake = fake.get();
        entity.setCreated(entityFake.getCreated());
        if (entity.getBlock() == null) {
            entity.setBlock(entityFake.getBlock());
        }
        if (entity.getStatus() == null) {
            entity.setStatus(entityFake.getStatus());
        }
        if (entity.getPassword() == null || entity.getPassword().isEmpty() || entity.getPassword().isBlank()) {
            entity.setPassword(entityFake.getPassword());
        } else {
            entity.setPassword(_passwordEncoder.encode(dto.getPassword()));
        }
        _iUserReponsitory.save(entity);
        saveHistory(userEntity, "Sửa thông tin người dùng: \n" + fake + "\n" + entity);
        return (UserDto) map(entity);
    }

}
