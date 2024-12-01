$(document).ready(function () {
    // 페이지 로드 시 각 좋아요 버튼 상태 확인
    $('.likeBtn').each(function () {
        var post_id = $(this).data('like-id');
        var $heart = $(this).find('.heart');

        $.ajax({
            url: '/api/v1/posts/' + post_id + '/like',
            type: 'GET',
            success: function (isLiked) {
                // 서버에서 반환된 좋아요 상태에 따라 색상 변경
                if (isLiked) {
                    $heart.css('color', 'red');
                    $heart.text('♥');
                } else {
                    $heart.css('color', 'gray');
                    $heart.text('♡');
                }
            },
            error: function () {
                alert('좋아요 상태를 가져오는 데 실패했습니다.');
            }
        });
    });

    var main = {
        init: function () {
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
            $('.likeBtn').on('click', function () {
                _this.like($(this));
            })
        },
        save: function () {
            var data = {
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        update: function () {
            var data = {
                title: $('#title').val(),
                content: $('#content').val()
            };

            var id = $('#id').val();

            $.ajax({
                type: 'PUT',
                url: '/api/v1/posts/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        delete: function () {
            var id = $('#id').val();

            $.ajax({
                type: 'DELETE',
                url: '/api/v1/posts/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('글이 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        like: function ($likeBtn) {
            const post_id = $likeBtn.data('like-id');
            const heart = $likeBtn.find('.heart');
            const liked = heart.css('color') === 'rgb(255, 0, 0)';  // 현재 상태가 빨간색일 경우 좋아요 상태

            // 좋아요 상태에 따라 색상과 텍스트 반영
            if (liked) {
                heart.css('color', 'gray');
                heart.text('♡');
            } else {
                heart.css('color', 'red');
                heart.text('♥');
            }

            // PUT 요청으로 좋아요 상태 변경
            $.ajax({
                type: 'PUT',
                url: '/api/v1/posts/' + post_id + '/like',  // 좋아요 토글 API 호출
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({ post_id: post_id })  // post_id만 전달
            }).done(function () {
                alert('좋아요 상태가 변경되었습니다.');
            }).fail(function (error) {
                alert('좋아요 상태 변경 실패: ' + JSON.stringify(error));
            });
        }
    };

    main.init();
});
