package com.example.webproductspringboot.vo;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.utils.ConvertUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SearchBrandVo {

    private String[] name;
    private String[] phoneNumber;
    private String[] email;
    private String[] address;
    private String[] description;
    private String[] status;
    private String[] dateCreated;
    private String[] countProducts;

    public List<BrandDto> search(List<BrandDto> lst, String[] type) {
        if (type != null)
            for (String x : type) {
                if (lst.isEmpty()) return lst;
                if (Arrays.equals(type, name))
                    lst = lst.stream().filter(e -> e.getName().contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, phoneNumber))
                    lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, email))
                    lst = lst.stream().filter(e -> e.getEmail().contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, address))
                    lst = lst.stream().filter(e -> e.getAddress().contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, description))
                    lst = lst.stream().filter(e -> e.getDescription().contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, status))
                    lst = lst.stream().filter(e -> e.getStatus() == Boolean.parseBoolean(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, dateCreated))
                    lst = lst.stream().filter(e -> ConvertUtils.get()
                            .dateToString(e.getDateCreated()).contains(x))
                            .collect(Collectors.toList());
                else if (Arrays.equals(type, countProducts))
                    lst = lst.stream().filter(e -> e.getCountProducts() == Integer.parseInt(x))
                            .collect(Collectors.toList());
            }
        if (Arrays.equals(type, name)) search(lst, phoneNumber);
        else if (Arrays.equals(type, phoneNumber)) search(lst, email);
        else if (Arrays.equals(type, email)) search(lst, address);
        else if (Arrays.equals(type, address)) search(lst, description);
        else if (Arrays.equals(type, description)) search(lst, status);
        else if (Arrays.equals(type, status)) search(lst, dateCreated);
        else if (Arrays.equals(type, dateCreated)) search(lst, countProducts);
        return lst;
    }

}
