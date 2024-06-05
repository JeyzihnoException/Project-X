async function mainAjaxFunc(path, method, data, headers) {
    return $.ajax({
        url: path,
        method: method,
        data: JSON.stringify(data),
        headers: headers
    });
}

$(document).on('click', '.auth', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/authorization", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/authorization");
    })
});

$(document).on('click', '.registration', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/registration", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/registration");
    })
});

$(document).on('click', '.add-to-friend', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/add/" + $('.main-panel').attr('uuid'), "GET").then(function (result) {
    })
});

$(document).on('click', '.delete-from-friend', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/delete/" + $('.main-panel').attr('uuid'), "GET").then(function (result) {
    })
});

$(document).on('click', '.delete-from-friend-friend-menu', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/delete/" + $('.friend-row').attr('uuid'), "GET").then(function (result) {
    })
});

$(document).on('click', '.friend', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/" + $(this).attr('uuid'), "GET").then(function (result) {
        $('body').html(result);
    })
});

$(document).on('click', '.my-friend-btn', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/get-all", "GET").then(function (result) {
        $('.self-info').html(result);
        window.history.pushState(null, "", "/friend");
    })
});

$(document).on('click', '.my-page', async function (e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/");
    })
});




// $(document).on('click', '.auth-btn', async function (e) {
//     e.preventDefault();
//     e.stopPropagation();
//     let username = $('.username-input').eq(0).val();
//     let password = $('.password-input').eq(0).val();
//     let userObject = {
//         userName: username,
//         password: password
//     };
//
//     mainAjaxFunc("/authorization", "POST", userObject,
//         {'Content-Type': 'application/json'}).then(function (result) {
//         $('body').html(result);
//     })
// });