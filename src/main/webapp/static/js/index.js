var construct = function() {
	// 常量
	var pauseButton = $('.pauseButton');
	var playButton = $('.playButton');
	var skipButton = $('.skipButton');
	var volumeButton = $('.volumeButton');
	var volumeBg = $('.volumeBackground')
	var dragVolume = $('.volumeKnob');
	var progressMaxWidth = 232;
	// private 变量
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

	// private function
	var loadBarMusicInfo = function(info) {
		$('.playerBarArt').attr('src', info.album.coverImg);
		$('.playerBarSong').html(info.song.name);
		$('.playerBarArtist').html(info.song.artist);
		$('.playerBarAlbum').html(info.song.album);

		$('.songTitle').html(info.song.name);
		$('.artistSummary').html(info.song.artist);
		$('.albumTitle').html(info.song.album);

		$('.artistBio > .heading').html('About ' + info.song.artist);

		// push slide
		var slideC = $('#stationSlides1844608319562125962');
		var currentSlide = $('#stationSlides1844608319562125962 .slide:first-child');
		if (info.album.coverImg) {
			currentSlide.find('.art').attr('src', info.album.coverImg).show();
			currentSlide.find('.noart').hide();
		}
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
		$('.lyricsText').html(info.song.lyric);

		// push left history
		var historyHtml = [
				'<div class="stationListItem">',
				'<ul class="stationName">',
				'<li class="shuffleStationLabel shuffleStationLabelCurrent">',
				'<div class="stationNameText" title="' + info.song.artist
						+ '">' + info.song.name + '</div></li>', '</ul>',
				'</div>' ];
		$('#stationList li:first-child').removeClass(
				"shuffleStationLabelCurrent");
		$('#stationList').prepend(historyHtml.join(''));

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
		$.ajax({
			async : false,
			url : "/fm/predict?r=" + new Date().getTime(),
			success : function(json, statusCode) {
				if (json.err_no == 1) {
					var sound = soundManager.createSound({
						id : json.song.id,
						url : json.song.streamUrl,
					});
					json.sound = sound;
					resetProperties();
					loadBarMusicInfo(json);// 加载音乐信息，lrc等
					progressBar.reset();// 进度条重置
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
				alert("net work error!");
			},
			dataType : "json"
		})
	}

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
	}

	return {
		nextSong : function() {
			nextSong()
		},
		init : function() {
			// init setup SM2
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