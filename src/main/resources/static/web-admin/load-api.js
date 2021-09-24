//LOAD LINK GITHUB
function getLinkGithub() {
    let fileGithub = $('.file-github')
    for (let i = 0; i < fileGithub.length; i++) {
        if (fileGithub[i].dataset.fileGithub !== undefined
            && fileGithub[i].dataset.fileGithub !== null) {
            fileGithub[i].src = 'https://raw.githubusercontent.com/ngocnqph12806/Repo_File/main/' + fileGithub[i].dataset.fileGithub
        }
    }
}

function saveWithAPI(formData, url, urlSuccess, method) {
    if (method === 'POST' || method === 'PUT') {
        $.ajax({
            url: url,
            type: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            enctype: 'multipart/form-data',
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (data) {
                console.log(data)
                urlSuccess = urlSuccess.substring(0, urlSuccess.lastIndexOf('#'))
                if (data.code === 200) {
                    swal("Thành công", 'Đã thêm mới', "success").then((function (t) {
                        if (t) window.location = urlSuccess
                    }))
                } else if (data.code === 201) {
                    swal("Thành công", 'Đã chỉnh sửa', "success").then((function (t) {
                        if (t) window.location = urlSuccess
                    }))
                }
            },
            error: function (data) {
                swal("Thất bại", data.responseJSON.message, "warning")
            }
        });
    }
}

function getWithAPI(obj, path) {
    $.ajax({
        url: '/api/' + path,
        type: 'GET',
        data: obj,
        contentType: 'application/json',
        success: function (response) {
            obj = response;
        },
        error: function (response) {
            obj = response;
        }
    });
}

// GITHUB
// save file to github
// let today = new Date();
// today = '[' + String(today.getDate()).padStart(2, '0') + '-'
//     + String(today.getMonth() + 1).padStart(2, '0') + '-'
//     + today.getFullYear() + ' '
//     + today.toTimeString() + ']' + ' == '
//     + String(today.getMilliseconds()).padStart(3, '0') + '.';
const nameOwner = 'ngocnqph12806';
const nameRepo = 'Repo_File';
const token = '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';

function getNameTime() {
    let today = new Date();
    today = '[' + String(today.getDate()).padStart(2, '0') + '-'
        + String(today.getMonth() + 1).padStart(2, '0') + '-'
        + today.getFullYear() + ' '
        + today.toTimeString() + ']' + ' == '
        + String(today.getMilliseconds()).padStart(3, '0') + '.';
    return today
}

function readFileGithub(fileName, tagImg) {
    $.ajax({
        url: 'https://api.github.com/repos/' + nameOwner + '/' + nameRepo + '/contents/' + fileName,
        type: 'GET',
        headers: {
            "Authorization": +token,
            "Accept": "application/vnd.github.v3+json"
        }
    }).done(function (response) {
        tagImg.src = 'data:image/png;base64,' + response.content
    });
}

function writeFileGitHub(target, fileName) {
    if (target.files && target.files[0]) {
        let reader = new FileReader();
        // let fileName = today + target.files[0].name
        reader.onloadend = function () {
            let base64result = reader.result.substr(reader.result.indexOf(',') + 1);
            saveFileGitHub(fileName, "uploading a file in date: " + getNameTime(), base64result)
        }
        reader.readAsDataURL(target.files[0]);
        // return fileName
    }
}

function saveFileGitHub(fileName, fileMessage, fileContent) {
    $.ajax({
        url: 'https://api.github.com/repos/' + nameOwner + '/' + nameRepo + '/contents/' + fileName,
        type: 'PUT',
        headers: {
            "Authorization": "Bearer " + token,
            "Accept": "application/vnd.github.v3+json"
        },
        data: JSON.stringify({
            "message": fileMessage,
            "content": fileContent
        })
    })
}

function loadImg() {
    let getImg = $('img')
    for (let i = 0; i < getImg.length; i++) {
        if (getImg[i].dataset.imgGithub !== undefined
            && getImg[i].dataset.imgGithub !== null) {
            console.log(getImg[i].dataset.imgGithub)
            readFileGithub(getImg[i].dataset.imgGithub, getImg[i])
        }
    }
}