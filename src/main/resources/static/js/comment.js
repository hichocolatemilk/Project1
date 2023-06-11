
let comment = {
    init: function () {
        let _this = this;
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });
        $('#btn-comment-update').on('click', function () {
            _this.commentUpdate();
        });
        $('#btn-comment-delete').on('click', function () {
            _this.commentDelete();
        });

    },
    commentSave: function () {
        let nno = $('#nno').val();
        let data = {
            comment: $('#comment').val(),
            commentWriter: $('#commentWriter').val(),
            board: {
                nno: nno
            }
        };
        $.ajax({
            type: 'POST',
            url: '/api/post/' + nno + '/comment' ,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data: JSON.stringify(data),
            beforeSend : function(xhr) {
                /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
                xhr.setRequestHeader(header, token);
            },
        }).done(function () {
            alert("등록");
            window.location.href = "/board/post/view/" + nno;
        }).fail(function () {
            alert("실패");
            console.log(data)
        })
    },
    commentUpdate: function () {

        let nno = $('#nno').val();
        let id = $('#commentId').val();
        let data = {
            comment: $('#comment').val(),
            commentWriter: $('#commentWriter').val(),
            board: {
                nno: nno
            }
        };
        $.ajax({
            type: 'PUT',
            url: '/api/post/' + nno + '/comment/' + id ,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data: JSON.stringify(data),
            beforeSend : function(xhr) {
                /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
                xhr.setRequestHeader(header, token);
            },
        }).done(function () {
            alert("등록");
            window.location.href = "/board/post/view/" + nno;
        }).fail(function () {
            alert("실패");
            console.log(data)
        })
    },
    commentDelete: function (){
        let nno = $('#nno').val();
        let id = $('#commentId').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/post/' + nno + '/comment/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            beforeSend : function(xhr) {
                /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
                xhr.setRequestHeader(header, token);
            },
        }).done(function() {
            alert("삭제");
            window.location.href = "/board/post/view/" + nno;
        }).fail(function (){
            alert("실패");
        })
    }

};
comment.init();