UP = 1;
DOWN = 2;
TIRED = 3;
var construct = function() {
    // ------------------------常量------------------------
    var pauseButton = $('.pauseButton');
    var playButton = $('.playButton');
    var skipButton = $('.skipButton');
    var volumeButton = $('.volumeButton');
    var volumeBg = $('.volumeBackground')
    var dragVolume = $('.volumeKnob');
    var thumbUpButton = $('.thumbUpButton');
    var tiredButton = $('.tiredButton');
    var thumbDownButton = $('.thumbDownButton');
    var progressMaxWidth = 232;
    // ------------------------private 变量------------------------
    var initSong = 0; // 通过 en.360.fm/#12345 形式，传递的歌曲
    var doneInitSong = false;
    // user
    var user = null;
    var playLists = [];
    var selectedListId = {};
    var current = null;
    var status = 0; // 0 停止 1 播放 2暂停
    var history = [];
    var curVolume = 100;
    var progressBar = function() {
	var elapsedTime = $('.elapsedTime');
	var remainingTime = $('.remainingTime');
	var progressMiddle = $('.progressMiddle');
	var interval;
	var duration = 0;
	var updateBar = function() {
	    var position = parseInt(current.sound.position / 1000);
	    var width = Math.round(position / duration * 232);
	    var s = parseInt(position % 60);
	    elapsedTime.html(parseInt(position / 60) + ':' + (s >= 10 ? s : ('0' + s)));
	    s = parseInt(duration % 60);
	    remainingTime.html(parseInt(duration / 60) + ':' + (s >= 10 ? s : ('0' + s)));
	    progressMiddle.attr('width', width);
	}
	return {
	    reset : function() {
		clearInterval(interval);
		elapsedTime.html('00:00');
		progressMiddle.attr('width', 0);
	    },
	    start : function() {
		interval = setInterval(function() {
		    updateBar()
		}, 300);
	    },
	    stop : function() {
		clearInterval(interval);
	    },
	    setDuration : function(d) {
		duration = d;
	    }
	}
    }();

    // volume draggable
    var volumeControl = function() {
	var min = 20;
	var max = 102;
	var left = 102;
	return {
	    init : function() {
		dragVolume.mousedown(function(e) {
		    var startX = e.pageX;
		    var realLeft = left;
		    $(document).mousemove(function(e) {
			var currentX = e.pageX;
			var t = currentX - startX + left;
			var rt = t < 20 ? 20 : (t > 102 ? 102 : t);
			// console.log(startX+','+currentX+','+t+','+realLeft);
			if (rt != realLeft) {
			    dragVolume.attr("style", "left:" + rt + "px;");
			}
			realLeft = rt;
			var volume = parseInt((realLeft - min) / (max - min) * 100);
			curVolume = volume;
			current.sound.setVolume(curVolume);
			return false;
		    });
		    $(document).mouseup(function() {
			left = realLeft;
			$(this).off('mousemove');
			$(this).off('mouseup');
			return false;
		    });
		    return false;
		})
	    }
	}
    }();

    /*
     * ---------------------------------------------------------------------
     * ------------------------- private function -------------------------
     * ---------------------------------------------------------------------
     */
    var loadBarMusicInfo = function(info) {
	var coverImg = info.song.coverImg || "/s/img/no_album_art.jpg";
	var songName = info.song.name || 'Unknown';
	var artist = info.song.artist || 'Unknown';
	var album = info.song.album || 'Unknown';
	$('.playerBarArt').attr('src', coverImg);
	$('.playerBarSong').html(songName);
	$('.playerBarArtist').html(artist);
	$('.playerBarAlbum').html(album);

	$('.songTitle').html(songName);
	$('.artistSummary').html(artist);
	$('.albumTitle').html(album);

	$('.artistBio > .heading').html('About ' + artist);

	// push slide
	var slideC = $('#stationSlides1844608319562125962');
	var currentSlide = $('#stationSlides1844608319562125962 .slide:first-child');

	currentSlide.find('.art').attr('src', coverImg).show();
	currentSlide.find('.noart').hide();

	currentSlide.find('.current').fadeIn();
	currentSlide.find('.previous').fadeOut();
	var prevSlide = currentSlide.next();
	if (prevSlide) {
	    prevSlide.find('.current').hide();
	    prevSlide.find('.previous').fadeIn();
	}

	var widthstr = slideC.css('width');
	var width = parseInt(widthstr.replace('px', ''));
	width += 215;
	slideC.css('width', width + 'px');
	var slidehtml = [
		'<div class="slide" style="height: 215px; width: 215px;">',
		'<div>',
		'<img style="display: block;" class="noart" src="/s/img/no_album_art.jpg" width="215" height="215" />',
		'<img style="display: none;" src="" class="art" width="215" height="215" />',
		'<img style="display: none;" class="treatment current" src="/s/img/content-area/albumart-treatment-current.png" width="215" height="215">',
		'<img style="display: none;" class="treatment previous" src="/s/img/content-area/albumart-treatment-previous.png" width="215" height="215">',
		'</div>', '</div>' ].join('');
	slideC.prepend(slidehtml);

	var leftstr = slideC.css('left');
	var left = parseInt(leftstr.replace('px', ''));
	left -= 215;
	slideC.animate({
	    'left' : left + 'px'
	}, 1000);

	// add lyc
	$('.lyricsText').html(info.song.lyric || "Lyrics currently not avaliable");

	// init thumb class
	loadThumbInfo(info.thumb);
    }

    /**
     * 加载thumb info
     */
    var loadThumbInfo = function(thumb) {
	if (thumb == 0) {
	    thumbUpButton.removeClass('indicator');
	    thumbDownButton.removeClass('indicator');
	    tiredButton.removeClass('indicator');
	}
	if (thumb == UP) {
	    thumbUpButton.addClass('indicator');
	    thumbDownButton.removeClass('indicator');
	    tiredButton.removeClass('indicator');
	} else if (thumb == DOWN) {
	    thumbUpButton.removeClass('indicator');
	    thumbDownButton.addClass('indicator');
	    tiredButton.removeClass('indicator');
	} else if (thumb == TIRED) {
	    thumbUpButton.removeClass('indicator');
	    thumbDownButton.removeClass('indicator');
	    tiredButton.addClass('indicator');
	}
    }

    /**
     * 下一首歌曲播放前重置，各种状态，包括进度条，暂停按钮等
     */
    var resetProperties = function(json) {
	// 进度条重置
	progressBar.reset();
	progressBar.setDuration(json.song.duration);
	progressBar.start(json.song.duration);
	//
	playButton.hide();
	pauseButton.show();
    }
    /**
     * jump to next song
     */
    var nextSong = function() {
	if (status != 0) {
	    current.sound.destruct();
	    // resetPlay();
	    status = 0;
	}
	var arr = [];
	for ( var i in selectedListId) {
	    if (selectedListId[i])
		arr.push(i);
	}
	$.ajax({
	    async : false,
	    url : "/fm/predict",
	    data : {
		plid : arr,
		r : new Date().getTime()
	    },
	    success : function(json, statusCode) {
		if (json.err_no == 0) {
		    var sound = soundManager.createSound({
			id : 'song-' + json.song.id,
			url : json.song.streamUrl,
		    });
		    json.sound = sound;
		    loadBarMusicInfo(json);// 加载音乐信息，lrc等
		    // 分享内容更新
		    __refreshShareInfo(json);
		    // 重置页面按钮状态
		    resetProperties(json);

		    history.push(json);
		    current = json;
		    sound.play({
			volume : curVolume,
			onfinish : function() {
			    nextSong();
			}
		    });
		    status = 1;
		} else {
		    alert("internal server error!");
		}
	    },
	    error : function() {
		// nextSong();
	    },
	    dataType : "json"
	})
    }

    /**
     * 播放页面传递的歌曲
     */
    var playInitSong = function() {
	if (status != 0) {
	    current.sound.destruct();
	    // resetPlay();
	    status = 0;
	}
	var arr = [];
	for ( var i in selectedListId) {
	    if (selectedListId[i])
		arr.push(i);
	}
	$.ajax({
	    async : false,
	    url : "/fm/song",
	    data : {
		id : initSong,
	    },
	    success : function(json, statusCode) {
		if (json.err_no == 0) {
		    var sound = soundManager.createSound({
			id : 'song-' + json.song.id,
			url : json.song.streamUrl,
		    });
		    json.sound = sound;
		    loadBarMusicInfo(json);// 加载音乐信息，lrc等
		    // 分享内容更新
		    __refreshShareInfo(json);
		    // 重置页面按钮状态
		    resetProperties(json);

		    history.push(json);
		    current = json;
		    sound.play({
			volume : curVolume,
			onfinish : function() {
			    doneInitSong = true;
			    nextSong();
			}
		    });
		    status = 1;
		} else {
		    alert("internal server error!");
		}
	    },
	    error : function() {
		// nextSong();
	    },
	    dataType : "json"
	})
    }

    var __refreshShareInfo = function(json) {
	window._bd_share_config.common = {
	    bdUrl : "http://en.360.fm/#" + json.song.id,
	    bdPic : (json.song.coverImg || ""),
	    bdText : "我正在360.fm听《" + json.song.name + "》",
	    bdDesc : "最好听的欧美音乐在这里等你。",
	    bdStyle : "2"
	}
    }

    /**
     * 各种按钮功能初始化
     */
    var initBtnAction = function() {
	// init play/pause button
	pauseButton.click(function(e) {
	    if (!current)
		return false;
	    current.sound.pause();
	    status = 2;
	    pauseButton.hide();
	    playButton.show();
	    e.preventDefault();
	});
	// init button
	playButton.click(function(e) {
	    if (!current)
		return false;
	    current.sound.resume();
	    status = 1;
	    playButton.hide();
	    pauseButton.show();
	    e.preventDefault();
	});
	skipButton.click(function(e) {
	    nextSong();
	    e.preventDefault();
	});
	volumeButton.hover(function(e) {
	    volumeBg.show();
	});
	volumeBg.hover(null, function(e) {
	    volumeBg.fadeOut();
	});

	// 初始化button
	volumeControl.init();

	// init thumbup down
	var thumbBtnAction = function(thumb) {
	    if (current) {
		$.ajax({
		    async : false,
		    url : "/fm/" + thumb + "?sid=" + current.song.id + "&r=" + new Date().getTime(),
		    success : function(json, statusCode) {
			if (json.err_no == 0) {
			    loadThumbInfo(json.thumb);
			    current.thumb = json.thumb;
			    for ( var i in playLists) {
				var playList = playLists[i];
				if (playList.type == UP && json.thumbup_num >= 0) {
				    playList.songCount = json.thumbup_num;
				}
				if (playList.type == DOWN && json.thumbdown_num >= 0) {
				    playList.songCount = json.thumbdown_num;
				}
				if (playList.type == TIRED && json.tired_num >= 0) {
				    playList.songCount = json.tired_num;
				}
				if (playList.element)
				    $(playList.element).find(".stationNameText").html(
					    playList.name + ' [' + playList.songCount + ']');
			    }
			    // 如果踩了就跳下一首
			    if (json.thumb == 0 && thumb == 'thumbdown') {
				nextSong();
			    }
			} else {
			    alert("internal server error!");
			}
		    },
		    error : function() {
			alert("internal server error!");
		    },
		    complete : function() {
		    },
		    dataType : "json"
		})
	    }
	}
	// 顶
	thumbUpButton.click(function(e) {
	    e.preventDefault();
	    if (!user)
		return false;
	    thumbUpButton.off();
	    thumbBtnAction("thumbup")
	    thumbUpButton.on("click", function(e) {
		e.preventDefault();
		thumbBtnAction("thumbup")
	    });
	});
	tiredButton.click(function(e) {
	    e.preventDefault();
	    if (!user)
		return false;
	    tiredButton.off();
	    thumbBtnAction("tired")
	    tiredButton.on("click", function(e) {
		e.preventDefault();
		thumbBtnAction("tired")
	    });
	});
	// 踩
	thumbDownButton.click(function(e) {
	    e.preventDefault();
	    if (!user)
		return false;
	    thumbDownButton.off();
	    thumbBtnAction("thumbdown")
	    thumbDownButton.on("click", function(e) {
		e.preventDefault();
		thumbBtnAction("thumbdown")
	    });
	});
	// logout menu
	$('.user_activator').click(function(e) {
	    $('#user_menu_dd').toggle();
	    e.preventDefault();
	});
	// logout button
	$('.signoutMenuItem').click(function(e) {
	    $.ajax({
		async : false,
		url : "/logout?r=" + new Date().getTime(),
		success : function(json, statusCode) {
		    if (json.err_no == 0) {
			user = null;
			$('.userName').html('');
			$('.notAnonymousUser').hide();
			$('.anonymousUser').show();
			playLists = null;
			destroyPlayList();
		    } else {
			alert("internal server error!");
		    }
		},
		error : function() {
		    alert("internal server error!");
		},
		complete : function() {
		},
		dataType : "json"
	    })
	    e.preventDefault();
	});
    }
    /**
     * playlist 加载
     */
    var showPlayList = function(playLists) {
	if (!playLists)
	    return;
	for ( var i in playLists) {
	    var playList = playLists[i];
	    var checked = '';
	    if (playList.songCount != 0 || playList.type == DOWN) {
		checked = 'checked';
		selectedListId[playList.id] = true;
	    }
	    var plHtmlArr = [
		    '<div class="stationListItem">',
		    '<ul class="stationName">',
		    '<li class="shuffleStationLabel"><span data-plid="' + playList.id + '" class="checkbox ' + checked
			    + '"></span>',
		    '<input checked="checked" value="' + playList.id
			    + '" class="shuffleStation styled" type="checkbox">',
		    '<div class="stationNameText " title="' + playList.name + '">' + playList.name + ' ['
			    + playList.songCount + ']</div></li>', '</ul>', '</div>' ];
	    var itemElement = $(plHtmlArr.join(''));
	    playList.element = itemElement;
	    $('#stationList').append(itemElement);
	}
	$('#stationList span').click(function(e) {
	    var plid = $(this).attr('data-plid');
	    for ( var i in playLists) {
		if (playLists[i].id == plid) {
		    var playList = playLists[i];
		    if (playList.songCount == 0 && playList.type != DOWN) {
			return;
		    }
		}
	    }
	    if (!playList)
		return;
	    if ($(this).hasClass('checked')) {
		$(this).removeClass('checked');
		selectedListId[plid] = false;
	    } else {
		$(this).addClass('checked');
		selectedListId[plid] = true;
	    }
	    e.preventDefault();
	})
    }

    /**
     * playlist 消除
     */
    var destroyPlayList = function() {
	$('#stationList .stationListItem').each(function(i) {
	    if (i > 0)
		$(this).remove();
	});
    }

    /**
     * 是否登陆，并获取用户信息
     */
    var checkLogin = function() {
	$.ajax({
	    async : false,
	    url : "/my?r=" + new Date().getTime(),
	    success : function(json, statusCode) {
		if (json.err_no == 0) {
		    if (json.login == true) {
			user = json.user;
			$('.userName').html(user.nickname);
			$('.anonymousUser').hide();
			$('.notAnonymousUser').show();
			if (json.playLists) {
			    playLists = json.playLists;
			    showPlayList(json.playLists);
			}
		    }
		} else {
		    alert("internal server error!");
		}
	    },
	    error : function() {
		alert("internal server error!");
	    },
	    complete : function() {
	    },
	    dataType : "json"
	})
    }

    var getInitSong = function() {
	var url = window.location.href;
	var i;
	if ((i = url.indexOf("#")) != -1 && i < url.length - 1) {
	    var str = url.substr(i + 1);
	    initSong = parseInt(str);
	}
    }

    return {
	nextSong : function() {
	    nextSong()
	},
	current : function() {
	    return current;
	},
	init : function() {
	    getInitSong();
	    // check login status
	    checkLogin();
	    // init setup SM2 and player action
	    soundManager.setup({
		url : '/s/swf/',
		onready : function() {
		    if (initSong > 0 && !doneInitSong) {
			playInitSong()
		    } else {
			nextSong();
		    }
		    initBtnAction();
		},
		ontimeout : function() {
		}
	    });
	},
	checkLogin : function() {
	    checkLogin();
	}
    }
};
$(function() {
    $('.ico__qq')
	    .click(
		    function(e) {
			window
				.open(
					"https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101033103&redirect_uri=http%3A%2F%2Fen.360.fm%2F3rd%2Fsuccess&scope=get_user_info&state=login",
					"_blank", "height=450,width=700,menubar=no,top=150,left=300");
			e.preventDefault();
		    });
    $('.ico__douban')
	    .click(
		    function(e) {
			window
				.open(
					"https://www.douban.com/service/auth2/auth?response_type=code&client_id=0cddf221f4e7e2bd2699d665deb7a837&redirect_uri=http%3A%2F%2Fen.360.fm%2F3rd%2Fdouban&scope=douban_basic_common,music_basic_r&state=login",
					"_blank", "height=450,width=700,menubar=no,top=150,left=300");
			e.preventDefault();
		    });
    $('.ico__sina')
	    .click(
		    function(e) {
			window
				.open(
					"https://api.weibo.com/oauth2/authorize?client_id=1901684633&redirect_uri=http%3A%2F%2Fen.360.fm%2F3rd%2Fweibo&scope=follow_app_official_microblog&state=login",
					"_blank", "height=450,width=700,menubar=no,top=150,left=300");
			e.preventDefault();
		    });
    index = construct();
    index.init();
})

// 第三方登陆子窗口调用
var login = function() {
    index.checkLogin();
}