package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.staff.FormUserAdminDto;
import com.example.webproductspringboot.dto.staff.IntroStaffAdminDto;
import com.example.webproductspringboot.dto.staff.IntroVisitAdminDto;

import java.util.List;

public interface IUserService {

    List<IntroStaffAdminDto> findAllIntroStaff();
    
    Boolean changeStatusStaff(String userNameStaff);

    Boolean changeBlockStaff(String userNameStaff);

    FormUserAdminDto findByUserName(String userNameStaff);

    FormUserAdminDto saveInfoStaff(FormUserAdminDto formUserAdminDto);

    List<IntroVisitAdminDto> findAllIntroVisit();

}
