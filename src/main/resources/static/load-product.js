function openProduct() {
    loadData('/admin/load/product', 0, 5)
    document.title = 'Quản lý sản phẩm'
}

let objFileImage = {
    data: [],
    nameFile: [],
}

let objFileUseManual = {
    data: null,
    nameFile: null,
}

async function onChangeImage(e) {
    if (!checkImage(e.files[0].name, 'File ')) return;
    let index = e.dataset.index
    // objFileUseManual.targetFile = e
    // objFileImage.targetFile[index] = e
    objFileImage.nameFile[index] = getNameTime() + e.files[0].name
    let data = await toBase64(e.files[0])
    objFileImage.data[index] = data
}

function changeNameProductAndChangePathProduct() {
    $('#path-product').val(strToPath($('#name-product').val()))
}

function changePathUrlProduct() {
    let pathUrl = strToPath($('#path-product').val())
    $('#path-product').val(pathUrl)
}

async function onChangeFile(e) {
    objFileUseManual.nameFile = getNameTime() + e.files[0].name
    let data = await toBase64(e.files[0])
    objFileUseManual.data = data
}

async function saveProduct() {
    let form = $("#form-input-product").serializeArray()
    let obj = {};
    for (let i = 0; i < form.length; i++) {
        let name = form[i].name;
        obj[name] = form[i].value;
    }
    console.log(obj)
    if (!checkFileDoc(objFileUseManual.nameFile, 'File hướng dẫn sử dụng')) {
        return
    } else {
        obj['pathUserManual'] = objFileUseManual.nameFile
    }
    for (let i = 0; i < objFileImage.nameFile.length; i++) {
        if (objFileImage.nameFile[i] !== null
            && objFileImage.nameFile[i] !== undefined
            && objFileImage.nameFile[i] !== '') {
            if (!checkImage(objFileImage.nameFile[i], 'Hình ảnh số ' + (i + 1))) {
                objFileImage.nameFile[i] = null;
                return;
            }
        }
    }
    obj.images = objFileImage.nameFile
    console.log(obj)
    swal('Đang lưu...', 'Vui lòng đợi', 'success')
    if (objFileUseManual.data !== null && objFileUseManual.data !== undefined) {
        let data = objFileUseManual.data + '';
        console.log(data)
        let base = data.substring(data.indexOf(",") + 1, data.length)
        await fetch('https://api.github.com/repos/ngocnqph12806/Repo_File/contents/' + objFileUseManual.nameFile, {
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
        objFileUseManual.data = null
    }
    for (let i = 0; i < objFileImage.nameFile.length; i++) {
        if (objFileImage.nameFile[i] !== null
            && objFileImage.nameFile[i] !== undefined
            && objFileImage.nameFile[i] !== '') {
            if (!checkImage(objFileImage.nameFile[i], 'Hình ảnh số ' + (i + 1))) {
                objFileImage.nameFile[i] = null;
                return;
            } else if (objFileImage.data[i] !== null && objFileImage.data[i] !== undefined
                && objFileImage.nameFile[i] !== null && objFileImage.nameFile[i] !== undefined) {
                let data = objFileImage.data[i] + '';
                console.log(data)
                let base = data.substring(data.indexOf(",") + 1, data.length)
                await fetch('https://api.github.com/repos/ngocnqph12806/Repo_File/contents/' + objFileImage.nameFile[i], {
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
                objFileImage.data[i] = null;
            }
        }
    }
    if (obj.id !== null && obj.id !== undefined && obj.id !== '') {
        saveWithAPI(JSON.stringify(obj), '/api/product/' + obj.id, 'PUT', '/admin/load/product', 0, 5)
    } else {
        saveWithAPI(JSON.stringify(obj), '/api/product', 'POST', '/admin/load/product', 0, 5)
    }
}

async function saveFile() {
    if (objFileUseManual.targetFile !== null && objFileUseManual.targetFile !== undefined) {
        await writeFileGitHub(objFileUseManual.targetFile, objFileUseManual.nameFile)
        objFileUseManual.targetFile = null
    }
    for (let i = 0; i < objFileImage.nameFile.length; i++) {
        if (objFileImage.nameFile[i] !== null
            && objFileImage.nameFile[i] !== undefined
            && objFileImage.nameFile[i] !== '') {
            if (!checkImage(objFileImage.nameFile[i], 'Hình ảnh số ' + (i + 1))) {
                objFileImage.nameFile[i] = null;
                return false;
            } else if (objFileImage.targetFile[i] !== null && objFileImage.targetFile[i] !== undefined
                && objFileImage.nameFile[i] !== null && objFileImage.nameFile[i] !== undefined) {
                await writeFileGitHub(objFileImage.targetFile[i], objFileImage.nameFile[i])
                objFileImage.nameFile[i] = null;
            }
        }
    }
    return true;
}

function getProduct(e) {
    $.ajax({
        url: '/api/product/' + e.dataset.id + '?modal',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            appendFormdata('form-input-product', response, objFileUseManual)
        }
    });
}

function changeStatusProduct(e) {
    let data = e.dataset.methodData;
    let id = e.dataset.id
    if (e.dataset.method === 'status') {
        $.ajax({
            url: '/api/product/' + id + '/status',
            type: 'PUT',
            data: {"status": (data === 'true')},
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
            },
            success: function (response) {
                swal("Thành công", "Thay đổi trạng thái sản phẩm thành công", "success")
                loadData('/admin/load/product', 0, 5)
            },
            error: function (data) {
                swal("Thất bại", data.responseJSON.message, "warning")
            }
        });
    }
}