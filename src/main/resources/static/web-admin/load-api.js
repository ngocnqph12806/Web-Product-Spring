//LOAD LINK GITHUB
function getLinkGithub() {
    let fileGithub = $('.file-github')
    for (let i = 0; i < fileGithub.length; i++) {
        console.log(fileGithub[i])
        if (fileGithub[i].dataset.fileGithub !== undefined
            && fileGithub[i].dataset.fileGithub !== null) {
            fileGithub[i].src = 'https://raw.githubusercontent.com/ngocnqph12806/Repo_File/main/' + fileGithub[i].dataset.fileGithub
        }
    }
}

// ADMIN
function loadDataAdmin(index, url_page) {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: '/api/' + url_page + '/load',
        type: 'POST',
        data: {
            "now-page": index + 6
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === null || data === '') {
                document.querySelector('.main__load').outerHTML = ''
            } else {
                $('#parent-show').append(data).html()
                if (($('.children-show').length - index) % 6 !== 0) {
                    document.querySelector('.main__load').outerHTML = ''
                }
            }
        },
        error: function (response) {

        }
    });
}

function submitFormDataAdmin(formData, url, urlSuccess) {
    $.ajax({
        url: url,
        type: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        // },
        success: function (data) {
            console.log(data)
            if (data.result) {
                swal("Thành công", data.message, "success").then((function (t) {
                    if (t) window.location = urlSuccess
                }))
            } else {
                swal("Thất bại", data.message, "warning")
            }
        },
        error: function (data) {
            swal("Thất bại", data.responseJSON.message, "warning")
        }
    });
}

// WEB
function loadDataWeb(index, url_page) {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: '/api/' + url_page + '/load',
        type: 'POST',
        data: {
            "now-page": index + 6
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === null || data === '') {
                document.querySelector('.main__load').outerHTML = ''
            } else {
                $('#parent-show').append(data).html()
                if (($('.children-show').length - index) % 6 !== 0) {
                    document.querySelector('.main__load').outerHTML = ''
                }
            }
        }
    });
}

function submitFormDataWeb(formData, url) {
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        beforeSend: function (xhr) {
            xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
        },
        success: function (data) {
            console.log(data);
            if (data === 'Thay đổi thông tin thành công' || data === 'Thay đổi mật khẩu mới thành công') {
                swal({
                    text: data,
                    icon: "success",
                    buttonsStyling: !1,
                    confirmButtonText: "Ok, got it!",
                }).then((function () {
                    window.location = '/profile'
                }))
            } else {
                swal({
                    text: data,
                    icon: "warning",
                    buttonsStyling: !1,
                    confirmButtonText: "Ok",
                    customClass: {confirmButton: "btn fw-bold btn-light-primary"}
                })
            }
        }
    });
}

// SLIDEBAR WEB
function loadLinkSidebar() {
    let getUrl = window.location.href
    let sidebar__nav_link = document.querySelectorAll('.sidebar__nav-link');
    let urlHome = true;
    for (let i = sidebar__nav_link.length - 1; i >= 0; i--) {
        console.log(getUrl)
        console.log(sidebar__nav_link[i].href)
        if (getUrl.includes(sidebar__nav_link[i].href)) {
            sidebar__nav_link[i].className = 'sidebar__nav-link sidebar__nav-link--active';
            urlHome = false;
            break;
        }
    }
    if (sidebar__nav_link.length > 0) {
        console.log(urlHome)
        if (urlHome) {
            console.log('a')
            sidebar__nav_link[0].className = 'sidebar__nav-link sidebar__nav-link--active';
        }
    }
}


// GITHUB
// save file to github
let today = new Date();
today = '[' + String(today.getDate()).padStart(2, '0') + '-'
    + String(today.getMonth() + 1).padStart(2, '0') + '-'
    + today.getFullYear() + ' '
    + today.toTimeString() + ']' + ' == '
    + String(today.getMilliseconds()).padStart(3, '0') + '.';
const nameOwner = 'ngocnqph12806';
const nameRepo = 'Repo_File';
const token = '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';

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

function writeFileGitHub(target) {
    console.log(target)
    if (target.files && target.files[0]) {
        let reader = new FileReader();
        let fileName = today + target.files[0].name
        reader.onloadend = function () {
            let base64result = reader.result.substr(reader.result.indexOf(',') + 1);
            saveFileGitHub(fileName, "uploading a file in date: " + today, base64result)
        }
        console.log(fileName)
        reader.readAsDataURL(target.files[0]);
        return fileName
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