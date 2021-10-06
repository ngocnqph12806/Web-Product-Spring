//LOAD LINK GITHUB
// function getLinkGithub() {
//     let fileGithub = $('.file-github')
//     for (let i = 0; i < fileGithub.length; i++) {
//         if (fileGithub[i].dataset.fileGithub !== undefined
//             && fileGithub[i].dataset.fileGithub !== null) {
//             fileGithub[i].src = 'https://raw.githubusercontent.com/ngocnqph12806/Repo_File/main/' + fileGithub[i].dataset.fileGithub
//         }
//     }
// }

$(document).ready(function () {
    let token = getSession('access_token')
    console.log('token: ' + token)
    if (token !== null && token !== undefined && token !== '') {
        console.log(token)
        $.ajax({
            url: '/token/refresh',
            type: 'GET',
            // contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function (response) {
                window.sessionStorage.setItem('access_token', JSON.stringify(response.access_token))
            },
            error: function (response) {
                window.location = '/login'
            }
        });
    } else {
        window.location = '/login'
    }
    reloadPage()
})

function reloadPage() {
    let url = window.location.href + ''
    let type = url.substring(url.lastIndexOf("#") + 1)
    switch (type) {
        case 'staff':
            openStaff()
            break;
        case 'visit':
            openVisit()
            break
    }
}

function getSession(key) {
    return JSON.parse(window.sessionStorage.getItem(key))
}

function saveSession(key, data) {
    window.sessionStorage.setItem(key, JSON.stringify(data))
}

function removeSession(key) {
    window.sessionStorage.removeItem(key)
}

let urlApiLoadPage = '';

function clickPage(e) {
    let page = e.dataset.index
    let url = window.location.href + ''
    let typeUser = url.substring(url.lastIndexOf('#') + 1)
    console.log(typeUser)
    loadData(urlApiLoadPage, page, 5, typeUser)
}

function loadData(url, page, size, typeUser) {
    urlApiLoadPage = url
    typeUser == null || typeUser == undefined ? typeUser = '' : typeUser = typeUser
    $.ajax({
        url: url + '?_p=' + page + '&_s=' + size + '&_type=' + typeUser,
        type: 'POST',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
        },
        success: function (response) {
            $('#data-user').html(response)
        },
        error: function (e) {
            console.log(e)
            $('#data-user').html('<h3>' + e.status + '</h3>')
        }
    });
}

function saveWithAPI(formData, url, method, linkSuccess, pageSuccess, sizeSucess, typeUser) {
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
                xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
            },
            success: function (data) {
                console.log(linkSuccess)
                console.log(typeUser)
                $('.modal').modal('hide');
                if (method === 'POST') {
                    swal("Thành công", 'Đã thêm mới', "success").then((function (t) {
                        if (t && linkSuccess !== null && linkSuccess !== undefined
                            && pageSuccess !== null && pageSuccess !== undefined
                            && sizeSucess !== null && sizeSucess !== undefined) {
                            loadData(linkSuccess, pageSuccess, sizeSucess, typeUser)
                        }
                    }))
                } else if (method === 'PUT') {
                    swal("Thành công", 'Đã chỉnh sửa', "success").then((function (t) {
                        if (t && linkSuccess !== null && linkSuccess !== undefined
                            && pageSuccess !== null && pageSuccess !== undefined
                            && sizeSucess !== null && sizeSucess !== undefined) {
                            loadData(linkSuccess, pageSuccess, sizeSucess, typeUser)
                        }
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
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
        },
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

async function writeMutilFileGitHub(objFile) {
    for (let i = 0; i < objFile.targetFile.length; i++) {
        if (objFileImage.targetFile[i] !== null && objFileImage.targetFile[i] !== undefined) {
            let target = objFile.targetFile[i];
            let fileName = objFile.nameFile[i];
            await writeMutilFileGitHub(target, fileName);
            objFile.targetFile[i] = null;
        }
    }

}

const toBase64 = objFile => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(objFile);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
});

function writeFileGitHub(target, fileName) {
    if (target.files && target.files[0]) {
        let reader = new FileReader();
        // let fileName = today + target.files[0].name
        reader.onloadend = function () {
            let base64result = reader.result.substr(reader.result.indexOf(',') + 1);
            // saveFileGitHub(fileName, "uploading a file in date: " + getNameTime(), base64result)
            $.ajax({
                url: 'https://api.github.com/repos/' + nameOwner + '/' + nameRepo + '/contents/' + fileName,
                type: 'PUT',
                headers: {
                    "Authorization": "Bearer " + token,
                    "Accept": "application/vnd.github.v3+json"
                },
                data: JSON.stringify({
                    "message": "uploading a file in date: " + getNameTime(),
                    "content": base64result
                }),
                success: function (e) {
                    console.log('lưu thành công');
                }
            })
        }
        reader.readAsDataURL(target.files[0]);
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