var construct = function() {
	// ------------------------常量------------------------
	var pauseButton = $('.pauseButton');
	var playButton = $('.playButton');
	var skipButton = $('.skipButton');
	var volumeButton = $('.volumeButton');
	var volumeBg = $('.volumeBackground')
	var dragVolume = $('.volumeKnob');
	var thumbUpButton = $('.thumbUpButton');
	var thumbDownButton = $('.thumbDownButton');
	var progressMaxWidth = 232;
	// ------------------------private 变量------------------------
	// user
	var user = null;
	var playLists = [];
	var selectedListId = {};
	var current = null;
	var status = 0; // 0 停止 1 播放 2暂停
	var history = [];
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
			elapsedTime.html(parseInt(position / 60) + ':'
					+ (s >= 10 ? s : ('0' + s)));
			s = parseInt(duration % 60);
			remainingTime.html(parseInt(duration / 60) + ':'
					+ (s >= 10 ? s : ('0' + s)));
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
		$('.lyricsText').html(info.song.lyric || "暂无歌词");

		// init thumb class
		loadThumbInfo(info.thumb);
	}
	
	/**
	 * 加载专辑info
	 */
	var loadThumbInfo = function(thumb) {
		if (thumb == 0) {
			thumbUpButton.removeClass('indicator');
			thumbDownButton.removeClass('indicator');
		} else if (thumb == 1) {
			thumbUpButton.addClass('indicator');
			thumbDownButton.removeClass('indicator');
		} else if (thumb == 2) {
			thumbUpButton.removeClass('indicator');
			thumbDownButton.addClass('indicator');
		}
	}

	var resetProperties = function() {

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
						id : json.song.id,
						url : json.song.streamUrl,
					});
					json.sound = sound;
					resetProperties();
					loadBarMusicInfo(json);// 加载音乐信息，lrc等
					// 分享内容更新
					window._bd_share_config.common.bdText = "我正在360.fm听《"
							+ json.song.name + "》";
					window._bd_share_config.common.bdDesc = "猛击到在360fm听《"
							+ json.song.name + "》";
					window._bd_share_config.common.bdUrl = "";
					window._bd_share_config.common.bdPic = json.song.coverImg
							|| "";
					// 进度条重置
					progressBar.reset();
					progressBar.setDuration(json.song.duration);
					progressBar.start(json.song.duration);

					history.push(json);
					current = json;
					sound.play({
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
				nextSong();
			},
			dataType : "json"
		})
	}

	/**
	 * 各种按钮功能初始化
	 */
	var initBtnAction = function() {
		// init play/pause button
		pauseButton.click(function() {
			if (!current)
				return false;
			current.sound.pause();
			status = 2;
			pauseButton.hide();
			playButton.show();
		});
		// init button
		playButton.click(function() {
			if (!current)
				return false;
			current.sound.resume();
			status = 1;
			playButton.hide();
			pauseButton.show();
		});
		skipButton.click(function() {
			nextSong();
		});
		volumeButton.hover(function() {
			volumeBg.show();
		});
		volumeBg.hover(null, function() {
			volumeBg.fadeOut();
		});
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
						$(document).mousemove(
								function(e) {
									var currentX = e.pageX;
									var t = currentX - startX + left;
									var rt = t < 20 ? 20 : (t > 102 ? 102 : t);
									// console.log(startX+','+currentX+','+t+','+realLeft);
									if (rt != realLeft) {
										dragVolume.attr("style", "left:" + rt
												+ "px;");
									}
									realLeft = rt;
									var volume = parseInt((realLeft - min)
											/ (max - min) * 100);
									current.sound.setVolume(volume);
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
		volumeControl.init();

		// init thumbup down
		var thumbBtnAction = function(thumb) {
			if (thumb != 1 && thumb != 2) {
				return;
			}
			if (current) {
				if (current.thumb != thumb) {
					$.ajax({
						async : false,
						url : "/fm/thumb" + (thumb == 1 ? 'up' : 'down')
								+ "?sid=" + current.song.id + "&r="
								+ new Date().getTime(),
						success : function(json, statusCode) {
							if (json.err_no == 0) {
								loadThumbInfo(thumb);
								current.thumb = thumb;
							} else {
								alert("internal server error!");
							}
						},
						error : function() {
							alert("internal server error!");
						},
						complete : function() {
							console.log(current);
						},
						dataType : "json"
					})
				}
			}
		}
		thumbUpButton.click(function() {
			if (!user)
				return false;
			thumbUpButton.off();
			thumbBtnAction(1)
			thumbUpButton.on("click", function() {
				thumbBtnAction(1)
			});
		});
		thumbDownButton.click(function() {
			if (!user)
				return false;
			thumbDownButton.off();
			thumbBtnAction(2)
			thumbDownButton.on("click", function() {
				thumbBtnAction(2)
			});
		});
	}
	/**
	 * playlist 加载
	 */
	var showPlayList = function(playLists) {
		if (!playLists)
			return;
		for ( var i in playLists) {
			var checked = '';
			if (playLists[i].type != 3) {
				checked = 'checked';
				selectedListId[playLists[i].id] = true;
			}
			var plHtmlArr = [
					'<div class="stationListItem">',
					'<ul class="stationName">',
					'<li class="shuffleStationLabel"><span data-plid="'
							+ playLists[i].id + '" class="checkbox ' + checked
							+ '"></span>',
					'<input checked="checked" value="'
							+ playLists[i].id
							+ '" class="shuffleStation styled" type="checkbox">',
					'<div class="stationNameText " title="' + playLists[i].name
							+ '">' + playLists[i].name + '</div></li>',
					'</ul>', '</div>' ];
			$('#stationList').append(plHtmlArr.join(''));
		}
		$('#stationList span').click(function() {
			if ($(this).hasClass('checked')) {
				$(this).removeClass('checked');
				var plid = $(this).attr('data-plid');
				selectedListId[plid] = false;
			} else {
				$(this).addClass('checked');
				var plid = $(this).attr('data-plid');
				selectedListId[plid] = true;
			}
		})
	}

	/**
	 * 是否登陆，并获取用户信息
	 */
	var checkLogin = function() {
		$.ajax({
			async : false,
			url : "/fm/my?r=" + new Date().getTime(),
			success : function(json, statusCode) {
				if (json.err_no == 0) {
					if (json.login == true) {
						user = json.user;
						$('.userName').html(user.email);
						playLists = json.playLists;
						showPlayList(playLists);
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

	return {
		nextSong : function() {
			nextSong()
		},
		init : function() {
			// check login status
			checkLogin();
			// init setup SM2 and player action
			soundManager.setup({
				url : '/s/swf/',
				onready : function() {
					nextSong();
					initBtnAction();
				},
				ontimeout : function() {
				}
			});
		}
	}
};
$(function() {
	var index = construct();
	index.init();
})