function openVoucher() {
    loadData('/admin/voucher/load', 0, 5)
    document.title = 'Quản lý thương hiệu'
}
//
// function getVoucher(e) {
//     $.ajax({
//         url: '/api/vouchers/' + e.dataset.id + '?modal',
//         type: 'GET',
//         contentType: 'application/json',
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader("Authorization", "Bearer " + getSession('access_token'));
//         },
//         success: function (response) {
//             appendFormdata('form-input-voucher', response.data)
//         }
//     });
// }
//
// function saveVoucher() {
//     let data = JSON.parse(JSON.stringify($("#form-input-voucher").serializeArray()))
//     let obj = {};
//     for (let i = 0; i < data.length; i++) {
//         let name = data[i].name;
//         let value = data[i].value;
//         // họ tên không chứa số
//         if (name === 'code' && !checkCode(value, 'Mã áp dụng')) return;
//         if (name === 'title' && !checkName(value, 'Tiêu đề')) return;
//         if (name === 'quantity' && !checkQuantity(value, 'Số lượng mã áp dụng')) return;
//         if (name === 'priceSale' && !checkQuantity(value, 'Giá trị mã giảm giá')) return;
//         if (name === 'description' && !checkDescription(value, 'Mô tả mã giảm giá')) return;
//         obj[name] = value;
//     }
//     let dateTime = $('#date-time-voucher').val() + ''
//     console.log(dateTime);
//     let dateStart = dateTime.substring(0, dateTime.lastIndexOf('-')).trim()
//     dateStart = dateStart.substring(dateStart.lastIndexOf('/') + 1) + '-' + dateStart.substring(0, dateStart.lastIndexOf('/')).replaceAll('/', '-');
//     let dateEnd = dateTime.substring(dateTime.lastIndexOf('-') + 1).trim()
//     dateEnd = dateEnd.substring(dateEnd.lastIndexOf('/') + 1) + '-' + dateEnd.substring(0, dateEnd.lastIndexOf('/')).replaceAll('/', '-');
//     obj.dateStart = dateStart
//     obj.dateEnd = dateEnd
//     console.log(obj)
//     setTimeout(function () {
//         console.log(obj.id)
//         if (obj.id !== null && obj.id !== undefined && obj.id !== '') {
//             saveWithAPI(JSON.stringify(obj), '/api/vouchers/' + obj.id, 'PUT', '/admin/voucher/load', 0, 5)
//         } else {
//             saveWithAPI(JSON.stringify(obj), '/api/vouchers', 'POST', '/admin/voucher/load', 0, 5)
//         }
//     }, 10)
// }