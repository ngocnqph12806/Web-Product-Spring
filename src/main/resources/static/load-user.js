function openStaff() {
    loadData('/admin/load/user', 0, 5, 'staff')
    document.title = 'Quản lý nhân viên'
}

function openVisit() {
    loadData('/admin/load/user', 0, 5, 'visit')
    document.title = 'Quản lý khách hàng'
}

var objFileAvatar = {
    targetFileAvatar: null,
    nameFile: null,
    data: null
}

async function onChangeFileAvatar(e) {
    objFileAvatar.targetFileAvatar = e
    objFileAvatar.nameFile = getNameTime() + e.files[0].name
    let data = await toBase64(e.files[0])
    objFileAvatar.data = data
}

async function saveStaff() {
    let data = JSON.parse(JSON.stringify($("#form-input-staff").serializeArray()))
    let obj = {};
    for (let i = 0; i < data.length; i++) {
        let name = data[i].name;
        let value = data[i].value;
        // họ tên không chứa số
        if (name === 'fullName' && !checkRealName(value)) return;
        if (name === 'dateOfBirth') {
            if (check18AgesAdmin(value)) {
                value = value.substring(value.lastIndexOf('/') + 1) + '-' + value.substring(0, value.lastIndexOf('/')).replaceAll('/', '-');
            } else {
                return;
            }
        }
        if (name === 'userName' && !checkUserName(value)) return;
        // if (name === 'password' && !checkPassword(value)) return;
        if (name === 'email' && !checkEmail(value)) return;
        if (name === 'phoneNumber' && !checkPhoneNumber(value)) return;
        if (name === 'address' && !checkAddress(value)) return;
        obj[name] = value;
    }
    let role = $('#role').val();
    if (role === null || role === undefined || role === '') {
        swal('Lỗi', 'Chưa chọn chức vụ', 'warning');
        return;
    } else {
        obj['role'] = role
    }
    // if (objFileAvatar.nameFile === null || objFileAvatar.nameFile === undefined || objFileAvatar.nameFile === '') {
    // if (objFileAvatar.targetFileAvatar !== null && objFileAvatar.targetFileAvatar !== undefined) {
    //     await writeFileGitHub(objFileAvatar.targetFileAvatar, objFileAvatar.nameFile)
    //     objFileAvatar.targetFileAvatar = null
    // }
    // avatar đúng định dạng hình ảnh
    if (!checkImage(objFileAvatar.nameFile, 'Ảnh đại diện')) {
        return
    } else {
        obj['avatar'] = objFileAvatar.nameFile
    }
    console.log(obj)
    swal('Đang lưu...', 'Vui lòng đợi', 'success')
    if (objFileAvatar.targetFileAvatar !== null && objFileAvatar.targetFileAvatar !== undefined
        && objFileAvatar.data !== null && objFileAvatar.data !== undefined) {
        let data = objFileAvatar.data + '';
        let base = data.substring(data.indexOf(",") + 1, data.length)
        await fetch('https://api.github.com/repos/ngocnqph12806/Repo_File/contents/' + objFileAvatar.nameFile, {
            method: 'PUT',
            headers: {
                "Authorization": "Bearer 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",
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "message": "uploading a file in date: " + getNameTime(),
                "content": base
            }),
        })
            .then(response => response.json())
            .then(out => {
                console.log("up thành công");
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        objFileAvatar.data = null
        objFileAvatar.targetFileAvatar = null
    }
    // }
    setTimeout(async function () {
        let url = window.location.href + ''
        let typeUser = url.substring(url.lastIndexOf('#') + 1)
        if (obj.id !== null && obj.id !== undefined && obj.id !== '') {
            await saveWithAPI(JSON.stringify(obj), '/api/users/' + obj.id, 'PUT', '/admin/load/user', 0, 5, typeUser)
        } else {
            await saveWithAPI(JSON.stringify(obj), '/api/users', 'POST', '/admin/load/user', 0, 5, typeUser)
        }
    }, 10)
}

function getStaff(e) {
    $.ajax({
        url: '/api/users/' + e.dataset.id + '?modal',
        type: 'GET',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
        },
        success: function (response) {
            appendFormdata('form-input-staff', response.data, objFileAvatar)
        }
    });
}

function changeStaff(e) {
    let method = e.dataset.method;
    let data = e.dataset.methodData;
    let id = e.dataset.id;
    console.log(id)
    $.ajax({
        url: '/api/users/' + id,
        type: 'GET',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
        },
        success: async function (response) {
            let url = window.location.href + ''
            let typeUser = url.substring(url.lastIndexOf('#') + 1)
            if (method === 'status') {
                let obj = response;
                obj.status = data
                await saveWithAPI(JSON.stringify(obj), '/api/users/' + obj.id, 'PUT', '/admin/load/user', 0, 5, typeUser)
            } else if (method === 'block') {
                let obj = response;
                obj.block = data
                await saveWithAPI(JSON.stringify(obj), '/api/users/' + obj.id, 'PUT', '/admin/load/user', 0, 5, typeUser)
            } else {
                swal('Hmmm!', 'Đừng sửa code...', 'warning')
            }
        },
        error: function (response) {
            swal('Thất bại', response.responseJSON.message, 'error')
        }
    });
}