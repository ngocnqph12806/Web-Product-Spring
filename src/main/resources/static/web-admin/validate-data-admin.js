// let REGEX_ADDRESS = /(?i)([0-9A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ\']+\s?\b){2,}/
// let REGEX_EMAIL = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
// let REGEX_PHONE_NUMBER = /[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}/;
// let REGEX_BRAND_NAME = /(?!\s)(?!.*\s$)(?=.*[a-zA-Z0-9])[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\- '~?!]{2,}/;
// let REGEX_FULL_NAME = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$/
// let REGEX_DATE = /(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|3[0-1])/;
// let REGEX_IMAGE = /.*\.(gif|jpe?g|bmp|png)/igm;

// " 123/13 Hương lộ 2. Khu phố 2, Quận Bình Tân. Phường Bình Trị Đông A"

function checkPath(value) {
    const REGEX_PATH = /^[a-zA-Z0-9\-\_\.]+$/
    if (value === null || value === '') {
        toastDanger('Đường dẫn không được để trống', 'Lỗi');
        return false;
    }
    if (value.trim().length < 5) {
        toastWarning('Đường dẫn phải từ 5 ký tự', 'Lỗi');
        return false
    }
    if (REGEX_PATH.test(value)) {
        return true
    }
    toastWarning('Đường dẫn không đúng', 'Lỗi');
    return false;
}

function checkName(value, title) {
    const REGEX_NAME = /^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ \-\.]+$/
    if (value === null || value === '') {
        toastDanger(title + ' không được để trống', 'Lỗi');
        return false;
    }
    if (value.trim().length < 5) {
        toastWarning(title + ' phải từ 5 ký tự', 'Lỗi');
        return false
    }
    if (REGEX_NAME.test(value)) {
        return true
    }
    toastWarning(title + ' không đúng', 'Lỗi');
    return false;
}

function checkRealName(value) {
    const REGEX_FULL_NAME = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$/
    if (value === null || value === '') {
        toastDanger('Họ tên không được để trống', 'Lỗi');
        return false;
    }
    if (value.trim().length < 5) {
        toastWarning('Họ tên phải từ 5 ký tự', 'Lỗi');
        return false
    }
    if (REGEX_FULL_NAME.test(value)) {
        return true
    }
    toastWarning('Họ tên không đúng', 'Lỗi');
    return false;
}

function checkAddress(value) {
    let REGEX_ADDRESS = /([0-9A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ\']+\s?\b){10,}/
    if (value === null || value === '') {
        toastDanger('Địa chỉ không được để trống', 'Lỗi');
        return false;
    }
    if (value.trim().length < 10) {
        toastWarning('Địa chỉ phải từ 10 ký tự', 'Lỗi');
        return false
    }
    // if (REGEX_ADDRESS.test(value)) {
    return true
    // }
    // toastWarning('Địa chỉ không đúng', 'Lỗi');
    // return false;
}

function checkNameArtists(value) {
    const REGEX_FULL_NAME = /^[0-9a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$/
    if (value === null || value === '') {
        toastDanger('Tên nghệ không được để trống', 'Lỗi');
        return false;
    }
    if (value.trim().length < 2) {
        toastWarning('Tên nghệ phải từ 2 ký tự', 'Lỗi');
        return false
    }
    if (REGEX_FULL_NAME.test(value)) {
        return true
    }
    toastWarning('Tên nghệ sĩ không đúng', 'Lỗi');
    return false;
}

function checkImage(image, title) {
    let REGEX_IMAGE = /.*\.(gif|jpe?g|bmp|png)/igm;
    if (image === null || image === '') {
        toastDanger(title + ' không được để trống', title);
        return false;
    }
    if (REGEX_IMAGE.test(image)) {
        return true;
    }
    toastWarning(title + ' không đúng định dạng .gif or .jpg or .jpe or .bmp or .png', title)
    return false;
}

function checkUserName(username) {
    let REGEX_USERNAME = /[a-zA-Z0-9\-]{5,}$/
    if (username === null || username === '') {
        toastDanger("Tên đăng nhập không được để trống", "Lỗi")
        return false;
    } else if (username.length < 5) {
        toastWarning("Tên đăng nhập phải từ 5 ký tự", "Lỗi")
        return false;
    } else if (REGEX_USERNAME.test(username)) {
        return true;
    }
    toastWarning("Tên đăng nhập không đúng", "Lỗi")
    return false;
}

function checkEmail(email) {
    let REGEX_EMAIL = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    if (email === null || email === '') {
        toastDanger("Email không được để trống", "Lỗi email")
        return false;
    } else if (REGEX_EMAIL.test(email)) {
        return true;
    }
    toastWarning("Email không đúng", "Lỗi email")
    return false;
}

function checkPhoneNumber(phoneNumber) {
    let REGEX_PHONE_NUMBER = /[\+]?[(]?[0-9]{3}[)]?[-\.]?[0-9]{3}[-\.]?[0-9]{4,6}/;
    if (phoneNumber === null || phoneNumber === '') {
        toastDanger('Số điện thoại không được để trống', 'Lỗi số điện thoại');
        return false;
    } else if (/[a-zA-Z]/.test(phoneNumber)) {
        toastDanger('Số điện thoại không được chứa chữ', 'Lỗi số điện thoại');
        return false
    } else if (REGEX_PHONE_NUMBER.test(phoneNumber)) {
        return true;
    }
    toastWarning('Số điện thoại không đúng định dạng \'0346667755\' hoặc \'+84326667755\'', 'Lỗi số điện thoại')
    return false;
}

function checkPassword(password) {
    if (password === null || password === '') {
        toastDanger("Mật khẩu không được để trống", "Lỗi mật khẩu")
        return false;
    } else if (password.length < 8) {
        toastWarning("Mật khẩu phải từ 8 ký tự", "Lỗi mật khẩu")
        return false;
    } else if (password.length > 50) {
        toastWarning("Mật khẩu không được quá 50 ký tự", "Lỗi mật khẩu")
        return false;
    }
    return true;
}

function checkDateAdmin(title, date) {
    let REGEX_DATE = /(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[0-1])\/(19|20)\d{2}/;
    if (date === null || date === '') {
        toastDanger(title + ' không được để trống', title);
        return false;
    }
    if (REGEX_DATE.test(date)) {
        return true;
    }
    toastWarning(title + ' không đúng định dạng', title)
    return false;
}

function check18AgesAdmin(date) {
    if (checkDateAdmin('Ngày sinh', date)) {
        let newDate = new Date();
        let getNow = newDate.getFullYear().toString() + convertTimeDate(newDate.getMonth() + 1) + convertTimeDate(newDate.getDate());
        let convertDate = date.substring(date.lastIndexOf('/')) + date.substring(0, date.lastIndexOf('/'))
        convertDate = convertDate.replaceAll('/', '');
        if (Number(getNow) - Number(convertDate) >= 180000) {
            return true;
        } else {
            toastWarning("Bạn chưa đủ 18 tuổi", "Tuổi không hợp lệ")
            return false;
        }
    }
    return false;
}

function checkDescription(value, title) {
    if (value === null || value === '') {
        toastDanger(title + ' không được để trống', 'Lỗi ' + title)
        return false;
    }
    if (value.trim().length < 150) {
        toastWarning(title + ' phải từ 150 ký tự', 'Lỗi ' + title)
        return false;
    }
    return true;
}

function checkIntroduction(value) {
    if (value === null || value === '') {
        toastDanger('Tóm tắt không được để trống', 'Lỗi tóm tắt')
        return false;
    }
    return true;
}

function convertTimeDate(date) {
    if (date < 10) {
        return '0' + date;
    }
    return date;
}

function toastWarning(message, title) {
    swal(title, message, "warning")
}

function toastDanger(message, title) {
    swal(title, message, "error")
}

function appendFormdata(idForm, data, objFile, name) {
    name = name || '';
    if (data && typeof data === 'object' && !(data instanceof Date) && !(data instanceof File)) {
        Object.keys(data).forEach(key => {
            appendFormdata(idForm, data[key], objFile, name ? `${name}[${key}]` : key);
        });
    } else {
        let d = new Date(data)
        if (d instanceof Date && !isNaN(d) && name.includes('date')) {
            data = moment(data).format('MM/DD/YYYY')
        }
        $('#' + idForm + ' :input').each(function () {
            if (this.name === name) {
                if (this.type === 'file') {
                    $(this)[0].dataset.oldimg = data
                    objFile.nameFile = data
                    return;
                } else if (this.type === 'select-one') {
                    $(this).val(data).change();
                    return;
                } else if (this.type === 'text' || this.type === 'textarea' || this.type === 'hidden') {
                    $(this).val(data);
                    return;
                }
            }
        })
    }
}

const strToPath = (str) => {
    str = str.toLowerCase()
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "a");
    str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "e");
    str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "i");
    str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "o");
    str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "u");
    str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "y");
    str = str.replace(/Đ/g, "d");
    str = str.replace(/ /g, "-");
    return str;
}