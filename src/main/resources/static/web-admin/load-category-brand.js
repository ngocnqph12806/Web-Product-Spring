function openCategory() {
    loadData('/admin/category/load', 0, 5)
    document.title = 'Quản lý loại sản phẩm'
}

var objFileBannerCategory = {
    targetFileAvatar: null,
    nameFile: null,
    data: null
}

let urlCallDataCategoryOrBrand = '';

async function onChangeFile(e) {
    objFileBannerCategory.targetFileAvatar = e
    objFileBannerCategory.nameFile = getNameTime() + e.files[0].name
    let data = await toBase64(e.files[0])
    objFileBannerCategory.data = data
}

async function saveCategory() {
    let data = JSON.parse(JSON.stringify($("#form-input-category").serializeArray()))
    let obj = {};
    for (let i = 0; i < data.length; i++) {
        let name = data[i].name;
        let value = data[i].value;
        if (name === 'name' && !checkName(value, 'Tên loại')) return;
        if (name === 'path' && !checkPath(value)) return;
        // if (name === 'banner' && !checkImage(value, 'Banner')) return;
        obj[name] = value;
    }
    if (!checkImage(objFileBannerCategory.nameFile, 'Ảnh banner')) {
        return
    } else {
        obj['banner'] = objFileBannerCategory.nameFile
    }
    console.log(obj)
    swal('Đang lưu...', 'Vui lòng đợi', 'success')
    if (objFileBannerCategory.targetFileAvatar !== null && objFileBannerCategory.targetFileAvatar !== undefined
        && objFileBannerCategory.data !== null && objFileBannerCategory.data !== undefined) {
        let data = objFileBannerCategory.data + '';
        let base = data.substring(data.indexOf(",") + 1, data.length)
        await fetch('https://api.github.com/repos/ngocnqph12806/Repo_File/contents/' + objFileBannerCategory.nameFile, {
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
        objFileBannerCategory.data = null
        objFileBannerCategory.targetFileAvatar = null
    }
    setTimeout(function () {
        urlCallDataCategoryOrBrand = '/admin/category/load'
        if (obj.id === null || obj.id === undefined || obj.id === '') {
            saveWithAPI(JSON.stringify(obj), '/api/categories', 'POST', '/admin/category/load', 0, 5)
        } else {
            saveWithAPI(JSON.stringify(obj), '/api/categories' + obj.id, 'PUT', '/admin/category/load', 0, 5)
        }
    }, 10)
}

function getCategory(e) {
    let id = e.dataset.id;
    if (id !== null && id !== undefined && id !== '') {
        $.ajax({
            url: '/api/categories/' + id + '?modal',
            type: 'GET',
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
            },
            success: function (response) {
                let obj = response.data;
                appendFormdata('form-input-category', obj, objFileBannerCategory)
            }
        });
    }
}

function changeNameCategoryAndChangePathCategory() {
    $('#path-url').val(strToPath($('#name-category').val()))
}

function changePathUrlCategory() {
    $('#path-url').val(strToPath($('#path-url').val()))
}

function openBrand() {
    loadData('/admin/brand/load', 0, 5)
    document.title = 'Quản lý thương hiệu'
}

function saveBrand() {
    let data = $("#form-input-brand").serializeArray()
    let obj = {};
    for (let i = 0; i < data.length; i++) {
        let name = data[i].name;
        let value = data[i].value;
        if (name === 'name' && !checkName(value, 'Tên thương hiệu')) return;
        if (name === 'phoneNumber' && !checkPhoneNumber(value)) return;
        if (name === 'email' && !checkEmail(value)) return;
        if (name === 'address' && !checkAddress(value)) return;
        obj[name] = value;
    }
    console.log(obj)
    setTimeout(function () {
        urlCallDataCategoryOrBrand = '/admin/brand/load'
        if (obj.id === null || obj.id === undefined || obj.id === '') {
            saveWithAPI(JSON.stringify(obj), '/api/brand', 'POST', '/admin/brand/load', 0, 5)
        } else {
            saveWithAPI(JSON.stringify(obj), '/api/brand' + obj.id, 'PUT', '/admin/brand/load', 0, 5)
        }
    }, 10)
}

function getBrand(e) {
    let id = e.dataset.id;
    if (id !== null && id !== undefined && id !== '') {
        $.ajax({
            url: '/api/brand/' + id + '?modal',
            type: 'GET',
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
            },
            success: function (response) {
                let obj = response.data
                appendFormdata('form-input-brand', obj)
            }
        });
    }
}

function saveCollection() {
    let data = JSON.parse(JSON.stringify($("#form-input-collection").serializeArray()))
    let obj = {};
    for (let i = 0; i < data.length; i++) {
        let name = data[i].name;
        let value = data[i].value;
        if (name === 'name' && !checkName(value, 'Tên danh mục')) return;
        obj[name] = value;
    }
    setTimeout(function () {
        if (obj.id === null || obj.id === undefined || obj.id === '') {
            saveWithAPI(JSON.stringify(obj), '/api/collections', 'POST', urlCallDataCategoryOrBrand, 0, 5)
        } else {
            saveWithAPI(JSON.stringify(obj), '/api/collections' + obj.id, 'PUT', urlCallDataCategoryOrBrand, 0, 5)
        }
    }, 10)
}

function changeStatusCollecton(e) {
    let method = e.dataset.method;
    let data = e.dataset.methodData;
    let id = e.dataset.id;
    $.ajax({
        url: '/api/collections/' + id,
        type: 'GET',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
        },
        success: function (response) {
            if (method === 'status') {
                let obj = response.data;
                console.log(obj)
                obj.status = data
                saveWithAPI(JSON.stringify(obj), '/api/collections' + obj.id, 'PUT', urlCallDataCategoryOrBrand, 0, 5)
            } else {
                swal('Hmmm!', 'Đừng sửa code...', 'warning')
            }
        },
        error: function (response) {
            swal('Thất bại', response.responseJSON.message, 'error')
        }
    });
}

function getCollection(e) {
    let id = e.dataset.id
    if (id !== null && id !== undefined && id !== '') {
        $.ajax({
            url: '/api/collections/' + id,
            type: 'GET',
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
            },
            success: function (response) {
                let obj = response.data;
                appendFormdata('form-input-collection', obj)
                $('#btn-input-collection').html('Chỉnh sửa')
            },
            error: function (response) {
                swal("Lỗi", response.responseJSON.message, "error")
            }
        });
    }
}