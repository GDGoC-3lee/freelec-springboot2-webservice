$(document).ready(function () {
    $('.likeBtn').each(function (){
        var post_id = $(this).data('like-id');
        var $heart = $(this).find('.heart');

        $.ajax({
            url:'api/v1/posts/' + post_id + '/like',
            type: 'GET',
            success: function (isLiked) {
                if(isLiked) {
                    $heart.css('color', 'red');
                    $heart.text('♥');
                } else {
                    $heart.css('color', 'gray');
                    $heart.text('♡');
                }
            },
            error: function() {
                alert('좋아요 상태를 가져오는 데 실패했습니다.');
            }
        })
    })
})
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('.likeBtn').on('click', function(){
            _this.like($(this));
        })
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    like : function ($likeBtn) {
        const heart = $likeBtn.find('.heart');
        const liked = (heart.css('color') === 'rgb(255, 0, 0)');

        if(liked) {
            heart.css('color', 'gray');
            heart.text('♡');
            // delete 서버 요청
            $.ajax({
                type: 'DELETE',
                url: 'api/v1/posts/',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify({
                    like_id: $(".likeBtn").data("like-id")
                })
            }).done(function() {
                alert('좋아요 취소');
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
        else {
            heart.css('color', 'red');
            heart.text('♥');
            // saveOrUpdate 서버 요청
            $.ajax({
                type: 'PUT',
                url: 'api/v1/posts/',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify({
                    post_id: $(".likeBtn").data("like-id"), //동일한 id를 갖기 때문
                })
            }).done(function() {
                alert('좋아요 등록');
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
}

main.init();
