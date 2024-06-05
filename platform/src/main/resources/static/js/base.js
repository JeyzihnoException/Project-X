async function mainAjaxFunc(path, method, data, headers) {
    return $.ajax({
        url: path,
        method: method,
        data: JSON.stringify(data),
        headers: headers
    });
}

$('.auth').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/authorization", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/authorization");
    })
});

$('.registration').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/registration", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/registration");
    })
});

$('.add-to-friend').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/add/" + $('.main-panel').attr('uuid'), "GET");
});

$('.delete-from-friend').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/delete/" + $('.main-panel').attr('uuid'), "GET");
});

$('.delete-from-friend-friend-menu').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/delete/" + $('.friend-row').attr('uuid'), "GET");
});

$('.friend').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    let uuid = $(this).attr('uuid');
    mainAjaxFunc("/" + uuid, "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/" + uuid);
    });
});

$('.my-friend-btn').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/friend/get-all", "GET").then(function (result) {
        $('.self-info').html(result);
        reBindFriend();
        window.history.pushState(null, "", "/friend");
    });
});

$('.my-page').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/", "GET").then(function (result) {
        $('body').html(result);
        window.history.pushState(null, "", "/");
    });
});

$('.send-message').off('click').on('click', function(e) {
    e.preventDefault();
    e.stopPropagation();
    mainAjaxFunc("/dialogue/get/" + $('.main-panel').attr('uuid'), "GET").then(function (result) {
        $('.self-info').html(result);
        reBindMessage();
        window.history.pushState(null, "", "/dialogue");
    });
});



function reBindFriend(){
    $('.friend-row').off('click').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        let uuid = $(this).attr('uuid');
        mainAjaxFunc("/" + uuid, "GET").then(function (result) {
            $('body').html(result);
            window.history.pushState(null, "", "/" + uuid);
        });
    });
}

function reBindMessage() {
    $('.send-message-btn').off('click').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        mainAjaxFunc("/message/send/" + $('.main-dialogue-container').attr('uuid') + "/" + $('.text-container').val(), "GET").then(function (result) {
            window.history.pushState(null, "", "/dialogue");
        });
    });
    reBindDialoguePartner();
}

function reBindDialoguePartner() {
    $('.dialogue-partner').off('click').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        let uuid = $(this).attr('uuid');
        mainAjaxFunc("/" + uuid, "GET").then(function (result) {
            $('body').html(result);
            window.history.pushState(null, "", "/" + uuid);
        });
    });
}

let dialogueMessageEventSource = new EventSource("/message/listen");

dialogueMessageEventSource.onmessage = (event) => {
    let data = JSON.parse(event.data);

    if (data.dialogueId === $('.main-dialogue-container').attr('uuid')) {
        let div = $('<div>');
        div.addClass( 'message');

        let p = $('<p>');
        p.text(data.message);

        let span = $('<span>');
        span.addClass( 'message-date');
        span.text(data.sendTime);

        div.append(p);
        div.append(span);
        $('.messages-container').append(div);
    }
};












